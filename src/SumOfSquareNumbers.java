import java.util.HashSet;
import java.util.Set;

/*
LC#633
Given a non-negative integer c, your task is to decide whether there're two integers a and b such that a2 + b2 = c.

Example 1:

Input: 5
Output: True
Explanation: 1 * 1 + 2 * 2 = 5


Example 2:

Input: 3
Output: False
 */
public class SumOfSquareNumbers {
    public boolean judgeSquareSum(int c) {
        for (long i = 0; i * i <= c; i++) {
            long other = c - i * i;
            if (issq(other)) {
                return true;
            }
        }
        return false;
    }

    boolean issq(long n) {
        long sqrt = (int) Math.sqrt(n);
        return sqrt * sqrt == n;
    }
}
