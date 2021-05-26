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
    // note in this problem when low>high, prod could still >=k so we need to add that special check
    // this solution counts subarrays ENDING at high
    public int numSubarrayProductLessThanK(int[] a, int k) {
        if (k == 0) {
            return 0;
        }
        int n = a.length;
        int start = 0;
        int prod = 1;
        int res = 0;
        for (int i = 0; i < n; i++) {
            prod *= a[i];
            while (start <= i && prod >= k) {
                // must have start <=i otherwise we move start too far away
                prod /= a[start];
                start++;
            }
            // start...i <k
            res += i - start + 1;
        }
        return res;
    }
}