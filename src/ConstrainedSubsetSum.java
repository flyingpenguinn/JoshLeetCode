import java.util.ArrayDeque;
import java.util.Deque;

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
        int[] dp = new int[n + 1]; // note dp here means we CHOOSE i
        dp[n - 1] = a[n - 1];
        int max = Integer.MIN_VALUE;
        Deque<Integer> dq = new ArrayDeque<>();
        dq.offerLast(n - 1);
        for (int i = n - 2; i >= 0; i--) {
            while (dq.peekLast() - i > k) {
                dq.pollLast();
            }
            int cur = a[i] + dp[dq.peekLast()];
            dp[i] = Math.max(cur, a[i]); // note we can choose not to select anything later
            while (!dq.isEmpty() && dp[dq.peekFirst()] <= dp[i]) {
                dq.pollFirst();
            }
            dq.offerFirst(i);
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}

class ConstrainedSubsetSumDirectDp {
    // TLE, but the dp idea is correct
    int MIN = -10000000;

    public int constrainedSubsetSum(int[] a, int k) {
        int n = a.length;
        int[] dp = new int[n + 1];
        dp[n - 1] = a[n - 1];
        int max = Integer.MIN_VALUE;
        for (int i = n - 2; i >= 0; i--) {
            int cur = a[i];
            for (int j = i + 1; j < n && j - i <= k; j++) {
                cur = Math.max(cur, a[i] + dp[j]);
            }
            dp[i] = cur;
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
