package kometa.kotlin

import kometa.kotlin.ast.*
import kometa.kotlin.ast.Annotation

private inline fun <T> List<T>.delimeted(delimeter: () -> Unit, action: (T) -> Unit) {
    for (a in dropLast(1)) {
        action(a)
        delimeter()
    }
    action(last())
}

class KotlinGenVisitor : Visitor() {
    val buffer = StringBuffer()

    private var indentation = 0

    private fun indent() {
        for (i in 0 until indentation) {
            buffer.append("  ")
        }
    }

    private fun <T : Element> StringBuffer.appendDelimeted(
        collection: List<T>,
        prefix: String = "", suffix: String = "", delimeter: String = ", ",
        action: (T) -> Unit = { it.accept(this@KotlinGenVisitor) }
    ) {
        append(prefix)
        collection.delimeted({ append(delimeter) }) {
            action(it)
        }
        append(suffix)
    }

    private fun <T : Element> StringBuffer.appendDelimetedIfNotEmpty(
        collection: List<T>,
        prefix: String = "", suffix: String = "", delimeter: String = ", ",
        action: (T) -> Unit = { it.accept(this@KotlinGenVisitor) }
    ) {
        if (collection.isNotEmpty()) {
            appendDelimeted(collection, prefix, suffix, delimeter, action)
        }
    }

    override fun visitFile(file: File) {
        for (ann in file.anns) {
            ann.accept(this)
            buffer.appendLine()
        }
        file.pkg?.let {
            it.accept(this)
            buffer.appendLine()
        }
        for (import in file.imports) {
            import.accept(this)
        }
        buffer.appendLine()
        for (declaration in file.declarations) {
            declaration.accept(this)
            buffer.appendLine()
        }
    }

    override fun visitElement(element: Element) {
        element.acceptChildren(this)
    }

    override fun visitPackage(pkg: Package) {
        buffer.append("package ")
        buffer.appendLine(pkg.fqName.toString())
    }

    override fun visitImport(imp: Import) {
        buffer.append("import ")
        buffer.append(imp.fqName.toString())
        if (imp.wildcard) {
            buffer.append(".*")
        }
        imp.alias?.let {
            buffer.append(" as ")
            buffer.append(it)
        }
        buffer.appendLine()
    }

    private fun appendAnnotationAndModifiersDelimetedByNewLine(element: Element.WithModifiers) {
        for (ann in element.anns) {
            indent()
            ann.accept(this)
            buffer.appendLine()
        }
        indent()
        for (mod in element.mods) {
            mod.accept(this)
            buffer.append(" ")
        }
    }

    override fun visitClassDeclaration(clazz: ClassDeclaration) {
        appendAnnotationAndModifiersDelimetedByNewLine(clazz)
        when (clazz.kind) {
            ClassDeclaration.Kind.CLASS -> buffer.append("class ")
            ClassDeclaration.Kind.ENUM_CLASS -> buffer.append("enum class ")
            ClassDeclaration.Kind.INTERFACE -> buffer.append("interface ")
            ClassDeclaration.Kind.OBJECT -> buffer.append("object ")
            ClassDeclaration.Kind.COMPANION_OBJECT -> buffer.append("companion object ")
        }
        var needSpace = false
        buffer.append(clazz.name)
        if (clazz.name.isNotEmpty()) {
            needSpace = true
        }
        if (clazz.typeParameters.isNotEmpty()) {
            buffer.appendDelimeted(clazz.typeParameters, "<", ">") {
                it.accept(this)
            }
            needSpace = true
        }
        if (clazz.primaryConstructor?.modOrAnns?.isNotEmpty() == true && needSpace) {
            buffer.append(" ")
        }
        clazz.primaryConstructor?.let {
            it.accept(this)
            buffer.append(" ")
        }
        buffer.appendDelimetedIfNotEmpty(clazz.parents, ": ", " ")
        buffer.appendDelimetedIfNotEmpty(clazz.typeConstraints, "where ", " ")
        if (clazz.members.isNotEmpty()) {
            buffer.appendLine("{")
            indentation++
            buffer.appendDelimetedIfNotEmpty(clazz.enumEntries, delimeter = ",\n")
            buffer.appendLine(";")
            buffer.appendDelimeted(clazz.members, delimeter = "\n")
            indentation--
            buffer.appendLine("}")
        }
    }

