//
// KOMeta GLET_Plugin Parser
//

package kometa.expression_tree

import kometa.*
import kometa.util.*
import kotlin.jvm.internal.Ref

import kometa.kotlin.*
import kometa.expressionTree.ETBuilder
import kometa.kotlin_parser.KotlinParser

typealias _GLET_Plugin_Inputs = Iterable<Token>
typealias _GLET_Plugin_Results = Iterable<AST.AstNode>
typealias _GLET_Plugin_Item = MatchItem<Token, AST.AstNode>
typealias _GLET_Plugin_Args = Iterable<_GLET_Plugin_Item>
typealias _GLET_Plugin_Memo = MatchState<Token, AST.AstNode>
typealias _GLET_Plugin_Rule = Production<Token, AST.AstNode>
typealias _GLET_Plugin_Base = Matcher<Token, AST.AstNode>

open class GLET_Plugin(handleLeftRecursion: Boolean = true) : KotlinParser(handleLeftRecursion) {
    init {
        terminals = setOf(
            "GLEXPRESSIONTREE",
        )
    }

    override fun userDefinedExpression(_memo: _GLET_Plugin_Memo, __index: Int, _args: _GLET_Plugin_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var sb: _GLET_Plugin_Item? = null

        // AND 1
        var _start_i1 = _index.element

        // AND 2
        var _start_i2 = _index.element

        // STAR 4
        var _start_i4 = _index.element
        val _inp4 = arrayListOf<Token?>()
        val _res4 = arrayListOf<AST.AstNode?>()

        // OR 6
        var _start_i6 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 1
                    _start_i1 = _index.element

                    // AND 2
                    _start_i2 = _index.element

                    // CALLORVAR GLEXPRESSIONTREE
                    var _r3: _GLET_Plugin_Item? = null
                    _r3 = _MemoCall(_memo, "GLEXPRESSIONTREE", _index.element, ::GLEXPRESSIONTREE, null)
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
                    // CALLORVAR NL
                    var _r5: _GLET_Plugin_Item? = null
                    _r5 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r5 != null) _index.element = _r5.nextIndex

                    // STAR 4
                    val _r4 = _memo.results.pop()
                    if (_r4 != null) {
                        _res4 += _r4.results
                        _label = 4
                        continue
                    } else {
                        _memo.results.push(_GLET_Plugin_Item(_start_i4, _index.element, _memo.input, _res4.filterNotNull(), true))
                    }

                    _label = 2
                }
                // AND 2
                2 -> {
                    val _r2_2 = _memo.results.pop()
                    val _r2_1 = _memo.results.pop()

                    if (_r2_1 != null && _r2_2 != null) {
                        _memo.results.push(_GLET_Plugin_Item(_start_i2, _index.element, _memo.input, (_r2_1.results + _r2_2.results).filterNotNull(), true))
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

                    // OR 6
                    _start_i6 = _index.element

                    // CALLORVAR block
                    var _r8: _GLET_Plugin_Item? = null
                    _r8 = _MemoCall(_memo, "block", _index.element, ::block, null)
                    if (_r8 != null) _index.element = _r8.nextIndex

                    // BIND sb
                    sb = _memo.results.peek()

                    // OR shortcut 6
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i6
                    } else {
                        _label = 6
                        continue
                    }

                    // FAIL 9
                    _memo.results.push(null)
                    error("failed to parse shaderBlock" + ":\n" + formatErrorString(_memo, _index.element))

                    _label = 6
                }
                // OR 6
                6 -> {
                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_GLET_Plugin_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i1
                    }

                    // ACT 0
                    val _r0 = _memo.results.peek()
                    if (_r0 != null) {
                        _memo.results.pop()
                        _memo.results.push(_GLET_Plugin_Item(_r0.startIndex, _r0.nextIndex, _memo.input, _Thunk({ ETBuilder.build(sb.r.cast()) }, _r0), true))
                    }

                    break
                }
            }
        }
    }


    open fun GLEXPRESSIONTREE(_memo: _GLET_Plugin_Memo, __index: Int, _args: _GLET_Plugin_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var t: _GLET_Plugin_Item? = null

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
                    val lambda0: (_GLET_Plugin_Item) -> Boolean = { ((t.i as? Token.Identifier)?.s == "glExpressionTree") }
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

}
