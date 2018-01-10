% Christopher Brant
% C19816588
% MATLAB Homework 2B Due on 9/27/17

clear; clc; close all;

% a denotes the leftmost digit of my student ID number
a = 1;
% b denotes the sampling time of the signal
b = 0.01;

% t denotes the time range in which the signal will be on
t = -4:b:4;

% x will be the values of the first signal
x = t .* ((t>=-4)&(t<=4));

% y will be the values of the second signal
y = x + (cos((a+1).*t)) .* ((t>=-4)&(t<=4));

% z will be the values of the third signal
z = x - (cos((a+1).*t)) .* ((t>=-4)&(t<=4));

% Ex, Ey, and Ez will be the corresponding energies of x, y, and z
Ex = sum(abs(x) .^ 2) .* b;
Ey = sum(abs(y) .^ 2) .* b;
Ez = sum(abs(z) .^ 2) .* b;

% Rxy, Rxz, and Ryz will be the correlations of xy, xz, and yz
Rxy = sum(x .* conj(y)) .* b;
Rxz = sum(x .* conj(z)) .* b;
Ryz = sum(y .* conj(z)) .* b;

% MSExy, MSExz, and MSEyz will be the mean square error of xy, xz, and yz
MSExy = sum(abs(x-y) .^ 2) .* b;
MSExz = sum(abs(x-z) .^ 2) .* b;
MSEyz = sum(abs(y-z) .^ 2) .* b;

fprintf('Ex    = %6.3f, Ey    = %6.3f, Ez    = %6.3f\n', Ex, Ey, Ez);
fprintf('Rxy   = %6.3f, Rxz   = %6.3f, Ryz   = %6.3f\n', Rxy, Rxz, Ryz);
fprintf('MSExy = %6.3f, MSExz = %6.3f, MSEyz = %6.3f\n',...
    MSExy, MSExz, MSEyz)









