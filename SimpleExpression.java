/**
 * Test task to study Java programming language.
 * This is the Simple calculator with ability to
 * perform operations on Arabic and Roman numerals.
 *
 * @author Eugene Urussoff
 * @version 1.0
 */

public class SimpleExpression {
    
    // Lower and upper limit for operands
    static int minValue = 1;
    static int maxValue = 1000000;
    
    // Limit Result to the above min max values or not
    static boolean limitResult = false;
    
    // Allow or disallow mixing Arabic and Roman numerals in one expression
    static boolean isMixingAllowed = true;
    
    // If true Integer division with a non-zero remainder is allowed
    static boolean remainderAllowed = true;
    
    
    private int operandLeft;
    private int operandRight;
    
    // Must be '+' '-' '*' or '/'
    private char operator;
    
    // True - if detect that the first operand is Roman numeral
    private boolean isRoman;
    
    // True - if detect  that one operand is a Roman, and the other Arabic
    private boolean isMixing;
    
    private int result;
    
    SimpleExpression(String input) throws NumberFormatException {
        input = input.strip();
        
        // Split the input string by delimiters (+ - * \)
        // and retain delimiters together with the result
        //
        String[] fields = input.split("((?<=[+\\-*/])|(?=[+\\-*/]))");
        
        if (fields.length != 3) {
            throw new NumberFormatException("Invalid input");
        }
        
        fields[0] = fields[0].strip();
        operator = fields[1].strip().charAt(0);
        fields[2] = fields[2].strip();
        
        var romanConverter = new RomanNumeral();
        boolean isRightRoman;
        
        // Parsing left operand
        try {
            // Try int
            operandLeft = Integer.parseInt(fields[0]);
            isRoman = false;
        } catch (NumberFormatException e) {
            // In case not int - try RomanNumeral
            // Do not throw an exception here, because we do it in romanConverter
            //
            romanConverter.setValue(fields[0]);
            operandLeft = romanConverter.getValue();
            isRoman = true;
        }
        
        // Parsing right operand
        try {
            // Try int
            operandRight = Integer.parseInt(fields[2]);
            isRightRoman = false;
        } catch (NumberFormatException e) {
            // In case not int - try RomanNumeral
            // Do not throw an exception here, because we do it in romanConverter
            //
            romanConverter.setValue(fields[2]);
            operandRight = romanConverter.getValue();
            isRightRoman = true;
        }
        isMixing = isRoman != isRightRoman;
        // In case mixing detected while it's not allowed
        if (isMixing && !isMixingAllowed) {
            throw new NumberFormatException("Mixing Arabic and Roman numerals is not allowed");
        }
        // Checking operands are in range
        if (operandLeft < minValue || operandLeft > maxValue
                || operandRight < minValue || operandRight > maxValue) {
            throw new NumberFormatException("Out of range error : [" + minValue + ", " + maxValue + "]");
        }
        calculate();
    }
    
    int getInt() {
        return result;
    }
    
    boolean isRoman() {
        return isRoman;
    }
    
    boolean isMixing() {
        return isMixing;
    }
    
    void calculate() throws NumberFormatException {
        switch (operator) {
            case '+':
                result = operandLeft + operandRight;
                break;
            case '-':
                result = operandLeft - operandRight;
                break;
            case '*':
                result = operandLeft * operandRight;
                break;
            case '/':
                if (!remainderAllowed && operandLeft % operandRight != 0) {
                    throw new NumberFormatException("Integer division with a non-zero remainder is not allowed");
                }
                result = operandLeft / operandRight;
                break;
        }
    }
    
    RomanNumeral toRomanNumeral() {
        return new RomanNumeral(result);
    }
    
    public String toString() {
        if (isRoman) {
            return new RomanNumeral(result).toString();
        } else {
            return String.valueOf(result);
        }
    }
}
