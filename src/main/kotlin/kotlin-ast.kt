package kometa.kotlin

import kometa.util.cast
import kometa.util.reduce2

object AST {
    abstract class AstNode

    class KotlinFile(
        val fileAnnotations: List<Annotation>,
        val packageHeader: PackageHeader?,
        val importList: List<ImportHeader>,
        val topLevelObject: List<Declaration>
    ) : AstNode() {
        constructor(fa: List<AstNode>, ph: AstNode?, il: List<AstNode>, obj: List<AstNode>) :
                this(
                    fa.cast<List<Annotation>>(),
                    ph.cast<PackageHeader>(),
                    il.cast<List<ImportHeader>>(),
                    obj.cast<List<Declaration>>()
                )
    }

    sealed interface Declaration : ClassMemberDeclaration, Labellable

    class PackageHeader(val fqName: String) : AstNode()

    class ImportHeader(val fqName: String, val star: Boolean = false, val alias: String? = null) : AstNode()

    class TypeAlias(
        val modifiers: List<Modifier>,
        val alias: String,
        val typeParameters: List<TypeParameter>,
        val originalType: Type
    ) : AstNode(), Declaration {
        constructor(
            modifiers: List<AstNode>,
            alias: String,
            typeParameters: List<AstNode>,
            originalType: AstNode
        ) : this(modifiers.cast(), alias, typeParameters.cast(), originalType.cast())
    }

    sealed class ClassDeclaration : AstNode(), Declaration

    class Class(
        val modifiers: List<Modifier>,
        val name: String,
        val typeParameters: List<TypeParameter>,
        val primaryConstructor: Constructor?,
        val annotatedDelegationSpecifiers: List<AnnotatedDelegationSpecifier>,
        val typeConstraints: List<TypeConstraint>,
        val body: ClassBodyOrEnumClassBody?
    ) : ClassDeclaration() {
        constructor(
            modifiers: List<AstNode>,
            name: String,
            typeParameters: List<AstNode>,
            primaryConstructor: AstNode?,
            delegationSpecifiers: List<AstNode>,
            typeConstraints: List<AstNode>,
            body: AstNode?
        ) : this(
            modifiers.cast<List<Modifier>>(),
            name,
            typeParameters.cast<List<TypeParameter>>(),
            primaryConstructor.cast<Constructor?>(),
            delegationSpecifiers.cast<List<AnnotatedDelegationSpecifier>>(),
            typeConstraints.cast(),
            body.cast()
        )
    }

    class Interface(
        val modifiers: List<Modifier>,
        val name: String,
        val typeParameters: List<TypeParameter>,
        val typeConstraints: List<TypeConstraint>,
        val body: ClassBody?
    ) : ClassDeclaration() {
        constructor(
            modifiers: List<AstNode>,
            name: String,
            typeParameters: List<AstNode>,
            typeConstraints: List<AstNode>,
            body: AstNode?
        ) : this(
            modifiers.cast(),
            name,
            typeParameters.cast(),
            typeConstraints.cast(),
            body.cast()
        )
    }

    class FunInterface(
        val modifiers: List<Modifier>,
        val name: String,
        val typeParameters: List<TypeParameter>,
        val typeConstraints: List<TypeConstraint>,
        val body: ClassBody?
    ) : ClassDeclaration() {
        constructor(
            modifiers: List<AstNode>,
            name: String,
            typeParameters: List<AstNode>,
            typeConstraints: List<AstNode>,
            body: AstNode?
        ) : this(
            modifiers.cast(),
            name,
            typeParameters.cast(),
            typeConstraints.cast(),
            body.cast()
        )
    }

    class ClassHeader(
        val name: String,
        val typeParameters: List<AstNode>,
        val primaryConstructor: AstNode?,
        val delegationSpecifiers: List<AstNode>,
        val typeConstraints: List<AstNode>
    ) : AstNode()

    class Constructor(
        val modifiers: List<Modifier>,
        val classParameters: List<ClassParameter>,
        val constructorDelegationCall: ConstructorDelegationCall?,
        val body: Block?,
        val primary: Boolean
    ) : AstNode(), ClassMemberDeclaration {
        constructor(
            modifiers: List<AstNode>,
            classParameters: List<AstNode>,
            constructorDelegationCall: AstNode?,
            body: AstNode?,
            primary: Boolean
        ) : this(modifiers.cast(), classParameters.cast(), constructorDelegationCall.cast(), body.cast(), primary)
    }

    sealed class ConstructorDelegationCall : AstNode()

    class ThisCall(val arguments: List<ValueArgument>) : ConstructorDelegationCall() {
        companion object {
            operator fun invoke(arguments: List<AstNode>): ThisCall = ThisCall(arguments.cast())
        }
    }

    class SuperCall(val arguments: List<ValueArgument>) : ConstructorDelegationCall() {
        companion object {
            operator fun invoke(arguments: List<AstNode>): SuperCall = SuperCall(arguments.cast())
        }
    }

