package kometa.expressionTree

import kometa.kotlin.AST
import kometa.util.cast

object ETBuilder {
    private fun List<Any>.buildList(): AST.Expression = map { build(it.cast()) }.callListOf()

    private fun Any?.buildNullable(): AST.Expression = this?.let { build(it.cast()) } ?: AST.NULL

    fun build(node: AST.AstNode): AST.Expression =
        when (node) {
            is AST.Block -> Call("AST.Block", listOf(node.statements.buildList()))
            is AST.Statement -> Call(
                "AST.Statement",
                listOf(node.label.buildList(), build(node.expression.cast()))
            )
            is AST.SingleAnnotation -> Call(
                "AST.SingleAnnotation",
                listOf(node.target.buildNullable(), build(node.unescapedAnnotation.cast()))
            )
            is AST.ConstructorInvocation -> Call(
                "AST.ConstructorInvocation",
                listOf(build(node.userType), node.valueArguments.buildList())
            )
            is AST.UserType -> Call(
                "AST.UserType",
                listOf(node.simpleTypes.buildList())
            )
            is AST.SimpleUserType -> Call(
                "AST.SimpleUserType",
                listOf(node.name.asLiteral(), node.typeArguments.buildList())
            )
            is AST.TypeProjectionWithType -> Call(
                "AST.TypeProjectionWithType",
                listOf(node.modifiers.buildList(), build(node.type))
            )
            AST.ADD -> Call("AST.ADD")
            AST.SUB -> Call("AST.SUB")
            is AST.AnnotatedDelegationSpecifier -> Call(
                "AST.AnnotatedDelegationSpecifier",
                listOf(
                    node.annotations.buildList(),
                    node.delegationSpecifiers.buildList()
                )
            )
            is AST.AnnotatedLambda -> Call(
                "AST.AnnotatedLambda",
                listOf(
                    node.annotations.buildList(),
                    node.label.buildNullable(),
                    build(node.lambdaLiteral)
                )
            )
            AST.DELEGATE -> Call("AST.DELEGATE")
            AST.FIELD -> Call("AST.FIELD")
            AST.GET -> Call("AST.GET")
            AST.PARAM -> Call("AST.PARAM")
            AST.PROPERTY -> Call("AST.PROPERTY")
            AST.RECEIVER -> Call("AST.RECEIVER")
            AST.SET -> Call("AST.SET")
            AST.SETPARAM -> Call("AST.SETPARAM")
            is AST.AnonymousInitializer -> Call("AST.AnonymousInitializer", listOf(build(node.block)))
            AST.AS -> Call("AST.AS")
            AST.AS_SAFE -> Call("AST.AS_SAFE")
            is AST.AugmentedAssignment -> Call(
                "AST.AugmentedAssignment",
                listOf(build(node.lhs.cast()), build(node.op), build(node.rhs))
            )
            is AST.DirectAssignment -> Call("AST.DirectAssignment", listOf(build(node.lhs), build(node.rhs)))
            AST.ADD_ASSIGNMENT -> Call("AST.ADD_ASSIGNMENT")
            AST.DIV_ASSIGNMENT -> Call("AST.DIV_ASSIGNMENT")
            AST.MOD_ASSIGNMENT -> Call("AST.MOD_ASSIGNMENT")
            AST.MULT_ASSIGNMENT -> Call("AST.MULT_ASSIGNMENT")
            AST.SUB_ASSIGNMENT -> Call("AST.SUB_ASSIGNMENT")
            is AST.CallSuffix -> Call(
                "AST.CallSuffix",
                listOf(
                    node.typeArguments.buildList(),
                    node.valueArguments.buildList(),
                    node.annotatedLambda.buildNullable()
                )
            )
            is AST.CallableReference -> Call(
                "AST.CallableReference",
                listOf(node.receiverType.buildNullable(), node.name.asLiteral())
            )
            is AST.CatchBlock -> Call(
                "AST.CatchBlock",
                listOf(node.annotations.buildList(), node.name.asLiteral(), build(node.type), build(node.block))
            )
            is AST.ClassBody -> Call("AST.ClassBody", listOf(node.members.buildList()))
            is AST.EnumClassBody -> Call(
                "AST.EnumClassBody",
                listOf(node.entries.buildList(), node.members.buildList())
            )
            is AST.Class -> Call(
                "AST.Class", listOf(
                    node.modifiers.buildList(),
                    node.name.asLiteral(),
                    node.typeParameters.buildList(),
                    node.primaryConstructor.buildNullable(),
                    node.annotatedDelegationSpecifiers.buildList(),
                    node.typeConstraints.buildList(),
                    node.body.buildNullable()
                )
            )
            is AST.FunInterface -> Call(
                "AST.FunInterface", listOf(
                    node.modifiers.buildList(), node.name.asLiteral(),
                    node.typeParameters.buildList(), node.typeConstraints.buildList(), node.body.buildNullable()
                )
            )
            is AST.Interface -> Call(
                "AST.Interface", listOf(
                    node.modifiers.buildList(), node.name.asLiteral(),
                    node.typeParameters.buildList(), node.typeConstraints.buildList(), node.body.buildNullable()
                )
            )
            is AST.ClassParameter -> Call(
                "AST.ClassParameter",
                listOf(
                    node.modifiers.buildList(),
                    node.name.asLiteral(),
                    build(node.type),
                    node.initial.buildNullable(),
                    node.vov.buildNullable()
                )
            )
            is AST.CompanionObject -> Call(
                "AST.CompanionObject",
                listOf(
                    node.modifiers.buildList(),
                    node.name?.let { it.asLiteral() } ?: AST.NULL,
                    node.annotatedDelegationSpecifiers.buildList(),
                    node.body.buildNullable()
                )
            )
            AST.GE -> Call("AST.GE")
            AST.GREATER -> Call("AST.GREATER")
            AST.LE -> Call("AST.LE")
            AST.LESS -> Call("AST.LESS")
            is AST.Constructor -> Call(
                "AST.Constructor",
                listOf(
                    node.modifiers.buildList(),
                    node.classParameters.buildList(),
                    node.constructorDelegationCall.buildNullable(),
                    node.body.buildNullable(),
                    if (node.primary) AST.TRUE else AST.FALSE
                )
            )
            is AST.SuperCall -> Call("AST.SuperCall", listOf(node.arguments.buildList()))
            is AST.ThisCall -> Call("AST.ThisCall", listOf(node.arguments.buildList()))
            is AST.EXCL -> Call("AST.EXCL")
            is AST.EnumEntry -> Call(
                "AST.EnumEntry", listOf(
                    node.modifiers.buildList(),
                    node.name.asLiteral(), node.arguments.buildList(), node.body.buildNullable()
                )
            )
            AST.EQEQ -> Call("AST.EQEQ")
            AST.EQEQEQ -> Call("AST.EQEQEQ")
            AST.EXCL_EQ -> Call("AST.EXCL_EQ")
            AST.EXCL_EQEQ -> Call("AST.EXCL_EQEQ")
            is AST.ExplicitDelegation -> Call(
                "AST.ExplicitDelegation", listOf(
                    build(node.type.cast()), build(node.expression)
                )
            )
            is AST.Additive -> Call(
                "AST.Additive", listOf(
                    build(node.lhs), build(node.op), build(node.rhs)
                )
            )
            is AST.AsExpression -> Call(
                "AST.AsExpression", listOf(
                    build(node.lhs), build(node.op), build(node.rhs)
                )
            )
            is AST.CollectionLiteral -> Call(
                "AST.CollectionLiteral", listOf(
                    node.expressions.buildList()
                )
            )
            is AST.Comparison -> Call(
                "AST.Comparison", listOf(
                    build(node.lhs), build(node.op), build(node.rhs)
                )
            )
            is AST.Conjunction -> Call(
                "AST.Conjunction", listOf(
                    node.subs.buildList()
                )
            )
            is AST.DirectlyAssignableExpression -> Call(
                "AST.DirectlyAssignableExpression", listOf(
                    build(node.expression), build(node.suffix.cast())
                )
            )
            is AST.Disjunction -> Call(
                "AST.Disjunction", listOf(
                    node.subs.buildList()
                )
            )
            is AST.ElvisExpression -> Call(
                "AST.ElvisExpression", listOf(
                    build(node.lhs), build(node.rhs)
                )
            )
            is AST.Equality -> Call(
                "AST.Equality", listOf(
                    build(node.lhs), build(node.op), build(node.rhs)
                )
            )
            is AST.AnonymousFunction -> Call(
                "AST.AnonymousFunction", listOf(
                    node.receiverType.buildNullable(),
                    node.valueParameters.buildList(),
                    node.returnType.buildNullable(),
                    node.typeConstraints.buildList(),
                    node.body.buildNullable()
                )
            )
            is AST.LambdaLiteral -> Call(
                "AST.LambdaLiteral", listOf(
                    node.parameters.buildList(), node.statements.buildList()
                )
            )
            is AST.GenericCallLikeComparison -> Call(
                "AST.GenericCallLikeComparison", listOf(
                    build(node.infixOperation), node.suffixes.buildList()
                )
            )
            is AST.IfExpression -> Call(
                "AST.IfExpression", listOf(
                    build(node.expression), node.thenExpression.buildNullable(), node.elseExpression.buildNullable()
                )
            )
            is AST.InfixFunctionCall -> Call(
                "AST.InfixFunctionCall", listOf(
                    build(node.lhs), node.functionName.asLiteral(), build(node.rhs)
                )
            )
            AST.BREAK -> Call("AST.BREAK")
            is AST.BreakAt -> Call(
                "AST.BreakAt", listOf(
                    node.label.asLiteral()
                )
            )
            AST.CONTINUE -> Call("AST.CONTINUE")
            is AST.ContinueAt -> Call(
                "AST.ContinueAt", listOf(
                    node.label.asLiteral()
                )
            )
            is AST.Return -> Call(
                "AST.Return", listOf(
                    node.expression.buildNullable()
                )
            )
            is AST.ReturnAt -> Call(
                "AST.ReturnAt", listOf(
                    node.label.asLiteral()
                )
            )
            is AST.Throw -> Call(
                "AST.Throw", listOf(
                    build(node.expression)
                )
            )
            AST.FALSE -> Call("AST.FALSE")
            AST.TRUE -> Call("AST.TRUE")
            is AST.CharacterLiteral -> Call(
                "AST.CharacterLiteral", listOf(
                    node.s.asLiteral()
                )
            )
            is AST.DoubleLiteral -> Call(
                "AST.DoubleLiteral", listOf(
                    node.s.asLiteral()
                )
            )
            is AST.FloatLiteral -> Call(
                "AST.FloatLiteral", listOf(
                    node.s.asLiteral()
                )
            )
            is AST.IntegerLiteral -> Call(
                "AST.IntegerLiteral", listOf(
                    node.s.asLiteral()
                )
            )
            is AST.LongLiteral -> Call(
                "AST.LongLiteral", listOf(
                    node.s.asLiteral()
                )
            )
            AST.NULL -> Call("AST.NULL")
            is AST.StringLiteral -> Call(
                "AST.StringLiteral", listOf(
                    node.s.asLiteral()
                )
            )
            is AST.UnsignedLiteral -> Call(
                "AST.UnsignedLiteral", listOf(
                    node.s.asLiteral()
                )
            )
            is AST.UnsignedLongLiteral -> Call(
                "AST.UnsignedLongLiteral", listOf(
                    node.s.asLiteral()
                )
            )
            is AST.Multiplicative -> Call(
                "AST.Multiplicative", listOf(
                    build(node.lhs), build(node.op), build(node.rhs)
                )
            )
            is AST.NameExpression -> Call(
                "AST.NameExpression", listOf(
                    node.name.asLiteral()
                )
            )
            is AST.PostfixUnaryExpression -> Call(
                "AST.PostfixUnaryExpression", listOf(
                    build(node.expression), node.postfixUnarySuffixes.buildList()
                )
            )
            is AST.PrefixUnaryExpression -> Call(
                "AST.PrefixUnaryExpression", listOf(
                    node.unaryPrefixes.buildList(), build(node.expression)
                )
            )
            is AST.RangeExpression -> Call(
                "AST.RangeExpression", listOf(
                    build(node.lhs), build(node.rhs)
                )
            )
            is AST.Super -> Call("AST.Super", listOf(
                node.type.buildNullable(), node.name?.let { it.asLiteral() } ?: AST.NULL
            ))
            AST.THIS -> Call("AST.THIS")
            is AST.ThisAt -> Call(
                "AST.ThisAt", listOf(
                    node.name.asLiteral()
                )
            )
            is AST.TryExpression -> Call(
                "AST.TryExpression", listOf(
                    build(node.block), node.catchBlocks.buildList(), node.finallyBlock.buildNullable()
                )
            )
            is AST.WhenExpression -> Call(
                "AST.WhenExpression", listOf(
                    node.subject.buildNullable(), node.entries.buildList()
                )
            )
            is AST.BlockBody -> Call(
                "AST.BlockBody", listOf(
                    build(node.block)
                )
            )
            is AST.ExpressionBody -> Call(
                "AST.ExpressionBody", listOf(
                    build(node.expression)
                )
            )
            is AST.FunctionDeclaration -> Call(
                "AST.FunctionDeclaration", listOf(
                    node.modifiers.buildList(),
                    node.typeParameters.buildList(),
                    node.receiverType.buildNullable(),
                    node.name.asLiteral(),
                    node.parameters.buildList(),
                    node.returnType.buildNullable(),
                    node.typeConstraints.buildList(),
                    node.body.buildNullable()
                )
            )
            is AST.FunctionType -> Call(
                "AST.FunctionType", listOf(
                    node.receiverType.buildNullable(),
                    node.parameters.buildList(),
                    build(node.returnType)
                )
            )
            is AST.Getter -> Call(
                "AST.Getter", listOf(
                    node.modifiers.buildList(),
                    node.type.buildNullable(),
                    node.body.buildNullable()
                )
            )
            is AST.ImportHeader -> error("ImportHeader should not be present in expression tree")
            is AST.IndexingSuffix -> Call(
                "AST.IndexingSuffix", listOf(
                    node.expressions.buildList()
                )
            )
            is AST.InfixOperation -> Call(
                "AST.InfixOperation", listOf(
                    build(node.lhs), node.suffixes.buildList()
                )
            )
            AST.IS -> Call("AST.IS")
            AST.NOT_IS -> Call("AST.NOT_IS")
            is AST.InCheckSuffix -> Call(
                "AST.InCheckSuffix", listOf(
                    build(node.op.cast()), build(node.expression)
                )
            )
            AST.IN -> Call("AST.IN")
            AST.NOT_IN -> Call("AST.NOT_IN")
            is AST.IsCheckSuffix -> Call(
                "AST.IsCheckSuffix", listOf(
                    build(node.op.cast()), build(node.type)
                )
            )
            is AST.KotlinFile -> error("KotlinFile should not be present in expression tree")
            is AST.Label -> Call(
                "AST.Label", listOf(
                    node.name.asLiteral()
                )
            )
            is AST.LambdaParameter -> Call(
                "AST.LambdaParameter", listOf(
                    build(node.variableDeclaration), node.type.buildNullable()
                )
            )
            is AST.DoWhileStatement -> Call(
                "AST.DoWhileStatement", listOf(
                    node.body.buildNullable(), build(node.expression)
                )
            )
            is AST.ForStatement -> Call(
                "AST.ForStatement", listOf(
                    node.annotations.buildList(),
                    build(node.variableDeclaration),
                    build(node.expression),
                    node.body.buildNullable()
                )
            )
            is AST.WhileStatement -> Call(
                "AST.WhileStatement", listOf(
                    build(node.expression), node.body.buildNullable()
                )
            )
            AST.COLONCOLON -> Call("AST.COLONCOLON")
            AST.DOT -> Call("AST.DOT")
            AST.QUEST_DOT -> Call("AST.QUEST_DOT")
            AST.ANNOTATION_ -> Call("AST.ANNOTATION_")
            AST.DATA -> Call("AST.DATA")
            AST.ENUM -> Call("AST.ENUM")
            AST.INNER -> Call("AST.INNER")
            AST.SEALED -> Call("AST.SEALED")
            AST.VALUE -> Call("AST.VALUE")
            AST.EXTERNAL -> Call("AST.EXTERNAL")
            AST.INFIX -> Call("AST.INFIX")
            AST.INLINE -> Call("AST.INLINE")
            AST.OPERATOR -> Call("AST.OPERATOR")
            AST.SUSPEND -> Call("AST.SUSPEND")
            AST.TAILREC -> Call("AST.TAILREC")
            AST.ABSTRACT -> Call("AST.ABSTRACT")
            AST.FINAL -> Call("AST.FINAL")
            AST.OPEN -> Call("AST.OPEN")
            AST.LATEINIT -> Call("AST.LATEINIT")
            AST.OVERRIDE -> Call("AST.OVERRIDE")
            is AST.MultiAnnotation -> Call(
                "AST.MultiAnnotation", listOf(
                    node.target.buildNullable(), node.unescapedAnnotations.buildList()
                )
            )
            AST.CROSSINLINE -> Call("AST.CROSSINLINE")
            AST.NOINLINE -> Call("AST.NOINLINE")
            AST.VARARG -> Call("AST.VARARG")
            AST.ACTUAL -> Call("AST.ACTUAL")
            AST.EXPECT -> Call("AST.EXPECT")
            AST.CONST -> Call("AST.CONST")
            AST.INTERNAL -> Call("AST.INTERNAL")
            AST.PRIVATE -> Call("AST.PRIVATE")
            AST.PROTECTED -> Call("AST.PROTECTED")
            AST.PUBLIC -> Call("AST.PUBLIC")
            AST.DIV -> Call("AST.DIV")
            AST.MOD -> Call("AST.MOD")
            AST.MULT -> Call("AST.MULT")
            AST.NOT_IN -> Call("AST.NOT_IN")
            is AST.Name -> error("Name should not be present in built AST")
            is AST.NameAndType -> error("NameAndType should not be present in built AST")
            is AST.ClassNavigationSuffix -> Call(
                "AST.ClassNavigationSuffix", listOf(
                    build(node.op)
                )
            )
            is AST.ExpressionNavigationSuffix -> Call(
                "AST.ExpressionNavigationSuffix", listOf(
                    build(node.op), build(node.expression)
                )
            )
            is AST.IdentifierNavigationSuffix -> Call(
                "AST.IdentifierNavigationSuffix", listOf(
                    build(node.op), node.name.asLiteral()
                )
            )
            is AST.NullableType -> Call(
                "AST.NullableType", listOf(
                    build(node.type.cast())
                )
            )
            AST.DYNAMIC -> Call("AST.DYNAMIC")
            is AST.ObjectDeclaration -> Call(
                "AST.ObjectDeclaration", listOf(
                    node.modifiers.buildList(),
                    node.name.asLiteral(),
                    node.annotatedDelegationSpecifiers.buildList(),
                    node.body.buildNullable()
                )
            )
            is AST.ObjectLiteral -> Call(
                "AST.ObjectLiteral", listOf(
                    node.annotatedDelegationSpecifiers.buildList(), node.body.buildNullable()
                )
            )
            is AST.PackageHeader -> error("PackageHeader should not be present in expression tree")
            AST.DECR -> Call("AST.DECR")
            AST.EXCL_EXCL -> Call("AST.EXCL_EXCL")
            AST.INCR -> Call("AST.INCR")
            is AST.PropertyDeclaration -> Call(
                "AST.PropertyDeclaration", listOf(
                    node.modifiers.buildList(),
                    build(node.vov),
                    node.typeParameters.buildList(),
                    node.receiverType.buildNullable(),
                    build(node.variableDeclaration),
                    node.typeConstraints.buildList(),
                    node.body.buildNullable(),
                    node.getter.buildNullable(),
                    node.setter.buildNullable()
                )
            )
            is AST.PropertyDelegate -> Call(
                "AST.PropertyDelegate", listOf(
                    build(node.expression)
                )
            )
            AST.REIFIED -> Call("AST.REIFIED")
            is AST.Setter -> Call(
                "AST.Setter", listOf(
                    node.modifiers.buildList(),
                    node.parameter.buildNullable(),
                    node.type.buildNullable(),
                    node.body.buildNullable()
                )
            )
            is AST.FunctionTypeWithModifiers -> Call(
                "AST.FunctionTypeWithModifiers", listOf(
                    node.modifiers.buildList(),
                    build(node.type)
                )
            )
            is AST.ReceiverType -> Call(
                "AST.ReceiverType", listOf(
                    node.modifiers.buildList(),
                    build(node.type.cast())
                )
            )
            AST.SUSPEND -> Call("AST.SUSPEND")
            AST.MULT -> Call("AST.MULT")
            AST.DYNAMIC -> Call("AST.DYNAMIC")
            is AST.TypeAlias -> error("TypeAlias should not be present in expression tree")
            is AST.TypeArgumentsPostfix -> Call(
                "AST.TypeArgumentsPostfix", listOf(
                    node.typeArguments.buildList()
                )
            )
            is AST.TypeConstraint -> Call(
                "AST.TypeConstraint", listOf(
                    node.annotations.buildList(), node.name.asLiteral(), build(node.type)
                )
            )
            is AST.TypeParameter -> Call(
                "AST.TypeParameter", listOf(
                    node.modifiers.buildList(), node.name.asLiteral(), node.type.buildNullable()
                )
            )
            AST.VAL -> Call("AST.VAL")
            AST.VAR -> Call("AST.VAR")
            is AST.ValueArgument -> Call("AST.ValueArgument", listOf(
                node.annotations.buildList(),
                node.name?.let { it.asLiteral() } ?: AST.NULL,
                build(node.expression),
                if (node.withSpread) AST.TRUE else AST.FALSE
            ))
            is AST.ValueParameter -> Call(
                "AST.ValueParameter", listOf(
                    node.modifiers.buildList(),
                    node.name.asLiteral(),
                    build(node.type),
                    node.initial.buildNullable()
                )
            )
            AST.IN -> Call("AST.IN")
            AST.OUT -> Call("AST.OUT")
            is AST.WhenEntry -> Call(
                "AST.WhenEntry", listOf(
                    node.conditions?.buildList() ?: AST.NULL, build(node.body.cast())
                )
            )
            is AST.WhenSubject -> Call(
                "AST.WhenSubject", listOf(
                    node.annotations.buildList(),
                    node.variableDeclaration.buildNullable(),
                    build(node.expression)
                )
            )
            AST.FILE -> Call("AST.FILE")
            is AST.MultiVariableDeclaration -> Call(
                "AST.MultiVariableDeclaration", listOf(
                    node.decls.buildList()
                )
            )
            is AST.SingleVariableDeclaration -> Call(
                "AST.SingleVariableDeclaration", listOf(
                    node.annotations.buildList(),
                    node.name.asLiteral(),
                    node.type.buildNullable()
                )
            )
            else -> error("$node is not supported yet")
        }

    private fun Call(name: String, arguments: List<AST.Expression>? = null): AST.Expression {
        val split = name.split(".")
        val receiver = split[0]
        val dotAccesses = split.drop(1)
        val args = arrayListOf<AST.PostfixUnarySuffix>()
        dotAccesses.mapTo(args) { AST.IdentifierNavigationSuffix(AST.DOT, it) }
        if (arguments != null) {
            args += AST.CallSuffix(
                emptyList<AST.TypeProjection>(),
                arguments.map { it.asValueArgument() },
                null
            )
        }
        return AST.PostfixUnaryExpression(AST.NameExpression(receiver), args)
    }

    private fun String.asLiteral() = AST.StringLiteral("\"$this\"")

    private fun List<AST.Expression>.callListOf(): AST.Expression = Call("listOf", this)

    private fun AST.Expression.asValueArgument(): AST.ValueArgument = AST.ValueArgument(emptyList(), null, this, false)
}