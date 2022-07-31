grammar Ccsvp;

WS: [ \t\n]+ -> skip;

DEFINE: '=';
SEPARATOR: '.';
COMMA: ',';
OUT: '\'';
LBRACKET: '(';
RBRACKET: ')';
CURLY_LBRACKET: '{';
CURLY_RBRACKET: '}';
SQUARED_LBRACKET: '[';
SQUARED_RBRACKET: ']';

SUM: '+';
SUB: '-';
MUL: '*';
DIV: '/';
LEQ: '<=';
LE: '<';
GEQ: '>=';
GE: '>';
EQUALS: '==';
NOT: '!';
AND: '&&';
OR: '||';
PAR: '|';
IF: 'if';
THEN: 'then';
RESTR: '\\';
TAU: 'tau';

INTEGER: [0-9]+;
IDENTIFIER: [a-zA-Z][a-zA-Z0-9_]*;

// arithmetic expression
summation: SUM | SUB;
product: MUL | DIV;
expr: term (summation term)*;
term: factor (product factor)*;
factor: LBRACKET expr RBRACKET | IDENTIFIER | INTEGER;

// boolean expression
logicop: AND | OR;
boolop: LE | GE | GEQ | LEQ | EQUALS;
boolbinop: bterm (logicop bterm)*;
bterm: NOT boolbinop | exprbinop | LBRACKET boolbinop RBRACKET;
exprbinop: expr boolop expr;

// ccs value passing
constant: IDENTIFIER (LBRACKET IDENTIFIER (COMMA IDENTIFIER)* RBRACKET)? ;

ccsvp: 
IDENTIFIER (LBRACKET expr (COMMA expr)* RBRACKET)?                                                     # Const
| IF LBRACKET boolbinop RBRACKET THEN ccsvp                                                            # Ifthen
| ccsvp RESTR CURLY_LBRACKET IDENTIFIER (COMMA IDENTIFIER)* CURLY_RBRACKET                             # Restriction
| ccsvp SQUARED_LBRACKET IDENTIFIER DIV IDENTIFIER (COMMA IDENTIFIER DIV IDENTIFIER)* SQUARED_RBRACKET # Redirection
| IDENTIFIER (LBRACKET IDENTIFIER RBRACKET)? SEPARATOR ccsvp                                           # Inputch
| OUT IDENTIFIER (LBRACKET expr RBRACKET)? SEPARATOR ccsvp                                             # Outputch
| TAU SEPARATOR ccsvp                                                                                  # Tauch
| ccsvp (SUM ccsvp)+                                                                                   # Sum
| ccsvp PAR ccsvp                                                                                      # Par
| LBRACKET ccsvp RBRACKET                                                                              # Parenthesis
;

program: constant DEFINE ccsvp;
