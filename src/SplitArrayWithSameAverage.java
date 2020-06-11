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
    // worst case 15*30*150000. but usually we won't hit all the 15 possible lens
    // pruning: we only deal with the smaller set
    // using a ap here: we wont hit all the 150000 values
    Map<Integer, Integer>[][] dp;

    public boolean splitArraySameAverage(int[] a) {
        int sum = 0;
        int n = a.length;
        for (int i = 0; i < n; i++) {
            sum += a[i];
        }

        for (int nb = 1; nb <= n / 2; nb++) {
            if ((sum * nb) % n == 0) {
                int sumb = sum * nb / n;
                if (sumb <= sum / 2) {
                    dp = new HashMap[n + 1][nb + 1];
                    for (int i = 0; i < dp.length; i++) {
                        for (int j = 0; j < dp[i].length; j++) {
                            dp[i][j] = new HashMap<>();
                        }
                    }
                    int rt = dos(0, 0, 0, nb, sumb, a);
                    if (rt == 1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    int dos(int i, int sumb, int nb, int nbtarget, int sumtarget, int[] a) {
        int n = a.length;
        // pruning: we only eye on b being the smallest set. so its nb and sumb must be < the half otherwise its avg will be  either too big or too small
        if (nb > nbtarget) {
            return 2;
        }
        if (sumb > sumtarget) {
            return 2;
        }
        if (i == n) {
            return nb == nbtarget && sumb == sumtarget ? 1 : 2;
        }
        Map<Integer, Integer> cm = dp[i][nb];
        Integer ch = cm.get(sumb);
        if (ch != null) {
            return ch;
        }
        int tob = dos(i + 1, sumb + a[i], nb + 1, nbtarget, sumtarget, a);
        if (tob == 1) {
            dp[i][nb].put(sumb, tob);
            return tob;
        }
        int toc = dos(i + 1, sumb, nb, nbtarget, sumtarget, a);
        dp[i][nb].put(sumb, toc);
        return toc;
    }
}

class SplitArraySameAverageRawDp {
    // raw dp, worst case same complexity as above but not pruning enough
    // the pruning on smaller set is critical
    int[][][] dp;

    public boolean splitArraySameAverage(int[] a) {
        int sum = 0;
        int n = a.length;
        for (int i = 0; i < n; i++) {
            sum += a[i];
        }
        dp = new int[n + 1][sum / 2 + 1][n / 2 + 1];
        return dos(0, 0, 0, 0, 0, a, sum) == 1;
    }

    int dos(int i, int sumb, int nb, int sumc, int nc, int[] a, int sum) {
        int n = a.length;
        if (nb > n / 2) {
            return 2;
        }
        if (sumb > sum / 2) {
            return 2;
        }
        if (i == n) {
            boolean good = nb != 0 && nc != 0 && Math.abs(sumb * 1.0 / nb - sumc * 1.0 / nc) < 0.00001;
            return good ? 1 : 2;
        }
        if (dp[i][sumb][nb] != 0) {
            return dp[i][sumb][nb];
        }
        int tob = dos(i + 1, sumb + a[i], nb + 1, sumc, nc, a, sum);
        if (tob == 1) {
            dp[i][sumb][nb] = tob;
            return tob;
        }
        int toc = dos(i + 1, sumb, nb, sumc + a[i], nc + 1, a, sum);
        dp[i][sumb][nb] = toc;
        return toc;
    }
}