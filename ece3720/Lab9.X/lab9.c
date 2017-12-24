/* Christopher Brant
 * C19816588
 * Lab 9: SPI
 * 11/9/2017
 */

#include <plib.h>

char spiChars[18] = {0,1,2,4,8,16,32,64,128,255,
                     254,253,251,247,239,223,191,127};

int clearData, sendIndex = 0;    

void __ISR(3) ButtonPress(void)
{
    // Decide if the index needs to wrap around
    if (sendIndex >= 17)
        sendIndex = 0;
    else
        sendIndex++;

    // Send the next value to the buffer
    SPI1BUF = spiChars[sendIndex];
    
    IFS0bits.INT0IF = 0;
}

int main(void)
{
    // Setting Tristate register bits
    TRISB = 0x0000;
    TRISBbits.TRISB7 = 1;
    TRISC = 0x0000;
    // Setting interrupt function
    INTEnableSystemMultiVectoredInt();
    INTCONbits.INT0EP = 0;
    IFS0bits.INT0IF = 0;
    // Setting the Peripheral Pin Selects for SDO1 and SS1
    PPSOutput(1,RPC0,SS1);
    PPSOutput(3,RPC1,SDO1);
    // The following steps set up Master Mode Operation
    IEC0CLR = 0x03800000;       // disable all interrupts
    SPI1CON = 0;                // stop and reset SPI1
    clearData = SPI1BUF;        // clear the receive buffer
    SPI1CONbits.ENHBUF = 0;     // clear the enhanced buffer bit
    IFS0CLR = 0X03800000;       // clear any existing event flags
    IPC5CLR = 0x1f000000;       // clear the priority
    SPI1CONbits.MCLKSEL = 0;    // Set the PBCLK for the BRG
    SPI1BRG = 1;             // use Fpb/4 clock frequency
    SPI1STATbits.SPIROV = 0;    // Clear the SPIROV bit
    SPI1CONbits.MSSEN = 1;      // Enable Master slave select control
    SPI1CONbits.SSEN = 1;       // Set slave select on
    SPI1CONbits.DISSDO = 0;     // Enable the SDO1 pin
    SPI1CONbits.MODE32 = 0;     // Set the data width to 8 bits
    SPI1CONbits.MODE16 = 0;     // Set the data width to 8 bits
    //SPI1CONbits.SMP = 1;        // Set data to sample at end of output time
    SPI1CONbits.CKE = 1;        // Output changes on change from active to idle
    SPI1CONbits.CKP = 0;        // Idle state is low, active is high
    SPI1CONbits.MSTEN = 1;      // Set to master mode
    SPI1CON2bits.AUDEN = 0;     // Set audio enable off
    // Turn on SPI1 module last
    SPI1CONbits.ON = 1;
    
    IEC0bits.INT0IE = 1;
    IPC0bits.INT0IP = 0x1;
    
    while(1);

    return 0;
}