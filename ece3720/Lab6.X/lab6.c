/* Christopher Brant
 * C19816588
 * Lab 6: Peripheral Pin Select
 * 10/12/2017
 */

#include <plib.h>

// Declare global variable for count
int count = 0;

// Function to set the LEDs in the cases of cw or ccw
void setLEDs(int cw)
{
    if (cw == 1)
    {
        if (count + 1 > 15)
        {
            count = 0;
            LATB = count;
        }
        else
            LATB= ++count;
    }
    else
    {
        if (count - 1 < 0)
        {
            count = 15;
            LATB = count;
        }
        else
            LATB = --count;
    }
}

int main(void)
{
    // Set important registers and enable necessary functions
    TRISB = 0x80;
    TRISC = 0x10;
    INTEnableSystemMultiVectoredInt();
    
    // Set peripheral pin macro for INT1
    PPSInput(4, INT1, RPC4);
    
    // Setting interrupt control register bits
    INTCONbits.INT0EP = 1 ^ PORTBbits.RB7;
    INTCONbits.INT1EP = 1 ^ PORTCbits.RC4;
    
    // Setting the interrupt enable register bits
    IEC0bits.INT0IE = 1;
    IEC0bits.INT1IE = 1;
    
    // Setting the interrupt priority control bits
    IPC0bits.INT0IP = 0x1;
    IPC1bits.INT1IP = 0x1;
            
    // Setting the interrupt flag status register bits
    IFS0bits.INT0IF = 0;
    IFS0bits.INT1IF = 0;

    // Run forever and wait for interrupts
    while(1);
    
    return 0;
}

void __ISR(3) OutputA(void)
{
    // Declare cw variable
    int cw = 0;
    
    // Check to see what direction encoder was turned
    // Checks for cw options, otherwise returns 0 for ccw
    if (INTCONbits.INT0EP == 1)
    {
        if (PORTBbits.RB7 == 1 && PORTCbits.RC4 == 0)
            cw = 1;
    }
    else if (INTCONbits.INT0EP == 0)
    {
        if (PORTBbits.RB7 == 0 && PORTCbits.RC4 == 1)
            cw = 1;
    }
        
    // Set LEDs to new count value
    setLEDs(cw);

    // Flip the edge trigger bit for Output A
    INTCONbits.INT0EP ^= 1;
    
    // Turn off interrupt flag
    IFS0bits.INT0IF = 0;
}

void __ISR(7) OutputB(void)
{
    // Declare cw variable
    int cw = 0;
    
    // Check to see what direction encoder was turned
    // Checks for cw options, otherwise returns 0 for ccw
    if (INTCONbits.INT1EP == 1)
    {
        if (PORTBbits.RB7 == 1 && PORTCbits.RC4 == 1)
            cw = 1;
    }
    else if (INTCONbits.INT1EP == 0)
    {
        if (PORTBbits.RB7 == 0 && PORTCbits.RC4 == 0)
            cw = 1;
    }
        
    // Set LEDs to new count value
    setLEDs(cw);
    
    // Flip the edge trigger bit for Output A
    INTCONbits.INT1EP ^= 1;
    
    // Turn off interrupt flag
    IFS0bits.INT1IF = 0;
}
