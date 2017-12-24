/* Christopher Brant
 * C19816588
 * Lab 8: Pulse Width Modulators
 * 11/2/2017
 */

#include <plib.h>

#define fullDutyCycle 10
#define dCycleInc 0.25
int dCycleMult = 0;
int dCycleDirect = 1;

void __ISR(3) ButtonPress(void)
{
    if (dCycleDirect == 1)
    {
        if (dCycleMult == 4)
        {
            dCycleMult = 0;
            dCycleDirect = -1;
            OC1RS = fullDutyCycle * (1 - (dCycleInc * dCycleMult));
            OC2RS = fullDutyCycle;
        }
        else
        {
            dCycleMult++;
            OC1RS = fullDutyCycle;
            OC2RS = fullDutyCycle * (1 - (dCycleInc * dCycleMult));
        }
    }
    else if (dCycleDirect == -1)
    {
        if (dCycleMult == 4)
        {
            dCycleMult = 0;
            dCycleDirect = 1;
            OC2RS = fullDutyCycle * (1 - (dCycleInc * dCycleMult));
            OC1RS = fullDutyCycle;
        }
        else
        {
            dCycleMult++;
            OC2RS = fullDutyCycle;
            OC1RS = fullDutyCycle * (1 - (dCycleInc * dCycleMult));
        }
    }
    
    IFS0bits.INT0IF = 0;
}

int main(void)
{
    // Setting Tristate register bits
    TRISB = 0x0000;
    TRISBbits.TRISB7 = 1;
    TRISC = 0x00;
    // Setting interrupt function
    INTEnableSystemMultiVectoredInt();
    INTCONbits.INT0EP = 0;
    IEC0bits.INT0IE = 1;
    IPC0bits.INT0IP = 0x1;
    IFS0bits.INT0IF = 0;
    // Setting the Output Peripheral Pin Selects
    PPSOutput(1,RPB3,OC1);
    PPSOutput(2,RPB8,OC2);
    // Setting Timer 2 Prescaler bits
    T2CONbits.TCKPS = 0x0;
    // Setting Timer 2 clock source select bit
    T2CONbits.TCS = 0;
    // Setting Timer 2 gated time accumulation enable bit
    T2CONbits.TGATE = 0;
    // Setting Timer 2 Period and OCxRS registers
    PR2 = fullDutyCycle;
    OC1RS = fullDutyCycle * dCycleInc * dCycleMult;
    OC2RS = fullDutyCycle * dCycleInc * dCycleMult;
    //OC2RS = fullDutyCycle * dCycleInc * dCycleMult;
    // Setting Output Compare enable and select bits
    OC1CONbits.ON = 1;
    OC1CONbits.OCM = 7;
    OC2CONbits.ON = 1;
    OC2CONbits.OCM = 7;
    // Turn timer on last
    // Setting Timer 2 enable bit on
    T2CONbits.ON = 1;
    
    // Running while loop
    while(1);
    
    return 0;
}