public class MinLimitOfBallsInBag {
    private int Max = 1000000000;

    public int minimumSize(int[] a, int maxop) {
        int n = a.length;
        int l = 1;
        int u = 0;
        for (int ai : a) {
            u = Math.max(u, ai);
        }
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (possible(a, maxop, mid)) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private boolean possible(int[] a, int op, int t) {
        int n = a.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] <= t) {
                continue;
            }
            res += (int) Math.ceil((a[i] - t + 0.0) / t);
            if (res > op) {
                return false;
            }
        }
        return true;
    }
}