    sealed class ClassBodyOrEnumClassBody : AstNode()

    class ClassBody(val members: List<ClassMemberDeclaration>) : ClassBodyOrEnumClassBody() {
        companion object {
            operator fun invoke(members: List<AstNode>): ClassBody = ClassBody(members.cast())
        }
    }

    class ClassParameter(
        val modifiers: List<Modifier>,
        val name: String,
        val type: Type,
        val initial: Expression?,
        val vov: ValOrVar?
    ) : AstNode() {
        constructor(
            modifiers: List<AstNode>,
            name: String,
            type: AstNode,
            initial: AstNode?,
            vov: AstNode?
        ) : this(
            modifiers.cast<List<Modifier>>(),
            name,
            type.cast<Type>(),
            initial.cast<Expression?>(),
            vov.cast<ValOrVar?>()
        )
    }

    class AnnotatedDelegationSpecifier(
        val annotations: List<Annotation>,
        val delegationSpecifiers: List<DelegationSpecifier>
    ) : AstNode() {
        companion object {
            operator fun invoke(
                annotation: List<AstNode>,
                delegationSpecifiers: List<AstNode>
            ): AnnotatedDelegationSpecifier =
                AnnotatedDelegationSpecifier(annotation.cast(), delegationSpecifiers.cast())
        }
    }

    sealed interface DelegationSpecifier

    class ConstructorInvocation(
        val userType: UserType, val valueArguments: List<ValueArgument>
    ) : AstNode(), DelegationSpecifier, UnescapedAnnotation {
        constructor(userType: AstNode, valueArguments: List<AstNode>) : this(userType.cast(), valueArguments.cast())
    }

    sealed interface UserTypeOrFunctionType

    class ExplicitDelegation(
        val type: UserTypeOrFunctionType, val expression: Expression
    ) : AstNode(), DelegationSpecifier {
        constructor(type: AstNode, expression: AstNode) : this(type.cast<UserTypeOrFunctionType>(), expression.cast())
    }

    class TypeParameter(val modifiers: List<TypeParameterModifier>, val name: String, val type: Type?) : AstNode() {
        constructor(
            modifiers: List<AstNode>, name: String, type: AstNode?
        ) : this(modifiers.cast<List<TypeParameterModifier>>(), name, type.cast())
    }

    class TypeConstraint(val annotations: List<Annotation>, val name: String, val type: Type) : AstNode() {
        constructor(
            annotations: List<AstNode>, name: String, type: AstNode
        ) : this(annotations.cast(), name, type.cast())
    }

    sealed interface ClassMemberDeclaration

    class AnonymousInitializer(val block: Block) : AstNode(), ClassMemberDeclaration {
        constructor(block: AstNode) : this(block.cast())
    }

    class CompanionObject(
        val modifiers: List<Modifier>,
        val name: String?,
        val annotatedDelegationSpecifiers: List<AnnotatedDelegationSpecifier>,
        val body: ClassBody?
    ) : AstNode(), ClassMemberDeclaration {
        constructor(
            modifiers: List<AstNode>, name: String?, annotatedDelegationSpecifiers: List<AstNode>, body: AstNode?
        ) : this(modifiers.cast(), name, annotatedDelegationSpecifiers.cast(), body.cast())
    }

    class ValueParameter(
        val modifiers: List<ParameterModifier>,
        val name: String,
        val type: Type,
        val initial: Expression?
    ) : AstNode() {
        constructor(
            modifiers: List<AstNode>, name: String, type: Type, initial: AstNode?
        ) : this(modifiers.cast<List<ParameterModifier>>(), name, type, initial.cast())
    }

    class FunctionDeclaration(
        val modifiers: List<Modifier>,
        val typeParameters: List<TypeParameter>,
        val receiverType: ReceiverType?,
        val name: String,
        val parameters: List<ValueParameter>,
        val returnType: Type?,
        val typeConstraints: List<TypeConstraint>,
        val body: FunctionBody?
    ) : AstNode(), Declaration {
        constructor(
            modifiers: List<AstNode>,
            typeParameters: List<AstNode>,
            receiverType: AstNode?,
            name: String,
            parameters: List<AstNode>,
            returnType: AstNode?,
            typeConstraints: List<AstNode>,
            body: AstNode?
        ) : this(
            modifiers.cast(),
            typeParameters.cast(),
            receiverType.cast(),
            name,
            parameters.cast(),
            returnType.cast(),
            typeConstraints.cast(),
            body.cast()
        )
    }

    sealed class FunctionBody : AstNode()

    sealed interface ExpressionBodyOrPropertyDelegate

    class BlockBody(val block: Block) : FunctionBody() {
        constructor(block: AstNode) : this(block.cast())
    }

