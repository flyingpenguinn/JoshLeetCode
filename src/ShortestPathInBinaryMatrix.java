import base.ArrayUtils;

import java.util.ArrayDeque;
import java.util.Deque;

public class ShortestPathInBinaryMatrix {
    class Pos {
        int i;
        int j;
        int dist;

        public Pos(int i, int j, int dist) {
            this.i = i;
            this.j = j;
            this.dist = dist;
        }
    }

    // bfs, can't dp
    public int shortestPathBinaryMatrix(int[][] grid) {
        int r = grid.length;
        if (r == 0) {
            return 0;
        }

        int c = grid[0].length;
        if (grid[0][0] == 1) {
            return -1;
        }
        Deque<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(0, 0, 1));
        while (!q.isEmpty()) {
            Pos top = q.pop();
            if (top.i == r - 1 && top.j == c - 1) {
                return top.dist;
            }
            for (int[] d : dirs) {
                int nr = top.i + d[0];
                int nc = top.j + d[1];
                if (inRange(nr, nc, grid) && grid[nr][nc] == 0) {
                    grid[nr][nc] = 2;
                    q.offer(new Pos(nr, nc, top.dist + 1));
                }
            }
        }
        return -1;
    }

    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

    boolean inRange(int r, int c, int[][] m) {
        return r >= 0 && r < m.length && c >= 0 && c < m[0].length;
    }

    public static void main(String[] args) {
        System.out.println(new ShortestPathInBinaryMatrix().shortestPathBinaryMatrix(ArrayUtils.read("[[0,0,0],[1,1,0],[1,1,1]]")));
    }
}
