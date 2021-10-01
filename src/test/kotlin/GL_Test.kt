//import kometa.Production
//import kometa.gl_plugin.GL_Plugin
//import kometa.kotlin.AST
//import kometa.kotlin.KotlinGen
//import kometa.kotlin.Token
//import kometa.kotlin_lexer.KotlinLexer
//import kometa.kotlin_parser.KotlinParser
//import kometa.trace
//import org.junit.jupiter.api.Assertions
//import org.junit.jupiter.api.Test
//import java.io.File
//
//class GL_Test {
//    private fun tokenize(filename: String): List<Token> =
//        KotlinLexer().tokenize(File("testData/gl-example/$filename.in.kt").readText().toList())
//
//    private fun parse(filename: String): AST.KotlinFile {
//        val tokens = tokenize(filename)
////        trace = true
////        File("out/dump.txt").delete()
//        return GL_Plugin().parse(tokens) as AST.KotlinFile
//    }
//
//    private fun generateCode(kotlinFile: AST.KotlinFile): String = KotlinGen().generateCode(kotlinFile)
//
//    private fun checkTestData(filename: String) {
//        val expected = File("testData/gl-example/$filename.out.kt").readText().replace("\r\n", "\n").trim()
//        val kotlinFile = parse(filename)
//        val got = generateCode(kotlinFile).trim()
//        Assertions.assertEquals(expected, got)
//    }
//
//    @Test
//    fun testPolyhedraVertex() {
//        checkTestData("polyhedra-vertex")
//    }
//}