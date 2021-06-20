import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinAbsoluteDiffQuery {
    // we can tell if a number exists in an interval in O(1) time by using prefix sum
    private int Max = 1000000000;

    public int[] minDifference(int[] a, int[][] queries) {
        int n = a.length;
        int[][] dp = new int[n][101];
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= 100; j++) {
                dp[i][j] = (i == 0 ? 0 : dp[i - 1][j]) + (a[i] == j ? 1 : 0);
            }
        }
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] q = queries[i];
            int start = q[0];
            int end = q[1];
            int last = -1;
            int mindiff = Max;
            for (int j = 1; j <= 100; j++) {
                int occ = dp[end][j] - (start == 0 ? 0 : dp[start - 1][j]);
                if (occ >= 1) {
                    if (last != -1) {
                        mindiff = Math.min(mindiff, j - last);
                    }
                    last = j;
                }
            }
            if (mindiff < Max) {
                res[i] = mindiff;
            } else {
                res[i] = -1;
            }
        }
        return res;
    }
}

class MinAbsoluteDiffBinarySearch {
    // or binary search it in a sorted list
    private int Max = 1000000000;

    public int[] minDifference(int[] a, int[][] queries) {
        int n = a.length;
        List<Integer>[] dp = new ArrayList[101];
        for (int i = 0; i < n; i++) {
            if (dp[a[i]] == null) {
                dp[a[i]] = new ArrayList<>();
            }
            dp[a[i]].add(i);
        }
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] q = queries[i];
            int start = q[0];
            int end = q[1];
            int last = -1;
            int mindiff = Max;
            for (int j = 1; j <= 100; j++) {
                if (dp[j] != null) {
                    List<Integer> list = dp[j];
                    int lower = Collections.binarySearch(list, start);
                    if (lower < 0) {
                        lower = -(lower + 1);
                    }
                    //here we use it as if it's cpp's lowerbound
                    if (lower < list.size() && list.get(lower) <= end) {
                        if (last != -1) {
                            mindiff = Math.min(j - last, mindiff);
                        }
                        last = j;
                    }
                }
            }
            if (mindiff < Max) {
                res[i] = mindiff;
            } else {
                res[i] = -1;
            }
        }
        return res;
    }
}

class MaxAbsoluteDiff {
    // if it's max absolute diff, use Tarjan's algo for rmq
    public int[] maxDifference(int[] a, int[][] queries) {
        int n = a.length;
        int[][] dpmin = new int[n + 1][18];
        int[][] dpmax = new int[n + 1][18];
        for (int i = 1; i <= n; i++) {
            dpmin[i][0] = a[i - 1];
            dpmax[i][0] = a[i - 1];
        }
        for (int j = 1; (1 << j) <= n; j++) {
            for (int i = 1; i + (1 << (j - 1)) <= n; i++) {
                dpmin[i][j] = Math.min(dpmin[i][j - 1], dpmin[i + (1 << (j - 1))][j - 1]);
                dpmax[i][j] = Math.max(dpmax[i][j - 1], dpmax[i + (1 << (j - 1))][j - 1]);
            }
        }
        int qn = queries.length;
        int[] res = new int[qn];
        for (int i = 0; i < qn; i++) {
            int[] q = queries[i];
            int start = q[0] + 1;
            int end = q[1] + 1;
            int minv = rmq(start, end, dpmin, true);
            int maxv = rmq(start, end, dpmax, false);
            if (minv == maxv) {
                res[i] = -1;
            } else {
                res[i] = (maxv - minv);
            }
        }
        return res;
    }

    private int rmq(int l, int r, int[][] dp, boolean ismin) {
        int k = 0;
        while ((1 << (k + 1)) <= r - l + 1) {
            k++;
        }
        return ismin ? Math.min(dp[l][k], dp[r - (1 << k) + 1][k]) : Math.max(dp[l][k], dp[r - (1 << k) + 1][k]);
    }
};
