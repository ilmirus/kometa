package kometa.kotlin

import core.Locus

sealed class Token {
    abstract val locus: Locus

    class ShebangLine(override val locus: Locus, val s: String): Token()
    class LongLiteral(override val locus: Locus, val s: String): Token()
    class UnsignedLiteral(override val locus: Locus, val s: String): Token()
    class UnsignedLongLiteral(override val locus: Locus, val s: String): Token()
    class IntegerLiteral(override val locus: Locus, val s: String): Token()
    class FloatLiteral(override val locus: Locus, val s: String): Token()
    class DoubleLiteral(override val locus: Locus, val s: String): Token()
    class CharacterLiteral(override val locus: Locus, val s: String): Token()
    class StringLiteral(override val locus: Locus, val s: String): Token()
    sealed class BooleanLiteral: Token()

    class True(override val locus: Locus): BooleanLiteral()
    class False(override val locus: Locus): BooleanLiteral()

    class ReturnAt(override val locus: Locus, val s: String): Token()
    class ContinueAt(override val locus: Locus, val s: String): Token()
    class BreakAt(override val locus: Locus, val s: String): Token()
    class ThisAt(override val locus: Locus, val s: String): Token()

    class Identifier(override val locus: Locus, val s: String): Token()

    class NewLine(override val locus: Locus): Token()
    class Reserved(override val locus: Locus): Token()
    class Range(override val locus: Locus): Token()
    class Dot(override val locus: Locus): Token()
    class Comma(override val locus: Locus): Token()
    class LeftParen(override val locus: Locus): Token()
    class RightParen(override val locus: Locus): Token()
    class LeftSquare(override val locus: Locus): Token()
    class RightSquare(override val locus: Locus): Token()
    class LeftBrace(override val locus: Locus): Token()
    class RightBrace(override val locus: Locus): Token()
    class Increment(override val locus: Locus): Token()
    class AddAssign(override val locus: Locus): Token()
    class Add(override val locus: Locus): Token()
    class Decrement(override val locus: Locus): Token()
    class Arrow(override val locus: Locus): Token()
    class SubAssign(override val locus: Locus): Token()
    class Sub(override val locus: Locus): Token()
    class MulAssign(override val locus: Locus): Token()
    class Mul(override val locus: Locus): Token()
    class Div(override val locus: Locus): Token()
    class DivAssign(override val locus: Locus): Token()
    class ModAssign(override val locus: Locus): Token()
    class Mod(override val locus: Locus): Token()
    class And(override val locus: Locus): Token()
    class Or(override val locus: Locus): Token()
    class NotRefEqual(override val locus: Locus): Token()
    class NotEqual(override val locus: Locus): Token()
    class Not(override val locus: Locus): Token()
    class DefNotNull(override val locus: Locus): Token()
    class SafeCall(override val locus: Locus): Token()
    class Semicolon(override val locus: Locus): Token()
    class CallRef(override val locus: Locus): Token()
    class Colon(override val locus: Locus): Token()
    class Hash(override val locus: Locus): Token()
    class At(override val locus: Locus): Token()
    class SafeCallRef(override val locus: Locus): Token()
    class Elvis(override val locus: Locus): Token()
    class Query(override val locus: Locus): Token()
    class LessEqual(override val locus: Locus): Token()
    class Less(override val locus: Locus): Token()
    class GreaterEqual(override val locus: Locus): Token()
    class Greater(override val locus: Locus): Token()
    class AsSafe(override val locus: Locus): Token()
    class RefEqual(override val locus: Locus): Token()
    class Equal(override val locus: Locus): Token()
    class DoubleArrow(override val locus: Locus): Token()
    class Assign(override val locus: Locus): Token()

    class File(override val locus: Locus): Token()
    class Package(override val locus: Locus): Token()
    class Import(override val locus: Locus): Token()
    class Class(override val locus: Locus): Token()
    class Interface(override val locus: Locus): Token()
    class Fun(override val locus: Locus): Token()
    class Object(override val locus: Locus): Token()
    class Val(override val locus: Locus): Token()
    class Var(override val locus: Locus): Token()
    class Typealias(override val locus: Locus): Token()
    class Constructor(override val locus: Locus): Token()
    class By(override val locus: Locus): Token()
    class Companion(override val locus: Locus): Token()
    class Init(override val locus: Locus): Token()
    class This(override val locus: Locus): Token()
    class Super(override val locus: Locus): Token()
    class Typeof(override val locus: Locus): Token()
    class Where(override val locus: Locus): Token()
    class If(override val locus: Locus): Token()
    class Else(override val locus: Locus): Token()
    class When(override val locus: Locus): Token()
    class Try(override val locus: Locus): Token()
    class Catch(override val locus: Locus): Token()
    class Finally(override val locus: Locus): Token()
    class For(override val locus: Locus): Token()
    class Do(override val locus: Locus): Token()
    class While(override val locus: Locus): Token()
    class Throw(override val locus: Locus): Token()
    class Return(override val locus: Locus): Token()
    class Continue(override val locus: Locus): Token()
    class Break(override val locus: Locus): Token()
    class As(override val locus: Locus): Token()
    class Is(override val locus: Locus): Token()
    class In(override val locus: Locus): Token()
    class NotIs(override val locus: Locus): Token()
    class NotIn(override val locus: Locus): Token()
    class Out(override val locus: Locus): Token()
    class Field(override val locus: Locus): Token()
    class Property(override val locus: Locus): Token()
    class Get(override val locus: Locus): Token()
    class Set(override val locus: Locus): Token()
    class Receiver(override val locus: Locus): Token()
    class Param(override val locus: Locus): Token()
    class Setparam(override val locus: Locus): Token()
    class Delegate(override val locus: Locus): Token()
    class Dynamic(override val locus: Locus): Token()
    class Public(override val locus: Locus): Token()
    class Private(override val locus: Locus): Token()
    class Protected(override val locus: Locus): Token()
    class Internal(override val locus: Locus): Token()
    class Enum(override val locus: Locus): Token()
    class Sealed(override val locus: Locus): Token()
    class Annotation(override val locus: Locus): Token()
    class Data(override val locus: Locus): Token()
    class Inner(override val locus: Locus): Token()
    class Tailrec(override val locus: Locus): Token()
    class Operator(override val locus: Locus): Token()
    class Inline(override val locus: Locus): Token()
    class Infix(override val locus: Locus): Token()
    class External(override val locus: Locus): Token()
    class Suspend(override val locus: Locus): Token()
    class Override(override val locus: Locus): Token()
    class Abstract(override val locus: Locus): Token()
    class Final(override val locus: Locus): Token()
    class Open(override val locus: Locus): Token()
    class Const(override val locus: Locus): Token()
    class Lateinit(override val locus: Locus): Token()
    class Vararg(override val locus: Locus): Token()
    class Noinline(override val locus: Locus): Token()
    class Crossinline(override val locus: Locus): Token()
    class Reified(override val locus: Locus): Token()
    class Null(override val locus: Locus): Token()
    class Value(override val locus: Locus): Token()
    class Expect(override val locus: Locus): Token()
    class Actual(override val locus: Locus): Token()

