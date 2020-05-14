import java.util.HashMap;
import java.util.Map;

/*
LC#1248
Given an array of integers nums and an integer k. A subarray is called nice if there are k odd numbers on it.

Return the number of nice sub-arrays.



Example 1:

Input: nums = [1,1,2,1,1], k = 3
Output: 2
Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].
Example 2:

Input: nums = [2,4,6], k = 1
Output: 0
Explanation: There is no odd numbers in the array.
Example 3:

Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
Output: 16


Constraints:

1 <= nums.length <= 50000
1 <= nums[i] <= 10^5
1 <= k <= nums.length
 */
public class CountNumberOfNiceSubarrays {
    // count how many odds are there in a prefix
    // then for each prefix look for number of target-k in previous prefixes
    public int numberOfSubarrays(int[] a, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int odds = 0;
        int r = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] % 2 == 1) {
                odds++;
            }
            int target = odds - k;
            int count = map.getOrDefault(target, 0);
            r += count;
            if (odds == k) {
                r++;
            }
            map.put(odds, map.getOrDefault(odds, 0) + 1);
        }
        return r;
    }
}
