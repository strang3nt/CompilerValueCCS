# CompilerValueCCS

This is a small assingnment project developed for the Languages for Concurrency and Distribution course at UniPd: <https://en.didattica.unipd.it/off/2021/LM/SC/SC2598/000ZZ/SCQ1098228/N0>.

Contains a compiler for Value Passing CCS into [CCS](https://en.wikipedia.org/wiki/Calculus_of_communicating_systems) with pure synchronization. 
It is realized with Scala 3 and [Scala parser combinators](https://github.com/scala/scala-parser-combinators) to build the parser.

## Parsing

The parser understands roughly the following grammar:

```xml
<const>  ::= <Id>( <expr>* ) | <Id>
<inch>   ::= <id> "(" <id> ")" "." <P> | <id> "." <Ppar>
<outch>  ::= "'" <id> "(" <expr> ")" "." <Ppar>
<tauch>  ::= "tau" "." <Ppar>
<ifthen> ::= "if" "(" <boolbinop> ")" "then" <PPar>
<sum>    ::= ( <par> | <rest> | <inch> | ... ) ("+" <P>)+
<par>    ::= (<sum> | <restr> | <inch> | ... ) "|" <P>
<restr>  ::= <Ppar> "{" ( <id>* ) "}"
<redr>   ::= <Ppar> "[" (<id> "/" <id>)* "]"

<Ppar>    ::= <outch> | <const> | <tauch> | <inch> | <ifthen> |
            | "(" (<sum> | <par> | <restr> | <redr>) ")"
<P>       ::= <sum> | <par> | <restr> | <inch> | ...
<program> ::= <const> "=" <P>
```

But the ast which is built at the end of the parsing process is the following:

```scala
enum ValueCCS:
  case Constant(name: String, l: Option[List[Aexpr]])
  case TauCh(p: ValueCCS)
  case InputCh(c: Channel, v: Option[Variable], p: ValueCCS)
  case OutputCh(c: Channel, e: Option[Aexpr], p: ValueCCS)
  case IfThen(b: Bexpr, p: ValueCCS)
  case Par(left: ValueCCS, right: ValueCCS)
  case Sum(l: List[ValueCCS])
  case Restrict(p: ValueCCS, l: List[Channel])
  case Redirection(p: ValueCCS, cs: List[(Channel, Channel)])
```

## Compilation

The compilation is a function which uses pattern matching on the ast which was previously built. It follows closely the translation function from the following website: <https://homepage.cem.itesm.mx/raulm/teaching/cc/notes/Bisimulation/node19.html>.
