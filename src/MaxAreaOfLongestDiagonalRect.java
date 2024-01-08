public class MaxAreaOfLongestDiagonalRect {
    public int areaOfMaxDiagonal(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int maxd = -1;
        int maxa = -1;
        for (int[] r : a) {
            int l = r[0];
            int w = r[1];
            int cur = l * l + w * w;
            if (cur > maxd) {
                maxd = cur;
                maxa = l * w;
            } else if (cur == maxd) {
                maxa = Math.max(maxa, l * w);
            }
        }
        return maxa;
    }
}
