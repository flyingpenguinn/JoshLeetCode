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
    // 3. record all rems and when they show up again we know where the loop starts
    public String fractionToDecimal(long numerator, long denominator) {
        if (numerator < 0 && denominator > 0) {
            return "-" + fractionToDecimal(-numerator, denominator);
        }
        if (numerator > 0 && denominator < 0) {
            return "-" + fractionToDecimal(numerator, -denominator);
        }
        Map<Long, Integer> map = new HashMap<>();
        long intpart = numerator / denominator;
        StringBuilder sb = new StringBuilder();
        sb.append(intpart);
        long rem = (numerator % denominator) * 10L;
        if (rem == 0) {
            return sb.toString();
        } else {
            sb.append(".");
            while (rem != 0) {
                int last = map.getOrDefault(rem, -1);
                if (last != -1) {
                    sb.insert(last, '(');
                    sb.append(')');
                    return sb.toString();
                } else {
                    map.put(rem, sb.length());
                    sb.append(rem / denominator);
                }
                rem = (rem % denominator) * 10;
            }
        }
        return sb.toString();
    }
}
