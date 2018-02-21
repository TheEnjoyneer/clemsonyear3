goal1([X,Y]) :- first(X), second(Y).

goal1a(Y,[X]) :- first(X), second(Y).

goal1b(X,Y) :- first(X), not(second(Y)).

goal2(X,Y) :- first(X), !, second(Y).

goal2a(X) :- first([X,G]), second(G).



first(1,1).
first(2,1).
first([3,4,5]).
first([5,4]).
second(2).
second([4]).
second(6).