    class ExpressionBody(
        val expression: Expression
    ) : FunctionBody(), ExpressionBodyOrPropertyDelegate {
        constructor(expression: AstNode) : this(expression.cast())
    }

    sealed class VariableDeclaration : AstNode()

    class SingleVariableDeclaration(
        val annotations: List<Annotation>,
        val name: String,
        val type: Type?
    ) : VariableDeclaration() {
        constructor(
            annotations: List<AstNode>, name: String, type: AstNode?
        ) : this(annotations.cast(), name, type.cast())
    }

    class MultiVariableDeclaration(
        val decls: List<SingleVariableDeclaration>
    ) : VariableDeclaration() {
        companion object {
            operator fun invoke(decls: List<AstNode>) = MultiVariableDeclaration(decls.cast())
        }
    }

    class PropertyDeclaration(
        val modifiers: List<Modifier>,
        val vov: ValOrVar,
        val typeParameters: List<TypeParameter>,
        val receiverType: Type?,
        val variableDeclaration: VariableDeclaration,
        val typeConstraints: List<TypeConstraint>,
        val body: ExpressionBodyOrPropertyDelegate?,
        val getter: Getter?,
        val setter: Setter?
    ) : AstNode(), Declaration {
        constructor(
            modifiers: List<AstNode>,
            vov: AstNode,
            typeParameters: List<AstNode>,
            receiverType: AstNode?,
            variableDeclarations: AstNode,
            typeConstraints: List<AstNode>,
            body: AstNode?,
            getter: AstNode?,
            setter: AstNode?
        ) : this(
            modifiers.cast<List<Modifier>>(),
            vov.cast<ValOrVar>(),
            typeParameters.cast<List<TypeParameter>>(),
            receiverType.cast<Type?>(),
            variableDeclarations.cast<VariableDeclaration>(),
            typeConstraints.cast<List<TypeConstraint>>(),
            body.cast<ExpressionBodyOrPropertyDelegate?>(),
            getter.cast<Getter?>(),
            setter.cast<Setter?>()
        )
    }

    class PropertyDelegate(val expression: Expression) : AstNode(), ExpressionBodyOrPropertyDelegate {
        constructor(expression: AstNode) : this(expression.cast())
    }

    class Getter(val modifiers: List<Modifier>, val type: Type?, val body: FunctionBody?) : AstNode() {
        constructor(
            modifiers: List<AstNode>, type: AstNode?, body: AstNode?
        ) : this(modifiers.cast(), type.cast(), body.cast())
    }

    class Setter(
        val modifiers: List<Modifier>,
        val parameter: ValueParameter?,
        val type: Type?,
        val body: FunctionBody?
    ) : AstNode() {
        constructor(
            modifiers: List<AstNode>, parameter: AstNode?, type: AstNode?, body: AstNode?
        ) : this(modifiers.cast(), parameter.cast(), type.cast(), body.cast())
    }

    data class NameAndType(val name: String, val type: Type?) : AstNode() {
        constructor(name: String, type: AstNode?) : this(name, type.cast())
    }

    class ObjectDeclaration(
        val modifiers: List<Modifier>,
        val name: String,
        val annotatedDelegationSpecifiers: List<AnnotatedDelegationSpecifier>,
        val body: ClassBody?
    ) : AstNode(), Declaration {
        constructor(
            modifiers: List<AstNode>, name: String, delegationSpecifiers: List<AstNode>, body: AstNode?
        ) : this(
            modifiers.cast<List<Modifier>>(),
            name,
            delegationSpecifiers.cast<List<AnnotatedDelegationSpecifier>>(),
            body.cast<ClassBody?>()
        )
    }

    class EnumClassBody(
        val entries: List<EnumEntry>, val members: List<ClassMemberDeclaration>
    ) : ClassBodyOrEnumClassBody() {
        companion object {
            operator fun invoke(entries: List<AstNode>, members: List<AstNode>): EnumClassBody =
                EnumClassBody(entries.cast(), members.cast())
        }
    }

    class EnumEntry(
        val modifiers: List<Modifier>, val name: String, val arguments: List<ValueArgument>, val body: ClassBody?
    ) : AstNode() {
        constructor(
            modifiers: List<AstNode>, name: String, arguments: List<AstNode>, body: AstNode?
        ) : this(modifiers.cast(), name, arguments.cast(), body.cast())
    }

    sealed class Type : AstNode()

    class FunctionTypeWithModifiers(val modifiers: List<TypeModifier>, val type: FunctionType) : Type() {
        constructor(modifiers: List<AstNode>, type: AstNode) : this(
            modifiers.cast<List<TypeModifier>>(),
            type.cast<FunctionType>()
        )
    }

    sealed class TypeReference : Type(), TypeReferenceOrParenthesizedType

    object DYNAMIC : TypeReference()

