import java.util.Arrays;

public class MaxAlternatingSumOfSquares {
    public long maxAlternatingSum(int[] a) {
        int n = a.length;
        long[] sq = new long[n];
        for (int i = 0; i < n; ++i) {
            long v = a[i];
            sq[i] = v * v;
        }
        Arrays.sort(sq);
        int i = 0;
        int j = n - 1;
        long res = 0;
        while (i < j) {
            res += sq[j] - sq[i];
            ++i;
            --j;
        }
        if (i == j) {
            res += sq[i];
        }
        return res;
    }
}
