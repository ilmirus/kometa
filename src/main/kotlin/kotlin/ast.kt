package kometa.kotlin.ast

import kometa.kotlin.Visitor

data class FqName(val names: List<String>) {
    override fun toString(): String = names.joinToString(".")
}

data class Locus(
    val row: Int,
    val column: Int
) {
    override fun toString(): String = "$row:$column"

    companion object {
        val FILE_START: Locus = Locus(1, 1)
    }
}

sealed class Node {
    abstract val locus: Locus
    var tag: Any? = null

    interface WithAnnotations {
        val anns: List<Modifier.AnnotationSet>
    }

    interface WithModifiers : WithAnnotations {
        val mods: List<Modifier>
        override val anns: List<Modifier.AnnotationSet>
            get() = mods.mapNotNull { it as? Modifier.AnnotationSet }
    }

    interface Entry : WithAnnotations {
        val pkg: Package?
        val imports: List<Import>
    }

    data class File(
        override val anns: List<Modifier.AnnotationSet>,
        override val pkg: Package?,
        override val imports: List<Import>,
        val decls: List<Decl>
    ) : Node(), Entry {
        override val locus: Locus
            get() = Locus.FILE_START

        override fun accept(v: Visitor) {
            v.visitFile(this)
        }

        override fun acceptChildren(v: Visitor) {
            for (ann in anns) {
                ann.accept(v)
            }
            pkg?.accept(v)
            for (imp in imports) {
                imp.accept(v)
            }
            for (decl in decls) {
                decl.accept(v)
            }
        }
    }

    data class Script(
        override val anns: List<Modifier.AnnotationSet>,
        override val pkg: Package?,
        override val imports: List<Import>,
        val exprs: List<Expr>
    ) : Node(), Entry {
        override val locus: Locus
            get() = Locus.FILE_START

        override fun accept(v: Visitor) {
            v.visitScript(this)
        }

        override fun acceptChildren(v: Visitor) {
            for (ann in anns) {
                ann.accept(v)
            }
            pkg?.accept(v)
            for (imp in imports) {
                imp.accept(v)
            }
            for (expr in exprs) {
                expr.accept(v)
            }
        }
    }

    data class Package(
        override val locus: Locus,
        val fqName: FqName
    ) : Node() {
        override fun accept(v: Visitor) {
            v.visitPackage(this)
        }

        override fun acceptChildren(v: Visitor) {
            // no children
        }
    }

    data class Import(
        override val locus: Locus,
        val fqName: FqName,
        val wildcard: Boolean,
        val alias: String?
    ) : Node() {
        override fun accept(v: Visitor) {
            v.visitImport(this)
        }

        override fun acceptChildren(v: Visitor) {
            // no children
        }
    }

    sealed class Decl : Node() {
        data class Class(
            override val locus: Locus,
            override val mods: List<Modifier>,
            val kind: Kind,
            val name: String,
            val typeParams: List<TypeParam>,
            val primaryConstructor: PrimaryConstructor?,
            val parentAnns: List<Modifier.AnnotationSet>,
            val parents: List<Parent>,
            val typeConstraints: List<TypeConstraint>,
            // TODO: Can include primary constructor
            val members: List<Decl>
        ) : Decl(), WithModifiers {
            enum class Kind {
                CLASS, ENUM_CLASS, INTERFACE, OBJECT, COMPANION_OBJECT
            }

            sealed class Parent : Node() {
                data class ConstructorCall(
                    val type: TypeRef.Simple,
                    val typeArgs: List<Type>,
                    val args: List<ValueArg>,
                    val lambda: Expr.Call.TrailLambda?
                ) : Parent() {
                    override val locus: Locus
                        get() = type.locus

                    override fun accept(v: Visitor) {
                        v.visitClassParentConstructorCall(this)
                    }

                    override fun acceptChildren(v: Visitor) {
                        type.accept(v)
                        for (typeArg in typeArgs) {
                            typeArg.accept(v)
                        }
                        for (arg in args) {
                            arg.accept(v)
                        }
                        lambda?.accept(v)
                    }
                }

                data class InterfaceType(
                    val type: TypeRef.Simple,
                    val delegated: Expr?
                ) : Parent() {
                    override val locus: Locus
                        get() = type.locus

                    override fun accept(v: Visitor) {
                        v.visitClassParentInterfaceType(this)
                    }

                    override fun acceptChildren(v: Visitor) {
                        type.accept(v)
                        delegated?.accept(v)
                    }
                }
            }

            data class PrimaryConstructor(
                override val locus: Locus,
                override val mods: List<Modifier>,
                val params: List<Func.Param>
            ) : Node(), WithModifiers {
                override fun accept(v: Visitor) {
                    v.visitPrimaryConstructor(this)
                }

                override fun acceptChildren(v: Visitor) {
                    for (mod in mods) {
                        mod.accept(v)
                    }
                    for (param in params) {
                        param.accept(v)
                    }
                }
            }

            override fun accept(v: Visitor) {
                v.visitClass(this)
            }

            override fun acceptChildren(v: Visitor) {
                for (mod in mods) {
                    mod.accept(v)
                }
                for (typeParam in typeParams) {
                    typeParam.accept(v)
                }
                primaryConstructor?.accept(v)
                for (parentAnn in parentAnns) {
                    parentAnn.accept(v)
                }
                for (typeConstraint in typeConstraints) {
                    typeConstraint.accept(v)
                }
                for (member in members) {
                    member.accept(v)
                }
            }
        }

