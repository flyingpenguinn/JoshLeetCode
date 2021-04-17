public class MinOperationsToMakeArrayAscending {
    public int minOperations(int[] a) {
        int n = a.length;
        int res = 0;
        for (int i = 1; i < n; i++) {
            int diff = a[i - 1] + 1 - a[i];
            res += Math.max(0, diff);
            a[i] = Math.max(a[i - 1] + 1, a[i]);
        }
        return res;
    }
}
