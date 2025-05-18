import java.util.*;

public class GridTeleportTraversal {
    // 01 BFS for any "teleport" question
    private int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int minMoves(String[] a) {
        int m = a.length;
        int n = a[0].length();
        int Max = (int) (1e9 + 7);
        List<int[]>[] jumps = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            jumps[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = a[i].charAt(j);
                int cind = c - 'A';
                if (Character.isUpperCase(c)) {
                    jumps[cind].add(new int[]{i, j});
                }
            }
        }
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dist[i], Max);
        }
        Deque<int[]> dq = new ArrayDeque<>();
        dist[0][0] = 0;
        dq.add(new int[]{0, 0});

        while (!dq.isEmpty()) {
            int[] top = dq.poll();
            int i = top[0];
            int j = top[1];
            int cd = dist[i][j];
            if (i == m - 1 && j == n - 1) {
                return cd;
            }
            char c = a[i].charAt(j);
            int cind = c - 'A';
            if (Character.isUpperCase(c)) {
                List<int[]> list = jumps[cind];
                if (!list.isEmpty()) {
                    for (int[] q : list) {
                        int ni = q[0];
                        int nj = q[1];
                        if (dist[ni][nj] > cd) {
                            dist[ni][nj] = cd;
                            dq.addFirst(new int[]{ni, nj});
                        }
                    }
                    jumps[cind] = new ArrayList<>();  // key
                }
            }
            for (int[] dir : dirs) {
                int ni = i + dir[0];
                int nj = j + dir[1];
                int nd = cd + 1;
                if (ni >= 0 && ni < m && nj >= 0 && nj < n && a[ni].charAt(nj) != '#' && dist[ni][nj] > nd) {
                    dist[ni][nj] = nd;
                    dq.addLast(new int[]{ni, nj});
                }
            }
        }
        return -1;
    }
}
