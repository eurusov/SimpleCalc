/**
 * Test task to study Java programming language.
 * This is the Simple calculator with ability to
 * perform operations on Arabic and Roman numerals.
 *
 * @author Eugene Urussoff
 * @version 1.0
 */

import java.util.Collections;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RomanNumeral {
    private int value;
    
    RomanNumeral() {
        value = 1;
    }
    
    RomanNumeral(int value) {
        setValue(value);
    }
    
    RomanNumeral(String str) {
        setValue(str);
    }
    
    void setValue(int value) throws NumberFormatException {
        if (value < 1 || value > 3999) {
            throw new NumberFormatException("Roman numeral out of range : [1, 3999]");
        }
        this.value = value;
    }
    
    void setValue(String string) throws NumberFormatException {
        Pattern romanPattern = Pattern.compile("^(?=[MDCLXVI])M*(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
        Matcher romanMatcher = romanPattern.matcher(string);
        if (!romanMatcher.matches()) {
            throw new NumberFormatException("Unrecognized Roman numeral: " + string);
        }
        
        int result = 0;
        int strPos = 0;
        int baseIdx = ROMAN_BASE.lastKey();
        while (strPos < string.length()) {
            String roman_unit = ROMAN_BASE.get(baseIdx);
            if (string.startsWith(roman_unit, strPos)) {
                strPos += roman_unit.length();
                result += baseIdx;
            } else {
                var lowerKey = ROMAN_BASE.lowerKey(baseIdx);
                if (lowerKey == null) {
                    throw new NumberFormatException("Invalid Roman numeral format: " + string);
                }
                baseIdx = lowerKey;
            }
        }
        value = result;
    }
    
    int getValue() {
        return value;
    }
    
    public String toString() {
        int val = value;
        var result = new StringBuilder();
        for (Integer key : ROMAN_BASE.descendingKeySet()) {
            while (val >= key) {
                result.append(ROMAN_BASE.get(key));
                val -= key;
            }
        }
        return result.toString();
    }
    
    private static final NavigableMap<Integer, String> ROMAN_BASE;
    
    static {
        var initMap = new TreeMap<Integer, String>();
        initMap.put(1, "I");
        initMap.put(4, "IV");
        initMap.put(5, "V");
        initMap.put(9, "IX");
        initMap.put(10, "X");
        initMap.put(40, "XL");
        initMap.put(50, "L");
        initMap.put(90, "XC");
        initMap.put(100, "C");
        initMap.put(400, "CD");
        initMap.put(500, "D");
        initMap.put(900, "CM");
        initMap.put(1000, "M");
        ROMAN_BASE = Collections.unmodifiableNavigableMap(initMap);
    }
}
