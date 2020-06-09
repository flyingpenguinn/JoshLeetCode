import java.util.HashMap;
import java.util.Map;

/*
LC#560
Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:
Input:nums = [1,1,1], k = 2
Output: 2
Note:
The length of the array is in range [1, 20,000].
The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
 */
public class SubarraySumEqualsK {
    public int subarraySum(int[] a, int k) {
        int n = a.length;
        Map<Integer, Integer> m = new HashMap<>();
        m.put(0, 1);
        int sum = 0;
        int r = 0;
        for (int i = 0; i < n; i++) {
            sum += a[i];
            int cur = m.getOrDefault(sum - k, 0);
            r += cur;
            m.put(sum, m.getOrDefault(sum, 0) + 1);
        }
        return r;
    }
}

