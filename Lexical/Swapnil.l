%{
int count=0,lcount=0,headercnt=0,keywordcnt=0,opcnt=0,idencnt=0,delimitcnt=0;
int numcnt=0,accesscnt=0;
%}
%%
#[a-zA-Z]*\<[a-zA-Z|\.]*\> {headercnt++; printf("\n %s :Headerfile",yytext+8);}
int|float|char|void|double|long|return|unsigned {keywordcnt++; printf("\n %s :Keyword",yytext);}
"+"|"-"|"/"|"*"|"="|"%"|"--"|"++"|">="|"<="|"!=" {opcnt++; printf("\n %s :Operator",yytext);}
[a-zA-Z0-9]+ {idencnt++; printf("\n %s :Identifier",yytext);}
"{"|"}"|"("|")"|","|";"|"]"|"[" {delimitcnt++; printf("\n %s :Delimiter",yytext);}
[0-9]+ {accesscnt++; printf("\n %s :Access Specifier",yytext);}
"\""[a-z]+"\"" {printf("\n %s :Simple Text",yytext);}
" " {count++;}
"\n" {lcount++;}
. {printf("\n");}
%%
main(int argc,char **argv[])
{
FILE *fp;
fp=fopen(argv[1],"r");
yyin=fp;
yylex();
printf("\n------------------------Subset Count------------------------");
printf("\n %d HeaderFile Count",headercnt);
printf("\n %d Keywords Count",keywordcnt);
printf("\n %d Operator Count",opcnt);
printf("\n %d Identifier Count",idencnt);
printf("\n %d Delimiter Count",delimitcnt);
printf("\n %d Number Count",numcnt);
printf("\n %d Total number of whitespace",count);
printf("\n %d Total number of lines",lcount);
}
int yywrap()
{return 1;}

