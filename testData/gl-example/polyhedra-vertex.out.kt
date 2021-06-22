import gl.*

// Moved to another color

// Moved to another color

// Moved to another color

// Moved to another color

// Moved to another color

// Moved to another color

// Moved to another color

// Moved to another color

// Moved to another color

// Moved to another color

// Moved to another color

// Moved to another color

// Moved to another color

// Moved to another color

// Moved to another color

// Moved to another color

// Moved to another color

// Moved to another color

fun main() {
    val sh = """uniform mat4 uProjectionMatrix;
varying float vColorMul;
uniform float uCullMode;
uniform mat3 uNormalMatrix;
attribute vec3 aVertexNormal;
uniform float uTargetFraction;
attribute vec3 aPrevVertexNormal;
uniform float uPrevFraction;
uniform vec3 uCameraPosition;
uniform mat4 uModelMatrix;
uniform float uExpand;
attribute vec3 aVertexPosition;
attribute vec3 aPrevVertexPosition;
vec3 fNormal() {
return (uNormalMatrix * fInterpolatedNormal());
}
vec3 fInterpolatedNormal() {
return ((aVertexNormal * uTargetFraction) + (aPrevVertexNormal * uPrevFraction));
}
float fFaceDirection(vec4 position, vec3 normal) {
return dot((position.xyz - uCameraPosition), normal);
}
vec4 fPosition() {
return (uModelMatrix * vec4((fInterpolatedPosition() + (fInterpolatedNormal() * uExpand)), 1.0));
}
vec3 fInterpolatedPosition() {
return ((aVertexPosition * uTargetFraction) + (aPrevVertexPosition * uPrevFraction));
}
void main() {
float position = fPosition();
gl_Position = (uProjectionMatrix * position);
vColorMul = (((uCullMode == 0.0)) ? 1.0 : ((((fFaceDirection(position, fNormal()) * uCullMode) >= 0.0)) ? 1.0 : 0.0));
}
"""
}