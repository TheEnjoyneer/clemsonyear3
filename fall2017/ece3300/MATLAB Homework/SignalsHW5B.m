% Christopher Brant
% C19816588
% MATLAB Homework 5B Due on 11/15/17

clear; clc; close all;

% a, b, c, and d denote the four leftmost nonzero digits of my student ID
% second value of 1 is skipped
a = 1;
b = 9;
c = 8;
d = 6;

% Laplace Numerator polynomials are denoted as L_Nx
% Laplace Denominator polynomials are denoted as L_Dx
L_N1 = [1,0];
L_N2 = [1,a];
L_N3 = [1,2*b,b^2];
L_D1 = [1,3*c,3*(c^2),c^3];
L_D2 = [1,d];

% L_Num and L_Den denote the Laplace transform numerator and denominator
L_N12 = conv(L_N1,L_N2);
L_Num = conv(L_N12,L_N3);
L_Den = conv(L_D1,L_D2);

% [r_L,p_L,k_L] will denote the Laplace transform in question 1
[r_L,p_L,k_L] = residue(L_Num, L_Den);

% Print out residue results for the Laplace transform
fprintf('The following are Laplace Transform values\n');
for i=1:4
    fprintf('Value %0.0f of r is %0.2f\n', i, r_L(i));
    fprintf('Value %0.0f of p is %0.2f\n', i, p_L(i));
end

fprintf('Values of k are ');
fprintf('%0.1f ', k_L);
fprintf('\n');

% Print out the format of the partial fraction expansion
fprintf('The partial fraction expansion is as follows:\n');
fprintf('(%0.2f/(s-(%0.2f)))', r_L(1), p_L(1));
fprintf('+(%0.2f/(s-(%0.2f))^2)', r_L(2), p_L(2));
fprintf('+(%0.2f/(s-(%0.2f))^3)\n', r_L(3), p_L(3));
fprintf('+(%0.2f/(s-(%0.2f)))', r_L(4), p_L(4));
fprintf('+ %0.1f\n\n', k_L);

% When this prints, underneath write the time domain equation necessary
fprintf('For ROC Real{s} > -min{c,d},\n');
fprintf('Time domain function is as follows:\n');
fprintf('[(%0.2f)+(%0.2f)(t)+(%0.2f)(t^2)](e^(-8t))u(t) ',...
    r_L(1),r_L(2),r_L(3));
fprintf('+ %0.2f(e^(-6t))u(t) + (%0.2f)\x3b4(t)\n\n', r_L(4), k_L);

% Z_Nx and Z_Dx denote the terms of the Z transform
Z_N1 = [-(a^3)/1000,3*(a^2/100),-3*(a/10),1];
Z_N2 = [-(b^3)/1000,3*(b^2/100),-3*(b/10),1];
Z_D1 = [-(c^3)/1000,3*(c^2/100),-3*(c/10),1];
Z_D2 = [-d/10,1];

% Z_Num and Z_Den denote the Z transform numerator and denominator
Z_Num = conv(Z_N1,Z_N2);
Z_Den = conv(Z_D1,Z_D2);

% [r_Z,p_Z,k_Z] will denote the Z transform in question 3
[r_Z,p_Z,k_Z] = residue(Z_Num, Z_Den);

% Print out residue results for the Z transform
fprintf('The following are Z transform values\n');
for i=1:4
    fprintf('Value %0.0f of r is %0.2f\n', i, r_Z(i));
    fprintf('Value %0.0f of p is %0.2f\n', i, p_Z(i));
end

fprintf('Values of k are ');
fprintf('%0.4f ', k_Z);
fprintf('\n');

% Print out the format of the partial fraction expansion
r_Z_divp = r_Z ./ p_Z;
p_Z_div1 = 1 ./ p_Z;
fprintf('The partial fraction expansion is as follows:\n');
fprintf('(%0.2f/(1-(%0.2f)(z^(-1))))', r_Z_divp(1), p_Z_div1(1));
fprintf('+(%0.2f/(1-(%0.2f)(z^(-1))))', r_Z_divp(2), p_Z_div1(2));
fprintf('+(%0.2f/(1-(%0.2f)(z^(-1)))^2)\n', r_Z_divp(3), p_Z_div1(3));
fprintf('+(%0.2f/(1-(%0.2f)(z^(-1))^3)', r_Z_divp(4), p_Z_div1(4));
fprintf('+(%0.4f)z^(-2)', k_Z(1));
fprintf('+(%0.4f)z^(-1)', k_Z(2));
fprintf('+(%0.4f)', k_Z(3));
fprintf('\n\n');

% When this prints, underneath write the time domain equation necessary
fprintf('For ROC |z| > (max{c,d})/10,\n');
fprintf('Time domain function is as follows:\n');
fprintf('(%0.2f)((%0.2f)^n)u[n]', r_Z_divp(1), p_Z_div1(1));
fprintf('+(%0.2f)((%0.2f)^n)u[n]\n', r_Z_divp(2), p_Z_div1(2));
fprintf('+(%0.2f)(n+1)((%0.2f)^n)u[n]', r_Z_divp(3), p_Z_div1(3));
fprintf('+(%0.2f)(((n+1)(n+2))/2)((%0.2f)^n)u[n]\n',r_Z_divp(4),p_Z_div1(4));
fprintf('+(%0.4f)\x3b4[n-2]', k_Z(1));
fprintf('+(%0.4f)\x3b4[n-1]', k_Z(2));
fprintf('+(%0.4f)\x3b4[n]\n\n', k_Z(3));

fprintf('There is a known error when printing, the delta becomes a #');

