/*
	Jennifer Starcher and Christopher Brant
	ECE 3220
	25 April 2018

	Compile: gcc -Wall os_project3.c -o os_project3 -pthread
	Run: ./os_project3
*/

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
#include <time.h>

// Structure containing the iteration and sleep of each resource.
struct Input
{
	int Carbon, Hydrogen, Oxygen; 	// Per iteration
	int Cms, Hms, Oms; 		// Sleep time [ms]
	int Ethanol, Water, Ozone; 	// Total needed
	int Ems, Wms, OZms; 		// Sleep time [ms]
} Input;

// Structure containing resource count.
struct Resources
{
	int C, H, O; 			// Current resources
	int C2H6O, H2O, O3; 		// Current combinations
	int Ct, Ht, Ot; 		// Total produced
} Resources;

// Thread type declarations.
pthread_t Cp, Hp, Op, Ec, Wc, OZc;
pthread_mutex_t CLock, HLock, OLock, ConsumerLock;
pthread_cond_t Ccv, Hcv, Ocv;

// Variables to stop the producers from producing and keep up with execution time.
int Quit;

// Function to load in input file.
void load_data() 
{
	char filler[100];

	// Open File
	FILE *fp = fopen("molecule.txt","r");

	// Carbon
	fscanf(fp,"%d",&Input.Carbon);
	fgets(filler, 100, fp);

	if(Input.Carbon < 1)
		Input.Carbon = 1;
	else if(Input.Carbon > 2)
		Input.Carbon = 2;

	fscanf(fp,"%d",&Input.Cms);
	fgets(filler, 100, fp);

	// Hydrogen
	fscanf(fp,"%d",&Input.Hydrogen);
	fgets(filler, 100, fp);

	if(Input.Hydrogen < 1)
		Input.Hydrogen = 1;
	else if(Input.Hydrogen > 4)
		Input.Hydrogen = 4;

	fscanf(fp,"%d",&Input.Hms);
	fgets(filler, 100, fp);

	// Oxygen
	fscanf(fp,"%d",&Input.Oxygen);
	fgets(filler, 100, fp);

	if(Input.Oxygen < 1)
		Input.Oxygen = 1;
	else if(Input.Oxygen > 2)
		Input.Oxygen = 2;

	fscanf(fp,"%d",&Input.Oms);
	fgets(filler, 100, fp);

	// Ethanol
	fscanf(fp,"%d",&Input.Ethanol);
	fgets(filler, 100, fp);

	fscanf(fp,"%d",&Input.Ems);
	fgets(filler, 100, fp);

	// Water
	fscanf(fp,"%d",&Input.Water);
	fgets(filler, 100, fp);

	fscanf(fp,"%d",&Input.Wms);
	fgets(filler, 100, fp);

	// Ozone
	fscanf(fp,"%d",&Input.Ozone);
	fgets(filler, 100, fp);

	fscanf(fp,"%d",&Input.OZms);
	fgets(filler, 100, fp);

	// Close File
	fclose(fp);

	// Clear Resources
	Resources.C = 0;
	Resources.H = 0;
	Resources.O = 0;
	Resources.C2H6O = 0;
	Resources.H2O = 0;
	Resources.O3 = 0;

	// Start Producers
	Quit = 0;
}

// Function controlling the carbon producer.
void *Carbon_p (void *args)
{
	int n;
	while(Quit != 3)
	{
		pthread_mutex_lock(&CLock);
		n = (rand() % Input.Carbon) + 1;
		Resources.C += n;
		Resources.Ct += n;
		printf("Carbon\t\t producer runs - produces %d\tC=%d\n", n, Resources.C);
		pthread_cond_signal(&Ccv);
		pthread_mutex_unlock(&CLock);
		usleep(Input.Cms);
	}
	pthread_exit(NULL);
}

// Function controlling the hydrogen producer.
void *Hydrogen_p (void *args)
{
	int n;
	while(Quit != 3)
	{
		pthread_mutex_lock(&HLock);
		n = (rand() % Input.Hydrogen) + 1;
		Resources.H += n;
		Resources.Ht += n;
		printf("Hydrogen\t producer runs - produces %d\tH=%d\n", n, Resources.H);
		pthread_cond_signal(&Hcv);
		pthread_mutex_unlock(&HLock);
		usleep(Input.Hms);
	}
	pthread_exit(NULL);
}

// Function controlling the oxygen producer.
void *Oxygen_p (void *args)
{
	int n;
	while(Quit != 3)
	{
		pthread_mutex_lock(&OLock);
		n = (rand() % Input.Oxygen) + 1;
		Resources.O += n;
		Resources.Ot += n;
		printf("Oxygen\t\t producer runs - produces %d\tO=%d\n", n, Resources.O);
		pthread_cond_signal(&Ocv);
		pthread_mutex_unlock(&OLock);
		usleep(Input.Oms);
	}
	pthread_exit(NULL);
}

