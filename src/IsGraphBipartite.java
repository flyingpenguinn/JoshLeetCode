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
    // label the nodes as 0 if it's an unvisited component because components are not connected with each other anyway
    public boolean isBipartite(int[][] g) {
        int n = g.length;
        int[] cs = new int[n];
        Arrays.fill(cs, -1);
        for (int i = 0; i < n; i++) {
            if (cs[i] == -1) {
                if (!dfs(g, i, 0, cs)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean dfs(int[][] g, int i, int cc, int[] cs) {
        if (cs[i] != -1) {
            if (cs[i] != cc) {
                return false;
            } else {
                return true;
            }
        }
        cs[i] = cc;
        for (int ne : g[i]) {
            if (!dfs(g, ne, cc ^ 1, cs)) {
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
