public class FindScoreOfAllPrefix {
    public long[] findPrefixScore(int[] a) {
        int n = a.length;
        long[] conv = new long[n];
        long maxsofar = 0;
        for (int i = 0; i < n; ++i) {
            maxsofar = Math.max(maxsofar, a[i]);
            conv[i] = 0L + a[i] + maxsofar;
        }
        long[] res = new long[n];
        res[0] = conv[0];
        for (int i = 1; i < n; ++i) {
            res[i] = res[i - 1] + conv[i];
        }
        return res;
    }
}
