public class CountSteppingNumbersInRange {

    private long Mod = (long) (1e9 + 7);
    private Long[][] dp0;
    private static long[] dp1 = new long[101];
    private Long[][][] dp2;
    private boolean init = false;

    public int countSteppingNumbers(String low, String high) {
        if (!init) {
            for (int len = 1; len <= 100; ++len) {
                dp0 = new Long[len][10];
                long cur = solve1(0, 0, len);
                dp1[len] = cur;
            }
            init = true;
        }
        long res = solveExact(high) - solveExact(low);
        res %= Mod;
        if (res < 0) {
            res += Mod;
        }
        if (good(low)) {
            res += 1;
            res %= Mod;
        }
        return (int) res;
    }

    private long solveExact(String s) {
        int n = s.length();
        long res = 0;
        for (int len = 1; len < n; ++len) {
            res += dp1[len];
            res %= Mod;
        }
        dp2 = new Long[n][10][2];
        res += solve2(s, 0, 0, 0);
        res %= Mod;
        return res;
    }

    private long solve1(int i, int pre, int len) {
        if (i == len) {
            return 1L;
        }
        if (dp0[i][pre] != null) {
            return dp0[i][pre];
        }
        long res = 0;
        if (i == 0) {
            for (int k = 1; k <= 9; ++k) {
                long cur = solve1(i + 1, k, len);
                res += cur;
                res %= Mod;
            }
        } else {
            long way1 = 0;
            if (pre - 1 >= 0) {
                way1 = solve1(i + 1, pre - 1, len);
            }
            long way2 = 0;
            if (pre + 1 <= 9) {
                way2 = solve1(i + 1, pre + 1, len);
            }
            res = way1 + way2;
            res %= Mod;
        }
        dp0[i][pre] = res;
        return res;
    }


    private boolean good(String low) {
        int n = low.length();
        for (int i = 0; i + 1 < n; ++i) {
            int cind = low.charAt(i) - '0';
            int cindn = low.charAt(i + 1) - '0';
            if (Math.abs(cind - cindn) != 1) {
                return false;
            }
        }
        return true;
    }

    private long solve2(String s, int i, int pre, int sm) {
        int n = s.length();
        if (i == n) {
            return 1L;
        }
        if (dp2[i][pre][sm] != null) {
            return dp2[i][pre][sm];
        }
        int cd = s.charAt(i) - '0';
        long res = 0;
        if (i == 0) {
            for (int k = 1; k <= cd; ++k) {
                int nsm = k == cd ? 0 : 1;
                long later = solve2(s, i + 1, k, nsm);
                res += later;
                res %= Mod;
            }
        } else {
            long way1 = 0;
            if (pre - 1 >= 0) {
                int cp = pre - 1;
                way1 = handle(s, i, pre, sm, cd, cp);
            }
            long way2 = 0;
            if (pre + 1 <= 9) {
                int cp = pre + 1;
                way2 = handle(s, i, pre, sm, cd, cp);
            }
            res = way1 + way2;
            res %= Mod;
        }
        dp2[i][pre][sm] = res;
        return res;
    }

    private long handle(String s, int i, int pre, int sm, int cd, int cp) {
        long way1 = 0;
        if (sm == 1) {
            way1 = solve2(s, i + 1, cp, sm);
        } else {
            if (cp > cd) {
                // nothing
            } else if (cp < cd) {
                way1 = solve2(s, i + 1, cp, 1);
            } else {
                way1 = solve2(s, i + 1, cp, sm);
            }
        }
        return way1;
    }
}
