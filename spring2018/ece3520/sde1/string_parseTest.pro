string_parseTest(["STRING"]).
parse_char1([B|_],B).

string_parse(W,X,Y,Z) :- string_parseTest(W),
parse_char1(W,X),
string_chars(X,Y),
parse_char1(Y,Z).
