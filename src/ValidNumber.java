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

    // pass limit==-1 to split
    // no inner space
    // allow starting 0s for non zero numbers
    // no fraction in exponent
    public boolean isNumber(String s) {
        return checke(s);
    }

    boolean checke(String s) {
        s = s.trim();
        if (s.isEmpty()) {
            return false;
        }


        String[] sp = s.split("e", -1);
        if (sp.length > 2) {
            return false;
        }
        if (sp.length == 1) {
            return checksignreal(s);
        } else {
            if (sp[0].isEmpty() || sp[1].isEmpty()) {
                // no e2 or 2e
                return false;
            }
            return checksignreal(sp[0]) && checksignint(sp[1]);
        }
    }

    private boolean checksignreal(String s) {
        if (s.startsWith("+") || s.startsWith("-")) {
            return s.length() > 1 && checkunsreal(s.substring(1));
        } else {
            return checkunsreal(s);
        }
    }

    private boolean checkunsreal(String s) {
        String[] sp = s.split("\\.", -1);
        if (sp.length > 2) {
            return false;
        }
        if (sp.length == 2) {
            if (sp[0].isEmpty() && sp[1].isEmpty()) {
                return false; // no "." only
            }
            // but allow 2. or .2
            return checkunsint(sp[0]) && checkunsint(sp[1]); // no 0.-2
        } else {
            return checkunsint(sp[0]);
        }
    }


    private boolean checksignint(String s) {
        if (s.startsWith("+") || s.startsWith("-")) {
            return s.length() > 1 && checkunsint(s.substring(1));
            // no single "+"
        } else {
            return checkunsint(s);
        }
    }

    private boolean checkunsint(String s) {

        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }
        return true;// allow empty
    }
}
