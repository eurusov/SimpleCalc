/**
 * Test task to study Java programming language.
 * This is the Simple calculator with ability to
 * perform operations on Arabic and Roman numerals.
 *
 * @author Eugene Urussoff
 * @version 1.0
 */

import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        /**
         *  Configure parameters here
         */
        // Lower and upper limit for operands
        SimpleExpression.minValue = 1;
        SimpleExpression.maxValue = 10;
        
        // Limit Result to the above min max values or not
        SimpleExpression.limitResult = false;
        // Allow or disallow mixing Arabic and Roman numerals in one expression
        SimpleExpression.isMixingAllowed = false;
        
        // If true Integer division with a non-zero remainder is allowed
        SimpleExpression.remainderAllowed = true;
        /**
         */
        
        System.out.print("\nPrimitive Calculator for Arabic and RomanNumeral numbers.\n" +
                "Type the expression to calculate and press Enter\nor press Ctrl-C (Ctrl-D in IDEA) to exit:\n");
        
        var scanner = new Scanner(System.in);
        String input;
        while (scanner.hasNext()) {
            input = scanner.nextLine();
            if (!input.isBlank()) {
                var expr = new SimpleExpression(input);
                System.out.println(expr);
            }
        }
        scanner.close();

//        try {
//            Tests.testRoman();
//            Tests.testExpression();
//        } catch (IOException e) {
//            System.exit(-1);
//        }
    }
}
