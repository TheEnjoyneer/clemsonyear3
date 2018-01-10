% Christopher Brant
% C19816588
% MATLAB Homework 4A Due on 10/25/17

clear; clc; close all;

% a denotes the leftmost digit of my CUID number listed above
% b denotes the time sampling value used
a = 1;
b = 0.01;

% T_0 denotes the fundamental period of the function x(t)
T_0 = a + 2;

% t denotes the range of time values for the signal
t = (-T_0/2):b:(T_0/2) ;

% x denotes the signal for x(t)
x = ((t .* exp(t)) .* ((t>=-1)&(t<0))) + ((t .* exp(-t)) .* ((t>=0)&(t<1)));

% we want Xk values for k = -16:16
k = -16:16;

% the corresponding frequencies are denoted by w
w = k .* 2 .* pi ./ T_0;

% x_k denotes the corresponding Xk values 
x_k = x * exp(-i * t .' * w) ./ T_0 .* b;

% x_kmag denotes the magnitude of the Xk values
x_kmag = abs(x_k);

% Plotting x_k on stem plot
origin = [0, 0];        % origin values used for plotting
x_lims = [-17, 17];     % x-axis limits for base plot
y_lims = [-0.1, 0.2];   % y-axis limits for base plot

% Create new graph window
figure();
% Plot axis lines
plot(y_lims, origin, 'LineStyle', '-', 'Color', [0,0,0], 'LineWidth', 1);
hold on;
% Plot each individual x_k value
stem(k, abs(x_k), 'Marker', '.', 'Color', [0,0,0.8], 'LineWidth', 2);
% Adding labels and axis values to the plot
axis(horzcat(x_lims, y_lims));
title('Plot 4A.1 \midx_{k}\mid');
xlabel('k');
ylabel('\midx_{k}\mid');

% P_x denotes the total power of the signal
P_x = sum(x .^ 2 .* ((t>=-1)&(t<=1))) .* b / T_0;

% P_X denotes the power in the Xth harmonic
P_0 = sum(x_kmag(k==0) .^ 2);
P_1 = sum(x_kmag((k==-1)|(k==1)) .^ 2);
P_2 = sum(x_kmag((k==-2)|(k==2)) .^ 2);

% P_Xperc denotes the percentage of the total power in the Xth harmonic
P_0perc = (P_0 / P_x) * 100;
P_1perc = (P_1 / P_x) * 100;
P_2perc = (P_2 / P_x) * 100;

% Print out percentage values
fprintf('DC Power Percentage = %0.3f%%\n', P_0perc);
fprintf('Fundamental Frequency Power Percentage = %0.3f%%\n', P_1perc);
fprintf('Second Harmonic Power Percentage = %0.3f%%\n', P_2perc);

% x_per and xX_per will denote the Xth value of k periodic signals
x_per = x_k.*((k>=-16)&(k<=16))*exp(i*k.'*t.*2.*pi./T_0);
x1_per = x_k.*((k>=-1)&(k<=1))*exp(i*k.'*t.*2.*pi./T_0);
x2_per = x_k.*((k>=-2)&(k<=2))*exp(i*k.'*t.*2.*pi./T_0);
x4_per = x_k.*((k>=-4)&(k<=4))*exp(i*k.'*t.*2.*pi./T_0);

% Plotting x_per for total x_per and for k = 1, 2, and 4
origin = [0, 0];              % origin values used for plotting
x_lims = [-T_0/2, T_0/2];     % x-axis limits for plot
y_lims = [-0.5, 0.5];           % y-axis limits for plot

% Create new graph window
figure();
% Plot axis lines
plot(x_lims, origin, 'LineStyle', '-', 'Color',...
    [0,0,0], 'LineWidth', 1);
hold on;
plot(origin, y_lims, 'LineStyle', '-', 'Color',...
    [0,0,0], 'LineWidth', 1);
% Plotting x_per, x1_per, x2_per, and x4_per
Plot_x_per = plot(t, real(x), 'LineStyle', '-', 'Color',...
    [0,0,1], 'LineWidth', 2);
Plot_x1_per = plot(t, real(x1_per), 'LineStyle', '-.', 'Color',...
    [0,1,0], 'LineWidth', 2);
Plot_x2_per = plot(t, real(x2_per), 'LineStyle', '--', 'Color',...
    [1,0,0], 'LineWidth', 2);
Plot_x4_per = plot(t, real(x4_per), 'LineStyle', '-', 'Color',...
    [0,1,1], 'LineWidth', 2);
% Adding labels and axis values
axis(horzcat(x_lims, y_lims));
title('Plot 4A.2 $\tilde{x}$(t) and its truncated expansions',...
    'interpreter', 'latex');
xlabel('t');
ylabel('$\tilde{x}$(t)', 'interpreter', 'latex');
leg = legend([Plot_x_per, Plot_x1_per, Plot_x2_per, Plot_x4_per],...
    '$\tilde{x}$(t)','$\tilde{x}_{1}(t)$',...
    '$\tilde{x}_{2}(t)$', '$\tilde{x}_{4}(t)$');
set(leg, 'Location', 'Southeast');
set(leg, 'Interpreter', 'latex');

% MSEx_xX will denote the mean-square error of x(t) and x_k(t) with X = k
MSEx_x1 = P_x - sum(x_kmag((k>=-1)&(k<=1)) .^ 2);
MSEx_x2 = P_x - sum(x_kmag((k>=-2)&(k<=2)) .^ 2);
MSEx_x4 = P_x - sum(x_kmag((k>=-4)&(k<=4)) .^ 2);

% Print out values for MSEx_xX
fprintf('MSE of x and x1 = %0.3f\n', MSEx_x1);
fprintf('MSE of x and x2 = %0.3f\n', MSEx_x2);
fprintf('MSE of x and x4 = %0.3f\n', MSEx_x4);




