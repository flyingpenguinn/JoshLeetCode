public class NumberOfCommonFactors {
    public int commonFactors(int a, int b) {
        int res = 0;
        for (int i = 1; i <= gcd(a, b); ++i) {
            if (a % i == 0 && b % i == 0) {
                ++res;
            }
        }
        return res;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
