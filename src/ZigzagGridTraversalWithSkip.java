import java.util.ArrayList;
import java.util.List;

public class ZigzagGridTraversalWithSkip {
    public List<Integer> zigzagTraversal(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        boolean add = true;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            if (i % 2 == 1) {
                for (int j = n - 1; j >= 0; --j) {
                    if (add) {
                        res.add(a[i][j]);
                    }
                    add = !add;
                }
            } else {
                for (int j = 0; j < n; ++j) {
                    if (add) {
                        res.add(a[i][j]);
                    }
                    add = !add;
                }
            }

        }
        return res;
    }
}