        data class Init(val block: Block) : Decl() {
            override val locus: Locus
                get() = block.locus

            override fun accept(v: Visitor) {
                v.visitInitDecl(this)
            }

            override fun acceptChildren(v: Visitor) {
                block.accept(v)
            }
        }

        data class Func(
            override val locus: Locus,
            override val mods: List<Modifier>,
            val typeParams: List<TypeParam>,
            val receiverType: Type?,
            // Name not present on anonymous functions
            val name: String?,
            val paramTypeParams: List<TypeParam>,
            val params: List<Param>,
            val type: Type?,
            val typeConstraints: List<TypeConstraint>,
            val body: Body?
        ) : Decl(), WithModifiers {
            data class Param(
                override val locus: Locus,
                override val mods: List<Modifier>,
                val isVal: Boolean?,
                val name: String,
                // Type can be null for anon functions
                val type: Type?,
                val default: Expr?
            ) : Node(), WithModifiers {
                override fun accept(v: Visitor) {
                    v.visitFuncParam(this)
                }

                override fun acceptChildren(v: Visitor) {
                    for (mod in mods) {
                        mod.accept(v)
                    }
                    type?.accept(v)
                    default?.accept(v)
                }
            }

            sealed class Body : Node() {
                data class Block(val block: Node.Block) : Body() {
                    override val locus: Locus
                        get() = block.locus

                    override fun accept(v: Visitor) {
                        v.visitFuncBlockBody(this)
                    }

                    override fun acceptChildren(v: Visitor) {
                        block.accept(v)
                    }
                }

                data class Expr(val expr: Node.Expr) : Body() {
                    override val locus: Locus
                        get() = expr.locus

                    override fun accept(v: Visitor) {
                        v.visitFuncExprBody(this)
                    }

                    override fun acceptChildren(v: Visitor) {
                        expr.accept(v)
                    }
                }
            }

            override fun accept(v: Visitor) {
                v.visitFunc(this)
            }

            override fun acceptChildren(v: Visitor) {
                for (mod in mods) {
                    mod.accept(v)
                }
                for (typeParam in typeParams) {
                    typeParam.accept(v)
                }
                receiverType?.accept(v)
                for (paramTypeParam in paramTypeParams) {
                    paramTypeParam.accept(v)
                }
                for (param in params) {
                    param.accept(v)
                }
                type?.accept(v)
                for (typeConstraint in typeConstraints) {
                    typeConstraint.accept(v)
                }
                body?.accept(v)
            }
        }

