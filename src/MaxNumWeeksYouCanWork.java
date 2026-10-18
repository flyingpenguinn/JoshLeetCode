public class MaxNumWeeksYouCanWork {
    // pairing. majority rule
    public long numberOfWeeks(int[] a) {
        int n = a.length;
        long maxv = 0;
        long sum = 0;
        for (int i = 0; i < n; ++i) {
            long v = a[i];
            maxv = Math.max(maxv, v);
            sum += v;
        }
        if (maxv <= sum / 2) {
            return sum;
        }
        long lost = maxv - (sum - maxv);
        return sum - lost + 1; // can arrange one in the end
    }
}
