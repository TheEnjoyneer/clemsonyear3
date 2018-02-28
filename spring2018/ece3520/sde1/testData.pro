/* testData.pro */
/* Test data to be used in SDE1 predicate testing */

table(sample_table,[
[["1","1"],["2","1"],["3","1"],["4","1"]],
[["1","2"],["2","2"],["3","2"]],
[["1","3"],["2","3"]],
[["1","4"]]
]).

table(book_result,[
[["S"],["A"],["B","C"],["B","D"],["C","E"]],
[["C"],["S","A"],["S","B","A"],["B","C","E"]],
[["C","A"],["C","S","A"],["D","S","A","B"]],
[["A","S","B","C"],["E","B","S","C","D"]],
[["B","D","A","S","C","E"]]
]).

table(book1,[
[["A"], ["B"], ["C", "D"], ["D", "E"]],
[["C"], ["S", "AB"], ["S", "B", "A"]],
[["S", "BC"], ["CD", "S", "E"]],
[["CD", "S", "AB", "E"]]
]).

/* book1 and book2 should be equal */

table(book2,[
[["A"], ["B"], ["C", "D"], ["D", "E"]],
[["C"], ["S", "AB"], ["S", "B", "A"]],
[["S", "BC"], ["E", "S", "CD"]],
[["AB", "E", "S", "CD"]]
]).

/* bookbad1 and bookbad2 should not be equal to either book1 or book2 */

table(bookbad1,[
[["A"], ["A"], ["B", "C"], ["B", "C"]],
[["C"], ["S", "A"], ["S", "B", "A"]],
[["D", "A"], ["C", "S", "A"]],
[["A", "B", "S", "G"]]
]).

table(bookbad2,[
[["A"], ["A"], ["B", "C"], ["B", "C"]],
[["C"], ["S", "A"], ["S", "B", "A"]],
[["C", "A"], ["C", "A"]],
[["C", "B", "S", "A"]]
]).

productions(book_prods,[["S","AC"],["S","BD"],["A","CD"],["A","AD"],["A","a"],
["B","BD"],["B","CA"],["B","b"],["C","BA"],["C","AA"],["C","b"],["D","AA"],["D","d"]]).