        data class Property(
            override val locus: Locus,
            override val mods: List<Modifier>,
            val readOnly: Boolean,
            val typeParams: List<TypeParam>,
            val receiverType: Type?,
            // Always at least one, more than one is destructuring, null is underscore in destructure
            val vars: List<Var?>,
            val typeConstraints: List<TypeConstraint>,
            val delegated: Boolean,
            val expr: Expr?,
            val getter: Getter?,
            val setter: Setter?
        ) : Decl(), WithModifiers {
            data class Var(
                override val locus: Locus,
                val name: String,
                val type: Type?
            ) : Node() {
                override fun accept(v: Visitor) {
                    v.visitPropertyVar(this)
                }

                override fun acceptChildren(v: Visitor) {
                    type?.accept(v)
                }
            }

            data class Getter(
                override val locus: Locus,
                override val mods: List<Modifier>,
                val type: Type?,
                val body: Func.Body?
            ) : Node(), WithModifiers {
                override fun accept(v: Visitor) {
                    v.visitPropertyGetter(this)
                }

                override fun acceptChildren(v: Visitor) {
                    for (mod in mods) {
                        mod.accept(v)
                    }
                    type?.accept(v)
                    body?.accept(v)
                }
            }

            data class Setter(
                override val locus: Locus,
                override val mods: List<Modifier>,
                val paramMods: List<Modifier>,
                val paramName: String?,
                val paramType: Type?,
                val body: Func.Body?
            ) : Node(), WithModifiers {
                override fun accept(v: Visitor) {
                    v.visitPropertySetter(this)
                }

                override fun acceptChildren(v: Visitor) {
                    for (mod in mods) {
                        mod.accept(v)
                    }
                    for (paramMod in paramMods) {
                        paramMod.accept(v)
                    }
                    paramType?.accept(v)
                    body?.accept(v)
                }
            }

            override fun accept(v: Visitor) {
                v.visitProperty(this)
            }

            override fun acceptChildren(v: Visitor) {
                for (mod in mods) {
                    mod.accept(v)
                }
                for (typeParam in typeParams) {
                    typeParam.accept(v)
                }
                receiverType?.accept(v)
                for (vr in vars) {
                    vr?.accept(v)
                }
                for (typeConstraint in typeConstraints) {
                    typeConstraint.accept(v)
                }
                expr?.accept(v)
                getter?.accept(v)
                setter?.accept(v)
            }
        }

        data class TypeAlias(
            override val locus: Locus,
            override val mods: List<Modifier>,
            val name: String,
            val typeParams: List<TypeParam>,
            val type: Type
        ) : Decl(), WithModifiers {
            override fun accept(v: Visitor) {
                v.visitTypeAlias(this)
            }

            override fun acceptChildren(v: Visitor) {
                for (mod in mods) {
                    mod.accept(v)
                }
                for (typeParam in typeParams) {
                    typeParam.accept(v)
                }
                type.accept(v)
            }
        }

        data class Constructor(
            override val locus: Locus,
            override val mods: List<Modifier>,
            val params: List<Func.Param>,
            val delegationCall: DelegationCall?,
            val block: Block?
        ) : Decl(), WithModifiers {
            data class DelegationCall(
                override val locus: Locus,
                val target: DelegationTarget,
                val args: List<ValueArg>
            ) : Node() {
                override fun accept(v: Visitor) {
                    v.visitConstructorDelegationCall(this)
                }

                override fun acceptChildren(v: Visitor) {
                    for (arg in args) {
                        arg.accept(v)
                    }
                }
            }

            enum class DelegationTarget { THIS, SUPER }

            override fun accept(v: Visitor) {
                v.visitConstructor(this)
            }

            override fun acceptChildren(v: Visitor) {
                for (mod in mods) {
                    mod.accept(v)
                }
                for (param in params) {
                    param.accept(v)
                }
                delegationCall?.accept(v)
                block?.accept(v)
            }
        }

        data class EnumEntry(
            override val locus: Locus,
            override val mods: List<Modifier>,
            val name: String,
            val args: List<ValueArg>,
            val members: List<Decl>
        ) : Decl(), WithModifiers {
            override fun accept(v: Visitor) {
                v.visitEnumEntry(this)
            }

            override fun acceptChildren(v: Visitor) {
                for (mod in mods) {
                    mod.accept(v)
                }
                for (arg in args) {
                    arg.accept(v)
                }
                for (member in members) {
                    member.accept(v)
                }
            }
        }
    }

    data class TypeParam(
        override val locus: Locus,
        override val mods: List<Modifier>,
        val name: String,
        val type: TypeRef?
    ) : Node(), WithModifiers {
        override fun accept(v: Visitor) {
            v.visitTypeParam(this)
        }

        override fun acceptChildren(v: Visitor) {
            for (mod in mods) {
                mod.accept(v)
            }
            type?.accept(v)
        }
    }

    data class TypeConstraint(
        override val locus: Locus,
        override val anns: List<Modifier.AnnotationSet>,
        val name: String,
        val type: Type
    ) : Node(), WithAnnotations {
        override fun accept(v: Visitor) {
            v.visitTypeConstraint(this)
        }

        override fun acceptChildren(v: Visitor) {
            for (ann in anns) {
                ann.accept(v)
            }
            type.accept(v)
        }
    }

