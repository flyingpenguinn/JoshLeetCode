public class BestReachableTower {
    private int manhatan(int[] v1, int[] v2) {
        return Math.abs(v1[0] - v2[0]) + Math.abs(v1[1] - v2[1]);
    }

    private boolean lexismall(int[] v1, int[] v2) {
        if (v1[0] < v2[0]) {
            return true;
        } else if (v1[0] == v2[0] && v1[1] < v2[1]) {
            return true;
        }
        return false;
    }

    public int[] bestTower(int[][] ts, int[] ct, int radius) {
        int n = ts.length;
        int maxq = -1;
        int[] maxv = {-1, -1};
        for (int i = 0; i < n; ++i) {
            int dist = manhatan(ts[i], ct);
            if (dist <= radius) {
                if (ts[i][2] > maxq) {
                    maxq = ts[i][2];
                    maxv[0] = ts[i][0];
                    maxv[1] = ts[i][1];
                } else if (ts[i][2] == maxq && lexismall(ts[i], maxv)) {
                    maxv[0] = ts[i][0];
                    maxv[1] = ts[i][1];
                }
            }
        }
        return maxv;
    }
}
