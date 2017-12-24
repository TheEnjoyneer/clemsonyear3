% Christopher Brant
% C19816588
% MATLAB Homework 6A Due on 11/29/17

clear; clc; close all;

% a, b, c, and d denote the four leftmost nonzero digits of my student ID
a = 1;
b = 9;
c = 8;
d = 1;

% H_s will denote the impulse signal H(s)
% k will denote the gain of the impulse signal
k = ((10^6)*(c^2)*(d^2)) / (a*((a^2)+(b^2)));
H_s = zpk([-a, -a+(b*j), -a-(b*j)], [-10*c, -10*c, -100*d, -100*d], k);

% h and t will denote the impulse response variables
[h,t] = impulse(H_s);

% Plot the impulse response
figure();
impulse(H_s);
title('Figure 6A.1: Impulse Response Plot');

% Use lsiminfo to determine info about the impulse response
% Impulse Info is denoted by h_impulse
h_impulse = lsiminfo(h, t);

% Print out field values of h_impulse
h_impulse

% g and t will denote the step response variables
[g,t] = step(H_s);

% Plot the step response
figure();
step(H_s);
title('Figure 6A.2: Step Response Plot');

% Use stepinfo to determine info about the step response
% Step info is denoted by h_step
h_step = stepinfo(H_s);

% Print out the field values of h_step
h_step

% x and t denotes the gensig generated input signal for question 3
[x,t] = gensig('square', 2, 20, 1/10);

% y will denote the output response of the system with input x and t
y = lsim(H_s, x, t);

% Plot the output response
figure();
lsim(H_s, x, t);
title('Figure 6A.3: Output Response Plot');

% y_response will denote the lsiminfo response variable
y_response = lsiminfo(y, t);

% Print out the field values of y_response
y_response

% Plot the bode magnitude and phase plots
figure();
bode(H_s,{(10^-1),(10^5)});
title('Figure 6A.4: Bode Magnitude and Phase Plots');

% Plot the pole-zero diagram
figure();
pzplot(H_s);
title('Figure 6A.5: Pole-Zero Diagram');