    sealed class TypeRef : Node() {
        data class Paren(
            override val locus: Locus,
            override val mods: List<Modifier>,
            val type: TypeRef
        ) : TypeRef(), WithModifiers {
            override fun accept(v: Visitor) {
                v.visitParenType(this)
            }

            override fun acceptChildren(v: Visitor) {
                for (mod in mods) {
                    mod.accept(v)
                }
                type.accept(v)
            }
        }

        data class Func(
            override val locus: Locus,
            val receiverType: Type?,
            val params: List<Param>,
            val type: Type
        ) : TypeRef() {
            data class Param(
                override val locus: Locus,
                val name: String?,
                val type: Type
            ) : Node() {
                override fun accept(v: Visitor) {
                    v.visitFuncTypeParam(this)
                }

                override fun acceptChildren(v: Visitor) {
                    type.accept(v)
                }
            }

            override fun accept(v: Visitor) {
                v.visitFuncType(this)
            }

            override fun acceptChildren(v: Visitor) {
                receiverType?.accept(v)
                for (param in params) {
                    param.accept(v)
                }
                type.accept(v)
            }
        }

        data class Simple(
            val pieces: List<Piece>
        ) : TypeRef() {
            data class Piece(
                override val locus: Locus,
                val name: String,
                // Null means wildcard
                val typeParams: List<Type?>
            ) : Node() {
                override fun accept(v: Visitor) {
                    v.visitSimpleTypePiece(this)
                }

                override fun acceptChildren(v: Visitor) {
                    for (typeParam in typeParams) {
                        typeParam?.accept(v)
                    }
                }
            }

            override val locus: Locus
                get() = pieces.first().locus

            override fun accept(v: Visitor) {
                v.visitSimpleType(this)
            }

            override fun acceptChildren(v: Visitor) {
                for (piece in pieces) {
                    piece.accept(v)
                }
            }
        }

        data class Nullable(val type: TypeRef) : TypeRef() {
            override val locus: Locus
                get() = type.locus

            override fun accept(v: Visitor) {
                v.visitNullableType(this)
            }

            override fun acceptChildren(v: Visitor) {
                type.accept(v)
            }
        }

        data class Dynamic(
            override val locus: Locus
        ) : TypeRef() {
            override fun accept(v: Visitor) {
                v.visitDynamicType(this)
            }

            override fun acceptChildren(v: Visitor) {
                // no children
            }
        }
    }

    data class Type(
        override val locus: Locus,
        override val mods: List<Modifier>,
        val ref: TypeRef
    ) : Node(), WithModifiers {
        override fun accept(v: Visitor) {
            v.visitType(this)
        }

        override fun acceptChildren(v: Visitor) {
            for (mod in mods) {
                mod.accept(v)
            }
            ref.accept(v)
        }
    }

    data class ValueArg(
        override val locus: Locus,
        val name: String?,
        val asterisk: Boolean,
        val expr: Expr
    ) : Node() {
        override fun accept(v: Visitor) {
            v.visitValueArg(this)
        }

        override fun acceptChildren(v: Visitor) {
            expr.accept(v)
        }
    }

    sealed class Expr : Node() {
        data class If(
            override val locus: Locus,
            val condition: Expr,
            val thenBody: Expr,
            val elseBody: Expr?
        ) : Expr() {
            override fun accept(v: Visitor) {
                v.visitIfExpr(this)
            }

            override fun acceptChildren(v: Visitor) {
                condition.accept(v)
                thenBody.accept(v)
                elseBody?.accept(v)
            }
        }

        data class Try(
            override val locus: Locus,
            val block: Block,
            val catches: List<Catch>,
            val finallyBlock: Block?
        ) : Expr() {
            data class Catch(
                override val locus: Locus,
                override val anns: List<Modifier.AnnotationSet>,
                val varName: String,
                val varType: TypeRef.Simple,
                val block: Block
            ) : Node(), WithAnnotations {
                override fun accept(v: Visitor) {
                    v.visitCatch(this)
                }

                override fun acceptChildren(v: Visitor) {
                    for (ann in anns) {
                        ann.accept(v)
                    }
                    varType.accept(v)
                    block.accept(v)
                }
            }

            override fun accept(v: Visitor) {
                v.visitTryExpr(this)
            }

            override fun acceptChildren(v: Visitor) {
                block.accept(v)
                for (catch in catches) {
                    catch.accept(v)
                }
                finallyBlock?.accept(v)
            }
        }

