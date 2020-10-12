import java.util.ArrayDeque;
import java.util.Deque;
import java.util.TreeMap;

/*
LC#1425
Given an integer array nums and an integer k, return the maximum sum of a non-empty subset of that array such that for every two consecutive integers in the subset, nums[i] and nums[j], where i < j, the condition j - i <= k is satisfied.

A subset of an array is obtained by deleting some number of elements (can be zero) from the array, leaving the remaining elements in their original order.



Example 1:

Input: nums = [10,2,-10,5,20], k = 2
Output: 37
Explanation: The subset is [10, 2, 5, 20].
Example 2:

Input: nums = [-1,-2,-3], k = 1
Output: -1
Explanation: The subset must be non-empty, so we choose the largest number.
Example 3:

Input: nums = [10,-2,-10,-5,20], k = 2
Output: 23
Explanation: The subset is [10, -2, -5, 20].


Constraints:

1 <= k <= nums.length <= 10^5
-10^4 <= nums[i] <= 10^4
 */
public class ConstrainedSubsetSum {
    // first use dp[i] to indicate the "score" we will get if we MUST PICK i as the first one
    // then use deque to optimize, like we did in sliding window max
    public int constrainedSubsetSum(int[] a, int k) {
        int n = a.length;
        int[] dp = new int[n]; // max profit in picking
        dp[n - 1] = a[n - 1];
        Deque<Integer> dq = new ArrayDeque<>();
        dq.offerLast(dp[n - 1]);
        int max = dp[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            // can opt not to pick
            int maxlater = Math.max(0, dq.peekFirst());
            dp[i] = a[i] + maxlater;
            max = Math.max(max, dp[i]);
            if (dq.size() >= k) {
                dq.pollFirst();
            }
            while (!dq.isEmpty() && dq.peekLast() <= dp[i]) {
                dq.pollLast();
            }
            dq.offer(dp[i]);

        }
        return max;
    }
}

class ConstrainedSubsetSumTreeMap {
    // could use a treemap to help max picking without using sliding window solution
    public int constrainedSubsetSum(int[] a, int k) {
        int n = a.length;
        int[] dp = new int[n]; // max profit in picking
        dp[n - 1] = a[n - 1];
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        update(tm, a[n - 1], 1);
        int max = dp[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            // can opt not to pick
            int maxlater = Math.max(0, tm.lastKey());
            dp[i] = a[i] + maxlater;
            max = Math.max(max, dp[i]);
            update(tm, dp[i], 1);
            if (i + k < n) {
                update(tm, dp[i + k], -1);
            }
        }
        return max;
    }

    private void update(TreeMap<Integer, Integer> tm, int k, int delta) {
        int nv = tm.getOrDefault(k, 0) + delta;
        if (nv <= 0) {
            tm.remove(k);
        } else {
            tm.put(k, nv);
        }
    }
}
