public class PrimeInDiagonal {
    public int diagonalPrime(int[][] a) {
        int n = a.length;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            if (isprime(a[i][i])) {
                res = Math.max(res, a[i][i]);
            }
        }
        for (int i = 0; i < n; ++i) {
            if (isprime(a[i][n - 1 - i])) {
                res = Math.max(res, a[i][n - 1 - i]);
            }
        }
        return res;
    }


    private boolean isprime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int j = 2; j * j <= n; ++j) {
            if (n % j == 0) {
                return false;
            }
        }
        return true;
    }
}
