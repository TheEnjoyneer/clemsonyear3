/* ECE 6380, Summer 2017
 * Harlan Russell
 *
 * Here is an implementation of the receiver's code for the sliding window
 * algorithm.  
 */

#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#define RWS         8   /* receive window size */
#define NUM_SEQ_NO 16   /* max. sequence number+1 */
                        /* (must be multiple of */
                        /* RWS for this code) */
#define FRAME_SIZE 1000 /* constant for simplicity */

char buf[RWS][FRAME_SIZE]; /* RWS frame buffers */
int present[RWS];        /* are frame buffers full? */
                         /* (initialized to 0's) */
int NFE = 0;             /* next frame expected */

/* subroutines to support receiver's sliding window */
void send_ack (int seq_no);
void pass_to_app (int idx, char* data);
void recv_frame (char* data, int seq_no);

/* some messages for testing */
static char *messages[] = {
        "'Jeeves,' I said, 'may I speak frankly?'",
        "'Certainly, sir'",
        "'What I have to say may wound you.'",
        "'Not at all, sir.'",
        "'Well, then -'",
        "No - wait. Hold the line a minute.  I've gone off the rails."};

int main(void)
{
    int i;
    for (i = 0; i < RWS; i++) {
	present[i] = 0;
    }

    /* in this example, we don't get frames 2 and 3 at first
     * but they are retransmitted later.  Frame 4 is a duplicate.
     * So the order is: 0, 1, 4, 5, 2, 3, 4
     */
    for (i = 0; i < 6; i++) {
	if (i != 3 && i != 2) {
	    printf("\nReceive frame %d\n", i);
	    recv_frame(messages[i], i);
	}
    }
    printf("\nReceive frame %d again\n", 2);
    recv_frame(messages[2], 2);

    printf("\nReceive frame %d again\n", 3);
    recv_frame(messages[3], 3);

    printf("\nReceive duplicate frame %d \n", 4);
    recv_frame(messages[4], 4);

    /* at this point receive window is [6: 13] since RWS is 8 */
    printf("\nHacker attempts to insert frame 14. RW is [%d:%d] so rejected!\n", NFE, NFE+RWS-1);
    recv_frame("Hello World!", NFE+RWS);

    exit(0);
}

void
recv_frame (char* data, int seq_no)
{
    int idx;      /* index into data structures */
    int i;        /* loop index */
    /* Map sequence numbers NFE...predecessor (NFE)
       into 0...NUM_SEQ_NO - 1, then see if seq_no
       falls within the receive window. */
    if (((seq_no + (NUM_SEQ_NO - NFE)) % NUM_SEQ_NO)
	    < RWS) { /* Frames outside the window */
	             /* are discarded */
	/* Calculate index into data structures. */
	idx = (seq_no % RWS);
	if (!present[idx]) {   /* frame is not duplicate */
	    present[idx] = 1;  /* mark as received */
	    memcpy (buf[idx], data, strlen(data)+1);
	                       /* copy data into buffer */

	    /* Got a new frame; pass frames up to host? */
	    for (i = 0; i < RWS; i++) {
		idx = (i + NFE) % RWS; /* Re-use idx. */
		/* The first missing frame becomes NFE */
		/* after this loop terminates. */
		if (!present[idx])
		    break;
		/* Frame is present-send it up! */
		pass_to_app (idx, buf[idx]); 
		/* Mark frame buffer as empty. */
		present[idx] = 0;
	    }
	    /* Advance NFE to first missing frame. */
	    NFE = (NFE + i) % NUM_SEQ_NO;
	}
	/* Frame handled (might have been a duplicate). */
    } 
    /* Now send acknowledgement for predecessor frame (NFE). */
    send_ack ((NFE + NUM_SEQ_NO - 1) % NUM_SEQ_NO); 
}

void send_ack (int seq_no)
{
    printf("  --Send ack number %d (the NFE is = %d)\n", seq_no, NFE);
}

void pass_to_app (int idx, char* data)
{
    printf("  --Pass frame %d to app: %s\n", idx, data);
}

