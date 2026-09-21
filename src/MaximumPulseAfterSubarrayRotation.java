import java.util.TreeSet;

public class MaximumPulseAfterSubarrayRotation {
    private long Max = (long) 1e18;

    public long maxValue(int[] a) {
        int n = a.length;
        long minsofar = Max;
        long sum = 0;
        long[] ra = new long[n];
        long rawsum = 0;
        for (int i = 0; i < n; ++i) {
            if (i % 2 == 0) {
                ra[i] = a[i];
            } else {
                ra[i] = -a[i];
            }
            rawsum += ra[i];
        }
        TreeSet<Long> os = new TreeSet<>();
        TreeSet<Long> es = new TreeSet<>();
        os.add(0L);
        for (int i = 0; i < n; ++i) {
            sum += ra[i];

            if (i % 2 == 0) {
                if (!es.isEmpty()) {
                    long curmin = es.last();
                    long cur = sum - curmin;
                    minsofar = Math.min(minsofar, cur);

                }
                es.add(sum);
            } else {
                long curmin = os.last();
                long cur = sum - curmin;
                minsofar = Math.min(minsofar, cur);
                os.add(sum);
            }
        }
        if (minsofar < 0) {
            rawsum += 2 * Math.abs(minsofar);
        }
        return rawsum;
    }
}
