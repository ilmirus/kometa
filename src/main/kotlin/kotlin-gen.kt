package kometa.generator

import kometa.MatchItem
import kometa.ast.AST
import java.time.Instant
import java.time.ZoneId
import kotlin.jvm.internal.Ref

/**
 * Generates Kotlin code for an KOMeta parser from an abstract syntax tree.
 */
class KotlinGen(val grammarFile: AST.GrammarFile, val namespace: String, val addTimestamp: Boolean = true) {
    private var gName = ""
    private var gBase = ""
    private var tInput = ""
    private var tResult = ""
    private var tItem = ""

    private val ruleNodes = arrayListOf<AST.Rule>()
    private val ruleBodies = linkedMapOf<String, AST.AstNode>()
    private val overrides = linkedMapOf<String, String>()
    private val regexps = linkedMapOf<String, String>()

    private var indent = 0

    fun generate(srcName: String): String {
        analyze(grammarFile, null)
        return generateGrammarFile()
    }

    fun analyze(node: AST.AstNode, currentRule: AST.Rule?) {
        when (node) {
            is AST.Grammar -> {
                // get grammar name & generic parameters
                tInput = node.getText(node.tinput).trim()
                tResult = node.getText(node.tresult).trim()
                gName = node.getText(node.name).trim()
                tItem = "_${gName}_Item"
                gBase = if (node.baseClass != null) node.getText(node.baseClass).trim() else ""

                if (gBase.isEmpty()) {
                    gBase = "kometa.Matcher<$tInput, $tResult>"
                }
            }
            is AST.Args -> {
                // also analyze arguments (because they are not children)
                if (node.params != null) {
                    analyze(node.params, currentRule)
                }
            }
            is AST.Rule -> {
                // collect rule bodies
                val currentRule = node
                ruleNodes += currentRule

                val ruleName = node.getName()

                if (!currentRule.override.isNullOrEmpty()) {
                    overrides[ruleName] = currentRule.override!!
                }

                val oldBody = ruleBodies[ruleName]
                ruleBodies[ruleName] =
                    if (oldBody != null) AST.Or(oldBody, currentRule.body)
                    else currentRule.body
            }
            is AST.InputClass -> {
                // collect input classes
                for (child in node.inputs) {
                    when (child) {
                        is AST.Code -> {
                            node.chars += child.getText().trim().trim('(', ')').trim()
                        }
                        is AST.ClassRange -> {
                            for (ch in child.inputs) {
                                node.chars += "\\u" + Integer.toHexString(ch.code).padStart(4, '0')
                            }
                        }
                    }
                }
            }
            is AST.Call -> {
                // hoist rule bodies inside calls
                hoistCalledDisjunctions(currentRule, node)
            }
        }

        for (child in node.children) {
            analyze(child, currentRule)
        }
    }

    fun generateGrammarFile(): String {
        val buffer = StringBuffer()
        with(buffer) {
            writeLine("//")

            if (addTimestamp) {
                writeLine("// KOMeta $gName Parser; generated ${Instant.now().atZone(ZoneId.of("UTC"))} UTC")
            } else {
                writeLine("// KOMeta $gName Parser")
            }

            writeLine("//")
            appendLine()

            if (namespace.isNotEmpty()) {
                writeLine("package $namespace")
                appendLine()
            }

            generateImports(buffer)

            generateTypealiases(buffer)

            generateGrammar(buffer)
        }
        return buffer.toString()
    }

    private fun generateImports(buffer: StringBuffer) {
        with(buffer) {
            appendLine("import kometa.*")
            writeLine("import kometa.util.*")
            writeLine("import kotlin.jvm.internal.Ref")
            appendLine()

            for (imp in (grammarFile.importList?.imports ?: emptyList())) {
                writeLine("import ${imp.getText()}")
            }
            if ((grammarFile.importList?.imports?.toList() ?: emptyList()).isNotEmpty()) {
                appendLine()
            }
        }
    }

