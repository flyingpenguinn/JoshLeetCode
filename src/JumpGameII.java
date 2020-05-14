import java.util.Arrays;

/*
LC#45

Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

Example:

Input: [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2.
    Jump 1 step from index 0 to 1, then 3 steps to the last index.
Note:

You can assume that you can always reach the last index.
 */
public class JumpGameII {
    // think of it as a bfs problem for min unweighted path. curend represent the end of current layer.
    public int jump(int[] a) {
        int curend = 0;
        int nextend = 0;
        int n = a.length;
        int jumps = 0;
        for (int i = 0; i < n - 1; i++) {
            // i can be reached in jumps steps
            nextend = Math.max(nextend, i + a[i]);
            if (i == curend) {
                // System.out.println("curend => "+curend);
                jumps++;
                curend = nextend;
                nextend = 0;
            }
        }
        return jumps;
    }

}

// accepted but shamefully
class JumpGameIIDp {
    int Max = 1000000;

    public int jump(int[] a) {
        int n = a.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Max);
        dp[n - 1] = 0;
        for (int i = n - 2; i >= 0; i--) {
            if (i + 1 < n - 1 && a[i] == a[i + 1] + 1) {
                dp[i] = dp[i + 1];
            } else {
                for (int j = i + 1; j <= i + a[i] && j < n; j++) {
                    dp[i] = Math.min(dp[j] + 1, dp[i]);
                }
            }
        }
        return dp[0];
    }
}