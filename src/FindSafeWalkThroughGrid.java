import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FindSafeWalkThroughGrid {
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public boolean findSafeWalk(List<List<Integer>> g, int h) {
        int m = g.size();
        int n = g.get(0).size();
        int[][] v = new int[m][n];
        Queue<int[]> q = new LinkedList<>();
        v[0][0] = h;
        if (g.get(0).get(0) == 1) {
            --h;
        }
        q.add(new int[]{0, 0, h});
        while (!q.isEmpty()) {
            int[] c = q.poll();
            int x = c[0];
            int y = c[1];
            int hp = c[2];
            if (x == m - 1 && y == n - 1 && hp >= 1) {
                return true;
            }
            for (int k = 0; k < 4; k++) {
                for (int[] d : dirs) {
                    int x1 = x + d[0];
                    int y1 = y + d[1];
                    if (x1 >= 0 && x1 < m && y1 >= 0 && y1 < n) {
                        int hp1 = hp - (g.get(x1).get(y1) == 1 ? 1 : 0);
                        if (hp1 <= 0 || v[x1][y1] >= hp1) {
                            continue;
                        }
                        v[x1][y1] = hp1;
                        q.add(new int[]{x1, y1, hp1});
                    }
                }
            }
        }
        return false;
    }
}
