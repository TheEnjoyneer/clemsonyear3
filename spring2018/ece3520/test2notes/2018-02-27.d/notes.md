# ECE3520 02/27/2018 Class Notes
Next class (Thurs) quizzes back.
Online Lectures Soon. Discuss more soon?

## Questions
./minicex1 <minicex1.mc
.mc file is the program to be scanned and parsed?

## SDE1
Due 1 week from today.
Do not include any testing data in sde1.

## Pragmatics of using bison
1. specify grammar >=1 bison grammar files
2. write or generate lexical analyzer to process src dode input and pass tokens to parser
3. write fnc to call bison-generated parser
4. write error reporting routines.
5. run bison on the grammar to produce parser function (yyparse)
6. compile yyparse with other source files and link the object files to produce the overall parser
* DON't name anything beginning with yy

## Overview of bison operation
file naming convention of flex (ON QUIZ 2)
* extension .in. to lex.yy.c
* by convention, bison input files have extension 'y'
* bison generated c func yyparse calls yylex to provide tokens
* bison may also be used w/ -d option. *.tab.h gen for use w flex.
* \*.y --(bison -d)--> {*.tab.c, *.tab.h

## Conveying grammar to bison
bison generates parser from input in form of a bison grammar file.
* nonterminal symb in formal gram represneted in bison input as an identifier, lowercase (i.e. all chars), e.g. expr
* terminal (token type aka) is upper case, e.g., IDENTIFIER
* terminal symb that stands for particular keyword in lang should be named after that keyword converted to uppercase

## bison input file for minic
Might have from last time.
Where lecture ended from last time

%{
puts this stuff in here at top of file
%}
<PICTURE 1>
<PICTURE 2>

Book first page or two of ch4. Syntax expressed in BNF

semicolon on next line. Is terminator for that particular production.

first production in minic. transunit replaced by naindecl or body

maindecl replaced by type MAIN arg (main token/keyword specifically in minic)

type: INT | VOID (| = or)(can do | in regex also)

** Not declaring any nonterminals **

<Picture 3>


### Flex regular expressions

printfing. allowed to specify some c code on right. (printf we used).
if regex matched, c code ran.
But in this case not regex recognition, but reduction of this production.
i.e. something derived(?diff word) from transunit

rest of grammar
<Picture 4> 

## Looked at bison now flex

* 4 files *:

1 for bison, 1 for flex (this sectino), 1 containing yyerror(yyerror.c), file containing main..

bison generated parsing function yyparse called by main.

<picture 5> flex file. stars with #include "minicex1.tab.h"

* Should be able to tell diff b/t flex and bison input files *

whitespace gobbling

parentheses and stuff

defining identifier

number defined above

### Just saw entirety of scanner and parser for minic
 but still missing two files
 
## Error handling function
 
 yyerror.c 
 
int yyerror (s) /* called by yyparse on error */
char *s;
{ printf ("%s\\n", s);
 return(-1);
}

syntax error string is most common erorr string.

## overall project and main
recall:
* flex generates c-based scanner in lex.yy.c
* bison generates the parse in minicex1.tab.x
* and yyerror() in yyerror.c
* one more file. file containing main(). (linker loader containing?)

## minicex1.c
/* overall project source file minicex1.c */

#include "minicex1.tab.c" /* parse */
#include "lex.yy.c"         /* scanner */
#include "yyerror.c"        /* error */
#define YYERROR_VERBOSE     /* sometimes needed. can take out if complains already defined */

int main()
{
    yyparse ();
    return(1);
}

calls yyparse and doesn't return void (good modern practice)
(who gets the 1?)

All pieces put together now.

there are alternative structures to what he's showing us but he likes it.
it's worked well.

## using flex and bison generated functions

$ gcc minicex1.c -lfl -o minicex1
$ ./minicex1 <minicex1.mc
$

Note:
* in this case, parse was successful but not apparent from sparce scanner/parse output.
* section 6.7 pages 180+ for more stuff

"I'm a narcissist and want to be congratulated for 5 lines of code running correctly" -Schalkoff

### Homework
#### First place
If yyparse(); terminates successfully in main, successful parse.
Homework: on successful completion of parse, what value returned??

heading of section of bison manual.

check return of yyparse() if statement.

#### one other place:
If bison successfully reduces everything sent to him by flex,
(circled right next to /* grammar rules */ transunit maindecl thing.

How tell bison S Starting thing for grammar thing?

looking for upper string of cyk parse table?

Declare S with transunit???
RTM???

do a printf(); right in right margin next to transunit thing.
Like how we printf'd earlier when successful regex thing.


too fast on NC back roads $263 ticket lololol

-Wall switch. find all stuff can criticize in source code


## Bison under hood

* look at operation of bison parsing algo
* a numbber of optional bison features are relevant to understand bison operation as well as for debuggin bison grammar files.
* to facilitate our inquiry, a very minimal version of minic (hereafter referred to as 'miniclite') is used.

<Picture 6>? miniclite.
left bison right flex
give right to bison.
give left to flex?
JK above two sentences may be incorrect.
all bison. left tokens, right productions.

## Bison parsing algo

* construct derivation tree 'buttom-up' are called LR parsers
* L denotes left to right scan of input and R denotes rightmost derivation
* LR parsers suited for use in compilers for many prog langs based upon context-free grammars
* At each step, parser is in some state determined solely by the part of the sentence it has parsed thus far. Reading and then pushing tokens onto a stack is denoted shifting; a single read/push thus defines a shift.

## LR parsing actions
4 parsing actions
* shift: accept next input token
* reduce: replace sequence of symbols corresponding to right part of some production by the nonterminal on the left of that production
* accept: announce completion of parse; or
* report error in parsing.


## -v -g switches in bison

Two very useful features verbose output and create graphical rendition of parser statem achine

* -v switch generates file *.output
* -g switch instructs bison to create visualization of compiler graph *.vcg file. may be viewed using xvcg viewer

<Picture 6>
pim-ch6-part1addn1-s..pdf
no idea what this is.

numbbers in parentheses next to erorr, INT, VOID?
integer represations of that token that flex, bison used to communicate.


## following a miniclite(2) parse

int main(void)
{
return(10);
}

syntactically valid minic-lite program.

<Picture 7>
left is now flex, right is now bison for miniclite parser.

couldn't get bottom half because my phone sucks ass

parsing:

bison and tabbed is flex

token is integer
    reduced type
token is main
token is (
token is void
    reduced type
token is )
    reduced arg
    reduced maindecl
token is {
token is return
token is (
token is 10
token is )
    reduced returnarg
roken is ;
    reduced returnstat
    reduced statseq
token is }
    reduced body
    reduced transunit
$

done


## end up with
2/3 of a compiler. where other third?
Machien code. Backend of cmopiler.
predicated on successful parse and development of derivation tree.

commands semantically (not syntactically) (forgot word but parse through maybe?)

## NExt thursday
address last part of flex and bison and such.

hopefully last tuesday lecture for awhile.


