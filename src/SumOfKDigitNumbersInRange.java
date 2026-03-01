public class SumOfKDigitNumbersInRange {
    private long Mod = (long) (1e9 + 7);

    public int sumOfNumbers(int l, int r, int k) {

        long len = r - l + 1;
        long singlesum = (l + r) * (r - l + 1) / 2;
        long times = pow(len, k - 1);
        long singleround = singlesum * times;
        singleround %= Mod;
        long factor = (pow(10, k) - 1);
        if (factor < 0) {
            factor += Mod;
        }
        factor *= modinverse(9);
        factor %= Mod;
        long res = factor * singleround;
        res %= Mod;
        return (int) res;
    }

    private long pow(long a, long b) {
        if (b == 0) {
            return 1L;
        }
        long hf = pow(a, b / 2);
        long res = hf * hf;
        res %= Mod;
        if (b % 2 == 1) {
            res *= a;
            res %= Mod;
        }
        return res;
    }

    private long modinverse(long a) {
        long m = Mod;
        long y = 0;
        long x = 1;
        while (a > 1) {
            long q = a / m;
            long t = m;
            m = a % m;
            a = t;
            t = y;
            y = x - q * y;
            x = t;
        }
        if (x < 0) {
            x += Mod;
        }
        return x;
    }
}