    private fun generateTypealiases(buffer: StringBuffer) {
        with(buffer) {
            writeLine("typealias _${gName}_Inputs = Iterable<$tInput>")
            writeLine("typealias _${gName}_Results = Iterable<$tResult>")
            writeLine("typealias $tItem = MatchItem<$tInput, $tResult>")
            writeLine("typealias _${gName}_Args = Iterable<$tItem>")
            writeLine("typealias _${gName}_Memo = MatchState<$tInput, $tResult>")
            writeLine("typealias _${gName}_Rule = Production<$tInput, $tResult>")
            writeLine("typealias _${gName}_Base = Matcher<$tInput, $tResult>")
            appendLine()
        }
    }

    private fun generateGrammar(buffer: StringBuffer) {
        with(buffer) {
            writeLine("class $gName(handleLeftRecursion: Boolean = true) : $gBase(handleLeftRecursion) {")
            indented {
                writeLine("init {")
                indented {
                    writeLine("terminals = setOf(")
                    indented {
                        for (terminal in ruleBodies.keys.filter { isTerminal(it) }.sorted().distinct()) {
                            writeLine("\"$terminal\",")
                        }
                    }
                    writeLine(")")
                }
                writeLine("}")

                for ((name, node) in ruleBodies) {
                    generateRule(buffer, name, node)
                }

                if (regexps.isNotEmpty()) {
                    writeLine("companion object {")
                    indented {
                        for ((name, value) in regexps) {
                            writeLine("val $name: Regex = Regex(\"$value\")")
                        }
                    }
                    writeLine("}")
                }
            }
            writeLine("}")
        }
    }

    private fun isTerminal(
        ruleName: String,
        involved: MutableSet<String> = mutableSetOf(),
        memo: MutableMap<String, Boolean> = hashMapOf()
    ): Boolean {
        memo[ruleName]?.let { return it }

        if (ruleName in involved) {
            memo[ruleName] = false
            return false
        }

        involved += ruleName

        if (ruleBodies[ruleName] == null) return false

        val calls = ruleBodies[ruleName]!!.getAllChildren().mapNotNull {
            when (it) {
                is AST.Call -> it.rule.results.single()!!.getText()
                is AST.CallOrVar -> it.name.results.single()!!.getText()
                else -> null
            }
        }.filterNot { it.isEmpty() }.sorted().distinct()

        for (call in calls) {
            if (!isTerminal(ruleName, involved, memo)) {
                memo[ruleName] = false
                return false
            }
        }

        memo[ruleName] = true
        return true
    }

    fun generateRule(buffer: StringBuffer, ruleName: String, body: AST.AstNode) {
        with(buffer) {
            appendLine()
            writeLine("""${if (ruleName in overrides) "override " else ""}fun $ruleName(_memo: _${gName}_Memo, __index: Int, _args: _${gName}_Args?) {""")
            indented {
                writeLine("val _index = Ref.IntRef()")
                writeLine("_index.element = __index")
                appendLine()

                val sb = StringBuffer()
                val variables = mutableSetOf<String>()
                val indices = mutableSetOf<Pair<Int, String>>()
                val n = Ref.IntRef()
                val useArgs = Ref.BooleanRef()
                indent += 3
                generateBody(sb, variables, indices, body, n, useArgs, false)
                indent -= 3

                writeLine("val _arg_index = Ref.IntRef()")
                writeLine("val _arg_input_index = Ref.IntRef()")
                appendLine()

                for (v in variables) {
                    writeLine("var $v: $tItem? = null")
                }
                if (variables.isNotEmpty()) appendLine()

                for ((index, name) in indices) {
                    writeLine("// $name $index")
                    writeLine("var _start_i$index = _index.element")
                    if (name == "STAR" || name == "PLUS") {
                        writeLine("val _inp$index = arrayListOf<$tInput?>()")
                        writeLine("val _res$index = arrayListOf<$tResult?>()")
                    }
                    appendLine()
                }

                writeLine("var _label = -1")
                writeLine("while(true) {")
                indented {
                    writeLine("when(_label) {")
                    indented {
                        writeLine("-1 -> {")
                    }
                }

                append(sb.toString())

                indented {
                    indented {
                        indented {
                            writeLine("break")
                        }
                        writeLine("}")
                    }
                    writeLine("}")
                }
                writeLine("}")
            }
            writeLine("}")
            appendLine()
        }
    }

