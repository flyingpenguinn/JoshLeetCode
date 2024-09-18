import java.util.*;

public class FindSafeWalkThroughGrid {
    // v could just be recording [r][c][h], but here using it to filter for a bigger h is even better
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public boolean findSafeWalk(List<List<Integer>> g, int h) {
        int m = g.size();
        int n = g.get(0).size();
        int[][] v = new int[m][n];
        Deque<int[]> q = new ArrayDeque<>();
        if (g.get(0).get(0) == 1) {
            --h;
        }
        q.offerLast(new int[]{0, 0, h});
        v[0][0] = h;
        while (!q.isEmpty()) {
            int[] top = q.pollFirst();
            int r = top[0];
            int c = top[1];
            int ch = top[2];
            // System.out.println(r+","+c+" ch="+ch);
            if (r == m - 1 && c == n - 1) {
                return true;
            }
            for (int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    int nh = ch;
                    if (g.get(nr).get(nc) == 1) {
                        --nh;
                    }
                    if (v[nr][nc] >= nh) {
                        continue;
                    }
                    v[nr][nc] = nh;
                    q.offerLast(new int[]{nr, nc, nh});
                }
            }
        }
        return false;
    }
}
