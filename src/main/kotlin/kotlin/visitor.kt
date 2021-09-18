package kometa.kotlin

import kometa.kotlin.ast.*

abstract class Visitor {
    abstract fun visitNode(element: Element)
    open fun visitFile(file: Element.File) = visitNode(file)
    open fun visitScript(script: Element.Script) = visitNode(script)
    open fun visitPackage(pkg: Element.Package) = visitNode(pkg)
    open fun visitImport(imp: Element.Import) = visitNode(imp)

    open fun visitDecl(decl: Element.Decl) = visitNode(decl)

    open fun visitClass(clazz: Element.Decl.Class) = visitDecl(clazz)
    open fun visitClassParent(classParent: Element.Decl.Class.Parent) =
        visitNode(classParent)
    open fun visitClassParentConstructorCall(
        call: Element.Decl.Class.Parent.ConstructorCall
    ) = visitClassParent(call)
    open fun visitClassParentInterfaceType(
        type: Element.Decl.Class.Parent.InterfaceType
    ) = visitClassParent(type)
    open fun visitPrimaryConstructor(
        constructor: Element.Decl.Class.PrimaryConstructor
    ) = visitNode(constructor)
    open fun visitInitDecl(init: Element.Decl.Init) = visitDecl(init)

    open fun visitFunc(func: Element.Decl.Function) = visitDecl(func)
    open fun visitFunctionParam(param: Element.Decl.Function.Param) = visitNode(param)
    open fun visitFunctionBody(body: Element.Decl.Function.Body) = visitNode(body)
    open fun visitFunctionBlockBody(body: Element.Decl.Function.Body.Block) =
        visitFunctionBody(body)
    open fun visitFunctionExprBody(body: Element.Decl.Function.Body.Expr) =
        visitFunctionBody(body)

    open fun visitProperty(property: Element.Decl.Property) =
        visitDecl(property)
    open fun visitPropertyVar(propVar: Element.Decl.Property.Var) =
        visitNode(propVar)
    open fun visitPropertyGetter(getter: Element.Decl.Property.Getter) =
        visitNode(getter)
    open fun visitPropertySetter(setter: Element.Decl.Property.Setter) =
        visitNode(setter)

    open fun visitTypeAlias(typeAlias: Element.Decl.TypeAlias) =
        visitDecl(typeAlias)

    open fun visitConstructor(constructor: Element.Decl.Constructor) =
        visitDecl(constructor)
    open fun visitConstructorDelegationCall(
        call: Element.Decl.Constructor.DelegationCall
    ) = visitNode(call)

    open fun visitEnumEntry(entry: Element.Decl.EnumEntry) = visitDecl(entry)

    open fun visitTypeParam(param: Element.TypeParam) = visitNode(param)
    open fun visitTypeConstraint(constraint: Element.TypeConstraint) =
        visitNode(constraint)

    open fun visitTypeRef(ref: Element.TypeRef) = visitNode(ref)
    open fun visitParenType(type: Element.TypeRef.Paren) = visitTypeRef(type)
    open fun visitFuncType(type: Element.TypeRef.Func) = visitTypeRef(type)
    open fun visitFuncTypeParam(param: Element.TypeRef.Func.Param) =
        visitNode(param)
    open fun visitSimpleType(type: Element.TypeRef.Simple) = visitTypeRef(type)
    open fun visitSimpleTypePiece(type: Element.TypeRef.Simple.Piece) =
        visitNode(type)
    open fun visitNullableType(type: Element.TypeRef.Nullable) =
        visitTypeRef(type)
    open fun visitDynamicType(type: Element.TypeRef.Dynamic) = visitTypeRef(type)
    open fun visitType(type: Element.Type) = visitNode(type)

    open fun visitValueArg(arg: Element.ValueArg) = visitNode(arg)

