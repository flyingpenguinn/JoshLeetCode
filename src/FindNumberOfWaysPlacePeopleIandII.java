import java.util.Arrays;

public class FindNumberOfWaysPlacePeopleIandII {
    // x asc then y desc
    public int numberOfPairs(int[][] a) {
        int m = a.length;
        Arrays.sort(a, (x, y) -> {
            if (x[0] == y[0]) {
                return Integer.compare(y[1], x[1]);
            } else {
                return Integer.compare(x[0], y[0]);
            }

        });
        int res = 0;
        for (int i = 0; i < m; ++i) {
            int v1 = a[i][0];
            int v2 = a[i][1];
            int by = (int) -2e9;
            for (int j = i + 1; j < m; ++j) {
                int v3 = a[j][0];
                int v4 = a[j][1];
                if (v3 < v1 || v4 > v2) {
                    continue;
                }
                if (by < v4) {
                    ++res;
                }
                by = Math.max(by, v4);
            }
        }
        return res;
    }
}
