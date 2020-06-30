/*
LC#65
Validate if a given string can be interpreted as a decimal number.

Some examples:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true
" -90e3   " => true
" 1e" => false
"e3" => false
" 6e-1" => true
" 99e2.5 " => false
"53.5e93" => true
" --6 " => false
"-+3" => false
"95a54e53" => false

Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one. However, here is a list of characters that can be in a valid decimal number:

Numbers 0-9
Exponent - "e"
Positive/negative sign - "+"/"-"
Decimal point - "."
Of course, the context of these characters also matters in the input.

Update (2015-02-10):
The signature of the C++ function had been updated. If you still see your function signature accepts a const char * argument, please click the reload button to reset your code definition.
 */
public class ValidNumber {

    // state machine based.
    // trim
    // note the reset of the seennumber state after seeing e
    class Solution {
        public boolean isNumber(String s) {
            if (s == null) {
                return false;
            }
            boolean seenNumber = false;
            boolean seenDot = false;
            boolean seenE = false;
            s = s.trim();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '+' || c == '-') {
                    if (i > 0 && s.charAt(i - 1) != 'e') {
                        // +/- can only be the first or after e
                        return false;
                    }
                } else if (c == '.') {
                    if (seenDot) {
                        //cant be two dots
                        return false;
                    }
                    if (seenE) {
                        // . cant show up after e
                        return false;
                    }
                    // may or may not have number yet. allow ".6"
                    seenDot = true;
                } else if (c == 'e') {
                    if (!seenNumber) {
                        // cant be "e5" or ".e6"
                        return false;
                    }
                    if (seenE) { // can't have 2 "e"s
                        return false;
                    }
                    // may or may not have dot  2e5 and 2.3e5 both fine
                    seenE = true;
                    // must have number after e
                    seenNumber = false;
                } else if (Character.isDigit(c)) {
                    seenNumber = true;
                } else {
                    // invlaid char, return false
                    return false;
                }
            }
            // capture "2e" or empty string
            return seenNumber;

        }
    }
}
