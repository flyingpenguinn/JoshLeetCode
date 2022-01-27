import java.util.HashSet;
import java.util.Set;

public class MaxGoodPeolpeBasedOnStatement {
    public int maximumGood(int[][] a) {
        int n = a.length;
        int res = 0;
        for (int i = 0; i < (1 << n); ++i) {
            Set<Integer> goods = new HashSet<>();
            Set<Integer> bads = new HashSet<>();
            for (int j = 0; j < n; ++j) {
                if (((i >> j) & 1) == 1) {
                    goods.add(j);
                } else {
                    bads.add(j);
                }
            }
            boolean bad = false;
            for (int gi : goods) {
                for (int j = 0; j < a[gi].length; ++j) {
                    if (a[gi][j] == 0 && goods.contains(j)) {
                        bad = true;
                        break;
                    }
                    if (a[gi][j] == 1 && bads.contains(j)) {
                        bad = true;
                        break;
                    }
                }
                if (bad) {
                    break;
                }
            }
            if (!bad) {
                res = Math.max(res, Integer.bitCount(i));
            }
        }
        return res;
    }
}
