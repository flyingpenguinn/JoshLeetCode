import java.util.Arrays;
import java.util.TreeSet;

public class ClosestRoom {
    public int[] closestRoom(int[][] rs, int[][] qs) {
        int n = rs.length;
        int k = qs.length;
        int[][] nq = new int[k][3];
        for (int i = 0; i < k; i++) {
            nq[i][0] = qs[i][0];
            nq[i][1] = qs[i][1];
            nq[i][2] = i;
        }
        int[] res = new int[k];
        Arrays.sort(nq, (x, y) -> Integer.compare(x[1], y[1]));
        Arrays.sort(rs, (x, y) -> Integer.compare(x[1], y[1]));
        int j = n - 1;
        TreeSet<Integer> pool = new TreeSet<>();
        for (int i = k - 1; i >= 0; i--) {
            while (j >= 0 && rs[j][1] >= nq[i][1]) {
                pool.add(rs[j][0]);
                j--;
            }
            int index = nq[i][2];
            if (pool.isEmpty()) {
                res[index] = -1;
            } else {
                res[index] = closest(pool, nq[i][0]);
            }
        }
        return res;
    }

    private int closest(TreeSet<Integer> ts, int t) {
        Integer floor = ts.floor(t);
        Integer ceil = ts.ceiling(t);
        if (floor == null) {
            return ceil;
        } else if (ceil == null) {
            return floor;
        } else {
            int d1 = Math.abs(floor - t);
            int d2 = Math.abs(ceil - t);
            return d1 <= d2 ? floor : ceil;
        }
    }
}
