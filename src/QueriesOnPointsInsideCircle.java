public class QueriesOnPointsInsideCircle {
    public int[] countPoints(int[][] ps, int[][] qs) {
        int[] res = new int[qs.length];
        for (int i = 0; i < qs.length; i++) {
            int cur = 0;
            for (int j = 0; j < ps.length; j++) {
                if (dist(ps[j][0], ps[j][1], qs[i][0], qs[i][1]) <= qs[i][2] * qs[i][2]) {
                    cur++;
                }
            }
            res[i] = cur;
        }
        return res;
    }

    private int dist(int x, int y, int a, int b) {
        int d1 = x - a;
        int d2 = y - b;
        return d1 * d1 + d2 * d2;
    }
}
