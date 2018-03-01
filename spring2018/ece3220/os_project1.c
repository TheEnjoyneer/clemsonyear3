/* os_project1.c
 * Christopher Brant
 * ECE 3220 Intro to Operating Systems
 * Project 1
 * Due 3/5/2018
 * The purpose of this is to learn to utilize
 * pipe() and fork() to write from one thread to 
 * another in only 1 direction.
 * compile: "gcc -Wall os_project1.c -o os_project1"
 * run: "./os_project1"
 */

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <time.h>
#include <sys/wait.h>
#include <sys/types.h>

#define MAX_NUM 1000000
#define ITERATIONS 5000
#define SEND_SIZE 4
#define PIPE_READ 0
#define PIPE_WRITE 1

int main(void)
{
	// Declare all variables necessary for overall run
	int i, j;
	pid_t pid;
	int pipe_end[2];
	int iterate_size, rec_total;
	int send_size, rec_size;
	unsigned long long num_to_send;
	unsigned long long num_received;
	unsigned long long send_sum, rec_ind_sum, rec_sum;
	unsigned long long send_avg, rec_avg;

	// Seed the random number sequence
	srand(time(NULL));

	// Create the pipe and check for failure, exit if failure is found
	if (pipe(pipe_end) == -1)
	{
		fprintf(stderr,"\nERROR: Pipe failed to open correctly.\n");
		exit(1);
	}

	// Create the child process
	pid = fork();

	// Conditional section below for error, producer, and consumer
	// Exit if failure is found
	if (pid < 0)
	{
		fprintf(stderr,"\nERROR: Consumer process failed to fork.\n");
		exit(1);
	}
	// Producer section of code is below 
	else if (pid > 0)
	{
		// Initialize send sum and total numbers sent to 0
		send_sum = 0;
		send_size = 0;

		// Close the reading end of the pipe on the producer end
		close(pipe_end[PIPE_READ]);

		// Run ITERATIONS number of iterations of random number creation and writing
		for (i = 0; i < ITERATIONS; i++)
		{
			// Choose amount of numbers to send this time and add them to total
			// Also ensure that the number is nonzero
			iterate_size = (rand() % SEND_SIZE) + 1;
			send_size += iterate_size;

			/* Create "iterate_size" random numbers to send
			 * Increment the sum of numbers sent
			 * Write each new random value to the pipe
			 */
			for (j = 0; j < iterate_size; j++)
			{
				num_to_send = (rand() % MAX_NUM) + 1;
				send_sum += num_to_send;
				write(pipe_end[PIPE_WRITE], &num_to_send, sizeof(num_to_send));
			}
		}

		// Close the writing end of the pipe on the producer end
		close(pipe_end[PIPE_WRITE]);

		// Compute and print the average number sent
		send_avg = send_sum / send_size;
		printf("Producer sent %d values with an average of %lld\n", send_size, send_avg);
	
		// Ensure the producer waits until the consumer finishes running to end itself
		wait(NULL);
	}
	// Consumer section of code is below
	else
	{
		// Close the writing end of the pipe on the consumer end
		close(pipe_end[PIPE_WRITE]);

		// Initialize rec sum and total numbers received to 0
		rec_sum = 0;
		rec_size = 0;
		rec_total = 0;

		/* Read each value from the pipe until it is empty
		 * Increment all sums and total values
		 * If 50 numbers have been received, compute partial average
		 */
		while(read(pipe_end[PIPE_READ], &num_received, sizeof(num_received)) != 0)
		{
			rec_ind_sum += num_received;
			rec_sum += num_received;
			rec_size++;
			rec_total++;

			// Conditional for partial average if 50 numbers are received
			if (rec_size == 50)
			{
				// Compute and print the individual average for this set of 50
				rec_avg = rec_ind_sum / rec_size;
				printf("Consumer received %d values with an average of %lld\n", rec_size, rec_avg);

				// Reset the individual receive sum and receive size
				rec_ind_sum = 0;
				rec_size = 0;
			}
		}

		// Close the read end of the pipe on the consumer end
		// And print the pipe has been closed
		close(pipe_end[PIPE_READ]);
		printf("Pipe was closed\n");

		// Compute and print last individual consumer average
		rec_avg = rec_ind_sum / rec_size;
		printf("Consumer received %d values with an average of %lld\n", rec_size, rec_avg);

		// Compute and print total consumer average
		rec_avg = rec_sum / rec_total;
		printf("Consumer received %d values with an average of %lld\n", rec_total, rec_avg);
	}

	return 0;
}