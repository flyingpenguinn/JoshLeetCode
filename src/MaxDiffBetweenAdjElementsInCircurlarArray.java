public class MaxDiffBetweenAdjElementsInCircurlarArray {
    public int maxAdjacentDistance(int[] a) {
        int n = a.length;
        int res = 0;
        for (int i = 0; i + 1 < 2 * n; ++i) {
            int i1mod = (i + 1) % n;
            int imod = i % n;
            int diff = Math.abs(a[i1mod] - a[imod]);
            res = Math.max(res, diff);
        }
        return res;
    }
}
