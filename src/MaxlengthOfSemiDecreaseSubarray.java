import java.util.ArrayList;
import java.util.List;

public class MaxlengthOfSemiDecreaseSubarray {
    // if a later value is smalller it's of no use. keep an increasing list binary search current value
    public int maxSubarrayLength(int[] a) {
        int n = a.length;
        List<Integer> cur = new ArrayList<>();

        int res = 0;
        for (int i = 0; i < n; ++i) {
            int l = 0;
            int u = cur.size()-1;
            while (l <= u) {
                int mid = l + (u - l) / 2;
                if (a[cur.get(mid)] <= a[i]) {
                    l = mid + 1;
                } else {
                    u = mid - 1;
                }
            }
            if (l == cur.size()) {
                cur.add(i);
            } else {
                int diff = i - cur.get(l) + 1;
                res = Math.max(res, diff);
            }
        }
        return res;
    }
}
