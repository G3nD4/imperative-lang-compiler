grammar MyLang;

// -------------------- Lexer Rules --------------------

// Keywords
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

// Data Types
INTEGER: 'integer';
REAL: 'real';
BOOLEAN: 'boolean';

// Operators
ASSIGN: ':=';
COLON: ':';
PLUS: '+';
MINUS: '-';
MULTIPLY: '*';
DIVIDE: '/';
MODULO: '%';
POWER: '^';
EQUALS: '=';
NOT_EQUALS: '!=';
LESS_THAN: '<';
GREATER_THAN: '>';
LESS_THAN_EQUALS: '<=';
GREATER_THAN_EQUALS: '>=';
RANGE: '..';

// Punctuation
LPAREN: '(';
RPAREN: ')';
LBRACKET: '[';
RBRACKET: ']';
DOT: '.';
COMMA: ',';
NEWLINE: '\r'? '\n';

// Literals
INTEGER_LITERAL: [0-9]+;
REAL_LITERAL: [0-9]+ '.' [0-9]+;
IDENTIFIER: [a-zA-Z_][a-zA-Z0-9_]*;

// Whitespace and Comments
WS: [ \t\r\n]+ -> skip;
LINE_COMMENT: '//' ~[\r\n]* -> skip;

// -------------------- Parser Rules --------------------

// Entry Point
program: (declaration | routineDeclaration)* EOF;

// Declarations
declaration: variableDeclaration | typeDeclaration;

// Variable Declaration
// Supports multiple variables with optional type and multiple initializations
variableDeclaration
    : VAR IDENTIFIER (COMMA IDENTIFIER)* (COLON type)? (IS expression (COMMA expression)*)?
    ;

// Type Declaration
typeDeclaration
    : TYPE IDENTIFIER IS type  // e.g., type Age is integer
    ;

// Type Definitions
type
    : 'integer'
    | 'real'
    | 'boolean'
    | IDENTIFIER               // User-defined types
    ;

// Routine Declaration
routineDeclaration
    : ROUTINE IDENTIFIER LPAREN parameters? RPAREN (COLON type)? IS sepOrNot body sepOrNot END
    ;

// Parameters
parameters
    : parameter (COMMA parameter)*
    ;

// Single Parameter
parameter
    : IDENTIFIER COLON type
    ;

// Body of Routine
body: (declaration | statement)*;

// Statements
statement
    : assignment
    | routineCall
    | whileLoop
    | forLoop
    | ifStatement
    | returnStatement
    | breakStatement
    ;

// Assignment Statement
assignment
    : modifiablePrimary ASSIGN expression
    ;

// Routine Call Statement
routineCall
    : IDENTIFIER LPAREN (expression (COMMA expression)*)? RPAREN
    ;

// While Loop
whileLoop
    : WHILE expression LOOP body separator? END
    ;

// For Loop
forLoop
    : FOR IDENTIFIER IN (REVERSE)? range LOOP body separator? END
    ;

// Range Expression (e.g., 1..10)
range
    : expression RANGE expression
    ;

// If Statement with Optional Elseif and Else Blocks
ifStatement
    : IF expression THEN body (ELSEIF expression THEN body)* (ELSE body)? END
    ;

// Return Statement
returnStatement
    : RETURN expression
    ;

// Break Statement
breakStatement
    : BREAK
    ;

// Separator for Statements (Optional Newlines)
separator: NEWLINE+;

// Optional Separator
sepOrNot
    : NEWLINE*
    ;

// Expressions
expression
    : logicalExpression
    ;

// Logical Expressions (AND, OR)
logicalExpression
    : logicalExpression AND relationalExpression     # LogicalAnd
    | logicalExpression OR relationalExpression      # LogicalOr
    | relationalExpression                           # LogicalPrimary
    ;

// Relational Expressions (=, !=, <, >, <=, >=)
relationalExpression
    : relationalExpression (EQUALS | NOT_EQUALS | LESS_THAN | GREATER_THAN | LESS_THAN_EQUALS | GREATER_THAN_EQUALS) additiveExpression
    | additiveExpression
    ;

// Additive Expressions (+, -)
additiveExpression
    : additiveExpression (PLUS | MINUS) multiplicativeExpression
    | multiplicativeExpression
    ;

// Multiplicative Expressions (*, /, %)
multiplicativeExpression
    : multiplicativeExpression (MULTIPLY | DIVIDE | MODULO) powerExpression
    | powerExpression
    ;

// Power Expressions (^)
powerExpression
    : atom (POWER atom)*
    ;

// Atomic Elements
atom
    : INTEGER_LITERAL
    | REAL_LITERAL
    | TRUE
    | FALSE
    | routineCall
    | modifiablePrimary
    | LPAREN expression RPAREN
    ;

// Modifiable Primary (Variables, Array Access, Object Fields)
modifiablePrimary
    : IDENTIFIER (DOT IDENTIFIER | LBRACKET expression RBRACKET)*
    ;
