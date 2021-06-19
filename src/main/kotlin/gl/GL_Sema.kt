package gl

import kometa.kotlin.AST
import kometa.util.cast

object GL_Sema {


    fun checkShaderVertexAndCreateStringLiteral(block: List<GL_AST.Statement>) {

    }
}

// TODO: This is incomplete
object GL_AST {
    sealed class AstNode : AST.AstNode()

    sealed class Statement : AstNode()

    class VariableDeclaration(
        val annotations: List<AST.Annotation>,
        val vov: AST.ValOrVar,
        val name: String,
        val type: Type?,
        val expression: Expression?
    ) : Statement() {
        constructor(
            annotations: List<AST.AstNode>, vov: AST.AstNode, name: String, type: AST.AstNode?, expression: AST.AstNode?
        ) : this(
            annotations.cast<List<AST.Annotation>>(),
            vov.cast<AST.ValOrVar>(),
            name,
            type.cast<Type?>(),
            expression.cast<Expression?>()
        )
    }

    sealed class Expression

    sealed class Type
}