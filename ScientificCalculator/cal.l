%{

#include "y.tab.h"
#include <math.h>
%}

NUMBER [0-9]+|([0-9]*"."[0-9]+)

%%
{NUMBER} {yylval.p=atof(yytext);
return NUMBER;}
sin|SIN {return SIN;}
cos|COS {return COS;}
tan|TAN {return TAN;}
sqrt|SQRT {return SQRT;}
squ|SQUARE {return SQUARE;}
exp|EXP {return EXP;}
reci|RECI {return RECI;}
cube|CUBE {return CUBE;}
fact|FACT {return FACT;}
ln|LN {return LN;}
mod|MOD {return MOD;}

[\t]+ ;

\n|. return yytext[0];
%%

