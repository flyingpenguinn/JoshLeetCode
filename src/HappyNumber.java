import java.util.HashSet;
import java.util.Set;

/*
LC#202
Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

Example:

Input: 19
Output: true
Explanation:
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
 */
public class HappyNumber {
    // similar to linked list cycle detection can be done in O1
    public boolean isHappy(int n) {
        Set<Integer> seen = new HashSet<>();
        seen.add(n);
        while (n != 1) {
            n = makehappy(n);
            if (seen.contains(n)) {
                return false;
            }
            seen.add(n);
        }
        return true;
    }

    int makehappy(int n) {
        int r = 0;
        while (n != 0) {
            int d = n % 10;
            r += d * d;
            n /= 10;
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(new HappyNumber().isHappy(18));
    }
}
