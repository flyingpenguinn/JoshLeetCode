import java.util.Arrays;

public class ApplyOperationToMaximizeFrequencyScore {
    // can do away with binary search
    private long[] prefix;

    public int maxFrequencyScore(int[] a, long k) {
        Arrays.sort(a);
        int n = a.length;
        prefix = new long[n];
        prefix[0] = a[0];
        for (int i = 1; i < n; ++i) {
            prefix[i] = prefix[i - 1] + a[i];
        }
        int l = 1;
        int u = n;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (doable(a, mid, k)) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return u;
    }

    private boolean doable(int[] a, int t, long k) {
        int n = a.length;
        for (int i = 0; i + t - 1 < n; ++i) {
            int j = i + t - 1;
            int m = i + (j - i) / 2;
            long part1 = prefix[j] - prefix[m] - (0L + j - m) * a[m];
            long part2 = (0L + m - i + 1) * a[m] - (prefix[m] - (i == 0 ? 0 : prefix[i - 1]));
            if (part1 + part2 <= k) {
                return true;
            }
        }
        return false;
    }
}
