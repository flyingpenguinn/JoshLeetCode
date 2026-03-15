import java.util.Arrays;

public class SumOfGcdOfFormedPairs {
    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public long gcdSum(int[] a) {
        int n = a.length;
        long maxx = 0;
        long[] pg = new long[n];
        for (int i = 0; i < n; ++i) {
            long v = a[i];
            maxx = Math.max(maxx, v);
            pg[i] = gcd(v, maxx);
        }
        Arrays.sort(pg);
        int i = 0;
        int j = n - 1;
        long res = 0;
        while (i < j) {
            long gc = gcd(pg[i], pg[j]);
            res += gc;
            ++i;
            --j;
        }
        return res;
    }
}
