import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
LC#805
In a given integer array A, we must move every element of A to either list B or list C. (B and C initially start empty.)

Return true if and only if after such a move, it is possible that the average value of B is equal to the average value of C, and B and C are both non-empty.

Example :
Input:
[1,2,3,4,5,6,7,8]
Output: true
Explanation: We can split the array into [1,4,5,8] and [2,3,6,7], and both of them have the average of 4.5.
Note:

The length of A will be in the range [1, 30].
A[i] will be in the range of [0, 10000].
 */
public class SplitArrayWithSameAverage {
    // sum/n==sumb/bn. cant prune based on n only, have to use int mod since sum*bn/n==sumb ab int
    // alternative way: first find possible bns, then dfs on that
    int sum = 0;
    int maxbn = 0;
    Map<Integer, Integer>[][] dp;

    public boolean splitArraySameAverage(int[] a) {
        int n = a.length;

        for (int ai : a) {
            sum += ai;
        }
        //1. aim for the smaller one- as there must be one group that has len<=n/2. we try to find that one only
        //2. exit early if not possible
        for (int i = 1; i <= n / 2; i++) {
            if (sum * i % n == 0) {
                maxbn = i;
            }
        }
        if (maxbn == 0) {
            return false;
        }

        dp = new HashMap[n + 1][n + 1];
        return dos(a, 0, 0, 0);
    }

    boolean dos(int[] a, int i, int bn, int bsum) {
        int n = a.length;
        if (bn != 0 && bn != n && bn * sum == n * bsum) {
            return true;
        }
        if (i == n) {
            return false;
        }
        if (bn > maxbn) {
            // we iterate the smaller one and finish early if no hope
            return false;
        }
        if (dp[i][bn] == null) {
            dp[i][bn] = new HashMap<>();
        }
        Integer ch = dp[i][bn].get(bsum);
        if (ch != null) {
            return ch.equals(1);
        }
        boolean rt = dos(a, i + 1, bn + 1, bsum + a[i]) || dos(a, i + 1, bn, bsum);
        Integer v = rt ? 1 : 2;
        dp[i][bn].put(bsum, v);
        return rt;
    }


    public static void main(String[] args) {
        int[] a = {1, 2};
        System.out.println(new SplitArrayWithSameAverageEnumeratebn().splitArraySameAverage(a));
    }
}

class SplitArrayWithSameAverageEnumeratebn {
    // alternative way: first find possible bns, then dfs on that
    int sum = 0;
    Map<Integer, Boolean>[][] dp;

    public boolean splitArraySameAverage(int[] a) {
        int n = a.length;

        for (int ai : a) {
            sum += ai;
        }
        //1. aim for the smaller one- as there must be one group that has len<=n/2. we try to find that one only
        //2. exit early if not possible note possibility is reflected in mod
        for (int i = 1; i <= n / 2; i++) {
            if (sum * i % n == 0) {
                dp = new HashMap[n + 1][n + 1];
                if (dfs(a, 0, 0, i, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int[] a, int i, int count, int bn, int bsum) {
        int n = a.length;
        if (count > bn) {
            return false;
        }
        if (i == n) {
            if (count == bn) {
                return bn * sum == n * bsum;
            } else {
                return false;
            }
        }
        if (dp[i][count] == null) {
            dp[i][count] = new HashMap<>();
        }
        Boolean ch = dp[i][count].get(bsum);
        if (ch != null) {
            return ch;
        }
        boolean rt = dfs(a, i + 1, count + 1, bn, bsum + a[i]) || dfs(a, i + 1, count, bn, bsum);
        dp[i][count].put(bsum, rt);
        return rt;
    }
}

