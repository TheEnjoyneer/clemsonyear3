/* Christopher Brant
 * Lab 3: Comparator
 * 9/21/2017
 */

#include <plib.h>

int main(void)
{
    // Setting bits 15 and 3 as analog inputs
    ANSELB = 0x8008;
    
    // Setting bits 1 and 0 as inputs to Port A
    TRISA = 0x03;
    
    // Setting bits 15, 3, 2, and 0 as inputs to Port B
    TRISB = 0x800D;
    
    // Setting CVRCON as ON
    CVRCONbits.ON = 1;
    
    // Setting CVRR
    CVRCONbits.CVRR = 0;
    
    // Setting CVRSS
    CVRCONbits.CVRSS = 1;
    
    // Setting lowest 4 bits of CVRCON to set CVR
    CVRCONbits.CVR0 = 1;
    CVRCONbits.CVR1 = 1;
    CVRCONbits.CVR2 = 1;
    CVRCONbits.CVR3 = 1;
    
    // Turning on each comparator
    CM1CONbits.ON = 1;
    CM2CONbits.ON = 1;
    CM3CONbits.ON = 1;
    
    /* Setting IVREF as inverting input
     * and C1INA as the non-inverting input
     */
    CM1CONbits.CCH0 = 1;
    CM1CONbits.CCH1 = 1;
    CM1CONbits.CREF = 0;
    
    /* Setting C2IND as the inverting input
     * and CVREF as the non-inverting input
     */
    CM2CONbits.CCH0 = 0;
    CM2CONbits.CCH1 = 1;
    CM2CONbits.CREF = 1;
    
    /* Setting C3IND as the inverting input
     * and C3INA as the non-inverting input
     */
    CM3CONbits.CCH0 = 0;
    CM3CONbits.CCH1 = 1;
    CM3CONbits.CREF = 0;
    
    while(1)
    {
        // Setting LED0 to output of C1 comparison
        LATBbits.LATB5 = CMSTATbits.C1OUT;
        
        // Setting LED1 to output of C2 comparison
        LATBbits.LATB6 = !(CMSTATbits.C2OUT);
        
        // Setting LED2 to output of C3 comparison
        LATBbits.LATB7 = !(CMSTATbits.C3OUT);
    }
    
    return 0;
}
