% Christopher Brant
% C19816588
% MATLAB Homework 1C Due on 9/13/17

clear; clc; close all;

% a denotes the leftmost numerical digit of my student ID number
a = 1;
% b denotes the time scale for sample widths
b = 0.01;

% 1
% t is the base time values that everything else will be based on
t = -10:b:10;

% x is the base function that everything else will be based on
x = (((a + 1) .* abs(t)) + ((a + 3) .* cos(t))) .* ((t>=-10)&(t<=10));


% Plotting x(t) in the following section
origin = [0, 0];        % origin values used for plotting
x_lims = [-10, 10];     % x-axis limits for base plot
y_lims = [-1, 20];      % y-axis limits for base plot

% Create new graph window
figure();
% Plot axis lines
plot(x_lims, origin, 'LineStyle', '-', 'Color',...
    [0,0,0], 'LineWidth', 1);
hold on;
plot(origin, y_lims, 'LineStyle', '-', 'Color',...
    [0,0,0], 'LineWidth', 1);
% Plotting x(t)
Plot_x = plot(t, x, 'LineStyle', '-', 'Color',...
    [0,0,1], 'LineWidth', 2);
hold off;
% Adding labels and axis values to the plot
axis(horzcat(x_lims, y_lims));
title('Plot 1C.1 Base Plot: x(t)');
xlabel('t');
ylabel('x(t)');

% 2
% t_2 will be the time for the derivative plot
t_2 = t(2:end);

% y will be the signal for the derivative plot
y = diff(x) / b;

% Plotting y(t) in the following section
x_lims = [-10, 10];     % x-axis limits for derivative plot
y_lims = [-7, 7];      % y-axis limits for derivate plot

% Create new graph window
figure();
% Plot axis lines
plot(x_lims, origin, 'LineStyle', '-', 'Color',...
    [0,0,0], 'LineWidth', 1);
hold on;
plot(origin, y_lims, 'LineStyle', '-', 'Color',...
    [0,0,0], 'LineWidth', 1);
% Plotting x(t)
Plot_x = plot(t_2, y, 'LineStyle', '-', 'Color',...
    [0,0,1], 'LineWidth', 2);
hold off;
% Adding labels and axis values to the plot
axis(horzcat(x_lims, y_lims));
title('Plot 1C.1 Derivate Plot: y(t)');
xlabel('t');
ylabel('y(t)');

% 3
% Determining y_max, y_min, t_max, t_min for the slope of x(t)
y_max = max(y);
t_max = t_2(y==y_max);
y_min = min(y);
t_min = t_2(y==y_min);

% Printing out maximum and minimum values
fprintf('y_max = %0.3f\nt_max = %0.3f\n', y_max, t_max);
fprintf('y_min = %0.3f\nt_min = %0.3f\n', y_min, t_min);

% 4
% Plotting z(t): the integral signal of y(t)
z = cumsum(y) * b;

% Plotting y(t) in the following section
x_lims = [-10, 10];     % x-axis limits for derivative plot
y_lims = [-16, 1];      % y-axis limits for derivate plot

% Create new graph window
figure();
% Plot axis lines
plot(x_lims, origin, 'LineStyle', '-', 'Color',...
    [0,0,0], 'LineWidth', 1);
hold on;
plot(origin, y_lims, 'LineStyle', '-', 'Color',...
    [0,0,0], 'LineWidth', 1);
% Plotting x(t)
Plot_x = plot(t_2, z, 'LineStyle', '-', 'Color',...
    [0,0,1], 'LineWidth', 2);
hold off;
% Adding labels and axis values to the plot
axis(horzcat(x_lims, y_lims));
title('Plot 1C.1 Integral Plot: z(t)');
xlabel('t');
ylabel('z(t)');