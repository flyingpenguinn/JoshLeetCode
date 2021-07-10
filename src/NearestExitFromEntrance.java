import java.util.ArrayDeque;
import java.util.Deque;

public class NearestExitFromEntrance {
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int nearestExit(char[][] maze, int[] e) {
        Deque<int[]> dq = new ArrayDeque<>();
        int m = maze.length;
        int n = maze[0].length;
        dq.offer(new int[]{e[0], e[1], 0});
        maze[e[0]][e[1]] = '-';
        while (!dq.isEmpty()) {
            int[] top = dq.poll();
            //    System.out.println(Arrays.toString(top));
            int x = top[0];
            int y = top[1];
            int cd = top[2];
            if (!(x == e[0] && y == e[1]) && (x == m - 1 || y == n - 1 || x == 0 || y == 0)) {
                return cd;
            }
            for (int[] d : dirs) {
                int nx = x + d[0];
                int ny = y + d[1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && maze[nx][ny] == '.') {
                    maze[nx][ny] = '-';
                    dq.offer(new int[]{nx, ny, cd + 1});
                }
            }
        }
        return -1;
    }
}
