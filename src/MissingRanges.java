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
    public List<String> findMissingRanges(int[] a, int l, int u) {
        List<String> r = new ArrayList<>();
        int n = a.length;
        if (n == 0) {
            r.add(tos(l, u)); //!!
            return r;
        }
        if (a[0] > l) {
            r.add(tos(l, a[0] - 1));
        }
        for (int i = 1; i < n; i++) {
            if (a[i] - 1L > a[i - 1]) {// use long to avoid overflow
                r.add(tos(a[i - 1] + 1, a[i] - 1));
            }
        }
        if (a[n - 1] < u) {
            r.add(tos(a[n - 1] + 1, u));
        }
        return r;
    }

    String tos(int l, int u) {
        return l == u ? l + "" : l + "->" + u;
    }
}
