import java.util.Arrays;

public class EliminateMaxNumOfMonsters {
    // really confused by "eliminate any monster" during the contest...it means we can choose any one of them but may not be the front one
    public int eliminateMaximum(int[] dist, int[] speed) {
        int n = dist.length;
        int[][] m = new int[n][3];
        for (int i = 0; i < n; i++) {
            m[i][0] = dist[i];
            m[i][1] = speed[i];
            m[i][2] = (int) Math.ceil(dist[i] * 1.0 / speed[i]);
        }
        Arrays.sort(m, (x, y) -> Integer.compare(x[2], y[2]));
        for (int i = 0; i < n; i++) {
            if (m[i][1] * i >= m[i][0]) {
                return i;
            }
        }
        return n;
    }
}