    class ReceiverType(val modifiers: List<TypeModifier>, val type: ReceiverTypeWithoutModifiers) : Type() {
        companion object {
            operator fun invoke(modifiers: List<AstNode>, type: AstNode): Type =
                if (modifiers.isEmpty()) type.cast()
                else ReceiverType(modifiers.cast(), type.cast())
        }
    }

    sealed interface TypeModifier

    class UserType(val simpleTypes: List<SimpleUserType>) : TypeReference(), UserTypeOrFunctionType,
        DelegationSpecifier, UnescapedAnnotation, ReceiverTypeWithoutModifiers {
        companion object {
            operator fun invoke(simpleTypes: List<AstNode>): UserType = UserType(simpleTypes.cast())
        }
    }

    class NullableType(val type: TypeReferenceOrParenthesizedType) : AstNode(), ReceiverTypeWithoutModifiers {
        constructor(type: AstNode) : this(type.cast<TypeReferenceOrParenthesizedType>())
    }

    sealed interface TypeProjection : ReceiverTypeWithoutModifiers
    class TypeProjectionWithType(val modifiers: List<TypeParameterModifier>, val type: Type) : AstNode(),
        TypeProjection {
        constructor(modifiers: List<AstNode>, type: AstNode) : this(
            modifiers.cast<List<TypeParameterModifier>>(),
            type.cast<Type>()
        )
    }

    object MULT : MultiplicativeOperator(), TypeProjection

    sealed interface TypeReferenceOrParenthesizedType

    class SimpleUserType(val name: String, val typeArguments: List<TypeProjection>) : AstNode() {
        companion object {
            operator fun invoke(name: String, typeArguments: List<AstNode>): SimpleUserType =
                SimpleUserType(name, typeArguments.cast())
        }
    }

    class FunctionType(
        val receiverType: Type?, val parameters: List<Type>, val returnType: Type
    ) : AstNode(), UserTypeOrFunctionType, DelegationSpecifier {
        constructor(
            receiverType: AstNode?, parameters: List<AstNode>, returnType: AstNode
        ) : this(receiverType.cast(), parameters.cast(), returnType.cast())
    }

    sealed interface ReceiverTypeWithoutModifiers

    class Statement(val label: List<LabelOrAnnotation>, val expression: Labellable) : AstNode(), ControlStructuredBody {
        constructor(label: List<AstNode>, expression: AstNode) : this(
            label.cast<List<LabelOrAnnotation>>(),
            expression.cast<Labellable>()
        )
    }

    sealed interface Labellable

    sealed class Assignment : AstNode(), Labellable

    class DirectAssignment(val lhs: Expression, val rhs: Expression) : Assignment() {
        constructor(lhs: AstNode, rhs: AstNode) : this(lhs.cast<Expression>(), rhs.cast())
    }

    class AugmentedAssignment(val lhs: AssignableExpression, val op: AssignmentAndOperator, val rhs: Expression) :
        Assignment() {
        constructor(
            lhs: AstNode, op: AstNode, rhs: AstNode
        ) : this(lhs.cast<AssignableExpression>(), op.cast<AssignmentAndOperator>(), rhs.cast<Expression>())
    }

    sealed class LoopStatement : AstNode(), Labellable

    class ForStatement(
        val annotations: List<Annotation>,
        val variableDeclaration: VariableDeclaration,
        val expression: Expression,
        val body: ControlStructuredBody?
    ) : LoopStatement() {
        constructor(
            annotations: List<AstNode>,
            variableDeclaration: AstNode,
            expression: AstNode,
            body: AstNode?
        ) : this(
            annotations.cast<List<Annotation>>(),
            variableDeclaration.cast<VariableDeclaration>(),
            expression.cast<Expression>(),
            body.cast<ControlStructuredBody?>()
        )
    }

    class WhileStatement(val expression: Expression, val body: ControlStructuredBody?) : LoopStatement() {
        constructor(expression: AstNode, body: AstNode?) : this(
            expression.cast<Expression>(),
            body.cast<ControlStructuredBody?>()
        )
    }

    class DoWhileStatement(val body: ControlStructuredBody?, val expression: Expression) : LoopStatement() {
        constructor(body: AstNode?, expression: AstNode) : this(
            body.cast<ControlStructuredBody?>(),
            expression.cast<Expression>()
        )
    }

    sealed class Expression : AstNode(), WhenCondition, Labellable

    class Disjunction(val subs: List<Expression>) : Expression() {
        companion object {
            operator fun invoke(subs: List<AstNode>): Expression =
                subs.singleOrNull()?.cast() ?: Disjunction(subs.cast())
        }
    }

    class Conjunction(val subs: List<Expression>) : Expression() {
        companion object {
            operator fun invoke(subs: List<AstNode>): Expression =
                subs.singleOrNull()?.cast() ?: Conjunction(subs.cast())
        }
    }

