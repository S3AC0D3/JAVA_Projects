/**
 * Title: RPN Calculator
 * Programmer: Werner Ordonez
 * Last Updated: 7/5/17
 * Details: How it works is if you want to do an operator that requires two 
 * operands you must enter both operands separetely first then press the 
 * operator you would like to calculate. As for the square root you just need 
 * one operand entered. EX: if you would like "6-1" press 6, press 1, 
 * press minus
 */
package rpn;

/**
 *
 * @author wernerordonez
 */
public class Rpn {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        calcFrame calc = new calcFrame();
        calc.setVisible(true);
        
    }
    
}
