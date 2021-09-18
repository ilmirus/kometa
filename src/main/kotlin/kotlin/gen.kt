package kometa.kotlin

import kometa.kotlin.ast.Element

interface ExtrasMap {
    fun extrasBefore(v: Element): List<Element.Extra>
    fun extrasWithin(v: Element): List<Element.Extra>
    fun extrasAfter(v: Element): List<Element.Extra>

    fun docComment(v: Element): Element.Extra.Comment? {
        for (extra in extrasBefore(v)) if (extra is Element.Extra.Comment && extra.text.startsWith("/**")) return extra
        return null
    }
}

open class Writer(
    val app: Appendable = StringBuilder(),
    val extrasMap: ExtrasMap? = null,
    val includeExtraBlankLines: Boolean = extrasMap == null
) : Visitor() {

    protected var indent = ""
    protected var elemsSinceLastLine = emptyList<Element>()

    protected fun endLine() = also {
        val elems = elemsSinceLastLine
        elemsSinceLastLine = emptyList()
        elems.forEach { it.writeExtrasLineEnd() }
        append('\n')
    }
    protected fun line() = endLine()
    protected fun line(str: String) = append(indent).append(str).endLine()
    protected fun lineBegin(str: String = "") = append(indent).append(str)
    protected fun lineEnd(str: String = "") = append(str).endLine()
    protected fun append(ch: Char) = also { app.append(ch) }
    protected fun append(str: String) = also { app.append(str) }
    protected fun appendName(name: String) =
        if (name.shouldEscapeIdent) append("`$name`") else append(name)
    protected fun appendNames(names: List<String>, sep: String) = also {
        names.forEachIndexed { index, name ->
            if (index > 0) append(sep)
            appendName(name)
        }
    }
    protected fun <T> indented(fn: () -> T): T = run {
        indent += "    "
        fn().also { indent = indent.dropLast(4) }
    }

    fun write(v: Element) { visit(v, v) }

    override fun visit(v: Element?, parent: Element) {
        v?.writeExtrasBefore()
        v?.apply {
            when (this) {
                is Element.File -> {
                    if (anns.isNotEmpty()) childAnns().line()
                    childrenLines(pkg, extraEndLines = 1)
                    childrenLines(imports, extraEndLines = 1)
                    childrenLines(decls, extraMidLines = 1)
                }
                is Element.Package ->
                    childMods().append("package ").appendNames(names, ".")
                is Element.Import -> {
                    append("import ").appendNames(names, ".")
                    if (wildcard) append(".*") else if (alias != null) append(" as ").appendName(alias)
                }
                is Element.Decl.Class -> childMods().also {
                    append(when (kind) {
                        Element.Decl.Class.Kind.CLASS -> "class "
                        Element.Decl.Class.Kind.ENUM_CLASS -> "enum class "
                        Element.Decl.Class.Kind.INTERFACE -> "interface "
                        Element.Decl.Class.Kind.OBJECT -> "object "
                        Element.Decl.Class.Kind.COMPANION_OBJECT -> "companion object "
                    })
                    if (kind != Element.Decl.Class.Kind.COMPANION_OBJECT || name != "Companion") appendName(name)
                    bracketedChildren(typeParams)
                    children(primaryConstructor)
                    if (parents.isNotEmpty()) {
                        append(" : ")
                        children(parentAnns)
                        children(parents, ", ")
                    }
                    childTypeConstraints(typeConstraints)
                    if (members.isNotEmpty()) lineEnd(" {").indented {
                        // First, do all the enum entries if there are any
                        val enumEntries = members.map { it as? Element.Decl.EnumEntry }.takeWhile { it != null }
                        enumEntries.forEachIndexed { index, enumEntry ->
                            lineBegin().also { children(enumEntry) }
                            when (index) {
                                members.size - 1 -> lineEnd()
                                enumEntries.size - 1 -> lineEnd(";").line()
                                else -> lineEnd(",")
                            }
                        }
                        // Now the rest of the members
                        childrenLines(members.drop(enumEntries.size), extraMidLines = 1)
                    }.lineBegin("}")

                    // As a special case, if an object is nameless and bodyless, we should give it an empty body
                    // to avoid ambiguities with the next item
                    // See: https://youtrack.jetbrains.com/issue/KT-25581
                    if ((kind == Element.Decl.Class.Kind.COMPANION_OBJECT ||
                                kind == Element.Decl.Class.Kind.OBJECT) && name == "Companion" && members.isEmpty())
                        append("{}")
                }
                is Element.Decl.Structured.Parent.Class.Parent.CallConstructor -> {
                    children(type)
                    parenChildren(args)
                }
                is Element.Decl.Structured.Parent.Class.Parent.Type -> {
                    children(type)
                    if (by != null) append(" by ").also { children(by) }
                }
                is Element.Decl.Structured.Class.PrimaryConstructor -> {
                    if (mods.isNotEmpty()) append(" ").also { childMods(newlines = false).append("constructor") }
                    parenChildren(params)
                }
                is Element.Decl.Init ->
                    append("init ").also { children(block) }
                is Element.Decl.Function -> {
                    childMods().append("fun")
                    if (name != null || typeParams.isNotEmpty() || receiverType != null) append(' ')
                    bracketedChildren(typeParams, " ")
                    if (receiverType != null) children(receiverType).append(".")
                    name?.also { appendName(it) }
                    bracketedChildren(paramTypeParams)
                    parenChildren(params)
                    if (type != null) append(": ").also { children(type) }
                    childTypeConstraints(typeConstraints)
                    if (body != null) append(' ').also { children(body) }
                }
                is Element.Decl.Func.Function.Param -> {
                    if (mods.isNotEmpty()) childMods(newlines = false).append(' ')
                    if (isVal == true) append("val ") else if (isVal == false) append("var ")
                    appendName(name)
                    if (type != null) append(": ").also { children(type) }
                    if (default != null) append(" = ").also { children(default) }
                }
                is Element.Decl.Func.Body.Function.Body.Block ->
                    children(block)
                is Element.Decl.Func.Body.Function.Body.Expr ->
                    append("= ").also { children(expr) }
                is Element.Decl.Property -> {
                    childMods().append(if (readOnly) "val " else "var ")
                    bracketedChildren(typeParams, " ")
                    if (receiverType != null) children(receiverType).append('.')
                    childVars(vars)
                    childTypeConstraints(typeConstraints)
                    if (expr != null) {
                        if (delegated) append(" by ") else append(" = ")
                        children(expr)
                    }
                    if (getter != null) lineEnd().indented { children(getter) }
                }
                is Element.Decl.Property.Var -> {
                    appendName(name)
                    if (type != null) append(": ").also { children(type) }
                }
                is Element.Decl.Property.Accessors -> {
                    childrenLines(first)
                    if (second != null) childrenLines(second)
                }
                is Element.Decl.Property.Accessor.Get -> {
                    childMods().append("get")
                    if (body != null) {
                        append("()")
                        if (type != null) append(": ").also { children(type) }
                        append(' ').also { children(body) }
                    }
                }
                is Element.Decl.Property.Accessor.Set -> {
                    childMods().append("set")
                    if (body != null) {
                        append('(')
                        childMods(paramMods, newlines = false)
                        appendName(paramName ?: error("Missing setter param name when body present"))
                        if (paramType != null) append(": ").also { children(paramType) }
                        append(") ")
                        children(body)
                    }
                }
                is Element.Decl.TypeAlias -> {
                    childMods().append("typealias ").appendName(name)
                    bracketedChildren(typeParams).append(" = ")
                    children(type)
                }
                is Element.Decl.Constructor -> {
                    childMods().append("constructor")
                    parenChildren(params)
                    if (delegationCall != null) append(": ").also { children(delegationCall) }
                    if (block != null) append(' ').also { children(block) }
                }
                is Element.Decl.Constructor.DelegationCall ->
                    append(target.name.toLowerCase()).also { parenChildren(args) }
                is Element.Decl.EnumEntry -> {
                    childMods().appendName(name)
                    if (args.isNotEmpty()) parenChildren(args)
                    if (members.isNotEmpty()) lineEnd(" {").indented {
                        childrenLines(members, extraMidLines = 1)
                    }.lineBegin("}")
                }
                is Element.TypeParam -> {
                    childMods(newlines = false).appendName(name)
                    if (type != null) append(": ").also { children(type) }
                }
                is Element.TypeConstraint ->
                    childAnns(sameLine = true).appendName(name).append(": ").also { children(type) }
                is Element.TypeRef.Paren ->
                    append('(').also {
                        childModsBeforeType(type).also { children(type) }
                    }.append(')')
                is Element.TypeRef.Func -> {
                    if (receiverType != null) children(receiverType).append('.')
                    parenChildren(params).append(" -> ").also { children(type) }
                }
                is Element.TypeRef.Func.Param -> {
                    if (name != null) appendName(name).append(": ")
                    children(type)
                }
                is Element.TypeRef.Simple ->
                    children(pieces, ".")
                is Element.TypeRef.Simple.Piece ->
                    appendName(name).also { bracketedChildren(typeParams) }
                is Element.TypeRef.Nullable ->
                    children(type).append('?')
                is Element.TypeRef.Dynamic ->
                    append("dynamic")
                is Element.Type ->
                    childModsBeforeType(ref).also { children(ref) }
                is Element.ValueArg -> {
                    if (name != null) appendName(name).append(" = ")
                    if (asterisk) append('*')
                    children(expr)
                }
                is Element.Expr.If -> {
                    append("if (").also { children(condition) }.append(") ")
                    children(thenBody)
                    if (elseBody != null) append(" else ").also { children(elseBody) }
                }
                is Element.Expr.Try -> {
                    append("try ")
                    children(block)
                    if (catches.isNotEmpty()) children(catches, " ", prefix = " ")
                    if (finallyBlock != null) append(" finally ").also { children(finallyBlock) }
                }
                is Element.Expr.Try.Catch -> {
                    append("catch (")
                    childAnns(sameLine = true)
                    appendName(varName).append(": ").also { children(varType) }.append(") ")
                    children(block)
                }
                is Element.Expr.For -> {
                    append("for (")
                    childAnns(sameLine = true)
                    childVars(vars).append(" in ").also { children(inExpr) }.append(") ")
                    children(body)
                }
                is Element.Expr.While -> {
                    if (!doWhile) append("while (").also { children(condition) }.append(") ").also { children(body) }
                    else append("do ").also { children(body) }.append(" while (").also { children(condition) }.append(')')
                }
                is Element.Expr.BinaryOp -> {
                    // Some operations don't have separators
                    val noSep = oper is Element.Expr.BinaryOp.Oper.Token && oper.token.let {
                        it == Element.Expr.BinaryOp.Token.RANGE || it == Element.Expr.BinaryOp.Token.DOT ||
                                it == Element.Expr.BinaryOp.Token.DOT_SAFE
                    }
                    children(listOf(lhs, oper, rhs), if (noSep) "" else " ")
                }
                is Element.Expr.BinaryOp.Oper.Infix ->
                    append(str)
                is Element.Expr.BinaryOp.Oper.Token ->
                    append(token.str)
                is Element.Expr.UnaryOp ->
                    if (isPrefix) children(oper, expr) else children(expr, oper)
                is Element.Expr.UnaryOp.Oper ->
                    append(token.str)
                is Element.Expr.TypeOp ->
                    children(listOf(lhs, oper, rhs), " ")
                is Element.Expr.TypeOp.Oper ->
                    append(token.str)
                is Element.Expr.DoubleColonRef.Callable -> {
                    if (recv != null) children(recv)
                    append("::").appendName(name)
                }
                is Element.Expr.DoubleColonRef.Class -> {
                    if (recv != null) children(recv)
                    append("::class")
                }
                is Element.Expr.DoubleColonRef.Recv.Expr ->
                    children(expr)
                is Element.Expr.DoubleColonRef.Recv.Type ->
                    children(type).append("?".repeat(questionMarks))
                is Element.Expr.Paren ->
                    append('(').also { children(expr) }.append(')')
                is Element.Expr.StringTmpl ->
                    if (raw) append("\"\"\"").also { children(elems) }.append("\"\"\"")
                    else append('"').also { children(elems) }.append('"')
                is Element.Expr.StringTmpl.Elem.Regular ->
                    append(str)
                is Element.Expr.StringTmpl.Elem.ShortTmpl ->
                    append('$').appendName(str)
                is Element.Expr.StringTmpl.Elem.UnicodeEsc ->
                    append("\\u").append(digits)
                is Element.Expr.StringTmpl.Elem.RegularEsc ->
                    append('\\').append(when (char) {
                        '\b' -> 'b'
                        '\n' -> 'n'
                        '\t' -> 't'
                        '\r' -> 'r'
                        else -> char
                    })
                is Element.Expr.StringTmpl.Elem.LongTmpl ->
                    append("\${").also { children(expr) }.append('}')
                is Element.Expr.Const ->
                    append(value)
                is Element.Expr.Brace -> {
                    append('{')
                    if (params.isNotEmpty()) append(' ').also { children(params, ", ", "", " ->") }
                    children(block).append('}')
                }
                is Element.Expr.Brace.Param -> {
                    childVars(vars)
                    if (destructType != null) append(": ").also { children(destructType) }
                }
                is Element.Expr.This -> {
                    append("this")
                    if (label != null) append('@').appendName(label)
                }
                is Element.Expr.Super -> {
                    append("super")
                    if (typeArg != null) append('<').also { children(typeArg) }.append('>')
                    if (label != null) append('@').appendName(label)
                }
                is Element.Expr.When -> {
                    append("when")
                    if (subject != null) append('(').also { children(subject) }.append(')')
                    lineEnd(" {").indented { childrenLines(entries) }.lineBegin("}")
                }
                is Element.Expr.When.Entry -> {
                    if (conds.isEmpty()) append("else")
                    else children(conds, ", ")
                    append(" -> ").also { children(body) }
                }
                is Element.Expr.When.Cond.Expr ->
                    children(expr)
                is Element.Expr.When.Cond.In -> {
                    if (not) append('!')
                    append("in ").also { children(expr) }
                }
                is Element.Expr.When.Cond.Is -> {
                    if (not) append('!')
                    append("is ").also { children(type) }
                }
                is Element.Expr.Object -> {
                    append("object")
                    if (parents.isNotEmpty()) append(" : ").also { children(parents, ", ") }
                    if (members.isEmpty()) append(" {}") else lineEnd(" {").indented {
                        childrenLines(members, extraMidLines = 1)
                    }.lineBegin("}")
                }
                is Element.Expr.Throw ->
                    append("throw ").also { children(expr) }
                is Element.Expr.Return -> {
                    append("return")
                    if (label != null) append('@').appendName(label)
                    if (expr != null) append(' ').also { children(expr) }
                }
                is Element.Expr.Continue -> {
                    append("continue")
                    if (label != null) append('@').appendName(label)
                }
                is Element.Expr.Break -> {
                    append("break")
                    if (label != null) append('@').appendName(label)
                }
                is Element.Expr.CollLit ->
                    children(exprs, ", ", "[", "]")
                is Element.Expr.Name ->
                    appendName(name)
                is Element.Expr.Labelled ->
                    appendName(label).append("@ ").also { children(expr) }
                is Element.Expr.Annotated ->
                    childAnnsBeforeExpr(expr).also { children(expr) }
                is Element.Expr.Call -> {
                    children(expr)
                    bracketedChildren(typeArgs)
                    if (args.isNotEmpty() || lambda == null) parenChildren(args)
                    if (lambda != null) append(' ').also { children(lambda) }
                }
                is Element.Expr.Call.TrailLambda -> {
                    if (anns.isNotEmpty()) childAnns(sameLine = true).append(' ')
                    if (label != null) appendName(label).append("@ ")
                    children(func)
                }
                is Element.Expr.ArrayAccess -> {
                    children(expr)
                    children(indices, ", ", "[", "]")
                }
                is Element.Expr.AnonymousFunction ->
                    children(function)
                is Element.Expr.Property ->
                    children(decl)
                is Element.Block -> {
                    // Special case, no braces if the parent is a brace
                    if (parent is Element.Expr.Brace) {
                        if (stmts.isNotEmpty()) lineEnd().indented { childrenLines(stmts) }.lineBegin()
                    } else if (stmts.isEmpty()) append("{}")
                    else lineEnd("{").indented { childrenLines(stmts) }.lineBegin("}")
                }
                is Element.Stmt.Decl -> {
                    children(decl)
                }
                is Element.Stmt.Expr ->
                    children(expr)
                is Element.Modifier.AnnotationSet -> {
                    append('@')
                    if (target != null) append(target.name.toLowerCase()).append(':')
                    if (anns.size == 1) children(anns)
                    else children(anns, " ", "[", "]")
                }
                is Element.Modifier.AnnotationSet.Annotation -> {
                    appendNames(names, ".")
                    bracketedChildren(typeArgs)
                    if (args.isNotEmpty()) parenChildren(args)
                }
                is Element.Modifier.Lit ->
                    append(keyword.name.toLowerCase())
                else ->
                    error("Unrecognized node type: $this")
            }
        }
        v?.writeExtrasAfter()
        v?.also { elemsSinceLastLine += it }
    }

    protected open fun Element.writeExtrasBefore() {
        if (extrasMap == null) return
        // Write everything before
        writeExtras(extrasMap.extrasBefore(this))
    }

    protected open fun Element.writeExtrasAfter() {
        if (extrasMap == null) return
        // Write everything after that doesn't start a line or end a line
        writeExtras(extrasMap.extrasAfter(this).takeWhile {
            it is Element.Extra.Comment && !it.startsLine && !it.endsLine
        })
    }

    protected open fun Element.writeExtrasLineEnd() {
        if (extrasMap == null) return
        // Write everything after the first non-line starter/ender
        writeExtras(extrasMap.extrasAfter(this).dropWhile {
            it is Element.Extra.Comment && !it.startsLine && !it.endsLine
        })
    }

    protected open fun Element.writeExtras(extras: List<Element.Extra>) {
        extras.forEach {
            when (it) {
                is Element.Extra.BlankLines -> {
                    (2..it.count).forEach { line() }
                    lineEnd().lineBegin()
                }
                is Element.Extra.Comment -> {
                    if (it.startsLine && it.endsLine) lineEnd(it.text).lineBegin() else {
                        if (!it.startsLine) append(' ')
                        append(it.text)
                        if (!it.endsLine) append(' ')
                    }
                }
            }
        }
    }

    protected fun Element.childTypeConstraints(v: List<Element.TypeConstraint>) = this@Writer.also {
        if (v.isNotEmpty()) append(" where ").also { children(v, ", ") }
    }

    protected fun Element.childVars(vars: List<Element.Decl.Property.Var?>) =
        if (vars.size == 1) {
            if (vars.single() == null) append('_') else children(vars)
        } else {
            append('(')
            vars.forEachIndexed { index, v ->
                if (v == null) append('_') else children(v)
                if (index < vars.size - 1) append(", ")
            }
            append(')')
        }

    // Ends with newline (or space if sameLine) if there are any
    protected fun Element.WithAnnotations.childAnns(sameLine: Boolean = false) = this@Writer.also {
        if (anns.isNotEmpty()) (this@childAnns as Element).apply {
            if (sameLine) children(anns, " ", "", " ")
            else anns.forEach { ann -> lineBegin().also { children(ann) }.lineEnd() }
        }
    }

    protected fun Element.WithAnnotations.childAnnsBeforeExpr(expr: Element.Expr) = this@Writer.also {
        if (anns.isNotEmpty()) {
            // As a special case, if there is a trailing annotation with no args and expr is paren,
            // then we need to add an empty set of parens ourselves
            val lastAnn = anns.lastOrNull()?.anns?.singleOrNull()?.takeIf { it.args.isEmpty() }
            val shouldAddParens = lastAnn != null && expr is Element.Expr.Paren
            (this as Element).children(anns, " ")
            if (shouldAddParens) append("()")
            append(' ')
        }
    }

    // Ends with newline if last is ann or space is last is mod or nothing if empty
    protected fun Element.WithModifiers.childMods(newlines: Boolean = true) =
        (this@childMods as Element).childMods(mods, newlines)

    protected fun Element.childMods(mods: List<Element.Modifier>, newlines: Boolean = true) =
        this@Writer.also {
            if (mods.isNotEmpty()) this@childMods.apply {
                mods.forEachIndexed { index, mod ->
                    children(mod)
                    if (newlines && (mod is Element.Modifier.AnnotationSet ||
                                mods.getOrNull(index + 1) is Element.Modifier.AnnotationSet))
                        lineEnd().lineBegin()
                    else append(' ')
                }
            }
        }

    protected fun Element.WithModifiers.childModsBeforeType(ref: Element.TypeRef) = this@Writer.also {
        if (mods.isNotEmpty()) {
            // As a special case, if there is a trailing annotation with no args and the ref has a paren which is a paren
            // type or a non-receiver fn type, then we need to add an empty set of parens ourselves
            val lastAnn = (mods.lastOrNull() as? Element.Modifier.AnnotationSet)?.anns?.
            singleOrNull()?.takeIf { it.args.isEmpty() }
            val shouldAddParens = lastAnn != null &&
                    (ref is Element.TypeRef.Paren || (ref is Element.TypeRef.Func && (
                            ref.receiverType == null || ref.receiverType.ref is Element.TypeRef.Paren)))
            (this as Element).children(mods, " ")
            if (shouldAddParens) append("()")
            append(' ')
        }
    }

    protected inline fun Element.children(vararg v: Element?) = this@Writer.also { v.forEach { visitChildren(it) } }

    // Null list values are asterisks
    protected fun Element.bracketedChildren(v: List<Element?>, appendIfNotEmpty: String = "") = this@Writer.also {
        if (v.isNotEmpty()) {
            append('<')
            v.forEachIndexed { index, node ->
                if (index > 0) append(", ")
                if (node == null) append('*') else children(node)
            }
            append('>').append(appendIfNotEmpty)
        }
    }

    protected fun Element.parenChildren(v: List<Element?>) = children(v, ", ", "(", ")")

    protected fun Element.childrenLines(v: Element?, extraMidLines: Int = 0, extraEndLines: Int = 0) =
        this@Writer.also { if (v != null) childrenLines(listOf(v), extraMidLines, extraEndLines) }

    protected fun Element.childrenLines(v: List<Element?>, extraMidLines: Int = 0, extraEndLines: Int = 0) =
        this@Writer.also {
            v.forEachIndexed { index, node ->
                lineBegin().also { children(node) }
                if (stmtRequiresEmptyBraceSetBeforeLineEnd(node, v.getOrNull(index + 1))) append(" {}")
                if (stmtRequiresSemicolonSetBeforeLineEnd(node, v.getOrNull(index + 1))) append(';')
                lineEnd()
                if (includeExtraBlankLines)
                    (0 until if (index == v.size - 1) extraEndLines else extraMidLines).forEach { line() }
            }
        }

    protected fun stmtRequiresEmptyBraceSetBeforeLineEnd(v: Element?, next: Element?): Boolean {
        // As a special case, if this is a local memberless class decl stmt and the next line is a paren
        // or ann+paren, we have to explicitly provide an empty brace set
        // See: https://youtrack.jetbrains.com/issue/KT-25578
        // TODO: is there a better place to do this?
        if (v !is Element.Stmt.Decl || v.decl !is Element.Decl.Class || v.decl.members.isNotEmpty() ||
            v.decl.kind != Element.Decl.Class.Kind.CLASS) return false
        if (next !is Element.Stmt.Expr || (next.expr !is Element.Expr.Paren &&
                    (next.expr !is Element.Expr.Annotated || next.expr.expr !is Element.Expr.Paren))) return false
        return true
    }

    protected fun stmtRequiresSemicolonSetBeforeLineEnd(v: Element?, next: Element?) =
        stmtHasModifierLocalVarDeclAmbiguity(v, next) || stmtHasTrailingLambdaAmbiguity(v, next)

    protected fun stmtHasModifierLocalVarDeclAmbiguity(v: Element?, next: Element?): Boolean {
        // As a special case, if there is just a name stmt, and it is a modifier, and the next stmt is
        // a decl, we need a semicolon
        // See: https://youtrack.jetbrains.com/issue/KT-25579
        // TODO: is there a better place to do this
        if (v !is Element.Stmt.Expr || v.expr !is Element.Expr.Name || next !is Element.Stmt.Decl) return false
        val name = v.expr.name.toUpperCase()
        return Element.Modifier.Keyword.values().any { it.name == name }
    }

    protected fun stmtHasTrailingLambdaAmbiguity(v: Element?, next: Element?): Boolean {
        // As a special case, if there is a function call stmt w/ no trailing lambda followed by a brace
        // stmt, the call needs a semicolon
        if (v !is Element.Stmt.Expr || v.expr !is Element.Expr.Call || v.expr.lambda != null) return false
        return next is Element.Stmt.Expr && next.expr is Element.Expr.Brace
    }

    protected fun Element.children(v: List<Element?>, sep: String = "", prefix: String = "", postfix: String = "") =
        this@Writer.also {
            append(prefix)
            v.forEachIndexed { index, t ->
                visit(t, this)
                if (index < v.size - 1) append(sep)
            }
            append(postfix)
        }

    // We accept lots of false positives to be simple and not have to bring in JVM dep to do accurate check
    protected val String.shouldEscapeIdent get() =
        KEYWORDS.contains(this) ||
                all { it == '_' } ||
                first() in '0'..'9' ||
                any { it !in 'a'..'z' && it !in 'A'..'Z' && it !in '0'..'9' && it != '_' }

    companion object {
        protected val KEYWORDS = setOf(
            "as", "break", "class", "continue", "do", "else", "false", "for", "fun", "if", "in", "interface",
            "is", "null", "object", "package", "return", "super", "this", "throw", "true", "try", "typealias",
            "typeof", "val", "var", "when", "while"
        )

        fun write(v: Element, extrasMap: ExtrasMap? = null) =
            write(v, StringBuilder(), extrasMap).toString()
        fun <T: Appendable> write(v: Element, app: T, extrasMap: ExtrasMap? = null) =
            app.also { Writer(it, extrasMap).write(v) }
    }
}
