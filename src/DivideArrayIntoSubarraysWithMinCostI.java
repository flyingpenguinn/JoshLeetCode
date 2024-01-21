public class DivideArrayIntoSubarraysWithMinCostI {
    public int minimumCost(int[] a) {
        int n = a.length;
        int res = a[0] + a[1] + a[2];
        for (int i = 1; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                int cur = a[0] + a[i] + a[j];
                res = Math.min(res, cur);
            }
        }
        return res;
    }
}
