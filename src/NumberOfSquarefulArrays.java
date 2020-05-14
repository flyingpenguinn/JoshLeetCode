import java.util.*;

/*
LC#996
Given an array A of non-negative integers, the array is squareful if for every pair of adjacent elements, their sum is a perfect square.

Return the number of permutations of A that are squareful.  Two permutations A1 and A2 differ if and only if there is some index i such that A1[i] != A2[i].



Example 1:

Input: [1,17,8]
Output: 2
Explanation:
[1,8,17] and [17,8,1] are the valid permutations.
Example 2:

Input: [2,2,2]
Output: 1


Note:

1 <= A.length <= 12
0 <= A[i] <= 1e9
 */
public class NumberOfSquarefulArrays {
    //use each a[i] as head, how many ways to get this form of array, then add them up. avoid duplicates
    // if we connect an edge between numbers that add up to squares, this is actually hamilton path problem...
    int[][] dp;

    public int numSquarefulPerms(int[] a) {
        Arrays.sort(a);
        int n = a.length;
        dp = new int[n + 1][1 << n];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        int r = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0 || a[i] != a[i - 1]) {
                r += don(a, i, 1 << i);
            }
        }
        return r;
    }

    // excluding numbers in st, how many perms starts with a[i]
    private int don(int[] a, int i, int st) {
        int n = a.length;
        if (st == (1 << n) - 1) {
            // all good before this
            return 1;
        }
        if (dp[i][st] != -1) {
            return dp[i][st];
        }
        int r = 0;
        Set<Integer> seen = new HashSet<>();
        for (int j = 0; j < n; j++) {
            if (((st >> j) & 1) == 0 && !seen.contains(a[j])) {
                // can use a[j]
                seen.add(a[j]);
                if (issquare(a[i], a[j])) {
                    int nst = (st | (1 << j));
                    r += don(a, j, nst);
                }
            }
        }
        dp[i][st] = r;
        return r;
    }

    private boolean issquare(int i, int j) {
        double sum = i + j;
        double sqrt = Math.floor(Math.sqrt(sum));
        double diff = sum - sqrt * sqrt;
        return diff < 0.000001;
    }
}