    override fun visitSuperClassConstructorCall(call: ClassDeclaration.SuperClassConstructorCall) {
        for (ann in call.anns) {
            ann.accept(this)
            buffer.append(" ")
        }
        call.type.accept(this)
        buffer.appendDelimetedIfNotEmpty(call.typeArgs, "<", ">")
        buffer.appendDelimetedIfNotEmpty(call.arguments, "(", ")")
        call.lambda?.let {
            buffer.append(" ")
            it.accept(this)
        }
    }

    override fun visitSuperInterfaceType(type: ClassDeclaration.SuperInterface) {
        for (ann in type.anns) {
            ann.accept(this)
            buffer.append(" ")
        }
        type.type.accept(this)
        type.delegated?.let {
            buffer.append(" by ")
            it.accept(this)
        }
    }

    private fun appendAnnotationAndModifiersDelimetedByWhitespace(element: Element.WithModifiers) {
        for (ann in element.anns) {
            ann.accept(this)
            buffer.append(" ")
        }
        for (mod in element.mods) {
            mod.accept(this)
            buffer.append(" ")
        }
    }

    override fun visitPrimaryConstructor(constructor: ClassDeclaration.PrimaryConstructor) {
        appendAnnotationAndModifiersDelimetedByWhitespace(constructor)
        if (constructor.modOrAnns.isNotEmpty()) {
            buffer.append("constructor")
        }
        buffer.appendDelimetedIfNotEmpty(constructor.params, "(", ")")
    }

    override fun visitInitBlock(init: ClassDeclaration.InitBlock) {
        indent()
        buffer.append("init ")
        init.block.accept(this)
    }

    override fun visitFunction(func: FunctionDeclaration) {
        appendAnnotationAndModifiersDelimetedByNewLine(func)
        buffer.append("fun ")
        buffer.appendDelimetedIfNotEmpty(func.typeParameters, "<", "> ")
        func.receiverType?.let {
            it.accept(this)
            buffer.append(".")
        }
        func.name?.let {
            buffer.append(it)
            buffer.append(" ")
        }
        buffer.append("(")
        buffer.appendDelimetedIfNotEmpty(func.params)
        buffer.append(")")
        func.type?.let {
            buffer.append(": ")
            it.accept(this)
        }
        buffer.append(" ")
        buffer.appendDelimetedIfNotEmpty(func.typeConstraints, "where ", " ")
        func.body?.accept(this)
    }

    override fun visitValueParameter(param: ValueParameter) {
        appendAnnotationAndModifiersDelimetedByWhitespace(param)
        if (param.isVal) {
            buffer.append("val ")
        }
        buffer.append(param.name)
        buffer.append(": ")
        param.type.accept(this)
        param.default?.let {
            buffer.append(" = ")
            it.accept(this)
        }
    }

    override fun visitBlockBody(body: BlockBody) {
        body.block.accept(this)
    }

    override fun visitExpressionBody(body: ExpressionBody) {
        buffer.append("= ")
        body.expr.accept(this)
    }

    override fun visitPropertyDeclaration(property: PropertyDeclaration) {
        appendAnnotationAndModifiersDelimetedByNewLine(property)
        if (property.isVal) {
            buffer.append("val ")
        } else {
            buffer.append("var ")
        }
        buffer.appendDelimetedIfNotEmpty(property.typeParameters, "<", "> ")
        property.receiverType?.let {
            it.accept(this)
            buffer.append(".")
        }
        if (property.vars.size == 1) {
            property.vars.single().let {
                it?.accept(this)
                buffer.append(" ")
            }
        } else {
            appendDesctructuring(property.vars)
            buffer.append(" ")
        }
        buffer.appendDelimeted(property.typeConstraints, "where ", " ")
        if (property.delegated) {
            buffer.append("by ")
        } else if (property.expr != null) {
            buffer.append("= ")
        }
        property.expr?.accept(this)
        indentation++
        buffer.appendLine()
        property.getter?.accept(this)
        buffer.appendLine()
        property.setter?.accept(this)
        indentation--
    }