        data class For(
            override val locus: Locus,
            override val anns: List<Modifier.AnnotationSet>,
            // More than one means destructure, null means underscore
            val vars: List<Decl.Property.Var?>,
            val inExpr: Expr,
            val body: Expr
        ) : Expr(), WithAnnotations {
            override fun accept(v: Visitor) {
                v.visitForStmt(this)
            }

            override fun acceptChildren(v: Visitor) {
                for (ann in anns) {
                    ann.accept(v)
                }
                for (vr in vars) {
                    vr?.accept(v)
                }
                inExpr.accept(v)
                body.accept(v)
            }
        }

        data class While(
            override val locus: Locus,
            val condition: Expr,
            val body: Expr
        ) : Expr() {
            override fun accept(v: Visitor) {
                v.visitWhileStmt(this)
            }

            override fun acceptChildren(v: Visitor) {
                condition.accept(v)
                body.accept(v)
            }
        }

        data class DoWhile(
            override val locus: Locus,
            val body: Expr,
            val condition: Expr
        ) : Expr() {
            override fun accept(v: Visitor) {
                v.visitDoWhileStmt(this)
            }

            override fun acceptChildren(v: Visitor) {
                body.accept(v)
                condition.accept(v)
            }
        }

        data class BinaryOp(
            val lhs: Expr,
            val oper: Oper,
            val rhs: Expr
        ) : Expr() {
            sealed class Oper : Node() {
                data class Infix(
                    override val locus: Locus,
                    val str: String
                ) : Oper() {
                    override fun accept(v: Visitor) {
                        v.visitInfixBinaryOpOper(this)
                    }

                    override fun acceptChildren(v: Visitor) {
                        // no children
                    }
                }

                data class Token(
                    override val locus: Locus,
                    val token: BinaryOp.Token
                ) : Oper() {
                    override fun accept(v: Visitor) {
                        v.visitTokenBinaryOpOper(this)
                    }

                    override fun acceptChildren(v: Visitor) {
                        // no children
                    }
                }
            }

            enum class Token(val str: String) {
                MUL("*"), DIV("/"), MOD("%"), ADD("+"), SUB("-"),
                IN("in"), NOT_IN("!in"),
                GT(">"), GTE(">="), LT("<"), LTE("<="),
                EQ("=="), NEQ("!="),
                ASSN("="), MUL_ASSN("*="), DIV_ASSN("/="), MOD_ASSN("%="), ADD_ASSN("+="), SUB_ASSN("-="),
                OR("||"), AND("&&"), ELVIS("?:"), RANGE(".."),
                DOT("."), DOT_SAFE("?.")
            }

            override val locus: Locus
                get() = lhs.locus

            override fun accept(v: Visitor) {
                v.visitBinaryOp(this)
            }

            override fun acceptChildren(v: Visitor) {
                lhs.accept(v)
                oper.accept(v)
                rhs.accept(v)
            }
        }

        data class UnaryOp(
            override val locus: Locus,
            val expr: Expr,
            val oper: Oper,
            val isPrefix: Boolean
        ) : Expr() {
            data class Oper(
                override val locus: Locus,
                val token: Token
            ) : Node() {
                override fun accept(v: Visitor) {
                    v.visitUnaryOpOper(this)
                }

                override fun acceptChildren(v: Visitor) {
                    // no children
                }
            }

            enum class Token(val str: String) {
                NEG("-"), POS("+"), INC("++"), DEC("--"), NOT("!"), NULL_DEREF("!!")
            }

            override fun accept(v: Visitor) {
                v.visitUnaryOp(this)
            }

            override fun acceptChildren(v: Visitor) {
                expr.accept(v)
                oper.accept(v)
            }
        }

        data class TypeOp(
            val lhs: Expr,
            val oper: Oper,
            val rhs: Type
        ) : Expr() {
            data class Oper(
                override val locus: Locus,
                val token: Token
            ) : Node() {
                override fun accept(v: Visitor) {
                    v.visitTypeOpOper(this)
                }

                override fun acceptChildren(v: Visitor) {
                    // no children
                }
            }

            enum class Token(val str: String) {
                AS("as"), AS_SAFE("as?"), COL(":"), IS("is"), NOT_IS("!is")
            }

            override val locus: Locus
                get() = lhs.locus

            override fun accept(v: Visitor) {
                v.visitTypeOp(this)
            }

            override fun acceptChildren(v: Visitor) {
                lhs.accept(v)
                oper.accept(v)
                rhs.accept(v)
            }
        }