    class Equality(val lhs: Expression, val op: EqualityOperator, val rhs: Expression) : Expression() {
        companion object {
            operator fun invoke(subs: List<AstNode>): Expression = subs.reduce2<AstNode, Expression> { lhs, op, rhs ->
                Equality(lhs.cast(), op.cast(), rhs.cast())
            }
        }
    }

    class Comparison(val lhs: Expression, val op: ComparisonOperator, val rhs: Expression) : Expression() {
        companion object {
            operator fun invoke(subs: List<AstNode>): Expression = subs.reduce2<AstNode, Expression> { lhs, op, rhs ->
                Comparison(lhs.cast(), op.cast(), rhs.cast())
            }
        }
    }

    class GenericCallLikeComparison(val infixOperation: InfixOperation, val suffixes: List<CallSuffix>) : Expression() {
        companion object {
            operator fun invoke(infixOperation: AstNode, suffixes: List<AstNode>): Expression =
                if (suffixes.isEmpty()) infixOperation.cast()
                else GenericCallLikeComparison(infixOperation.cast(), suffixes.cast())
        }
    }

    class InfixOperation(val lhs: Expression, val suffixes: List<IsOrInCheckSuffix>) : Expression() {
        companion object {
            operator fun invoke(lhs: AstNode, suffixes: List<AstNode>): Expression =
                if (suffixes.isEmpty()) lhs.cast()
                else InfixOperation(lhs.cast(), suffixes.cast())
        }
    }

    sealed class IsOrInCheckSuffix : AstNode(), WhenCondition

    class InCheckSuffix(val op: InOperator, val expression: Expression) : IsOrInCheckSuffix() {
        constructor(op: AstNode, expression: AstNode) : this(op.cast<InOperator>(), expression.cast())
    }

    class IsCheckSuffix(val op: IsOperator, val type: Type) : IsOrInCheckSuffix() {
        constructor(op: AstNode, type: AstNode) : this(op.cast(), type.cast())
    }

    sealed interface InOperator
    object NOT_IN : AstNode(), InOperator

    sealed class IsOperator : AstNode()
    object IS : IsOperator()
    object NOT_IS : IsOperator()

    class ElvisExpression(val lhs: Expression, val rhs: Expression) : Expression() {
        companion object {
            operator fun invoke(subs: List<AstNode>): Expression = subs.reduce { lhs, rhs ->
                ElvisExpression(lhs.cast(), rhs.cast())
            }.cast()
        }
    }

    class InfixFunctionCall(val lhs: Expression, val functionName: String, val rhs: Expression) : Expression() {
        companion object {
            operator fun invoke(subs: List<AstNode>): Expression = subs.reduce2<AstNode, Expression> { lhs, op, rhs ->
                InfixFunctionCall(lhs.cast(), op.toString(), rhs.cast())
            }
        }
    }

    class RangeExpression(val lhs: Expression, val rhs: Expression) : Expression() {
        companion object {
            operator fun invoke(subs: List<AstNode>): Expression = subs.reduce { lhs, rhs ->
                RangeExpression(lhs.cast(), rhs.cast())
            }.cast()
        }
    }

    class Additive(val lhs: Expression, val op: AdditiveOperator, val rhs: Expression) : Expression() {
        companion object {
            operator fun invoke(subs: List<AstNode>): Expression = subs.reduce2<AstNode, Expression> { lhs, op, rhs ->
                Additive(lhs.cast(), op.cast(), rhs.cast())
            }
        }
    }

    class Multiplicative(val lhs: Expression, val op: MultiplicativeOperator, val rhs: Expression) : Expression() {
        companion object {
            operator fun invoke(subs: List<AstNode>): Expression = subs.reduce2<AstNode, Expression> { lhs, op, rhs ->
                Multiplicative(lhs.cast(), op.cast(), rhs.cast())
            }
        }
    }

    class AsExpression(val lhs: Expression, val op: AsOperator, val rhs: Expression) : Expression() {
        companion object {
            operator fun invoke(subs: List<AstNode>): Expression = subs.reduce2<AstNode, Expression> { lhs, op, rhs ->
                AsExpression(lhs.cast(), op.cast(), rhs.cast())
            }
        }
    }

    class PrefixUnaryExpression(
        val unaryPrefixes: List<UnaryPrefix>, val expression: Expression
    ) : Expression(), AssignableExpression {
        companion object {
            operator fun invoke(unaryPrefixes: List<AstNode>, expression: AstNode): Expression =
                if (unaryPrefixes.isEmpty()) expression.cast()
                else PrefixUnaryExpression(unaryPrefixes.cast(), expression.cast())
        }
    }

    class PostfixUnaryExpression(val expression: Expression, val postfixUnarySuffixes: List<PostfixUnarySuffix>) :
        Expression() {
        companion object {
            operator fun invoke(expression: AstNode, postfixUnarySuffixes: List<AstNode>): Expression =
                if (postfixUnarySuffixes.isEmpty()) expression.cast()
                else PostfixUnaryExpression(expression.cast(), postfixUnarySuffixes.cast())
        }
    }

