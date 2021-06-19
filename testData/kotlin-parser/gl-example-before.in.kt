package polyhedra.js.poly

import polyhedra.js.glsl.*
import org.khronos.webgl.WebGLRenderingContext as GL

class EdgeProgram(gl: GL) : PolyProgram(gl) {
    override val vertexShader = shader(ShaderType.Vertex) {
        val position = fPosition()
        gl_Position = uProjectionMatrix * position
        vColorMul =
            if(uCullMode == 0.0) 1.0
            else if (fFaceDirection(position, fNormal()) * uCullMode >= 0.0) 1.0
            else 0.0
    }

    override val fragmentShader = shader(ShaderType.Fragment) {
        gl_FragColor = uVertexColor * vColorMul
    }
}