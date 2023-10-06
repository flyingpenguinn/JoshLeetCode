public class MinimizeArrayAfterReplacePairsWithProduct {
    public int minArrayLength(int[] a, int k) {
        int n = a.length;
        for (int ai : a) {
            if (ai == 0) {
                return 1;
            }
        }
        int i = 0;
        int res = 0;
        while (i < n) {
            int j = i;
            long prod = a[i];
            while (j + 1 < n && prod * a[j + 1] <= k) {
                prod *= a[j + 1];
                ++j;
            }

            ++res;
            i = j + 1;
        }
        return res;
    }
}
