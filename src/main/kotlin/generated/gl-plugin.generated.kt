//
// KOMeta GL_Plugin Parser
//

package kometa.gl_plugin

import kometa.*
import kometa.util.*
import kotlin.jvm.internal.Ref

import kometa.kotlin.Token
import kometa.kotlin.AST
import kometa.Matcher
import kometa.kotlin_parser.KotlinParser
import kometa.gl.GL_Sema
import kometa.gl.GL_AST

typealias _GL_Plugin_Inputs = Iterable<Token>
typealias _GL_Plugin_Results = Iterable<AST.AstNode>
typealias _GL_Plugin_Item = MatchItem<Token, AST.AstNode>
typealias _GL_Plugin_Args = Iterable<_GL_Plugin_Item>
typealias _GL_Plugin_Memo = MatchState<Token, AST.AstNode>
typealias _GL_Plugin_Rule = Production<Token, AST.AstNode>
typealias _GL_Plugin_Base = Matcher<Token, AST.AstNode>

open class GL_Plugin(handleLeftRecursion: Boolean = true) : KotlinParser(handleLeftRecursion) {
    init {
        terminals = setOf(
            "CONTEXT",
            "GL_CONTEXT",
            "SHADERFRAGMENT",
            "SHADERVERTEX",
            "shaderExpression",
            "shaderFloatLiteral",
            "shaderFunctionBody",
        )
    }

    override fun userDefinedDeclaration(_memo: _GL_Plugin_Memo, __index: Int, _args: _GL_Plugin_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var decl: _GL_Plugin_Item? = null

        // AND 1
        var _start_i1 = _index.element

        // AND 2
        var _start_i2 = _index.element

        // AND 3
        var _start_i3 = _index.element

        // AND 4
        var _start_i4 = _index.element

        // AND 5
        var _start_i5 = _index.element

        // AND 6
        var _start_i6 = _index.element

        // AND 7
        var _start_i7 = _index.element

        // AND 8
        var _start_i8 = _index.element

        // STAR 10
        var _start_i10 = _index.element
        val _inp10 = arrayListOf<Token?>()
        val _res10 = arrayListOf<AST.AstNode?>()

        // STAR 13
        var _start_i13 = _index.element
        val _inp13 = arrayListOf<Token?>()
        val _res13 = arrayListOf<AST.AstNode?>()

        // STAR 16
        var _start_i16 = _index.element
        val _inp16 = arrayListOf<Token?>()
        val _res16 = arrayListOf<AST.AstNode?>()

        // STAR 19
        var _start_i19 = _index.element
        val _inp19 = arrayListOf<Token?>()
        val _res19 = arrayListOf<AST.AstNode?>()

        // OR 22
        var _start_i22 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 1
                    _start_i1 = _index.element

                    // AND 2
                    _start_i2 = _index.element

                    // AND 3
                    _start_i3 = _index.element

                    // AND 4
                    _start_i4 = _index.element

                    // AND 5
                    _start_i5 = _index.element

                    // AND 6
                    _start_i6 = _index.element

                    // AND 7
                    _start_i7 = _index.element

                    // AND 8
                    _start_i8 = _index.element

                    // CALLORVAR CONTEXT
                    var _r9: _GL_Plugin_Item? = null
                    _r9 = _MemoCall(_memo, "CONTEXT", _index.element, ::CONTEXT, null)
                    if (_r9 != null) _index.element = _r9.nextIndex

                    // AND shortcut 8
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 8
                        continue
                    }

