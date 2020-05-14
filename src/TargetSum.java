import java.util.HashMap;
import java.util.Map;

/*
LC#494
You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.

Find out how many ways to assign symbols to make sum of integers equal to target S.

Example 1:
Input: nums is [1, 1, 1, 1, 1], S is 3.
Output: 5
Explanation:

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

There are 5 ways to assign symbols to make the sum of nums be target 3.
Note:
The length of the given array is positive and will not exceed 20.
The sum of elements in the given array will not exceed 1000.
Your output answer is guaranteed to be fitted in a 32-bit integer.
 */
public class TargetSum {
    // for each number either -a or +a and move on to the next
    Map<Integer, Integer>[] dp;

    public int findTargetSumWays(int[] a, int t) {
        dp = new HashMap[a.length];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = new HashMap<>();
        }
        return dof(a, t, 0);
    }

    int dof(int[] a, int t, int i) {
        int n = a.length;
        if (i == n) {
            return t == 0 ? 1 : 0;
        }
        Integer ch = dp[i].get(t);
        if (ch != null) {
            return ch;
        }
        int pos = dof(a, t - a[i], i + 1);
        int neg = dof(a, t + a[i], i + 1);
        int rt = pos + neg;
        dp[i].put(t, rt);
        return rt;
    }
}
