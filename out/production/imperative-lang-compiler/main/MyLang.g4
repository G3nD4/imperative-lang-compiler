grammar MyLang;

// -------------------- Lexer Rules --------------------

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
ELSEIF: 'elseif';
THEN: 'then';
REVERSE: 'reverse';
TRUE: 'true';
FALSE: 'false';
RETURN: 'return';
BREAK: 'break';
AND: 'and';
OR: 'or';
NOT: 'not';

INTEGER: 'integer';
REAL: 'real';
BOOLEAN: 'boolean';

ASSIGN: ':=';
COLON: ':';
SEMICOLON: ';';
PLUS: '+';
MINUS: '-';
MULTIPLY: '*';
DIVIDE: '/';
MODULO: '%';
EQUALS: '=';
NOT_EQUALS: '!=';
LESS_THAN: '<';
GREATER_THAN: '>';
LESS_THAN_EQUALS: '<=';
GREATER_THAN_EQUALS: '>=';
RANGE: '..';

LPAREN: '(';
RPAREN: ')';
DOT: '.';
COMMA: ',';
NEWLINE: '\r'? '\n';

INTEGER_LITERAL: [0-9]+;
REAL_LITERAL: [0-9]+ '.' [0-9]+;
IDENTIFIER: [a-zA-Z_][a-zA-Z0-9_]*;

WS: [ \t]+ -> skip;
LINE_COMMENT: '//' ~[\r\n]* -> skip;
BLOCK_COMMENT: '/*' .*? '*/' -> skip;

// -------------------- Parser Rules --------------------

program
    : (declaration | routineDeclaration | separator | statement)+ EOF
    ;

declaration
    : variableDeclaration separator
    | typeDeclaration separator
    ;

variableDeclaration
    : VAR IDENTIFIER (COLON type)? (IS expression)?
    ;

typeDeclaration
    : TYPE IDENTIFIER IS type
    ;

type
    : INTEGER
    | REAL
    | BOOLEAN
    | IDENTIFIER               // User-defined types
    ;

routineDeclaration
    : ROUTINE IDENTIFIER LPAREN parameters? RPAREN (COLON type)? IS sepOrNot body sepOrNot END
    ;

parameters
    : parameter (COMMA parameter)*
    ;

parameter
    : IDENTIFIER COLON type
    ;

body
    : (declaration | statement | separator)*
    ;

statement
    : assignment separator
    | routineCall separator
    | whileLoop separator
    | forLoop separator
    | ifStatement separator
    | returnStatement separator
    | breakStatement separator
    ;

assignment
    : modifiablePrimary ASSIGN expression
    ;

routineCall
    : IDENTIFIER LPAREN (expression (COMMA expression)*)? RPAREN
    ;

whileLoop
    : WHILE expression LOOP sepOrNot body sepOrNot END
    ;

forLoop
    : FOR IDENTIFIER IN (REVERSE)? range LOOP sepOrNot body sepOrNot END
    ;

range
    : expression RANGE expression
    ;

ifStatement
    : IF expression THEN sepOrNot body (ELSEIF expression THEN sepOrNot body)* (ELSE sepOrNot body)? sepOrNot END
    ;

returnStatement
    : RETURN expression?
    ;

breakStatement
    : BREAK
    ;

separator
    : (NEWLINE | SEMICOLON)+
    ;

sepOrNot
    : (NEWLINE | SEMICOLON)*
    ;

// -------------------- Expression Rules --------------------

expression
    : logicalOrExpression
    ;

logicalOrExpression
    : logicalAndExpression (OR logicalAndExpression)*
    ;

logicalAndExpression
    : equalityExpression (AND equalityExpression)*
    ;

equalityExpression
    : relationalExpression ((EQUALS | NOT_EQUALS) relationalExpression)*
    ;

relationalExpression
    : additiveExpression ((LESS_THAN | GREATER_THAN | LESS_THAN_EQUALS | GREATER_THAN_EQUALS) additiveExpression)*
    ;

additiveExpression
    : multiplicativeExpression ((PLUS | MINUS) multiplicativeExpression)*
    ;

multiplicativeExpression
    : unaryExpression ((MULTIPLY | DIVIDE | MODULO) unaryExpression)*
    ;

unaryExpression
    : (PLUS | MINUS | NOT)? primary
    ;

primary
    : INTEGER_LITERAL
    | REAL_LITERAL
    | TRUE
    | FALSE
    | LPAREN expression RPAREN
    | routineCall
    | modifiablePrimary
    ;

// -------------------- Modifiable Primary --------------------

modifiablePrimary
    : IDENTIFIER (DOT IDENTIFIER)*
    ;
