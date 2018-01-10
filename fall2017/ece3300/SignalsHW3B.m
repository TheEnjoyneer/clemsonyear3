% Christopher Brant
% C19816588
% MATLAB Homework 3B Due on 10/13/17

clear; clc; close all;

% a denotes the leftmost digit of my aforementioned student ID number
a = 1;

% b denotes the time and frequency sampling values
b = 0.01;

% t will denote the time range
t = -5:b:5;

% w will denote the frequency range
w = -10:b:10;

% x will denote the signal value
x = ((a + 1 + t) ./ (a + 1 + (t .^ 2))) .* ((t>=-5)&(t<=5));

% X will denote the fourier transform of the base signal
X = x * exp(-i*t.'*w) .* b;

% X_mag will denote the magnitude of the fourier transform
X_mag = abs(X);

% X_phase will denote the phase of the fourier transform
X_phase = angle(X);

% UX_phase will denote the unwrapped phase of the fourier transform
UX_phase = unwrap(X_phase);

% w_0 will denote the frequency range for the band 0:5/2
% X_0 will denote the fourier transform for when w is 0:5/2
% Eband will denote the energy of the frequency band 0:5/2
w_0 = (-5/2):b:(5/2);
X_0 = x * exp(-i*t.'*w_0) .* b;
Eband = (1/(2*pi)) * sum(abs(X_0).^2) .* b;

% Ex will denote the total energy of the signal
Ex = (1/(2*pi)) * sum(X_mag.^2) .* b;

% E_percent is the energy percentage of the 0:5/2 band out of the total
E_percent = (Eband / Ex) * 100;

% Y will denote the fourier transform of y(t)
Y = x * exp(-i*t.'*w_0) .* b;

% y will denote the inverse fourier transform of Y
y = Y * exp(i*w_0.'*t) .* b ./ (2.*pi);

fprintf('The percentage of the total power in the frequency band from');
fprintf(' [0:5/2] is %0.2f%%\n', E_percent);

% Plotting X(jw) and |X(jw)| over -10 <= w <= 10
origin = [0, 0];        % origin values used for plotting
x_lims = [-10, 10];     % x-axis limits for base plot
y_lims = [-1, 4];       % y-axis limits for base plot

% Create new graph window
figure();
% Plot axis lines
plot(x_lims, origin, 'LineStyle', '-', 'Color',...
    [0,0,0], 'LineWidth', 1);
hold on;
plot(origin, y_lims, 'LineStyle', '-', 'Color',...
    [0,0,0], 'LineWidth', 1);
% Plotting x_per
Plot_X_mag = plot(w, X_mag, 'LineStyle', '-', 'Color',...
    [0,0,1], 'LineWidth', 2);
hold off;
% Adding labels and axis values to the plot
axis(horzcat(x_lims, y_lims));
title('Plot 3B.1 \midX(j\omega)\mid');
xlabel('\omega');
ylabel('\midX(j\omega)\mid');

% Plotting <X(jw) over -10<=w<=10
origin = [0, 0];        % origin values used for plotting
x_lims = [-10, 10];     % x-axis limits for base plot
y_lims = [-80, 5];       % y-axis limits for base plot

% Create new graph window
figure();
% Plot axis lines
plot(x_lims, origin, 'LineStyle', '-', 'Color',...
    [0,0,0], 'LineWidth', 1);
hold on;
plot(origin, y_lims, 'LineStyle', '-', 'Color',...
    [0,0,0], 'LineWidth', 1);
% Plotting x_per
Plot_UX_phase = plot(w, UX_phase, 'LineStyle', '-', 'Color',...
    [0,0,1], 'LineWidth', 2);
hold off;
% Adding labels and axis values to the plot
axis(horzcat(x_lims, y_lims));
title('Plot 3B.2 \angleX(j\omega)');
xlabel('\omega');
ylabel('\angleX(j\omega)');

% Plotting x(t) and y(t) both on the same graph from -5<=t<=5
origin = [0, 0];        % origin values used for plotting
x_lims = [-5, 5];     % x-axis limits for base plot
y_lims = [-1, 2];       % y-axis limits for base plot

% Create new graph window
figure();
% Plot axis lines
plot(x_lims, origin, 'LineStyle', '-', 'Color',...
    [0,0,0], 'LineWidth', 1);
hold on;
plot(origin, y_lims, 'LineStyle', '-', 'Color',...
    [0,0,0], 'LineWidth', 1);
% Plotting x_per
Plot_x = plot(t, x, 'LineStyle', '-', 'Color',...
    [0,0,1], 'LineWidth', 2);
Plot_y = plot(t, y, 'LineStyle', '-.', 'Color',...
    [0,1,0], 'LineWidth', 2);
hold off;
% Adding labels and axis values to the plot
axis(horzcat(x_lims, y_lims));
title('Plot 3B.3 x(t) and y(t)');
xlabel('t');
ylabel('x(t) and y(t)');
legend([Plot_x, Plot_y], 'x(t)',...
    'y(t)', 'Location', 'Northeast');





