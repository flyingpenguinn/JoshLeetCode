import base.ArrayUtils;

import java.util.Arrays;

/*
LC#785
Given an undirected graph, return true if and only if it is bipartite.

Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B such that every edge in the graph has one node in A and another node in B.

The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.  Each node is an integer between 0 and graph.length - 1.  There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.

Example 1:
Input: [[1,3], [0,2], [1,3], [0,2]]
Output: true
Explanation:
The graph looks like this:
0----1
|    |
|    |
3----2
We can divide the vertices into two groups: {0, 2} and {1, 3}.
Example 2:
Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
Output: false
Explanation:
The graph looks like this:
0----1
| \  |
|  \ |
3----2
We cannot find a way to divide the set of nodes into two independent subsets.


Note:

graph will have length in range [1, 100].
graph[i] will contain integers in range [0, graph.length - 1].
graph[i] will not contain i or duplicate values.
The graph is undirected: if any element j is in graph[i], then i will be in graph[j].
 */
public class IsGraphBipartite {

    // label the nodes as 1 if it's an unvisited component because components are not connected with each other anyway

    public boolean isBipartite(int[][] g) {
        int n = g.length;
        int[] st = new int[n];
        for (int i = 0; i < n; i++) {
            if (st[i] == 0) {
                boolean good = dfs(i, g, st, 1);
                if (!good) {
                    return false;
                }
            }
        }
        return true;
    }

    boolean dfs(int i, int[][] g, int[] st, int color) {
        st[i] = color;
        int nextcolor = color == 1 ? 2 : 1;
        for (int j : g[i]) {
            if (st[j] == 0) {
                boolean rt = dfs(j, g, st, nextcolor);
                if (!rt) {
                    return false;
                }
            } else if (st[j] != nextcolor) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] graph = ArrayUtils.read("[[1,3],[0,2],[1,3],[0,2]]");
        System.out.println(new IsGraphBipartite().isBipartite(graph));
    }
}
