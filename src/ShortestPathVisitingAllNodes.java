import base.ArrayUtils;

import java.util.*;

public class ShortestPathVisitingAllNodes {
    Map<BitSet, Map<Integer, Integer>> dp = new HashMap<>();

    class QueueItem {
        int node;
        BitSet status;
        int len;

        public QueueItem(int node, BitSet status, int len) {
            this.node = node;
            this.status = status;
            this.len = len;
        }

    }


    public int shortestPathLength(int[][] graph) {
        Deque<QueueItem> q = new ArrayDeque<>();
        q.offer(new QueueItem(-1, new BitSet(), 0));
        int shortest = -1;
        int n = graph.length;
        while (!q.isEmpty()) {
            QueueItem top = q.poll();
            if (top.status.cardinality() == n) {
                shortest = top.len;
                break;
            }

            int cur = top.node;
            if (cur == -1) {
                for (int i = 0; i < n; i++) {
                    BitSet bs = (BitSet) top.status.clone();
                    bs.set(i);
                    QueueItem qi = new QueueItem(i, bs, 0);
                    q.offer(qi);
                }
            } else {
                for (int k = 0; k < graph[cur].length; k++) {
                    int j = graph[cur][k];
                    if (j == cur) {
                        continue;
                    }
                    BitSet bs = (BitSet) top.status.clone();
                    bs.set(j);
                    int newLen = top.len + 1;
                    Map<Integer, Integer> subMap = dp.getOrDefault(bs, new HashMap<>());
                    Integer cached = subMap.get(j);
                    if (cached != null) {
                        if (newLen >= cached) {
                            continue;
                        } else {
                            // shoudlnt come here because it's bfs...
                            subMap.put(j, newLen);
                            dp.put(bs, subMap);
                        }
                    } else {
                        subMap.put(j, newLen);
                        dp.put(bs, subMap);
                    }
                    QueueItem qi = new QueueItem(j, bs, newLen);
                    q.offer(qi);
                }
            }
        }
        return shortest;
    }


    public static void main(String[] args) {
        //   int[][] g = ArrayUtils.readUnEven("[[2,5,7],[2,4],[0,1],[5],[5,6,1],[4,10,8,0,3],[4,9],[0],[5],[6],[5]]");
        //   int[][] g = ArrayUtils.readUnEven("[[1],[0,2,6],[1,3],[2],[5],[4,6],[1,5,7],[6]]");
        //   int[][] g = ArrayUtils.readUnEven("[[1],[0,2,4],[1,3,4],[2],[1,2]]");
        //  int[][] g = ArrayUtils.readUnEven("[[1,2,3],[0],[0],[0]]");
        int[][] g = {{}};
        System.out.println(new ShortestDpBottomUp().shortestPathLength(g));
    }

}

// slow....n3*2pn
class ShortestDpMemoization {

    Map<BitSet, Map<Integer, Integer>> dp = new HashMap<>();

    public int shortestPathLength(int[][] graph) {
        return dfs(graph, new BitSet(), -1, -1);
    }

    private int dfs(int[][] graph, BitSet bitSet, int cur, int len) {
        Map<Integer, Integer> submap = dp.getOrDefault(bitSet, new HashMap<>());
        Integer cached = submap.get(cur);
        if (cached != null) {
            return cached;
        }
        int n = graph.length;
        if (bitSet.cardinality() == n) {
            return 0;
        }
        int cad = bitSet.cardinality();
        if (len >= cad * cad) {
            return -1;
        }

        int min = Integer.MAX_VALUE;
        if (cur == -1) {
            for (int i = 0; i < n; i++) {
                BitSet bs = (BitSet) bitSet.clone();
                bs.set(i);
                int later = dfs(graph, bs, i, len + 1);
                if (later != -1) {
                    min = Math.min(min, later);
                }
            }
        } else {
            for (int k = 0; k < graph[cur].length; k++) {
                int j = graph[cur][k];
                if (j == cur) {
                    continue;
                }
                BitSet bs = (BitSet) bitSet.clone();
                bs.set(j);
                int later = dfs(graph, bs, j, len + 1);
                if (later != -1) {
                    min = Math.min(min, later + 1);
                }
            }
        }
        int result = min == Integer.MAX_VALUE ? -1 : min;
        submap.put(cur, result);
        dp.put(bitSet, submap);
        return result;
    }
}

class ShortestDpBottomUp {
    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        int allVisited = (1 << n) - 1;
        int[][] dp = new int[allVisited + 1][n];
        int limit = n * n;
        for (int i = 0; i < dp.length; i++) {
            // length is n *n at most
            Arrays.fill(dp[i], limit);
        }
        for (int i = 0; i < n; i++) {
            int state = 1 << i;
            dp[state][i] = 0;
        }

        for (int state = 0; state < allVisited; state++) {
            boolean repeat = true;
            while (repeat) {
                repeat = false;
                for (int i = 0; i < n; i++) {
                    for (int k = 0; k < graph[i].length; k++) {
                        int j = graph[i][k];
                        if (j == i) {
                            continue;
                        }
                        int disti = dp[state][i];
                        int newstate = state | (1 << j);
                        int curdistj = dp[newstate][j];
                        if (disti + 1 < curdistj) {
                            dp[newstate][j] = disti + 1;
                            if (newstate == state) {
                                // we must have enabled another node under the same state. let's try this state again
                                // to see if that node will help
                                repeat = true;
                            }
                        }
                    }
                }
            }
        }
        int min = limit;
        for (int i = 0; i < dp[allVisited].length; i++) {
            min = Math.min(min, dp[allVisited][i]);
        }
        return min;
    }
}
