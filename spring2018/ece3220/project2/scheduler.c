/******************************************/
/* Names: Christopher Brant               */
/*    and Jennifer Starcher               */
/* Project: CPSC 3220 Scheduling Project  */
/* Due: April 9, 2018                     */
/*                                        */
/* Compile: gcc scheduler.c               */
/* Run: ./scheduler                       */
/******************************************/
#include <stdio.h>
#include <stdlib.h>

#define MAX 1000

/* struct represention one process/task of work */
typedef struct _process {
   int process_id;
   int arrival_time;
   int process_length;
   int process_loaded;
   int time_remaining;
   int time_waiting;
   int completion_time;
   int response_time;
} process;

/* Global variable are poor, but fast to code up */
process simulation_load[MAX]; /* array to hold entire work load */
process work_queue[MAX];  /* array representing current work in progress */
process on_cpu;  /* cpu hold the one task running on the cpu */

int number_of_processes;  /* number of processes to simulate during the simulation (total workload)*/
int scheduling_policy; /* 0=FIFO (no premption), 1=SJF (choose), 2=RR (premption is needed) */
int preemption_policy;  /* 0 is off 1 is on (scheduling policy will overrule this setting) */
int time_quantum;  /* used in preemption */
int master_clock;  /* clock start at 0 */
int processes_left;  /* processes currently loaded in the work_queue */
int switches;  /* number of context switches in simulation */

/* otuput section for reporting purposes */
void print_report()
{
   int i, avg;

   avg = 0;
   if (scheduling_policy == 0) { printf("Scheduling Policy: FIFO\n"); }
   else if (scheduling_policy == 1) { printf("Scheduling Policy: SJF\n"); }
   else if (scheduling_policy == 2) { printf("Scheduling Policy: RR\n"); }

   if (preemption_policy == 0) { printf("Premption: OFF\n"); }
   else if (preemption_policy == 1) { printf("Premption: ON\n"); }

   printf("Time Quantium: %d\n", time_quantum);
   printf("Number of Processes: %d\n\n", number_of_processes);

   for (i=0; i<number_of_processes; i++)
   {
      printf("Process ID: %d\n", simulation_load[i].process_id);
      printf("   Arrival Time: %d\n", simulation_load[i].arrival_time);
      printf("   Process Length: %d\n", simulation_load[i].process_length);
      printf("   Completion Time: %d\n", simulation_load[i].completion_time);
      printf("   Response Time: %d\n\n", simulation_load[i].response_time);
      avg += simulation_load[i].response_time;
   }

   avg /= number_of_processes;
   printf(" Avg Response Time: %d\n", avg);
   printf(" Number of Context Switches: %d\n\n", switches);
}

/* debuging output section */
void print_machine_state() {
    int i;

   printf("\n\n TOTAL WORK LOAD \n");

   for (i=0; i<number_of_processes; i++)
   {
      printf("Process ID: %d\n", simulation_load[i].process_id);
      printf("   Arrival Time: %d\n", simulation_load[i].arrival_time);
      printf("   Process Length: %d\n", simulation_load[i].process_length);
      printf("   Process Loaded: %d\n", simulation_load[i].process_loaded);
      printf("   Time Remaining: %d\n", simulation_load[i].time_remaining);
      printf("   Time Waiting: %d\n", simulation_load[i].time_waiting);
      printf("   Completion Time: %d\n", simulation_load[i].completion_time);
      printf("   Response Time: %d\n\n", simulation_load[i].response_time);
   }

   printf("\n\n CPU \n");
   printf("Process ID: %d\n", on_cpu.process_id);
   printf("   Time Remaining: %d\n", on_cpu.time_remaining);
   printf("   Time Waiting: %d\n", on_cpu.time_waiting);

   printf("\n\n WORK QUEUE \n");

   for (i=0; i<processes_left; i++)
   {
      printf("Process ID: %d\n", work_queue[i].process_id);
      printf("   Time Remaining: %d\n", work_queue[i].time_remaining);
      printf("   Time Waiting: %d\n", work_queue[i].time_waiting);
   }

   printf("\n    Switches = %d\n", switches);
   printf("Master Clock = %d\n", master_clock);
   printf("\n\n");
}

/* Stop Running process                              */
/*    Increment switches counter                     */
/*    If task is done (time_remaining <= 0) then     */
/*       compute results and update total_simulation */
/*    else                                           */
/*       move task to the end of the work_queue      */
void preempt_process()
{
   int i;

   // Increment switches counter
   switches++;

   if (on_cpu.time_remaining <= 0)
   {
      for(i = 0; simulation_load[i].process_id != on_cpu.process_id; i++);

      simulation_load[i].completion_time = master_clock;
      simulation_load[i].response_time = master_clock - simulation_load[i].arrival_time;
      on_cpu.process_id = -1;
      processes_left--;
   }
   else
   {
      for(i = 0; work_queue[i].process_id != -1; i++);

      work_queue[i].process_id = on_cpu.process_id;
      work_queue[i].time_remaining = on_cpu.time_remaining;

      on_cpu.process_id = -1;
   }
}

