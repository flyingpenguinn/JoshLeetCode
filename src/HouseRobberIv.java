import java.util.Arrays;

public class HouseRobberIv {
    // always better to pick the first good house on the most left, as it allows us to pick i+1 as well
    public int minCapability(int[] a, int k) {
        int n = a.length;
        int l = 1;
        int u = (int) 1e9;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            boolean good = false;
            int picked = 0;
            for (int i = 0; i < n; ++i) {
                if (a[i] <= mid) {
                    ++picked;
                    if (picked >= k) {
                        good = true;
                        break;
                    }
                    ++i;
                }
            }
            if (good) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
