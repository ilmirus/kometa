//
// KOMeta UuidParser Parser
//

package kometa.UUID

import kometa.*
import kometa.util.*
import kotlin.jvm.internal.Ref

import kometa.CharMatcher

typealias _UuidParser_Inputs = Iterable<Char>
typealias _UuidParser_Results = Iterable<String>
typealias _UuidParser_Item = MatchItem<Char, String>
typealias _UuidParser_Args = Iterable<_UuidParser_Item>
typealias _UuidParser_Memo = MatchState<Char, String>
typealias _UuidParser_Rule = Production<Char, String>
typealias _UuidParser_Base = Matcher<Char, String>

open class UuidParser : CharMatcher<String>() {
    init {
        terminals = setOf(
            "EOF",
            "clock_seq_and_reserved",
            "clock_seq_low",
            "hexDigit",
        )
    }

    override fun TOP(_memo: _UuidParser_Memo, __index: Int, _args: _UuidParser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // CALLORVAR UUID
                    var _r1: _UuidParser_Item? = null
                    _r1 = _MemoCall(_memo, "UUID", _index.element, ::UUID, null)
                    if (_r1 != null) _index.element = _r1.nextIndex

                    // ACT 0
                    val _r0 = _memo.results.peek()
                    if (_r0 != null) {
                        _memo.results.pop()
                        _memo.results.push(_UuidParser_Item(_r0.startIndex, _r0.nextIndex, _memo.input, _Thunk({ it.s }, _r0), true))
                    }

                    break
                }
            }
        }
    }


    open fun UUID(_memo: _UuidParser_Memo, __index: Int, _args: _UuidParser_Args?) {
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

                    // CALLORVAR time_low
                    var _r10: _UuidParser_Item? = null
                    _r10 = _MemoCall(_memo, "time_low", _index.element, ::time_low, null)
                    if (_r10 != null) _index.element = _r10.nextIndex

                    // AND shortcut 9
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 9
                        continue
                    }

                    // LITERAL "-"
                    _ParseLiteralString(_memo, _index, "-")

                    _label = 9
                }
                // AND 9
                9 -> {
                    val _r9_2 = _memo.results.pop()
                    val _r9_1 = _memo.results.pop()

                    if (_r9_1 != null && _r9_2 != null) {
                        _memo.results.push(_UuidParser_Item(_start_i9, _index.element, _memo.input, (_r9_1.results + _r9_2.results).filterNotNull(), true))
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

                    // CALLORVAR time_mid
                    var _r12: _UuidParser_Item? = null
                    _r12 = _MemoCall(_memo, "time_mid", _index.element, ::time_mid, null)
                    if (_r12 != null) _index.element = _r12.nextIndex

                    _label = 8
                }
                // AND 8
                8 -> {
                    val _r8_2 = _memo.results.pop()
                    val _r8_1 = _memo.results.pop()

                    if (_r8_1 != null && _r8_2 != null) {
                        _memo.results.push(_UuidParser_Item(_start_i8, _index.element, _memo.input, (_r8_1.results + _r8_2.results).filterNotNull(), true))
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

                    // LITERAL "-"
                    _ParseLiteralString(_memo, _index, "-")

                    _label = 7
                }
                // AND 7
                7 -> {
                    val _r7_2 = _memo.results.pop()
                    val _r7_1 = _memo.results.pop()

                    if (_r7_1 != null && _r7_2 != null) {
                        _memo.results.push(_UuidParser_Item(_start_i7, _index.element, _memo.input, (_r7_1.results + _r7_2.results).filterNotNull(), true))
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

                    // CALLORVAR time_high_and_version
                    var _r14: _UuidParser_Item? = null
                    _r14 = _MemoCall(_memo, "time_high_and_version", _index.element, ::time_high_and_version, null)
                    if (_r14 != null) _index.element = _r14.nextIndex

                    _label = 6
                }
                // AND 6
                6 -> {
                    val _r6_2 = _memo.results.pop()
                    val _r6_1 = _memo.results.pop()

                    if (_r6_1 != null && _r6_2 != null) {
                        _memo.results.push(_UuidParser_Item(_start_i6, _index.element, _memo.input, (_r6_1.results + _r6_2.results).filterNotNull(), true))
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

                    // LITERAL "-"
                    _ParseLiteralString(_memo, _index, "-")

                    _label = 5
                }
                // AND 5
                5 -> {
                    val _r5_2 = _memo.results.pop()
                    val _r5_1 = _memo.results.pop()

                    if (_r5_1 != null && _r5_2 != null) {
                        _memo.results.push(_UuidParser_Item(_start_i5, _index.element, _memo.input, (_r5_1.results + _r5_2.results).filterNotNull(), true))
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

                    // CALLORVAR clock_seq_and_reserved
                    var _r16: _UuidParser_Item? = null
                    _r16 = _MemoCall(_memo, "clock_seq_and_reserved", _index.element, ::clock_seq_and_reserved, null)
                    if (_r16 != null) _index.element = _r16.nextIndex

                    _label = 4
                }
                // AND 4
                4 -> {
                    val _r4_2 = _memo.results.pop()
                    val _r4_1 = _memo.results.pop()

                    if (_r4_1 != null && _r4_2 != null) {
                        _memo.results.push(_UuidParser_Item(_start_i4, _index.element, _memo.input, (_r4_1.results + _r4_2.results).filterNotNull(), true))
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

                    // CALLORVAR clock_seq_low
                    var _r17: _UuidParser_Item? = null
                    _r17 = _MemoCall(_memo, "clock_seq_low", _index.element, ::clock_seq_low, null)
                    if (_r17 != null) _index.element = _r17.nextIndex

                    _label = 3
                }
                // AND 3
                3 -> {
                    val _r3_2 = _memo.results.pop()
                    val _r3_1 = _memo.results.pop()

                    if (_r3_1 != null && _r3_2 != null) {
                        _memo.results.push(_UuidParser_Item(_start_i3, _index.element, _memo.input, (_r3_1.results + _r3_2.results).filterNotNull(), true))
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

                    // LITERAL "-"
                    _ParseLiteralString(_memo, _index, "-")

                    _label = 2
                }
                // AND 2
                2 -> {
                    val _r2_2 = _memo.results.pop()
                    val _r2_1 = _memo.results.pop()

                    if (_r2_1 != null && _r2_2 != null) {
                        _memo.results.push(_UuidParser_Item(_start_i2, _index.element, _memo.input, (_r2_1.results + _r2_2.results).filterNotNull(), true))
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

                    // CALLORVAR node
                    var _r19: _UuidParser_Item? = null
                    _r19 = _MemoCall(_memo, "node", _index.element, ::node, null)
                    if (_r19 != null) _index.element = _r19.nextIndex

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_UuidParser_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
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

                    // CALLORVAR EOF
                    var _r20: _UuidParser_Item? = null
                    _r20 = _MemoCall(_memo, "EOF", _index.element, ::EOF, null)
                    if (_r20 != null) _index.element = _r20.nextIndex

                    _label = 0
                }
                // AND 0
                0 -> {
                    val _r0_2 = _memo.results.pop()
                    val _r0_1 = _memo.results.pop()

                    if (_r0_1 != null && _r0_2 != null) {
                        _memo.results.push(_UuidParser_Item(_start_i0, _index.element, _memo.input, (_r0_1.results + _r0_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i0
                    }

                    break
                }
            }
        }
    }


    open fun time_low(_memo: _UuidParser_Memo, __index: Int, _args: _UuidParser_Args?) {
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

                    // CALLORVAR hexOctet
                    var _r3: _UuidParser_Item? = null
                    _r3 = _MemoCall(_memo, "hexOctet", _index.element, ::hexOctet, null)
                    if (_r3 != null) _index.element = _r3.nextIndex

                    // AND shortcut 2
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 2
                        continue
                    }

                    // CALLORVAR hexOctet
                    var _r4: _UuidParser_Item? = null
                    _r4 = _MemoCall(_memo, "hexOctet", _index.element, ::hexOctet, null)
                    if (_r4 != null) _index.element = _r4.nextIndex

                    _label = 2
                }
                // AND 2
                2 -> {
                    val _r2_2 = _memo.results.pop()
                    val _r2_1 = _memo.results.pop()

                    if (_r2_1 != null && _r2_2 != null) {
                        _memo.results.push(_UuidParser_Item(_start_i2, _index.element, _memo.input, (_r2_1.results + _r2_2.results).filterNotNull(), true))
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

                    // CALLORVAR hexOctet
                    var _r5: _UuidParser_Item? = null
                    _r5 = _MemoCall(_memo, "hexOctet", _index.element, ::hexOctet, null)
                    if (_r5 != null) _index.element = _r5.nextIndex

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_UuidParser_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
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

                    // CALLORVAR hexOctet
                    var _r6: _UuidParser_Item? = null
                    _r6 = _MemoCall(_memo, "hexOctet", _index.element, ::hexOctet, null)
                    if (_r6 != null) _index.element = _r6.nextIndex

                    _label = 0
                }
                // AND 0
                0 -> {
                    val _r0_2 = _memo.results.pop()
                    val _r0_1 = _memo.results.pop()

                    if (_r0_1 != null && _r0_2 != null) {
                        _memo.results.push(_UuidParser_Item(_start_i0, _index.element, _memo.input, (_r0_1.results + _r0_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i0
                    }

                    break
                }
            }
        }
    }


    open fun time_mid(_memo: _UuidParser_Memo, __index: Int, _args: _UuidParser_Args?) {
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

                    // CALLORVAR hexOctet
                    var _r1: _UuidParser_Item? = null
                    _r1 = _MemoCall(_memo, "hexOctet", _index.element, ::hexOctet, null)
                    if (_r1 != null) _index.element = _r1.nextIndex

                    // AND shortcut 0
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 0
                        continue
                    }

                    // CALLORVAR hexOctet
                    var _r2: _UuidParser_Item? = null
                    _r2 = _MemoCall(_memo, "hexOctet", _index.element, ::hexOctet, null)
                    if (_r2 != null) _index.element = _r2.nextIndex

                    _label = 0
                }
                // AND 0
                0 -> {
                    val _r0_2 = _memo.results.pop()
                    val _r0_1 = _memo.results.pop()

                    if (_r0_1 != null && _r0_2 != null) {
                        _memo.results.push(_UuidParser_Item(_start_i0, _index.element, _memo.input, (_r0_1.results + _r0_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i0
                    }

                    break
                }
            }
        }
    }


    open fun time_high_and_version(_memo: _UuidParser_Memo, __index: Int, _args: _UuidParser_Args?) {
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

                    // CALLORVAR hexOctet
                    var _r1: _UuidParser_Item? = null
                    _r1 = _MemoCall(_memo, "hexOctet", _index.element, ::hexOctet, null)
                    if (_r1 != null) _index.element = _r1.nextIndex

                    // AND shortcut 0
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 0
                        continue
                    }

                    // CALLORVAR hexOctet
                    var _r2: _UuidParser_Item? = null
                    _r2 = _MemoCall(_memo, "hexOctet", _index.element, ::hexOctet, null)
                    if (_r2 != null) _index.element = _r2.nextIndex

                    _label = 0
                }
                // AND 0
                0 -> {
                    val _r0_2 = _memo.results.pop()
                    val _r0_1 = _memo.results.pop()

                    if (_r0_1 != null && _r0_2 != null) {
                        _memo.results.push(_UuidParser_Item(_start_i0, _index.element, _memo.input, (_r0_1.results + _r0_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i0
                    }

                    break
                }
            }
        }
    }


    open fun clock_seq_and_reserved(_memo: _UuidParser_Memo, __index: Int, _args: _UuidParser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // CALLORVAR hexOctet
                    var _r0: _UuidParser_Item? = null
                    _r0 = _MemoCall(_memo, "hexOctet", _index.element, ::hexOctet, null)
                    if (_r0 != null) _index.element = _r0.nextIndex

                    break
                }
            }
        }
    }


    open fun clock_seq_low(_memo: _UuidParser_Memo, __index: Int, _args: _UuidParser_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // CALLORVAR hexOctet
                    var _r0: _UuidParser_Item? = null
                    _r0 = _MemoCall(_memo, "hexOctet", _index.element, ::hexOctet, null)
                    if (_r0 != null) _index.element = _r0.nextIndex

                    break
                }
            }
        }
    }


    open fun node(_memo: _UuidParser_Memo, __index: Int, _args: _UuidParser_Args?) {
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

        // AND 3
        var _start_i3 = _index.element

        // AND 4
        var _start_i4 = _index.element

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

                    // AND 3
                    _start_i3 = _index.element

                    // AND 4
                    _start_i4 = _index.element

                    // CALLORVAR hexOctet
                    var _r5: _UuidParser_Item? = null
                    _r5 = _MemoCall(_memo, "hexOctet", _index.element, ::hexOctet, null)
                    if (_r5 != null) _index.element = _r5.nextIndex

                    // AND shortcut 4
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 4
                        continue
                    }

                    // CALLORVAR hexOctet
                    var _r6: _UuidParser_Item? = null
                    _r6 = _MemoCall(_memo, "hexOctet", _index.element, ::hexOctet, null)
                    if (_r6 != null) _index.element = _r6.nextIndex

                    _label = 4
                }
                // AND 4
                4 -> {
                    val _r4_2 = _memo.results.pop()
                    val _r4_1 = _memo.results.pop()

                    if (_r4_1 != null && _r4_2 != null) {
                        _memo.results.push(_UuidParser_Item(_start_i4, _index.element, _memo.input, (_r4_1.results + _r4_2.results).filterNotNull(), true))
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

                    // CALLORVAR hexOctet
                    var _r7: _UuidParser_Item? = null
                    _r7 = _MemoCall(_memo, "hexOctet", _index.element, ::hexOctet, null)
                    if (_r7 != null) _index.element = _r7.nextIndex

                    _label = 3
                }
                // AND 3
                3 -> {
                    val _r3_2 = _memo.results.pop()
                    val _r3_1 = _memo.results.pop()

                    if (_r3_1 != null && _r3_2 != null) {
                        _memo.results.push(_UuidParser_Item(_start_i3, _index.element, _memo.input, (_r3_1.results + _r3_2.results).filterNotNull(), true))
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

                    // CALLORVAR hexOctet
                    var _r8: _UuidParser_Item? = null
                    _r8 = _MemoCall(_memo, "hexOctet", _index.element, ::hexOctet, null)
                    if (_r8 != null) _index.element = _r8.nextIndex

                    _label = 2
                }
                // AND 2
                2 -> {
                    val _r2_2 = _memo.results.pop()
                    val _r2_1 = _memo.results.pop()

                    if (_r2_1 != null && _r2_2 != null) {
                        _memo.results.push(_UuidParser_Item(_start_i2, _index.element, _memo.input, (_r2_1.results + _r2_2.results).filterNotNull(), true))
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

                    // CALLORVAR hexOctet
                    var _r9: _UuidParser_Item? = null
                    _r9 = _MemoCall(_memo, "hexOctet", _index.element, ::hexOctet, null)
                    if (_r9 != null) _index.element = _r9.nextIndex

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_UuidParser_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
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

                    // CALLORVAR hexOctet
                    var _r10: _UuidParser_Item? = null
                    _r10 = _MemoCall(_memo, "hexOctet", _index.element, ::hexOctet, null)
                    if (_r10 != null) _index.element = _r10.nextIndex

                    _label = 0
                }
                // AND 0
                0 -> {
                    val _r0_2 = _memo.results.pop()
                    val _r0_1 = _memo.results.pop()

                    if (_r0_1 != null && _r0_2 != null) {
                        _memo.results.push(_UuidParser_Item(_start_i0, _index.element, _memo.input, (_r0_1.results + _r0_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i0
                    }

                    break
                }
            }
        }
    }


    open fun hexOctet(_memo: _UuidParser_Memo, __index: Int, _args: _UuidParser_Args?) {
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

                    // CALLORVAR hexDigit
                    var _r1: _UuidParser_Item? = null
                    _r1 = _MemoCall(_memo, "hexDigit", _index.element, ::hexDigit, null)
                    if (_r1 != null) _index.element = _r1.nextIndex

                    // AND shortcut 0
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 0
                        continue
                    }

                    // CALLORVAR hexDigit
                    var _r2: _UuidParser_Item? = null
                    _r2 = _MemoCall(_memo, "hexDigit", _index.element, ::hexDigit, null)
                    if (_r2 != null) _index.element = _r2.nextIndex

                    _label = 0
                }
                // AND 0
                0 -> {
                    val _r0_2 = _memo.results.pop()
                    val _r0_1 = _memo.results.pop()

                    if (_r0_1 != null && _r0_2 != null) {
                        _memo.results.push(_UuidParser_Item(_start_i0, _index.element, _memo.input, (_r0_1.results + _r0_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i0
                    }

                    break
                }
            }
        }
    }


    open fun hexDigit(_memo: _UuidParser_Memo, __index: Int, _args: _UuidParser_Args?) {
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

                    // INPUT CLASS
                    _ParseInputClass(_memo, _index, listOf('\u0030', '\u0031', '\u0032', '\u0033', '\u0034', '\u0035', '\u0036', '\u0037', '\u0038', '\u0039'))

                    // OR shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i1
                    } else {
                        _label = 1
                        continue
                    }

                    // INPUT CLASS
                    _ParseInputClass(_memo, _index, listOf('\u0061', '\u0062', '\u0063', '\u0064', '\u0065', '\u0066', '\u0067', '\u0068'))

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

                    // INPUT CLASS
                    _ParseInputClass(_memo, _index, listOf('\u0041', '\u0042', '\u0043', '\u0044', '\u0045', '\u0046', '\u0047', '\u0048'))

                    _label = 0
                }
                // OR 0
                0 -> {
                    break
                }
            }
        }
    }


    open fun EOF(_memo: _UuidParser_Memo, __index: Int, _args: _UuidParser_Args?) {
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
                    _memo.results.push(if (_r0 == null) _UuidParser_Item(_start_i0, _memo.input) else null)
                    _index.element = _start_i0
                    break
                }
            }
        }
    }

}
