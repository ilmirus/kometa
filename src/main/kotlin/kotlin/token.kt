package kometa.kotlin

sealed class Token {
    class ShebangLine(val s: String): Token()
    class LongLiteral(val s: String): Token()
    class UnsignedLiteral(val s: String): Token()
    class UnsignedLongLiteral(val s: String): Token()
    class IntegerLiteral(val s: String): Token()
    class FloatLiteral(val s: String): Token()
    class DoubleLiteral(val s: String): Token()
    class CharacterLiteral(val s: String): Token()
    class StringLiteral(val s: String): Token()
    sealed class BooleanLiteral: Token()

    object TRUE: BooleanLiteral()
    object FALSE: BooleanLiteral()

    class ReturnAt(val s: String): Token()
    class ContinueAt(val s: String): Token()
    class BreakAt(val s: String): Token()
    class ThisAt(val s: String): Token()

    class Identifier(val s: String): Token()

    object NL: Token()
    object RESERVED: Token()
    object RANGE: Token()
    object DOT: Token()
    object COMMA: Token()
    object LPAREN: Token()
    object RPAREN: Token()
    object LSQUARE: Token()
    object RSQUARE: Token()
    object LCURL: Token()
    object RCURL: Token()
    object INCR: Token()
    object ADD_ASSIGNMENT: Token()
    object ADD: Token()
    object DECR: Token()
    object ARROW: Token()
    object SUB_ASSIGNMENT: Token()
    object SUB: Token()
    object MULT_ASSIGNMENT: Token()
    object MULT: Token()
    object DIV_ASSIGNMENT: Token()
    object DIV: Token()
    object MOD_ASSIGNMENT: Token()
    object MOD: Token()
    object CONJ: Token()
    object DISJ: Token()
    object EXCL_EQEQ: Token()
    object EXCL_EQ: Token()
    object EXCL: Token()
    object EXCL_EXCL: Token()
    object QUEST_DOT: Token()
    object SEMICOLON: Token()
    object COLONCOLON: Token()
    object COLON: Token()
    object HASH: Token()
    object AT: Token()
    object Q_COLONCOLON: Token()
    object ELVIS: Token()
    object QUEST: Token()
    object LE: Token()
    object LANGLE: Token()
    object GE: Token()
    object RANGLE: Token()
    object AS_SAFE: Token()
    object EQEQEQ: Token()
    object EQEQ: Token()
    object DOUBLE_ARROW: Token()
    object ASSIGNMENT: Token()

    object FILE: Token()
    object PACKAGE: Token()
    object IMPORT: Token()
    object CLASS: Token()
    object INTERFACE: Token()
    object FUN: Token()
    object OBJECT: Token()
    object VAL: Token()
    object VAR: Token()
    object TYPE_ALIAS: Token()
    object CONSTRUCTOR: Token()
    object BY: Token()
    object COMPANION: Token()
    object INIT: Token()
    object THIS: Token()
    object SUPER: Token()
    object TYPEOF: Token()
    object WHERE: Token()
    object IF: Token()
    object ELSE: Token()
    object WHEN: Token()
    object TRY: Token()
    object CATCH: Token()
    object FINALLY: Token()
    object FOR: Token()
    object DO: Token()
    object WHILE: Token()
    object THROW: Token()
    object RETURN: Token()
    object CONTINUE: Token()
    object BREAK: Token()
    object AS: Token()
    object IS: Token()
    object IN: Token()
    object NOT_IS: Token()
    object NOT_IN: Token()
    object OUT: Token()
    object FIELD: Token()
    object PROPERTY: Token()
    object GET: Token()
    object SET: Token()
    object RECEIVER: Token()
    object PARAM: Token()
    object SETPARAM: Token()
    object DELEGATE: Token()
    object DYNAMIC: Token()
    object PUBLIC: Token()
    object PRIVATE: Token()
    object PROTECTED: Token()
    object INTERNAL: Token()
    object ENUM: Token()
    object SEALED: Token()
    object ANNOTATION: Token()
    object DATA: Token()
    object INNER: Token()
    object TAILREC: Token()
    object OPERATOR: Token()
    object INLINE: Token()
    object INFIX: Token()
    object EXTERNAL: Token()
    object SUSPEND: Token()
    object OVERRIDE: Token()
    object ABSTRACT: Token()
    object FINAL: Token()
    object OPEN: Token()
    object CONST: Token()
    object LATEINIT: Token()
    object VARARG: Token()
    object NOINLINE: Token()
    object CROSSINLINE: Token()
    object REIFIED: Token()
    object NULL: Token()
    object VALUE: Token()
    object EXPECT: Token()
    object ACTUAL: Token()

