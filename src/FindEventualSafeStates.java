import java.util.ArrayList;
import java.util.List;

/*
LC#802
In a directed graph, we start at some node and every turn, walk along a directed edge of the graph.  If we reach a node that is terminal (that is, it has no outgoing directed edges), we stop.

Now, say our starting node is eventually safe if and only if we must eventually walk to a terminal node.  More specifically, there exists a natural number K so that for any choice of where to walk, we must have stopped at a terminal node in less than K steps.

Which nodes are eventually safe?  Return them as an array in sorted order.

The directed graph has N nodes with labels 0, 1, ..., N-1, where N is the length of graph.  The graph is given in the following form: graph[i] is a list of labels j such that (i, j) is a directed edge of the graph.

Example:
Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
Output: [2,4,5,6]
Here is a diagram of the above graph.

Illustration of graph

Note:

graph will have length at most 10000.
The number of edges in the graph will not exceed 32000.
Each graph[i] will be a sorted list of different integers, chosen within the range [0, graph.length - 1].
 */
public class FindEventualSafeStates {
    int[] dp;

    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        dp = new int[n];
        for (int i = 0; i < n; i++) {
            if (dp[i] == 0) {
                dfs(i, graph);
            }
        }
        List<Integer> r = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (dp[i] == 1) {
                r.add(i);
            }
        }
        return r;
    }

    private void dfs(int i, int[][] graph) {
        dp[i] = 3;
        for (int j : graph[i]) {
            if (dp[j] == 3) {
                dp[i] = 2;
                return;
            } else if (dp[j] == 0) {
                dfs(j, graph);
            }
            if (dp[j] == 2) {
                // if one non terminal it's non terminal
                dp[i] = 2;
                return;
            }
        }
        dp[i] = 1;
    }
}
