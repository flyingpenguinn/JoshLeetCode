public class FillSpecialGrid {
    private int[][] res;

    public int[][] specialGrid(int n) {
        int w = 1 << n;
        int d = 1 << n;
        res = new int[w][d];
        fill(0, 0, w - 1, d - 1, 0, w * d - 1);
        return res;
    }

    private void fill(int lr, int lc, int rr, int rc, int l, int u) {
        int w = rr - lr + 1;
        int d = rc - lc + 1;
        if (w == 1 && d == 1) {
            res[lr][lc] = l;
            return;
        }
        int numd = u - l + 1;
        fill(lr, lc + d / 2, lr + w / 2 - 1, rc, l, l + numd / 4);
        fill(lr + w / 2, lc + d / 2, rr, rc, l + numd / 4, l + numd / 2);
        fill(lr + w / 2, lc, rr, lc + d / 2 - 1, l + numd / 2, l + numd / 2 + numd / 4);
        fill(lr, lc, lr + w / 2 - 1, lc + d / 2 - 1, l + numd / 2 + numd / 4, u);
    }
}
