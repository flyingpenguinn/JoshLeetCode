import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConcatenatedDvisibility {
    private List<Integer> res = new ArrayList<>();
    private int fulllen = 0;
    private int[] len;
    private Integer[][][] dp;

    public int[] concatenatedDivisibility(int[] a, int k) {
        int n = a.length;
        Integer[] ia = new Integer[n];
        for (int i = 0; i < n; ++i) {
            ia[i] = a[i];
        }
        len = new int[n];
        dp = new Integer[n][(1 << n)][k];
        Arrays.sort(ia, (x, y) -> {
            String sx = String.valueOf(x);
            String sy = String.valueOf(y);
            if (sx.length() < sy.length()) {
                return -1;
            } else if (sx.length() > sy.length()) {
                return 1;
            }
            return sx.compareTo(sy);
        });
        for (int i = 0; i < n; ++i) {
            len[i] = String.valueOf(ia[i]).length();
        }
        for (int ai : a) {
            fulllen += String.valueOf(ai).length();
        }
        initpow10mod(k);
        List<Integer> cur = new ArrayList<>();
        dfs(ia, 0, 0, cur, 0, 0, k);
        int[] rr = new int[res.size()];
        for (int i = 0; i < res.size(); ++i) {
            rr[i] = res.get(i);
        }
        return rr;
    }

    private void dfs(Integer[] a, int i, int st, List<Integer> cur, int clen, int cmod, int k) {
        if (!res.isEmpty()) {
            return;
        }
        int n = a.length;
        if (i == n) {
            if (cmod == 0) {
                res = new ArrayList<>(cur);
            }
            return;
        }
        if (dp[i][st][cmod] != null) {
            return;
        }
        for (int j = 0; j < n; ++j) {
            if (((st >> j) & 1) == 0) {
                cur.add(a[j]);
                int nst = st | (1 << j);
                int seglen = len[j];
                int nclen = seglen + clen;
                int newmod = cmod;
                int restlen = (fulllen - nclen);
                int pow10mod = p10[restlen];
                newmod += ((a[j] % k) * pow10mod) % k;
                newmod %= k;
                dfs(a, i + 1, nst, cur, nclen, newmod, k);
                cur.remove(cur.size() - 1);
            }
        }
        dp[i][st][cmod] = 1;
    }


    private int[] p10 = new int[100];

    private void initpow10mod(int k) {
        p10[0] = 1 % k;
        for (int i = 1; i < 100; ++i) {
            p10[i] = (p10[i - 1] * 10) % k;
        }
    }
}