        sealed class DoubleColonRef : Expr() {
            abstract val recv: Recv?

            data class Callable(
                override val locus: Locus,
                override val recv: Recv?,
                val name: String
            ) : DoubleColonRef() {
                override fun accept(v: Visitor) {
                    v.visitDoubleColonCallable(this)
                }

                override fun acceptChildren(v: Visitor) {
                    recv?.accept(v)
                }
            }

            data class Class(
                override val locus: Locus,
                override val recv: Recv?
            ) : DoubleColonRef() {
                override fun accept(v: Visitor) {
                    v.visitDoubleColonClass(this)
                }

                override fun acceptChildren(v: Visitor) {
                    recv?.accept(v)
                }
            }

            sealed class Recv : Node() {
                data class Expr(val expr: Node.Expr) : Recv() {
                    override val locus: Locus
                        get() = expr.locus

                    override fun accept(v: Visitor) {
                        v.visitDoubleColonExprRecv(this)
                    }

                    override fun acceptChildren(v: Visitor) {
                        expr.accept(v)
                    }
                }

                data class Type(
                    val type: TypeRef.Simple,
                    val questionMark: Boolean
                ) : Recv() {
                    override val locus: Locus
                        get() = type.locus

                    override fun accept(v: Visitor) {
                        v.visitDoubleColonTypeRecv()
                    }

                    override fun acceptChildren(v: Visitor) {
                        type.accept(v)
                    }
                }
            }
        }

        // TODO: Replace with sealed class
        data class Const(
            override val locus: Locus,
            val value: String,
            val form: Form
        ) : Expr() {
            enum class Form { STRING, BOOLEAN, CHAR, INT, FLOAT, NULL }

            override fun accept(v: Visitor) {
                v.visitConst(this)
            }

            override fun acceptChildren(v: Visitor) {
                // no children
            }
        }

        data class Lambda(
            override val locus: Locus,
            val params: List<Param>,
            val block: Block?
        ) : Expr() {
            data class Param(
                override val locus: Locus,
                // Multiple means destructure, null means underscore
                val vars: List<Decl.Property.Var?>,
                val destructType: Type?
            ) : Expr() {
                override fun accept(v: Visitor) {
                    v.visitLambdaParam(this)
                }

                override fun acceptChildren(v: Visitor) {
                    for (vr in vars) {
                        vr?.accept(v)
                    }
                    destructType?.accept(v)
                }
            }

            override fun accept(v: Visitor) {
                v.visitLambda(this)
            }

            override fun acceptChildren(v: Visitor) {
                for (param in params) {
                    param.accept(v)
                }
                block?.accept(v)
            }
        }

        data class This(
            override val locus: Locus,
            val label: String?
        ) : Expr() {
            override fun accept(v: Visitor) {
                v.visitThis(this)
            }

            override fun acceptChildren(v: Visitor) {
                // no children
            }
        }

        data class Super(
            override val locus: Locus,
            val typeArg: Type?,
            val label: String?
        ) : Expr() {
            override fun accept(v: Visitor) {
                v.visitSuper(this)
            }

            override fun acceptChildren(v: Visitor) {
                typeArg?.accept(v)
            }
        }

