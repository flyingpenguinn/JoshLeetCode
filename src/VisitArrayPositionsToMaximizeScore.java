public class VisitArrayPositionsToMaximizeScore {
    public long maxScore(int[] a, int x) {
        int n = a.length;
        long maxeven = 0;
        long maxodd = 0;
        long res = 0;
        for (int i = n - 1; i >= 0; --i) {
            long way1 = 0;
            long way2 = 0;
            if (a[i] % 2 == 1) {
                way1 = Math.max(way1, maxeven - x);
                way2 = Math.max(way2, maxodd);
            } else {
                way1 = Math.max(way1, maxodd - x);
                way2 = Math.max(way2, maxeven);
            }
            long cur = a[i] + Math.max(way1, way2);
            res = cur;
            if (a[i] % 2 == 1) {
                maxodd = Math.max(maxodd, cur);
            } else {
                maxeven = Math.max(maxeven, cur);
            }
        }
        return res;
    }
}
