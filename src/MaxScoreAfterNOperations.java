import java.util.ArrayList;
import java.util.List;

public class MaxScoreAfterNOperations {
    // subset enumerations
    private Integer[][] dp;
    private List<Integer>[] subs;
    private List<Integer>[] twos;

    public int maxScore(int[] a) {
        int n = a.length;
        dp = new Integer[1 << n][n + 1];
        subs = new ArrayList[1 << n];
        twos = new ArrayList[1 << n];
        for (int i = 0; i < (1 << n); i++) {
            subs[i] = new ArrayList<>();
            twos[i] = new ArrayList<>();
        }
        for (int i = 0; i < (1 << n); i++) {
            if (Integer.bitCount(i) == 2) {
                for (int j = 0; j < n; j++) {
                    if (((i >> j) & 1) == 1) {
                        twos[i].add(a[j]);
                    }
                }
            }
        }
        for (int st = 0; st < (1 << n); st++) {
            for (int nst = st - 1; nst >= 0; ) {
                int selected = st - nst;
                if (!twos[selected].isEmpty()) {
                    subs[st].add(nst);
                }

                if (nst == 0) {
                    break;
                } else {
                    nst = (nst - 1) & st;
                }
            }
        }
        return solve(a, (1 << n) - 1, 1);
    }

    private int solve(int[] a, int st, int i) {

        int n = a.length;
        if (st == 0) {
            return 0;
        }
        if (dp[st][i] != null) {
            return dp[st][i];
        }
        int res = 0;
        for (int nst : subs[st]) {
            int selected = st - nst;
            List<Integer> nums = twos[selected];
            int cur = i * gcd(nums.get(0), nums.get(1)) + solve(a, nst, i + 1);
            res = Math.max(res, cur);
        }
        dp[st][i] = res;
        return res;
    }

    private int gcd(int a, int b) {
        if (a < b) {
            return gcd(b, a);
        }
        return b == 0 ? a : gcd(b, a % b);

    }
}
