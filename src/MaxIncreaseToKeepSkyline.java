public class MaxIncreaseToKeepSkyline {
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int[] maxrow = new int[grid.length];
        int[] maxcol = new int[grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            int max = -1;
            for (int j = 0; j < grid[0].length; j++) {
                max = Math.max(max, grid[i][j]);
            }
            maxrow[i] = max;
        }
        for (int j = 0; j < grid[0].length; j++) {
            int max = -1;
            for (int i = 0; i < grid.length; i++) {
                max = Math.max(max, grid[i][j]);
            }
            maxcol[j] = max;
        }
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int ideal = Math.min(maxrow[i], maxcol[j]);
                count += ideal - grid[i][j];
            }
        }
        return count;
    }

    public static void main(String[] args) {

    }
}
