import base.ArrayUtils;

import java.util.*;

public class MostSimilarPathInAGraph {

    private int[][] dp;
    private int[][] sel;

    public List<Integer> mostSimilar(int n, int[][] roads, String[] names, String[] targetPath) {

        Map<Integer, Set<Integer>> g = new HashMap<>();
        for (int[] road : roads) {
            g.computeIfAbsent(road[0], k -> new HashSet<>()).add(road[1]);
            g.computeIfAbsent(road[1], k -> new HashSet<>()).add(road[0]);
        }
        // the first choice can be anything
        for (int i = 0; i < n; i++) {
            g.computeIfAbsent(n, k -> new HashSet<>()).add(i);
        }
        dp = new int[targetPath.length][n + 1];
        sel = new int[targetPath.length][n + 1];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        int cost = dom(targetPath, 0, n, g, names);
        System.out.println(cost);
        List<Integer> path = gen(sel, 0, n, targetPath.length);
        Collections.reverse(path);
        return path;
    }

    // at ith step, standing on curNode node, what's the min edit cost, also note down our choice
    private int dom(String[] tp, int i, int curNode, Map<Integer, Set<Integer>> g, String[] names) {
        if (i == tp.length) {
            return 0;
        }
        if (dp[i][curNode] != -1) {
            return dp[i][curNode];
        }
        Set<Integer> nexts = g.getOrDefault(i, new HashSet<>());
        int min = Integer.MAX_VALUE;
        for (int ne : nexts) {
            String name = names[ne];
            int curCost = name.equals(tp[i]) ? 0 : 1;
            int laterCost = dom(tp, i + 1, ne, g, names);
            int curSum = curCost + laterCost;
            if (curSum < min) {
                sel[i][curNode] = ne;
                min = curSum;
            }
        }
        dp[i][curNode] = min;
        return min;
    }

    private List<Integer> gen(int[][] sel, int i, int curNode, int pathLen) {
        if (i == pathLen) {
            return new ArrayList<>();
        } else {
            int ne = sel[i][curNode];
            List<Integer> later = gen(sel, i + 1, ne, pathLen);
            later.add(ne);
            return later;
        }
    }

    public static void main(String[] args) {
        System.out.println(new MostSimilarPathInAGraph().mostSimilar(5, ArrayUtils.read("[[0,2],[0,3],[1,2],[1,3],[1,4],[2,4]]"), new String[]{"ATL", "PEK", "LAX", "DXB", "HND"}, new String[]{"ATL", "DXB", "HND", "LAX"}));
    }

}
