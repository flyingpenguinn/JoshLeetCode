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
    public boolean isNumber(String s) {
        s = s.trim();
        boolean seennumber = false;
        boolean seene = false;
        boolean seendot = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                seennumber = true;
            } else if (c == 'e') {
                if (seene) {
                    return false;
                }
                if (!seennumber) {
                    // can't be e9
                    return false;
                }
                seene = true;
                seennumber = false; // reset, because we need to make sure it's not 9e
            } else if (c == '.') {
                if (seene) {
                    return false;
                }
                if (seendot) {
                    return false;
                }
                seendot = true;
            } else if (c == '+' || c == '-') {
                if (i == 0 || s.charAt(i - 1) == 'e') {
                    // only allow -2 or -3e-2
                    continue;
                }
                return false;
            } else {
                return false; // anything else return false
            }
        }
        return seennumber;
    }
}
