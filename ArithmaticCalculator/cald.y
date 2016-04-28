%{

#include "y.tab.h"
#include <math.h>
#include <stdio.h>

%}

%union
{
double p;
}
%token <p> NUMBER
%token SQRT SQUARE MOD CUBE
%left '+''-'
%right '*''/'
%right '^'

%nonassoc NEG
%type <p> Exp

%%

SL : SS '\n'
   | SL SS '\n'
   ;
SS : Exp {printf("The output is=%g\n",$1);}
   ;

Exp : Exp '+' Exp {$$=$1+$3;}
    |Exp '-' Exp {$$=$1-$3;}
    |Exp '*' Exp {$$=$1*$3;}
    |Exp '/' Exp {

if($3==0)
  printf("Error! Divide by zero!!");
else
  $$=$1/$3;
}

|Exp '^' Exp {$$=pow($1,$3);}

|SQRT '(' Exp ')' {$$=sqrt($3);}
|SQUARE '(' Exp ')' {$$=$3*$3;}

|CUBE '(' Exp ')' {$$=$3*$3*$3;}

|MOD '('Exp','Exp')' {$$=fmod($3,$5);}
|NUMBER
;


%%
extern FILE*yyin;

int main()
{
do{
yyparse();
}while(!feof(yyin));
}

yyerror(char*a)
{
fprintf(stderr,"parse error!!!");
}

