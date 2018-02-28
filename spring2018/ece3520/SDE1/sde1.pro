/* sde1.pro */
/* This file contains all required predicates for ECE 3520 SDE1 */

/* listCellVal(+Idx,+List,-CellContents) similar to nth1 prolog library function */
listCellVal(1,[X|_],X) :- !.
listCellVal(Idx,[_|List],X) :- 
	Idx>1,
	Idx1 is Idx-1,
	listCellVal(Idx1,List,X).

/* get_tables_values_cell predicate is below */
/* get_table_values_cell([+I,+J],+Table,-ContentsL) */
get_table_values_cell([I,J],Table,ContentsL) :- 
	listCellVal(J,Table,JList),
	listCellVal(I,JList,ContentsL), !.

/* append predicate for use in this program is listed below */
/* append(+OriginalList,+CellToAppend,-NewList) */
append([],A,A).
append([H|T],A,[H|NT]) :- append(T,A,NT).

/* decompose prediate is below */
/* decompose([+X,+Y],[[-X1,-Y1]|-List]) gives us a continually appended list */
decompose([1,_],[]) :- !.
decompose([X,Y],[[X1,Y1]|List]) :-
	X>0,
	X1 is X-1,
	Y1 is Y+1,
	decompose([X1,Y1],List).

/* decompositions predicate is below */
/* decompositions(+N, -List_of_decomposition_sublists) */

decompositions(N,_) :- N<2, write('Do not use decompositions with an argument less than 2'),!.
decompositions(N,Result) :-
	decompose([N,0],Result).

/* one_product predicate is below */
/* one_product(+Nonterminal,+Cell,-Product) */
one_product(_,[],[]).
one_product([],_,[]).
one_product(Nonterm,[CH|CT],[Prod1|ProdList]) :-
	string_concat(Nonterm,CH,Prod1),
	one_product(Nonterm,CT,ProdList), !.

/* cell_products predicate is below */
/* cell_products(+Cell1,+Cell2,-Product) */
cell_products([],_,[]).
cell_products(_,[],[]).
cell_products([CH1|CT1],Cell2,List) :-
	one_product(CH1,Cell2,Prod1),
	cell_products(CT1,Cell2,ProdList),
	append(Prod1,ProdList,List), !.

/* retEmpty predicate is true if the given list is not empty */
/* retEmpty(+List,-EmptyList) */
retEmpty(List,[]) :- not(emptyEq(List,[])).

/* singletonList predicate is true if the given list has a length of 1 */
/* singletonList(+InputList,-OutputList) */
singletonList(List) :- length(List, L1), L1 == 1.

/* iterate_found predicate is used to find correct cases, */
/* and to append their result to the list of answers */
/* iterate_find(+StringElement,+InputCell,-Nonterminal) */
iterate_find(Term,Cell,Nonterm) :- 
	subtract(Cell,[Term],Nonterm),
	singletonList(Nonterm), !.
iterate_find(Term,Cell,Nonterm) :-
	subtract(Cell,[Term],Result),
	not(singletonList(Result)),
	retEmpty(Result,Nonterm), !.

/* form_row1_cell predicate is below */
/* form_row1_cell(+StringElement,+ProductionsList,-Row1Cell) */
/*form_row1_cell(_,[],[]).*/
form_row1_cell(_,[],[]).
form_row1_cell(Term,[LH|LT],ResultList) :-
	iterate_find(Term,LH,Nonterm),
	form_row1_cell(Term,LT,List),
	append(Nonterm,List,ResultList), !.

/* emptyEq defines 2 empty lists to be equal */
emptyEq([],[]).

/* equivalent predicate is below */
/* equivalent(+A,+B) */
equivalent(Cell1,Cell2) :- 
	subtract(Cell1,Cell2,Result1),
	subtract(Cell2,Cell1,Result2),
	emptyEq([],Result1),
	emptyEq([],Result2), !.

/* row_equivalent predicate is below */
/* row_equivalent(+RowA,+RowB) */
row_equivalent([],[]).
row_equivalent([RH1|RT1],[RH2|RT2]) :-
	equivalent(RH1,RH2),
	row_equivalent(RT1,RT2), !.

/* table_equivalent predicate is below */
/* table_equivalent(+TableA,+TableB) */
table_equivalent([],[]).
table_equivalent([RH1|RT1],[RH2|RT2]) :-
	row_equivalent(RH1,RH2),
	table_equivalent(RT1,RT2), !.

