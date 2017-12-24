/* Christopher Brant
 * C19816588
 * Lab 2
 * 9/14/2017
 */

#include <plib.h>

int main(void) 
{
    TRISB = 0x48;
    TRISC = 0xC0;
    
    while(1)
    {
        // Setting DIO inputs to output to their LEDs and D-Latch
        LATBbits.LATB1 = PORTCbits.RC6; // LED 0
        LATBbits.LATB2 = PORTCbits.RC7; // LED 1
        
        LATBbits.LATB7 = !(PORTBbits.RB6);
        // Setting Push Button input to write out to D-Latch clock pin
        //if (PORTBbits.RB3 ~= 1)
        //{
            //LATBbits.LATB7 = 1;
        //}
        
        //LATBbits.LATB7 = 0;
    }
    
    return 0;
}
