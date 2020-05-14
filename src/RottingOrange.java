import java.util.ArrayDeque;
import java.util.Deque;

public class RottingOrange {

    // bfs, or spfa, they are similar
    // @SILU cant dp like 01 matrix because there are empty cells. path could be zigzag. in 01 matrix it's always straight line oe 2 such lines
    // at right angle
    class QueueItem {
        int row;
        int col;

        public QueueItem(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private int[][] directions = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    public int orangesRotting(int[][] grid) {
        int[][] dist = new int[grid.length][grid[0].length];
        Deque<QueueItem> q = new ArrayDeque<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    dist[i][j] = Integer.MAX_VALUE;
                }
                if (grid[i][j] == 2) {
                    q.offer(new QueueItem(i, j));
                }
            }
        }

        while (!q.isEmpty()) {
            QueueItem top = q.poll();
            for (int[] d : directions) {
                int nr = top.row + d[0];
                int nc = top.col + d[1];
                if (inRange(nr, nc, grid) && grid[nr][nc] == 1) {
                    // we can also do spfa here by comparing dist[nr][nc] with top+1, but because it's unweighted graph bfs is enough
                    // nodes closer to 0 are enqueued and dequeued earlier anyway
                    grid[nr][nc] = 2;
                    dist[nr][nc] = dist[top.row][top.col] + 1;
                    q.offer(new QueueItem(nr, nc));
                }
            }
        }

        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (dist[i][j] == Integer.MAX_VALUE) {
                    return -1;
                }
                max = Math.max(dist[i][j], max);
            }
        }
        return max; // max shortest path
    }

    private boolean inRange(int nr, int nc, int[][] grid) {
        return nr >= 0 && nr < grid.length && nc >= 0 && nc < grid[0].length;
    }
}
