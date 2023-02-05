import java.util.Arrays;

public class HouseRobberIv {
    // for each block of rob-able house, we know how to pick them- there are (len+1)/2 ways
    public int minCapability(int[] a, int k) {
        int n = a.length;
        int[] na = Arrays.copyOf(a, n);
        Arrays.sort(na);
        int l = 0;
        int u = n - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (good(a, na[mid], k)) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return na[l];
    }

    private boolean good(int[] a, int t, int k) {
        int i = 0;
        int n = a.length;
        int chosen = 0;
        while (i < n) {
            if (a[i] > t) {
                ++i;
                continue;
            }
            int j = i;
            while (j < n && a[j] <= t) {
                ++j;
            }
            int count = j - i;
            chosen += (count + 1) / 2;
            i = j;
        }
        return chosen >= k;
    }
}