    fun generateBody(
        buffer: StringBuffer,
        vars: MutableSet<String>,
        indices: MutableSet<Pair<Int, String>>,
        node: AST.AstNode,
        n: Ref.IntRef,
        useArgs: Ref.BooleanRef,
        matchArgs: Boolean
    ) {
        with(buffer) {
            val outerN = n.element;

            // and/or header
            when (node) {
                is AST.And -> generateAndPre(buffer, indices, node, n.element, matchArgs)
                is AST.Or -> generateOrPre(buffer, indices, node, n.element, matchArgs)
                is AST.Look -> generateLookPre(buffer, indices, node, n.element, matchArgs)
                is AST.Not -> generateNotPre(buffer, indices, node, n.element, matchArgs)
                is AST.Star -> generateStarPre(buffer, indices, node, n.element, matchArgs)
                is AST.Plus -> generatePlusPre(buffer, indices, node, n.element, matchArgs)
                is AST.Cond -> generateCondPre(buffer, indices, node, n.element, matchArgs)
                is AST.Args -> generateArgsPre(buffer, vars, indices, node, n, useArgs, matchArgs)
            }

            // generate in post order
            for (child in node.children) {
                val curN = n.element++

                // children
                generateBody(buffer, vars, indices, child, n, useArgs, matchArgs)

                // shortcut and/or
                if (curN == outerN && node is AST.And) {
                    generateAndShortcut(buffer, node, outerN, matchArgs)
                } else if (curN == outerN && node is AST.Or) {
                    generateOrShortcut(buffer, node, outerN, matchArgs)
                }
            }

            when (node) {
                is AST.Code -> generateLiteralPost(buffer, node, outerN, matchArgs)
                is AST.Regexp -> generateRegexpPost(buffer, node, outerN, matchArgs)
                is AST.Fail -> generateFailPost(buffer, node, outerN, matchArgs)
                is AST.Any -> generateAnyPost(buffer, node, outerN, matchArgs)
                is AST.Look -> generateLookPost(buffer, node, outerN, matchArgs)
                is AST.Not -> generateNotPost(buffer, node, outerN, matchArgs)
                is AST.Or -> generateOrPost(buffer, node, outerN, matchArgs)
                is AST.And -> generateAndPost(buffer, node, outerN, matchArgs)
                is AST.Star -> generateStarPost(buffer, node, outerN, matchArgs)
                is AST.Plus -> generatePlusPost(buffer, node, outerN, matchArgs)
                is AST.Ques -> generateQuesPost(buffer, node, outerN, matchArgs)
                is AST.CallOrVar -> generateCallOrVarPost(buffer, vars, node, outerN, matchArgs)
                is AST.Call -> generateCallPost(buffer, vars, node, outerN, matchArgs)
                is AST.Bind -> generateBindPost(buffer, vars, node, outerN, matchArgs)
                is AST.Cond -> generateCondPost(buffer, node, outerN, matchArgs)
                is AST.Act -> generateActPost(buffer, node, outerN, matchArgs)
                is AST.InputClass -> generateInputClassPost(buffer, node, outerN, matchArgs)
                is AST.Args -> generateArgsPost(buffer, node, outerN, matchArgs)
                else -> {
                    error("Unknown AST node type ${node::class.java.canonicalName}")
                }
            }
        }
    }

    private fun index(matchArgs: Boolean): String = if (matchArgs) "_arg_index.element" else "_index.element"

    private fun results(matchArgs: Boolean): String = if (matchArgs) "_memo.argResults" else "_memo.results"

    private fun generateLiteralPost(buffer: StringBuffer, node: AST.Code, n: Int, matchArgs: Boolean) {
        with(buffer) {
            val literal = node.getText().trimBraces()
            writeLine("// LITERAL $literal")

            if (matchArgs) {
                writeLine("_ParseLiteralArgs(_memo, _arg_index, _arg_input_index, $literal, _args)")
            } else {
                val isCharMatcher = tInput == "Char"

                if (isCharMatcher && literal.startsWith('"')) {
                    writeLine("_ParseLiteralString(_memo, _index, $literal)")
                } else if (isCharMatcher && literal.startsWith("'")) {
                    writeLine("_ParseLiteralChar(_memo, _index, $literal)")
                } else {
                    writeLine("_ParseLiteralObject(_memo, _index, $literal)")
                }
            }

            appendLine()
        }
    }

