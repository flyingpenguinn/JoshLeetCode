public class SmallestIndexWithDigitSumEqualToIndex {
    private int sumdig(int v) {
        int res = 0;
        while (v > 0) {
            res += v % 10;
            v /= 10;
        }
        return res;
    }

    public int smallestIndex(int[] a) {
        int n = a.length;
        for (int i = 0; i < n; ++i) {
            if (sumdig(a[i]) == i) {
                return i;
            }
        }
        return -1;
    }
}
