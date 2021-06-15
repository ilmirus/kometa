import kometa.kotlin.AST
import kometa.util.peek
import kometa.util.pop
import kometa.util.push

class KotlinGen {
    private val buffer = StringBuffer()
    private var indent = 0
    private val expressionPriorityStack = arrayListOf<Int>()

    private fun indent() {
        for (i in 0 until indent) {
            buffer.append("    ")
        }
    }

    private fun expressionPriority(expression: AST.Expression): Int = when (expression) {
        is AST.DirectlyAssignableExpression -> 0
        is AST.Disjunction -> 1
        is AST.Conjunction -> 2
        is AST.Equality -> 3
        is AST.Comparison -> 4
        is AST.GenericCallLikeComparison -> 5
        is AST.InfixOperation -> 6
        is AST.ElvisExpression -> 7
        is AST.InfixFunctionCall -> 8
        is AST.RangeExpression -> 9
        is AST.Additive -> 10
        is AST.Multiplicative -> 11
        is AST.AsExpression -> 12
        is AST.PrefixUnaryExpression -> 13
        is AST.PostfixUnaryExpression -> 14
        is AST.CollectionLiteral -> 15
        is AST.AnonymousFunction -> 15
        is AST.LambdaLiteral -> 15
        is AST.IfExpression -> 15
        AST.BREAK -> 15
        is AST.BreakAt -> 15
        AST.CONTINUE -> 15
        is AST.ContinueAt -> 15
        is AST.Return -> 15
        is AST.ReturnAt -> 15
        is AST.Throw -> 15
        AST.FALSE -> 15
        AST.TRUE -> 15
        is AST.CharacterLiteral -> 15
        is AST.DoubleLiteral -> 15
        is AST.FloatLiteral -> 15
        is AST.IntegerLiteral -> 15
        is AST.LongLiteral -> 15
        AST.NULL -> 15
        is AST.StringLiteral -> 15
        is AST.UnsignedLiteral -> 15
        is AST.UnsignedLongLiteral -> 15
        is AST.NameExpression -> 15
        is AST.Super -> 15
        AST.THIS -> 15
        is AST.ThisAt -> 15
        is AST.TryExpression -> 15
        is AST.WhenExpression -> 15
    }

