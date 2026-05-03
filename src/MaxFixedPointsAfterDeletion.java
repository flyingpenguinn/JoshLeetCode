import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaxFixedPointsAfterDeletion {
    // convert to 2d LIS problem!
    private int[] bit;

    public int maxFixedPoints(int[] nums) {
        int n = nums.length;
        Map<Integer, List<Integer>> gm = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int v = nums[i];
            if (v <= i && v <= n) {
                int gap = i - v;
                gm.computeIfAbsent(v, k -> new ArrayList<>()).add(gap);

            }
        }

        int[] bit = new int[n + 2];
        int res = 0;

        for (int v = 0; v <= n; v++) {
            if (!gm.containsKey(v)) {
                continue;
            }

            List<int[]> cand = new ArrayList<>();
            for (int p : gm.get(v)) {
                int cur = 1 + q(bit, p + 1);
                cand.add(new int[]{p, cur});
                res = Math.max(res, cur);
            }

            for (int[] p : cand) {
                u(bit, p[0] + 1, p[1]);
            }
        }

        return res;
    }

    private int q(int[] bit, int i) {
        int res = 0;
        while (i > 0) {

            res = Math.max(res, bit[i]);
            i -= i & (-i);
        }
        return res;
    }

    private void u(int[] bit, int i, int v) {
        while (i < bit.length) {

            bit[i] = Math.max(bit[i], v);
            i += i & (-i);
        }
    }
}
