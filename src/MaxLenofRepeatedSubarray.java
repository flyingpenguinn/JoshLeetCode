import java.util.*;

/*
LC#718
Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.

Example 1:

Input:
A: [1,2,3,2,1]
B: [3,2,1,4,7]
Output: 3
Explanation:
The repeated subarray with maximum length is [3, 2, 1].


Note:

1 <= len(A), len(B) <= 1000
0 <= A[i], B[i] < 100
 */
public class MaxLenofRepeatedSubarray {

    // longest common substring problem. note the shape diff from lcs problem
    // dp ij means the longest substring starting at i and j
    public int findLength(int[] a, int[] b) {
        int na = a.length;
        int nb = b.length;
        int[][] dp = new int[na + 1][nb + 1];
        int max = 0;
        for (int i = na - 1; i >= 0; i--) {
            for (int j = nb - 1; j >= 0; j--) {
                if (a[i] == b[j]) {
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                } else {
                    dp[i][j] = 0;
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }
}

class MaxlenOfRepeatedSubarrayRollingHash {
    // Onlogn in average
    long Mod = 1000000007L;
    long Base = 101;

    public int findLength(int[] a, int[] b) {
        int na = a.length;
        int nb = b.length;
        int maxlen = Math.min(na, nb);
        int l = 1;
        int u = maxlen;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (isgoodlen(a, b, mid)) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return u;
    }

    protected boolean isgoodlen(int[] a, int[] b, int k) {
        Map<Long, Set<Integer>> sa = new HashMap<>();
        long sum = 0;
        int na = a.length;
        int nb = b.length;
        long baseminus = 1;
        for (int i = 0; i < na; i++) {
            sum = sum * Base + a[i];
            sum %= Mod;
            int head = i - k + 1;
            if (head >= 0) {
                sa.computeIfAbsent(sum, key -> new HashSet<>()).add(i);
                sum = (sum - baseminus * a[head]) % Mod;
                if (sum < 0) {
                    sum += Mod;
                }
            } else {
                baseminus *= Base;
                baseminus %= Mod;
            }
        }

        sum = 0;
        baseminus = 1;
        for (int i = 0; i < nb; i++) {
            sum = sum * Base + b[i];
            sum %= Mod;
            int head = i - k + 1;
            if (head >= 0) {
                if (sa.containsKey(sum)) {
                    for (int ai : sa.get(sum)) {
                        // same hashcode, verify if they are really equal
                        if (same(a, b, ai, i, k)) {
                            return true;
                        }
                    }
                }
                sum = (sum - baseminus * b[head]) % Mod;
                if (sum < 0) {
                    sum += Mod;
                }
            } else {
                baseminus *= Base;
                baseminus %= Mod;
            }
        }
        return false;
    }

    private boolean same(int[] a, int[] b, int i, int j, int len) {
        while (len > 0) {
            if (a[i - len + 1] != b[j - len + 1]) {
                return false;
            }
            len--;
        }
        return true;
    }
}
