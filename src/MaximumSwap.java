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
        // non neg int
        // after swap won't overflow int
        char[] sn = String.valueOf(num).toCharArray();
        int rmax = sn.length-1;
        int cand = -1;
        int candmax = -1;
        for(int i=sn.length-2; i>=0; i--){
            if(sn[i] < sn[rmax]){
                cand = i;
                candmax = rmax;
                // note we must record the max to swap as well. otherwise we could have sth like 9,9,5,8. we swap 5 with 8, not 9
            }else if(sn[i] > sn[rmax]){
                rmax = i;
            }
            // if ==, keep rmax because we want to record the right most biggest
        }
        if(cand==-1){
            return num;
        }else{
            char tmp = sn[cand];
            sn[cand] = sn[candmax];
            sn[candmax] = tmp;
            return Integer.valueOf(new String(sn));
        }
    }

    public static void main(String[] args) {
        System.out.println(new MaximumSwap().maximumSwap(2743));
        System.out.println(new MaximumSwap().maximumSwap(10909091));
    }
}