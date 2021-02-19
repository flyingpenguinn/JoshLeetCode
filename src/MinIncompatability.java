import java.util.Arrays;

public class MinIncompatability {
    // dp[size] = dp[sth]+dp[size-sth]  when both size and sth can divide t
    // in the worst case we need to iterate through c16/8 = 12870 items and calc their subset. that's roughly 3^12 so we were able to AC
    // we could have used similar approach as "partition to k subset sum" but here i fear status can't really represent what we have chosen in this cycle though it passes AC...
    private Integer[] dp;
    private int Max = 1000000;

    public int minimumIncompatibility(int[] a, int k) {
        int n = a.length;
        if (k == n) {
            return 0;
        }

        if (n % k != 0) {
            return -1;
        }
        dp = new Integer[(1 << n)];
        int size = n / k;
        int[] diff = new int[(1 << n)];
        Arrays.fill(diff, Max);
        for (int i = 1; i < (1 << n); i++) {
            int csize = Integer.bitCount(i);
            if (csize % size == 0) {
                int max = 0;
                int min = Max;
                boolean[] seen = new boolean[17];
                boolean bad = false;
                for (int j = 0; j < n; j++) {
                    if (((i >> j) & 1) == 1) {
                        if (seen[a[j]]) {
                            bad = true;
                            break;
                        }
                        seen[a[j]] = true;
                        max = Math.max(max, a[j]);
                        min = Math.min(min, a[j]);
                    }
                }
                if (!bad) {
                    diff[i] = max - min;
                }
            }
        }
        int rt = dfs((1 << n) - 1, size, diff);
        return rt >= Max ? -1 : rt;
    }

    private int dfs(int i, int size, int[] diff) {
        int isize = Integer.bitCount(i);
        if (isize == size) {
            return diff[i];
        }
        if (dp[i] != null) {
            return dp[i];
        }
        int min = Max;
        for (int j = i - 1; j > 0; j = (j - 1) & i) {
            int jsize = Integer.bitCount(j);
            if (jsize % size == 0 && diff[j] < Max) {
                int later = dfs(i - j, size, diff);
                if (later < Max) {
                    int cur = diff[j] + later;
                    min = Math.min(min, cur);
                }
            }
        }
        dp[i] = min;
        return min;
    }
}
