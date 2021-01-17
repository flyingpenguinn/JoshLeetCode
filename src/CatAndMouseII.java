import java.util.Arrays;

public class CatAndMouseII {
    // game ends sooner than 1000 turns...about 100 turns is enough
    // because we have turns, there is no circle in the dependency so we can dp
    private int m;
    private int n;
    private int fp;
    private boolean[] bad;
    private int turnlimit = 100;

    private int[][][] dp;

    public boolean canMouseWin(String[] g, int cj, int mj) {
        this.m = g.length;
        this.n = g[0].length();
        int cp = -1;
        int mp = -1;
        this.bad = new boolean[m * n];
        dp = new int[m * n][m * n][turnlimit + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = g[i].charAt(j);
                if (c == 'C') {
                    cp = code(i, j);
                } else if (c == 'M') {
                    mp = code(i, j);
                } else if (c == 'F') {
                    this.fp = code(i, j);
                } else if (c == '#') {
                    bad[code(i, j)] = true;
                }
            }
        }
        return doc(mp, cp, 0, mj, cj) == 0; // return 0 if mouse wins
    }

    private int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    // mover = 0 mouse, 1 cat
    private int doc(int pm, int po, int turn, int jm, int jo) {
        int mover = turn % 2;
        int other = mover ^ 1;
        if (po == fp) {
            return other;
        }
        if (pm == po) {
            return 1;
        }
        if (turn > turnlimit) {
            return 1;
        }
        int p0 = mover == 0 ? pm : po;
        int p1 = mover == 0 ? po : pm;
        if (dp[p0][p1][turn] != -1) {
            return dp[p0][p1][turn];
        }
        int mr = pm / n;
        int mc = pm % n;
        for (int[] d : dirs) {
            for (int j = 0; j <= jm; j++) {
                int nr = mr + d[0] * j;
                int nc = mc + d[1] * j;
                int npm = code(nr, nc);
                if (nr < 0 || nr >= m || nc < 0 || nc >= n || bad[npm]) {
                    break;
                }
                int later = doc(po, npm, turn + 1, jo, jm);
                if (later == mover) {
                    dp[p0][p1][turn] = mover;
                    return mover;
                }
            }
        }
        dp[p0][p1][turn] = other;
        return other;
    }

    private int code(int i, int j) {
        return i * n + j;
    }
}
