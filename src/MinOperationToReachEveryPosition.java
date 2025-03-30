public class MinOperationToReachEveryPosition {
    public int[] minCosts(int[] a) {
        int n = a.length;
        int[] res = new int[n];
        int min = a[0];
        res[0] = min;
        for (int i = 1; i < n; ++i) {
            min = Math.min(min, a[i]);
            res[i] = min;
        }
        return res;
    }
}
