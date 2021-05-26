package kometa.bootstrap

import kometa.MatchItem
import kometa.ast.AST

val bootstrapAst by lazy {
    AST.GrammarFile(
        AST.ImportList(
            listOf(
                AST.Idfr("kometa.Matcher".i),
                AST.Idfr("kometa.ast.AST".i)
            )
        ),
        AST.Grammar(
            "Parser".i,
            "Char".i,
            "AST.AstNode".i,
            "Matcher<Char, AST.AstNode>".i,
            rules = listOf(
                AST.Rule(
                    name = "KOMetaFile",
                    body = AST.Args(
                        params = null,
                        body = AST.Act(
                            body = listOf(
                                "SP".callorvar,
                                AST.Bind(
                                    body = AST.Ques("ImportsList".callorvar),
                                    varName = "il".i
                                ),
                                AST.Bind(
                                    body = "Grammar".callorvar,
                                    varName = "g".i
                                ),
                                "SP".callorvar,
                                "EOF".callorvar
                            ).reduce { prev, cur -> AST.And(prev, cur) },
                            expression = "AST.GrammarFile(il?.asResult()!!, g?.asResult()!!)".i
                        ),
                    )
                ),
                AST.Rule(
                    name = "ImportsList",
                    body = AST.Args(
                        params = null,
                        body = AST.Act(
                            body = AST.Bind(
                                body = AST.Plus("Import".callorvar),
                                varName = "i".i
                            ),
                            expression = "AST.ImportList(i!!.results.filterNotNull())".i
                        )
                    )
                ),
                AST.Rule(
                    name = "Import",
                    body = AST.Args(
                        params = null,
                        body = AST.Act(
                            body = listOf(
                                "USING".callorvar,
                                AST.Bind(
                                    body = AST.And(
                                        "Ident".callorvar,
                                        AST.Star(AST.And("DOT".callorvar, "Ident".callorvar))
                                    ),
                                    varName = "name".i
                                ),
                                "SP".callorvar,
                                "SEMI".callorvar
                            ).reduce { prev, cur -> AST.And(prev, cur) },
                            expression = "AST.Import(name!!)".i
                        )
                    )
                ),
                AST.Rule(
                    name = "Grammar",
                    body = AST.Args(
                        params = null,
                        body = AST.Act(
                            body = listOf(
                                "KOMETA".callorvar,
                                AST.Bind(
                                    body = "Identifier".callorvar,
                                    varName = "name".i
                                ),
                                "LESS".callorvar,
                                AST.Bind("GenericId".callorvar, "tinput".i),
                                "SP".callorvar,
                                "COMMA".callorvar,
                                AST.Bind("GenericId".callorvar, "tresult".i),
                                "SP".callorvar,
                                "GREATER".callorvar,
                                AST.Ques(
                                    listOf(
                                        "COLON".callorvar,
                                        AST.Bind("GenericId".callorvar, "baseclass".i),
                                        "SP".callorvar
                                    ).reduce { prev, cur -> AST.And(prev, cur) }
                                ),
                                "LEFT_BRACE".callorvar,
                                AST.Bind(AST.Star("Rule".callorvar), "rules".i),
                                "RIGHT_BRACE".callorvar
                            ).reduce { prev, cur -> AST.And(prev, cur) },
                            expression = "AST.Grammar(name!!, tinput!!, tresult!!, baseclass, rules!!.results.filterIsInstance<AST.Rule>())".i
                        )
                    )
                ),
                AST.Rule(
                    name = "Rule",
                    body = AST.Args(
                        params = null,
                        body = AST.Act(
                            body = listOf(
                                AST.Bind(AST.Ques("OVERRIDE".callorvar), "ovr".i),
                                AST.Bind("Identifier".callorvar, "name".i),
                                AST.Bind(AST.Ques("Disjunction".callorvar), "parms".i),
                                "EQUALS".callorvar,
                                AST.Bind("Disjunction".callorvar, "body".i),
                                "SEMI".callorvar
                            ).reduce { prev, cur -> AST.And(prev, cur) },
                            expression = """AST.Rule(name!!, if (parms != null && parms.inputs.any()) AST.Args(parms.asResult(), body?.asResult()!!) else body?.asResult()!!, ovr?.inputs?.joinToString("") ?: "")""".i
                        )
                    )
                ),
                AST.Rule(
                    name = "Disjunction",
                    body = AST.Args(
                        params = null,
                        body = AST.Act(
                            body = listOf(
                                "ActionExp".callorvar,
                                AST.Star(AST.And("OR".callorvar, "ActionExp".callorvar))
                            ).reduce { prev, cur -> AST.And(prev, cur) },
                            expression = "it.results.filterNotNull().reduce{ prev, cur -> AST.Or(prev, cur) }".i
                        )
                    )
                ),
                AST.Rule(
                    name = "ActionExp",
                    body = AST.Args(
                        params = null,
                        body = AST.Or(
                            AST.Act(
                                body = listOf(
                                    AST.Bind("SequenceExp".callorvar, "body".i),
                                    "ACTION".callorvar,
                                    AST.Look("LEFT_BRACE".callorvar),
                                    AST.Bind("KotlinCode".callorvar, "action".i)
                                ).reduce { prev, cur -> AST.And(prev, cur) },
                                expression = "AST.Act(body?.asResult()!!, action!!)".i
                            ),
                            "SequenceExp".callorvar
                        )
                    )
                ),
                AST.Rule(
                    name = "FailExp",
                    body = AST.Args(
                        params = null,
                        body = AST.Act(
                            body = listOf(
                                "BANG".callorvar,
                                AST.Ques(AST.Bind("KotlinCode".callorvar, "msg".i))
                            ).reduce { prev, cur -> AST.And(prev, cur) },
                            expression = """AST.Fail(msg?.asResult()!!)""".i
                        )
                    )
                ),
                AST.Rule(
                    name = "SequenceExp",
                    body = AST.Args(
                        params = null,
                        body = AST.Act(
                            body = AST.Plus("ConditionExp".callorvar),
                            expression = """it.results.filterNotNull().reduce { prev, cur -> AST.And(prev, cur) }""".i
                        )
                    )
                ),
                AST.Rule(
                    name = "ConditionExp",
                    body = AST.Args(
                        params = null,
                        body = "FailExp".callorvar
                    )
                ),
                AST.Rule(
                    name = "ConditionExp",
                    body = AST.Args(
                        params = null,
                        body = AST.Act(
                            body = listOf(
                                AST.Bind("BoundTerm".callorvar, "body".i),
                                "QUES".callorvar,
                                AST.Look("OPEN".callorvar),
                                AST.Bind("KotlinCode".callorvar, "cond".i)
                            ).reduce { prev, cur -> AST.And(prev, cur) },
                            expression = """AST.Cond(body?.asResult()!!, cond!!)""".i
                        )
                    )
                ),
                AST.Rule(
                    name = "ConditionExp",
                    body = AST.Args(
                        params = null,
                        body = "BoundTerm".callorvar
                    )
                ),
                AST.Rule(
                    name = "BoundTerm",
                    body = AST.Args(
                        params = null,
                        body = AST.Act(
                            body = listOf(
                                AST.Bind("PrefixedTerm".callorvar, "exp".i),
                                "COLON".callorvar,
                                AST.Bind("Identifier".callorvar, "id".i)
                            ).reduce { prev, cur -> AST.And(prev, cur) },
                            expression = """AST.Bind(exp?.asResult()!!, id!!)""".i
                        )
                    )
                ),
                AST.Rule(
                    name = "BoundTerm",
                    body = AST.Args(
                        params = null,
                        body = AST.Act(
                            body = listOf(
                                "COLON".callorvar,
                                AST.Bind("Identifier".callorvar, "id".i)
                            ).reduce { prev, cur -> AST.And(prev, cur) },
                            expression = """AST.Bind(AST.Any, id!!)""".i
                        )
                    )
                ),
                AST.Rule(
                    name = "BoundTerm",
                    body = AST.Args(
                        params = null,
                        body = "PrefixedTerm".callorvar
                    )
                ),
                AST.Rule(
                    name = "PrefixedTerm",
                    body = AST.Args(
                        params = null,
                        body = listOf(
                            "LookTerm".callorvar,
                            "NotTerm".callorvar,
                            "PostfixedTerm".callorvar,
                        ).reduce { prev: AST.AstNode, cur: AST.AstNode -> AST.Or(prev, cur) }
                    )
                ),
                AST.Rule(
                    name = "LookTerm",
                    body = AST.Args(
                        params = null,
                        body = AST.Act(
                            body = listOf(
                                "AND_PRE".callorvar,
                                AST.Bind("PostfixedTerm".callorvar, "exp".i)
                            ).reduce { prev, cur -> AST.And(prev, cur) },
                            expression = """AST.Look(exp?.asResult()!!)""".i
                        )
                    )
                ),
                AST.Rule(
                    name = "NotTerm",
                    body = AST.Args(
                        params = null,
                        body = AST.Act(
                            body = listOf(
                                "NOT_PRE".callorvar,
                                AST.Bind("PostfixedTerm".callorvar, "exp".i)
                            ).reduce { prev, cur -> AST.And(prev, cur) },
                            expression = """AST.Not(exp?.asResult()!!)""".i
                        )
                    )
                ),
                AST.Rule(
                    name = "PostfixedTerm",
                    body = AST.Args(
                        params = null,
                        body = listOf(
                            "MinMaxTerm".callorvar,
                            "StarTerm".callorvar,
                            "PlusTerm".callorvar,
                            "QuesTerm".callorvar,
                            "Term".callorvar,
                        ).reduce { prev: AST.AstNode, cur: AST.AstNode -> AST.Or(prev, cur) }
                    )
                ),
                AST.Rule(
                    name = "MinMaxTerm",
                    body = AST.Args(
                        params = null,
                        body = AST.Act(
                            body = listOf(
                                AST.Bind("Term".callorvar, "exp".i),
                                "LEFT_BRACE".callorvar,
                                AST.Bind("Number".callorvar, "n".i),
                                AST.Ques(AST.And("COMMA".callorvar, AST.Bind("Number".callorvar, "x".i))),
                                "RIGHT_BRACE".callorvar
                            ).reduce { prev, cur -> AST.And(prev, cur) },
                            expression = """
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
""".i
                        )
                    )
                ),
                AST.Rule(
                    name = "StarTerm",
                    body = AST.Args(
                        params = null,
                        body = AST.Act(
                            body = listOf(
                                AST.Bind("Term".callorvar, "exp".i),
                                "STAR".callorvar,
                            ).reduce { prev, cur -> AST.And(prev, cur) },
                            expression = """AST.Star(exp?.asResult()!!)""".i
                        )
                    )
                ),
                AST.Rule(
                    name = "PlusTerm",
                    body = AST.Args(
                        params = null,
                        body = AST.Act(
                            body = listOf(
                                AST.Bind("Term".callorvar, "exp".i),
                                "PLUS".callorvar,
                            ).reduce { prev, cur -> AST.And(prev, cur) },
                            expression = """AST.Plus(exp?.asResult()!!)""".i
                        )
                    )
                ),
                AST.Rule(
                    name = "QuesTerm",
                    body = AST.Args(
                        params = null,
                        body = AST.Act(
                            body = listOf(
                                AST.Bind("Term".callorvar, "exp".i),
                                "QUES".callorvar,
                                AST.Not("OPEN".callorvar),
                                "SP".callorvar
                            ).reduce { prev, cur -> AST.And(prev, cur) },
                            expression = """AST.Ques(exp?.asResult()!!)""".i
                        )
                    )
                ),
                AST.Rule(
                    name = "Term",
                    body = AST.Args(
                        params = null,
                        body = listOf(
                            "InputClass".callorvar,
                            "ParenTerm".callorvar,
                            "RuleCall".callorvar,
                            "CallOrVar".callorvar,
                            "Literal".callorvar,
                            "AnyTerm".callorvar,
                        ).reduce { prev: AST.AstNode, cur: AST.AstNode -> AST.Or(prev, cur) }
                    )
                ),
                AST.Rule(
                    name = "ParenTerm",
                    body = AST.Args(
                        params = null,
                        body = AST.Act(
                            body = listOf(
                                "OPEN".callorvar,
                                AST.Bind("Disjunction".callorvar, "exp".i),
                                "CLOSE".callorvar
                            ).reduce { prev, cur -> AST.And(prev, cur) },
                            expression = """exp?.asResult()!!""".i
                        )
                    )
                ),
                AST.Rule(
                    name = "AnyTerm",
                    body = AST.Args(
                        params = null,
                        body = AST.Act(
                            body = "PERIOD".callorvar,
                            expression = """AST.Any""".i
                        )
                    )
                ),
                AST.Rule(
                    name = "RuleCall",
                    body = AST.Args(
                        params = null,
                        body = AST.Act(
                            body = listOf(
                                AST.Bind("QualifiedId".callorvar, "name".i),
                                "OPEN".callorvar,
                                AST.Bind(AST.Ques("ParameterList".callorvar), "p".i),
                                "CLOSE".callorvar
                            ).reduce { prev, cur -> AST.And(prev, cur) },
                            expression = """AST.Call(name!!, p?.results?.filterNotNull() ?: emptyList())""".i
                        )
                    )
                ),
                AST.Rule(
                    name = "ParameterList",
                    body = AST.Args(
                        params = null,
                        body = AST.Act(
                            body = listOf(
                                "Parameter".callorvar,
                                AST.Star(AST.And("COMMA".callorvar, "Parameter".callorvar))
                            ).reduce { prev, cur -> AST.And(prev, cur) },
                            expression = """it.results.filterNotNull()""".i
                        )
                    )
                ),
                AST.Rule(
                    name = "Parameter",
                    body = AST.Args(
                        params = null,
                        body = "Disjunction".callorvar
                    )
                ),
                AST.Rule(
                    name = "CallOrVar",
                    body = AST.Args(
                        params = null,
                        body = AST.Act(
                            body = listOf(
                                AST.Not("KEYWORD".callorvar),
                                AST.Bind("QualifiedId".callorvar, "name".i),
                                "SP".callorvar,
                            ).reduce { prev, cur -> AST.And(prev, cur) },
                            expression = """AST.CallOrVar(name!!)""".i
                        )
                    )
                ),
                AST.Rule(
                    name = "Literal",
                    body = AST.Args(
                        params = null,
                        body = AST.Or(
                            AST.Cond(
                                AST.Bind("Regexp".callorvar, "r".i),
                                "AST.Regexp.isValid(r?.inputs?.joinToString(\"\")!!)".i
                            ),
                            AST.Act(
                                body = AST.And(
                                    AST.Or(
                                        AST.And(
                                            AST.And(
                                                "NEW".callorvar,
                                                AST.Ques(AST.And("GenericId".callorvar, "SP".callorvar))
                                            ),
                                            AST.Look("{".code)
                                        ),
                                        AST.Look(
                                            AST.Or(
                                                AST.Or(
                                                    "\\u0022".code,
                                                    "\\u0027".code,
                                                ),
                                                "{".code
                                            )
                                        )
                                    ),
                                    "KotlinCode".callorvar
                                ),
                                expression = """AST.Code(it)""".i
                            ),
                        )
                    )
                ),
                AST.Rule(
                    name = "Regexp",
                    body = AST.Args(
                        params = null,
                        body = listOf(
                            "/".code,
                            AST.Plus(
                                AST.Or(
                                    AST.And("\\\\".code, "/".code),
                                    AST.And(AST.Not("//".code), AST.Any)
                                )
                            ),
                            "SP".callorvar,
                        ).reduce { prev, cur -> AST.And(prev, cur) }
                    )
                ),
                AST.Rule(
                    name = "InputClass",
                    body = AST.Args(
                        params = null,
                        body = AST.Act(
                            body = listOf(
                                "[".code,
                                "SP".callorvar,
                                AST.Bind(
                                    AST.Or(
                                        "ClassRange".callorvar,
                                        AST.And(AST.Look("\\u0027".code), "Literal".callorvar)
                                    ), "c".i
                                ),
                                "]".code,
                                "SP".callorvar,
                            ).reduce { prev, cur -> AST.And(prev, cur) },
                            expression = """AST.InputClass(c!!.results.filterNotNull())""".i
                        )
                    )
                ),
                AST.Rule(
                    name = "ClassRange",
                    body = AST.Args(
                        params = null,
                        body = AST.Act(
                            body = listOf(
                                AST.Look("\\u0027".code),
                                AST.Bind("Literal".callorvar, "from".i),
                                "SP".callorvar,
                                "-".code,
                                "SP".callorvar,
                                AST.Look("\\u0027".code),
                                AST.Bind("Literal".callorvar, "to".i),
                                "SP".callorvar,
                            ).reduce { prev, cur -> AST.And(prev, cur) },
                            expression = """
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
                            """.i
                        )
                    )
                ),
                AST.Rule(
                    name = "KotlinCode",
                    body = AST.Args(
                        params = null,
                        body = AST.Act(
                            body = listOf(
                                AST.Bind("KotlinCodeItem".callorvar, "code".i),
                                "SP".callorvar
                            ).reduce { prev, cur -> AST.And(prev, cur) },
                            expression = """AST.Code(code!!)""".i
                        )
                    )
                ),
                AST.Rule(
                    name = "KotlinCodeItem",
                    body = AST.Args(
                        params = null,
                        body = listOf(
                            "{".code,
                            AST.Star(
                                AST.And(
                                    AST.Not("}".code),
                                    listOf(
                                        "EOL".callorvar,
                                        "Comment".callorvar,
                                        "KotlinCodeItem".callorvar,
                                        AST.Any
                                    ).reduce { prev, cur -> AST.Or(prev, cur) },
                                )
                            ),
                            "}".code
                        ).reduce { prev, cur -> AST.And(prev, cur) },
                    )
                ),
                AST.Rule(
                    name = "KotlinCodeItem",
                    body = AST.Args(
                        params = null,
                        body = listOf(
                            "(".code,
                            AST.Star(
                                AST.And(
                                    AST.Not(")".code),
                                    listOf(
                                        "EOL".callorvar,
                                        "Comment".callorvar,
                                        "KotlinCodeItem".callorvar,
                                        AST.Any
                                    ).reduce { prev, cur -> AST.Or(prev, cur) },
                                )
                            ),
                            ")".code
                        ).reduce { prev, cur -> AST.And(prev, cur) },
                    )
                ),
                AST.Rule(
                    name = "KotlinCodeItem",
                    body = AST.Args(
                        params = null,
                        body = listOf(
                            "\\u0027".code,
                            AST.Star(
                                listOf(
                                    "EOL".callorvar,
                                    "\\u005c\\u0027".code,
                                    "\\u005c\\u005c".code,
                                    AST.And(
                                        AST.Not("\\u0027".code),
                                        AST.Any
                                    )
                                ).reduce { prev, cur -> AST.Or(prev, cur) },
                            ),
                            "\\u0027".code,
                        ).reduce { prev, cur -> AST.And(prev, cur) },
                    )
                ),
                AST.Rule(
                    name = "KotlinCodeItem",
                    body = AST.Args(
                        params = null,
                        body = listOf(
                            "\\u0022".code,
                            AST.Star(
                                listOf(
                                    "EOL".callorvar,
                                    "\\u005c\\u0022".code,
                                    "\\u005c\\u005c".code,
                                    AST.And(
                                        AST.Not("\\u0022".code),
                                        AST.Any
                                    )
                                ).reduce { prev, cur -> AST.Or(prev, cur) },
                            ),
                            "\\u0022".code,
                        ).reduce { prev, cur -> AST.And(prev, cur) },
                    )
                ),
                AST.Rule(
                    name = "Identifier",
                    body = AST.Args(
                        params = null,
                        body = AST.Act(
                            body = listOf(
                                AST.Bind("Ident".callorvar, "id".i),
                                "SP".callorvar
                            ).reduce { prev, cur -> AST.And(prev, cur) },
                            expression = """id?.asResult()!!""".i
                        )
                    )
                ),
                AST.Rule(
                    name = "Ident",
                    body = AST.Args(
                        params = null,
                        body = AST.Act(
                            body = AST.Bind(
                                AST.And(
                                    "IdentBegin".callorvar,
                                    AST.Star("IdentBody".callorvar)
                                ),
                                "id".i
                            ),
                            expression = """AST.Idfr(id!!)""".i
                        )
                    )
                ),
                AST.Rule(
                    name = "IdentBegin",
                    body = AST.Args(
                        params = null,
                        body = AST.Cond(AST.Any, "it.asInput().isJavaIdentifierStart()".i)
                    )
                ),
                AST.Rule(
                    name = "IdentBody",
                    body = AST.Args(
                        params = null,
                        body = AST.Cond(AST.Any, "it.asInput().isJavaIdentifierPart()".i)
                    )
                ),
                AST.Rule(
                    name = "QualifiedId",
                    body = AST.Args(
                        params = null,
                        body = AST.Act(
                            body = AST.Bind(
                                AST.And(
                                    "Ident".callorvar,
                                    AST.Star(
                                        AST.And(
                                            "DOT".callorvar,
                                            "Ident".callorvar
                                        )
                                    )
                                ),
                                "id".i
                            ),
                            expression = """AST.Idfr(id!!)""".i
                        )
                    )
                ),
                AST.Rule(
                    name = "GenericId",
                    body = AST.Args(
                        params = null,
                        body = AST.Act(
                            body = AST.And(
                                AST.Bind(
                                    AST.And(
                                        "Ident".callorvar,
                                        AST.Star(
                                            AST.And(
                                                "DOT".callorvar,
                                                "Ident".callorvar
                                            )
                                        )
                                    ),
                                    "ids".i
                                ),
                                AST.Bind(
                                    AST.Star(
                                        listOf(
                                            "LESS".callorvar,
                                            listOf(
                                                "GenericId".callorvar,
                                                "SP".callorvar,
                                                AST.Star(
                                                    listOf(
                                                        "COMMA".callorvar,
                                                        "GenericId".callorvar,
                                                        "SP".callorvar,
                                                    ).reduce { prev: AST.AstNode, cur: AST.AstNode ->
                                                        AST.And(
                                                            prev,
                                                            cur
                                                        )
                                                    }
                                                )
                                            ).reduce { prev, cur -> AST.And(prev, cur) },
                                            "GREATER".callorvar
                                        ).reduce { prev, cur -> AST.And(prev, cur) }
                                    ),
                                    "gp".i
                                )
                            ),
                            expression = """AST.Idfr(ids!!, gp)""".i
                        )
                    )
                ),
                AST.Rule(
                    name = "Number",
                    body = AST.Args(
                        params = null,
                        body = AST.And(
                            AST.Star(AST.InputClass(('0'..'9').toList().map { AST.Code("'$it'".i) })),
                            "SP".callorvar
                        )
                    )
                ),
                AST.Rule(
                    name = "KEYWORD",
                    body = AST.Args(
                        params = null,
                        body = listOf(
                            "USING".callorvar,
                            "KOMETA".callorvar,
                            "OVERRIDE".callorvar,
                            "NEW".callorvar,
                            "LR".callorvar,
                        ).reduce { prev: AST.AstNode, cur: AST.AstNode -> AST.Or(prev, cur) },
                    )
                ),
                AST.Rule(
                    name = "USING",
                    body = AST.Args(
                        params = null,
                        body = AST.And("import".code, "SP".callorvar)
                    )
                ),
                AST.Rule(
                    name = "KOMETA",
                    body = AST.Args(
                        params = null,
                        body = AST.And("kometa".code, "SP".callorvar)
                    )
                ),
                AST.Rule(
                    name = "EQUALS",
                    body = AST.Args(
                        params = null,
                        body = AST.And(
                            AST.Or("=".code, "::=".code),
                            "SP".callorvar
                        )
                    )
                ),
                AST.Rule(
                    name = "OVERRIDE",
                    body = AST.Args(
                        params = null,
                        body = AST.And("override".code, "SP".callorvar)
                    )
                ),
                AST.Rule(
                    name = "NEW",
                    body = AST.Args(
                        params = null,
                        body = AST.And("new".code, "SP".callorvar)
                    )
                ),
                AST.Rule(
                    name = "LR",
                    body = AST.Args(
                        params = null,
                        body = AST.And("lr".code, "SP".callorvar)
                    )
                ),
                AST.Rule(
                    name = "SEMI",
                    body = AST.Args(
                        params = null,
                        body = AST.And(
                            AST.Or(";".code, ",".code),
                            "SP".callorvar
                        )
                    )
                ),
                AST.Rule(
                    name = "BANG",
                    body = AST.Args(
                        params = null,
                        body = AST.And("!".code, "SP".callorvar)
                    )
                ),
                AST.Rule(
                    name = "OR",
                    body = AST.Args(
                        params = null,
                        body = AST.And("|".code, "SP".callorvar)
                    )
                ),
                AST.Rule(
                    name = "ACTION",
                    body = AST.Args(
                        params = null,
                        body = AST.And("->".code, "SP".callorvar)
                    )
                ),
                AST.Rule(
                    name = "COLON",
                    body = AST.Args(
                        params = null,
                        body = AST.And(":".code, "SP".callorvar)
                    )
                ),
                AST.Rule(
                    name = "COMMA",
                    body = AST.Args(
                        params = null,
                        body = AST.And(",".code, "SP".callorvar)
                    )
                ),
                AST.Rule(
                    name = "DOT",
                    body = AST.Args(
                        params = null,
                        body = ".".code
                    )
                ),
                AST.Rule(
                    name = "PERIOD",
                    body = AST.Args(
                        params = null,
                        body = AST.And("DOT".callorvar, "SP".callorvar)
                    )
                ),
                AST.Rule(
                    name = "LEFT_BRACE",
                    body = AST.Args(
                        params = null,
                        body = AST.And("{".code, "SP".callorvar)
                    )
                ),
                AST.Rule(
                    name = "RIGHT_BRACE",
                    body = AST.Args(
                        params = null,
                        body = AST.And("}".code, "SP".callorvar)
                    )
                ),
                AST.Rule(
                    name = "OPEN",
                    body = AST.Args(
                        params = null,
                        body = AST.And("(".code, "SP".callorvar)
                    )
                ),
                AST.Rule(
                    name = "CLOSE",
                    body = AST.Args(
                        params = null,
                        body = AST.And(")".code, "SP".callorvar)
                    )
                ),
                AST.Rule(
                    name = "LESS",
                    body = AST.Args(
                        params = null,
                        body = AST.And("<".code, "SP".callorvar)
                    )
                ),
                AST.Rule(
                    name = "GREATER",
                    body = AST.Args(
                        params = null,
                        body = AST.And(">".code, "SP".callorvar)
                    )
                ),
                AST.Rule(
                    name = "QUES",
                    body = AST.Args(
                        params = null,
                        body = "?".code
                    )
                ),
                AST.Rule(
                    name = "AND_PRE",
                    body = AST.Args(
                        params = null,
                        body = "&".code
                    )
                ),
                AST.Rule(
                    name = "NOT_PRE",
                    body = AST.Args(
                        params = null,
                        body = "~".code
                    )
                ),
                AST.Rule(
                    name = "STAR",
                    body = AST.Args(
                        params = null,
                        body = AST.And("*".code, "SP".callorvar)
                    )
                ),
                AST.Rule(
                    name = "PLUS",
                    body = AST.Args(
                        params = null,
                        body = AST.And("+".code, "SP".callorvar)
                    )
                ),
                AST.Rule(
                    name = "SP",
                    body = AST.Args(
                        params = null,
                        body = AST.Star(
                            listOf(
                                "EOL".callorvar,
                                "WS".callorvar,
                                "Comment".callorvar,
                            ).reduce { prev: AST.AstNode, cur: AST.AstNode -> AST.Or(prev, cur) }
                        )
                    )
                ),
                AST.Rule(
                    name = "WS",
                    body = AST.Args(
                        params = null,
                        body = AST.Or(" ".code, "\\t".code)
                    )
                ),
                AST.Rule(
                    name = "Comment",
                    body = AST.Args(
                        params = null,
                        body = listOf(
                            "//".code,
                            AST.Star(AST.And(AST.Not("EOL".callorvar), AST.Any)),
                            "EOL".callorvar,
                        ).reduce { prev: AST.AstNode, cur: AST.AstNode -> AST.And(prev, cur) }
                    )
                ),
                AST.Rule(
                    name = "Comment",
                    body = AST.Args(
                        params = null,
                        body = listOf(
                            "/*".code,
                            AST.Star(AST.And(AST.Not("*/".code), AST.Or("EOL".callorvar, AST.Any))),
                            "*/".code,
                        ).reduce { prev: AST.AstNode, cur: AST.AstNode -> AST.And(prev, cur) }
                    )
                ),
                AST.Rule(
                    name = "EOL",
                    body = AST.Args(
                        params = null,
                        body = AST.Act(
                            AST.Bind(
                                AST.Or(
                                    AST.And(
                                        "\\r".code,
                                        AST.Ques("\\n".code)
                                    ),
                                    "\\n".code
                                ),
                                "nl".i
                            ),
                            expression = "_memo.positions += nl!!.nextIndex; nl?.asResult() ?: AST.Code(MatchItem(listOf('\\n')))".i
                        )
                    )
                ),
                AST.Rule(
                    name = "EOF",
                    body = AST.Args(
                        params = null,
                        body = AST.Not(AST.Any)
                    )
                ),
            )
        )
    )
}

private val String.i: MatchItem<Char, AST.AstNode>
    get() = MatchItem(toList())

private val String.code: AST.AstNode
    get() = AST.Code("\"$this\"".i)

private val String.callorvar: AST.CallOrVar
    get() = AST.CallOrVar(MatchItem(emptyList(), listOf(AST.Idfr(i))))