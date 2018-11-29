% Christopher Brant
% C19816588
% MATLAB Homework 3A Due on 10/6/17

clear; clc; close all;

% a and b denote the leftmost 2 digits of my CUID number respectively
a = 1;
b = 9;

% c denotes the time scale for sampling
c = 0.01;

% time values for h(t) are denoted with t_h
t_h = 0:c:20;

% time values for periodic x(t) are denoted by t
t = -10:c:30;

% h(t) denoted by h
h = (exp(-a .* t_h) + exp(-b .* t_h)) .* ((t_h>=0)&(t_h<=20));

% Question 1 uses T_0 = 10, and plot per x(t) and per y(t) from -10 to 30
T_0 = 10;

% periodic time range is denoted as t_per
t_per = t-T_0.*round(t./T_0);

% x denotes the periodic x(t)
x_per = ((1 .* (t_per>=(-T_0/4))) - (1 .* (t_per>=(T_0/4)))) .*...
    ((t>=-10)&(t<=10));

% time range for convolution is denoted as t_conv
t_conv = (t(1)+t_h(1)):c:(t(end)+t_h(end));

% y(t) is the convolution of x(t) and h(t) and denoted by just y
y = conv(x_per,h) .* c;

% 2: Plot all necessary signals on the designated graphs.
% Plotting x_per in the following section
origin = [0, 0];        % origin values used for plotting
x_lims = [-10, 30];     % x-axis limits for base plot
y_lims = [-1, 2];       % y-axis limits for base plot

% Create new graph window
figure();
set(groot, 'defaultLegendInterpreter', 'latex');
% Plot axis lines
plot(x_lims, origin, 'LineStyle', '-', 'Color',...
    [0,0,0], 'LineWidth', 1);
hold on;
plot(origin, y_lims, 'LineStyle', '-', 'Color',...
    [0,0,0], 'LineWidth', 1);
% Plotting x_per
Plot_XPer = plot(t, x_per, 'LineStyle', '-', 'Color',...
    [0,0,1], 'LineWidth', 2);
Plot_YPer = plot(t_conv, y, 'LineStyle', '-.', 'Color',...
    [0,1,0], 'LineWidth', 2);
hold off;
% Adding labels and axis values to the plot
axis(horzcat(x_lims, y_lims));
title('Plot 3A.1 ${T}_{0} = 10: \tilde{y}(t)=[\tilde{x}(t)*h(t)]$',...
    'interpreter', 'latex');
xlabel('t');
ylabel('$\tilde{x}(t)\mid\tilde{y}(t)$',...
    'interpreter', 'latex');
legend([Plot_XPer, Plot_YPer], '$\tilde{x}(t)$',...
    '$\tilde{y}(t)$', 'Location', 'Northeast');

% Part 2 with T_0 = 2
% Question 1 uses T_0 = 10, and plot per x(t) and per y(t) from -10 to 30
T_0 = 2;

% periodic time range is denoted as t_per
t_per = t-T_0.*round(t./T_0);

% x denotes the periodic x(t)
x_per = ((1 .* (t_per>=(-T_0/4))) - (1 .* (t_per>=(T_0/4)))) .*...
    ((t>=-10)&(t<=10));

% time range for convolution is denoted as t_conv
t_conv = (t(1)+t_h(1)):c:(t(end)+t_h(end));

% y(t) is the convolution of x(t) and h(t) and denoted by just y
y = conv(x_per,h) .* c;

% 2: Plot all necessary signals on the designated graphs.
% Plotting x_per in the following section
origin = [0, 0];        % origin values used for plotting
x_lims = [-10, 30];     % x-axis limits for base plot
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
Plot_XPer = plot(t, x_per, 'LineStyle', '-', 'Color',...
    [0,0,1], 'LineWidth', 2);
Plot_YPer = plot(t_conv, y, 'LineStyle', '-.', 'Color',...
    [0,1,0], 'LineWidth', 2);
hold off;
% Adding labels and axis values to the plot
axis(horzcat(x_lims, y_lims));
title('Plot 3A.2 ${T}_{0} = 2 : \tilde{y}(t)=[\tilde{x}(t)*h(t)]$',...
    'interpreter', 'latex');
