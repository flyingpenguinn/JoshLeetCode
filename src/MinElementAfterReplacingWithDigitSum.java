public class MinElementAfterReplacingWithDigitSum {
    private int digisum(int t) {
        int res = 0;
        while (t > 0) {
            res += t % 10;
            t /= 10;
        }
        return res;
    }

    public int minElement(int[] a) {
        int res = 100000;
        int n = a.length;
        for (int i = 0; i < n; ++i) {
            int cur = digisum(a[i]);
            res = Math.min(res, cur);
        }
        return res;
    }
}
