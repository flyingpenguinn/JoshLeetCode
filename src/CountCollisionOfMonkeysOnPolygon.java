public class CountCollisionOfMonkeysOnPolygon {
    private long mod = (long) (1e9 + 7);

    public int monkeyMove(int n) {
        long rt = pow2(n) - 2;
        rt %= mod;
        if (rt < 0) {
            rt += mod;
        }
        return (int) rt;
    }

    private long pow2(int n) {
        if (n == 0) {
            return 1L;
        }
        long half = pow2(n / 2);
        long res = half * half;
        res %= mod;
        if (n % 2 == 1) {
            res *= 2;
            res %= mod;
        }
        return res;
    }
}
