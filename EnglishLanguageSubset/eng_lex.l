%{

#include "y.tab.h"

%}

char [A-Za-z]
digit[0-9]

%%

[\t ]+                    /* ignore whitespace */ ;

poonam |
sakshi |
vit |
book  |
lexandyacc |
studying  {return NOUN;}

is |
am |
are |
were |
was |
be |
being |
been |
do |
does |
did |
will |
would |
should |
can |
could |
has |
have |
had |
go       { return VERB; }

very |
simply |
slowly |
gently |
quietly |
calmly |
angrily   { return ADVERB; }

to |
from |
behind |
above |
below |
in |
between  |
below     { return PREPOSITION; }

if |
then |
and |
but |
or       { return CONJUNCTION; }

their |
my |
your |
his |
her |
its      { return ADJECTIVE; }

i |
you |
he |
she  |
we  |
they       { return PRONOUN; }


{digit}+ return NUMBER;
{char}({char}|{char})*  return character;

.|\n        { ECHO;/* normal default anyway */ }

