import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxGoodSubtreeScore {
    private List<Integer>[] t;
    private int bits = 10;
    private long IMP = (long) -1e17;

    public int goodSubtreeSum(int[] a, int[] pa) {
        int n = a.length;
        t = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            t[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; ++i) {
            if (pa[i] != -1) {
                t[pa[i]].add(i);
            }
        }
        dp = new long[n][1 << bits];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dp[i], IMP);
        }
        solve(0, a);
        long rt = dfssum(0);
        return (int) rt;
    }

    private long mod = (long) (1e9 + 7);

    private long dfssum(int i) {
        long res = 0;
        for (int st = 0; st < (1 << bits); ++st) {
            res = Math.max(res, dp[i][st]);
        }
        for (int ne : t[i]) {
            res += dfssum(ne);
            res %= mod;
        }
        return res;
    }

    private long[][] dp;

    private List<Integer> getdigs(int num) {
        List<Integer> res = new ArrayList<>();
        while (num > 0) {
            int dig = num % 10;
            res.add(dig);
            num /= 10;
        }
        return res;
    }

    private void solve(int i, int[] a) {
        dp[i][0] = 0;
        int v = a[i];
        List<Integer> digs = getdigs(v);
        int cst = 0;
        boolean bad = false;
        for (int di : digs) {
            if (((cst >> di) & 1) == 1) {
                bad = true;
                break;
            }
            cst |= (1 << di);
        }
        if (!bad) {
            dp[i][cst] = a[i];
        }
        for (int ne : t[i]) {
            solve(ne, a);
            long[] ndp = mergene(i, ne, a);
            dp[i] = ndp;
        }
    }

    private long[] mergene(int i, int ne, int[] a) {
        int all = (1 << bits) - 1;
        long[] ndp = new long[dp[i].length];
        ndp = Arrays.copyOf(dp[i], dp[i].length);
        for (int cst = 0; cst < all; ++cst) {
            if (dp[i][cst] == IMP) {
                continue;
            }
            int remst = all - cst;
            for (int nst = remst; nst > 0; nst = (nst - 1) & remst) {
                if (dp[ne][nst] == IMP) {
                    continue;
                }
                int addedst = cst + nst;
                ndp[addedst] = Math.max(ndp[addedst], dp[ne][nst] + dp[i][cst]);
            }
        }
        return ndp;
    }
}
