/* llast.pro */
/* 'real' declarative programming example */

llast([X|[]],X) :- !. /* if tail = [], must be last element */
					  /* note 1: applicable to a list of anything */
					  /* note 2: NOT written:
					  			llast([X|Y],X) :- Y=[].
								OR
								llast([X|Y],R) :- Y=[], R is X.
								OR
								llast([X|Y],R) :- Y=[], R=X. */

llast([_|T],L) :- llast(T,L).  /* lots of points may get marked for backtracking */
							   /* ==>> suggest to class they leave out cut and try */
