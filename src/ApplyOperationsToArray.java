public class ApplyOperationsToArray {
    public int[] applyOperations(int[] a) {
        int n = a.length;
        for (int i = 0; i + 1 < n; ++i) {
            if (a[i] == a[i + 1]) {
                a[i] *= 2;
                a[i + 1] = 0;
            }
        }
        int[] res = new int[n];
        int ri = 0;
        for (int i = 0; i < n; ++i) {
            if (a[i] != 0) {
                res[ri++] = a[i];
            }
        }
        return res;
    }
}
