import java.util.ArrayList;
import java.util.List;

public class MinMovesToGetKOnes {
    // TODO: do it myself. Similar to Allocate mail box
    public long minimumMoves(int[] nums, int k, int maxChanges) {
        List<Long> a = new ArrayList<>();
        a.add(0L);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                a.add(a.get(a.size() - 1) + i);
            }
        }

        int n = a.size() - 1, m = Math.max(0, k - maxChanges);
        long res = Long.MAX_VALUE;
        for (int l = m; l <= Math.min(n, Math.min(m + 3, k)); l++) {
            for (int i = 0; i <= n - l; i++) {
                int mid1 = i + l / 2, mid2 = i + l - l / 2;
                long cur = a.get(i + l) - a.get(mid2) - (a.get(mid1) - a.get(i));
                res = Math.min(res, cur + (k - l) * 2);
            }
        }
        return res;
    }
}
