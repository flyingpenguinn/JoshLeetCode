public class SumOfGoodNumbers {
    public int sumOfGoodNumbers(int[] a, int k) {
        int n = a.length;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            if (i - k < 0 && i + k >= n) {
                res += a[i];
                continue;
            }
            if ((i - k < 0 || a[i] > a[i - k]) && (i + k >= n || a[i] > a[i + k])) {
                res += a[i];
            }
        }
        return res;
    }
}
