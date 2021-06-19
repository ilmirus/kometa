package gl

typealias Shader = String

fun shaderVertex(block: GL_VertexContext.() -> Unit): Shader = error("Implemented as intrinsic")
fun shaderFragment(block: GL_FragmentContext.() -> Unit): Shader = error("Implemented as intrinsic")