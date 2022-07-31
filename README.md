# CompilerValueCCS

This is a small assingnment project developed for the Languages for Concurrency and Distribution course at UniPd: <https://en.didattica.unipd.it/off/2021/LM/SC/SC2598/000ZZ/SCQ1098228/N0>.

Contains a compiler for Value Passing CCS into [CCS](https://en.wikipedia.org/wiki/Calculus_of_communicating_systems) with pure synchronization. 
It is realized with Scala 3 and [Scala parser combinators](https://github.com/scala/scala-parser-combinators) to build the parser.

## Requirements

To compile and run this project the following is required:
 
 - Makefile
 - Mill
 - suitable version of the Jvm
 - Antlr4 installed and set up in the `PATH`.

## Parsing

The parser understands the following grammar (the source is the file Ccsvp.g4):

```antlr4
constant: IDENTIFIER (LBRACKET IDENTIFIER (COMMA IDENTIFIER)* RBRACKET)? ;
ccsvp: 
// const
IDENTIFIER (LBRACKET expr (COMMA expr)* RBRACKET)?
// ifthen
| IF LBRACKET boolbinop RBRACKET THEN ccsvp
// restriction
| ccsvp RESTR CURLY_LBRACKET IDENTIFIER (COMMA IDENTIFIER)* CURLY_RBRACKET
// redirection
| ccsvp SQUARED_LBRACKET IDENTIFIER DIV IDENTIFIER 
            (COMMA IDENTIFIER DIV IDENTIFIER)* SQUARED_LBRACKET
//inputch
| IDENTIFIER (LBRACKET IDENTIFIER RBRACKET)? SEPARATOR ccsvp
//outputch
| OUT IDENTIFIER (LBRACKET expr RBRACKET)? SEPARATOR ccsvp
// tauch
| TAU SEPARATOR ccsvp
// sum
| ccsvp (SUM ccsvp)+
// par
| ccsvp PAR ccsvp
// parenthesis
| LBRACKET ccsvp RBRACKET
;
program: constant DEFINE ccsvp;
```

But the ast which is built at the end of the parsing process is the following:

```scala
trait ValueCCS

case class Const(name: String, l: Option[List[Aexpr]]) 
  extends ValueCCS
case class TauCh(p: ValueCCS) extends ValueCCS
case class InputCh(c: Channel, v: Option[Variable], p: ValueCCS) 
  extends ValueCCS
case class OutputCh(c: Channel, e: Option[Aexpr], p: ValueCCS) 
  extends ValueCCS
case class IfThen(b: Bexpr, p: ValueCCS) extends ValueCCS
case class Par(left: ValueCCS, right: ValueCCS) extends ValueCCS
case class Sum(l: List[ValueCCS]) extends ValueCCS
case class Restrict(p: ValueCCS, l: List[Channel]) extends ValueCCS
case class Redirection(p: ValueCCS, cs: List[(Channel, Channel)]) 
  extends ValueCCS
```

## Compilation

The compilation is a function which uses pattern matching on the ast which was previously built. It follows closely the translation function from the following website: <https://homepage.cem.itesm.mx/raulm/teaching/cc/notes/Bisimulation/node19.html>.
