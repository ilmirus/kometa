package kometa.kotlin

import kometa.kotlin.ast.*

abstract class Visitor {
    abstract fun visitNode(node: Node)
    open fun visitFile(file: Node.File) = visitNode(file)
    open fun visitScript(script: Node.Script) = visitNode(script)
    open fun visitPackage(pkg: Node.Package) = visitNode(pkg)
    open fun visitImport(imp: Node.Import) = visitNode(imp)

    open fun visitDecl(decl: Node.Decl) = visitNode(decl)

    open fun visitClass(clazz: Node.Decl.Class) = visitDecl(clazz)
    open fun visitClassParent(classParent: Node.Decl.Class.Parent) =
        visitNode(classParent)
    open fun visitClassParentConstructorCall(
        call: Node.Decl.Class.Parent.ConstructorCall
    ) = visitClassParent(call)
    open fun visitClassParentInterfaceType(
        type: Node.Decl.Class.Parent.InterfaceType
    ) = visitClassParent(type)
    open fun visitPrimaryConstructor(
        constructor: Node.Decl.Class.PrimaryConstructor
    ) = visitNode(constructor)
    open fun visitInitDecl(init: Node.Decl.Init) = visitDecl(init)

    open fun visitFunc(func: Node.Decl.Func) = visitDecl(func)
    open fun visitFuncParam(param: Node.Decl.Func.Param) = visitNode(param)
    open fun visitFuncBody(body: Node.Decl.Func.Body) = visitNode(body)
    open fun visitFuncBlockBody(body: Node.Decl.Func.Body.Block) =
        visitFuncBody(body)
    open fun visitFuncExprBody(body: Node.Decl.Func.Body.Expr) =
        visitFuncBody(body)

    open fun visitProperty(property: Node.Decl.Property) =
        visitDecl(property)
    open fun visitPropertyVar(propVar: Node.Decl.Property.Var) =
        visitNode(propVar)
    open fun visitPropertyGetter(getter: Node.Decl.Property.Getter) =
        visitNode(getter)
    open fun visitPropertySetter(setter: Node.Decl.Property.Setter) =
        visitNode(setter)

    open fun visitTypeAlias(typeAlias: Node.Decl.TypeAlias) =
        visitDecl(typeAlias)

    open fun visitConstructor(constructor: Node.Decl.Constructor) =
        visitDecl(constructor)
    open fun visitConstructorDelegationCall(
        call: Node.Decl.Constructor.DelegationCall
    ) = visitNode(call)

    open fun visitEnumEntry(entry: Node.Decl.EnumEntry) = visitDecl(entry)

    open fun visitTypeParam(param: Node.TypeParam) = visitNode(param)
    open fun visitTypeConstraint(constraint: Node.TypeConstraint) =
        visitNode(constraint)

    open fun visitTypeRef(ref: Node.TypeRef) = visitNode(ref)
    open fun visitParenType(type: Node.TypeRef.Paren) = visitTypeRef(type)
    open fun visitFuncType(type: Node.TypeRef.Func) = visitTypeRef(type)
    open fun visitFuncTypeParam(param: Node.TypeRef.Func.Param) =
        visitNode(param)
    open fun visitSimpleType(type: Node.TypeRef.Simple) = visitTypeRef(type)
    open fun visitSimpleTypePiece(type: Node.TypeRef.Simple.Piece) =
        visitNode(type)
    open fun visitNullableType(type: Node.TypeRef.Nullable) =
        visitTypeRef(type)
    open fun visitDynamicType(type: Node.TypeRef.Dynamic) = visitTypeRef(type)
    open fun visitType(type: Node.Type) = visitNode(type)

    open fun visitValueArg(arg: Node.ValueArg) = visitNode(arg)

    open fun visitExpr(expr: Node.Expr) = visitNode(expr)
    open fun visitIfExpr(expr: Node.Expr.If) = visitExpr(expr)
    open fun visitTryExpr(expr: Node.Expr.Try) = visitExpr(expr)
    open fun visitCatch(catch: Node.Expr.Try.Catch) = visitNode(catch)
    open fun visitForStmt(stmt: Node.Expr.For) = visitExpr(stmt)
    open fun visitWhileStmt(stmt: Node.Expr.While) = visitExpr(stmt)
    open fun visitDoWhileStmt(stmt: Node.Expr.DoWhile) = visitExpr(stmt)
    open fun visitBinaryOp(op: Node.Expr.BinaryOp) = visitExpr(op)
    open fun visitBinaryOpOper(oper: Node.Expr.BinaryOp.Oper) = visitNode(oper)
    open fun visitInfixBinaryOpOper(oper: Node.Expr.BinaryOp.Oper.Infix) =
        visitBinaryOpOper(oper)
    open fun visitTokenBinaryOpOper(token: Node.Expr.BinaryOp.Oper.Token) =
        visitBinaryOpOper(token)
    open fun visitUnaryOp(op: Node.Expr.UnaryOp) = visitExpr(op)
    open fun visitUnaryOpOper(oper: Node.Expr.UnaryOp.Oper) = visitNode(oper)
    open fun visitTypeOp(op: Node.Expr.TypeOp) = visitExpr(op)
    open fun visitTypeOpOper(oper: Node.Expr.TypeOp.Oper) = visitNode(oper)
    open fun visitDoubleColonRef(ref: Node.Expr.DoubleColonRef) =
        visitExpr(ref)
    open fun visitDoubleColonCallable(
        callable: Node.Expr.DoubleColonRef.Callable
    ) = visitDoubleColonRef(callable)
    open fun visitDoubleColonClass(ref: Node.Expr.DoubleColonRef.Class) =
        visitDoubleColonRef(ref)
    open fun visitDoubleColonRecv(recv: Node.Expr.DoubleColonRef.Recv) =
        visitNode(recv)
    open fun visitDoubleColonExprRecv(recv: Node.Expr.DoubleColonRef.Recv) =
        visitDoubleColonRecv(recv)
    open fun visitDoubleColonTypeRecv(recv: Node.Expr.DoubleColonRef.Recv) =
        visitDoubleColonRecv(recv)
    open fun visitConst(const: Node.Expr.Const) = visitExpr(const)
    open fun visitLambda(lambda: Node.Expr.Lambda) = visitExpr(lambda)
    open fun visitLambdaParam(param: Node.Expr.Lambda.Param) = visitExpr(param)
    open fun visitThis(expr: Node.Expr.This) = visitExpr(expr)
    open fun visitSuper(expr: Node.Expr.Super) = visitExpr(expr)
    open fun visitWhen(expr: Node.Expr.When) = visitExpr(expr)
    open fun visitWhenEntry(entry: Node.Expr.When.Entry) = visitNode(entry)
    open fun visitWhenCond(cond: Node.Expr.When.Cond) = visitNode(cond)
    open fun visitWhenExprCond(cond: Node.Expr.When.Cond.Expr) =
        visitWhenCond(cond)
    open fun visitWhenInCond(cond: Node.Expr.When.Cond.In) = visitWhenCond(cond)
    open fun visitWhenIsCond(cond: Node.Expr.When.Cond.Is) = visitWhenCond(cond)
    open fun visitObjectExpr(obj: Node.Expr.Object) = visitExpr(obj)
    open fun
}