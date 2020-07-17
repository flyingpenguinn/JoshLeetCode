import java.util.HashMap;
import java.util.Map;

/*
LC#166
Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

Example 1:

Input: numerator = 1, denominator = 2
Output: "0.5"
Example 2:

Input: numerator = 2, denominator = 1
Output: "2"
Example 3:

Input: numerator = 2, denominator = 3
Output: "0.(6)"
 */
public class FractionToRecurringDecimal {

    // 1. use long for overflow
    // 2. take care of negative values
    // 3. record the As we've seen and if it reoccurs we konw where to put the (). note it's repeating As, not repeating quotient
    // it's not quotient because 140
    public String fractionToDecimal(long a, long b) {
        if (a == 0) {
            return "0";
        }
        if ((a < 0 && b > 0) || (a > 0 && b < 0)) {
            return "-" + frac(Math.abs(a), Math.abs(b));
        } else {
            return frac(Math.abs(a), Math.abs(b));
        }
    }

    private String frac(long a, long b) {
        StringBuilder sb = new StringBuilder();
        sb.append(a / b);
        a = a % b;
        if (a == 0) {
            return sb.toString();
        }
        sb.append(".");
        Map<Long, Integer> im = new HashMap<>();
        while (a != 0) {
            a *= 10;
            Integer pre = im.get(a);
            if (pre != null) {
                sb.insert(pre.intValue(), '(');
                sb.append(')');
                break;
            } else {
                sb.append(a / b);
                im.put(a, sb.length() - 1);
            }
            a = a % b;
        }
        return sb.toString();
    }
}