    private fun generateRegexpPost(buffer: StringBuffer, node: AST.Regexp, n: Int, matchArgs: Boolean) {
        with(buffer) {
            val isCharMatcher = tInput == "Char"
            if (!isCharMatcher) error("Can only use regular expressions when matching characters.")
            if (matchArgs) error("Cannot use a regular expression in rule arguments.")

            val text = node.getText().trim(' ', '/').replace("\"", "\"\"")

            var name = regexps[text]
            if (name == null) {
                name = "_re${regexps.size}"
                regexps[text] = name
            }

            writeLine("// REGEXP $text")
            writeLine("_ParseRegexp(_memo, _index, $name)")
            appendLine()
        }
    }

    private fun generateInputClassPost(buffer: StringBuffer, node: AST.InputClass, n: Int, matchArgs: Boolean) {
        with(buffer) {
            writeLine("// INPUT CLASS")

            if (matchArgs) {
                writeLine("_ParseInputClassArgs(_memo, _arg_index, _arg_input_index, _args, listOf(${node.chars.joinToString() { "'$it'" }}))")
            } else {
                writeLine("_ParseInputClass(_memo, _index, listOf(${node.chars.joinToString() { "'$it'" }}))")
            }

            appendLine()
        }
    }

    private fun generateFailPost(buffer: StringBuffer, node: AST.Fail, n: Int, matchArgs: Boolean) {
        with(buffer) {
            writeLine("// FAIL $n")

            writeLine("${results(matchArgs)}.push(null)")

            if (node.message.isNotEmpty()) {
                writeLine("_memo.clearErrors()")
                writeLine("_memo.addError(${node.message})")
            }

            appendLine()
        }
    }

    private fun generateAnyPost(buffer: StringBuffer, node: AST.Any, n: Int, matchArgs: Boolean) {
        with(buffer) {
            writeLine("// ANY")

            if (matchArgs) {
                writeLine("_ParseAnyArgs(_memo, _arg_index, _arg_input_index, _args)")
            } else {
                writeLine("_ParseAny(_memo, _index)")
            }

            appendLine()
        }
    }

    private fun generateLookPre(
        buffer: StringBuffer,
        indices: MutableSet<Pair<Int, String>>,
        node: AST.Look,
        n: Int,
        matchArgs: Boolean
    ) {
        with(buffer) {
            indices += n to "LOOK"
            writeLine("// LOOK $n")

            writeLine("_start_i$n = ${index(matchArgs)}")

            appendLine()
        }
    }

    private fun generateLookPost(buffer: StringBuffer, node: AST.Look, n: Int, matchArgs: Boolean) {
        with(buffer) {
            writeLine("// LOOK $n")

            writeLine("val _r$n = ${results(matchArgs)}.pop()")
            if (matchArgs) {
                writeLine("_memo.argResults.push(_r$n)")
            } else {
                writeLine("_memo.results.push(if (_r$n != null) $tItem(_start_i$n, _memo.input) else null)")
            }
            writeLine("${index(matchArgs)} = _start_i$n")

            appendLine()
        }
    }

    private fun generateNotPre(
        buffer: StringBuffer,
        indices: MutableSet<Pair<Int, String>>,
        node: AST.Not,
        n: Int,
        matchArgs: Boolean
    ) {
        indices += n to "NOT"
        with(buffer) {
            writeLine("// NOT $n")
            writeLine("_start_i$n = ${index(matchArgs)}")

            appendLine()
        }
    }

    private fun generateNotPost(buffer: StringBuffer, node: AST.Not, n: Int, matchArgs: Boolean) {
        with(buffer) {
            writeLine("// NOT $n")

            writeLine("val _r$n = ${results(matchArgs)}.pop()")
            if (matchArgs) {
                writeLine("_memo.argResults.push(if (_r$n == null) $tItem(_arg_index, _arg_index, _memo.input, emptyList(), false) else null)")
            } else {
                writeLine("_memo.results.push(if (_r$n == null) $tItem(_start_i$n, _memo.input) else null)")
            }
            writeLine("${index(matchArgs)} = _start_i$n")
        }
    }

