# CompilerValueCCS source overview

![CompilerValueCCS package diagram](img/valuePassing-package_diagram.png){ width=70% }

# Value Passing CCS Ast

```scala
enum ValueCCS:
  case Constant(name: String, l: Option[List[Aexpr]])
  case InputCh(c: Channel | Tau, v: Option[Variable], p: ValueCCS)
  case OutputCh(c: Channel, e: Option[Aexpr], p: ValueCCS)
  case IfThen(b: Bexpr, p: ValueCCS)
  case Par(left: ValueCCS, right: ValueCCS)
  case Sum(l: List[ValueCCS])
  case Restrict(p: ValueCCS, l: List[Channel])
  case Redirection(p: ValueCCS, cs: List[(Channel, Channel)])
```

# Value Passing CCS Grammar

    expr -> term [ (‘+’ | ‘-’) term ]*
    term -> factor [ (‘*’ | ‘/’) factor ]*
    factor -> ‘(‘ expr ‘)’ | ID | NUMBER

    boolbinop -> term [(&& | ||) term]*
    term -> '!' boolbinop | exprbinop | '(' boolbinop ')'
    exprbinop -> aexpr (< | > | ...) aexpr

P, Q ::= $\sum_{i\in I}P$
| $P\ |\ Q$
| $P\backslash L$
| $K(e_1,...,e_h)$
| $a(x).P$
| $'a(e).P$
| $\tau.P$

The parser handles things slightly differently

# The parser

Packrat Parsers:
  - needed left recursion
  - a DSL tighly integrated with Scala

# The _actual_ grammar

# Pure CCS Ast

```scala
enum PureCCS:
  case Constant(name: String)
  case InputCh(c: Channel | Tau, p: PureCCS)
  case OutputCh(c: Channel, p: PureCCS)
  case Par(left: PureCCS, right: PureCCS)
  case Sum(l: List[PureCCS])
  case Restrict(p: PureCCS, l: List[Channel])
  case Redirection(p: PureCCS, cs: List[(Channel, Channel)])
```

# The compilation function

## The `Natural` type

## Evaluation

# A few examples

-----------------

# References
