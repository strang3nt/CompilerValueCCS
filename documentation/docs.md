# CompilerValueCCS source overview

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

P, Q ::= $\sum_{i\in I}P$
| $P\ |\ Q$
| $P\backslash L$
| $K(e_1,...,e_h)$
| $a(x).P$
| $'a(e).P$
| $\tau.P$

The parser handles things slightly differently

# The parser

# The _actual_ grammar

# Pure CCS Ast

# The compilation function

# A few examples

-----------------

# References
