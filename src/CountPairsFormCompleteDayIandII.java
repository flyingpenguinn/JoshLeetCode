public class CountPairsFormCompleteDayIandII {
    public long countCompleteDayPairs(int[] a) {
        int n = a.length;
        long[] m = new long[24];
        long sum = 0;
        long res = 0;
        for (int i = 0; i < n; ++i) {
            long mod = (a[i] % 24);
            long other = 24 - mod;
            other = other % 24;
            long cur = m[(int) (other)];
            res += cur;
            //  System.out.println(mod+" "+cur);
            ++m[(int) mod];
        }
        return res;
    }
}