xlabel('t');
ylabel('$\tilde{x}(t)\mid\tilde{y}(t)$',...
    'interpreter', 'latex');
legend([Plot_XPer, Plot_YPer], '$\tilde{x}(t)$',...
    '$\tilde{y}(t)$', 'Location', 'Northeast');

% Part 3 with T_0 = 0.4
% Question 1 uses T_0 = 10, and plot per x(t) and per y(t) from -10 to 30
T_0 = 0.4;

% periodic time range is denoted as t_per
t_per = t-T_0.*round(t./T_0);

% x denotes the periodic x(t)
x_per = ((1 .* (t_per>=(-T_0/4))) - (1 .* (t_per>=(T_0/4)))) .*...
    ((t>=-10)&(t<=10));

% time range for convolution is denoted as t_conv
t_conv = (t(1)+t_h(1)):c:(t(end)+t_h(end));

% y(t) is the convolution of x(t) and h(t) and denoted by just y
y = conv(x_per,h) .* c;

% 2: Plot all necessary signals on the designated graphs.
% Plotting x_per in the following section
origin = [0, 0];        % origin values used for plotting
x_lims = [-10, 30];     % x-axis limits for base plot
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
Plot_XPer = plot(t, x_per, 'LineStyle', '-', 'Color',...
    [0,0,1], 'LineWidth', 2);
Plot_YPer = plot(t_conv, y, 'LineStyle', '-.', 'Color',...
    [0,1,0], 'LineWidth', 2);
hold off;
% Adding labels and axis values to the plot
axis(horzcat(x_lims, y_lims));
title('Plot 3A.3 ${T}_{0} = 0.4 : \tilde{y}(t)=[\tilde{x}(t)*h(t)]$',...
    'interpreter', 'latex');
xlabel('t');
ylabel('$\tilde{x}(t)\mid\tilde{y}(t)$',...
    'interpreter', 'latex');
legend([Plot_XPer, Plot_YPer], '$\tilde{x}(t)$',...
    '$\tilde{y}(t)$', 'Location', 'Northeast');

% Part 4 with T_0 = 0.4 and 5a and 5b
a = 5 * a;
b = 5 * b;

% h(t) denoted by h
h = (exp(-a .* t_h) + exp(-b .* t_h)) .* ((t_h>=0)&(t_h<=20));

% Question 1 uses T_0 = 10, and plot per x(t) and per y(t) from -10 to 30
T_0 = 0.4;

% periodic time range is denoted as t_per
t_per = t-T_0.*round(t./T_0);

% x denotes the periodic x(t)
x_per = ((1 .* (t_per>=(-T_0/4))) - (1 .* (t_per>=(T_0/4)))) .*...
    ((t>=-10)&(t<=10));

% time range for convolution is denoted as t_conv
t_conv = (t(1)+t_h(1)):c:(t(end)+t_h(end));

% y(t) is the convolution of x(t) and h(t) and denoted by just y
y = conv(x_per,h) .* c;

% 2: Plot all necessary signals on the designated graphs.
% Plotting x_per in the following section
origin = [0, 0];        % origin values used for plotting
x_lims = [-10, 30];     % x-axis limits for base plot
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
Plot_XPer = plot(t, x_per, 'LineStyle', '-', 'Color',...
    [0,0,1], 'LineWidth', 2);
Plot_YPer = plot(t_conv, y, 'LineStyle', '-.', 'Color',...
    [0,1,0], 'LineWidth', 2);
hold off;
% Adding labels and axis values to the plot
axis(horzcat(x_lims, y_lims));
title('Plot 3A.4 5a and 5b ${T}_{0} = 0.4 : \tilde{y}(t)=[\tilde{x}(t)*h(t)]$',...
    'interpreter', 'latex');
xlabel('t');
ylabel('$\tilde{x}(t)\mid\tilde{y}(t)$',...
    'interpreter', 'latex');
legend([Plot_XPer, Plot_YPer], '$\tilde{x}(t)$',...
    '$\tilde{y}(t)$', 'Location', 'Northeast');