    fun dump(astNode: AST.AstNode) {
        var surroundWithParens = false
        if (astNode is AST.Expression) {
            val currentPriority = expressionPriority(astNode)
            expressionPriorityStack.peek()?.let {
                surroundWithParens = currentPriority < it
            }
            expressionPriorityStack.push(currentPriority)
        }

        if (surroundWithParens) {
            buffer.append("(")
        }

        when (astNode) {
            AST.ADD -> buffer.append("+")
            AST.SUB -> buffer.append("-")
            is AST.AnnotatedDelegationSpecifier -> {
                dumpModifiers(astNode.annotations)
                if (astNode.delegationSpecifiers.isNotEmpty()) {
                    for (delegationSpecifier in astNode.delegationSpecifiers.dropLast(1)) {
                        dumpDelegationSpecifier(delegationSpecifier)
                        buffer.append(", ")
                    }
                    dumpDelegationSpecifier(astNode.delegationSpecifiers.last())
                }
            }
            is AST.AnnotatedLambda -> {
                dumpModifiers(astNode.annotations)
                astNode.label?.let { dump(it) }
                dump(astNode.lambdaLiteral)
            }
            AST.DELEGATE -> buffer.append("delegate")
            AST.FIELD -> buffer.append("field")
            AST.GET -> buffer.append("get")
            AST.PARAM -> buffer.append("param")
            AST.PROPERTY -> buffer.append("property")
            AST.RECEIVER -> buffer.append("receiver")
            AST.SET -> buffer.append("set")
            AST.SETPARAM -> buffer.append("setparam")
            is AST.AnonymousInitializer -> {
                buffer.appendLine("init ")
                dump(astNode.block)
            }
            AST.AS -> buffer.append("as")
            AST.AS_SAFE -> buffer.append("as?")
            is AST.AugmentedAssignment -> {
                dumpAssignableExpression(astNode.lhs)
                buffer.append(" ")
                dumpAssignmentAndOperator(astNode.op)
                buffer.append(" ")
                dump(astNode.rhs)
            }
            is AST.DirectAssignment -> {
                dump(astNode.lhs)
                buffer.append(" = ")
                dump(astNode.rhs)
            }
            AST.ADD_ASSIGNMENT -> buffer.append("+=")
            AST.DIV_ASSIGNMENT -> buffer.append("/=")
            AST.MOD_ASSIGNMENT -> buffer.append("%=")
            AST.MULT_ASSIGNMENT -> buffer.append("*=")
            AST.SUB_ASSIGNMENT -> buffer.append("-=")
            is AST.Block -> {
                // TODO: generate one-liner is statement.size == 1
                buffer.appendLine("{")
                expressionPriorityStack.push(0)
                indent++
                for (statement in astNode.statements) {
                    indent()
                    dump(statement)
                    buffer.appendLine()
                }
                indent--
                indent()
                expressionPriorityStack.pop()
                buffer.appendLine("}")
            }
            is AST.CallSuffix -> {
                dumpTypeArguments(astNode.typeArguments)
                if (astNode.valueArguments.isNotEmpty()) {
                    buffer.append("(")
                }
                dumpValueArguments(astNode.valueArguments)
                if (astNode.valueArguments.isNotEmpty()) {
                    buffer.append(")")
                }
                astNode.annotatedLambda?.let { dump(it) }
            }
            is AST.CallableReference -> {
                astNode.receiverType?.let { dump(it) }
                buffer.append("::")
                buffer.append(astNode.name)
            }
            is AST.CatchBlock -> {
                buffer.append("catch(")
                dumpModifiers(astNode.annotations)
                buffer.append(astNode.name)
                buffer.append(": ")
                dump(astNode.type)
                buffer.append(") ")
                dump(astNode.block)
            }
            is AST.ClassBody -> {
                buffer.append("{")
                indent++
                for (member in astNode.members) {
                    indent()
                    dumpClassMemberDeclaration(member)
                    buffer.appendLine()
                }
                indent--
                buffer.appendLine("}")
            }
            is AST.EnumClassBody -> {
                buffer.append("{")
                indent++
                if (astNode.entries.isNotEmpty()) {
                    for (entry in astNode.entries.dropLast(1)) {
                        indent()
                        dump(entry)
                        buffer.appendLine(",")
                    }
                    indent()
                    dump(astNode.entries.last())
                    buffer.appendLine(";")
                    buffer.appendLine()
                }

                for (member in astNode.members) {
                    indent()
                    dumpClassMemberDeclaration(member)
                    buffer.appendLine()
                }
                indent--
                buffer.appendLine("}")
            }
            is AST.Class -> {
                dumpModifiers(astNode.modifiers)
                buffer.append("class ")
                buffer.append(astNode.name)
                dumpTypeParameters(astNode.typeParameters)
                astNode.primaryConstructor?.let { dump(it) }
                if (astNode.annotatedDelegationSpecifiers.isNotEmpty()) {
                    buffer.append(": ")
                    for (spec in astNode.annotatedDelegationSpecifiers.dropLast(1)) {
                        dump(spec)
                        buffer.append(", ")
                    }
                    dump(astNode.annotatedDelegationSpecifiers.last())
                }
                buffer.append(" ")
                dumpTypeConstraints(astNode.typeConstraints)
                if (astNode.typeConstraints.isNotEmpty() && astNode.body != null) {
                    buffer.append(" ")
                }
                astNode.body?.let { dump(it) }
            }
            is AST.FunInterface -> {
                dumpModifiers(astNode.modifiers)
                buffer.append("fun interface ")
                buffer.append(astNode.name)
                dumpTypeParameters(astNode.typeParameters)
                buffer.append(" ")
                if (astNode.typeConstraints.isNotEmpty()) {
                    buffer.append("where ")
                    for (constraint in astNode.typeConstraints.dropLast(1)) {
                        dump(constraint)
                        buffer.append(", ")
                    }
                    dump(astNode.typeConstraints.last())
                    if (astNode.body != null) {
                        buffer.append(" ")
                    }
                }
                astNode.body?.let { dump(it) }
            }
            is AST.Interface -> {
                dumpModifiers(astNode.modifiers)
                buffer.append("interface ")
                buffer.append(astNode.name)
                dumpTypeParameters(astNode.typeParameters)
                buffer.append(" ")
                if (astNode.typeConstraints.isNotEmpty()) {
                    buffer.append("where ")
                    for (constraint in astNode.typeConstraints.dropLast(1)) {
                        dump(constraint)
                        buffer.append(", ")
                    }
                    dump(astNode.typeConstraints.last())
                    if (astNode.body != null) {
                        buffer.append(" ")
                    }
                }
                astNode.body?.let { dump(it) }
            }
            is AST.ClassHeader -> error("ClassHeader should not be present in resulting AST")
            is AST.ClassParameter -> {
                dumpModifiers(astNode.modifiers)
                astNode.vov?.let {
                    dump(it)
                    buffer.append(" ")
                }
                buffer.append(astNode.name)
                buffer.append(":")
                dump(astNode.type)
                astNode.initial?.let {
                    buffer.append(" = ")
                    dump(it)
                }
            }
            is AST.CompanionObject -> {
                dumpModifiers(astNode.modifiers)
                buffer.append("companion object")
                astNode.name?.let {
                    buffer.append(it)
                    buffer.append(" ")
                }
                if (astNode.annotatedDelegationSpecifiers.isNotEmpty()) {
                    buffer.append(" : ")
                    dumpAnnotatedDelegatedSpecifiers(astNode.annotatedDelegationSpecifiers)
                }
                astNode.body?.let {
                    buffer.append(" ")
                    dump(it)
                }
            }
            AST.GE -> buffer.append(">=")
            AST.GREATER -> buffer.append(">")
            AST.LE -> buffer.append("<=")
            AST.LESS -> buffer.append("<")
            is AST.Constructor -> {
                dumpModifiers(astNode.modifiers)
                if (!astNode.primary || astNode.modifiers.isNotEmpty()) {
                    buffer.append("constructor")
                }
                buffer.append("(")
                if (astNode.classParameters.isNotEmpty()) {
                    for (classParameter in astNode.classParameters.dropLast(1)) {
                        dump(classParameter)
                        buffer.append(", ")
                    }
                    dump(astNode.classParameters.last())
                }
                buffer.append(")")
                astNode.constructorDelegationCall?.let {
                    buffer.append(": ")
                    dump(it)
                }
                astNode.body?.let {
                    buffer.append(" ")
                    dump(it)
                }
            }
            is AST.SuperCall -> {
                buffer.append("super(")
                dumpValueArguments(astNode.arguments)
                buffer.append(")")
            }
            is AST.ThisCall -> {
                buffer.append("this(")
                dumpValueArguments(astNode.arguments)
                buffer.append(")")
            }
            is AST.ConstructorInvocation -> {
                dump(astNode.userType)
                buffer.append("(")
                dumpValueArguments(astNode.valueArguments)
                buffer.append(")")
            }
            AST.EXCL -> buffer.append("!")
            is AST.EnumEntry -> {
                dumpModifiers(astNode.modifiers)
                buffer.append(astNode.name)
                buffer.append("(")
                dumpValueArguments(astNode.arguments)
                buffer.append(")")
                astNode.body?.let {
                    dump(it)
                }
            }
            AST.EQEQ -> buffer.append("==")
            AST.EQEQEQ -> buffer.append("===")
            AST.EXCL_EQ -> buffer.append("!=")
            AST.EXCL_EQEQ -> buffer.append("!==")
            is AST.ExplicitDelegation -> {
                when (val type = astNode.type) {
                    is AST.FunctionType -> dump(type)
                    is AST.UserType -> dump(type)
                }
                buffer.append(" by ")
                dump(astNode.expression)
            }
            is AST.Additive -> {
                dump(astNode.lhs)
                buffer.append(" ")
                dump(astNode.op)
                buffer.append(" ")
                dump(astNode.rhs)
            }
            is AST.AsExpression -> {
                dump(astNode.lhs)
                buffer.append(" ")
                dump(astNode.op)
                buffer.append(" ")
                dump(astNode.rhs)
            }
            is AST.CollectionLiteral -> {
                buffer.append("[")
                expressionPriorityStack.push(0)
                if (astNode.expressions.isNotEmpty()) {
                    for (expr in astNode.expressions.dropLast(1)) {
                        dump(expr)
                        buffer.append(", ")
                    }
                    dump(astNode.expressions.last())
                }
                expressionPriorityStack.pop()
            }
            is AST.Comparison -> {
                dump(astNode.lhs)
                buffer.append(" ")
                dump(astNode.op)
                buffer.append(" ")
                dump(astNode.rhs)
            }
            is AST.Conjunction -> {
                for (expr in astNode.subs.dropLast(1)) {
                    dump(expr)
                    buffer.append(" && ")
                }
                dump(astNode.subs.last())
            }
            is AST.DirectlyAssignableExpression -> {
                dump(astNode.expression)
                dumpAssignableSuffix(astNode.suffix)
            }
            is AST.Disjunction -> {
                for (expr in astNode.subs.dropLast(1)) {
                    dump(expr)
                    buffer.append(" || ")
                }
                dump(astNode.subs.last())
            }
            is AST.ElvisExpression -> {
                dump(astNode.lhs)
                buffer.append(" ?: ")
                dump(astNode.rhs)
            }
            is AST.Equality -> {
                dump(astNode.lhs)
                buffer.append(" ")
                dump(astNode.op)
                buffer.append(" ")
                dump(astNode.rhs)
            }
            is AST.AnonymousFunction -> {
                buffer.append("fun ")
                astNode.receiverType?.let {
                    dump(it)
                    buffer.append(".")
                }
                dumpValueParameters(astNode.valueParameters)
                astNode.returnType?.let {
                    buffer.append(": ")
                    dump(it)
                }
                dumpTypeConstraints(astNode.typeConstraints)
                astNode.body?.let {
                    expressionPriorityStack.push(0)
                    buffer.append(" ")
                    dump(it)
                    expressionPriorityStack.pop()
                }
            }
            is AST.LambdaLiteral -> {
                buffer.append("{")
                if (astNode.parameters.isNotEmpty()) {
                    buffer.append(" ")
                    for (param in astNode.parameters.dropLast(1)) {
                        dump(param)
                        buffer.append(", ")
                    }
                    dump(astNode.parameters.last())
                    buffer.append(" ->")
                }
                if (astNode.statements.size > 1) {
                    buffer.appendLine()
                    indent++
                    for (stmt in astNode.statements) {
                        indent()
                        dump(stmt)
                    }
                    indent--
                    indent()
                    buffer.appendLine("}")
                    indent()
                } else {
                    buffer.append(" ")
                    for (stmt in astNode.statements) {
                        dump(stmt)
                    }
                    buffer.append(" }")
                }
            }
            is AST.GenericCallLikeComparison -> {
                dump(astNode.infixOperation)
                for (suffix in astNode.suffixes) {
                    dump(suffix)
                }
            }
            is AST.IfExpression -> {
                buffer.append("if (")
                dump(astNode.expression)
                buffer.append(") ")
                astNode.thenExpression?.let {
                    dumpControlStructureBody(it)
                    if (astNode.elseExpression != null) {
                        buffer.append(" ")
                    }
                }
                astNode.elseExpression?.let {
                    buffer.append("else ")
                    dumpControlStructureBody(it)
                }
            }
            is AST.InfixFunctionCall -> {
                dump(astNode.lhs)
                buffer.append(" ")
                buffer.append(astNode.functionName)
                buffer.append(" ")
                dump(astNode.rhs)
            }
            AST.BREAK -> buffer.append("break")
            is AST.BreakAt -> {
                buffer.append("break@")
                buffer.append(astNode.label)
            }
            AST.CONTINUE -> buffer.append("continue")
            is AST.ContinueAt -> {
                buffer.append("continue@")
                buffer.append(astNode.label)
            }
            is AST.Return -> {
                buffer.append("return")
                astNode.expression?.let {
                    buffer.append(" ")
                    expressionPriorityStack.push(0)
                    dump(it)
                    expressionPriorityStack.pop()
                }
            }
            is AST.ReturnAt -> {
                buffer.append("return@")
                buffer.append(astNode.label)
                astNode.expression?.let {
                    buffer.append(" ")
                    expressionPriorityStack.push(0)
                    dump(it)
                    expressionPriorityStack.pop()
                }
            }
            is AST.Throw -> {
                buffer.append("throw ")
                expressionPriorityStack.push(0)
                dump(astNode.expression)
                expressionPriorityStack.pop()
            }
            AST.FALSE -> buffer.append("false")
            AST.TRUE -> buffer.append("true")
            is AST.CharacterLiteral -> buffer.append(astNode.s)
            is AST.DoubleLiteral -> buffer.append(astNode.s)
            is AST.FloatLiteral -> buffer.append(astNode.s)
            is AST.IntegerLiteral -> buffer.append(astNode.s)
            is AST.LongLiteral -> buffer.append(astNode.s)
            AST.NULL -> buffer.append("null")
            is AST.StringLiteral -> buffer.append(astNode.s)
            is AST.UnsignedLiteral -> buffer.append(astNode.s)
            is AST.UnsignedLongLiteral -> buffer.append(astNode.s)
            is AST.Multiplicative -> {
                dump(astNode.lhs)
                buffer.append(" ")
                dump(astNode.op)
                buffer.append(" ")
                dump(astNode.rhs)
            }
            is AST.NameExpression -> buffer.append(astNode.name)
            is AST.PostfixUnaryExpression -> {
                dump(astNode.expression)
                for (suffix in astNode.postfixUnarySuffixes) {
                    dumpPostfixUnarySuffix(suffix)
                }
            }
            is AST.PrefixUnaryExpression -> {
                for (prefix in astNode.unaryPrefixes) {
                    dumpUnaryPrefix(prefix)
                }
                dump(astNode.expression)
            }
            is AST.RangeExpression -> {
                dump(astNode.lhs)
                buffer.append("..")
                dump(astNode.rhs)
            }
            is AST.Super -> {
                buffer.append("super")
                astNode.type?.let {
                    buffer.append("<")
                    dump(it)
                    buffer.append(">")
                }
                astNode.name?.let {
                    buffer.append("@")
                    buffer.append(it)
                }
            }
            AST.THIS -> buffer.append("this")
            is AST.ThisAt -> {
                buffer.append("this@")
                buffer.append(astNode.name)
            }
            is AST.TryExpression -> {
                buffer.append("try ")
                dump(astNode.block)
                for (catchBlock in astNode.catchBlocks) {
                    indent()
                    dump(catchBlock)
                }
                astNode.finallyBlock?.let {
                    indent()
                    dump(it)
                }
            }
            is AST.WhenExpression -> {
                buffer.append("when")
                expressionPriorityStack.push(0)
                astNode.subject?.let {
                    dump(it)
                }
                buffer.append(" {")
                indent++
                for (entry in astNode.entries) {
                    dump(entry)
                }
                indent--
                indent()
                buffer.appendLine("}")
                expressionPriorityStack.pop()
            }
            is AST.BlockBody -> {
                buffer.append(" ")
                dump(astNode.block)
            }
            is AST.ExpressionBody -> {
                buffer.append(" = ")
                expressionPriorityStack.push(0)
                dump(astNode.expression)
                expressionPriorityStack.pop()
            }
            is AST.FunctionDeclaration -> {
                dumpModifiers(astNode.modifiers)
                buffer.append("fun ")
                dumpTypeParameters(astNode.typeParameters)
                if (astNode.typeParameters.isNotEmpty()) {
                    buffer.append(" ")
                }
                astNode.receiverType?.let {
                    dump(it)
                    buffer.append(".")
                }
                buffer.append(astNode.name)
                dumpValueParameters(astNode.parameters)
                astNode.returnType?.let {
                    buffer.append(": ")
                    dump(it)
                }
                astNode.body?.let {
                    dump(it)
                }
            }
            is AST.FunctionType -> {
                astNode.receiverType?.let {
                    dump(it)
                    buffer.append(".")
                }
                buffer.append("(")
                for (type in astNode.parameters.dropLast(1)) {
                    dump(type)
                    buffer.append(", ")
                }
                dump(astNode.parameters.last())
                buffer.append(") -> ")
                dump(astNode.returnType)
            }
            is AST.Getter -> {
                dumpModifiers(astNode.modifiers)
                buffer.append("get")
                astNode.body?.let { body ->
                    buffer.append("()")
                    astNode.type?.let {
                        buffer.append(": ")
                        dump(it)
                    }
                    buffer.append(" ")
                    dump(body)
                }
            }
            is AST.ImportHeader -> {
                buffer.append("import ")
                buffer.append(astNode.fqName)
                if (astNode.star) {
                    buffer.append(".*")
                }
                astNode.alias?.let {
                    buffer.append(" as ")
                    buffer.append(it)
                }
                buffer.appendLine()
            }
            is AST.IndexingSuffix -> {
                buffer.append("[")
                for (expr in astNode.expressions.dropLast(1)) {
                    dump(expr)
                    buffer.append(", ")
                }
                dump(astNode.expressions.last())
                buffer.append("]")
            }
            is AST.InfixOperation -> {
                dump(astNode.lhs)
                for (suffix in astNode.suffixes) {
                    buffer.append(" ")
                    dump(suffix)
                }
            }
            AST.IS -> buffer.append("is")
            AST.NOT_IS -> buffer.append("!is")
            is AST.InCheckSuffix -> {
                when (astNode.op) {
                    AST.IN -> buffer.append("in")
                    AST.NOT_IN -> buffer.append("!in")
                }
                buffer.append(" ")
                dump(astNode.expression)
            }
            is AST.IsCheckSuffix -> {
                dump(astNode.op)
                buffer.append(" ")
                dump(astNode.type)
            }
            is AST.KotlinFile -> {
                for (annotation in astNode.fileAnnotations) {
                    dump(annotation)
                    buffer.appendLine()
                }
                if (astNode.fileAnnotations.isNotEmpty()) {
                    buffer.appendLine()
                }
                var appendLine = false
                astNode.packageHeader?.let {
                    dump(it)
                    buffer.appendLine()
                    appendLine = true
                }
                for (header in astNode.importList) {
                    dump(header)
                    buffer.appendLine()
                    appendLine = true
                }
                if (appendLine) {
                    buffer.appendLine()
                }
                for (declaration in astNode.topLevelObject) {
                    dumpDeclaration(declaration)
                    buffer.appendLine()
                }
            }
            is AST.Label -> {
                buffer.append(astNode.name)
                buffer.append("@")
            }
            is AST.LambdaParameter -> {
                dump(astNode.variableDeclaration)
                astNode.type?.let {
                    buffer.append(": ")
                    dump(it)
                }
            }
            is AST.DoWhileStatement -> {
                expressionPriorityStack.push(0)
                buffer.append("do")
                astNode.body?.let {
                    buffer.append(" ")
                    dumpControlStructureBody(it)
                }
                if (astNode.body is AST.Block) {
                    indent()
                }
                buffer.append("while(")
                dump(astNode.expression)
                buffer.append(")")
                expressionPriorityStack.pop()
            }
            is AST.ForStatement -> {
                expressionPriorityStack.push(0)
                buffer.append("for (")
                dumpModifiers(astNode.annotations)
                dump(astNode.variableDeclaration)
                buffer.append(" in ")
                dump(astNode.expression)
                buffer.append(") ")
                astNode.body?.let {
                    dumpControlStructureBody(it)
                }
                expressionPriorityStack.pop()
            }
            is AST.WhileStatement -> {
                expressionPriorityStack.push(0)
                buffer.append("while (")
                dump(astNode.expression)
                buffer.append(") ")
                astNode.body?.let {
                    dumpControlStructureBody(it)
                }
                expressionPriorityStack.pop()
            }
            AST.COLONCOLON -> buffer.append("::")
            AST.DOT -> buffer.append(".")
            AST.QUEST_DOT -> buffer.append("?.")
            AST.ANNOTATION_ -> buffer.append("annotation")
            AST.DATA -> buffer.append("data")
            AST.ENUM -> buffer.append("enum")
            AST.INNER -> buffer.append("inner")
            AST.SEALED -> buffer.append("sealed")
            AST.VALUE -> buffer.append("value")
            AST.EXTERNAL -> buffer.append("external")
            AST.INFIX -> buffer.append("infix")
            AST.INLINE -> buffer.append("inline")
            AST.OPERATOR -> buffer.append("operator")
            AST.SUSPEND -> buffer.append("suspend")
            AST.TAILREC -> buffer.append("tailrec")
            AST.ABSTRACT -> buffer.append("abstract")
            AST.FINAL -> buffer.append("final")
            AST.OPEN -> buffer.append("open")
            AST.LATEINIT -> buffer.append("lateinit")
            AST.OVERRIDE -> buffer.append("override")
            is AST.MultiAnnotation -> {
                buffer.append("@")
                astNode.target?.let {
                    dump(it)
                    buffer.append(":")
                }
                buffer.append("[")
                for (unescapedAnnotation in astNode.unescapedAnnotations.dropLast(1)) {
                    dumpUnescapedAnnotation(unescapedAnnotation)
                    buffer.append(", ")
                }
                dumpUnescapedAnnotation(astNode.unescapedAnnotations.last())
                buffer.append("]")
            }
            is AST.SingleAnnotation -> {
                buffer.append("@")
                dumpUnescapedAnnotation(astNode.unescapedAnnotation)
            }
            AST.CROSSINLINE -> buffer.append("crossinline")
            AST.NOINLINE -> buffer.append("noinline")
            AST.VARARG -> buffer.append("vararg")
            AST.ACTUAL -> buffer.append("actual")
            AST.EXPECT -> buffer.append("expect")
            AST.CONST -> buffer.append("const")
            AST.INTERNAL -> buffer.append("internal")
            AST.PRIVATE -> buffer.append("private")
            AST.PROTECTED -> buffer.append("protected")
            AST.PUBLIC -> buffer.append("public")
            AST.DIV -> buffer.append("/")
            AST.MOD -> buffer.append("%")
            AST.MULT -> buffer.append("*")
            AST.NOT_IN -> buffer.append("!in")
            is AST.Name -> error("AST.Name should not be present in resulting AST")
            is AST.NameAndType -> error("AST.NameAndType should not be present in resulting AST")
            is AST.ClassNavigationSuffix -> {
                dump(astNode.op)
                buffer.append("class")
            }
            is AST.ExpressionNavigationSuffix -> {
                dump(astNode.op)
                buffer.append("(")
                dump(astNode.expression)
                buffer.append(")")
            }
            is AST.IdentifierNavigationSuffix -> {
                dump(astNode.op)
                buffer.append(astNode.name)
            }
            is AST.NullableType -> {
                when (val type = astNode.type) {
                    AST.DYNAMIC -> buffer.append("dynamic")
                    is AST.UserType -> dump(type)
                }
                buffer.append("?")
            }
            is AST.ObjectDeclaration -> {
                dumpModifiers(astNode.modifiers)
                buffer.append("object ")
                buffer.append(astNode.name)
                if (astNode.annotatedDelegationSpecifiers.isNotEmpty()) {
                    buffer.append(" : ")
                    dumpAnnotatedDelegatedSpecifiers(astNode.annotatedDelegationSpecifiers)
                }
                astNode.body?.let {
                    buffer.append(" ")
                    dump(it)
                }
            }
            is AST.ObjectLiteral -> {
                expressionPriorityStack.push(0)
                buffer.append("object")
                if (astNode.annotatedDelegationSpecifiers.isNotEmpty()) {
                    buffer.append(" : ")
                    dumpAnnotatedDelegatedSpecifiers(astNode.annotatedDelegationSpecifiers)
                }
                astNode.body?.let {
                    buffer.append(" ")
                    dump(it)
                }
                expressionPriorityStack.pop()
            }
            is AST.PackageHeader -> {
                buffer.append("package ")
                buffer.append(astNode.fqName)
            }
            AST.DECR -> buffer.append("--")
            AST.EXCL_EXCL -> buffer.append("!!")
            AST.INCR -> buffer.append("++")
            is AST.PropertyDeclaration -> {
                dumpModifiers(astNode.modifiers)
                dump(astNode.vov)
                buffer.append(" ")
                dumpTypeParameters(astNode.typeParameters)
                astNode.receiverType?.let {
                    dump(it)
                    buffer.append(".")
                }
                dump(astNode.variableDeclaration)
                buffer.append(" ")
                dumpTypeConstraints(astNode.typeConstraints)
                if (astNode.typeConstraints.isNotEmpty()) {
                    buffer.append(" ")
                }
                astNode.body?.let {
                    when (it) {
                        is AST.ExpressionBody -> dump(it)
                        is AST.PropertyDelegate -> dump(it)
                    }
                }
                astNode.getter?.let {
                    buffer.appendLine()
                    indent++
                    indent()
                    dump(it)
                    indent--
                }
                astNode.setter?.let {
                    buffer.appendLine()
                    indent++
                    indent()
                    dump(it)
                    indent--
                }
                buffer.appendLine()
            }
            is AST.PropertyDelegate -> {
                buffer.append("by ")
                dump(astNode.expression)
            }
            AST.REIFIED -> buffer.append("reified")
            is AST.Setter -> {
                dumpModifiers(astNode.modifiers)
                buffer.append("set")
                astNode.parameter?.let {
                    buffer.append("(")
                    dump(it)
                    buffer.append(")")
                }
                astNode.type?.let {
                    buffer.append(": ")
                    dump(it)
                }
                astNode.body?.let {
                    buffer.append(" ")
                    dump(it)
                }
            }
            is AST.SimpleUserType -> {
                buffer.append(astNode.name)
                dumpTypeArguments(astNode.typeArguments)
            }
            is AST.Statement -> {
                for (modifier in astNode.label) {
                    when(modifier) {
                        is AST.MultiAnnotation -> {
                            dump(modifier)
                            buffer.append(" ")
                        }
                        is AST.SingleAnnotation -> {
                            dump(modifier)
                            buffer.append(" ")
                        }
                        is AST.Label -> dump(modifier)
                    }
                }

                when(val expr = astNode.expression) {
                    is AST.AugmentedAssignment -> dump(expr)
                    is AST.DirectAssignment -> dump(expr)
                    is AST.Class -> dump(expr)
                    is AST.FunInterface -> dump(expr)
                    is AST.Interface -> dump(expr)
                    is AST.ObjectDeclaration -> dump(expr)
                    is AST.TypeAlias -> dump(expr)
                    is AST.DoWhileStatement -> dump(expr)
                    is AST.ForStatement -> dump(expr)
                    is AST.WhileStatement -> dump(expr)
                }
            }
            is AST.FunctionTypeWithModifiers -> error("AST.FunctionTypeWithModifiers should not be present in final AST")
            is AST.ReceiverType -> {
                for (modifier in astNode.modifiers) {
                    when(modifier) {
                        is AST.MultiAnnotation -> dump(modifier)
                        is AST.SingleAnnotation -> dump(modifier)
                        AST.SUSPEND -> buffer.append("suspend")
                    }
                    buffer.append(" ")
                }

                when (val type = astNode.type) {
                    is AST.NullableType -> dump(type)
                    AST.MULT -> buffer.append("*")
                    is AST.TypeProjectionWithType -> dump(type)
                }
            }
            AST.DYNAMIC -> buffer.append("dynamic")
            is AST.UserType -> {
                for (simple in astNode.simpleTypes.dropLast(1)) {
                    dump(simple)
                    buffer.append(".")
                }
                dump(astNode.simpleTypes.last())
            }
            is AST.TypeAlias -> {
                dumpModifiers(astNode.modifiers)
                buffer.append("typealias ")
                buffer.append(astNode.alias)
                dumpTypeParameters(astNode.typeParameters)
                buffer.append(" = ")
                dump(astNode.originalType)
            }
            is AST.TypeArgumentsPostfix -> dumpTypeArguments(astNode.typeArguments)
            is AST.TypeConstraint -> {
                dumpModifiers(astNode.annotations)
                buffer.append(astNode.name)
                buffer.append(": ")
                dump(astNode.type)
            }
            is AST.TypeParameter -> {
                dumpTypeParameterModifiers(astNode.modifiers)
                buffer.append(astNode.name)
                astNode.type?.let {
                    buffer.append(": ")
                    dump(it)
                }
            }
            is AST.TypeProjectionWithType -> {
                dumpTypeParameterModifiers(astNode.modifiers)
                dump(astNode.type)
            }
            AST.VAL -> buffer.append("val")
            AST.VAR -> buffer.append("var")
            is AST.ValueArgument -> {
                dumpModifiers(astNode.annotations)
                astNode.name?.let {
                    buffer.append(it)
                    buffer.append(" = ")
                }
                if (astNode.withSpread) {
                    buffer.append("*")
                }
                dump(astNode.expression)
            }
            is AST.ValueParameter -> {
                dumpModifiers(astNode.modifiers)
                buffer.append(astNode.name)
                buffer.append(": ")
                dump(astNode.type)
                astNode.initial?.let {
                    buffer.append(" = ")
                    dump(it)
                }
            }
            AST.IN -> buffer.append("in")
            AST.OUT -> buffer.append("out")
            is AST.WhenEntry -> {
                astNode.conditions?.let {
                    if (it.isNotEmpty()) {
                        for (condition in it.dropLast(1)) {
                            dumpWhenCondition(condition)
                            buffer.append(", ")
                        }
                        dumpWhenCondition(it.last())
                    }
                } ?: buffer.append("else")
                buffer.append(" -> ")
                dumpControlStructureBody(astNode.body)
            }
            is AST.WhenSubject -> {
                buffer.append("(")
                astNode.variableDeclaration?.let {
                    dumpModifiers(astNode.annotations)
                    dump(it)
                    buffer.append(" = ")
                }
                dump(astNode.expression)
                buffer.append(")")
            }
            AST.FILE -> buffer.append("file")
            is AST.MultiVariableDeclaration -> {
                buffer.append("(")
                for (varDecl in astNode.decls.dropLast(1)) {
                    dump(varDecl)
                    buffer.append(", ")
                }
                dump(astNode.decls.last())
                buffer.append(")")
            }
            is AST.SingleVariableDeclaration -> {
                dumpModifiers(astNode.annotations)
                buffer.append(astNode.name)
                astNode.type?.let {
                    buffer.append(": ")
                    dump(it)
                }
            }
        }

        if (surroundWithParens) {
            buffer.append(")")
        }

        if (astNode is AST.Expression) {
            expressionPriorityStack.pop()
        }
    }

