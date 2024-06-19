public class MinMovesPeacefulBoard {
    // decompose row and column!
    // then can also sort rows or columns and assign first row to row1, 2nd row to row 2, and so forth
    public int minMoves(int[][] a) {
        int n = a.length;
        int[] r = new int[n];
        int[] c = new int[n];
        for (int[] ai : a) {
            ++r[ai[0]];
            ++c[ai[1]];
        }
        int rr = solve(r);
        int cr = solve(c);
        return rr + cr;
    }

    private int solve(int[] a) {
        int n = a.length;
        int j = 0;
        int i = 0;
        int res = 0;
        while (i < n) {
            while (a[i] > 1) {
                while (j < n && a[j] > 0) {
                    ++j;
                }
                res += Math.abs(i - j);
                --a[i];
                ++j;
            }
            ++i;
        }
        return res;
    }
}