/* Run one process and update the waiting time for all processes in the work_queue */
/*  (Watch out for process that have less than time quantium work left to do)      */
/*    Check Premption Policy                                                       */
/*    if NO premption                                                              */
/*       "run task to completion"                                                  */
/*       update all the waiting times for processes in the work_queue              */
/*    else (YES premption)                                                         */
/*       if (time_remaining <= time_quantum) [check for short task]               */
/*          "run task to completion"                                               */
/*          update all the waiting times for processes in the work_queue           */
/*       else                                                                      */
/*          "run task for one time_quantum"                                       */
/*          update all the waiting times for processes in the work_queue           */
void run_process()
{
   int i;

   if (preemption_policy == 0 || on_cpu.time_remaining < time_quantum)
   {
      master_clock += on_cpu.time_remaining;
      on_cpu.time_remaining = 0;
      on_cpu.completion_time = master_clock;
      on_cpu.response_time = master_clock - on_cpu.arrival_time;

      for (i = 0; i < processes_left; i++)
      {
         work_queue[i].time_waiting = master_clock - work_queue[i].arrival_time;
      }
   }
   else
   {
      if (on_cpu.time_remaining <= time_quantum)
      {
         master_clock += on_cpu.time_remaining;
         on_cpu.time_remaining = 0;

         for (i = 0; i < processes_left; i++)
         {
            work_queue[i].time_waiting = master_clock - work_queue[i].arrival_time;
         }
      }
      else
      {
         master_clock += time_quantum;
         on_cpu.time_remaining -= time_quantum;

         for (i = 0; i < processes_left; i++)
         {
            work_queue[i].time_waiting = master_clock - work_queue[i].arrival_time;
         }
      }
   }
}

/* Decide which process will be loaded onto the cpu to run next based on Scheduling Policy */
/*    If FIFO or RR                                                                           */
/*       move work_queue[0] to cpu                                                            */
/*       move all other tasks up one slot                                                     */
/*    If SJF (premption does not matter)                                                      */
/*       find "shortest job left" and move it to cpu                                          */
/*       move all other tasks up one slot                                                     */
void load_process()
{
   int i, min = 0;

   if ((scheduling_policy == 0) || (scheduling_policy == 2))
   {
      on_cpu = work_queue[0];

      for (i = 0; i < processes_left; i++)
      {
         work_queue[i] = work_queue[i+1];
      }

      processes_left--;
   }
   else if (scheduling_policy == 1)
   {
      for (i = 0; i < processes_left; i++)
      {
         if (work_queue[i].process_length < work_queue[min].process_length)
         {
            min = i;
         }
      }

      on_cpu = work_queue[min];

      for (i = min; i < processes_left; i++)
      {
         work_queue[i] = work_queue[i+1];
      }

      processes_left--;
   }
}

/* Copy "new" processes from simulation load to the end of work queue */
/*    For each task in simulation_load <= master_clock and NOT loaded */
/*       copy task from simulation_load to the end of the work_queue */
void new_process()
{
   int i, j;

   for (i = 0; simulation_load[i].process_id != -1; i++)
   {
      // fix the last test for equality
      if (simulation_load[i].arrival_time <= master_clock && simulation_load[i].process_loaded == 1)
      {
         for (j = 0; work_queue[i].process_id != -1; j++);

         work_queue[j] = simulation_load[i];
         processes_left++;
      }
   }
}

void load_task_simulation_data()
{
     FILE *fp;
     int i;
     char filler[100];  // used to read to end of line in the config files -- like a NULL read

   // set all process slots to "empty (-1)"
   for (i=0; i<MAX; i++)
   {
      simulation_load[i].process_id = -1;
      work_queue[i].process_id = -1;
   }

   // open input file
   fp = fopen("simulation_load.txt","r");

   fscanf(fp,"%d",&scheduling_policy);
   fgets(filler, 100, fp);

   fscanf(fp,"%d",&preemption_policy);
   fgets(filler, 100, fp);

   fscanf(fp,"%d",&time_quantum);
   fgets(filler, 100, fp);

   fscanf(fp,"%d",&number_of_processes);
   fgets(filler, 100, fp);

   for (i=0; i<number_of_processes; i++)
   {
      fscanf(fp,"%d",&(simulation_load[i].process_id));
      fgets(filler, 100, fp);

      fscanf(fp,"%d",&(simulation_load[i].process_length));
      fgets(filler, 100, fp);

      fscanf(fp,"%d",&(simulation_load[i].arrival_time));
      fgets(filler, 100, fp);

      simulation_load[i].process_loaded = 0;
      simulation_load[i].time_remaining = simulation_load[i].process_length;
      simulation_load[i].time_waiting = 0;
      simulation_load[i].completion_time = -1;
      simulation_load[i].response_time = -1;
   }
   fclose(fp);

   if (scheduling_policy == 0)
   {  // FIFO requires Premption to be turned OFF
      preemption_policy = 0;
   }
   if (scheduling_policy == 2)
   {  // RR requires Premption to be turned ON
      preemption_policy = 1;
   }
   if (preemption_policy < 0 || preemption_policy > 1)
   {  // Catch bad input
      preemption_policy = 1;
   }
   master_clock = 0;
   processes_left = 0;
   switches = 0;
}

int main()
{
   load_task_simulation_data();  // Get input
   new_process();                // Load at least 1+ processes onto work queue
   while (processes_left)
   {
      load_process();    // Move work from work queue to cpu
      run_process();     // run ONE process (add wait times to processes in work_queue)
      new_process();     // Load any "newly" arriving processes
      preempt_process(); // Kick CPU process back to work queue (or remove if complete)
   }
   print_report();  // Output stats at end of run
}