    private fun dumpWhenCondition(condition: AST.WhenCondition) {
        when (condition) {
            is AST.Additive -> dump(condition)
            is AST.AsExpression -> dump(condition)
            is AST.CollectionLiteral -> dump(condition)
            is AST.Comparison -> dump(condition)
            is AST.Conjunction -> dump(condition)
            is AST.DirectlyAssignableExpression -> dump(condition)
            is AST.Disjunction -> dump(condition)
            is AST.ElvisExpression -> dump(condition)
            is AST.Equality -> dump(condition)
            is AST.AnonymousFunction -> dump(condition)
            is AST.LambdaLiteral -> dump(condition)
            is AST.GenericCallLikeComparison -> dump(condition)
            is AST.IfExpression -> dump(condition)
            is AST.InfixFunctionCall -> dump(condition)
            is AST.InfixOperation -> dump(condition)
            AST.BREAK -> buffer.append("break")
            is AST.BreakAt -> dump(condition)
            AST.CONTINUE -> buffer.append("continue")
            is AST.ContinueAt -> dump(condition)
            is AST.Return -> dump(condition)
            is AST.ReturnAt -> dump(condition)
            is AST.Throw -> dump(condition)
            AST.FALSE -> buffer.append("false")
            AST.TRUE -> buffer.append("true")
            is AST.CharacterLiteral -> dump(condition)
            is AST.DoubleLiteral -> dump(condition)
            is AST.FloatLiteral -> dump(condition)
            is AST.IntegerLiteral -> dump(condition)
            is AST.LongLiteral -> dump(condition)
            AST.NULL -> buffer.append("null")
            is AST.StringLiteral -> dump(condition)
            is AST.UnsignedLiteral -> dump(condition)
            is AST.UnsignedLongLiteral -> dump(condition)
            is AST.Multiplicative -> dump(condition)
            is AST.NameExpression -> dump(condition)
            is AST.PostfixUnaryExpression -> dump(condition)
            is AST.PrefixUnaryExpression -> dump(condition)
            is AST.RangeExpression -> dump(condition)
            is AST.Super -> dump(condition)
            AST.THIS -> buffer.append("this")
            is AST.ThisAt -> dump(condition)
            is AST.TryExpression -> dump(condition)
            is AST.WhenExpression -> dump(condition)
            is AST.InCheckSuffix -> dump(condition)
            is AST.IsCheckSuffix -> dump(condition)
        }
    }