        data class When(
            override val locus: Locus,
            val subject: Expr?,
            val entries: List<Entry>
        ) : Expr() {
            data class Entry(
                override val locus: Locus,
                val conds: List<Cond>,
                val body: Expr
            ) : Node() {
                override fun accept(v: Visitor) {
                    v.visitWhenEntry(this)
                }

                override fun acceptChildren(v: Visitor) {
                    for (cond in conds) {
                        cond.accept(v)
                    }
                    body.accept(v)
                }
            }

            sealed class Cond : Node() {
                data class Expr(val expr: Node.Expr) : Cond() {
                    override val locus: Locus
                        get() = expr.locus

                    override fun accept(v: Visitor) {
                        v.visitWhenExprCond(this)
                    }

                    override fun acceptChildren(v: Visitor) {
                        expr.accept(v)
                    }
                }

                data class In(
                    override val locus: Locus,
                    val expr: Node.Expr,
                    val not: Boolean
                ) : Cond() {
                    override fun accept(v: Visitor) {
                        v.visitWhenInCond(this)
                    }

                    override fun acceptChildren(v: Visitor) {
                        expr.accept(v)
                    }
                }

                data class Is(
                    override val locus: Locus,
                    val type: Type,
                    val not: Boolean
                ) : Cond() {
                    override fun accept(v: Visitor) {
                        v.visitWhenIsCond(this)
                    }

                    override fun acceptChildren(v: Visitor) {
                        type.accept(v)
                    }
                }
            }

            override fun accept(v: Visitor) {
                v.visitWhen(this)
            }

            override fun acceptChildren(v: Visitor) {
                subject?.accept(v)
                for (entry in entries) {
                    entry.accept(v)
                }
            }
        }

        data class Object(
            override val locus: Locus,
            val parents: List<Decl.Class.Parent>,
            val members: List<Decl>
        ) : Expr() {
            override fun accept(v: Visitor) {
                v.visitObjectExpr(this)
            }

            override fun acceptChildren(v: Visitor) {
                for (parent in parents) {
                    parent.accept(v)
                }
                for (member in members) {
                    member.accept(v)
                }
            }
        }

        data class Throw(
            override val locus: Locus,
            val expr: Expr
        ) : Expr() {
            override fun accept(v: Visitor) {

            }

            override fun acceptChildren(v: Visitor) {
                TODO("Not yet implemented")
            }
        }

        data class Return(
            val label: String?,
            val expr: Expr?
        ) : Expr()

        data class Continue(
            val label: String?
        ) : Expr()

        data class Break(
            val label: String?
        ) : Expr()

        data class CollLit(
            val exprs: List<Expr>
        ) : Expr()

        data class Name(
            val name: String
        ) : Expr()

        data class Labeled(
            val label: String,
            val expr: Expr
        ) : Expr()

        data class Annotated(
            override val anns: List<Modifier.AnnotationSet>,
            val expr: Expr
        ) : Expr(), WithAnnotations

        data class Call(
            val expr: Expr,
            val typeArgs: List<Type?>,
            val args: List<ValueArg>,
            val lambda: TrailLambda?
        ) : Expr() {
            data class TrailLambda(
                override val anns: List<Modifier.AnnotationSet>,
                val label: String?,
                val func: Brace
            ) : Node(), WithAnnotations
        }

        data class ArrayAccess(
            val expr: Expr,
            val indices: List<Expr>
        ) : Expr()

        data class AnonFunc(
            val func: Decl.Func
        ) : Expr()

        // This is only present for when expressions and labeled expressions
        data class Property(
            val decl: Decl.Property
        ) : Expr()
    }

    data class Block(val stmts: List<Stmt>) : Node()
    sealed class Stmt : Node() {
        data class Decl(val decl: Node.Decl) : Stmt()
        data class Expr(val expr: Node.Expr) : Stmt()
    }

    sealed class Modifier : Node() {
        data class AnnotationSet(
            val target: Target?,
            val anns: List<Annotation>
        ) : Modifier() {
            enum class Target {
                FIELD, FILE, PROPERTY, GET, SET, RECEIVER, PARAM, SETPARAM, DELEGATE
            }

            data class Annotation(
                val names: List<String>,
                val typeArgs: List<Type>,
                val args: List<ValueArg>
            ) : Node()
        }

        data class Lit(val keyword: Keyword) : Modifier()
        enum class Keyword {
            ABSTRACT, FINAL, OPEN, ANNOTATION, SEALED, DATA, OVERRIDE, LATEINIT, INNER,
            PRIVATE, PROTECTED, PUBLIC, INTERNAL,
            IN, OUT, NOINLINE, CROSSINLINE, VARARG, REIFIED,
            TAILREC, OPERATOR, INFIX, INLINE, EXTERNAL, SUSPEND, CONST,
            ACTUAL, EXPECT
        }
    }

    sealed class Extra : Node() {
        data class BlankLines(
            val count: Int
        ) : Extra()

        data class Comment(
            val text: String,
            val startsLine: Boolean,
            val endsLine: Boolean
        ) : Extra()
    }

    abstract fun accept(v: Visitor)

    abstract fun acceptChildren(v: Visitor)
}