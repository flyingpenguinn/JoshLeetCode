public class MinBitwiseOrFromGrid {
    public int minimumOR(int[][] grid) {

        int mask = (1 << 20) - 1;
        for (int i = 31; i >= 0; i--) {
            int cand = mask & ~(1 << i);
            if (ok(grid, cand)) {
                mask = cand;
            }
        }
        return mask;
    }

    private boolean ok(int[][] grid, int mask) {
        int ban = ~mask;
        for (int i = 0; i < grid.length; i++) {
            boolean good = false;
            for (int j = 0; j < grid[i].length; j++) {
                if ((grid[i][j] & ban) == 0) {
                    good = true;
                    break;
                }
            }
            if (!good) {
                return false;
            }
        }
        return true;
    }
}
