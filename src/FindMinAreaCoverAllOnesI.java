public class FindMinAreaCoverAllOnesI {
    private int Max = (int) 1e9;

    public int minimumArea(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int minr = Max;
        int minc = Max;
        int maxr = -1;
        int maxc = -1;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (a[i][j] == 1) {
                    minr = Math.min(minr, i);
                    maxr = Math.max(minr, i);
                    minc = Math.min(minc, j);
                    maxc = Math.max(maxc, j);
                }
            }
        }
        if (minr > maxr || minc > maxc) {
            return 0;
        }
        return (maxr - minr + 1) * (maxc - minc + 1);
    }
}
