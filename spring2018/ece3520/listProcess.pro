/* listProcess.pro */

someOper(X,R) :- R is X*X.

listProcess([],[]).
listProcess([AH|AT],[RH|RT]) :- someOper(AH,RH), listProcess(AT,RT).