// Function controlling the ethanol producer.
void *Ethanol_c (void *args)
{
	int n = 0;
	while(n < Input.Ethanol)
	{
		pthread_mutex_lock(&ConsumerLock);

		pthread_mutex_lock(&CLock);
		while(Resources.C < 2)
		{
			pthread_cond_wait(&Ccv, &CLock);
		}
		pthread_mutex_unlock(&CLock);

		pthread_mutex_lock(&HLock);
		while(Resources.H < 6)
		{
			pthread_cond_wait(&Hcv, &HLock);
		}
		pthread_mutex_unlock(&HLock);

		pthread_mutex_lock(&OLock);
		while(Resources.O < 1)
		{
			pthread_cond_wait(&Ocv, &OLock);
		}
		pthread_mutex_unlock(&OLock);

		Resources.C -= 2;
		Resources.H -= 6;
		--Resources.O;
		++Resources.C2H6O;
		++n;

		printf("Ethanol\t\tconsumer runs - consumes C2H6O: C=%d, H=%d, O=%d\n", Resources.C, Resources.H, Resources.O);
		pthread_mutex_unlock(&ConsumerLock);
		usleep(Input.Ems);
	}
	++Quit;
	pthread_exit(NULL);
}

// Function controlling the water producer.
void *Water_c (void *args)
{
	int n = 0;
	while(n < Input.Water)
	{
		pthread_mutex_lock(&ConsumerLock);

		pthread_mutex_lock(&HLock);
		while(Resources.H < 2)
		{
			pthread_cond_wait(&Hcv, &HLock);
		}
		pthread_mutex_unlock(&HLock);

		pthread_mutex_lock(&OLock);
		while(Resources.O < 1)
		{
			pthread_cond_wait(&Ocv, &OLock);
		}
		pthread_mutex_unlock(&OLock);

		Resources.H -= 2;
		--Resources.O;
		++Resources.H2O;
		++n;

		printf("Water\t\tconsumer runs - consumes H2O:\tH=%d, O=%d\n", Resources.H, Resources.O);
		pthread_mutex_unlock(&ConsumerLock);
		usleep(Input.Wms);
	}
	++Quit;
	pthread_exit(NULL);
}

// Function controlling the ozone producer.
void *Ozone_c (void *args)
{
	int n = 0;
	while(n < Input.Ozone)
	{
		pthread_mutex_lock(&ConsumerLock);

		pthread_mutex_lock(&OLock);
		while(Resources.O < 3)
		{
			pthread_cond_wait(&Ocv, &OLock);
		}
		pthread_mutex_unlock(&OLock);

		Resources.O -= 3;
		++Resources.O3;
		++n;

		printf("Ozone\t\tconsumer runs - consumes O3:\tO=%d\n", Resources.O);
		pthread_mutex_unlock(&ConsumerLock);
		usleep(Input.OZms);
	}
	++Quit;
	pthread_exit(NULL);
}

int main()
{
	// Start Time
	clock_t Begin = clock();

	// Load Input File
	load_data();

	// Locks
	pthread_mutex_init(&CLock, NULL);	
	pthread_mutex_init(&HLock, NULL);
	pthread_mutex_init(&OLock, NULL);
	pthread_mutex_init(&ConsumerLock, NULL);

	// Producers
	pthread_create(&Cp, NULL, &Carbon_p, NULL);
	pthread_create(&Hp, NULL, &Hydrogen_p, NULL);
	pthread_create(&Op, NULL, &Oxygen_p, NULL);

	// Consumers
	pthread_create(&Ec, NULL, &Ethanol_c, NULL);
	pthread_create(&Wc, NULL, &Water_c, NULL);
	pthread_create(&OZc, NULL, &Ozone_c, NULL);

	// Wait for consumers
	pthread_join(Ec, NULL);	
	pthread_join(Wc, NULL);
	pthread_join(OZc, NULL);
	
	// Wait for producers
	pthread_join(Cp, NULL);	
	pthread_join(Hp, NULL);
	pthread_join(Op, NULL);

	// End Time
	clock_t End = clock();
	
	// Result Output
	printf("\n");
	printf("Carbon Atoms Produced = %d\n", Resources.Ct);
	printf("Hydrogen Atoms Produced = %d\n", Resources.Ht);
	printf("Oxygen Atoms Produced = %d\n", Resources.Ot);
	printf("%d Ethanol Molecules Produced\n", Resources.C2H6O);
	printf("%d Water Molecules Produced\n", Resources.H2O);
	printf("%d Ozone Molecules Produced\n", Resources.O3);
	printf("\n");
	printf("Time to Finish Execution: %0.0fuSec\n", ((double)(End - Begin)) / CLOCKS_PER_SEC * 1000000.0);
	

	return 0;
}
