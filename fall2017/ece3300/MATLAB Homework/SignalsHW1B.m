% Christopher Brant
% C19816588
% MATLAB Homework 1B Due on 9/6/17

clear; clc; close all;

% b denotes the rightmost digit of the above given student ID number
b = 8;

% t is the list of time samples
t = -20:0.01:20;
% x is the list of signal values
x = (b+1+t).*((-(b+1)<=t)&(t<1))+(b+3-(2.*t)).*((1<=t)&(t<((b+3)/2)));

% Vertical transformations of x using original t
x1_3v = 1.3 .* x;
x0_7v = 0.7 .* x;

% Time shifts
t_1 = t + 1;
t_2 = t + 2;
t_3 = t + 3;

% Time scaling
t1_3 = t ./ 1.3;
t0_7 = t ./ 0.7;

% Even and Odd Signal Combinations
even_x = 0.5 * (x+x(end:-1:1));
odd_x = 0.5 * (x-x(end:-1:1));

% Plotting x(t), 1.3x(t), and 0.7x(t) on the same graph
% in the following section
origin = [0, 0];        % origin values used for plotting
xv_lims = [-10, 10];    % x-axis limits for vertical transform plot
yv_lims = [-1, 14];    % y-axis limits for vertical transform plot

% Create new graph window
figure();
% Plot axis lines
plot(xv_lims, origin, 'LineStyle', '-', 'Color',...
    [0,0,0], 'LineWidth', 1);
hold on;
plot(origin, yv_lims, 'LineStyle', '-', 'Color',...
    [0,0,0], 'LineWidth', 1);
% Plotting x(t)
Plot_x = plot(t, x, 'LineStyle', '-', 'Color',...
    [0,0,1], 'LineWidth', 2);
% Plotting 1.3x(t)
Plot_1_3x = plot(t, x1_3v, 'LineStyle', '--', 'Color',...
    [0,1,0], 'LineWidth', 2);
% Plotting 0.7x(t)
Plot_0_7x = plot(t, x0_7v, 'LineStyle', '-.', 'Color',...
    [1,0,0], 'LineWidth', 2);
hold off;
% Adding labels and axis values to the plot
axis(horzcat(xv_lims, yv_lims));
title('Plot 1B.1 Amplitude Scaling');
xlabel('t');
ylabel('x(t)');
legend([Plot_x, Plot_1_3x, Plot_0_7x],...
    'x(t)', '1.3x(t)', '0.7x(t)', 'Location', 'northeast');

% Plotting x(t), x(t-1), x(t-2), and x(t-3) on the same graph
xh1_lims = [-10, 10];   % x-axis limits for time shifting graph
yh1_lims = [-1, 11];    % y-axis limits for time shifting graph

% Create new graph window
figure();
% Plot axis lines
plot(xh1_lims, origin, 'LineStyle', '-', 'Color',...
    [0,0,0], 'LineWidth', 1);
hold on;
plot(origin, yh1_lims, 'LineStyle', '-', 'Color',...
    [0,0,0], 'LineWidth', 1);
% Plotting x(t)
Plot_x = plot(t, x, 'LineStyle', '-', 'Color',...
    [0,0,1], 'LineWidth', 2);
% Plotting x(t-1)
Plot_x_1 = plot(t_1, x, 'LineStyle', '--', 'Color',...
    [0,1,0], 'LineWidth', 2);
% Plotting x(t-2)
Plot_x_2 = plot(t_2, x, 'LineStyle', '-.', 'Color',...
    [1,0,0], 'LineWidth', 2);
% Plotting x(t-3)
Plot_x_3 = plot(t_3, x, 'LineStyle', ':', 'Color',...
    [1,0,1], 'LineWidth', 2);
hold off;

% Adding labels and axis values to the plot
axis(horzcat(xh1_lims, yh1_lims));
title('Plot 1B.2 Time Shifting');
xlabel('t');
ylabel('x(t)');
legend([Plot_x, Plot_x_1, Plot_x_2, Plot_x_3],...
    'x(t)', 'x(t-1)', 'x(t-2)', 'x(t-3)', 'Location', 'northeast');

% Plotting x(t), x(1.3t), and x(0.7t) on the same graph
xh2_lims = [-15, 15];    % x-axis limits for time scaling plot
yh2_lims = [-1, 11];    % y-axis limits for time scaling plot

% Create new graph window
figure();
% Plot axis lines
plot(xh2_lims, origin, 'LineStyle', '-', 'Color',...
    [0,0,0], 'LineWidth', 1);
hold on;
plot(origin, yh2_lims, 'LineStyle', '-', 'Color',...
    [0,0,0], 'LineWidth', 1);
% Plotting x(t)
Plot_x = plot(t, x, 'LineStyle', '-', 'Color',...
    [0,0,1], 'LineWidth', 2);
% Plotting x(1.3t)
Plot_x1_3 = plot(t1_3, x, 'LineStyle', '--', 'Color',...
    [0,1,0], 'LineWidth', 2);
% Plotting x(0.7t)
Plot_x0_7 = plot(t0_7, x, 'LineStyle', '-.', 'Color',...
    [1,0,0], 'LineWidth', 2);
hold off;

% Adding labels and axis values to the plot
axis(horzcat(xh2_lims, yh2_lims));
title('Plot 1B.3 Time Scaling');
xlabel('t');
ylabel('x(t)');
legend([Plot_x, Plot_x1_3, Plot_x0_7],...
    'x(t)', 'x(1.3t)', 'x(0.7t)', 'Location', 'northeast');

% Plotting Ev{x(t)}
xev_lims = [-10, 10];   % x-axis limits for the even signal
yev_lims = [-1, 10];    % y-axis limits for the even signal

% Create new graph window
figure();
% Plot axis lines
plot(xev_lims, origin, 'LineStyle', '-', 'Color',...
    [0,0,0], 'LineWidth', 1);
hold on;
plot(origin, yev_lims, 'LineStyle', '-', 'Color',...
    [0,0,0], 'LineWidth', 1);
% Plot Ev{x(t)}
Plot_Ev = plot(t, even_x, 'LineStyle', '-', 'Color',...
    [0,0,1], 'LineWidth', 2);
hold off;

% Adding labels and axis values to the plot
axis(horzcat(xev_lims, yev_lims));
title('Plot 1B.4 The Even Signal: Ev\{x(t)\}');
xlabel('t');
ylabel('x(t)');

% Plotting Od{x(t)}
xod_lims = [-10, 10];   % x-axis limits for the odd signal
yod_lims = [-2, 2];    % y-axis limits for the odd signal

% Create new graph window
figure();
% Plot axis lines
plot(xod_lims, origin, 'LineStyle', '-', 'Color',...
    [0,0,0], 'LineWidth', 1);
hold on;
plot(origin, yod_lims, 'LineStyle', '-', 'Color',...
    [0,0,0], 'LineWidth', 1);
% Plot Ev{x(t)}
Plot_Od = plot(t, odd_x, 'LineStyle', '-', 'Color',...
    [0,0,1], 'LineWidth', 2);
hold off;

% Adding labels and axis values to the plot
axis(horzcat(xod_lims, yod_lims));
title('Plot 1B.5 The Odd Signal: Od\{x(t)\}');
xlabel('t');
ylabel('x(t)');

