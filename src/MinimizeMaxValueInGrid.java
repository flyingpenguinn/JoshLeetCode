import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MinimizeMaxValueInGrid {
    // can also use topo sort and assign values based on their generation in the topo sort result
    private class P {
        int r;
        int c;
        int v;

        public P(int r, int c, int v) {
            this.r = r;
            this.c = c;
            this.v = v;
        }
    }

    public int[][] minScore(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        List<P> list = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                list.add(new P(i, j, a[i][j]));
            }
        }
        Collections.sort(list, (x, y) -> Integer.compare(x.v, y.v));
        int[] minr = new int[m];
        int[] minc = new int[n];
        Arrays.fill(minr, 1);
        Arrays.fill(minc, 1);
        int[][] res = new int[m][n];
        for (int i = 0; i < list.size(); ++i) {
            P cp = list.get(i);
            int cv = Math.max(minr[cp.r], minc[cp.c]);
            res[cp.r][cp.c] = cv;
            minr[cp.r] = Math.max(minr[cp.r], cv + 1);
            minc[cp.c] = Math.max(minc[cp.c], cv + 1);
        }
        return res;
    }
}
