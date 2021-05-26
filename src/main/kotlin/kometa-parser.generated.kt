//
// KOMeta Parser Parser
//

package kometa.kometa_parser

import kometa.*
import kometa.util.*
import kotlin.jvm.internal.Ref

import kometa.Matcher
import kometa.ast.AST

typealias _Parser_Inputs = Iterable<Char>
typealias _Parser_Results = Iterable<AST.AstNode>
typealias _Parser_Item = MatchItem<Char, AST.AstNode>
typealias _Parser_Args = Iterable<_Parser_Item>
typealias _Parser_Memo = MatchState<Char, AST.AstNode>
typealias _Parser_Rule = Production<Char, AST.AstNode>
typealias _Parser_Base = Matcher<Char, AST.AstNode>

class Parser(handleLeftRecursion: Boolean = true) : Matcher<Char, AST.AstNode>(handleLeftRecursion) {
    init {
        terminals = setOf(
            "AND_PRE",
            "DOT",
            "EOF",
            "EOL",
            "IdentBegin",
            "IdentBody",
            "NOT_PRE",
            "Parameter",
            "QUES",
            "WS",
        )
    }

    fun KOMetaFile(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var il: _Parser_Item? = null
        var g: _Parser_Item? = null

        // AND 1
        var _start_i1 = _index.element

        // AND 2
        var _start_i2 = _index.element

        // AND 3
        var _start_i3 = _index.element

        // AND 4
        var _start_i4 = _index.element

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

                    // CALLORVAR SP
                    var _r5: _Parser_Item? = null
                    _r5 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r5 != null) _index.element = _r5.nextIndex