    private fun generateOrPre(
        buffer: StringBuffer,
        indices: MutableSet<Pair<Int, String>>,
        node: AST.Or,
        n: Int,
        matchArgs: Boolean
    ) {
        indices += n to "OR"
        with(buffer) {
            writeLine("// OR $n")
            writeLine("_start_i$n = ${index(matchArgs)}")

            appendLine()
        }
    }

    private fun generateOrShortcut(buffer: StringBuffer, node: AST.Or, n: Int, matchArgs: Boolean) {
        with(buffer) {
            writeLine("// OR shortcut $n")

            writeLine("if (${results(matchArgs)}.peek() == null) {")
            indented {
                writeLine("${results(matchArgs)}.pop()")
                writeLine("${index(matchArgs)} = _start_i$n")
            }
            writeLine("} else {")
            indented {
                writeLine("_label = $n")
                writeLine("continue")
            }
            writeLine("}")

            appendLine()
        }
    }

    private fun generateOrPost(buffer: StringBuffer, node: AST.Or, n: Int, matchArgs: Boolean) {
        with(buffer) {
            writeLine("_label = $n")
            indent--
            writeLine("}")
            writeLine("// OR $n")
            writeLine("$n -> {")
            indent++
        }
    }

    private fun generateAndPre(
        buffer: StringBuffer,
        indices: MutableSet<Pair<Int, String>>,
        node: AST.And,
        n: Int,
        matchArgs: Boolean
    ) {
        indices += n to "AND"
        with(buffer) {
            writeLine("// AND $n")
            writeLine("_start_i$n = ${index(matchArgs)}")

            appendLine()
        }
    }

    private fun generateAndShortcut(buffer: StringBuffer, node: AST.And, n: Int, matchArgs: Boolean) {
        with(buffer) {
            writeLine("// AND shortcut $n")

            writeLine("if (${results(matchArgs)}.peek() == null) {")
            indented {
                writeLine("${results(matchArgs)}.push(null)")
                writeLine("_label = $n")
                writeLine("continue")
            }
            writeLine("}")

            appendLine()
        }
    }

    private fun generateAndPost(buffer: StringBuffer, node: AST.And, n: Int, matchArgs: Boolean) {
        with(buffer) {
            writeLine("_label = $n")
            indent--
            writeLine("}")
            writeLine("// AND $n")
            writeLine("$n -> {")
            indent++

            writeLine("val _r${n}_2 = ${results(matchArgs)}.pop()")
            writeLine("val _r${n}_1 = ${results(matchArgs)}.pop()")
            appendLine()
            writeLine("if (_r${n}_1 != null && _r${n}_2 != null) {")
            indented {
                if (matchArgs) {
                    writeLine("${results(matchArgs)}.push($tItem(_start_i$n, _arg_index.element, (_r${n}_1.inputs + _r${n}_2.inputs).filterNotNull(), (_r${n}_1.results + _r${n}_2.results).filterNotNull(), false))")
                } else {
                    writeLine("${results(matchArgs)}.push($tItem(_start_i$n, _index.element, _memo.input, (_r${n}_1.results + _r${n}_2.results).filterNotNull(), true))")
                }
            }
            writeLine("} else {")
            indented {
                writeLine("${results(matchArgs)}.push(null)")
                writeLine("${index(matchArgs)} = _start_i$n")
            }
            writeLine("}")

            appendLine()
        }
    }

    private fun generateStarPre(
        buffer: StringBuffer,
        indices: MutableSet<Pair<Int, String>>,
        node: AST.Star,
        n: Int,
        matchArgs: Boolean
    ) {
        indices += n to "STAR"
        with(buffer) {
            writeLine("// STAR $n")
            writeLine("_start_i$n = ${index(matchArgs)}")
            writeLine("_label = $n")

            indent--
            writeLine("}")
            writeLine("// STAR $n")
            writeLine("$n -> {")
            indent++
        }
    }

