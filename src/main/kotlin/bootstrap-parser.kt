//
// KOMeta Parser Parser
//

package kometa.bootstrap

import kometa.*
import kometa.util.*
import kotlin.jvm.internal.Ref

import kometa.CharMatcher
import kometa.ast.AST

typealias _Parser_Inputs = Iterable<Char>
typealias _Parser_Results = Iterable<AST.AstNode>
typealias _Parser_Item = MatchItem<Char, AST.AstNode>
typealias _Parser_Args = Iterable<_Parser_Item>
typealias _Parser_Memo = MatchState<Char, AST.AstNode>
typealias _Parser_Rule = Production<Char, AST.AstNode>
typealias _Parser_Base = Matcher<Char, AST.AstNode>

open class Parser : CharMatcher<AST.AstNode>() {
    init {
        terminals = setOf(
            "AND_PRE",
            "DOT",
            "EOF",
            "EOL",
            "IdentBegin",
            "IdentBody",
            "NOT_PRE",
            "QUES",
            "WS",
        )
    }

    open fun KOMetaFile(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var il: _Parser_Item? = null
        var g: _Parser_Item? = null

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
                    // AND 2
                    _start_i2 = _index.element

                    // AND 3
                    _start_i3 = _index.element

                    // AND 4
                    _start_i4 = _index.element

                    // AND 5
                    _start_i5 = _index.element

                    // CALLORVAR SP
                    var _r6: _Parser_Item? = null
                    _r6 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r6 != null) _index.element = _r6.nextIndex

