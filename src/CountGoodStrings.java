public class CountGoodStrings {
    long[][] matrix = {{1, 1}, {1, 0}};
    private long Mod = (long) (1e9 + 7);

    public long fib(long n) {
        if (n <= 1) {
            return n;
        }
        long[][] m = pow(matrix, n - 1);
        return m[0][0];
    }

    private long[][] pow(long[][] matrix, long n) {
        if (n == 1) {
            return matrix;
        }
        if (n % 2 == 0) {
            long[][] p = pow(matrix, n / 2);
            return multi(p, p);
        } else {
            long[][] p = pow(matrix, (n - 1) / 2);
            return multi(matrix, multi(p, p));
        }
    }

    // m*n
    private long[][] multi(long[][] p1, long[][] p2) {
        if (p1[0].length != p2.length) {
            throw new IllegalArgumentException();
        }
        long[][] r = new long[p1.length][p2[0].length];
        for (int i = 0; i < p1.length; i++) {
            for (int j = 0; j < p2[0].length; j++) {
                long sum = 0;
                for (int k = 0; k < p1[0].length; k++) {
                    /// this dimension is shared by the two. 2nd matrix's row number is first matrix's column number
                    sum += p1[i][k] * p2[k][j];
                    sum %= Mod;
                }
                r[i][j] = sum;
            }
        }
        return r;
    }

    public int countGoodStrings(long n) {
        if(n==1){
            return 2;
        }
        return seq(n-1);
    }

    private int seq(long n) {
        long cur = fib(n - 1) + fib(n + 2);
        cur %= Mod;
        return (int) cur;
    }
}
