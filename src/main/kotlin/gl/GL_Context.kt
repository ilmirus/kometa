package gl

interface vec
interface genType
interface float : genType
interface vec2 : genType, vec {
    val x: float
    val y: float
}

interface vec3 : genType, vec {
    val x: float
    val y: float
    val z: float
}

interface vec4 : genType, vec {
    val x: float
    val y: float
    val z: float
    val w: float

    val xyz: vec3
}

interface mat
interface mat2 : mat {
    val x: vec2
    val y: vec2
}

interface mat3 : mat {
    val x: vec3
    val y: vec3
    val z: vec3
}

interface mat4 : mat {
    val x: vec4
    val y: vec4
    val z: vec4
    val w: vec4
}

interface ivec
interface int
interface ivec2 : ivec {
    val x: int
    val y: int
}

interface ivec3 : ivec {
    val x: int
    val y: int
    val z: int
}

interface ivec4 : ivec {
    val x: int
    val y: int
    val z: int
    val w: int
}

interface bvec
interface bool
interface bvec2 : bvec {
    val x: bool
    val y: bool
}

interface bvec3 : bvec {
    val x: bool
    val y: bool
    val z: bool
}

interface bvec4 : bvec {
    val x: bool
    val y: bool
    val z: bool
    val w: bool
}

interface void
interface sampler1D
interface sampler2D
interface sampler3D
interface samplerCube
interface sampler1DShadow
interface sampler2DShadow

annotation class uniform(val precision: Int = highp)
annotation class attribute
annotation class varying
annotation class const

const val lowp = 1
const val mediump = 2
const val highp = 3

// TODO: Must be a contextual receiver type
interface GL_Context {
    //
    // BUILT-IN CONSTANTS (7.4 p44)
    //

    @const
    val gl_MaxVertexUniformComponents: int

    @const
    val gl_MaxFragmentUniformComponents: int

    @const
    val gl_MaxVertexAttribs: int

    @const
    val gl_MaxVaryingFloats: int

    @const
    val gl_MaxDrawBuffers: int

    @const
    val gl_MaxTextureCoords: int

    @const
    val gl_MaxTextureUnits: int

    @const
    val gl_MaxTextureImageUnits: int

    @const
    val gl_MaxVertexTextureImageUnits: int

    @const
    val gl_MaxCombinedTextureImageUnits: int

    @const
    val gl_MaxLights: int

    @const
    val gl_MaxClipPlanes: int

    //
    // BUILT-IN UNIFORMs (7.5 p45) access=RO
    //

    @uniform
    val gl_ModelViewMatrix: mat4

    @uniform
    val gl_ModelViewProjectionMatrix: mat4

    @uniform
    val gl_ProjectionMatrix: mat4

    @uniform
    val gl_TextureMatrix: Array<mat4>

    @uniform
    val gl_ModelViewMatrixInverse: mat4

    @uniform
    val gl_ModelViewProjectionMatrixInverse: mat4

    @uniform
    val gl_ProjectionMatrixInverse: mat4

    @uniform
    val gl_TextureMatrixInverse: Array<mat4>

    @uniform
    val gl_ModelViewMatrixTranspose: mat4

    @uniform
    val gl_ModelViewProjectionMatrixTranspose: mat4

    @uniform
    val gl_ProjectionMatrixTranspose: mat4

    @uniform
    val gl_TextureMatrixTranspose: Array<mat4>

    @uniform
    val gl_ModelViewMatrixInverseTranspose: mat4

    @uniform
    val gl_ModelViewProjectionMatrixInverseTranspose: mat4

    @uniform
    val gl_ProjectionMatrixInverseTranspose: mat4

    @uniform
    val gl_TextureMatrixInverseTranspose: Array<mat4>

    @uniform
    val gl_NormalMatrix: mat3

    @uniform
    val gl_NormalScale: float

    interface gl_DepthRangeParameters {
        val near: float
        val far: float
        val diff: float
    }

    @uniform
    val gl_DepthRange: gl_DepthRangeParameters

    interface gl_FogParameters {
        val color: vec4
        val density: float
        val start: float
        val end: float
        val scale: float
    }

    @uniform
    val gl_Fog: gl_FogParameters

    interface gl_LightSourceParameters {
        val ambient: vec4
        val diffuse: vec4
        val specular: vec4
        val position: vec4
        val halfVector: vec4
        val spotDirection: vec3
        val spotExponent: float
        val spotCutoff: float
        val spotCosCutoff: float
        val constantAttenuation: float
        val linearAttenuation: float
        val quadraticAttenuation: float
    }

    @uniform
    val gl_LightSource: Array<gl_LightSourceParameters>

    //
    // OpenSceneGraph Preset Uniforms as of OSG 1.0
    //

    val osg_FrameNumber: int
    val osg_FrameTime: float
    val osg_DeltaFrameTime: float
    val osg_ViewMatrix: mat4
    val osg_ViewMatrixInverse: mat4

    //
    // Angle and Trigonometry Functions (8.1 p51)
    //

