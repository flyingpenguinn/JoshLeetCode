import java.util.*;

public class GridTeleportTraversal {
    // shortest path with the twist that for a given letter if we ever used null it. it's correct because we are generating dist greedily
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
        PriorityQueue<int[]> pq = new PriorityQueue<>((x,y)-> Integer.compare(x[2], y[2]));
        dist[0][0] = 0;
        pq.offer(new int[]{0, 0, 0});

        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int i = top[0];
            int j = top[1];
            int cd = top[2];
            if (i == m - 1 && j == n - 1) {
                return cd;
            }
            char c = a[i].charAt(j);
            int cind = c-'A';
            if (Character.isUpperCase(c)) {
                List<int[]> list = jumps[cind];
                if (!list.isEmpty()) {
                    for (int[] q : list) {
                        int ni = q[0];
                        int nj = q[1];
                        if (dist[ni][nj] > cd) {
                            dist[ni][nj] = cd;
                            pq.offer(new int[]{ni, nj, cd});
                        }
                    }
                    jumps[cind] = new ArrayList<>();
                }
            }
            for (int[] dir : dirs) {
                int ni = i + dir[0];
                int nj = j + dir[1];
                int nd = cd+1;
                if (ni >= 0 && ni < m && nj >= 0 && nj < n && a[ni].charAt(nj) != '#' && dist[ni][nj] > nd) {
                    dist[ni][nj] = nd;
                    pq.offer(new int[]{ni, nj, nd});
                }
            }
        }
        return -1;
    }
}