    private fun dumpTypeParameterModifiers(modifiers: List<AST.TypeParameterModifier>) {
        for (modifier in modifiers) {
            when (modifier) {
                is AST.MultiAnnotation -> dump(modifier)
                is AST.SingleAnnotation -> dump(modifier)
                AST.REIFIED -> buffer.append("reified")
                AST.IN -> buffer.append("in")
                AST.OUT -> buffer.append("out")
            }
            buffer.append(" ")
        }
    }

    private fun dumpUnescapedAnnotation(unescapedAnnotation: AST.UnescapedAnnotation) {
        when (unescapedAnnotation) {
            is AST.ConstructorInvocation -> dump(unescapedAnnotation)
            is AST.UserType -> dump(unescapedAnnotation)
        }
    }

    private fun dumpDeclaration(declaration: AST.Declaration) {
        when (declaration) {
            is AST.Class -> dump(declaration)
            is AST.FunInterface -> dump(declaration)
            is AST.Interface -> dump(declaration)
            is AST.ObjectDeclaration -> dump(declaration)
            is AST.TypeAlias -> dump(declaration)
        }
    }

    private fun dumpUnaryPrefix(prefix: AST.UnaryPrefix) {
        when (prefix) {
            is AST.MultiAnnotation -> dump(prefix)
            is AST.SingleAnnotation -> dump(prefix)
            is AST.Label -> dump(prefix)
            AST.ADD -> buffer.append("+")
            AST.DECR -> buffer.append("--")
            AST.EXCL -> buffer.append("!")
            AST.INCR -> buffer.append("++")
            AST.SUB -> buffer.append("-")
        }
    }

