import java.util.HashMap;
import java.util.Map;

/*
LC#325
Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.

Note:
The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.

Example 1:

Input: nums = [1, -1, 5, -2, 3], k = 3
Output: 4
Explanation: The subarray [1, -1, 5, -2] sums to 3 and is the longest.
Example 2:

Input: nums = [-2, -1, 2, 1], k = 1
Output: 2
Explanation: The subarray [-1, 2] sums to 1 and is the longest.
Follow Up:
Can you do it in O(n) time?
 */
public class MaxSumSubarraySumEqualsK {
    public int maxSubArrayLen(int[] a, int k) {
        Map<Integer, Integer> m = new HashMap<>();
        m.put(0, -1);
        int max = 0;
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
            if (m.containsKey(sum - k)) {
                int pre = m.get(sum - k);
                max = Math.max(max, i - pre);
            }
            m.putIfAbsent(sum, i);
        }
        return max;
    }
}
