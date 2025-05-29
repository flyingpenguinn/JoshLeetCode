import base.ArrayUtils;

import java.util.*;

public class SeqentialGridPathCover {
    private List<List<Integer>> res = new ArrayList<>();
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public List<List<Integer>> findPath(int[][] g, int k) {
        int m = g.length;
        int n = g[0].length;

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                List<List<Integer>> cur = new ArrayList<>();
                Set<List<Integer>> seen = new HashSet<>();
                boolean good = dfs(g, i, j, cur, seen, 1, k);
                if (good) {
                    return res;
                }
            }
        }
        return List.of();
    }

    private boolean dfs(int[][] g, int i, int j, List<List<Integer>> cur, Set<List<Integer>> seen, int t, int k) {
        int m = g.length;
        int n = g[0].length;

        final List<Integer> cpos = List.of(i, j);
        if (g[i][j] > 0) {
            if (g[i][j] != t) {
                return false;
            } else {
                ++t;
            }
        }
        cur.add(cpos);
        seen.add(cpos);
        if (cur.size() == m * n && t == k + 1) {
            res.addAll(cur);
            return true;
        }

        for (int[] d : dirs) {
            int ni = i + d[0];
            int nj = j + d[1];
            if (ni >= 0 && ni < m && nj >= 0 && nj < n) {
                if (seen.contains(List.of(ni, nj))) {
                    continue;
                }
                boolean later = dfs(g, ni, nj, cur, seen, t, k);
                if (later) {
                    return true;
                }
            }
        }
        cur.remove(cur.size() - 1);
        seen.remove(cpos);
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new SeqentialGridPathCover().findPath(ArrayUtils.read("[[0,0,0],[0,1,2]]"), 2));
    }
}
