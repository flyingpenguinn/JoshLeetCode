public class NumberOfWaysToSplitArray {
    public int waysToSplitArray(int[] a) {
        int n = a.length;
        long sum = 0;
        for (int i = 0; i < n; ++i) {
            sum += a[i];
        }
        long left = 0;
        long right = sum;
        int res = 0;
        for (int i = 0; i < n - 1; ++i) {
            left += a[i];
            right -= a[i];
            if (left >= right) {
                ++res;
            }
        }
        return res;
    }
}
