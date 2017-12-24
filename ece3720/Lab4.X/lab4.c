/* Christopher Brant
 * C19816588
 * Lab 4
 * 9/28/2017
 */

#include <plib.h>

// Define invalid key press value
#define INVALID_KEY 0

int main(void)
{
    TRISC = 0xF0;       // Setting outputs and inputs
    
    TRISB = 0x0;        // Setting all bits to outputs
    
    ANSELC = 0x0;      // Setting all pins to digital
    
    // Setting the Pull Register Enable to 1 for bits 4-7
    CNENCbits.CNIEC4 = 1;
    CNENCbits.CNIEC5 = 1;
    CNENCbits.CNIEC6 = 1;
    CNENCbits.CNIEC7 = 1;
    
    // Setting the Pull Up Register to 1 for bits 4-7
    CNPUCbits.CNPUC4 = 1;
    CNPUCbits.CNPUC5 = 1;
    CNPUCbits.CNPUC6 = 1;
    CNPUCbits.CNPUC7 = 1;
    
    // Declaring pressed key and iterator variables
    int i;
    
    // Setting mask values
    unsigned char mask[16] = {0xEE, 0xDE, 0xBE, 0x7E,
                              0xED, 0xDD, 0xBD, 0x7D,
                              0xEB, 0xDB, 0xBB, 0x7B,
                              0xE7, 0xD7, 0xB7, 0x77};
    
    // Setting key values
    unsigned char key[16] = {1, 2, 3, 0xA,
                             4, 5, 6, 0xB,
                             7, 8, 9, 0xC,
                             14, 0, 15, 0xD};
    
    while(1)
    {
        for(i = 0; i < 16; i++)
        {
            // Assert a row
            LATC = mask[i];
            
            // Search for pressed key match
            if ((PORTC & 0xFF) == (mask[i] & 0xFF))
                LATB = key[i]; // Set pressed key value
            
            // Deselect all rows
            LATC = 0xF0;
        }
    }
    
    return 0;
}