                    // AND shortcut 5
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 5
                        continue
                    }

                    // CALLORVAR ImportsList
                    var _r9: _Parser_Item? = null
                    _r9 = _MemoCall(_memo, "ImportsList", _index.element, ::ImportsList, null)
                    if (_r9 != null) _index.element = _r9.nextIndex

                    // QUES 8
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_index.element, _memo.input))
                    }
                    // BIND il
                    il = _memo.results.peek()

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

                    // CALLORVAR Grammar
                    var _r11: _Parser_Item? = null
                    _r11 = _MemoCall(_memo, "Grammar", _index.element, ::Grammar, null)
                    if (_r11 != null) _index.element = _r11.nextIndex

                    // BIND g
                    g = _memo.results.peek()

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

                    // CALLORVAR SP
                    var _r12: _Parser_Item? = null
                    _r12 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r12 != null) _index.element = _r12.nextIndex

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

                    // CALLORVAR EOF
                    var _r13: _Parser_Item? = null
                    _r13 = _MemoCall(_memo, "EOF", _index.element, ::EOF, null)
                    if (_r13 != null) _index.element = _r13.nextIndex

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
                        _memo.results.push(_Parser_Item(_r1.startIndex, _r1.nextIndex, _memo.input, _Thunk({ AST.GrammarFile(il?.asResult()!!, g?.asResult()!!) }, _r1), true))
                    }

                    break
                }
            }
        }
    }


    open fun ImportsList(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var i: _Parser_Item? = null

        // PLUS 3
        var _start_i3 = _index.element
        val _inp3 = arrayListOf<Char?>()
        val _res3 = arrayListOf<AST.AstNode?>()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // PLUS 3
                    _start_i3 = _index.element
                    _label = 3
                }
                // PLUS 3
                3 -> {
                    // CALLORVAR Import
                    var _r4: _Parser_Item? = null
                    _r4 = _MemoCall(_memo, "Import", _index.element, ::Import, null)
                    if (_r4 != null) _index.element = _r4.nextIndex

                    // PLUS 3
                    val _r3 = _memo.results.pop()
                    if (_r3 != null) {
                        _res3 += _r3.results
                        _label = 3
                        continue
                    } else {
                        if (_index.element > _start_i3) {
                            _memo.results.push(_Parser_Item(_start_i3, _index.element, _memo.input, _res3.filterNotNull(), true))
                        } else {
                            _memo.results.push(null)
                        }
                    }

                    // BIND i
                    i = _memo.results.peek()

                    // ACT 1
                    val _r1 = _memo.results.peek()
                    if (_r1 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_r1.startIndex, _r1.nextIndex, _memo.input, _Thunk({ AST.ImportList(i!!.results.filterNotNull()) }, _r1), true))
                    }

                    break
                }
            }
        }
    }


    open fun Import(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var name: _Parser_Item? = null

        // AND 2
        var _start_i2 = _index.element

        // AND 3
        var _start_i3 = _index.element

        // AND 4
        var _start_i4 = _index.element

        // AND 7
        var _start_i7 = _index.element

        // STAR 9
        var _start_i9 = _index.element
        val _inp9 = arrayListOf<Char?>()
        val _res9 = arrayListOf<AST.AstNode?>()

        // AND 10
        var _start_i10 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 2
                    _start_i2 = _index.element

                    // AND 3
                    _start_i3 = _index.element

                    // AND 4
                    _start_i4 = _index.element

                    // CALLORVAR USING
                    var _r5: _Parser_Item? = null
                    _r5 = _MemoCall(_memo, "USING", _index.element, ::USING, null)
                    if (_r5 != null) _index.element = _r5.nextIndex

                    // AND shortcut 4
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 4
                        continue
                    }

                    // AND 7
                    _start_i7 = _index.element

                    // CALLORVAR Ident
                    var _r8: _Parser_Item? = null
                    _r8 = _MemoCall(_memo, "Ident", _index.element, ::Ident, null)
                    if (_r8 != null) _index.element = _r8.nextIndex

                    // AND shortcut 7
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 7
                        continue
                    }

                    // STAR 9
                    _start_i9 = _index.element
                    _label = 9
                }
                // STAR 9
                9 -> {
                    // AND 10
                    _start_i10 = _index.element

                    // CALLORVAR DOT
                    var _r11: _Parser_Item? = null
                    _r11 = _MemoCall(_memo, "DOT", _index.element, ::DOT, null)
                    if (_r11 != null) _index.element = _r11.nextIndex

                    // AND shortcut 10
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 10
                        continue
                    }

                    // CALLORVAR Ident
                    var _r12: _Parser_Item? = null
                    _r12 = _MemoCall(_memo, "Ident", _index.element, ::Ident, null)
                    if (_r12 != null) _index.element = _r12.nextIndex

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

                    // STAR 9
                    val _r9 = _memo.results.pop()
                    if (_r9 != null) {
                        _res9 += _r9.results
                        _label = 9
                        continue
                    } else {
                        _memo.results.push(_Parser_Item(_start_i9, _index.element, _memo.input, _res9.filterNotNull(), true))
                    }

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

                    // BIND name
                    name = _memo.results.peek()

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

                    // CALLORVAR SP
                    var _r13: _Parser_Item? = null
                    _r13 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r13 != null) _index.element = _r13.nextIndex

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

                    // CALLORVAR SEMI
                    var _r14: _Parser_Item? = null
                    _r14 = _MemoCall(_memo, "SEMI", _index.element, ::SEMI, null)
                    if (_r14 != null) _index.element = _r14.nextIndex

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
                        _memo.results.push(_Parser_Item(_r1.startIndex, _r1.nextIndex, _memo.input, _Thunk({ AST.Import(name!!) }, _r1), true))
                    }

                    break
                }
            }
        }
    }


    open fun Grammar(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var name: _Parser_Item? = null
        var tinput: _Parser_Item? = null
        var tresult: _Parser_Item? = null
        var baseclass: _Parser_Item? = null
        var rules: _Parser_Item? = null

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

        // AND 27
        var _start_i27 = _index.element

        // AND 28
        var _start_i28 = _index.element

        // STAR 35
        var _start_i35 = _index.element
        val _inp35 = arrayListOf<Char?>()
        val _res35 = arrayListOf<AST.AstNode?>()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
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

                    // CALLORVAR KOMETA
                    var _r14: _Parser_Item? = null
                    _r14 = _MemoCall(_memo, "KOMETA", _index.element, ::KOMETA, null)
                    if (_r14 != null) _index.element = _r14.nextIndex

                    // AND shortcut 13
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 13
                        continue
                    }

                    // CALLORVAR Identifier
                    var _r16: _Parser_Item? = null
                    _r16 = _MemoCall(_memo, "Identifier", _index.element, ::Identifier, null)
                    if (_r16 != null) _index.element = _r16.nextIndex

                    // BIND name
                    name = _memo.results.peek()

                    _label = 13
                }
                // AND 13
                13 -> {
                    val _r13_2 = _memo.results.pop()
                    val _r13_1 = _memo.results.pop()

                    if (_r13_1 != null && _r13_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i13, _index.element, _memo.input, (_r13_1.results + _r13_2.results).filterNotNull(), true))
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

                    // CALLORVAR LESS
                    var _r17: _Parser_Item? = null
                    _r17 = _MemoCall(_memo, "LESS", _index.element, ::LESS, null)
                    if (_r17 != null) _index.element = _r17.nextIndex

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

                    // CALLORVAR GenericId
                    var _r19: _Parser_Item? = null
                    _r19 = _MemoCall(_memo, "GenericId", _index.element, ::GenericId, null)
                    if (_r19 != null) _index.element = _r19.nextIndex

                    // BIND tinput
                    tinput = _memo.results.peek()

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

                    // CALLORVAR SP
                    var _r20: _Parser_Item? = null
                    _r20 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r20 != null) _index.element = _r20.nextIndex

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

                    // CALLORVAR COMMA
                    var _r21: _Parser_Item? = null
                    _r21 = _MemoCall(_memo, "COMMA", _index.element, ::COMMA, null)
                    if (_r21 != null) _index.element = _r21.nextIndex

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

                    // CALLORVAR GenericId
                    var _r23: _Parser_Item? = null
                    _r23 = _MemoCall(_memo, "GenericId", _index.element, ::GenericId, null)
                    if (_r23 != null) _index.element = _r23.nextIndex

                    // BIND tresult
                    tresult = _memo.results.peek()

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

                    // CALLORVAR SP
                    var _r24: _Parser_Item? = null
                    _r24 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r24 != null) _index.element = _r24.nextIndex

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

                    // CALLORVAR GREATER
                    var _r25: _Parser_Item? = null
                    _r25 = _MemoCall(_memo, "GREATER", _index.element, ::GREATER, null)
                    if (_r25 != null) _index.element = _r25.nextIndex

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

                    // AND 27
                    _start_i27 = _index.element

                    // AND 28
                    _start_i28 = _index.element

                    // CALLORVAR COLON
                    var _r29: _Parser_Item? = null
                    _r29 = _MemoCall(_memo, "COLON", _index.element, ::COLON, null)
                    if (_r29 != null) _index.element = _r29.nextIndex

                    // AND shortcut 28
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 28
                        continue
                    }

                    // CALLORVAR GenericId
                    var _r31: _Parser_Item? = null
                    _r31 = _MemoCall(_memo, "GenericId", _index.element, ::GenericId, null)
                    if (_r31 != null) _index.element = _r31.nextIndex

                    // BIND baseclass
                    baseclass = _memo.results.peek()

                    _label = 28
                }
                // AND 28
                28 -> {
                    val _r28_2 = _memo.results.pop()
                    val _r28_1 = _memo.results.pop()

                    if (_r28_1 != null && _r28_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i28, _index.element, _memo.input, (_r28_1.results + _r28_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i28
                    }

                    // AND shortcut 27
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 27
                        continue
                    }

                    // CALLORVAR SP
                    var _r32: _Parser_Item? = null
                    _r32 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r32 != null) _index.element = _r32.nextIndex

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

                    // QUES 26
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_index.element, _memo.input))
                    }
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

                    // CALLORVAR LEFT_BRACE
                    var _r33: _Parser_Item? = null
                    _r33 = _MemoCall(_memo, "LEFT_BRACE", _index.element, ::LEFT_BRACE, null)
                    if (_r33 != null) _index.element = _r33.nextIndex

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

                    // STAR 35
                    _start_i35 = _index.element
                    _label = 35
                }
                // STAR 35
                35 -> {
                    // CALLORVAR Rule
                    var _r36: _Parser_Item? = null
                    _r36 = _MemoCall(_memo, "Rule", _index.element, ::Rule, null)
                    if (_r36 != null) _index.element = _r36.nextIndex

                    // STAR 35
                    val _r35 = _memo.results.pop()
                    if (_r35 != null) {
                        _res35 += _r35.results
                        _label = 35
                        continue
                    } else {
                        _memo.results.push(_Parser_Item(_start_i35, _index.element, _memo.input, _res35.filterNotNull(), true))
                    }

                    // BIND rules
                    rules = _memo.results.peek()

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

                    // CALLORVAR RIGHT_BRACE
                    var _r37: _Parser_Item? = null
                    _r37 = _MemoCall(_memo, "RIGHT_BRACE", _index.element, ::RIGHT_BRACE, null)
                    if (_r37 != null) _index.element = _r37.nextIndex

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
                        _memo.results.push(_Parser_Item(_r1.startIndex, _r1.nextIndex, _memo.input, _Thunk({ AST.Grammar(name!!, tinput!!, tresult!!, baseclass, rules!!.results.filterIsInstance<AST.Rule>()) }, _r1), true))
                    }

                    break
                }
            }
        }
    }


    open fun Rule(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var ovr: _Parser_Item? = null
        var name: _Parser_Item? = null
        var parms: _Parser_Item? = null
        var body: _Parser_Item? = null

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

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
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

                    // CALLORVAR OVERRIDE
                    var _r9: _Parser_Item? = null
                    _r9 = _MemoCall(_memo, "OVERRIDE", _index.element, ::OVERRIDE, null)
                    if (_r9 != null) _index.element = _r9.nextIndex

                    // QUES 8
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_index.element, _memo.input))
                    }
                    // BIND ovr
                    ovr = _memo.results.peek()

                    // AND shortcut 6
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 6
                        continue
                    }

                    // CALLORVAR Identifier
                    var _r11: _Parser_Item? = null
                    _r11 = _MemoCall(_memo, "Identifier", _index.element, ::Identifier, null)
                    if (_r11 != null) _index.element = _r11.nextIndex

                    // BIND name
                    name = _memo.results.peek()

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

                    // CALLORVAR Disjunction
                    var _r14: _Parser_Item? = null
                    _r14 = _MemoCall(_memo, "Disjunction", _index.element, ::Disjunction, null)
                    if (_r14 != null) _index.element = _r14.nextIndex

                    // QUES 13
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_index.element, _memo.input))
                    }
                    // BIND parms
                    parms = _memo.results.peek()

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

                    // CALLORVAR EQUALS
                    var _r15: _Parser_Item? = null
                    _r15 = _MemoCall(_memo, "EQUALS", _index.element, ::EQUALS, null)
                    if (_r15 != null) _index.element = _r15.nextIndex

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

                    // CALLORVAR Disjunction
                    var _r17: _Parser_Item? = null
                    _r17 = _MemoCall(_memo, "Disjunction", _index.element, ::Disjunction, null)
                    if (_r17 != null) _index.element = _r17.nextIndex

                    // BIND body
                    body = _memo.results.peek()

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

                    // CALLORVAR SEMI
                    var _r18: _Parser_Item? = null
                    _r18 = _MemoCall(_memo, "SEMI", _index.element, ::SEMI, null)
                    if (_r18 != null) _index.element = _r18.nextIndex

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
                        _memo.results.push(_Parser_Item(_r1.startIndex, _r1.nextIndex, _memo.input, _Thunk({ AST.Rule(name!!, if (parms != null && parms.inputs.any()) AST.Args(parms.asResult(), body?.asResult()!!) else body?.asResult()!!, ovr?.inputs?.joinToString("") ?: "") }, _r1), true))
                    }

                    break
                }
            }
        }
    }


    open fun Disjunction(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

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

                    // CALLORVAR ActionExp
                    var _r3: _Parser_Item? = null
                    _r3 = _MemoCall(_memo, "ActionExp", _index.element, ::ActionExp, null)
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

                    // CALLORVAR OR
                    var _r6: _Parser_Item? = null
                    _r6 = _MemoCall(_memo, "OR", _index.element, ::OR, null)
                    if (_r6 != null) _index.element = _r6.nextIndex

                    // AND shortcut 5
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 5
                        continue
                    }

                    // CALLORVAR ActionExp
                    var _r7: _Parser_Item? = null
                    _r7 = _MemoCall(_memo, "ActionExp", _index.element, ::ActionExp, null)
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

                    // ACT 1
                    val _r1 = _memo.results.peek()
                    if (_r1 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_r1.startIndex, _r1.nextIndex, _memo.input, _Thunk({ it.results.filterNotNull().reduce{ prev, cur -> AST.Or(prev, cur) } }, _r1), true))
                    }

                    break
                }
            }
        }
    }


    open fun ActionExp(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var body: _Parser_Item? = null
        var action: _Parser_Item? = null

        // OR 1
        var _start_i1 = _index.element

        // AND 3
        var _start_i3 = _index.element

        // AND 4
        var _start_i4 = _index.element

        // AND 5
        var _start_i5 = _index.element

        // LOOK 9
        var _start_i9 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // OR 1
                    _start_i1 = _index.element

                    // AND 3
                    _start_i3 = _index.element

                    // AND 4
                    _start_i4 = _index.element

                    // AND 5
                    _start_i5 = _index.element

                    // CALLORVAR SequenceExp
                    var _r7: _Parser_Item? = null
                    _r7 = _MemoCall(_memo, "SequenceExp", _index.element, ::SequenceExp, null)
                    if (_r7 != null) _index.element = _r7.nextIndex

                    // BIND body
                    body = _memo.results.peek()

                    // AND shortcut 5
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 5
                        continue
                    }

                    // CALLORVAR ACTION
                    var _r8: _Parser_Item? = null
                    _r8 = _MemoCall(_memo, "ACTION", _index.element, ::ACTION, null)
                    if (_r8 != null) _index.element = _r8.nextIndex

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

                    // LOOK 9
                    _start_i9 = _index.element

                    // CALLORVAR LEFT_BRACE
                    var _r10: _Parser_Item? = null
                    _r10 = _MemoCall(_memo, "LEFT_BRACE", _index.element, ::LEFT_BRACE, null)
                    if (_r10 != null) _index.element = _r10.nextIndex

                    // LOOK 9
                    val _r9 = _memo.results.pop()
                    _memo.results.push(if (_r9 != null) _Parser_Item(_start_i9, _memo.input) else null)
                    _index.element = _start_i9

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

                    // CALLORVAR KotlinCode
                    var _r12: _Parser_Item? = null
                    _r12 = _MemoCall(_memo, "KotlinCode", _index.element, ::KotlinCode, null)
                    if (_r12 != null) _index.element = _r12.nextIndex

                    // BIND action
                    action = _memo.results.peek()

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
                        _memo.results.push(_Parser_Item(_r2.startIndex, _r2.nextIndex, _memo.input, _Thunk({ AST.Act(body?.asResult()!!, action!!) }, _r2), true))
                    }

                    // OR shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i1
                    } else {
                        _label = 1
                        continue
                    }

                    // CALLORVAR SequenceExp
                    var _r13: _Parser_Item? = null
                    _r13 = _MemoCall(_memo, "SequenceExp", _index.element, ::SequenceExp, null)
                    if (_r13 != null) _index.element = _r13.nextIndex

                    _label = 1
                }
                // OR 1
                1 -> {
                    break
                }
            }
        }
    }


    open fun FailExp(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var msg: _Parser_Item? = null

        // AND 2
        var _start_i2 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 2
                    _start_i2 = _index.element

                    // CALLORVAR BANG
                    var _r3: _Parser_Item? = null
                    _r3 = _MemoCall(_memo, "BANG", _index.element, ::BANG, null)
                    if (_r3 != null) _index.element = _r3.nextIndex

                    // AND shortcut 2
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 2
                        continue
                    }

                    // CALLORVAR KotlinCode
                    var _r6: _Parser_Item? = null
                    _r6 = _MemoCall(_memo, "KotlinCode", _index.element, ::KotlinCode, null)
                    if (_r6 != null) _index.element = _r6.nextIndex

                    // BIND msg
                    msg = _memo.results.peek()

                    // QUES 4
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

                    // ACT 1
                    val _r1 = _memo.results.peek()
                    if (_r1 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_r1.startIndex, _r1.nextIndex, _memo.input, _Thunk({ AST.Fail(msg?.asResult()!!) }, _r1), true))
                    }

                    break
                }
            }
        }
    }


    open fun SequenceExp(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

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
                    // CALLORVAR ConditionExp
                    var _r3: _Parser_Item? = null
                    _r3 = _MemoCall(_memo, "ConditionExp", _index.element, ::ConditionExp, null)
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

                    // ACT 1
                    val _r1 = _memo.results.peek()
                    if (_r1 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_r1.startIndex, _r1.nextIndex, _memo.input, _Thunk({ it.results.filterNotNull().reduce { prev, cur -> AST.And(prev, cur) } }, _r1), true))
                    }

                    break
                }
            }
        }
    }


    open fun ConditionExp(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
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

        // AND 6
        var _start_i6 = _index.element

        // AND 7
        var _start_i7 = _index.element

        // AND 8
        var _start_i8 = _index.element

        // LOOK 12
        var _start_i12 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // OR 0
                    _start_i0 = _index.element

                    // OR 1
                    _start_i1 = _index.element

                    // CALLORVAR FailExp
                    var _r3: _Parser_Item? = null
                    _r3 = _MemoCall(_memo, "FailExp", _index.element, ::FailExp, null)
                    if (_r3 != null) _index.element = _r3.nextIndex

                    // OR shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i1
                    } else {
                        _label = 1
                        continue
                    }

                    // AND 6
                    _start_i6 = _index.element

                    // AND 7
                    _start_i7 = _index.element

                    // AND 8
                    _start_i8 = _index.element

                    // CALLORVAR BoundTerm
                    var _r10: _Parser_Item? = null
                    _r10 = _MemoCall(_memo, "BoundTerm", _index.element, ::BoundTerm, null)
                    if (_r10 != null) _index.element = _r10.nextIndex

                    // BIND body
                    body = _memo.results.peek()

                    // AND shortcut 8
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 8
                        continue
                    }

                    // CALLORVAR QUES
                    var _r11: _Parser_Item? = null
                    _r11 = _MemoCall(_memo, "QUES", _index.element, ::QUES, null)
                    if (_r11 != null) _index.element = _r11.nextIndex

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

                    // LOOK 12
                    _start_i12 = _index.element

                    // CALLORVAR OPEN
                    var _r13: _Parser_Item? = null
                    _r13 = _MemoCall(_memo, "OPEN", _index.element, ::OPEN, null)
                    if (_r13 != null) _index.element = _r13.nextIndex

                    // LOOK 12
                    val _r12 = _memo.results.pop()
                    _memo.results.push(if (_r12 != null) _Parser_Item(_start_i12, _memo.input) else null)
                    _index.element = _start_i12

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

                    // CALLORVAR KotlinCode
                    var _r15: _Parser_Item? = null
                    _r15 = _MemoCall(_memo, "KotlinCode", _index.element, ::KotlinCode, null)
                    if (_r15 != null) _index.element = _r15.nextIndex

                    // BIND cond
                    cond = _memo.results.peek()

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

                    // ACT 5
                    val _r5 = _memo.results.peek()
                    if (_r5 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_r5.startIndex, _r5.nextIndex, _memo.input, _Thunk({ AST.Cond(body?.asResult()!!, cond!!) }, _r5), true))
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
                    var _r17: _Parser_Item? = null
                    _r17 = _MemoCall(_memo, "BoundTerm", _index.element, ::BoundTerm, null)
                    if (_r17 != null) _index.element = _r17.nextIndex

                    _label = 0
                }
                // OR 0
                0 -> {
                    break
                }
            }
        }
    }


    open fun BoundTerm(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
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

        // AND 4
        var _start_i4 = _index.element

        // AND 5
        var _start_i5 = _index.element

        // AND 13
        var _start_i13 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // OR 0
                    _start_i0 = _index.element

                    // OR 1
                    _start_i1 = _index.element

                    // AND 4
                    _start_i4 = _index.element

                    // AND 5
                    _start_i5 = _index.element

                    // CALLORVAR PrefixedTerm
                    var _r7: _Parser_Item? = null
                    _r7 = _MemoCall(_memo, "PrefixedTerm", _index.element, ::PrefixedTerm, null)
                    if (_r7 != null) _index.element = _r7.nextIndex

                    // BIND exp
                    exp = _memo.results.peek()

                    // AND shortcut 5
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 5
                        continue
                    }

                    // CALLORVAR COLON
                    var _r8: _Parser_Item? = null
                    _r8 = _MemoCall(_memo, "COLON", _index.element, ::COLON, null)
                    if (_r8 != null) _index.element = _r8.nextIndex

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

                    // CALLORVAR Identifier
                    var _r10: _Parser_Item? = null
                    _r10 = _MemoCall(_memo, "Identifier", _index.element, ::Identifier, null)
                    if (_r10 != null) _index.element = _r10.nextIndex

                    // BIND id
                    id = _memo.results.peek()

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
                        _memo.results.push(_Parser_Item(_r3.startIndex, _r3.nextIndex, _memo.input, _Thunk({ AST.Bind(exp?.asResult()!!, id!!) }, _r3), true))
                    }

                    // OR shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i1
                    } else {
                        _label = 1
                        continue
                    }

                    // AND 13
                    _start_i13 = _index.element

                    // CALLORVAR COLON
                    var _r14: _Parser_Item? = null
                    _r14 = _MemoCall(_memo, "COLON", _index.element, ::COLON, null)
                    if (_r14 != null) _index.element = _r14.nextIndex

                    // AND shortcut 13
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 13
                        continue
                    }

                    // CALLORVAR Identifier
                    var _r16: _Parser_Item? = null
                    _r16 = _MemoCall(_memo, "Identifier", _index.element, ::Identifier, null)
                    if (_r16 != null) _index.element = _r16.nextIndex

                    // BIND id
                    id = _memo.results.peek()

                    _label = 13
                }
                // AND 13
                13 -> {
                    val _r13_2 = _memo.results.pop()
                    val _r13_1 = _memo.results.pop()

                    if (_r13_1 != null && _r13_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i13, _index.element, _memo.input, (_r13_1.results + _r13_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i13
                    }

                    // ACT 12
                    val _r12 = _memo.results.peek()
                    if (_r12 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_r12.startIndex, _r12.nextIndex, _memo.input, _Thunk({ AST.Bind(AST.Any, id!!) }, _r12), true))
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
                    var _r18: _Parser_Item? = null
                    _r18 = _MemoCall(_memo, "PrefixedTerm", _index.element, ::PrefixedTerm, null)
                    if (_r18 != null) _index.element = _r18.nextIndex

                    _label = 0
                }
                // OR 0
                0 -> {
                    break
                }
            }
        }
    }


    open fun PrefixedTerm(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // OR 1
        var _start_i1 = _index.element

        // OR 2
        var _start_i2 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // OR 1
                    _start_i1 = _index.element

                    // OR 2
                    _start_i2 = _index.element

                    // CALLORVAR LookTerm
                    var _r3: _Parser_Item? = null
                    _r3 = _MemoCall(_memo, "LookTerm", _index.element, ::LookTerm, null)
                    if (_r3 != null) _index.element = _r3.nextIndex

                    // OR shortcut 2
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i2
                    } else {
                        _label = 2
                        continue
                    }

                    // CALLORVAR NotTerm
                    var _r4: _Parser_Item? = null
                    _r4 = _MemoCall(_memo, "NotTerm", _index.element, ::NotTerm, null)
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

                    // CALLORVAR PostfixedTerm
                    var _r5: _Parser_Item? = null
                    _r5 = _MemoCall(_memo, "PostfixedTerm", _index.element, ::PostfixedTerm, null)
                    if (_r5 != null) _index.element = _r5.nextIndex

                    _label = 1
                }
                // OR 1
                1 -> {
                    break
                }
            }
        }
    }


    open fun LookTerm(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var exp: _Parser_Item? = null

        // AND 2
        var _start_i2 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 2
                    _start_i2 = _index.element

                    // CALLORVAR AND_PRE
                    var _r3: _Parser_Item? = null
                    _r3 = _MemoCall(_memo, "AND_PRE", _index.element, ::AND_PRE, null)
                    if (_r3 != null) _index.element = _r3.nextIndex

                    // AND shortcut 2
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 2
                        continue
                    }

                    // CALLORVAR PostfixedTerm
                    var _r5: _Parser_Item? = null
                    _r5 = _MemoCall(_memo, "PostfixedTerm", _index.element, ::PostfixedTerm, null)
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

                    // ACT 1
                    val _r1 = _memo.results.peek()
                    if (_r1 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_r1.startIndex, _r1.nextIndex, _memo.input, _Thunk({ AST.Look(exp?.asResult()!!) }, _r1), true))
                    }

                    break
                }
            }
        }
    }


    open fun NotTerm(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var exp: _Parser_Item? = null

        // AND 2
        var _start_i2 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 2
                    _start_i2 = _index.element

                    // CALLORVAR NOT_PRE
                    var _r3: _Parser_Item? = null
                    _r3 = _MemoCall(_memo, "NOT_PRE", _index.element, ::NOT_PRE, null)
                    if (_r3 != null) _index.element = _r3.nextIndex

                    // AND shortcut 2
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 2
                        continue
                    }

                    // CALLORVAR PostfixedTerm
                    var _r5: _Parser_Item? = null
                    _r5 = _MemoCall(_memo, "PostfixedTerm", _index.element, ::PostfixedTerm, null)
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

                    // ACT 1
                    val _r1 = _memo.results.peek()
                    if (_r1 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_r1.startIndex, _r1.nextIndex, _memo.input, _Thunk({ AST.Not(exp?.asResult()!!) }, _r1), true))
                    }

                    break
                }
            }
        }
    }


    open fun PostfixedTerm(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

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
                    // OR 1
                    _start_i1 = _index.element

                    // OR 2
                    _start_i2 = _index.element

                    // OR 3
                    _start_i3 = _index.element

                    // OR 4
                    _start_i4 = _index.element

                    // CALLORVAR MinMaxTerm
                    var _r5: _Parser_Item? = null
                    _r5 = _MemoCall(_memo, "MinMaxTerm", _index.element, ::MinMaxTerm, null)
                    if (_r5 != null) _index.element = _r5.nextIndex

                    // OR shortcut 4
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i4
                    } else {
                        _label = 4
                        continue
                    }

                    // CALLORVAR StarTerm
                    var _r6: _Parser_Item? = null
                    _r6 = _MemoCall(_memo, "StarTerm", _index.element, ::StarTerm, null)
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

                    // CALLORVAR PlusTerm
                    var _r7: _Parser_Item? = null
                    _r7 = _MemoCall(_memo, "PlusTerm", _index.element, ::PlusTerm, null)
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

                    // CALLORVAR QuesTerm
                    var _r8: _Parser_Item? = null
                    _r8 = _MemoCall(_memo, "QuesTerm", _index.element, ::QuesTerm, null)
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

                    // CALLORVAR Term
                    var _r9: _Parser_Item? = null
                    _r9 = _MemoCall(_memo, "Term", _index.element, ::Term, null)
                    if (_r9 != null) _index.element = _r9.nextIndex

                    _label = 1
                }
                // OR 1
                1 -> {
                    break
                }
            }
        }
    }


    open fun MinMaxTerm(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var exp: _Parser_Item? = null
        var n: _Parser_Item? = null
        var x: _Parser_Item? = null

        // AND 2
        var _start_i2 = _index.element

        // AND 3
        var _start_i3 = _index.element

        // AND 4
        var _start_i4 = _index.element

        // AND 5
        var _start_i5 = _index.element

        // AND 12
        var _start_i12 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 2
                    _start_i2 = _index.element

                    // AND 3
                    _start_i3 = _index.element

                    // AND 4
                    _start_i4 = _index.element

                    // AND 5
                    _start_i5 = _index.element

                    // CALLORVAR Term
                    var _r7: _Parser_Item? = null
                    _r7 = _MemoCall(_memo, "Term", _index.element, ::Term, null)
                    if (_r7 != null) _index.element = _r7.nextIndex

                    // BIND exp
                    exp = _memo.results.peek()

                    // AND shortcut 5
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 5
                        continue
                    }

                    // CALLORVAR LEFT_BRACE
                    var _r8: _Parser_Item? = null
                    _r8 = _MemoCall(_memo, "LEFT_BRACE", _index.element, ::LEFT_BRACE, null)
                    if (_r8 != null) _index.element = _r8.nextIndex

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

                    // CALLORVAR Number
                    var _r10: _Parser_Item? = null
                    _r10 = _MemoCall(_memo, "Number", _index.element, ::Number, null)
                    if (_r10 != null) _index.element = _r10.nextIndex

                    // BIND n
                    n = _memo.results.peek()

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

                    // AND 12
                    _start_i12 = _index.element

                    // CALLORVAR COMMA
                    var _r13: _Parser_Item? = null
                    _r13 = _MemoCall(_memo, "COMMA", _index.element, ::COMMA, null)
                    if (_r13 != null) _index.element = _r13.nextIndex

                    // AND shortcut 12
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 12
                        continue
                    }

                    // CALLORVAR Number
                    var _r15: _Parser_Item? = null
                    _r15 = _MemoCall(_memo, "Number", _index.element, ::Number, null)
                    if (_r15 != null) _index.element = _r15.nextIndex

                    // BIND x
                    x = _memo.results.peek()

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

                    // QUES 11
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

                    // AND shortcut 2
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 2
                        continue
                    }

                    // CALLORVAR RIGHT_BRACE
                    var _r16: _Parser_Item? = null
                    _r16 = _MemoCall(_memo, "RIGHT_BRACE", _index.element, ::RIGHT_BRACE, null)
                    if (_r16 != null) _index.element = _r16.nextIndex

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
                        _memo.results.push(_Parser_Item(_r1.startIndex, _r1.nextIndex, _memo.input, _Thunk({
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
}, _r1), true))
                    }

                    break
                }
            }
        }
    }


    open fun StarTerm(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var exp: _Parser_Item? = null

        // AND 2
        var _start_i2 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 2
                    _start_i2 = _index.element

                    // CALLORVAR Term
                    var _r4: _Parser_Item? = null
                    _r4 = _MemoCall(_memo, "Term", _index.element, ::Term, null)
                    if (_r4 != null) _index.element = _r4.nextIndex

                    // BIND exp
                    exp = _memo.results.peek()

                    // AND shortcut 2
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 2
                        continue
                    }

                    // CALLORVAR STAR
                    var _r5: _Parser_Item? = null
                    _r5 = _MemoCall(_memo, "STAR", _index.element, ::STAR, null)
                    if (_r5 != null) _index.element = _r5.nextIndex

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
                        _memo.results.push(_Parser_Item(_r1.startIndex, _r1.nextIndex, _memo.input, _Thunk({ AST.Star(exp?.asResult()!!) }, _r1), true))
                    }

                    break
                }
            }
        }
    }


    open fun PlusTerm(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var exp: _Parser_Item? = null

        // AND 2
        var _start_i2 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 2
                    _start_i2 = _index.element

                    // CALLORVAR Term
                    var _r4: _Parser_Item? = null
                    _r4 = _MemoCall(_memo, "Term", _index.element, ::Term, null)
                    if (_r4 != null) _index.element = _r4.nextIndex

                    // BIND exp
                    exp = _memo.results.peek()

                    // AND shortcut 2
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 2
                        continue
                    }

                    // CALLORVAR PLUS
                    var _r5: _Parser_Item? = null
                    _r5 = _MemoCall(_memo, "PLUS", _index.element, ::PLUS, null)
                    if (_r5 != null) _index.element = _r5.nextIndex

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
                        _memo.results.push(_Parser_Item(_r1.startIndex, _r1.nextIndex, _memo.input, _Thunk({ AST.Plus(exp?.asResult()!!) }, _r1), true))
                    }

                    break
                }
            }
        }
    }


    open fun QuesTerm(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var exp: _Parser_Item? = null

        // AND 2
        var _start_i2 = _index.element

        // AND 3
        var _start_i3 = _index.element

        // AND 4
        var _start_i4 = _index.element

        // NOT 8
        var _start_i8 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
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

                    // CALLORVAR QUES
                    var _r7: _Parser_Item? = null
                    _r7 = _MemoCall(_memo, "QUES", _index.element, ::QUES, null)
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

                    // NOT 8
                    _start_i8 = _index.element

                    // CALLORVAR OPEN
                    var _r9: _Parser_Item? = null
                    _r9 = _MemoCall(_memo, "OPEN", _index.element, ::OPEN, null)
                    if (_r9 != null) _index.element = _r9.nextIndex

                    // NOT 8
                    val _r8 = _memo.results.pop()
                    _memo.results.push(if (_r8 == null) _Parser_Item(_start_i8, _memo.input) else null)
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

                    // CALLORVAR SP
                    var _r10: _Parser_Item? = null
                    _r10 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r10 != null) _index.element = _r10.nextIndex

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
                        _memo.results.push(_Parser_Item(_r1.startIndex, _r1.nextIndex, _memo.input, _Thunk({ AST.Ques(exp?.asResult()!!) }, _r1), true))
                    }

                    break
                }
            }
        }
    }


    open fun Term(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // OR 1
        var _start_i1 = _index.element

        // OR 2
        var _start_i2 = _index.element

        // OR 3
        var _start_i3 = _index.element

        // OR 4
        var _start_i4 = _index.element

        // OR 5
        var _start_i5 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // OR 1
                    _start_i1 = _index.element

                    // OR 2
                    _start_i2 = _index.element

                    // OR 3
                    _start_i3 = _index.element

                    // OR 4
                    _start_i4 = _index.element

                    // OR 5
                    _start_i5 = _index.element

                    // CALLORVAR InputClass
                    var _r6: _Parser_Item? = null
                    _r6 = _MemoCall(_memo, "InputClass", _index.element, ::InputClass, null)
                    if (_r6 != null) _index.element = _r6.nextIndex

                    // OR shortcut 5
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i5
                    } else {
                        _label = 5
                        continue
                    }

                    // CALLORVAR ParenTerm
                    var _r7: _Parser_Item? = null
                    _r7 = _MemoCall(_memo, "ParenTerm", _index.element, ::ParenTerm, null)
                    if (_r7 != null) _index.element = _r7.nextIndex

                    _label = 5
                }
                // OR 5
                5 -> {
                    // OR shortcut 4
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i4
                    } else {
                        _label = 4
                        continue
                    }

                    // CALLORVAR RuleCall
                    var _r8: _Parser_Item? = null
                    _r8 = _MemoCall(_memo, "RuleCall", _index.element, ::RuleCall, null)
                    if (_r8 != null) _index.element = _r8.nextIndex

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

                    // CALLORVAR CallOrVar
                    var _r9: _Parser_Item? = null
                    _r9 = _MemoCall(_memo, "CallOrVar", _index.element, ::CallOrVar, null)
                    if (_r9 != null) _index.element = _r9.nextIndex

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

                    // CALLORVAR Literal
                    var _r10: _Parser_Item? = null
                    _r10 = _MemoCall(_memo, "Literal", _index.element, ::Literal, null)
                    if (_r10 != null) _index.element = _r10.nextIndex

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

                    // CALLORVAR AnyTerm
                    var _r11: _Parser_Item? = null
                    _r11 = _MemoCall(_memo, "AnyTerm", _index.element, ::AnyTerm, null)
                    if (_r11 != null) _index.element = _r11.nextIndex

                    _label = 1
                }
                // OR 1
                1 -> {
                    break
                }
            }
        }
    }


    open fun ParenTerm(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var exp: _Parser_Item? = null

        // AND 2
        var _start_i2 = _index.element

        // AND 3
        var _start_i3 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 2
                    _start_i2 = _index.element

                    // AND 3
                    _start_i3 = _index.element

                    // CALLORVAR OPEN
                    var _r4: _Parser_Item? = null
                    _r4 = _MemoCall(_memo, "OPEN", _index.element, ::OPEN, null)
                    if (_r4 != null) _index.element = _r4.nextIndex

                    // AND shortcut 3
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 3
                        continue
                    }

                    // CALLORVAR Disjunction
                    var _r6: _Parser_Item? = null
                    _r6 = _MemoCall(_memo, "Disjunction", _index.element, ::Disjunction, null)
                    if (_r6 != null) _index.element = _r6.nextIndex

                    // BIND exp
                    exp = _memo.results.peek()

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

                    // CALLORVAR CLOSE
                    var _r7: _Parser_Item? = null
                    _r7 = _MemoCall(_memo, "CLOSE", _index.element, ::CLOSE, null)
                    if (_r7 != null) _index.element = _r7.nextIndex

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
                        _memo.results.push(_Parser_Item(_r1.startIndex, _r1.nextIndex, _memo.input, _Thunk({ exp?.asResult()!! }, _r1), true))
                    }

                    break
                }
            }
        }
    }


    open fun AnyTerm(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // CALLORVAR PERIOD
                    var _r2: _Parser_Item? = null
                    _r2 = _MemoCall(_memo, "PERIOD", _index.element, ::PERIOD, null)
                    if (_r2 != null) _index.element = _r2.nextIndex

                    // ACT 1
                    val _r1 = _memo.results.peek()
                    if (_r1 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_r1.startIndex, _r1.nextIndex, _memo.input, _Thunk({ AST.Any }, _r1), true))
                    }

                    break
                }
            }
        }
    }


    open fun RuleCall(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var name: _Parser_Item? = null
        var p: _Parser_Item? = null

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
                    // AND 2
                    _start_i2 = _index.element

                    // AND 3
                    _start_i3 = _index.element

                    // AND 4
                    _start_i4 = _index.element

                    // CALLORVAR QualifiedId
                    var _r6: _Parser_Item? = null
                    _r6 = _MemoCall(_memo, "QualifiedId", _index.element, ::QualifiedId, null)
                    if (_r6 != null) _index.element = _r6.nextIndex

                    // BIND name
                    name = _memo.results.peek()

                    // AND shortcut 4
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 4
                        continue
                    }

                    // CALLORVAR OPEN
                    var _r7: _Parser_Item? = null
                    _r7 = _MemoCall(_memo, "OPEN", _index.element, ::OPEN, null)
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

                    // CALLORVAR ParameterList
                    var _r10: _Parser_Item? = null
                    _r10 = _MemoCall(_memo, "ParameterList", _index.element, ::ParameterList, null)
                    if (_r10 != null) _index.element = _r10.nextIndex

                    // QUES 9
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_index.element, _memo.input))
                    }
                    // BIND p
                    p = _memo.results.peek()

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

                    // CALLORVAR CLOSE
                    var _r11: _Parser_Item? = null
                    _r11 = _MemoCall(_memo, "CLOSE", _index.element, ::CLOSE, null)
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

                    // ACT 1
                    val _r1 = _memo.results.peek()
                    if (_r1 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_r1.startIndex, _r1.nextIndex, _memo.input, _Thunk({ AST.Call(name!!, p?.results?.filterNotNull() ?: emptyList()) }, _r1), true))
                    }

                    break
                }
            }
        }
    }


    open fun ParameterList(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

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

                    // CALLORVAR Parameter
                    var _r3: _Parser_Item? = null
                    _r3 = _MemoCall(_memo, "Parameter", _index.element, ::Parameter, null)
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

                    // CALLORVAR COMMA
                    var _r6: _Parser_Item? = null
                    _r6 = _MemoCall(_memo, "COMMA", _index.element, ::COMMA, null)
                    if (_r6 != null) _index.element = _r6.nextIndex

                    // AND shortcut 5
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 5
                        continue
                    }

                    // CALLORVAR Parameter
                    var _r7: _Parser_Item? = null
                    _r7 = _MemoCall(_memo, "Parameter", _index.element, ::Parameter, null)
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

                    // ACT 1
                    val _r1 = _memo.results.peek()
                    if (_r1 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_r1.startIndex, _r1.nextIndex, _memo.input, _Thunk({ it.results.filterNotNull() }, _r1), true))
                    }

                    break
                }
            }
        }
    }


    open fun Parameter(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // CALLORVAR Disjunction
                    var _r1: _Parser_Item? = null
                    _r1 = _MemoCall(_memo, "Disjunction", _index.element, ::Disjunction, null)
                    if (_r1 != null) _index.element = _r1.nextIndex

                    break
                }
            }
        }
    }


    open fun CallOrVar(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var name: _Parser_Item? = null

        // AND 2
        var _start_i2 = _index.element

        // AND 3
        var _start_i3 = _index.element

        // NOT 4
        var _start_i4 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 2
                    _start_i2 = _index.element

                    // AND 3
                    _start_i3 = _index.element

                    // NOT 4
                    _start_i4 = _index.element

                    // CALLORVAR KEYWORD
                    var _r5: _Parser_Item? = null
                    _r5 = _MemoCall(_memo, "KEYWORD", _index.element, ::KEYWORD, null)
                    if (_r5 != null) _index.element = _r5.nextIndex

                    // NOT 4
                    val _r4 = _memo.results.pop()
                    _memo.results.push(if (_r4 == null) _Parser_Item(_start_i4, _memo.input) else null)
                    _index.element = _start_i4
                    // AND shortcut 3
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 3
                        continue
                    }

                    // CALLORVAR QualifiedId
                    var _r7: _Parser_Item? = null
                    _r7 = _MemoCall(_memo, "QualifiedId", _index.element, ::QualifiedId, null)
                    if (_r7 != null) _index.element = _r7.nextIndex

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
                    var _r8: _Parser_Item? = null
                    _r8 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r8 != null) _index.element = _r8.nextIndex

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
                        _memo.results.push(_Parser_Item(_r1.startIndex, _r1.nextIndex, _memo.input, _Thunk({ AST.CallOrVar(name!!) }, _r1), true))
                    }

                    break
                }
            }
        }
    }


    open fun Literal(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var r: _Parser_Item? = null

        // OR 1
        var _start_i1 = _index.element

        // COND 2
        var _start_i2 = _index.element

        // AND 6
        var _start_i6 = _index.element

        // OR 7
        var _start_i7 = _index.element

        // AND 8
        var _start_i8 = _index.element

        // AND 9
        var _start_i9 = _index.element

        // AND 12
        var _start_i12 = _index.element

        // LOOK 15
        var _start_i15 = _index.element

        // LOOK 17
        var _start_i17 = _index.element

        // OR 18
        var _start_i18 = _index.element

        // OR 19
        var _start_i19 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // OR 1
                    _start_i1 = _index.element

                    // COND 2
                    _start_i2 = _index.element

                    // CALLORVAR Regexp
                    var _r4: _Parser_Item? = null
                    _r4 = _MemoCall(_memo, "Regexp", _index.element, ::Regexp, null)
                    if (_r4 != null) _index.element = _r4.nextIndex

                    // BIND r
                    r = _memo.results.peek()

                    // COND 2
                    val lambda2: (_Parser_Item) -> Boolean = { AST.Regexp.isValid(r?.inputs?.joinToString("")!!) }
                    if (_memo.results.peek() == null || !lambda2(_memo.results.peek()!!)) {
                        _memo.results.pop()
                        _memo.results.push(null)
                        _index.element = _start_i2
                    }

                    // OR shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i1
                    } else {
                        _label = 1
                        continue
                    }

                    // AND 6
                    _start_i6 = _index.element

                    // OR 7
                    _start_i7 = _index.element

                    // AND 8
                    _start_i8 = _index.element

                    // AND 9
                    _start_i9 = _index.element

                    // CALLORVAR NEW
                    var _r10: _Parser_Item? = null
                    _r10 = _MemoCall(_memo, "NEW", _index.element, ::NEW, null)
                    if (_r10 != null) _index.element = _r10.nextIndex

                    // AND shortcut 9
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 9
                        continue
                    }

                    // AND 12
                    _start_i12 = _index.element

                    // CALLORVAR GenericId
                    var _r13: _Parser_Item? = null
                    _r13 = _MemoCall(_memo, "GenericId", _index.element, ::GenericId, null)
                    if (_r13 != null) _index.element = _r13.nextIndex

                    // AND shortcut 12
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 12
                        continue
                    }

                    // CALLORVAR SP
                    var _r14: _Parser_Item? = null
                    _r14 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r14 != null) _index.element = _r14.nextIndex

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

                    // QUES 11
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_index.element, _memo.input))
                    }
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

                    // LOOK 15
                    _start_i15 = _index.element

                    // LITERAL "{"
                    _ParseLiteralString(_memo, _index, "{")

                    // LOOK 15
                    val _r15 = _memo.results.pop()
                    _memo.results.push(if (_r15 != null) _Parser_Item(_start_i15, _memo.input) else null)
                    _index.element = _start_i15

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

                    // OR shortcut 7
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i7
                    } else {
                        _label = 7
                        continue
                    }

                    // LOOK 17
                    _start_i17 = _index.element

                    // OR 18
                    _start_i18 = _index.element

                    // OR 19
                    _start_i19 = _index.element

                    // LITERAL "\u0022"
                    _ParseLiteralString(_memo, _index, "\u0022")

                    // OR shortcut 19
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i19
                    } else {
                        _label = 19
                        continue
                    }

                    // LITERAL "\u0027"
                    _ParseLiteralString(_memo, _index, "\u0027")

                    _label = 19
                }
                // OR 19
                19 -> {
                    // OR shortcut 18
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i18
                    } else {
                        _label = 18
                        continue
                    }

                    // LITERAL "{"
                    _ParseLiteralString(_memo, _index, "{")

                    _label = 18
                }
                // OR 18
                18 -> {
                    // LOOK 17
                    val _r17 = _memo.results.pop()
                    _memo.results.push(if (_r17 != null) _Parser_Item(_start_i17, _memo.input) else null)
                    _index.element = _start_i17

                    _label = 7
                }
                // OR 7
                7 -> {
                    // AND shortcut 6
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 6
                        continue
                    }

                    // CALLORVAR KotlinCode
                    var _r23: _Parser_Item? = null
                    _r23 = _MemoCall(_memo, "KotlinCode", _index.element, ::KotlinCode, null)
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

                    // ACT 5
                    val _r5 = _memo.results.peek()
                    if (_r5 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_r5.startIndex, _r5.nextIndex, _memo.input, _Thunk({ AST.Code(it) }, _r5), true))
                    }

                    _label = 1
                }
                // OR 1
                1 -> {
                    break
                }
            }
        }
    }


    open fun Regexp(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

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
                    // AND 1
                    _start_i1 = _index.element

                    // AND 2
                    _start_i2 = _index.element

                    // LITERAL "/"
                    _ParseLiteralString(_memo, _index, "/")

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

                    // LITERAL "\\"
                    _ParseLiteralString(_memo, _index, "\\")

                    // AND shortcut 6
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 6
                        continue
                    }

                    // LITERAL "/"
                    _ParseLiteralString(_memo, _index, "/")

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

                    // LITERAL "//"
                    _ParseLiteralString(_memo, _index, "//")

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

                    // CALLORVAR SP
                    var _r13: _Parser_Item? = null
                    _r13 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
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

                    break
                }
            }
        }
    }


    open fun InputClass(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var c: _Parser_Item? = null

        // AND 2
        var _start_i2 = _index.element

        // AND 3
        var _start_i3 = _index.element

        // AND 4
        var _start_i4 = _index.element

        // AND 5
        var _start_i5 = _index.element

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
                    // AND 2
                    _start_i2 = _index.element

                    // AND 3
                    _start_i3 = _index.element

                    // AND 4
                    _start_i4 = _index.element

                    // AND 5
                    _start_i5 = _index.element

                    // LITERAL "["
                    _ParseLiteralString(_memo, _index, "[")

                    // AND shortcut 5
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 5
                        continue
                    }

                    // CALLORVAR SP
                    var _r7: _Parser_Item? = null
                    _r7 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
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

                    // AND shortcut 4
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 4
                        continue
                    }

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

                    // LITERAL "\u0027"
                    _ParseLiteralString(_memo, _index, "\u0027")

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
                    // BIND c
                    c = _memo.results.peek()

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

                    // LITERAL "]"
                    _ParseLiteralString(_memo, _index, "]")

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
                    var _r16: _Parser_Item? = null
                    _r16 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r16 != null) _index.element = _r16.nextIndex

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
                        _memo.results.push(_Parser_Item(_r1.startIndex, _r1.nextIndex, _memo.input, _Thunk({ AST.InputClass(c!!.results.filterNotNull()) }, _r1), true))
                    }

                    break
                }
            }
        }
    }


    open fun ClassRange(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var from: _Parser_Item? = null
        var to: _Parser_Item? = null

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

        // LOOK 9
        var _start_i9 = _index.element

        // LOOK 16
        var _start_i16 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
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

                    // LOOK 9
                    _start_i9 = _index.element

                    // LITERAL "\u0027"
                    _ParseLiteralString(_memo, _index, "\u0027")

                    // LOOK 9
                    val _r9 = _memo.results.pop()
                    _memo.results.push(if (_r9 != null) _Parser_Item(_start_i9, _memo.input) else null)
                    _index.element = _start_i9

                    // AND shortcut 8
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 8
                        continue
                    }

                    // CALLORVAR Literal
                    var _r12: _Parser_Item? = null
                    _r12 = _MemoCall(_memo, "Literal", _index.element, ::Literal, null)
                    if (_r12 != null) _index.element = _r12.nextIndex

                    // BIND from
                    from = _memo.results.peek()

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

                    // CALLORVAR SP
                    var _r13: _Parser_Item? = null
                    _r13 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r13 != null) _index.element = _r13.nextIndex

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

                    // LITERAL "-"
                    _ParseLiteralString(_memo, _index, "-")

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

                    // CALLORVAR SP
                    var _r15: _Parser_Item? = null
                    _r15 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r15 != null) _index.element = _r15.nextIndex

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

                    // LOOK 16
                    _start_i16 = _index.element

                    // LITERAL "\u0027"
                    _ParseLiteralString(_memo, _index, "\u0027")

                    // LOOK 16
                    val _r16 = _memo.results.pop()
                    _memo.results.push(if (_r16 != null) _Parser_Item(_start_i16, _memo.input) else null)
                    _index.element = _start_i16

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

                    // CALLORVAR Literal
                    var _r19: _Parser_Item? = null
                    _r19 = _MemoCall(_memo, "Literal", _index.element, ::Literal, null)
                    if (_r19 != null) _index.element = _r19.nextIndex

                    // BIND to
                    to = _memo.results.peek()

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
                    var _r20: _Parser_Item? = null
                    _r20 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r20 != null) _index.element = _r20.nextIndex

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
                        _memo.results.push(_Parser_Item(_r1.startIndex, _r1.nextIndex, _memo.input, _Thunk({
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
                            }, _r1), true))
                    }

                    break
                }
            }
        }
    }


    open fun KotlinCode(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var code: _Parser_Item? = null

        // AND 2
        var _start_i2 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 2
                    _start_i2 = _index.element

                    // CALLORVAR KotlinCodeItem
                    var _r4: _Parser_Item? = null
                    _r4 = _MemoCall(_memo, "KotlinCodeItem", _index.element, ::KotlinCodeItem, null)
                    if (_r4 != null) _index.element = _r4.nextIndex

                    // BIND code
                    code = _memo.results.peek()

                    // AND shortcut 2
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 2
                        continue
                    }

                    // CALLORVAR SP
                    var _r5: _Parser_Item? = null
                    _r5 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r5 != null) _index.element = _r5.nextIndex

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
                        _memo.results.push(_Parser_Item(_r1.startIndex, _r1.nextIndex, _memo.input, _Thunk({ AST.Code(code!!) }, _r1), true))
                    }

                    break
                }
            }
        }
    }


    open fun KotlinCodeItem(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
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

        // AND 4
        var _start_i4 = _index.element

        // AND 5
        var _start_i5 = _index.element

        // STAR 7
        var _start_i7 = _index.element
        val _inp7 = arrayListOf<Char?>()
        val _res7 = arrayListOf<AST.AstNode?>()

        // AND 8
        var _start_i8 = _index.element

        // NOT 9
        var _start_i9 = _index.element

        // OR 11
        var _start_i11 = _index.element

        // OR 12
        var _start_i12 = _index.element

        // OR 13
        var _start_i13 = _index.element

        // AND 20
        var _start_i20 = _index.element

        // AND 21
        var _start_i21 = _index.element

        // STAR 23
        var _start_i23 = _index.element
        val _inp23 = arrayListOf<Char?>()
        val _res23 = arrayListOf<AST.AstNode?>()

        // AND 24
        var _start_i24 = _index.element

        // NOT 25
        var _start_i25 = _index.element

        // OR 27
        var _start_i27 = _index.element

        // OR 28
        var _start_i28 = _index.element

        // OR 29
        var _start_i29 = _index.element

        // AND 36
        var _start_i36 = _index.element

        // AND 37
        var _start_i37 = _index.element

        // STAR 39
        var _start_i39 = _index.element
        val _inp39 = arrayListOf<Char?>()
        val _res39 = arrayListOf<AST.AstNode?>()

        // OR 40
        var _start_i40 = _index.element

        // OR 41
        var _start_i41 = _index.element

        // OR 42
        var _start_i42 = _index.element

        // AND 46
        var _start_i46 = _index.element

        // NOT 47
        var _start_i47 = _index.element

        // AND 52
        var _start_i52 = _index.element

        // AND 53
        var _start_i53 = _index.element

        // STAR 55
        var _start_i55 = _index.element
        val _inp55 = arrayListOf<Char?>()
        val _res55 = arrayListOf<AST.AstNode?>()

        // OR 56
        var _start_i56 = _index.element

        // OR 57
        var _start_i57 = _index.element

        // OR 58
        var _start_i58 = _index.element

        // AND 62
        var _start_i62 = _index.element

        // NOT 63
        var _start_i63 = _index.element

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

                    // AND 4
                    _start_i4 = _index.element

                    // AND 5
                    _start_i5 = _index.element

                    // LITERAL "{"
                    _ParseLiteralString(_memo, _index, "{")

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
                    // AND 8
                    _start_i8 = _index.element

                    // NOT 9
                    _start_i9 = _index.element

                    // LITERAL "}"
                    _ParseLiteralString(_memo, _index, "}")

                    // NOT 9
                    val _r9 = _memo.results.pop()
                    _memo.results.push(if (_r9 == null) _Parser_Item(_start_i9, _memo.input) else null)
                    _index.element = _start_i9
                    // AND shortcut 8
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 8
                        continue
                    }

                    // OR 11
                    _start_i11 = _index.element

                    // OR 12
                    _start_i12 = _index.element

                    // OR 13
                    _start_i13 = _index.element

                    // CALLORVAR EOL
                    var _r14: _Parser_Item? = null
                    _r14 = _MemoCall(_memo, "EOL", _index.element, ::EOL, null)
                    if (_r14 != null) _index.element = _r14.nextIndex

                    // OR shortcut 13
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i13
                    } else {
                        _label = 13
                        continue
                    }

                    // CALLORVAR Comment
                    var _r15: _Parser_Item? = null
                    _r15 = _MemoCall(_memo, "Comment", _index.element, ::Comment, null)
                    if (_r15 != null) _index.element = _r15.nextIndex

                    _label = 13
                }
                // OR 13
                13 -> {
                    // OR shortcut 12
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i12
                    } else {
                        _label = 12
                        continue
                    }

                    // CALLORVAR KotlinCodeItem
                    var _r16: _Parser_Item? = null
                    _r16 = _MemoCall(_memo, "KotlinCodeItem", _index.element, ::KotlinCodeItem, null)
                    if (_r16 != null) _index.element = _r16.nextIndex

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

                    // ANY
                    _ParseAny(_memo, _index)

                    _label = 11
                }
                // OR 11
                11 -> {
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

                    // STAR 7
                    val _r7 = _memo.results.pop()
                    if (_r7 != null) {
                        _res7 += _r7.results
                        _label = 7
                        continue
                    } else {
                        _memo.results.push(_Parser_Item(_start_i7, _index.element, _memo.input, _res7.filterNotNull(), true))
                    }

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

                    // LITERAL "}"
                    _ParseLiteralString(_memo, _index, "}")

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

                    // OR shortcut 2
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i2
                    } else {
                        _label = 2
                        continue
                    }

                    // AND 20
                    _start_i20 = _index.element

                    // AND 21
                    _start_i21 = _index.element

                    // LITERAL "("
                    _ParseLiteralString(_memo, _index, "(")

                    // AND shortcut 21
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 21
                        continue
                    }

                    // STAR 23
                    _start_i23 = _index.element
                    _label = 23
                }
                // STAR 23
                23 -> {
                    // AND 24
                    _start_i24 = _index.element

                    // NOT 25
                    _start_i25 = _index.element

                    // LITERAL ")"
                    _ParseLiteralString(_memo, _index, ")")

                    // NOT 25
                    val _r25 = _memo.results.pop()
                    _memo.results.push(if (_r25 == null) _Parser_Item(_start_i25, _memo.input) else null)
                    _index.element = _start_i25
                    // AND shortcut 24
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 24
                        continue
                    }

                    // OR 27
                    _start_i27 = _index.element

                    // OR 28
                    _start_i28 = _index.element

                    // OR 29
                    _start_i29 = _index.element

                    // CALLORVAR EOL
                    var _r30: _Parser_Item? = null
                    _r30 = _MemoCall(_memo, "EOL", _index.element, ::EOL, null)
                    if (_r30 != null) _index.element = _r30.nextIndex

                    // OR shortcut 29
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i29
                    } else {
                        _label = 29
                        continue
                    }

                    // CALLORVAR Comment
                    var _r31: _Parser_Item? = null
                    _r31 = _MemoCall(_memo, "Comment", _index.element, ::Comment, null)
                    if (_r31 != null) _index.element = _r31.nextIndex

                    _label = 29
                }
                // OR 29
                29 -> {
                    // OR shortcut 28
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i28
                    } else {
                        _label = 28
                        continue
                    }

                    // CALLORVAR KotlinCodeItem
                    var _r32: _Parser_Item? = null
                    _r32 = _MemoCall(_memo, "KotlinCodeItem", _index.element, ::KotlinCodeItem, null)
                    if (_r32 != null) _index.element = _r32.nextIndex

                    _label = 28
                }
                // OR 28
                28 -> {
                    // OR shortcut 27
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i27
                    } else {
                        _label = 27
                        continue
                    }

                    // ANY
                    _ParseAny(_memo, _index)

                    _label = 27
                }
                // OR 27
                27 -> {
                    _label = 24
                }
                // AND 24
                24 -> {
                    val _r24_2 = _memo.results.pop()
                    val _r24_1 = _memo.results.pop()

                    if (_r24_1 != null && _r24_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i24, _index.element, _memo.input, (_r24_1.results + _r24_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i24
                    }

                    // STAR 23
                    val _r23 = _memo.results.pop()
                    if (_r23 != null) {
                        _res23 += _r23.results
                        _label = 23
                        continue
                    } else {
                        _memo.results.push(_Parser_Item(_start_i23, _index.element, _memo.input, _res23.filterNotNull(), true))
                    }

                    _label = 21
                }
                // AND 21
                21 -> {
                    val _r21_2 = _memo.results.pop()
                    val _r21_1 = _memo.results.pop()

                    if (_r21_1 != null && _r21_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i21, _index.element, _memo.input, (_r21_1.results + _r21_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i21
                    }

                    // AND shortcut 20
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 20
                        continue
                    }

                    // LITERAL ")"
                    _ParseLiteralString(_memo, _index, ")")

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

                    // AND 36
                    _start_i36 = _index.element

                    // AND 37
                    _start_i37 = _index.element

                    // LITERAL "\u0027"
                    _ParseLiteralString(_memo, _index, "\u0027")

                    // AND shortcut 37
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 37
                        continue
                    }

                    // STAR 39
                    _start_i39 = _index.element
                    _label = 39
                }
                // STAR 39
                39 -> {
                    // OR 40
                    _start_i40 = _index.element

                    // OR 41
                    _start_i41 = _index.element

                    // OR 42
                    _start_i42 = _index.element

                    // CALLORVAR EOL
                    var _r43: _Parser_Item? = null
                    _r43 = _MemoCall(_memo, "EOL", _index.element, ::EOL, null)
                    if (_r43 != null) _index.element = _r43.nextIndex

                    // OR shortcut 42
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i42
                    } else {
                        _label = 42
                        continue
                    }

                    // LITERAL "\u005c\u0027"
                    _ParseLiteralString(_memo, _index, "\u005c\u0027")

                    _label = 42
                }
                // OR 42
                42 -> {
                    // OR shortcut 41
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i41
                    } else {
                        _label = 41
                        continue
                    }

                    // LITERAL "\u005c\u005c"
                    _ParseLiteralString(_memo, _index, "\u005c\u005c")

                    _label = 41
                }
                // OR 41
                41 -> {
                    // OR shortcut 40
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i40
                    } else {
                        _label = 40
                        continue
                    }

                    // AND 46
                    _start_i46 = _index.element

                    // NOT 47
                    _start_i47 = _index.element

                    // LITERAL "\u0027"
                    _ParseLiteralString(_memo, _index, "\u0027")

                    // NOT 47
                    val _r47 = _memo.results.pop()
                    _memo.results.push(if (_r47 == null) _Parser_Item(_start_i47, _memo.input) else null)
                    _index.element = _start_i47
                    // AND shortcut 46
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 46
                        continue
                    }

                    // ANY
                    _ParseAny(_memo, _index)

                    _label = 46
                }
                // AND 46
                46 -> {
                    val _r46_2 = _memo.results.pop()
                    val _r46_1 = _memo.results.pop()

                    if (_r46_1 != null && _r46_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i46, _index.element, _memo.input, (_r46_1.results + _r46_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i46
                    }

                    _label = 40
                }
                // OR 40
                40 -> {
                    // STAR 39
                    val _r39 = _memo.results.pop()
                    if (_r39 != null) {
                        _res39 += _r39.results
                        _label = 39
                        continue
                    } else {
                        _memo.results.push(_Parser_Item(_start_i39, _index.element, _memo.input, _res39.filterNotNull(), true))
                    }

                    _label = 37
                }
                // AND 37
                37 -> {
                    val _r37_2 = _memo.results.pop()
                    val _r37_1 = _memo.results.pop()

                    if (_r37_1 != null && _r37_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i37, _index.element, _memo.input, (_r37_1.results + _r37_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i37
                    }

                    // AND shortcut 36
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 36
                        continue
                    }

                    // LITERAL "\u0027"
                    _ParseLiteralString(_memo, _index, "\u0027")

                    _label = 36
                }
                // AND 36
                36 -> {
                    val _r36_2 = _memo.results.pop()
                    val _r36_1 = _memo.results.pop()

                    if (_r36_1 != null && _r36_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i36, _index.element, _memo.input, (_r36_1.results + _r36_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i36
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

                    // AND 52
                    _start_i52 = _index.element

                    // AND 53
                    _start_i53 = _index.element

                    // LITERAL "\u0022"
                    _ParseLiteralString(_memo, _index, "\u0022")

                    // AND shortcut 53
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 53
                        continue
                    }

                    // STAR 55
                    _start_i55 = _index.element
                    _label = 55
                }
                // STAR 55
                55 -> {
                    // OR 56
                    _start_i56 = _index.element

                    // OR 57
                    _start_i57 = _index.element

                    // OR 58
                    _start_i58 = _index.element

                    // CALLORVAR EOL
                    var _r59: _Parser_Item? = null
                    _r59 = _MemoCall(_memo, "EOL", _index.element, ::EOL, null)
                    if (_r59 != null) _index.element = _r59.nextIndex

                    // OR shortcut 58
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i58
                    } else {
                        _label = 58
                        continue
                    }

                    // LITERAL "\u005c\u0022"
                    _ParseLiteralString(_memo, _index, "\u005c\u0022")

                    _label = 58
                }
                // OR 58
                58 -> {
                    // OR shortcut 57
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i57
                    } else {
                        _label = 57
                        continue
                    }

                    // LITERAL "\u005c\u005c"
                    _ParseLiteralString(_memo, _index, "\u005c\u005c")

                    _label = 57
                }
                // OR 57
                57 -> {
                    // OR shortcut 56
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i56
                    } else {
                        _label = 56
                        continue
                    }

                    // AND 62
                    _start_i62 = _index.element

                    // NOT 63
                    _start_i63 = _index.element

                    // LITERAL "\u0022"
                    _ParseLiteralString(_memo, _index, "\u0022")

                    // NOT 63
                    val _r63 = _memo.results.pop()
                    _memo.results.push(if (_r63 == null) _Parser_Item(_start_i63, _memo.input) else null)
                    _index.element = _start_i63
                    // AND shortcut 62
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 62
                        continue
                    }

                    // ANY
                    _ParseAny(_memo, _index)

                    _label = 62
                }
                // AND 62
                62 -> {
                    val _r62_2 = _memo.results.pop()
                    val _r62_1 = _memo.results.pop()

                    if (_r62_1 != null && _r62_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i62, _index.element, _memo.input, (_r62_1.results + _r62_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i62
                    }

                    _label = 56
                }
                // OR 56
                56 -> {
                    // STAR 55
                    val _r55 = _memo.results.pop()
                    if (_r55 != null) {
                        _res55 += _r55.results
                        _label = 55
                        continue
                    } else {
                        _memo.results.push(_Parser_Item(_start_i55, _index.element, _memo.input, _res55.filterNotNull(), true))
                    }

                    _label = 53
                }
                // AND 53
                53 -> {
                    val _r53_2 = _memo.results.pop()
                    val _r53_1 = _memo.results.pop()

                    if (_r53_1 != null && _r53_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i53, _index.element, _memo.input, (_r53_1.results + _r53_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i53
                    }

                    // AND shortcut 52
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 52
                        continue
                    }

                    // LITERAL "\u0022"
                    _ParseLiteralString(_memo, _index, "\u0022")

                    _label = 52
                }
                // AND 52
                52 -> {
                    val _r52_2 = _memo.results.pop()
                    val _r52_1 = _memo.results.pop()

                    if (_r52_1 != null && _r52_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i52, _index.element, _memo.input, (_r52_1.results + _r52_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i52
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


    open fun Identifier(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var id: _Parser_Item? = null

        // AND 2
        var _start_i2 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 2
                    _start_i2 = _index.element

                    // CALLORVAR Ident
                    var _r4: _Parser_Item? = null
                    _r4 = _MemoCall(_memo, "Ident", _index.element, ::Ident, null)
                    if (_r4 != null) _index.element = _r4.nextIndex

                    // BIND id
                    id = _memo.results.peek()

                    // AND shortcut 2
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 2
                        continue
                    }

                    // CALLORVAR SP
                    var _r5: _Parser_Item? = null
                    _r5 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r5 != null) _index.element = _r5.nextIndex

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
                        _memo.results.push(_Parser_Item(_r1.startIndex, _r1.nextIndex, _memo.input, _Thunk({ id?.asResult()!! }, _r1), true))
                    }

                    break
                }
            }
        }
    }


    open fun Ident(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var id: _Parser_Item? = null

        // AND 3
        var _start_i3 = _index.element

        // STAR 5
        var _start_i5 = _index.element
        val _inp5 = arrayListOf<Char?>()
        val _res5 = arrayListOf<AST.AstNode?>()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 3
                    _start_i3 = _index.element

                    // CALLORVAR IdentBegin
                    var _r4: _Parser_Item? = null
                    _r4 = _MemoCall(_memo, "IdentBegin", _index.element, ::IdentBegin, null)
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
                    // CALLORVAR IdentBody
                    var _r6: _Parser_Item? = null
                    _r6 = _MemoCall(_memo, "IdentBody", _index.element, ::IdentBody, null)
                    if (_r6 != null) _index.element = _r6.nextIndex

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

                    // BIND id
                    id = _memo.results.peek()

                    // ACT 1
                    val _r1 = _memo.results.peek()
                    if (_r1 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_r1.startIndex, _r1.nextIndex, _memo.input, _Thunk({ AST.Idfr(id!!) }, _r1), true))
                    }

                    break
                }
            }
        }
    }


    open fun IdentBegin(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

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

                    // COND 1
                    val lambda1: (_Parser_Item) -> Boolean = { it.asInput().isJavaIdentifierStart() }
                    if (_memo.results.peek() == null || !lambda1(_memo.results.peek()!!)) {
                        _memo.results.pop()
                        _memo.results.push(null)
                        _index.element = _start_i1
                    }

                    break
                }
            }
        }
    }


    open fun IdentBody(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

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

                    // COND 1
                    val lambda1: (_Parser_Item) -> Boolean = { it.asInput().isJavaIdentifierPart() }
                    if (_memo.results.peek() == null || !lambda1(_memo.results.peek()!!)) {
                        _memo.results.pop()
                        _memo.results.push(null)
                        _index.element = _start_i1
                    }

                    break
                }
            }
        }
    }


    open fun QualifiedId(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var id: _Parser_Item? = null

        // AND 3
        var _start_i3 = _index.element

        // STAR 5
        var _start_i5 = _index.element
        val _inp5 = arrayListOf<Char?>()
        val _res5 = arrayListOf<AST.AstNode?>()

        // AND 6
        var _start_i6 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
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

                    // BIND id
                    id = _memo.results.peek()

                    // ACT 1
                    val _r1 = _memo.results.peek()
                    if (_r1 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_r1.startIndex, _r1.nextIndex, _memo.input, _Thunk({ AST.Idfr(id!!) }, _r1), true))
                    }

                    break
                }
            }
        }
    }


    open fun GenericId(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var ids: _Parser_Item? = null
        var gp: _Parser_Item? = null

        // AND 2
        var _start_i2 = _index.element

        // AND 4
        var _start_i4 = _index.element

        // STAR 6
        var _start_i6 = _index.element
        val _inp6 = arrayListOf<Char?>()
        val _res6 = arrayListOf<AST.AstNode?>()

        // AND 7
        var _start_i7 = _index.element

        // STAR 11
        var _start_i11 = _index.element
        val _inp11 = arrayListOf<Char?>()
        val _res11 = arrayListOf<AST.AstNode?>()

        // AND 12
        var _start_i12 = _index.element

        // AND 13
        var _start_i13 = _index.element

        // AND 15
        var _start_i15 = _index.element

        // AND 16
        var _start_i16 = _index.element

        // STAR 19
        var _start_i19 = _index.element
        val _inp19 = arrayListOf<Char?>()
        val _res19 = arrayListOf<AST.AstNode?>()

        // AND 20
        var _start_i20 = _index.element

        // AND 21
        var _start_i21 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 2
                    _start_i2 = _index.element

                    // AND 4
                    _start_i4 = _index.element

                    // CALLORVAR Ident
                    var _r5: _Parser_Item? = null
                    _r5 = _MemoCall(_memo, "Ident", _index.element, ::Ident, null)
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
                    // AND 7
                    _start_i7 = _index.element

                    // CALLORVAR DOT
                    var _r8: _Parser_Item? = null
                    _r8 = _MemoCall(_memo, "DOT", _index.element, ::DOT, null)
                    if (_r8 != null) _index.element = _r8.nextIndex

                    // AND shortcut 7
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 7
                        continue
                    }

                    // CALLORVAR Ident
                    var _r9: _Parser_Item? = null
                    _r9 = _MemoCall(_memo, "Ident", _index.element, ::Ident, null)
                    if (_r9 != null) _index.element = _r9.nextIndex

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

                    // BIND ids
                    ids = _memo.results.peek()

                    // AND shortcut 2
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 2
                        continue
                    }

                    // STAR 11
                    _start_i11 = _index.element
                    _label = 11
                }
                // STAR 11
                11 -> {
                    // AND 12
                    _start_i12 = _index.element

                    // AND 13
                    _start_i13 = _index.element

                    // CALLORVAR LESS
                    var _r14: _Parser_Item? = null
                    _r14 = _MemoCall(_memo, "LESS", _index.element, ::LESS, null)
                    if (_r14 != null) _index.element = _r14.nextIndex

                    // AND shortcut 13
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 13
                        continue
                    }

                    // AND 15
                    _start_i15 = _index.element

                    // AND 16
                    _start_i16 = _index.element

                    // CALLORVAR GenericId
                    var _r17: _Parser_Item? = null
                    _r17 = _MemoCall(_memo, "GenericId", _index.element, ::GenericId, null)
                    if (_r17 != null) _index.element = _r17.nextIndex

                    // AND shortcut 16
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 16
                        continue
                    }

                    // CALLORVAR SP
                    var _r18: _Parser_Item? = null
                    _r18 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r18 != null) _index.element = _r18.nextIndex

                    _label = 16
                }
                // AND 16
                16 -> {
                    val _r16_2 = _memo.results.pop()
                    val _r16_1 = _memo.results.pop()

                    if (_r16_1 != null && _r16_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i16, _index.element, _memo.input, (_r16_1.results + _r16_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i16
                    }

                    // AND shortcut 15
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 15
                        continue
                    }

                    // STAR 19
                    _start_i19 = _index.element
                    _label = 19
                }
                // STAR 19
                19 -> {
                    // AND 20
                    _start_i20 = _index.element

                    // AND 21
                    _start_i21 = _index.element

                    // CALLORVAR COMMA
                    var _r22: _Parser_Item? = null
                    _r22 = _MemoCall(_memo, "COMMA", _index.element, ::COMMA, null)
                    if (_r22 != null) _index.element = _r22.nextIndex

                    // AND shortcut 21
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 21
                        continue
                    }

                    // CALLORVAR GenericId
                    var _r23: _Parser_Item? = null
                    _r23 = _MemoCall(_memo, "GenericId", _index.element, ::GenericId, null)
                    if (_r23 != null) _index.element = _r23.nextIndex

                    _label = 21
                }
                // AND 21
                21 -> {
                    val _r21_2 = _memo.results.pop()
                    val _r21_1 = _memo.results.pop()

                    if (_r21_1 != null && _r21_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i21, _index.element, _memo.input, (_r21_1.results + _r21_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i21
                    }

                    // AND shortcut 20
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 20
                        continue
                    }

                    // CALLORVAR SP
                    var _r24: _Parser_Item? = null
                    _r24 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r24 != null) _index.element = _r24.nextIndex

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

                    // STAR 19
                    val _r19 = _memo.results.pop()
                    if (_r19 != null) {
                        _res19 += _r19.results
                        _label = 19
                        continue
                    } else {
                        _memo.results.push(_Parser_Item(_start_i19, _index.element, _memo.input, _res19.filterNotNull(), true))
                    }

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

                    _label = 13
                }
                // AND 13
                13 -> {
                    val _r13_2 = _memo.results.pop()
                    val _r13_1 = _memo.results.pop()

                    if (_r13_1 != null && _r13_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i13, _index.element, _memo.input, (_r13_1.results + _r13_2.results).filterNotNull(), true))
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

                    // CALLORVAR GREATER
                    var _r25: _Parser_Item? = null
                    _r25 = _MemoCall(_memo, "GREATER", _index.element, ::GREATER, null)
                    if (_r25 != null) _index.element = _r25.nextIndex

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

                    // STAR 11
                    val _r11 = _memo.results.pop()
                    if (_r11 != null) {
                        _res11 += _r11.results
                        _label = 11
                        continue
                    } else {
                        _memo.results.push(_Parser_Item(_start_i11, _index.element, _memo.input, _res11.filterNotNull(), true))
                    }

                    // BIND gp
                    gp = _memo.results.peek()

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
                        _memo.results.push(_Parser_Item(_r1.startIndex, _r1.nextIndex, _memo.input, _Thunk({ AST.Idfr(ids!!, gp) }, _r1), true))
                    }

                    break
                }
            }
        }
    }


    open fun Number(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 1
        var _start_i1 = _index.element

        // STAR 2
        var _start_i2 = _index.element
        val _inp2 = arrayListOf<Char?>()
        val _res2 = arrayListOf<AST.AstNode?>()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 1
                    _start_i1 = _index.element

                    // STAR 2
                    _start_i2 = _index.element
                    _label = 2
                }
                // STAR 2
                2 -> {
                    // INPUT CLASS
                    _ParseInputClass(_memo, _index, listOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9'))

                    // STAR 2
                    val _r2 = _memo.results.pop()
                    if (_r2 != null) {
                        _res2 += _r2.results
                        _label = 2
                        continue
                    } else {
                        _memo.results.push(_Parser_Item(_start_i2, _index.element, _memo.input, _res2.filterNotNull(), true))
                    }

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

                    break
                }
            }
        }
    }


    open fun KEYWORD(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

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
                    // OR 1
                    _start_i1 = _index.element

                    // OR 2
                    _start_i2 = _index.element

                    // OR 3
                    _start_i3 = _index.element

                    // OR 4
                    _start_i4 = _index.element

                    // CALLORVAR USING
                    var _r5: _Parser_Item? = null
                    _r5 = _MemoCall(_memo, "USING", _index.element, ::USING, null)
                    if (_r5 != null) _index.element = _r5.nextIndex

                    // OR shortcut 4
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i4
                    } else {
                        _label = 4
                        continue
                    }

                    // CALLORVAR KOMETA
                    var _r6: _Parser_Item? = null
                    _r6 = _MemoCall(_memo, "KOMETA", _index.element, ::KOMETA, null)
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

                    // CALLORVAR OVERRIDE
                    var _r7: _Parser_Item? = null
                    _r7 = _MemoCall(_memo, "OVERRIDE", _index.element, ::OVERRIDE, null)
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

                    // CALLORVAR NEW
                    var _r8: _Parser_Item? = null
                    _r8 = _MemoCall(_memo, "NEW", _index.element, ::NEW, null)
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

                    // CALLORVAR LR
                    var _r9: _Parser_Item? = null
                    _r9 = _MemoCall(_memo, "LR", _index.element, ::LR, null)
                    if (_r9 != null) _index.element = _r9.nextIndex

                    _label = 1
                }
                // OR 1
                1 -> {
                    break
                }
            }
        }
    }


    open fun USING(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 1
        var _start_i1 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 1
                    _start_i1 = _index.element

                    // LITERAL "import"
                    _ParseLiteralString(_memo, _index, "import")

                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // CALLORVAR SP
                    var _r3: _Parser_Item? = null
                    _r3 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r3 != null) _index.element = _r3.nextIndex

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

                    break
                }
            }
        }
    }


    open fun KOMETA(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 1
        var _start_i1 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 1
                    _start_i1 = _index.element

                    // LITERAL "kometa"
                    _ParseLiteralString(_memo, _index, "kometa")

                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // CALLORVAR SP
                    var _r3: _Parser_Item? = null
                    _r3 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r3 != null) _index.element = _r3.nextIndex

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

                    break
                }
            }
        }
    }


    open fun EQUALS(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 1
        var _start_i1 = _index.element

        // OR 2
        var _start_i2 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 1
                    _start_i1 = _index.element

                    // OR 2
                    _start_i2 = _index.element

                    // LITERAL "="
                    _ParseLiteralString(_memo, _index, "=")

                    // OR shortcut 2
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i2
                    } else {
                        _label = 2
                        continue
                    }

                    // LITERAL "::="
                    _ParseLiteralString(_memo, _index, "::=")

                    _label = 2
                }
                // OR 2
                2 -> {
                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // CALLORVAR SP
                    var _r5: _Parser_Item? = null
                    _r5 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r5 != null) _index.element = _r5.nextIndex

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

                    break
                }
            }
        }
    }


    open fun OVERRIDE(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 1
        var _start_i1 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 1
                    _start_i1 = _index.element

                    // LITERAL "override"
                    _ParseLiteralString(_memo, _index, "override")

                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // CALLORVAR SP
                    var _r3: _Parser_Item? = null
                    _r3 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r3 != null) _index.element = _r3.nextIndex

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

                    break
                }
            }
        }
    }


    open fun NEW(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 1
        var _start_i1 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 1
                    _start_i1 = _index.element

                    // LITERAL "new"
                    _ParseLiteralString(_memo, _index, "new")

                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // CALLORVAR SP
                    var _r3: _Parser_Item? = null
                    _r3 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r3 != null) _index.element = _r3.nextIndex

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

                    break
                }
            }
        }
    }


    open fun LR(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 1
        var _start_i1 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 1
                    _start_i1 = _index.element

                    // LITERAL "lr"
                    _ParseLiteralString(_memo, _index, "lr")

                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // CALLORVAR SP
                    var _r3: _Parser_Item? = null
                    _r3 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r3 != null) _index.element = _r3.nextIndex

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

                    break
                }
            }
        }
    }


    open fun SEMI(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 1
        var _start_i1 = _index.element

        // OR 2
        var _start_i2 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 1
                    _start_i1 = _index.element

                    // OR 2
                    _start_i2 = _index.element

                    // LITERAL ";"
                    _ParseLiteralString(_memo, _index, ";")

                    // OR shortcut 2
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i2
                    } else {
                        _label = 2
                        continue
                    }

                    // LITERAL ","
                    _ParseLiteralString(_memo, _index, ",")

                    _label = 2
                }
                // OR 2
                2 -> {
                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // CALLORVAR SP
                    var _r5: _Parser_Item? = null
                    _r5 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r5 != null) _index.element = _r5.nextIndex

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

                    break
                }
            }
        }
    }


    open fun BANG(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 1
        var _start_i1 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 1
                    _start_i1 = _index.element

                    // LITERAL "!"
                    _ParseLiteralString(_memo, _index, "!")

                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // CALLORVAR SP
                    var _r3: _Parser_Item? = null
                    _r3 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r3 != null) _index.element = _r3.nextIndex

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

                    break
                }
            }
        }
    }


    open fun OR(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 1
        var _start_i1 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 1
                    _start_i1 = _index.element

                    // LITERAL "|"
                    _ParseLiteralString(_memo, _index, "|")

                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // CALLORVAR SP
                    var _r3: _Parser_Item? = null
                    _r3 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r3 != null) _index.element = _r3.nextIndex

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

                    break
                }
            }
        }
    }


    open fun ACTION(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 1
        var _start_i1 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 1
                    _start_i1 = _index.element

                    // LITERAL "->"
                    _ParseLiteralString(_memo, _index, "->")

                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // CALLORVAR SP
                    var _r3: _Parser_Item? = null
                    _r3 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r3 != null) _index.element = _r3.nextIndex

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

                    break
                }
            }
        }
    }


    open fun COLON(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 1
        var _start_i1 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 1
                    _start_i1 = _index.element

                    // LITERAL ":"
                    _ParseLiteralString(_memo, _index, ":")

                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // CALLORVAR SP
                    var _r3: _Parser_Item? = null
                    _r3 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r3 != null) _index.element = _r3.nextIndex

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

                    break
                }
            }
        }
    }


    open fun COMMA(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 1
        var _start_i1 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 1
                    _start_i1 = _index.element

                    // LITERAL ","
                    _ParseLiteralString(_memo, _index, ",")

                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // CALLORVAR SP
                    var _r3: _Parser_Item? = null
                    _r3 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r3 != null) _index.element = _r3.nextIndex

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

                    break
                }
            }
        }
    }


    open fun DOT(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // LITERAL "."
                    _ParseLiteralString(_memo, _index, ".")

                    break
                }
            }
        }
    }


    open fun PERIOD(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 1
        var _start_i1 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 1
                    _start_i1 = _index.element

                    // CALLORVAR DOT
                    var _r2: _Parser_Item? = null
                    _r2 = _MemoCall(_memo, "DOT", _index.element, ::DOT, null)
                    if (_r2 != null) _index.element = _r2.nextIndex

                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // CALLORVAR SP
                    var _r3: _Parser_Item? = null
                    _r3 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r3 != null) _index.element = _r3.nextIndex

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

                    break
                }
            }
        }
    }


    open fun LEFT_BRACE(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 1
        var _start_i1 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 1
                    _start_i1 = _index.element

                    // LITERAL "{"
                    _ParseLiteralString(_memo, _index, "{")

                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // CALLORVAR SP
                    var _r3: _Parser_Item? = null
                    _r3 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r3 != null) _index.element = _r3.nextIndex

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

                    break
                }
            }
        }
    }


    open fun RIGHT_BRACE(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 1
        var _start_i1 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 1
                    _start_i1 = _index.element

                    // LITERAL "}"
                    _ParseLiteralString(_memo, _index, "}")

                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // CALLORVAR SP
                    var _r3: _Parser_Item? = null
                    _r3 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r3 != null) _index.element = _r3.nextIndex

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

                    break
                }
            }
        }
    }


    open fun OPEN(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 1
        var _start_i1 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 1
                    _start_i1 = _index.element

                    // LITERAL "("
                    _ParseLiteralString(_memo, _index, "(")

                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // CALLORVAR SP
                    var _r3: _Parser_Item? = null
                    _r3 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r3 != null) _index.element = _r3.nextIndex

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

                    break
                }
            }
        }
    }


    open fun CLOSE(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 1
        var _start_i1 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 1
                    _start_i1 = _index.element

                    // LITERAL ")"
                    _ParseLiteralString(_memo, _index, ")")

                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // CALLORVAR SP
                    var _r3: _Parser_Item? = null
                    _r3 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r3 != null) _index.element = _r3.nextIndex

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

                    break
                }
            }
        }
    }


    open fun LESS(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 1
        var _start_i1 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 1
                    _start_i1 = _index.element

                    // LITERAL "<"
                    _ParseLiteralString(_memo, _index, "<")

                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // CALLORVAR SP
                    var _r3: _Parser_Item? = null
                    _r3 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r3 != null) _index.element = _r3.nextIndex

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

                    break
                }
            }
        }
    }


    open fun GREATER(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 1
        var _start_i1 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 1
                    _start_i1 = _index.element

                    // LITERAL ">"
                    _ParseLiteralString(_memo, _index, ">")

                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // CALLORVAR SP
                    var _r3: _Parser_Item? = null
                    _r3 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r3 != null) _index.element = _r3.nextIndex

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

                    break
                }
            }
        }
    }


    open fun QUES(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // LITERAL "?"
                    _ParseLiteralString(_memo, _index, "?")

                    break
                }
            }
        }
    }


    open fun AND_PRE(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // LITERAL "&"
                    _ParseLiteralString(_memo, _index, "&")

                    break
                }
            }
        }
    }


    open fun NOT_PRE(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // LITERAL "~"
                    _ParseLiteralString(_memo, _index, "~")

                    break
                }
            }
        }
    }


    open fun STAR(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 1
        var _start_i1 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 1
                    _start_i1 = _index.element

                    // LITERAL "*"
                    _ParseLiteralString(_memo, _index, "*")

                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // CALLORVAR SP
                    var _r3: _Parser_Item? = null
                    _r3 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r3 != null) _index.element = _r3.nextIndex

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

                    break
                }
            }
        }
    }


    open fun PLUS(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 1
        var _start_i1 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 1
                    _start_i1 = _index.element

                    // LITERAL "+"
                    _ParseLiteralString(_memo, _index, "+")

                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // CALLORVAR SP
                    var _r3: _Parser_Item? = null
                    _r3 = _MemoCall(_memo, "SP", _index.element, ::SP, null)
                    if (_r3 != null) _index.element = _r3.nextIndex

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

                    break
                }
            }
        }
    }


    open fun SP(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // STAR 1
        var _start_i1 = _index.element
        val _inp1 = arrayListOf<Char?>()
        val _res1 = arrayListOf<AST.AstNode?>()

        // OR 2
        var _start_i2 = _index.element

        // OR 3
        var _start_i3 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // STAR 1
                    _start_i1 = _index.element
                    _label = 1
                }
                // STAR 1
                1 -> {
                    // OR 2
                    _start_i2 = _index.element

                    // OR 3
                    _start_i3 = _index.element

                    // CALLORVAR EOL
                    var _r4: _Parser_Item? = null
                    _r4 = _MemoCall(_memo, "EOL", _index.element, ::EOL, null)
                    if (_r4 != null) _index.element = _r4.nextIndex

                    // OR shortcut 3
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i3
                    } else {
                        _label = 3
                        continue
                    }

                    // CALLORVAR WS
                    var _r5: _Parser_Item? = null
                    _r5 = _MemoCall(_memo, "WS", _index.element, ::WS, null)
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

                    // CALLORVAR Comment
                    var _r6: _Parser_Item? = null
                    _r6 = _MemoCall(_memo, "Comment", _index.element, ::Comment, null)
                    if (_r6 != null) _index.element = _r6.nextIndex

                    _label = 2
                }
                // OR 2
                2 -> {
                    // STAR 1
                    val _r1 = _memo.results.pop()
                    if (_r1 != null) {
                        _res1 += _r1.results
                        _label = 1
                        continue
                    } else {
                        _memo.results.push(_Parser_Item(_start_i1, _index.element, _memo.input, _res1.filterNotNull(), true))
                    }

                    break
                }
            }
        }
    }


    open fun WS(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // OR 1
        var _start_i1 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // OR 1
                    _start_i1 = _index.element

                    // LITERAL " "
                    _ParseLiteralString(_memo, _index, " ")

                    // OR shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i1
                    } else {
                        _label = 1
                        continue
                    }

                    // LITERAL "\t"
                    _ParseLiteralString(_memo, _index, "\t")

                    _label = 1
                }
                // OR 1
                1 -> {
                    break
                }
            }
        }
    }


    open fun Comment(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // OR 0
        var _start_i0 = _index.element

        // AND 2
        var _start_i2 = _index.element

        // AND 3
        var _start_i3 = _index.element

        // STAR 5
        var _start_i5 = _index.element
        val _inp5 = arrayListOf<Char?>()
        val _res5 = arrayListOf<AST.AstNode?>()

        // AND 6
        var _start_i6 = _index.element

        // NOT 7
        var _start_i7 = _index.element

        // AND 12
        var _start_i12 = _index.element

        // AND 13
        var _start_i13 = _index.element

        // STAR 15
        var _start_i15 = _index.element
        val _inp15 = arrayListOf<Char?>()
        val _res15 = arrayListOf<AST.AstNode?>()

        // AND 16
        var _start_i16 = _index.element

        // NOT 17
        var _start_i17 = _index.element

        // OR 19
        var _start_i19 = _index.element

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

                    // LITERAL "//"
                    _ParseLiteralString(_memo, _index, "//")

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

                    // NOT 7
                    _start_i7 = _index.element

                    // CALLORVAR EOL
                    var _r8: _Parser_Item? = null
                    _r8 = _MemoCall(_memo, "EOL", _index.element, ::EOL, null)
                    if (_r8 != null) _index.element = _r8.nextIndex

                    // NOT 7
                    val _r7 = _memo.results.pop()
                    _memo.results.push(if (_r7 == null) _Parser_Item(_start_i7, _memo.input) else null)
                    _index.element = _start_i7
                    // AND shortcut 6
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 6
                        continue
                    }

                    // ANY
                    _ParseAny(_memo, _index)

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

                    // AND shortcut 2
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 2
                        continue
                    }

                    // CALLORVAR EOL
                    var _r10: _Parser_Item? = null
                    _r10 = _MemoCall(_memo, "EOL", _index.element, ::EOL, null)
                    if (_r10 != null) _index.element = _r10.nextIndex

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

                    // LITERAL "/*"
                    _ParseLiteralString(_memo, _index, "/*")

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
                    // AND 16
                    _start_i16 = _index.element

                    // NOT 17
                    _start_i17 = _index.element

                    // LITERAL "*/"
                    _ParseLiteralString(_memo, _index, "*/")

                    // NOT 17
                    val _r17 = _memo.results.pop()
                    _memo.results.push(if (_r17 == null) _Parser_Item(_start_i17, _memo.input) else null)
                    _index.element = _start_i17
                    // AND shortcut 16
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 16
                        continue
                    }

                    // OR 19
                    _start_i19 = _index.element

                    // CALLORVAR EOL
                    var _r20: _Parser_Item? = null
                    _r20 = _MemoCall(_memo, "EOL", _index.element, ::EOL, null)
                    if (_r20 != null) _index.element = _r20.nextIndex

                    // OR shortcut 19
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i19
                    } else {
                        _label = 19
                        continue
                    }

                    // ANY
                    _ParseAny(_memo, _index)

                    _label = 19
                }
                // OR 19
                19 -> {
                    _label = 16
                }
                // AND 16
                16 -> {
                    val _r16_2 = _memo.results.pop()
                    val _r16_1 = _memo.results.pop()

                    if (_r16_1 != null && _r16_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i16, _index.element, _memo.input, (_r16_1.results + _r16_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i16
                    }

                    // STAR 15
                    val _r15 = _memo.results.pop()
                    if (_r15 != null) {
                        _res15 += _r15.results
                        _label = 15
                        continue
                    } else {
                        _memo.results.push(_Parser_Item(_start_i15, _index.element, _memo.input, _res15.filterNotNull(), true))
                    }

                    _label = 13
                }
                // AND 13
                13 -> {
                    val _r13_2 = _memo.results.pop()
                    val _r13_1 = _memo.results.pop()

                    if (_r13_1 != null && _r13_2 != null) {
                        _memo.results.push(_Parser_Item(_start_i13, _index.element, _memo.input, (_r13_1.results + _r13_2.results).filterNotNull(), true))
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

                    // LITERAL "*/"
                    _ParseLiteralString(_memo, _index, "*/")

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

                    _label = 0
                }
                // OR 0
                0 -> {
                    break
                }
            }
        }
    }


    open fun EOL(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var nl: _Parser_Item? = null

        // OR 3
        var _start_i3 = _index.element

        // AND 4
        var _start_i4 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // OR 3
                    _start_i3 = _index.element

                    // AND 4
                    _start_i4 = _index.element

                    // LITERAL "\r"
                    _ParseLiteralString(_memo, _index, "\r")

                    // AND shortcut 4
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 4
                        continue
                    }

                    // LITERAL "\n"
                    _ParseLiteralString(_memo, _index, "\n")

                    // QUES 6
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

                    // OR shortcut 3
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i3
                    } else {
                        _label = 3
                        continue
                    }

                    // LITERAL "\n"
                    _ParseLiteralString(_memo, _index, "\n")

                    _label = 3
                }
                // OR 3
                3 -> {
                    // BIND nl
                    nl = _memo.results.peek()

                    // ACT 1
                    val _r1 = _memo.results.peek()
                    if (_r1 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Parser_Item(_r1.startIndex, _r1.nextIndex, _memo.input, _Thunk({ _memo.positions += nl!!.nextIndex; nl?.asResult() ?: AST.Code(MatchItem(listOf('\n'))) }, _r1), true))
                    }

                    break
                }
            }
        }
    }


    open fun EOF(_memo: _Parser_Memo, __index: Int, _args: _Parser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // NOT 1
        var _start_i1 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // NOT 1
                    _start_i1 = _index.element

                    // ANY
                    _ParseAny(_memo, _index)

                    // NOT 1
                    val _r1 = _memo.results.pop()
                    _memo.results.push(if (_r1 == null) _Parser_Item(_start_i1, _memo.input) else null)
                    _index.element = _start_i1
                    break
                }
            }
        }
    }

}