    private fun appendDesctructuring(vars: List<PropertyDeclaration.DestructuringEntry?>) {
        buffer.append("(")
        for (vr in vars.dropLast(1)) {
            vr?.accept(this) ?: buffer.append("_")
            buffer.append(", ")
        }
        vars.last()?.accept(this) ?: buffer.append("_")
        buffer.append(")")
    }

    override fun visitDestructuringEntry(propVar: PropertyDeclaration.DestructuringEntry) {
        buffer.append(propVar.name)
        propVar.type?.let {
            buffer.append(": ")
            it.accept(this)
        }
    }

    override fun visitPropertyGetter(getter: PropertyDeclaration.Getter) {
        appendAnnotationAndModifiersDelimetedByNewLine(getter)
        buffer.append("get()")
        getter.type?.let {
            buffer.append(": ")
            it.accept(this)
        }
        getter.body?.let {
            buffer.append(" ")
            it.accept(this)
        }
    }

    override fun visitPropertySetter(setter: PropertyDeclaration.Setter) {
        appendAnnotationAndModifiersDelimetedByNewLine(setter)
        buffer.append("set(")
        for (ann in setter.paramMods.filterIsInstance<Annotation>()) {
            ann.accept(this)
            buffer.append(" ")
        }
        for (mod in setter.paramMods.filterIsInstance<Modifier>()) {
            mod.accept(this)
            buffer.append(" ")
        }
        buffer.append(setter.paramName)
        setter.paramType?.let {
            buffer.append(": ")
            it.accept(this)
        }
        buffer.append(")")
        setter.body?.let {
            buffer.append(" ")
            it.accept(this)
        }
    }

    override fun visitTypeAliasDeclaration(typeAlias: TypeAliasDeclaration) {
        appendAnnotationAndModifiersDelimetedByNewLine(typeAlias)
        buffer.append(typeAlias.name)
        buffer.appendDelimetedIfNotEmpty(typeAlias.typeParameters, "<", ">")
        buffer.append(" = ")
        typeAlias.type.accept(this)
    }

    override fun visitConstructor(constructor: ClassDeclaration.Constructor) {
        appendAnnotationAndModifiersDelimetedByNewLine(constructor)
        buffer.append("constructor(")
        buffer.appendDelimetedIfNotEmpty(constructor.params)
        buffer.append(")")
        constructor.delegationCall?.let {
            buffer.append(": ")
            it.accept(this)
        }
        constructor.block?.let {
            buffer.append(" ")
            it.accept(this)
        }
    }

    override fun visitConstructorDelegationCall(call: ClassDeclaration.Constructor.DelegationCall) {
        when (call.target) {
            ClassDeclaration.Constructor.DelegationTarget.THIS -> buffer.append("this(")
            ClassDeclaration.Constructor.DelegationTarget.SUPER -> buffer.append("super(")
        }
        buffer.appendDelimetedIfNotEmpty(call.arguments)
        buffer.append(")")
    }

    override fun visitEnumEntry(entry: ClassDeclaration.EnumEntry) {
        appendAnnotationAndModifiersDelimetedByNewLine(entry)
        buffer.append(entry.name)
        buffer.appendDelimetedIfNotEmpty(entry.arguments, "(", ")")
        if (entry.members.isNotEmpty()) {
            buffer.append(" {")
            indentation++
            buffer.appendDelimeted(entry.members, delimeter = "\n")
            indentation--
            buffer.append("}")
        }
    }

    override fun visitTypeParameter(param: TypeParameter) {
        appendAnnotationAndModifiersDelimetedByWhitespace(param)
        buffer.append(param.name)
        param.type?.let {
            buffer.append(": ")
            it.accept(this)
        }
    }

    override fun visitTypeConstraint(constraint: TypeConstraint) {
        for (ann in constraint.anns) {
            ann.accept(this)
            buffer.append(" ")
        }
        buffer.append(constraint.name)
        buffer.append(": ")
        constraint.type.accept(this)
    }

