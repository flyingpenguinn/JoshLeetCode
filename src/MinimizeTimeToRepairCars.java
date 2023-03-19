import java.util.Arrays;

public class MinimizeTimeToRepairCars {
    public long repairCars(int[] ranks, int cars) {
        Arrays.sort(ranks);
        int n = ranks.length;
        long l = 1;
        long u = (long) 1e18;
        while (l <= u) {
            long mid = l + (u - l) / 2;
            if (doable(ranks, cars, mid)) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private boolean doable(int[] a, int cars, long t) {
        int n = a.length;
        int ccars = 0;
        for (int i = 0; i < n; ++i) {
            long fixed = (long) Math.sqrt(t * 1.0 / a[i]);
            ccars += fixed;
            if (ccars >= cars) {
                return true;
            }
        }
        return false;
    }
}
