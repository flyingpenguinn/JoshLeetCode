import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
LC#964
Given a single positive integer x, we will write an expression of the form x (op1) x (op2) x (op3) x ... where each operator op1, op2, etc. is either addition, subtraction, multiplication, or division (+, -, *, or /).  For example, with x = 3, we might write 3 * 3 / 3 + 3 - 3 which is a value of 3.

When writing such an expression, we adhere to the following conventions:

The division operator (/) returns rational numbers.
There are no parentheses placed anywhere.
We use the usual order of operations: multiplication and division happens before addition and subtraction.
It's not allowed to use the unary negation operator (-).  For example, "x - x" is a valid expression as it only uses subtraction, but "-x + x" is not because it uses negation.
We would like to write an expression with the least number of operators such that the expression equals the given target.  Return the least number of operators used.



Example 1:

Input: x = 3, target = 19
Output: 5
Explanation: 3 * 3 + 3 * 3 + 3 / 3.  The expression contains 5 operations.
Example 2:

Input: x = 5, target = 501
Output: 8
Explanation: 5 * 5 * 5 * 5 - 5 * 5 * 5 + 5 / 5.  The expression contains 8 operations.
Example 3:

Input: x = 100, target = 100000000
Output: 3
Explanation: 100 * 100 * 100 * 100.  The expression contains 3 operations.


Note:

2 <= x <= 100
1 <= target <= 2 * 10^8
 */
public class LeastNumberOfOperator {
    // multiple till it's >=t, then take /x to get the closest number. we recurse on the diff
    // note the processing when x>t
    // similar to "race car", but here we dont need to emunrate the "go back time". we either overhsoot or undershoot
    // only going back once. if we need to go back multiple times, later dol calls will take care of that
    // @todo: math proof that this will work? need proof on prod >= 2*t    part
    Map<Long, Integer> dp = new HashMap<>();

    public int leastOpsExpressTarget(int x, int t) {

        return dol(x, t);
    }

    int dol(long x, long t) {
        if (x >= t) {
            long way1 = 2 * (x - t); // 5-5/5-5/5 ==3, 2*diff
            long way2 = 2 * t - 1; // 5/5+5/5+5/5 == 3
            return (int) Math.min(way1, way2);
        } else {
            if (dp.containsKey(t)) {
                return dp.get(t);
            }
            int steps = 0;
            long prod = x;
            while (prod < t) {
                prod *= x;
                steps++;
            }
            if (x == t) {
                return steps;
            }
            int min = prod >= 2 * t ? Max : (steps + 1 + dol(x, prod - t));
            // must prune like this otherwise we get an infinite loop
            // cost of going back from 2t to t must be bigger than going from a smaller number than t
            prod /= x;
            steps--;
            int cur = steps + 1 + dol(x, t - prod);
            min = Math.min(min, cur);

            dp.put(t, min);
            return min;
        }
    }

    int Max = 1000000;

    public static void main(String[] args) {

        System.out.println(new LeastNumberOfOperator().leastOpsExpressTarget(3, 19));
        System.out.println(new LeastNumberOfOperator().leastOpsExpressTarget(5, 501));
    }
}
