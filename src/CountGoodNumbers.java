public class CountGoodNumbers {
    private long mod = 1000000007;

    private long pow(long n, long i) {
        if (i == 0) {
            return 1;
        }
        long half = pow(n, i / 2);
        long rt = half * half;
        rt %= mod;
        if (i % 2 == 1) {
            rt *= n;
            rt %= mod;
        }

        return rt;
    }

    public int countGoodNumbers(long n) {
        return (int)solve(n);
    }

    long solve(long n) {
        if (n % 2 == 0) {
            return pow(20, n / 2);
        } else {
            long rt = pow(20, n / 2) * 5;
            rt %= mod;
            return rt;
        }
    }
}