    sealed interface UnaryPrefix

    sealed interface PostfixUnarySuffix

    class TypeArgumentsPostfix(val typeArguments: List<TypeProjection>) : AstNode(), AssignableSuffix {
        companion object {
            operator fun invoke(typeArguments: List<AstNode>): TypeArgumentsPostfix =
                TypeArgumentsPostfix(typeArguments.cast())
        }
    }

    class DirectlyAssignableExpression(val expression: Expression, val suffix: AssignableSuffix) : Expression() {
        constructor(expression: AstNode, suffix: AstNode) : this(
            expression.cast<Expression>(),
            suffix.cast<AssignableSuffix>()
        )
    }

    class NameExpression(val name: String) : Expression()

    sealed interface AssignableSuffix : PostfixUnarySuffix

    class IndexingSuffix(val expressions: List<Expression>) : AstNode(), AssignableSuffix {
        companion object {
            operator fun invoke(expressions: List<AstNode>): IndexingSuffix = IndexingSuffix(expressions.cast())
        }
    }

    sealed class NavigationSuffix : AstNode(), AssignableSuffix

    class IdentifierNavigationSuffix(val op: MemberAccessOperator, val name: String) : NavigationSuffix() {
        constructor(op: AstNode, name: String) : this(op.cast(), name)
    }

    class ExpressionNavigationSuffix(val op: MemberAccessOperator, val expression: Expression) : NavigationSuffix() {
        constructor(op: AstNode, expression: AstNode) : this(op.cast(), expression.cast())
    }

    class ClassNavigationSuffix(val op: MemberAccessOperator) : NavigationSuffix() {
        constructor(op: AstNode) : this(op.cast())
    }

    class CallSuffix(
        val typeArguments: List<TypeProjection>,
        val valueArguments: List<ValueArgument>,
        val annotatedLambda: AnnotatedLambda?
    ) : AstNode(), PostfixUnarySuffix {
        constructor(
            typeArguments: List<AstNode>, valueArguments: List<AstNode>, annotatedLambda: AstNode?
        ) : this(typeArguments.cast<List<TypeProjection>>(), valueArguments.cast(), annotatedLambda.cast())
    }

    class AnnotatedLambda(val annotations: List<Annotation>, val label: Label?, val lambdaLiteral: LambdaLiteral) :
        AstNode() {
        constructor(
            annotations: List<AstNode>, label: AstNode?, lambdaLiteral: AstNode
        ) : this(annotations.cast(), label.cast(), lambdaLiteral.cast())
    }

    class ValueArgument(
        val annotations: List<Annotation>,
        val name: String?,
        val expression: Expression,
        val withSpread: Boolean
    ) : AstNode() {
        constructor(
            annotations: List<AstNode>, name: String?, expression: AstNode, withSpread: Boolean
        ) : this(annotations.cast<List<Annotation>>(), name, expression.cast(), withSpread)
    }

    class CollectionLiteral(val expressions: List<Expression>) : Expression() {
        companion object {
            operator fun invoke(expressions: List<AstNode>) = CollectionLiteral(expressions.cast())
        }
    }

    sealed class LiteralConstant : Expression()

    class StringLiteral(val s: String) : LiteralConstant()

    sealed class BooleanLiteral : LiteralConstant()
    object TRUE : BooleanLiteral()
    object FALSE : BooleanLiteral()
    object NULL : LiteralConstant()

    class IntegerLiteral(val s: String) : LiteralConstant()
    class LongLiteral(val s: String) : LiteralConstant()
    class UnsignedLiteral(val s: String) : LiteralConstant()
    class UnsignedLongLiteral(val s: String) : LiteralConstant()
    class FloatLiteral(val s: String) : LiteralConstant()
    class DoubleLiteral(val s: String) : LiteralConstant()
    class CharacterLiteral(val s: String) : LiteralConstant()

    sealed class FunctionLiteral : Expression()

    class LambdaLiteral(val parameters: List<LambdaParameter>, val statements: List<Statement>) : FunctionLiteral() {
        companion object {
            operator fun invoke(parameters: List<AstNode>, statements: List<AstNode>) =
                LambdaLiteral(parameters.cast(), statements.cast())
        }
    }

    class LambdaParameter(val variableDeclaration: VariableDeclaration, val type: Type?) : AstNode() {
        constructor(variableDeclarations: AstNode, type: AstNode?) : this(
            variableDeclarations.cast(),
            type.cast()
        )
    }

    class AnonymousFunction(
        val receiverType: Type?,
        val valueParameters: List<ValueParameter>,
        val returnType: Type?,
        val typeConstraints: List<TypeConstraint>,
        val body: FunctionBody?
    ) : FunctionLiteral() {
        constructor(
            receiverType: AstNode?,
            valueParameters: List<AstNode>,
            returnType: AstNode?,
            typeConstraints: List<AstNode>,
            body: AstNode?
        ) : this(receiverType.cast(), valueParameters.cast(), returnType.cast(), typeConstraints.cast(), body.cast())
    }

