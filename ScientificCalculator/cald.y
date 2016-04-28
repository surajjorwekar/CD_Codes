%{

#include "y.tab.h"
#include <stdio.h>
#include <math.h>
#define PI 3.141592
int i,j;
%}

%union
{
double p;
}
%token <p> NUMBER
%token SIN COS TAN SQRT SQUARE RECI EXP MOD CUBE FACT
%left LN 

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
|SIN '(' Exp ')' {$$=sin($3/180*PI);
}
|COS '(' Exp ')' {$$=cos($3/180*PI);
}
|TAN '(' Exp ')' { if($3==90)
printf("undefined infinity");
else
$$=tan($3/180*PI);
}
|SQRT '(' Exp ')' {$$=sqrt($3);
}
|SQUARE '(' Exp ')' {$$=$3*$3;
}
|EXP '(' Exp ')' {$$=exp($3);
}
|RECI '(' Exp ')' {$$=1/($3);
}
|CUBE '(' Exp ')' {$$=$3*$3*$3;
}
|FACT '(' Exp ')' {
$$=1;
for(j=1;j<=$3;j++)
$$=$$*j;
}
|'(' Exp ')' {$$=$2;
}
|'-' Exp %prec NEG {$$=-$2;
}
|LN Exp { $$=log($2);
}
|MOD '('Exp','Exp')' {$$=fmod($3,$5);
}
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

