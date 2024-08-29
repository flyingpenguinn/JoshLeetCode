public class FindArrayStateAfterKMultiplicationI {
    public int[] getFinalState(int[] a, int k, int m) {
        int n = a.length;
        for (int i = 0; i < k; ++i) {
            int mini = 0;
            for (int j = 1; j < n; ++j) {
                if (a[j] < a[mini]) {
                    mini = j;
                }
            }
            a[mini] *= m;
        }
        return a;
    }
}
