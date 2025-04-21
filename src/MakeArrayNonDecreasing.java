public class MakeArrayNonDecreasing {
    public int maximumPossibleSize(int[] a) {
        int n = a.length;
        int cmax = 0;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            if (a[i] >= cmax) {
                cmax = a[i];
                ++res;
            }
        }
        return res;
    }
}