    private fun dumpPostfixUnarySuffix(suffix: AST.PostfixUnarySuffix) {
        when (suffix) {
            is AST.IndexingSuffix -> dump(suffix)
            is AST.ClassNavigationSuffix -> dump(suffix)
            is AST.ExpressionNavigationSuffix -> dump(suffix)
            is AST.IdentifierNavigationSuffix -> dump(suffix)
            is AST.TypeArgumentsPostfix -> dump(suffix)
            is AST.CallSuffix -> dump(suffix)
            AST.DECR -> buffer.append("--")
            AST.EXCL_EXCL -> buffer.append("!!")
            AST.INCR -> buffer.append("++")
        }
    }

    private fun dumpControlStructureBody(csb: AST.ControlStructuredBody) {
        expressionPriorityStack.push(0)
        when (csb) {
            is AST.Block -> dump(csb)
            is AST.Statement -> dump(csb)
        }
        expressionPriorityStack.pop()
    }

    private fun dumpTypeConstraints(typeConstraints: List<AST.TypeConstraint>) {
        buffer.append("where ")
        for (constraint in typeConstraints.dropLast(1)) {
            dump(constraint)
            buffer.append(", ")
        }
        dump(typeConstraints.last())
    }

    private fun dumpValueParameters(valueParameters: List<AST.ValueParameter>) {
        buffer.append("(")
        for (param in valueParameters.dropLast(1)) {
            dump(param)
            buffer.append(", ")
        }
        dump(valueParameters.last())
        buffer.append(")")
    }

