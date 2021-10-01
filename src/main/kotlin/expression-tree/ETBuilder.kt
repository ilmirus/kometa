//package kometa.expressionTree
//
//import kometa.kotlin.Visitor
//import kometa.kotlin.ast.Element
//import kometa.util.cast
//
//object ETBuilder {
//    private fun List<Any>.buildList(): Element.Expr = map { build(it.cast()) }.callListOf()
//
//    private fun Any?.buildNullable(): AST.Expression = this?.let { build(it.cast()) } ?: AST.NULL
//
//    fun build(element: Element): AST.Expression =
//        when (element) {
//            is AST.Block -> Call("AST.Block", listOf(element.statements.buildList()))
//            is AST.Statement -> Call(
//                "AST.Statement",
//                listOf(element.label.buildList(), build(element.expression.cast()))
//            )
//            is AST.SingleAnnotation -> Call(
//                "AST.SingleAnnotation",
//                listOf(element.target.buildNullable(), build(element.unescapedAnnotation.cast()))
//            )
//            is AST.ConstructorInvocation -> Call(
//                "AST.ConstructorInvocation",
//                listOf(build(element.userType), element.valueArguments.buildList())
//            )
//            is AST.UserType -> Call(
//                "AST.UserType",
//                listOf(element.simpleTypes.buildList())
//            )
//            is AST.SimpleUserType -> Call(
//                "AST.SimpleUserType",
//                listOf(element.name.asLiteral(), element.typeArguments.buildList())
//            )
//            is AST.TypeProjectionWithType -> Call(
//                "AST.TypeProjectionWithType",
//                listOf(element.modifiers.buildList(), build(element.type))
//            )
//            AST.ADD -> Call("AST.ADD")
//            AST.SUB -> Call("AST.SUB")
//            is AST.AnnotatedDelegationSpecifier -> Call(
//                "AST.AnnotatedDelegationSpecifier",
//                listOf(
//                    element.annotations.buildList(),
//                    element.delegationSpecifiers.buildList()
//                )
//            )
//            is AST.AnnotatedLambda -> Call(
//                "AST.AnnotatedLambda",
//                listOf(
//                    element.annotations.buildList(),
//                    element.label.buildNullable(),
//                    build(element.lambdaLiteral)
//                )
//            )
//            AST.DELEGATE -> Call("AST.DELEGATE")
//            AST.FIELD -> Call("AST.FIELD")
//            AST.GET -> Call("AST.GET")
//            AST.PARAM -> Call("AST.PARAM")
//            AST.PROPERTY -> Call("AST.PROPERTY")
//            AST.RECEIVER -> Call("AST.RECEIVER")
//            AST.SET -> Call("AST.SET")
//            AST.SETPARAM -> Call("AST.SETPARAM")
//            is AST.AnonymousInitializer -> Call("AST.AnonymousInitializer", listOf(build(element.block)))
//            AST.AS -> Call("AST.AS")
//            AST.AS_SAFE -> Call("AST.AS_SAFE")
//            is AST.AugmentedAssignment -> Call(
//                "AST.AugmentedAssignment",
//                listOf(build(element.lhs.cast()), build(element.op), build(element.rhs))
//            )
//            is AST.DirectAssignment -> Call("AST.DirectAssignment", listOf(build(element.lhs), build(element.rhs)))
//            AST.ADD_ASSIGNMENT -> Call("AST.ADD_ASSIGNMENT")
//            AST.DIV_ASSIGNMENT -> Call("AST.DIV_ASSIGNMENT")
//            AST.MOD_ASSIGNMENT -> Call("AST.MOD_ASSIGNMENT")
//            AST.MULT_ASSIGNMENT -> Call("AST.MULT_ASSIGNMENT")
//            AST.SUB_ASSIGNMENT -> Call("AST.SUB_ASSIGNMENT")
//            is AST.CallSuffix -> Call(
//                "AST.CallSuffix",
//                listOf(
//                    element.typeArguments.buildList(),
//                    element.valueArguments.buildList(),
//                    element.annotatedLambda.buildNullable()
//                )
//            )
//            is AST.CallableReference -> Call(
//                "AST.CallableReference",
//                listOf(element.receiverType.buildNullable(), element.name.asLiteral())
//            )
//            is AST.CatchBlock -> Call(
//                "AST.CatchBlock",
//                listOf(element.annotations.buildList(), element.name.asLiteral(), build(element.type), build(element.block))
//            )
//            is AST.ClassBody -> Call("AST.ClassBody", listOf(element.members.buildList()))
//            is AST.EnumClassBody -> Call(
//                "AST.EnumClassBody",
//                listOf(element.entries.buildList(), element.members.buildList())
//            )
//            is AST.Class -> Call(
//                "AST.Class", listOf(
//                    element.modifiers.buildList(),
//                    element.name.asLiteral(),
//                    element.typeParameters.buildList(),
//                    element.primaryConstructor.buildNullable(),
//                    element.annotatedDelegationSpecifiers.buildList(),
//                    element.typeConstraints.buildList(),
//                    element.body.buildNullable()
//                )
//            )
//            is AST.FunInterface -> Call(
//                "AST.FunInterface", listOf(
//                    element.modifiers.buildList(), element.name.asLiteral(),
//                    element.typeParameters.buildList(), element.typeConstraints.buildList(), element.body.buildNullable()
//                )
//            )
//            is AST.Interface -> Call(
//                "AST.Interface", listOf(
//                    element.modifiers.buildList(), element.name.asLiteral(),
//                    element.typeParameters.buildList(), element.typeConstraints.buildList(), element.body.buildNullable()
//                )
//            )
//            is AST.ClassParameter -> Call(
//                "AST.ClassParameter",
//                listOf(
//                    element.modifiers.buildList(),
//                    element.name.asLiteral(),
//                    build(element.type),
//                    element.initial.buildNullable(),
//                    element.vov.buildNullable()
//                )
//            )
//            is AST.CompanionObject -> Call(
//                "AST.CompanionObject",
//                listOf(
//                    element.modifiers.buildList(),
//                    element.name?.let { it.asLiteral() } ?: AST.NULL,
//                    element.annotatedDelegationSpecifiers.buildList(),
//                    element.body.buildNullable()
//                )
//            )
//            AST.GE -> Call("AST.GE")
//            AST.GREATER -> Call("AST.GREATER")
//            AST.LE -> Call("AST.LE")
//            AST.LESS -> Call("AST.LESS")
//            is AST.Constructor -> Call(
//                "AST.Constructor",
//                listOf(
//                    element.modifiers.buildList(),
//                    element.classParameters.buildList(),
//                    element.constructorDelegationCall.buildNullable(),
//                    element.body.buildNullable(),
//                    if (element.primary) AST.TRUE else AST.FALSE
//                )
//            )
//            is AST.SuperCall -> Call("AST.SuperCall", listOf(element.arguments.buildList()))
//            is AST.ThisCall -> Call("AST.ThisCall", listOf(element.arguments.buildList()))
//            is AST.EXCL -> Call("AST.EXCL")
//            is AST.EnumEntry -> Call(
//                "AST.EnumEntry", listOf(
//                    element.modifiers.buildList(),
//                    element.name.asLiteral(), element.arguments.buildList(), element.body.buildNullable()
//                )
//            )
//            AST.EQEQ -> Call("AST.EQEQ")
//            AST.EQEQEQ -> Call("AST.EQEQEQ")
//            AST.EXCL_EQ -> Call("AST.EXCL_EQ")
//            AST.EXCL_EQEQ -> Call("AST.EXCL_EQEQ")
//            is AST.ExplicitDelegation -> Call(
//                "AST.ExplicitDelegation", listOf(
//                    build(element.type.cast()), build(element.expression)
//                )
//            )
//            is AST.Additive -> Call(
//                "AST.Additive", listOf(
//                    build(element.lhs), build(element.op), build(element.rhs)
//                )
//            )
//            is AST.AsExpression -> Call(
//                "AST.AsExpression", listOf(
//                    build(element.lhs), build(element.op), build(element.rhs)
//                )
//            )
//            is AST.CollectionLiteral -> Call(
//                "AST.CollectionLiteral", listOf(
//                    element.expressions.buildList()
//                )
//            )
//            is AST.Comparison -> Call(
//                "AST.Comparison", listOf(
//                    build(element.lhs), build(element.op), build(element.rhs)
//                )
//            )
//            is AST.Conjunction -> Call(
//                "AST.Conjunction", listOf(
//                    element.subs.buildList()
//                )
//            )
//            is AST.DirectlyAssignableExpression -> Call(
//                "AST.DirectlyAssignableExpression", listOf(
//                    build(element.expression), build(element.suffix.cast())
//                )
//            )
//            is AST.Disjunction -> Call(
//                "AST.Disjunction", listOf(
//                    element.subs.buildList()
//                )
//            )
//            is AST.ElvisExpression -> Call(
//                "AST.ElvisExpression", listOf(
//                    build(element.lhs), build(element.rhs)
//                )
//            )
//            is AST.Equality -> Call(
//                "AST.Equality", listOf(
//                    build(element.lhs), build(element.op), build(element.rhs)
//                )
//            )
//            is AST.AnonymousFunction -> Call(
//                "AST.AnonymousFunction", listOf(
//                    element.receiverType.buildNullable(),
//                    element.valueParameters.buildList(),
//                    element.returnType.buildNullable(),
//                    element.typeConstraints.buildList(),
//                    element.body.buildNullable()
//                )
//            )
//            is AST.LambdaLiteral -> Call(
//                "AST.LambdaLiteral", listOf(
//                    element.parameters.buildList(), element.statements.buildList()
//                )
//            )
//            is AST.GenericCallLikeComparison -> Call(
//                "AST.GenericCallLikeComparison", listOf(
//                    build(element.infixOperation), element.suffixes.buildList()
//                )
//            )
//            is AST.IfExpression -> Call(
//                "AST.IfExpression", listOf(
//                    build(element.expression), element.thenExpression.buildNullable(), element.elseExpression.buildNullable()
//                )
//            )
//            is AST.InfixFunctionCall -> Call(
//                "AST.InfixFunctionCall", listOf(
//                    build(element.lhs), element.functionName.asLiteral(), build(element.rhs)
//                )
//            )
//            AST.BREAK -> Call("AST.BREAK")
//            is AST.BreakAt -> Call(
//                "AST.BreakAt", listOf(
//                    element.label.asLiteral()
//                )
//            )
//            AST.CONTINUE -> Call("AST.CONTINUE")
//            is AST.ContinueAt -> Call(
//                "AST.ContinueAt", listOf(
//                    element.label.asLiteral()
//                )
//            )
//            is AST.Return -> Call(
//                "AST.Return", listOf(
//                    element.expression.buildNullable()
//                )
//            )
//            is AST.ReturnAt -> Call(
//                "AST.ReturnAt", listOf(
//                    element.label.asLiteral()
//                )
//            )
//            is AST.Throw -> Call(
//                "AST.Throw", listOf(
//                    build(element.expression)
//                )
//            )
//            AST.FALSE -> Call("AST.FALSE")
//            AST.TRUE -> Call("AST.TRUE")
//            is AST.CharacterLiteral -> Call(
//                "AST.CharacterLiteral", listOf(
//                    element.s.asLiteral()
//                )
//            )
//            is AST.DoubleLiteral -> Call(
//                "AST.DoubleLiteral", listOf(
//                    element.s.asLiteral()
//                )
//            )
//            is AST.FloatLiteral -> Call(
//                "AST.FloatLiteral", listOf(
//                    element.s.asLiteral()
//                )
//            )
//            is AST.IntegerLiteral -> Call(
//                "AST.IntegerLiteral", listOf(
//                    element.s.asLiteral()
//                )
//            )
//            is AST.LongLiteral -> Call(
//                "AST.LongLiteral", listOf(
//                    element.s.asLiteral()
//                )
//            )
//            AST.NULL -> Call("AST.NULL")
//            is AST.StringLiteral -> Call(
//                "AST.StringLiteral", listOf(
//                    element.s.asLiteral()
//                )
//            )
//            is AST.UnsignedLiteral -> Call(
//                "AST.UnsignedLiteral", listOf(
//                    element.s.asLiteral()
//                )
//            )
//            is AST.UnsignedLongLiteral -> Call(
//                "AST.UnsignedLongLiteral", listOf(
//                    element.s.asLiteral()
//                )
//            )
//            is AST.Multiplicative -> Call(
//                "AST.Multiplicative", listOf(
//                    build(element.lhs), build(element.op), build(element.rhs)
//                )
//            )
//            is AST.NameExpression -> Call(
//                "AST.NameExpression", listOf(
//                    element.name.asLiteral()
//                )
//            )
//            is AST.PostfixUnaryExpression -> Call(
//                "AST.PostfixUnaryExpression", listOf(
//                    build(element.expression), element.postfixUnarySuffixes.buildList()
//                )
//            )
//            is AST.PrefixUnaryExpression -> Call(
//                "AST.PrefixUnaryExpression", listOf(
//                    element.unaryPrefixes.buildList(), build(element.expression)
//                )
//            )
//            is AST.RangeExpression -> Call(
//                "AST.RangeExpression", listOf(
//                    build(element.lhs), build(element.rhs)
//                )
//            )
//            is AST.Super -> Call("AST.Super", listOf(
//                element.type.buildNullable(), element.name?.let { it.asLiteral() } ?: AST.NULL
//            ))
//            AST.THIS -> Call("AST.THIS")
//            is AST.ThisAt -> Call(
//                "AST.ThisAt", listOf(
//                    element.name.asLiteral()
//                )
//            )
//            is AST.TryExpression -> Call(
//                "AST.TryExpression", listOf(
//                    build(element.block), element.catchBlocks.buildList(), element.finallyBlock.buildNullable()
//                )
//            )
//            is AST.WhenExpression -> Call(
//                "AST.WhenExpression", listOf(
//                    element.subject.buildNullable(), element.entries.buildList()
//                )
//            )
//            is AST.BlockBody -> Call(
//                "AST.BlockBody", listOf(
//                    build(element.block)
//                )
//            )
//            is AST.ExpressionBody -> Call(
//                "AST.ExpressionBody", listOf(
//                    build(element.expression)
//                )
//            )
//            is AST.FunctionDeclaration -> Call(
//                "AST.FunctionDeclaration", listOf(
//                    element.modifiers.buildList(),
//                    element.typeParameters.buildList(),
//                    element.receiverType.buildNullable(),
//                    element.name.asLiteral(),
//                    element.parameters.buildList(),
//                    element.returnType.buildNullable(),
//                    element.typeConstraints.buildList(),
//                    element.body.buildNullable()
//                )
//            )
//            is AST.FunctionType -> Call(
//                "AST.FunctionType", listOf(
//                    element.receiverType.buildNullable(),
//                    element.parameters.buildList(),
//                    build(element.returnType)
//                )
//            )
//            is AST.Getter -> Call(
//                "AST.Getter", listOf(
//                    element.modifiers.buildList(),
//                    element.type.buildNullable(),
//                    element.body.buildNullable()
//                )
//            )
//            is AST.ImportHeader -> error("ImportHeader should not be present in expression tree")
//            is AST.IndexingSuffix -> Call(
//                "AST.IndexingSuffix", listOf(
//                    element.expressions.buildList()
//                )
//            )
//            is AST.InfixOperation -> Call(
//                "AST.InfixOperation", listOf(
//                    build(element.lhs), element.suffixes.buildList()
//                )
//            )
//            AST.IS -> Call("AST.IS")
//            AST.NOT_IS -> Call("AST.NOT_IS")
//            is AST.InCheckSuffix -> Call(
//                "AST.InCheckSuffix", listOf(
//                    build(element.op.cast()), build(element.expression)
//                )
//            )
//            AST.IN -> Call("AST.IN")
//            AST.NOT_IN -> Call("AST.NOT_IN")
//            is AST.IsCheckSuffix -> Call(
//                "AST.IsCheckSuffix", listOf(
//                    build(element.op.cast()), build(element.type)
//                )
//            )
//            is AST.KotlinFile -> error("KotlinFile should not be present in expression tree")
//            is AST.Label -> Call(
//                "AST.Label", listOf(
//                    element.name.asLiteral()
//                )
//            )
//            is AST.LambdaParameter -> Call(
//                "AST.LambdaParameter", listOf(
//                    build(element.variableDeclaration), element.type.buildNullable()
//                )
//            )
//            is AST.DoWhileStatement -> Call(
//                "AST.DoWhileStatement", listOf(
//                    element.body.buildNullable(), build(element.expression)
//                )
//            )
//            is AST.ForStatement -> Call(
//                "AST.ForStatement", listOf(
//                    element.annotations.buildList(),
//                    build(element.variableDeclaration),
//                    build(element.expression),
//                    element.body.buildNullable()
//                )
//            )
//            is AST.WhileStatement -> Call(
//                "AST.WhileStatement", listOf(
//                    build(element.expression), element.body.buildNullable()
//                )
//            )
//            AST.COLONCOLON -> Call("AST.COLONCOLON")
//            AST.DOT -> Call("AST.DOT")
//            AST.QUEST_DOT -> Call("AST.QUEST_DOT")
//            AST.ANNOTATION_ -> Call("AST.ANNOTATION_")
//            AST.DATA -> Call("AST.DATA")
//            AST.ENUM -> Call("AST.ENUM")
//            AST.INNER -> Call("AST.INNER")
//            AST.SEALED -> Call("AST.SEALED")
//            AST.VALUE -> Call("AST.VALUE")
//            AST.EXTERNAL -> Call("AST.EXTERNAL")
//            AST.INFIX -> Call("AST.INFIX")
//            AST.INLINE -> Call("AST.INLINE")
//            AST.OPERATOR -> Call("AST.OPERATOR")
//            AST.SUSPEND -> Call("AST.SUSPEND")
//            AST.TAILREC -> Call("AST.TAILREC")
//            AST.ABSTRACT -> Call("AST.ABSTRACT")
//            AST.FINAL -> Call("AST.FINAL")
//            AST.OPEN -> Call("AST.OPEN")
//            AST.LATEINIT -> Call("AST.LATEINIT")
//            AST.OVERRIDE -> Call("AST.OVERRIDE")
//            is AST.MultiAnnotation -> Call(
//                "AST.MultiAnnotation", listOf(
//                    element.target.buildNullable(), element.unescapedAnnotations.buildList()
//                )
//            )
//            AST.CROSSINLINE -> Call("AST.CROSSINLINE")
//            AST.NOINLINE -> Call("AST.NOINLINE")
//            AST.VARARG -> Call("AST.VARARG")
//            AST.ACTUAL -> Call("AST.ACTUAL")
//            AST.EXPECT -> Call("AST.EXPECT")
//            AST.CONST -> Call("AST.CONST")
//            AST.INTERNAL -> Call("AST.INTERNAL")
//            AST.PRIVATE -> Call("AST.PRIVATE")
//            AST.PROTECTED -> Call("AST.PROTECTED")
//            AST.PUBLIC -> Call("AST.PUBLIC")
//            AST.DIV -> Call("AST.DIV")
//            AST.MOD -> Call("AST.MOD")
//            AST.MULT -> Call("AST.MULT")
//            AST.NOT_IN -> Call("AST.NOT_IN")
//            is AST.Name -> error("Name should not be present in built AST")
//            is AST.NameAndType -> error("NameAndType should not be present in built AST")
//            is AST.ClassNavigationSuffix -> Call(
//                "AST.ClassNavigationSuffix", listOf(
//                    build(element.op)
//                )
//            )
//            is AST.ExpressionNavigationSuffix -> Call(
//                "AST.ExpressionNavigationSuffix", listOf(
//                    build(element.op), build(element.expression)
//                )
//            )
//            is AST.IdentifierNavigationSuffix -> Call(
//                "AST.IdentifierNavigationSuffix", listOf(
//                    build(element.op), element.name.asLiteral()
//                )
//            )
//            is AST.NullableType -> Call(
//                "AST.NullableType", listOf(
//                    build(element.type.cast())
//                )
//            )
//            AST.DYNAMIC -> Call("AST.DYNAMIC")
//            is AST.ObjectDeclaration -> Call(
//                "AST.ObjectDeclaration", listOf(
//                    element.modifiers.buildList(),
//                    element.name.asLiteral(),
//                    element.annotatedDelegationSpecifiers.buildList(),
//                    element.body.buildNullable()
//                )
//            )
//            is AST.ObjectLiteral -> Call(
//                "AST.ObjectLiteral", listOf(
//                    element.annotatedDelegationSpecifiers.buildList(), element.body.buildNullable()
//                )
//            )
//            is AST.PackageHeader -> error("PackageHeader should not be present in expression tree")
//            AST.DECR -> Call("AST.DECR")
//            AST.EXCL_EXCL -> Call("AST.EXCL_EXCL")
//            AST.INCR -> Call("AST.INCR")
//            is AST.PropertyDeclaration -> Call(
//                "AST.PropertyDeclaration", listOf(
//                    element.modifiers.buildList(),
//                    build(element.vov),
//                    element.typeParameters.buildList(),
//                    element.receiverType.buildNullable(),
//                    build(element.variableDeclaration),
//                    element.typeConstraints.buildList(),
//                    element.body.buildNullable(),
//                    element.getter.buildNullable(),
//                    element.setter.buildNullable()
//                )
//            )
//            is AST.PropertyDelegate -> Call(
//                "AST.PropertyDelegate", listOf(
//                    build(element.expression)
//                )
//            )
//            AST.REIFIED -> Call("AST.REIFIED")
//            is AST.Setter -> Call(
//                "AST.Setter", listOf(
//                    element.modifiers.buildList(),
//                    element.parameter.buildNullable(),
//                    element.type.buildNullable(),
//                    element.body.buildNullable()
//                )
//            )
//            is AST.FunctionTypeWithModifiers -> Call(
//                "AST.FunctionTypeWithModifiers", listOf(
//                    element.modifiers.buildList(),
//                    build(element.type)
//                )
//            )
//            is AST.ReceiverType -> Call(
//                "AST.ReceiverType", listOf(
//                    element.modifiers.buildList(),
//                    build(element.type.cast())
//                )
//            )
//            AST.SUSPEND -> Call("AST.SUSPEND")
//            AST.MULT -> Call("AST.MULT")
//            AST.DYNAMIC -> Call("AST.DYNAMIC")
//            is AST.TypeAlias -> error("TypeAlias should not be present in expression tree")
//            is AST.TypeArgumentsPostfix -> Call(
//                "AST.TypeArgumentsPostfix", listOf(
//                    element.typeArguments.buildList()
//                )
//            )
//            is AST.TypeConstraint -> Call(
//                "AST.TypeConstraint", listOf(
//                    element.annotations.buildList(), element.name.asLiteral(), build(element.type)
//                )
//            )
//            is AST.TypeParameter -> Call(
//                "AST.TypeParameter", listOf(
//                    element.modifiers.buildList(), element.name.asLiteral(), element.type.buildNullable()
//                )
//            )
//            AST.VAL -> Call("AST.VAL")
//            AST.VAR -> Call("AST.VAR")
//            is AST.ValueArgument -> Call("AST.ValueArgument", listOf(
//                element.annotations.buildList(),
//                element.name?.let { it.asLiteral() } ?: AST.NULL,
//                build(element.expression),
//                if (element.withSpread) AST.TRUE else AST.FALSE
//            ))
//            is AST.ValueParameter -> Call(
//                "AST.ValueParameter", listOf(
//                    element.modifiers.buildList(),
//                    element.name.asLiteral(),
//                    build(element.type),
//                    element.initial.buildNullable()
//                )
//            )
//            AST.IN -> Call("AST.IN")
//            AST.OUT -> Call("AST.OUT")
//            is AST.WhenEntry -> Call(
//                "AST.WhenEntry", listOf(
//                    element.conditions?.buildList() ?: AST.NULL, build(element.body.cast())
//                )
//            )
//            is AST.WhenSubject -> Call(
//                "AST.WhenSubject", listOf(
//                    element.annotations.buildList(),
//                    element.variableDeclaration.buildNullable(),
//                    build(element.expression)
//                )
//            )
//            AST.FILE -> Call("AST.FILE")
//            is AST.MultiVariableDeclaration -> Call(
//                "AST.MultiVariableDeclaration", listOf(
//                    element.decls.buildList()
//                )
//            )
//            is AST.SingleVariableDeclaration -> Call(
//                "AST.SingleVariableDeclaration", listOf(
//                    element.annotations.buildList(),
//                    element.name.asLiteral(),
//                    element.type.buildNullable()
//                )
//            )
//            else -> error("$element is not supported yet")
//        }
//
//    private fun Call(name: String, arguments: List<AST.Expression>? = null): AST.Expression {
//        val split = name.split(".")
//        val receiver = split[0]
//        val dotAccesses = split.drop(1)
//        val args = arrayListOf<AST.PostfixUnarySuffix>()
//        dotAccesses.mapTo(args) { AST.IdentifierNavigationSuffix(AST.DOT, it) }
//        if (arguments != null) {
//            args += AST.CallSuffix(
//                emptyList(),
//                arguments.map { it.asValueArgument() },
//                null
//            )
//        }
//        return AST.PostfixUnaryExpression(AST.NameExpression(receiver), args)
//    }
//
//    private fun String.asLiteral() = AST.StringLiteral("\"$this\"")
//
//    private fun List<AST.Expression>.callListOf(): AST.Expression = Call("listOf", this)
//
//    private fun AST.Expression.asValueArgument(): AST.ValueArgument = AST.ValueArgument(emptyList(), null, this, false)
//}