    private fun generateStarPost(buffer: StringBuffer, node: AST.Star, n: Int, matchArgs: Boolean) {
        with(buffer) {
            writeLine("// STAR $n")

            writeLine("val _r$n = ${results(matchArgs)}.pop()")
            writeLine("if (_r$n != null) {")
            indented {
                if (matchArgs) {
                    writeLine("_inp$n += _r$n.inputs")
                }
                writeLine("_res$n += _r$n.results")
                writeLine("_label = $n")
                writeLine("continue")
            }
            writeLine("} else {")
            indented {
                if (matchArgs) {
                    writeLine("_memo.argResults.push($tItem(_start_i$n, _arg_index.element, _inp$n.filterNotNull(), _res$n.filterNotNull(), false))")
                } else {
                    writeLine("_memo.results.push($tItem(_start_i$n, _index.element, _memo.input, _res$n.filterNotNull(), true))")
                }
            }
            writeLine("}")

            appendLine()
        }
    }

    private fun generatePlusPre(
        buffer: StringBuffer,
        indices: MutableSet<Pair<Int, String>>,
        node: AST.Plus,
        n: Int,
        matchArgs: Boolean
    ) {
        indices += n to "PLUS"

        with(buffer) {
            writeLine("// PLUS $n")
            writeLine("_start_i$n = ${index(matchArgs)}")
            writeLine("_label = $n")

            indent--
            writeLine("}")
            writeLine("// PLUS $n")
            writeLine("$n -> {")
            indent++
        }
    }

    private fun generatePlusPost(buffer: StringBuffer, node: AST.Plus, n: Int, matchArgs: Boolean) {
        with(buffer) {
            writeLine("// PLUS $n")

            writeLine("val _r$n = ${results(matchArgs)}.pop()")
            writeLine("if (_r$n != null) {")
            indented {
                if (matchArgs) {
                    writeLine("_inp$n += _r$n.inputs")
                }
                writeLine("_res$n += _r$n.results")
                writeLine("_label = $n")
                writeLine("continue")
            }
            writeLine("} else {")
            indented {
                writeLine("if (${index(matchArgs)} > _start_i$n) {")
                indented {
                    val input = if (matchArgs) "_inp$n.filterNotNull()" else "_memo.input"
                    writeLine("${results(matchArgs)}.push($tItem(_start_i$n, ${index(matchArgs)}, $input, _res$n.filterNotNull(), ${!matchArgs}))")
                }
                writeLine("} else {")
                indented {
                    writeLine("${results(matchArgs)}.push(null)")
                }
                writeLine("}")
            }
            writeLine("}")

            appendLine()
        }
    }

    private fun generateQuesPost(buffer: StringBuffer, node: AST.Ques, n: Int, matchArgs: Boolean) {
        with(buffer) {
            writeLine("// QUES $n")

            writeLine("if (${results(matchArgs)}.peek() == null) {")
            indented {
                writeLine("${results(matchArgs)}.pop()")
                writeLine("${results(matchArgs)}.push($tItem(${index(matchArgs)}, _memo.input))")
            }
            writeLine("}")
        }
    }

    private fun generateCondPre(
        buffer: StringBuffer,
        indices: MutableSet<Pair<Int, String>>,
        node: AST.Cond,
        n: Int,
        matchArgs: Boolean
    ) {
        indices += n to "COND"
        with(buffer) {
            writeLine("// COND $n")
            writeLine("_start_i$n = ${index(matchArgs)}")

            appendLine()
        }
    }

    private fun generateCondPost(buffer: StringBuffer, node: AST.Cond, n: Int, matchArgs: Boolean) {
        val condition = node.getText().trim()

        with(buffer) {
            writeLine("// COND $n")
            writeLine("val lambda$n: ($tItem) -> Boolean = { $condition }")
            writeLine("if (${results(matchArgs)}.peek() == null || !lambda$n(${results(matchArgs)}.peek()!!)) {")
            indented {
                writeLine("${results(matchArgs)}.pop()")
                writeLine("${results(matchArgs)}.push(null)")
                writeLine("${index(matchArgs)} = _start_i$n")
            }
            writeLine("}")

            appendLine()
        }
    }

    private fun generateActPost(buffer: StringBuffer, node: AST.Act, n: Int, matchArgs: Boolean) {
        val action = node.getText()

        with(buffer) {
            writeLine("// ACT $n")
            writeLine("val _r$n = ${results(matchArgs)}.peek()")
            writeLine("if (_r$n != null) {")
            indented {
                writeLine("${results(matchArgs)}.pop()")
                if (matchArgs) {
                    writeLine("_memo.argResults.push($tItem(_r$n.startIndex, _r$n.nextIndex, _r$n.inputs, _Thunk($action, _r$n), false))")
                } else {
                    writeLine("_memo.results.push($tItem(_r$n.startIndex, _r$n.nextIndex, _memo.input, _Thunk($action, _r$n), true))")
                }
            }
            writeLine("}")

            appendLine()
        }
    }

