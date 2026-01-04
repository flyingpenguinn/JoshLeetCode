import java.util.HashMap;
import java.util.Map;

public class FindMaxValueInContraint {
    public int findMaxVal(int n, int[][] r, int[] d) {
        int[] res = new int[n];
        Map<Integer, Integer> rm = new HashMap<>();
        for (int[] ri : r) {
            int ind = ri[0];
            int maxv = ri[1];
            rm.put(ind, maxv);
        }
        for (int i = 1; i < n; ++i) {
            res[i] = res[i - 1] + d[i - 1];
            if (rm.containsKey(i)) {
                res[i] = Math.min(res[i], rm.get(i));
            } else {

            }
        }
        for (int i = n - 2; i >= 0; --i) {
            if (res[i] < res[i + 1] - d[i]) {
                res[i] = res[i + 1] - d[i];
            } else if (res[i] > res[i + 1] + d[i]) {
                res[i] = res[i + 1] + d[i];
            }
        }
        int maxv = 0;
        for (int ri : res) {
            maxv = Math.max(maxv, ri);
        }
        return maxv;
    }
}
