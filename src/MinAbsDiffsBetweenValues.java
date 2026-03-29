public class MinAbsDiffsBetweenValues {
    public int minAbsoluteDifference(int[] a) {
        int n = a.length;
        int Max = 100000;
        int res = Max;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (a[i] == 1 && a[j] == 2) {
                    int diff = Math.abs(i - j);
                    res = Math.min(res, diff);
                }
            }
        }
        return res >= Max ? -1 : res;
    }
}
