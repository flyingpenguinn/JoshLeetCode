import java.util.HashMap;
import java.util.Map;

/*
lC#594
We define a harmounious array as an array where the difference between its maximum value and its minimum value is exactly 1.

Now, given an integer array, you need to find the length of its longest harmonious subsequence among all its possible subsequences.

Example 1:

Input: [1,3,2,2,5,2,3,7]
Output: 5
Explanation: The longest harmonious subsequence is [3,2,2,2,3].


Note: The length of the input array will not exceed 20,000.
 */
public class LongestHarmoniousSubsequence {
    // for each i iterate it being the bigger one. note exactly one, so km1 can't be 0
    public int findLHS(int[] a) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            m.put(a[i], m.getOrDefault(a[i], 0) + 1);
        }
        int max = 0;
        for (int k : m.keySet()) {
            int km1 = m.getOrDefault(k - 1, 0);
            if (km1 != 0) {
                int cur = m.get(k) + km1;
                max = Math.max(max, cur);
            }
        }
        return max;
    }
}
