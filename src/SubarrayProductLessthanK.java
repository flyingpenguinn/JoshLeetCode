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
        int low = 0;
        int high = -1;
        int prod = 1;
        int res = 0;
        while (true) {
            if (low == high + 1 || prod < k) {
                // trap here: when low>high prod could be still >=k. here we need to move low anyway
                res += high - low + 1;
                // conunting subarray ending at high here
                high++;
                if (high == a.length) {
                    break;
                }
                prod *= a[high];
            } else {
                prod /= a[low++];
            }
        }
        return res;
    }
}

class SubarrayProductLessthanKCountStart {
    // this solution counts start. note the tricky handling at high==a.length. so usually for this kind of problem we count high
    public int numSubarrayProductLessThanK(int[] a, int k) {
        int low = 0;
        int high = -1;
        int prod = 1;
        long res = 0;
        while (true) {
            if (low == high + 1 || prod < k) {
                // this is a rare problem that even empty set ==1 and won't qualify for cases when k==0. so when we meet empty set we need to move high not low even if prod >=k
                high++;
                if (high == a.length) {
                    // low...high-1 good. any substring of it is good. we are not moving low so just count everything
                    int len = high - low;
                    res += 1L * len * (len + 1) / 2;
                    break;
                } else {
                    prod *= a[high];
                }
            } else {
                // low..high-1 good. add the result for this low
                res += high - low;
                prod /= a[low];
                low++;
            }
        }
        return (int) res;
    }
}