public class FrogJumpII {
    // only do even and odd
    public int maxJump(int[] a) {
        int n = a.length;
        if (n == 2) {
            return a[1] - a[0];
        }
        int res = 0;
        for (int i = 1; i + 2 < n; i += 2) {
            int diff = a[i + 2] - a[i];
            res = Math.max(res, diff);
        }
        for (int i = 0; i + 2 < n; i += 2) {
            int diff = a[i + 2] - a[i];
            res = Math.max(res, diff);
        }
        return res;
    }
}
