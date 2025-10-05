import java.util.Arrays;


public class CountNonzeroPairsSumToN {
    // TODO digit dp with lots of nuances...
    private Long[][][][][][] dp;
    private int[] d;

    public long countNoZeroPairs(long input) {
        String s = String.valueOf(input);
        int n = s.length();
        d = new int[n];
        for (int i = 0; i < n; i++) {
            d[i] = s.charAt(n - 1 - i) - '0';
        }
        dp = new Long[n + 1][2][2][2][2][2];
        return f(0, 0, 0, 0, 0, 0, n);
    }

    private long f(int i, int c, int ta, int tb, int pa, int pb, int n) {
        if (i == n) {
            return (c == 0 && pa == 1 && pb == 1) ? 1L : 0L;
        }
        if (dp[i][c][ta][tb][pa][pb] != null) {
            return dp[i][c][ta][tb][pa][pb];
        }
        long res = 0L;
        int nd = d[i];
        for (int da = 0; da <= 9; da++) {
            if (ta == 1 && da != 0) {
                continue;
            }
            int db = nd - c - da;
            db %= 10;
            if (db < 0) {
                db += 10;
            }
            if (tb == 1 && db != 0) {
                continue;
            }
            int na = (ta == 1) ? 1 : ((da == 0) ? 1 : 0);
            int nb = (tb == 1) ? 1 : ((db == 0) ? 1 : 0);
            int npa = (pa == 1) ? 1 : ((da > 0) ? 1 : 0);
            int npb = (pb == 1) ? 1 : ((db > 0) ? 1 : 0);
            int nc = (da + db + c) / 10;
            res += f(i + 1, nc, na, nb, npa, npb, n);
        }
        dp[i][c][ta][tb][pa][pb] = res;
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new CountNonzeroPairsSumToN().countNoZeroPairs(11));
    }
}
