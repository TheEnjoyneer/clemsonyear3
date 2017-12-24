% Christopher Brant
% C19816588
% MATLAB Homework 1 Due on 8/30/17

clear; clc; close all;

% a will denote the leftmost numerical digit of my student ID number.
a = 1;

% The equation for the signal that will be used will be as follows:
% x[n] = (n .^ (3 + (a / 5))) * ((0.9) ^ n)

% 1
% The time values for n range from 0 to 300
n = 0:1:300;
% The following equation will dictate the signal values over n's range
x = (n .^ (3 + (a / 5))) .* ((0.9) .^ n);

% 2
% x_max will denote the max value of the signal
x_max = max(x);
% n_max will be the time value where this max occurs
n_max = n(x==max(x));

% Printing out values for x_max and n_max
fprintf('x_max = %0.3f\n', x_max);
fprintf('n_max = %0.3f\n', n_max);

% 3
% n_turnon will denote the turn-on time
% percent_of_xmax is the value of x[n] is less than 1% of x_max
percent_of_xmax = (0.01 * x_max);
nrange = n(n<n_max);
xrange = (nrange .^ (3 + (a / 5))) .* ((0.9) .^ nrange);
n_turnon = max(nrange(xrange < percent_of_xmax));
fprintf('n_turnon = %0.3f\n', n_turnon);

% 4
% n_turnoff will denote the turn-off time
% percent_of_xmax is reused for n_turnoff
nrange = n(n>n_max);
xrange = (nrange .^ (3 + (a / 5))) .* ((0.9) .^ nrange);
n_turnoff = min(nrange(xrange < percent_of_xmax));

fprintf('n_turnoff = %0.3f\n', n_turnoff);
