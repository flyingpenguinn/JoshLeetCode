import java.util.HashMap;
import java.util.Map;

public class CountValidSuffixSortsI {
    // suffix i is good if a[i..] and t[i..] are the same multiset and t[i..] is non-increasing
    // maintain diff[value] and the number of nonzero entries (mismatch) while extending the suffix
    // then the prefix a[0..i-1] must equal t[0..i-1]
    int mismatch = 0;

    private void updateDiff(Map<Integer, Integer> m, int k, int d) {
        int oldv = m.getOrDefault(k, 0);
        int nv = oldv + d;
        if (oldv == 0) {
            ++mismatch;
        }
        if (nv == 0) {
            --mismatch;
        }
        m.put(k, nv);
    }

    Map<Integer, Integer> diff = new HashMap<>();

    public int countSuffix(int[] a, int[] t) {
        int n = a.length;

        boolean[] good = new boolean[n];
        for (int i = n - 1; i >= 0; --i) {
            if (i < n - 1 && t[i] < t[i + 1]) {
                break;
            }
            int av = a[i];
            int tv = t[i];
            updateDiff(diff, av, 1);
            updateDiff(diff, tv, -1);
            if (mismatch == 0) {
                good[i] = true;
            }
        }
        int res = 0;
        for (int i = 0; i < n; ++i) {
            if (i == 0) {
                if (good[i]) {
                    ++res;
                }
            } else {
                if (a[i - 1] != t[i - 1]) {
                    break;
                }
                if (good[i]) {
                    ++res;
                }
            }
        }
        return res;
    }

}
