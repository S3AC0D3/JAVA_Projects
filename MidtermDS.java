/**
 * Title: Midterm Exam
 * Programmer: Werner Ordonez
 * Date: 7/20/2017
 * Details: This program checks for mismatched delimiters using the stack data
 * structure
 */
package midtermds;

import java.util.Scanner;

/**
 *
 * @author wernerordonez
 */
public class MidtermDS {

    /**
     * @param args the command line arguments
     */
    
    //creating a stack data structure
    static class StackW {

        private int size;  //max size
        private char[] input;  //char input array
        private int top;  //the top of the stack
        
        //stack contructer
        public StackW(int s) {
            size = s;
            input = new char[size];
            top = -1;
        }
        
        public void push(char c) {
            input[++top] = c;
        }

        public char pop() {
            return input[top--];
        }

        public char peek() {
            return input[top];
        }

        public boolean isEmpty() {
            return (top == -1);
        }

    }

    public static void main(String[] args) {
        String s;
        char[] cInput;

        StackW theStack;

        Scanner userInput = new Scanner(System.in);  //reading from system.in
        
        while (true) {
            //prompt user for input 
            System.out.print("Enter string containing delimiters: ");
            //retrieve user input and conver the string to a char array
            s = userInput.next();
            cInput = s.toCharArray();
            theStack = new StackW(cInput.length);
            
            //checking if user input was read correctly
            /* for (int i = 0; i < cInput.length; i++) {
                theStack.push(cInput[i]);
                System.out.print(cInput[i]);
                System.out.println();
            }
            */
            
            //error checking for loop
            for (int i = 0; i < cInput.length; i++) {
                char ch = cInput[i];
                //switch case checking for delimiters
                switch (ch) {
                    case '{':
                    case '[':
                    case '(':
                        theStack.push(ch); // if any of the front delimiters
                        break;             // push it to the stack
                    case '}':
                    case ']':
                    case ')':
                        //until its empty
                        if (!theStack.isEmpty()) {
                            char ch2 = theStack.pop(); //pop char into variable
                            
                            //if theres not a front delimiter present but there
                            //is an end delimiter print out error messages
                            if ((ch2 != '{' && ch != '}') || 
                                (ch2 != '[' && ch != ']') || 
                                (ch2 != '(' && ch != ')')) 
                            {
                                System.out.println("Error: " + ch + " at " + i);
                            }
                        } else {
                            System.out.println("Error: " + ch + " at " + i);
                        }
                        break;
                    default:
                        break;
                }
            }
            //if there is no right delimiter found print out error 
            if (!theStack.isEmpty()) {
                System.out.println("Error: missing right delimiter");
            }

        }
    }

}