    override fun visitFunctionalType(type: FunctionalType) {
        type.receiverType?.let {
            it.accept(this)
            buffer.append(".")
        }
        buffer.append("(")
        buffer.appendDelimetedIfNotEmpty(type.params)
        buffer.append(") -> ")
        type.returnType.accept(this)
    }

    override fun visitFunctionalTypeParameter(param: FunctionalType.Parameter) {
        param.name?.let {
            buffer.append(it)
            buffer.append(": ")
        }
        param.type.accept(this)
    }

    override fun visitSimpleType(type: SimpleType) {
        buffer.appendDelimeted(type.pieces, delimeter = ".")
    }

    override fun visitSimpleTypePiece(type: SimpleType.Piece) {
        buffer.append(type.name)
        appendTypeParamsWithWildcards(type.typeParams)
    }

    private fun appendTypeParamsWithWildcards(typeParams: List<Type?>) {
        if (typeParams.isEmpty()) return
        buffer.append("<")
        for (typeParam in typeParams.dropLast(1)) {
            typeParam?.accept(this) ?: buffer.append("*")
            buffer.append(", ")
        }
        typeParams.last()?.accept(this) ?: buffer.append("*")
        buffer.append(">")
    }

    override fun visitNullableType(type: NullableType) {
        type.type.accept(this)
        buffer.append("?")
    }

    override fun visitDynamicType(type: DynamicType) {
        buffer.append("dynamic")
    }

    override fun visitType(type: Type) {
        appendAnnotationAndModifiersDelimetedByWhitespace(type)
        type.ref.accept(this)
    }

    override fun visitValueArgument(arg: ValueArgument) {
        arg.name?.let {
            buffer.append(it)
            buffer.append(" = ")
        }
        if (arg.hasSpread) {
            buffer.append("*")
        }
        arg.expr.accept(this)
    }

    override fun visitIfExpression(expr: IfExpression) {
        buffer.append("if (")
        expr.condition.accept(this)
        buffer.append(") ")
        expr.thenBody.accept(this)
        expr.elseBody?.let {
            buffer.append(" else ")
            it.accept(this)
        }
    }

    override fun visitTryExpression(expr: TryExpression) {
        buffer.append("try ")
        expr.block.accept(this)
        for (catch in expr.catches) {
            buffer.append(" ")
            catch.accept(this)
        }
        expr.finallyBlock?.let {
            buffer.append(" finally ")
            it.accept(this)
        }
    }

    override fun visitCatch(catch: TryExpression.Catch) {
        buffer.append("catch (")
        for (ann in catch.anns) {
            ann.accept(this)
            buffer.append(" ")
        }
        buffer.append(catch.varName)
        buffer.append(": ")
        catch.varType.accept(this)
        buffer.append(") ")
        catch.block.accept(this)
    }

    override fun visitForLoop(stmt: ForLoop) {
        buffer.append("for (")
        for (ann in stmt.anns) {
            ann.accept(this)
            buffer.append(" ")
        }
        appendDesctructuring(stmt.vars)
        buffer.append(" in ")
        stmt.inExpr.accept(this)
        buffer.append(") ")
        stmt.body.accept(this)
    }

    override fun visitWhileLoop(stmt: WhileLoop) {
        buffer.append("while (")
        stmt.condition.accept(this)
        buffer.append(") ")
        stmt.body.accept(this)
    }

    override fun visitDoWhileLoop(stmt: DoWhileLoop) {
        buffer.append("do ")
        stmt.body.accept(this)
        buffer.append(" while (")
        stmt.condition.accept(this)
        buffer.append(")")
    }

    override fun visitBinaryExpression(op: BinaryExpression) {
        buffer.append("(")
        op.lhs.accept(this)
        buffer.append(")")
        op.oper.accept(this)
        buffer.append("(")
        op.rhs.accept(this)
        buffer.append(")")
    }

    override fun visitInfixFunctionName(oper: InfixFunctionName) {
        buffer.append(" ")
        buffer.append(oper.name)
        buffer.append(" ")
    }

