public class SumOfNumberAndReverse {
    public boolean sumOfNumberAndReverse(int n) {
        for (int i = 0; i <= n; ++i) {
            int rev = reverse(i);
            if (i + rev == n) {
                return true;
            }
        }
        return false;
    }

    private int reverse(int n) {
        int res = 0;
        int on = n;

        while (n > 0) {
            int dig = n % 10;
            n /= 10;
            res = res * 10 + dig;

        }
        return res;
    }
}