    private fun dumpAssignableSuffix(suffix: AST.AssignableSuffix) {
        when (suffix) {
            is AST.IndexingSuffix -> dump(suffix)
            is AST.ClassNavigationSuffix -> dump(suffix)
            is AST.ExpressionNavigationSuffix -> dump(suffix)
            is AST.IdentifierNavigationSuffix -> dump(suffix)
            is AST.TypeArgumentsPostfix -> dump(suffix)
        }
    }

    private fun dumpAnnotatedDelegatedSpecifiers(annotatedDelegationSpecifiers: List<AST.AnnotatedDelegationSpecifier>) {
        if (annotatedDelegationSpecifiers.isNotEmpty()) {
            for (ads in annotatedDelegationSpecifiers.dropLast(1)) {
                dump(ads)
                buffer.append(", ")
            }
            dump(annotatedDelegationSpecifiers.last())
        }
    }

    private fun dumpModifiers(modifiers: List<AST.Modifier>) {
        for (modifier in modifiers) {
            dump(modifier)
            buffer.append(" ")
        }
    }

    private fun dumpTypeParameters(typeParameters: List<AST.TypeParameter>) {
        if (typeParameters.isNotEmpty()) {
            buffer.append("<")
            for (typeParameter in typeParameters.dropLast(1)) {
                dump(typeParameter)
                buffer.append(", ")
            }
            dump(typeParameters.last())
            buffer.append(">")
        }
    }

