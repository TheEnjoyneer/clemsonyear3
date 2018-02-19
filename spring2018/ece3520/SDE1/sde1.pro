/* sde1.pro */
/* This file contains all required predicates for ECE 3520 SDE1 */

/* get_tables_values_cell predicate is below 		*/
/* get_table_values_cell([+I,+J],+Table,-ContentsL) 	*/

/* get_table_values_cell([I,J],Table,ContentsL) :- */





/* decompositions predicate is below			*/
/* decompositions(+N, -List_of_decomposition_sublists)	*/

decompositions(N,_) :- N<1, write('Do not use decompositions with non-positive argument'),!.

K is 0.

decompDec(X,Y,Z) :- K is K+1,Y is X-K, Z is X-Y.

decompositions(N,Result_List) :-  K<N,
decompDec(N,Num1,Num2),
Result = [[Num1,Num2]|_].
