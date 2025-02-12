import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortMatrixByDiagonal {
    public int[][] sortMatrix(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        for (int i = 0; i < m; ++i) {
            List<Integer> l = new ArrayList<>();
            int rj = 0;
            int ri = i;
            int oi = ri;
            int oj = rj;
            while (ri < m && rj < n) {
                l.add(a[ri][rj]);
                ++ri;
                ++rj;
            }

            Collections.sort(l);
            Collections.reverse(l);
            int li = 0;
            while (oi < m && oj < n) {
                a[oi][oj] = l.get(li++);
                ++oi;
                ++oj;
            }
        }
        for (int j = 1; j < n; ++j) {
            List<Integer> l = new ArrayList<>();
            int ri = 0;
            int rj = j;
            int oi = ri;
            int oj = rj;
            while (ri < m && rj < n) {
                l.add(a[ri][rj]);
                ++ri;
                ++rj;
            }
            Collections.sort(l);
            int li = 0;
            while (oi < m && oj < n) {
                a[oi][oj] = l.get(li++);
                ++oi;
                ++oj;
            }
        }

        return a;
    }
}
