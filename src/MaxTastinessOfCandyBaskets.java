import java.util.Arrays;

public class MaxTastinessOfCandyBaskets {
    public int maximumTastiness(int[] a, int k) {
        int n = a.length;
        Arrays.sort(a);
        int l = 1;
        int u = (int) 1e9;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (good(a, mid, k)) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return u;
    }

    private boolean good(int[] a, int t, int k) {
        int n = a.length;
        int i = 0;
        // can either start from 0 or n-1 doesnt really matter
        while (k > 1 && i < n) {
            int j = i;
            while (j < n && a[j] - a[i] < t) {
                ++j;
            }
            if (j < n) {
                --k;
            }
            i = j;
        }
        return k == 1;
    }
}
