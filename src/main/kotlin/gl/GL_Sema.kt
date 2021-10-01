//package kometa.gl
//
//import kometa.kotlin.AST
//import kometa.util.cast
//import kometa.util.pop
//import kometa.util.reduce2
//
//object GL_Sema {
//    private val builtinFunctions = setOf("dot", "vec4")
//    private val builtinVariables = setOf("gl_Position")
//
//    private val declaredFunctions = arrayListOf<GL_AST.FunctionDeclaration>()
//    private val declaredVariables = arrayListOf<GL_AST.VariableDeclaration>()
//
//    fun addDeclaration(declaration: AST.AstNode) {
//        require(declaration is GL_AST.FunctionDeclaration || declaration is GL_AST.VariableDeclaration)
//
//        when (declaration) {
//            is GL_AST.FunctionDeclaration -> declaredFunctions += declaration
//            is GL_AST.VariableDeclaration -> declaredVariables += declaration
//        }
//    }
//
//    private class Scope {
//        val variables = arrayListOf<String>()
//    }
//
//    private fun analyzeAnnotations(annotations: List<AST.Annotation>): String {
//        val res = StringBuffer()
//        for (ann in annotations) {
//            if (ann !is AST.SingleAnnotation) error("Multi-annotations are not supported yet")
//            if (ann.target != null) error("GL annotation cannot have target")
//            when (val annotation = ann.unescapedAnnotation) {
//                is AST.ConstructorInvocation -> error("Annotation with parameters are not supported yet")
//                is AST.UserType -> {
//                    val userType =
//                        annotation.simpleTypes.singleOrNull() ?: error("Only simple annotations are supported")
//                    when (val name = userType.name) {
//                        "uniform", "varying", "attribute" -> {
//                            res.append(name)
//                            res.append(" ")
//                        }
//                        else -> error("Only @uniform, @varying and @attribute annotations are supported")
//                    }
//                }
//            }
//        }
//        return res.toString()
//    }
//
//    private fun dumpVariableDeclaration(declaration: GL_AST.VariableDeclaration): String {
//        val res = StringBuffer()
//
//        res.append(analyzeAnnotations(declaration.annotations))
//        declaration.type?.let { res.append(it) } ?: res.append("float")
//        res.append(" ")
//        res.append(declaration.name)
//
//        return res.toString()
//    }
//
//    // TODO: Check for vertex/fragment variables/functions
//    private fun analyzeBlockAndCreateShaderString(block: GL_AST.Block): String {
//
//        val usedGlobals = mutableSetOf<GL_AST.VariableDeclaration>()
//
//        val processed = mutableSetOf<GL_AST.AstNode>()
//
//        fun findDeclaredVariable(name: String, scopes: List<Scope>) {
//            var found = false
//            for (scope in scopes.reversed()) {
//                if (name in scope.variables) {
//                    found = true
//                    break
//                }
//            }
//            if (!found) {
//                declaredVariables.find { it.name == name }?.let {
//                    usedGlobals += it
//                    return
//                }
//                if (name !in builtinVariables) {
//                    error("$name is not declared")
//                }
//            }
//        }
//
//        fun analyzeGlobalDeclaration(declaration: GL_AST.AstNode): Pair<String, List<GL_AST.FunctionDeclaration>> {
//            if (declaration in processed) return "" to emptyList()
//            processed += declaration
//
//            val usedFunctions = arrayListOf<GL_AST.FunctionDeclaration>()
//
//            val body = StringBuffer()
//            val scopes = arrayListOf<Scope>()
//
//            fun analyze(statement: GL_AST.Statement) {
//                when (statement) {
//                    is GL_AST.Assignment -> {
//                        findDeclaredVariable(statement.name, scopes)
//                        body.append(statement.name)
//                        body.append(" = ")
//                        analyze(statement.expression)
//                    }
//                    is GL_AST.Additive -> {
//                        body.append("(")
//                        analyze(statement.lhs)
//                        when(statement.op) {
//                            AST.ADD -> body.append(" + ")
//                            AST.SUB -> body.append(" - ")
//                        }
//                        analyze(statement.rhs)
//                        body.append(")")
//                    }
//                    is GL_AST.Block -> {
//                        body.appendLine("{")
//                        scopes.add(Scope())
//                        for (stmt in statement.statements) {
//                            analyze(stmt)
//                            body.appendLine(";")
//                        }
//                        scopes.pop()
//                        body.appendLine("}")
//                    }
//                    is GL_AST.CallExpression -> {
//                        val usedFunction = declaredFunctions.find { it.name == statement.name }
//                        if (usedFunction == null) {
//                            if (statement.name !in builtinFunctions) {
//                                error("${statement.name} is not declared")
//                            }
//                        } else {
//                            usedFunctions += usedFunction
//                        }
//                        body.append(statement.name)
//                        body.append("(")
//                        if (statement.arguments.isNotEmpty()) {
//                            for (arg in statement.arguments.dropLast(1)) {
//                                analyze(arg)
//                                body.append(", ")
//                            }
//                            analyze(statement.arguments.last())
//                        }
//                        body.append(")")
//                    }
//                    is GL_AST.DotAccessExpression -> {
//                        findDeclaredVariable(statement.name, scopes)
//                        body.append(statement.name)
//                        for (field in statement.fields) {
//                            body.append(".")
//                            body.append(field)
//                        }
//                    }
//                    is GL_AST.FloatLiteral -> body.append(statement.s)
//                    is GL_AST.IfExpression -> {
//                        body.append("((")
//                        analyze(statement.condition)
//                        body.append(") ? ")
//                        analyze(statement.thenExpression)
//                        body.append(" : ")
//                        analyze(statement.elseExpression)
//                        body.append(")")
//                    }
//                    is GL_AST.Multiplicative -> {
//                        body.append("(")
//                        analyze(statement.lhs)
//                        when(statement.op) {
//                            AST.DIV -> body.append(" / ")
//                            AST.MOD -> body.append(" % ")
//                            AST.MULT -> body.append(" * ")
//                        }
//                        analyze(statement.rhs)
//                        body.append(")")
//                    }
//                    is GL_AST.NameExpression -> {
//                        findDeclaredVariable(statement.name, scopes)
//                        body.append(statement.name)
//                    }
//                    is GL_AST.ReturnStatement -> {
//                        body.append("return ")
//                        analyze(statement.expression)
//                    }
//                    is GL_AST.VariableDeclaration -> {
//                        scopes.last().variables.add(statement.name)
//                        body.append(dumpVariableDeclaration(statement))
//                        statement.expression?.let { initial ->
//                            body.append(" = ")
//                            analyze(initial)
//                        }
//                    }
//                    is GL_AST.Equality -> {
//                        body.append("(")
//                        analyze(statement.lhs)
//                        when(statement.op) {
//                            AST.EQEQ, AST.EQEQEQ -> body.append(" == ")
//                            AST.EXCL_EQ, AST.EXCL_EQEQ -> body.append(" != ")
//                        }
//                        analyze(statement.rhs)
//                        body.append(")")
//                    }
//                    is GL_AST.Comparison -> {
//                        body.append("(")
//                        analyze(statement.lhs)
//                        when(statement.op) {
//                            AST.GE -> body.append(" >= ")
//                            AST.GREATER -> body.append(" > ")
//                            AST.LE -> body.append(" <= ")
//                            AST.LESS -> body.append(" < ")
//                        }
//                        analyze(statement.rhs)
//                        body.append(")")
//                    }
//                }
//            }
//
//            when (declaration) {
//                is GL_AST.FunctionDeclaration -> {
//                    body.append(analyzeAnnotations(declaration.annotations))
//                    body.append(declaration.type)
//                    body.append(" ")
//                    body.append(declaration.name)
//                    body.append("(")
//                    scopes.add(Scope())
//                    if (declaration.parameters.isNotEmpty()) {
//                        for (param in declaration.parameters.dropLast(1)) {
//                            body.append(param.type)
//                            body.append(" ")
//                            body.append(param.name)
//                            body.append(", ")
//                        }
//                        body.append(declaration.parameters.last().type)
//                        body.append(" ")
//                        body.append(declaration.parameters.last().name)
//                    }
//                    for (param in declaration.parameters) {
//                        scopes.last().variables.add(param.name)
//                    }
//                    body.appendLine(") {")
//                    for (stmt in declaration.body.statements) {
//                        analyze(stmt)
//                        body.appendLine(";")
//                    }
//                    scopes.pop()
//                    body.appendLine("}")
//                }
//                is GL_AST.Block -> {
//                    body.appendLine("void main() {")
//                    scopes.add(Scope())
//                    for (stmt in block.statements) {
//                        analyze(stmt)
//                        body.appendLine(";")
//                    }
//                    scopes.pop()
//                    body.appendLine("}")
//                }
//                else -> error("unreachable $declaration")
//            }
//
//            return body.toString() to usedFunctions
//        }
//
//        val queue = arrayListOf<GL_AST.AstNode>(block)
//
//        val (main, used) = analyzeGlobalDeclaration(block)
//        queue.addAll(used)
//
//        val functions = StringBuffer()
//        while (queue.isNotEmpty()) {
//            val (fb, used) = analyzeGlobalDeclaration(queue.pop()!!)
//            functions.append(fb)
//            queue.addAll(used)
//        }
//
//        val res = StringBuffer()
//
//        for (varDecl in usedGlobals) {
//            res.append(dumpVariableDeclaration(varDecl))
//            res.appendLine(";")
//        }
//        res.append(functions)
//        res.append(main)
//        return "\"\"\"$res\"\"\""
//    }
//
//    fun checkShaderVertexAndCreateStringLiteral(block: AST.AstNode): AST.AstNode {
//        require(block is GL_AST.Block)
//
//        return AST.StringLiteral(analyzeBlockAndCreateShaderString(block))
//    }
//
//    fun checkShaderFragmentAndCreateStringLiteral(block: AST.AstNode): AST.AstNode {
//        require(block is GL_AST.Block)
//
//        return AST.StringLiteral(analyzeBlockAndCreateShaderString(block))
//    }
//}
//
//object GL_AST {
//    sealed class AstNode : AST.AstNode()
//
//    class FunctionDeclaration(
//        val annotations: List<AST.Annotation>,
//        val name: String, val type: String, val parameters: List<ValueParameter>, val body: Block
//    ) : AstNode() {
//        constructor(
//            annotations: List<AST.AstNode>, name: String, type: String, parameters: List<AST.AstNode>, body: AST.AstNode
//        ) : this(annotations.cast(), name, type, parameters.cast(), body.cast())
//    }
//
//    class ValueParameter(val name: String, val type: String) : AstNode()
//
//    sealed class Statement : AstNode()
//
//    class VariableDeclaration(
//        val annotations: List<AST.Annotation>,
//        val vov: AST.ValOrVar,
//        val name: String,
//        val type: String?,
//        val expression: Expression?
//    ) : Statement() {
//        constructor(
//            annotations: List<AST.AstNode>, vov: AST.AstNode, name: String, type: String?, expression: AST.AstNode?
//        ) : this(
//            annotations.cast<List<AST.Annotation>>(),
//            vov.cast<AST.ValOrVar>(),
//            name,
//            type,
//            expression.cast<Expression?>()
//        )
//    }
//
//    class Assignment(val name: String, val expression: Expression) : Statement() {
//        constructor(name: String, expression: AST.AstNode) : this(name, expression.cast<Expression>())
//    }
//
//    class ReturnStatement(val expression: Expression) : Statement() {
//        constructor(expression: AST.AstNode) : this(expression.cast())
//    }
//
//    sealed class Expression : Statement()
//
//    class Equality(val lhs: Expression, val op: AST.EqualityOperator, val rhs: Expression) : Expression() {
//        companion object {
//            operator fun invoke(subs: List<AST.AstNode>): Expression =
//                subs.reduce2<AST.AstNode, Expression> { lhs, op, rhs ->
//                    Equality(lhs.cast(), op.cast(), rhs.cast())
//                }
//        }
//    }
//
//    class Comparison(val lhs: Expression, val op: AST.ComparisonOperator, val rhs: Expression) : Expression() {
//        companion object {
//            operator fun invoke(subs: List<AST.AstNode>): Expression =
//                subs.reduce2<AST.AstNode, Expression> { lhs, op, rhs ->
//                    Comparison(lhs.cast(), op.cast(), rhs.cast())
//                }
//        }
//    }
//
//    class Additive(val lhs: Expression, val op: AST.AdditiveOperator, val rhs: Expression) : Expression() {
//        companion object {
//            operator fun invoke(subs: List<AST.AstNode>): Expression =
//                subs.reduce2<AST.AstNode, Expression> { lhs, op, rhs ->
//                    Additive(lhs.cast(), op.cast(), rhs.cast())
//                }
//        }
//    }
//
//    class Multiplicative(val lhs: Expression, val op: AST.MultiplicativeOperator, val rhs: Expression) : Expression() {
//        companion object {
//            operator fun invoke(subs: List<AST.AstNode>): Expression =
//                subs.reduce2<AST.AstNode, Expression> { lhs, op, rhs ->
//                    Multiplicative(lhs.cast(), op.cast(), rhs.cast())
//                }
//        }
//    }
//
//    class Block(val statements: List<Statement>) : Expression() {
//        companion object {
//            operator fun invoke(statements: List<AST.AstNode>): Block = Block(statements.cast())
//        }
//    }
//
//    class NameExpression(val name: String) : Expression() {
//        override fun toString(): String = name
//    }
//
//    class CallExpression(val name: String, val arguments: List<Expression>) : Expression() {
//        companion object {
//            operator fun invoke(name: String, arguments: List<AST.AstNode>): CallExpression =
//                CallExpression(name, arguments.cast())
//        }
//    }
//
//    class DotAccessExpression(val name: String, val fields: List<String>) : Expression() {
//        companion object {
//            operator fun invoke(name: String, fields: List<AST.AstNode>): DotAccessExpression =
//                DotAccessExpression(name, fields.map { it.cast<NameExpression>().toString() })
//        }
//    }
//
//    class IfExpression(
//        val condition: Expression, val thenExpression: Expression, val elseExpression: Expression
//    ) : Expression() {
//        constructor(
//            condition: AST.AstNode, thenExpression: AST.AstNode, elseExpression: AST.AstNode
//        ) : this(condition.cast(), thenExpression.cast(), elseExpression.cast())
//    }
//
//    class FloatLiteral(val s: String) : Expression()
//}