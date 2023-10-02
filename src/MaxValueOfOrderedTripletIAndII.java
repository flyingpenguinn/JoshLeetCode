public class MaxValueOfOrderedTripletIAndII {
    public long maximumTripletValue(int[] a) {
        int n = a.length;
        long max = a[0];
        long p1 = a[0] - a[1];
        long res = 0;
        for (int i = 2; i < n; ++i) {
            long p2 = a[i];
            long cur = p1 * p2;
            res = Math.max(res, cur);
            max = Math.max(max, a[i - 1]);
            p1 = Math.max(p1, max - a[i]);
        }
        return res;
    }
}
