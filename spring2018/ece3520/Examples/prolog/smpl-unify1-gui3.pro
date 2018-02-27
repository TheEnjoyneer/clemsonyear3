/* smpl-unify1-gui3.pro */
/* rev. 2/15/2018 */

/* new goal to start debugging */

startDebug :- /* what to trace */
spy(first), spy(second), spy(goal1), spy(goal2),
/* turn on guitracer for trace */
guitracer.

goal1(X,Y) :- 
/* original goal1 (logically unmodified) */
first(X), second(Y).

goal2(X) :- 
/* original goal2 (logically unmodified) */
first(X), second(X).

/* original database */

first(1).
first(2).
first(3).
second(2).
second(4).
second(6).
