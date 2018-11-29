/* Christopher Brant
 * C19816588
 * Lab 10: Analog to Digital Conversion
 * 11/16/2017
 */

#include <plib.h>

int ADCsum, LEDvalue;

void __ISR(23) adcDone(void)
{
    ADCsum = 0;
    
    // Sum the values of the 8 most significant bits in each buffer
    ADCsum += (ADC1BUF0 >> 2);
    ADCsum += (ADC1BUF1 >> 2);
    ADCsum += (ADC1BUF2 >> 2);
    ADCsum += (ADC1BUF3 >> 2);
    ADCsum += (ADC1BUF4 >> 2);
    ADCsum += (ADC1BUF5 >> 2);
    ADCsum += (ADC1BUF6 >> 2);
    ADCsum += (ADC1BUF7 >> 2);
    ADCsum += (ADC1BUF8 >> 2);
    ADCsum += (ADC1BUF9 >> 2);
    ADCsum += (ADC1BUFA >> 2);
    ADCsum += (ADC1BUFB >> 2);
    ADCsum += (ADC1BUFC >> 2);
    ADCsum += (ADC1BUFD >> 2);
    ADCsum += (ADC1BUFE >> 2);
    
    // Find the mean of those values
    LEDvalue = ADCsum / 15;
    
    // Write that value to the LEDs
    LATB = LEDvalue;
}

int main(void)
{
    // Setting tristate registers
    TRISA = 0x01;
    TRISB = 0x00;
    
    // Setting analog inputs
    ANSELA = 0x01;
    
    // Setting up ADC interrupts for use
    INTEnableSystemMultiVectoredInt();
    IFS0bits.AD1IF = 0;     // Clears ADC interrupt flag
    IPC5bits.AD1IP = 1;     // Sets ADC interrupt priority to 1
    IEC0bits.AD1IE = 1;     // Enables ADC interrupt
    
    // Setting up the data output format
    AD1CON1bits.FORM = 0;   // Sets to 16-bit integer
    // Setting up the sampling and conversion triggers
    AD1CON1bits.SSRC = 7;   // Sets auto-conversion on
    AD1CON1bits.ASAM = 1;   // Sets auto-sampling on
    // Setting voltage references
    AD1CON2bits.VCFG = 0;   // Sets the voltage references to auto-values
    // Setting channel scan and samples/converts per interrupt
    AD1CON2bits.CSCNA = 0;  // Turns scanning off
    AD1CON2bits.SMPI = 14;  // Interrupts after the 15th sample/convert
    // Setting buffer mode select and input sample select
    AD1CON2bits.BUFM = 0;   // Sets buffer to a single 16-bit word buffer
    AD1CON2bits.ALTS = 0;   // Sets MUX A as input always
    // Setting clock reference and auto-sample time bits
    AD1CON3bits.ADRC = 0;   // Sets clock to the PBCLK
    AD1CON3bits.SAMC = 12;  // Sets auto-sample time to 12 TAD
    // Setting the prescaler for the ADC conversion clock
    AD1CON3bits.ADCS = 0;   // Sets the prescaler to 2*TPB
    // Setting analog channel for reading
    AD1CHSbits.CH0SA = 0;   // Sets AN0 as the input select bits
    
    // Enabling the ADC module last
    AD1CON1bits.ON = 1;     // Turn on the ADC module
    
    while(1);
    
    return 0;
}