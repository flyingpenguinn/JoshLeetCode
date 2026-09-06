public class CountGoodCyclicRotations {
    public int countGoodRotations(int[] a) {
        int n = a.length;
        long[] psum = new long[n];
        psum[0] = a[0];
        for (int i = 1; i < n; ++i) {
            psum[i] = psum[i - 1] + a[i];
        }
        int half = n / 2;
        int res = 0;
        for (int len = 0; len <= n - 1; ++len) {
            int end = len - 1;
            int start = end + 1;
            int startend = Math.min(n - 1, start + half - 1);
            int overflow = start + half - 1 - startend;
            long sum1 = psum[startend] - (start == 0 ? 0 : psum[start - 1]);
            long sum2 = overflow == 0 ? 0 : psum[overflow - 1];
            long firsthalf = sum1 + sum2;
            long secondhalf = psum[n - 1] - firsthalf;
            if (firsthalf > secondhalf) {
                ++res;
            }
        }
        return res;
    }
}