    class ObjectLiteral(
        val annotatedDelegationSpecifiers: List<AnnotatedDelegationSpecifier>, val body: ClassBody?
    ) : AstNode() {
        constructor(
            annotatedDelegationSpecifiers: List<AstNode>, body: AstNode?
        ) : this(annotatedDelegationSpecifiers.cast(), body.cast())
    }

    object THIS : Expression()

    class ThisAt(val name: String) : Expression()

    class Super(val type: Type?, val name: String?) : Expression() {
        constructor(type: AstNode?, name: String?) : this(type.cast(), name)
    }

    class IfExpression(
        val expression: Expression,
        val thenExpression: ControlStructuredBody?,
        val elseExpression: ControlStructuredBody?
    ) : Expression() {
        constructor(
            expression: AstNode, thenExpression: AstNode?, elseExpression: AstNode?
        ) : this(expression.cast<Expression>(), thenExpression.cast<ControlStructuredBody?>(), elseExpression.cast())
    }

    class WhenSubject(
        val annotations: List<Annotation>,
        val variableDeclaration: SingleVariableDeclaration?,
        val expression: Expression
    ) : AstNode() {
        constructor(
            annotations: List<AstNode>, variableDeclaration: AstNode?, expression: AstNode
        ) : this(annotations.cast(), variableDeclaration.cast(), expression.cast())
    }

    class WhenExpression(val subject: WhenSubject?, val entries: List<WhenEntry>) : Expression() {
        constructor(subject: AstNode?, entries: List<AstNode>) : this(subject.cast(), entries.cast())
    }

    class WhenEntry(val conditions: List<WhenCondition>?, val body: ControlStructuredBody) : AstNode() {
        constructor(
            conditions: List<AstNode>?, body: AstNode
        ) : this(conditions.cast<List<WhenCondition>?>(), body.cast<ControlStructuredBody>())
    }

    sealed interface WhenCondition

    class TryExpression(val block: Block, val catchBlocks: List<CatchBlock>, val finallyBlock: Block?) : Expression() {
        constructor(
            block: AstNode, catchBlocks: List<AstNode>, finallyBlock: AstNode?
        ) : this(block.cast(), catchBlocks.cast(), finallyBlock.cast())
    }

    class CatchBlock(val annotations: List<Annotation>, val name: String, val type: Type, val block: Block) :
        AstNode() {
        constructor(
            annotations: List<AstNode>, name: String, type: AstNode, block: AstNode
        ) : this(annotations.cast(), name, type.cast(), block.cast())
    }

    sealed class JumpExpression : Expression()

    class Throw(val expression: Expression) : JumpExpression() {
        constructor(expression: AstNode) : this(expression.cast())
    }

    class Return(val expression: Expression?) : JumpExpression() {
        constructor(expression: AstNode?) : this(expression.cast())
    }

    class ReturnAt(val label: String, val expression: Expression?) : JumpExpression() {
        constructor(label: String, expression: AstNode?) : this(label, expression.cast())
    }

    object CONTINUE : JumpExpression()

    class ContinueAt(val label: String) : JumpExpression()

    object BREAK : JumpExpression()

    class BreakAt(val label: String) : JumpExpression()

    class CallableReference(val receiverType: ReceiverType?, val name: String) : AstNode() {
        constructor(receiverType: AstNode?, name: String) : this(receiverType.cast(), name)
    }

    sealed class AssignmentAndOperator : AstNode()
    object ADD_ASSIGNMENT : AssignmentAndOperator()
    object SUB_ASSIGNMENT : AssignmentAndOperator()
    object MULT_ASSIGNMENT : AssignmentAndOperator()
    object DIV_ASSIGNMENT : AssignmentAndOperator()
    object MOD_ASSIGNMENT : AssignmentAndOperator()

    sealed class EqualityOperator : AstNode()
    object EXCL_EQ : EqualityOperator()
    object EXCL_EQEQ : EqualityOperator()
    object EQEQ : EqualityOperator()
    object EQEQEQ : EqualityOperator()

    sealed class ComparisonOperator : AstNode()
    object LESS : ComparisonOperator()
    object GREATER : ComparisonOperator()
    object LE : ComparisonOperator()
    object GE : ComparisonOperator()

    sealed class AdditiveOperator : AstNode()
    object ADD : AdditiveOperator(), PrefixUnaryOperator
    object SUB : AdditiveOperator(), PrefixUnaryOperator

    sealed class MultiplicativeOperator : AstNode()
    object DIV : MultiplicativeOperator()
    object MOD : MultiplicativeOperator()

    sealed class AsOperator : AstNode()
    object AS : AsOperator()
    object AS_SAFE : AsOperator()

