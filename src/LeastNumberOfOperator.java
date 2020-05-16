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

    Map<Long, Integer> dp = new HashMap<>();

    public int leastOpsExpressTarget(int x, int target) {
        return dol(x, target);

    }

    private int dol(long x, long t) {
        if (t == 0) {
            return 0;
        }
        if (t == 1) {
            return 1;
        }
        if (dp.containsKey(t)) {
            return dp.get(t);
        }
        if (x > t) {
            int way1 = (int) (2 * t - 1);//11=>6, 6*(11/1), 6 divisions, 5 plus, 11 in all, 2*t-1
            int way2 = (int) (2 * (x - t)); //  11=>6, 11-5*(11/1),10 operatons.
            return Math.min(way1, way2);
        }
        long base = x;
        int count = 0;
        while (base < t) {
            base *= x;
            count++;
        }
        if (base == t) {
            dp.put(t, count);
            return count;
        } else {
            int way1 = base - t >= t ? Max : count + 1 + dol(x, base - t); // if >=t no point of doing it
            int way2 = base == 1 ? Max : (count - 1) + 1 + dol(x, t - (base / x));
            int rt = Math.min(way1, way2);
            dp.put(t, rt);
            return rt;
        }
    }

    int Max = 1000000;

    public static void main(String[] args) {
        System.out.println(new LeastNumberOfOperator().leastOpsExpressTarget(11, 6));
        System.out.println(new LeastNumberOfOperator().leastOpsExpressTarget(3, 365));
        System.out.println(new LeastNumberOfOperator().leastOpsExpressTarget(11, 551304));
        System.out.println(new LeastNumberOfOperator().leastOpsExpressTarget(11, 551302));
        System.out.println(new LeastNumberOfOperator().leastOpsExpressTarget(3, 19));
        System.out.println(new LeastNumberOfOperator().leastOpsExpressTarget(10, 1000));
    }
}
