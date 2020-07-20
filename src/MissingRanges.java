import java.util.ArrayList;
import java.util.List;

/*
LC#163
Given a sorted integer array nums, where the range of elements are in the inclusive range [lower, upper], return its missing ranges.

Example:

Input: nums = [0, 1, 3, 50, 75], lower = 0 and upper = 99,
Output: ["2", "4->49", "51->74", "76->99"]
 */
public class MissingRanges {
    public List<String> findMissingRanges(int[] a, long lower, long upper) {
        // all a[i] IN THE RANGE of lower/upper!
        // also need to mind overflow
        List<String> r = new ArrayList<>();
        if (a == null || a.length == 0) {
            r.add(range(lower, upper));
            return r;
        }
        int n = a.length;
        if (lower < a[0]) {
            r.add(range(lower, a[0] - 1L));
        }
        for (int i = 1; i < n; i++) {
            if (a[i] > a[i - 1] + 1L) {
                r.add(range(a[i - 1] + 1L, a[i] - 1L));
            }
        }
        if (a[n - 1] < upper) {
            r.add(range(a[n - 1] + 1L, upper));
        }
        return r;
    }

    private String range(long l, long u) {
        if (l == u) {
            return l + "";
        } else {
            return l + "->" + u;
        }
    }
}