    sealed interface PrefixUnaryOperator : UnaryPrefix
    object EXCL : AstNode(), PrefixUnaryOperator

    sealed class PostfixUnaryOperator : AstNode(), PostfixUnarySuffix
    object INCR : PostfixUnaryOperator(), PrefixUnaryOperator
    object DECR : PostfixUnaryOperator(), PrefixUnaryOperator
    object EXCL_EXCL : PostfixUnaryOperator()

    sealed class MemberAccessOperator() : AstNode()
    object DOT : MemberAccessOperator()
    object QUEST_DOT : MemberAccessOperator()
    object COLONCOLON : MemberAccessOperator()

    object SUSPEND : FunctionModifier(), TypeModifier

    sealed class ClassModifier : Modifier()
    object ENUM : ClassModifier()
    object SEALED : ClassModifier()
    object ANNOTATION_ : ClassModifier()
    object DATA : ClassModifier()
    object INNER : ClassModifier()
    object VALUE : ClassModifier()

    sealed class MemberModifier : Modifier()
    object OVERRIDE : MemberModifier()
    object LATEINIT : MemberModifier()

    sealed class VisibilityModifier : Modifier()
    object PUBLIC : VisibilityModifier()
    object PRIVATE : VisibilityModifier()
    object INTERNAL : VisibilityModifier()
    object PROTECTED : VisibilityModifier()

    sealed class FunctionModifier : Modifier()
    object TAILREC : FunctionModifier()
    object OPERATOR : FunctionModifier()
    object INFIX : FunctionModifier()
    object INLINE : FunctionModifier()
    object EXTERNAL : FunctionModifier()

    sealed class PropertyModifier : Modifier()
    object CONST : PropertyModifier()

    sealed class InheritanceModifier : Modifier()
    object ABSTRACT : InheritanceModifier()
    object FINAL : InheritanceModifier()
    object OPEN : InheritanceModifier()

    sealed class PlatformModifier : Modifier()
    object EXPECT : PlatformModifier()
    object ACTUAL : PlatformModifier()

    sealed interface AssignableExpression

    sealed interface LabelOrAnnotation

    class Label(val name: String) : AstNode(), LabelOrAnnotation, UnaryPrefix

    sealed interface ControlStructuredBody

    sealed class ParameterModifier : Modifier()
    object VARARG : ParameterModifier()
    object NOINLINE : ParameterModifier()
    object CROSSINLINE : ParameterModifier()

    sealed interface TypeParameterModifier

    object REIFIED : AstNode(), TypeParameterModifier

    sealed class Variance : AstNode(), TypeParameterModifier, VarianceModifier
    object IN : Variance(), InOperator
    object OUT : Variance()

    sealed class Modifier : AstNode()
    sealed class Annotation : ParameterModifier(), TypeParameterModifier, TypeProjectionModifier,
        LabelOrAnnotation, UnaryPrefix, TypeModifier

    class SingleAnnotation(
        val target: AnnotationUseSiteTarget?, val unescapedAnnotation: UnescapedAnnotation
    ) : Annotation() {
        constructor(
            target: AstNode?, unescapedAnnotation: AstNode
        ) : this(target.cast<AnnotationUseSiteTarget?>(), unescapedAnnotation.cast<UnescapedAnnotation>())
    }

    class MultiAnnotation(
        val target: AnnotationUseSiteTarget?, val unescapedAnnotations: List<UnescapedAnnotation>
    ) : Annotation() {
        constructor(
            target: AstNode?, unescapedAnnotation: List<AstNode>
        ) : this(target.cast<AnnotationUseSiteTarget?>(), unescapedAnnotation.cast<List<UnescapedAnnotation>>())
    }

    sealed class AnnotationUseSiteTarget : AstNode()
    object FIELD : AnnotationUseSiteTarget()
    object PROPERTY : AnnotationUseSiteTarget()
    object GET : AnnotationUseSiteTarget()
    object SET : AnnotationUseSiteTarget()
    object RECEIVER : AnnotationUseSiteTarget()
    object PARAM : AnnotationUseSiteTarget()
    object SETPARAM : AnnotationUseSiteTarget()
    object DELEGATE : AnnotationUseSiteTarget()
    object FILE : AnnotationUseSiteTarget()

    sealed interface UnescapedAnnotation

    sealed interface TypeProjectionModifier

    sealed interface VarianceModifier : TypeProjectionModifier, TypeParameterModifier

    class Block(val statements: List<Statement>) : AstNode(), ControlStructuredBody {
        companion object {
            operator fun invoke(statements: List<AstNode>): Block = Block(statements.cast())
        }
    }

    sealed class ValOrVar : AstNode()
    object VAL : ValOrVar()
    object VAR : ValOrVar()

    class Name(val s: String) : AstNode() {
        override fun toString(): String = s
    }
}