# ECE 3520 notes March 1st 2018

## Handing test back (Quiz1)

### Corrections

2a)
do few derivations to see pattern (proof by induction)

3) use graphical debugger and spy to see it. guitracer.

Solution:

What = [prod1,prod2],
PL = [["S", "AB"], ["A", "a"]].

4) again, run in prolog to see answer.


### Statistics

(x with bar over) = 67.44  --> average

high = 95

When he unmutes on canvas, can see low.

some high scores, some relatively low (--euphemism).

this is 20% of final composite grade.

## SDE1

table(book_result,Data),get_table_values_cell([3,1],Data,CL).
Data = [[["A"],["A"]]]qiojweirowjer A A BC BC C SA SBA CA CSA CBSA
CL = ["B", "C"].

### Grading script

involves
write q? write something that's quoted? call? cut

DO NOT EMBED testing data into prolog.



## Passing of semantic values

* Recall ONLY (int representatino of) a token is passed to yyparse
* Useful semantic values of tokens:
 1. Names of variables (and later function names) corresponding to the IDE token resulting from scanning an ID (identifier)
 2. Numerical values of variables or input corresponding to token NUM resulting from scanning A_NUM

asks WHAT is token? --> an integer.
represents what? 


### Wall street?
jane trading business using OCAML
Issue with finance in general

### semantic vars, attribute vars

making up example:

if had in bison particular production written this way
```
s: a b 		NUM
;
```
is it syntactically correct?

NUM syntactic value?

```
$$ $1 $2 $3
s: a   b NUM
;
```

associated with every token/symbol in productions of bison

$$ = $3
what would happen if this particular production were reduced by bison.

There's a reduction but also an action

($$=$3 on right? C code to be executed?)

so if semantic value of NUM 9999, semantic value of S or $$ is 9999

Look in Bison manual for more about attrivute values, semantic values or something. Allow us to take Context free grammar and make it behave like it's context sensitive. If start doing manipulation not only tokens productions but also attributes, have gone beyond powers of CFG.
Can represent things that would take a context-sensitive grammar to do.

CFG + attributes = Attribute grammar

## Implementation
* Based upon passing of values (other than tokens) from fnc yylex to fnc yyparse
* Accomplished using global vars which known to both fncts.
* Begin w simple example based upon simple (non-union) use of global variable yylval
* First example: A string is scanned and yields token NUM. The semantic value of NUM is defined as the integer interpretation of the string.

atoi() in C. makes an integer out of a string.

```

%{
	#include "minicex3.tab.h"
	int yylval;

%}

ID [a-z][a-z0-9]*
A_NUM [0-9]+
%%
.
.
.
Note: Most REs as before.

{ID} { printf("ide(%s) \n", yytext);
		return IDE;}

{A_NUM} { printf("num(%s)=%d \n", yytext, 
			yylval=atoi(yytext));
			return NUM;}


```

^^ integer interpretation of IDE token?

yylval container used to transfer something b/t flex/bison or something

## See text for complete file

```
returnstat: RETURN returnarg SEMICOLON 
			{printf("\nin the return statement\n");}
;
returnarg: LPARENS NUM RPARENTS
			{$$=$2;
				printf("the value returned by 'transunit' is %d",$$);
			}
;
%%
```

## Unions: efficient programming structure to not malloc way too much mem
(he calls chameleon)

## on canvas, what comes next

new album what comes next

## UNC-CH
public health pHD at UNC-Chapel Hill daughter.
Opioid addictions.



