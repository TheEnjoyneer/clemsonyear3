/* designing a member testing function */

nmember(X,[X|_]).

nmember(X,[_|Y]) :- nmember(X,Y).

