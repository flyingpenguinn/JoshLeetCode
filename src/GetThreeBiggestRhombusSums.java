import java.util.TreeSet;

public class GetThreeBiggestRhombusSums {

    public int[] getBiggestThree(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        TreeSet<Integer> ts = new TreeSet<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ts.add(a[i][j]);
                for (int d = 1; d < Math.min(m, n); ++d) {
                    if (i - d < 0 || i + d >= m || j - d < 0 || j + d >= n) {
                        continue;
                    }
                    int sum = 0;
                    for (int t = 0; t < d; ++t) {
                        sum += a[i - d + t][j - t];
                    }
                    for (int t = 0; t < d; ++t) {
                        sum += a[i + t][j - d + t];
                    }
                    for (int t = 0; t < d; ++t) {
                        sum += a[i + d - t][j + t];
                    }
                    for (int t = 0; t < d; ++t) {
                        sum += a[i - t][j + d - t];
                    }

                    ts.add(sum);

                }
            }
        }
        int ri = 0;
        int[] res = new int[Math.min(3, ts.size())];
        while (ri < 3 && !ts.isEmpty()) {
            res[ri++] = ts.pollLast();

        }
        return res;

    }
}
