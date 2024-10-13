%{
    #include "parser.tab.h"
    #include <string.h>
%}

%%

"var"          { return VAR; }
"type"         { return TYPE; }
"routine"      { return ROUTINE; }
"is"           { return IS; }
"in"           { return IN; }
"end"          { return END; }
"while"        { return WHILE; }
"loop"         { return LOOP; }
"for"          { return FOR; }
"if"           { return IF; }
"else"         { return ELSE; }
"reverse"      { return REVERSE; }
"true"         { yylval.intval = 1; return BOOLEAN_LITERAL_TRUE; }
"false"        { yylval.intval = 0; return BOOLEAN_LITERAL_FALSE; }
"range"        { return RANGE; }
"then"         { return THEN; }
"\n"           { return NEW_LINE; }

"integer"      { return DATA_TYPE_INTEGER; }
"real"         { return DATA_TYPE_REAL; }
"boolean"      { return DATA_TYPE_BOOLEAN; }

"+"            { return PLUS; }
"-"            { return MINUS; }
"*"            { return MULTIPLY; }
"/"            { return DIVIDE; }
"%"            { return MOD; }
"="            { return ASSIGN; }
"<"            { return LESS_THAN; }
"<="           { return LESS_EQUAL; }
">"            { return GREATER_THAN; }
">="           { return GREATER_EQUAL; }
"=="           { return EQUAL; }
"!="           { return NOT_EQUAL; }
"&&"           { return AND; }
"||"           { return OR; }
"^"            { return XOR; }
"!"            { return NOT; }

\.\.            { return DOUBLE_DOT; }
\.              { return DOT; }

[a-zA-Z_][a-zA-Z0-9_]*  { 
                            yylval.strval = strdup(yytext); 
                            return IDENTIFIER; 
                          }

"("            { return LEFT_PAREN; }
")"            { return RIGHT_PAREN; }
"["            { return LEFT_BRACKET; }
"]"            { return RIGHT_BRACKET; }
":"            { return COLON; }
","            { return COMMA; }

<<EOF>>        { return EOF_TOKEN; }

[ \t\r]+       { /* Skip whitespace */ }

.              { printf("Unexpected character: %s\n", yytext); exit(1); }

%%

int yywrap(void) {
    return 1;
}
