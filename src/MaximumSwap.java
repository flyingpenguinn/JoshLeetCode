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

    // find the last number that is > this digit, then swap
    public int maximumSwap(int num) {
        String sn = String.valueOf(num);
        int n = sn.length();
        int max = sn.charAt(n - 1);
        int maxi = n - 1;
        int lc = -1;
        int rc = -1;
        for (int i = n - 2; i >= 0; i--) {
            if (sn.charAt(i) > max) {
                maxi = i;
                max = sn.charAt(i);
            } else if (sn.charAt(i) < max) {
                // record the last one that is of this nature
                lc = i;
                rc = maxi;
            }
            // if ==, then =-1
        }
        if (lc != -1) {
            StringBuilder sb = new StringBuilder(sn);
            sb.setCharAt(lc, sn.charAt(rc));
            sb.setCharAt(rc, sn.charAt(lc));
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