    private fun generateCallPost(
        buffer: StringBuffer,
        vars: MutableSet<String>,
        node: AST.Call,
        n: Int,
        matchArgs: Boolean
    ) {
        val name = node.rule.results.single()!!.getText().trim()

        if (matchArgs) error("$name: you are not allowed to call rules in argument patterns.")

        val isVar = name in vars

        with(buffer) {
            writeLine("// CALL $name")
            writeLine("val _start_i$n = ${index(matchArgs)}")
            writeLine("var _r$n: $tItem? = null")

            if (node.params.any()) {
                val actualParams = mutableSetOf<String>()
                generateActualParams(buffer, vars, node, n, name, actualParams)

                appendLine()
                writeLine("var _actual_args$n: _${gName}_Args = mutableListOf(${actualParams.joinToString { "$it!!" } })")
                writeLine("if (_args != null) {")
                indented {
                    writeLine("_actual_args$n += _args.drop(_arg_index.element)")
                }
                writeLine("}")

                writeLine("_r$n = _MemoCall(_memo, ${if (isVar) "$name?.productionName!!" else "\"$name\""}, _index.element, ${if (isVar) "$name?.production?.op!!" else "::$name"}, _actual_args$n)")
            } else {
                writeLine("_r$n = _MemoCall(_memo, ${if (isVar) "$name?.productionName!!" else "\"$name\""}, _index, ${if (isVar) "$name?.production?.op!!" else name}, if (_args != null) _args.drop(_arg_index.element) else null)")
            }
            appendLine()
            writeLine("if (_r$n != null) {")
            indented {
                writeLine("${index(matchArgs)} = _r$n.nextIndex")
            }
            writeLine("}")

