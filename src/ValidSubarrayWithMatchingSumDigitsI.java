public class ValidSubarrayWithMatchingSumDigitsI {
    public int countValidSubarrays(int[] a, int x) {
        int n = a.length;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            long sum = 0;
            for (int j = i; j < n; ++j) {
                sum += a[j];
                if (good(sum, x)) {
                    ++res;
                }
            }
        }
        return res;
    }

    private boolean good(long num, int x) {
        long ld = num % 10;
        long fd = -1;
        while (num > 0) {
            long dig = num % 10;
            fd = dig;
            num /= 10;
        }
        return ld == x && fd == x;
    }
}
