import base.ArrayUtils;
import javafx.scene.layout.Priority;

import java.util.*;

public class MinCostPathWithTeleports {
    public int minCost(int[][] g, int k) {
        int m = g.length;
        int n = g[0].length;
        TreeMap<Integer, Integer> rm = new TreeMap<>();
        int Max = (int) 1e9;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                list.add(g[i][j]);
            }
        }
        Collections.sort(list);
        int rank = 1;
        for (int i = 0; i < list.size(); ++i) {
            if (i == 0 || !list.get(i - 1).equals(list.get(i))) {
                rm.put(list.get(i), rank++);
            }
        }
        Map<Integer, List<int[]>> l2 = new HashMap<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int v = g[i][j];
                int cr = rm.get(v);
                l2.computeIfAbsent(cr, p -> new ArrayList<>()).add(new int[]{i, j});
            }
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> {
            if (x[3] != y[3]) {
                return Integer.compare(x[3], y[3]);
            } else {
                return Integer.compare(y[2], x[2]);
            }
        });
        int[][][] dist = new int[m][n][k + 1];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                Arrays.fill(dist[i][j], Max);
            }
        }
        dist[0][0][k] = 0;
        pq.offer(new int[]{0, 0, k, 0});
        // this must be per k as well!
        int[] maxvisitedrank = new int[k + 1];
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            //   System.out.println(Arrays.toString(top));
            int r = top[0];
            int c = top[1];
            int rk = top[2];
            int cd = top[3];
            if (r == m - 1 && c == n - 1) {
                return cd;
            }
            if (r + 1 < m && dist[r + 1][c][rk] > cd + g[r + 1][c]) {
                dist[r + 1][c][rk] = cd + g[r + 1][c];
                pq.offer(new int[]{r + 1, c, rk, cd + g[r + 1][c]});
            }
            if (c + 1 < n && dist[r][c + 1][rk] > cd + g[r][c + 1]) {
                dist[r][c + 1][rk] = cd + g[r][c + 1];
                pq.offer(new int[]{r, c + 1, rk, cd + g[r][c + 1]});
            }
            if (rk >= 1) {
                int cv = g[r][c];
                int crank = rm.get(cv);
                for (int i = maxvisitedrank[rk] + 1; i <= crank; ++i) {
                    List<int[]> cand = l2.get(i);
                    for (int[] ca : cand) {
                        int nr = ca[0];
                        int nc = ca[1];
                        if (nr == r && nc == c) {
                            continue;
                        }
                        if (dist[nr][nc][rk - 1] > cd) {
                            dist[nr][nc][rk - 1] = cd;
                            pq.offer(new int[]{nr, nc, rk - 1, cd});
                        }
                    }
                }
                maxvisitedrank[rk] = Math.max(crank, maxvisitedrank[rk]);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new MinCostPathWithTeleports().minCost(ArrayUtils.read("[[0,12,1,14,3],[8,18,18,4,8],[15,17,13,16,6],[11,16,23,15,16],[21,17,25,27,24],[26,27,21,16,23],[27,20,31,30,32]]"), 2));
    }
}
