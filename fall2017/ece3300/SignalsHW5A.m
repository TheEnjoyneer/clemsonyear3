% Christopher Brant
% C19816588
% MATLAB Homework 5A Due on 11/8/17

clear; clc; close all;

% a denotes the leftmost nonzero digit of my student ID number
a = 1;

% b denotes the second leftmost nonzero digit of my student ID number
b = 9;

% c denotes the third leftmost nonezro digit of my student ID number
c = 8;

% w denotes the logarithmic scale for omega values
w = logspace(-1,4,500);

% Fnum and Fden denote the numerator and denominator of the transform
Fnum = ((10*b + i.*w).^3) .* ((100*c + i.*w).^2);
Fden = ((i*w).^2) ./ (a + i.*w);

% X denotes the fourier transform
X = Fnum ./ Fden;

% Xmag denotes the magnitude values of the transform in dB values
Xmag = 20 * log10(abs(X));

% Xphase denotes the phase values of the transform
Xphase = unwrap(angle(X));

% Plotting Bode magnitude plot
origin = [0, 0];           % origin values used for plotting
x_lims = [0.1, 10000];     % x-axis limits
y_lims = [200, 325];        % y-axis limits

% Create new graph window
figure();
% Plot magnitude values
semilogx(w, Xmag, 'LineStyle', '-', 'Color', [0,0,1], 'LineWidth', 2);
% Adding labels and axis values to the plot
axis(horzcat(x_lims, y_lims));
title('Plot 5A.1 Bode Magnitude Plot for X(j\omega)');
xlabel('\omega');
ylabel('\midX(j\omega)\mid in dB');

% Print out whether or not the system is active or passive
if (max(Xmag) <= 0)
    fprintf('The system is passive\n');
else
    fprintf('The system is active\n');
end

% Print out what type of filter the system is
fprintf('The system is bandstop\n');

% Plotting Bode phase plot
origin = [0, 0];           % origin values used for plotting
x_lims = [0.1, 10000];     % x-axis limits
y_lims = [-5, 10];        % y-axis limits

% Create new graph window
figure();
% Plot magnitude values
semilogx(w, Xphase, 'LineStyle', '-', 'Color', [0,0,1], 'LineWidth', 2);
% Adding labels and axis values to the plot
axis(horzcat(x_lims, y_lims));
title('Plot 5A.2 Bode Phase Plot for X(j\omega)');
xlabel('\omega');
ylabel('\angleX(j\omega)');



