//
// KOMeta KotlinLexer Parser
//

package kometa.kotlin_lexer

import kometa.*
import kometa.util.*
import kotlin.jvm.internal.Ref

import kometa.kotlin.Token
import kometa.CharMatcher

typealias _KotlinLexer_Inputs = Iterable<Char>
typealias _KotlinLexer_Results = Iterable<Token>
typealias _KotlinLexer_Item = MatchItem<Char, Token>
typealias _KotlinLexer_Args = Iterable<_KotlinLexer_Item>
typealias _KotlinLexer_Memo = MatchState<Char, Token>
typealias _KotlinLexer_Rule = Production<Char, Token>
typealias _KotlinLexer_Base = Matcher<Char, Token>

open class KotlinLexer(handleLeftRecursion: Boolean = true) : CharMatcher<Token>(handleLeftRecursion) {
    init {
        terminals = setOf(
            "EOF",
            "NL",
            "binDigit",
            "decDigitNoZero",
            "identifierPart",
            "identifierStart",
            "multiLineStrText",
            "multiLineStringQuote",
            "strText",
        )
    }

    open fun tokens(_memo: _KotlinLexer_Memo, __index: Int, _args: _KotlinLexer_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 1
        var _start_i1 = _index.element

        // STAR 2
        var _start_i2 = _index.element
        val _inp2 = arrayListOf<Char?>()
        val _res2 = arrayListOf<Token?>()

        // AND 3
        var _start_i3 = _index.element

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
                    // AND 3
                    _start_i3 = _index.element

                    // CALLORVAR token
                    var _r4: _KotlinLexer_Item? = null
                    _r4 = _MemoCall(_memo, "token", _index.element, ::token, null)
                    if (_r4 != null) _index.element = _r4.nextIndex

                    // AND shortcut 3
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 3
                        continue
                    }

                    // CALLORVAR WS
                    var _r5: _KotlinLexer_Item? = null
                    _r5 = _MemoCall(_memo, "WS", _index.element, ::WS, null)
                    if (_r5 != null) _index.element = _r5.nextIndex

