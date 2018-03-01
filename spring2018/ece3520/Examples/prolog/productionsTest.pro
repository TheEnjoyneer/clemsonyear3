production(prod2,["A","a"]).
production(prod1,["S","AB"]).

pn([prod1,prod2]).
ap([],[]).
ap([NH|NT],[PH|PT]) :- production(NH,PH),ap(NT,PT).
