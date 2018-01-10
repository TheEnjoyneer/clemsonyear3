% Christopher Brant
% C19816588
% MATLAB Homework 4B Due on 11/1/17

clear; clc; close all;

% a denotes the leftmost digit of my student ID number
a = 1;

%  b denotes the input sampling for frequency
b = 1;

% w will denote the frequency range
w = -1000:b:1000;

% H will denote the fourier transform of the given high pass filter
Hnumerator = ((i*w)+5*(10+a)).*((i*w)+5*(11+a));
Hdenominator = ((i*w)+10*(10+a)).*((i*w)+10*(11+a));
H = (Hnumerator ./ Hdenominator) .^ 3;

% H_mag denotes the magnitude response
H_mag = abs(H);

% H_phase denotes the phase response
H_phase = angle(H);

% Plotting magnitude response
origin = [0, 0];        % origin values used for plotting
x_lims = [-1000, 1000];     % x-axis limits for plot
y_lims = [-0.2, 1];   % y-axis limits for plot

% Create new graph window
figure();
% Plot axis lines
plot(x_lims, origin, 'LineStyle', '-', 'Color',...
    [0,0,0], 'LineWidth', 1);
hold on;
plot(origin, y_lims, 'LineStyle', '-', 'Color',...
    [0,0,0], 'LineWidth', 1);
% Plot each individual x_k value
plot(w, H_mag, 'LineStyle', '-', 'Color',[0,0,1], 'LineWidth', 2);
% Adding labels and axis values to the plot
axis(horzcat(x_lims, y_lims));
title('Plot 4B.1 \midH(j\omega)\mid The Magnitude Response');
xlabel('\omega');
ylabel('\midH(j\omega)\mid');

% Plotting phase response
origin = [0, 0];        % origin values used for plotting
x_lims = [-1000, 1000];     % x-axis limits for plot
y_lims = [-3, 3];   % y-axis limits for plot

% Create new graph window
figure();
% Plot axis lines
plot(x_lims, origin, 'LineStyle', '-', 'Color',...
    [0,0,0], 'LineWidth', 1);
hold on;
plot(origin, y_lims, 'LineStyle', '-', 'Color',...
    [0,0,0], 'LineWidth', 1);
% Plot each individual x_k value
plot(w, unwrap(H_phase), 'LineStyle', '-', 'Color',[0,0,1], 'LineWidth', 2);
% Adding labels and axis values to the plot
axis(horzcat(x_lims, y_lims));
title('Plot 4B.2 \angleH(j\omega) The Phase Response');
xlabel('\omega');
ylabel('\angleH(j\omega)');

% passPer denotes the passband atten. level as a decimal value % of peak
passPer = 0.90;

% stopPer denotes the stopband atten. level as a decimal value % of peak
stopPer = 0.10;

% H_max denotes the maximum value of the magnitude response
H_max = max(H_mag);

% pass denotes the passband attenuation level
pass = passPer * H_max;

% stop denotes the stopband attenuation level
stop = stopPer * H_max;

% passFreq and stopFreq denote the pass and stop band attenuation freq.
passFreq = abs(w(H==min(H(H_mag>=pass))));
stopFreq = abs(w(H==max(H(H_mag<=stop))));

% rollOff denotes the roll off ratio
rollOff = passFreq / stopFreq;

fprintf('Passband attenuation level is %0.3f\n', pass);
fprintf('Passband attenuation frequency is %0.3f\n', passFreq);
fprintf('Stopband attenuation level is %0.3f\n', stop);
fprintf('Stopband attenuation frequency is %0.3f\n', stopFreq);
fprintf('Roll-off ratio is %0.3f\n', rollOff);

% D denotes the group delay of the signal
D = -diff(unwrap(H_phase)) / b;

% w_diff denotes the frequency vector for the differentiated signal
w_diff = w(2:end);

% Plotting group delay
origin = [0, 0];        % origin values used for plotting
x_lims = [-1000, 1000];     % x-axis limits for plot
y_lims = [-0.06, 0.01];   % y-axis limits for plot

% Create new graph window
figure();
% Plot axis lines
plot(x_lims, origin, 'LineStyle', '-', 'Color',...
    [0,0,0], 'LineWidth', 1);
hold on;
plot(origin, y_lims, 'LineStyle', '-', 'Color',...
    [0,0,0], 'LineWidth', 1);
% Plot each individual x_k value
plot(w_diff, D, 'LineStyle', '-', 'Color',[0,0,1], 'LineWidth', 2);
% Adding labels and axis values to the plot
axis(horzcat(x_lims, y_lims));
title('Plot 4B.3 D(\omega) The Group Delay');
xlabel('\omega');
ylabel('D(\omega)');

% D_max denotes the maximum value of the group delay
D_max = max(D);

% w_Dmax denotes the frequency(ies) that the maximum group delay occur(s)
w_Dmax = w_diff(D==D_max);

fprintf('The maximum Group Delay is %0.3f\n', D_max);
fprintf('The/A frequency of the maximum Group Delay is %0.3f\n', w_Dmax);





