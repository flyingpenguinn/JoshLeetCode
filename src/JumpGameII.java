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
    // think of it as finding smallest number of segment to cover a range problem: similar to min taps to water garden problem
    // it's also a bfs problem where we find the shortest path
    public int jump(int[] a) {
        int n = a.length;
        int start = 0;
        int end = a[0];
        int res = 0;
        for(int i=1; i<n; i++){
            int cstart = i;
            int cend = i+a[i];
            if(cstart<=start){
                // cutting the parts < start that's useless. we treat as if they all start at start, find the biggest end
                end = Math.max(end, cend);
            }else if(cstart>end){
                // bad case
            }else{
                // > start, but <=end. we ignore the parts that is <=end because end will be the new start
                res++;
                start = end;
                end = cend;
            }
        }
        return res;
    }
}

// accepted but shamefully
class JumpGameIIDp {
    private int Max = 10000000;

    public int jump(int[] a) {
        int n = a.length;
        int[] dp = new int[n];
        dp[n - 1] = 0;
        for (int i = n - 2; i >= 0; i--) {
            int cur = Max;
            for (int j = 1; j <= a[i] && i + j < n; j++) {
                cur = Math.min(cur, dp[i + j]);
            }
            dp[i] = 1 + cur;
        }
        return dp[0];
    }
}