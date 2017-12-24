% Christopher Brant
% C19816588
% MATLAB Homework 2A Due on 9/20/17

clear; clc; close all;

% a will denote the leftmost digit of my student ID number
a = 1;
% b will denote the time sampling increment
b = 0.01;

% 1: Set all necessary values and lists for signals
% T_0 will denote the fundamental period of the following periodic signal
T_0 = 12;

% t will denote the base range of time for the signal
t = -20:b:20;
% t_per will denote the periodic signal's time range we will use.
t_per = t-T_0.*round(t./T_0);

% x_per will be the base periodic signal
x_per = (t_per+6).*((-6<=t_per)&(t_per<(a-5))) + ...
    ((a+1)/(11-a))*(6-t_per).*(((a-5)<=t_per)&(t_per<6));

% t_diff will denote the list of time values for the derivative signal
t_diff = t(2:end);

% x_diff will denote the derivative of the periodic signal
x_diff = diff(x_per)/b;

% z will denote the complex signal below
z = (a+10+(j*t))./(a+1+(j*t));

% mag_z will denote the magniture of z
mag_z = abs(z);

% angle_z will denote the angle of z
angle_z = angle(z);

% real_z will denote the real part of z
real_z = real(z);

% imag_z will denote the imaginary part of z
imag_z = imag(z);

% 2: Plot all necessary signals on the designated graphs.
% Plotting x_per in the following section
origin = [0, 0];        % origin values used for plotting
x_lims = [-20, 20];     % x-axis limits for base plot
y_lims = [-1, 3];       % y-axis limits for base plot

% Create new graph window
figure();
% Plot axis lines
plot(x_lims, origin, 'LineStyle', '-', 'Color',...
    [0,0,0], 'LineWidth', 1);
hold on;
plot(origin, y_lims, 'LineStyle', '-', 'Color',...
    [0,0,0], 'LineWidth', 1);
% Plotting x_per
plot(t, x_per, 'LineStyle', '-', 'Color',...
    [0,0,1], 'LineWidth', 2);
hold off;
% Adding labels and axis values to the plot
axis(horzcat(x_lims, y_lims));
title('Plot 2A.1 Base Plot: $\widetilde{x}(t)$', 'interpreter', 'latex');
xlabel('t');
ylabel('$\widetilde{x}(t)$', 'interpreter', 'latex');

% Plotting the derivative of x_per in the following section
origin = [0, 0];        % origin values used for plotting
x_lims = [-20, 20];     % x-axis limits for base plot
y_lims = [-1, 2];       % y-axis limits for base plot

% Create new graph window
figure();
% Plot axis lines
plot(x_lims, origin, 'LineStyle', '-', 'Color',...
    [0,0,0], 'LineWidth', 1);
hold on;
plot(origin, y_lims, 'LineStyle', '-', 'Color',...
    [0,0,0], 'LineWidth', 1);
% Plotting x_diff
plot(t_diff, x_diff, 'LineStyle', '-', 'Color',...
    [0,0,1], 'LineWidth', 2);
hold off;
% Adding labels and axis values to the plot
axis(horzcat(x_lims, y_lims));
title('Plot 2A.2 Derivative Plot: $\frac{d}{dt}\widetilde{x}(t)$',...
    'interpreter', 'latex');
xlabel('t');
ylabel('$\frac{d}{dt}\widetilde{x}(t)$', 'interpreter', 'latex');

% Plotting the magnitude and phase of z(t) in the following section
origin = [0, 0];        % origin values used for plotting
x_lims = [-20, 20];     % x-axis limits for base plot
y_lims = [-2, 6];       % y-axis limits for base plot

% Create new graph window
figure();
% Plot axis lines
plot(x_lims, origin, 'LineStyle', '-', 'Color',...
    [0,0,0], 'LineWidth', 1);
hold on;
plot(origin, y_lims, 'LineStyle', '-', 'Color',...
    [0,0,0], 'LineWidth', 1);
% Plotting |z(t)|
Plot_mag_z = plot(t, mag_z, 'LineStyle', '-', 'Color',...
    [0,0,1], 'LineWidth', 2);
% Plotting the angle of z(t)
Plot_angle_z = plot(t, angle_z, 'LineStyle', '-.', 'Color',...
    [0,1,0], 'LineWidth', 2);
hold off;
% Adding labels and axis values to the plot
axis(horzcat(x_lims, y_lims));
title('Plot 2A.3 Complex Signal Plot');
xlabel('t');
ylabel('|z(t)| and \anglez(t)');
legend([Plot_mag_z, Plot_angle_z],...
    '\midz(t)\mid', '\anglez(t)', 'Location', 'northeast');

% Plotting the real and imaginary parts of z(t) in the following section
origin = [0, 0];        % origin values used for plotting
x_lims = [-20, 20];     % x-axis limits for base plot
y_lims = [-3, 6];       % y-axis limits for base plot

% Create new graph window
figure();
% Plot axis lines
plot(x_lims, origin, 'LineStyle', '-', 'Color',...
    [0,0,0], 'LineWidth', 1);
hold on;
plot(origin, y_lims, 'LineStyle', '-', 'Color',...
    [0,0,0], 'LineWidth', 1);
% Plotting |z(t)|
Plot_real_z = plot(t, real_z, 'LineStyle', '-', 'Color',...
    [0,0,1], 'LineWidth', 2);
% Plotting the angle of z(t)
Plot_imag_z = plot(t, imag_z, 'LineStyle', '-.', 'Color',...
    [0,1,0], 'LineWidth', 2);
hold off;
% Adding labels and axis values to the plot
axis(horzcat(x_lims, y_lims));
title('Plot 2A.4 Real and Imaginary parts of z(t)');
xlabel('t');
ylabel('z(t)');
legend([Plot_real_z, Plot_imag_z],...
    'Re\{z(t)\}', 'Im\{z(t)\}/j', 'Location', 'northeast');
