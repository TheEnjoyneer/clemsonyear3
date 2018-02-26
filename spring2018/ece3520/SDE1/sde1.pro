/* sde1.pro */
/* This file contains all required predicates for ECE 3520 SDE1 */

/* get_tables_values_cell predicate is below */
/* get_table_values_cell([+I,+J],+Table,-ContentsL) */

/* listCellVal(+Idx,+List,-CellContents) similar to nth1 prolog library function */
listCellVal(1,[X|_],X) :- !.
listCellVal(Idx,[_|List],X) :- 
	Idx>1,
	Idx1 is Idx-1,
	listCellVal(Idx1,List,X).

get_table_values_cell([I,J],Table,ContentsL) :- 
	listCellVal(J,Table,JList),
	listCellVal(I,JList,ContentsL).

/* append(+OriginalList,+CellToAppend,-NewList) */
append([],A,A).
append([H|T],A,[H|NT]) :- append(T,A,NT).

/* decompose prediate is below */
/* decompose([+X,+Y],[[-X1,-Y1]|-List]) gives us a continually appended list */
decompose([1,_],[]).
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








