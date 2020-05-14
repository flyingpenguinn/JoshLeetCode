public class ColoringABorder {
    private int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private boolean inRange(int[][] board, int ni, int nj) {

        return ni >= 0 && ni < board.length && nj >= 0 && nj < board[0].length;
    }

    public int[][] colorBorder(int[][] grid, int r0, int c0, int color) {

        int old = grid[r0][c0];
        if (color == old) {
            return grid;
        }
        dfs(grid, r0, c0, old, color);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] < 0) {
                    grid[i][j] = color;
                }
            }
        }
        return grid;
    }


    private void dfs(int[][] grid, int i, int j, int old, int color) {
        // all cells >0, use < to indicate 1. borders 2. nodes we still havent finished dfs
        grid[i][j] = -old;
        boolean isborder = false;
        for (int[] d : directions) {
            int ni = i + d[0];
            int nj = j + d[1];
            boolean inrange = inRange(grid, ni, nj);
            if (inrange && grid[ni][nj] == old) {
                dfs(grid, ni, nj, old, color);
            } else if (!inrange || Math.abs(grid[ni][nj]) != old) {
                // either on border, or ninj!= old, meaning ij is on border
                isborder = true;
            }
        }
        // after dfs is done we can safely change color back. in undirected graph there is only treeedge and backedge.
        // if we are done with a dfs of a node we won't come back again
        if (!isborder) {
            grid[i][j] = old;
        }

    }

}
