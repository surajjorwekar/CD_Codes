%{                            
int WordCount=0;
int LineCount=0;    
int CharCount=0;
int SpaceCount=0; 
%}

%%

[a-z,A-Z]     {printf("%d Char  :%s \n",++CharCount,yytext);} 
[a-z,A-Z]*    {printf("%d Word  :%s \n",++WordCount,yytext);}
[" "]         {printf("%d Space \n",++SpaceCount);}
[\n]          {printf("%d Line  \n\n",++LineCount);}             
.             {;}                           

%%


main()
{

yylex();

 printf("Word Count=%d\n",WordCount);
 printf("Line Count=%d\n",LineCount);
 printf("Character Count=%d\n",CharCount);
 printf("Space Count=%d\n",SpaceCount);

}

