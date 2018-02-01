/* set factorial(1,1) = 1 */

factorial(1,1).

/* recursive factorial */
/* file: fact.pro */

factorial(N, Result) :- Imin1 is N-1,
factorial(Imin1, Fmin1),
Result is N*Fmin1.
