import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CountPeakArrays {
    public List<Integer> countOfPeaks(int[] a, int[][] qs) {
        int n = a.length;
        int[] bit = new int[n];
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i + 1 < n; ++i) {
            if (a[i] > a[i - 1] && a[i] > a[i + 1]) {
                update(bit, i, 1);
                set.add(i);
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int[] q : qs) {
            int type = q[0];
            int l = q[1];
            int r = q[2];
            if (type == 1) {
                int upper = query(bit, r);
                int lower = l == 0 ? 0 : query(bit, l - 1);
                int cur = upper - lower;
                if (set.contains(r)) {
                    --cur;
                }
                if (l != r && set.contains(l)) {
                    --cur;
                }
                res.add(cur);
            } else {
                int idx = q[1];
                int v = q[2];
                if (v > a[idx]) {
                    if (!set.contains(idx) && idx > 0 && idx + 1 < n && v > a[idx - 1] && v > a[idx + 1]) {
                        update(bit, idx, 1);
                        set.add(idx);
                    }
                    if (idx - 1 > 0 && v >= a[idx - 1] && set.contains(idx - 1)) {
                        update(bit, idx - 1, -1);
                        set.remove(idx - 1);
                    }
                    if (idx + 1 < n && v >= a[idx + 1] && set.contains(idx + 1)) {
                        update(bit, idx + 1, -1);
                        set.remove(idx + 1);
                    }
                } else {
                    if (set.contains(idx) && idx > 0 && idx + 1 < n && (v <= a[idx - 1] || v <= a[idx + 1])) {
                        update(bit, idx, -1);
                        set.remove(idx);
                    }
                    if (idx - 2 >= 0 && a[idx - 1] > a[idx - 2] && a[idx - 1] > v && !set.contains(idx - 1)) {
                        update(bit, idx - 1, 1);
                        set.add(idx - 1);
                    }
                    if (idx + 2 < n && a[idx + 1] > a[idx + 2] && a[idx + 1] > v && !set.contains(idx + 1)) {
                        update(bit, idx + 1, 1);
                        set.add(idx + 1);
                    }
                }
                a[idx] = v;
            }
        }
        return res;
    }


    private int query(int[] bit, int i) {
        int res = 0;
        while (i > 0) {
            res += bit[i];
            i -= i & (-i);
        }
        return res;
    }

    private void update(int[] bit, int i, int d) {
        while (i < bit.length) {
            bit[i] += d;
            i += i & (-i);
        }
    }
}
