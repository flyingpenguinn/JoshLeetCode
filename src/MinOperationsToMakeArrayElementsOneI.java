public class MinOperationsToMakeArrayElementsOneI {
    public int minOperations(int[] a) {
        int n = a.length;
        int res = 0;
        for (int i = 0; i + 2 < n; ++i) {
            if (a[i] == 0) {
                a[i] = 1;
                a[i + 1] ^= 1;
                a[i + 2] ^= 1;
                ++res;
            }
        }
        for (int ai : a) {
            if (ai != 1) {
                return -1;
            }
        }
        return res;
    }
}
