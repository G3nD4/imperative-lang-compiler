grammar MyLang;

// Tokens
VAR: 'var';
TYPE: 'type';
ROUTINE: 'routine';
IS: 'is';
IN: 'in';
END: 'end';
WHILE: 'while';
LOOP: 'loop';
FOR: 'for';
IF: 'if';
ELSE: 'else';
THEN: 'then';
REVERSE: 'reverse';
TRUE: 'true';
FALSE: 'false';
INTEGER: 'integer';
REAL: 'real';
BOOLEAN: 'boolean';
ASSIGN: ':=';
COLON: ':';
PLUS: '+';
MINUS: '-';
MULTIPLY: '*';
DIVIDE: '/';
LPAREN: '(';
RPAREN: ')';
LBRACKET: '[';
RBRACKET: ']';
DOT: '.';
COMMA: ',';
NEWLINE: '\r'? '\n';
// Tokens for comparison operators
EQUALS: '==';
NOT_EQUALS: '!=';
LESS_THAN: '<';
GREATER_THAN: '>';
LESS_THAN_EQUALS: '<=';
GREATER_THAN_EQUALS: '>=';
RANGE: '..';

// Literals
INTEGER_LITERAL: [0-9]+;
REAL_LITERAL: [0-9]+ '.' [0-9]+;
IDENTIFIER: [a-zA-Z_][a-zA-Z0-9_]*;

// Skip whitespaces and comments
WS: [ \t\r\n]+ -> skip;
LINE_COMMENT: '//' ~[\r\n]* -> skip;

/* Parser rules */

// Program structure

separator: NEWLINE+;

sepOrNot
    :   NEWLINE*
    ;

program: (declaration | routineDeclaration)* EOF;

// Declarations
declaration: variableDeclaration | typeDeclaration;

variableDeclaration
    : VAR IDENTIFIER COLON type (IS expression)?  // var x: integer is 42
    | VAR IDENTIFIER IS expression                 // var x is 42
    ;

typeDeclaration
    : TYPE IDENTIFIER IS type  // type T is integer
    ;

type: 'integer'
    | 'real'
    | 'boolean'
    | IDENTIFIER               // for user-defined types
    ;

// Routine declaration
routineDeclaration
    : ROUTINE IDENTIFIER LPAREN parameters? RPAREN (COLON type)? IS sepOrNot body sepOrNot END
    ;

parameters
    : parameter (COMMA parameter)*
    ;

parameter
    : IDENTIFIER COLON type
    ;

// Body
body: (declaration | statement)*;

// Statements
statement: assignment
         | routineCall
         | whileLoop
         | forLoop
         | ifStatement
         ;

assignment
    : modifiablePrimary ASSIGN expression
    ;

routineCall
    : IDENTIFIER LPAREN (expression (COMMA expression)*)? RPAREN
    ;

whileLoop
   : WHILE expression LOOP body separator END
   | WHILE expression LOOP body END
   ;

forLoop
   : FOR IDENTIFIER IN (REVERSE)? range LOOP body separator END
   | FOR IDENTIFIER IN (REVERSE)? range LOOP body END
   ;

range
    : expression RANGE expression
    ;

ifStatement
    : IF expression THEN body (ELSE body)? END
    ;

// Expressions
expression
  : expression ('+' | '-') expression
  | expression ('*' | '/') expression
  | atom
  ;

atom
  : INTEGER_LITERAL
  | REAL_LITERAL
  | TRUE
  | FALSE
  | modifiablePrimary
  | LPAREN expression RPAREN
  ;



modifiablePrimary
    : IDENTIFIER (DOT IDENTIFIER | LBRACKET expression RBRACKET)*
    ;
