public class NumberOfDistinctBinaryStrings {
    private long mod = (long) 1e9 + 7;

    public int countDistinctStrings(String s, int k) {
        int n = s.length();
        int coe = n - k + 1;
        return (int) pow2(coe);
    }

    private long pow2(int n) {
        if (n == 0) {
            return 1L;
        } else {
            long half = pow2(n / 2);
            long combined = (half * half) % mod;
            if (n % 2 == 1) {
                combined *= 2;
                combined %= mod;
            }
            return combined;
        }
    }
}