    override fun visitBinaryOperator(oper: BinaryOperator) {
        buffer.append(
            when (oper.token) {
                BinaryOperator.Token.MUL -> " * "
                BinaryOperator.Token.DIV -> " / "
                BinaryOperator.Token.MOD -> " % "
                BinaryOperator.Token.ADD -> " + "
                BinaryOperator.Token.SUB -> " - "
                BinaryOperator.Token.IN -> " in "
                BinaryOperator.Token.NOT_IN -> " !in "
                BinaryOperator.Token.GT -> " > "
                BinaryOperator.Token.GTE -> " >= "
                BinaryOperator.Token.LT -> " < "
                BinaryOperator.Token.LTE -> " <= "
                BinaryOperator.Token.EQ -> " == "
                BinaryOperator.Token.NEQ -> " != "
                BinaryOperator.Token.ASSIGN -> " = "
                BinaryOperator.Token.MUL_ASSIGN -> " *= "
                BinaryOperator.Token.DIV_ASSIGN -> " /= "
                BinaryOperator.Token.MOD_ASSIGN -> " %= "
                BinaryOperator.Token.ADD_ASSIGN -> " += "
                BinaryOperator.Token.SUB_ASSIGN -> " -= "
                BinaryOperator.Token.OR -> " || "
                BinaryOperator.Token.AND -> " && "
                BinaryOperator.Token.ELVIS -> " ?: "
                BinaryOperator.Token.RANGE -> ".."
                BinaryOperator.Token.DOT -> "."
                BinaryOperator.Token.DOT_SAFE -> "?."
            }
        )
    }

    override fun visitUnaryExpression(expr: UnaryExpression) {
        if (expr.isPrefix) {
            expr.oper.accept(this)
            buffer.append("(")
            expr.expr.accept(this)
            buffer.append(")")
        } else {
            buffer.append("(")
            expr.expr.accept(this)
            buffer.append(")")
            expr.oper.accept(this)
        }
    }

    override fun visitUnaryOperator(oper: UnaryOperator) {
        buffer.append(
            when (oper.token) {
                UnaryOperator.Token.NEG -> "-"
                UnaryOperator.Token.POS -> "+"
                UnaryOperator.Token.INC -> "++"
                UnaryOperator.Token.DEC -> "--"
                UnaryOperator.Token.NOT -> "!"
                UnaryOperator.Token.NULL_DEREF -> "!!"
            }
        )
    }

    override fun visitTypeExpression(expr: TypeExpression) {
        buffer.append("(")
        expr.lhs.accept(this)
        buffer.append(")")
        expr.oper.accept(this)
        buffer.append("(")
        expr.rhs.accept(this)
        buffer.append(")")
    }

    override fun visitTypeOperator(oper: TypeOperator) {
        buffer.append(
            when (oper.token) {
                TypeOperator.Token.AS -> " as "
                TypeOperator.Token.AS_SAFE -> " as? "
                TypeOperator.Token.COL -> ": "
                TypeOperator.Token.IS -> " is "
                TypeOperator.Token.NOT_IS -> " !is "
            }
        )
    }

    override fun visitCallableReferenceExpression(callable: CallableReferenceExpression) {
        callable.recv?.accept(this)
        buffer.append("::")
        buffer.append(callable.name)
    }

    override fun visitClassReferenceExpression(ref: ClassReferenceExpression) {
        ref.recv?.accept(this)
        buffer.append("::class")
    }

    override fun visitReferenceExpressionReceiver(recv: ReferenceReceiver.ExpressionReceiver) {
        buffer.append(")")
        recv.expr.accept(this)
        buffer.append(")")
    }

    override fun visitReferenceTypeExpression(recv: ReferenceReceiver.Type) {
        recv.type.accept(this)
        if (recv.isNullable) {
            buffer.append("?")
        }
    }

    override fun visitConstantExpression(const: ConstantExpression) {
        buffer.append(const.value)
    }

