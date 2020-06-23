import java.util.Arrays;
import java.util.PriorityQueue;

/*
LC#670
Given a non-negative integer, you could swap two digits at most once to get the maximum valued number. Return the maximum valued number you could get.

Example 1:
Input: 2736
Output: 7236
Explanation: Swap the number 2 and the number 7.
Example 2:
Input: 9973
Output: 9973
Explanation: No swap.
Note:
The given number is in the range [0, 108]
 */
public class MaximumSwap {

    // from right to left: find the biggest index on the right (if eq, favor right ones: 9299 => 9992
    // from left to right: if that biggest digit != this digit, swap. note we skip nums == biggest
    // dont need a right arrray to do this
    public int maximumSwap(int num) {
        String sn = String.valueOf(num);
        int n = sn.length();
        int right = n - 1;
        int cand = -1;
        int candright = right;
        for (int i = n - 2; i >= 0; i--) {
            if (sn.charAt(right) < sn.charAt(i)) {
                right = i;
            } else if (sn.charAt(right) > sn.charAt(i)) {
                cand = i;
                candright = right;
            }
        }
        if (cand != -1) {
            StringBuilder sb = new StringBuilder(sn);
            char tmp = sb.charAt(cand);
            sb.setCharAt(cand, sb.charAt(candright));
            sb.setCharAt(candright, tmp);
            return Integer.valueOf(sb.toString());

        } else {
            return num;
        }
    }

    public static void main(String[] args) {
        System.out.println(new MaximumSwap().maximumSwap(2743));
        System.out.println(new MaximumSwap().maximumSwap(10909091));
    }
}