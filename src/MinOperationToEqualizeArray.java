public class MinOperationToEqualizeArray {
    public int minOperations(int[] a) {
        int n = a.length;
        int a0 = a[0];
        for (int i = 1; i < n; ++i) {
            if (a[i] != a0) {
                return 1;
            }
        }
        return 0;
    }
}