    override fun visitLambdaLiteral(lambda: LambdaLiteral) {
        buffer.append("{")
        lambda.params?.let {
            buffer.appendDelimetedIfNotEmpty(it, " ", " ->\n")
        }
        indentation++
        for (stmt in lambda.stmts) {
            indent()
            stmt.accept(this)
            buffer.appendLine()
        }
        indentation--
        indent()
        buffer.appendLine("}")
    }

    override fun visitLambdaParameter(param: LambdaLiteral.Parameter) {
        appendDesctructuring(param.vars)
        param.destructType?.let {
            buffer.append(": ")
            it.accept(this)
        }
    }

    override fun visitThisReference(expr: ThisReference) {
        buffer.append("this")
        expr.label?.let {
            buffer.append("@")
            buffer.append(it)
        }
    }

    override fun visitSuperReference(expr: SuperReference) {
        buffer.append("super")
        expr.label?.let {
            buffer.append("@")
            buffer.append(it)
        }
        expr.typeArg?.let {
            buffer.append("<")
            it.accept(this)
            buffer.append(">")
        }
    }

    override fun visitWhenExpression(expr: WhenExpression) {
        buffer.append("when ")
        expr.subject?.let {
            buffer.append("(")
            it.accept(this)
            buffer.append(") ")
        }
        buffer.appendLine("{")
        indentation++
        for (entry in expr.entries) {
            indent()
            entry.accept(this)
        }
        indentation--
    }

    override fun visitWhenEntry(entry: WhenExpression.Entry) {
        entry.conds?.let {
            buffer.appendDelimeted(it, suffix = " -> {")
        } ?: buffer.append("else -> {")
        buffer.appendLine()
        indentation++
        for (expr in entry.body) {
            indent()
            expr.accept(this)
            buffer.appendLine()
        }
        indentation--
        indent()
        buffer.appendLine("}")
    }

    override fun visitWhenExpressionCondition(cond: WhenExpression.ExpressionCondition) {
        cond.expr.accept(this)
    }

    override fun visitWhenInCondition(cond: WhenExpression.InCondition) {
        if (cond.not) {
            buffer.append("!")
        }
        buffer.append("in ")
        cond.expr.accept(this)
    }

    override fun visitWhenIsCondition(cond: WhenExpression.IsCondition) {
        if (cond.not) {
            buffer.append("!")
        }
        buffer.append("is ")
        cond.type.accept(this)
    }

    override fun visitAnonymousObjectExpression(obj: AnonymousObjectExpression) {
        buffer.append("object ")
        buffer.appendDelimetedIfNotEmpty(obj.parents, ": ", " ")
        buffer.appendLine("{")
        indentation++
        buffer.appendDelimetedIfNotEmpty(obj.members, delimeter = "\n")
        indent()
        buffer.appendLine("}")
        indentation--
    }

    override fun visitThrowStatement(expr: ThrowStatement) {
        buffer.append("throw ")
        expr.expr.accept(this)
    }

    override fun visitReturnStatement(expr: ReturnStatement) {
        buffer.append("return")
        expr.label?.let {
            buffer.append("@")
            buffer.append(it)
        }
        expr.expr?.let {
            buffer.append(" ")
            it.accept(this)
        }
    }

    override fun visitContinueStatement(expr: ContinueStatement) {
        buffer.append("continue")
        expr.label?.let {
            buffer.append("@")
            buffer.append(it)
        }
    }

    override fun visitBreakStatement(expr: BreakStatement) {
        buffer.append("break")
        expr.label?.let {
            buffer.append("@")
            buffer.append(it)
        }
    }

    override fun visitCollectionLiteral(lit: CollectionLiteral) {
        buffer.append("[")
        buffer.appendDelimetedIfNotEmpty(lit.exprs)
        buffer.append("]")
    }

    override fun visitIdentifier(name: Identifier) {
        buffer.append(name.name)
    }

    override fun visitLabelledExpression(expr: LabelledExpression) {
        buffer.append(expr.label)
        buffer.append("@")
        expr.expr.accept(this)
    }

    override fun visitAnnotatedExpression(expr: AnnotatedExpression) {
        for (ann in expr.anns) {
            ann.accept(this)
            buffer.append(" ")
        }
        expr.expr.accept(this)
    }

