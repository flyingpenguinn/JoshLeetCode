import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FlipSquareSubmatrixVertically {
    public int[][] reverseSubmatrix(int[][] a, int x, int y, int k) {
        int m = a.length;
        int n = a[0].length;
        for (int j = y; j < y + k; ++j) {
            List<Integer> v = new ArrayList<>();
            for (int i = x; i < x + k; ++i) {
                v.add(a[i][j]);
            }
            Collections.reverse(v);
            int vi = 0;
            for (int i = x; i < x + k; ++i) {
                a[i][j] = v.get(vi++);
            }
        }
        return a;
    }
}
