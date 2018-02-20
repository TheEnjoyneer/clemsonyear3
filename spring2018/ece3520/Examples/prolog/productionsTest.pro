productions(prod2,["A","a"]).
productions(prod1,["S","AB"]).

pn([prod1,prod2]).
ap([],[]).
ap([PH|PT],[NH|NT]) :- productions(PH,NH),ap(PT,NT).
