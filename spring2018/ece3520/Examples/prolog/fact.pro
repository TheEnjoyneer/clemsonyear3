/* fact.pro is a basic factorial recursive program */
/* set factorial(1,1) = 1 */

factorial(0,1).
factorial(1,1).

/* recursive factorial */
/* file: fact.pro */

factorial(N, _) :- N<0, write('Do not use factorial with a negative argument'), !.
factorial(N, Result) :- Imin1 is N-1,
factorial(Imin1, Fmin1),
Result is N*Fmin1.