    fun <T : genType> sin(a: T): T
    fun <T : genType> cos(a: T): T
    fun <T : genType> tan(a: T): T
    fun <T : genType> asin(a: T): T
    fun <T : genType> acos(a: T): T
    fun <T : genType> atan(a: T, b: T): T
    fun <T : genType> atan(a: T): T
    fun <T : genType> radians(a: T): T
    fun <T : genType> degrees(a: T): T

    //
    // Exponential Functions (8.2 p52)
    //

    fun <T : genType> pow(a: T, b: T): T
    fun <T : genType> exp(a: T): T
    fun <T : genType> log(a: T): T
    fun <T : genType> exp2(a: T): T
    fun <T : genType> log2(a: T): T
    fun <T : genType> sqrt(a: T): T
    fun <T : genType> inversesqrt(a: T): T

    //
    // Common Functions (8.3 p52)
    //

    fun <T : genType> abs(a: T): T
    fun <T : genType> ceil(a: T): T
    fun <T : genType> clamp(a: T, b: T, c: T): T
    fun <T : genType> clamp(a: T, b: float, c: float): T
    fun <T : genType> floor(a: T): T
    fun <T : genType> fract(a: T): T
    fun <T : genType> max(a: T, b: T): T
    fun <T : genType> max(a: T, b: float): T
    fun <T : genType> min(a: T, b: T): T
    fun <T : genType> min(a: T, b: float): T
    fun <T : genType> mix(a: T, b: T, c: T): T
    fun <T : genType> mix(a: T, b: T, c: float): T
    fun <T : genType> mod(a: T, b: T): T
    fun <T : genType> mod(a: T, b: float): T
    fun <T : genType> sign(a: T): T
    fun <T : genType> smoothstep(a: T, b: T, c: T): T
    fun <T : genType> smoothstep(a: float, b: float, c: T): T
    fun <T : genType> step(a: T, b: T): T
    fun <T : genType> step(a: float, b: T): T

    //
    // Geometric Functions (8.4 p54)
    //

    fun cross(a: vec3, b: vec3): vec3
    fun <T : genType> distance(a: T, b: T): float
    fun <T : genType> dot(a: T, b: T): float
    fun <T : genType> faceforward(v: T, i: T, n: T): T
    fun <T : genType> length(a: T): float
    fun <T : genType> normalize(a: T): T
    fun <T : genType> reflect(i: T, n: T): T
    fun <T : genType> refract(i: T, n: T, eta: float): genType

    //
    // Matrix Funcitons
    //
    fun <T : mat> matrixCompMult(a: T, b: T): T

    //
    // Vector Relational Functions (8.6 p55)
    //
    fun <T : bvec> all(t: T): bool
    fun <T : bvec> any(t: T): bool
    fun <T : vec, R : bvec> equal(a: T, b: T): R
    fun <T : ivec, R : bvec> equal(a: T, b: T): R
    fun <T : bvec, R : bvec> equal(a: T, b: T): R
    fun <T : vec, R : bvec> greaterThan(a: T, b: T): R
    fun <T : ivec, R : bvec> greaterThan(a: T, b: T): R
    fun <T : vec, R : bvec> greaterThanEqual(a: T, b: T): R
    fun <T : ivec, R : bvec> greaterThanEqual(a: T, b: T): R
    fun <T : vec, R : bvec> lessThan(a: T, b: T): R
    fun <T : ivec, R : bvec> lessThan(a: T, b: T): R
    fun <T : vec, R : bvec> lessThanEqual(a: T, b: T): R
    fun <T : ivec, R : bvec> lessThanEqual(a: T, b: T): R
    fun <T : bvec> not(a: T): T
    fun <T : vec, R : bvec> notEqual(a: T, b: T): R
    fun <T : ivec, R : bvec> notEqual(a: T, b: T): R
    fun <T : bvec, R : bvec> notEqual(a: T, b: T): R

    //
    // Texture Lookup Functions (8.7 p56)
    //
    fun texture1D(a: sampler1D, b: float): vec4
    fun texture1DProj(a: sampler1D, b: vec2): vec4
    fun texture1DProj(a: sampler1D, b: vec4): vec4
    fun texture2D(a: sampler2D, b: vec2): vec4
    fun texture2DProj(a: sampler2D, b: vec3): vec4
    fun texture2DProj(a: sampler2D, b: vec4): vec4
    fun texture3D(a: sampler3D, b: vec3): vec4
    fun texture3DProj(a: sampler3D, b: vec4): vec4
    fun textureCube(a: samplerCube, b: vec3): vec4
    fun shadow1D(a: sampler1DShadow, b: vec3): vec4
    fun shadow2D(a: sampler2DShadow, b: vec3): vec4
    fun shadow1DProj(a: sampler1DShadow, b: vec4): vec4
    fun shadow2DProj(a: sampler2DShadow, b: vec4): vec4

    fun vec4(a: vec3, b: float): vec4
}