                    // AND shortcut 4
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 4
                        continue
                    }

                    // CALLORVAR ImportsList
                    var _r8: _Parser_Item? = null
                    _r8 = _MemoCall(_memo, "ImportsList", _index.element, ::ImportsList, null)
                    if (_r8 != null) _index.element = _r8.nextIndex

                    // QUES 7
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_index.element, _memo.input))
                    }
                    // BIND il
                    il = _memo.results.peek()

                    _label = 4
                }
                // AND 4
                4 -> {
                    val _r4_2 = _memo.results.pop()
                    val _r4_1 = _memo.results.pop()

                    if (_r4_1 != null && _r4_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i4, _index.element, _memo.input, (_r4_1.results + _r4_2.results).filterNotNull(), true))
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

                    // CALLORVAR Grammar
                    var _r10: _Parser_Item? = null
                    _r10 = _MemoCall(_memo, "Grammar", _index.element, ::Grammar, null)
                    if (_r10 != null) _index.element = _r10.nextIndex

                    // BIND g
                    g = _memo.results.peek()

                    _label = 3
                }
                // AND 3
                3 -> {
                    val _r3_2 = _memo.results.pop()
                    val _r3_1 = _memo.results.pop()

                    if (_r3_1 != null && _r3_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i3, _index.element, _memo.input, (_r3_1.results + _r3_2.results).filterNotNull(), true))
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

                    // CALLORVAR SP
                    var _r11: _Parser_Item? = null
                    _r11 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r11 != null) _index.element = _r11.nextIndex

                    _label = 2
                }
                // AND 2
                2 -> {
                    val _r2_2 = _memo.results.pop()
                    val _r2_1 = _memo.results.pop()

                    if (_r2_1 != null && _r2_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i2, _index.element, _memo.input, (_r2_1.results + _r2_2.results).filterNotNull(), true))
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

                    // CALLORVAR EOF
                    var _r12: _Parser_Item? = null
                    _r12 = _MemoCall(_memo, "EOF", _index.element, ::EOF, null)
                    if (_r12 != null) _index.element = _r12.nextIndex

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i1
                    }

                    // ACT 0
                    val _r0 = _memo.results.peek()
                    if (_r0 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_r0.startIndex, _r0.nextIndex, _memo.input, _Thunk({ AST.GrammarFile(il?.asResult()!!, g?.asResult()!!) }, _r0), true))
                    }

                    break
                }
            }
        }
    }


    fun ImportsList(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var i: _Parser_Item? = null

        // PLUS 2
        var _start_i2 = _index.element
        val _inp2 = arrayListOf<Char?>()
        val _res2 = arrayListOf<AST.AstNode?>()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // PLUS 2
                    _start_i2 = _index.element
                    _label = 2
                }
                // PLUS 2
                2 -> {
                    // CALLORVAR Import
                    var _r3: _Parser_Item? = null
                    _r3 = _MemoCall(_memo, "Import", _index.element, ::Import, null)
                    if (_r3 != null) _index.element = _r3.nextIndex

                    // PLUS 2
                    val _r2 = _memo.results.pop()
                    if (_r2 != null) {
                        _res2 += _r2.results
                        _label = 2
                        continue
                    } else {
                        if (_index.element > _start_i2) {
                            _memo.results.push(_Parser_Item(_start_i2, _index.element, _memo.input, _res2.filterNotNull(), true))
                        } else {
                            _memo.results.push(null)
                        }
                    }

                    // BIND i
                    i = _memo.results.peek()

                    // ACT 0
                    val _r0 = _memo.results.peek()
                    if (_r0 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_r0.startIndex, _r0.nextIndex, _memo.input, _Thunk({ AST.ImportList(i!!.results.filterNotNull()) }, _r0), true))
                    }

                    break
                }
            }
        }
    }


    fun Import(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var name: _Parser_Item? = null

        // AND 1
        var _start_i1 = _index.element

        // AND 2
        var _start_i2 = _index.element

        // AND 3
        var _start_i3 = _index.element

        // AND 6
        var _start_i6 = _index.element

        // STAR 8
        var _start_i8 = _index.element
        val _inp8 = arrayListOf<Char?>()
        val _res8 = arrayListOf<AST.AstNode?>()

        // AND 9
        var _start_i9 = _index.element

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

                    // CALLORVAR USING
                    var _r4: _Parser_Item? = null
                    _r4 = _MemoCall(_memo, "USING", _index.element, ::USING, null)
                    if (_r4 != null) _index.element = _r4.nextIndex

                    // AND shortcut 3
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 3
                        continue
                    }

                    // AND 6
                    _start_i6 = _index.element

                    // CALLORVAR Ident
                    var _r7: _Parser_Item? = null
                    _r7 = _MemoCall(_memo, "Ident", _index.element, ::Ident, null)
                    if (_r7 != null) _index.element = _r7.nextIndex

                    // AND shortcut 6
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 6
                        continue
                    }

                    // STAR 8
                    _start_i8 = _index.element
                    _label = 8
                }
                // STAR 8
                8 -> {
                    // AND 9
                    _start_i9 = _index.element

                    // CALLORVAR DOT
                    var _r10: _Parser_Item? = null
                    _r10 = _MemoCall(_memo, "DOT", _index.element, ::DOT, null)
                    if (_r10 != null) _index.element = _r10.nextIndex

                    // AND shortcut 9
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 9
                        continue
                    }

                    // CALLORVAR Ident
                    var _r11: _Parser_Item? = null
                    _r11 = _MemoCall(_memo, "Ident", _index.element, ::Ident, null)
                    if (_r11 != null) _index.element = _r11.nextIndex

                    _label = 9
                }
                // AND 9
                9 -> {
                    val _r9_2 = _memo.results.pop()
                    val _r9_1 = _memo.results.pop()

                    if (_r9_1 != null && _r9_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i9, _index.element, _memo.input, (_r9_1.results + _r9_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i9
                    }

                    // STAR 8
                    val _r8 = _memo.results.pop()
                    if (_r8 != null) {
                        _res8 += _r8.results
                        _label = 8
                        continue
                    } else {
                        _memo.results.push(_Parser_Item(_start_i8, _index.element, _memo.input, _res8.filterNotNull(), true))
                    }

                    _label = 6
                }
                // AND 6
                6 -> {
                    val _r6_2 = _memo.results.pop()
                    val _r6_1 = _memo.results.pop()

                    if (_r6_1 != null && _r6_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i6, _index.element, _memo.input, (_r6_1.results + _r6_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i6
                    }

                    // BIND name
                    name = _memo.results.peek()

                    _label = 3
                }
                // AND 3
                3 -> {
                    val _r3_2 = _memo.results.pop()
                    val _r3_1 = _memo.results.pop()

                    if (_r3_1 != null && _r3_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i3, _index.element, _memo.input, (_r3_1.results + _r3_2.results).filterNotNull(), true))
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

                    // CALLORVAR SP
                    var _r12: _Parser_Item? = null
                    _r12 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r12 != null) _index.element = _r12.nextIndex

                    _label = 2
                }
                // AND 2
                2 -> {
                    val _r2_2 = _memo.results.pop()
                    val _r2_1 = _memo.results.pop()

                    if (_r2_1 != null && _r2_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i2, _index.element, _memo.input, (_r2_1.results + _r2_2.results).filterNotNull(), true))
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

                    // CALLORVAR SEMI
                    var _r13: _Parser_Item? = null
                    _r13 = _MemoCall(_memo, "SEMI", _index.element, ::SEMI, null)
                    if (_r13 != null) _index.element = _r13.nextIndex

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i1
                    }

                    // ACT 0
                    val _r0 = _memo.results.peek()
                    if (_r0 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_r0.startIndex, _r0.nextIndex, _memo.input, _Thunk({ AST.Import(name!!) }, _r0), true))
                    }

                    break
                }
            }
        }
    }


    fun Grammar(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var name: _Parser_Item? = null
        var tinput: _Parser_Item? = null
        var tresult: _Parser_Item? = null
        var baseclass: _Parser_Item? = null
        var rules: _Parser_Item? = null

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

        // AND 26
        var _start_i26 = _index.element

        // AND 27
        var _start_i27 = _index.element

        // STAR 34
        var _start_i34 = _index.element
        val _inp34 = arrayListOf<Char?>()
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

                    // CALLORVAR KOMETA
                    var _r13: _Parser_Item? = null
                    _r13 = _MemoCall(_memo, "KOMETA", _index.element, ::KOMETA, null)
                    if (_r13 != null) _index.element = _r13.nextIndex

                    // AND shortcut 12
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 12
                        continue
                    }

                    // CALLORVAR Identifier
                    var _r15: _Parser_Item? = null
                    _r15 = _MemoCall(_memo, "Identifier", _index.element, ::Identifier, null)
                    if (_r15 != null) _index.element = _r15.nextIndex

                    // BIND name
                    name = _memo.results.peek()

                    _label = 12
                }
                // AND 12
                12 -> {
                    val _r12_2 = _memo.results.pop()
                    val _r12_1 = _memo.results.pop()

                    if (_r12_1 != null && _r12_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i12, _index.element, _memo.input, (_r12_1.results + _r12_2.results).filterNotNull(), true))
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

                    // CALLORVAR LESS
                    var _r16: _Parser_Item? = null
                    _r16 = _MemoCall(_memo, "LESS", _index.element, ::LESS, null)
                    if (_r16 != null) _index.element = _r16.nextIndex

                    _label = 11
                }
                // AND 11
                11 -> {
                    val _r11_2 = _memo.results.pop()
                    val _r11_1 = _memo.results.pop()

                    if (_r11_1 != null && _r11_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i11, _index.element, _memo.input, (_r11_1.results + _r11_2.results).filterNotNull(), true))
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

                    // CALLORVAR GenericId
                    var _r18: _Parser_Item? = null
                    _r18 = _MemoCall(_memo, "GenericId", _index.element, ::GenericId, null)
                    if (_r18 != null) _index.element = _r18.nextIndex

                    // BIND tinput
                    tinput = _memo.results.peek()

                    _label = 10
                }
                // AND 10
                10 -> {
                    val _r10_2 = _memo.results.pop()
                    val _r10_1 = _memo.results.pop()

                    if (_r10_1 != null && _r10_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i10, _index.element, _memo.input, (_r10_1.results + _r10_2.results).filterNotNull(), true))
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

                    // CALLORVAR SP
                    var _r19: _Parser_Item? = null
                    _r19 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r19 != null) _index.element = _r19.nextIndex

                    _label = 9
                }
                // AND 9
                9 -> {
                    val _r9_2 = _memo.results.pop()
                    val _r9_1 = _memo.results.pop()

                    if (_r9_1 != null && _r9_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i9, _index.element, _memo.input, (_r9_1.results + _r9_2.results).filterNotNull(), true))
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

                    // CALLORVAR COMMA
                    var _r20: _Parser_Item? = null
                    _r20 = _MemoCall(_memo, "COMMA", _index.element, ::COMMA, null)
                    if (_r20 != null) _index.element = _r20.nextIndex

                    _label = 8
                }
                // AND 8
                8 -> {
                    val _r8_2 = _memo.results.pop()
                    val _r8_1 = _memo.results.pop()

                    if (_r8_1 != null && _r8_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i8, _index.element, _memo.input, (_r8_1.results + _r8_2.results).filterNotNull(), true))
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

                    // CALLORVAR GenericId
                    var _r22: _Parser_Item? = null
                    _r22 = _MemoCall(_memo, "GenericId", _index.element, ::GenericId, null)
                    if (_r22 != null) _index.element = _r22.nextIndex

                    // BIND tresult
                    tresult = _memo.results.peek()

                    _label = 7
                }
                // AND 7
                7 -> {
                    val _r7_2 = _memo.results.pop()
                    val _r7_1 = _memo.results.pop()

                    if (_r7_1 != null && _r7_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i7, _index.element, _memo.input, (_r7_1.results + _r7_2.results).filterNotNull(), true))
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

                    // CALLORVAR SP
                    var _r23: _Parser_Item? = null
                    _r23 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r23 != null) _index.element = _r23.nextIndex

                    _label = 6
                }
                // AND 6
                6 -> {
                    val _r6_2 = _memo.results.pop()
                    val _r6_1 = _memo.results.pop()

                    if (_r6_1 != null && _r6_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i6, _index.element, _memo.input, (_r6_1.results + _r6_2.results).filterNotNull(), true))
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

                    // CALLORVAR GREATER
                    var _r24: _Parser_Item? = null
                    _r24 = _MemoCall(_memo, "GREATER", _index.element, ::GREATER, null)
                    if (_r24 != null) _index.element = _r24.nextIndex

                    _label = 5
                }
                // AND 5
                5 -> {
                    val _r5_2 = _memo.results.pop()
                    val _r5_1 = _memo.results.pop()

                    if (_r5_1 != null && _r5_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i5, _index.element, _memo.input, (_r5_1.results + _r5_2.results).filterNotNull(), true))
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

                    // AND 26
                    _start_i26 = _index.element

                    // AND 27
                    _start_i27 = _index.element

                    // CALLORVAR COLON
                    var _r28: _Parser_Item? = null
                    _r28 = _MemoCall(_memo, "COLON", _index.element, ::COLON, null)
                    if (_r28 != null) _index.element = _r28.nextIndex

                    // AND shortcut 27
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 27
                        continue
                    }

                    // CALLORVAR GenericId
                    var _r30: _Parser_Item? = null
                    _r30 = _MemoCall(_memo, "GenericId", _index.element, ::GenericId, null)
                    if (_r30 != null) _index.element = _r30.nextIndex

                    // BIND baseclass
                    baseclass = _memo.results.peek()

                    _label = 27
                }
                // AND 27
                27 -> {
                    val _r27_2 = _memo.results.pop()
                    val _r27_1 = _memo.results.pop()

                    if (_r27_1 != null && _r27_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i27, _index.element, _memo.input, (_r27_1.results + _r27_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i27
                    }

                    // AND shortcut 26
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 26
                        continue
                    }

                    // CALLORVAR SP
                    var _r31: _Parser_Item? = null
                    _r31 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r31 != null) _index.element = _r31.nextIndex

                    _label = 26
                }
                // AND 26
                26 -> {
                    val _r26_2 = _memo.results.pop()
                    val _r26_1 = _memo.results.pop()

                    if (_r26_1 != null && _r26_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i26, _index.element, _memo.input, (_r26_1.results + _r26_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i26
                    }

                    // QUES 25
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_index.element, _memo.input))
                    }
                    _label = 4
                }
                // AND 4
                4 -> {
                    val _r4_2 = _memo.results.pop()
                    val _r4_1 = _memo.results.pop()

                    if (_r4_1 != null && _r4_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i4, _index.element, _memo.input, (_r4_1.results + _r4_2.results).filterNotNull(), true))
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

                    // CALLORVAR LEFT_BRACE
                    var _r32: _Parser_Item? = null
                    _r32 = _MemoCall(_memo, "LEFT_BRACE", _index.element, ::LEFT_BRACE, null)
                    if (_r32 != null) _index.element = _r32.nextIndex

                    _label = 3
                }
                // AND 3
                3 -> {
                    val _r3_2 = _memo.results.pop()
                    val _r3_1 = _memo.results.pop()

                    if (_r3_1 != null && _r3_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i3, _index.element, _memo.input, (_r3_1.results + _r3_2.results).filterNotNull(), true))
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

                    // STAR 34
                    _start_i34 = _index.element
                    _label = 34
                }
                // STAR 34
                34 -> {
                    // CALLORVAR Rule
                    var _r35: _Parser_Item? = null
                    _r35 = _MemoCall(_memo, "Rule", _index.element, ::Rule, null)
                    if (_r35 != null) _index.element = _r35.nextIndex

                    // STAR 34
                    val _r34 = _memo.results.pop()
                    if (_r34 != null) {
                        _res34 += _r34.results
                        _label = 34
                        continue
                    } else {
                        _memo.results.push(_Parser_Item(_start_i34, _index.element, _memo.input, _res34.filterNotNull(), true))
                    }

                    // BIND rules
                    rules = _memo.results.peek()

                    _label = 2
                }
                // AND 2
                2 -> {
                    val _r2_2 = _memo.results.pop()
                    val _r2_1 = _memo.results.pop()

                    if (_r2_1 != null && _r2_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i2, _index.element, _memo.input, (_r2_1.results + _r2_2.results).filterNotNull(), true))
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

                    // CALLORVAR RIGHT_BRACE
                    var _r36: _Parser_Item? = null
                    _r36 = _MemoCall(_memo, "RIGHT_BRACE", _index.element, ::RIGHT_BRACE, null)
                    if (_r36 != null) _index.element = _r36.nextIndex

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i1
                    }

                    // ACT 0
                    val _r0 = _memo.results.peek()
                    if (_r0 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_r0.startIndex, _r0.nextIndex, _memo.input, _Thunk({ AST.Grammar(name!!, tinput!!, tresult!!, baseclass, rules!!.results.filterIsInstance<AST.Rule>()) }, _r0), true))
                    }

                    break
                }
            }
        }
    }


    fun Rule(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var ovr: _Parser_Item? = null
        var name: _Parser_Item? = null
        var parms: _Parser_Item? = null
        var body: _Parser_Item? = null

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

                    // CALLORVAR OVERRIDE
                    var _r8: _Parser_Item? = null
                    _r8 = _MemoCall(_memo, "OVERRIDE", _index.element, ::OVERRIDE, null)
                    if (_r8 != null) _index.element = _r8.nextIndex

                    // QUES 7
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_index.element, _memo.input))
                    }
                    // BIND ovr
                    ovr = _memo.results.peek()

                    // AND shortcut 5
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 5
                        continue
                    }

                    // CALLORVAR Identifier
                    var _r10: _Parser_Item? = null
                    _r10 = _MemoCall(_memo, "Identifier", _index.element, ::Identifier, null)
                    if (_r10 != null) _index.element = _r10.nextIndex

                    // BIND name
                    name = _memo.results.peek()

                    _label = 5
                }
                // AND 5
                5 -> {
                    val _r5_2 = _memo.results.pop()
                    val _r5_1 = _memo.results.pop()

                    if (_r5_1 != null && _r5_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i5, _index.element, _memo.input, (_r5_1.results + _r5_2.results).filterNotNull(), true))
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

                    // CALLORVAR Disjunction
                    var _r13: _Parser_Item? = null
                    _r13 = _MemoCall(_memo, "Disjunction", _index.element, ::Disjunction, null)
                    if (_r13 != null) _index.element = _r13.nextIndex

                    // QUES 12
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_index.element, _memo.input))
                    }
                    // BIND parms
                    parms = _memo.results.peek()

                    _label = 4
                }
                // AND 4
                4 -> {
                    val _r4_2 = _memo.results.pop()
                    val _r4_1 = _memo.results.pop()

                    if (_r4_1 != null && _r4_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i4, _index.element, _memo.input, (_r4_1.results + _r4_2.results).filterNotNull(), true))
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

                    // CALLORVAR EQUALS
                    var _r14: _Parser_Item? = null
                    _r14 = _MemoCall(_memo, "EQUALS", _index.element, ::EQUALS, null)
                    if (_r14 != null) _index.element = _r14.nextIndex

                    _label = 3
                }
                // AND 3
                3 -> {
                    val _r3_2 = _memo.results.pop()
                    val _r3_1 = _memo.results.pop()

                    if (_r3_1 != null && _r3_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i3, _index.element, _memo.input, (_r3_1.results + _r3_2.results).filterNotNull(), true))
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

                    // CALLORVAR Disjunction
                    var _r16: _Parser_Item? = null
                    _r16 = _MemoCall(_memo, "Disjunction", _index.element, ::Disjunction, null)
                    if (_r16 != null) _index.element = _r16.nextIndex

                    // BIND body
                    body = _memo.results.peek()

                    _label = 2
                }
                // AND 2
                2 -> {
                    val _r2_2 = _memo.results.pop()
                    val _r2_1 = _memo.results.pop()

                    if (_r2_1 != null && _r2_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i2, _index.element, _memo.input, (_r2_1.results + _r2_2.results).filterNotNull(), true))
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

                    // CALLORVAR SEMI
                    var _r17: _Parser_Item? = null
                    _r17 = _MemoCall(_memo, "SEMI", _index.element, ::SEMI, null)
                    if (_r17 != null) _index.element = _r17.nextIndex

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i1
                    }

                    // ACT 0
                    val _r0 = _memo.results.peek()
                    if (_r0 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_r0.startIndex, _r0.nextIndex, _memo.input, _Thunk({ AST.Rule(name!!, if (parms != null && parms.inputs.any()) AST.Args(parms.asResult(), body?.asResult()!!) else body?.asResult()!!, ovr?.inputs?.joinToString("") ?: "") }, _r0), true))
                    }

                    break
                }
            }
        }
    }


    fun Disjunction(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 1
        var _start_i1 = _index.element

        // STAR 3
        var _start_i3 = _index.element
        val _inp3 = arrayListOf<Char?>()
        val _res3 = arrayListOf<AST.AstNode?>()

        // AND 4
        var _start_i4 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 1
                    _start_i1 = _index.element

                    // CALLORVAR ActionExp
                    var _r2: _Parser_Item? = null
                    _r2 = _MemoCall(_memo, "ActionExp", _index.element, ::ActionExp, null)
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

                    // CALLORVAR OR
                    var _r5: _Parser_Item? = null
                    _r5 = _MemoCall(_memo, "OR", _index.element, ::OR, null)
                    if (_r5 != null) _index.element = _r5.nextIndex

                    // AND shortcut 4
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 4
                        continue
                    }

                    // CALLORVAR ActionExp
                    var _r6: _Parser_Item? = null
                    _r6 = _MemoCall(_memo, "ActionExp", _index.element, ::ActionExp, null)
                    if (_r6 != null) _index.element = _r6.nextIndex

                    _label = 4
                }
                // AND 4
                4 -> {
                    val _r4_2 = _memo.results.pop()
                    val _r4_1 = _memo.results.pop()

                    if (_r4_1 != null && _r4_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i4, _index.element, _memo.input, (_r4_1.results + _r4_2.results).filterNotNull(), true))
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
                        _memo.results.push(_Parser_Item(_start_i3, _index.element, _memo.input, _res3.filterNotNull(), true))
                    }

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i1
                    }

                    // ACT 0
                    val _r0 = _memo.results.peek()
                    if (_r0 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_r0.startIndex, _r0.nextIndex, _memo.input, _Thunk({ it.results.filterNotNull().reduce{ prev, cur -> AST.Or(prev, cur) } }, _r0), true))
                    }

                    break
                }
            }
        }
    }


    fun ActionExp(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var body: _Parser_Item? = null
        var action: _Parser_Item? = null

        // OR 0
        var _start_i0 = _index.element

        // AND 2
        var _start_i2 = _index.element

        // AND 3
        var _start_i3 = _index.element

        // AND 4
        var _start_i4 = _index.element

        // LOOK 8
        var _start_i8 = _index.element

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

                    // AND 4
                    _start_i4 = _index.element

                    // CALLORVAR SequenceExp
                    var _r6: _Parser_Item? = null
                    _r6 = _MemoCall(_memo, "SequenceExp", _index.element, ::SequenceExp, null)
                    if (_r6 != null) _index.element = _r6.nextIndex

                    // BIND body
                    body = _memo.results.peek()

                    // AND shortcut 4
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 4
                        continue
                    }

                    // CALLORVAR ACTION
                    var _r7: _Parser_Item? = null
                    _r7 = _MemoCall(_memo, "ACTION", _index.element, ::ACTION, null)
                    if (_r7 != null) _index.element = _r7.nextIndex

                    _label = 4
                }
                // AND 4
                4 -> {
                    val _r4_2 = _memo.results.pop()
                    val _r4_1 = _memo.results.pop()

                    if (_r4_1 != null && _r4_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i4, _index.element, _memo.input, (_r4_1.results + _r4_2.results).filterNotNull(), true))
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

                    // LOOK 8
                    _start_i8 = _index.element

                    // CALLORVAR LEFT_BRACE
                    var _r9: _Parser_Item? = null
                    _r9 = _MemoCall(_memo, "LEFT_BRACE", _index.element, ::LEFT_BRACE, null)
                    if (_r9 != null) _index.element = _r9.nextIndex

                    // LOOK 8
                    val _r8 = _memo.results.pop()
                    _memo.results.push(if (_r8 != null) _Parser_Item(_start_i8, _memo.input) else null)
                    _index.element = _start_i8

                    _label = 3
                }
                // AND 3
                3 -> {
                    val _r3_2 = _memo.results.pop()
                    val _r3_1 = _memo.results.pop()

                    if (_r3_1 != null && _r3_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i3, _index.element, _memo.input, (_r3_1.results + _r3_2.results).filterNotNull(), true))
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

                    // CALLORVAR KotlinCode
                    var _r11: _Parser_Item? = null
                    _r11 = _MemoCall(_memo, "KotlinCode", _index.element, ::KotlinCode, null)
                    if (_r11 != null) _index.element = _r11.nextIndex

                    // BIND action
                    action = _memo.results.peek()

                    _label = 2
                }
                // AND 2
                2 -> {
                    val _r2_2 = _memo.results.pop()
                    val _r2_1 = _memo.results.pop()

                    if (_r2_1 != null && _r2_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i2, _index.element, _memo.input, (_r2_1.results + _r2_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i2
                    }

                    // ACT 1
                    val _r1 = _memo.results.peek()
                    if (_r1 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_r1.startIndex, _r1.nextIndex, _memo.input, _Thunk({ AST.Act(body?.asResult()!!, action!!) }, _r1), true))
                    }

                    // OR shortcut 0
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i0
                    } else {
                        _label = 0
                        continue
                    }

                    // CALLORVAR SequenceExp
                    var _r12: _Parser_Item? = null
                    _r12 = _MemoCall(_memo, "SequenceExp", _index.element, ::SequenceExp, null)
                    if (_r12 != null) _index.element = _r12.nextIndex

                    _label = 0
                }
                // OR 0
                0 -> {
                    break
                }
            }
        }
    }


    fun FailExp(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var msg: _Parser_Item? = null

        // AND 1
        var _start_i1 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 1
                    _start_i1 = _index.element

                    // CALLORVAR BANG
                    var _r2: _Parser_Item? = null
                    _r2 = _MemoCall(_memo, "BANG", _index.element, ::BANG, null)
                    if (_r2 != null) _index.element = _r2.nextIndex

                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // CALLORVAR KotlinCode
                    var _r5: _Parser_Item? = null
                    _r5 = _MemoCall(_memo, "KotlinCode", _index.element, ::KotlinCode, null)
                    if (_r5 != null) _index.element = _r5.nextIndex

                    // BIND msg
                    msg = _memo.results.peek()

                    // QUES 3
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_index.element, _memo.input))
                    }
                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i1
                    }

                    // ACT 0
                    val _r0 = _memo.results.peek()
                    if (_r0 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_r0.startIndex, _r0.nextIndex, _memo.input, _Thunk({ AST.Fail(msg?.asResult()!!) }, _r0), true))
                    }

                    break
                }
            }
        }
    }


    fun SequenceExp(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // PLUS 1
        var _start_i1 = _index.element
        val _inp1 = arrayListOf<Char?>()
        val _res1 = arrayListOf<AST.AstNode?>()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // PLUS 1
                    _start_i1 = _index.element
                    _label = 1
                }
                // PLUS 1
                1 -> {
                    // CALLORVAR ConditionExp
                    var _r2: _Parser_Item? = null
                    _r2 = _MemoCall(_memo, "ConditionExp", _index.element, ::ConditionExp, null)
                    if (_r2 != null) _index.element = _r2.nextIndex

                    // PLUS 1
                    val _r1 = _memo.results.pop()
                    if (_r1 != null) {
                        _res1 += _r1.results
                        _label = 1
                        continue
                    } else {
                        if (_index.element > _start_i1) {
                            _memo.results.push(_Parser_Item(_start_i1, _index.element, _memo.input, _res1.filterNotNull(), true))
                        } else {
                            _memo.results.push(null)
                        }
                    }

                    // ACT 0
                    val _r0 = _memo.results.peek()
                    if (_r0 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_r0.startIndex, _r0.nextIndex, _memo.input, _Thunk({ it.results.filterNotNull().reduce { prev, cur -> AST.And(prev, cur) } }, _r0), true))
                    }

                    break
                }
            }
        }
    }


    fun ConditionExp(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var body: _Parser_Item? = null
        var cond: _Parser_Item? = null

        // OR 0
        var _start_i0 = _index.element

        // OR 1
        var _start_i1 = _index.element

        // AND 4
        var _start_i4 = _index.element

        // AND 5
        var _start_i5 = _index.element

        // AND 6
        var _start_i6 = _index.element

        // LOOK 10
        var _start_i10 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // OR 0
                    _start_i0 = _index.element

                    // OR 1
                    _start_i1 = _index.element

                    // CALLORVAR FailExp
                    var _r2: _Parser_Item? = null
                    _r2 = _MemoCall(_memo, "FailExp", _index.element, ::FailExp, null)
                    if (_r2 != null) _index.element = _r2.nextIndex

                    // OR shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i1
                    } else {
                        _label = 1
                        continue
                    }

                    // AND 4
                    _start_i4 = _index.element

                    // AND 5
                    _start_i5 = _index.element

                    // AND 6
                    _start_i6 = _index.element

                    // CALLORVAR BoundTerm
                    var _r8: _Parser_Item? = null
                    _r8 = _MemoCall(_memo, "BoundTerm", _index.element, ::BoundTerm, null)
                    if (_r8 != null) _index.element = _r8.nextIndex

                    // BIND body
                    body = _memo.results.peek()

                    // AND shortcut 6
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 6
                        continue
                    }

                    // CALLORVAR QUES
                    var _r9: _Parser_Item? = null
                    _r9 = _MemoCall(_memo, "QUES", _index.element, ::QUES, null)
                    if (_r9 != null) _index.element = _r9.nextIndex

                    _label = 6
                }
                // AND 6
                6 -> {
                    val _r6_2 = _memo.results.pop()
                    val _r6_1 = _memo.results.pop()

                    if (_r6_1 != null && _r6_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i6, _index.element, _memo.input, (_r6_1.results + _r6_2.results).filterNotNull(), true))
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

                    // LOOK 10
                    _start_i10 = _index.element

                    // CALLORVAR OPEN
                    var _r11: _Parser_Item? = null
                    _r11 = _MemoCall(_memo, "OPEN", _index.element, ::OPEN, null)
                    if (_r11 != null) _index.element = _r11.nextIndex

                    // LOOK 10
                    val _r10 = _memo.results.pop()
                    _memo.results.push(if (_r10 != null) _Parser_Item(_start_i10, _memo.input) else null)
                    _index.element = _start_i10

                    _label = 5
                }
                // AND 5
                5 -> {
                    val _r5_2 = _memo.results.pop()
                    val _r5_1 = _memo.results.pop()

                    if (_r5_1 != null && _r5_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i5, _index.element, _memo.input, (_r5_1.results + _r5_2.results).filterNotNull(), true))
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

                    // CALLORVAR KotlinCode
                    var _r13: _Parser_Item? = null
                    _r13 = _MemoCall(_memo, "KotlinCode", _index.element, ::KotlinCode, null)
                    if (_r13 != null) _index.element = _r13.nextIndex

                    // BIND cond
                    cond = _memo.results.peek()

                    _label = 4
                }
                // AND 4
                4 -> {
                    val _r4_2 = _memo.results.pop()
                    val _r4_1 = _memo.results.pop()

                    if (_r4_1 != null && _r4_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i4, _index.element, _memo.input, (_r4_1.results + _r4_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i4
                    }

                    // ACT 3
                    val _r3 = _memo.results.peek()
                    if (_r3 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_r3.startIndex, _r3.nextIndex, _memo.input, _Thunk({ AST.Cond(body?.asResult()!!, cond!!) }, _r3), true))
                    }

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

                    // CALLORVAR BoundTerm
                    var _r14: _Parser_Item? = null
                    _r14 = _MemoCall(_memo, "BoundTerm", _index.element, ::BoundTerm, null)
                    if (_r14 != null) _index.element = _r14.nextIndex

                    _label = 0
                }
                // OR 0
                0 -> {
                    break
                }
            }
        }
    }


    fun BoundTerm(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var exp: _Parser_Item? = null
        var id: _Parser_Item? = null

        // OR 0
        var _start_i0 = _index.element

        // OR 1
        var _start_i1 = _index.element

        // AND 3
        var _start_i3 = _index.element

        // AND 4
        var _start_i4 = _index.element

        // AND 11
        var _start_i11 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // OR 0
                    _start_i0 = _index.element

                    // OR 1
                    _start_i1 = _index.element

                    // AND 3
                    _start_i3 = _index.element

                    // AND 4
                    _start_i4 = _index.element

                    // CALLORVAR PrefixedTerm
                    var _r6: _Parser_Item? = null
                    _r6 = _MemoCall(_memo, "PrefixedTerm", _index.element, ::PrefixedTerm, null)
                    if (_r6 != null) _index.element = _r6.nextIndex

                    // BIND exp
                    exp = _memo.results.peek()

                    // AND shortcut 4
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 4
                        continue
                    }

                    // CALLORVAR COLON
                    var _r7: _Parser_Item? = null
                    _r7 = _MemoCall(_memo, "COLON", _index.element, ::COLON, null)
                    if (_r7 != null) _index.element = _r7.nextIndex

                    _label = 4
                }
                // AND 4
                4 -> {
                    val _r4_2 = _memo.results.pop()
                    val _r4_1 = _memo.results.pop()

                    if (_r4_1 != null && _r4_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i4, _index.element, _memo.input, (_r4_1.results + _r4_2.results).filterNotNull(), true))
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

                    // CALLORVAR Identifier
                    var _r9: _Parser_Item? = null
                    _r9 = _MemoCall(_memo, "Identifier", _index.element, ::Identifier, null)
                    if (_r9 != null) _index.element = _r9.nextIndex

                    // BIND id
                    id = _memo.results.peek()

                    _label = 3
                }
                // AND 3
                3 -> {
                    val _r3_2 = _memo.results.pop()
                    val _r3_1 = _memo.results.pop()

                    if (_r3_1 != null && _r3_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i3, _index.element, _memo.input, (_r3_1.results + _r3_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i3
                    }

                    // ACT 2
                    val _r2 = _memo.results.peek()
                    if (_r2 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_r2.startIndex, _r2.nextIndex, _memo.input, _Thunk({ AST.Bind(exp?.asResult()!!, id!!) }, _r2), true))
                    }

                    // OR shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i1
                    } else {
                        _label = 1
                        continue
                    }

                    // AND 11
                    _start_i11 = _index.element

                    // CALLORVAR COLON
                    var _r12: _Parser_Item? = null
                    _r12 = _MemoCall(_memo, "COLON", _index.element, ::COLON, null)
                    if (_r12 != null) _index.element = _r12.nextIndex

                    // AND shortcut 11
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 11
                        continue
                    }

                    // CALLORVAR Identifier
                    var _r14: _Parser_Item? = null
                    _r14 = _MemoCall(_memo, "Identifier", _index.element, ::Identifier, null)
                    if (_r14 != null) _index.element = _r14.nextIndex

                    // BIND id
                    id = _memo.results.peek()

                    _label = 11
                }
                // AND 11
                11 -> {
                    val _r11_2 = _memo.results.pop()
                    val _r11_1 = _memo.results.pop()

                    if (_r11_1 != null && _r11_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i11, _index.element, _memo.input, (_r11_1.results + _r11_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i11
                    }

                    // ACT 10
                    val _r10 = _memo.results.peek()
                    if (_r10 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_r10.startIndex, _r10.nextIndex, _memo.input, _Thunk({ AST.Bind(AST.Any, id!!); }, _r10), true))
                    }

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

                    // CALLORVAR PrefixedTerm
                    var _r15: _Parser_Item? = null
                    _r15 = _MemoCall(_memo, "PrefixedTerm", _index.element, ::PrefixedTerm, null)
                    if (_r15 != null) _index.element = _r15.nextIndex

                    _label = 0
                }
                // OR 0
                0 -> {
                    break
                }
            }
        }
    }


    fun PrefixedTerm(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // OR 0
        var _start_i0 = _index.element

        // OR 1
        var _start_i1 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // OR 0
                    _start_i0 = _index.element

                    // OR 1
                    _start_i1 = _index.element

                    // CALLORVAR LookTerm
                    var _r2: _Parser_Item? = null
                    _r2 = _MemoCall(_memo, "LookTerm", _index.element, ::LookTerm, null)
                    if (_r2 != null) _index.element = _r2.nextIndex

                    // OR shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i1
                    } else {
                        _label = 1
                        continue
                    }

                    // CALLORVAR NotTerm
                    var _r3: _Parser_Item? = null
                    _r3 = _MemoCall(_memo, "NotTerm", _index.element, ::NotTerm, null)
                    if (_r3 != null) _index.element = _r3.nextIndex

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

                    // CALLORVAR PostfixedTerm
                    var _r4: _Parser_Item? = null
                    _r4 = _MemoCall(_memo, "PostfixedTerm", _index.element, ::PostfixedTerm, null)
                    if (_r4 != null) _index.element = _r4.nextIndex

                    _label = 0
                }
                // OR 0
                0 -> {
                    break
                }
            }
        }
    }


    fun LookTerm(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var exp: _Parser_Item? = null

        // AND 1
        var _start_i1 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 1
                    _start_i1 = _index.element

                    // CALLORVAR AND_PRE
                    var _r2: _Parser_Item? = null
                    _r2 = _MemoCall(_memo, "AND_PRE", _index.element, ::AND_PRE, null)
                    if (_r2 != null) _index.element = _r2.nextIndex

                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // CALLORVAR PostfixedTerm
                    var _r4: _Parser_Item? = null
                    _r4 = _MemoCall(_memo, "PostfixedTerm", _index.element, ::PostfixedTerm, null)
                    if (_r4 != null) _index.element = _r4.nextIndex

                    // BIND exp
                    exp = _memo.results.peek()

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i1
                    }

                    // ACT 0
                    val _r0 = _memo.results.peek()
                    if (_r0 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_r0.startIndex, _r0.nextIndex, _memo.input, _Thunk({ AST.Look(exp?.asResult()!!) }, _r0), true))
                    }

                    break
                }
            }
        }
    }


    fun NotTerm(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var exp: _Parser_Item? = null

        // AND 1
        var _start_i1 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 1
                    _start_i1 = _index.element

                    // CALLORVAR NOT_PRE
                    var _r2: _Parser_Item? = null
                    _r2 = _MemoCall(_memo, "NOT_PRE", _index.element, ::NOT_PRE, null)
                    if (_r2 != null) _index.element = _r2.nextIndex

                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // CALLORVAR PostfixedTerm
                    var _r4: _Parser_Item? = null
                    _r4 = _MemoCall(_memo, "PostfixedTerm", _index.element, ::PostfixedTerm, null)
                    if (_r4 != null) _index.element = _r4.nextIndex

                    // BIND exp
                    exp = _memo.results.peek()

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i1
                    }

                    // ACT 0
                    val _r0 = _memo.results.peek()
                    if (_r0 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_r0.startIndex, _r0.nextIndex, _memo.input, _Thunk({ AST.Not(exp?.asResult()!!) }, _r0), true))
                    }

                    break
                }
            }
        }
    }


    fun PostfixedTerm(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
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

                    // CALLORVAR MinMaxTerm
                    var _r4: _Parser_Item? = null
                    _r4 = _MemoCall(_memo, "MinMaxTerm", _index.element, ::MinMaxTerm, null)
                    if (_r4 != null) _index.element = _r4.nextIndex

                    // OR shortcut 3
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i3
                    } else {
                        _label = 3
                        continue
                    }

                    // CALLORVAR StarTerm
                    var _r5: _Parser_Item? = null
                    _r5 = _MemoCall(_memo, "StarTerm", _index.element, ::StarTerm, null)
                    if (_r5 != null) _index.element = _r5.nextIndex

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

                    // CALLORVAR PlusTerm
                    var _r6: _Parser_Item? = null
                    _r6 = _MemoCall(_memo, "PlusTerm", _index.element, ::PlusTerm, null)
                    if (_r6 != null) _index.element = _r6.nextIndex

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

                    // CALLORVAR QuesTerm
                    var _r7: _Parser_Item? = null
                    _r7 = _MemoCall(_memo, "QuesTerm", _index.element, ::QuesTerm, null)
                    if (_r7 != null) _index.element = _r7.nextIndex

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

                    // CALLORVAR Term
                    var _r8: _Parser_Item? = null
                    _r8 = _MemoCall(_memo, "Term", _index.element, ::Term, null)
                    if (_r8 != null) _index.element = _r8.nextIndex

                    _label = 0
                }
                // OR 0
                0 -> {
                    break
                }
            }
        }
    }


    fun MinMaxTerm(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var exp: _Parser_Item? = null
        var n: _Parser_Item? = null
        var x: _Parser_Item? = null

        // AND 1
        var _start_i1 = _index.element

        // AND 2
        var _start_i2 = _index.element

        // AND 3
        var _start_i3 = _index.element

        // AND 4
        var _start_i4 = _index.element

        // AND 11
        var _start_i11 = _index.element

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

                    // CALLORVAR Term
                    var _r6: _Parser_Item? = null
                    _r6 = _MemoCall(_memo, "Term", _index.element, ::Term, null)
                    if (_r6 != null) _index.element = _r6.nextIndex

                    // BIND exp
                    exp = _memo.results.peek()

                    // AND shortcut 4
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 4
                        continue
                    }

                    // CALLORVAR LEFT_BRACE
                    var _r7: _Parser_Item? = null
                    _r7 = _MemoCall(_memo, "LEFT_BRACE", _index.element, ::LEFT_BRACE, null)
                    if (_r7 != null) _index.element = _r7.nextIndex

                    _label = 4
                }
                // AND 4
                4 -> {
                    val _r4_2 = _memo.results.pop()
                    val _r4_1 = _memo.results.pop()

                    if (_r4_1 != null && _r4_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i4, _index.element, _memo.input, (_r4_1.results + _r4_2.results).filterNotNull(), true))
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

                    // CALLORVAR Number
                    var _r9: _Parser_Item? = null
                    _r9 = _MemoCall(_memo, "Number", _index.element, ::Number, null)
                    if (_r9 != null) _index.element = _r9.nextIndex

                    // BIND n
                    n = _memo.results.peek()

                    _label = 3
                }
                // AND 3
                3 -> {
                    val _r3_2 = _memo.results.pop()
                    val _r3_1 = _memo.results.pop()

                    if (_r3_1 != null && _r3_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i3, _index.element, _memo.input, (_r3_1.results + _r3_2.results).filterNotNull(), true))
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

                    // AND 11
                    _start_i11 = _index.element

                    // CALLORVAR COMMA
                    var _r12: _Parser_Item? = null
                    _r12 = _MemoCall(_memo, "COMMA", _index.element, ::COMMA, null)
                    if (_r12 != null) _index.element = _r12.nextIndex

                    // AND shortcut 11
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 11
                        continue
                    }

                    // CALLORVAR Number
                    var _r14: _Parser_Item? = null
                    _r14 = _MemoCall(_memo, "Number", _index.element, ::Number, null)
                    if (_r14 != null) _index.element = _r14.nextIndex

                    // BIND x
                    x = _memo.results.peek()

                    _label = 11
                }
                // AND 11
                11 -> {
                    val _r11_2 = _memo.results.pop()
                    val _r11_1 = _memo.results.pop()

                    if (_r11_1 != null && _r11_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i11, _index.element, _memo.input, (_r11_1.results + _r11_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i11
                    }

                    // QUES 10
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_index.element, _memo.input))
                    }
                    _label = 2
                }
                // AND 2
                2 -> {
                    val _r2_2 = _memo.results.pop()
                    val _r2_1 = _memo.results.pop()

                    if (_r2_1 != null && _r2_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i2, _index.element, _memo.input, (_r2_1.results + _r2_2.results).filterNotNull(), true))
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

                    // CALLORVAR RIGHT_BRACE
                    var _r15: _Parser_Item? = null
                    _r15 = _MemoCall(_memo, "RIGHT_BRACE", _index.element, ::RIGHT_BRACE, null)
                    if (_r15 != null) _index.element = _r15.nextIndex

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i1
                    }

                    // ACT 0
                    val _r0 = _memo.results.peek()
                    if (_r0 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_r0.startIndex, _r0.nextIndex, _memo.input, _Thunk({
            val exp = exp?.asResult()!!
            val min = n!!.inputs.joinToString("").toInt()
            val max = if (x != null && x!!.inputs.toList().isNotEmpty()) x!!.inputs.joinToString("").toInt() else min

            if (min < 0) throw MatcherException(n!!.startIndex, "min must be >= 0")
            if (max < 1) throw MatcherException(x!!.startIndex, "max must be > 1")
            if (max < min) throw MatcherException(n!!.startIndex, "max cannot be less that min for a MinMaxTerm")

            var res: AST.AstNode? = null
            for (i in 0 until min) {
                res = if (res != null) AST.And(res, exp) else exp
            }
            for (i in 0 until (max-min)) {
                res = if (res != null) AST.And(res, AST.Ques(exp)) else AST.Ques(exp)
            }
            res!!
        }, _r0), true))
                    }

                    break
                }
            }
        }
    }


    fun StarTerm(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var exp: _Parser_Item? = null

        // AND 1
        var _start_i1 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 1
                    _start_i1 = _index.element

                    // CALLORVAR Term
                    var _r3: _Parser_Item? = null
                    _r3 = _MemoCall(_memo, "Term", _index.element, ::Term, null)
                    if (_r3 != null) _index.element = _r3.nextIndex

                    // BIND exp
                    exp = _memo.results.peek()

                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // CALLORVAR STAR
                    var _r4: _Parser_Item? = null
                    _r4 = _MemoCall(_memo, "STAR", _index.element, ::STAR, null)
                    if (_r4 != null) _index.element = _r4.nextIndex

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i1
                    }

                    // ACT 0
                    val _r0 = _memo.results.peek()
                    if (_r0 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_r0.startIndex, _r0.nextIndex, _memo.input, _Thunk({ AST.Star(exp?.asResult()!!) }, _r0), true))
                    }

                    break
                }
            }
        }
    }


    fun PlusTerm(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var exp: _Parser_Item? = null

        // AND 1
        var _start_i1 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 1
                    _start_i1 = _index.element

                    // CALLORVAR Term
                    var _r3: _Parser_Item? = null
                    _r3 = _MemoCall(_memo, "Term", _index.element, ::Term, null)
                    if (_r3 != null) _index.element = _r3.nextIndex

                    // BIND exp
                    exp = _memo.results.peek()

                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // CALLORVAR PLUS
                    var _r4: _Parser_Item? = null
                    _r4 = _MemoCall(_memo, "PLUS", _index.element, ::PLUS, null)
                    if (_r4 != null) _index.element = _r4.nextIndex

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i1
                    }

                    // ACT 0
                    val _r0 = _memo.results.peek()
                    if (_r0 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_r0.startIndex, _r0.nextIndex, _memo.input, _Thunk({ AST.Plus(exp?.asResult()!!) }, _r0), true))
                    }

                    break
                }
            }
        }
    }


    fun QuesTerm(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var exp: _Parser_Item? = null

        // AND 1
        var _start_i1 = _index.element

        // AND 2
        var _start_i2 = _index.element

        // AND 3
        var _start_i3 = _index.element

        // NOT 7
        var _start_i7 = _index.element

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

                    // CALLORVAR Term
                    var _r5: _Parser_Item? = null
                    _r5 = _MemoCall(_memo, "Term", _index.element, ::Term, null)
                    if (_r5 != null) _index.element = _r5.nextIndex

                    // BIND exp
                    exp = _memo.results.peek()

                    // AND shortcut 3
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 3
                        continue
                    }

                    // CALLORVAR QUES
                    var _r6: _Parser_Item? = null
                    _r6 = _MemoCall(_memo, "QUES", _index.element, ::QUES, null)
                    if (_r6 != null) _index.element = _r6.nextIndex

                    _label = 3
                }
                // AND 3
                3 -> {
                    val _r3_2 = _memo.results.pop()
                    val _r3_1 = _memo.results.pop()

                    if (_r3_1 != null && _r3_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i3, _index.element, _memo.input, (_r3_1.results + _r3_2.results).filterNotNull(), true))
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

                    // NOT 7
                    _start_i7 = _index.element

                    // CALLORVAR OPEN
                    var _r8: _Parser_Item? = null
                    _r8 = _MemoCall(_memo, "OPEN", _index.element, ::OPEN, null)
                    if (_r8 != null) _index.element = _r8.nextIndex

                    // NOT 7
                    val _r7 = _memo.results.pop()
                    _memo.results.push(if (_r7 == null) _Parser_Item(_start_i7, _memo.input) else null)
                    _index.element = _start_i7
                    _label = 2
                }
                // AND 2
                2 -> {
                    val _r2_2 = _memo.results.pop()
                    val _r2_1 = _memo.results.pop()

                    if (_r2_1 != null && _r2_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i2, _index.element, _memo.input, (_r2_1.results + _r2_2.results).filterNotNull(), true))
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

                    // CALLORVAR SP
                    var _r9: _Parser_Item? = null
                    _r9 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r9 != null) _index.element = _r9.nextIndex

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i1
                    }

                    // ACT 0
                    val _r0 = _memo.results.peek()
                    if (_r0 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_r0.startIndex, _r0.nextIndex, _memo.input, _Thunk({ AST.Ques(exp?.asResult()!!) }, _r0), true))
                    }

                    break
                }
            }
        }
    }


    fun Term(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
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

                    // CALLORVAR InputClass
                    var _r5: _Parser_Item? = null
                    _r5 = _MemoCall(_memo, "InputClass", _index.element, ::InputClass, null)
                    if (_r5 != null) _index.element = _r5.nextIndex

                    // OR shortcut 4
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i4
                    } else {
                        _label = 4
                        continue
                    }

                    // CALLORVAR ParenTerm
                    var _r6: _Parser_Item? = null
                    _r6 = _MemoCall(_memo, "ParenTerm", _index.element, ::ParenTerm, null)
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

                    // CALLORVAR RuleCall
                    var _r7: _Parser_Item? = null
                    _r7 = _MemoCall(_memo, "RuleCall", _index.element, ::RuleCall, null)
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

                    // CALLORVAR CallOrVar
                    var _r8: _Parser_Item? = null
                    _r8 = _MemoCall(_memo, "CallOrVar", _index.element, ::CallOrVar, null)
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

                    // CALLORVAR Literal
                    var _r9: _Parser_Item? = null
                    _r9 = _MemoCall(_memo, "Literal", _index.element, ::Literal, null)
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

                    // CALLORVAR AnyTerm
                    var _r10: _Parser_Item? = null
                    _r10 = _MemoCall(_memo, "AnyTerm", _index.element, ::AnyTerm, null)
                    if (_r10 != null) _index.element = _r10.nextIndex

                    _label = 0
                }
                // OR 0
                0 -> {
                    break
                }
            }
        }
    }


    fun ParenTerm(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var exp: _Parser_Item? = null

        // AND 1
        var _start_i1 = _index.element

        // AND 2
        var _start_i2 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 1
                    _start_i1 = _index.element

                    // AND 2
                    _start_i2 = _index.element

                    // CALLORVAR OPEN
                    var _r3: _Parser_Item? = null
                    _r3 = _MemoCall(_memo, "OPEN", _index.element, ::OPEN, null)
                    if (_r3 != null) _index.element = _r3.nextIndex

                    // AND shortcut 2
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 2
                        continue
                    }

                    // CALLORVAR Disjunction
                    var _r5: _Parser_Item? = null
                    _r5 = _MemoCall(_memo, "Disjunction", _index.element, ::Disjunction, null)
                    if (_r5 != null) _index.element = _r5.nextIndex

                    // BIND exp
                    exp = _memo.results.peek()

                    _label = 2
                }
                // AND 2
                2 -> {
                    val _r2_2 = _memo.results.pop()
                    val _r2_1 = _memo.results.pop()

                    if (_r2_1 != null && _r2_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i2, _index.element, _memo.input, (_r2_1.results + _r2_2.results).filterNotNull(), true))
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

                    // CALLORVAR CLOSE
                    var _r6: _Parser_Item? = null
                    _r6 = _MemoCall(_memo, "CLOSE", _index.element, ::CLOSE, null)
                    if (_r6 != null) _index.element = _r6.nextIndex

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i1
                    }

                    // ACT 0
                    val _r0 = _memo.results.peek()
                    if (_r0 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_r0.startIndex, _r0.nextIndex, _memo.input, _Thunk({ exp?.asResult()!! }, _r0), true))
                    }

                    break
                }
            }
        }
    }


    fun AnyTerm(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // CALLORVAR PERIOD
                    var _r1: _Parser_Item? = null
                    _r1 = _MemoCall(_memo, "PERIOD", _index.element, ::PERIOD, null)
                    if (_r1 != null) _index.element = _r1.nextIndex

                    // ACT 0
                    val _r0 = _memo.results.peek()
                    if (_r0 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_r0.startIndex, _r0.nextIndex, _memo.input, _Thunk({ AST.Any }, _r0), true))
                    }

                    break
                }
            }
        }
    }


    fun RuleCall(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var name: _Parser_Item? = null
        var p: _Parser_Item? = null

        // AND 1
        var _start_i1 = _index.element

        // AND 2
        var _start_i2 = _index.element

        // AND 3
        var _start_i3 = _index.element

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

                    // CALLORVAR QualifiedId
                    var _r5: _Parser_Item? = null
                    _r5 = _MemoCall(_memo, "QualifiedId", _index.element, ::QualifiedId, null)
                    if (_r5 != null) _index.element = _r5.nextIndex

                    // BIND name
                    name = _memo.results.peek()

                    // AND shortcut 3
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 3
                        continue
                    }

                    // CALLORVAR OPEN
                    var _r6: _Parser_Item? = null
                    _r6 = _MemoCall(_memo, "OPEN", _index.element, ::OPEN, null)
                    if (_r6 != null) _index.element = _r6.nextIndex

                    _label = 3
                }
                // AND 3
                3 -> {
                    val _r3_2 = _memo.results.pop()
                    val _r3_1 = _memo.results.pop()

                    if (_r3_1 != null && _r3_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i3, _index.element, _memo.input, (_r3_1.results + _r3_2.results).filterNotNull(), true))
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

                    // CALLORVAR ParameterList
                    var _r9: _Parser_Item? = null
                    _r9 = _MemoCall(_memo, "ParameterList", _index.element, ::ParameterList, null)
                    if (_r9 != null) _index.element = _r9.nextIndex

                    // QUES 8
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_index.element, _memo.input))
                    }
                    // BIND p
                    p = _memo.results.peek()

                    _label = 2
                }
                // AND 2
                2 -> {
                    val _r2_2 = _memo.results.pop()
                    val _r2_1 = _memo.results.pop()

                    if (_r2_1 != null && _r2_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i2, _index.element, _memo.input, (_r2_1.results + _r2_2.results).filterNotNull(), true))
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

                    // CALLORVAR CLOSE
                    var _r10: _Parser_Item? = null
                    _r10 = _MemoCall(_memo, "CLOSE", _index.element, ::CLOSE, null)
                    if (_r10 != null) _index.element = _r10.nextIndex

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i1
                    }

                    // ACT 0
                    val _r0 = _memo.results.peek()
                    if (_r0 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_r0.startIndex, _r0.nextIndex, _memo.input, _Thunk({ AST.Call(name!!, p?.results?.filterNotNull() ?: emptyList()) }, _r0), true))
                    }

                    break
                }
            }
        }
    }


    fun ParameterList(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 1
        var _start_i1 = _index.element

        // STAR 3
        var _start_i3 = _index.element
        val _inp3 = arrayListOf<Char?>()
        val _res3 = arrayListOf<AST.AstNode?>()

        // AND 4
        var _start_i4 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 1
                    _start_i1 = _index.element

                    // CALLORVAR Parameter
                    var _r2: _Parser_Item? = null
                    _r2 = _MemoCall(_memo, "Parameter", _index.element, ::Parameter, null)
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

                    // CALLORVAR COMMA
                    var _r5: _Parser_Item? = null
                    _r5 = _MemoCall(_memo, "COMMA", _index.element, ::COMMA, null)
                    if (_r5 != null) _index.element = _r5.nextIndex

                    // AND shortcut 4
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 4
                        continue
                    }

                    // CALLORVAR Parameter
                    var _r6: _Parser_Item? = null
                    _r6 = _MemoCall(_memo, "Parameter", _index.element, ::Parameter, null)
                    if (_r6 != null) _index.element = _r6.nextIndex

                    _label = 4
                }
                // AND 4
                4 -> {
                    val _r4_2 = _memo.results.pop()
                    val _r4_1 = _memo.results.pop()

                    if (_r4_1 != null && _r4_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i4, _index.element, _memo.input, (_r4_1.results + _r4_2.results).filterNotNull(), true))
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
                        _memo.results.push(_Parser_Item(_start_i3, _index.element, _memo.input, _res3.filterNotNull(), true))
                    }

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i1
                    }

                    // ACT 0
                    val _r0 = _memo.results.peek()
                    if (_r0 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_r0.startIndex, _r0.nextIndex, _memo.input, _Thunk({ it.results.filterNotNull() }, _r0), true))
                    }

                    break
                }
            }
        }
    }


    fun Parameter(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // CALLORVAR Disjunction
                    var _r0: _Parser_Item? = null
                    _r0 = _MemoCall(_memo, "Disjunction", _index.element, ::Disjunction, null)
                    if (_r0 != null) _index.element = _r0.nextIndex

                    break
                }
            }
        }
    }


    fun CallOrVar(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var name: _Parser_Item? = null

        // AND 1
        var _start_i1 = _index.element

        // AND 2
        var _start_i2 = _index.element

        // NOT 3
        var _start_i3 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 1
                    _start_i1 = _index.element

                    // AND 2
                    _start_i2 = _index.element

                    // NOT 3
                    _start_i3 = _index.element

                    // CALLORVAR KEYWORD
                    var _r4: _Parser_Item? = null
                    _r4 = _MemoCall(_memo, "KEYWORD", _index.element, ::KEYWORD, null)
                    if (_r4 != null) _index.element = _r4.nextIndex

                    // NOT 3
                    val _r3 = _memo.results.pop()
                    _memo.results.push(if (_r3 == null) _Parser_Item(_start_i3, _memo.input) else null)
                    _index.element = _start_i3
                    // AND shortcut 2
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 2
                        continue
                    }

                    // CALLORVAR QualifiedId
                    var _r6: _Parser_Item? = null
                    _r6 = _MemoCall(_memo, "QualifiedId", _index.element, ::QualifiedId, null)
                    if (_r6 != null) _index.element = _r6.nextIndex

                    // BIND name
                    name = _memo.results.peek()

                    _label = 2
                }
                // AND 2
                2 -> {
                    val _r2_2 = _memo.results.pop()
                    val _r2_1 = _memo.results.pop()

                    if (_r2_1 != null && _r2_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i2, _index.element, _memo.input, (_r2_1.results + _r2_2.results).filterNotNull(), true))
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

                    // CALLORVAR SP
                    var _r7: _Parser_Item? = null
                    _r7 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r7 != null) _index.element = _r7.nextIndex

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i1
                    }

                    // ACT 0
                    val _r0 = _memo.results.peek()
                    if (_r0 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_r0.startIndex, _r0.nextIndex, _memo.input, _Thunk({ AST.CallOrVar(name!!) }, _r0), true))
                    }

                    break
                }
            }
        }
    }


    fun Literal(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var r: _Parser_Item? = null

        // OR 0
        var _start_i0 = _index.element

        // COND 1
        var _start_i1 = _index.element

        // AND 5
        var _start_i5 = _index.element

        // OR 6
        var _start_i6 = _index.element

        // AND 7
        var _start_i7 = _index.element

        // AND 8
        var _start_i8 = _index.element

        // AND 11
        var _start_i11 = _index.element

        // LOOK 14
        var _start_i14 = _index.element

        // LOOK 16
        var _start_i16 = _index.element

        // OR 17
        var _start_i17 = _index.element

        // OR 18
        var _start_i18 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // OR 0
                    _start_i0 = _index.element

                    // COND 1
                    _start_i1 = _index.element

                    // CALLORVAR Regexp
                    var _r3: _Parser_Item? = null
                    _r3 = _MemoCall(_memo, "Regexp", _index.element, ::Regexp, null)
                    if (_r3 != null) _index.element = _r3.nextIndex

                    // BIND r
                    r = _memo.results.peek()

                    // COND 1
                    val lambda1: (_Parser_Item) -> Boolean = { (AST.Regexp.isValid(r?.inputs?.joinToString("")!!)) }
                    if (_memo.results.peek() == null || !lambda1(_memo.results.peek()!!)) {
                        _memo.results.pop()
                        _memo.results.push(null)
                        _index.element = _start_i1
                    }

                    // OR shortcut 0
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i0
                    } else {
                        _label = 0
                        continue
                    }

                    // AND 5
                    _start_i5 = _index.element

                    // OR 6
                    _start_i6 = _index.element

                    // AND 7
                    _start_i7 = _index.element

                    // AND 8
                    _start_i8 = _index.element

                    // CALLORVAR NEW
                    var _r9: _Parser_Item? = null
                    _r9 = _MemoCall(_memo, "NEW", _index.element, ::NEW, null)
                    if (_r9 != null) _index.element = _r9.nextIndex

                    // AND shortcut 8
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 8
                        continue
                    }

                    // AND 11
                    _start_i11 = _index.element

                    // CALLORVAR GenericId
                    var _r12: _Parser_Item? = null
                    _r12 = _MemoCall(_memo, "GenericId", _index.element, ::GenericId, null)
                    if (_r12 != null) _index.element = _r12.nextIndex

                    // AND shortcut 11
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 11
                        continue
                    }

                    // CALLORVAR SP
                    var _r13: _Parser_Item? = null
                    _r13 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r13 != null) _index.element = _r13.nextIndex

                    _label = 11
                }
                // AND 11
                11 -> {
                    val _r11_2 = _memo.results.pop()
                    val _r11_1 = _memo.results.pop()

                    if (_r11_1 != null && _r11_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i11, _index.element, _memo.input, (_r11_1.results + _r11_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i11
                    }

                    // QUES 10
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_index.element, _memo.input))
                    }
                    _label = 8
                }
                // AND 8
                8 -> {
                    val _r8_2 = _memo.results.pop()
                    val _r8_1 = _memo.results.pop()

                    if (_r8_1 != null && _r8_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i8, _index.element, _memo.input, (_r8_1.results + _r8_2.results).filterNotNull(), true))
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

                    // LOOK 14
                    _start_i14 = _index.element

                    // LITERAL '{'
                    _ParseLiteralChar(_memo, _index, '{')

                    // LOOK 14
                    val _r14 = _memo.results.pop()
                    _memo.results.push(if (_r14 != null) _Parser_Item(_start_i14, _memo.input) else null)
                    _index.element = _start_i14

                    _label = 7
                }
                // AND 7
                7 -> {
                    val _r7_2 = _memo.results.pop()
                    val _r7_1 = _memo.results.pop()

                    if (_r7_1 != null && _r7_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i7, _index.element, _memo.input, (_r7_1.results + _r7_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i7
                    }

                    // OR shortcut 6
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i6
                    } else {
                        _label = 6
                        continue
                    }

                    // LOOK 16
                    _start_i16 = _index.element

                    // OR 17
                    _start_i17 = _index.element

                    // OR 18
                    _start_i18 = _index.element

                    // LITERAL '\u0022'
                    _ParseLiteralChar(_memo, _index, '\u0022')

                    // OR shortcut 18
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i18
                    } else {
                        _label = 18
                        continue
                    }

                    // LITERAL '\u0027'
                    _ParseLiteralChar(_memo, _index, '\u0027')

                    _label = 18
                }
                // OR 18
                18 -> {
                    // OR shortcut 17
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i17
                    } else {
                        _label = 17
                        continue
                    }

                    // LITERAL '{'
                    _ParseLiteralChar(_memo, _index, '{')

                    _label = 17
                }
                // OR 17
                17 -> {
                    // LOOK 16
                    val _r16 = _memo.results.pop()
                    _memo.results.push(if (_r16 != null) _Parser_Item(_start_i16, _memo.input) else null)
                    _index.element = _start_i16

                    _label = 6
                }
                // OR 6
                6 -> {
                    // AND shortcut 5
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 5
                        continue
                    }

                    // CALLORVAR KotlinCode
                    var _r22: _Parser_Item? = null
                    _r22 = _MemoCall(_memo, "KotlinCode", _index.element, ::KotlinCode, null)
                    if (_r22 != null) _index.element = _r22.nextIndex

                    _label = 5
                }
                // AND 5
                5 -> {
                    val _r5_2 = _memo.results.pop()
                    val _r5_1 = _memo.results.pop()

                    if (_r5_1 != null && _r5_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i5, _index.element, _memo.input, (_r5_1.results + _r5_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i5
                    }

                    // ACT 4
                    val _r4 = _memo.results.peek()
                    if (_r4 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_r4.startIndex, _r4.nextIndex, _memo.input, _Thunk({ AST.Code(it) }, _r4), true))
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


    fun Regexp(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 0
        var _start_i0 = _index.element

        // AND 1
        var _start_i1 = _index.element

        // AND 2
        var _start_i2 = _index.element

        // PLUS 4
        var _start_i4 = _index.element
        val _inp4 = arrayListOf<Char?>()
        val _res4 = arrayListOf<AST.AstNode?>()

        // OR 5
        var _start_i5 = _index.element

        // AND 6
        var _start_i6 = _index.element

        // AND 9
        var _start_i9 = _index.element

        // NOT 10
        var _start_i10 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 0
                    _start_i0 = _index.element

                    // AND 1
                    _start_i1 = _index.element

                    // AND 2
                    _start_i2 = _index.element

                    // LITERAL '/'
                    _ParseLiteralChar(_memo, _index, '/')

                    // AND shortcut 2
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 2
                        continue
                    }

                    // PLUS 4
                    _start_i4 = _index.element
                    _label = 4
                }
                // PLUS 4
                4 -> {
                    // OR 5
                    _start_i5 = _index.element

                    // AND 6
                    _start_i6 = _index.element

                    // LITERAL '\\'
                    _ParseLiteralChar(_memo, _index, '\\')

                    // AND shortcut 6
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 6
                        continue
                    }

                    // LITERAL '/'
                    _ParseLiteralChar(_memo, _index, '/')

                    _label = 6
                }
                // AND 6
                6 -> {
                    val _r6_2 = _memo.results.pop()
                    val _r6_1 = _memo.results.pop()

                    if (_r6_1 != null && _r6_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i6, _index.element, _memo.input, (_r6_1.results + _r6_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i6
                    }

                    // OR shortcut 5
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i5
                    } else {
                        _label = 5
                        continue
                    }

                    // AND 9
                    _start_i9 = _index.element

                    // NOT 10
                    _start_i10 = _index.element

                    // LITERAL '/'
                    _ParseLiteralChar(_memo, _index, '/')

                    // NOT 10
                    val _r10 = _memo.results.pop()
                    _memo.results.push(if (_r10 == null) _Parser_Item(_start_i10, _memo.input) else null)
                    _index.element = _start_i10
                    // AND shortcut 9
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 9
                        continue
                    }

                    // ANY
                    _ParseAny(_memo, _index)

                    _label = 9
                }
                // AND 9
                9 -> {
                    val _r9_2 = _memo.results.pop()
                    val _r9_1 = _memo.results.pop()

                    if (_r9_1 != null && _r9_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i9, _index.element, _memo.input, (_r9_1.results + _r9_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i9
                    }

                    _label = 5
                }
                // OR 5
                5 -> {
                    // PLUS 4
                    val _r4 = _memo.results.pop()
                    if (_r4 != null) {
                        _res4 += _r4.results
                        _label = 4
                        continue
                    } else {
                        if (_index.element > _start_i4) {
                            _memo.results.push(_Parser_Item(_start_i4, _index.element, _memo.input, _res4.filterNotNull(), true))
                        } else {
                            _memo.results.push(null)
                        }
                    }

                    _label = 2
                }
                // AND 2
                2 -> {
                    val _r2_2 = _memo.results.pop()
                    val _r2_1 = _memo.results.pop()

                    if (_r2_1 != null && _r2_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i2, _index.element, _memo.input, (_r2_1.results + _r2_2.results).filterNotNull(), true))
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

                    // LITERAL '/'
                    _ParseLiteralChar(_memo, _index, '/')

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i1
                    }

                    // AND shortcut 0
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 0
                        continue
                    }

                    // CALLORVAR SP
                    var _r14: _Parser_Item? = null
                    _r14 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r14 != null) _index.element = _r14.nextIndex

                    _label = 0
                }
                // AND 0
                0 -> {
                    val _r0_2 = _memo.results.pop()
                    val _r0_1 = _memo.results.pop()

                    if (_r0_1 != null && _r0_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i0, _index.element, _memo.input, (_r0_1.results + _r0_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i0
                    }

                    break
                }
            }
        }
    }


    fun InputClass(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var c: _Parser_Item? = null

        // AND 1
        var _start_i1 = _index.element

        // AND 2
        var _start_i2 = _index.element

        // AND 3
        var _start_i3 = _index.element

        // AND 4
        var _start_i4 = _index.element

        // PLUS 8
        var _start_i8 = _index.element
        val _inp8 = arrayListOf<Char?>()
        val _res8 = arrayListOf<AST.AstNode?>()

        // OR 9
        var _start_i9 = _index.element

        // AND 11
        var _start_i11 = _index.element

        // LOOK 12
        var _start_i12 = _index.element

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

                    // LITERAL '['
                    _ParseLiteralChar(_memo, _index, '[')

                    // AND shortcut 4
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 4
                        continue
                    }

                    // CALLORVAR SP
                    var _r6: _Parser_Item? = null
                    _r6 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r6 != null) _index.element = _r6.nextIndex

                    _label = 4
                }
                // AND 4
                4 -> {
                    val _r4_2 = _memo.results.pop()
                    val _r4_1 = _memo.results.pop()

                    if (_r4_1 != null && _r4_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i4, _index.element, _memo.input, (_r4_1.results + _r4_2.results).filterNotNull(), true))
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

                    // PLUS 8
                    _start_i8 = _index.element
                    _label = 8
                }
                // PLUS 8
                8 -> {
                    // OR 9
                    _start_i9 = _index.element

                    // CALLORVAR ClassRange
                    var _r10: _Parser_Item? = null
                    _r10 = _MemoCall(_memo, "ClassRange", _index.element, ::ClassRange, null)
                    if (_r10 != null) _index.element = _r10.nextIndex

                    // OR shortcut 9
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i9
                    } else {
                        _label = 9
                        continue
                    }

                    // AND 11
                    _start_i11 = _index.element

                    // LOOK 12
                    _start_i12 = _index.element

                    // LITERAL '\u0027'
                    _ParseLiteralChar(_memo, _index, '\u0027')

                    // LOOK 12
                    val _r12 = _memo.results.pop()
                    _memo.results.push(if (_r12 != null) _Parser_Item(_start_i12, _memo.input) else null)
                    _index.element = _start_i12

                    // AND shortcut 11
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 11
                        continue
                    }

                    // CALLORVAR Literal
                    var _r14: _Parser_Item? = null
                    _r14 = _MemoCall(_memo, "Literal", _index.element, ::Literal, null)
                    if (_r14 != null) _index.element = _r14.nextIndex

                    _label = 11
                }
                // AND 11
                11 -> {
                    val _r11_2 = _memo.results.pop()
                    val _r11_1 = _memo.results.pop()

                    if (_r11_1 != null && _r11_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i11, _index.element, _memo.input, (_r11_1.results + _r11_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i11
                    }

                    _label = 9
                }
                // OR 9
                9 -> {
                    // PLUS 8
                    val _r8 = _memo.results.pop()
                    if (_r8 != null) {
                        _res8 += _r8.results
                        _label = 8
                        continue
                    } else {
                        if (_index.element > _start_i8) {
                            _memo.results.push(_Parser_Item(_start_i8, _index.element, _memo.input, _res8.filterNotNull(), true))
                        } else {
                            _memo.results.push(null)
                        }
                    }

                    // BIND c
                    c = _memo.results.peek()

                    _label = 3
                }
                // AND 3
                3 -> {
                    val _r3_2 = _memo.results.pop()
                    val _r3_1 = _memo.results.pop()

                    if (_r3_1 != null && _r3_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i3, _index.element, _memo.input, (_r3_1.results + _r3_2.results).filterNotNull(), true))
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

                    // LITERAL ']'
                    _ParseLiteralChar(_memo, _index, ']')

                    _label = 2
                }
                // AND 2
                2 -> {
                    val _r2_2 = _memo.results.pop()
                    val _r2_1 = _memo.results.pop()

                    if (_r2_1 != null && _r2_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i2, _index.element, _memo.input, (_r2_1.results + _r2_2.results).filterNotNull(), true))
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

                    // CALLORVAR SP
                    var _r16: _Parser_Item? = null
                    _r16 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r16 != null) _index.element = _r16.nextIndex

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i1
                    }

                    // ACT 0
                    val _r0 = _memo.results.peek()
                    if (_r0 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_r0.startIndex, _r0.nextIndex, _memo.input, _Thunk({ AST.InputClass(c!!.results.filterNotNull()) }, _r0), true))
                    }

                    break
                }
            }
        }
    }


    fun ClassRange(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var from: _Parser_Item? = null
        var to: _Parser_Item? = null

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

        // LOOK 8
        var _start_i8 = _index.element

        // LOOK 15
        var _start_i15 = _index.element

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

                    // LOOK 8
                    _start_i8 = _index.element

                    // LITERAL '\u0027'
                    _ParseLiteralChar(_memo, _index, '\u0027')

                    // LOOK 8
                    val _r8 = _memo.results.pop()
                    _memo.results.push(if (_r8 != null) _Parser_Item(_start_i8, _memo.input) else null)
                    _index.element = _start_i8

                    // AND shortcut 7
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 7
                        continue
                    }

                    // CALLORVAR Literal
                    var _r11: _Parser_Item? = null
                    _r11 = _MemoCall(_memo, "Literal", _index.element, ::Literal, null)
                    if (_r11 != null) _index.element = _r11.nextIndex

                    // BIND from
                    from = _memo.results.peek()

                    _label = 7
                }
                // AND 7
                7 -> {
                    val _r7_2 = _memo.results.pop()
                    val _r7_1 = _memo.results.pop()

                    if (_r7_1 != null && _r7_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i7, _index.element, _memo.input, (_r7_1.results + _r7_2.results).filterNotNull(), true))
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

                    // CALLORVAR SP
                    var _r12: _Parser_Item? = null
                    _r12 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r12 != null) _index.element = _r12.nextIndex

                    _label = 6
                }
                // AND 6
                6 -> {
                    val _r6_2 = _memo.results.pop()
                    val _r6_1 = _memo.results.pop()

                    if (_r6_1 != null && _r6_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i6, _index.element, _memo.input, (_r6_1.results + _r6_2.results).filterNotNull(), true))
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

                    // LITERAL '-'
                    _ParseLiteralChar(_memo, _index, '-')

                    _label = 5
                }
                // AND 5
                5 -> {
                    val _r5_2 = _memo.results.pop()
                    val _r5_1 = _memo.results.pop()

                    if (_r5_1 != null && _r5_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i5, _index.element, _memo.input, (_r5_1.results + _r5_2.results).filterNotNull(), true))
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

                    // CALLORVAR SP
                    var _r14: _Parser_Item? = null
                    _r14 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r14 != null) _index.element = _r14.nextIndex

                    _label = 4
                }
                // AND 4
                4 -> {
                    val _r4_2 = _memo.results.pop()
                    val _r4_1 = _memo.results.pop()

                    if (_r4_1 != null && _r4_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i4, _index.element, _memo.input, (_r4_1.results + _r4_2.results).filterNotNull(), true))
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

                    // LOOK 15
                    _start_i15 = _index.element

                    // LITERAL '\u0027'
                    _ParseLiteralChar(_memo, _index, '\u0027')

                    // LOOK 15
                    val _r15 = _memo.results.pop()
                    _memo.results.push(if (_r15 != null) _Parser_Item(_start_i15, _memo.input) else null)
                    _index.element = _start_i15

                    _label = 3
                }
                // AND 3
                3 -> {
                    val _r3_2 = _memo.results.pop()
                    val _r3_1 = _memo.results.pop()

                    if (_r3_1 != null && _r3_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i3, _index.element, _memo.input, (_r3_1.results + _r3_2.results).filterNotNull(), true))
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

                    // CALLORVAR Literal
                    var _r18: _Parser_Item? = null
                    _r18 = _MemoCall(_memo, "Literal", _index.element, ::Literal, null)
                    if (_r18 != null) _index.element = _r18.nextIndex

                    // BIND to
                    to = _memo.results.peek()

                    _label = 2
                }
                // AND 2
                2 -> {
                    val _r2_2 = _memo.results.pop()
                    val _r2_1 = _memo.results.pop()

                    if (_r2_1 != null && _r2_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i2, _index.element, _memo.input, (_r2_1.results + _r2_2.results).filterNotNull(), true))
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

                    // CALLORVAR SP
                    var _r19: _Parser_Item? = null
                    _r19 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r19 != null) _index.element = _r19.nextIndex

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i1
                    }

                    // ACT 0
                    val _r0 = _memo.results.peek()
                    if (_r0 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_r0.startIndex, _r0.nextIndex, _memo.input, _Thunk({
            val chFrom = AST.ClassRange.getChar(from?.asResult())!!
            val chTo = AST.ClassRange.getChar(to?.asResult())!!

            val range = arrayListOf<Char>()
            if (chFrom > chTo) {
                range += chFrom
                range += chTo
            } else {
                for (ch in chFrom..chTo) {
                    range += ch
                }
            }
            AST.ClassRange(it, range)
        }, _r0), true))
                    }

                    break
                }
            }
        }
    }


    fun KotlinCode(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var code: _Parser_Item? = null

        // AND 1
        var _start_i1 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 1
                    _start_i1 = _index.element

                    // CALLORVAR KotlinCodeItem
                    var _r3: _Parser_Item? = null
                    _r3 = _MemoCall(_memo, "KotlinCodeItem", _index.element, ::KotlinCodeItem, null)
                    if (_r3 != null) _index.element = _r3.nextIndex

                    // BIND code
                    code = _memo.results.peek()

                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // CALLORVAR SP
                    var _r4: _Parser_Item? = null
                    _r4 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r4 != null) _index.element = _r4.nextIndex

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i1
                    }

                    // ACT 0
                    val _r0 = _memo.results.peek()
                    if (_r0 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_r0.startIndex, _r0.nextIndex, _memo.input, _Thunk({ AST.Code(code!!) }, _r0), true))
                    }

                    break
                }
            }
        }
    }


    fun KotlinCodeItem(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
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

        // AND 3
        var _start_i3 = _index.element

        // AND 4
        var _start_i4 = _index.element

        // STAR 6
        var _start_i6 = _index.element
        val _inp6 = arrayListOf<Char?>()
        val _res6 = arrayListOf<AST.AstNode?>()

        // AND 7
        var _start_i7 = _index.element

        // NOT 8
        var _start_i8 = _index.element

        // OR 10
        var _start_i10 = _index.element

        // OR 11
        var _start_i11 = _index.element

        // OR 12
        var _start_i12 = _index.element

        // AND 18
        var _start_i18 = _index.element

        // AND 19
        var _start_i19 = _index.element

        // STAR 21
        var _start_i21 = _index.element
        val _inp21 = arrayListOf<Char?>()
        val _res21 = arrayListOf<AST.AstNode?>()

        // AND 22
        var _start_i22 = _index.element

        // NOT 23
        var _start_i23 = _index.element

        // OR 25
        var _start_i25 = _index.element

        // OR 26
        var _start_i26 = _index.element

        // OR 27
        var _start_i27 = _index.element

        // AND 33
        var _start_i33 = _index.element

        // AND 34
        var _start_i34 = _index.element

        // STAR 36
        var _start_i36 = _index.element
        val _inp36 = arrayListOf<Char?>()
        val _res36 = arrayListOf<AST.AstNode?>()

        // OR 37
        var _start_i37 = _index.element

        // OR 38
        var _start_i38 = _index.element

        // OR 39
        var _start_i39 = _index.element

        // AND 43
        var _start_i43 = _index.element

        // NOT 44
        var _start_i44 = _index.element

        // AND 48
        var _start_i48 = _index.element

        // AND 49
        var _start_i49 = _index.element

        // STAR 51
        var _start_i51 = _index.element
        val _inp51 = arrayListOf<Char?>()
        val _res51 = arrayListOf<AST.AstNode?>()

        // OR 52
        var _start_i52 = _index.element

        // OR 53
        var _start_i53 = _index.element

        // OR 54
        var _start_i54 = _index.element

        // AND 58
        var _start_i58 = _index.element

        // NOT 59
        var _start_i59 = _index.element

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

                    // AND 3
                    _start_i3 = _index.element

                    // AND 4
                    _start_i4 = _index.element

                    // LITERAL '{'
                    _ParseLiteralChar(_memo, _index, '{')

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
                    // AND 7
                    _start_i7 = _index.element

                    // NOT 8
                    _start_i8 = _index.element

                    // LITERAL '}'
                    _ParseLiteralChar(_memo, _index, '}')

                    // NOT 8
                    val _r8 = _memo.results.pop()
                    _memo.results.push(if (_r8 == null) _Parser_Item(_start_i8, _memo.input) else null)
                    _index.element = _start_i8
                    // AND shortcut 7
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 7
                        continue
                    }

                    // OR 10
                    _start_i10 = _index.element

                    // OR 11
                    _start_i11 = _index.element

                    // OR 12
                    _start_i12 = _index.element

                    // CALLORVAR EOL
                    var _r13: _Parser_Item? = null
                    _r13 = _MemoCall(_memo, "EOL", _index.element, ::EOL, null)
                    if (_r13 != null) _index.element = _r13.nextIndex

                    // OR shortcut 12
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i12
                    } else {
                        _label = 12
                        continue
                    }

                    // CALLORVAR Comment
                    var _r14: _Parser_Item? = null
                    _r14 = _MemoCall(_memo, "Comment", _index.element, ::Comment, null)
                    if (_r14 != null) _index.element = _r14.nextIndex

                    _label = 12
                }
                // OR 12
                12 -> {
                    // OR shortcut 11
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i11
                    } else {
                        _label = 11
                        continue
                    }

                    // CALLORVAR KotlinCodeItem
                    var _r15: _Parser_Item? = null
                    _r15 = _MemoCall(_memo, "KotlinCodeItem", _index.element, ::KotlinCodeItem, null)
                    if (_r15 != null) _index.element = _r15.nextIndex

                    _label = 11
                }
                // OR 11
                11 -> {
                    // OR shortcut 10
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i10
                    } else {
                        _label = 10
                        continue
                    }

                    // ANY
                    _ParseAny(_memo, _index)

                    _label = 10
                }
                // OR 10
                10 -> {
                    _label = 7
                }
                // AND 7
                7 -> {
                    val _r7_2 = _memo.results.pop()
                    val _r7_1 = _memo.results.pop()

                    if (_r7_1 != null && _r7_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i7, _index.element, _memo.input, (_r7_1.results + _r7_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i7
                    }

                    // STAR 6
                    val _r6 = _memo.results.pop()
                    if (_r6 != null) {
                        _res6 += _r6.results
                        _label = 6
                        continue
                    } else {
                        _memo.results.push(_Parser_Item(_start_i6, _index.element, _memo.input, _res6.filterNotNull(), true))
                    }

                    _label = 4
                }
                // AND 4
                4 -> {
                    val _r4_2 = _memo.results.pop()
                    val _r4_1 = _memo.results.pop()

                    if (_r4_1 != null && _r4_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i4, _index.element, _memo.input, (_r4_1.results + _r4_2.results).filterNotNull(), true))
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

                    // LITERAL '}'
                    _ParseLiteralChar(_memo, _index, '}')

                    _label = 3
                }
                // AND 3
                3 -> {
                    val _r3_2 = _memo.results.pop()
                    val _r3_1 = _memo.results.pop()

                    if (_r3_1 != null && _r3_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i3, _index.element, _memo.input, (_r3_1.results + _r3_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i3
                    }

                    // OR shortcut 2
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i2
                    } else {
                        _label = 2
                        continue
                    }

                    // AND 18
                    _start_i18 = _index.element

                    // AND 19
                    _start_i19 = _index.element

                    // LITERAL '('
                    _ParseLiteralChar(_memo, _index, '(')

                    // AND shortcut 19
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 19
                        continue
                    }

                    // STAR 21
                    _start_i21 = _index.element
                    _label = 21
                }
                // STAR 21
                21 -> {
                    // AND 22
                    _start_i22 = _index.element

                    // NOT 23
                    _start_i23 = _index.element

                    // LITERAL ')'
                    _ParseLiteralChar(_memo, _index, ')')

                    // NOT 23
                    val _r23 = _memo.results.pop()
                    _memo.results.push(if (_r23 == null) _Parser_Item(_start_i23, _memo.input) else null)
                    _index.element = _start_i23
                    // AND shortcut 22
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 22
                        continue
                    }

                    // OR 25
                    _start_i25 = _index.element

                    // OR 26
                    _start_i26 = _index.element

                    // OR 27
                    _start_i27 = _index.element

                    // CALLORVAR EOL
                    var _r28: _Parser_Item? = null
                    _r28 = _MemoCall(_memo, "EOL", _index.element, ::EOL, null)
                    if (_r28 != null) _index.element = _r28.nextIndex

                    // OR shortcut 27
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i27
                    } else {
                        _label = 27
                        continue
                    }

                    // CALLORVAR Comment
                    var _r29: _Parser_Item? = null
                    _r29 = _MemoCall(_memo, "Comment", _index.element, ::Comment, null)
                    if (_r29 != null) _index.element = _r29.nextIndex

                    _label = 27
                }
                // OR 27
                27 -> {
                    // OR shortcut 26
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i26
                    } else {
                        _label = 26
                        continue
                    }

                    // CALLORVAR KotlinCodeItem
                    var _r30: _Parser_Item? = null
                    _r30 = _MemoCall(_memo, "KotlinCodeItem", _index.element, ::KotlinCodeItem, null)
                    if (_r30 != null) _index.element = _r30.nextIndex

                    _label = 26
                }
                // OR 26
                26 -> {
                    // OR shortcut 25
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i25
                    } else {
                        _label = 25
                        continue
                    }

                    // ANY
                    _ParseAny(_memo, _index)

                    _label = 25
                }
                // OR 25
                25 -> {
                    _label = 22
                }
                // AND 22
                22 -> {
                    val _r22_2 = _memo.results.pop()
                    val _r22_1 = _memo.results.pop()

                    if (_r22_1 != null && _r22_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i22, _index.element, _memo.input, (_r22_1.results + _r22_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i22
                    }

                    // STAR 21
                    val _r21 = _memo.results.pop()
                    if (_r21 != null) {
                        _res21 += _r21.results
                        _label = 21
                        continue
                    } else {
                        _memo.results.push(_Parser_Item(_start_i21, _index.element, _memo.input, _res21.filterNotNull(), true))
                    }

                    _label = 19
                }
                // AND 19
                19 -> {
                    val _r19_2 = _memo.results.pop()
                    val _r19_1 = _memo.results.pop()

                    if (_r19_1 != null && _r19_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i19, _index.element, _memo.input, (_r19_1.results + _r19_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i19
                    }

                    // AND shortcut 18
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 18
                        continue
                    }

                    // LITERAL ')'
                    _ParseLiteralChar(_memo, _index, ')')

                    _label = 18
                }
                // AND 18
                18 -> {
                    val _r18_2 = _memo.results.pop()
                    val _r18_1 = _memo.results.pop()

                    if (_r18_1 != null && _r18_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i18, _index.element, _memo.input, (_r18_1.results + _r18_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i18
                    }

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

                    // AND 33
                    _start_i33 = _index.element

                    // AND 34
                    _start_i34 = _index.element

                    // LITERAL '\u0027'
                    _ParseLiteralChar(_memo, _index, '\u0027')

                    // AND shortcut 34
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 34
                        continue
                    }

                    // STAR 36
                    _start_i36 = _index.element
                    _label = 36
                }
                // STAR 36
                36 -> {
                    // OR 37
                    _start_i37 = _index.element

                    // OR 38
                    _start_i38 = _index.element

                    // OR 39
                    _start_i39 = _index.element

                    // CALLORVAR EOL
                    var _r40: _Parser_Item? = null
                    _r40 = _MemoCall(_memo, "EOL", _index.element, ::EOL, null)
                    if (_r40 != null) _index.element = _r40.nextIndex

                    // OR shortcut 39
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i39
                    } else {
                        _label = 39
                        continue
                    }

                    // LITERAL "\u005c\u0027"
                    _ParseLiteralString(_memo, _index, "\u005c\u0027")

                    _label = 39
                }
                // OR 39
                39 -> {
                    // OR shortcut 38
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i38
                    } else {
                        _label = 38
                        continue
                    }

                    // LITERAL "\u005c\u005c"
                    _ParseLiteralString(_memo, _index, "\u005c\u005c")

                    _label = 38
                }
                // OR 38
                38 -> {
                    // OR shortcut 37
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i37
                    } else {
                        _label = 37
                        continue
                    }

                    // AND 43
                    _start_i43 = _index.element

                    // NOT 44
                    _start_i44 = _index.element

                    // LITERAL '\u0027'
                    _ParseLiteralChar(_memo, _index, '\u0027')

                    // NOT 44
                    val _r44 = _memo.results.pop()
                    _memo.results.push(if (_r44 == null) _Parser_Item(_start_i44, _memo.input) else null)
                    _index.element = _start_i44
                    // AND shortcut 43
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 43
                        continue
                    }

                    // ANY
                    _ParseAny(_memo, _index)

                    _label = 43
                }
                // AND 43
                43 -> {
                    val _r43_2 = _memo.results.pop()
                    val _r43_1 = _memo.results.pop()

                    if (_r43_1 != null && _r43_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i43, _index.element, _memo.input, (_r43_1.results + _r43_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i43
                    }

                    _label = 37
                }
                // OR 37
                37 -> {
                    // STAR 36
                    val _r36 = _memo.results.pop()
                    if (_r36 != null) {
                        _res36 += _r36.results
                        _label = 36
                        continue
                    } else {
                        _memo.results.push(_Parser_Item(_start_i36, _index.element, _memo.input, _res36.filterNotNull(), true))
                    }

                    _label = 34
                }
                // AND 34
                34 -> {
                    val _r34_2 = _memo.results.pop()
                    val _r34_1 = _memo.results.pop()

                    if (_r34_1 != null && _r34_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i34, _index.element, _memo.input, (_r34_1.results + _r34_2.results).filterNotNull(), true))
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

                    // LITERAL '\u0027'
                    _ParseLiteralChar(_memo, _index, '\u0027')

                    _label = 33
                }
                // AND 33
                33 -> {
                    val _r33_2 = _memo.results.pop()
                    val _r33_1 = _memo.results.pop()

                    if (_r33_1 != null && _r33_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i33, _index.element, _memo.input, (_r33_1.results + _r33_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i33
                    }

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

                    // AND 48
                    _start_i48 = _index.element

                    // AND 49
                    _start_i49 = _index.element

                    // LITERAL '\u0022'
                    _ParseLiteralChar(_memo, _index, '\u0022')

                    // AND shortcut 49
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 49
                        continue
                    }

                    // STAR 51
                    _start_i51 = _index.element
                    _label = 51
                }
                // STAR 51
                51 -> {
                    // OR 52
                    _start_i52 = _index.element

                    // OR 53
                    _start_i53 = _index.element

                    // OR 54
                    _start_i54 = _index.element

                    // CALLORVAR EOL
                    var _r55: _Parser_Item? = null
                    _r55 = _MemoCall(_memo, "EOL", _index.element, ::EOL, null)
                    if (_r55 != null) _index.element = _r55.nextIndex

                    // OR shortcut 54
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i54
                    } else {
                        _label = 54
                        continue
                    }

                    // LITERAL "\u005c\u0022"
                    _ParseLiteralString(_memo, _index, "\u005c\u0022")

                    _label = 54
                }
                // OR 54
                54 -> {
                    // OR shortcut 53
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i53
                    } else {
                        _label = 53
                        continue
                    }

                    // LITERAL "\u005c\u005c"
                    _ParseLiteralString(_memo, _index, "\u005c\u005c")

                    _label = 53
                }
                // OR 53
                53 -> {
                    // OR shortcut 52
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i52
                    } else {
                        _label = 52
                        continue
                    }

                    // AND 58
                    _start_i58 = _index.element

                    // NOT 59
                    _start_i59 = _index.element

                    // LITERAL '\u0022'
                    _ParseLiteralChar(_memo, _index, '\u0022')

                    // NOT 59
                    val _r59 = _memo.results.pop()
                    _memo.results.push(if (_r59 == null) _Parser_Item(_start_i59, _memo.input) else null)
                    _index.element = _start_i59
                    // AND shortcut 58
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 58
                        continue
                    }

                    // ANY
                    _ParseAny(_memo, _index)

                    _label = 58
                }
                // AND 58
                58 -> {
                    val _r58_2 = _memo.results.pop()
                    val _r58_1 = _memo.results.pop()

                    if (_r58_1 != null && _r58_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i58, _index.element, _memo.input, (_r58_1.results + _r58_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i58
                    }

                    _label = 52
                }
                // OR 52
                52 -> {
                    // STAR 51
                    val _r51 = _memo.results.pop()
                    if (_r51 != null) {
                        _res51 += _r51.results
                        _label = 51
                        continue
                    } else {
                        _memo.results.push(_Parser_Item(_start_i51, _index.element, _memo.input, _res51.filterNotNull(), true))
                    }

                    _label = 49
                }
                // AND 49
                49 -> {
                    val _r49_2 = _memo.results.pop()
                    val _r49_1 = _memo.results.pop()

                    if (_r49_1 != null && _r49_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i49, _index.element, _memo.input, (_r49_1.results + _r49_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i49
                    }

                    // AND shortcut 48
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 48
                        continue
                    }

                    // LITERAL '\u0022'
                    _ParseLiteralChar(_memo, _index, '\u0022')

                    _label = 48
                }
                // AND 48
                48 -> {
                    val _r48_2 = _memo.results.pop()
                    val _r48_1 = _memo.results.pop()

                    if (_r48_1 != null && _r48_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i48, _index.element, _memo.input, (_r48_1.results + _r48_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i48
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


    fun Identifier(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var id: _Parser_Item? = null

        // AND 1
        var _start_i1 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 1
                    _start_i1 = _index.element

                    // CALLORVAR Ident
                    var _r3: _Parser_Item? = null
                    _r3 = _MemoCall(_memo, "Ident", _index.element, ::Ident, null)
                    if (_r3 != null) _index.element = _r3.nextIndex

                    // BIND id
                    id = _memo.results.peek()

                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // CALLORVAR SP
                    var _r4: _Parser_Item? = null
                    _r4 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r4 != null) _index.element = _r4.nextIndex

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i1
                    }

                    // ACT 0
                    val _r0 = _memo.results.peek()
                    if (_r0 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_r0.startIndex, _r0.nextIndex, _memo.input, _Thunk({ id?.asResult()!! }, _r0), true))
                    }

                    break
                }
            }
        }
    }


    fun Ident(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var id: _Parser_Item? = null

        // AND 2
        var _start_i2 = _index.element

        // STAR 4
        var _start_i4 = _index.element
        val _inp4 = arrayListOf<Char?>()
        val _res4 = arrayListOf<AST.AstNode?>()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 2
                    _start_i2 = _index.element

                    // CALLORVAR IdentBegin
                    var _r3: _Parser_Item? = null
                    _r3 = _MemoCall(_memo, "IdentBegin", _index.element, ::IdentBegin, null)
                    if (_r3 != null) _index.element = _r3.nextIndex

                    // AND shortcut 2
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 2
                        continue
                    }

                    // STAR 4
                    _start_i4 = _index.element
                    _label = 4
                }
                // STAR 4
                4 -> {
                    // CALLORVAR IdentBody
                    var _r5: _Parser_Item? = null
                    _r5 = _MemoCall(_memo, "IdentBody", _index.element, ::IdentBody, null)
                    if (_r5 != null) _index.element = _r5.nextIndex

                    // STAR 4
                    val _r4 = _memo.results.pop()
                    if (_r4 != null) {
                        _res4 += _r4.results
                        _label = 4
                        continue
                    } else {
                        _memo.results.push(_Parser_Item(_start_i4, _index.element, _memo.input, _res4.filterNotNull(), true))
                    }

                    _label = 2
                }
                // AND 2
                2 -> {
                    val _r2_2 = _memo.results.pop()
                    val _r2_1 = _memo.results.pop()

                    if (_r2_1 != null && _r2_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i2, _index.element, _memo.input, (_r2_1.results + _r2_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i2
                    }

                    // BIND id
                    id = _memo.results.peek()

                    // ACT 0
                    val _r0 = _memo.results.peek()
                    if (_r0 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_r0.startIndex, _r0.nextIndex, _memo.input, _Thunk({ AST.Idfr(id!!) }, _r0), true))
                    }

                    break
                }
            }
        }
    }


    fun IdentBegin(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

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

                    // COND 0
                    val lambda0: (_Parser_Item) -> Boolean = { (it.asInput().isJavaIdentifierStart()) }
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


    fun IdentBody(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

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

                    // COND 0
                    val lambda0: (_Parser_Item) -> Boolean = { (it.asInput().isJavaIdentifierPart()) }
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


    fun QualifiedId(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var id: _Parser_Item? = null

        // AND 2
        var _start_i2 = _index.element

        // STAR 4
        var _start_i4 = _index.element
        val _inp4 = arrayListOf<Char?>()
        val _res4 = arrayListOf<AST.AstNode?>()

        // AND 5
        var _start_i5 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 2
                    _start_i2 = _index.element

                    // CALLORVAR Ident
                    var _r3: _Parser_Item? = null
                    _r3 = _MemoCall(_memo, "Ident", _index.element, ::Ident, null)
                    if (_r3 != null) _index.element = _r3.nextIndex

                    // AND shortcut 2
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 2
                        continue
                    }

                    // STAR 4
                    _start_i4 = _index.element
                    _label = 4
                }
                // STAR 4
                4 -> {
                    // AND 5
                    _start_i5 = _index.element

                    // CALLORVAR DOT
                    var _r6: _Parser_Item? = null
                    _r6 = _MemoCall(_memo, "DOT", _index.element, ::DOT, null)
                    if (_r6 != null) _index.element = _r6.nextIndex

                    // AND shortcut 5
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 5
                        continue
                    }

                    // CALLORVAR Ident
                    var _r7: _Parser_Item? = null
                    _r7 = _MemoCall(_memo, "Ident", _index.element, ::Ident, null)
                    if (_r7 != null) _index.element = _r7.nextIndex

                    _label = 5
                }
                // AND 5
                5 -> {
                    val _r5_2 = _memo.results.pop()
                    val _r5_1 = _memo.results.pop()

                    if (_r5_1 != null && _r5_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i5, _index.element, _memo.input, (_r5_1.results + _r5_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i5
                    }

                    // STAR 4
                    val _r4 = _memo.results.pop()
                    if (_r4 != null) {
                        _res4 += _r4.results
                        _label = 4
                        continue
                    } else {
                        _memo.results.push(_Parser_Item(_start_i4, _index.element, _memo.input, _res4.filterNotNull(), true))
                    }

                    _label = 2
                }
                // AND 2
                2 -> {
                    val _r2_2 = _memo.results.pop()
                    val _r2_1 = _memo.results.pop()

                    if (_r2_1 != null && _r2_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i2, _index.element, _memo.input, (_r2_1.results + _r2_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i2
                    }

                    // BIND id
                    id = _memo.results.peek()

                    // ACT 0
                    val _r0 = _memo.results.peek()
                    if (_r0 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_r0.startIndex, _r0.nextIndex, _memo.input, _Thunk({ AST.Idfr(id!!) }, _r0), true))
                    }

                    break
                }
            }
        }
    }


    fun GenericId(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var ids: _Parser_Item? = null
        var gp: _Parser_Item? = null

        // AND 1
        var _start_i1 = _index.element

        // AND 3
        var _start_i3 = _index.element

        // STAR 5
        var _start_i5 = _index.element
        val _inp5 = arrayListOf<Char?>()
        val _res5 = arrayListOf<AST.AstNode?>()

        // AND 6
        var _start_i6 = _index.element

        // AND 11
        var _start_i11 = _index.element

        // AND 12
        var _start_i12 = _index.element

        // AND 14
        var _start_i14 = _index.element

        // AND 15
        var _start_i15 = _index.element

        // STAR 18
        var _start_i18 = _index.element
        val _inp18 = arrayListOf<Char?>()
        val _res18 = arrayListOf<AST.AstNode?>()

        // AND 19
        var _start_i19 = _index.element

        // AND 20
        var _start_i20 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 1
                    _start_i1 = _index.element

                    // AND 3
                    _start_i3 = _index.element

                    // CALLORVAR Ident
                    var _r4: _Parser_Item? = null
                    _r4 = _MemoCall(_memo, "Ident", _index.element, ::Ident, null)
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
                    // AND 6
                    _start_i6 = _index.element

                    // CALLORVAR DOT
                    var _r7: _Parser_Item? = null
                    _r7 = _MemoCall(_memo, "DOT", _index.element, ::DOT, null)
                    if (_r7 != null) _index.element = _r7.nextIndex

                    // AND shortcut 6
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 6
                        continue
                    }

                    // CALLORVAR Ident
                    var _r8: _Parser_Item? = null
                    _r8 = _MemoCall(_memo, "Ident", _index.element, ::Ident, null)
                    if (_r8 != null) _index.element = _r8.nextIndex

                    _label = 6
                }
                // AND 6
                6 -> {
                    val _r6_2 = _memo.results.pop()
                    val _r6_1 = _memo.results.pop()

                    if (_r6_1 != null && _r6_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i6, _index.element, _memo.input, (_r6_1.results + _r6_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i6
                    }

                    // STAR 5
                    val _r5 = _memo.results.pop()
                    if (_r5 != null) {
                        _res5 += _r5.results
                        _label = 5
                        continue
                    } else {
                        _memo.results.push(_Parser_Item(_start_i5, _index.element, _memo.input, _res5.filterNotNull(), true))
                    }

                    _label = 3
                }
                // AND 3
                3 -> {
                    val _r3_2 = _memo.results.pop()
                    val _r3_1 = _memo.results.pop()

                    if (_r3_1 != null && _r3_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i3, _index.element, _memo.input, (_r3_1.results + _r3_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i3
                    }

                    // BIND ids
                    ids = _memo.results.peek()

                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // AND 11
                    _start_i11 = _index.element

                    // AND 12
                    _start_i12 = _index.element

                    // CALLORVAR LESS
                    var _r13: _Parser_Item? = null
                    _r13 = _MemoCall(_memo, "LESS", _index.element, ::LESS, null)
                    if (_r13 != null) _index.element = _r13.nextIndex

                    // AND shortcut 12
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 12
                        continue
                    }

                    // AND 14
                    _start_i14 = _index.element

                    // AND 15
                    _start_i15 = _index.element

                    // CALLORVAR GenericId
                    var _r16: _Parser_Item? = null
                    _r16 = _MemoCall(_memo, "GenericId", _index.element, ::GenericId, null)
                    if (_r16 != null) _index.element = _r16.nextIndex

                    // AND shortcut 15
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 15
                        continue
                    }

                    // CALLORVAR SP
                    var _r17: _Parser_Item? = null
                    _r17 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r17 != null) _index.element = _r17.nextIndex

                    _label = 15
                }
                // AND 15
                15 -> {
                    val _r15_2 = _memo.results.pop()
                    val _r15_1 = _memo.results.pop()

                    if (_r15_1 != null && _r15_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i15, _index.element, _memo.input, (_r15_1.results + _r15_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i15
                    }

                    // AND shortcut 14
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 14
                        continue
                    }

                    // STAR 18
                    _start_i18 = _index.element
                    _label = 18
                }
                // STAR 18
                18 -> {
                    // AND 19
                    _start_i19 = _index.element

                    // AND 20
                    _start_i20 = _index.element

                    // CALLORVAR COMMA
                    var _r21: _Parser_Item? = null
                    _r21 = _MemoCall(_memo, "COMMA", _index.element, ::COMMA, null)
                    if (_r21 != null) _index.element = _r21.nextIndex

                    // AND shortcut 20
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 20
                        continue
                    }

                    // CALLORVAR GenericId
                    var _r22: _Parser_Item? = null
                    _r22 = _MemoCall(_memo, "GenericId", _index.element, ::GenericId, null)
                    if (_r22 != null) _index.element = _r22.nextIndex

                    _label = 20
                }
                // AND 20
                20 -> {
                    val _r20_2 = _memo.results.pop()
                    val _r20_1 = _memo.results.pop()

                    if (_r20_1 != null && _r20_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i20, _index.element, _memo.input, (_r20_1.results + _r20_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i20
                    }

                    // AND shortcut 19
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 19
                        continue
                    }

                    // CALLORVAR SP
                    var _r23: _Parser_Item? = null
                    _r23 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r23 != null) _index.element = _r23.nextIndex

                    _label = 19
                }
                // AND 19
                19 -> {
                    val _r19_2 = _memo.results.pop()
                    val _r19_1 = _memo.results.pop()

                    if (_r19_1 != null && _r19_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i19, _index.element, _memo.input, (_r19_1.results + _r19_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i19
                    }

                    // STAR 18
                    val _r18 = _memo.results.pop()
                    if (_r18 != null) {
                        _res18 += _r18.results
                        _label = 18
                        continue
                    } else {
                        _memo.results.push(_Parser_Item(_start_i18, _index.element, _memo.input, _res18.filterNotNull(), true))
                    }

                    _label = 14
                }
                // AND 14
                14 -> {
                    val _r14_2 = _memo.results.pop()
                    val _r14_1 = _memo.results.pop()

                    if (_r14_1 != null && _r14_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i14, _index.element, _memo.input, (_r14_1.results + _r14_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i14
                    }

                    _label = 12
                }
                // AND 12
                12 -> {
                    val _r12_2 = _memo.results.pop()
                    val _r12_1 = _memo.results.pop()

                    if (_r12_1 != null && _r12_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i12, _index.element, _memo.input, (_r12_1.results + _r12_2.results).filterNotNull(), true))
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

                    // CALLORVAR GREATER
                    var _r24: _Parser_Item? = null
                    _r24 = _MemoCall(_memo, "GREATER", _index.element, ::GREATER, null)
                    if (_r24 != null) _index.element = _r24.nextIndex

                    _label = 11
                }
                // AND 11
                11 -> {
                    val _r11_2 = _memo.results.pop()
                    val _r11_1 = _memo.results.pop()

                    if (_r11_1 != null && _r11_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i11, _index.element, _memo.input, (_r11_1.results + _r11_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i11
                    }

                    // QUES 10
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_index.element, _memo.input))
                    }
                    // BIND gp
                    gp = _memo.results.peek()

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i1
                    }

                    // ACT 0
                    val _r0 = _memo.results.peek()
                    if (_r0 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_r0.startIndex, _r0.nextIndex, _memo.input, _Thunk({ AST.Idfr(ids!!, gp) }, _r0), true))
                    }

                    break
                }
            }
        }
    }


    fun Number(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 0
        var _start_i0 = _index.element

        // PLUS 1
        var _start_i1 = _index.element
        val _inp1 = arrayListOf<Char?>()
        val _res1 = arrayListOf<AST.AstNode?>()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 0
                    _start_i0 = _index.element

                    // PLUS 1
                    _start_i1 = _index.element
                    _label = 1
                }
                // PLUS 1
                1 -> {
                    // INPUT CLASS
                    _ParseInputClass(_memo, _index, listOf('\u0030', '\u0031', '\u0032', '\u0033', '\u0034', '\u0035', '\u0036', '\u0037', '\u0038', '\u0039'))

                    // PLUS 1
                    val _r1 = _memo.results.pop()
                    if (_r1 != null) {
                        _res1 += _r1.results
                        _label = 1
                        continue
                    } else {
                        if (_index.element > _start_i1) {
                            _memo.results.push(_Parser_Item(_start_i1, _index.element, _memo.input, _res1.filterNotNull(), true))
                        } else {
                            _memo.results.push(null)
                        }
                    }

                    // AND shortcut 0
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 0
                        continue
                    }

                    // CALLORVAR SP
                    var _r3: _Parser_Item? = null
                    _r3 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r3 != null) _index.element = _r3.nextIndex

                    _label = 0
                }
                // AND 0
                0 -> {
                    val _r0_2 = _memo.results.pop()
                    val _r0_1 = _memo.results.pop()

                    if (_r0_1 != null && _r0_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i0, _index.element, _memo.input, (_r0_1.results + _r0_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i0
                    }

                    break
                }
            }
        }
    }


    fun KEYWORD(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
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

                    // CALLORVAR USING
                    var _r4: _Parser_Item? = null
                    _r4 = _MemoCall(_memo, "USING", _index.element, ::USING, null)
                    if (_r4 != null) _index.element = _r4.nextIndex

                    // OR shortcut 3
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i3
                    } else {
                        _label = 3
                        continue
                    }

                    // CALLORVAR KOMETA
                    var _r5: _Parser_Item? = null
                    _r5 = _MemoCall(_memo, "KOMETA", _index.element, ::KOMETA, null)
                    if (_r5 != null) _index.element = _r5.nextIndex

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

                    // CALLORVAR OVERRIDE
                    var _r6: _Parser_Item? = null
                    _r6 = _MemoCall(_memo, "OVERRIDE", _index.element, ::OVERRIDE, null)
                    if (_r6 != null) _index.element = _r6.nextIndex

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

                    // CALLORVAR NEW
                    var _r7: _Parser_Item? = null
                    _r7 = _MemoCall(_memo, "NEW", _index.element, ::NEW, null)
                    if (_r7 != null) _index.element = _r7.nextIndex

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

                    // CALLORVAR LR
                    var _r8: _Parser_Item? = null
                    _r8 = _MemoCall(_memo, "LR", _index.element, ::LR, null)
                    if (_r8 != null) _index.element = _r8.nextIndex

                    _label = 0
                }
                // OR 0
                0 -> {
                    break
                }
            }
        }
    }


    fun USING(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 0
        var _start_i0 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 0
                    _start_i0 = _index.element

                    // LITERAL "import"
                    _ParseLiteralString(_memo, _index, "import")

                    // AND shortcut 0
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 0
                        continue
                    }

                    // CALLORVAR SP
                    var _r2: _Parser_Item? = null
                    _r2 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r2 != null) _index.element = _r2.nextIndex

                    _label = 0
                }
                // AND 0
                0 -> {
                    val _r0_2 = _memo.results.pop()
                    val _r0_1 = _memo.results.pop()

                    if (_r0_1 != null && _r0_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i0, _index.element, _memo.input, (_r0_1.results + _r0_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i0
                    }

                    break
                }
            }
        }
    }


    fun KOMETA(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 0
        var _start_i0 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 0
                    _start_i0 = _index.element

                    // LITERAL "kometa"
                    _ParseLiteralString(_memo, _index, "kometa")

                    // AND shortcut 0
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 0
                        continue
                    }

                    // CALLORVAR SP
                    var _r2: _Parser_Item? = null
                    _r2 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r2 != null) _index.element = _r2.nextIndex

                    _label = 0
                }
                // AND 0
                0 -> {
                    val _r0_2 = _memo.results.pop()
                    val _r0_1 = _memo.results.pop()

                    if (_r0_1 != null && _r0_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i0, _index.element, _memo.input, (_r0_1.results + _r0_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i0
                    }

                    break
                }
            }
        }
    }


    fun EQUALS(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 0
        var _start_i0 = _index.element

        // OR 1
        var _start_i1 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 0
                    _start_i0 = _index.element

                    // OR 1
                    _start_i1 = _index.element

                    // LITERAL '='
                    _ParseLiteralChar(_memo, _index, '=')

                    // OR shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i1
                    } else {
                        _label = 1
                        continue
                    }

                    // LITERAL "::="
                    _ParseLiteralString(_memo, _index, "::=")

                    _label = 1
                }
                // OR 1
                1 -> {
                    // AND shortcut 0
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 0
                        continue
                    }

                    // CALLORVAR SP
                    var _r4: _Parser_Item? = null
                    _r4 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r4 != null) _index.element = _r4.nextIndex

                    _label = 0
                }
                // AND 0
                0 -> {
                    val _r0_2 = _memo.results.pop()
                    val _r0_1 = _memo.results.pop()

                    if (_r0_1 != null && _r0_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i0, _index.element, _memo.input, (_r0_1.results + _r0_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i0
                    }

                    break
                }
            }
        }
    }


    fun OVERRIDE(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 0
        var _start_i0 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 0
                    _start_i0 = _index.element

                    // LITERAL "override"
                    _ParseLiteralString(_memo, _index, "override")

                    // AND shortcut 0
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 0
                        continue
                    }

                    // CALLORVAR SP
                    var _r2: _Parser_Item? = null
                    _r2 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r2 != null) _index.element = _r2.nextIndex

                    _label = 0
                }
                // AND 0
                0 -> {
                    val _r0_2 = _memo.results.pop()
                    val _r0_1 = _memo.results.pop()

                    if (_r0_1 != null && _r0_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i0, _index.element, _memo.input, (_r0_1.results + _r0_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i0
                    }

                    break
                }
            }
        }
    }


    fun NEW(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 0
        var _start_i0 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 0
                    _start_i0 = _index.element

                    // LITERAL "new"
                    _ParseLiteralString(_memo, _index, "new")

                    // AND shortcut 0
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 0
                        continue
                    }

                    // CALLORVAR SP
                    var _r2: _Parser_Item? = null
                    _r2 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r2 != null) _index.element = _r2.nextIndex

                    _label = 0
                }
                // AND 0
                0 -> {
                    val _r0_2 = _memo.results.pop()
                    val _r0_1 = _memo.results.pop()

                    if (_r0_1 != null && _r0_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i0, _index.element, _memo.input, (_r0_1.results + _r0_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i0
                    }

                    break
                }
            }
        }
    }


    fun LR(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 0
        var _start_i0 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 0
                    _start_i0 = _index.element

                    // LITERAL "lr"
                    _ParseLiteralString(_memo, _index, "lr")

                    // AND shortcut 0
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 0
                        continue
                    }

                    // CALLORVAR SP
                    var _r2: _Parser_Item? = null
                    _r2 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r2 != null) _index.element = _r2.nextIndex

                    _label = 0
                }
                // AND 0
                0 -> {
                    val _r0_2 = _memo.results.pop()
                    val _r0_1 = _memo.results.pop()

                    if (_r0_1 != null && _r0_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i0, _index.element, _memo.input, (_r0_1.results + _r0_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i0
                    }

                    break
                }
            }
        }
    }


    fun SEMI(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 0
        var _start_i0 = _index.element

        // OR 1
        var _start_i1 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 0
                    _start_i0 = _index.element

                    // OR 1
                    _start_i1 = _index.element

                    // LITERAL ';'
                    _ParseLiteralChar(_memo, _index, ';')

                    // OR shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i1
                    } else {
                        _label = 1
                        continue
                    }

                    // LITERAL ','
                    _ParseLiteralChar(_memo, _index, ',')

                    _label = 1
                }
                // OR 1
                1 -> {
                    // AND shortcut 0
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 0
                        continue
                    }

                    // CALLORVAR SP
                    var _r4: _Parser_Item? = null
                    _r4 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r4 != null) _index.element = _r4.nextIndex

                    _label = 0
                }
                // AND 0
                0 -> {
                    val _r0_2 = _memo.results.pop()
                    val _r0_1 = _memo.results.pop()

                    if (_r0_1 != null && _r0_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i0, _index.element, _memo.input, (_r0_1.results + _r0_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i0
                    }

                    break
                }
            }
        }
    }


    fun BANG(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 0
        var _start_i0 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 0
                    _start_i0 = _index.element

                    // LITERAL '!'
                    _ParseLiteralChar(_memo, _index, '!')

                    // AND shortcut 0
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 0
                        continue
                    }

                    // CALLORVAR SP
                    var _r2: _Parser_Item? = null
                    _r2 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r2 != null) _index.element = _r2.nextIndex

                    _label = 0
                }
                // AND 0
                0 -> {
                    val _r0_2 = _memo.results.pop()
                    val _r0_1 = _memo.results.pop()

                    if (_r0_1 != null && _r0_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i0, _index.element, _memo.input, (_r0_1.results + _r0_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i0
                    }

                    break
                }
            }
        }
    }


    fun OR(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 0
        var _start_i0 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 0
                    _start_i0 = _index.element

                    // LITERAL '|'
                    _ParseLiteralChar(_memo, _index, '|')

                    // AND shortcut 0
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 0
                        continue
                    }

                    // CALLORVAR SP
                    var _r2: _Parser_Item? = null
                    _r2 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r2 != null) _index.element = _r2.nextIndex

                    _label = 0
                }
                // AND 0
                0 -> {
                    val _r0_2 = _memo.results.pop()
                    val _r0_1 = _memo.results.pop()

                    if (_r0_1 != null && _r0_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i0, _index.element, _memo.input, (_r0_1.results + _r0_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i0
                    }

                    break
                }
            }
        }
    }


    fun ACTION(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 0
        var _start_i0 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 0
                    _start_i0 = _index.element

                    // LITERAL "->"
                    _ParseLiteralString(_memo, _index, "->")

                    // AND shortcut 0
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 0
                        continue
                    }

                    // CALLORVAR SP
                    var _r2: _Parser_Item? = null
                    _r2 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r2 != null) _index.element = _r2.nextIndex

                    _label = 0
                }
                // AND 0
                0 -> {
                    val _r0_2 = _memo.results.pop()
                    val _r0_1 = _memo.results.pop()

                    if (_r0_1 != null && _r0_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i0, _index.element, _memo.input, (_r0_1.results + _r0_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i0
                    }

                    break
                }
            }
        }
    }


    fun COLON(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 0
        var _start_i0 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 0
                    _start_i0 = _index.element

                    // LITERAL ':'
                    _ParseLiteralChar(_memo, _index, ':')

                    // AND shortcut 0
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 0
                        continue
                    }

                    // CALLORVAR SP
                    var _r2: _Parser_Item? = null
                    _r2 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r2 != null) _index.element = _r2.nextIndex

                    _label = 0
                }
                // AND 0
                0 -> {
                    val _r0_2 = _memo.results.pop()
                    val _r0_1 = _memo.results.pop()

                    if (_r0_1 != null && _r0_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i0, _index.element, _memo.input, (_r0_1.results + _r0_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i0
                    }

                    break
                }
            }
        }
    }


    fun COMMA(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 0
        var _start_i0 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 0
                    _start_i0 = _index.element

                    // LITERAL ','
                    _ParseLiteralChar(_memo, _index, ',')

                    // AND shortcut 0
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 0
                        continue
                    }

                    // CALLORVAR SP
                    var _r2: _Parser_Item? = null
                    _r2 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r2 != null) _index.element = _r2.nextIndex

                    _label = 0
                }
                // AND 0
                0 -> {
                    val _r0_2 = _memo.results.pop()
                    val _r0_1 = _memo.results.pop()

                    if (_r0_1 != null && _r0_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i0, _index.element, _memo.input, (_r0_1.results + _r0_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i0
                    }

                    break
                }
            }
        }
    }


    fun DOT(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // LITERAL '.'
                    _ParseLiteralChar(_memo, _index, '.')

                    break
                }
            }
        }
    }


    fun PERIOD(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 0
        var _start_i0 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 0
                    _start_i0 = _index.element

                    // CALLORVAR DOT
                    var _r1: _Parser_Item? = null
                    _r1 = _MemoCall(_memo, "DOT", _index.element, ::DOT, null)
                    if (_r1 != null) _index.element = _r1.nextIndex

                    // AND shortcut 0
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 0
                        continue
                    }

                    // CALLORVAR SP
                    var _r2: _Parser_Item? = null
                    _r2 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r2 != null) _index.element = _r2.nextIndex

                    _label = 0
                }
                // AND 0
                0 -> {
                    val _r0_2 = _memo.results.pop()
                    val _r0_1 = _memo.results.pop()

                    if (_r0_1 != null && _r0_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i0, _index.element, _memo.input, (_r0_1.results + _r0_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i0
                    }

                    break
                }
            }
        }
    }


    fun LEFT_BRACE(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 0
        var _start_i0 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 0
                    _start_i0 = _index.element

                    // LITERAL '{'
                    _ParseLiteralChar(_memo, _index, '{')

                    // AND shortcut 0
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 0
                        continue
                    }

                    // CALLORVAR SP
                    var _r2: _Parser_Item? = null
                    _r2 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r2 != null) _index.element = _r2.nextIndex

                    _label = 0
                }
                // AND 0
                0 -> {
                    val _r0_2 = _memo.results.pop()
                    val _r0_1 = _memo.results.pop()

                    if (_r0_1 != null && _r0_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i0, _index.element, _memo.input, (_r0_1.results + _r0_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i0
                    }

                    break
                }
            }
        }
    }


    fun RIGHT_BRACE(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 0
        var _start_i0 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 0
                    _start_i0 = _index.element

                    // LITERAL '}'
                    _ParseLiteralChar(_memo, _index, '}')

                    // AND shortcut 0
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 0
                        continue
                    }

                    // CALLORVAR SP
                    var _r2: _Parser_Item? = null
                    _r2 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r2 != null) _index.element = _r2.nextIndex

                    _label = 0
                }
                // AND 0
                0 -> {
                    val _r0_2 = _memo.results.pop()
                    val _r0_1 = _memo.results.pop()

                    if (_r0_1 != null && _r0_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i0, _index.element, _memo.input, (_r0_1.results + _r0_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i0
                    }

                    break
                }
            }
        }
    }


    fun OPEN(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 0
        var _start_i0 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 0
                    _start_i0 = _index.element

                    // LITERAL '('
                    _ParseLiteralChar(_memo, _index, '(')

                    // AND shortcut 0
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 0
                        continue
                    }

                    // CALLORVAR SP
                    var _r2: _Parser_Item? = null
                    _r2 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r2 != null) _index.element = _r2.nextIndex

                    _label = 0
                }
                // AND 0
                0 -> {
                    val _r0_2 = _memo.results.pop()
                    val _r0_1 = _memo.results.pop()

                    if (_r0_1 != null && _r0_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i0, _index.element, _memo.input, (_r0_1.results + _r0_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i0
                    }

                    break
                }
            }
        }
    }


    fun CLOSE(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 0
        var _start_i0 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 0
                    _start_i0 = _index.element

                    // LITERAL ')'
                    _ParseLiteralChar(_memo, _index, ')')

                    // AND shortcut 0
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 0
                        continue
                    }

                    // CALLORVAR SP
                    var _r2: _Parser_Item? = null
                    _r2 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r2 != null) _index.element = _r2.nextIndex

                    _label = 0
                }
                // AND 0
                0 -> {
                    val _r0_2 = _memo.results.pop()
                    val _r0_1 = _memo.results.pop()

                    if (_r0_1 != null && _r0_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i0, _index.element, _memo.input, (_r0_1.results + _r0_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i0
                    }

                    break
                }
            }
        }
    }


    fun LESS(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 0
        var _start_i0 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 0
                    _start_i0 = _index.element

                    // LITERAL '<'
                    _ParseLiteralChar(_memo, _index, '<')

                    // AND shortcut 0
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 0
                        continue
                    }

                    // CALLORVAR SP
                    var _r2: _Parser_Item? = null
                    _r2 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r2 != null) _index.element = _r2.nextIndex

                    _label = 0
                }
                // AND 0
                0 -> {
                    val _r0_2 = _memo.results.pop()
                    val _r0_1 = _memo.results.pop()

                    if (_r0_1 != null && _r0_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i0, _index.element, _memo.input, (_r0_1.results + _r0_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i0
                    }

                    break
                }
            }
        }
    }


    fun GREATER(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 0
        var _start_i0 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 0
                    _start_i0 = _index.element

                    // LITERAL '>'
                    _ParseLiteralChar(_memo, _index, '>')

                    // AND shortcut 0
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 0
                        continue
                    }

                    // CALLORVAR SP
                    var _r2: _Parser_Item? = null
                    _r2 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r2 != null) _index.element = _r2.nextIndex

                    _label = 0
                }
                // AND 0
                0 -> {
                    val _r0_2 = _memo.results.pop()
                    val _r0_1 = _memo.results.pop()

                    if (_r0_1 != null && _r0_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i0, _index.element, _memo.input, (_r0_1.results + _r0_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i0
                    }

                    break
                }
            }
        }
    }


    fun QUES(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // LITERAL '?'
                    _ParseLiteralChar(_memo, _index, '?')

                    break
                }
            }
        }
    }


    fun AND_PRE(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // LITERAL '&'
                    _ParseLiteralChar(_memo, _index, '&')

                    break
                }
            }
        }
    }


    fun NOT_PRE(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // LITERAL '~'
                    _ParseLiteralChar(_memo, _index, '~')

                    break
                }
            }
        }
    }


    fun STAR(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 0
        var _start_i0 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 0
                    _start_i0 = _index.element

                    // LITERAL '*'
                    _ParseLiteralChar(_memo, _index, '*')

                    // AND shortcut 0
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 0
                        continue
                    }

                    // CALLORVAR SP
                    var _r2: _Parser_Item? = null
                    _r2 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r2 != null) _index.element = _r2.nextIndex

                    _label = 0
                }
                // AND 0
                0 -> {
                    val _r0_2 = _memo.results.pop()
                    val _r0_1 = _memo.results.pop()

                    if (_r0_1 != null && _r0_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i0, _index.element, _memo.input, (_r0_1.results + _r0_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i0
                    }

                    break
                }
            }
        }
    }


    fun PLUS(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 0
        var _start_i0 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 0
                    _start_i0 = _index.element

                    // LITERAL '+'
                    _ParseLiteralChar(_memo, _index, '+')

                    // AND shortcut 0
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 0
                        continue
                    }

                    // CALLORVAR SP
                    var _r2: _Parser_Item? = null
                    _r2 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r2 != null) _index.element = _r2.nextIndex

                    _label = 0
                }
                // AND 0
                0 -> {
                    val _r0_2 = _memo.results.pop()
                    val _r0_1 = _memo.results.pop()

                    if (_r0_1 != null && _r0_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i0, _index.element, _memo.input, (_r0_1.results + _r0_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i0
                    }

                    break
                }
            }
        }
    }


    fun SP(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // STAR 0
        var _start_i0 = _index.element
        val _inp0 = arrayListOf<Char?>()
        val _res0 = arrayListOf<AST.AstNode?>()

        // OR 1
        var _start_i1 = _index.element

        // OR 2
        var _start_i2 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // STAR 0
                    _start_i0 = _index.element
                    _label = 0
                }
                // STAR 0
                0 -> {
                    // OR 1
                    _start_i1 = _index.element

                    // OR 2
                    _start_i2 = _index.element

                    // CALLORVAR EOL
                    var _r3: _Parser_Item? = null
                    _r3 = _MemoCall(_memo, "EOL", _index.element, ::EOL, null)
                    if (_r3 != null) _index.element = _r3.nextIndex

                    // OR shortcut 2
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i2
                    } else {
                        _label = 2
                        continue
                    }

                    // CALLORVAR WS
                    var _r4: _Parser_Item? = null
                    _r4 = _MemoCall(_memo, "WS", _index.element, ::WS, null)
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

                    // CALLORVAR Comment
                    var _r5: _Parser_Item? = null
                    _r5 = _MemoCall(_memo, "Comment", _index.element, ::Comment, null)
                    if (_r5 != null) _index.element = _r5.nextIndex

                    _label = 1
                }
                // OR 1
                1 -> {
                    // STAR 0
                    val _r0 = _memo.results.pop()
                    if (_r0 != null) {
                        _res0 += _r0.results
                        _label = 0
                        continue
                    } else {
                        _memo.results.push(_Parser_Item(_start_i0, _index.element, _memo.input, _res0.filterNotNull(), true))
                    }

                    break
                }
            }
        }
    }


    fun WS(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
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

                    // LITERAL ' '
                    _ParseLiteralChar(_memo, _index, ' ')

                    // OR shortcut 0
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i0
                    } else {
                        _label = 0
                        continue
                    }

                    // LITERAL '\t'
                    _ParseLiteralChar(_memo, _index, '\t')

                    _label = 0
                }
                // OR 0
                0 -> {
                    break
                }
            }
        }
    }


    fun Comment(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // OR 0
        var _start_i0 = _index.element

        // AND 1
        var _start_i1 = _index.element

        // AND 2
        var _start_i2 = _index.element

        // STAR 4
        var _start_i4 = _index.element
        val _inp4 = arrayListOf<Char?>()
        val _res4 = arrayListOf<AST.AstNode?>()

        // AND 5
        var _start_i5 = _index.element

        // NOT 6
        var _start_i6 = _index.element

        // AND 10
        var _start_i10 = _index.element

        // AND 11
        var _start_i11 = _index.element

        // STAR 13
        var _start_i13 = _index.element
        val _inp13 = arrayListOf<Char?>()
        val _res13 = arrayListOf<AST.AstNode?>()

        // AND 14
        var _start_i14 = _index.element

        // NOT 15
        var _start_i15 = _index.element

        // OR 17
        var _start_i17 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // OR 0
                    _start_i0 = _index.element

                    // AND 1
                    _start_i1 = _index.element

                    // AND 2
                    _start_i2 = _index.element

                    // LITERAL "//"
                    _ParseLiteralString(_memo, _index, "//")

                    // AND shortcut 2
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 2
                        continue
                    }

                    // STAR 4
                    _start_i4 = _index.element
                    _label = 4
                }
                // STAR 4
                4 -> {
                    // AND 5
                    _start_i5 = _index.element

                    // NOT 6
                    _start_i6 = _index.element

                    // CALLORVAR EOL
                    var _r7: _Parser_Item? = null
                    _r7 = _MemoCall(_memo, "EOL", _index.element, ::EOL, null)
                    if (_r7 != null) _index.element = _r7.nextIndex

                    // NOT 6
                    val _r6 = _memo.results.pop()
                    _memo.results.push(if (_r6 == null) _Parser_Item(_start_i6, _memo.input) else null)
                    _index.element = _start_i6
                    // AND shortcut 5
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 5
                        continue
                    }

                    // ANY
                    _ParseAny(_memo, _index)

                    _label = 5
                }
                // AND 5
                5 -> {
                    val _r5_2 = _memo.results.pop()
                    val _r5_1 = _memo.results.pop()

                    if (_r5_1 != null && _r5_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i5, _index.element, _memo.input, (_r5_1.results + _r5_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i5
                    }

                    // STAR 4
                    val _r4 = _memo.results.pop()
                    if (_r4 != null) {
                        _res4 += _r4.results
                        _label = 4
                        continue
                    } else {
                        _memo.results.push(_Parser_Item(_start_i4, _index.element, _memo.input, _res4.filterNotNull(), true))
                    }

                    _label = 2
                }
                // AND 2
                2 -> {
                    val _r2_2 = _memo.results.pop()
                    val _r2_1 = _memo.results.pop()

                    if (_r2_1 != null && _r2_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i2, _index.element, _memo.input, (_r2_1.results + _r2_2.results).filterNotNull(), true))
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

                    // CALLORVAR EOL
                    var _r9: _Parser_Item? = null
                    _r9 = _MemoCall(_memo, "EOL", _index.element, ::EOL, null)
                    if (_r9 != null) _index.element = _r9.nextIndex

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i1
                    }

                    // OR shortcut 0
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i0
                    } else {
                        _label = 0
                        continue
                    }

                    // AND 10
                    _start_i10 = _index.element

                    // AND 11
                    _start_i11 = _index.element

                    // LITERAL "/*"
                    _ParseLiteralString(_memo, _index, "/*")

                    // AND shortcut 11
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 11
                        continue
                    }

                    // STAR 13
                    _start_i13 = _index.element
                    _label = 13
                }
                // STAR 13
                13 -> {
                    // AND 14
                    _start_i14 = _index.element

                    // NOT 15
                    _start_i15 = _index.element

                    // LITERAL "*/"
                    _ParseLiteralString(_memo, _index, "*/")

                    // NOT 15
                    val _r15 = _memo.results.pop()
                    _memo.results.push(if (_r15 == null) _Parser_Item(_start_i15, _memo.input) else null)
                    _index.element = _start_i15
                    // AND shortcut 14
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 14
                        continue
                    }

                    // OR 17
                    _start_i17 = _index.element

                    // CALLORVAR EOL
                    var _r18: _Parser_Item? = null
                    _r18 = _MemoCall(_memo, "EOL", _index.element, ::EOL, null)
                    if (_r18 != null) _index.element = _r18.nextIndex

                    // OR shortcut 17
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i17
                    } else {
                        _label = 17
                        continue
                    }

                    // ANY
                    _ParseAny(_memo, _index)

                    _label = 17
                }
                // OR 17
                17 -> {
                    _label = 14
                }
                // AND 14
                14 -> {
                    val _r14_2 = _memo.results.pop()
                    val _r14_1 = _memo.results.pop()

                    if (_r14_1 != null && _r14_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i14, _index.element, _memo.input, (_r14_1.results + _r14_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i14
                    }

                    // STAR 13
                    val _r13 = _memo.results.pop()
                    if (_r13 != null) {
                        _res13 += _r13.results
                        _label = 13
                        continue
                    } else {
                        _memo.results.push(_Parser_Item(_start_i13, _index.element, _memo.input, _res13.filterNotNull(), true))
                    }

                    _label = 11
                }
                // AND 11
                11 -> {
                    val _r11_2 = _memo.results.pop()
                    val _r11_1 = _memo.results.pop()

                    if (_r11_1 != null && _r11_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i11, _index.element, _memo.input, (_r11_1.results + _r11_2.results).filterNotNull(), true))
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

                    // LITERAL "*/"
                    _ParseLiteralString(_memo, _index, "*/")

                    _label = 10
                }
                // AND 10
                10 -> {
                    val _r10_2 = _memo.results.pop()
                    val _r10_1 = _memo.results.pop()

                    if (_r10_1 != null && _r10_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i10, _index.element, _memo.input, (_r10_1.results + _r10_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i10
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


    fun EOL(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var nl: _Parser_Item? = null

        // OR 2
        var _start_i2 = _index.element

        // AND 3
        var _start_i3 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // OR 2
                    _start_i2 = _index.element

                    // AND 3
                    _start_i3 = _index.element

                    // LITERAL '\r'
                    _ParseLiteralChar(_memo, _index, '\r')

                    // AND shortcut 3
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 3
                        continue
                    }

                    // LITERAL '\n'
                    _ParseLiteralChar(_memo, _index, '\n')

                    // QUES 5
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_index.element, _memo.input))
                    }
                    _label = 3
                }
                // AND 3
                3 -> {
                    val _r3_2 = _memo.results.pop()
                    val _r3_1 = _memo.results.pop()

                    if (_r3_1 != null && _r3_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i3, _index.element, _memo.input, (_r3_1.results + _r3_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i3
                    }

                    // OR shortcut 2
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i2
                    } else {
                        _label = 2
                        continue
                    }

                    // LITERAL '\n'
                    _ParseLiteralChar(_memo, _index, '\n')

                    _label = 2
                }
                // OR 2
                2 -> {
                    // BIND nl
                    nl = _memo.results.peek()

                    // ACT 0
                    val _r0 = _memo.results.peek()
                    if (_r0 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_r0.startIndex, _r0.nextIndex, _memo.input, _Thunk({ _memo.positions += nl!!.nextIndex; nl?.asResult() ?: AST.Code(MatchItem(listOf('\n'))) }, _r0), true))
                    }

                    break
                }
            }
        }
    }


    fun EOF(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // NOT 0
        var _start_i0 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // NOT 0
                    _start_i0 = _index.element

                    // ANY
                    _ParseAny(_memo, _index)

                    // NOT 0
                    val _r0 = _memo.results.pop()
                    _memo.results.push(if (_r0 == null) _Parser_Item(_start_i0, _memo.input) else null)
                    _index.element = _start_i0
                    break
                }
            }
        }
    }

}
