import java.util.TreeSet;

public class SumOfImbalanceNumberOfAlLSubarrays {
    public int sumImbalanceNumbers(int[] a) {
        int n = a.length;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            TreeSet<Integer> ts = new TreeSet<>();
            int cur = 0;
            for (int j = i; j < n; ++j) {
                Integer before = ts.floor(a[j]);
                Integer after = ts.ceiling(a[j]);
                if (before == null && after == null) {

                } else if (before == null) {
                    if (after - a[j] > 1) {
                        ++cur;
                    }
                } else if (after == null) {
                    if (a[j] - before > 1) {
                        ++cur;
                    }
                } else {
                    if (after - a[j] <= 1 && a[j] - before <= 1) {
                        if (after - before > 1) {
                            --cur;
                        } else {
                            // no change
                        }
                    } else if (after - a[j] <= 1) {
                        //
                    } else if (a[j] - before <= 1) {
                        //
                    } else {
                        ++cur;
                    }
                }
                res += cur;
                ts.add(a[j]);
            }
        }
        return res;
    }
}
