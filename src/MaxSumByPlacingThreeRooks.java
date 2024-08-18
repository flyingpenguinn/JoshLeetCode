import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaxSumByPlacingThreeRooks {
    // each row just need 3 columns
    private long Min = (long) -1e18;

    public long maximumValueSum(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        List<Integer>[] rows = new ArrayList[m];
        List<int[]> allbigs = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            List<int[]> vs = new ArrayList<>();
            for (int j = 0; j < n; ++j) {
                vs.add(new int[]{a[i][j], i, j});
                allbigs.add(new int[]{a[i][j], i, j});
            }
            Collections.sort(vs, (x, y) -> Integer.compare(y[0], x[0]));
            rows[i] = new ArrayList<>();
            for (int j = 0; j < 3; ++j) {
                rows[i].add(vs.get(j)[2]);
            }

        }
        Collections.sort(allbigs, (x, y) -> Integer.compare(y[0], x[0]));
        List<int[]> allbigs10 = new ArrayList<>();
        for (int i = 0; i < Math.min(allbigs.size(), 50); ++i) {
            allbigs10.add(allbigs.get(i));
        }
        long res = Min;
        for (int i = 0; i < m; ++i) {
            int imaxcol = rows[i].get(0);
            for (int j = 0; j < m; ++j) {
                if (i == j) {
                    continue;
                }
                int p = 0;
                int jmaxcol = -1;
                for (; p < 3; ++p) {
                    if (rows[j].get(p) != imaxcol) {
                        jmaxcol = rows[j].get(p);
                        break;
                    }
                }
                for (int k = 0; k < allbigs10.size(); ++k) {
                    if (allbigs10.get(k)[2] != imaxcol && allbigs10.get(k)[2] != jmaxcol && allbigs10.get(k)[1] != i && allbigs10.get(k)[1] != j) {
                        long cur = 0L + a[i][imaxcol] + a[j][jmaxcol] + allbigs10.get(k)[0];
                        res = Math.max(res, cur);
                        break;
                    }
                }
            }
        }
        return res;
    }
}
