/* Christopher Brant
 * C19816588
 * Lab 5: Interrupts
 * 10/5/2017
 */

#include <plib.h>

delay()
{
    int i, j;
    for(i = 0; i < 500; i++)
        for(j = 0; j < 500; j++);
}

int main(void)
{
    // Set important registers and enable necessary functions
    TRISB = 0x80;
    INTEnableSystemMultiVectoredInt();
    
    // Setting interrupt control register
    INTCONbits.INT0EP = 1;
    
    // Setting the interrupt enable register
    IEC0bits.INT0IE = 1;
    
    // Setting the interrupt priority control bits
    IPC0bits.INT0IP = 0x1;
            
    // Setting the interrupt flag status register
    IFS0bits.INT0IF = 0;
    
    // Declare and initialize count variable
    int count = 0;
    
    while(1)
    {
        LATB = count; //Output count to LEDs
        count++; 
        
        if(count > 15)//Restrict count to 0-15, needing only 4 bits
            count = 0;
        
        delay();
    }
    
    return 0;
}

void __ISR(3) toggled(void)
{
    // Turn on LEDs
    LATB = 15;
    
    delay();
    
    // Turn off interrupt flag
    IFS0bits.INT0IF = 0;
}