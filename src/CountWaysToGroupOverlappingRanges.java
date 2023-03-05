import java.util.Arrays;

public class CountWaysToGroupOverlappingRanges {
    private long mod = (long) (1e9 + 7);

    public int countWays(int[][] a) {
        int n = a.length;
        Arrays.sort(a, (x, y) -> Integer.compare(x[0], y[0]));
        int start = a[0][0];
        int end = a[0][1];
        int groups = 1;
        for (int i = 1; i < n; ++i) {
            int cstart = a[i][0];
            int cend = a[i][1];
            if (cstart > end) {
                ++groups;
                start = cstart;
                end = cend;
            } else {
                end = Math.max(end, cend);
            }
        }
        return (int) pows2(groups);

    }

    private long pows2(int t) {
        if (t == 0) {
            return 1L;
        }
        long half = pows2(t / 2);
        long res = half * half;
        res %= mod;
        if (t % 2 == 1) {
            res *= 2;
            res %= mod;
        }
        return res;
    }
}