    override fun toString(): String = when (this) {
        is Abstract -> "abstract"
        is Add -> "+"
        is AddAssign -> "+="
        is Annotation -> "annotation"
        is Arrow -> "->"
        is As -> "as"
        is Assign -> "="
        is AsSafe -> "as?"
        is At -> "@"
        is Break -> "break"
        is By -> "by"
        is True -> "true"
        is False -> "false"
        is Null -> "null"
        is BreakAt -> "break@$s"
        is Catch -> "catch"
        is Class -> "class"
        is Colon -> ":"
        is CallRef -> "::"
        is Comma -> ","
        is Companion -> "companion"
        is And -> "&&"
        is Const -> "const"
        is Constructor -> "constructor"
        is Continue -> "continue"
        is Crossinline -> "crossinline"
        is CharacterLiteral -> s
        is ContinueAt -> "continue@$s"
        is ThisAt -> "this@$s"
        is Data -> "data"
        is Decrement -> "--"
        is Delegate -> "delegate"
        is Or -> "||"
        is Div -> "/"
        is DivAssign -> "/="
        is Do -> "do"
        is Dot -> "."
        is DoubleArrow -> "=>"
        is Dynamic -> "dynamic"
        is DoubleLiteral -> s
        is Else -> "else"
        is Elvis -> "?:"
        is Enum -> "enum"
        is Equal -> "=="
        is RefEqual -> "==="
        is Not -> "!"
        is NotEqual -> "!="
        is NotRefEqual -> "!=="
        is External -> "external"
        is Field -> "field"
        is File -> "file"
        is Final -> "final"
        is Finally -> "finally"
        is For -> "for"
        is Fun -> "fun"
        is FloatLiteral -> s
        is GreaterEqual -> ">="
        is Get -> "get"
        is Hash -> "#"
        is If -> "if"
        is Import -> "import"
        is In -> "in"
        is Increment -> "++"
        is Infix -> "infix"
        is Init -> "init"
        is Inline -> "inline"
        is Inner -> "inner"
        is Interface -> "interface"
        is Internal -> "internal"
        is Is -> "is"
        is Identifier -> s
        is Less -> "<"
        is Lateinit -> "lateinit"
        is LeftBrace -> "{"
        is LessEqual -> "<="
        is LeftParen -> "("
        is LeftSquare -> "["
        is LongLiteral -> s
        is IntegerLiteral -> s
        is UnsignedLiteral -> s
        is UnsignedLongLiteral -> s
        is Mod -> "%"
        is ModAssign -> "%="
        is Mul -> "*"
        is MulAssign -> "*="
        is NewLine -> "\n"
        is Noinline -> "noinline"
        is NotIn -> "!in"
        is NotIs -> "!is"
        is Object -> "object"
        is Open -> "open"
        is Operator -> "operator"
        is Out -> "out"
        is Override -> "override"
        is Package -> "package"
        is Param -> "param"
        is Private -> "private"
        is Property -> "property"
        is Protected -> "protected"
        is Public -> "public"
        is Query -> "?"
        is SafeCallRef -> "?::"
        is Range -> ".."
        is Greater -> ">"
        is RightBrace -> "}"
        is Receiver -> "receiver"
        is Reified -> "reified"
        is Reserved -> "..."
        is Return -> "return"
        is RightParen-> ")"
        is RightSquare -> "]"
        is ReturnAt -> "return@$s"
        is Sealed -> "sealed"
        is Semicolon -> ";"
        is Set -> "set"
        is Setparam -> "setparam"
        is Sub -> "-"
        is SubAssign -> "-="
        is Super -> "super"
        is Suspend -> "suspend"
        is ShebangLine -> "#!$s"
        is StringLiteral -> s
        is Tailrec -> "tailrec"
        is This -> "this"
        is Throw -> "throw"
        is Try -> "try"
        is Typeof -> "typeof"
        is Typealias -> "typealias"
        is Val -> "val"
        is Var -> "var"
        is Vararg -> "vararg"
        is When -> "when"
        is Where -> "where"
        is While -> "while"
        is DefNotNull -> "!!"
        is SafeCall -> "?."
        is Value -> "value"
        is Expect -> "expect"
        is Actual -> "actual"
    }
}