    private fun dumpClassMemberDeclaration(member: AST.ClassMemberDeclaration) {
        when (member) {
            is AST.AnonymousInitializer -> dump(member)
            is AST.CompanionObject -> dump(member)
            is AST.Constructor -> dump(member)
            is AST.Class -> dump(member)
            is AST.FunInterface -> dump(member)
            is AST.Interface -> dump(member)
            is AST.ObjectDeclaration -> dump(member)
            is AST.TypeAlias -> dump(member)
        }
    }

    private fun dumpTypeArguments(typeArguments: List<AST.TypeProjection>) {
        if (typeArguments.isNotEmpty()) {
            buffer.append("<")
            for (typeProjection in typeArguments.dropLast(1)) {
                dumpTypeProjection(typeProjection)
                buffer.append(", ")
            }
            dumpTypeProjection(typeArguments.last())
            buffer.append(">")
        }
    }

    private fun dumpValueArguments(valueArguments: List<AST.ValueArgument>) {
        if (valueArguments.isNotEmpty()) {
            for (valueArgument in valueArguments.dropLast(1)) {
                dump(valueArgument)
                buffer.append(", ")
            }
            dump(valueArguments.last())
        }
    }

    private fun dumpDelegationSpecifier(delegationSpecifier: AST.DelegationSpecifier) {
        when (delegationSpecifier) {
            is AST.ConstructorInvocation -> dump(delegationSpecifier)
            is AST.ExplicitDelegation -> dump(delegationSpecifier)
            is AST.FunctionType -> dump(delegationSpecifier)
            is AST.UserType -> dump(delegationSpecifier)
        }
    }

    private fun dumpAssignableExpression(assignableExpression: AST.AssignableExpression) {
        when (assignableExpression) {
            is AST.PrefixUnaryExpression -> dump(assignableExpression)
        }
    }

    private fun dumpAssignmentAndOperator(op: AST.AssignmentAndOperator) {
        when (op) {
            AST.ADD_ASSIGNMENT -> buffer.append("+=")
            AST.DIV_ASSIGNMENT -> buffer.append("/=")
            AST.MOD_ASSIGNMENT -> buffer.append("%=")
            AST.MULT_ASSIGNMENT -> buffer.append("*=")
            AST.SUB_ASSIGNMENT -> buffer.append("-=")
        }
    }

    private fun dumpTypeProjection(typeProjection: AST.TypeProjection) {
        when (typeProjection) {
            AST.MULT -> buffer.append("*")
            is AST.TypeProjectionWithType -> dump(typeProjection)
        }
    }
}