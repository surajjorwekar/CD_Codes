%{
#include "y.tab.h"
#include <math.h>
%}

NUMBER [0-9]+|([0-9]*"."[0-9]+)

%%
{NUMBER} {yylval.p=atof(yytext);
return NUMBER;}
sqrt|SQRT {return SQRT;}
squ|SQUARE {return SQUARE;}
cube|CUBE {return CUBE;}
mod|MOD {return MOD;}

[\t]+ ;

\n|. return yytext[0];
%%

