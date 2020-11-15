public class MaximizeGridHappiness {
    // boundary, luo kuo xian dp! similar to tiling problem. we only care about the last n bricks starting from the upper one
    private Integer[][][][][] dp;

    public int getMaxGridHappiness(int m, int n, int ic, int ec) {
        dp = new Integer[m * n][ic + 1][ec + 1][1 << n][1 << n];
        return domax(0, m, n, ic, ec, 0, 0);
    }

    private int domax(int i, int m, int n, int ic, int ec, int ist, int est) {
        if (i == m * n) {
            return 0;
        }
        if (dp[i][ic][ec][ist][est] != null) {
            return dp[i][ic][ec][ist][est];
        }
        int r = i / n;
        int c = i % n;
        int nist = ((ist << 1) & ((1 << n) - 1)); // clear off upper bits
        int nest = ((est << 1) & ((1 << n) - 1));
        int rt = domax(i + 1, m, n, ic, ec, nist, nest); // put nothing here
        int upic = r == 0 ? 0 : ((ist >> (n - 1)) & 1);
        int leftic = c == 0 ? 0 : (ist & 1);
        int upec = r == 0 ? 0 : ((est >> (n - 1)) & 1);
        int leftec = c == 0 ? 0 : (est & 1);
        int delta = -(upic + leftic) * 30 + (upec + leftec) * 20;
        int allneb = upic + upec + leftic + leftec;
        if (ic > 0) {
            rt = Math.max(rt, delta + 120 - allneb * 30 + domax(i + 1, m, n, ic - 1, ec, nist | 1, nest));
        }
        if (ec > 0) {
            rt = Math.max(rt, delta + 40 + allneb * 20 + domax(i + 1, m, n, ic, ec - 1, nist, nest | 1));
        }
        dp[i][ic][ec][ist][est] = rt;
        return rt;
    }
}