                    _label = 3
                }
                // AND 3
                3 -> {
                    val _r3_2 = _memo.results.pop()
                    val _r3_1 = _memo.results.pop()

                    if (_r3_1 != null && _r3_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i3, _index.element, _memo.input, (_r3_1.results + _r3_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i3
                    }

                    // STAR 2
                    val _r2 = _memo.results.pop()
                    if (_r2 != null) {
                        _res2 += _r2.results
                        _label = 2
                        continue
                    } else {
                        _memo.results.push(_KotlinLexer_Item(_start_i2, _index.element, _memo.input, _res2.filterNotNull(), true))
                    }

                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // CALLORVAR EOF
                    var _r6: _KotlinLexer_Item? = null
                    _r6 = _MemoCall(_memo, "EOF", _index.element, ::EOF, null)
                    if (_r6 != null) _index.element = _r6.nextIndex

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i1
                    }

                    // ACT 0
                    val _r0 = _memo.results.peek()
                    if (_r0 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r0.startIndex, _r0.nextIndex, _memo.input, _Thunk({ it.l }, _r0), true))
                    }

                    break
                }
            }
        }
    }


    open fun token(_memo: _KotlinLexer_Memo, __index: Int, _args: _KotlinLexer_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var s: _KotlinLexer_Item? = null
        var name: _KotlinLexer_Item? = null

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

        // OR 5
        var _start_i5 = _index.element

        // OR 6
        var _start_i6 = _index.element

        // OR 7
        var _start_i7 = _index.element

        // OR 8
        var _start_i8 = _index.element

        // OR 9
        var _start_i9 = _index.element

        // OR 10
        var _start_i10 = _index.element

        // OR 11
        var _start_i11 = _index.element

        // OR 12
        var _start_i12 = _index.element

        // OR 13
        var _start_i13 = _index.element

        // OR 14
        var _start_i14 = _index.element

        // OR 15
        var _start_i15 = _index.element

        // OR 16
        var _start_i16 = _index.element

        // OR 17
        var _start_i17 = _index.element

        // OR 18
        var _start_i18 = _index.element

        // OR 19
        var _start_i19 = _index.element

        // OR 20
        var _start_i20 = _index.element

        // OR 21
        var _start_i21 = _index.element

        // OR 22
        var _start_i22 = _index.element

        // OR 23
        var _start_i23 = _index.element

        // OR 24
        var _start_i24 = _index.element

        // OR 25
        var _start_i25 = _index.element

        // OR 26
        var _start_i26 = _index.element

        // OR 27
        var _start_i27 = _index.element

        // OR 28
        var _start_i28 = _index.element

        // OR 29
        var _start_i29 = _index.element

        // OR 30
        var _start_i30 = _index.element

        // OR 31
        var _start_i31 = _index.element

        // OR 32
        var _start_i32 = _index.element

        // OR 33
        var _start_i33 = _index.element

        // OR 34
        var _start_i34 = _index.element

        // OR 35
        var _start_i35 = _index.element

        // OR 36
        var _start_i36 = _index.element

        // OR 37
        var _start_i37 = _index.element

        // OR 38
        var _start_i38 = _index.element

        // OR 39
        var _start_i39 = _index.element

        // OR 40
        var _start_i40 = _index.element

        // OR 41
        var _start_i41 = _index.element

        // OR 42
        var _start_i42 = _index.element

        // OR 43
        var _start_i43 = _index.element

        // OR 44
        var _start_i44 = _index.element

        // OR 45
        var _start_i45 = _index.element

        // OR 46
        var _start_i46 = _index.element

        // OR 47
        var _start_i47 = _index.element

        // OR 48
        var _start_i48 = _index.element

        // OR 49
        var _start_i49 = _index.element

        // OR 50
        var _start_i50 = _index.element

        // OR 51
        var _start_i51 = _index.element

        // OR 52
        var _start_i52 = _index.element

        // OR 53
        var _start_i53 = _index.element

        // OR 54
        var _start_i54 = _index.element

        // OR 55
        var _start_i55 = _index.element

        // OR 56
        var _start_i56 = _index.element

        // OR 57
        var _start_i57 = _index.element

        // OR 58
        var _start_i58 = _index.element

        // OR 59
        var _start_i59 = _index.element

        // OR 60
        var _start_i60 = _index.element

        // OR 61
        var _start_i61 = _index.element

        // OR 62
        var _start_i62 = _index.element

        // OR 63
        var _start_i63 = _index.element

        // OR 64
        var _start_i64 = _index.element

        // OR 65
        var _start_i65 = _index.element

        // OR 66
        var _start_i66 = _index.element

        // OR 67
        var _start_i67 = _index.element

        // OR 68
        var _start_i68 = _index.element

        // OR 69
        var _start_i69 = _index.element

        // OR 70
        var _start_i70 = _index.element

        // OR 71
        var _start_i71 = _index.element

        // OR 72
        var _start_i72 = _index.element

        // OR 73
        var _start_i73 = _index.element

        // OR 74
        var _start_i74 = _index.element

        // OR 75
        var _start_i75 = _index.element

        // OR 76
        var _start_i76 = _index.element

        // OR 77
        var _start_i77 = _index.element

        // OR 78
        var _start_i78 = _index.element

        // OR 79
        var _start_i79 = _index.element

        // OR 80
        var _start_i80 = _index.element

        // OR 81
        var _start_i81 = _index.element

        // OR 82
        var _start_i82 = _index.element

        // OR 83
        var _start_i83 = _index.element

        // OR 84
        var _start_i84 = _index.element

        // OR 85
        var _start_i85 = _index.element

        // OR 86
        var _start_i86 = _index.element

        // OR 87
        var _start_i87 = _index.element

        // OR 88
        var _start_i88 = _index.element

        // OR 89
        var _start_i89 = _index.element

        // OR 90
        var _start_i90 = _index.element

        // OR 91
        var _start_i91 = _index.element

        // OR 92
        var _start_i92 = _index.element

        // OR 93
        var _start_i93 = _index.element

        // OR 94
        var _start_i94 = _index.element

        // OR 95
        var _start_i95 = _index.element

        // OR 96
        var _start_i96 = _index.element

        // OR 97
        var _start_i97 = _index.element

        // OR 98
        var _start_i98 = _index.element

        // OR 99
        var _start_i99 = _index.element

        // OR 100
        var _start_i100 = _index.element

        // OR 101
        var _start_i101 = _index.element

        // OR 102
        var _start_i102 = _index.element

        // OR 103
        var _start_i103 = _index.element

        // OR 104
        var _start_i104 = _index.element

        // OR 105
        var _start_i105 = _index.element

        // OR 106
        var _start_i106 = _index.element

        // OR 107
        var _start_i107 = _index.element

        // OR 108
        var _start_i108 = _index.element

        // OR 109
        var _start_i109 = _index.element

        // OR 110
        var _start_i110 = _index.element

        // OR 111
        var _start_i111 = _index.element

        // OR 112
        var _start_i112 = _index.element

        // OR 113
        var _start_i113 = _index.element

        // OR 114
        var _start_i114 = _index.element

        // OR 115
        var _start_i115 = _index.element

        // OR 116
        var _start_i116 = _index.element

        // OR 117
        var _start_i117 = _index.element

        // OR 118
        var _start_i118 = _index.element

        // OR 119
        var _start_i119 = _index.element

        // OR 120
        var _start_i120 = _index.element

        // OR 121
        var _start_i121 = _index.element

        // OR 122
        var _start_i122 = _index.element

        // OR 123
        var _start_i123 = _index.element

        // OR 124
        var _start_i124 = _index.element

        // OR 125
        var _start_i125 = _index.element

        // OR 126
        var _start_i126 = _index.element

        // OR 127
        var _start_i127 = _index.element

        // OR 128
        var _start_i128 = _index.element

        // OR 129
        var _start_i129 = _index.element

        // OR 130
        var _start_i130 = _index.element

        // OR 131
        var _start_i131 = _index.element

        // OR 132
        var _start_i132 = _index.element

        // OR 133
        var _start_i133 = _index.element

        // OR 134
        var _start_i134 = _index.element

        // OR 135
        var _start_i135 = _index.element

        // OR 136
        var _start_i136 = _index.element

        // OR 137
        var _start_i137 = _index.element

        // OR 138
        var _start_i138 = _index.element

        // AND 140
        var _start_i140 = _index.element

        // STAR 143
        var _start_i143 = _index.element
        val _inp143 = arrayListOf<Char?>()
        val _res143 = arrayListOf<Token?>()

        // AND 144
        var _start_i144 = _index.element

        // NOT 145
        var _start_i145 = _index.element

        // AND 149
        var _start_i149 = _index.element

        // NOT 151
        var _start_i151 = _index.element

        // AND 154
        var _start_i154 = _index.element

        // NOT 156
        var _start_i156 = _index.element

        // AND 159
        var _start_i159 = _index.element

        // NOT 161
        var _start_i161 = _index.element

        // AND 164
        var _start_i164 = _index.element

        // NOT 166
        var _start_i166 = _index.element

        // AND 169
        var _start_i169 = _index.element

        // NOT 171
        var _start_i171 = _index.element

        // AND 174
        var _start_i174 = _index.element

        // NOT 176
        var _start_i176 = _index.element

        // AND 281
        var _start_i281 = _index.element

        // AND 286
        var _start_i286 = _index.element

        // AND 291
        var _start_i291 = _index.element

        // AND 296
        var _start_i296 = _index.element

        // AND 301
        var _start_i301 = _index.element

        // NOT 303
        var _start_i303 = _index.element

        // AND 306
        var _start_i306 = _index.element

        // NOT 308
        var _start_i308 = _index.element

        // AND 311
        var _start_i311 = _index.element

        // NOT 313
        var _start_i313 = _index.element

        // AND 316
        var _start_i316 = _index.element

        // NOT 318
        var _start_i318 = _index.element

        // AND 321
        var _start_i321 = _index.element

        // NOT 323
        var _start_i323 = _index.element

        // AND 326
        var _start_i326 = _index.element

        // NOT 328
        var _start_i328 = _index.element

        // AND 331
        var _start_i331 = _index.element

        // NOT 333
        var _start_i333 = _index.element

        // AND 336
        var _start_i336 = _index.element

        // NOT 338
        var _start_i338 = _index.element

        // AND 341
        var _start_i341 = _index.element

        // NOT 343
        var _start_i343 = _index.element

        // AND 346
        var _start_i346 = _index.element

        // NOT 348
        var _start_i348 = _index.element

        // AND 351
        var _start_i351 = _index.element

        // NOT 353
        var _start_i353 = _index.element

        // AND 356
        var _start_i356 = _index.element

        // NOT 358
        var _start_i358 = _index.element

        // AND 361
        var _start_i361 = _index.element

        // NOT 363
        var _start_i363 = _index.element

        // AND 366
        var _start_i366 = _index.element

        // NOT 368
        var _start_i368 = _index.element

        // AND 371
        var _start_i371 = _index.element

        // NOT 373
        var _start_i373 = _index.element

        // AND 376
        var _start_i376 = _index.element

        // NOT 378
        var _start_i378 = _index.element

        // AND 381
        var _start_i381 = _index.element

        // NOT 383
        var _start_i383 = _index.element

        // AND 386
        var _start_i386 = _index.element

        // NOT 388
        var _start_i388 = _index.element

        // AND 391
        var _start_i391 = _index.element

        // NOT 393
        var _start_i393 = _index.element

        // AND 396
        var _start_i396 = _index.element

        // NOT 398
        var _start_i398 = _index.element

        // AND 401
        var _start_i401 = _index.element

        // NOT 403
        var _start_i403 = _index.element

        // AND 406
        var _start_i406 = _index.element

        // NOT 408
        var _start_i408 = _index.element

        // AND 411
        var _start_i411 = _index.element

        // NOT 413
        var _start_i413 = _index.element

        // AND 416
        var _start_i416 = _index.element

        // NOT 418
        var _start_i418 = _index.element

        // AND 421
        var _start_i421 = _index.element

        // NOT 423
        var _start_i423 = _index.element

        // AND 426
        var _start_i426 = _index.element

        // NOT 428
        var _start_i428 = _index.element

        // AND 431
        var _start_i431 = _index.element

        // NOT 433
        var _start_i433 = _index.element

        // AND 436
        var _start_i436 = _index.element

        // NOT 438
        var _start_i438 = _index.element

        // AND 441
        var _start_i441 = _index.element

        // NOT 443
        var _start_i443 = _index.element

        // AND 446
        var _start_i446 = _index.element

        // NOT 448
        var _start_i448 = _index.element

        // AND 451
        var _start_i451 = _index.element

        // NOT 453
        var _start_i453 = _index.element

        // AND 456
        var _start_i456 = _index.element

        // NOT 458
        var _start_i458 = _index.element

        // AND 461
        var _start_i461 = _index.element

        // NOT 463
        var _start_i463 = _index.element

        // AND 466
        var _start_i466 = _index.element

        // NOT 468
        var _start_i468 = _index.element

        // AND 471
        var _start_i471 = _index.element

        // NOT 473
        var _start_i473 = _index.element

        // AND 476
        var _start_i476 = _index.element

        // NOT 478
        var _start_i478 = _index.element

        // AND 481
        var _start_i481 = _index.element

        // NOT 483
        var _start_i483 = _index.element

        // AND 486
        var _start_i486 = _index.element

        // NOT 488
        var _start_i488 = _index.element

        // AND 491
        var _start_i491 = _index.element

        // NOT 493
        var _start_i493 = _index.element

        // AND 496
        var _start_i496 = _index.element

        // NOT 498
        var _start_i498 = _index.element

        // AND 501
        var _start_i501 = _index.element

        // NOT 503
        var _start_i503 = _index.element

        // AND 506
        var _start_i506 = _index.element

        // NOT 508
        var _start_i508 = _index.element

        // AND 511
        var _start_i511 = _index.element

        // NOT 513
        var _start_i513 = _index.element

        // AND 516
        var _start_i516 = _index.element

        // NOT 518
        var _start_i518 = _index.element

        // AND 521
        var _start_i521 = _index.element

        // NOT 523
        var _start_i523 = _index.element

        // AND 526
        var _start_i526 = _index.element

        // NOT 528
        var _start_i528 = _index.element

        // AND 531
        var _start_i531 = _index.element

        // NOT 533
        var _start_i533 = _index.element

        // AND 536
        var _start_i536 = _index.element

        // NOT 538
        var _start_i538 = _index.element

        // AND 541
        var _start_i541 = _index.element

        // NOT 543
        var _start_i543 = _index.element

        // AND 546
        var _start_i546 = _index.element

        // NOT 548
        var _start_i548 = _index.element

        // AND 551
        var _start_i551 = _index.element

        // NOT 553
        var _start_i553 = _index.element

        // AND 556
        var _start_i556 = _index.element

        // NOT 558
        var _start_i558 = _index.element

        // AND 561
        var _start_i561 = _index.element

        // NOT 563
        var _start_i563 = _index.element

        // AND 566
        var _start_i566 = _index.element

        // NOT 568
        var _start_i568 = _index.element

        // AND 571
        var _start_i571 = _index.element

        // NOT 573
        var _start_i573 = _index.element

        // AND 576
        var _start_i576 = _index.element

        // NOT 578
        var _start_i578 = _index.element

        // AND 581
        var _start_i581 = _index.element

        // NOT 583
        var _start_i583 = _index.element

        // AND 586
        var _start_i586 = _index.element

        // NOT 588
        var _start_i588 = _index.element

        // AND 591
        var _start_i591 = _index.element

        // NOT 593
        var _start_i593 = _index.element

        // AND 596
        var _start_i596 = _index.element

        // NOT 598
        var _start_i598 = _index.element

        // AND 601
        var _start_i601 = _index.element

        // NOT 603
        var _start_i603 = _index.element

        // AND 606
        var _start_i606 = _index.element

        // NOT 608
        var _start_i608 = _index.element

        // AND 611
        var _start_i611 = _index.element

        // NOT 613
        var _start_i613 = _index.element

        // AND 616
        var _start_i616 = _index.element

        // NOT 618
        var _start_i618 = _index.element

        // AND 621
        var _start_i621 = _index.element

        // NOT 623
        var _start_i623 = _index.element

        // AND 626
        var _start_i626 = _index.element

        // NOT 628
        var _start_i628 = _index.element

        // AND 631
        var _start_i631 = _index.element

        // NOT 633
        var _start_i633 = _index.element

        // AND 636
        var _start_i636 = _index.element

        // NOT 638
        var _start_i638 = _index.element

        // AND 641
        var _start_i641 = _index.element

        // NOT 643
        var _start_i643 = _index.element

        // AND 646
        var _start_i646 = _index.element

        // NOT 648
        var _start_i648 = _index.element

        // AND 651
        var _start_i651 = _index.element

        // NOT 653
        var _start_i653 = _index.element

        // AND 656
        var _start_i656 = _index.element

        // NOT 658
        var _start_i658 = _index.element

        // AND 661
        var _start_i661 = _index.element

        // NOT 663
        var _start_i663 = _index.element

        // AND 666
        var _start_i666 = _index.element

        // NOT 668
        var _start_i668 = _index.element

        // AND 671
        var _start_i671 = _index.element

        // NOT 673
        var _start_i673 = _index.element

        // AND 676
        var _start_i676 = _index.element

        // NOT 678
        var _start_i678 = _index.element

        // AND 681
        var _start_i681 = _index.element

        // NOT 683
        var _start_i683 = _index.element

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

                    // OR 5
                    _start_i5 = _index.element

                    // OR 6
                    _start_i6 = _index.element

                    // OR 7
                    _start_i7 = _index.element

                    // OR 8
                    _start_i8 = _index.element

                    // OR 9
                    _start_i9 = _index.element

                    // OR 10
                    _start_i10 = _index.element

                    // OR 11
                    _start_i11 = _index.element

                    // OR 12
                    _start_i12 = _index.element

                    // OR 13
                    _start_i13 = _index.element

                    // OR 14
                    _start_i14 = _index.element

                    // OR 15
                    _start_i15 = _index.element

                    // OR 16
                    _start_i16 = _index.element

                    // OR 17
                    _start_i17 = _index.element

                    // OR 18
                    _start_i18 = _index.element

                    // OR 19
                    _start_i19 = _index.element

                    // OR 20
                    _start_i20 = _index.element

                    // OR 21
                    _start_i21 = _index.element

                    // OR 22
                    _start_i22 = _index.element

                    // OR 23
                    _start_i23 = _index.element

                    // OR 24
                    _start_i24 = _index.element

                    // OR 25
                    _start_i25 = _index.element

                    // OR 26
                    _start_i26 = _index.element

                    // OR 27
                    _start_i27 = _index.element

                    // OR 28
                    _start_i28 = _index.element

                    // OR 29
                    _start_i29 = _index.element

                    // OR 30
                    _start_i30 = _index.element

                    // OR 31
                    _start_i31 = _index.element

                    // OR 32
                    _start_i32 = _index.element

                    // OR 33
                    _start_i33 = _index.element

                    // OR 34
                    _start_i34 = _index.element

                    // OR 35
                    _start_i35 = _index.element

                    // OR 36
                    _start_i36 = _index.element

                    // OR 37
                    _start_i37 = _index.element

                    // OR 38
                    _start_i38 = _index.element

                    // OR 39
                    _start_i39 = _index.element

                    // OR 40
                    _start_i40 = _index.element

                    // OR 41
                    _start_i41 = _index.element

                    // OR 42
                    _start_i42 = _index.element

                    // OR 43
                    _start_i43 = _index.element

                    // OR 44
                    _start_i44 = _index.element

                    // OR 45
                    _start_i45 = _index.element

                    // OR 46
                    _start_i46 = _index.element

                    // OR 47
                    _start_i47 = _index.element

                    // OR 48
                    _start_i48 = _index.element

                    // OR 49
                    _start_i49 = _index.element

                    // OR 50
                    _start_i50 = _index.element

                    // OR 51
                    _start_i51 = _index.element

                    // OR 52
                    _start_i52 = _index.element

                    // OR 53
                    _start_i53 = _index.element

                    // OR 54
                    _start_i54 = _index.element

                    // OR 55
                    _start_i55 = _index.element

                    // OR 56
                    _start_i56 = _index.element

                    // OR 57
                    _start_i57 = _index.element

                    // OR 58
                    _start_i58 = _index.element

                    // OR 59
                    _start_i59 = _index.element

                    // OR 60
                    _start_i60 = _index.element

                    // OR 61
                    _start_i61 = _index.element

                    // OR 62
                    _start_i62 = _index.element

                    // OR 63
                    _start_i63 = _index.element

                    // OR 64
                    _start_i64 = _index.element

                    // OR 65
                    _start_i65 = _index.element

                    // OR 66
                    _start_i66 = _index.element

                    // OR 67
                    _start_i67 = _index.element

                    // OR 68
                    _start_i68 = _index.element

                    // OR 69
                    _start_i69 = _index.element

                    // OR 70
                    _start_i70 = _index.element

                    // OR 71
                    _start_i71 = _index.element

                    // OR 72
                    _start_i72 = _index.element

                    // OR 73
                    _start_i73 = _index.element

                    // OR 74
                    _start_i74 = _index.element

                    // OR 75
                    _start_i75 = _index.element

                    // OR 76
                    _start_i76 = _index.element

                    // OR 77
                    _start_i77 = _index.element

                    // OR 78
                    _start_i78 = _index.element

                    // OR 79
                    _start_i79 = _index.element

                    // OR 80
                    _start_i80 = _index.element

                    // OR 81
                    _start_i81 = _index.element

                    // OR 82
                    _start_i82 = _index.element

                    // OR 83
                    _start_i83 = _index.element

                    // OR 84
                    _start_i84 = _index.element

                    // OR 85
                    _start_i85 = _index.element

                    // OR 86
                    _start_i86 = _index.element

                    // OR 87
                    _start_i87 = _index.element

                    // OR 88
                    _start_i88 = _index.element

                    // OR 89
                    _start_i89 = _index.element

                    // OR 90
                    _start_i90 = _index.element

                    // OR 91
                    _start_i91 = _index.element

                    // OR 92
                    _start_i92 = _index.element

                    // OR 93
                    _start_i93 = _index.element

                    // OR 94
                    _start_i94 = _index.element

                    // OR 95
                    _start_i95 = _index.element

                    // OR 96
                    _start_i96 = _index.element

                    // OR 97
                    _start_i97 = _index.element

                    // OR 98
                    _start_i98 = _index.element

                    // OR 99
                    _start_i99 = _index.element

                    // OR 100
                    _start_i100 = _index.element

                    // OR 101
                    _start_i101 = _index.element

                    // OR 102
                    _start_i102 = _index.element

                    // OR 103
                    _start_i103 = _index.element

                    // OR 104
                    _start_i104 = _index.element

                    // OR 105
                    _start_i105 = _index.element

                    // OR 106
                    _start_i106 = _index.element

                    // OR 107
                    _start_i107 = _index.element

                    // OR 108
                    _start_i108 = _index.element

                    // OR 109
                    _start_i109 = _index.element

                    // OR 110
                    _start_i110 = _index.element

                    // OR 111
                    _start_i111 = _index.element

                    // OR 112
                    _start_i112 = _index.element

                    // OR 113
                    _start_i113 = _index.element

                    // OR 114
                    _start_i114 = _index.element

                    // OR 115
                    _start_i115 = _index.element

                    // OR 116
                    _start_i116 = _index.element

                    // OR 117
                    _start_i117 = _index.element

                    // OR 118
                    _start_i118 = _index.element

                    // OR 119
                    _start_i119 = _index.element

                    // OR 120
                    _start_i120 = _index.element

                    // OR 121
                    _start_i121 = _index.element

                    // OR 122
                    _start_i122 = _index.element

                    // OR 123
                    _start_i123 = _index.element

                    // OR 124
                    _start_i124 = _index.element

                    // OR 125
                    _start_i125 = _index.element

                    // OR 126
                    _start_i126 = _index.element

                    // OR 127
                    _start_i127 = _index.element

                    // OR 128
                    _start_i128 = _index.element

                    // OR 129
                    _start_i129 = _index.element

                    // OR 130
                    _start_i130 = _index.element

                    // OR 131
                    _start_i131 = _index.element

                    // OR 132
                    _start_i132 = _index.element

                    // OR 133
                    _start_i133 = _index.element

                    // OR 134
                    _start_i134 = _index.element

                    // OR 135
                    _start_i135 = _index.element

                    // OR 136
                    _start_i136 = _index.element

                    // OR 137
                    _start_i137 = _index.element

                    // OR 138
                    _start_i138 = _index.element

                    // AND 140
                    _start_i140 = _index.element

                    // LITERAL "#!"
                    _ParseLiteralString(_memo, _index, "#!")

                    // AND shortcut 140
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 140
                        continue
                    }

                    // STAR 143
                    _start_i143 = _index.element
                    _label = 143
                }
                // STAR 143
                143 -> {
                    // AND 144
                    _start_i144 = _index.element

                    // NOT 145
                    _start_i145 = _index.element

                    // CALLORVAR NL
                    var _r146: _KotlinLexer_Item? = null
                    _r146 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r146 != null) _index.element = _r146.nextIndex

                    // NOT 145
                    val _r145 = _memo.results.pop()
                    _memo.results.push(if (_r145 == null) _KotlinLexer_Item(_start_i145, _memo.input) else null)
                    _index.element = _start_i145
                    // AND shortcut 144
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 144
                        continue
                    }

                    // ANY
                    _ParseAny(_memo, _index)

                    _label = 144
                }
                // AND 144
                144 -> {
                    val _r144_2 = _memo.results.pop()
                    val _r144_1 = _memo.results.pop()

                    if (_r144_1 != null && _r144_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i144, _index.element, _memo.input, (_r144_1.results + _r144_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i144
                    }

                    // STAR 143
                    val _r143 = _memo.results.pop()
                    if (_r143 != null) {
                        _res143 += _r143.results
                        _label = 143
                        continue
                    } else {
                        _memo.results.push(_KotlinLexer_Item(_start_i143, _index.element, _memo.input, _res143.filterNotNull(), true))
                    }

                    // BIND s
                    s = _memo.results.peek()

                    _label = 140
                }
                // AND 140
                140 -> {
                    val _r140_2 = _memo.results.pop()
                    val _r140_1 = _memo.results.pop()

                    if (_r140_1 != null && _r140_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i140, _index.element, _memo.input, (_r140_1.results + _r140_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i140
                    }

                    // ACT 139
                    val _r139 = _memo.results.peek()
                    if (_r139 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r139.startIndex, _r139.nextIndex, _memo.input, _Thunk({ Token.ShebangLine(s.s) }, _r139), true))
                    }

                    // OR shortcut 138
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i138
                    } else {
                        _label = 138
                        continue
                    }

                    // AND 149
                    _start_i149 = _index.element

                    // CALLORVAR floatLiteral
                    var _r150: _KotlinLexer_Item? = null
                    _r150 = _MemoCall(_memo, "floatLiteral", _index.element, ::floatLiteral, null)
                    if (_r150 != null) _index.element = _r150.nextIndex

                    // AND shortcut 149
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 149
                        continue
                    }

                    // NOT 151
                    _start_i151 = _index.element

                    // CALLORVAR identifierPart
                    var _r152: _KotlinLexer_Item? = null
                    _r152 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r152 != null) _index.element = _r152.nextIndex

                    // NOT 151
                    val _r151 = _memo.results.pop()
                    _memo.results.push(if (_r151 == null) _KotlinLexer_Item(_start_i151, _memo.input) else null)
                    _index.element = _start_i151
                    _label = 149
                }
                // AND 149
                149 -> {
                    val _r149_2 = _memo.results.pop()
                    val _r149_1 = _memo.results.pop()

                    if (_r149_1 != null && _r149_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i149, _index.element, _memo.input, (_r149_1.results + _r149_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i149
                    }

                    // ACT 148
                    val _r148 = _memo.results.peek()
                    if (_r148 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r148.startIndex, _r148.nextIndex, _memo.input, _Thunk({ Token.FloatLiteral(it.s) }, _r148), true))
                    }

                    _label = 138
                }
                // OR 138
                138 -> {
                    // OR shortcut 137
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i137
                    } else {
                        _label = 137
                        continue
                    }

                    // AND 154
                    _start_i154 = _index.element

                    // CALLORVAR doubleLiteral
                    var _r155: _KotlinLexer_Item? = null
                    _r155 = _MemoCall(_memo, "doubleLiteral", _index.element, ::doubleLiteral, null)
                    if (_r155 != null) _index.element = _r155.nextIndex

                    // AND shortcut 154
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 154
                        continue
                    }

                    // NOT 156
                    _start_i156 = _index.element

                    // CALLORVAR identifierPart
                    var _r157: _KotlinLexer_Item? = null
                    _r157 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r157 != null) _index.element = _r157.nextIndex

                    // NOT 156
                    val _r156 = _memo.results.pop()
                    _memo.results.push(if (_r156 == null) _KotlinLexer_Item(_start_i156, _memo.input) else null)
                    _index.element = _start_i156
                    _label = 154
                }
                // AND 154
                154 -> {
                    val _r154_2 = _memo.results.pop()
                    val _r154_1 = _memo.results.pop()

                    if (_r154_1 != null && _r154_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i154, _index.element, _memo.input, (_r154_1.results + _r154_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i154
                    }

                    // ACT 153
                    val _r153 = _memo.results.peek()
                    if (_r153 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r153.startIndex, _r153.nextIndex, _memo.input, _Thunk({ Token.DoubleLiteral(it.s) }, _r153), true))
                    }

                    _label = 137
                }
                // OR 137
                137 -> {
                    // OR shortcut 136
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i136
                    } else {
                        _label = 136
                        continue
                    }

                    // AND 159
                    _start_i159 = _index.element

                    // CALLORVAR longLiteral
                    var _r160: _KotlinLexer_Item? = null
                    _r160 = _MemoCall(_memo, "longLiteral", _index.element, ::longLiteral, null)
                    if (_r160 != null) _index.element = _r160.nextIndex

                    // AND shortcut 159
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 159
                        continue
                    }

                    // NOT 161
                    _start_i161 = _index.element

                    // CALLORVAR identifierPart
                    var _r162: _KotlinLexer_Item? = null
                    _r162 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r162 != null) _index.element = _r162.nextIndex

                    // NOT 161
                    val _r161 = _memo.results.pop()
                    _memo.results.push(if (_r161 == null) _KotlinLexer_Item(_start_i161, _memo.input) else null)
                    _index.element = _start_i161
                    _label = 159
                }
                // AND 159
                159 -> {
                    val _r159_2 = _memo.results.pop()
                    val _r159_1 = _memo.results.pop()

                    if (_r159_1 != null && _r159_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i159, _index.element, _memo.input, (_r159_1.results + _r159_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i159
                    }

                    // ACT 158
                    val _r158 = _memo.results.peek()
                    if (_r158 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r158.startIndex, _r158.nextIndex, _memo.input, _Thunk({ Token.LongLiteral(it.s) }, _r158), true))
                    }

                    _label = 136
                }
                // OR 136
                136 -> {
                    // OR shortcut 135
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i135
                    } else {
                        _label = 135
                        continue
                    }

                    // AND 164
                    _start_i164 = _index.element

                    // CALLORVAR unsignedLiteral
                    var _r165: _KotlinLexer_Item? = null
                    _r165 = _MemoCall(_memo, "unsignedLiteral", _index.element, ::unsignedLiteral, null)
                    if (_r165 != null) _index.element = _r165.nextIndex

                    // AND shortcut 164
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 164
                        continue
                    }

                    // NOT 166
                    _start_i166 = _index.element

                    // CALLORVAR identifierPart
                    var _r167: _KotlinLexer_Item? = null
                    _r167 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r167 != null) _index.element = _r167.nextIndex

                    // NOT 166
                    val _r166 = _memo.results.pop()
                    _memo.results.push(if (_r166 == null) _KotlinLexer_Item(_start_i166, _memo.input) else null)
                    _index.element = _start_i166
                    _label = 164
                }
                // AND 164
                164 -> {
                    val _r164_2 = _memo.results.pop()
                    val _r164_1 = _memo.results.pop()

                    if (_r164_1 != null && _r164_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i164, _index.element, _memo.input, (_r164_1.results + _r164_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i164
                    }

                    // ACT 163
                    val _r163 = _memo.results.peek()
                    if (_r163 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r163.startIndex, _r163.nextIndex, _memo.input, _Thunk({ Token.UnsignedLiteral(it.s) }, _r163), true))
                    }

                    _label = 135
                }
                // OR 135
                135 -> {
                    // OR shortcut 134
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i134
                    } else {
                        _label = 134
                        continue
                    }

                    // AND 169
                    _start_i169 = _index.element

                    // CALLORVAR unsignedLongLiteral
                    var _r170: _KotlinLexer_Item? = null
                    _r170 = _MemoCall(_memo, "unsignedLongLiteral", _index.element, ::unsignedLongLiteral, null)
                    if (_r170 != null) _index.element = _r170.nextIndex

                    // AND shortcut 169
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 169
                        continue
                    }

                    // NOT 171
                    _start_i171 = _index.element

                    // CALLORVAR identifierPart
                    var _r172: _KotlinLexer_Item? = null
                    _r172 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r172 != null) _index.element = _r172.nextIndex

                    // NOT 171
                    val _r171 = _memo.results.pop()
                    _memo.results.push(if (_r171 == null) _KotlinLexer_Item(_start_i171, _memo.input) else null)
                    _index.element = _start_i171
                    _label = 169
                }
                // AND 169
                169 -> {
                    val _r169_2 = _memo.results.pop()
                    val _r169_1 = _memo.results.pop()

                    if (_r169_1 != null && _r169_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i169, _index.element, _memo.input, (_r169_1.results + _r169_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i169
                    }

                    // ACT 168
                    val _r168 = _memo.results.peek()
                    if (_r168 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r168.startIndex, _r168.nextIndex, _memo.input, _Thunk({ Token.UnsignedLongLiteral(it.s) }, _r168), true))
                    }

                    _label = 134
                }
                // OR 134
                134 -> {
                    // OR shortcut 133
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i133
                    } else {
                        _label = 133
                        continue
                    }

                    // AND 174
                    _start_i174 = _index.element

                    // CALLORVAR integerLiteral
                    var _r175: _KotlinLexer_Item? = null
                    _r175 = _MemoCall(_memo, "integerLiteral", _index.element, ::integerLiteral, null)
                    if (_r175 != null) _index.element = _r175.nextIndex

                    // AND shortcut 174
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 174
                        continue
                    }

                    // NOT 176
                    _start_i176 = _index.element

                    // CALLORVAR identifierPart
                    var _r177: _KotlinLexer_Item? = null
                    _r177 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r177 != null) _index.element = _r177.nextIndex

                    // NOT 176
                    val _r176 = _memo.results.pop()
                    _memo.results.push(if (_r176 == null) _KotlinLexer_Item(_start_i176, _memo.input) else null)
                    _index.element = _start_i176
                    _label = 174
                }
                // AND 174
                174 -> {
                    val _r174_2 = _memo.results.pop()
                    val _r174_1 = _memo.results.pop()

                    if (_r174_1 != null && _r174_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i174, _index.element, _memo.input, (_r174_1.results + _r174_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i174
                    }

                    // ACT 173
                    val _r173 = _memo.results.peek()
                    if (_r173 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r173.startIndex, _r173.nextIndex, _memo.input, _Thunk({ Token.IntegerLiteral(it.s) }, _r173), true))
                    }

                    _label = 133
                }
                // OR 133
                133 -> {
                    // OR shortcut 132
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i132
                    } else {
                        _label = 132
                        continue
                    }

                    // CALLORVAR characterLiteral
                    var _r179: _KotlinLexer_Item? = null
                    _r179 = _MemoCall(_memo, "characterLiteral", _index.element, ::characterLiteral, null)
                    if (_r179 != null) _index.element = _r179.nextIndex

                    // ACT 178
                    val _r178 = _memo.results.peek()
                    if (_r178 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r178.startIndex, _r178.nextIndex, _memo.input, _Thunk({ Token.CharacterLiteral(it.s) }, _r178), true))
                    }

                    _label = 132
                }
                // OR 132
                132 -> {
                    // OR shortcut 131
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i131
                    } else {
                        _label = 131
                        continue
                    }

                    // CALLORVAR multiLineStringLiteral
                    var _r181: _KotlinLexer_Item? = null
                    _r181 = _MemoCall(_memo, "multiLineStringLiteral", _index.element, ::multiLineStringLiteral, null)
                    if (_r181 != null) _index.element = _r181.nextIndex

                    // ACT 180
                    val _r180 = _memo.results.peek()
                    if (_r180 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r180.startIndex, _r180.nextIndex, _memo.input, _Thunk({ Token.StringLiteral(it.s) }, _r180), true))
                    }

                    _label = 131
                }
                // OR 131
                131 -> {
                    // OR shortcut 130
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i130
                    } else {
                        _label = 130
                        continue
                    }

                    // CALLORVAR stringLiteral
                    var _r183: _KotlinLexer_Item? = null
                    _r183 = _MemoCall(_memo, "stringLiteral", _index.element, ::stringLiteral, null)
                    if (_r183 != null) _index.element = _r183.nextIndex

                    // ACT 182
                    val _r182 = _memo.results.peek()
                    if (_r182 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r182.startIndex, _r182.nextIndex, _memo.input, _Thunk({ Token.StringLiteral(it.s) }, _r182), true))
                    }

                    _label = 130
                }
                // OR 130
                130 -> {
                    // OR shortcut 129
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i129
                    } else {
                        _label = 129
                        continue
                    }

                    // CALLORVAR NL
                    var _r185: _KotlinLexer_Item? = null
                    _r185 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r185 != null) _index.element = _r185.nextIndex

                    // ACT 184
                    val _r184 = _memo.results.peek()
                    if (_r184 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r184.startIndex, _r184.nextIndex, _memo.input, _Thunk({ Token.NL }, _r184), true))
                    }

                    _label = 129
                }
                // OR 129
                129 -> {
                    // OR shortcut 128
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i128
                    } else {
                        _label = 128
                        continue
                    }

                    // LITERAL "..."
                    _ParseLiteralString(_memo, _index, "...")

                    // ACT 186
                    val _r186 = _memo.results.peek()
                    if (_r186 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r186.startIndex, _r186.nextIndex, _memo.input, _Thunk({ Token.RESERVED }, _r186), true))
                    }

                    _label = 128
                }
                // OR 128
                128 -> {
                    // OR shortcut 127
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i127
                    } else {
                        _label = 127
                        continue
                    }

                    // LITERAL ".."
                    _ParseLiteralString(_memo, _index, "..")

                    // ACT 188
                    val _r188 = _memo.results.peek()
                    if (_r188 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r188.startIndex, _r188.nextIndex, _memo.input, _Thunk({ Token.RANGE }, _r188), true))
                    }

                    _label = 127
                }
                // OR 127
                127 -> {
                    // OR shortcut 126
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i126
                    } else {
                        _label = 126
                        continue
                    }

                    // LITERAL "."
                    _ParseLiteralString(_memo, _index, ".")

                    // ACT 190
                    val _r190 = _memo.results.peek()
                    if (_r190 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r190.startIndex, _r190.nextIndex, _memo.input, _Thunk({ Token.DOT }, _r190), true))
                    }

                    _label = 126
                }
                // OR 126
                126 -> {
                    // OR shortcut 125
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i125
                    } else {
                        _label = 125
                        continue
                    }

                    // LITERAL ","
                    _ParseLiteralString(_memo, _index, ",")

                    // ACT 192
                    val _r192 = _memo.results.peek()
                    if (_r192 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r192.startIndex, _r192.nextIndex, _memo.input, _Thunk({ Token.COMMA }, _r192), true))
                    }

                    _label = 125
                }
                // OR 125
                125 -> {
                    // OR shortcut 124
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i124
                    } else {
                        _label = 124
                        continue
                    }

                    // LITERAL "("
                    _ParseLiteralString(_memo, _index, "(")

                    // ACT 194
                    val _r194 = _memo.results.peek()
                    if (_r194 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r194.startIndex, _r194.nextIndex, _memo.input, _Thunk({ Token.LPAREN }, _r194), true))
                    }

                    _label = 124
                }
                // OR 124
                124 -> {
                    // OR shortcut 123
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i123
                    } else {
                        _label = 123
                        continue
                    }

                    // LITERAL ")"
                    _ParseLiteralString(_memo, _index, ")")

                    // ACT 196
                    val _r196 = _memo.results.peek()
                    if (_r196 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r196.startIndex, _r196.nextIndex, _memo.input, _Thunk({ Token.RPAREN }, _r196), true))
                    }

                    _label = 123
                }
                // OR 123
                123 -> {
                    // OR shortcut 122
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i122
                    } else {
                        _label = 122
                        continue
                    }

                    // LITERAL "["
                    _ParseLiteralString(_memo, _index, "[")

                    // ACT 198
                    val _r198 = _memo.results.peek()
                    if (_r198 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r198.startIndex, _r198.nextIndex, _memo.input, _Thunk({ Token.LSQUARE }, _r198), true))
                    }

                    _label = 122
                }
                // OR 122
                122 -> {
                    // OR shortcut 121
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i121
                    } else {
                        _label = 121
                        continue
                    }

                    // LITERAL "]"
                    _ParseLiteralString(_memo, _index, "]")

                    // ACT 200
                    val _r200 = _memo.results.peek()
                    if (_r200 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r200.startIndex, _r200.nextIndex, _memo.input, _Thunk({ Token.RSQUARE }, _r200), true))
                    }

                    _label = 121
                }
                // OR 121
                121 -> {
                    // OR shortcut 120
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i120
                    } else {
                        _label = 120
                        continue
                    }

                    // LITERAL "{"
                    _ParseLiteralString(_memo, _index, "{")

                    // ACT 202
                    val _r202 = _memo.results.peek()
                    if (_r202 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r202.startIndex, _r202.nextIndex, _memo.input, _Thunk({ Token.LCURL }, _r202), true))
                    }

                    _label = 120
                }
                // OR 120
                120 -> {
                    // OR shortcut 119
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i119
                    } else {
                        _label = 119
                        continue
                    }

                    // LITERAL "}"
                    _ParseLiteralString(_memo, _index, "}")

                    // ACT 204
                    val _r204 = _memo.results.peek()
                    if (_r204 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r204.startIndex, _r204.nextIndex, _memo.input, _Thunk({ Token.RCURL }, _r204), true))
                    }

                    _label = 119
                }
                // OR 119
                119 -> {
                    // OR shortcut 118
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i118
                    } else {
                        _label = 118
                        continue
                    }

                    // LITERAL "++"
                    _ParseLiteralString(_memo, _index, "++")

                    // ACT 206
                    val _r206 = _memo.results.peek()
                    if (_r206 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r206.startIndex, _r206.nextIndex, _memo.input, _Thunk({ Token.INCR }, _r206), true))
                    }

                    _label = 118
                }
                // OR 118
                118 -> {
                    // OR shortcut 117
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i117
                    } else {
                        _label = 117
                        continue
                    }

                    // LITERAL "+="
                    _ParseLiteralString(_memo, _index, "+=")

                    // ACT 208
                    val _r208 = _memo.results.peek()
                    if (_r208 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r208.startIndex, _r208.nextIndex, _memo.input, _Thunk({ Token.ADD_ASSIGNMENT }, _r208), true))
                    }

                    _label = 117
                }
                // OR 117
                117 -> {
                    // OR shortcut 116
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i116
                    } else {
                        _label = 116
                        continue
                    }

                    // LITERAL "+"
                    _ParseLiteralString(_memo, _index, "+")

                    // ACT 210
                    val _r210 = _memo.results.peek()
                    if (_r210 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r210.startIndex, _r210.nextIndex, _memo.input, _Thunk({ Token.ADD }, _r210), true))
                    }

                    _label = 116
                }
                // OR 116
                116 -> {
                    // OR shortcut 115
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i115
                    } else {
                        _label = 115
                        continue
                    }

                    // LITERAL "--"
                    _ParseLiteralString(_memo, _index, "--")

                    // ACT 212
                    val _r212 = _memo.results.peek()
                    if (_r212 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r212.startIndex, _r212.nextIndex, _memo.input, _Thunk({ Token.DECR }, _r212), true))
                    }

                    _label = 115
                }
                // OR 115
                115 -> {
                    // OR shortcut 114
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i114
                    } else {
                        _label = 114
                        continue
                    }

                    // LITERAL "->"
                    _ParseLiteralString(_memo, _index, "->")

                    // ACT 214
                    val _r214 = _memo.results.peek()
                    if (_r214 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r214.startIndex, _r214.nextIndex, _memo.input, _Thunk({ Token.ARROW }, _r214), true))
                    }

                    _label = 114
                }
                // OR 114
                114 -> {
                    // OR shortcut 113
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i113
                    } else {
                        _label = 113
                        continue
                    }

                    // LITERAL "-="
                    _ParseLiteralString(_memo, _index, "-=")

                    // ACT 216
                    val _r216 = _memo.results.peek()
                    if (_r216 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r216.startIndex, _r216.nextIndex, _memo.input, _Thunk({ Token.SUB_ASSIGNMENT }, _r216), true))
                    }

                    _label = 113
                }
                // OR 113
                113 -> {
                    // OR shortcut 112
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i112
                    } else {
                        _label = 112
                        continue
                    }

                    // LITERAL "-"
                    _ParseLiteralString(_memo, _index, "-")

                    // ACT 218
                    val _r218 = _memo.results.peek()
                    if (_r218 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r218.startIndex, _r218.nextIndex, _memo.input, _Thunk({ Token.SUB }, _r218), true))
                    }

                    _label = 112
                }
                // OR 112
                112 -> {
                    // OR shortcut 111
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i111
                    } else {
                        _label = 111
                        continue
                    }

                    // LITERAL "*="
                    _ParseLiteralString(_memo, _index, "*=")

                    // ACT 220
                    val _r220 = _memo.results.peek()
                    if (_r220 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r220.startIndex, _r220.nextIndex, _memo.input, _Thunk({ Token.MULT_ASSIGNMENT }, _r220), true))
                    }

                    _label = 111
                }
                // OR 111
                111 -> {
                    // OR shortcut 110
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i110
                    } else {
                        _label = 110
                        continue
                    }

                    // LITERAL "*"
                    _ParseLiteralString(_memo, _index, "*")

                    // ACT 222
                    val _r222 = _memo.results.peek()
                    if (_r222 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r222.startIndex, _r222.nextIndex, _memo.input, _Thunk({ Token.MULT }, _r222), true))
                    }

                    _label = 110
                }
                // OR 110
                110 -> {
                    // OR shortcut 109
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i109
                    } else {
                        _label = 109
                        continue
                    }

                    // LITERAL "/="
                    _ParseLiteralString(_memo, _index, "/=")

                    // ACT 224
                    val _r224 = _memo.results.peek()
                    if (_r224 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r224.startIndex, _r224.nextIndex, _memo.input, _Thunk({ Token.DIV_ASSIGNMENT }, _r224), true))
                    }

                    _label = 109
                }
                // OR 109
                109 -> {
                    // OR shortcut 108
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i108
                    } else {
                        _label = 108
                        continue
                    }

                    // LITERAL "/"
                    _ParseLiteralString(_memo, _index, "/")

                    // ACT 226
                    val _r226 = _memo.results.peek()
                    if (_r226 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r226.startIndex, _r226.nextIndex, _memo.input, _Thunk({ Token.DIV }, _r226), true))
                    }

                    _label = 108
                }
                // OR 108
                108 -> {
                    // OR shortcut 107
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i107
                    } else {
                        _label = 107
                        continue
                    }

                    // LITERAL "%="
                    _ParseLiteralString(_memo, _index, "%=")

                    // ACT 228
                    val _r228 = _memo.results.peek()
                    if (_r228 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r228.startIndex, _r228.nextIndex, _memo.input, _Thunk({ Token.MOD_ASSIGNMENT }, _r228), true))
                    }

                    _label = 107
                }
                // OR 107
                107 -> {
                    // OR shortcut 106
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i106
                    } else {
                        _label = 106
                        continue
                    }

                    // LITERAL "%"
                    _ParseLiteralString(_memo, _index, "%")

                    // ACT 230
                    val _r230 = _memo.results.peek()
                    if (_r230 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r230.startIndex, _r230.nextIndex, _memo.input, _Thunk({ Token.MOD }, _r230), true))
                    }

                    _label = 106
                }
                // OR 106
                106 -> {
                    // OR shortcut 105
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i105
                    } else {
                        _label = 105
                        continue
                    }

                    // LITERAL "&&"
                    _ParseLiteralString(_memo, _index, "&&")

                    // ACT 232
                    val _r232 = _memo.results.peek()
                    if (_r232 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r232.startIndex, _r232.nextIndex, _memo.input, _Thunk({ Token.CONJ }, _r232), true))
                    }

                    _label = 105
                }
                // OR 105
                105 -> {
                    // OR shortcut 104
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i104
                    } else {
                        _label = 104
                        continue
                    }

                    // LITERAL "||"
                    _ParseLiteralString(_memo, _index, "||")

                    // ACT 234
                    val _r234 = _memo.results.peek()
                    if (_r234 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r234.startIndex, _r234.nextIndex, _memo.input, _Thunk({ Token.DISJ }, _r234), true))
                    }

                    _label = 104
                }
                // OR 104
                104 -> {
                    // OR shortcut 103
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i103
                    } else {
                        _label = 103
                        continue
                    }

                    // LITERAL "!!"
                    _ParseLiteralString(_memo, _index, "!!")

                    // ACT 236
                    val _r236 = _memo.results.peek()
                    if (_r236 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r236.startIndex, _r236.nextIndex, _memo.input, _Thunk({ Token.EXCL_EXCL }, _r236), true))
                    }

                    _label = 103
                }
                // OR 103
                103 -> {
                    // OR shortcut 102
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i102
                    } else {
                        _label = 102
                        continue
                    }

                    // LITERAL "!=="
                    _ParseLiteralString(_memo, _index, "!==")

                    // ACT 238
                    val _r238 = _memo.results.peek()
                    if (_r238 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r238.startIndex, _r238.nextIndex, _memo.input, _Thunk({ Token.EXCL_EQEQ }, _r238), true))
                    }

                    _label = 102
                }
                // OR 102
                102 -> {
                    // OR shortcut 101
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i101
                    } else {
                        _label = 101
                        continue
                    }

                    // LITERAL "!="
                    _ParseLiteralString(_memo, _index, "!=")

                    // ACT 240
                    val _r240 = _memo.results.peek()
                    if (_r240 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r240.startIndex, _r240.nextIndex, _memo.input, _Thunk({ Token.EXCL_EQ }, _r240), true))
                    }

                    _label = 101
                }
                // OR 101
                101 -> {
                    // OR shortcut 100
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i100
                    } else {
                        _label = 100
                        continue
                    }

                    // LITERAL "!"
                    _ParseLiteralString(_memo, _index, "!")

                    // ACT 242
                    val _r242 = _memo.results.peek()
                    if (_r242 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r242.startIndex, _r242.nextIndex, _memo.input, _Thunk({ Token.EXCL }, _r242), true))
                    }

                    _label = 100
                }
                // OR 100
                100 -> {
                    // OR shortcut 99
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i99
                    } else {
                        _label = 99
                        continue
                    }

                    // LITERAL ";"
                    _ParseLiteralString(_memo, _index, ";")

                    // ACT 244
                    val _r244 = _memo.results.peek()
                    if (_r244 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r244.startIndex, _r244.nextIndex, _memo.input, _Thunk({ Token.SEMICOLON }, _r244), true))
                    }

                    _label = 99
                }
                // OR 99
                99 -> {
                    // OR shortcut 98
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i98
                    } else {
                        _label = 98
                        continue
                    }

                    // LITERAL "::"
                    _ParseLiteralString(_memo, _index, "::")

                    // ACT 246
                    val _r246 = _memo.results.peek()
                    if (_r246 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r246.startIndex, _r246.nextIndex, _memo.input, _Thunk({ Token.COLONCOLON }, _r246), true))
                    }

                    _label = 98
                }
                // OR 98
                98 -> {
                    // OR shortcut 97
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i97
                    } else {
                        _label = 97
                        continue
                    }

                    // LITERAL ":"
                    _ParseLiteralString(_memo, _index, ":")

                    // ACT 248
                    val _r248 = _memo.results.peek()
                    if (_r248 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r248.startIndex, _r248.nextIndex, _memo.input, _Thunk({ Token.COLON }, _r248), true))
                    }

                    _label = 97
                }
                // OR 97
                97 -> {
                    // OR shortcut 96
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i96
                    } else {
                        _label = 96
                        continue
                    }

                    // LITERAL "#"
                    _ParseLiteralString(_memo, _index, "#")

                    // ACT 250
                    val _r250 = _memo.results.peek()
                    if (_r250 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r250.startIndex, _r250.nextIndex, _memo.input, _Thunk({ Token.HASH }, _r250), true))
                    }

                    _label = 96
                }
                // OR 96
                96 -> {
                    // OR shortcut 95
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i95
                    } else {
                        _label = 95
                        continue
                    }

                    // LITERAL "@"
                    _ParseLiteralString(_memo, _index, "@")

                    // ACT 252
                    val _r252 = _memo.results.peek()
                    if (_r252 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r252.startIndex, _r252.nextIndex, _memo.input, _Thunk({ Token.AT }, _r252), true))
                    }

                    _label = 95
                }
                // OR 95
                95 -> {
                    // OR shortcut 94
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i94
                    } else {
                        _label = 94
                        continue
                    }

                    // LITERAL "?::"
                    _ParseLiteralString(_memo, _index, "?::")

                    // ACT 254
                    val _r254 = _memo.results.peek()
                    if (_r254 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r254.startIndex, _r254.nextIndex, _memo.input, _Thunk({ Token.Q_COLONCOLON }, _r254), true))
                    }

                    _label = 94
                }
                // OR 94
                94 -> {
                    // OR shortcut 93
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i93
                    } else {
                        _label = 93
                        continue
                    }

                    // LITERAL "?:"
                    _ParseLiteralString(_memo, _index, "?:")

                    // ACT 256
                    val _r256 = _memo.results.peek()
                    if (_r256 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r256.startIndex, _r256.nextIndex, _memo.input, _Thunk({ Token.ELVIS }, _r256), true))
                    }

                    _label = 93
                }
                // OR 93
                93 -> {
                    // OR shortcut 92
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i92
                    } else {
                        _label = 92
                        continue
                    }

                    // LITERAL "?."
                    _ParseLiteralString(_memo, _index, "?.")

                    // ACT 258
                    val _r258 = _memo.results.peek()
                    if (_r258 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r258.startIndex, _r258.nextIndex, _memo.input, _Thunk({ Token.QUEST_DOT }, _r258), true))
                    }

                    _label = 92
                }
                // OR 92
                92 -> {
                    // OR shortcut 91
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i91
                    } else {
                        _label = 91
                        continue
                    }

                    // LITERAL "?"
                    _ParseLiteralString(_memo, _index, "?")

                    // ACT 260
                    val _r260 = _memo.results.peek()
                    if (_r260 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r260.startIndex, _r260.nextIndex, _memo.input, _Thunk({ Token.QUEST }, _r260), true))
                    }

                    _label = 91
                }
                // OR 91
                91 -> {
                    // OR shortcut 90
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i90
                    } else {
                        _label = 90
                        continue
                    }

                    // LITERAL "<="
                    _ParseLiteralString(_memo, _index, "<=")

                    // ACT 262
                    val _r262 = _memo.results.peek()
                    if (_r262 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r262.startIndex, _r262.nextIndex, _memo.input, _Thunk({ Token.LE }, _r262), true))
                    }

                    _label = 90
                }
                // OR 90
                90 -> {
                    // OR shortcut 89
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i89
                    } else {
                        _label = 89
                        continue
                    }

                    // LITERAL "<"
                    _ParseLiteralString(_memo, _index, "<")

                    // ACT 264
                    val _r264 = _memo.results.peek()
                    if (_r264 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r264.startIndex, _r264.nextIndex, _memo.input, _Thunk({ Token.LANGLE }, _r264), true))
                    }

                    _label = 89
                }
                // OR 89
                89 -> {
                    // OR shortcut 88
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i88
                    } else {
                        _label = 88
                        continue
                    }

                    // LITERAL ">="
                    _ParseLiteralString(_memo, _index, ">=")

                    // ACT 266
                    val _r266 = _memo.results.peek()
                    if (_r266 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r266.startIndex, _r266.nextIndex, _memo.input, _Thunk({ Token.GE }, _r266), true))
                    }

                    _label = 88
                }
                // OR 88
                88 -> {
                    // OR shortcut 87
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i87
                    } else {
                        _label = 87
                        continue
                    }

                    // LITERAL ">"
                    _ParseLiteralString(_memo, _index, ">")

                    // ACT 268
                    val _r268 = _memo.results.peek()
                    if (_r268 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r268.startIndex, _r268.nextIndex, _memo.input, _Thunk({ Token.RANGLE }, _r268), true))
                    }

                    _label = 87
                }
                // OR 87
                87 -> {
                    // OR shortcut 86
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i86
                    } else {
                        _label = 86
                        continue
                    }

                    // LITERAL "as?"
                    _ParseLiteralString(_memo, _index, "as?")

                    // ACT 270
                    val _r270 = _memo.results.peek()
                    if (_r270 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r270.startIndex, _r270.nextIndex, _memo.input, _Thunk({ Token.AS_SAFE }, _r270), true))
                    }

                    _label = 86
                }
                // OR 86
                86 -> {
                    // OR shortcut 85
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i85
                    } else {
                        _label = 85
                        continue
                    }

                    // LITERAL "==="
                    _ParseLiteralString(_memo, _index, "===")

                    // ACT 272
                    val _r272 = _memo.results.peek()
                    if (_r272 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r272.startIndex, _r272.nextIndex, _memo.input, _Thunk({ Token.EQEQEQ }, _r272), true))
                    }

                    _label = 85
                }
                // OR 85
                85 -> {
                    // OR shortcut 84
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i84
                    } else {
                        _label = 84
                        continue
                    }

                    // LITERAL "=="
                    _ParseLiteralString(_memo, _index, "==")

                    // ACT 274
                    val _r274 = _memo.results.peek()
                    if (_r274 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r274.startIndex, _r274.nextIndex, _memo.input, _Thunk({ Token.EQEQ }, _r274), true))
                    }

                    _label = 84
                }
                // OR 84
                84 -> {
                    // OR shortcut 83
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i83
                    } else {
                        _label = 83
                        continue
                    }

                    // LITERAL "=>"
                    _ParseLiteralString(_memo, _index, "=>")

                    // ACT 276
                    val _r276 = _memo.results.peek()
                    if (_r276 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r276.startIndex, _r276.nextIndex, _memo.input, _Thunk({ Token.DOUBLE_ARROW }, _r276), true))
                    }

                    _label = 83
                }
                // OR 83
                83 -> {
                    // OR shortcut 82
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i82
                    } else {
                        _label = 82
                        continue
                    }

                    // LITERAL "="
                    _ParseLiteralString(_memo, _index, "=")

                    // ACT 278
                    val _r278 = _memo.results.peek()
                    if (_r278 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r278.startIndex, _r278.nextIndex, _memo.input, _Thunk({ Token.ASSIGNMENT }, _r278), true))
                    }

                    _label = 82
                }
                // OR 82
                82 -> {
                    // OR shortcut 81
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i81
                    } else {
                        _label = 81
                        continue
                    }

                    // AND 281
                    _start_i281 = _index.element

                    // LITERAL "return@"
                    _ParseLiteralString(_memo, _index, "return@")

                    // AND shortcut 281
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 281
                        continue
                    }

                    // CALLORVAR identifier
                    var _r284: _KotlinLexer_Item? = null
                    _r284 = _MemoCall(_memo, "identifier", _index.element, ::identifier, null)
                    if (_r284 != null) _index.element = _r284.nextIndex

                    // BIND name
                    name = _memo.results.peek()

                    _label = 281
                }
                // AND 281
                281 -> {
                    val _r281_2 = _memo.results.pop()
                    val _r281_1 = _memo.results.pop()

                    if (_r281_1 != null && _r281_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i281, _index.element, _memo.input, (_r281_1.results + _r281_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i281
                    }

                    // ACT 280
                    val _r280 = _memo.results.peek()
                    if (_r280 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r280.startIndex, _r280.nextIndex, _memo.input, _Thunk({ Token.ReturnAt(name.s) }, _r280), true))
                    }

                    _label = 81
                }
                // OR 81
                81 -> {
                    // OR shortcut 80
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i80
                    } else {
                        _label = 80
                        continue
                    }

                    // AND 286
                    _start_i286 = _index.element

                    // LITERAL "continue@"
                    _ParseLiteralString(_memo, _index, "continue@")

                    // AND shortcut 286
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 286
                        continue
                    }

                    // CALLORVAR identifier
                    var _r289: _KotlinLexer_Item? = null
                    _r289 = _MemoCall(_memo, "identifier", _index.element, ::identifier, null)
                    if (_r289 != null) _index.element = _r289.nextIndex

                    // BIND name
                    name = _memo.results.peek()

                    _label = 286
                }
                // AND 286
                286 -> {
                    val _r286_2 = _memo.results.pop()
                    val _r286_1 = _memo.results.pop()

                    if (_r286_1 != null && _r286_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i286, _index.element, _memo.input, (_r286_1.results + _r286_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i286
                    }

                    // ACT 285
                    val _r285 = _memo.results.peek()
                    if (_r285 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r285.startIndex, _r285.nextIndex, _memo.input, _Thunk({ Token.ContinueAt(name.s) }, _r285), true))
                    }

                    _label = 80
                }
                // OR 80
                80 -> {
                    // OR shortcut 79
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i79
                    } else {
                        _label = 79
                        continue
                    }

                    // AND 291
                    _start_i291 = _index.element

                    // LITERAL "break@"
                    _ParseLiteralString(_memo, _index, "break@")

                    // AND shortcut 291
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 291
                        continue
                    }

                    // CALLORVAR identifier
                    var _r294: _KotlinLexer_Item? = null
                    _r294 = _MemoCall(_memo, "identifier", _index.element, ::identifier, null)
                    if (_r294 != null) _index.element = _r294.nextIndex

                    // BIND name
                    name = _memo.results.peek()

                    _label = 291
                }
                // AND 291
                291 -> {
                    val _r291_2 = _memo.results.pop()
                    val _r291_1 = _memo.results.pop()

                    if (_r291_1 != null && _r291_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i291, _index.element, _memo.input, (_r291_1.results + _r291_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i291
                    }

                    // ACT 290
                    val _r290 = _memo.results.peek()
                    if (_r290 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r290.startIndex, _r290.nextIndex, _memo.input, _Thunk({ Token.BreakAt(name.s) }, _r290), true))
                    }

                    _label = 79
                }
                // OR 79
                79 -> {
                    // OR shortcut 78
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i78
                    } else {
                        _label = 78
                        continue
                    }

                    // AND 296
                    _start_i296 = _index.element

                    // LITERAL "this@"
                    _ParseLiteralString(_memo, _index, "this@")

                    // AND shortcut 296
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 296
                        continue
                    }

                    // CALLORVAR identifier
                    var _r299: _KotlinLexer_Item? = null
                    _r299 = _MemoCall(_memo, "identifier", _index.element, ::identifier, null)
                    if (_r299 != null) _index.element = _r299.nextIndex

                    // BIND name
                    name = _memo.results.peek()

                    _label = 296
                }
                // AND 296
                296 -> {
                    val _r296_2 = _memo.results.pop()
                    val _r296_1 = _memo.results.pop()

                    if (_r296_1 != null && _r296_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i296, _index.element, _memo.input, (_r296_1.results + _r296_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i296
                    }

                    // ACT 295
                    val _r295 = _memo.results.peek()
                    if (_r295 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r295.startIndex, _r295.nextIndex, _memo.input, _Thunk({ Token.ThisAt(name.s) }, _r295), true))
                    }

                    _label = 78
                }
                // OR 78
                78 -> {
                    // OR shortcut 77
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i77
                    } else {
                        _label = 77
                        continue
                    }

                    // AND 301
                    _start_i301 = _index.element

                    // LITERAL "file"
                    _ParseLiteralString(_memo, _index, "file")

                    // AND shortcut 301
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 301
                        continue
                    }

                    // NOT 303
                    _start_i303 = _index.element

                    // CALLORVAR identifierPart
                    var _r304: _KotlinLexer_Item? = null
                    _r304 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r304 != null) _index.element = _r304.nextIndex

                    // NOT 303
                    val _r303 = _memo.results.pop()
                    _memo.results.push(if (_r303 == null) _KotlinLexer_Item(_start_i303, _memo.input) else null)
                    _index.element = _start_i303
                    _label = 301
                }
                // AND 301
                301 -> {
                    val _r301_2 = _memo.results.pop()
                    val _r301_1 = _memo.results.pop()

                    if (_r301_1 != null && _r301_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i301, _index.element, _memo.input, (_r301_1.results + _r301_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i301
                    }

                    // ACT 300
                    val _r300 = _memo.results.peek()
                    if (_r300 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r300.startIndex, _r300.nextIndex, _memo.input, _Thunk({ Token.FILE }, _r300), true))
                    }

                    _label = 77
                }
                // OR 77
                77 -> {
                    // OR shortcut 76
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i76
                    } else {
                        _label = 76
                        continue
                    }

                    // AND 306
                    _start_i306 = _index.element

                    // LITERAL "package"
                    _ParseLiteralString(_memo, _index, "package")

                    // AND shortcut 306
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 306
                        continue
                    }

                    // NOT 308
                    _start_i308 = _index.element

                    // CALLORVAR identifierPart
                    var _r309: _KotlinLexer_Item? = null
                    _r309 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r309 != null) _index.element = _r309.nextIndex

                    // NOT 308
                    val _r308 = _memo.results.pop()
                    _memo.results.push(if (_r308 == null) _KotlinLexer_Item(_start_i308, _memo.input) else null)
                    _index.element = _start_i308
                    _label = 306
                }
                // AND 306
                306 -> {
                    val _r306_2 = _memo.results.pop()
                    val _r306_1 = _memo.results.pop()

                    if (_r306_1 != null && _r306_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i306, _index.element, _memo.input, (_r306_1.results + _r306_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i306
                    }

                    // ACT 305
                    val _r305 = _memo.results.peek()
                    if (_r305 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r305.startIndex, _r305.nextIndex, _memo.input, _Thunk({ Token.PACKAGE }, _r305), true))
                    }

                    _label = 76
                }
                // OR 76
                76 -> {
                    // OR shortcut 75
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i75
                    } else {
                        _label = 75
                        continue
                    }

                    // AND 311
                    _start_i311 = _index.element

                    // LITERAL "import"
                    _ParseLiteralString(_memo, _index, "import")

                    // AND shortcut 311
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 311
                        continue
                    }

                    // NOT 313
                    _start_i313 = _index.element

                    // CALLORVAR identifierPart
                    var _r314: _KotlinLexer_Item? = null
                    _r314 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r314 != null) _index.element = _r314.nextIndex

                    // NOT 313
                    val _r313 = _memo.results.pop()
                    _memo.results.push(if (_r313 == null) _KotlinLexer_Item(_start_i313, _memo.input) else null)
                    _index.element = _start_i313
                    _label = 311
                }
                // AND 311
                311 -> {
                    val _r311_2 = _memo.results.pop()
                    val _r311_1 = _memo.results.pop()

                    if (_r311_1 != null && _r311_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i311, _index.element, _memo.input, (_r311_1.results + _r311_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i311
                    }

                    // ACT 310
                    val _r310 = _memo.results.peek()
                    if (_r310 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r310.startIndex, _r310.nextIndex, _memo.input, _Thunk({ Token.IMPORT }, _r310), true))
                    }

                    _label = 75
                }
                // OR 75
                75 -> {
                    // OR shortcut 74
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i74
                    } else {
                        _label = 74
                        continue
                    }

                    // AND 316
                    _start_i316 = _index.element

                    // LITERAL "class"
                    _ParseLiteralString(_memo, _index, "class")

                    // AND shortcut 316
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 316
                        continue
                    }

                    // NOT 318
                    _start_i318 = _index.element

                    // CALLORVAR identifierPart
                    var _r319: _KotlinLexer_Item? = null
                    _r319 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r319 != null) _index.element = _r319.nextIndex

                    // NOT 318
                    val _r318 = _memo.results.pop()
                    _memo.results.push(if (_r318 == null) _KotlinLexer_Item(_start_i318, _memo.input) else null)
                    _index.element = _start_i318
                    _label = 316
                }
                // AND 316
                316 -> {
                    val _r316_2 = _memo.results.pop()
                    val _r316_1 = _memo.results.pop()

                    if (_r316_1 != null && _r316_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i316, _index.element, _memo.input, (_r316_1.results + _r316_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i316
                    }

                    // ACT 315
                    val _r315 = _memo.results.peek()
                    if (_r315 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r315.startIndex, _r315.nextIndex, _memo.input, _Thunk({ Token.CLASS }, _r315), true))
                    }

                    _label = 74
                }
                // OR 74
                74 -> {
                    // OR shortcut 73
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i73
                    } else {
                        _label = 73
                        continue
                    }

                    // AND 321
                    _start_i321 = _index.element

                    // LITERAL "interface"
                    _ParseLiteralString(_memo, _index, "interface")

                    // AND shortcut 321
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 321
                        continue
                    }

                    // NOT 323
                    _start_i323 = _index.element

                    // CALLORVAR identifierPart
                    var _r324: _KotlinLexer_Item? = null
                    _r324 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r324 != null) _index.element = _r324.nextIndex

                    // NOT 323
                    val _r323 = _memo.results.pop()
                    _memo.results.push(if (_r323 == null) _KotlinLexer_Item(_start_i323, _memo.input) else null)
                    _index.element = _start_i323
                    _label = 321
                }
                // AND 321
                321 -> {
                    val _r321_2 = _memo.results.pop()
                    val _r321_1 = _memo.results.pop()

                    if (_r321_1 != null && _r321_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i321, _index.element, _memo.input, (_r321_1.results + _r321_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i321
                    }

                    // ACT 320
                    val _r320 = _memo.results.peek()
                    if (_r320 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r320.startIndex, _r320.nextIndex, _memo.input, _Thunk({ Token.INTERFACE }, _r320), true))
                    }

                    _label = 73
                }
                // OR 73
                73 -> {
                    // OR shortcut 72
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i72
                    } else {
                        _label = 72
                        continue
                    }

                    // AND 326
                    _start_i326 = _index.element

                    // LITERAL "fun"
                    _ParseLiteralString(_memo, _index, "fun")

                    // AND shortcut 326
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 326
                        continue
                    }

                    // NOT 328
                    _start_i328 = _index.element

                    // CALLORVAR identifierPart
                    var _r329: _KotlinLexer_Item? = null
                    _r329 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r329 != null) _index.element = _r329.nextIndex

                    // NOT 328
                    val _r328 = _memo.results.pop()
                    _memo.results.push(if (_r328 == null) _KotlinLexer_Item(_start_i328, _memo.input) else null)
                    _index.element = _start_i328
                    _label = 326
                }
                // AND 326
                326 -> {
                    val _r326_2 = _memo.results.pop()
                    val _r326_1 = _memo.results.pop()

                    if (_r326_1 != null && _r326_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i326, _index.element, _memo.input, (_r326_1.results + _r326_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i326
                    }

                    // ACT 325
                    val _r325 = _memo.results.peek()
                    if (_r325 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r325.startIndex, _r325.nextIndex, _memo.input, _Thunk({ Token.FUN }, _r325), true))
                    }

                    _label = 72
                }
                // OR 72
                72 -> {
                    // OR shortcut 71
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i71
                    } else {
                        _label = 71
                        continue
                    }

                    // AND 331
                    _start_i331 = _index.element

                    // LITERAL "object"
                    _ParseLiteralString(_memo, _index, "object")

                    // AND shortcut 331
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 331
                        continue
                    }

                    // NOT 333
                    _start_i333 = _index.element

                    // CALLORVAR identifierPart
                    var _r334: _KotlinLexer_Item? = null
                    _r334 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r334 != null) _index.element = _r334.nextIndex

                    // NOT 333
                    val _r333 = _memo.results.pop()
                    _memo.results.push(if (_r333 == null) _KotlinLexer_Item(_start_i333, _memo.input) else null)
                    _index.element = _start_i333
                    _label = 331
                }
                // AND 331
                331 -> {
                    val _r331_2 = _memo.results.pop()
                    val _r331_1 = _memo.results.pop()

                    if (_r331_1 != null && _r331_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i331, _index.element, _memo.input, (_r331_1.results + _r331_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i331
                    }

                    // ACT 330
                    val _r330 = _memo.results.peek()
                    if (_r330 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r330.startIndex, _r330.nextIndex, _memo.input, _Thunk({ Token.OBJECT }, _r330), true))
                    }

                    _label = 71
                }
                // OR 71
                71 -> {
                    // OR shortcut 70
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i70
                    } else {
                        _label = 70
                        continue
                    }

                    // AND 336
                    _start_i336 = _index.element

                    // LITERAL "val"
                    _ParseLiteralString(_memo, _index, "val")

                    // AND shortcut 336
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 336
                        continue
                    }

                    // NOT 338
                    _start_i338 = _index.element

                    // CALLORVAR identifierPart
                    var _r339: _KotlinLexer_Item? = null
                    _r339 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r339 != null) _index.element = _r339.nextIndex

                    // NOT 338
                    val _r338 = _memo.results.pop()
                    _memo.results.push(if (_r338 == null) _KotlinLexer_Item(_start_i338, _memo.input) else null)
                    _index.element = _start_i338
                    _label = 336
                }
                // AND 336
                336 -> {
                    val _r336_2 = _memo.results.pop()
                    val _r336_1 = _memo.results.pop()

                    if (_r336_1 != null && _r336_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i336, _index.element, _memo.input, (_r336_1.results + _r336_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i336
                    }

                    // ACT 335
                    val _r335 = _memo.results.peek()
                    if (_r335 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r335.startIndex, _r335.nextIndex, _memo.input, _Thunk({ Token.VAL }, _r335), true))
                    }

                    _label = 70
                }
                // OR 70
                70 -> {
                    // OR shortcut 69
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i69
                    } else {
                        _label = 69
                        continue
                    }

                    // AND 341
                    _start_i341 = _index.element

                    // LITERAL "var"
                    _ParseLiteralString(_memo, _index, "var")

                    // AND shortcut 341
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 341
                        continue
                    }

                    // NOT 343
                    _start_i343 = _index.element

                    // CALLORVAR identifierPart
                    var _r344: _KotlinLexer_Item? = null
                    _r344 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r344 != null) _index.element = _r344.nextIndex

                    // NOT 343
                    val _r343 = _memo.results.pop()
                    _memo.results.push(if (_r343 == null) _KotlinLexer_Item(_start_i343, _memo.input) else null)
                    _index.element = _start_i343
                    _label = 341
                }
                // AND 341
                341 -> {
                    val _r341_2 = _memo.results.pop()
                    val _r341_1 = _memo.results.pop()

                    if (_r341_1 != null && _r341_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i341, _index.element, _memo.input, (_r341_1.results + _r341_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i341
                    }

                    // ACT 340
                    val _r340 = _memo.results.peek()
                    if (_r340 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r340.startIndex, _r340.nextIndex, _memo.input, _Thunk({ Token.VAR }, _r340), true))
                    }

                    _label = 69
                }
                // OR 69
                69 -> {
                    // OR shortcut 68
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i68
                    } else {
                        _label = 68
                        continue
                    }

                    // AND 346
                    _start_i346 = _index.element

                    // LITERAL "typealias"
                    _ParseLiteralString(_memo, _index, "typealias")

                    // AND shortcut 346
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 346
                        continue
                    }

                    // NOT 348
                    _start_i348 = _index.element

                    // CALLORVAR identifierPart
                    var _r349: _KotlinLexer_Item? = null
                    _r349 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r349 != null) _index.element = _r349.nextIndex

                    // NOT 348
                    val _r348 = _memo.results.pop()
                    _memo.results.push(if (_r348 == null) _KotlinLexer_Item(_start_i348, _memo.input) else null)
                    _index.element = _start_i348
                    _label = 346
                }
                // AND 346
                346 -> {
                    val _r346_2 = _memo.results.pop()
                    val _r346_1 = _memo.results.pop()

                    if (_r346_1 != null && _r346_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i346, _index.element, _memo.input, (_r346_1.results + _r346_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i346
                    }

                    // ACT 345
                    val _r345 = _memo.results.peek()
                    if (_r345 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r345.startIndex, _r345.nextIndex, _memo.input, _Thunk({ Token.TYPE_ALIAS }, _r345), true))
                    }

                    _label = 68
                }
                // OR 68
                68 -> {
                    // OR shortcut 67
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i67
                    } else {
                        _label = 67
                        continue
                    }

                    // AND 351
                    _start_i351 = _index.element

                    // LITERAL "constructor"
                    _ParseLiteralString(_memo, _index, "constructor")

                    // AND shortcut 351
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 351
                        continue
                    }

                    // NOT 353
                    _start_i353 = _index.element

                    // CALLORVAR identifierPart
                    var _r354: _KotlinLexer_Item? = null
                    _r354 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r354 != null) _index.element = _r354.nextIndex

                    // NOT 353
                    val _r353 = _memo.results.pop()
                    _memo.results.push(if (_r353 == null) _KotlinLexer_Item(_start_i353, _memo.input) else null)
                    _index.element = _start_i353
                    _label = 351
                }
                // AND 351
                351 -> {
                    val _r351_2 = _memo.results.pop()
                    val _r351_1 = _memo.results.pop()

                    if (_r351_1 != null && _r351_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i351, _index.element, _memo.input, (_r351_1.results + _r351_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i351
                    }

                    // ACT 350
                    val _r350 = _memo.results.peek()
                    if (_r350 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r350.startIndex, _r350.nextIndex, _memo.input, _Thunk({ Token.CONSTRUCTOR }, _r350), true))
                    }

                    _label = 67
                }
                // OR 67
                67 -> {
                    // OR shortcut 66
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i66
                    } else {
                        _label = 66
                        continue
                    }

                    // AND 356
                    _start_i356 = _index.element

                    // LITERAL "by"
                    _ParseLiteralString(_memo, _index, "by")

                    // AND shortcut 356
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 356
                        continue
                    }

                    // NOT 358
                    _start_i358 = _index.element

                    // CALLORVAR identifierPart
                    var _r359: _KotlinLexer_Item? = null
                    _r359 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r359 != null) _index.element = _r359.nextIndex

                    // NOT 358
                    val _r358 = _memo.results.pop()
                    _memo.results.push(if (_r358 == null) _KotlinLexer_Item(_start_i358, _memo.input) else null)
                    _index.element = _start_i358
                    _label = 356
                }
                // AND 356
                356 -> {
                    val _r356_2 = _memo.results.pop()
                    val _r356_1 = _memo.results.pop()

                    if (_r356_1 != null && _r356_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i356, _index.element, _memo.input, (_r356_1.results + _r356_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i356
                    }

                    // ACT 355
                    val _r355 = _memo.results.peek()
                    if (_r355 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r355.startIndex, _r355.nextIndex, _memo.input, _Thunk({ Token.BY }, _r355), true))
                    }

                    _label = 66
                }
                // OR 66
                66 -> {
                    // OR shortcut 65
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i65
                    } else {
                        _label = 65
                        continue
                    }

                    // AND 361
                    _start_i361 = _index.element

                    // LITERAL "companion"
                    _ParseLiteralString(_memo, _index, "companion")

                    // AND shortcut 361
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 361
                        continue
                    }

                    // NOT 363
                    _start_i363 = _index.element

                    // CALLORVAR identifierPart
                    var _r364: _KotlinLexer_Item? = null
                    _r364 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r364 != null) _index.element = _r364.nextIndex

                    // NOT 363
                    val _r363 = _memo.results.pop()
                    _memo.results.push(if (_r363 == null) _KotlinLexer_Item(_start_i363, _memo.input) else null)
                    _index.element = _start_i363
                    _label = 361
                }
                // AND 361
                361 -> {
                    val _r361_2 = _memo.results.pop()
                    val _r361_1 = _memo.results.pop()

                    if (_r361_1 != null && _r361_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i361, _index.element, _memo.input, (_r361_1.results + _r361_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i361
                    }

                    // ACT 360
                    val _r360 = _memo.results.peek()
                    if (_r360 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r360.startIndex, _r360.nextIndex, _memo.input, _Thunk({ Token.COMPANION }, _r360), true))
                    }

                    _label = 65
                }
                // OR 65
                65 -> {
                    // OR shortcut 64
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i64
                    } else {
                        _label = 64
                        continue
                    }

                    // AND 366
                    _start_i366 = _index.element

                    // LITERAL "init'"
                    _ParseLiteralString(_memo, _index, "init'")

                    // AND shortcut 366
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 366
                        continue
                    }

                    // NOT 368
                    _start_i368 = _index.element

                    // CALLORVAR identifierPart
                    var _r369: _KotlinLexer_Item? = null
                    _r369 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r369 != null) _index.element = _r369.nextIndex

                    // NOT 368
                    val _r368 = _memo.results.pop()
                    _memo.results.push(if (_r368 == null) _KotlinLexer_Item(_start_i368, _memo.input) else null)
                    _index.element = _start_i368
                    _label = 366
                }
                // AND 366
                366 -> {
                    val _r366_2 = _memo.results.pop()
                    val _r366_1 = _memo.results.pop()

                    if (_r366_1 != null && _r366_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i366, _index.element, _memo.input, (_r366_1.results + _r366_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i366
                    }

                    // ACT 365
                    val _r365 = _memo.results.peek()
                    if (_r365 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r365.startIndex, _r365.nextIndex, _memo.input, _Thunk({ Token.INIT }, _r365), true))
                    }

                    _label = 64
                }
                // OR 64
                64 -> {
                    // OR shortcut 63
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i63
                    } else {
                        _label = 63
                        continue
                    }

                    // AND 371
                    _start_i371 = _index.element

                    // LITERAL "this"
                    _ParseLiteralString(_memo, _index, "this")

                    // AND shortcut 371
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 371
                        continue
                    }

                    // NOT 373
                    _start_i373 = _index.element

                    // CALLORVAR identifierPart
                    var _r374: _KotlinLexer_Item? = null
                    _r374 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r374 != null) _index.element = _r374.nextIndex

                    // NOT 373
                    val _r373 = _memo.results.pop()
                    _memo.results.push(if (_r373 == null) _KotlinLexer_Item(_start_i373, _memo.input) else null)
                    _index.element = _start_i373
                    _label = 371
                }
                // AND 371
                371 -> {
                    val _r371_2 = _memo.results.pop()
                    val _r371_1 = _memo.results.pop()

                    if (_r371_1 != null && _r371_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i371, _index.element, _memo.input, (_r371_1.results + _r371_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i371
                    }

                    // ACT 370
                    val _r370 = _memo.results.peek()
                    if (_r370 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r370.startIndex, _r370.nextIndex, _memo.input, _Thunk({ Token.THIS }, _r370), true))
                    }

                    _label = 63
                }
                // OR 63
                63 -> {
                    // OR shortcut 62
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i62
                    } else {
                        _label = 62
                        continue
                    }

                    // AND 376
                    _start_i376 = _index.element

                    // LITERAL "super"
                    _ParseLiteralString(_memo, _index, "super")

                    // AND shortcut 376
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 376
                        continue
                    }

                    // NOT 378
                    _start_i378 = _index.element

                    // CALLORVAR identifierPart
                    var _r379: _KotlinLexer_Item? = null
                    _r379 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r379 != null) _index.element = _r379.nextIndex

                    // NOT 378
                    val _r378 = _memo.results.pop()
                    _memo.results.push(if (_r378 == null) _KotlinLexer_Item(_start_i378, _memo.input) else null)
                    _index.element = _start_i378
                    _label = 376
                }
                // AND 376
                376 -> {
                    val _r376_2 = _memo.results.pop()
                    val _r376_1 = _memo.results.pop()

                    if (_r376_1 != null && _r376_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i376, _index.element, _memo.input, (_r376_1.results + _r376_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i376
                    }

                    // ACT 375
                    val _r375 = _memo.results.peek()
                    if (_r375 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r375.startIndex, _r375.nextIndex, _memo.input, _Thunk({ Token.SUPER }, _r375), true))
                    }

                    _label = 62
                }
                // OR 62
                62 -> {
                    // OR shortcut 61
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i61
                    } else {
                        _label = 61
                        continue
                    }

                    // AND 381
                    _start_i381 = _index.element

                    // LITERAL "typeof"
                    _ParseLiteralString(_memo, _index, "typeof")

                    // AND shortcut 381
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 381
                        continue
                    }

                    // NOT 383
                    _start_i383 = _index.element

                    // CALLORVAR identifierPart
                    var _r384: _KotlinLexer_Item? = null
                    _r384 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r384 != null) _index.element = _r384.nextIndex

                    // NOT 383
                    val _r383 = _memo.results.pop()
                    _memo.results.push(if (_r383 == null) _KotlinLexer_Item(_start_i383, _memo.input) else null)
                    _index.element = _start_i383
                    _label = 381
                }
                // AND 381
                381 -> {
                    val _r381_2 = _memo.results.pop()
                    val _r381_1 = _memo.results.pop()

                    if (_r381_1 != null && _r381_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i381, _index.element, _memo.input, (_r381_1.results + _r381_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i381
                    }

                    // ACT 380
                    val _r380 = _memo.results.peek()
                    if (_r380 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r380.startIndex, _r380.nextIndex, _memo.input, _Thunk({ Token.TYPEOF }, _r380), true))
                    }

                    _label = 61
                }
                // OR 61
                61 -> {
                    // OR shortcut 60
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i60
                    } else {
                        _label = 60
                        continue
                    }

                    // AND 386
                    _start_i386 = _index.element

                    // LITERAL "where"
                    _ParseLiteralString(_memo, _index, "where")

                    // AND shortcut 386
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 386
                        continue
                    }

                    // NOT 388
                    _start_i388 = _index.element

                    // CALLORVAR identifierPart
                    var _r389: _KotlinLexer_Item? = null
                    _r389 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r389 != null) _index.element = _r389.nextIndex

                    // NOT 388
                    val _r388 = _memo.results.pop()
                    _memo.results.push(if (_r388 == null) _KotlinLexer_Item(_start_i388, _memo.input) else null)
                    _index.element = _start_i388
                    _label = 386
                }
                // AND 386
                386 -> {
                    val _r386_2 = _memo.results.pop()
                    val _r386_1 = _memo.results.pop()

                    if (_r386_1 != null && _r386_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i386, _index.element, _memo.input, (_r386_1.results + _r386_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i386
                    }

                    // ACT 385
                    val _r385 = _memo.results.peek()
                    if (_r385 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r385.startIndex, _r385.nextIndex, _memo.input, _Thunk({ Token.WHERE }, _r385), true))
                    }

                    _label = 60
                }
                // OR 60
                60 -> {
                    // OR shortcut 59
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i59
                    } else {
                        _label = 59
                        continue
                    }

                    // AND 391
                    _start_i391 = _index.element

                    // LITERAL "if"
                    _ParseLiteralString(_memo, _index, "if")

                    // AND shortcut 391
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 391
                        continue
                    }

                    // NOT 393
                    _start_i393 = _index.element

                    // CALLORVAR identifierPart
                    var _r394: _KotlinLexer_Item? = null
                    _r394 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r394 != null) _index.element = _r394.nextIndex

                    // NOT 393
                    val _r393 = _memo.results.pop()
                    _memo.results.push(if (_r393 == null) _KotlinLexer_Item(_start_i393, _memo.input) else null)
                    _index.element = _start_i393
                    _label = 391
                }
                // AND 391
                391 -> {
                    val _r391_2 = _memo.results.pop()
                    val _r391_1 = _memo.results.pop()

                    if (_r391_1 != null && _r391_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i391, _index.element, _memo.input, (_r391_1.results + _r391_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i391
                    }

                    // ACT 390
                    val _r390 = _memo.results.peek()
                    if (_r390 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r390.startIndex, _r390.nextIndex, _memo.input, _Thunk({ Token.IF }, _r390), true))
                    }

                    _label = 59
                }
                // OR 59
                59 -> {
                    // OR shortcut 58
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i58
                    } else {
                        _label = 58
                        continue
                    }

                    // AND 396
                    _start_i396 = _index.element

                    // LITERAL "else"
                    _ParseLiteralString(_memo, _index, "else")

                    // AND shortcut 396
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 396
                        continue
                    }

                    // NOT 398
                    _start_i398 = _index.element

                    // CALLORVAR identifierPart
                    var _r399: _KotlinLexer_Item? = null
                    _r399 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r399 != null) _index.element = _r399.nextIndex

                    // NOT 398
                    val _r398 = _memo.results.pop()
                    _memo.results.push(if (_r398 == null) _KotlinLexer_Item(_start_i398, _memo.input) else null)
                    _index.element = _start_i398
                    _label = 396
                }
                // AND 396
                396 -> {
                    val _r396_2 = _memo.results.pop()
                    val _r396_1 = _memo.results.pop()

                    if (_r396_1 != null && _r396_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i396, _index.element, _memo.input, (_r396_1.results + _r396_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i396
                    }

                    // ACT 395
                    val _r395 = _memo.results.peek()
                    if (_r395 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r395.startIndex, _r395.nextIndex, _memo.input, _Thunk({ Token.ELSE }, _r395), true))
                    }

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

                    // AND 401
                    _start_i401 = _index.element

                    // LITERAL "when"
                    _ParseLiteralString(_memo, _index, "when")

                    // AND shortcut 401
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 401
                        continue
                    }

                    // NOT 403
                    _start_i403 = _index.element

                    // CALLORVAR identifierPart
                    var _r404: _KotlinLexer_Item? = null
                    _r404 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r404 != null) _index.element = _r404.nextIndex

                    // NOT 403
                    val _r403 = _memo.results.pop()
                    _memo.results.push(if (_r403 == null) _KotlinLexer_Item(_start_i403, _memo.input) else null)
                    _index.element = _start_i403
                    _label = 401
                }
                // AND 401
                401 -> {
                    val _r401_2 = _memo.results.pop()
                    val _r401_1 = _memo.results.pop()

                    if (_r401_1 != null && _r401_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i401, _index.element, _memo.input, (_r401_1.results + _r401_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i401
                    }

                    // ACT 400
                    val _r400 = _memo.results.peek()
                    if (_r400 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r400.startIndex, _r400.nextIndex, _memo.input, _Thunk({ Token.WHEN }, _r400), true))
                    }

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

                    // AND 406
                    _start_i406 = _index.element

                    // LITERAL "try"
                    _ParseLiteralString(_memo, _index, "try")

                    // AND shortcut 406
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 406
                        continue
                    }

                    // NOT 408
                    _start_i408 = _index.element

                    // CALLORVAR identifierPart
                    var _r409: _KotlinLexer_Item? = null
                    _r409 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r409 != null) _index.element = _r409.nextIndex

                    // NOT 408
                    val _r408 = _memo.results.pop()
                    _memo.results.push(if (_r408 == null) _KotlinLexer_Item(_start_i408, _memo.input) else null)
                    _index.element = _start_i408
                    _label = 406
                }
                // AND 406
                406 -> {
                    val _r406_2 = _memo.results.pop()
                    val _r406_1 = _memo.results.pop()

                    if (_r406_1 != null && _r406_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i406, _index.element, _memo.input, (_r406_1.results + _r406_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i406
                    }

                    // ACT 405
                    val _r405 = _memo.results.peek()
                    if (_r405 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r405.startIndex, _r405.nextIndex, _memo.input, _Thunk({ Token.TRY }, _r405), true))
                    }

                    _label = 56
                }
                // OR 56
                56 -> {
                    // OR shortcut 55
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i55
                    } else {
                        _label = 55
                        continue
                    }

                    // AND 411
                    _start_i411 = _index.element

                    // LITERAL "catch"
                    _ParseLiteralString(_memo, _index, "catch")

                    // AND shortcut 411
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 411
                        continue
                    }

                    // NOT 413
                    _start_i413 = _index.element

                    // CALLORVAR identifierPart
                    var _r414: _KotlinLexer_Item? = null
                    _r414 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r414 != null) _index.element = _r414.nextIndex

                    // NOT 413
                    val _r413 = _memo.results.pop()
                    _memo.results.push(if (_r413 == null) _KotlinLexer_Item(_start_i413, _memo.input) else null)
                    _index.element = _start_i413
                    _label = 411
                }
                // AND 411
                411 -> {
                    val _r411_2 = _memo.results.pop()
                    val _r411_1 = _memo.results.pop()

                    if (_r411_1 != null && _r411_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i411, _index.element, _memo.input, (_r411_1.results + _r411_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i411
                    }

                    // ACT 410
                    val _r410 = _memo.results.peek()
                    if (_r410 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r410.startIndex, _r410.nextIndex, _memo.input, _Thunk({ Token.CATCH }, _r410), true))
                    }

                    _label = 55
                }
                // OR 55
                55 -> {
                    // OR shortcut 54
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i54
                    } else {
                        _label = 54
                        continue
                    }

                    // AND 416
                    _start_i416 = _index.element

                    // LITERAL "finally"
                    _ParseLiteralString(_memo, _index, "finally")

                    // AND shortcut 416
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 416
                        continue
                    }

                    // NOT 418
                    _start_i418 = _index.element

                    // CALLORVAR identifierPart
                    var _r419: _KotlinLexer_Item? = null
                    _r419 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r419 != null) _index.element = _r419.nextIndex

                    // NOT 418
                    val _r418 = _memo.results.pop()
                    _memo.results.push(if (_r418 == null) _KotlinLexer_Item(_start_i418, _memo.input) else null)
                    _index.element = _start_i418
                    _label = 416
                }
                // AND 416
                416 -> {
                    val _r416_2 = _memo.results.pop()
                    val _r416_1 = _memo.results.pop()

                    if (_r416_1 != null && _r416_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i416, _index.element, _memo.input, (_r416_1.results + _r416_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i416
                    }

                    // ACT 415
                    val _r415 = _memo.results.peek()
                    if (_r415 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r415.startIndex, _r415.nextIndex, _memo.input, _Thunk({ Token.FINALLY }, _r415), true))
                    }

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

                    // AND 421
                    _start_i421 = _index.element

                    // LITERAL "for"
                    _ParseLiteralString(_memo, _index, "for")

                    // AND shortcut 421
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 421
                        continue
                    }

                    // NOT 423
                    _start_i423 = _index.element

                    // CALLORVAR identifierPart
                    var _r424: _KotlinLexer_Item? = null
                    _r424 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r424 != null) _index.element = _r424.nextIndex

                    // NOT 423
                    val _r423 = _memo.results.pop()
                    _memo.results.push(if (_r423 == null) _KotlinLexer_Item(_start_i423, _memo.input) else null)
                    _index.element = _start_i423
                    _label = 421
                }
                // AND 421
                421 -> {
                    val _r421_2 = _memo.results.pop()
                    val _r421_1 = _memo.results.pop()

                    if (_r421_1 != null && _r421_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i421, _index.element, _memo.input, (_r421_1.results + _r421_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i421
                    }

                    // ACT 420
                    val _r420 = _memo.results.peek()
                    if (_r420 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r420.startIndex, _r420.nextIndex, _memo.input, _Thunk({ Token.FOR }, _r420), true))
                    }

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

                    // AND 426
                    _start_i426 = _index.element

                    // LITERAL "do"
                    _ParseLiteralString(_memo, _index, "do")

                    // AND shortcut 426
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 426
                        continue
                    }

                    // NOT 428
                    _start_i428 = _index.element

                    // CALLORVAR identifierPart
                    var _r429: _KotlinLexer_Item? = null
                    _r429 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r429 != null) _index.element = _r429.nextIndex

                    // NOT 428
                    val _r428 = _memo.results.pop()
                    _memo.results.push(if (_r428 == null) _KotlinLexer_Item(_start_i428, _memo.input) else null)
                    _index.element = _start_i428
                    _label = 426
                }
                // AND 426
                426 -> {
                    val _r426_2 = _memo.results.pop()
                    val _r426_1 = _memo.results.pop()

                    if (_r426_1 != null && _r426_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i426, _index.element, _memo.input, (_r426_1.results + _r426_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i426
                    }

                    // ACT 425
                    val _r425 = _memo.results.peek()
                    if (_r425 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r425.startIndex, _r425.nextIndex, _memo.input, _Thunk({ Token.DO }, _r425), true))
                    }

                    _label = 52
                }
                // OR 52
                52 -> {
                    // OR shortcut 51
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i51
                    } else {
                        _label = 51
                        continue
                    }

                    // AND 431
                    _start_i431 = _index.element

                    // LITERAL "while"
                    _ParseLiteralString(_memo, _index, "while")

                    // AND shortcut 431
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 431
                        continue
                    }

                    // NOT 433
                    _start_i433 = _index.element

                    // CALLORVAR identifierPart
                    var _r434: _KotlinLexer_Item? = null
                    _r434 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r434 != null) _index.element = _r434.nextIndex

                    // NOT 433
                    val _r433 = _memo.results.pop()
                    _memo.results.push(if (_r433 == null) _KotlinLexer_Item(_start_i433, _memo.input) else null)
                    _index.element = _start_i433
                    _label = 431
                }
                // AND 431
                431 -> {
                    val _r431_2 = _memo.results.pop()
                    val _r431_1 = _memo.results.pop()

                    if (_r431_1 != null && _r431_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i431, _index.element, _memo.input, (_r431_1.results + _r431_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i431
                    }

                    // ACT 430
                    val _r430 = _memo.results.peek()
                    if (_r430 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r430.startIndex, _r430.nextIndex, _memo.input, _Thunk({ Token.WHILE }, _r430), true))
                    }

                    _label = 51
                }
                // OR 51
                51 -> {
                    // OR shortcut 50
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i50
                    } else {
                        _label = 50
                        continue
                    }

                    // AND 436
                    _start_i436 = _index.element

                    // LITERAL "throw"
                    _ParseLiteralString(_memo, _index, "throw")

                    // AND shortcut 436
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 436
                        continue
                    }

                    // NOT 438
                    _start_i438 = _index.element

                    // CALLORVAR identifierPart
                    var _r439: _KotlinLexer_Item? = null
                    _r439 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r439 != null) _index.element = _r439.nextIndex

                    // NOT 438
                    val _r438 = _memo.results.pop()
                    _memo.results.push(if (_r438 == null) _KotlinLexer_Item(_start_i438, _memo.input) else null)
                    _index.element = _start_i438
                    _label = 436
                }
                // AND 436
                436 -> {
                    val _r436_2 = _memo.results.pop()
                    val _r436_1 = _memo.results.pop()

                    if (_r436_1 != null && _r436_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i436, _index.element, _memo.input, (_r436_1.results + _r436_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i436
                    }

                    // ACT 435
                    val _r435 = _memo.results.peek()
                    if (_r435 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r435.startIndex, _r435.nextIndex, _memo.input, _Thunk({ Token.THROW }, _r435), true))
                    }

                    _label = 50
                }
                // OR 50
                50 -> {
                    // OR shortcut 49
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i49
                    } else {
                        _label = 49
                        continue
                    }

                    // AND 441
                    _start_i441 = _index.element

                    // LITERAL "return"
                    _ParseLiteralString(_memo, _index, "return")

                    // AND shortcut 441
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 441
                        continue
                    }

                    // NOT 443
                    _start_i443 = _index.element

                    // CALLORVAR identifierPart
                    var _r444: _KotlinLexer_Item? = null
                    _r444 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r444 != null) _index.element = _r444.nextIndex

                    // NOT 443
                    val _r443 = _memo.results.pop()
                    _memo.results.push(if (_r443 == null) _KotlinLexer_Item(_start_i443, _memo.input) else null)
                    _index.element = _start_i443
                    _label = 441
                }
                // AND 441
                441 -> {
                    val _r441_2 = _memo.results.pop()
                    val _r441_1 = _memo.results.pop()

                    if (_r441_1 != null && _r441_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i441, _index.element, _memo.input, (_r441_1.results + _r441_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i441
                    }

                    // ACT 440
                    val _r440 = _memo.results.peek()
                    if (_r440 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r440.startIndex, _r440.nextIndex, _memo.input, _Thunk({ Token.RETURN }, _r440), true))
                    }

                    _label = 49
                }
                // OR 49
                49 -> {
                    // OR shortcut 48
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i48
                    } else {
                        _label = 48
                        continue
                    }

                    // AND 446
                    _start_i446 = _index.element

                    // LITERAL "continue"
                    _ParseLiteralString(_memo, _index, "continue")

                    // AND shortcut 446
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 446
                        continue
                    }

                    // NOT 448
                    _start_i448 = _index.element

                    // CALLORVAR identifierPart
                    var _r449: _KotlinLexer_Item? = null
                    _r449 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r449 != null) _index.element = _r449.nextIndex

                    // NOT 448
                    val _r448 = _memo.results.pop()
                    _memo.results.push(if (_r448 == null) _KotlinLexer_Item(_start_i448, _memo.input) else null)
                    _index.element = _start_i448
                    _label = 446
                }
                // AND 446
                446 -> {
                    val _r446_2 = _memo.results.pop()
                    val _r446_1 = _memo.results.pop()

                    if (_r446_1 != null && _r446_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i446, _index.element, _memo.input, (_r446_1.results + _r446_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i446
                    }

                    // ACT 445
                    val _r445 = _memo.results.peek()
                    if (_r445 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r445.startIndex, _r445.nextIndex, _memo.input, _Thunk({ Token.CONTINUE }, _r445), true))
                    }

                    _label = 48
                }
                // OR 48
                48 -> {
                    // OR shortcut 47
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i47
                    } else {
                        _label = 47
                        continue
                    }

                    // AND 451
                    _start_i451 = _index.element

                    // LITERAL "break"
                    _ParseLiteralString(_memo, _index, "break")

                    // AND shortcut 451
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 451
                        continue
                    }

                    // NOT 453
                    _start_i453 = _index.element

                    // CALLORVAR identifierPart
                    var _r454: _KotlinLexer_Item? = null
                    _r454 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r454 != null) _index.element = _r454.nextIndex

                    // NOT 453
                    val _r453 = _memo.results.pop()
                    _memo.results.push(if (_r453 == null) _KotlinLexer_Item(_start_i453, _memo.input) else null)
                    _index.element = _start_i453
                    _label = 451
                }
                // AND 451
                451 -> {
                    val _r451_2 = _memo.results.pop()
                    val _r451_1 = _memo.results.pop()

                    if (_r451_1 != null && _r451_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i451, _index.element, _memo.input, (_r451_1.results + _r451_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i451
                    }

                    // ACT 450
                    val _r450 = _memo.results.peek()
                    if (_r450 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r450.startIndex, _r450.nextIndex, _memo.input, _Thunk({ Token.BREAK }, _r450), true))
                    }

                    _label = 47
                }
                // OR 47
                47 -> {
                    // OR shortcut 46
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i46
                    } else {
                        _label = 46
                        continue
                    }

                    // AND 456
                    _start_i456 = _index.element

                    // LITERAL "as"
                    _ParseLiteralString(_memo, _index, "as")

                    // AND shortcut 456
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 456
                        continue
                    }

                    // NOT 458
                    _start_i458 = _index.element

                    // CALLORVAR identifierPart
                    var _r459: _KotlinLexer_Item? = null
                    _r459 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r459 != null) _index.element = _r459.nextIndex

                    // NOT 458
                    val _r458 = _memo.results.pop()
                    _memo.results.push(if (_r458 == null) _KotlinLexer_Item(_start_i458, _memo.input) else null)
                    _index.element = _start_i458
                    _label = 456
                }
                // AND 456
                456 -> {
                    val _r456_2 = _memo.results.pop()
                    val _r456_1 = _memo.results.pop()

                    if (_r456_1 != null && _r456_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i456, _index.element, _memo.input, (_r456_1.results + _r456_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i456
                    }

                    // ACT 455
                    val _r455 = _memo.results.peek()
                    if (_r455 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r455.startIndex, _r455.nextIndex, _memo.input, _Thunk({ Token.AS }, _r455), true))
                    }

                    _label = 46
                }
                // OR 46
                46 -> {
                    // OR shortcut 45
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i45
                    } else {
                        _label = 45
                        continue
                    }

                    // AND 461
                    _start_i461 = _index.element

                    // LITERAL "is"
                    _ParseLiteralString(_memo, _index, "is")

                    // AND shortcut 461
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 461
                        continue
                    }

                    // NOT 463
                    _start_i463 = _index.element

                    // CALLORVAR identifierPart
                    var _r464: _KotlinLexer_Item? = null
                    _r464 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r464 != null) _index.element = _r464.nextIndex

                    // NOT 463
                    val _r463 = _memo.results.pop()
                    _memo.results.push(if (_r463 == null) _KotlinLexer_Item(_start_i463, _memo.input) else null)
                    _index.element = _start_i463
                    _label = 461
                }
                // AND 461
                461 -> {
                    val _r461_2 = _memo.results.pop()
                    val _r461_1 = _memo.results.pop()

                    if (_r461_1 != null && _r461_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i461, _index.element, _memo.input, (_r461_1.results + _r461_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i461
                    }

                    // ACT 460
                    val _r460 = _memo.results.peek()
                    if (_r460 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r460.startIndex, _r460.nextIndex, _memo.input, _Thunk({ Token.IS }, _r460), true))
                    }

                    _label = 45
                }
                // OR 45
                45 -> {
                    // OR shortcut 44
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i44
                    } else {
                        _label = 44
                        continue
                    }

                    // AND 466
                    _start_i466 = _index.element

                    // LITERAL "in"
                    _ParseLiteralString(_memo, _index, "in")

                    // AND shortcut 466
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 466
                        continue
                    }

                    // NOT 468
                    _start_i468 = _index.element

                    // CALLORVAR identifierPart
                    var _r469: _KotlinLexer_Item? = null
                    _r469 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r469 != null) _index.element = _r469.nextIndex

                    // NOT 468
                    val _r468 = _memo.results.pop()
                    _memo.results.push(if (_r468 == null) _KotlinLexer_Item(_start_i468, _memo.input) else null)
                    _index.element = _start_i468
                    _label = 466
                }
                // AND 466
                466 -> {
                    val _r466_2 = _memo.results.pop()
                    val _r466_1 = _memo.results.pop()

                    if (_r466_1 != null && _r466_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i466, _index.element, _memo.input, (_r466_1.results + _r466_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i466
                    }

                    // ACT 465
                    val _r465 = _memo.results.peek()
                    if (_r465 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r465.startIndex, _r465.nextIndex, _memo.input, _Thunk({ Token.IN }, _r465), true))
                    }

                    _label = 44
                }
                // OR 44
                44 -> {
                    // OR shortcut 43
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i43
                    } else {
                        _label = 43
                        continue
                    }

                    // AND 471
                    _start_i471 = _index.element

                    // LITERAL "!is"
                    _ParseLiteralString(_memo, _index, "!is")

                    // AND shortcut 471
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 471
                        continue
                    }

                    // NOT 473
                    _start_i473 = _index.element

                    // CALLORVAR identifierPart
                    var _r474: _KotlinLexer_Item? = null
                    _r474 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r474 != null) _index.element = _r474.nextIndex

                    // NOT 473
                    val _r473 = _memo.results.pop()
                    _memo.results.push(if (_r473 == null) _KotlinLexer_Item(_start_i473, _memo.input) else null)
                    _index.element = _start_i473
                    _label = 471
                }
                // AND 471
                471 -> {
                    val _r471_2 = _memo.results.pop()
                    val _r471_1 = _memo.results.pop()

                    if (_r471_1 != null && _r471_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i471, _index.element, _memo.input, (_r471_1.results + _r471_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i471
                    }

                    // ACT 470
                    val _r470 = _memo.results.peek()
                    if (_r470 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r470.startIndex, _r470.nextIndex, _memo.input, _Thunk({ Token.NOT_IS }, _r470), true))
                    }

                    _label = 43
                }
                // OR 43
                43 -> {
                    // OR shortcut 42
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i42
                    } else {
                        _label = 42
                        continue
                    }

                    // AND 476
                    _start_i476 = _index.element

                    // LITERAL "!in"
                    _ParseLiteralString(_memo, _index, "!in")

                    // AND shortcut 476
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 476
                        continue
                    }

                    // NOT 478
                    _start_i478 = _index.element

                    // CALLORVAR identifierPart
                    var _r479: _KotlinLexer_Item? = null
                    _r479 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r479 != null) _index.element = _r479.nextIndex

                    // NOT 478
                    val _r478 = _memo.results.pop()
                    _memo.results.push(if (_r478 == null) _KotlinLexer_Item(_start_i478, _memo.input) else null)
                    _index.element = _start_i478
                    _label = 476
                }
                // AND 476
                476 -> {
                    val _r476_2 = _memo.results.pop()
                    val _r476_1 = _memo.results.pop()

                    if (_r476_1 != null && _r476_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i476, _index.element, _memo.input, (_r476_1.results + _r476_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i476
                    }

                    // ACT 475
                    val _r475 = _memo.results.peek()
                    if (_r475 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r475.startIndex, _r475.nextIndex, _memo.input, _Thunk({ Token.NOT_IN }, _r475), true))
                    }

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

                    // AND 481
                    _start_i481 = _index.element

                    // LITERAL "out"
                    _ParseLiteralString(_memo, _index, "out")

                    // AND shortcut 481
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 481
                        continue
                    }

                    // NOT 483
                    _start_i483 = _index.element

                    // CALLORVAR identifierPart
                    var _r484: _KotlinLexer_Item? = null
                    _r484 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r484 != null) _index.element = _r484.nextIndex

                    // NOT 483
                    val _r483 = _memo.results.pop()
                    _memo.results.push(if (_r483 == null) _KotlinLexer_Item(_start_i483, _memo.input) else null)
                    _index.element = _start_i483
                    _label = 481
                }
                // AND 481
                481 -> {
                    val _r481_2 = _memo.results.pop()
                    val _r481_1 = _memo.results.pop()

                    if (_r481_1 != null && _r481_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i481, _index.element, _memo.input, (_r481_1.results + _r481_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i481
                    }

                    // ACT 480
                    val _r480 = _memo.results.peek()
                    if (_r480 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r480.startIndex, _r480.nextIndex, _memo.input, _Thunk({ Token.OUT }, _r480), true))
                    }

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

                    // AND 486
                    _start_i486 = _index.element

                    // LITERAL "field"
                    _ParseLiteralString(_memo, _index, "field")

                    // AND shortcut 486
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 486
                        continue
                    }

                    // NOT 488
                    _start_i488 = _index.element

                    // CALLORVAR identifierPart
                    var _r489: _KotlinLexer_Item? = null
                    _r489 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r489 != null) _index.element = _r489.nextIndex

                    // NOT 488
                    val _r488 = _memo.results.pop()
                    _memo.results.push(if (_r488 == null) _KotlinLexer_Item(_start_i488, _memo.input) else null)
                    _index.element = _start_i488
                    _label = 486
                }
                // AND 486
                486 -> {
                    val _r486_2 = _memo.results.pop()
                    val _r486_1 = _memo.results.pop()

                    if (_r486_1 != null && _r486_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i486, _index.element, _memo.input, (_r486_1.results + _r486_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i486
                    }

                    // ACT 485
                    val _r485 = _memo.results.peek()
                    if (_r485 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r485.startIndex, _r485.nextIndex, _memo.input, _Thunk({ Token.FIELD }, _r485), true))
                    }

                    _label = 40
                }
                // OR 40
                40 -> {
                    // OR shortcut 39
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i39
                    } else {
                        _label = 39
                        continue
                    }

                    // AND 491
                    _start_i491 = _index.element

                    // LITERAL "property"
                    _ParseLiteralString(_memo, _index, "property")

                    // AND shortcut 491
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 491
                        continue
                    }

                    // NOT 493
                    _start_i493 = _index.element

                    // CALLORVAR identifierPart
                    var _r494: _KotlinLexer_Item? = null
                    _r494 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r494 != null) _index.element = _r494.nextIndex

                    // NOT 493
                    val _r493 = _memo.results.pop()
                    _memo.results.push(if (_r493 == null) _KotlinLexer_Item(_start_i493, _memo.input) else null)
                    _index.element = _start_i493
                    _label = 491
                }
                // AND 491
                491 -> {
                    val _r491_2 = _memo.results.pop()
                    val _r491_1 = _memo.results.pop()

                    if (_r491_1 != null && _r491_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i491, _index.element, _memo.input, (_r491_1.results + _r491_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i491
                    }

                    // ACT 490
                    val _r490 = _memo.results.peek()
                    if (_r490 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r490.startIndex, _r490.nextIndex, _memo.input, _Thunk({ Token.PROPERTY }, _r490), true))
                    }

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

                    // AND 496
                    _start_i496 = _index.element

                    // LITERAL "get"
                    _ParseLiteralString(_memo, _index, "get")

                    // AND shortcut 496
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 496
                        continue
                    }

                    // NOT 498
                    _start_i498 = _index.element

                    // CALLORVAR identifierPart
                    var _r499: _KotlinLexer_Item? = null
                    _r499 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r499 != null) _index.element = _r499.nextIndex

                    // NOT 498
                    val _r498 = _memo.results.pop()
                    _memo.results.push(if (_r498 == null) _KotlinLexer_Item(_start_i498, _memo.input) else null)
                    _index.element = _start_i498
                    _label = 496
                }
                // AND 496
                496 -> {
                    val _r496_2 = _memo.results.pop()
                    val _r496_1 = _memo.results.pop()

                    if (_r496_1 != null && _r496_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i496, _index.element, _memo.input, (_r496_1.results + _r496_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i496
                    }

                    // ACT 495
                    val _r495 = _memo.results.peek()
                    if (_r495 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r495.startIndex, _r495.nextIndex, _memo.input, _Thunk({ Token.GET }, _r495), true))
                    }

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

                    // AND 501
                    _start_i501 = _index.element

                    // LITERAL "set"
                    _ParseLiteralString(_memo, _index, "set")

                    // AND shortcut 501
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 501
                        continue
                    }

                    // NOT 503
                    _start_i503 = _index.element

                    // CALLORVAR identifierPart
                    var _r504: _KotlinLexer_Item? = null
                    _r504 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r504 != null) _index.element = _r504.nextIndex

                    // NOT 503
                    val _r503 = _memo.results.pop()
                    _memo.results.push(if (_r503 == null) _KotlinLexer_Item(_start_i503, _memo.input) else null)
                    _index.element = _start_i503
                    _label = 501
                }
                // AND 501
                501 -> {
                    val _r501_2 = _memo.results.pop()
                    val _r501_1 = _memo.results.pop()

                    if (_r501_1 != null && _r501_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i501, _index.element, _memo.input, (_r501_1.results + _r501_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i501
                    }

                    // ACT 500
                    val _r500 = _memo.results.peek()
                    if (_r500 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r500.startIndex, _r500.nextIndex, _memo.input, _Thunk({ Token.SET }, _r500), true))
                    }

                    _label = 37
                }
                // OR 37
                37 -> {
                    // OR shortcut 36
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i36
                    } else {
                        _label = 36
                        continue
                    }

                    // AND 506
                    _start_i506 = _index.element

                    // LITERAL "receiver"
                    _ParseLiteralString(_memo, _index, "receiver")

                    // AND shortcut 506
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 506
                        continue
                    }

                    // NOT 508
                    _start_i508 = _index.element

                    // CALLORVAR identifierPart
                    var _r509: _KotlinLexer_Item? = null
                    _r509 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r509 != null) _index.element = _r509.nextIndex

                    // NOT 508
                    val _r508 = _memo.results.pop()
                    _memo.results.push(if (_r508 == null) _KotlinLexer_Item(_start_i508, _memo.input) else null)
                    _index.element = _start_i508
                    _label = 506
                }
                // AND 506
                506 -> {
                    val _r506_2 = _memo.results.pop()
                    val _r506_1 = _memo.results.pop()

                    if (_r506_1 != null && _r506_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i506, _index.element, _memo.input, (_r506_1.results + _r506_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i506
                    }

                    // ACT 505
                    val _r505 = _memo.results.peek()
                    if (_r505 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r505.startIndex, _r505.nextIndex, _memo.input, _Thunk({ Token.RECEIVER }, _r505), true))
                    }

                    _label = 36
                }
                // OR 36
                36 -> {
                    // OR shortcut 35
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i35
                    } else {
                        _label = 35
                        continue
                    }

                    // AND 511
                    _start_i511 = _index.element

                    // LITERAL "param"
                    _ParseLiteralString(_memo, _index, "param")

                    // AND shortcut 511
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 511
                        continue
                    }

                    // NOT 513
                    _start_i513 = _index.element

                    // CALLORVAR identifierPart
                    var _r514: _KotlinLexer_Item? = null
                    _r514 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r514 != null) _index.element = _r514.nextIndex

                    // NOT 513
                    val _r513 = _memo.results.pop()
                    _memo.results.push(if (_r513 == null) _KotlinLexer_Item(_start_i513, _memo.input) else null)
                    _index.element = _start_i513
                    _label = 511
                }
                // AND 511
                511 -> {
                    val _r511_2 = _memo.results.pop()
                    val _r511_1 = _memo.results.pop()

                    if (_r511_1 != null && _r511_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i511, _index.element, _memo.input, (_r511_1.results + _r511_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i511
                    }

                    // ACT 510
                    val _r510 = _memo.results.peek()
                    if (_r510 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r510.startIndex, _r510.nextIndex, _memo.input, _Thunk({ Token.PARAM }, _r510), true))
                    }

                    _label = 35
                }
                // OR 35
                35 -> {
                    // OR shortcut 34
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i34
                    } else {
                        _label = 34
                        continue
                    }

                    // AND 516
                    _start_i516 = _index.element

                    // LITERAL "setparam"
                    _ParseLiteralString(_memo, _index, "setparam")

                    // AND shortcut 516
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 516
                        continue
                    }

                    // NOT 518
                    _start_i518 = _index.element

                    // CALLORVAR identifierPart
                    var _r519: _KotlinLexer_Item? = null
                    _r519 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r519 != null) _index.element = _r519.nextIndex

                    // NOT 518
                    val _r518 = _memo.results.pop()
                    _memo.results.push(if (_r518 == null) _KotlinLexer_Item(_start_i518, _memo.input) else null)
                    _index.element = _start_i518
                    _label = 516
                }
                // AND 516
                516 -> {
                    val _r516_2 = _memo.results.pop()
                    val _r516_1 = _memo.results.pop()

                    if (_r516_1 != null && _r516_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i516, _index.element, _memo.input, (_r516_1.results + _r516_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i516
                    }

                    // ACT 515
                    val _r515 = _memo.results.peek()
                    if (_r515 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r515.startIndex, _r515.nextIndex, _memo.input, _Thunk({ Token.SETPARAM }, _r515), true))
                    }

                    _label = 34
                }
                // OR 34
                34 -> {
                    // OR shortcut 33
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i33
                    } else {
                        _label = 33
                        continue
                    }

                    // AND 521
                    _start_i521 = _index.element

                    // LITERAL "delegate"
                    _ParseLiteralString(_memo, _index, "delegate")

                    // AND shortcut 521
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 521
                        continue
                    }

                    // NOT 523
                    _start_i523 = _index.element

                    // CALLORVAR identifierPart
                    var _r524: _KotlinLexer_Item? = null
                    _r524 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r524 != null) _index.element = _r524.nextIndex

                    // NOT 523
                    val _r523 = _memo.results.pop()
                    _memo.results.push(if (_r523 == null) _KotlinLexer_Item(_start_i523, _memo.input) else null)
                    _index.element = _start_i523
                    _label = 521
                }
                // AND 521
                521 -> {
                    val _r521_2 = _memo.results.pop()
                    val _r521_1 = _memo.results.pop()

                    if (_r521_1 != null && _r521_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i521, _index.element, _memo.input, (_r521_1.results + _r521_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i521
                    }

                    // ACT 520
                    val _r520 = _memo.results.peek()
                    if (_r520 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r520.startIndex, _r520.nextIndex, _memo.input, _Thunk({ Token.DELEGATE }, _r520), true))
                    }

                    _label = 33
                }
                // OR 33
                33 -> {
                    // OR shortcut 32
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i32
                    } else {
                        _label = 32
                        continue
                    }

                    // AND 526
                    _start_i526 = _index.element

                    // LITERAL "dynamic"
                    _ParseLiteralString(_memo, _index, "dynamic")

                    // AND shortcut 526
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 526
                        continue
                    }

                    // NOT 528
                    _start_i528 = _index.element

                    // CALLORVAR identifierPart
                    var _r529: _KotlinLexer_Item? = null
                    _r529 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r529 != null) _index.element = _r529.nextIndex

                    // NOT 528
                    val _r528 = _memo.results.pop()
                    _memo.results.push(if (_r528 == null) _KotlinLexer_Item(_start_i528, _memo.input) else null)
                    _index.element = _start_i528
                    _label = 526
                }
                // AND 526
                526 -> {
                    val _r526_2 = _memo.results.pop()
                    val _r526_1 = _memo.results.pop()

                    if (_r526_1 != null && _r526_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i526, _index.element, _memo.input, (_r526_1.results + _r526_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i526
                    }

                    // ACT 525
                    val _r525 = _memo.results.peek()
                    if (_r525 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r525.startIndex, _r525.nextIndex, _memo.input, _Thunk({ Token.DYNAMIC }, _r525), true))
                    }

                    _label = 32
                }
                // OR 32
                32 -> {
                    // OR shortcut 31
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i31
                    } else {
                        _label = 31
                        continue
                    }

                    // AND 531
                    _start_i531 = _index.element

                    // LITERAL "public"
                    _ParseLiteralString(_memo, _index, "public")

                    // AND shortcut 531
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 531
                        continue
                    }

                    // NOT 533
                    _start_i533 = _index.element

                    // CALLORVAR identifierPart
                    var _r534: _KotlinLexer_Item? = null
                    _r534 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r534 != null) _index.element = _r534.nextIndex

                    // NOT 533
                    val _r533 = _memo.results.pop()
                    _memo.results.push(if (_r533 == null) _KotlinLexer_Item(_start_i533, _memo.input) else null)
                    _index.element = _start_i533
                    _label = 531
                }
                // AND 531
                531 -> {
                    val _r531_2 = _memo.results.pop()
                    val _r531_1 = _memo.results.pop()

                    if (_r531_1 != null && _r531_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i531, _index.element, _memo.input, (_r531_1.results + _r531_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i531
                    }

                    // ACT 530
                    val _r530 = _memo.results.peek()
                    if (_r530 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r530.startIndex, _r530.nextIndex, _memo.input, _Thunk({ Token.PUBLIC }, _r530), true))
                    }

                    _label = 31
                }
                // OR 31
                31 -> {
                    // OR shortcut 30
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i30
                    } else {
                        _label = 30
                        continue
                    }

                    // AND 536
                    _start_i536 = _index.element

                    // LITERAL "private"
                    _ParseLiteralString(_memo, _index, "private")

                    // AND shortcut 536
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 536
                        continue
                    }

                    // NOT 538
                    _start_i538 = _index.element

                    // CALLORVAR identifierPart
                    var _r539: _KotlinLexer_Item? = null
                    _r539 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r539 != null) _index.element = _r539.nextIndex

                    // NOT 538
                    val _r538 = _memo.results.pop()
                    _memo.results.push(if (_r538 == null) _KotlinLexer_Item(_start_i538, _memo.input) else null)
                    _index.element = _start_i538
                    _label = 536
                }
                // AND 536
                536 -> {
                    val _r536_2 = _memo.results.pop()
                    val _r536_1 = _memo.results.pop()

                    if (_r536_1 != null && _r536_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i536, _index.element, _memo.input, (_r536_1.results + _r536_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i536
                    }

                    // ACT 535
                    val _r535 = _memo.results.peek()
                    if (_r535 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r535.startIndex, _r535.nextIndex, _memo.input, _Thunk({ Token.PRIVATE }, _r535), true))
                    }

                    _label = 30
                }
                // OR 30
                30 -> {
                    // OR shortcut 29
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i29
                    } else {
                        _label = 29
                        continue
                    }

                    // AND 541
                    _start_i541 = _index.element

                    // LITERAL "protected"
                    _ParseLiteralString(_memo, _index, "protected")

                    // AND shortcut 541
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 541
                        continue
                    }

                    // NOT 543
                    _start_i543 = _index.element

                    // CALLORVAR identifierPart
                    var _r544: _KotlinLexer_Item? = null
                    _r544 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r544 != null) _index.element = _r544.nextIndex

                    // NOT 543
                    val _r543 = _memo.results.pop()
                    _memo.results.push(if (_r543 == null) _KotlinLexer_Item(_start_i543, _memo.input) else null)
                    _index.element = _start_i543
                    _label = 541
                }
                // AND 541
                541 -> {
                    val _r541_2 = _memo.results.pop()
                    val _r541_1 = _memo.results.pop()

                    if (_r541_1 != null && _r541_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i541, _index.element, _memo.input, (_r541_1.results + _r541_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i541
                    }

                    // ACT 540
                    val _r540 = _memo.results.peek()
                    if (_r540 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r540.startIndex, _r540.nextIndex, _memo.input, _Thunk({ Token.PROTECTED }, _r540), true))
                    }

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

                    // AND 546
                    _start_i546 = _index.element

                    // LITERAL "internal"
                    _ParseLiteralString(_memo, _index, "internal")

                    // AND shortcut 546
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 546
                        continue
                    }

                    // NOT 548
                    _start_i548 = _index.element

                    // CALLORVAR identifierPart
                    var _r549: _KotlinLexer_Item? = null
                    _r549 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r549 != null) _index.element = _r549.nextIndex

                    // NOT 548
                    val _r548 = _memo.results.pop()
                    _memo.results.push(if (_r548 == null) _KotlinLexer_Item(_start_i548, _memo.input) else null)
                    _index.element = _start_i548
                    _label = 546
                }
                // AND 546
                546 -> {
                    val _r546_2 = _memo.results.pop()
                    val _r546_1 = _memo.results.pop()

                    if (_r546_1 != null && _r546_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i546, _index.element, _memo.input, (_r546_1.results + _r546_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i546
                    }

                    // ACT 545
                    val _r545 = _memo.results.peek()
                    if (_r545 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r545.startIndex, _r545.nextIndex, _memo.input, _Thunk({ Token.INTERNAL }, _r545), true))
                    }

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

                    // AND 551
                    _start_i551 = _index.element

                    // LITERAL "enum"
                    _ParseLiteralString(_memo, _index, "enum")

                    // AND shortcut 551
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 551
                        continue
                    }

                    // NOT 553
                    _start_i553 = _index.element

                    // CALLORVAR identifierPart
                    var _r554: _KotlinLexer_Item? = null
                    _r554 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r554 != null) _index.element = _r554.nextIndex

                    // NOT 553
                    val _r553 = _memo.results.pop()
                    _memo.results.push(if (_r553 == null) _KotlinLexer_Item(_start_i553, _memo.input) else null)
                    _index.element = _start_i553
                    _label = 551
                }
                // AND 551
                551 -> {
                    val _r551_2 = _memo.results.pop()
                    val _r551_1 = _memo.results.pop()

                    if (_r551_1 != null && _r551_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i551, _index.element, _memo.input, (_r551_1.results + _r551_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i551
                    }

                    // ACT 550
                    val _r550 = _memo.results.peek()
                    if (_r550 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r550.startIndex, _r550.nextIndex, _memo.input, _Thunk({ Token.ENUM }, _r550), true))
                    }

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

                    // AND 556
                    _start_i556 = _index.element

                    // LITERAL "sealed"
                    _ParseLiteralString(_memo, _index, "sealed")

                    // AND shortcut 556
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 556
                        continue
                    }

                    // NOT 558
                    _start_i558 = _index.element

                    // CALLORVAR identifierPart
                    var _r559: _KotlinLexer_Item? = null
                    _r559 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r559 != null) _index.element = _r559.nextIndex

                    // NOT 558
                    val _r558 = _memo.results.pop()
                    _memo.results.push(if (_r558 == null) _KotlinLexer_Item(_start_i558, _memo.input) else null)
                    _index.element = _start_i558
                    _label = 556
                }
                // AND 556
                556 -> {
                    val _r556_2 = _memo.results.pop()
                    val _r556_1 = _memo.results.pop()

                    if (_r556_1 != null && _r556_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i556, _index.element, _memo.input, (_r556_1.results + _r556_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i556
                    }

                    // ACT 555
                    val _r555 = _memo.results.peek()
                    if (_r555 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r555.startIndex, _r555.nextIndex, _memo.input, _Thunk({ Token.SEALED }, _r555), true))
                    }

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

                    // AND 561
                    _start_i561 = _index.element

                    // LITERAL "annotation"
                    _ParseLiteralString(_memo, _index, "annotation")

                    // AND shortcut 561
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 561
                        continue
                    }

                    // NOT 563
                    _start_i563 = _index.element

                    // CALLORVAR identifierPart
                    var _r564: _KotlinLexer_Item? = null
                    _r564 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r564 != null) _index.element = _r564.nextIndex

                    // NOT 563
                    val _r563 = _memo.results.pop()
                    _memo.results.push(if (_r563 == null) _KotlinLexer_Item(_start_i563, _memo.input) else null)
                    _index.element = _start_i563
                    _label = 561
                }
                // AND 561
                561 -> {
                    val _r561_2 = _memo.results.pop()
                    val _r561_1 = _memo.results.pop()

                    if (_r561_1 != null && _r561_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i561, _index.element, _memo.input, (_r561_1.results + _r561_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i561
                    }

                    // ACT 560
                    val _r560 = _memo.results.peek()
                    if (_r560 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r560.startIndex, _r560.nextIndex, _memo.input, _Thunk({ Token.ANNOTATION }, _r560), true))
                    }

                    _label = 25
                }
                // OR 25
                25 -> {
                    // OR shortcut 24
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i24
                    } else {
                        _label = 24
                        continue
                    }

                    // AND 566
                    _start_i566 = _index.element

                    // LITERAL "data"
                    _ParseLiteralString(_memo, _index, "data")

                    // AND shortcut 566
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 566
                        continue
                    }

                    // NOT 568
                    _start_i568 = _index.element

                    // CALLORVAR identifierPart
                    var _r569: _KotlinLexer_Item? = null
                    _r569 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r569 != null) _index.element = _r569.nextIndex

                    // NOT 568
                    val _r568 = _memo.results.pop()
                    _memo.results.push(if (_r568 == null) _KotlinLexer_Item(_start_i568, _memo.input) else null)
                    _index.element = _start_i568
                    _label = 566
                }
                // AND 566
                566 -> {
                    val _r566_2 = _memo.results.pop()
                    val _r566_1 = _memo.results.pop()

                    if (_r566_1 != null && _r566_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i566, _index.element, _memo.input, (_r566_1.results + _r566_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i566
                    }

                    // ACT 565
                    val _r565 = _memo.results.peek()
                    if (_r565 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r565.startIndex, _r565.nextIndex, _memo.input, _Thunk({ Token.DATA }, _r565), true))
                    }

                    _label = 24
                }
                // OR 24
                24 -> {
                    // OR shortcut 23
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i23
                    } else {
                        _label = 23
                        continue
                    }

                    // AND 571
                    _start_i571 = _index.element

                    // LITERAL "inner"
                    _ParseLiteralString(_memo, _index, "inner")

                    // AND shortcut 571
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 571
                        continue
                    }

                    // NOT 573
                    _start_i573 = _index.element

                    // CALLORVAR identifierPart
                    var _r574: _KotlinLexer_Item? = null
                    _r574 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r574 != null) _index.element = _r574.nextIndex

                    // NOT 573
                    val _r573 = _memo.results.pop()
                    _memo.results.push(if (_r573 == null) _KotlinLexer_Item(_start_i573, _memo.input) else null)
                    _index.element = _start_i573
                    _label = 571
                }
                // AND 571
                571 -> {
                    val _r571_2 = _memo.results.pop()
                    val _r571_1 = _memo.results.pop()

                    if (_r571_1 != null && _r571_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i571, _index.element, _memo.input, (_r571_1.results + _r571_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i571
                    }

                    // ACT 570
                    val _r570 = _memo.results.peek()
                    if (_r570 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r570.startIndex, _r570.nextIndex, _memo.input, _Thunk({ Token.INNER }, _r570), true))
                    }

                    _label = 23
                }
                // OR 23
                23 -> {
                    // OR shortcut 22
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i22
                    } else {
                        _label = 22
                        continue
                    }

                    // AND 576
                    _start_i576 = _index.element

                    // LITERAL "tailrec"
                    _ParseLiteralString(_memo, _index, "tailrec")

                    // AND shortcut 576
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 576
                        continue
                    }

                    // NOT 578
                    _start_i578 = _index.element

                    // CALLORVAR identifierPart
                    var _r579: _KotlinLexer_Item? = null
                    _r579 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r579 != null) _index.element = _r579.nextIndex

                    // NOT 578
                    val _r578 = _memo.results.pop()
                    _memo.results.push(if (_r578 == null) _KotlinLexer_Item(_start_i578, _memo.input) else null)
                    _index.element = _start_i578
                    _label = 576
                }
                // AND 576
                576 -> {
                    val _r576_2 = _memo.results.pop()
                    val _r576_1 = _memo.results.pop()

                    if (_r576_1 != null && _r576_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i576, _index.element, _memo.input, (_r576_1.results + _r576_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i576
                    }

                    // ACT 575
                    val _r575 = _memo.results.peek()
                    if (_r575 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r575.startIndex, _r575.nextIndex, _memo.input, _Thunk({ Token.TAILREC }, _r575), true))
                    }

                    _label = 22
                }
                // OR 22
                22 -> {
                    // OR shortcut 21
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i21
                    } else {
                        _label = 21
                        continue
                    }

                    // AND 581
                    _start_i581 = _index.element

                    // LITERAL "operator"
                    _ParseLiteralString(_memo, _index, "operator")

                    // AND shortcut 581
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 581
                        continue
                    }

                    // NOT 583
                    _start_i583 = _index.element

                    // CALLORVAR identifierPart
                    var _r584: _KotlinLexer_Item? = null
                    _r584 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r584 != null) _index.element = _r584.nextIndex

                    // NOT 583
                    val _r583 = _memo.results.pop()
                    _memo.results.push(if (_r583 == null) _KotlinLexer_Item(_start_i583, _memo.input) else null)
                    _index.element = _start_i583
                    _label = 581
                }
                // AND 581
                581 -> {
                    val _r581_2 = _memo.results.pop()
                    val _r581_1 = _memo.results.pop()

                    if (_r581_1 != null && _r581_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i581, _index.element, _memo.input, (_r581_1.results + _r581_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i581
                    }

                    // ACT 580
                    val _r580 = _memo.results.peek()
                    if (_r580 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r580.startIndex, _r580.nextIndex, _memo.input, _Thunk({ Token.OPERATOR }, _r580), true))
                    }

                    _label = 21
                }
                // OR 21
                21 -> {
                    // OR shortcut 20
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i20
                    } else {
                        _label = 20
                        continue
                    }

                    // AND 586
                    _start_i586 = _index.element

                    // LITERAL "inline"
                    _ParseLiteralString(_memo, _index, "inline")

                    // AND shortcut 586
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 586
                        continue
                    }

                    // NOT 588
                    _start_i588 = _index.element

                    // CALLORVAR identifierPart
                    var _r589: _KotlinLexer_Item? = null
                    _r589 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r589 != null) _index.element = _r589.nextIndex

                    // NOT 588
                    val _r588 = _memo.results.pop()
                    _memo.results.push(if (_r588 == null) _KotlinLexer_Item(_start_i588, _memo.input) else null)
                    _index.element = _start_i588
                    _label = 586
                }
                // AND 586
                586 -> {
                    val _r586_2 = _memo.results.pop()
                    val _r586_1 = _memo.results.pop()

                    if (_r586_1 != null && _r586_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i586, _index.element, _memo.input, (_r586_1.results + _r586_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i586
                    }

                    // ACT 585
                    val _r585 = _memo.results.peek()
                    if (_r585 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r585.startIndex, _r585.nextIndex, _memo.input, _Thunk({ Token.INLINE }, _r585), true))
                    }

                    _label = 20
                }
                // OR 20
                20 -> {
                    // OR shortcut 19
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i19
                    } else {
                        _label = 19
                        continue
                    }

                    // AND 591
                    _start_i591 = _index.element

                    // LITERAL "infix"
                    _ParseLiteralString(_memo, _index, "infix")

                    // AND shortcut 591
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 591
                        continue
                    }

                    // NOT 593
                    _start_i593 = _index.element

                    // CALLORVAR identifierPart
                    var _r594: _KotlinLexer_Item? = null
                    _r594 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r594 != null) _index.element = _r594.nextIndex

                    // NOT 593
                    val _r593 = _memo.results.pop()
                    _memo.results.push(if (_r593 == null) _KotlinLexer_Item(_start_i593, _memo.input) else null)
                    _index.element = _start_i593
                    _label = 591
                }
                // AND 591
                591 -> {
                    val _r591_2 = _memo.results.pop()
                    val _r591_1 = _memo.results.pop()

                    if (_r591_1 != null && _r591_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i591, _index.element, _memo.input, (_r591_1.results + _r591_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i591
                    }

                    // ACT 590
                    val _r590 = _memo.results.peek()
                    if (_r590 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r590.startIndex, _r590.nextIndex, _memo.input, _Thunk({ Token.INFIX }, _r590), true))
                    }

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

                    // AND 596
                    _start_i596 = _index.element

                    // LITERAL "external"
                    _ParseLiteralString(_memo, _index, "external")

                    // AND shortcut 596
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 596
                        continue
                    }

                    // NOT 598
                    _start_i598 = _index.element

                    // CALLORVAR identifierPart
                    var _r599: _KotlinLexer_Item? = null
                    _r599 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r599 != null) _index.element = _r599.nextIndex

                    // NOT 598
                    val _r598 = _memo.results.pop()
                    _memo.results.push(if (_r598 == null) _KotlinLexer_Item(_start_i598, _memo.input) else null)
                    _index.element = _start_i598
                    _label = 596
                }
                // AND 596
                596 -> {
                    val _r596_2 = _memo.results.pop()
                    val _r596_1 = _memo.results.pop()

                    if (_r596_1 != null && _r596_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i596, _index.element, _memo.input, (_r596_1.results + _r596_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i596
                    }

                    // ACT 595
                    val _r595 = _memo.results.peek()
                    if (_r595 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r595.startIndex, _r595.nextIndex, _memo.input, _Thunk({ Token.EXTERNAL }, _r595), true))
                    }

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

                    // AND 601
                    _start_i601 = _index.element

                    // LITERAL "suspend"
                    _ParseLiteralString(_memo, _index, "suspend")

                    // AND shortcut 601
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 601
                        continue
                    }

                    // NOT 603
                    _start_i603 = _index.element

                    // CALLORVAR identifierPart
                    var _r604: _KotlinLexer_Item? = null
                    _r604 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r604 != null) _index.element = _r604.nextIndex

                    // NOT 603
                    val _r603 = _memo.results.pop()
                    _memo.results.push(if (_r603 == null) _KotlinLexer_Item(_start_i603, _memo.input) else null)
                    _index.element = _start_i603
                    _label = 601
                }
                // AND 601
                601 -> {
                    val _r601_2 = _memo.results.pop()
                    val _r601_1 = _memo.results.pop()

                    if (_r601_1 != null && _r601_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i601, _index.element, _memo.input, (_r601_1.results + _r601_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i601
                    }

                    // ACT 600
                    val _r600 = _memo.results.peek()
                    if (_r600 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r600.startIndex, _r600.nextIndex, _memo.input, _Thunk({ Token.SUSPEND }, _r600), true))
                    }

                    _label = 17
                }
                // OR 17
                17 -> {
                    // OR shortcut 16
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i16
                    } else {
                        _label = 16
                        continue
                    }

                    // AND 606
                    _start_i606 = _index.element

                    // LITERAL "override"
                    _ParseLiteralString(_memo, _index, "override")

                    // AND shortcut 606
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 606
                        continue
                    }

                    // NOT 608
                    _start_i608 = _index.element

                    // CALLORVAR identifierPart
                    var _r609: _KotlinLexer_Item? = null
                    _r609 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r609 != null) _index.element = _r609.nextIndex

                    // NOT 608
                    val _r608 = _memo.results.pop()
                    _memo.results.push(if (_r608 == null) _KotlinLexer_Item(_start_i608, _memo.input) else null)
                    _index.element = _start_i608
                    _label = 606
                }
                // AND 606
                606 -> {
                    val _r606_2 = _memo.results.pop()
                    val _r606_1 = _memo.results.pop()

                    if (_r606_1 != null && _r606_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i606, _index.element, _memo.input, (_r606_1.results + _r606_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i606
                    }

                    // ACT 605
                    val _r605 = _memo.results.peek()
                    if (_r605 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r605.startIndex, _r605.nextIndex, _memo.input, _Thunk({ Token.OVERRIDE }, _r605), true))
                    }

                    _label = 16
                }
                // OR 16
                16 -> {
                    // OR shortcut 15
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i15
                    } else {
                        _label = 15
                        continue
                    }

                    // AND 611
                    _start_i611 = _index.element

                    // LITERAL "abstract"
                    _ParseLiteralString(_memo, _index, "abstract")

                    // AND shortcut 611
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 611
                        continue
                    }

                    // NOT 613
                    _start_i613 = _index.element

                    // CALLORVAR identifierPart
                    var _r614: _KotlinLexer_Item? = null
                    _r614 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r614 != null) _index.element = _r614.nextIndex

                    // NOT 613
                    val _r613 = _memo.results.pop()
                    _memo.results.push(if (_r613 == null) _KotlinLexer_Item(_start_i613, _memo.input) else null)
                    _index.element = _start_i613
                    _label = 611
                }
                // AND 611
                611 -> {
                    val _r611_2 = _memo.results.pop()
                    val _r611_1 = _memo.results.pop()

                    if (_r611_1 != null && _r611_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i611, _index.element, _memo.input, (_r611_1.results + _r611_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i611
                    }

                    // ACT 610
                    val _r610 = _memo.results.peek()
                    if (_r610 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r610.startIndex, _r610.nextIndex, _memo.input, _Thunk({ Token.ABSTRACT }, _r610), true))
                    }

                    _label = 15
                }
                // OR 15
                15 -> {
                    // OR shortcut 14
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i14
                    } else {
                        _label = 14
                        continue
                    }

                    // AND 616
                    _start_i616 = _index.element

                    // LITERAL "final"
                    _ParseLiteralString(_memo, _index, "final")

                    // AND shortcut 616
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 616
                        continue
                    }

                    // NOT 618
                    _start_i618 = _index.element

                    // CALLORVAR identifierPart
                    var _r619: _KotlinLexer_Item? = null
                    _r619 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r619 != null) _index.element = _r619.nextIndex

                    // NOT 618
                    val _r618 = _memo.results.pop()
                    _memo.results.push(if (_r618 == null) _KotlinLexer_Item(_start_i618, _memo.input) else null)
                    _index.element = _start_i618
                    _label = 616
                }
                // AND 616
                616 -> {
                    val _r616_2 = _memo.results.pop()
                    val _r616_1 = _memo.results.pop()

                    if (_r616_1 != null && _r616_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i616, _index.element, _memo.input, (_r616_1.results + _r616_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i616
                    }

                    // ACT 615
                    val _r615 = _memo.results.peek()
                    if (_r615 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r615.startIndex, _r615.nextIndex, _memo.input, _Thunk({ Token.FINAL }, _r615), true))
                    }

                    _label = 14
                }
                // OR 14
                14 -> {
                    // OR shortcut 13
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i13
                    } else {
                        _label = 13
                        continue
                    }

                    // AND 621
                    _start_i621 = _index.element

                    // LITERAL "open"
                    _ParseLiteralString(_memo, _index, "open")

                    // AND shortcut 621
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 621
                        continue
                    }

                    // NOT 623
                    _start_i623 = _index.element

                    // CALLORVAR identifierPart
                    var _r624: _KotlinLexer_Item? = null
                    _r624 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r624 != null) _index.element = _r624.nextIndex

                    // NOT 623
                    val _r623 = _memo.results.pop()
                    _memo.results.push(if (_r623 == null) _KotlinLexer_Item(_start_i623, _memo.input) else null)
                    _index.element = _start_i623
                    _label = 621
                }
                // AND 621
                621 -> {
                    val _r621_2 = _memo.results.pop()
                    val _r621_1 = _memo.results.pop()

                    if (_r621_1 != null && _r621_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i621, _index.element, _memo.input, (_r621_1.results + _r621_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i621
                    }

                    // ACT 620
                    val _r620 = _memo.results.peek()
                    if (_r620 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r620.startIndex, _r620.nextIndex, _memo.input, _Thunk({ Token.OPEN }, _r620), true))
                    }

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

                    // AND 626
                    _start_i626 = _index.element

                    // LITERAL "const"
                    _ParseLiteralString(_memo, _index, "const")

                    // AND shortcut 626
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 626
                        continue
                    }

                    // NOT 628
                    _start_i628 = _index.element

                    // CALLORVAR identifierPart
                    var _r629: _KotlinLexer_Item? = null
                    _r629 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r629 != null) _index.element = _r629.nextIndex

                    // NOT 628
                    val _r628 = _memo.results.pop()
                    _memo.results.push(if (_r628 == null) _KotlinLexer_Item(_start_i628, _memo.input) else null)
                    _index.element = _start_i628
                    _label = 626
                }
                // AND 626
                626 -> {
                    val _r626_2 = _memo.results.pop()
                    val _r626_1 = _memo.results.pop()

                    if (_r626_1 != null && _r626_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i626, _index.element, _memo.input, (_r626_1.results + _r626_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i626
                    }

                    // ACT 625
                    val _r625 = _memo.results.peek()
                    if (_r625 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r625.startIndex, _r625.nextIndex, _memo.input, _Thunk({ Token.CONST }, _r625), true))
                    }

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

                    // AND 631
                    _start_i631 = _index.element

                    // LITERAL "lateinit"
                    _ParseLiteralString(_memo, _index, "lateinit")

                    // AND shortcut 631
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 631
                        continue
                    }

                    // NOT 633
                    _start_i633 = _index.element

                    // CALLORVAR identifierPart
                    var _r634: _KotlinLexer_Item? = null
                    _r634 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r634 != null) _index.element = _r634.nextIndex

                    // NOT 633
                    val _r633 = _memo.results.pop()
                    _memo.results.push(if (_r633 == null) _KotlinLexer_Item(_start_i633, _memo.input) else null)
                    _index.element = _start_i633
                    _label = 631
                }
                // AND 631
                631 -> {
                    val _r631_2 = _memo.results.pop()
                    val _r631_1 = _memo.results.pop()

                    if (_r631_1 != null && _r631_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i631, _index.element, _memo.input, (_r631_1.results + _r631_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i631
                    }

                    // ACT 630
                    val _r630 = _memo.results.peek()
                    if (_r630 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r630.startIndex, _r630.nextIndex, _memo.input, _Thunk({ Token.LATEINIT }, _r630), true))
                    }

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

                    // AND 636
                    _start_i636 = _index.element

                    // LITERAL "vararg"
                    _ParseLiteralString(_memo, _index, "vararg")

                    // AND shortcut 636
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 636
                        continue
                    }

                    // NOT 638
                    _start_i638 = _index.element

                    // CALLORVAR identifierPart
                    var _r639: _KotlinLexer_Item? = null
                    _r639 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r639 != null) _index.element = _r639.nextIndex

                    // NOT 638
                    val _r638 = _memo.results.pop()
                    _memo.results.push(if (_r638 == null) _KotlinLexer_Item(_start_i638, _memo.input) else null)
                    _index.element = _start_i638
                    _label = 636
                }
                // AND 636
                636 -> {
                    val _r636_2 = _memo.results.pop()
                    val _r636_1 = _memo.results.pop()

                    if (_r636_1 != null && _r636_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i636, _index.element, _memo.input, (_r636_1.results + _r636_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i636
                    }

                    // ACT 635
                    val _r635 = _memo.results.peek()
                    if (_r635 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r635.startIndex, _r635.nextIndex, _memo.input, _Thunk({ Token.VARARG }, _r635), true))
                    }

                    _label = 10
                }
                // OR 10
                10 -> {
                    // OR shortcut 9
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i9
                    } else {
                        _label = 9
                        continue
                    }

                    // AND 641
                    _start_i641 = _index.element

                    // LITERAL "noinline"
                    _ParseLiteralString(_memo, _index, "noinline")

                    // AND shortcut 641
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 641
                        continue
                    }

                    // NOT 643
                    _start_i643 = _index.element

                    // CALLORVAR identifierPart
                    var _r644: _KotlinLexer_Item? = null
                    _r644 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r644 != null) _index.element = _r644.nextIndex

                    // NOT 643
                    val _r643 = _memo.results.pop()
                    _memo.results.push(if (_r643 == null) _KotlinLexer_Item(_start_i643, _memo.input) else null)
                    _index.element = _start_i643
                    _label = 641
                }
                // AND 641
                641 -> {
                    val _r641_2 = _memo.results.pop()
                    val _r641_1 = _memo.results.pop()

                    if (_r641_1 != null && _r641_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i641, _index.element, _memo.input, (_r641_1.results + _r641_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i641
                    }

                    // ACT 640
                    val _r640 = _memo.results.peek()
                    if (_r640 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r640.startIndex, _r640.nextIndex, _memo.input, _Thunk({ Token.NOINLINE }, _r640), true))
                    }

                    _label = 9
                }
                // OR 9
                9 -> {
                    // OR shortcut 8
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i8
                    } else {
                        _label = 8
                        continue
                    }

                    // AND 646
                    _start_i646 = _index.element

                    // LITERAL "crossinline"
                    _ParseLiteralString(_memo, _index, "crossinline")

                    // AND shortcut 646
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 646
                        continue
                    }

                    // NOT 648
                    _start_i648 = _index.element

                    // CALLORVAR identifierPart
                    var _r649: _KotlinLexer_Item? = null
                    _r649 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r649 != null) _index.element = _r649.nextIndex

                    // NOT 648
                    val _r648 = _memo.results.pop()
                    _memo.results.push(if (_r648 == null) _KotlinLexer_Item(_start_i648, _memo.input) else null)
                    _index.element = _start_i648
                    _label = 646
                }
                // AND 646
                646 -> {
                    val _r646_2 = _memo.results.pop()
                    val _r646_1 = _memo.results.pop()

                    if (_r646_1 != null && _r646_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i646, _index.element, _memo.input, (_r646_1.results + _r646_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i646
                    }

                    // ACT 645
                    val _r645 = _memo.results.peek()
                    if (_r645 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r645.startIndex, _r645.nextIndex, _memo.input, _Thunk({ Token.CROSSINLINE }, _r645), true))
                    }

                    _label = 8
                }
                // OR 8
                8 -> {
                    // OR shortcut 7
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i7
                    } else {
                        _label = 7
                        continue
                    }

                    // AND 651
                    _start_i651 = _index.element

                    // LITERAL "reified"
                    _ParseLiteralString(_memo, _index, "reified")

                    // AND shortcut 651
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 651
                        continue
                    }

                    // NOT 653
                    _start_i653 = _index.element

                    // CALLORVAR identifierPart
                    var _r654: _KotlinLexer_Item? = null
                    _r654 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r654 != null) _index.element = _r654.nextIndex

                    // NOT 653
                    val _r653 = _memo.results.pop()
                    _memo.results.push(if (_r653 == null) _KotlinLexer_Item(_start_i653, _memo.input) else null)
                    _index.element = _start_i653
                    _label = 651
                }
                // AND 651
                651 -> {
                    val _r651_2 = _memo.results.pop()
                    val _r651_1 = _memo.results.pop()

                    if (_r651_1 != null && _r651_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i651, _index.element, _memo.input, (_r651_1.results + _r651_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i651
                    }

                    // ACT 650
                    val _r650 = _memo.results.peek()
                    if (_r650 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r650.startIndex, _r650.nextIndex, _memo.input, _Thunk({ Token.REIFIED }, _r650), true))
                    }

                    _label = 7
                }
                // OR 7
                7 -> {
                    // OR shortcut 6
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i6
                    } else {
                        _label = 6
                        continue
                    }

                    // AND 656
                    _start_i656 = _index.element

                    // LITERAL "true"
                    _ParseLiteralString(_memo, _index, "true")

                    // AND shortcut 656
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 656
                        continue
                    }

                    // NOT 658
                    _start_i658 = _index.element

                    // CALLORVAR identifierPart
                    var _r659: _KotlinLexer_Item? = null
                    _r659 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r659 != null) _index.element = _r659.nextIndex

                    // NOT 658
                    val _r658 = _memo.results.pop()
                    _memo.results.push(if (_r658 == null) _KotlinLexer_Item(_start_i658, _memo.input) else null)
                    _index.element = _start_i658
                    _label = 656
                }
                // AND 656
                656 -> {
                    val _r656_2 = _memo.results.pop()
                    val _r656_1 = _memo.results.pop()

                    if (_r656_1 != null && _r656_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i656, _index.element, _memo.input, (_r656_1.results + _r656_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i656
                    }

                    // ACT 655
                    val _r655 = _memo.results.peek()
                    if (_r655 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r655.startIndex, _r655.nextIndex, _memo.input, _Thunk({ Token.TRUE }, _r655), true))
                    }

                    _label = 6
                }
                // OR 6
                6 -> {
                    // OR shortcut 5
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i5
                    } else {
                        _label = 5
                        continue
                    }

                    // AND 661
                    _start_i661 = _index.element

                    // LITERAL "false"
                    _ParseLiteralString(_memo, _index, "false")

                    // AND shortcut 661
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 661
                        continue
                    }

                    // NOT 663
                    _start_i663 = _index.element

                    // CALLORVAR identifierPart
                    var _r664: _KotlinLexer_Item? = null
                    _r664 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r664 != null) _index.element = _r664.nextIndex

                    // NOT 663
                    val _r663 = _memo.results.pop()
                    _memo.results.push(if (_r663 == null) _KotlinLexer_Item(_start_i663, _memo.input) else null)
                    _index.element = _start_i663
                    _label = 661
                }
                // AND 661
                661 -> {
                    val _r661_2 = _memo.results.pop()
                    val _r661_1 = _memo.results.pop()

                    if (_r661_1 != null && _r661_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i661, _index.element, _memo.input, (_r661_1.results + _r661_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i661
                    }

                    // ACT 660
                    val _r660 = _memo.results.peek()
                    if (_r660 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r660.startIndex, _r660.nextIndex, _memo.input, _Thunk({ Token.FALSE }, _r660), true))
                    }

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

                    // AND 666
                    _start_i666 = _index.element

                    // LITERAL "null"
                    _ParseLiteralString(_memo, _index, "null")

                    // AND shortcut 666
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 666
                        continue
                    }

                    // NOT 668
                    _start_i668 = _index.element

                    // CALLORVAR identifierPart
                    var _r669: _KotlinLexer_Item? = null
                    _r669 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r669 != null) _index.element = _r669.nextIndex

                    // NOT 668
                    val _r668 = _memo.results.pop()
                    _memo.results.push(if (_r668 == null) _KotlinLexer_Item(_start_i668, _memo.input) else null)
                    _index.element = _start_i668
                    _label = 666
                }
                // AND 666
                666 -> {
                    val _r666_2 = _memo.results.pop()
                    val _r666_1 = _memo.results.pop()

                    if (_r666_1 != null && _r666_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i666, _index.element, _memo.input, (_r666_1.results + _r666_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i666
                    }

                    // ACT 665
                    val _r665 = _memo.results.peek()
                    if (_r665 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r665.startIndex, _r665.nextIndex, _memo.input, _Thunk({ Token.NULL }, _r665), true))
                    }

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

                    // AND 671
                    _start_i671 = _index.element

                    // LITERAL "value"
                    _ParseLiteralString(_memo, _index, "value")

                    // AND shortcut 671
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 671
                        continue
                    }

                    // NOT 673
                    _start_i673 = _index.element

                    // CALLORVAR identifierPart
                    var _r674: _KotlinLexer_Item? = null
                    _r674 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r674 != null) _index.element = _r674.nextIndex

                    // NOT 673
                    val _r673 = _memo.results.pop()
                    _memo.results.push(if (_r673 == null) _KotlinLexer_Item(_start_i673, _memo.input) else null)
                    _index.element = _start_i673
                    _label = 671
                }
                // AND 671
                671 -> {
                    val _r671_2 = _memo.results.pop()
                    val _r671_1 = _memo.results.pop()

                    if (_r671_1 != null && _r671_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i671, _index.element, _memo.input, (_r671_1.results + _r671_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i671
                    }

                    // ACT 670
                    val _r670 = _memo.results.peek()
                    if (_r670 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r670.startIndex, _r670.nextIndex, _memo.input, _Thunk({ Token.VALUE }, _r670), true))
                    }

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

                    // AND 676
                    _start_i676 = _index.element

                    // LITERAL "expect"
                    _ParseLiteralString(_memo, _index, "expect")

                    // AND shortcut 676
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 676
                        continue
                    }

                    // NOT 678
                    _start_i678 = _index.element

                    // CALLORVAR identifierPart
                    var _r679: _KotlinLexer_Item? = null
                    _r679 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r679 != null) _index.element = _r679.nextIndex

                    // NOT 678
                    val _r678 = _memo.results.pop()
                    _memo.results.push(if (_r678 == null) _KotlinLexer_Item(_start_i678, _memo.input) else null)
                    _index.element = _start_i678
                    _label = 676
                }
                // AND 676
                676 -> {
                    val _r676_2 = _memo.results.pop()
                    val _r676_1 = _memo.results.pop()

                    if (_r676_1 != null && _r676_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i676, _index.element, _memo.input, (_r676_1.results + _r676_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i676
                    }

                    // ACT 675
                    val _r675 = _memo.results.peek()
                    if (_r675 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r675.startIndex, _r675.nextIndex, _memo.input, _Thunk({ Token.EXPECT }, _r675), true))
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

                    // AND 681
                    _start_i681 = _index.element

                    // LITERAL "actual"
                    _ParseLiteralString(_memo, _index, "actual")

                    // AND shortcut 681
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 681
                        continue
                    }

                    // NOT 683
                    _start_i683 = _index.element

                    // CALLORVAR identifierPart
                    var _r684: _KotlinLexer_Item? = null
                    _r684 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r684 != null) _index.element = _r684.nextIndex

                    // NOT 683
                    val _r683 = _memo.results.pop()
                    _memo.results.push(if (_r683 == null) _KotlinLexer_Item(_start_i683, _memo.input) else null)
                    _index.element = _start_i683
                    _label = 681
                }
                // AND 681
                681 -> {
                    val _r681_2 = _memo.results.pop()
                    val _r681_1 = _memo.results.pop()

                    if (_r681_1 != null && _r681_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i681, _index.element, _memo.input, (_r681_1.results + _r681_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i681
                    }

                    // ACT 680
                    val _r680 = _memo.results.peek()
                    if (_r680 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r680.startIndex, _r680.nextIndex, _memo.input, _Thunk({ Token.ACTUAL }, _r680), true))
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

                    // CALLORVAR identifier
                    var _r686: _KotlinLexer_Item? = null
                    _r686 = _MemoCall(_memo, "identifier", _index.element, ::identifier, null)
                    if (_r686 != null) _index.element = _r686.nextIndex

                    // ACT 685
                    val _r685 = _memo.results.peek()
                    if (_r685 != null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_r685.startIndex, _r685.nextIndex, _memo.input, _Thunk({ Token.Identifier(it.s) }, _r685), true))
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


    open fun floatLiteral(_memo: _KotlinLexer_Memo, __index: Int, _args: _KotlinLexer_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 0
        var _start_i0 = _index.element

        // OR 1
        var _start_i1 = _index.element

        // OR 4
        var _start_i4 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 0
                    _start_i0 = _index.element

                    // OR 1
                    _start_i1 = _index.element

                    // CALLORVAR doubleLiteral
                    var _r2: _KotlinLexer_Item? = null
                    _r2 = _MemoCall(_memo, "doubleLiteral", _index.element, ::doubleLiteral, null)
                    if (_r2 != null) _index.element = _r2.nextIndex

                    // OR shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i1
                    } else {
                        _label = 1
                        continue
                    }

                    // CALLORVAR decLiteral
                    var _r3: _KotlinLexer_Item? = null
                    _r3 = _MemoCall(_memo, "decLiteral", _index.element, ::decLiteral, null)
                    if (_r3 != null) _index.element = _r3.nextIndex

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

                    // OR 4
                    _start_i4 = _index.element

                    // LITERAL 'f'
                    _ParseLiteralChar(_memo, _index, 'f')

                    // OR shortcut 4
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i4
                    } else {
                        _label = 4
                        continue
                    }

                    // LITERAL 'F'
                    _ParseLiteralChar(_memo, _index, 'F')

                    _label = 4
                }
                // OR 4
                4 -> {
                    _label = 0
                }
                // AND 0
                0 -> {
                    val _r0_2 = _memo.results.pop()
                    val _r0_1 = _memo.results.pop()

                    if (_r0_1 != null && _r0_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i0, _index.element, _memo.input, (_r0_1.results + _r0_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i0
                    }

                    break
                }
            }
        }
    }


    open fun doubleLiteral(_memo: _KotlinLexer_Memo, __index: Int, _args: _KotlinLexer_Args?) {
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

                    // CALLORVAR doubleLiteralStart
                    var _r1: _KotlinLexer_Item? = null
                    _r1 = _MemoCall(_memo, "doubleLiteralStart", _index.element, ::doubleLiteralStart, null)
                    if (_r1 != null) _index.element = _r1.nextIndex

                    // AND shortcut 0
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 0
                        continue
                    }

                    // CALLORVAR doubleLiteralPart
                    var _r2: _KotlinLexer_Item? = null
                    _r2 = _MemoCall(_memo, "doubleLiteralPart", _index.element, ::doubleLiteralPart, null)
                    if (_r2 != null) _index.element = _r2.nextIndex

                    _label = 0
                }
                // AND 0
                0 -> {
                    val _r0_2 = _memo.results.pop()
                    val _r0_1 = _memo.results.pop()

                    if (_r0_1 != null && _r0_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i0, _index.element, _memo.input, (_r0_1.results + _r0_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i0
                    }

                    break
                }
            }
        }
    }


    open fun doubleLiteralStart(_memo: _KotlinLexer_Memo, __index: Int, _args: _KotlinLexer_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // OR 0
        var _start_i0 = _index.element

        // AND 1
        var _start_i1 = _index.element

        // AND 3
        var _start_i3 = _index.element

        // AND 4
        var _start_i4 = _index.element

        // STAR 6
        var _start_i6 = _index.element
        val _inp6 = arrayListOf<Char?>()
        val _res6 = arrayListOf<Token?>()

        // OR 7
        var _start_i7 = _index.element

        // AND 12
        var _start_i12 = _index.element

        // OR 14
        var _start_i14 = _index.element

        // AND 15
        var _start_i15 = _index.element

        // STAR 17
        var _start_i17 = _index.element
        val _inp17 = arrayListOf<Char?>()
        val _res17 = arrayListOf<Token?>()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // OR 0
                    _start_i0 = _index.element

                    // AND 1
                    _start_i1 = _index.element

                    // AND 3
                    _start_i3 = _index.element

                    // AND 4
                    _start_i4 = _index.element

                    // CALLORVAR decDigitNoZero
                    var _r5: _KotlinLexer_Item? = null
                    _r5 = _MemoCall(_memo, "decDigitNoZero", _index.element, ::decDigitNoZero, null)
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
                    // OR 7
                    _start_i7 = _index.element

                    // CALLORVAR decDigit
                    var _r8: _KotlinLexer_Item? = null
                    _r8 = _MemoCall(_memo, "decDigit", _index.element, ::decDigit, null)
                    if (_r8 != null) _index.element = _r8.nextIndex

                    // OR shortcut 7
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i7
                    } else {
                        _label = 7
                        continue
                    }

                    // LITERAL '_'
                    _ParseLiteralChar(_memo, _index, '_')

                    _label = 7
                }
                // OR 7
                7 -> {
                    // STAR 6
                    val _r6 = _memo.results.pop()
                    if (_r6 != null) {
                        _res6 += _r6.results
                        _label = 6
                        continue
                    } else {
                        _memo.results.push(_KotlinLexer_Item(_start_i6, _index.element, _memo.input, _res6.filterNotNull(), true))
                    }

                    _label = 4
                }
                // AND 4
                4 -> {
                    val _r4_2 = _memo.results.pop()
                    val _r4_1 = _memo.results.pop()

                    if (_r4_1 != null && _r4_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i4, _index.element, _memo.input, (_r4_1.results + _r4_2.results).filterNotNull(), true))
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

                    // CALLORVAR decDigit
                    var _r10: _KotlinLexer_Item? = null
                    _r10 = _MemoCall(_memo, "decDigit", _index.element, ::decDigit, null)
                    if (_r10 != null) _index.element = _r10.nextIndex

                    _label = 3
                }
                // AND 3
                3 -> {
                    val _r3_2 = _memo.results.pop()
                    val _r3_1 = _memo.results.pop()

                    if (_r3_1 != null && _r3_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i3, _index.element, _memo.input, (_r3_1.results + _r3_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i3
                    }

                    // QUES 2
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_index.element, _memo.input))
                    }
                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // LITERAL '.'
                    _ParseLiteralChar(_memo, _index, '.')

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
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

                    // AND 12
                    _start_i12 = _index.element

                    // OR 14
                    _start_i14 = _index.element

                    // AND 15
                    _start_i15 = _index.element

                    // CALLORVAR decDigitNoZero
                    var _r16: _KotlinLexer_Item? = null
                    _r16 = _MemoCall(_memo, "decDigitNoZero", _index.element, ::decDigitNoZero, null)
                    if (_r16 != null) _index.element = _r16.nextIndex

                    // AND shortcut 15
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 15
                        continue
                    }

                    // STAR 17
                    _start_i17 = _index.element
                    _label = 17
                }
                // STAR 17
                17 -> {
                    // CALLORVAR decDigit
                    var _r18: _KotlinLexer_Item? = null
                    _r18 = _MemoCall(_memo, "decDigit", _index.element, ::decDigit, null)
                    if (_r18 != null) _index.element = _r18.nextIndex

                    // STAR 17
                    val _r17 = _memo.results.pop()
                    if (_r17 != null) {
                        _res17 += _r17.results
                        _label = 17
                        continue
                    } else {
                        _memo.results.push(_KotlinLexer_Item(_start_i17, _index.element, _memo.input, _res17.filterNotNull(), true))
                    }

                    _label = 15
                }
                // AND 15
                15 -> {
                    val _r15_2 = _memo.results.pop()
                    val _r15_1 = _memo.results.pop()

                    if (_r15_1 != null && _r15_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i15, _index.element, _memo.input, (_r15_1.results + _r15_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i15
                    }

                    // OR shortcut 14
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i14
                    } else {
                        _label = 14
                        continue
                    }

                    // LITERAL '0'
                    _ParseLiteralChar(_memo, _index, '0')

                    _label = 14
                }
                // OR 14
                14 -> {
                    // QUES 13
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_index.element, _memo.input))
                    }
                    // AND shortcut 12
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 12
                        continue
                    }

                    // LITERAL '.'
                    _ParseLiteralChar(_memo, _index, '.')

                    _label = 12
                }
                // AND 12
                12 -> {
                    val _r12_2 = _memo.results.pop()
                    val _r12_1 = _memo.results.pop()

                    if (_r12_1 != null && _r12_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i12, _index.element, _memo.input, (_r12_1.results + _r12_2.results).filterNotNull(), true))
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


    open fun doubleLiteralPart(_memo: _KotlinLexer_Memo, __index: Int, _args: _KotlinLexer_Args?) {
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

        // PLUS 13
        var _start_i13 = _index.element
        val _inp13 = arrayListOf<Char?>()
        val _res13 = arrayListOf<Token?>()

        // OR 14
        var _start_i14 = _index.element

        // OR 18
        var _start_i18 = _index.element

        // OR 22
        var _start_i22 = _index.element

        // PLUS 26
        var _start_i26 = _index.element
        val _inp26 = arrayListOf<Char?>()
        val _res26 = arrayListOf<Token?>()

        // OR 27
        var _start_i27 = _index.element

        // AND 31
        var _start_i31 = _index.element

        // AND 32
        var _start_i32 = _index.element

        // AND 33
        var _start_i33 = _index.element

        // AND 34
        var _start_i34 = _index.element

        // AND 35
        var _start_i35 = _index.element

        // PLUS 37
        var _start_i37 = _index.element
        val _inp37 = arrayListOf<Char?>()
        val _res37 = arrayListOf<Token?>()

        // OR 38
        var _start_i38 = _index.element

        // OR 42
        var _start_i42 = _index.element

        // OR 46
        var _start_i46 = _index.element

        // PLUS 49
        var _start_i49 = _index.element
        val _inp49 = arrayListOf<Char?>()
        val _res49 = arrayListOf<Token?>()

        // AND 51
        var _start_i51 = _index.element

        // AND 52
        var _start_i52 = _index.element

        // AND 53
        var _start_i53 = _index.element

        // AND 54
        var _start_i54 = _index.element

        // AND 55
        var _start_i55 = _index.element

        // PLUS 56
        var _start_i56 = _index.element
        val _inp56 = arrayListOf<Char?>()
        val _res56 = arrayListOf<Token?>()

        // OR 58
        var _start_i58 = _index.element

        // OR 62
        var _start_i62 = _index.element

        // PLUS 66
        var _start_i66 = _index.element
        val _inp66 = arrayListOf<Char?>()
        val _res66 = arrayListOf<Token?>()

        // OR 67
        var _start_i67 = _index.element

        // AND 71
        var _start_i71 = _index.element

        // AND 72
        var _start_i72 = _index.element

        // AND 73
        var _start_i73 = _index.element

        // PLUS 74
        var _start_i74 = _index.element
        val _inp74 = arrayListOf<Char?>()
        val _res74 = arrayListOf<Token?>()

        // OR 76
        var _start_i76 = _index.element

        // OR 80
        var _start_i80 = _index.element

        // PLUS 83
        var _start_i83 = _index.element
        val _inp83 = arrayListOf<Char?>()
        val _res83 = arrayListOf<Token?>()

        // AND 85
        var _start_i85 = _index.element

        // AND 86
        var _start_i86 = _index.element

        // PLUS 88
        var _start_i88 = _index.element
        val _inp88 = arrayListOf<Char?>()
        val _res88 = arrayListOf<Token?>()

        // OR 89
        var _start_i89 = _index.element

        // PLUS 93
        var _start_i93 = _index.element
        val _inp93 = arrayListOf<Char?>()
        val _res93 = arrayListOf<Token?>()

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

                    // CALLORVAR decDigit
                    var _r12: _KotlinLexer_Item? = null
                    _r12 = _MemoCall(_memo, "decDigit", _index.element, ::decDigit, null)
                    if (_r12 != null) _index.element = _r12.nextIndex

                    // AND shortcut 11
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 11
                        continue
                    }

                    // PLUS 13
                    _start_i13 = _index.element
                    _label = 13
                }
                // PLUS 13
                13 -> {
                    // OR 14
                    _start_i14 = _index.element

                    // CALLORVAR decDigit
                    var _r15: _KotlinLexer_Item? = null
                    _r15 = _MemoCall(_memo, "decDigit", _index.element, ::decDigit, null)
                    if (_r15 != null) _index.element = _r15.nextIndex

                    // OR shortcut 14
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i14
                    } else {
                        _label = 14
                        continue
                    }

                    // LITERAL '_'
                    _ParseLiteralChar(_memo, _index, '_')

                    _label = 14
                }
                // OR 14
                14 -> {
                    // PLUS 13
                    val _r13 = _memo.results.pop()
                    if (_r13 != null) {
                        _res13 += _r13.results
                        _label = 13
                        continue
                    } else {
                        if (_index.element > _start_i13) {
                            _memo.results.push(_KotlinLexer_Item(_start_i13, _index.element, _memo.input, _res13.filterNotNull(), true))
                        } else {
                            _memo.results.push(null)
                        }
                    }

                    _label = 11
                }
                // AND 11
                11 -> {
                    val _r11_2 = _memo.results.pop()
                    val _r11_1 = _memo.results.pop()

                    if (_r11_1 != null && _r11_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i11, _index.element, _memo.input, (_r11_1.results + _r11_2.results).filterNotNull(), true))
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

                    // CALLORVAR decDigit
                    var _r17: _KotlinLexer_Item? = null
                    _r17 = _MemoCall(_memo, "decDigit", _index.element, ::decDigit, null)
                    if (_r17 != null) _index.element = _r17.nextIndex

                    _label = 10
                }
                // AND 10
                10 -> {
                    val _r10_2 = _memo.results.pop()
                    val _r10_1 = _memo.results.pop()

                    if (_r10_1 != null && _r10_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i10, _index.element, _memo.input, (_r10_1.results + _r10_2.results).filterNotNull(), true))
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

                    // OR 18
                    _start_i18 = _index.element

                    // LITERAL 'e'
                    _ParseLiteralChar(_memo, _index, 'e')

                    // OR shortcut 18
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i18
                    } else {
                        _label = 18
                        continue
                    }

                    // LITERAL 'E'
                    _ParseLiteralChar(_memo, _index, 'E')

                    _label = 18
                }
                // OR 18
                18 -> {
                    _label = 9
                }
                // AND 9
                9 -> {
                    val _r9_2 = _memo.results.pop()
                    val _r9_1 = _memo.results.pop()

                    if (_r9_1 != null && _r9_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i9, _index.element, _memo.input, (_r9_1.results + _r9_2.results).filterNotNull(), true))
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

                    // OR 22
                    _start_i22 = _index.element

                    // LITERAL '+'
                    _ParseLiteralChar(_memo, _index, '+')

                    // OR shortcut 22
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i22
                    } else {
                        _label = 22
                        continue
                    }

                    // LITERAL '-'
                    _ParseLiteralChar(_memo, _index, '-')

                    _label = 22
                }
                // OR 22
                22 -> {
                    // QUES 21
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_index.element, _memo.input))
                    }
                    _label = 8
                }
                // AND 8
                8 -> {
                    val _r8_2 = _memo.results.pop()
                    val _r8_1 = _memo.results.pop()

                    if (_r8_1 != null && _r8_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i8, _index.element, _memo.input, (_r8_1.results + _r8_2.results).filterNotNull(), true))
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

                    // CALLORVAR decDigit
                    var _r25: _KotlinLexer_Item? = null
                    _r25 = _MemoCall(_memo, "decDigit", _index.element, ::decDigit, null)
                    if (_r25 != null) _index.element = _r25.nextIndex

                    _label = 7
                }
                // AND 7
                7 -> {
                    val _r7_2 = _memo.results.pop()
                    val _r7_1 = _memo.results.pop()

                    if (_r7_1 != null && _r7_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i7, _index.element, _memo.input, (_r7_1.results + _r7_2.results).filterNotNull(), true))
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

                    // PLUS 26
                    _start_i26 = _index.element
                    _label = 26
                }
                // PLUS 26
                26 -> {
                    // OR 27
                    _start_i27 = _index.element

                    // CALLORVAR decDigit
                    var _r28: _KotlinLexer_Item? = null
                    _r28 = _MemoCall(_memo, "decDigit", _index.element, ::decDigit, null)
                    if (_r28 != null) _index.element = _r28.nextIndex

                    // OR shortcut 27
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i27
                    } else {
                        _label = 27
                        continue
                    }

                    // LITERAL '_'
                    _ParseLiteralChar(_memo, _index, '_')

                    _label = 27
                }
                // OR 27
                27 -> {
                    // PLUS 26
                    val _r26 = _memo.results.pop()
                    if (_r26 != null) {
                        _res26 += _r26.results
                        _label = 26
                        continue
                    } else {
                        if (_index.element > _start_i26) {
                            _memo.results.push(_KotlinLexer_Item(_start_i26, _index.element, _memo.input, _res26.filterNotNull(), true))
                        } else {
                            _memo.results.push(null)
                        }
                    }

                    _label = 6
                }
                // AND 6
                6 -> {
                    val _r6_2 = _memo.results.pop()
                    val _r6_1 = _memo.results.pop()

                    if (_r6_1 != null && _r6_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i6, _index.element, _memo.input, (_r6_1.results + _r6_2.results).filterNotNull(), true))
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

                    // CALLORVAR decDigit
                    var _r30: _KotlinLexer_Item? = null
                    _r30 = _MemoCall(_memo, "decDigit", _index.element, ::decDigit, null)
                    if (_r30 != null) _index.element = _r30.nextIndex

                    _label = 5
                }
                // AND 5
                5 -> {
                    val _r5_2 = _memo.results.pop()
                    val _r5_1 = _memo.results.pop()

                    if (_r5_1 != null && _r5_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i5, _index.element, _memo.input, (_r5_1.results + _r5_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i5
                    }

                    // OR shortcut 4
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i4
                    } else {
                        _label = 4
                        continue
                    }

                    // AND 31
                    _start_i31 = _index.element

                    // AND 32
                    _start_i32 = _index.element

                    // AND 33
                    _start_i33 = _index.element

                    // AND 34
                    _start_i34 = _index.element

                    // AND 35
                    _start_i35 = _index.element

                    // CALLORVAR decDigit
                    var _r36: _KotlinLexer_Item? = null
                    _r36 = _MemoCall(_memo, "decDigit", _index.element, ::decDigit, null)
                    if (_r36 != null) _index.element = _r36.nextIndex

                    // AND shortcut 35
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 35
                        continue
                    }

                    // PLUS 37
                    _start_i37 = _index.element
                    _label = 37
                }
                // PLUS 37
                37 -> {
                    // OR 38
                    _start_i38 = _index.element

                    // CALLORVAR decDigit
                    var _r39: _KotlinLexer_Item? = null
                    _r39 = _MemoCall(_memo, "decDigit", _index.element, ::decDigit, null)
                    if (_r39 != null) _index.element = _r39.nextIndex

                    // OR shortcut 38
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i38
                    } else {
                        _label = 38
                        continue
                    }

                    // LITERAL '_'
                    _ParseLiteralChar(_memo, _index, '_')

                    _label = 38
                }
                // OR 38
                38 -> {
                    // PLUS 37
                    val _r37 = _memo.results.pop()
                    if (_r37 != null) {
                        _res37 += _r37.results
                        _label = 37
                        continue
                    } else {
                        if (_index.element > _start_i37) {
                            _memo.results.push(_KotlinLexer_Item(_start_i37, _index.element, _memo.input, _res37.filterNotNull(), true))
                        } else {
                            _memo.results.push(null)
                        }
                    }

                    _label = 35
                }
                // AND 35
                35 -> {
                    val _r35_2 = _memo.results.pop()
                    val _r35_1 = _memo.results.pop()

                    if (_r35_1 != null && _r35_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i35, _index.element, _memo.input, (_r35_1.results + _r35_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i35
                    }

                    // AND shortcut 34
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 34
                        continue
                    }

                    // CALLORVAR decDigit
                    var _r41: _KotlinLexer_Item? = null
                    _r41 = _MemoCall(_memo, "decDigit", _index.element, ::decDigit, null)
                    if (_r41 != null) _index.element = _r41.nextIndex

                    _label = 34
                }
                // AND 34
                34 -> {
                    val _r34_2 = _memo.results.pop()
                    val _r34_1 = _memo.results.pop()

                    if (_r34_1 != null && _r34_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i34, _index.element, _memo.input, (_r34_1.results + _r34_2.results).filterNotNull(), true))
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

                    // OR 42
                    _start_i42 = _index.element

                    // LITERAL 'e'
                    _ParseLiteralChar(_memo, _index, 'e')

                    // OR shortcut 42
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i42
                    } else {
                        _label = 42
                        continue
                    }

                    // LITERAL 'E'
                    _ParseLiteralChar(_memo, _index, 'E')

                    _label = 42
                }
                // OR 42
                42 -> {
                    _label = 33
                }
                // AND 33
                33 -> {
                    val _r33_2 = _memo.results.pop()
                    val _r33_1 = _memo.results.pop()

                    if (_r33_1 != null && _r33_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i33, _index.element, _memo.input, (_r33_1.results + _r33_2.results).filterNotNull(), true))
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

                    // OR 46
                    _start_i46 = _index.element

                    // LITERAL '+'
                    _ParseLiteralChar(_memo, _index, '+')

                    // OR shortcut 46
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i46
                    } else {
                        _label = 46
                        continue
                    }

                    // LITERAL '-'
                    _ParseLiteralChar(_memo, _index, '-')

                    _label = 46
                }
                // OR 46
                46 -> {
                    // QUES 45
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_index.element, _memo.input))
                    }
                    _label = 32
                }
                // AND 32
                32 -> {
                    val _r32_2 = _memo.results.pop()
                    val _r32_1 = _memo.results.pop()

                    if (_r32_1 != null && _r32_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i32, _index.element, _memo.input, (_r32_1.results + _r32_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i32
                    }

                    // AND shortcut 31
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 31
                        continue
                    }

                    // PLUS 49
                    _start_i49 = _index.element
                    _label = 49
                }
                // PLUS 49
                49 -> {
                    // CALLORVAR decDigit
                    var _r50: _KotlinLexer_Item? = null
                    _r50 = _MemoCall(_memo, "decDigit", _index.element, ::decDigit, null)
                    if (_r50 != null) _index.element = _r50.nextIndex

                    // PLUS 49
                    val _r49 = _memo.results.pop()
                    if (_r49 != null) {
                        _res49 += _r49.results
                        _label = 49
                        continue
                    } else {
                        if (_index.element > _start_i49) {
                            _memo.results.push(_KotlinLexer_Item(_start_i49, _index.element, _memo.input, _res49.filterNotNull(), true))
                        } else {
                            _memo.results.push(null)
                        }
                    }

                    _label = 31
                }
                // AND 31
                31 -> {
                    val _r31_2 = _memo.results.pop()
                    val _r31_1 = _memo.results.pop()

                    if (_r31_1 != null && _r31_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i31, _index.element, _memo.input, (_r31_1.results + _r31_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i31
                    }

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

                    // AND 51
                    _start_i51 = _index.element

                    // AND 52
                    _start_i52 = _index.element

                    // AND 53
                    _start_i53 = _index.element

                    // AND 54
                    _start_i54 = _index.element

                    // AND 55
                    _start_i55 = _index.element

                    // PLUS 56
                    _start_i56 = _index.element
                    _label = 56
                }
                // PLUS 56
                56 -> {
                    // CALLORVAR decDigit
                    var _r57: _KotlinLexer_Item? = null
                    _r57 = _MemoCall(_memo, "decDigit", _index.element, ::decDigit, null)
                    if (_r57 != null) _index.element = _r57.nextIndex

                    // PLUS 56
                    val _r56 = _memo.results.pop()
                    if (_r56 != null) {
                        _res56 += _r56.results
                        _label = 56
                        continue
                    } else {
                        if (_index.element > _start_i56) {
                            _memo.results.push(_KotlinLexer_Item(_start_i56, _index.element, _memo.input, _res56.filterNotNull(), true))
                        } else {
                            _memo.results.push(null)
                        }
                    }

                    // AND shortcut 55
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 55
                        continue
                    }

                    // OR 58
                    _start_i58 = _index.element

                    // LITERAL 'e'
                    _ParseLiteralChar(_memo, _index, 'e')

                    // OR shortcut 58
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i58
                    } else {
                        _label = 58
                        continue
                    }

                    // LITERAL 'E'
                    _ParseLiteralChar(_memo, _index, 'E')

                    _label = 58
                }
                // OR 58
                58 -> {
                    _label = 55
                }
                // AND 55
                55 -> {
                    val _r55_2 = _memo.results.pop()
                    val _r55_1 = _memo.results.pop()

                    if (_r55_1 != null && _r55_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i55, _index.element, _memo.input, (_r55_1.results + _r55_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i55
                    }

                    // AND shortcut 54
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 54
                        continue
                    }

                    // OR 62
                    _start_i62 = _index.element

                    // LITERAL '+'
                    _ParseLiteralChar(_memo, _index, '+')

                    // OR shortcut 62
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i62
                    } else {
                        _label = 62
                        continue
                    }

                    // LITERAL '-'
                    _ParseLiteralChar(_memo, _index, '-')

                    _label = 62
                }
                // OR 62
                62 -> {
                    // QUES 61
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_index.element, _memo.input))
                    }
                    _label = 54
                }
                // AND 54
                54 -> {
                    val _r54_2 = _memo.results.pop()
                    val _r54_1 = _memo.results.pop()

                    if (_r54_1 != null && _r54_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i54, _index.element, _memo.input, (_r54_1.results + _r54_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i54
                    }

                    // AND shortcut 53
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 53
                        continue
                    }

                    // CALLORVAR decDigit
                    var _r65: _KotlinLexer_Item? = null
                    _r65 = _MemoCall(_memo, "decDigit", _index.element, ::decDigit, null)
                    if (_r65 != null) _index.element = _r65.nextIndex

                    _label = 53
                }
                // AND 53
                53 -> {
                    val _r53_2 = _memo.results.pop()
                    val _r53_1 = _memo.results.pop()

                    if (_r53_1 != null && _r53_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i53, _index.element, _memo.input, (_r53_1.results + _r53_2.results).filterNotNull(), true))
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

                    // PLUS 66
                    _start_i66 = _index.element
                    _label = 66
                }
                // PLUS 66
                66 -> {
                    // OR 67
                    _start_i67 = _index.element

                    // CALLORVAR decDigit
                    var _r68: _KotlinLexer_Item? = null
                    _r68 = _MemoCall(_memo, "decDigit", _index.element, ::decDigit, null)
                    if (_r68 != null) _index.element = _r68.nextIndex

                    // OR shortcut 67
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i67
                    } else {
                        _label = 67
                        continue
                    }

                    // LITERAL '_'
                    _ParseLiteralChar(_memo, _index, '_')

                    _label = 67
                }
                // OR 67
                67 -> {
                    // PLUS 66
                    val _r66 = _memo.results.pop()
                    if (_r66 != null) {
                        _res66 += _r66.results
                        _label = 66
                        continue
                    } else {
                        if (_index.element > _start_i66) {
                            _memo.results.push(_KotlinLexer_Item(_start_i66, _index.element, _memo.input, _res66.filterNotNull(), true))
                        } else {
                            _memo.results.push(null)
                        }
                    }

                    _label = 52
                }
                // AND 52
                52 -> {
                    val _r52_2 = _memo.results.pop()
                    val _r52_1 = _memo.results.pop()

                    if (_r52_1 != null && _r52_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i52, _index.element, _memo.input, (_r52_1.results + _r52_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i52
                    }

                    // AND shortcut 51
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 51
                        continue
                    }

                    // CALLORVAR decDigit
                    var _r70: _KotlinLexer_Item? = null
                    _r70 = _MemoCall(_memo, "decDigit", _index.element, ::decDigit, null)
                    if (_r70 != null) _index.element = _r70.nextIndex

                    _label = 51
                }
                // AND 51
                51 -> {
                    val _r51_2 = _memo.results.pop()
                    val _r51_1 = _memo.results.pop()

                    if (_r51_1 != null && _r51_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i51, _index.element, _memo.input, (_r51_1.results + _r51_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i51
                    }

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

                    // AND 71
                    _start_i71 = _index.element

                    // AND 72
                    _start_i72 = _index.element

                    // AND 73
                    _start_i73 = _index.element

                    // PLUS 74
                    _start_i74 = _index.element
                    _label = 74
                }
                // PLUS 74
                74 -> {
                    // CALLORVAR decDigit
                    var _r75: _KotlinLexer_Item? = null
                    _r75 = _MemoCall(_memo, "decDigit", _index.element, ::decDigit, null)
                    if (_r75 != null) _index.element = _r75.nextIndex

                    // PLUS 74
                    val _r74 = _memo.results.pop()
                    if (_r74 != null) {
                        _res74 += _r74.results
                        _label = 74
                        continue
                    } else {
                        if (_index.element > _start_i74) {
                            _memo.results.push(_KotlinLexer_Item(_start_i74, _index.element, _memo.input, _res74.filterNotNull(), true))
                        } else {
                            _memo.results.push(null)
                        }
                    }

                    // AND shortcut 73
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 73
                        continue
                    }

                    // OR 76
                    _start_i76 = _index.element

                    // LITERAL 'e'
                    _ParseLiteralChar(_memo, _index, 'e')

                    // OR shortcut 76
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i76
                    } else {
                        _label = 76
                        continue
                    }

                    // LITERAL 'E'
                    _ParseLiteralChar(_memo, _index, 'E')

                    _label = 76
                }
                // OR 76
                76 -> {
                    _label = 73
                }
                // AND 73
                73 -> {
                    val _r73_2 = _memo.results.pop()
                    val _r73_1 = _memo.results.pop()

                    if (_r73_1 != null && _r73_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i73, _index.element, _memo.input, (_r73_1.results + _r73_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i73
                    }

                    // AND shortcut 72
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 72
                        continue
                    }

                    // OR 80
                    _start_i80 = _index.element

                    // LITERAL '+'
                    _ParseLiteralChar(_memo, _index, '+')

                    // OR shortcut 80
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i80
                    } else {
                        _label = 80
                        continue
                    }

                    // LITERAL '-'
                    _ParseLiteralChar(_memo, _index, '-')

                    _label = 80
                }
                // OR 80
                80 -> {
                    // QUES 79
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_index.element, _memo.input))
                    }
                    _label = 72
                }
                // AND 72
                72 -> {
                    val _r72_2 = _memo.results.pop()
                    val _r72_1 = _memo.results.pop()

                    if (_r72_1 != null && _r72_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i72, _index.element, _memo.input, (_r72_1.results + _r72_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i72
                    }

                    // AND shortcut 71
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 71
                        continue
                    }

                    // PLUS 83
                    _start_i83 = _index.element
                    _label = 83
                }
                // PLUS 83
                83 -> {
                    // CALLORVAR decDigit
                    var _r84: _KotlinLexer_Item? = null
                    _r84 = _MemoCall(_memo, "decDigit", _index.element, ::decDigit, null)
                    if (_r84 != null) _index.element = _r84.nextIndex

                    // PLUS 83
                    val _r83 = _memo.results.pop()
                    if (_r83 != null) {
                        _res83 += _r83.results
                        _label = 83
                        continue
                    } else {
                        if (_index.element > _start_i83) {
                            _memo.results.push(_KotlinLexer_Item(_start_i83, _index.element, _memo.input, _res83.filterNotNull(), true))
                        } else {
                            _memo.results.push(null)
                        }
                    }

                    _label = 71
                }
                // AND 71
                71 -> {
                    val _r71_2 = _memo.results.pop()
                    val _r71_1 = _memo.results.pop()

                    if (_r71_1 != null && _r71_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i71, _index.element, _memo.input, (_r71_1.results + _r71_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i71
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

                    // AND 85
                    _start_i85 = _index.element

                    // AND 86
                    _start_i86 = _index.element

                    // CALLORVAR decDigit
                    var _r87: _KotlinLexer_Item? = null
                    _r87 = _MemoCall(_memo, "decDigit", _index.element, ::decDigit, null)
                    if (_r87 != null) _index.element = _r87.nextIndex

                    // AND shortcut 86
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 86
                        continue
                    }

                    // PLUS 88
                    _start_i88 = _index.element
                    _label = 88
                }
                // PLUS 88
                88 -> {
                    // OR 89
                    _start_i89 = _index.element

                    // CALLORVAR decDigit
                    var _r90: _KotlinLexer_Item? = null
                    _r90 = _MemoCall(_memo, "decDigit", _index.element, ::decDigit, null)
                    if (_r90 != null) _index.element = _r90.nextIndex

                    // OR shortcut 89
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i89
                    } else {
                        _label = 89
                        continue
                    }

                    // LITERAL '_'
                    _ParseLiteralChar(_memo, _index, '_')

                    _label = 89
                }
                // OR 89
                89 -> {
                    // PLUS 88
                    val _r88 = _memo.results.pop()
                    if (_r88 != null) {
                        _res88 += _r88.results
                        _label = 88
                        continue
                    } else {
                        if (_index.element > _start_i88) {
                            _memo.results.push(_KotlinLexer_Item(_start_i88, _index.element, _memo.input, _res88.filterNotNull(), true))
                        } else {
                            _memo.results.push(null)
                        }
                    }

                    _label = 86
                }
                // AND 86
                86 -> {
                    val _r86_2 = _memo.results.pop()
                    val _r86_1 = _memo.results.pop()

                    if (_r86_1 != null && _r86_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i86, _index.element, _memo.input, (_r86_1.results + _r86_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i86
                    }

                    // AND shortcut 85
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 85
                        continue
                    }

                    // CALLORVAR decDigit
                    var _r92: _KotlinLexer_Item? = null
                    _r92 = _MemoCall(_memo, "decDigit", _index.element, ::decDigit, null)
                    if (_r92 != null) _index.element = _r92.nextIndex

                    _label = 85
                }
                // AND 85
                85 -> {
                    val _r85_2 = _memo.results.pop()
                    val _r85_1 = _memo.results.pop()

                    if (_r85_1 != null && _r85_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i85, _index.element, _memo.input, (_r85_1.results + _r85_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i85
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

                    // PLUS 93
                    _start_i93 = _index.element
                    _label = 93
                }
                // PLUS 93
                93 -> {
                    // CALLORVAR decDigit
                    var _r94: _KotlinLexer_Item? = null
                    _r94 = _MemoCall(_memo, "decDigit", _index.element, ::decDigit, null)
                    if (_r94 != null) _index.element = _r94.nextIndex

                    // PLUS 93
                    val _r93 = _memo.results.pop()
                    if (_r93 != null) {
                        _res93 += _r93.results
                        _label = 93
                        continue
                    } else {
                        if (_index.element > _start_i93) {
                            _memo.results.push(_KotlinLexer_Item(_start_i93, _index.element, _memo.input, _res93.filterNotNull(), true))
                        } else {
                            _memo.results.push(null)
                        }
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


    open fun longLiteral(_memo: _KotlinLexer_Memo, __index: Int, _args: _KotlinLexer_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 0
        var _start_i0 = _index.element

        // OR 2
        var _start_i2 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 0
                    _start_i0 = _index.element

                    // CALLORVAR integerLiteral
                    var _r1: _KotlinLexer_Item? = null
                    _r1 = _MemoCall(_memo, "integerLiteral", _index.element, ::integerLiteral, null)
                    if (_r1 != null) _index.element = _r1.nextIndex

                    // AND shortcut 0
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 0
                        continue
                    }

                    // OR 2
                    _start_i2 = _index.element

                    // LITERAL 'l'
                    _ParseLiteralChar(_memo, _index, 'l')

                    // OR shortcut 2
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i2
                    } else {
                        _label = 2
                        continue
                    }

                    // LITERAL 'L'
                    _ParseLiteralChar(_memo, _index, 'L')

                    _label = 2
                }
                // OR 2
                2 -> {
                    _label = 0
                }
                // AND 0
                0 -> {
                    val _r0_2 = _memo.results.pop()
                    val _r0_1 = _memo.results.pop()

                    if (_r0_1 != null && _r0_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i0, _index.element, _memo.input, (_r0_1.results + _r0_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i0
                    }

                    break
                }
            }
        }
    }


    open fun unsignedLiteral(_memo: _KotlinLexer_Memo, __index: Int, _args: _KotlinLexer_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 0
        var _start_i0 = _index.element

        // OR 2
        var _start_i2 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 0
                    _start_i0 = _index.element

                    // CALLORVAR integerLiteral
                    var _r1: _KotlinLexer_Item? = null
                    _r1 = _MemoCall(_memo, "integerLiteral", _index.element, ::integerLiteral, null)
                    if (_r1 != null) _index.element = _r1.nextIndex

                    // AND shortcut 0
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 0
                        continue
                    }

                    // OR 2
                    _start_i2 = _index.element

                    // LITERAL 'l'
                    _ParseLiteralChar(_memo, _index, 'l')

                    // OR shortcut 2
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i2
                    } else {
                        _label = 2
                        continue
                    }

                    // LITERAL 'L'
                    _ParseLiteralChar(_memo, _index, 'L')

                    _label = 2
                }
                // OR 2
                2 -> {
                    _label = 0
                }
                // AND 0
                0 -> {
                    val _r0_2 = _memo.results.pop()
                    val _r0_1 = _memo.results.pop()

                    if (_r0_1 != null && _r0_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i0, _index.element, _memo.input, (_r0_1.results + _r0_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i0
                    }

                    break
                }
            }
        }
    }


    open fun unsignedLongLiteral(_memo: _KotlinLexer_Memo, __index: Int, _args: _KotlinLexer_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 0
        var _start_i0 = _index.element

        // OR 2
        var _start_i2 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 0
                    _start_i0 = _index.element

                    // CALLORVAR integerLiteral
                    var _r1: _KotlinLexer_Item? = null
                    _r1 = _MemoCall(_memo, "integerLiteral", _index.element, ::integerLiteral, null)
                    if (_r1 != null) _index.element = _r1.nextIndex

                    // AND shortcut 0
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 0
                        continue
                    }

                    // OR 2
                    _start_i2 = _index.element

                    // LITERAL "ul"
                    _ParseLiteralString(_memo, _index, "ul")

                    // OR shortcut 2
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i2
                    } else {
                        _label = 2
                        continue
                    }

                    // LITERAL "UL"
                    _ParseLiteralString(_memo, _index, "UL")

                    _label = 2
                }
                // OR 2
                2 -> {
                    _label = 0
                }
                // AND 0
                0 -> {
                    val _r0_2 = _memo.results.pop()
                    val _r0_1 = _memo.results.pop()

                    if (_r0_1 != null && _r0_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i0, _index.element, _memo.input, (_r0_1.results + _r0_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i0
                    }

                    break
                }
            }
        }
    }


    open fun integerLiteral(_memo: _KotlinLexer_Memo, __index: Int, _args: _KotlinLexer_Args?) {
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

                    // CALLORVAR decLiteral
                    var _r2: _KotlinLexer_Item? = null
                    _r2 = _MemoCall(_memo, "decLiteral", _index.element, ::decLiteral, null)
                    if (_r2 != null) _index.element = _r2.nextIndex

                    // OR shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i1
                    } else {
                        _label = 1
                        continue
                    }

                    // CALLORVAR hexLiteral
                    var _r3: _KotlinLexer_Item? = null
                    _r3 = _MemoCall(_memo, "hexLiteral", _index.element, ::hexLiteral, null)
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

                    // CALLORVAR binLiteral
                    var _r4: _KotlinLexer_Item? = null
                    _r4 = _MemoCall(_memo, "binLiteral", _index.element, ::binLiteral, null)
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


    open fun decLiteral(_memo: _KotlinLexer_Memo, __index: Int, _args: _KotlinLexer_Args?) {
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

        // OR 5
        var _start_i5 = _index.element

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

        // PLUS 15
        var _start_i15 = _index.element
        val _inp15 = arrayListOf<Char?>()
        val _res15 = arrayListOf<Token?>()

        // OR 16
        var _start_i16 = _index.element

        // OR 20
        var _start_i20 = _index.element

        // OR 24
        var _start_i24 = _index.element

        // PLUS 28
        var _start_i28 = _index.element
        val _inp28 = arrayListOf<Char?>()
        val _res28 = arrayListOf<Token?>()

        // OR 29
        var _start_i29 = _index.element

        // AND 33
        var _start_i33 = _index.element

        // AND 34
        var _start_i34 = _index.element

        // AND 35
        var _start_i35 = _index.element

        // AND 36
        var _start_i36 = _index.element

        // AND 37
        var _start_i37 = _index.element

        // PLUS 39
        var _start_i39 = _index.element
        val _inp39 = arrayListOf<Char?>()
        val _res39 = arrayListOf<Token?>()

        // OR 40
        var _start_i40 = _index.element

        // OR 44
        var _start_i44 = _index.element

        // OR 48
        var _start_i48 = _index.element

        // PLUS 51
        var _start_i51 = _index.element
        val _inp51 = arrayListOf<Char?>()
        val _res51 = arrayListOf<Token?>()

        // AND 53
        var _start_i53 = _index.element

        // AND 54
        var _start_i54 = _index.element

        // AND 55
        var _start_i55 = _index.element

        // AND 56
        var _start_i56 = _index.element

        // AND 57
        var _start_i57 = _index.element

        // AND 58
        var _start_i58 = _index.element

        // STAR 60
        var _start_i60 = _index.element
        val _inp60 = arrayListOf<Char?>()
        val _res60 = arrayListOf<Token?>()

        // OR 62
        var _start_i62 = _index.element

        // OR 66
        var _start_i66 = _index.element

        // PLUS 70
        var _start_i70 = _index.element
        val _inp70 = arrayListOf<Char?>()
        val _res70 = arrayListOf<Token?>()

        // OR 71
        var _start_i71 = _index.element

        // AND 75
        var _start_i75 = _index.element

        // AND 76
        var _start_i76 = _index.element

        // AND 77
        var _start_i77 = _index.element

        // AND 78
        var _start_i78 = _index.element

        // STAR 80
        var _start_i80 = _index.element
        val _inp80 = arrayListOf<Char?>()
        val _res80 = arrayListOf<Token?>()

        // OR 82
        var _start_i82 = _index.element

        // OR 86
        var _start_i86 = _index.element

        // PLUS 89
        var _start_i89 = _index.element
        val _inp89 = arrayListOf<Char?>()
        val _res89 = arrayListOf<Token?>()

        // AND 91
        var _start_i91 = _index.element

        // AND 92
        var _start_i92 = _index.element

        // PLUS 94
        var _start_i94 = _index.element
        val _inp94 = arrayListOf<Char?>()
        val _res94 = arrayListOf<Token?>()

        // OR 95
        var _start_i95 = _index.element

        // AND 99
        var _start_i99 = _index.element

        // STAR 101
        var _start_i101 = _index.element
        val _inp101 = arrayListOf<Char?>()
        val _res101 = arrayListOf<Token?>()

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

                    // OR 5
                    _start_i5 = _index.element

                    // LITERAL '0'
                    _ParseLiteralChar(_memo, _index, '0')

                    // OR shortcut 5
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i5
                    } else {
                        _label = 5
                        continue
                    }

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

                    // CALLORVAR decDigitNoZero
                    var _r14: _KotlinLexer_Item? = null
                    _r14 = _MemoCall(_memo, "decDigitNoZero", _index.element, ::decDigitNoZero, null)
                    if (_r14 != null) _index.element = _r14.nextIndex

                    // AND shortcut 13
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 13
                        continue
                    }

                    // PLUS 15
                    _start_i15 = _index.element
                    _label = 15
                }
                // PLUS 15
                15 -> {
                    // OR 16
                    _start_i16 = _index.element

                    // CALLORVAR decDigit
                    var _r17: _KotlinLexer_Item? = null
                    _r17 = _MemoCall(_memo, "decDigit", _index.element, ::decDigit, null)
                    if (_r17 != null) _index.element = _r17.nextIndex

                    // OR shortcut 16
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i16
                    } else {
                        _label = 16
                        continue
                    }

                    // LITERAL '_'
                    _ParseLiteralChar(_memo, _index, '_')

                    _label = 16
                }
                // OR 16
                16 -> {
                    // PLUS 15
                    val _r15 = _memo.results.pop()
                    if (_r15 != null) {
                        _res15 += _r15.results
                        _label = 15
                        continue
                    } else {
                        if (_index.element > _start_i15) {
                            _memo.results.push(_KotlinLexer_Item(_start_i15, _index.element, _memo.input, _res15.filterNotNull(), true))
                        } else {
                            _memo.results.push(null)
                        }
                    }

                    _label = 13
                }
                // AND 13
                13 -> {
                    val _r13_2 = _memo.results.pop()
                    val _r13_1 = _memo.results.pop()

                    if (_r13_1 != null && _r13_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i13, _index.element, _memo.input, (_r13_1.results + _r13_2.results).filterNotNull(), true))
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

                    // CALLORVAR decDigit
                    var _r19: _KotlinLexer_Item? = null
                    _r19 = _MemoCall(_memo, "decDigit", _index.element, ::decDigit, null)
                    if (_r19 != null) _index.element = _r19.nextIndex

                    _label = 12
                }
                // AND 12
                12 -> {
                    val _r12_2 = _memo.results.pop()
                    val _r12_1 = _memo.results.pop()

                    if (_r12_1 != null && _r12_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i12, _index.element, _memo.input, (_r12_1.results + _r12_2.results).filterNotNull(), true))
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

                    // OR 20
                    _start_i20 = _index.element

                    // LITERAL 'e'
                    _ParseLiteralChar(_memo, _index, 'e')

                    // OR shortcut 20
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i20
                    } else {
                        _label = 20
                        continue
                    }

                    // LITERAL 'E'
                    _ParseLiteralChar(_memo, _index, 'E')

                    _label = 20
                }
                // OR 20
                20 -> {
                    _label = 11
                }
                // AND 11
                11 -> {
                    val _r11_2 = _memo.results.pop()
                    val _r11_1 = _memo.results.pop()

                    if (_r11_1 != null && _r11_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i11, _index.element, _memo.input, (_r11_1.results + _r11_2.results).filterNotNull(), true))
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

                    // OR 24
                    _start_i24 = _index.element

                    // LITERAL '+'
                    _ParseLiteralChar(_memo, _index, '+')

                    // OR shortcut 24
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i24
                    } else {
                        _label = 24
                        continue
                    }

                    // LITERAL '-'
                    _ParseLiteralChar(_memo, _index, '-')

                    _label = 24
                }
                // OR 24
                24 -> {
                    // QUES 23
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_index.element, _memo.input))
                    }
                    _label = 10
                }
                // AND 10
                10 -> {
                    val _r10_2 = _memo.results.pop()
                    val _r10_1 = _memo.results.pop()

                    if (_r10_1 != null && _r10_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i10, _index.element, _memo.input, (_r10_1.results + _r10_2.results).filterNotNull(), true))
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

                    // CALLORVAR decDigit
                    var _r27: _KotlinLexer_Item? = null
                    _r27 = _MemoCall(_memo, "decDigit", _index.element, ::decDigit, null)
                    if (_r27 != null) _index.element = _r27.nextIndex

                    _label = 9
                }
                // AND 9
                9 -> {
                    val _r9_2 = _memo.results.pop()
                    val _r9_1 = _memo.results.pop()

                    if (_r9_1 != null && _r9_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i9, _index.element, _memo.input, (_r9_1.results + _r9_2.results).filterNotNull(), true))
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

                    // PLUS 28
                    _start_i28 = _index.element
                    _label = 28
                }
                // PLUS 28
                28 -> {
                    // OR 29
                    _start_i29 = _index.element

                    // CALLORVAR decDigit
                    var _r30: _KotlinLexer_Item? = null
                    _r30 = _MemoCall(_memo, "decDigit", _index.element, ::decDigit, null)
                    if (_r30 != null) _index.element = _r30.nextIndex

                    // OR shortcut 29
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i29
                    } else {
                        _label = 29
                        continue
                    }

                    // LITERAL '_'
                    _ParseLiteralChar(_memo, _index, '_')

                    _label = 29
                }
                // OR 29
                29 -> {
                    // PLUS 28
                    val _r28 = _memo.results.pop()
                    if (_r28 != null) {
                        _res28 += _r28.results
                        _label = 28
                        continue
                    } else {
                        if (_index.element > _start_i28) {
                            _memo.results.push(_KotlinLexer_Item(_start_i28, _index.element, _memo.input, _res28.filterNotNull(), true))
                        } else {
                            _memo.results.push(null)
                        }
                    }

                    _label = 8
                }
                // AND 8
                8 -> {
                    val _r8_2 = _memo.results.pop()
                    val _r8_1 = _memo.results.pop()

                    if (_r8_1 != null && _r8_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i8, _index.element, _memo.input, (_r8_1.results + _r8_2.results).filterNotNull(), true))
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

                    // CALLORVAR decDigit
                    var _r32: _KotlinLexer_Item? = null
                    _r32 = _MemoCall(_memo, "decDigit", _index.element, ::decDigit, null)
                    if (_r32 != null) _index.element = _r32.nextIndex

                    _label = 7
                }
                // AND 7
                7 -> {
                    val _r7_2 = _memo.results.pop()
                    val _r7_1 = _memo.results.pop()

                    if (_r7_1 != null && _r7_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i7, _index.element, _memo.input, (_r7_1.results + _r7_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i7
                    }

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

                    // AND 33
                    _start_i33 = _index.element

                    // AND 34
                    _start_i34 = _index.element

                    // AND 35
                    _start_i35 = _index.element

                    // AND 36
                    _start_i36 = _index.element

                    // AND 37
                    _start_i37 = _index.element

                    // CALLORVAR decDigitNoZero
                    var _r38: _KotlinLexer_Item? = null
                    _r38 = _MemoCall(_memo, "decDigitNoZero", _index.element, ::decDigitNoZero, null)
                    if (_r38 != null) _index.element = _r38.nextIndex

                    // AND shortcut 37
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 37
                        continue
                    }

                    // PLUS 39
                    _start_i39 = _index.element
                    _label = 39
                }
                // PLUS 39
                39 -> {
                    // OR 40
                    _start_i40 = _index.element

                    // CALLORVAR decDigit
                    var _r41: _KotlinLexer_Item? = null
                    _r41 = _MemoCall(_memo, "decDigit", _index.element, ::decDigit, null)
                    if (_r41 != null) _index.element = _r41.nextIndex

                    // OR shortcut 40
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i40
                    } else {
                        _label = 40
                        continue
                    }

                    // LITERAL '_'
                    _ParseLiteralChar(_memo, _index, '_')

                    _label = 40
                }
                // OR 40
                40 -> {
                    // PLUS 39
                    val _r39 = _memo.results.pop()
                    if (_r39 != null) {
                        _res39 += _r39.results
                        _label = 39
                        continue
                    } else {
                        if (_index.element > _start_i39) {
                            _memo.results.push(_KotlinLexer_Item(_start_i39, _index.element, _memo.input, _res39.filterNotNull(), true))
                        } else {
                            _memo.results.push(null)
                        }
                    }

                    _label = 37
                }
                // AND 37
                37 -> {
                    val _r37_2 = _memo.results.pop()
                    val _r37_1 = _memo.results.pop()

                    if (_r37_1 != null && _r37_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i37, _index.element, _memo.input, (_r37_1.results + _r37_2.results).filterNotNull(), true))
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

                    // CALLORVAR decDigit
                    var _r43: _KotlinLexer_Item? = null
                    _r43 = _MemoCall(_memo, "decDigit", _index.element, ::decDigit, null)
                    if (_r43 != null) _index.element = _r43.nextIndex

                    _label = 36
                }
                // AND 36
                36 -> {
                    val _r36_2 = _memo.results.pop()
                    val _r36_1 = _memo.results.pop()

                    if (_r36_1 != null && _r36_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i36, _index.element, _memo.input, (_r36_1.results + _r36_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i36
                    }

                    // AND shortcut 35
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 35
                        continue
                    }

                    // OR 44
                    _start_i44 = _index.element

                    // LITERAL 'e'
                    _ParseLiteralChar(_memo, _index, 'e')

                    // OR shortcut 44
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i44
                    } else {
                        _label = 44
                        continue
                    }

                    // LITERAL 'E'
                    _ParseLiteralChar(_memo, _index, 'E')

                    _label = 44
                }
                // OR 44
                44 -> {
                    _label = 35
                }
                // AND 35
                35 -> {
                    val _r35_2 = _memo.results.pop()
                    val _r35_1 = _memo.results.pop()

                    if (_r35_1 != null && _r35_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i35, _index.element, _memo.input, (_r35_1.results + _r35_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i35
                    }

                    // AND shortcut 34
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 34
                        continue
                    }

                    // OR 48
                    _start_i48 = _index.element

                    // LITERAL '+'
                    _ParseLiteralChar(_memo, _index, '+')

                    // OR shortcut 48
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i48
                    } else {
                        _label = 48
                        continue
                    }

                    // LITERAL '-'
                    _ParseLiteralChar(_memo, _index, '-')

                    _label = 48
                }
                // OR 48
                48 -> {
                    // QUES 47
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_index.element, _memo.input))
                    }
                    _label = 34
                }
                // AND 34
                34 -> {
                    val _r34_2 = _memo.results.pop()
                    val _r34_1 = _memo.results.pop()

                    if (_r34_1 != null && _r34_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i34, _index.element, _memo.input, (_r34_1.results + _r34_2.results).filterNotNull(), true))
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

                    // PLUS 51
                    _start_i51 = _index.element
                    _label = 51
                }
                // PLUS 51
                51 -> {
                    // CALLORVAR decDigit
                    var _r52: _KotlinLexer_Item? = null
                    _r52 = _MemoCall(_memo, "decDigit", _index.element, ::decDigit, null)
                    if (_r52 != null) _index.element = _r52.nextIndex

                    // PLUS 51
                    val _r51 = _memo.results.pop()
                    if (_r51 != null) {
                        _res51 += _r51.results
                        _label = 51
                        continue
                    } else {
                        if (_index.element > _start_i51) {
                            _memo.results.push(_KotlinLexer_Item(_start_i51, _index.element, _memo.input, _res51.filterNotNull(), true))
                        } else {
                            _memo.results.push(null)
                        }
                    }

                    _label = 33
                }
                // AND 33
                33 -> {
                    val _r33_2 = _memo.results.pop()
                    val _r33_1 = _memo.results.pop()

                    if (_r33_1 != null && _r33_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i33, _index.element, _memo.input, (_r33_1.results + _r33_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i33
                    }

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

                    // AND 53
                    _start_i53 = _index.element

                    // AND 54
                    _start_i54 = _index.element

                    // AND 55
                    _start_i55 = _index.element

                    // AND 56
                    _start_i56 = _index.element

                    // AND 57
                    _start_i57 = _index.element

                    // AND 58
                    _start_i58 = _index.element

                    // CALLORVAR decDigitNoZero
                    var _r59: _KotlinLexer_Item? = null
                    _r59 = _MemoCall(_memo, "decDigitNoZero", _index.element, ::decDigitNoZero, null)
                    if (_r59 != null) _index.element = _r59.nextIndex

                    // AND shortcut 58
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 58
                        continue
                    }

                    // STAR 60
                    _start_i60 = _index.element
                    _label = 60
                }
                // STAR 60
                60 -> {
                    // CALLORVAR decDigit
                    var _r61: _KotlinLexer_Item? = null
                    _r61 = _MemoCall(_memo, "decDigit", _index.element, ::decDigit, null)
                    if (_r61 != null) _index.element = _r61.nextIndex

                    // STAR 60
                    val _r60 = _memo.results.pop()
                    if (_r60 != null) {
                        _res60 += _r60.results
                        _label = 60
                        continue
                    } else {
                        _memo.results.push(_KotlinLexer_Item(_start_i60, _index.element, _memo.input, _res60.filterNotNull(), true))
                    }

                    _label = 58
                }
                // AND 58
                58 -> {
                    val _r58_2 = _memo.results.pop()
                    val _r58_1 = _memo.results.pop()

                    if (_r58_1 != null && _r58_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i58, _index.element, _memo.input, (_r58_1.results + _r58_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i58
                    }

                    // AND shortcut 57
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 57
                        continue
                    }

                    // OR 62
                    _start_i62 = _index.element

                    // LITERAL 'e'
                    _ParseLiteralChar(_memo, _index, 'e')

                    // OR shortcut 62
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i62
                    } else {
                        _label = 62
                        continue
                    }

                    // LITERAL 'E'
                    _ParseLiteralChar(_memo, _index, 'E')

                    _label = 62
                }
                // OR 62
                62 -> {
                    _label = 57
                }
                // AND 57
                57 -> {
                    val _r57_2 = _memo.results.pop()
                    val _r57_1 = _memo.results.pop()

                    if (_r57_1 != null && _r57_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i57, _index.element, _memo.input, (_r57_1.results + _r57_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i57
                    }

                    // AND shortcut 56
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 56
                        continue
                    }

                    // OR 66
                    _start_i66 = _index.element

                    // LITERAL '+'
                    _ParseLiteralChar(_memo, _index, '+')

                    // OR shortcut 66
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i66
                    } else {
                        _label = 66
                        continue
                    }

                    // LITERAL '-'
                    _ParseLiteralChar(_memo, _index, '-')

                    _label = 66
                }
                // OR 66
                66 -> {
                    // QUES 65
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_index.element, _memo.input))
                    }
                    _label = 56
                }
                // AND 56
                56 -> {
                    val _r56_2 = _memo.results.pop()
                    val _r56_1 = _memo.results.pop()

                    if (_r56_1 != null && _r56_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i56, _index.element, _memo.input, (_r56_1.results + _r56_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i56
                    }

                    // AND shortcut 55
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 55
                        continue
                    }

                    // CALLORVAR decDigit
                    var _r69: _KotlinLexer_Item? = null
                    _r69 = _MemoCall(_memo, "decDigit", _index.element, ::decDigit, null)
                    if (_r69 != null) _index.element = _r69.nextIndex

                    _label = 55
                }
                // AND 55
                55 -> {
                    val _r55_2 = _memo.results.pop()
                    val _r55_1 = _memo.results.pop()

                    if (_r55_1 != null && _r55_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i55, _index.element, _memo.input, (_r55_1.results + _r55_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i55
                    }

                    // AND shortcut 54
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 54
                        continue
                    }

                    // PLUS 70
                    _start_i70 = _index.element
                    _label = 70
                }
                // PLUS 70
                70 -> {
                    // OR 71
                    _start_i71 = _index.element

                    // CALLORVAR decDigit
                    var _r72: _KotlinLexer_Item? = null
                    _r72 = _MemoCall(_memo, "decDigit", _index.element, ::decDigit, null)
                    if (_r72 != null) _index.element = _r72.nextIndex

                    // OR shortcut 71
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i71
                    } else {
                        _label = 71
                        continue
                    }

                    // LITERAL '_'
                    _ParseLiteralChar(_memo, _index, '_')

                    _label = 71
                }
                // OR 71
                71 -> {
                    // PLUS 70
                    val _r70 = _memo.results.pop()
                    if (_r70 != null) {
                        _res70 += _r70.results
                        _label = 70
                        continue
                    } else {
                        if (_index.element > _start_i70) {
                            _memo.results.push(_KotlinLexer_Item(_start_i70, _index.element, _memo.input, _res70.filterNotNull(), true))
                        } else {
                            _memo.results.push(null)
                        }
                    }

                    _label = 54
                }
                // AND 54
                54 -> {
                    val _r54_2 = _memo.results.pop()
                    val _r54_1 = _memo.results.pop()

                    if (_r54_1 != null && _r54_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i54, _index.element, _memo.input, (_r54_1.results + _r54_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i54
                    }

                    // AND shortcut 53
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 53
                        continue
                    }

                    // CALLORVAR decDigit
                    var _r74: _KotlinLexer_Item? = null
                    _r74 = _MemoCall(_memo, "decDigit", _index.element, ::decDigit, null)
                    if (_r74 != null) _index.element = _r74.nextIndex

                    _label = 53
                }
                // AND 53
                53 -> {
                    val _r53_2 = _memo.results.pop()
                    val _r53_1 = _memo.results.pop()

                    if (_r53_1 != null && _r53_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i53, _index.element, _memo.input, (_r53_1.results + _r53_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i53
                    }

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

                    // AND 75
                    _start_i75 = _index.element

                    // AND 76
                    _start_i76 = _index.element

                    // AND 77
                    _start_i77 = _index.element

                    // AND 78
                    _start_i78 = _index.element

                    // CALLORVAR decDigitNoZero
                    var _r79: _KotlinLexer_Item? = null
                    _r79 = _MemoCall(_memo, "decDigitNoZero", _index.element, ::decDigitNoZero, null)
                    if (_r79 != null) _index.element = _r79.nextIndex

                    // AND shortcut 78
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 78
                        continue
                    }

                    // STAR 80
                    _start_i80 = _index.element
                    _label = 80
                }
                // STAR 80
                80 -> {
                    // CALLORVAR decDigit
                    var _r81: _KotlinLexer_Item? = null
                    _r81 = _MemoCall(_memo, "decDigit", _index.element, ::decDigit, null)
                    if (_r81 != null) _index.element = _r81.nextIndex

                    // STAR 80
                    val _r80 = _memo.results.pop()
                    if (_r80 != null) {
                        _res80 += _r80.results
                        _label = 80
                        continue
                    } else {
                        _memo.results.push(_KotlinLexer_Item(_start_i80, _index.element, _memo.input, _res80.filterNotNull(), true))
                    }

                    _label = 78
                }
                // AND 78
                78 -> {
                    val _r78_2 = _memo.results.pop()
                    val _r78_1 = _memo.results.pop()

                    if (_r78_1 != null && _r78_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i78, _index.element, _memo.input, (_r78_1.results + _r78_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i78
                    }

                    // AND shortcut 77
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 77
                        continue
                    }

                    // OR 82
                    _start_i82 = _index.element

                    // LITERAL 'e'
                    _ParseLiteralChar(_memo, _index, 'e')

                    // OR shortcut 82
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i82
                    } else {
                        _label = 82
                        continue
                    }

                    // LITERAL 'E'
                    _ParseLiteralChar(_memo, _index, 'E')

                    _label = 82
                }
                // OR 82
                82 -> {
                    _label = 77
                }
                // AND 77
                77 -> {
                    val _r77_2 = _memo.results.pop()
                    val _r77_1 = _memo.results.pop()

                    if (_r77_1 != null && _r77_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i77, _index.element, _memo.input, (_r77_1.results + _r77_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i77
                    }

                    // AND shortcut 76
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 76
                        continue
                    }

                    // OR 86
                    _start_i86 = _index.element

                    // LITERAL '+'
                    _ParseLiteralChar(_memo, _index, '+')

                    // OR shortcut 86
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i86
                    } else {
                        _label = 86
                        continue
                    }

                    // LITERAL '-'
                    _ParseLiteralChar(_memo, _index, '-')

                    _label = 86
                }
                // OR 86
                86 -> {
                    // QUES 85
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _memo.results.push(_KotlinLexer_Item(_index.element, _memo.input))
                    }
                    _label = 76
                }
                // AND 76
                76 -> {
                    val _r76_2 = _memo.results.pop()
                    val _r76_1 = _memo.results.pop()

                    if (_r76_1 != null && _r76_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i76, _index.element, _memo.input, (_r76_1.results + _r76_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i76
                    }

                    // AND shortcut 75
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 75
                        continue
                    }

                    // PLUS 89
                    _start_i89 = _index.element
                    _label = 89
                }
                // PLUS 89
                89 -> {
                    // CALLORVAR decDigit
                    var _r90: _KotlinLexer_Item? = null
                    _r90 = _MemoCall(_memo, "decDigit", _index.element, ::decDigit, null)
                    if (_r90 != null) _index.element = _r90.nextIndex

                    // PLUS 89
                    val _r89 = _memo.results.pop()
                    if (_r89 != null) {
                        _res89 += _r89.results
                        _label = 89
                        continue
                    } else {
                        if (_index.element > _start_i89) {
                            _memo.results.push(_KotlinLexer_Item(_start_i89, _index.element, _memo.input, _res89.filterNotNull(), true))
                        } else {
                            _memo.results.push(null)
                        }
                    }

                    _label = 75
                }
                // AND 75
                75 -> {
                    val _r75_2 = _memo.results.pop()
                    val _r75_1 = _memo.results.pop()

                    if (_r75_1 != null && _r75_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i75, _index.element, _memo.input, (_r75_1.results + _r75_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i75
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

                    // AND 91
                    _start_i91 = _index.element

                    // AND 92
                    _start_i92 = _index.element

                    // CALLORVAR decDigitNoZero
                    var _r93: _KotlinLexer_Item? = null
                    _r93 = _MemoCall(_memo, "decDigitNoZero", _index.element, ::decDigitNoZero, null)
                    if (_r93 != null) _index.element = _r93.nextIndex

                    // AND shortcut 92
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 92
                        continue
                    }

                    // PLUS 94
                    _start_i94 = _index.element
                    _label = 94
                }
                // PLUS 94
                94 -> {
                    // OR 95
                    _start_i95 = _index.element

                    // CALLORVAR decDigit
                    var _r96: _KotlinLexer_Item? = null
                    _r96 = _MemoCall(_memo, "decDigit", _index.element, ::decDigit, null)
                    if (_r96 != null) _index.element = _r96.nextIndex

                    // OR shortcut 95
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i95
                    } else {
                        _label = 95
                        continue
                    }

                    // LITERAL '_'
                    _ParseLiteralChar(_memo, _index, '_')

                    _label = 95
                }
                // OR 95
                95 -> {
                    // PLUS 94
                    val _r94 = _memo.results.pop()
                    if (_r94 != null) {
                        _res94 += _r94.results
                        _label = 94
                        continue
                    } else {
                        if (_index.element > _start_i94) {
                            _memo.results.push(_KotlinLexer_Item(_start_i94, _index.element, _memo.input, _res94.filterNotNull(), true))
                        } else {
                            _memo.results.push(null)
                        }
                    }

                    _label = 92
                }
                // AND 92
                92 -> {
                    val _r92_2 = _memo.results.pop()
                    val _r92_1 = _memo.results.pop()

                    if (_r92_1 != null && _r92_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i92, _index.element, _memo.input, (_r92_1.results + _r92_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i92
                    }

                    // AND shortcut 91
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 91
                        continue
                    }

                    // CALLORVAR decDigit
                    var _r98: _KotlinLexer_Item? = null
                    _r98 = _MemoCall(_memo, "decDigit", _index.element, ::decDigit, null)
                    if (_r98 != null) _index.element = _r98.nextIndex

                    _label = 91
                }
                // AND 91
                91 -> {
                    val _r91_2 = _memo.results.pop()
                    val _r91_1 = _memo.results.pop()

                    if (_r91_1 != null && _r91_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i91, _index.element, _memo.input, (_r91_1.results + _r91_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i91
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

                    // AND 99
                    _start_i99 = _index.element

                    // CALLORVAR decDigitNoZero
                    var _r100: _KotlinLexer_Item? = null
                    _r100 = _MemoCall(_memo, "decDigitNoZero", _index.element, ::decDigitNoZero, null)
                    if (_r100 != null) _index.element = _r100.nextIndex

                    // AND shortcut 99
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 99
                        continue
                    }

                    // STAR 101
                    _start_i101 = _index.element
                    _label = 101
                }
                // STAR 101
                101 -> {
                    // CALLORVAR decDigit
                    var _r102: _KotlinLexer_Item? = null
                    _r102 = _MemoCall(_memo, "decDigit", _index.element, ::decDigit, null)
                    if (_r102 != null) _index.element = _r102.nextIndex

                    // STAR 101
                    val _r101 = _memo.results.pop()
                    if (_r101 != null) {
                        _res101 += _r101.results
                        _label = 101
                        continue
                    } else {
                        _memo.results.push(_KotlinLexer_Item(_start_i101, _index.element, _memo.input, _res101.filterNotNull(), true))
                    }

                    _label = 99
                }
                // AND 99
                99 -> {
                    val _r99_2 = _memo.results.pop()
                    val _r99_1 = _memo.results.pop()

                    if (_r99_1 != null && _r99_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i99, _index.element, _memo.input, (_r99_1.results + _r99_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i99
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


    open fun decDigit(_memo: _KotlinLexer_Memo, __index: Int, _args: _KotlinLexer_Args?) {
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

                    // LITERAL '0'
                    _ParseLiteralChar(_memo, _index, '0')

                    // OR shortcut 0
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i0
                    } else {
                        _label = 0
                        continue
                    }

                    // CALLORVAR decDigitNoZero
                    var _r2: _KotlinLexer_Item? = null
                    _r2 = _MemoCall(_memo, "decDigitNoZero", _index.element, ::decDigitNoZero, null)
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


    open fun decDigitNoZero(_memo: _KotlinLexer_Memo, __index: Int, _args: _KotlinLexer_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // INPUT CLASS
                    _ParseInputClass(_memo, _index, listOf('\u0031', '\u0032', '\u0033', '\u0034', '\u0035', '\u0036', '\u0037', '\u0038', '\u0039'))

                    break
                }
            }
        }
    }


    open fun hexLiteral(_memo: _KotlinLexer_Memo, __index: Int, _args: _KotlinLexer_Args?) {
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

        // OR 4
        var _start_i4 = _index.element

        // STAR 8
        var _start_i8 = _index.element
        val _inp8 = arrayListOf<Char?>()
        val _res8 = arrayListOf<Token?>()

        // OR 9
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

                    // LITERAL '0'
                    _ParseLiteralChar(_memo, _index, '0')

                    // AND shortcut 2
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 2
                        continue
                    }

                    // OR 4
                    _start_i4 = _index.element

                    // LITERAL 'x'
                    _ParseLiteralChar(_memo, _index, 'x')

                    // OR shortcut 4
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i4
                    } else {
                        _label = 4
                        continue
                    }

                    // LITERAL 'X'
                    _ParseLiteralChar(_memo, _index, 'X')

                    _label = 4
                }
                // OR 4
                4 -> {
                    _label = 2
                }
                // AND 2
                2 -> {
                    val _r2_2 = _memo.results.pop()
                    val _r2_1 = _memo.results.pop()

                    if (_r2_1 != null && _r2_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i2, _index.element, _memo.input, (_r2_1.results + _r2_2.results).filterNotNull(), true))
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

                    // CALLORVAR hexDigit
                    var _r7: _KotlinLexer_Item? = null
                    _r7 = _MemoCall(_memo, "hexDigit", _index.element, ::hexDigit, null)
                    if (_r7 != null) _index.element = _r7.nextIndex

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
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

                    // STAR 8
                    _start_i8 = _index.element
                    _label = 8
                }
                // STAR 8
                8 -> {
                    // OR 9
                    _start_i9 = _index.element

                    // CALLORVAR hexDigit
                    var _r10: _KotlinLexer_Item? = null
                    _r10 = _MemoCall(_memo, "hexDigit", _index.element, ::hexDigit, null)
                    if (_r10 != null) _index.element = _r10.nextIndex

                    // OR shortcut 9
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i9
                    } else {
                        _label = 9
                        continue
                    }

                    // LITERAL '_'
                    _ParseLiteralChar(_memo, _index, '_')

                    _label = 9
                }
                // OR 9
                9 -> {
                    // STAR 8
                    val _r8 = _memo.results.pop()
                    if (_r8 != null) {
                        _res8 += _r8.results
                        _label = 8
                        continue
                    } else {
                        _memo.results.push(_KotlinLexer_Item(_start_i8, _index.element, _memo.input, _res8.filterNotNull(), true))
                    }

                    _label = 0
                }
                // AND 0
                0 -> {
                    val _r0_2 = _memo.results.pop()
                    val _r0_1 = _memo.results.pop()

                    if (_r0_1 != null && _r0_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i0, _index.element, _memo.input, (_r0_1.results + _r0_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i0
                    }

                    break
                }
            }
        }
    }


    open fun hexDigit(_memo: _KotlinLexer_Memo, __index: Int, _args: _KotlinLexer_Args?) {
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

                    // CALLORVAR decDigit
                    var _r2: _KotlinLexer_Item? = null
                    _r2 = _MemoCall(_memo, "decDigit", _index.element, ::decDigit, null)
                    if (_r2 != null) _index.element = _r2.nextIndex

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


    open fun binLiteral(_memo: _KotlinLexer_Memo, __index: Int, _args: _KotlinLexer_Args?) {
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

        // OR 4
        var _start_i4 = _index.element

        // STAR 8
        var _start_i8 = _index.element
        val _inp8 = arrayListOf<Char?>()
        val _res8 = arrayListOf<Token?>()

        // OR 9
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

                    // LITERAL '0'
                    _ParseLiteralChar(_memo, _index, '0')

                    // AND shortcut 2
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 2
                        continue
                    }

                    // OR 4
                    _start_i4 = _index.element

                    // LITERAL 'b'
                    _ParseLiteralChar(_memo, _index, 'b')

                    // OR shortcut 4
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i4
                    } else {
                        _label = 4
                        continue
                    }

                    // LITERAL 'B'
                    _ParseLiteralChar(_memo, _index, 'B')

                    _label = 4
                }
                // OR 4
                4 -> {
                    _label = 2
                }
                // AND 2
                2 -> {
                    val _r2_2 = _memo.results.pop()
                    val _r2_1 = _memo.results.pop()

                    if (_r2_1 != null && _r2_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i2, _index.element, _memo.input, (_r2_1.results + _r2_2.results).filterNotNull(), true))
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

                    // CALLORVAR binDigit
                    var _r7: _KotlinLexer_Item? = null
                    _r7 = _MemoCall(_memo, "binDigit", _index.element, ::binDigit, null)
                    if (_r7 != null) _index.element = _r7.nextIndex

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
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

                    // STAR 8
                    _start_i8 = _index.element
                    _label = 8
                }
                // STAR 8
                8 -> {
                    // OR 9
                    _start_i9 = _index.element

                    // CALLORVAR binDigit
                    var _r10: _KotlinLexer_Item? = null
                    _r10 = _MemoCall(_memo, "binDigit", _index.element, ::binDigit, null)
                    if (_r10 != null) _index.element = _r10.nextIndex

                    // OR shortcut 9
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i9
                    } else {
                        _label = 9
                        continue
                    }

                    // LITERAL '_'
                    _ParseLiteralChar(_memo, _index, '_')

                    _label = 9
                }
                // OR 9
                9 -> {
                    // STAR 8
                    val _r8 = _memo.results.pop()
                    if (_r8 != null) {
                        _res8 += _r8.results
                        _label = 8
                        continue
                    } else {
                        _memo.results.push(_KotlinLexer_Item(_start_i8, _index.element, _memo.input, _res8.filterNotNull(), true))
                    }

                    _label = 0
                }
                // AND 0
                0 -> {
                    val _r0_2 = _memo.results.pop()
                    val _r0_1 = _memo.results.pop()

                    if (_r0_1 != null && _r0_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i0, _index.element, _memo.input, (_r0_1.results + _r0_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i0
                    }

                    break
                }
            }
        }
    }


    open fun binDigit(_memo: _KotlinLexer_Memo, __index: Int, _args: _KotlinLexer_Args?) {
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

                    // LITERAL '0'
                    _ParseLiteralChar(_memo, _index, '0')

                    // OR shortcut 0
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i0
                    } else {
                        _label = 0
                        continue
                    }

                    // LITERAL '1'
                    _ParseLiteralChar(_memo, _index, '1')

                    _label = 0
                }
                // OR 0
                0 -> {
                    break
                }
            }
        }
    }


    open fun characterLiteral(_memo: _KotlinLexer_Memo, __index: Int, _args: _KotlinLexer_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 0
        var _start_i0 = _index.element

        // AND 1
        var _start_i1 = _index.element

        // OR 3
        var _start_i3 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 0
                    _start_i0 = _index.element

                    // AND 1
                    _start_i1 = _index.element

                    // LITERAL '\''
                    _ParseLiteralChar(_memo, _index, '\'')

                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // OR 3
                    _start_i3 = _index.element

                    // CALLORVAR escapeSeq
                    var _r4: _KotlinLexer_Item? = null
                    _r4 = _MemoCall(_memo, "escapeSeq", _index.element, ::escapeSeq, null)
                    if (_r4 != null) _index.element = _r4.nextIndex

                    // OR shortcut 3
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i3
                    } else {
                        _label = 3
                        continue
                    }

                    // LITERAL '.'
                    _ParseLiteralChar(_memo, _index, '.')

                    _label = 3
                }
                // OR 3
                3 -> {
                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
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

                    // LITERAL '\''
                    _ParseLiteralChar(_memo, _index, '\'')

                    _label = 0
                }
                // AND 0
                0 -> {
                    val _r0_2 = _memo.results.pop()
                    val _r0_1 = _memo.results.pop()

                    if (_r0_1 != null && _r0_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i0, _index.element, _memo.input, (_r0_1.results + _r0_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i0
                    }

                    break
                }
            }
        }
    }


    open fun escapeSeq(_memo: _KotlinLexer_Memo, __index: Int, _args: _KotlinLexer_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // OR 0
        var _start_i0 = _index.element

        // AND 1
        var _start_i1 = _index.element

        // OR 3
        var _start_i3 = _index.element

        // OR 4
        var _start_i4 = _index.element

        // OR 5
        var _start_i5 = _index.element

        // OR 6
        var _start_i6 = _index.element

        // OR 7
        var _start_i7 = _index.element

        // OR 8
        var _start_i8 = _index.element

        // OR 9
        var _start_i9 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // OR 0
                    _start_i0 = _index.element

                    // AND 1
                    _start_i1 = _index.element

                    // LITERAL '\\'
                    _ParseLiteralChar(_memo, _index, '\\')

                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // OR 3
                    _start_i3 = _index.element

                    // OR 4
                    _start_i4 = _index.element

                    // OR 5
                    _start_i5 = _index.element

                    // OR 6
                    _start_i6 = _index.element

                    // OR 7
                    _start_i7 = _index.element

                    // OR 8
                    _start_i8 = _index.element

                    // OR 9
                    _start_i9 = _index.element

                    // LITERAL 't'
                    _ParseLiteralChar(_memo, _index, 't')

                    // OR shortcut 9
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i9
                    } else {
                        _label = 9
                        continue
                    }

                    // LITERAL 'b'
                    _ParseLiteralChar(_memo, _index, 'b')

                    _label = 9
                }
                // OR 9
                9 -> {
                    // OR shortcut 8
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i8
                    } else {
                        _label = 8
                        continue
                    }

                    // LITERAL 'r'
                    _ParseLiteralChar(_memo, _index, 'r')

                    _label = 8
                }
                // OR 8
                8 -> {
                    // OR shortcut 7
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i7
                    } else {
                        _label = 7
                        continue
                    }

                    // LITERAL 'n'
                    _ParseLiteralChar(_memo, _index, 'n')

                    _label = 7
                }
                // OR 7
                7 -> {
                    // OR shortcut 6
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i6
                    } else {
                        _label = 6
                        continue
                    }

                    // LITERAL '\''
                    _ParseLiteralChar(_memo, _index, '\'')

                    _label = 6
                }
                // OR 6
                6 -> {
                    // OR shortcut 5
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i5
                    } else {
                        _label = 5
                        continue
                    }

                    // LITERAL '"'
                    _ParseLiteralChar(_memo, _index, '"')

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

                    // LITERAL '\\'
                    _ParseLiteralChar(_memo, _index, '\\')

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

                    // LITERAL '$'
                    _ParseLiteralChar(_memo, _index, '$')

                    _label = 3
                }
                // OR 3
                3 -> {
                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
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

                    // CALLORVAR uniEscapeChar
                    var _r18: _KotlinLexer_Item? = null
                    _r18 = _MemoCall(_memo, "uniEscapeChar", _index.element, ::uniEscapeChar, null)
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


    open fun uniEscapeChar(_memo: _KotlinLexer_Memo, __index: Int, _args: _KotlinLexer_Args?) {
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

                    // LITERAL "\\u"
                    _ParseLiteralString(_memo, _index, "\\u")

                    // AND shortcut 3
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 3
                        continue
                    }

                    // CALLORVAR hexDigit
                    var _r5: _KotlinLexer_Item? = null
                    _r5 = _MemoCall(_memo, "hexDigit", _index.element, ::hexDigit, null)
                    if (_r5 != null) _index.element = _r5.nextIndex

                    _label = 3
                }
                // AND 3
                3 -> {
                    val _r3_2 = _memo.results.pop()
                    val _r3_1 = _memo.results.pop()

                    if (_r3_1 != null && _r3_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i3, _index.element, _memo.input, (_r3_1.results + _r3_2.results).filterNotNull(), true))
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

                    // CALLORVAR hexDigit
                    var _r6: _KotlinLexer_Item? = null
                    _r6 = _MemoCall(_memo, "hexDigit", _index.element, ::hexDigit, null)
                    if (_r6 != null) _index.element = _r6.nextIndex

                    _label = 2
                }
                // AND 2
                2 -> {
                    val _r2_2 = _memo.results.pop()
                    val _r2_1 = _memo.results.pop()

                    if (_r2_1 != null && _r2_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i2, _index.element, _memo.input, (_r2_1.results + _r2_2.results).filterNotNull(), true))
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

                    // CALLORVAR hexDigit
                    var _r7: _KotlinLexer_Item? = null
                    _r7 = _MemoCall(_memo, "hexDigit", _index.element, ::hexDigit, null)
                    if (_r7 != null) _index.element = _r7.nextIndex

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
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

                    // CALLORVAR hexDigit
                    var _r8: _KotlinLexer_Item? = null
                    _r8 = _MemoCall(_memo, "hexDigit", _index.element, ::hexDigit, null)
                    if (_r8 != null) _index.element = _r8.nextIndex

                    _label = 0
                }
                // AND 0
                0 -> {
                    val _r0_2 = _memo.results.pop()
                    val _r0_1 = _memo.results.pop()

                    if (_r0_1 != null && _r0_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i0, _index.element, _memo.input, (_r0_1.results + _r0_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i0
                    }

                    break
                }
            }
        }
    }


    open fun multiLineStringLiteral(_memo: _KotlinLexer_Memo, __index: Int, _args: _KotlinLexer_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 0
        var _start_i0 = _index.element

        // AND 1
        var _start_i1 = _index.element

        // STAR 3
        var _start_i3 = _index.element
        val _inp3 = arrayListOf<Char?>()
        val _res3 = arrayListOf<Token?>()

        // OR 4
        var _start_i4 = _index.element

        // OR 5
        var _start_i5 = _index.element

        // OR 6
        var _start_i6 = _index.element

        // OR 11
        var _start_i11 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 0
                    _start_i0 = _index.element

                    // AND 1
                    _start_i1 = _index.element

                    // LITERAL "\"\"\""
                    _ParseLiteralString(_memo, _index, "\"\"\"")

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
                    // OR 4
                    _start_i4 = _index.element

                    // OR 5
                    _start_i5 = _index.element

                    // OR 6
                    _start_i6 = _index.element

                    // CALLORVAR stringExpression
                    var _r7: _KotlinLexer_Item? = null
                    _r7 = _MemoCall(_memo, "stringExpression", _index.element, ::stringExpression, null)
                    if (_r7 != null) _index.element = _r7.nextIndex

                    // OR shortcut 6
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i6
                    } else {
                        _label = 6
                        continue
                    }

                    // CALLORVAR strRef
                    var _r8: _KotlinLexer_Item? = null
                    _r8 = _MemoCall(_memo, "strRef", _index.element, ::strRef, null)
                    if (_r8 != null) _index.element = _r8.nextIndex

                    _label = 6
                }
                // OR 6
                6 -> {
                    // OR shortcut 5
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i5
                    } else {
                        _label = 5
                        continue
                    }

                    // CALLORVAR multiLineStrText
                    var _r9: _KotlinLexer_Item? = null
                    _r9 = _MemoCall(_memo, "multiLineStrText", _index.element, ::multiLineStrText, null)
                    if (_r9 != null) _index.element = _r9.nextIndex

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

                    // CALLORVAR multiLineStringQuote
                    var _r10: _KotlinLexer_Item? = null
                    _r10 = _MemoCall(_memo, "multiLineStringQuote", _index.element, ::multiLineStringQuote, null)
                    if (_r10 != null) _index.element = _r10.nextIndex

                    _label = 4
                }
                // OR 4
                4 -> {
                    // STAR 3
                    val _r3 = _memo.results.pop()
                    if (_r3 != null) {
                        _res3 += _r3.results
                        _label = 3
                        continue
                    } else {
                        _memo.results.push(_KotlinLexer_Item(_start_i3, _index.element, _memo.input, _res3.filterNotNull(), true))
                    }

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
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

                    // OR 11
                    _start_i11 = _index.element

                    // LITERAL "\"\"\""
                    _ParseLiteralString(_memo, _index, "\"\"\"")

                    // OR shortcut 11
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i11
                    } else {
                        _label = 11
                        continue
                    }

                    // FAIL 13
                    _memo.results.push(null)
                    error("unclosed string literal" + ":\n" + formatErrorString(_memo, _index.element))

                    _label = 11
                }
                // OR 11
                11 -> {
                    _label = 0
                }
                // AND 0
                0 -> {
                    val _r0_2 = _memo.results.pop()
                    val _r0_1 = _memo.results.pop()

                    if (_r0_1 != null && _r0_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i0, _index.element, _memo.input, (_r0_1.results + _r0_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i0
                    }

                    break
                }
            }
        }
    }


    open fun stringExpression(_memo: _KotlinLexer_Memo, __index: Int, _args: _KotlinLexer_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 0
        var _start_i0 = _index.element

        // AND 1
        var _start_i1 = _index.element

        // LOOK 3
        var _start_i3 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 0
                    _start_i0 = _index.element

                    // AND 1
                    _start_i1 = _index.element

                    // LITERAL '$'
                    _ParseLiteralChar(_memo, _index, '$')

                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // LOOK 3
                    _start_i3 = _index.element

                    // LITERAL '{'
                    _ParseLiteralChar(_memo, _index, '{')

                    // LOOK 3
                    val _r3 = _memo.results.pop()
                    _memo.results.push(if (_r3 != null) _KotlinLexer_Item(_start_i3, _memo.input) else null)
                    _index.element = _start_i3

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
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

                    // CALLORVAR kotlinCode
                    var _r5: _KotlinLexer_Item? = null
                    _r5 = _MemoCall(_memo, "kotlinCode", _index.element, ::kotlinCode, null)
                    if (_r5 != null) _index.element = _r5.nextIndex

                    _label = 0
                }
                // AND 0
                0 -> {
                    val _r0_2 = _memo.results.pop()
                    val _r0_1 = _memo.results.pop()

                    if (_r0_1 != null && _r0_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i0, _index.element, _memo.input, (_r0_1.results + _r0_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i0
                    }

                    break
                }
            }
        }
    }


    open fun strRef(_memo: _KotlinLexer_Memo, __index: Int, _args: _KotlinLexer_Args?) {
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

                    // LITERAL '$'
                    _ParseLiteralChar(_memo, _index, '$')

                    // AND shortcut 0
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 0
                        continue
                    }

                    // CALLORVAR identifier
                    var _r2: _KotlinLexer_Item? = null
                    _r2 = _MemoCall(_memo, "identifier", _index.element, ::identifier, null)
                    if (_r2 != null) _index.element = _r2.nextIndex

                    _label = 0
                }
                // AND 0
                0 -> {
                    val _r0_2 = _memo.results.pop()
                    val _r0_1 = _memo.results.pop()

                    if (_r0_1 != null && _r0_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i0, _index.element, _memo.input, (_r0_1.results + _r0_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i0
                    }

                    break
                }
            }
        }
    }


    open fun multiLineStrText(_memo: _KotlinLexer_Memo, __index: Int, _args: _KotlinLexer_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // OR 0
        var _start_i0 = _index.element

        // AND 1
        var _start_i1 = _index.element

        // NOT 2
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

                    // AND 1
                    _start_i1 = _index.element

                    // NOT 2
                    _start_i2 = _index.element

                    // OR 3
                    _start_i3 = _index.element

                    // OR 4
                    _start_i4 = _index.element

                    // LITERAL '\\'
                    _ParseLiteralChar(_memo, _index, '\\')

                    // OR shortcut 4
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i4
                    } else {
                        _label = 4
                        continue
                    }

                    // LITERAL '"'
                    _ParseLiteralChar(_memo, _index, '"')

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

                    // LITERAL '$'
                    _ParseLiteralChar(_memo, _index, '$')

                    _label = 3
                }
                // OR 3
                3 -> {
                    // NOT 2
                    val _r2 = _memo.results.pop()
                    _memo.results.push(if (_r2 == null) _KotlinLexer_Item(_start_i2, _memo.input) else null)
                    _index.element = _start_i2
                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // ANY
                    _ParseAny(_memo, _index)

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
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

                    // LITERAL '$'
                    _ParseLiteralChar(_memo, _index, '$')

                    _label = 0
                }
                // OR 0
                0 -> {
                    break
                }
            }
        }
    }


    open fun multiLineStringQuote(_memo: _KotlinLexer_Memo, __index: Int, _args: _KotlinLexer_Args?) {
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

                    // LITERAL "\"\""
                    _ParseLiteralString(_memo, _index, "\"\"")

                    // OR shortcut 0
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i0
                    } else {
                        _label = 0
                        continue
                    }

                    // LITERAL '"'
                    _ParseLiteralChar(_memo, _index, '"')

                    _label = 0
                }
                // OR 0
                0 -> {
                    break
                }
            }
        }
    }


    open fun stringLiteral(_memo: _KotlinLexer_Memo, __index: Int, _args: _KotlinLexer_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 0
        var _start_i0 = _index.element

        // AND 1
        var _start_i1 = _index.element

        // STAR 3
        var _start_i3 = _index.element
        val _inp3 = arrayListOf<Char?>()
        val _res3 = arrayListOf<Token?>()

        // OR 4
        var _start_i4 = _index.element

        // OR 5
        var _start_i5 = _index.element

        // OR 6
        var _start_i6 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 0
                    _start_i0 = _index.element

                    // AND 1
                    _start_i1 = _index.element

                    // LITERAL '"'
                    _ParseLiteralChar(_memo, _index, '"')

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
                    // OR 4
                    _start_i4 = _index.element

                    // OR 5
                    _start_i5 = _index.element

                    // OR 6
                    _start_i6 = _index.element

                    // CALLORVAR stringExpression
                    var _r7: _KotlinLexer_Item? = null
                    _r7 = _MemoCall(_memo, "stringExpression", _index.element, ::stringExpression, null)
                    if (_r7 != null) _index.element = _r7.nextIndex

                    // OR shortcut 6
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i6
                    } else {
                        _label = 6
                        continue
                    }

                    // CALLORVAR strRef
                    var _r8: _KotlinLexer_Item? = null
                    _r8 = _MemoCall(_memo, "strRef", _index.element, ::strRef, null)
                    if (_r8 != null) _index.element = _r8.nextIndex

                    _label = 6
                }
                // OR 6
                6 -> {
                    // OR shortcut 5
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i5
                    } else {
                        _label = 5
                        continue
                    }

                    // CALLORVAR strEscapedChar
                    var _r9: _KotlinLexer_Item? = null
                    _r9 = _MemoCall(_memo, "strEscapedChar", _index.element, ::strEscapedChar, null)
                    if (_r9 != null) _index.element = _r9.nextIndex

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

                    // CALLORVAR strText
                    var _r10: _KotlinLexer_Item? = null
                    _r10 = _MemoCall(_memo, "strText", _index.element, ::strText, null)
                    if (_r10 != null) _index.element = _r10.nextIndex

                    _label = 4
                }
                // OR 4
                4 -> {
                    // STAR 3
                    val _r3 = _memo.results.pop()
                    if (_r3 != null) {
                        _res3 += _r3.results
                        _label = 3
                        continue
                    } else {
                        _memo.results.push(_KotlinLexer_Item(_start_i3, _index.element, _memo.input, _res3.filterNotNull(), true))
                    }

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
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

                    // LITERAL '"'
                    _ParseLiteralChar(_memo, _index, '"')

                    _label = 0
                }
                // AND 0
                0 -> {
                    val _r0_2 = _memo.results.pop()
                    val _r0_1 = _memo.results.pop()

                    if (_r0_1 != null && _r0_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i0, _index.element, _memo.input, (_r0_1.results + _r0_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i0
                    }

                    break
                }
            }
        }
    }


    open fun strText(_memo: _KotlinLexer_Memo, __index: Int, _args: _KotlinLexer_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // OR 0
        var _start_i0 = _index.element

        // AND 1
        var _start_i1 = _index.element

        // NOT 2
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

                    // AND 1
                    _start_i1 = _index.element

                    // NOT 2
                    _start_i2 = _index.element

                    // OR 3
                    _start_i3 = _index.element

                    // OR 4
                    _start_i4 = _index.element

                    // LITERAL '\\'
                    _ParseLiteralChar(_memo, _index, '\\')

                    // OR shortcut 4
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i4
                    } else {
                        _label = 4
                        continue
                    }

                    // LITERAL '"'
                    _ParseLiteralChar(_memo, _index, '"')

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

                    // LITERAL '$'
                    _ParseLiteralChar(_memo, _index, '$')

                    _label = 3
                }
                // OR 3
                3 -> {
                    // NOT 2
                    val _r2 = _memo.results.pop()
                    _memo.results.push(if (_r2 == null) _KotlinLexer_Item(_start_i2, _memo.input) else null)
                    _index.element = _start_i2
                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // ANY
                    _ParseAny(_memo, _index)

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
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

                    // LITERAL '$'
                    _ParseLiteralChar(_memo, _index, '$')

                    _label = 0
                }
                // OR 0
                0 -> {
                    break
                }
            }
        }
    }


    open fun strEscapedChar(_memo: _KotlinLexer_Memo, __index: Int, _args: _KotlinLexer_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // OR 0
        var _start_i0 = _index.element

        // AND 1
        var _start_i1 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // OR 0
                    _start_i0 = _index.element

                    // AND 1
                    _start_i1 = _index.element

                    // LITERAL '\\'
                    _ParseLiteralChar(_memo, _index, '\\')

                    // AND shortcut 1
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 1
                        continue
                    }

                    // ANY
                    _ParseAny(_memo, _index)

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
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

                    // CALLORVAR uniEscapeChar
                    var _r4: _KotlinLexer_Item? = null
                    _r4 = _MemoCall(_memo, "uniEscapeChar", _index.element, ::uniEscapeChar, null)
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


    open fun kotlinCode(_memo: _KotlinLexer_Memo, __index: Int, _args: _KotlinLexer_Args?) {
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
        val _res6 = arrayListOf<Token?>()

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
        val _res21 = arrayListOf<Token?>()

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
        val _res36 = arrayListOf<Token?>()

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
        val _res51 = arrayListOf<Token?>()

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
                    _memo.results.push(if (_r8 == null) _KotlinLexer_Item(_start_i8, _memo.input) else null)
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

                    // CALLORVAR NL
                    var _r13: _KotlinLexer_Item? = null
                    _r13 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r13 != null) _index.element = _r13.nextIndex

                    // OR shortcut 12
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i12
                    } else {
                        _label = 12
                        continue
                    }

                    // CALLORVAR comment
                    var _r14: _KotlinLexer_Item? = null
                    _r14 = _MemoCall(_memo, "comment", _index.element, ::comment, null)
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

                    // CALLORVAR kotlinCode
                    var _r15: _KotlinLexer_Item? = null
                    _r15 = _MemoCall(_memo, "kotlinCode", _index.element, ::kotlinCode, null)
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
                        _memo.results.push(_KotlinLexer_Item(_start_i7, _index.element, _memo.input, (_r7_1.results + _r7_2.results).filterNotNull(), true))
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
                        _memo.results.push(_KotlinLexer_Item(_start_i6, _index.element, _memo.input, _res6.filterNotNull(), true))
                    }

                    _label = 4
                }
                // AND 4
                4 -> {
                    val _r4_2 = _memo.results.pop()
                    val _r4_1 = _memo.results.pop()

                    if (_r4_1 != null && _r4_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i4, _index.element, _memo.input, (_r4_1.results + _r4_2.results).filterNotNull(), true))
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
                        _memo.results.push(_KotlinLexer_Item(_start_i3, _index.element, _memo.input, (_r3_1.results + _r3_2.results).filterNotNull(), true))
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
                    _memo.results.push(if (_r23 == null) _KotlinLexer_Item(_start_i23, _memo.input) else null)
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

                    // CALLORVAR NL
                    var _r28: _KotlinLexer_Item? = null
                    _r28 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r28 != null) _index.element = _r28.nextIndex

                    // OR shortcut 27
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i27
                    } else {
                        _label = 27
                        continue
                    }

                    // CALLORVAR comment
                    var _r29: _KotlinLexer_Item? = null
                    _r29 = _MemoCall(_memo, "comment", _index.element, ::comment, null)
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

                    // CALLORVAR kotlinCode
                    var _r30: _KotlinLexer_Item? = null
                    _r30 = _MemoCall(_memo, "kotlinCode", _index.element, ::kotlinCode, null)
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
                        _memo.results.push(_KotlinLexer_Item(_start_i22, _index.element, _memo.input, (_r22_1.results + _r22_2.results).filterNotNull(), true))
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
                        _memo.results.push(_KotlinLexer_Item(_start_i21, _index.element, _memo.input, _res21.filterNotNull(), true))
                    }

                    _label = 19
                }
                // AND 19
                19 -> {
                    val _r19_2 = _memo.results.pop()
                    val _r19_1 = _memo.results.pop()

                    if (_r19_1 != null && _r19_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i19, _index.element, _memo.input, (_r19_1.results + _r19_2.results).filterNotNull(), true))
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
                        _memo.results.push(_KotlinLexer_Item(_start_i18, _index.element, _memo.input, (_r18_1.results + _r18_2.results).filterNotNull(), true))
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

                    // CALLORVAR NL
                    var _r40: _KotlinLexer_Item? = null
                    _r40 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
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
                    _memo.results.push(if (_r44 == null) _KotlinLexer_Item(_start_i44, _memo.input) else null)
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
                        _memo.results.push(_KotlinLexer_Item(_start_i43, _index.element, _memo.input, (_r43_1.results + _r43_2.results).filterNotNull(), true))
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
                        _memo.results.push(_KotlinLexer_Item(_start_i36, _index.element, _memo.input, _res36.filterNotNull(), true))
                    }

                    _label = 34
                }
                // AND 34
                34 -> {
                    val _r34_2 = _memo.results.pop()
                    val _r34_1 = _memo.results.pop()

                    if (_r34_1 != null && _r34_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i34, _index.element, _memo.input, (_r34_1.results + _r34_2.results).filterNotNull(), true))
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
                        _memo.results.push(_KotlinLexer_Item(_start_i33, _index.element, _memo.input, (_r33_1.results + _r33_2.results).filterNotNull(), true))
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

                    // CALLORVAR NL
                    var _r55: _KotlinLexer_Item? = null
                    _r55 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
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
                    _memo.results.push(if (_r59 == null) _KotlinLexer_Item(_start_i59, _memo.input) else null)
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
                        _memo.results.push(_KotlinLexer_Item(_start_i58, _index.element, _memo.input, (_r58_1.results + _r58_2.results).filterNotNull(), true))
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
                        _memo.results.push(_KotlinLexer_Item(_start_i51, _index.element, _memo.input, _res51.filterNotNull(), true))
                    }

                    _label = 49
                }
                // AND 49
                49 -> {
                    val _r49_2 = _memo.results.pop()
                    val _r49_1 = _memo.results.pop()

                    if (_r49_1 != null && _r49_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i49, _index.element, _memo.input, (_r49_1.results + _r49_2.results).filterNotNull(), true))
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
                        _memo.results.push(_KotlinLexer_Item(_start_i48, _index.element, _memo.input, (_r48_1.results + _r48_2.results).filterNotNull(), true))
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


    open fun identifier(_memo: _KotlinLexer_Memo, __index: Int, _args: _KotlinLexer_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 0
        var _start_i0 = _index.element

        // STAR 2
        var _start_i2 = _index.element
        val _inp2 = arrayListOf<Char?>()
        val _res2 = arrayListOf<Token?>()

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 0
                    _start_i0 = _index.element

                    // CALLORVAR identifierStart
                    var _r1: _KotlinLexer_Item? = null
                    _r1 = _MemoCall(_memo, "identifierStart", _index.element, ::identifierStart, null)
                    if (_r1 != null) _index.element = _r1.nextIndex

                    // AND shortcut 0
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 0
                        continue
                    }

                    // STAR 2
                    _start_i2 = _index.element
                    _label = 2
                }
                // STAR 2
                2 -> {
                    // CALLORVAR identifierPart
                    var _r3: _KotlinLexer_Item? = null
                    _r3 = _MemoCall(_memo, "identifierPart", _index.element, ::identifierPart, null)
                    if (_r3 != null) _index.element = _r3.nextIndex

                    // STAR 2
                    val _r2 = _memo.results.pop()
                    if (_r2 != null) {
                        _res2 += _r2.results
                        _label = 2
                        continue
                    } else {
                        _memo.results.push(_KotlinLexer_Item(_start_i2, _index.element, _memo.input, _res2.filterNotNull(), true))
                    }

                    _label = 0
                }
                // AND 0
                0 -> {
                    val _r0_2 = _memo.results.pop()
                    val _r0_1 = _memo.results.pop()

                    if (_r0_1 != null && _r0_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i0, _index.element, _memo.input, (_r0_1.results + _r0_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i0
                    }

                    break
                }
            }
        }
    }


    open fun identifierStart(_memo: _KotlinLexer_Memo, __index: Int, _args: _KotlinLexer_Args?) {
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
                    val lambda0: (_KotlinLexer_Item) -> Boolean = { (it.i.isJavaIdentifierStart()) }
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


    open fun identifierPart(_memo: _KotlinLexer_Memo, __index: Int, _args: _KotlinLexer_Args?) {
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
                    val lambda0: (_KotlinLexer_Item) -> Boolean = { (it.i.isJavaIdentifierPart()) }
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


    open fun WS(_memo: _KotlinLexer_Memo, __index: Int, _args: _KotlinLexer_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // STAR 0
        var _start_i0 = _index.element
        val _inp0 = arrayListOf<Char?>()
        val _res0 = arrayListOf<Token?>()

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

                    // OR 3
                    _start_i3 = _index.element

                    // LITERAL '\u0020'
                    _ParseLiteralChar(_memo, _index, '\u0020')

                    // OR shortcut 3
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i3
                    } else {
                        _label = 3
                        continue
                    }

                    // LITERAL '\u0009'
                    _ParseLiteralChar(_memo, _index, '\u0009')

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

                    // LITERAL '\u000C'
                    _ParseLiteralChar(_memo, _index, '\u000C')

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

                    // CALLORVAR comment
                    var _r7: _KotlinLexer_Item? = null
                    _r7 = _MemoCall(_memo, "comment", _index.element, ::comment, null)
                    if (_r7 != null) _index.element = _r7.nextIndex

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
                        _memo.results.push(_KotlinLexer_Item(_start_i0, _index.element, _memo.input, _res0.filterNotNull(), true))
                    }

                    break
                }
            }
        }
    }


    open fun comment(_memo: _KotlinLexer_Memo, __index: Int, _args: _KotlinLexer_Args?) {
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

                    // CALLORVAR lineComment
                    var _r1: _KotlinLexer_Item? = null
                    _r1 = _MemoCall(_memo, "lineComment", _index.element, ::lineComment, null)
                    if (_r1 != null) _index.element = _r1.nextIndex

                    // OR shortcut 0
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i0
                    } else {
                        _label = 0
                        continue
                    }

                    // CALLORVAR delimetedComment
                    var _r2: _KotlinLexer_Item? = null
                    _r2 = _MemoCall(_memo, "delimetedComment", _index.element, ::delimetedComment, null)
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


    open fun lineComment(_memo: _KotlinLexer_Memo, __index: Int, _args: _KotlinLexer_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 0
        var _start_i0 = _index.element

        // STAR 2
        var _start_i2 = _index.element
        val _inp2 = arrayListOf<Char?>()
        val _res2 = arrayListOf<Token?>()

        // AND 3
        var _start_i3 = _index.element

        // NOT 4
        var _start_i4 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 0
                    _start_i0 = _index.element

                    // LITERAL "//"
                    _ParseLiteralString(_memo, _index, "//")

                    // AND shortcut 0
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 0
                        continue
                    }

                    // STAR 2
                    _start_i2 = _index.element
                    _label = 2
                }
                // STAR 2
                2 -> {
                    // AND 3
                    _start_i3 = _index.element

                    // NOT 4
                    _start_i4 = _index.element

                    // CALLORVAR NL
                    var _r5: _KotlinLexer_Item? = null
                    _r5 = _MemoCall(_memo, "NL", _index.element, ::NL, null)
                    if (_r5 != null) _index.element = _r5.nextIndex

                    // NOT 4
                    val _r4 = _memo.results.pop()
                    _memo.results.push(if (_r4 == null) _KotlinLexer_Item(_start_i4, _memo.input) else null)
                    _index.element = _start_i4
                    // AND shortcut 3
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 3
                        continue
                    }

                    // ANY
                    _ParseAny(_memo, _index)

                    _label = 3
                }
                // AND 3
                3 -> {
                    val _r3_2 = _memo.results.pop()
                    val _r3_1 = _memo.results.pop()

                    if (_r3_1 != null && _r3_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i3, _index.element, _memo.input, (_r3_1.results + _r3_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i3
                    }

                    // STAR 2
                    val _r2 = _memo.results.pop()
                    if (_r2 != null) {
                        _res2 += _r2.results
                        _label = 2
                        continue
                    } else {
                        _memo.results.push(_KotlinLexer_Item(_start_i2, _index.element, _memo.input, _res2.filterNotNull(), true))
                    }

                    _label = 0
                }
                // AND 0
                0 -> {
                    val _r0_2 = _memo.results.pop()
                    val _r0_1 = _memo.results.pop()

                    if (_r0_1 != null && _r0_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i0, _index.element, _memo.input, (_r0_1.results + _r0_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i0
                    }

                    break
                }
            }
        }
    }


    open fun delimetedComment(_memo: _KotlinLexer_Memo, __index: Int, _args: _KotlinLexer_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // AND 0
        var _start_i0 = _index.element

        // AND 1
        var _start_i1 = _index.element

        // STAR 3
        var _start_i3 = _index.element
        val _inp3 = arrayListOf<Char?>()
        val _res3 = arrayListOf<Token?>()

        // OR 4
        var _start_i4 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // AND 0
                    _start_i0 = _index.element

                    // AND 1
                    _start_i1 = _index.element

                    // LITERAL "/*"
                    _ParseLiteralString(_memo, _index, "/*")

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
                    // OR 4
                    _start_i4 = _index.element

                    // CALLORVAR delimetedComment
                    var _r5: _KotlinLexer_Item? = null
                    _r5 = _MemoCall(_memo, "delimetedComment", _index.element, ::delimetedComment, null)
                    if (_r5 != null) _index.element = _r5.nextIndex

                    // OR shortcut 4
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i4
                    } else {
                        _label = 4
                        continue
                    }

                    // ANY
                    _ParseAny(_memo, _index)

                    _label = 4
                }
                // OR 4
                4 -> {
                    // STAR 3
                    val _r3 = _memo.results.pop()
                    if (_r3 != null) {
                        _res3 += _r3.results
                        _label = 3
                        continue
                    } else {
                        _memo.results.push(_KotlinLexer_Item(_start_i3, _index.element, _memo.input, _res3.filterNotNull(), true))
                    }

                    _label = 1
                }
                // AND 1
                1 -> {
                    val _r1_2 = _memo.results.pop()
                    val _r1_1 = _memo.results.pop()

                    if (_r1_1 != null && _r1_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i1, _index.element, _memo.input, (_r1_1.results + _r1_2.results).filterNotNull(), true))
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

                    // LITERAL "*/"
                    _ParseLiteralString(_memo, _index, "*/")

                    _label = 0
                }
                // AND 0
                0 -> {
                    val _r0_2 = _memo.results.pop()
                    val _r0_1 = _memo.results.pop()

                    if (_r0_1 != null && _r0_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i0, _index.element, _memo.input, (_r0_1.results + _r0_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i0
                    }

                    break
                }
            }
        }
    }


    open fun NL(_memo: _KotlinLexer_Memo, __index: Int, _args: _KotlinLexer_Args?) {
        val _index = Ref.IntRef()
        _index.element = __index

        val _arg_index = Ref.IntRef()
        val _arg_input_index = Ref.IntRef()

        // OR 0
        var _start_i0 = _index.element

        // AND 2
        var _start_i2 = _index.element

        var _label = -1
        while(true) {
            when(_label) {
                -1 -> {
                    // OR 0
                    _start_i0 = _index.element

                    // LITERAL '\n'
                    _ParseLiteralChar(_memo, _index, '\n')

                    // OR shortcut 0
                    if (_memo.results.peek() == null) {
                        _memo.results.pop()
                        _index.element = _start_i0
                    } else {
                        _label = 0
                        continue
                    }

                    // AND 2
                    _start_i2 = _index.element

                    // LITERAL '\r'
                    _ParseLiteralChar(_memo, _index, '\r')

                    // AND shortcut 2
                    if (_memo.results.peek() == null) {
                        _memo.results.push(null)
                        _label = 2
                        continue
                    }

                    // LITERAL '\n'
                    _ParseLiteralChar(_memo, _index, '\n')

                    _label = 2
                }
                // AND 2
                2 -> {
                    val _r2_2 = _memo.results.pop()
                    val _r2_1 = _memo.results.pop()

                    if (_r2_1 != null && _r2_2 != null) {
                        _memo.results.push(_KotlinLexer_Item(_start_i2, _index.element, _memo.input, (_r2_1.results + _r2_2.results).filterNotNull(), true))
                    } else {
                        _memo.results.push(null)
                        _index.element = _start_i2
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


    open fun EOF(_memo: _KotlinLexer_Memo, __index: Int, _args: _KotlinLexer_Args?) {
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
                    _memo.results.push(if (_r0 == null) _KotlinLexer_Item(_start_i0, _memo.input) else null)
                    _index.element = _start_i0
                    break
                }
            }
        }
    }

}
