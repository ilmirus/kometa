import gl.*

context(GL_Context)
@uniform val uProjectionMatrix: mat4

context(GL_Context)
@uniform val uModelMatrix: mat4

context(GL_Context)
@uniform val uTargetFraction: float

context(GL_Context)
@uniform val uPrevFraction: float

context(GL_Context)
@uniform val uExpand: float

context(GL_Context)
@uniform val uCullMode: float

context(GL_Context)
@uniform val uCameraPosition: vec3

context(GL_Context)
@uniform val uNormalMatrix: mat3

context(GL_Context)
@attribute val aVertexPosition: vec3

context(GL_Context)
@attribute val aPrevVertexPosition: vec3

context(GL_Context)
@attribute val aVertexNormal: vec3

context(GL_Context)
@attribute val aPrevVertexNormal: vec3

context(GL_Context)
@varying val vColorMul: float

context(GL_Context)
fun fInterpolatedPosition(): vec3 {
    return aVertexPosition * uTargetFraction + aPrevVertexPosition * uPrevFraction
}

context(GL_Context)
fun fInterpolatedNormal(): vec3 {
    return aVertexNormal * uTargetFraction + aPrevVertexNormal * uPrevFraction
}

context(GL_Context)
fun fPosition(): vec4 {
    return uModelMatrix * vec4(fInterpolatedPosition() + fInterpolatedNormal() * uExpand, 1.0)
}

context(GL_Context)
fun fFaceDirection(position: vec4, normal: vec3): float {
    return dot(position.xyz - uCameraPosition, normal)
}

context(GL_Context)
fun fNormal(): vec3 {
    return uNormalMatrix * fInterpolatedNormal()
}

fun main() {
    val sh = shaderVertex {
        val position = fPosition()
        gl_Position = uProjectionMatrix * position
        vColorMul =
            if (uCullMode == 0.0) 1.0
            else if (fFaceDirection(position, fNormal()) * uCullMode >= 0.0) 1.0
            else 0.0
    }
}
