public class NumberOfBeautifulPairs {
    public int countBeautifulPairs(int[] a) {
        int n = a.length;
        int res = 0;
        int[] count = new int[10];
        for (int i = 0; i < n; ++i) {
            int last = a[i] % 10;
            for (int d = 1; d < 10; ++d) {
                if (gcd(d, last) == 1) {
                    res += count[d];
                }
            }
            int first = String.valueOf(a[i]).charAt(0) - '0';
            ++count[first];
        }
        return res;
    }

    private int gcd(int a, int b) {
        if (b > a) {
            return gcd(b, a);
        }
        return b == 0 ? a : gcd(b, a % b);
    }
}
