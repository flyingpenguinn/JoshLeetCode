public class SumOfSquaresOfSpecialElements {
    public int sumOfSquares(int[] a) {
        int res = 0;
        int n = a.length;
        for (int i = 0; i < n; ++i) {
            if (n % (i + 1) == 0) {
                res += a[i] * a[i];
            }
        }
        return res;
    }
}