interface GL_VertexContext : GL_Context {
    //
    // VERTEX SHADER VARIABLES
    //

    // Special Output Variables (7.1 p42) access=RW
    var gl_Position: vec4
    var gl_PointSize: float
    var gl_ClipVertex: vec4

    // Attribute Inputs (7.3 p44) access=RO
    @attribute
    val gl_Vertex: vec4

    @attribute
    val gl_Normal: vec3

    @attribute
    val gl_Color: vec4

    @attribute
    val gl_SecondaryColor: vec4

    @attribute
    val gl_MultiTexCoord0: vec4

    @attribute
    val gl_MultiTexCoord1: vec4

    @attribute
    val gl_MultiTexCoord2: vec4

    @attribute
    val gl_MultiTexCoord3: vec4

    @attribute
    val gl_MultiTexCoord4: vec4

    @attribute
    val gl_MultiTexCoord5: vec4

    @attribute
    val gl_MultiTexCoord6: vec4

    @attribute
    val gl_MultiTexCoord7: vec4

    @attribute
    val gl_FogCoord: float

    // Varying Outputs (7.6 p48) access=RW
    @varying
    var gl_FrontColor: vec4

    @varying
    var gl_BackColor: vec4

    @varying
    var gl_FrontSecondaryColor: vec4

    @varying
    var gl_BackSecondaryColor: vec4

    @varying
    var gl_TexCoord: Array<vec4>

    @varying
    var gl_FogFragCoord: float

    //
    // Geometric Functions (8.4 p54)
    //

    fun ftransform(): vec4

    //
    // Texture Lookup Functions with LOD (8.7 p56)
    //

    fun texture1DLod(a: sampler1D, b: float, lod: float): vec4
    fun texture1DProjLod(a: sampler1D, b: vec2, lod: float): vec4
    fun texture1DProjLod(a: sampler1D, b: vec4, lod: float): vec4
    fun texture2DLod(a: sampler2D, b: vec2, lod: float): vec4
    fun texture2DProjLod(a: sampler2D, b: vec3, lod: float): vec4
    fun texture2DProjLod(a: sampler2D, b: vec4, lod: float): vec4
    fun texture3DProjLod(a: sampler3D, b: vec4, lod: float): vec4
    fun textureCubeLod(a: samplerCube, b: vec3, lod: float): vec4
    fun shadow1DLod(a: sampler1DShadow, b: vec3, lod: float): vec4
    fun shadow2DLod(a: sampler2DShadow, b: vec3, lod: float): vec4
    fun shadow1DProjLod(a: sampler1DShadow, b: vec4, lod: float): vec4
    fun shadow2DProjLod(a: sampler2DShadow, b: vec4, lod: float): vec4

    //
    // Noise Functions
    //

    fun <T : genType> noise1(a: T): float
    fun <T : genType> noise2(a: T): vec2
    fun <T : genType> noise3(a: T): vec3
    fun <T : genType> noise4(a: T): vec4

}

interface GL_FragmentContext : GL_Context {
    //
    // FRAGMENT SHADER VARIABLES
    //

    // Special Output Variables (7.2 p43) access=RW
    var gl_FragColor: vec4
    var gl_FragData: Array<vec4>
    var gl_FragDepth: float

    // Varying Inputs (7.6 p48) access=RO
    @varying
    val gl_Color: vec4

    @varying
    val gl_SecondaryColor: vec4

    @varying
    val gl_TexCoord: Array<vec4>

    @varying
    val gl_FogFragCoord: float

    // Special Input Variables (7.2 p43) access=RO
    val gl_FragCoord: vec4
    val gl_FrontFacing: bool

    //
    // Fragment Processing Functions (8.8 p58)
    //
    fun <T : genType> dFdx(a: T): T
    fun <T : genType> dFdy(a: T): T
    fun <T : genType> fwidth(a: T): T

    //
    // Texture Lookup Functions (8.7 p56)
    //
    fun texture1D(a: sampler1D, b: float, bias: float): vec4
    fun texture1DProj(a: sampler1D, b: vec2, bias: float): vec4
    fun texture1DProj(a: sampler1D, b: vec4, bias: float): vec4
    fun texture2D(a: sampler2D, b: vec2, bias: float): vec4
    fun texture2DProj(a: sampler2D, b: vec3, bias: float): vec4
    fun texture2DProj(a: sampler2D, b: vec4, bias: float): vec4
    fun texture3D(a: sampler3D, b: vec3, bias: float): vec4
    fun texture3DProj(a: sampler3D, b: vec4, bias: float): vec4
    fun textureCube(a: samplerCube, b: vec3, bias: float): vec4
    fun shadow1D(a: sampler1DShadow, b: vec3, bias: float): vec4
    fun shadow2D(a: sampler2DShadow, b: vec3, bias: float): vec4
    fun shadow1DProj(a: sampler1DShadow, b: vec4, bias: float): vec4
    fun shadow2DProj(a: sampler2DShadow, b: vec4, bias: float): vec4
}