    open fun visitExpr(expr: Element.Expr) = visitNode(expr)
    open fun visitIfExpr(expr: Element.Expr.If) = visitExpr(expr)
    open fun visitTryExpr(expr: Element.Expr.Try) = visitExpr(expr)
    open fun visitCatch(catch: Element.Expr.Try.Catch) = visitNode(catch)
    open fun visitForStmt(stmt: Element.Expr.For) = visitExpr(stmt)
    open fun visitWhileStmt(stmt: Element.Expr.While) = visitExpr(stmt)
    open fun visitDoWhileStmt(stmt: Element.Expr.DoWhile) = visitExpr(stmt)
    open fun visitBinaryOp(op: Element.Expr.BinaryOp) = visitExpr(op)
    open fun visitBinaryOpOper(oper: Element.Expr.BinaryOp.Oper) = visitNode(oper)
    open fun visitInfixBinaryOpOper(oper: Element.Expr.BinaryOp.Oper.Infix) =
        visitBinaryOpOper(oper)
    open fun visitTokenBinaryOpOper(token: Element.Expr.BinaryOp.Oper.Token) =
        visitBinaryOpOper(token)
    open fun visitUnaryOp(op: Element.Expr.UnaryOp) = visitExpr(op)
    open fun visitUnaryOpOper(oper: Element.Expr.UnaryOp.Oper) = visitNode(oper)
    open fun visitTypeOp(op: Element.Expr.TypeOp) = visitExpr(op)
    open fun visitTypeOpOper(oper: Element.Expr.TypeOp.Oper) = visitNode(oper)
    open fun visitDoubleColonRef(ref: Element.Expr.DoubleColonRef) =
        visitExpr(ref)
    open fun visitDoubleColonCallable(
        callable: Element.Expr.DoubleColonRef.Callable
    ) = visitDoubleColonRef(callable)
    open fun visitDoubleColonClass(ref: Element.Expr.DoubleColonRef.Class) =
        visitDoubleColonRef(ref)
    open fun visitDoubleColonRecv(recv: Element.Expr.DoubleColonRef.Recv) =
        visitNode(recv)
    open fun visitDoubleColonExprRecv(recv: Element.Expr.DoubleColonRef.Recv) =
        visitDoubleColonRecv(recv)
    open fun visitDoubleColonTypeRecv(recv: Element.Expr.DoubleColonRef.Recv) =
        visitDoubleColonRecv(recv)
    open fun visitConst(const: Element.Expr.Const) = visitExpr(const)
    open fun visitLambda(lambda: Element.Expr.Lambda) = visitExpr(lambda)
    open fun visitLambdaParam(param: Element.Expr.Lambda.Param) = visitExpr(param)
    open fun visitThis(expr: Element.Expr.This) = visitExpr(expr)
    open fun visitSuper(expr: Element.Expr.Super) = visitExpr(expr)
    open fun visitWhen(expr: Element.Expr.When) = visitExpr(expr)
    open fun visitWhenEntry(entry: Element.Expr.When.Entry) = visitNode(entry)
    open fun visitWhenCond(cond: Element.Expr.When.Cond) = visitNode(cond)
    open fun visitWhenExprCond(cond: Element.Expr.When.Cond.Expr) =
        visitWhenCond(cond)
    open fun visitWhenInCond(cond: Element.Expr.When.Cond.In) = visitWhenCond(cond)
    open fun visitWhenIsCond(cond: Element.Expr.When.Cond.Is) = visitWhenCond(cond)
    open fun visitObjectExpr(obj: Element.Expr.Object) = visitExpr(obj)
    open fun visitThrowExpr(expr: Element.Expr.Throw) = visitExpr(expr)
    open fun visitReturnExpr(expr: Element.Expr.Return) = visitExpr(expr)
    open fun visitContinueExpr(expr: Element.Expr.Continue) = visitExpr(expr)
    open fun visitBreakExpr(expr: Element.Expr.Break) = visitExpr(expr)
    open fun visitCollectionLiteralExpr(lit: Element.Expr.CollectionLiteral) =
        visitExpr(lit)
    open fun visitNameExpr(name: Element.Expr.Name) = visitExpr(name)
    open fun visitLabelledExpr(expr: Element.Expr.Labelled) = visitExpr(expr)
    open fun visitAnnotatedExpr(expr: Element.Expr.Annotated) = visitExpr(expr)
    open fun visitCallExpr(call: Element.Expr.Call) = visitExpr(call)
    open fun visitTrailingLambda(lambda: Element.Expr.Call.TrailLambda) =
        visitNode(lambda)
    open fun visitArrayAccess(expr: Element.Expr.ArrayAccess) = visitExpr(expr)
    open fun visitAnonymousFunction(func: Element.Expr.AnonymousFunction) =
        visitExpr(func)
    open fun visitPropertyExpression(expr: Element.Expr.Property) = visitExpr(expr)

    open fun visitBlock(block: Element.Block) = visitNode(block)
    open fun visitModifier(mod: Element.Modifier) = visitNode(mod)
    open fun visitAnnotationSet(anns: Element.Modifier.AnnotationSet) =
        visitModifier(anns)
    open fun visitAnnotation(ann: Element.Modifier.AnnotationSet.Annotation) =
        visitNode(ann)
    open fun visitIdentifierLikeModifier(mod: Element.Modifier.IdentifierLike) =
        visitModifier(mod)
}