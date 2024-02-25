public class FindLargestAreaOfSquareInside {
    public long largestSquareArea(int[][] bl, int[][] tr) {
        int n = bl.length;
        long res = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                long nbl0 = Math.max(bl[i][0], bl[j][0]);
                long nbl1 = Math.max(bl[i][1], bl[j][1]);
                long ntr0 = Math.min(tr[i][0], tr[j][0]);
                long ntr1 = Math.min(tr[i][1], tr[j][1]);
                if (nbl0 >= ntr0 || nbl1 >= ntr1) {
                    continue;
                }
                long e1 = (ntr0 - nbl0);
                long e2 = (ntr1 - nbl1);
                long e = Math.min(e1, e2);
                res = Math.max(res, e * e);
            }
        }
        return res;
    }
}