                    // STAR 10
                    _start_i10 = _index.element
                    _label = 10
                }
                // STAR 10
                10 -> {
                    // CALLORVAR NL
                    var _r11: _GL_Plugin_Item? = null
                    _r11 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r11 != null) _index.element = _r11.nextIndex

                    // STAR 10
                    val _r10 = _memo.results.pop()
                    if (_r10 != null) {
                        _res10 += _r10.results
                        _label = 10
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i10, _index.element, _memo.input, _res10.filterNotNull(), true))
                    }

                    _label = 8
                }
                // AND 8
                8 -> {
                    val _r8_2 = _memo.results.pop()
                    val _r8_1 = _memo.results.pop()

                    if (_r8_1 != null && _r8_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i8, _index.element, _memo.input, (_r8_1.results + _r8_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i8
                    }

                    // AND shortcut 7
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 7
                        continue
                    }

                    // CALLORVAR LPAREN
                    var _r12: _GL_Plugin_Item? = null
                    _r12 = _MemoCall(_memo, "LPAREN", _index.element, ::LPAREN, null)
                    if (_r12 != null) _index.element = _r12.nextIndex

                    _label = 7
                }
                // AND 7
                7 -> {
                    val _r7_2 = _memo.results.pop()
                    val _r7_1 = _memo.results.pop()

                    if (_r7_1 != null && _r7_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i7, _index.element, _memo.input, (_r7_1.results + _r7_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i7
                    }

                    // AND shortcut 6
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 6
                        continue
                    }

                    // STAR 13
                    _start_i13 = _index.element
                    _label = 13
                }
                // STAR 13
                13 -> {
                    // CALLORVAR NL
                    var _r14: _GL_Plugin_Item? = null
                    _r14 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r14 != null) _index.element = _r14.nextIndex

                    // STAR 13
                    val _r13 = _memo.results.pop()
                    if (_r13 != null) {
                        _res13 += _r13.results
                        _label = 13
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i13, _index.element, _memo.input, _res13.filterNotNull(), true))
                    }

                    _label = 6
                }
                // AND 6
                6 -> {
                    val _r6_2 = _memo.results.pop()
                    val _r6_1 = _memo.results.pop()

                    if (_r6_1 != null && _r6_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i6, _index.element, _memo.input, (_r6_1.results + _r6_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i6
                    }

                    // AND shortcut 5
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 5
                        continue
                    }

                    // CALLORVAR GL_CONTEXT
                    var _r15: _GL_Plugin_Item? = null
                    _r15 = _MemoCall(_memo, "GL_CONTEXT", _index.element, ::GL_CONTEXT, null)
                    if (_r15 != null) _index.element = _r15.nextIndex

                    _label = 5
                }
                // AND 5
                5 -> {
                    val _r5_2 = _memo.results.pop()
                    val _r5_1 = _memo.results.pop()

                    if (_r5_1 != null && _r5_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i5, _index.element, _memo.input, (_r5_1.results + _r5_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i5
                    }

                    // AND shortcut 4
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 4
                        continue
                    }

                    // STAR 16
                    _start_i16 = _index.element
                    _label = 16
                }
                // STAR 16
                16 -> {
                    // CALLORVAR NL
                    var _r17: _GL_Plugin_Item? = null
                    _r17 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r17 != null) _index.element = _r17.nextIndex

                    // STAR 16
                    val _r16 = _memo.results.pop()
                    if (_r16 != null) {
                        _res16 += _r16.results
                        _label = 16
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i16, _index.element, _memo.input, _res16.filterNotNull(), true))
                    }

                    _label = 4
                }
                // AND 4
                4 -> {
                    val _r4_2 = _memo.results.pop()
                    val _r4_1 = _memo.results.pop()

                    if (_r4_1 != null && _r4_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i4, _index.element, _memo.input, (_r4_1.results + _r4_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i4
                    }

                    // AND shortcut 3
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 3
                        continue
                    }

                    // CALLORVAR RPAREN
                    var _r18: _GL_Plugin_Item? = null
                    _r18 = _MemoCall(_memo, "RPAREN", _index.element, ::RPAREN, null)
                    if (_r18 != null) _index.element = _r18.nextIndex

                    _label = 3
                }
                // AND 3
                3 -> {
                    val _r3_2 = _memo.results.pop()
                    val _r3_1 = _memo.results.pop()

                    if (_r3_1 != null && _r3_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i3, _index.element, _memo.input, (_r3_1.results + _r3_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i3
                    }

                    // AND shortcut 2
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 2
                        continue
                    }

                    // STAR 19
                    _start_i19 = _index.element
                    _label = 19
                }
                // STAR 19
                19 -> {
                    // CALLORVAR NL
                    var _r20: _GL_Plugin_Item? = null
                    _r20 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r20 != null) _index.element = _r20.nextIndex

                    // STAR 19
                    val _r19 = _memo.results.pop()
                    if (_r19 != null) {
                        _res19 += _r19.results
                        _label = 19
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i19, _index.element, _memo.input, _res19.filterNotNull(), true))
                    }

                    _label = 2
                }
                // AND 2
                2 -> {
                    val _r2_2 = _memo.results.pop()
                    val _r2_1 = _memo.results.pop()

                    if (_r2_1 != null && _r2_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i2, _index.element, _memo.input, (_r2_1.results + _r2_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i2
                    }

                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // OR 22
                    _start_i22 = _index.element

                    // CALLORVAR shaderVariableDeclaration
                    var _r23: _GL_Plugin_Item? = null
                    _r23 = _MemoCall(_memo, "shaderVariableDeclaration", _index.element, ::shaderVariableDeclaration, null)
                    if (_r23 != null) _index.element = _r23.nextIndex

                    // OR shortcut 22
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i22
                    } else {
                        _label = 22
                        continue
                    }

                    // CALLORVAR shaderFunctionDeclaration
                    var _r24: _GL_Plugin_Item? = null
                    _r24 = _MemoCall(_memo, "shaderFunctionDeclaration", _index.element, ::shaderFunctionDeclaration, null)
                    if (_r24 != null) _index.element = _r24.nextIndex

                    _label = 22
                }
                // OR 22
                22 -> {
                    // BIND decl
                    decl = _memo.results.peek()

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i1
                    }

                    // ACT 0
                    val _r0 = _memo.results.peek()
                    if (_r0 != null) {
                        _memo.results.pop()
                        _memo.results.push(_GL_Plugin_Item(_r0.startIndex, _r0.nextIndex, _memo.input, _Thunk({ GL_Sema.addDeclaration(decl.r); AST.DropDeclaration }, _r0), true))
                    }

                    break
                }
            }
        }
    }


    open fun shaderFunctionDeclaration(_memo: _GL_Plugin_Memo, __index: Int, _args: _GL_Plugin_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var anns: _GL_Plugin_Item? = null
        var name: _GL_Plugin_Item? = null
        var params: _GL_Plugin_Item? = null
        var type: _GL_Plugin_Item? = null
        var body: _GL_Plugin_Item? = null

        // AND 1
        var _start_i1 = _index.element

        // AND 2
        var _start_i2 = _index.element

        // AND 3
        var _start_i3 = _index.element

        // AND 4
        var _start_i4 = _index.element

        // AND 5
        var _start_i5 = _index.element

        // AND 6
        var _start_i6 = _index.element

        // AND 7
        var _start_i7 = _index.element

        // AND 8
        var _start_i8 = _index.element

        // AND 9
        var _start_i9 = _index.element

        // AND 10
        var _start_i10 = _index.element

        // AND 11
        var _start_i11 = _index.element

        // AND 12
        var _start_i12 = _index.element

        // AND 13
        var _start_i13 = _index.element

        // AND 14
        var _start_i14 = _index.element

        // STAR 16
        var _start_i16 = _index.element
        val _inp16 = arrayListOf<Token?>()
        val _res16 = arrayListOf<AST.AstNode?>()

        // STAR 19
        var _start_i19 = _index.element
        val _inp19 = arrayListOf<Token?>()
        val _res19 = arrayListOf<AST.AstNode?>()

        // STAR 23
        var _start_i23 = _index.element
        val _inp23 = arrayListOf<Token?>()
        val _res23 = arrayListOf<AST.AstNode?>()

        // AND 28
        var _start_i28 = _index.element

        // AND 29
        var _start_i29 = _index.element

        // STAR 31
        var _start_i31 = _index.element
        val _inp31 = arrayListOf<Token?>()
        val _res31 = arrayListOf<AST.AstNode?>()

        // AND 32
        var _start_i32 = _index.element

        // AND 33
        var _start_i33 = _index.element

        // AND 34
        var _start_i34 = _index.element

        // STAR 35
        var _start_i35 = _index.element
        val _inp35 = arrayListOf<Token?>()
        val _res35 = arrayListOf<AST.AstNode?>()

        // STAR 38
        var _start_i38 = _index.element
        val _inp38 = arrayListOf<Token?>()
        val _res38 = arrayListOf<AST.AstNode?>()

        // AND 42
        var _start_i42 = _index.element

        // STAR 43
        var _start_i43 = _index.element
        val _inp43 = arrayListOf<Token?>()
        val _res43 = arrayListOf<AST.AstNode?>()

        // STAR 46
        var _start_i46 = _index.element
        val _inp46 = arrayListOf<Token?>()
        val _res46 = arrayListOf<AST.AstNode?>()

        // STAR 49
        var _start_i49 = _index.element
        val _inp49 = arrayListOf<Token?>()
        val _res49 = arrayListOf<AST.AstNode?>()

        // STAR 52
        var _start_i52 = _index.element
        val _inp52 = arrayListOf<Token?>()
        val _res52 = arrayListOf<AST.AstNode?>()

        // STAR 56
        var _start_i56 = _index.element
        val _inp56 = arrayListOf<Token?>()
        val _res56 = arrayListOf<AST.AstNode?>()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 1
                    _start_i1 = _index.element

                    // AND 2
                    _start_i2 = _index.element

                    // AND 3
                    _start_i3 = _index.element

                    // AND 4
                    _start_i4 = _index.element

                    // AND 5
                    _start_i5 = _index.element

                    // AND 6
                    _start_i6 = _index.element

                    // AND 7
                    _start_i7 = _index.element

                    // AND 8
                    _start_i8 = _index.element

                    // AND 9
                    _start_i9 = _index.element

                    // AND 10
                    _start_i10 = _index.element

                    // AND 11
                    _start_i11 = _index.element

                    // AND 12
                    _start_i12 = _index.element

                    // AND 13
                    _start_i13 = _index.element

                    // AND 14
                    _start_i14 = _index.element

                    // STAR 16
                    _start_i16 = _index.element
                    _label = 16
                }
                // STAR 16
                16 -> {
                    // CALLORVAR annotation
                    var _r17: _GL_Plugin_Item? = null
                    _r17 = _MemoCall(_memo, "annotation", _index.element, ::annotation, null)
                    if (_r17 != null) _index.element = _r17.nextIndex

                    // STAR 16
                    val _r16 = _memo.results.pop()
                    if (_r16 != null) {
                        _res16 += _r16.results
                        _label = 16
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i16, _index.element, _memo.input, _res16.filterNotNull(), true))
                    }

                    // BIND anns
                    anns = _memo.results.peek()

                    // AND shortcut 14
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 14
                        continue
                    }

                    // CALLORVAR FUN
                    var _r18: _GL_Plugin_Item? = null
                    _r18 = _MemoCall(_memo, "FUN", _index.element, ::FUN, null)
                    if (_r18 != null) _index.element = _r18.nextIndex

                    _label = 14
                }
                // AND 14
                14 -> {
                    val _r14_2 = _memo.results.pop()
                    val _r14_1 = _memo.results.pop()

                    if (_r14_1 != null && _r14_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i14, _index.element, _memo.input, (_r14_1.results + _r14_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i14
                    }

                    // AND shortcut 13
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 13
                        continue
                    }

                    // STAR 19
                    _start_i19 = _index.element
                    _label = 19
                }
                // STAR 19
                19 -> {
                    // CALLORVAR NL
                    var _r20: _GL_Plugin_Item? = null
                    _r20 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r20 != null) _index.element = _r20.nextIndex

                    // STAR 19
                    val _r19 = _memo.results.pop()
                    if (_r19 != null) {
                        _res19 += _r19.results
                        _label = 19
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i19, _index.element, _memo.input, _res19.filterNotNull(), true))
                    }

                    _label = 13
                }
                // AND 13
                13 -> {
                    val _r13_2 = _memo.results.pop()
                    val _r13_1 = _memo.results.pop()

                    if (_r13_1 != null && _r13_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i13, _index.element, _memo.input, (_r13_1.results + _r13_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i13
                    }

                    // AND shortcut 12
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 12
                        continue
                    }

                    // CALLORVAR simpleIdentifier
                    var _r22: _GL_Plugin_Item? = null
                    _r22 = _MemoCall(_memo, "simpleIdentifier", _index.element, ::simpleIdentifier, null)
                    if (_r22 != null) _index.element = _r22.nextIndex

                    // BIND name
                    name = _memo.results.peek()

                    _label = 12
                }
                // AND 12
                12 -> {
                    val _r12_2 = _memo.results.pop()
                    val _r12_1 = _memo.results.pop()

                    if (_r12_1 != null && _r12_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i12, _index.element, _memo.input, (_r12_1.results + _r12_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i12
                    }

                    // AND shortcut 11
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 11
                        continue
                    }

                    // STAR 23
                    _start_i23 = _index.element
                    _label = 23
                }
                // STAR 23
                23 -> {
                    // CALLORVAR NL
                    var _r24: _GL_Plugin_Item? = null
                    _r24 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r24 != null) _index.element = _r24.nextIndex

                    // STAR 23
                    val _r23 = _memo.results.pop()
                    if (_r23 != null) {
                        _res23 += _r23.results
                        _label = 23
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i23, _index.element, _memo.input, _res23.filterNotNull(), true))
                    }

                    _label = 11
                }
                // AND 11
                11 -> {
                    val _r11_2 = _memo.results.pop()
                    val _r11_1 = _memo.results.pop()

                    if (_r11_1 != null && _r11_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i11, _index.element, _memo.input, (_r11_1.results + _r11_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i11
                    }

                    // AND shortcut 10
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 10
                        continue
                    }

                    // CALLORVAR LPAREN
                    var _r25: _GL_Plugin_Item? = null
                    _r25 = _MemoCall(_memo, "LPAREN", _index.element, ::LPAREN, null)
                    if (_r25 != null) _index.element = _r25.nextIndex

                    _label = 10
                }
                // AND 10
                10 -> {
                    val _r10_2 = _memo.results.pop()
                    val _r10_1 = _memo.results.pop()

                    if (_r10_1 != null && _r10_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i10, _index.element, _memo.input, (_r10_1.results + _r10_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i10
                    }

                    // AND shortcut 9
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 9
                        continue
                    }

                    // AND 28
                    _start_i28 = _index.element

                    // AND 29
                    _start_i29 = _index.element

                    // CALLORVAR shaderParam
                    var _r30: _GL_Plugin_Item? = null
                    _r30 = _MemoCall(_memo, "shaderParam", _index.element, ::shaderParam, null)
                    if (_r30 != null) _index.element = _r30.nextIndex

                    // AND shortcut 29
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 29
                        continue
                    }

                    // STAR 31
                    _start_i31 = _index.element
                    _label = 31
                }
                // STAR 31
                31 -> {
                    // AND 32
                    _start_i32 = _index.element

                    // AND 33
                    _start_i33 = _index.element

                    // AND 34
                    _start_i34 = _index.element

                    // STAR 35
                    _start_i35 = _index.element
                    _label = 35
                }
                // STAR 35
                35 -> {
                    // CALLORVAR NL
                    var _r36: _GL_Plugin_Item? = null
                    _r36 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r36 != null) _index.element = _r36.nextIndex

                    // STAR 35
                    val _r35 = _memo.results.pop()
                    if (_r35 != null) {
                        _res35 += _r35.results
                        _label = 35
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i35, _index.element, _memo.input, _res35.filterNotNull(), true))
                    }

                    // AND shortcut 34
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 34
                        continue
                    }

                    // CALLORVAR COMMA
                    var _r37: _GL_Plugin_Item? = null
                    _r37 = _MemoCall(_memo, "COMMA", _index.element, ::COMMA, null)
                    if (_r37 != null) _index.element = _r37.nextIndex

                    _label = 34
                }
                // AND 34
                34 -> {
                    val _r34_2 = _memo.results.pop()
                    val _r34_1 = _memo.results.pop()

                    if (_r34_1 != null && _r34_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i34, _index.element, _memo.input, (_r34_1.results + _r34_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i34
                    }

                    // AND shortcut 33
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 33
                        continue
                    }

                    // STAR 38
                    _start_i38 = _index.element
                    _label = 38
                }
                // STAR 38
                38 -> {
                    // CALLORVAR NL
                    var _r39: _GL_Plugin_Item? = null
                    _r39 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r39 != null) _index.element = _r39.nextIndex

                    // STAR 38
                    val _r38 = _memo.results.pop()
                    if (_r38 != null) {
                        _res38 += _r38.results
                        _label = 38
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i38, _index.element, _memo.input, _res38.filterNotNull(), true))
                    }

                    _label = 33
                }
                // AND 33
                33 -> {
                    val _r33_2 = _memo.results.pop()
                    val _r33_1 = _memo.results.pop()

                    if (_r33_1 != null && _r33_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i33, _index.element, _memo.input, (_r33_1.results + _r33_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i33
                    }

                    // AND shortcut 32
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 32
                        continue
                    }

                    // CALLORVAR shaderParam
                    var _r40: _GL_Plugin_Item? = null
                    _r40 = _MemoCall(_memo, "shaderParam", _index.element, ::shaderParam, null)
                    if (_r40 != null) _index.element = _r40.nextIndex

                    _label = 32
                }
                // AND 32
                32 -> {
                    val _r32_2 = _memo.results.pop()
                    val _r32_1 = _memo.results.pop()

                    if (_r32_1 != null && _r32_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i32, _index.element, _memo.input, (_r32_1.results + _r32_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i32
                    }

                    // STAR 31
                    val _r31 = _memo.results.pop()
                    if (_r31 != null) {
                        _res31 += _r31.results
                        _label = 31
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i31, _index.element, _memo.input, _res31.filterNotNull(), true))
                    }

                    _label = 29
                }
                // AND 29
                29 -> {
                    val _r29_2 = _memo.results.pop()
                    val _r29_1 = _memo.results.pop()

                    if (_r29_1 != null && _r29_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i29, _index.element, _memo.input, (_r29_1.results + _r29_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i29
                    }

                    // AND shortcut 28
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 28
                        continue
                    }

                    // AND 42
                    _start_i42 = _index.element

                    // STAR 43
                    _start_i43 = _index.element
                    _label = 43
                }
                // STAR 43
                43 -> {
                    // CALLORVAR NL
                    var _r44: _GL_Plugin_Item? = null
                    _r44 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r44 != null) _index.element = _r44.nextIndex

                    // STAR 43
                    val _r43 = _memo.results.pop()
                    if (_r43 != null) {
                        _res43 += _r43.results
                        _label = 43
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i43, _index.element, _memo.input, _res43.filterNotNull(), true))
                    }

                    // AND shortcut 42
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 42
                        continue
                    }

                    // CALLORVAR COMMA
                    var _r45: _GL_Plugin_Item? = null
                    _r45 = _MemoCall(_memo, "COMMA", _index.element, ::COMMA, null)
                    if (_r45 != null) _index.element = _r45.nextIndex

                    _label = 42
                }
                // AND 42
                42 -> {
                    val _r42_2 = _memo.results.pop()
                    val _r42_1 = _memo.results.pop()

                    if (_r42_1 != null && _r42_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i42, _index.element, _memo.input, (_r42_1.results + _r42_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i42
                    }

                    // QUES 41
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _memo.results.push(_GL_Plugin_Item(_index.element, _memo.input))
                    }
                    _label = 28
                }
                // AND 28
                28 -> {
                    val _r28_2 = _memo.results.pop()
                    val _r28_1 = _memo.results.pop()

                    if (_r28_1 != null && _r28_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i28, _index.element, _memo.input, (_r28_1.results + _r28_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i28
                    }

                    // QUES 27
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _memo.results.push(_GL_Plugin_Item(_index.element, _memo.input))
                    }
                    // BIND params
                    params = _memo.results.peek()

                    _label = 9
                }
                // AND 9
                9 -> {
                    val _r9_2 = _memo.results.pop()
                    val _r9_1 = _memo.results.pop()

                    if (_r9_1 != null && _r9_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i9, _index.element, _memo.input, (_r9_1.results + _r9_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i9
                    }

                    // AND shortcut 8
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 8
                        continue
                    }

                    // STAR 46
                    _start_i46 = _index.element
                    _label = 46
                }
                // STAR 46
                46 -> {
                    // CALLORVAR NL
                    var _r47: _GL_Plugin_Item? = null
                    _r47 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r47 != null) _index.element = _r47.nextIndex

                    // STAR 46
                    val _r46 = _memo.results.pop()
                    if (_r46 != null) {
                        _res46 += _r46.results
                        _label = 46
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i46, _index.element, _memo.input, _res46.filterNotNull(), true))
                    }

                    _label = 8
                }
                // AND 8
                8 -> {
                    val _r8_2 = _memo.results.pop()
                    val _r8_1 = _memo.results.pop()

                    if (_r8_1 != null && _r8_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i8, _index.element, _memo.input, (_r8_1.results + _r8_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i8
                    }

                    // AND shortcut 7
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 7
                        continue
                    }

                    // CALLORVAR RPAREN
                    var _r48: _GL_Plugin_Item? = null
                    _r48 = _MemoCall(_memo, "RPAREN", _index.element, ::RPAREN, null)
                    if (_r48 != null) _index.element = _r48.nextIndex

                    _label = 7
                }
                // AND 7
                7 -> {
                    val _r7_2 = _memo.results.pop()
                    val _r7_1 = _memo.results.pop()

                    if (_r7_1 != null && _r7_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i7, _index.element, _memo.input, (_r7_1.results + _r7_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i7
                    }

                    // AND shortcut 6
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 6
                        continue
                    }

                    // STAR 49
                    _start_i49 = _index.element
                    _label = 49
                }
                // STAR 49
                49 -> {
                    // CALLORVAR NL
                    var _r50: _GL_Plugin_Item? = null
                    _r50 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r50 != null) _index.element = _r50.nextIndex

                    // STAR 49
                    val _r49 = _memo.results.pop()
                    if (_r49 != null) {
                        _res49 += _r49.results
                        _label = 49
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i49, _index.element, _memo.input, _res49.filterNotNull(), true))
                    }

                    _label = 6
                }
                // AND 6
                6 -> {
                    val _r6_2 = _memo.results.pop()
                    val _r6_1 = _memo.results.pop()

                    if (_r6_1 != null && _r6_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i6, _index.element, _memo.input, (_r6_1.results + _r6_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i6
                    }

                    // AND shortcut 5
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 5
                        continue
                    }

                    // CALLORVAR COLON
                    var _r51: _GL_Plugin_Item? = null
                    _r51 = _MemoCall(_memo, "COLON", _index.element, ::COLON, null)
                    if (_r51 != null) _index.element = _r51.nextIndex

                    _label = 5
                }
                // AND 5
                5 -> {
                    val _r5_2 = _memo.results.pop()
                    val _r5_1 = _memo.results.pop()

                    if (_r5_1 != null && _r5_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i5, _index.element, _memo.input, (_r5_1.results + _r5_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i5
                    }

                    // AND shortcut 4
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 4
                        continue
                    }

                    // STAR 52
                    _start_i52 = _index.element
                    _label = 52
                }
                // STAR 52
                52 -> {
                    // CALLORVAR NL
                    var _r53: _GL_Plugin_Item? = null
                    _r53 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r53 != null) _index.element = _r53.nextIndex

                    // STAR 52
                    val _r52 = _memo.results.pop()
                    if (_r52 != null) {
                        _res52 += _r52.results
                        _label = 52
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i52, _index.element, _memo.input, _res52.filterNotNull(), true))
                    }

                    _label = 4
                }
                // AND 4
                4 -> {
                    val _r4_2 = _memo.results.pop()
                    val _r4_1 = _memo.results.pop()

                    if (_r4_1 != null && _r4_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i4, _index.element, _memo.input, (_r4_1.results + _r4_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i4
                    }

                    // AND shortcut 3
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 3
                        continue
                    }

                    // CALLORVAR simpleIdentifier
                    var _r55: _GL_Plugin_Item? = null
                    _r55 = _MemoCall(_memo, "simpleIdentifier", _index.element, ::simpleIdentifier, null)
                    if (_r55 != null) _index.element = _r55.nextIndex

                    // BIND type
                    type = _memo.results.peek()

                    _label = 3
                }
                // AND 3
                3 -> {
                    val _r3_2 = _memo.results.pop()
                    val _r3_1 = _memo.results.pop()

                    if (_r3_1 != null && _r3_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i3, _index.element, _memo.input, (_r3_1.results + _r3_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i3
                    }

                    // AND shortcut 2
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 2
                        continue
                    }

                    // STAR 56
                    _start_i56 = _index.element
                    _label = 56
                }
                // STAR 56
                56 -> {
                    // CALLORVAR NL
                    var _r57: _GL_Plugin_Item? = null
                    _r57 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r57 != null) _index.element = _r57.nextIndex

                    // STAR 56
                    val _r56 = _memo.results.pop()
                    if (_r56 != null) {
                        _res56 += _r56.results
                        _label = 56
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i56, _index.element, _memo.input, _res56.filterNotNull(), true))
                    }

                    _label = 2
                }
                // AND 2
                2 -> {
                    val _r2_2 = _memo.results.pop()
                    val _r2_1 = _memo.results.pop()

                    if (_r2_1 != null && _r2_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i2, _index.element, _memo.input, (_r2_1.results + _r2_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i2
                    }

                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // CALLORVAR shaderFunctionBody
                    var _r59: _GL_Plugin_Item? = null
                    _r59 = _MemoCall(_memo, "shaderFunctionBody", _index.element, ::shaderFunctionBody, null)
                    if (_r59 != null) _index.element = _r59.nextIndex

                    // BIND body
                    body = _memo.results.peek()

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i1
                    }

                    // ACT 0
                    val _r0 = _memo.results.peek()
                    if (_r0 != null) {
                        _memo.results.pop()
                        _memo.results.push(_GL_Plugin_Item(_r0.startIndex, _r0.nextIndex, _memo.input, _Thunk({ GL_AST.FunctionDeclaration(anns.l, name.s, type.s, params.l, body.r) }, _r0), true))
                    }

                    break
                }
            }
        }
    }


    open fun shaderParam(_memo: _GL_Plugin_Memo, __index: Int, _args: _GL_Plugin_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var name: _GL_Plugin_Item? = null
        var type: _GL_Plugin_Item? = null

        // AND 1
        var _start_i1 = _index.element

        // AND 2
        var _start_i2 = _index.element

        // AND 3
        var _start_i3 = _index.element

        // AND 4
        var _start_i4 = _index.element

        // STAR 7
        var _start_i7 = _index.element
        val _inp7 = arrayListOf<Token?>()
        val _res7 = arrayListOf<AST.AstNode?>()

        // STAR 10
        var _start_i10 = _index.element
        val _inp10 = arrayListOf<Token?>()
        val _res10 = arrayListOf<AST.AstNode?>()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 1
                    _start_i1 = _index.element

                    // AND 2
                    _start_i2 = _index.element

                    // AND 3
                    _start_i3 = _index.element

                    // AND 4
                    _start_i4 = _index.element

                    // CALLORVAR simpleIdentifier
                    var _r6: _GL_Plugin_Item? = null
                    _r6 = _MemoCall(_memo, "simpleIdentifier", _index.element, ::simpleIdentifier, null)
                    if (_r6 != null) _index.element = _r6.nextIndex

                    // BIND name
                    name = _memo.results.peek()

                    // AND shortcut 4
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 4
                        continue
                    }

                    // STAR 7
                    _start_i7 = _index.element
                    _label = 7
                }
                // STAR 7
                7 -> {
                    // CALLORVAR NL
                    var _r8: _GL_Plugin_Item? = null
                    _r8 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r8 != null) _index.element = _r8.nextIndex

                    // STAR 7
                    val _r7 = _memo.results.pop()
                    if (_r7 != null) {
                        _res7 += _r7.results
                        _label = 7
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i7, _index.element, _memo.input, _res7.filterNotNull(), true))
                    }

                    _label = 4
                }
                // AND 4
                4 -> {
                    val _r4_2 = _memo.results.pop()
                    val _r4_1 = _memo.results.pop()

                    if (_r4_1 != null && _r4_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i4, _index.element, _memo.input, (_r4_1.results + _r4_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i4
                    }

                    // AND shortcut 3
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 3
                        continue
                    }

                    // CALLORVAR COLON
                    var _r9: _GL_Plugin_Item? = null
                    _r9 = _MemoCall(_memo, "COLON", _index.element, ::COLON, null)
                    if (_r9 != null) _index.element = _r9.nextIndex

                    _label = 3
                }
                // AND 3
                3 -> {
                    val _r3_2 = _memo.results.pop()
                    val _r3_1 = _memo.results.pop()

                    if (_r3_1 != null && _r3_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i3, _index.element, _memo.input, (_r3_1.results + _r3_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i3
                    }

                    // AND shortcut 2
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 2
                        continue
                    }

                    // STAR 10
                    _start_i10 = _index.element
                    _label = 10
                }
                // STAR 10
                10 -> {
                    // CALLORVAR NL
                    var _r11: _GL_Plugin_Item? = null
                    _r11 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r11 != null) _index.element = _r11.nextIndex

                    // STAR 10
                    val _r10 = _memo.results.pop()
                    if (_r10 != null) {
                        _res10 += _r10.results
                        _label = 10
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i10, _index.element, _memo.input, _res10.filterNotNull(), true))
                    }

                    _label = 2
                }
                // AND 2
                2 -> {
                    val _r2_2 = _memo.results.pop()
                    val _r2_1 = _memo.results.pop()

                    if (_r2_1 != null && _r2_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i2, _index.element, _memo.input, (_r2_1.results + _r2_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i2
                    }

                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // CALLORVAR simpleIdentifier
                    var _r13: _GL_Plugin_Item? = null
                    _r13 = _MemoCall(_memo, "simpleIdentifier", _index.element, ::simpleIdentifier, null)
                    if (_r13 != null) _index.element = _r13.nextIndex

                    // BIND type
                    type = _memo.results.peek()

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i1
                    }

                    // ACT 0
                    val _r0 = _memo.results.peek()
                    if (_r0 != null) {
                        _memo.results.pop()
                        _memo.results.push(_GL_Plugin_Item(_r0.startIndex, _r0.nextIndex, _memo.input, _Thunk({ GL_AST.ValueParameter(name.s, type.s) }, _r0), true))
                    }

                    break
                }
            }
        }
    }


    open fun shaderFunctionBody(_memo: _GL_Plugin_Memo, __index: Int, _args: _GL_Plugin_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // CALLORVAR shaderBlock
                    var _r0: _GL_Plugin_Item? = null
                    _r0 = _MemoCall(_memo, "shaderBlock", _index.element, ::shaderBlock, null)
                    if (_r0 != null) _index.element = _r0.nextIndex

                    break
                }
            }
        }
    }


    open fun CONTEXT(_memo: _GL_Plugin_Memo, __index: Int, _args: _GL_Plugin_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var t: _GL_Plugin_Item? = null

        // COND 0
        var _start_i0 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // COND 0
                    _start_i0 = _index.element

                    // ANY
                    _ParseAny(_memo, _index)

                    // BIND t
                    t = _memo.results.peek()

                    // COND 0
                    val lambda0: (_GL_Plugin_Item) -> Boolean = { ((t.i as? Token.Identifier)?.s == "context") }
                    if (_memo.results.peek() == null || !lambda0(_memo.results.peek()!!)) {
                        _memo.results.pop()
                        _memo.results.push(null)
                        _index.element = _start_i0
                    }

                    break
                }
            }
        }
    }


    open fun GL_CONTEXT(_memo: _GL_Plugin_Memo, __index: Int, _args: _GL_Plugin_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var t: _GL_Plugin_Item? = null

        // COND 0
        var _start_i0 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // COND 0
                    _start_i0 = _index.element

                    // ANY
                    _ParseAny(_memo, _index)

                    // BIND t
                    t = _memo.results.peek()

                    // COND 0
                    val lambda0: (_GL_Plugin_Item) -> Boolean = { ((t.i as? Token.Identifier)?.s == "GL_Context") }
                    if (_memo.results.peek() == null || !lambda0(_memo.results.peek()!!)) {
                        _memo.results.pop()
                        _memo.results.push(null)
                        _index.element = _start_i0
                    }

                    break
                }
            }
        }
    }


    override fun userDefinedExpression(_memo: _GL_Plugin_Memo, __index: Int, _args: _GL_Plugin_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var sb: _GL_Plugin_Item? = null

        // OR 0
        var _start_i0 = _index.element

        // AND 2
        var _start_i2 = _index.element

        // AND 3
        var _start_i3 = _index.element

        // STAR 5
        var _start_i5 = _index.element
        val _inp5 = arrayListOf<Token?>()
        val _res5 = arrayListOf<AST.AstNode?>()

        // OR 7
        var _start_i7 = _index.element

        // AND 12
        var _start_i12 = _index.element

        // AND 13
        var _start_i13 = _index.element

        // STAR 15
        var _start_i15 = _index.element
        val _inp15 = arrayListOf<Token?>()
        val _res15 = arrayListOf<AST.AstNode?>()

        // OR 17
        var _start_i17 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // OR 0
                    _start_i0 = _index.element

                    // AND 2
                    _start_i2 = _index.element

                    // AND 3
                    _start_i3 = _index.element

                    // CALLORVAR SHADERVERTEX
                    var _r4: _GL_Plugin_Item? = null
                    _r4 = _MemoCall(_memo, "SHADERVERTEX", _index.element, ::SHADERVERTEX, null)
                    if (_r4 != null) _index.element = _r4.nextIndex

                    // AND shortcut 3
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 3
                        continue
                    }

                    // STAR 5
                    _start_i5 = _index.element
                    _label = 5
                }
                // STAR 5
                5 -> {
                    // CALLORVAR NL
                    var _r6: _GL_Plugin_Item? = null
                    _r6 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r6 != null) _index.element = _r6.nextIndex

                    // STAR 5
                    val _r5 = _memo.results.pop()
                    if (_r5 != null) {
                        _res5 += _r5.results
                        _label = 5
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i5, _index.element, _memo.input, _res5.filterNotNull(), true))
                    }

                    _label = 3
                }
                // AND 3
                3 -> {
                    val _r3_2 = _memo.results.pop()
                    val _r3_1 = _memo.results.pop()

                    if (_r3_1 != null && _r3_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i3, _index.element, _memo.input, (_r3_1.results + _r3_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i3
                    }

                    // AND shortcut 2
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 2
                        continue
                    }

                    // OR 7
                    _start_i7 = _index.element

                    // CALLORVAR shaderBlock
                    var _r9: _GL_Plugin_Item? = null
                    _r9 = _MemoCall(_memo, "shaderBlock", _index.element, ::shaderBlock, null)
                    if (_r9 != null) _index.element = _r9.nextIndex

                    // BIND sb
                    sb = _memo.results.peek()

                    // OR shortcut 7
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i7
                    } else {
                        _label = 7
                        continue
                    }

                    // FAIL 10
                    _memo.results.push(null)
                    error("failed to parse shaderBlock" + ":\n" + formatErrorString(_memo, _index.element))

                    _label = 7
                }
                // OR 7
                7 -> {
                    _label = 2
                }
                // AND 2
                2 -> {
                    val _r2_2 = _memo.results.pop()
                    val _r2_1 = _memo.results.pop()

                    if (_r2_1 != null && _r2_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i2, _index.element, _memo.input, (_r2_1.results + _r2_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i2
                    }

                    // ACT 1
                    val _r1 = _memo.results.peek()
                    if (_r1 != null) {
                        _memo.results.pop()
                        _memo.results.push(_GL_Plugin_Item(_r1.startIndex, _r1.nextIndex, _memo.input, _Thunk({ GL_Sema.checkShaderVertexAndCreateStringLiteral(sb.r.cast()) }, _r1), true))
                    }

                    // OR shortcut 0
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i0
                    } else {
                        _label = 0
                        continue
                    }

                    // AND 12
                    _start_i12 = _index.element

                    // AND 13
                    _start_i13 = _index.element

                    // CALLORVAR SHADERFRAGMENT
                    var _r14: _GL_Plugin_Item? = null
                    _r14 = _MemoCall(_memo, "SHADERFRAGMENT", _index.element, ::SHADERFRAGMENT, null)
                    if (_r14 != null) _index.element = _r14.nextIndex

                    // AND shortcut 13
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 13
                        continue
                    }

                    // STAR 15
                    _start_i15 = _index.element
                    _label = 15
                }
                // STAR 15
                15 -> {
                    // CALLORVAR NL
                    var _r16: _GL_Plugin_Item? = null
                    _r16 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r16 != null) _index.element = _r16.nextIndex

                    // STAR 15
                    val _r15 = _memo.results.pop()
                    if (_r15 != null) {
                        _res15 += _r15.results
                        _label = 15
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i15, _index.element, _memo.input, _res15.filterNotNull(), true))
                    }

                    _label = 13
                }
                // AND 13
                13 -> {
                    val _r13_2 = _memo.results.pop()
                    val _r13_1 = _memo.results.pop()

                    if (_r13_1 != null && _r13_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i13, _index.element, _memo.input, (_r13_1.results + _r13_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i13
                    }

                    // AND shortcut 12
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 12
                        continue
                    }

                    // OR 17
                    _start_i17 = _index.element

                    // CALLORVAR shaderBlock
                    var _r19: _GL_Plugin_Item? = null
                    _r19 = _MemoCall(_memo, "shaderBlock", _index.element, ::shaderBlock, null)
                    if (_r19 != null) _index.element = _r19.nextIndex

                    // BIND sb
                    sb = _memo.results.peek()

                    // OR shortcut 17
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i17
                    } else {
                        _label = 17
                        continue
                    }

                    // FAIL 20
                    _memo.results.push(null)
                    error("failed to parse shaderBlock" + ":\n" + formatErrorString(_memo, _index.element))

                    _label = 17
                }
                // OR 17
                17 -> {
                    _label = 12
                }
                // AND 12
                12 -> {
                    val _r12_2 = _memo.results.pop()
                    val _r12_1 = _memo.results.pop()

                    if (_r12_1 != null && _r12_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i12, _index.element, _memo.input, (_r12_1.results + _r12_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i12
                    }

                    // ACT 11
                    val _r11 = _memo.results.peek()
                    if (_r11 != null) {
                        _memo.results.pop()
                        _memo.results.push(_GL_Plugin_Item(_r11.startIndex, _r11.nextIndex, _memo.input, _Thunk({ GL_Sema.checkShaderFragmentAndCreateStringLiteral(sb.r.cast()) }, _r11), true))
                    }

                    _label = 0
                }
                // OR 0
                0 -> {
                    break
                }
            }
        }
    }


    open fun SHADERVERTEX(_memo: _GL_Plugin_Memo, __index: Int, _args: _GL_Plugin_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var t: _GL_Plugin_Item? = null

        // COND 0
        var _start_i0 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // COND 0
                    _start_i0 = _index.element

                    // ANY
                    _ParseAny(_memo, _index)

                    // BIND t
                    t = _memo.results.peek()

                    // COND 0
                    val lambda0: (_GL_Plugin_Item) -> Boolean = { ((t.i as? Token.Identifier)?.s == "shaderVertex") }
                    if (_memo.results.peek() == null || !lambda0(_memo.results.peek()!!)) {
                        _memo.results.pop()
                        _memo.results.push(null)
                        _index.element = _start_i0
                    }

                    break
                }
            }
        }
    }


    open fun SHADERFRAGMENT(_memo: _GL_Plugin_Memo, __index: Int, _args: _GL_Plugin_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var t: _GL_Plugin_Item? = null

        // COND 0
        var _start_i0 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // COND 0
                    _start_i0 = _index.element

                    // ANY
                    _ParseAny(_memo, _index)

                    // BIND t
                    t = _memo.results.peek()

                    // COND 0
                    val lambda0: (_GL_Plugin_Item) -> Boolean = { ((t.i as? Token.Identifier)?.s == "shaderFragment") }
                    if (_memo.results.peek() == null || !lambda0(_memo.results.peek()!!)) {
                        _memo.results.pop()
                        _memo.results.push(null)
                        _index.element = _start_i0
                    }

                    break
                }
            }
        }
    }


    open fun shaderBlock(_memo: _GL_Plugin_Memo, __index: Int, _args: _GL_Plugin_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 1
        var _start_i1 = _index.element

        // AND 2
        var _start_i2 = _index.element

        // AND 3
        var _start_i3 = _index.element

        // AND 4
        var _start_i4 = _index.element

        // AND 5
        var _start_i5 = _index.element

        // STAR 7
        var _start_i7 = _index.element
        val _inp7 = arrayListOf<Token?>()
        val _res7 = arrayListOf<AST.AstNode?>()

        // AND 10
        var _start_i10 = _index.element

        // STAR 12
        var _start_i12 = _index.element
        val _inp12 = arrayListOf<Token?>()
        val _res12 = arrayListOf<AST.AstNode?>()

        // AND 13
        var _start_i13 = _index.element

        // STAR 18
        var _start_i18 = _index.element
        val _inp18 = arrayListOf<Token?>()
        val _res18 = arrayListOf<AST.AstNode?>()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 1
                    _start_i1 = _index.element

                    // AND 2
                    _start_i2 = _index.element

                    // AND 3
                    _start_i3 = _index.element

                    // AND 4
                    _start_i4 = _index.element

                    // AND 5
                    _start_i5 = _index.element

                    // CALLORVAR LCURL
                    var _r6: _GL_Plugin_Item? = null
                    _r6 = _MemoCall(_memo, "LCURL", _index.element, ::LCURL, null)
                    if (_r6 != null) _index.element = _r6.nextIndex

                    // AND shortcut 5
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 5
                        continue
                    }

                    // STAR 7
                    _start_i7 = _index.element
                    _label = 7
                }
                // STAR 7
                7 -> {
                    // CALLORVAR NL
                    var _r8: _GL_Plugin_Item? = null
                    _r8 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r8 != null) _index.element = _r8.nextIndex

                    // STAR 7
                    val _r7 = _memo.results.pop()
                    if (_r7 != null) {
                        _res7 += _r7.results
                        _label = 7
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i7, _index.element, _memo.input, _res7.filterNotNull(), true))
                    }

                    _label = 5
                }
                // AND 5
                5 -> {
                    val _r5_2 = _memo.results.pop()
                    val _r5_1 = _memo.results.pop()

                    if (_r5_1 != null && _r5_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i5, _index.element, _memo.input, (_r5_1.results + _r5_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i5
                    }

                    // AND shortcut 4
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 4
                        continue
                    }

                    // AND 10
                    _start_i10 = _index.element

                    // CALLORVAR shaderStatement
                    var _r11: _GL_Plugin_Item? = null
                    _r11 = _MemoCall(_memo, "shaderStatement", _index.element, ::shaderStatement, null)
                    if (_r11 != null) _index.element = _r11.nextIndex

                    // AND shortcut 10
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 10
                        continue
                    }

                    // STAR 12
                    _start_i12 = _index.element
                    _label = 12
                }
                // STAR 12
                12 -> {
                    // AND 13
                    _start_i13 = _index.element

                    // CALLORVAR semis
                    var _r14: _GL_Plugin_Item? = null
                    _r14 = _MemoCall(_memo, "semis", _index.element, ::semis, null)
                    if (_r14 != null) _index.element = _r14.nextIndex

                    // AND shortcut 13
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 13
                        continue
                    }

                    // CALLORVAR shaderStatement
                    var _r15: _GL_Plugin_Item? = null
                    _r15 = _MemoCall(_memo, "shaderStatement", _index.element, ::shaderStatement, null)
                    if (_r15 != null) _index.element = _r15.nextIndex

                    _label = 13
                }
                // AND 13
                13 -> {
                    val _r13_2 = _memo.results.pop()
                    val _r13_1 = _memo.results.pop()

                    if (_r13_1 != null && _r13_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i13, _index.element, _memo.input, (_r13_1.results + _r13_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i13
                    }

                    // STAR 12
                    val _r12 = _memo.results.pop()
                    if (_r12 != null) {
                        _res12 += _r12.results
                        _label = 12
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i12, _index.element, _memo.input, _res12.filterNotNull(), true))
                    }

                    _label = 10
                }
                // AND 10
                10 -> {
                    val _r10_2 = _memo.results.pop()
                    val _r10_1 = _memo.results.pop()

                    if (_r10_1 != null && _r10_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i10, _index.element, _memo.input, (_r10_1.results + _r10_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i10
                    }

                    // QUES 9
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _memo.results.push(_GL_Plugin_Item(_index.element, _memo.input))
                    }
                    _label = 4
                }
                // AND 4
                4 -> {
                    val _r4_2 = _memo.results.pop()
                    val _r4_1 = _memo.results.pop()

                    if (_r4_1 != null && _r4_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i4, _index.element, _memo.input, (_r4_1.results + _r4_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i4
                    }

                    // AND shortcut 3
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 3
                        continue
                    }

                    // CALLORVAR semis
                    var _r17: _GL_Plugin_Item? = null
                    _r17 = _MemoCall(_memo, "semis", _index.element, ::semis, null)
                    if (_r17 != null) _index.element = _r17.nextIndex

                    // QUES 16
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _memo.results.push(_GL_Plugin_Item(_index.element, _memo.input))
                    }
                    _label = 3
                }
                // AND 3
                3 -> {
                    val _r3_2 = _memo.results.pop()
                    val _r3_1 = _memo.results.pop()

                    if (_r3_1 != null && _r3_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i3, _index.element, _memo.input, (_r3_1.results + _r3_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i3
                    }

                    // AND shortcut 2
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 2
                        continue
                    }

                    // STAR 18
                    _start_i18 = _index.element
                    _label = 18
                }
                // STAR 18
                18 -> {
                    // CALLORVAR NL
                    var _r19: _GL_Plugin_Item? = null
                    _r19 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r19 != null) _index.element = _r19.nextIndex

                    // STAR 18
                    val _r18 = _memo.results.pop()
                    if (_r18 != null) {
                        _res18 += _r18.results
                        _label = 18
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i18, _index.element, _memo.input, _res18.filterNotNull(), true))
                    }

                    _label = 2
                }
                // AND 2
                2 -> {
                    val _r2_2 = _memo.results.pop()
                    val _r2_1 = _memo.results.pop()

                    if (_r2_1 != null && _r2_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i2, _index.element, _memo.input, (_r2_1.results + _r2_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i2
                    }

                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // CALLORVAR RCURL
                    var _r20: _GL_Plugin_Item? = null
                    _r20 = _MemoCall(_memo, "RCURL", _index.element, ::RCURL, null)
                    if (_r20 != null) _index.element = _r20.nextIndex

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i1
                    }

                    // ACT 0
                    val _r0 = _memo.results.peek()
                    if (_r0 != null) {
                        _memo.results.pop()
                        _memo.results.push(_GL_Plugin_Item(_r0.startIndex, _r0.nextIndex, _memo.input, _Thunk({ GL_AST.Block(it.l) }, _r0), true))
                    }

                    break
                }
            }
        }
    }


    open fun shaderStatement(_memo: _GL_Plugin_Memo, __index: Int, _args: _GL_Plugin_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // OR 0
        var _start_i0 = _index.element

        // OR 1
        var _start_i1 = _index.element

        // OR 2
        var _start_i2 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // OR 0
                    _start_i0 = _index.element

                    // OR 1
                    _start_i1 = _index.element

                    // OR 2
                    _start_i2 = _index.element

                    // CALLORVAR shaderVariableDeclaration
                    var _r3: _GL_Plugin_Item? = null
                    _r3 = _MemoCall(_memo, "shaderVariableDeclaration", _index.element, ::shaderVariableDeclaration, null)
                    if (_r3 != null) _index.element = _r3.nextIndex

                    // OR shortcut 2
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i2
                    } else {
                        _label = 2
                        continue
                    }

                    // CALLORVAR shaderAssignment
                    var _r4: _GL_Plugin_Item? = null
                    _r4 = _MemoCall(_memo, "shaderAssignment", _index.element, ::shaderAssignment, null)
                    if (_r4 != null) _index.element = _r4.nextIndex

                    _label = 2
                }
                // OR 2
                2 -> {
                    // OR shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i1
                    } else {
                        _label = 1
                        continue
                    }

                    // CALLORVAR shaderExpression
                    var _r5: _GL_Plugin_Item? = null
                    _r5 = _MemoCall(_memo, "shaderExpression", _index.element, ::shaderExpression, null)
                    if (_r5 != null) _index.element = _r5.nextIndex

                    _label = 1
                }
                // OR 1
                1 -> {
                    // OR shortcut 0
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i0
                    } else {
                        _label = 0
                        continue
                    }

                    // CALLORVAR shaderReturnStatement
                    var _r6: _GL_Plugin_Item? = null
                    _r6 = _MemoCall(_memo, "shaderReturnStatement", _index.element, ::shaderReturnStatement, null)
                    if (_r6 != null) _index.element = _r6.nextIndex

                    _label = 0
                }
                // OR 0
                0 -> {
                    break
                }
            }
        }
    }


    open fun shaderReturnStatement(_memo: _GL_Plugin_Memo, __index: Int, _args: _GL_Plugin_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var expr: _GL_Plugin_Item? = null

        // AND 1
        var _start_i1 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 1
                    _start_i1 = _index.element

                    // CALLORVAR RETURN
                    var _r2: _GL_Plugin_Item? = null
                    _r2 = _MemoCall(_memo, "RETURN", _index.element, ::RETURN, null)
                    if (_r2 != null) _index.element = _r2.nextIndex

                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // CALLORVAR shaderExpression
                    var _r4: _GL_Plugin_Item? = null
                    _r4 = _MemoCall(_memo, "shaderExpression", _index.element, ::shaderExpression, null)
                    if (_r4 != null) _index.element = _r4.nextIndex

                    // BIND expr
                    expr = _memo.results.peek()

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i1
                    }

                    // ACT 0
                    val _r0 = _memo.results.peek()
                    if (_r0 != null) {
                        _memo.results.pop()
                        _memo.results.push(_GL_Plugin_Item(_r0.startIndex, _r0.nextIndex, _memo.input, _Thunk({ GL_AST.ReturnStatement(expr.r) }, _r0), true))
                    }

                    break
                }
            }
        }
    }


    open fun shaderVariableDeclaration(_memo: _GL_Plugin_Memo, __index: Int, _args: _GL_Plugin_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var ann: _GL_Plugin_Item? = null
        var vov: _GL_Plugin_Item? = null
        var name: _GL_Plugin_Item? = null
        var t: _GL_Plugin_Item? = null
        var expr: _GL_Plugin_Item? = null

        // AND 1
        var _start_i1 = _index.element

        // AND 2
        var _start_i2 = _index.element

        // AND 3
        var _start_i3 = _index.element

        // AND 4
        var _start_i4 = _index.element

        // AND 5
        var _start_i5 = _index.element

        // STAR 7
        var _start_i7 = _index.element
        val _inp7 = arrayListOf<Token?>()
        val _res7 = arrayListOf<AST.AstNode?>()

        // STAR 11
        var _start_i11 = _index.element
        val _inp11 = arrayListOf<Token?>()
        val _res11 = arrayListOf<AST.AstNode?>()

        // AND 16
        var _start_i16 = _index.element

        // AND 17
        var _start_i17 = _index.element

        // AND 18
        var _start_i18 = _index.element

        // STAR 19
        var _start_i19 = _index.element
        val _inp19 = arrayListOf<Token?>()
        val _res19 = arrayListOf<AST.AstNode?>()

        // STAR 22
        var _start_i22 = _index.element
        val _inp22 = arrayListOf<Token?>()
        val _res22 = arrayListOf<AST.AstNode?>()

        // AND 28
        var _start_i28 = _index.element

        // AND 29
        var _start_i29 = _index.element

        // AND 30
        var _start_i30 = _index.element

        // STAR 31
        var _start_i31 = _index.element
        val _inp31 = arrayListOf<Token?>()
        val _res31 = arrayListOf<AST.AstNode?>()

        // STAR 34
        var _start_i34 = _index.element
        val _inp34 = arrayListOf<Token?>()
        val _res34 = arrayListOf<AST.AstNode?>()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 1
                    _start_i1 = _index.element

                    // AND 2
                    _start_i2 = _index.element

                    // AND 3
                    _start_i3 = _index.element

                    // AND 4
                    _start_i4 = _index.element

                    // AND 5
                    _start_i5 = _index.element

                    // STAR 7
                    _start_i7 = _index.element
                    _label = 7
                }
                // STAR 7
                7 -> {
                    // CALLORVAR annotation
                    var _r8: _GL_Plugin_Item? = null
                    _r8 = _MemoCall(_memo, "annotation", _index.element, ::annotation, null)
                    if (_r8 != null) _index.element = _r8.nextIndex

                    // STAR 7
                    val _r7 = _memo.results.pop()
                    if (_r7 != null) {
                        _res7 += _r7.results
                        _label = 7
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i7, _index.element, _memo.input, _res7.filterNotNull(), true))
                    }

                    // BIND ann
                    ann = _memo.results.peek()

                    // AND shortcut 5
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 5
                        continue
                    }

                    // CALLORVAR valOrVar
                    var _r10: _GL_Plugin_Item? = null
                    _r10 = _MemoCall(_memo, "valOrVar", _index.element, ::valOrVar, null)
                    if (_r10 != null) _index.element = _r10.nextIndex

                    // BIND vov
                    vov = _memo.results.peek()

                    _label = 5
                }
                // AND 5
                5 -> {
                    val _r5_2 = _memo.results.pop()
                    val _r5_1 = _memo.results.pop()

                    if (_r5_1 != null && _r5_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i5, _index.element, _memo.input, (_r5_1.results + _r5_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i5
                    }

                    // AND shortcut 4
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 4
                        continue
                    }

                    // STAR 11
                    _start_i11 = _index.element
                    _label = 11
                }
                // STAR 11
                11 -> {
                    // CALLORVAR NL
                    var _r12: _GL_Plugin_Item? = null
                    _r12 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r12 != null) _index.element = _r12.nextIndex

                    // STAR 11
                    val _r11 = _memo.results.pop()
                    if (_r11 != null) {
                        _res11 += _r11.results
                        _label = 11
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i11, _index.element, _memo.input, _res11.filterNotNull(), true))
                    }

                    _label = 4
                }
                // AND 4
                4 -> {
                    val _r4_2 = _memo.results.pop()
                    val _r4_1 = _memo.results.pop()

                    if (_r4_1 != null && _r4_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i4, _index.element, _memo.input, (_r4_1.results + _r4_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i4
                    }

                    // AND shortcut 3
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 3
                        continue
                    }

                    // CALLORVAR simpleIdentifier
                    var _r14: _GL_Plugin_Item? = null
                    _r14 = _MemoCall(_memo, "simpleIdentifier", _index.element, ::simpleIdentifier, null)
                    if (_r14 != null) _index.element = _r14.nextIndex

                    // BIND name
                    name = _memo.results.peek()

                    _label = 3
                }
                // AND 3
                3 -> {
                    val _r3_2 = _memo.results.pop()
                    val _r3_1 = _memo.results.pop()

                    if (_r3_1 != null && _r3_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i3, _index.element, _memo.input, (_r3_1.results + _r3_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i3
                    }

                    // AND shortcut 2
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 2
                        continue
                    }

                    // AND 16
                    _start_i16 = _index.element

                    // AND 17
                    _start_i17 = _index.element

                    // AND 18
                    _start_i18 = _index.element

                    // STAR 19
                    _start_i19 = _index.element
                    _label = 19
                }
                // STAR 19
                19 -> {
                    // CALLORVAR NL
                    var _r20: _GL_Plugin_Item? = null
                    _r20 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r20 != null) _index.element = _r20.nextIndex

                    // STAR 19
                    val _r19 = _memo.results.pop()
                    if (_r19 != null) {
                        _res19 += _r19.results
                        _label = 19
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i19, _index.element, _memo.input, _res19.filterNotNull(), true))
                    }

                    // AND shortcut 18
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 18
                        continue
                    }

                    // CALLORVAR COLON
                    var _r21: _GL_Plugin_Item? = null
                    _r21 = _MemoCall(_memo, "COLON", _index.element, ::COLON, null)
                    if (_r21 != null) _index.element = _r21.nextIndex

                    _label = 18
                }
                // AND 18
                18 -> {
                    val _r18_2 = _memo.results.pop()
                    val _r18_1 = _memo.results.pop()

                    if (_r18_1 != null && _r18_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i18, _index.element, _memo.input, (_r18_1.results + _r18_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i18
                    }

                    // AND shortcut 17
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 17
                        continue
                    }

                    // STAR 22
                    _start_i22 = _index.element
                    _label = 22
                }
                // STAR 22
                22 -> {
                    // CALLORVAR NL
                    var _r23: _GL_Plugin_Item? = null
                    _r23 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r23 != null) _index.element = _r23.nextIndex

                    // STAR 22
                    val _r22 = _memo.results.pop()
                    if (_r22 != null) {
                        _res22 += _r22.results
                        _label = 22
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i22, _index.element, _memo.input, _res22.filterNotNull(), true))
                    }

                    _label = 17
                }
                // AND 17
                17 -> {
                    val _r17_2 = _memo.results.pop()
                    val _r17_1 = _memo.results.pop()

                    if (_r17_1 != null && _r17_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i17, _index.element, _memo.input, (_r17_1.results + _r17_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i17
                    }

                    // AND shortcut 16
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 16
                        continue
                    }

                    // CALLORVAR simpleIdentifier
                    var _r25: _GL_Plugin_Item? = null
                    _r25 = _MemoCall(_memo, "simpleIdentifier", _index.element, ::simpleIdentifier, null)
                    if (_r25 != null) _index.element = _r25.nextIndex

                    // BIND t
                    t = _memo.results.peek()

                    _label = 16
                }
                // AND 16
                16 -> {
                    val _r16_2 = _memo.results.pop()
                    val _r16_1 = _memo.results.pop()

                    if (_r16_1 != null && _r16_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i16, _index.element, _memo.input, (_r16_1.results + _r16_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i16
                    }

                    // QUES 15
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _memo.results.push(_GL_Plugin_Item(_index.element, _memo.input))
                    }
                    _label = 2
                }
                // AND 2
                2 -> {
                    val _r2_2 = _memo.results.pop()
                    val _r2_1 = _memo.results.pop()

                    if (_r2_1 != null && _r2_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i2, _index.element, _memo.input, (_r2_1.results + _r2_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i2
                    }

                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // AND 28
                    _start_i28 = _index.element

                    // AND 29
                    _start_i29 = _index.element

                    // AND 30
                    _start_i30 = _index.element

                    // STAR 31
                    _start_i31 = _index.element
                    _label = 31
                }
                // STAR 31
                31 -> {
                    // CALLORVAR NL
                    var _r32: _GL_Plugin_Item? = null
                    _r32 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r32 != null) _index.element = _r32.nextIndex

                    // STAR 31
                    val _r31 = _memo.results.pop()
                    if (_r31 != null) {
                        _res31 += _r31.results
                        _label = 31
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i31, _index.element, _memo.input, _res31.filterNotNull(), true))
                    }

                    // AND shortcut 30
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 30
                        continue
                    }

                    // CALLORVAR ASSIGNMENT
                    var _r33: _GL_Plugin_Item? = null
                    _r33 = _MemoCall(_memo, "ASSIGNMENT", _index.element, ::ASSIGNMENT, null)
                    if (_r33 != null) _index.element = _r33.nextIndex

                    _label = 30
                }
                // AND 30
                30 -> {
                    val _r30_2 = _memo.results.pop()
                    val _r30_1 = _memo.results.pop()

                    if (_r30_1 != null && _r30_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i30, _index.element, _memo.input, (_r30_1.results + _r30_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i30
                    }

                    // AND shortcut 29
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 29
                        continue
                    }

                    // STAR 34
                    _start_i34 = _index.element
                    _label = 34
                }
                // STAR 34
                34 -> {
                    // CALLORVAR NL
                    var _r35: _GL_Plugin_Item? = null
                    _r35 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r35 != null) _index.element = _r35.nextIndex

                    // STAR 34
                    val _r34 = _memo.results.pop()
                    if (_r34 != null) {
                        _res34 += _r34.results
                        _label = 34
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i34, _index.element, _memo.input, _res34.filterNotNull(), true))
                    }

                    _label = 29
                }
                // AND 29
                29 -> {
                    val _r29_2 = _memo.results.pop()
                    val _r29_1 = _memo.results.pop()

                    if (_r29_1 != null && _r29_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i29, _index.element, _memo.input, (_r29_1.results + _r29_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i29
                    }

                    // AND shortcut 28
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 28
                        continue
                    }

                    // CALLORVAR shaderExpression
                    var _r36: _GL_Plugin_Item? = null
                    _r36 = _MemoCall(_memo, "shaderExpression", _index.element, ::shaderExpression, null)
                    if (_r36 != null) _index.element = _r36.nextIndex

                    _label = 28
                }
                // AND 28
                28 -> {
                    val _r28_2 = _memo.results.pop()
                    val _r28_1 = _memo.results.pop()

                    if (_r28_1 != null && _r28_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i28, _index.element, _memo.input, (_r28_1.results + _r28_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i28
                    }

                    // QUES 27
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _memo.results.push(_GL_Plugin_Item(_index.element, _memo.input))
                    }
                    // BIND expr
                    expr = _memo.results.peek()

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i1
                    }

                    // ACT 0
                    val _r0 = _memo.results.peek()
                    if (_r0 != null) {
                        _memo.results.pop()
                        _memo.results.push(_GL_Plugin_Item(_r0.startIndex, _r0.nextIndex, _memo.input, _Thunk({ GL_AST.VariableDeclaration(ann.l, vov.r, name.s, t.ns, expr.nr) }, _r0), true))
                    }

                    break
                }
            }
        }
    }


    open fun shaderAssignment(_memo: _GL_Plugin_Memo, __index: Int, _args: _GL_Plugin_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var name: _GL_Plugin_Item? = null
        var expr: _GL_Plugin_Item? = null

        // AND 1
        var _start_i1 = _index.element

        // AND 2
        var _start_i2 = _index.element

        // AND 3
        var _start_i3 = _index.element

        // AND 4
        var _start_i4 = _index.element

        // STAR 7
        var _start_i7 = _index.element
        val _inp7 = arrayListOf<Token?>()
        val _res7 = arrayListOf<AST.AstNode?>()

        // STAR 10
        var _start_i10 = _index.element
        val _inp10 = arrayListOf<Token?>()
        val _res10 = arrayListOf<AST.AstNode?>()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 1
                    _start_i1 = _index.element

                    // AND 2
                    _start_i2 = _index.element

                    // AND 3
                    _start_i3 = _index.element

                    // AND 4
                    _start_i4 = _index.element

                    // CALLORVAR simpleIdentifier
                    var _r6: _GL_Plugin_Item? = null
                    _r6 = _MemoCall(_memo, "simpleIdentifier", _index.element, ::simpleIdentifier, null)
                    if (_r6 != null) _index.element = _r6.nextIndex

                    // BIND name
                    name = _memo.results.peek()

                    // AND shortcut 4
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 4
                        continue
                    }

                    // STAR 7
                    _start_i7 = _index.element
                    _label = 7
                }
                // STAR 7
                7 -> {
                    // CALLORVAR NL
                    var _r8: _GL_Plugin_Item? = null
                    _r8 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r8 != null) _index.element = _r8.nextIndex

                    // STAR 7
                    val _r7 = _memo.results.pop()
                    if (_r7 != null) {
                        _res7 += _r7.results
                        _label = 7
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i7, _index.element, _memo.input, _res7.filterNotNull(), true))
                    }

                    _label = 4
                }
                // AND 4
                4 -> {
                    val _r4_2 = _memo.results.pop()
                    val _r4_1 = _memo.results.pop()

                    if (_r4_1 != null && _r4_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i4, _index.element, _memo.input, (_r4_1.results + _r4_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i4
                    }

                    // AND shortcut 3
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 3
                        continue
                    }

                    // CALLORVAR ASSIGNMENT
                    var _r9: _GL_Plugin_Item? = null
                    _r9 = _MemoCall(_memo, "ASSIGNMENT", _index.element, ::ASSIGNMENT, null)
                    if (_r9 != null) _index.element = _r9.nextIndex

                    _label = 3
                }
                // AND 3
                3 -> {
                    val _r3_2 = _memo.results.pop()
                    val _r3_1 = _memo.results.pop()

                    if (_r3_1 != null && _r3_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i3, _index.element, _memo.input, (_r3_1.results + _r3_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i3
                    }

                    // AND shortcut 2
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 2
                        continue
                    }

                    // STAR 10
                    _start_i10 = _index.element
                    _label = 10
                }
                // STAR 10
                10 -> {
                    // CALLORVAR NL
                    var _r11: _GL_Plugin_Item? = null
                    _r11 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r11 != null) _index.element = _r11.nextIndex

                    // STAR 10
                    val _r10 = _memo.results.pop()
                    if (_r10 != null) {
                        _res10 += _r10.results
                        _label = 10
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i10, _index.element, _memo.input, _res10.filterNotNull(), true))
                    }

                    _label = 2
                }
                // AND 2
                2 -> {
                    val _r2_2 = _memo.results.pop()
                    val _r2_1 = _memo.results.pop()

                    if (_r2_1 != null && _r2_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i2, _index.element, _memo.input, (_r2_1.results + _r2_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i2
                    }

                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // CALLORVAR shaderExpression
                    var _r13: _GL_Plugin_Item? = null
                    _r13 = _MemoCall(_memo, "shaderExpression", _index.element, ::shaderExpression, null)
                    if (_r13 != null) _index.element = _r13.nextIndex

                    // BIND expr
                    expr = _memo.results.peek()

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i1
                    }

                    // ACT 0
                    val _r0 = _memo.results.peek()
                    if (_r0 != null) {
                        _memo.results.pop()
                        _memo.results.push(_GL_Plugin_Item(_r0.startIndex, _r0.nextIndex, _memo.input, _Thunk({ GL_AST.Assignment(name.s, expr.r) }, _r0), true))
                    }

                    break
                }
            }
        }
    }


    open fun shaderExpression(_memo: _GL_Plugin_Memo, __index: Int, _args: _GL_Plugin_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // CALLORVAR shaderEqualityExpression
                    var _r0: _GL_Plugin_Item? = null
                    _r0 = _MemoCall(_memo, "shaderEqualityExpression", _index.element, ::shaderEqualityExpression, null)
                    if (_r0 != null) _index.element = _r0.nextIndex

                    break
                }
            }
        }
    }


    open fun shaderEqualityExpression(_memo: _GL_Plugin_Memo, __index: Int, _args: _GL_Plugin_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 1
        var _start_i1 = _index.element

        // STAR 3
        var _start_i3 = _index.element
        val _inp3 = arrayListOf<Token?>()
        val _res3 = arrayListOf<AST.AstNode?>()

        // AND 4
        var _start_i4 = _index.element

        // AND 5
        var _start_i5 = _index.element

        // AND 6
        var _start_i6 = _index.element

        // STAR 7
        var _start_i7 = _index.element
        val _inp7 = arrayListOf<Token?>()
        val _res7 = arrayListOf<AST.AstNode?>()

        // STAR 10
        var _start_i10 = _index.element
        val _inp10 = arrayListOf<Token?>()
        val _res10 = arrayListOf<AST.AstNode?>()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 1
                    _start_i1 = _index.element

                    // CALLORVAR shaderComparisonExpression
                    var _r2: _GL_Plugin_Item? = null
                    _r2 = _MemoCall(_memo, "shaderComparisonExpression", _index.element, ::shaderComparisonExpression, null)
                    if (_r2 != null) _index.element = _r2.nextIndex

                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // STAR 3
                    _start_i3 = _index.element
                    _label = 3
                }
                // STAR 3
                3 -> {
                    // AND 4
                    _start_i4 = _index.element

                    // AND 5
                    _start_i5 = _index.element

                    // AND 6
                    _start_i6 = _index.element

                    // STAR 7
                    _start_i7 = _index.element
                    _label = 7
                }
                // STAR 7
                7 -> {
                    // CALLORVAR NL
                    var _r8: _GL_Plugin_Item? = null
                    _r8 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r8 != null) _index.element = _r8.nextIndex

                    // STAR 7
                    val _r7 = _memo.results.pop()
                    if (_r7 != null) {
                        _res7 += _r7.results
                        _label = 7
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i7, _index.element, _memo.input, _res7.filterNotNull(), true))
                    }

                    // AND shortcut 6
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 6
                        continue
                    }

                    // CALLORVAR equalityOperator
                    var _r9: _GL_Plugin_Item? = null
                    _r9 = _MemoCall(_memo, "equalityOperator", _index.element, ::equalityOperator, null)
                    if (_r9 != null) _index.element = _r9.nextIndex

                    _label = 6
                }
                // AND 6
                6 -> {
                    val _r6_2 = _memo.results.pop()
                    val _r6_1 = _memo.results.pop()

                    if (_r6_1 != null && _r6_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i6, _index.element, _memo.input, (_r6_1.results + _r6_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i6
                    }

                    // AND shortcut 5
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 5
                        continue
                    }

                    // STAR 10
                    _start_i10 = _index.element
                    _label = 10
                }
                // STAR 10
                10 -> {
                    // CALLORVAR NL
                    var _r11: _GL_Plugin_Item? = null
                    _r11 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r11 != null) _index.element = _r11.nextIndex

                    // STAR 10
                    val _r10 = _memo.results.pop()
                    if (_r10 != null) {
                        _res10 += _r10.results
                        _label = 10
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i10, _index.element, _memo.input, _res10.filterNotNull(), true))
                    }

                    _label = 5
                }
                // AND 5
                5 -> {
                    val _r5_2 = _memo.results.pop()
                    val _r5_1 = _memo.results.pop()

                    if (_r5_1 != null && _r5_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i5, _index.element, _memo.input, (_r5_1.results + _r5_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i5
                    }

                    // AND shortcut 4
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 4
                        continue
                    }

                    // CALLORVAR shaderComparisonExpression
                    var _r12: _GL_Plugin_Item? = null
                    _r12 = _MemoCall(_memo, "shaderComparisonExpression", _index.element, ::shaderComparisonExpression, null)
                    if (_r12 != null) _index.element = _r12.nextIndex

                    _label = 4
                }
                // AND 4
                4 -> {
                    val _r4_2 = _memo.results.pop()
                    val _r4_1 = _memo.results.pop()

                    if (_r4_1 != null && _r4_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i4, _index.element, _memo.input, (_r4_1.results + _r4_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i4
                    }

                    // STAR 3
                    val _r3 = _memo.results.pop()
                    if (_r3 != null) {
                        _res3 += _r3.results
                        _label = 3
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i3, _index.element, _memo.input, _res3.filterNotNull(), true))
                    }

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i1
                    }

                    // ACT 0
                    val _r0 = _memo.results.peek()
                    if (_r0 != null) {
                        _memo.results.pop()
                        _memo.results.push(_GL_Plugin_Item(_r0.startIndex, _r0.nextIndex, _memo.input, _Thunk({ GL_AST.Equality(it.l) }, _r0), true))
                    }

                    break
                }
            }
        }
    }


    open fun shaderComparisonExpression(_memo: _GL_Plugin_Memo, __index: Int, _args: _GL_Plugin_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 1
        var _start_i1 = _index.element

        // STAR 3
        var _start_i3 = _index.element
        val _inp3 = arrayListOf<Token?>()
        val _res3 = arrayListOf<AST.AstNode?>()

        // AND 4
        var _start_i4 = _index.element

        // AND 5
        var _start_i5 = _index.element

        // AND 6
        var _start_i6 = _index.element

        // STAR 7
        var _start_i7 = _index.element
        val _inp7 = arrayListOf<Token?>()
        val _res7 = arrayListOf<AST.AstNode?>()

        // STAR 10
        var _start_i10 = _index.element
        val _inp10 = arrayListOf<Token?>()
        val _res10 = arrayListOf<AST.AstNode?>()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 1
                    _start_i1 = _index.element

                    // CALLORVAR shaderAdditiveExpression
                    var _r2: _GL_Plugin_Item? = null
                    _r2 = _MemoCall(_memo, "shaderAdditiveExpression", _index.element, ::shaderAdditiveExpression, null)
                    if (_r2 != null) _index.element = _r2.nextIndex

                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // STAR 3
                    _start_i3 = _index.element
                    _label = 3
                }
                // STAR 3
                3 -> {
                    // AND 4
                    _start_i4 = _index.element

                    // AND 5
                    _start_i5 = _index.element

                    // AND 6
                    _start_i6 = _index.element

                    // STAR 7
                    _start_i7 = _index.element
                    _label = 7
                }
                // STAR 7
                7 -> {
                    // CALLORVAR NL
                    var _r8: _GL_Plugin_Item? = null
                    _r8 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r8 != null) _index.element = _r8.nextIndex

                    // STAR 7
                    val _r7 = _memo.results.pop()
                    if (_r7 != null) {
                        _res7 += _r7.results
                        _label = 7
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i7, _index.element, _memo.input, _res7.filterNotNull(), true))
                    }

                    // AND shortcut 6
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 6
                        continue
                    }

                    // CALLORVAR comparisonOperator
                    var _r9: _GL_Plugin_Item? = null
                    _r9 = _MemoCall(_memo, "comparisonOperator", _index.element, ::comparisonOperator, null)
                    if (_r9 != null) _index.element = _r9.nextIndex

                    _label = 6
                }
                // AND 6
                6 -> {
                    val _r6_2 = _memo.results.pop()
                    val _r6_1 = _memo.results.pop()

                    if (_r6_1 != null && _r6_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i6, _index.element, _memo.input, (_r6_1.results + _r6_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i6
                    }

                    // AND shortcut 5
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 5
                        continue
                    }

                    // STAR 10
                    _start_i10 = _index.element
                    _label = 10
                }
                // STAR 10
                10 -> {
                    // CALLORVAR NL
                    var _r11: _GL_Plugin_Item? = null
                    _r11 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r11 != null) _index.element = _r11.nextIndex

                    // STAR 10
                    val _r10 = _memo.results.pop()
                    if (_r10 != null) {
                        _res10 += _r10.results
                        _label = 10
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i10, _index.element, _memo.input, _res10.filterNotNull(), true))
                    }

                    _label = 5
                }
                // AND 5
                5 -> {
                    val _r5_2 = _memo.results.pop()
                    val _r5_1 = _memo.results.pop()

                    if (_r5_1 != null && _r5_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i5, _index.element, _memo.input, (_r5_1.results + _r5_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i5
                    }

                    // AND shortcut 4
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 4
                        continue
                    }

                    // CALLORVAR shaderAdditiveExpression
                    var _r12: _GL_Plugin_Item? = null
                    _r12 = _MemoCall(_memo, "shaderAdditiveExpression", _index.element, ::shaderAdditiveExpression, null)
                    if (_r12 != null) _index.element = _r12.nextIndex

                    _label = 4
                }
                // AND 4
                4 -> {
                    val _r4_2 = _memo.results.pop()
                    val _r4_1 = _memo.results.pop()

                    if (_r4_1 != null && _r4_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i4, _index.element, _memo.input, (_r4_1.results + _r4_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i4
                    }

                    // STAR 3
                    val _r3 = _memo.results.pop()
                    if (_r3 != null) {
                        _res3 += _r3.results
                        _label = 3
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i3, _index.element, _memo.input, _res3.filterNotNull(), true))
                    }

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i1
                    }

                    // ACT 0
                    val _r0 = _memo.results.peek()
                    if (_r0 != null) {
                        _memo.results.pop()
                        _memo.results.push(_GL_Plugin_Item(_r0.startIndex, _r0.nextIndex, _memo.input, _Thunk({ GL_AST.Comparison(it.l) }, _r0), true))
                    }

                    break
                }
            }
        }
    }


    open fun shaderAdditiveExpression(_memo: _GL_Plugin_Memo, __index: Int, _args: _GL_Plugin_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 1
        var _start_i1 = _index.element

        // STAR 3
        var _start_i3 = _index.element
        val _inp3 = arrayListOf<Token?>()
        val _res3 = arrayListOf<AST.AstNode?>()

        // AND 4
        var _start_i4 = _index.element

        // AND 5
        var _start_i5 = _index.element

        // AND 6
        var _start_i6 = _index.element

        // STAR 7
        var _start_i7 = _index.element
        val _inp7 = arrayListOf<Token?>()
        val _res7 = arrayListOf<AST.AstNode?>()

        // STAR 10
        var _start_i10 = _index.element
        val _inp10 = arrayListOf<Token?>()
        val _res10 = arrayListOf<AST.AstNode?>()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 1
                    _start_i1 = _index.element

                    // CALLORVAR shaderMultiplicativeExpression
                    var _r2: _GL_Plugin_Item? = null
                    _r2 = _MemoCall(_memo, "shaderMultiplicativeExpression", _index.element, ::shaderMultiplicativeExpression, null)
                    if (_r2 != null) _index.element = _r2.nextIndex

                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // STAR 3
                    _start_i3 = _index.element
                    _label = 3
                }
                // STAR 3
                3 -> {
                    // AND 4
                    _start_i4 = _index.element

                    // AND 5
                    _start_i5 = _index.element

                    // AND 6
                    _start_i6 = _index.element

                    // STAR 7
                    _start_i7 = _index.element
                    _label = 7
                }
                // STAR 7
                7 -> {
                    // CALLORVAR NL
                    var _r8: _GL_Plugin_Item? = null
                    _r8 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r8 != null) _index.element = _r8.nextIndex

                    // STAR 7
                    val _r7 = _memo.results.pop()
                    if (_r7 != null) {
                        _res7 += _r7.results
                        _label = 7
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i7, _index.element, _memo.input, _res7.filterNotNull(), true))
                    }

                    // AND shortcut 6
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 6
                        continue
                    }

                    // CALLORVAR additiveOperator
                    var _r9: _GL_Plugin_Item? = null
                    _r9 = _MemoCall(_memo, "additiveOperator", _index.element, ::additiveOperator, null)
                    if (_r9 != null) _index.element = _r9.nextIndex

                    _label = 6
                }
                // AND 6
                6 -> {
                    val _r6_2 = _memo.results.pop()
                    val _r6_1 = _memo.results.pop()

                    if (_r6_1 != null && _r6_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i6, _index.element, _memo.input, (_r6_1.results + _r6_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i6
                    }

                    // AND shortcut 5
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 5
                        continue
                    }

                    // STAR 10
                    _start_i10 = _index.element
                    _label = 10
                }
                // STAR 10
                10 -> {
                    // CALLORVAR NL
                    var _r11: _GL_Plugin_Item? = null
                    _r11 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r11 != null) _index.element = _r11.nextIndex

                    // STAR 10
                    val _r10 = _memo.results.pop()
                    if (_r10 != null) {
                        _res10 += _r10.results
                        _label = 10
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i10, _index.element, _memo.input, _res10.filterNotNull(), true))
                    }

                    _label = 5
                }
                // AND 5
                5 -> {
                    val _r5_2 = _memo.results.pop()
                    val _r5_1 = _memo.results.pop()

                    if (_r5_1 != null && _r5_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i5, _index.element, _memo.input, (_r5_1.results + _r5_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i5
                    }

                    // AND shortcut 4
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 4
                        continue
                    }

                    // CALLORVAR shaderMultiplicativeExpression
                    var _r12: _GL_Plugin_Item? = null
                    _r12 = _MemoCall(_memo, "shaderMultiplicativeExpression", _index.element, ::shaderMultiplicativeExpression, null)
                    if (_r12 != null) _index.element = _r12.nextIndex

                    _label = 4
                }
                // AND 4
                4 -> {
                    val _r4_2 = _memo.results.pop()
                    val _r4_1 = _memo.results.pop()

                    if (_r4_1 != null && _r4_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i4, _index.element, _memo.input, (_r4_1.results + _r4_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i4
                    }

                    // STAR 3
                    val _r3 = _memo.results.pop()
                    if (_r3 != null) {
                        _res3 += _r3.results
                        _label = 3
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i3, _index.element, _memo.input, _res3.filterNotNull(), true))
                    }

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i1
                    }

                    // ACT 0
                    val _r0 = _memo.results.peek()
                    if (_r0 != null) {
                        _memo.results.pop()
                        _memo.results.push(_GL_Plugin_Item(_r0.startIndex, _r0.nextIndex, _memo.input, _Thunk({ GL_AST.Additive(it.l) }, _r0), true))
                    }

                    break
                }
            }
        }
    }


    open fun shaderMultiplicativeExpression(_memo: _GL_Plugin_Memo, __index: Int, _args: _GL_Plugin_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 1
        var _start_i1 = _index.element

        // STAR 3
        var _start_i3 = _index.element
        val _inp3 = arrayListOf<Token?>()
        val _res3 = arrayListOf<AST.AstNode?>()

        // AND 4
        var _start_i4 = _index.element

        // AND 5
        var _start_i5 = _index.element

        // AND 6
        var _start_i6 = _index.element

        // STAR 7
        var _start_i7 = _index.element
        val _inp7 = arrayListOf<Token?>()
        val _res7 = arrayListOf<AST.AstNode?>()

        // STAR 10
        var _start_i10 = _index.element
        val _inp10 = arrayListOf<Token?>()
        val _res10 = arrayListOf<AST.AstNode?>()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 1
                    _start_i1 = _index.element

                    // CALLORVAR shaderPrimaryExpression
                    var _r2: _GL_Plugin_Item? = null
                    _r2 = _MemoCall(_memo, "shaderPrimaryExpression", _index.element, ::shaderPrimaryExpression, null)
                    if (_r2 != null) _index.element = _r2.nextIndex

                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // STAR 3
                    _start_i3 = _index.element
                    _label = 3
                }
                // STAR 3
                3 -> {
                    // AND 4
                    _start_i4 = _index.element

                    // AND 5
                    _start_i5 = _index.element

                    // AND 6
                    _start_i6 = _index.element

                    // STAR 7
                    _start_i7 = _index.element
                    _label = 7
                }
                // STAR 7
                7 -> {
                    // CALLORVAR NL
                    var _r8: _GL_Plugin_Item? = null
                    _r8 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r8 != null) _index.element = _r8.nextIndex

                    // STAR 7
                    val _r7 = _memo.results.pop()
                    if (_r7 != null) {
                        _res7 += _r7.results
                        _label = 7
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i7, _index.element, _memo.input, _res7.filterNotNull(), true))
                    }

                    // AND shortcut 6
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 6
                        continue
                    }

                    // CALLORVAR multiplicativeOperator
                    var _r9: _GL_Plugin_Item? = null
                    _r9 = _MemoCall(_memo, "multiplicativeOperator", _index.element, ::multiplicativeOperator, null)
                    if (_r9 != null) _index.element = _r9.nextIndex

                    _label = 6
                }
                // AND 6
                6 -> {
                    val _r6_2 = _memo.results.pop()
                    val _r6_1 = _memo.results.pop()

                    if (_r6_1 != null && _r6_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i6, _index.element, _memo.input, (_r6_1.results + _r6_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i6
                    }

                    // AND shortcut 5
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 5
                        continue
                    }

                    // STAR 10
                    _start_i10 = _index.element
                    _label = 10
                }
                // STAR 10
                10 -> {
                    // CALLORVAR NL
                    var _r11: _GL_Plugin_Item? = null
                    _r11 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r11 != null) _index.element = _r11.nextIndex

                    // STAR 10
                    val _r10 = _memo.results.pop()
                    if (_r10 != null) {
                        _res10 += _r10.results
                        _label = 10
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i10, _index.element, _memo.input, _res10.filterNotNull(), true))
                    }

                    _label = 5
                }
                // AND 5
                5 -> {
                    val _r5_2 = _memo.results.pop()
                    val _r5_1 = _memo.results.pop()

                    if (_r5_1 != null && _r5_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i5, _index.element, _memo.input, (_r5_1.results + _r5_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i5
                    }

                    // AND shortcut 4
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 4
                        continue
                    }

                    // CALLORVAR shaderPrimaryExpression
                    var _r12: _GL_Plugin_Item? = null
                    _r12 = _MemoCall(_memo, "shaderPrimaryExpression", _index.element, ::shaderPrimaryExpression, null)
                    if (_r12 != null) _index.element = _r12.nextIndex

                    _label = 4
                }
                // AND 4
                4 -> {
                    val _r4_2 = _memo.results.pop()
                    val _r4_1 = _memo.results.pop()

                    if (_r4_1 != null && _r4_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i4, _index.element, _memo.input, (_r4_1.results + _r4_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i4
                    }

                    // STAR 3
                    val _r3 = _memo.results.pop()
                    if (_r3 != null) {
                        _res3 += _r3.results
                        _label = 3
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i3, _index.element, _memo.input, _res3.filterNotNull(), true))
                    }

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i1
                    }

                    // ACT 0
                    val _r0 = _memo.results.peek()
                    if (_r0 != null) {
                        _memo.results.pop()
                        _memo.results.push(_GL_Plugin_Item(_r0.startIndex, _r0.nextIndex, _memo.input, _Thunk({ GL_AST.Multiplicative(it.l) }, _r0), true))
                    }

                    break
                }
            }
        }
    }


    open fun shaderPrimaryExpression(_memo: _GL_Plugin_Memo, __index: Int, _args: _GL_Plugin_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // OR 0
        var _start_i0 = _index.element

        // OR 1
        var _start_i1 = _index.element

        // OR 2
        var _start_i2 = _index.element

        // OR 3
        var _start_i3 = _index.element

        // OR 4
        var _start_i4 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // OR 0
                    _start_i0 = _index.element

                    // OR 1
                    _start_i1 = _index.element

                    // OR 2
                    _start_i2 = _index.element

                    // OR 3
                    _start_i3 = _index.element

                    // OR 4
                    _start_i4 = _index.element

                    // CALLORVAR shaderCallExpression
                    var _r5: _GL_Plugin_Item? = null
                    _r5 = _MemoCall(_memo, "shaderCallExpression", _index.element, ::shaderCallExpression, null)
                    if (_r5 != null) _index.element = _r5.nextIndex

                    // OR shortcut 4
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i4
                    } else {
                        _label = 4
                        continue
                    }

                    // CALLORVAR shaderDotAccessExpression
                    var _r6: _GL_Plugin_Item? = null
                    _r6 = _MemoCall(_memo, "shaderDotAccessExpression", _index.element, ::shaderDotAccessExpression, null)
                    if (_r6 != null) _index.element = _r6.nextIndex

                    _label = 4
                }
                // OR 4
                4 -> {
                    // OR shortcut 3
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i3
                    } else {
                        _label = 3
                        continue
                    }

                    // CALLORVAR shaderIfExpression
                    var _r7: _GL_Plugin_Item? = null
                    _r7 = _MemoCall(_memo, "shaderIfExpression", _index.element, ::shaderIfExpression, null)
                    if (_r7 != null) _index.element = _r7.nextIndex

                    _label = 3
                }
                // OR 3
                3 -> {
                    // OR shortcut 2
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i2
                    } else {
                        _label = 2
                        continue
                    }

                    // CALLORVAR shaderFloatLiteral
                    var _r8: _GL_Plugin_Item? = null
                    _r8 = _MemoCall(_memo, "shaderFloatLiteral", _index.element, ::shaderFloatLiteral, null)
                    if (_r8 != null) _index.element = _r8.nextIndex

                    _label = 2
                }
                // OR 2
                2 -> {
                    // OR shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i1
                    } else {
                        _label = 1
                        continue
                    }

                    // CALLORVAR shaderParenthesizedExpression
                    var _r9: _GL_Plugin_Item? = null
                    _r9 = _MemoCall(_memo, "shaderParenthesizedExpression", _index.element, ::shaderParenthesizedExpression, null)
                    if (_r9 != null) _index.element = _r9.nextIndex

                    _label = 1
                }
                // OR 1
                1 -> {
                    // OR shortcut 0
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i0
                    } else {
                        _label = 0
                        continue
                    }

                    // CALLORVAR simpleIdentifier
                    var _r11: _GL_Plugin_Item? = null
                    _r11 = _MemoCall(_memo, "simpleIdentifier", _index.element, ::simpleIdentifier, null)
                    if (_r11 != null) _index.element = _r11.nextIndex

                    // ACT 10
                    val _r10 = _memo.results.peek()
                    if (_r10 != null) {
                        _memo.results.pop()
                        _memo.results.push(_GL_Plugin_Item(_r10.startIndex, _r10.nextIndex, _memo.input, _Thunk({ GL_AST.NameExpression(it.s) }, _r10), true))
                    }

                    _label = 0
                }
                // OR 0
                0 -> {
                    break
                }
            }
        }
    }


    open fun shaderCallExpression(_memo: _GL_Plugin_Memo, __index: Int, _args: _GL_Plugin_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var name: _GL_Plugin_Item? = null
        var params: _GL_Plugin_Item? = null

        // AND 1
        var _start_i1 = _index.element

        // AND 2
        var _start_i2 = _index.element

        // AND 3
        var _start_i3 = _index.element

        // AND 4
        var _start_i4 = _index.element

        // AND 5
        var _start_i5 = _index.element

        // STAR 8
        var _start_i8 = _index.element
        val _inp8 = arrayListOf<Token?>()
        val _res8 = arrayListOf<AST.AstNode?>()

        // STAR 12
        var _start_i12 = _index.element
        val _inp12 = arrayListOf<Token?>()
        val _res12 = arrayListOf<AST.AstNode?>()

        // AND 13
        var _start_i13 = _index.element

        // AND 14
        var _start_i14 = _index.element

        // AND 16
        var _start_i16 = _index.element

        // AND 17
        var _start_i17 = _index.element

        // STAR 18
        var _start_i18 = _index.element
        val _inp18 = arrayListOf<Token?>()
        val _res18 = arrayListOf<AST.AstNode?>()

        // AND 23
        var _start_i23 = _index.element

        // STAR 24
        var _start_i24 = _index.element
        val _inp24 = arrayListOf<Token?>()
        val _res24 = arrayListOf<AST.AstNode?>()

        // STAR 27
        var _start_i27 = _index.element
        val _inp27 = arrayListOf<Token?>()
        val _res27 = arrayListOf<AST.AstNode?>()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 1
                    _start_i1 = _index.element

                    // AND 2
                    _start_i2 = _index.element

                    // AND 3
                    _start_i3 = _index.element

                    // AND 4
                    _start_i4 = _index.element

                    // AND 5
                    _start_i5 = _index.element

                    // CALLORVAR simpleIdentifier
                    var _r7: _GL_Plugin_Item? = null
                    _r7 = _MemoCall(_memo, "simpleIdentifier", _index.element, ::simpleIdentifier, null)
                    if (_r7 != null) _index.element = _r7.nextIndex

                    // BIND name
                    name = _memo.results.peek()

                    // AND shortcut 5
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 5
                        continue
                    }

                    // STAR 8
                    _start_i8 = _index.element
                    _label = 8
                }
                // STAR 8
                8 -> {
                    // CALLORVAR NL
                    var _r9: _GL_Plugin_Item? = null
                    _r9 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r9 != null) _index.element = _r9.nextIndex

                    // STAR 8
                    val _r8 = _memo.results.pop()
                    if (_r8 != null) {
                        _res8 += _r8.results
                        _label = 8
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i8, _index.element, _memo.input, _res8.filterNotNull(), true))
                    }

                    _label = 5
                }
                // AND 5
                5 -> {
                    val _r5_2 = _memo.results.pop()
                    val _r5_1 = _memo.results.pop()

                    if (_r5_1 != null && _r5_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i5, _index.element, _memo.input, (_r5_1.results + _r5_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i5
                    }

                    // AND shortcut 4
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 4
                        continue
                    }

                    // CALLORVAR LPAREN
                    var _r10: _GL_Plugin_Item? = null
                    _r10 = _MemoCall(_memo, "LPAREN", _index.element, ::LPAREN, null)
                    if (_r10 != null) _index.element = _r10.nextIndex

                    _label = 4
                }
                // AND 4
                4 -> {
                    val _r4_2 = _memo.results.pop()
                    val _r4_1 = _memo.results.pop()

                    if (_r4_1 != null && _r4_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i4, _index.element, _memo.input, (_r4_1.results + _r4_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i4
                    }

                    // AND shortcut 3
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 3
                        continue
                    }

                    // STAR 12
                    _start_i12 = _index.element
                    _label = 12
                }
                // STAR 12
                12 -> {
                    // AND 13
                    _start_i13 = _index.element

                    // AND 14
                    _start_i14 = _index.element

                    // CALLORVAR shaderExpression
                    var _r15: _GL_Plugin_Item? = null
                    _r15 = _MemoCall(_memo, "shaderExpression", _index.element, ::shaderExpression, null)
                    if (_r15 != null) _index.element = _r15.nextIndex

                    // AND shortcut 14
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 14
                        continue
                    }

                    // AND 16
                    _start_i16 = _index.element

                    // AND 17
                    _start_i17 = _index.element

                    // STAR 18
                    _start_i18 = _index.element
                    _label = 18
                }
                // STAR 18
                18 -> {
                    // CALLORVAR NL
                    var _r19: _GL_Plugin_Item? = null
                    _r19 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r19 != null) _index.element = _r19.nextIndex

                    // STAR 18
                    val _r18 = _memo.results.pop()
                    if (_r18 != null) {
                        _res18 += _r18.results
                        _label = 18
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i18, _index.element, _memo.input, _res18.filterNotNull(), true))
                    }

                    // AND shortcut 17
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 17
                        continue
                    }

                    // CALLORVAR COMMA
                    var _r20: _GL_Plugin_Item? = null
                    _r20 = _MemoCall(_memo, "COMMA", _index.element, ::COMMA, null)
                    if (_r20 != null) _index.element = _r20.nextIndex

                    _label = 17
                }
                // AND 17
                17 -> {
                    val _r17_2 = _memo.results.pop()
                    val _r17_1 = _memo.results.pop()

                    if (_r17_1 != null && _r17_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i17, _index.element, _memo.input, (_r17_1.results + _r17_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i17
                    }

                    // AND shortcut 16
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 16
                        continue
                    }

                    // CALLORVAR shaderExpression
                    var _r21: _GL_Plugin_Item? = null
                    _r21 = _MemoCall(_memo, "shaderExpression", _index.element, ::shaderExpression, null)
                    if (_r21 != null) _index.element = _r21.nextIndex

                    _label = 16
                }
                // AND 16
                16 -> {
                    val _r16_2 = _memo.results.pop()
                    val _r16_1 = _memo.results.pop()

                    if (_r16_1 != null && _r16_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i16, _index.element, _memo.input, (_r16_1.results + _r16_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i16
                    }

                    _label = 14
                }
                // AND 14
                14 -> {
                    val _r14_2 = _memo.results.pop()
                    val _r14_1 = _memo.results.pop()

                    if (_r14_1 != null && _r14_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i14, _index.element, _memo.input, (_r14_1.results + _r14_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i14
                    }

                    // AND shortcut 13
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 13
                        continue
                    }

                    // AND 23
                    _start_i23 = _index.element

                    // STAR 24
                    _start_i24 = _index.element
                    _label = 24
                }
                // STAR 24
                24 -> {
                    // CALLORVAR NL
                    var _r25: _GL_Plugin_Item? = null
                    _r25 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r25 != null) _index.element = _r25.nextIndex

                    // STAR 24
                    val _r24 = _memo.results.pop()
                    if (_r24 != null) {
                        _res24 += _r24.results
                        _label = 24
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i24, _index.element, _memo.input, _res24.filterNotNull(), true))
                    }

                    // AND shortcut 23
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 23
                        continue
                    }

                    // CALLORVAR COMMA
                    var _r26: _GL_Plugin_Item? = null
                    _r26 = _MemoCall(_memo, "COMMA", _index.element, ::COMMA, null)
                    if (_r26 != null) _index.element = _r26.nextIndex

                    _label = 23
                }
                // AND 23
                23 -> {
                    val _r23_2 = _memo.results.pop()
                    val _r23_1 = _memo.results.pop()

                    if (_r23_1 != null && _r23_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i23, _index.element, _memo.input, (_r23_1.results + _r23_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i23
                    }

                    // QUES 22
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _memo.results.push(_GL_Plugin_Item(_index.element, _memo.input))
                    }
                    _label = 13
                }
                // AND 13
                13 -> {
                    val _r13_2 = _memo.results.pop()
                    val _r13_1 = _memo.results.pop()

                    if (_r13_1 != null && _r13_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i13, _index.element, _memo.input, (_r13_1.results + _r13_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i13
                    }

                    // STAR 12
                    val _r12 = _memo.results.pop()
                    if (_r12 != null) {
                        _res12 += _r12.results
                        _label = 12
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i12, _index.element, _memo.input, _res12.filterNotNull(), true))
                    }

                    // BIND params
                    params = _memo.results.peek()

                    _label = 3
                }
                // AND 3
                3 -> {
                    val _r3_2 = _memo.results.pop()
                    val _r3_1 = _memo.results.pop()

                    if (_r3_1 != null && _r3_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i3, _index.element, _memo.input, (_r3_1.results + _r3_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i3
                    }

                    // AND shortcut 2
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 2
                        continue
                    }

                    // STAR 27
                    _start_i27 = _index.element
                    _label = 27
                }
                // STAR 27
                27 -> {
                    // CALLORVAR NL
                    var _r28: _GL_Plugin_Item? = null
                    _r28 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r28 != null) _index.element = _r28.nextIndex

                    // STAR 27
                    val _r27 = _memo.results.pop()
                    if (_r27 != null) {
                        _res27 += _r27.results
                        _label = 27
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i27, _index.element, _memo.input, _res27.filterNotNull(), true))
                    }

                    _label = 2
                }
                // AND 2
                2 -> {
                    val _r2_2 = _memo.results.pop()
                    val _r2_1 = _memo.results.pop()

                    if (_r2_1 != null && _r2_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i2, _index.element, _memo.input, (_r2_1.results + _r2_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i2
                    }

                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // CALLORVAR RPAREN
                    var _r29: _GL_Plugin_Item? = null
                    _r29 = _MemoCall(_memo, "RPAREN", _index.element, ::RPAREN, null)
                    if (_r29 != null) _index.element = _r29.nextIndex

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i1
                    }

                    // ACT 0
                    val _r0 = _memo.results.peek()
                    if (_r0 != null) {
                        _memo.results.pop()
                        _memo.results.push(_GL_Plugin_Item(_r0.startIndex, _r0.nextIndex, _memo.input, _Thunk({ GL_AST.CallExpression(name.s, params.l) }, _r0), true))
                    }

                    break
                }
            }
        }
    }


    open fun shaderDotAccessExpression(_memo: _GL_Plugin_Memo, __index: Int, _args: _GL_Plugin_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var name: _GL_Plugin_Item? = null
        var access: _GL_Plugin_Item? = null

        // AND 1
        var _start_i1 = _index.element

        // PLUS 5
        var _start_i5 = _index.element
        val _inp5 = arrayListOf<Token?>()
        val _res5 = arrayListOf<AST.AstNode?>()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 1
                    _start_i1 = _index.element

                    // CALLORVAR simpleIdentifier
                    var _r3: _GL_Plugin_Item? = null
                    _r3 = _MemoCall(_memo, "simpleIdentifier", _index.element, ::simpleIdentifier, null)
                    if (_r3 != null) _index.element = _r3.nextIndex

                    // BIND name
                    name = _memo.results.peek()

                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // PLUS 5
                    _start_i5 = _index.element
                    _label = 5
                }
                // PLUS 5
                5 -> {
                    // CALLORVAR shaderDotAccessExpressionSuffix
                    var _r6: _GL_Plugin_Item? = null
                    _r6 = _MemoCall(_memo, "shaderDotAccessExpressionSuffix", _index.element, ::shaderDotAccessExpressionSuffix, null)
                    if (_r6 != null) _index.element = _r6.nextIndex

                    // PLUS 5
                    val _r5 = _memo.results.pop()
                    if (_r5 != null) {
                        _res5 += _r5.results
                        _label = 5
                        continue
                    } else {
                        if (_index.element > _start_i5) {
                            _memo.results.push(_GL_Plugin_Item(_start_i5, _index.element, _memo.input, _res5.filterNotNull(), true))
                        } else {
                            _memo.results.push(null)
                        }
                    }

                    // BIND access
                    access = _memo.results.peek()

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i1
                    }

                    // ACT 0
                    val _r0 = _memo.results.peek()
                    if (_r0 != null) {
                        _memo.results.pop()
                        _memo.results.push(_GL_Plugin_Item(_r0.startIndex, _r0.nextIndex, _memo.input, _Thunk({ GL_AST.DotAccessExpression(name.s, access.l) }, _r0), true))
                    }

                    break
                }
            }
        }
    }


    open fun shaderDotAccessExpressionSuffix(_memo: _GL_Plugin_Memo, __index: Int, _args: _GL_Plugin_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var s: _GL_Plugin_Item? = null

        // AND 1
        var _start_i1 = _index.element

        // AND 2
        var _start_i2 = _index.element

        // AND 3
        var _start_i3 = _index.element

        // STAR 4
        var _start_i4 = _index.element
        val _inp4 = arrayListOf<Token?>()
        val _res4 = arrayListOf<AST.AstNode?>()

        // STAR 7
        var _start_i7 = _index.element
        val _inp7 = arrayListOf<Token?>()
        val _res7 = arrayListOf<AST.AstNode?>()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 1
                    _start_i1 = _index.element

                    // AND 2
                    _start_i2 = _index.element

                    // AND 3
                    _start_i3 = _index.element

                    // STAR 4
                    _start_i4 = _index.element
                    _label = 4
                }
                // STAR 4
                4 -> {
                    // CALLORVAR NL
                    var _r5: _GL_Plugin_Item? = null
                    _r5 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r5 != null) _index.element = _r5.nextIndex

                    // STAR 4
                    val _r4 = _memo.results.pop()
                    if (_r4 != null) {
                        _res4 += _r4.results
                        _label = 4
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i4, _index.element, _memo.input, _res4.filterNotNull(), true))
                    }

                    // AND shortcut 3
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 3
                        continue
                    }

                    // CALLORVAR DOT
                    var _r6: _GL_Plugin_Item? = null
                    _r6 = _MemoCall(_memo, "DOT", _index.element, ::DOT, null)
                    if (_r6 != null) _index.element = _r6.nextIndex

                    _label = 3
                }
                // AND 3
                3 -> {
                    val _r3_2 = _memo.results.pop()
                    val _r3_1 = _memo.results.pop()

                    if (_r3_1 != null && _r3_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i3, _index.element, _memo.input, (_r3_1.results + _r3_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i3
                    }

                    // AND shortcut 2
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 2
                        continue
                    }

                    // STAR 7
                    _start_i7 = _index.element
                    _label = 7
                }
                // STAR 7
                7 -> {
                    // CALLORVAR NL
                    var _r8: _GL_Plugin_Item? = null
                    _r8 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r8 != null) _index.element = _r8.nextIndex

                    // STAR 7
                    val _r7 = _memo.results.pop()
                    if (_r7 != null) {
                        _res7 += _r7.results
                        _label = 7
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i7, _index.element, _memo.input, _res7.filterNotNull(), true))
                    }

                    _label = 2
                }
                // AND 2
                2 -> {
                    val _r2_2 = _memo.results.pop()
                    val _r2_1 = _memo.results.pop()

                    if (_r2_1 != null && _r2_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i2, _index.element, _memo.input, (_r2_1.results + _r2_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i2
                    }

                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // CALLORVAR simpleIdentifier
                    var _r10: _GL_Plugin_Item? = null
                    _r10 = _MemoCall(_memo, "simpleIdentifier", _index.element, ::simpleIdentifier, null)
                    if (_r10 != null) _index.element = _r10.nextIndex

                    // BIND s
                    s = _memo.results.peek()

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i1
                    }

                    // ACT 0
                    val _r0 = _memo.results.peek()
                    if (_r0 != null) {
                        _memo.results.pop()
                        _memo.results.push(_GL_Plugin_Item(_r0.startIndex, _r0.nextIndex, _memo.input, _Thunk({ GL_AST.NameExpression(s.s) }, _r0), true))
                    }

                    break
                }
            }
        }
    }


    open fun shaderIfExpression(_memo: _GL_Plugin_Memo, __index: Int, _args: _GL_Plugin_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var expr: _GL_Plugin_Item? = null
        var thenExpr: _GL_Plugin_Item? = null
        var elseExpr: _GL_Plugin_Item? = null

        // AND 1
        var _start_i1 = _index.element

        // AND 2
        var _start_i2 = _index.element

        // AND 3
        var _start_i3 = _index.element

        // AND 4
        var _start_i4 = _index.element

        // AND 5
        var _start_i5 = _index.element

        // AND 6
        var _start_i6 = _index.element

        // AND 7
        var _start_i7 = _index.element

        // AND 8
        var _start_i8 = _index.element

        // AND 9
        var _start_i9 = _index.element

        // AND 10
        var _start_i10 = _index.element

        // AND 11
        var _start_i11 = _index.element

        // AND 12
        var _start_i12 = _index.element

        // STAR 14
        var _start_i14 = _index.element
        val _inp14 = arrayListOf<Token?>()
        val _res14 = arrayListOf<AST.AstNode?>()

        // STAR 17
        var _start_i17 = _index.element
        val _inp17 = arrayListOf<Token?>()
        val _res17 = arrayListOf<AST.AstNode?>()

        // STAR 21
        var _start_i21 = _index.element
        val _inp21 = arrayListOf<Token?>()
        val _res21 = arrayListOf<AST.AstNode?>()

        // STAR 24
        var _start_i24 = _index.element
        val _inp24 = arrayListOf<Token?>()
        val _res24 = arrayListOf<AST.AstNode?>()

        // STAR 28
        var _start_i28 = _index.element
        val _inp28 = arrayListOf<Token?>()
        val _res28 = arrayListOf<AST.AstNode?>()

        // STAR 31
        var _start_i31 = _index.element
        val _inp31 = arrayListOf<Token?>()
        val _res31 = arrayListOf<AST.AstNode?>()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 1
                    _start_i1 = _index.element

                    // AND 2
                    _start_i2 = _index.element

                    // AND 3
                    _start_i3 = _index.element

                    // AND 4
                    _start_i4 = _index.element

                    // AND 5
                    _start_i5 = _index.element

                    // AND 6
                    _start_i6 = _index.element

                    // AND 7
                    _start_i7 = _index.element

                    // AND 8
                    _start_i8 = _index.element

                    // AND 9
                    _start_i9 = _index.element

                    // AND 10
                    _start_i10 = _index.element

                    // AND 11
                    _start_i11 = _index.element

                    // AND 12
                    _start_i12 = _index.element

                    // CALLORVAR IF
                    var _r13: _GL_Plugin_Item? = null
                    _r13 = _MemoCall(_memo, "IF", _index.element, ::IF, null)
                    if (_r13 != null) _index.element = _r13.nextIndex

                    // AND shortcut 12
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 12
                        continue
                    }

                    // STAR 14
                    _start_i14 = _index.element
                    _label = 14
                }
                // STAR 14
                14 -> {
                    // CALLORVAR NL
                    var _r15: _GL_Plugin_Item? = null
                    _r15 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r15 != null) _index.element = _r15.nextIndex

                    // STAR 14
                    val _r14 = _memo.results.pop()
                    if (_r14 != null) {
                        _res14 += _r14.results
                        _label = 14
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i14, _index.element, _memo.input, _res14.filterNotNull(), true))
                    }

                    _label = 12
                }
                // AND 12
                12 -> {
                    val _r12_2 = _memo.results.pop()
                    val _r12_1 = _memo.results.pop()

                    if (_r12_1 != null && _r12_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i12, _index.element, _memo.input, (_r12_1.results + _r12_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i12
                    }

                    // AND shortcut 11
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 11
                        continue
                    }

                    // CALLORVAR LPAREN
                    var _r16: _GL_Plugin_Item? = null
                    _r16 = _MemoCall(_memo, "LPAREN", _index.element, ::LPAREN, null)
                    if (_r16 != null) _index.element = _r16.nextIndex

                    _label = 11
                }
                // AND 11
                11 -> {
                    val _r11_2 = _memo.results.pop()
                    val _r11_1 = _memo.results.pop()

                    if (_r11_1 != null && _r11_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i11, _index.element, _memo.input, (_r11_1.results + _r11_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i11
                    }

                    // AND shortcut 10
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 10
                        continue
                    }

                    // STAR 17
                    _start_i17 = _index.element
                    _label = 17
                }
                // STAR 17
                17 -> {
                    // CALLORVAR NL
                    var _r18: _GL_Plugin_Item? = null
                    _r18 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r18 != null) _index.element = _r18.nextIndex

                    // STAR 17
                    val _r17 = _memo.results.pop()
                    if (_r17 != null) {
                        _res17 += _r17.results
                        _label = 17
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i17, _index.element, _memo.input, _res17.filterNotNull(), true))
                    }

                    _label = 10
                }
                // AND 10
                10 -> {
                    val _r10_2 = _memo.results.pop()
                    val _r10_1 = _memo.results.pop()

                    if (_r10_1 != null && _r10_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i10, _index.element, _memo.input, (_r10_1.results + _r10_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i10
                    }

                    // AND shortcut 9
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 9
                        continue
                    }

                    // CALLORVAR shaderExpression
                    var _r20: _GL_Plugin_Item? = null
                    _r20 = _MemoCall(_memo, "shaderExpression", _index.element, ::shaderExpression, null)
                    if (_r20 != null) _index.element = _r20.nextIndex

                    // BIND expr
                    expr = _memo.results.peek()

                    _label = 9
                }
                // AND 9
                9 -> {
                    val _r9_2 = _memo.results.pop()
                    val _r9_1 = _memo.results.pop()

                    if (_r9_1 != null && _r9_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i9, _index.element, _memo.input, (_r9_1.results + _r9_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i9
                    }

                    // AND shortcut 8
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 8
                        continue
                    }

                    // STAR 21
                    _start_i21 = _index.element
                    _label = 21
                }
                // STAR 21
                21 -> {
                    // CALLORVAR NL
                    var _r22: _GL_Plugin_Item? = null
                    _r22 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r22 != null) _index.element = _r22.nextIndex

                    // STAR 21
                    val _r21 = _memo.results.pop()
                    if (_r21 != null) {
                        _res21 += _r21.results
                        _label = 21
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i21, _index.element, _memo.input, _res21.filterNotNull(), true))
                    }

                    _label = 8
                }
                // AND 8
                8 -> {
                    val _r8_2 = _memo.results.pop()
                    val _r8_1 = _memo.results.pop()

                    if (_r8_1 != null && _r8_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i8, _index.element, _memo.input, (_r8_1.results + _r8_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i8
                    }

                    // AND shortcut 7
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 7
                        continue
                    }

                    // CALLORVAR RPAREN
                    var _r23: _GL_Plugin_Item? = null
                    _r23 = _MemoCall(_memo, "RPAREN", _index.element, ::RPAREN, null)
                    if (_r23 != null) _index.element = _r23.nextIndex

                    _label = 7
                }
                // AND 7
                7 -> {
                    val _r7_2 = _memo.results.pop()
                    val _r7_1 = _memo.results.pop()

                    if (_r7_1 != null && _r7_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i7, _index.element, _memo.input, (_r7_1.results + _r7_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i7
                    }

                    // AND shortcut 6
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 6
                        continue
                    }

                    // STAR 24
                    _start_i24 = _index.element
                    _label = 24
                }
                // STAR 24
                24 -> {
                    // CALLORVAR NL
                    var _r25: _GL_Plugin_Item? = null
                    _r25 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r25 != null) _index.element = _r25.nextIndex

                    // STAR 24
                    val _r24 = _memo.results.pop()
                    if (_r24 != null) {
                        _res24 += _r24.results
                        _label = 24
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i24, _index.element, _memo.input, _res24.filterNotNull(), true))
                    }

                    _label = 6
                }
                // AND 6
                6 -> {
                    val _r6_2 = _memo.results.pop()
                    val _r6_1 = _memo.results.pop()

                    if (_r6_1 != null && _r6_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i6, _index.element, _memo.input, (_r6_1.results + _r6_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i6
                    }

                    // AND shortcut 5
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 5
                        continue
                    }

                    // CALLORVAR shaderControlStructureBody
                    var _r27: _GL_Plugin_Item? = null
                    _r27 = _MemoCall(_memo, "shaderControlStructureBody", _index.element, ::shaderControlStructureBody, null)
                    if (_r27 != null) _index.element = _r27.nextIndex

                    // BIND thenExpr
                    thenExpr = _memo.results.peek()

                    _label = 5
                }
                // AND 5
                5 -> {
                    val _r5_2 = _memo.results.pop()
                    val _r5_1 = _memo.results.pop()

                    if (_r5_1 != null && _r5_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i5, _index.element, _memo.input, (_r5_1.results + _r5_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i5
                    }

                    // AND shortcut 4
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 4
                        continue
                    }

                    // STAR 28
                    _start_i28 = _index.element
                    _label = 28
                }
                // STAR 28
                28 -> {
                    // CALLORVAR NL
                    var _r29: _GL_Plugin_Item? = null
                    _r29 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r29 != null) _index.element = _r29.nextIndex

                    // STAR 28
                    val _r28 = _memo.results.pop()
                    if (_r28 != null) {
                        _res28 += _r28.results
                        _label = 28
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i28, _index.element, _memo.input, _res28.filterNotNull(), true))
                    }

                    _label = 4
                }
                // AND 4
                4 -> {
                    val _r4_2 = _memo.results.pop()
                    val _r4_1 = _memo.results.pop()

                    if (_r4_1 != null && _r4_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i4, _index.element, _memo.input, (_r4_1.results + _r4_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i4
                    }

                    // AND shortcut 3
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 3
                        continue
                    }

                    // CALLORVAR ELSE
                    var _r30: _GL_Plugin_Item? = null
                    _r30 = _MemoCall(_memo, "ELSE", _index.element, ::ELSE, null)
                    if (_r30 != null) _index.element = _r30.nextIndex

                    _label = 3
                }
                // AND 3
                3 -> {
                    val _r3_2 = _memo.results.pop()
                    val _r3_1 = _memo.results.pop()

                    if (_r3_1 != null && _r3_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i3, _index.element, _memo.input, (_r3_1.results + _r3_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i3
                    }

                    // AND shortcut 2
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 2
                        continue
                    }

                    // STAR 31
                    _start_i31 = _index.element
                    _label = 31
                }
                // STAR 31
                31 -> {
                    // CALLORVAR NL
                    var _r32: _GL_Plugin_Item? = null
                    _r32 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r32 != null) _index.element = _r32.nextIndex

                    // STAR 31
                    val _r31 = _memo.results.pop()
                    if (_r31 != null) {
                        _res31 += _r31.results
                        _label = 31
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i31, _index.element, _memo.input, _res31.filterNotNull(), true))
                    }

                    _label = 2
                }
                // AND 2
                2 -> {
                    val _r2_2 = _memo.results.pop()
                    val _r2_1 = _memo.results.pop()

                    if (_r2_1 != null && _r2_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i2, _index.element, _memo.input, (_r2_1.results + _r2_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i2
                    }

                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // CALLORVAR shaderControlStructureBody
                    var _r34: _GL_Plugin_Item? = null
                    _r34 = _MemoCall(_memo, "shaderControlStructureBody", _index.element, ::shaderControlStructureBody, null)
                    if (_r34 != null) _index.element = _r34.nextIndex

                    // BIND elseExpr
                    elseExpr = _memo.results.peek()

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i1
                    }

                    // ACT 0
                    val _r0 = _memo.results.peek()
                    if (_r0 != null) {
                        _memo.results.pop()
                        _memo.results.push(_GL_Plugin_Item(_r0.startIndex, _r0.nextIndex, _memo.input, _Thunk({ GL_AST.IfExpression(expr.r, thenExpr.r, elseExpr.r) }, _r0), true))
                    }

                    break
                }
            }
        }
    }


    open fun shaderControlStructureBody(_memo: _GL_Plugin_Memo, __index: Int, _args: _GL_Plugin_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // OR 0
        var _start_i0 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // OR 0
                    _start_i0 = _index.element

                    // CALLORVAR shaderBlock
                    var _r1: _GL_Plugin_Item? = null
                    _r1 = _MemoCall(_memo, "shaderBlock", _index.element, ::shaderBlock, null)
                    if (_r1 != null) _index.element = _r1.nextIndex

                    // OR shortcut 0
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i0
                    } else {
                        _label = 0
                        continue
                    }

                    // CALLORVAR shaderExpression
                    var _r2: _GL_Plugin_Item? = null
                    _r2 = _MemoCall(_memo, "shaderExpression", _index.element, ::shaderExpression, null)
                    if (_r2 != null) _index.element = _r2.nextIndex

                    _label = 0
                }
                // OR 0
                0 -> {
                    break
                }
            }
        }
    }


    open fun shaderFloatLiteral(_memo: _GL_Plugin_Memo, __index: Int, _args: _GL_Plugin_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var t: _GL_Plugin_Item? = null

        // COND 1
        var _start_i1 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // COND 1
                    _start_i1 = _index.element

                    // ANY
                    _ParseAny(_memo, _index)

                    // BIND t
                    t = _memo.results.peek()

                    // COND 1
                    val lambda1: (_GL_Plugin_Item) -> Boolean = { (t.i is Token.DoubleLiteral) }
                    if (_memo.results.peek() == null || !lambda1(_memo.results.peek()!!)) {
                        _memo.results.pop()
                        _memo.results.push(null)
                        _index.element = _start_i1
                    }

                    // ACT 0
                    val _r0 = _memo.results.peek()
                    if (_r0 != null) {
                        _memo.results.pop()
                        _memo.results.push(_GL_Plugin_Item(_r0.startIndex, _r0.nextIndex, _memo.input, _Thunk({ GL_AST.FloatLiteral(t.i.cast<Token.DoubleLiteral>().s) }, _r0), true))
                    }

                    break
                }
            }
        }
    }


    open fun shaderParenthesizedExpression(_memo: _GL_Plugin_Memo, __index: Int, _args: _GL_Plugin_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 1
        var _start_i1 = _index.element

        // AND 2
        var _start_i2 = _index.element

        // AND 3
        var _start_i3 = _index.element

        // AND 4
        var _start_i4 = _index.element

        // STAR 6
        var _start_i6 = _index.element
        val _inp6 = arrayListOf<Token?>()
        val _res6 = arrayListOf<AST.AstNode?>()

        // STAR 9
        var _start_i9 = _index.element
        val _inp9 = arrayListOf<Token?>()
        val _res9 = arrayListOf<AST.AstNode?>()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 1
                    _start_i1 = _index.element

                    // AND 2
                    _start_i2 = _index.element

                    // AND 3
                    _start_i3 = _index.element

                    // AND 4
                    _start_i4 = _index.element

                    // CALLORVAR LPAREN
                    var _r5: _GL_Plugin_Item? = null
                    _r5 = _MemoCall(_memo, "LPAREN", _index.element, ::LPAREN, null)
                    if (_r5 != null) _index.element = _r5.nextIndex

                    // AND shortcut 4
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 4
                        continue
                    }

                    // STAR 6
                    _start_i6 = _index.element
                    _label = 6
                }
                // STAR 6
                6 -> {
                    // CALLORVAR NL
                    var _r7: _GL_Plugin_Item? = null
                    _r7 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r7 != null) _index.element = _r7.nextIndex

                    // STAR 6
                    val _r6 = _memo.results.pop()
                    if (_r6 != null) {
                        _res6 += _r6.results
                        _label = 6
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i6, _index.element, _memo.input, _res6.filterNotNull(), true))
                    }

                    _label = 4
                }
                // AND 4
                4 -> {
                    val _r4_2 = _memo.results.pop()
                    val _r4_1 = _memo.results.pop()

                    if (_r4_1 != null && _r4_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i4, _index.element, _memo.input, (_r4_1.results + _r4_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i4
                    }

                    // AND shortcut 3
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 3
                        continue
                    }

                    // CALLORVAR expression
                    var _r8: _GL_Plugin_Item? = null
                    _r8 = _MemoCall(_memo, "expression", _index.element, ::expression, null)
                    if (_r8 != null) _index.element = _r8.nextIndex

                    _label = 3
                }
                // AND 3
                3 -> {
                    val _r3_2 = _memo.results.pop()
                    val _r3_1 = _memo.results.pop()

                    if (_r3_1 != null && _r3_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i3, _index.element, _memo.input, (_r3_1.results + _r3_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i3
                    }

                    // AND shortcut 2
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 2
                        continue
                    }

                    // STAR 9
                    _start_i9 = _index.element
                    _label = 9
                }
                // STAR 9
                9 -> {
                    // CALLORVAR NL
                    var _r10: _GL_Plugin_Item? = null
                    _r10 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r10 != null) _index.element = _r10.nextIndex

                    // STAR 9
                    val _r9 = _memo.results.pop()
                    if (_r9 != null) {
                        _res9 += _r9.results
                        _label = 9
                        continue
                    } else {
                        _memo.results.push(_GL_Plugin_Item(_start_i9, _index.element, _memo.input, _res9.filterNotNull(), true))
                    }

                    _label = 2
                }
                // AND 2
                2 -> {
                    val _r2_2 = _memo.results.pop()
                    val _r2_1 = _memo.results.pop()

                    if (_r2_1 != null && _r2_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i2, _index.element, _memo.input, (_r2_1.results + _r2_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i2
                    }

                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // CALLORVAR RPAREN
                    var _r11: _GL_Plugin_Item? = null
                    _r11 = _MemoCall(_memo, "RPAREN", _index.element, ::RPAREN, null)
                    if (_r11 != null) _index.element = _r11.nextIndex

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_GL_Plugin_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i1
                    }

                    // ACT 0
                    val _r0 = _memo.results.peek()
                    if (_r0 != null) {
                        _memo.results.pop()
                        _memo.results.push(_GL_Plugin_Item(_r0.startIndex, _r0.nextIndex, _memo.input, _Thunk({ it.r }, _r0), true))
                    }

                    break
                }
            }
        }
    }

}
