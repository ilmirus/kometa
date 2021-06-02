package kometa.ast

import kometa.MatchItem
import kometa.util.cast
import java.util.regex.Pattern
import java.util.regex.PatternSyntaxException

typealias TItem = MatchItem<Char, AST.AstNode>

object AST {
    /**
     * Base class for Abstract Syntax Tree nodes for the K-OMeta parser.
     */
    sealed class AstNode {
        /**
         * Children of this node.
         */
        var children: List<AstNode> = emptyList()
            protected set

        /**
         * Match results for this node.
         */
        var items: List<TItem> = emptyList()
            protected set

        /**
         * Get the text that this node covers.
         */
        open fun getText(): String = items.joinToString(separator = "") { it.inputs.joinToString(separator = "") }

        /**
         * Get the text covered by the match item.
         *
         * @param item Match item.
         */
        fun getText(item: TItem): String = item.inputs.joinToString(separator = "")

        fun getAllChildren(): Iterable<AstNode> = children.flatMap { listOf(it) + it.getAllChildren() }

        inline fun <reified T : AstNode> ofType(): Iterable<T> = sequence {
            if (this is T) yield(this)
            yieldAll(children.filterIsInstance<T>())
        }.toList()
    }

    data class Fail(val message: String) : AstNode() {
        constructor(messageNode: AstNode): this(messageNode.getText().trim())
    }

    object Any : AstNode()

    data class Look(val body: AstNode) : AstNode() {
        init {
            children = listOf(body)
        }
    }

    data class Not(val body: AstNode) : AstNode() {
        init {
            children = listOf(body)
        }
    }

    data class Or(val left: AstNode, val right: AstNode) : AstNode() {
        init {
            children = listOf(left, right)
        }
    }

    data class And(val left: AstNode, val right: AstNode) : AstNode() {
        init {
            children = listOf(left, right)
        }
    }

    data class Star(val body: AstNode) : AstNode() {
        init {
            children = listOf(body)
        }
    }

    data class Plus(val body: AstNode) : AstNode() {
        init {
            children = listOf(body)
        }
    }

    data class Ques(val body: AstNode) : AstNode() {
        init {
            children = listOf(body)
        }
    }

    data class CallOrVar(val name: TItem) : AstNode() {
        init {
            items = listOf(name)
        }
    }

    data class Call(val rule: TItem, var params: List<AstNode>) : AstNode() {
        var captures: List<TItem> = emptyList()

        init {
            items = listOf(rule)
        }
    }

    data class Idfr(val text: TItem, val genericParams: TItem? = null) : AstNode() {
        init {
            items = listOfNotNull(text, genericParams)
        }
    }

    data class Code(val text: TItem) : AstNode() {
        init {
            items = listOf(text)
        }
    }

    data class Regexp(val text: TItem) : AstNode() {
        val re: Regex by lazy { Regex(getText().trim()) }

        init {
            items = listOf(text)
        }

        companion object {
            fun isValid(s: String): Boolean = try {
                Pattern.compile(s)
                true
            } catch (e: PatternSyntaxException) {
                false
            }
        }
    }

    data class InputClass(val inputs: Iterable<AstNode?>) : AstNode() {
        val chars: MutableList<String> = arrayListOf()
    }

    data class ClassRange(val item: TItem, val inputs: Iterable<Char>) : AstNode() {
        init {
            items = listOf(item)
        }

        companion object {
            fun getChar(node: AstNode?): Char? {
                if (node != null) {
                    val str = node.getText().trim('\'').trim()
                    // TODO: unespace str
                    return str.firstOrNull()
                }
                return null
            }
        }
    }

    data class Bind(val body: AstNode, val varName: TItem) : AstNode() {
        init {
            children = listOf(body)
            items = listOf(varName)
        }
    }

    data class Cond(val body: AstNode, val expression: TItem) : AstNode() {
        init {
            children = listOf(body)
            items = listOf(expression)
        }
    }

    data class Act(val body: AstNode, val expression: TItem) : AstNode() {
        init {
            children = listOf(body)
            items = listOf(expression)
        }
    }

    data class Args(val params: AstNode?, val body: AstNode) : AstNode() {
        init {
            children = listOf(body)
        }
    }

    data class Rule private constructor(val body: AstNode) : AstNode() {
        private var nameStr: String? = null

        var name: TItem? = null
            private set
        var override: String? = null
            private set

        constructor(name: TItem, body: AstNode, override: String) : this(body) {
            this.name = name
            this.override = override

            items = listOf(name)
            children = listOf(body)
        }

        constructor(name: String, body: AstNode) : this(body) {
            this.nameStr = name

            children = listOf(body)
        }

        fun getName(): String = nameStr ?: getText().trim().also { nameStr = it }
    }

    data class Grammar(
        val name: TItem,
        val tinput: TItem,
        val tresult: TItem,
        val baseClass: TItem?,
        val rules: Iterable<Rule>
    ) : AstNode() {
        init {
            items = listOfNotNull(name, tinput, tresult, baseClass)
            children = rules.toList()
        }
    }

    data class Import(val name: TItem) : AstNode() {
        init {
            items = listOf(name)
        }
    }

    data class ImportList(val imports: Iterable<AstNode>) : AstNode()

    data class GrammarFile(val importList: ImportList?, val grammar: Grammar) : AstNode() {
        init {
            children = listOf(grammar)
        }

        constructor(preamble: AstNode?, grammar: AstNode) : this(preamble?.cast(), grammar.cast())
    }
}
