//
// KOMeta Calc Parser
//

package kometa.calc

import kometa.*
import kometa.util.*
import kotlin.jvm.internal.Ref

import kometa.CharMatcher

typealias _Calc_Inputs = Iterable<Char>
typealias _Calc_Results = Iterable<Int>
typealias _Calc_Item = MatchItem<Char, Int>
typealias _Calc_Args = Iterable<_Calc_Item>
typealias _Calc_Memo = MatchState<Char, Int>
typealias _Calc_Rule = Production<Char, Int>
typealias _Calc_Base = Matcher<Char, Int>

open class Calc : CharMatcher<Int>() {
    init {
        terminals = setOf(
            "DecimalDigit",
            "Expression",
            "TOP",
            "WS",
        )
    }

    override fun TOP(_memo: _Calc_Memo, __index: Int, _args: _Calc_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // CALLORVAR Expression
                    var _r0: _Calc_Item? = null
                    _r0 = _MemoCall(_memo, "Expression", _index.element, ::Expression, null)
                    if (_r0 != null) _index.element = _r0.nextIndex

                    break
                }
            }
        }
    }


    open fun Expression(_memo: _Calc_Memo, __index: Int, _args: _Calc_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // CALLORVAR Additive
                    var _r0: _Calc_Item? = null
                    _r0 = _MemoCall(_memo, "Additive", _index.element, ::Additive, null)
                    if (_r0 != null) _index.element = _r0.nextIndex

                    break
                }
            }
        }
    }


    open fun Additive(_memo: _Calc_Memo, __index: Int, _args: _Calc_Args?) {
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

                    // CALLORVAR Add
                    var _r2: _Calc_Item? = null
                    _r2 = _MemoCall(_memo, "Add", _index.element, ::Add, null)
                    if (_r2 != null) _index.element = _r2.nextIndex

                    // OR shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i1
                    } else {
                        _label = 1
                        continue
                    }

                    // CALLORVAR Sub
                    var _r3: _Calc_Item? = null
                    _r3 = _MemoCall(_memo, "Sub", _index.element, ::Sub, null)
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

                    // CALLORVAR Multiplicative
                    var _r4: _Calc_Item? = null
                    _r4 = _MemoCall(_memo, "Multiplicative", _index.element, ::Multiplicative, null)
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


    open fun Add(_memo: _Calc_Memo, __index: Int, _args: _Calc_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // CALL BinaryOp
                    val _start_i1 = _index.element
                    var _r1: _Calc_Item? = null
                    val _arg1_0 = '+'

                    var _actual_args1: _Calc_Args = mutableListOf(_Calc_Item(Production("Multiplicative", ::Multiplicative))!!, _Calc_Item(_arg1_0)!!, _Calc_Item(Production("Additive", ::Additive))!!)
                    if (_args != null) {
                        _actual_args1 += _args.drop(_arg_index.element)
                    }
                    _r1 = _MemoCall(_memo, "BinaryOp", _index.element, ::BinaryOp, _actual_args1)

                    if (_r1 != null) {
                        _index.element = _r1.nextIndex
                    }

                    // ACT 0
                    val _r0 = _memo.results.peek()
                    if (_r0 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Calc_Item(_r0.startIndex, _r0.nextIndex, _memo.input, _Thunk({ it.results.filterNotNull().reduce { a, b -> a + b } }, _r0), true))
                    }

                    break
                }
            }
        }
    }


    open fun Sub(_memo: _Calc_Memo, __index: Int, _args: _Calc_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // CALL BinaryOp
                    val _start_i1 = _index.element
                    var _r1: _Calc_Item? = null
                    val _arg1_0 = '-'

                    var _actual_args1: _Calc_Args = mutableListOf(_Calc_Item(Production("Multiplicative", ::Multiplicative))!!, _Calc_Item(_arg1_0)!!, _Calc_Item(Production("Additive", ::Additive))!!)
                    if (_args != null) {
                        _actual_args1 += _args.drop(_arg_index.element)
                    }
                    _r1 = _MemoCall(_memo, "BinaryOp", _index.element, ::BinaryOp, _actual_args1)

                    if (_r1 != null) {
                        _index.element = _r1.nextIndex
                    }

                    // ACT 0
                    val _r0 = _memo.results.peek()
                    if (_r0 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Calc_Item(_r0.startIndex, _r0.nextIndex, _memo.input, _Thunk({ it.results.filterNotNull().reduce { a, b -> a - b } }, _r0), true))
                    }

                    break
                }
            }
        }
    }


    open fun Multiplicative(_memo: _Calc_Memo, __index: Int, _args: _Calc_Args?) {
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

                    // CALLORVAR Multiply
                    var _r2: _Calc_Item? = null
                    _r2 = _MemoCall(_memo, "Multiply", _index.element, ::Multiply, null)
                    if (_r2 != null) _index.element = _r2.nextIndex

                    // OR shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i1
                    } else {
                        _label = 1
                        continue
                    }

                    // CALLORVAR Divide
                    var _r3: _Calc_Item? = null
                    _r3 = _MemoCall(_memo, "Divide", _index.element, ::Divide, null)
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

                    // CALL Number
                    val _start_i4 = _index.element
                    var _r4: _Calc_Item? = null

                    var _actual_args4: _Calc_Args = mutableListOf(_Calc_Item(Production("DecimalDigit", ::DecimalDigit))!!)
                    if (_args != null) {
                        _actual_args4 += _args.drop(_arg_index.element)
                    }
                    _r4 = _MemoCall(_memo, "Number", _index.element, ::Number, _actual_args4)

                    if (_r4 != null) {
                        _index.element = _r4.nextIndex
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


    open fun Multiply(_memo: _Calc_Memo, __index: Int, _args: _Calc_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // CALL BinaryOp
                    val _start_i1 = _index.element
                    var _r1: _Calc_Item? = null
                    val _arg1_0 = "*"

                    var _actual_args1: _Calc_Args = mutableListOf(_Calc_Item(Production("Number", ::Number))!!, _Calc_Item(_arg1_0.toList())!!, _Calc_Item(Production("Multiplicative", ::Multiplicative))!!, _Calc_Item(Production("DecimalDigit", ::DecimalDigit))!!)
                    if (_args != null) {
                        _actual_args1 += _args.drop(_arg_index.element)
                    }
                    _r1 = _MemoCall(_memo, "BinaryOp", _index.element, ::BinaryOp, _actual_args1)

                    if (_r1 != null) {
                        _index.element = _r1.nextIndex
                    }

                    // ACT 0
                    val _r0 = _memo.results.peek()
                    if (_r0 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Calc_Item(_r0.startIndex, _r0.nextIndex, _memo.input, _Thunk({ it.results.filterNotNull().reduce { a, b -> a * b }!! }, _r0), true))
                    }

                    break
                }
            }
        }
    }


    open fun Divide(_memo: _Calc_Memo, __index: Int, _args: _Calc_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // CALL BinaryOp
                    val _start_i1 = _index.element
                    var _r1: _Calc_Item? = null
                    val _arg1_0 = "/"

                    var _actual_args1: _Calc_Args = mutableListOf(_Calc_Item(Production("Number", ::Number))!!, _Calc_Item(_arg1_0.toList())!!, _Calc_Item(Production("Multiplicative", ::Multiplicative))!!, _Calc_Item(Production("DecimalDigit", ::DecimalDigit))!!)
                    if (_args != null) {
                        _actual_args1 += _args.drop(_arg_index.element)
                    }
                    _r1 = _MemoCall(_memo, "BinaryOp", _index.element, ::BinaryOp, _actual_args1)

                    if (_r1 != null) {
                        _index.element = _r1.nextIndex
                    }

                    // ACT 0
                    val _r0 = _memo.results.peek()
                    if (_r0 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Calc_Item(_r0.startIndex, _r0.nextIndex, _memo.input, _Thunk({ it.results.filterNotNull().reduce { a, b -> a / b } }, _r0), true))
                    }

                    break
                }
            }
        }
    }


    open fun BinaryOp(_memo: _Calc_Memo, __index: Int, _args: _Calc_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var first: _Calc_Item? = null
        var op: _Calc_Item? = null
        var second: _Calc_Item? = null
        var type: _Calc_Item? = null
        var a: _Calc_Item? = null
        var b: _Calc_Item? = null

        // ARGS 0
        var _start_i0 = _index.element

        // AND 1
        var _start_i1 = _index.element

        // AND 2
        var _start_i2 = _index.element

        // AND 3
        var _start_i3 = _index.element

        // AND 14
        var _start_i14 = _index.element

        // AND 15
        var _start_i15 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // ARGS 0
                    _arg_index.element = 0
                    _arg_input_index.element = 0
                        // AND 1
                        _start_i1 = _arg_index.element

                        // AND 2
                        _start_i2 = _arg_index.element

                        // AND 3
                        _start_i3 = _arg_index.element

                        // ANY
                        _ParseAnyArgs(_memo, _arg_index, _arg_input_index, _args)

                        // BIND first
                        first = _memo.argResults.peek()

                        // AND shortcut 3
                        if (_memo.argResults.peek() == null) {
                            _memo.argResults.push(null)
                            _label = 3
                            continue
                        }

                        // ANY
                        _ParseAnyArgs(_memo, _arg_index, _arg_input_index, _args)

                        // BIND op
                        op = _memo.argResults.peek()

                        _label = 3
                    }
                    // AND 3
                    3 -> {
                        val _r3_2 = _memo.argResults.pop()
                        val _r3_1 = _memo.argResults.pop()

                        if (_r3_1 != null && _r3_2 != null) {
                            _memo.argResults.push(_Calc_Item(_start_i3, _arg_index.element, (_r3_1.inputs + _r3_2.inputs).filterNotNull(), (_r3_1.results + _r3_2.results).filterNotNull(), false))
                        } else {
                            _memo.argResults.push(null)
                            _arg_index.element = _start_i3
                        }

                        // AND shortcut 2
                        if (_memo.argResults.peek() == null) {
                            _memo.argResults.push(null)
                            _label = 2
                            continue
                        }

                        // ANY
                        _ParseAnyArgs(_memo, _arg_index, _arg_input_index, _args)

                        // BIND second
                        second = _memo.argResults.peek()

                        _label = 2
                    }
                    // AND 2
                    2 -> {
                        val _r2_2 = _memo.argResults.pop()
                        val _r2_1 = _memo.argResults.pop()

                        if (_r2_1 != null && _r2_2 != null) {
                            _memo.argResults.push(_Calc_Item(_start_i2, _arg_index.element, (_r2_1.inputs + _r2_2.inputs).filterNotNull(), (_r2_1.results + _r2_2.results).filterNotNull(), false))
                        } else {
                            _memo.argResults.push(null)
                            _arg_index.element = _start_i2
                        }

                        // AND shortcut 1
                        if (_memo.argResults.peek() == null) {
                            _memo.argResults.push(null)
                            _label = 1
                            continue
                        }

                        // ANY
                        _ParseAnyArgs(_memo, _arg_index, _arg_input_index, _args)

                        // QUES 11
                        if (_memo.argResults.peek() == null) {
                            _memo.argResults.pop()
                            _memo.argResults.push(_Calc_Item(_arg_index.element, _memo.input))
                        }
                        // BIND type
                        type = _memo.argResults.peek()

                        _label = 1
                    }
                    // AND 1
                    1 -> {
                        val _r1_2 = _memo.argResults.pop()
                        val _r1_1 = _memo.argResults.pop()

                        if (_r1_1 != null && _r1_2 != null) {
                            _memo.argResults.push(_Calc_Item(_start_i1, _arg_index.element, (_r1_1.inputs + _r1_2.inputs).filterNotNull(), (_r1_1.results + _r1_2.results).filterNotNull(), false))
                        } else {
                            _memo.argResults.push(null)
                            _arg_index.element = _start_i1
                        }

                    if (_memo.argResults.pop() == null) {
                        _memo.results.push(null)
                        _label = 0
                        continue
                    }

                    // AND 14
                    _start_i14 = _index.element

                    // AND 15
                    _start_i15 = _index.element

                    // CALL first
                    val _start_i17 = _index.element
                    var _r17: _Calc_Item? = null

                    var _actual_args17: _Calc_Args = mutableListOf(type!!)
                    if (_args != null) {
                        _actual_args17 += _args.drop(_arg_index.element)
                    }
                    _r17 = _MemoCall(_memo, first?.productionName!!, _index.element, first?.production?.op!!, _actual_args17)

                    if (_r17 != null) {
                        _index.element = _r17.nextIndex
                    }

                    // BIND a
                    a = _memo.results.peek()

                    // AND shortcut 15
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 15
                        continue
                    }

                    // CALL KW
                    val _start_i18 = _index.element
                    var _r18: _Calc_Item? = null

                    var _actual_args18: _Calc_Args = mutableListOf(op!!)
                    if (_args != null) {
                        _actual_args18 += _args.drop(_arg_index.element)
                    }
                    _r18 = _MemoCall(_memo, "KW", _index.element, ::KW, _actual_args18)

                    if (_r18 != null) {
                        _index.element = _r18.nextIndex
                    }

                    _label = 15
                }
                // AND 15
                15 -> {
                    val _r15_2 = _memo.results.pop()
                    val _r15_1 = _memo.results.pop()

                    if (_r15_1 != null && _r15_2 != null) {
                        _memo.results.push(_Calc_Item(_start_i15, _index.element, _memo.input, (_r15_1.results + _r15_2.results).filterNotNull(), true))
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

                    // CALLORVAR second
                    var _r20: _Calc_Item? = null
                    if (second?.production != null) {
                        _r20 = _MemoCall(_memo, second.production!!.methodName, _index.element, second.production!!.op, if (_args != null) _args.drop(_arg_index.element) else null)
                    } else {
                        _r20 = _ParseLiteralObj(_memo, _index, second?.inputs)
                    }
                    if (_r20 != null) _index.element = _r20.nextIndex

                    // BIND b
                    b = _memo.results.peek()

                    _label = 14
                }
                // AND 14
                14 -> {
                    val _r14_2 = _memo.results.pop()
                    val _r14_1 = _memo.results.pop()

                    if (_r14_1 != null && _r14_2 != null) {
                        _memo.results.push(_Calc_Item(_start_i14, _index.element, _memo.input, (_r14_1.results + _r14_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i14
                    }

                    // ACT 13
                    val _r13 = _memo.results.peek()
                    if (_r13 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Calc_Item(_r13.startIndex, _r13.nextIndex, _memo.input, _Thunk({ arrayListOf<Int>(a?.asResult()!!, b?.asResult()!!) }, _r13), true))
                    }

                    _label = 0
                }
                // ARGS 0
                0 -> {
                    _arg_input_index.element = _arg_index.element

                    break
                }
            }
        }
    }


    open fun Number(_memo: _Calc_Memo, __index: Int, _args: _Calc_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var type: _Calc_Item? = null
        var n: _Calc_Item? = null

        // ARGS 0
        var _start_i0 = _index.element

        // AND 4
        var _start_i4 = _index.element

        // STAR 7
        var _start_i7 = _index.element
        val _inp7 = arrayListOf<Char?>()
        val _res7 = arrayListOf<Int?>()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // ARGS 0
                    _arg_index.element = 0
                    _arg_input_index.element = 0
                        // ANY
                        _ParseAnyArgs(_memo, _arg_index, _arg_input_index, _args)

                        // BIND type
                        type = _memo.argResults.peek()

                    if (_memo.argResults.pop() == null) {
                        _memo.results.push(null)
                        _label = 0
                        continue
                    }

                    // AND 4
                    _start_i4 = _index.element

                    // CALL Digits
                    val _start_i6 = _index.element
                    var _r6: _Calc_Item? = null

                    var _actual_args6: _Calc_Args = mutableListOf(type!!)
                    if (_args != null) {
                        _actual_args6 += _args.drop(_arg_index.element)
                    }
                    _r6 = _MemoCall(_memo, "Digits", _index.element, ::Digits, _actual_args6)

                    if (_r6 != null) {
                        _index.element = _r6.nextIndex
                    }

                    // BIND n
                    n = _memo.results.peek()

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
                    // CALLORVAR WS
                    var _r8: _Calc_Item? = null
                    _r8 = _MemoCall(_memo, "WS", _index.element, ::WS, null)
                    if (_r8 != null) _index.element = _r8.nextIndex

                    // STAR 7
                    val _r7 = _memo.results.pop()
                    if (_r7 != null) {
                        _res7 += _r7.results
                        _label = 7
                        continue
                    } else {
                        _memo.results.push(_Calc_Item(_start_i7, _index.element, _memo.input, _res7.filterNotNull(), true))
                    }

                    _label = 4
                }
                // AND 4
                4 -> {
                    val _r4_2 = _memo.results.pop()
                    val _r4_1 = _memo.results.pop()

                    if (_r4_1 != null && _r4_2 != null) {
                        _memo.results.push(_Calc_Item(_start_i4, _index.element, _memo.input, (_r4_1.results + _r4_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i4
                    }

                    // ACT 3
                    val _r3 = _memo.results.peek()
                    if (_r3 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Calc_Item(_r3.startIndex, _r3.nextIndex, _memo.input, _Thunk({ n?.asResult()!! }, _r3), true))
                    }

                    _label = 0
                }
                // ARGS 0
                0 -> {
                    _arg_input_index.element = _arg_index.element

                    break
                }
            }
        }
    }


    open fun Digits(_memo: _Calc_Memo, __index: Int, _args: _Calc_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var type: _Calc_Item? = null

        // ARGS 0
        var _start_i0 = _index.element

        // PLUS 4
        var _start_i4 = _index.element
        val _inp4 = arrayListOf<Char?>()
        val _res4 = arrayListOf<Int?>()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // ARGS 0
                    _arg_index.element = 0
                    _arg_input_index.element = 0
                        // ANY
                        _ParseAnyArgs(_memo, _arg_index, _arg_input_index, _args)

                        // BIND type
                        type = _memo.argResults.peek()

                    if (_memo.argResults.pop() == null) {
                        _memo.results.push(null)
                        _label = 0
                        continue
                    }

                    // PLUS 4
                    _start_i4 = _index.element
                    _label = 4
                }
                // PLUS 4
                4 -> {
                    // CALLORVAR type
                    var _r5: _Calc_Item? = null
                    if (type?.production != null) {
                        _r5 = _MemoCall(_memo, type.production!!.methodName, _index.element, type.production!!.op, if (_args != null) _args.drop(_arg_index.element) else null)
                    } else {
                        _r5 = _ParseLiteralObj(_memo, _index, type?.inputs)
                    }
                    if (_r5 != null) _index.element = _r5.nextIndex

                    // PLUS 4
                    val _r4 = _memo.results.pop()
                    if (_r4 != null) {
                        _res4 += _r4.results
                        _label = 4
                        continue
                    } else {
                        if (_index.element > _start_i4) {
                            _memo.results.push(_Calc_Item(_start_i4, _index.element, _memo.input, _res4.filterNotNull(), true))
                        } else {
                            _memo.results.push(null)
                        }
                    }

                    // ACT 3
                    val _r3 = _memo.results.peek()
                    if (_r3 != null) {
                        _memo.results.pop()
                        _memo.results.push(_Calc_Item(_r3.startIndex, _r3.nextIndex, _memo.input, _Thunk({ it!!.inputs.joinToString().toInt() }, _r3), true))
                    }

                    _label = 0
                }
                // ARGS 0
                0 -> {
                    _arg_input_index.element = _arg_index.element

                    break
                }
            }
        }
    }


    open fun DecimalDigit(_memo: _Calc_Memo, __index: Int, _args: _Calc_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // INPUT CLASS
                    _ParseInputClass(_memo, _index, listOf('\u0030', '\u0031', '\u0032', '\u0033', '\u0034', '\u0035', '\u0036', '\u0037', '\u0038', '\u0039'))

                    break
                }
            }
        }
    }


    open fun KW(_memo: _Calc_Memo, __index: Int, _args: _Calc_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var str: _Calc_Item? = null

        // ARGS 0
        var _start_i0 = _index.element

        // AND 3
        var _start_i3 = _index.element

        // STAR 5
        var _start_i5 = _index.element
        val _inp5 = arrayListOf<Char?>()
        val _res5 = arrayListOf<Int?>()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // ARGS 0
                    _arg_index.element = 0
                    _arg_input_index.element = 0
                        // ANY
                        _ParseAnyArgs(_memo, _arg_index, _arg_input_index, _args)

                        // BIND str
                        str = _memo.argResults.peek()

                    if (_memo.argResults.pop() == null) {
                        _memo.results.push(null)
                        _label = 0
                        continue
                    }

                    // AND 3
                    _start_i3 = _index.element

                    // CALLORVAR str
                    var _r4: _Calc_Item? = null
                    if (str?.production != null) {
                        _r4 = _MemoCall(_memo, str.production!!.methodName, _index.element, str.production!!.op, if (_args != null) _args.drop(_arg_index.element) else null)
                    } else {
                        _r4 = _ParseLiteralObj(_memo, _index, str?.inputs)
                    }
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
                    // CALLORVAR WS
                    var _r6: _Calc_Item? = null
                    _r6 = _MemoCall(_memo, "WS", _index.element, ::WS, null)
                    if (_r6 != null) _index.element = _r6.nextIndex

                    // STAR 5
                    val _r5 = _memo.results.pop()
                    if (_r5 != null) {
                        _res5 += _r5.results
                        _label = 5
                        continue
                    } else {
                        _memo.results.push(_Calc_Item(_start_i5, _index.element, _memo.input, _res5.filterNotNull(), true))
                    }

                    _label = 3
                }
                // AND 3
                3 -> {
                    val _r3_2 = _memo.results.pop()
                    val _r3_1 = _memo.results.pop()

                    if (_r3_1 != null && _r3_2 != null) {
                        _memo.results.push(_Calc_Item(_start_i3, _index.element, _memo.input, (_r3_1.results + _r3_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i3
                    }

                    _label = 0
                }
                // ARGS 0
                0 -> {
                    _arg_input_index.element = _arg_index.element

                    break
                }
            }
        }
    }


    open fun WS(_memo: _Calc_Memo, __index: Int, _args: _Calc_Args?) {
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

                    // LITERAL ' '
                    _ParseLiteralChar(_memo, _index, ' ')

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
                    // OR shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i1
                    } else {
                        _label = 1
                        continue
                    }

                    // LITERAL '\r'
                    _ParseLiteralChar(_memo, _index, '\r')

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

}
