import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

/*
Lc#713
Your are given an array of positive integers nums.

Count and print the number of (contiguous) subarrays where the product of all the elements in the subarray is less than k.

Example 1:
Input: nums = [10, 5, 2, 6], k = 100
Output: 8
Explanation: The 8 subarrays that have product less than 100 are: [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
Note:

0 < nums.length <= 50000.
0 < nums[i] < 1000.
0 <= k < 10^6.
 */
public class SubarrayProductLessthanK {

    public int numSubarrayProductLessThanK(int[] a, int k) {
        int low = 0;
        int high = -1;
        int prod = 1;
        int r = 0;
        while (true) {
            if (low > high || prod < k) {
                // when low...high is good, low..high ,low+..high....high-1..hgih, high...high are all good
                r += high - low + 1;
                high++;
                if (high == a.length) {
                    break;
                }
                prod *= a[high];
            } else {
                prod /= a[low];
                low++;
            }
        }
        return r;
    }

}
