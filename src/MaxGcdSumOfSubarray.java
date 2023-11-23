import base.ArrayUtils;

public class MaxGcdSumOfSubarray {
    // RMQ using Tarjan sparse table + binary search
    // given a starting point i, gcd will drop at most 20 times. find these times using a sparse table
    private final int M = 17;
    private int[][] d;

    public long maxGcdSum(int[] a, int k) {
        int n = a.length;
        d = new int[n + 10][M];
        createSparseTable(a);
        long[] psum = new long[n];
        psum[0] = a[0];
        for (int i = 1; i < n; i++) {
            psum[i] = psum[i - 1] + a[i];
        }
        long res = 0;
        for (int i = 0; i < n; i++) {
            int l = i, t = a[i];
            while (l < n) {
                t = gcd(t, a[l]);
                int r = find(a, i, l, t);
                if (r - i + 1 >= k) {
                    long sum = psum[r] - (i == 0 ? 0 : psum[i - 1]);
                    long cur = t * sum;
                    res = Math.max(res, cur);
                }
                l = r + 1;
            }
        }
        return res;
    }

    // find biggest mid that gcd is equal to t
    private int find(int[] w, int i, int l, int t) {
        int r = w.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (querySparseTable(i + 1, mid + 1) == t) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return r;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private void createSparseTable(int[] w) {
        int n = w.length;
        for (int j = 0; j < M; j++) {
            for (int i = 1; i + (1 << j) - 1 <= n; i++) {
                if (j == 0) {
                    d[i][j] = w[i - 1];
                } else {
                    d[i][j] = gcd(d[i][j - 1], d[i + (1 << (j - 1))][j - 1]);
                }
            }
        }
    }

    private int querySparseTable(int l, int r) {
        int len = r - l + 1;
        int k = (int) (Math.log(len) / Math.log(2));
        return gcd(d[l][k], d[r - (1 << k) + 1][k]);
    }

    public static void main(String[] args) {
        System.out.println(new MaxGcdSumOfSubarray().maxGcdSum(ArrayUtils.read1d("[2,1,4,4]"), 2));
    }
}

