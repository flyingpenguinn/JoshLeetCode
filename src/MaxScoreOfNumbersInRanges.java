import java.util.Arrays;

public class MaxScoreOfNumbersInRanges {
    public int maxPossibleScore(int[] a, int d) {
        Arrays.sort(a);  // Sort the start array to handle intervals in increasing order
        int n = a.length;
        int l = 0;
        int u = (int) (2e9);
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (doable(a, mid, d)) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return u;
    }

    private boolean doable(int[] a, int t, long d) {
        int n = a.length;
        int i = 0;
        long cur = a[i];
        while (i + 1 < n) {
            if (cur + t > a[i + 1] + d) {
                return false;
            }

            cur = Math.max(cur + t, a[i + 1]);
            ++i;
        }
        return true;
    }
}