    override fun visitCallExpression(call: CallExpression) {
        call.expr.accept(this)
        appendTypeParamsWithWildcards(call.typeArgs)
        buffer.append("(")
        buffer.appendDelimetedIfNotEmpty(call.arguments)
        buffer.append(")")
        call.lambda?.let {
            buffer.append(" ")
            it.accept(this)
        }
    }

    override fun visitTrailingLambda(lambda: CallExpression.TrailingLambda) {
        lambda.label?.let {
            buffer.append(it)
            buffer.append("@")
        }
        lambda.lambda.accept(this)
    }

    override fun visitArrayAccessExpression(expr: ArrayAccessExpression) {
        expr.expr.accept(this)
        buffer.append("[")
        buffer.appendDelimetedIfNotEmpty(expr.indices)
        buffer.append("]")
    }

    override fun visitAnonymousFunctionExpression(func: AnonymousFunctionExpression) {
        func.function.accept(this)
    }

    override fun visitPropertyExpression(expr: PropertyExpression) {
        expr.declaration.accept(this)
    }

    override fun visitBlock(block: Block) {
        buffer.appendLine("{")
        indentation++
        for (stmt in block.stmts) {
            indent()
            stmt.accept(this)
            buffer.appendLine()
        }
        indentation--
        indent()
        buffer.appendLine("}")
    }

    override fun visitAnnotation(ann: Annotation) {
        buffer.append("@")
        ann.target?.let {
            buffer.append(
                when (it) {
                    Annotation.Target.FIELD -> "field"
                    Annotation.Target.FILE -> "file"
                    Annotation.Target.PROPERTY -> "property"
                    Annotation.Target.GET -> "get"
                    Annotation.Target.SET -> "set"
                    Annotation.Target.RECEIVER -> "receiver"
                    Annotation.Target.PARAM -> "param"
                    Annotation.Target.SETPARAM -> "setparam"
                    Annotation.Target.DELEGATE -> "delegate"
                }
            )
            buffer.append(":")
        }
        buffer.append(ann.name.toString())
        buffer.appendDelimetedIfNotEmpty(ann.typeArgs, "<", ">")
        buffer.appendDelimetedIfNotEmpty(ann.arguments, "(", ")")
    }

    override fun visitModifier(mod: Modifier) {
        buffer.append(
            when (mod.keyword) {
                Modifier.Keyword.ABSTRACT -> "abstract"
                Modifier.Keyword.FINAL -> "final"
                Modifier.Keyword.OPEN -> "open"
                Modifier.Keyword.ANNOTATION -> "annotation"
                Modifier.Keyword.SEALED -> "sealed"
                Modifier.Keyword.DATA -> "data"
                Modifier.Keyword.OVERRIDE -> "override"
                Modifier.Keyword.LATEINIT -> "lateinit"
                Modifier.Keyword.INNER -> "inner"
                Modifier.Keyword.PRIVATE -> "private"
                Modifier.Keyword.PROTECTED -> "protected"
                Modifier.Keyword.PUBLIC -> "public"
                Modifier.Keyword.INTERNAL -> "internal"
                Modifier.Keyword.IN -> "in"
                Modifier.Keyword.OUT -> "out"
                Modifier.Keyword.NOINLINE -> "noinline"
                Modifier.Keyword.CROSSINLINE -> "crossinline"
                Modifier.Keyword.VARARG -> "vararg"
                Modifier.Keyword.REIFIED -> "reified"
                Modifier.Keyword.TAILREC -> "tailrec"
                Modifier.Keyword.OPERATOR -> "operator"
                Modifier.Keyword.INFIX -> "infix"
                Modifier.Keyword.INLINE -> "inline"
                Modifier.Keyword.EXTERNAL -> "external"
                Modifier.Keyword.SUSPEND -> "suspend"
                Modifier.Keyword.CONST -> "const"
                Modifier.Keyword.ACTUAL -> "actual"
                Modifier.Keyword.EXPECT -> "expect"
            }
        )
    }
}