    override fun toString(): String = when (this) {
        ABSTRACT -> "abstract"
        ADD -> "+"
        ADD_ASSIGNMENT -> "+="
        ANNOTATION -> "annotation"
        ARROW -> "->"
        AS -> "as"
        ASSIGNMENT -> "="
        AS_SAFE -> "as?"
        AT -> "@"
        BREAK -> "break"
        BY -> "by"
        TRUE -> "true"
        FALSE -> "false"
        NULL -> "null"
        is BreakAt -> "break@$s"
        CATCH -> "catch"
        CLASS -> "class"
        COLON -> ":"
        COLONCOLON -> "::"
        COMMA -> ","
        COMPANION -> "companion"
        CONJ -> "&&"
        CONST -> "const"
        CONSTRUCTOR -> "constructor"
        CONTINUE -> "continue"
        CROSSINLINE -> "crossinline"
        is CharacterLiteral -> s
        is ContinueAt -> "continue@$s"
        is ThisAt -> "this@$s"
        DATA -> "data"
        DECR -> "--"
        DELEGATE -> "delegate"
        DISJ -> "||"
        DIV -> "/"
        DIV_ASSIGNMENT -> "/="
        DO -> "do"
        DOT -> "."
        DOUBLE_ARROW -> "=>"
        DYNAMIC -> "dynamic"
        is DoubleLiteral -> s
        ELSE -> "else"
        ELVIS -> "?:"
        ENUM -> "enum"
        EQEQ -> "=="
        EQEQEQ -> "==="
        EXCL -> "!"
        EXCL_EQ -> "!="
        EXCL_EQEQ -> "!=="
        EXTERNAL -> "external"
        FIELD -> "field"
        FILE -> "file"
        FINAL -> "final"
        FINALLY -> "finally"
        FOR -> "for"
        FUN -> "fun"
        is FloatLiteral -> s
        GE -> ">="
        GET -> "get"
        HASH -> "#"
        IF -> "if"
        IMPORT -> "import"
        IN -> "in"
        INCR -> "++"
        INFIX -> "infix"
        INIT -> "init"
        INLINE -> "inline"
        INNER -> "inner"
        INTERFACE -> "interface"
        INTERNAL -> "internal"
        IS -> "is"
        is Identifier -> s
        LANGLE -> "<"
        LATEINIT -> "lateinit"
        LCURL -> "{"
        LE -> "<="
        LPAREN -> "("
        LSQUARE -> "["
        is LongLiteral -> s
        is IntegerLiteral -> s
        is UnsignedLiteral -> s
        is UnsignedLongLiteral -> s
        MOD -> "%"
        MOD_ASSIGNMENT -> "%="
        MULT -> "*"
        MULT_ASSIGNMENT -> "*="
        NL -> "\n"
        NOINLINE -> "noinline"
        NOT_IN -> "!in"
        NOT_IS -> "!is"
        OBJECT -> "object"
        OPEN -> "open"
        OPERATOR -> "operator"
        OUT -> "out"
        OVERRIDE -> "override"
        PACKAGE -> "package"
        PARAM -> "param"
        PRIVATE -> "private"
        PROPERTY -> "property"
        PROTECTED -> "protected"
        PUBLIC -> "public"
        QUEST -> "?"
        Q_COLONCOLON -> "?::"
        RANGE -> ".."
        RANGLE -> ">"
        RCURL -> "}"
        RECEIVER -> "receiver"
        REIFIED -> "reified"
        RESERVED -> "..."
        RETURN -> "return"
        RPAREN -> ")"
        RSQUARE -> "]"
        is ReturnAt -> "return@$s"
        SEALED -> "sealed"
        SEMICOLON -> ";"
        SET -> "set"
        SETPARAM -> "setparam"
        SUB -> "-"
        SUB_ASSIGNMENT -> "-="
        SUPER -> "super"
        SUSPEND -> "suspend"
        is ShebangLine -> "#!$s"
        is StringLiteral -> s
        TAILREC -> "tailrec"
        THIS -> "this"
        THROW -> "throw"
        TRY -> "try"
        TYPEOF -> "typeof"
        TYPE_ALIAS -> "typealias"
        VAL -> "val"
        VAR -> "var"
        VARARG -> "vararg"
        WHEN -> "when"
        WHERE -> "where"
        WHILE -> "while"
        EXCL_EXCL -> "!!"
        QUEST_DOT -> "?."
        VALUE -> "value"
        EXPECT -> "expect"
        ACTUAL -> "actual"
    }
}