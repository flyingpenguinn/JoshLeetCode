public class MinSumOfTwoNonOverlappingSubarray {

    // actually similar to buysell III: buy and sell twice.
    // so we can also handle K non overlapping subarrays...
    public int maxSumTwoNoOverlap(int[] a, int l, int m) {

        int s1 = maxsum(a, l, m);
        int s2 = maxsum(a, m, l);
        return Math.max(s1, s2);
    }

    int maxsum(int[] a, int l, int m) {
        int n = a.length;
        int[] maxr = new int[n];
        int accu = 0;
        int i = n - 1;
        while (i >= n - m) {
            accu += a[i--];
        }
        int base = n - 1;
        int rmax = -1;

        while (i >= l - 1) {
            rmax = Math.max(rmax, accu);
            maxr[i + 1] = rmax;
            accu -= a[base--];
            accu += a[i--];
        }
        accu = 0;
        i = 0;
        while (i < l) {
            accu += a[i++];
        }

        base = 0;
        int max = -1;
        // i is the right start
        int lmax = -1;

        while (i <= n - m) {
            lmax = Math.max(lmax, accu);
            int rmaxi = maxr[i];
            max = Math.max(max, lmax + rmaxi);
            accu -= a[base++];
            accu += a[i++];
        }
        return max;
    }
}
