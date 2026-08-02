import java.util.Arrays;

public class MinPossibleMaxWaitingTime {
    private static int[][][] dp1;
    private static int[][][] sel1;
    private static int[][][][][] dp2;
    private int Max = (int) 1e9;

    public int minMaxWaitingTime(int[] a, int[] fuel) {
        int n = a.length;
        if (dp1 == null) {
            dp1 = new int[50][51][51];
            sel1 = new int[50][51][51];
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j <= 50; ++j) {
                Arrays.fill(dp1[i][j], -1);
                Arrays.fill(sel1[i][j], -1);
            }
        }
        int served = solve(a, 0, fuel[0], fuel[1]);
        if (dp2 == null) {
            dp2 = new int[50][51][51][21][2];
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j <= 50; ++j) {
                for (int k = 0; k <= 50; ++k) {
                    for (int p = 0; p <= 20; ++p) {
                        Arrays.fill(dp2[i][j][k][p], -1);
                    }
                }
            }
        }
        int rt = solve2(a, served, 0, fuel[0], fuel[1], 0, 0);
        return rt >= Max ? -1 : rt;
    }

    private int solve2(int[] a, int limit, int i, int f0, int f1, int other, int pre) {
        int n = a.length;
        if (i == limit) {
            return -1;
        }
        if (dp2[i][f0][f1][other][pre] != -1) {
            return dp2[i][f0][f1][other][pre];
        }
        int w0 = 0;
        int w1 = 0;
        if (pre == 0) {
            w0 = i == 0 ? 0 : a[i - 1];
            w1 = other;
        } else {
            w1 = i == 0 ? 0 : a[i - 1];
            w0 = other;
        }
        int res = 0;
        if (sel1[i][f0][f1] == 2) {
            int way0 = -1;
            if (a[i] <= f0) {
                way0 = Math.max(w0, solve2(a, limit, i + 1, f0 - a[i], f1, Math.max(0, w1 - w0), 0));
            }

            int way1 = -1;
            if (a[i] <= f1) {
                way1 = Math.max(w1, solve2(a, limit, i + 1, f0, f1 - a[i], Math.max(0, w0 - w1), 1));
            }

            if (way1 != -1 && way0 == -1) {
                res = way1;
            } else if (way1 != -1 && way1 < way0) {
                res = way1;
            } else {
                res = way0;
            }
            if (res == -1) {
                res = Max;
            }
        } else if (sel1[i][f0][f1] == 0) {
            res = Math.max(w0, solve2(a, limit, i + 1, f0 - a[i], f1, Math.max(0, w1 - w0), 0));
        } else {
            res = Math.max(w1, solve2(a, limit, i + 1, f0, f1 - a[i], Math.max(0, w0 - w1), 1));
        }
        dp2[i][f0][f1][other][pre] = res;
        return res;
    }

    private int solve(int[] a, int i, int f0, int f1) {
        int n = a.length;
        if (i == n) {
            return 0;
        }
        if (dp1[i][f0][f1] != -1) {
            return dp1[i][f0][f1];
        }
        int way1 = 0;
        if (a[i] <= f0) {
            way1 = 1 + solve(a, i + 1, f0 - a[i], f1);
        }
        int way2 = 0;
        if (a[i] <= f1) {
            way2 = 1 + solve(a, i + 1, f0, f1 - a[i]);
        }
        if (way1 > way2) {
            sel1[i][f0][f1] = 0;
        } else if (way2 > way1) {
            sel1[i][f0][f1] = 1;
        } else {
            sel1[i][f0][f1] = 2;
        }
        int res = Math.max(way1, way2);
        dp1[i][f0][f1] = res;
        return res;
    }
}
