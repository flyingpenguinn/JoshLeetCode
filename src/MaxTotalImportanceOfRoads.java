import java.util.Arrays;

public class MaxTotalImportanceOfRoads {
    public long maximumImportance(int n, int[][] roads) {
        int rn = roads.length;
        int[][] dig = new int[n][2];
        for (int i = 0; i < n; ++i) {
            dig[i][0] = i;
        }
        for (int i = 0; i < rn; ++i) {
            int s = roads[i][0];
            int e = roads[i][1];
            ++dig[s][1];
            ++dig[e][1];
        }
        Arrays.sort(dig, (x, y) -> Integer.compare(x[1], y[1]));
        int[] num = new int[n];
        for (int i = n - 1; i >= 0; --i) {
            num[dig[i][0]] = i + 1;
        }
        long res = 0;
        for (int i = 0; i < rn; ++i) {
            int s = roads[i][0];
            int e = roads[i][1];
            res += num[s];
            res += num[e];
        }
        return res;
    }
}
