import java.util.ArrayDeque;
import java.util.Deque;


public class ShortestBridge {
    class QItem {
        int row;
        int col;
        int dist;

        public QItem(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
    }


    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int[][] visited = null;

    boolean inrange(int i, int j, int[][] a) {
        return i >= 0 && i < a.length && j >= 0 && j < a[0].length;
    }

    Deque<QItem> q = new ArrayDeque<>();

    // dfs first  to mark all 1s as 2 in one island then spfa to the other. treat 2s as having 0 distance between them
    public int shortestBridge(int[][] a) {
        q.clear();
        int rs = a.length;
        int cs = a[0].length;
        visited = new int[rs][cs];
        // change first island to 2
        boolean done = false;
        for (int i = 0; i < rs; i++) {
            for (int j = 0; j < cs; j++) {
                if (a[i][j] == 1) {
                    dfs22(i, j, a);
                    done = true;
                    break;
                }
            }
            if (done) {
                break;
            }
        }

        while (!q.isEmpty()) {
            QItem top = q.poll();
            int ti = top.row;
            int tj = top.col;
            int dst = top.dist;
            if (a[ti][tj] == 1) {
                return dst - 1;
            }
            for (int[] d : dirs) {
                int nr = ti + d[0];
                int nc = tj + d[1];
                int dist = dst + 1;
                if (inrange(nr, nc, a) && visited[nr][nc] != 1) {
                    visited[nr][nc] = 1;
                    q.offer(new QItem(nr, nc, dist));
                }
            }
        }
        return -1;
    }

    void dfs22(int i, int j, int[][] a) {

        a[i][j] = 2;
        q.offer(new QItem(i, j, 0));
        visited[i][j] = 1;
        for (int[] d : dirs) {
            int nr = i + d[0];
            int nc = j + d[1];
            if (inrange(nr, nc, a) && a[nr][nc] == 1 && visited[nr][nc] != 1) {
                dfs22(nr, nc, a);
            }
        }
    }
}