            appendLine()
        }
    }

    private fun generateActualParams(
        buffer: StringBuffer,
        vars: MutableSet<String>,
        node: AST.Call,
        n: Int,
        name: String,
        actualParams: MutableSet<String>
    ) {
        var i = 0
        for (pnode in node.params) {
            var pstr = pnode.getText().trim()

            if (pnode is AST.CallOrVar || pnode is AST.Call) {
                actualParams += if (vars.contains(pstr)) pstr else "$tItem(Production(\"$pstr\", ::$pstr))"
            } else {
                pstr = pstr.trimBraces()
                if (pstr.isBlank()) {
                    actualParams += "$tItem(_arg${n}_$i)"
                    buffer.writeLine("val _arg${n}_$i = $pstr")
                }
                i++
            }
        }
        for (captured in node.captures) {
            actualParams += captured.inputs.joinToString("").trim()
        }

        if (actualParams.isEmpty() && node.params.any()) {
            error("Unable to process actual parameters for call to $name")
        }
    }

    private var nextHoistedRuleId = 0

    fun hoistCalledDisjunctions(currentRule: AST.Rule?, callNode: AST.Call) {
        val newParams = arrayListOf<AST.AstNode>()
        val captures = arrayListOf<MatchItem<Char, AST.AstNode>>()
        callNode.captures = captures

        for (oldParam in callNode.params) {
            if (oldParam is AST.And || oldParam is AST.Or) {
                var ruleBody = oldParam

                // find callorvars in the nested rule body, and match them to params of this current rule
                val outerArgs = currentRule?.body as? AST.Args
                if (outerArgs != null) {
                    val outerVarNames =
                        outerArgs.params?.ofType<AST.Bind>()?.map { it.varName.inputs.joinToString("") } ?: emptyList()
                    val closedVarNames =
                        ruleBody.ofType<AST.CallOrVar>().filter { it.name.inputs.joinToString("") in outerVarNames }
                            .map { it.items.first() }

                    if (closedVarNames.any()) {
                        var args: AST.AstNode = AST.Bind(AST.Any, closedVarNames.first())
                        captures += closedVarNames.first()

                        for (varName in closedVarNames.drop(1)) {
                            args = AST.And(args, AST.Bind(AST.Any, varName))
                            captures += varName
                        }
                        ruleBody = AST.Args(args, ruleBody)
                    }
                }

                val ruleName = "__nested_rule_${nextHoistedRuleId++}"
                val newRule = AST.Rule(ruleName, ruleBody)
                ruleNodes += newRule
                ruleBodies[ruleName] = ruleBody

                val nameItem = MatchItem<Char, AST.AstNode>(0, ruleName.length, emptyList())
                val newCall = AST.Call(nameItem, emptyList())
                newParams += newCall
            } else {
                newParams += oldParam
            }
        }

        callNode.params += newParams
    }

    private fun generateBindPost(
        buffer: StringBuffer,
        vars: MutableSet<String>,
        node: AST.Bind,
        n: Int,
        matchArgs: Boolean
    ) {
        val name = node.getText().trim()
        vars += name
        with(buffer) {
            writeLine("// BIND $name")
            writeLine("$name = ${results(matchArgs)}.peek()")

            appendLine()
        }
    }

    private fun generateCallOrVarPost(
        buffer: StringBuffer,
        vars: MutableSet<String>,
        node: AST.CallOrVar,
        n: Int,
        matchArgs: Boolean
    ) {
        val name = node.name.results.single()!!.getText().trim()
        with(buffer) {
            writeLine("// CALLORVAR $name")
            if (matchArgs) {
                if (name in vars) {
                    writeLine("val _r$n = _ParseLiteralArgs(_memo, _arg_index, _arg_input_index, name.inputs, _args)")
                    writeLine("if (_r$n != null) _arg_index.element = _r$n.nextIndex")
                } else {
                    error("$name: you are not allowed to call rules in argument patterns.")
                }
            } else {
                writeLine("var _r$n: $tItem? = null")
                if (name in vars) {
                    writeLine("if ($name?.production != null) {")
                    indented {
                        writeLine("_r$n = _MemoCall(_memo, $name.production!!.methodName, _index.element, $name.production!!.op, if (_args != null) _args.drop(_arg_index.element) else null)")
                    }
                    writeLine("} else {")
                    indented {
                        writeLine("_r$n = _ParseLiteralObj(_memo, _index, $name?.inputs)")
                    }
                    writeLine("}")
                } else {
                    writeLine("_r$n = _MemoCall(_memo, \"$name\", _index.element, ::$name, null)")
                }
                writeLine("if (_r$n != null) _index.element = _r$n.nextIndex")
            }
            appendLine()
        }
    }

    private fun generateArgsPre(
        buffer: StringBuffer,
        vars: MutableSet<String>,
        indices: MutableSet<Pair<Int, String>>,
        node: AST.Args,
        n: Ref.IntRef,
        useArgs: Ref.BooleanRef,
        matchArgs: Boolean
    ) {
        if (node.params == null) return

        indices += n.element to "ARGS"
        useArgs.element = true

        with(buffer) {
            writeLine("// ARGS ${n.element}")
            writeLine("_arg_index.element = 0")
            writeLine("_arg_input_index.element = 0")
            val outerN = n.element++
            indented {
                generateBody(buffer, vars, indices, node.params, n, useArgs, true)
            }
            writeLine("if (_memo.argResults.pop() == null) {")
            indented {
                writeLine("_memo.results.push(null)")
                writeLine("_label = $outerN")
                writeLine("continue")
            }
            writeLine("}")

            appendLine()
        }
    }

    private fun generateArgsPost(buffer: StringBuffer, node: AST.Args, n: Int, matchArgs: Boolean) {
        if (node.params == null) return

        with(buffer) {
            writeLine("_label = $n")
            indent--
            writeLine("}")
            writeLine("// ARGS $n")
            writeLine("$n -> {")
            indent++
            writeLine("_arg_input_index.element = _arg_index.element")

            appendLine()
        }
    }

    private fun String.trimBraces(): String =
        trim { it == ')' || it == '(' || it == '{' || it == '}' || it.isWhitespace() }

    private fun indented(op: () -> Unit) {
        indent++
        op()
        indent--
    }

    private fun StringBuffer.writeLine(s: String) {
        for (i in 0 until indent) {
            append("    ")
        }
        appendLine(s)
    }
}