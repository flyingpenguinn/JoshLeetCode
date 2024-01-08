import java.util.ArrayList;
import java.util.List;

public class CountNumberOfPowerfulIntegers {
    private int DIGS = 17;
    private Long[][] dp;


    public long numberOfPowerfulInt(long start, long finish, int limit, String s) {
        dp = new Long[DIGS][2];
        long v1 = calc(limit, s, finish);
        dp = new Long[DIGS][2];
        long v2 = calc(limit, s, start - 1);
        return v1 - v2;
    }

    private long calc(int limit, String s, long bound) {
        int[] b = new int[DIGS];
        int bi = b.length - 1;
        while (bound > 0) {
            b[bi--] = (int) (bound % 10);
            bound /= 10;
        }
        return solve(0, 0, s, limit, b);
    }

    private long solve(int i, int sm, String s, int limit, int[] b) {
        int sn = s.length();
        int ssi = DIGS - sn;
        if (i == DIGS) {
            return 1;
        }
        if (dp[i][sm] != null) {
            return dp[i][sm];
        }
        long res = 0;
        List<Integer> cand = new ArrayList<>();
        if (i >= ssi) {
            int si = i - ssi;
            int cind = s.charAt(si) - '0';
            cand.add(cind);
        } else {
            for (int j = 0; j <= limit; ++j) {
                cand.add(j);
            }
        }
        for (int j : cand) {
            if (sm == 1) {
                res += solve(i + 1, sm, s, limit, b);
            } else if (j < b[i]) {
                res += solve(i + 1, 1, s, limit, b);
            } else if (j == b[i]) {
                res += solve(i + 1, sm, s, limit, b);
            } else {
                res += 0;
            }

        }
        dp[i][sm] = res;
        return res;
    }
}
