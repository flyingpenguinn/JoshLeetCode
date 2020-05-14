import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
LC#1059
Given the edges of a directed graph, and two nodes source and destination of this graph, determine whether or not all paths starting from source eventually end at destination, that is:

At least one path exists from the source node to the destination node
If a path exists from the source node to a node with no outgoing edges, then that node is equal to destination.
The number of possible paths from source to destination is a finite number.
Return true if and only if all roads from source lead to destination.



Example 1:



Input: n = 3, edges = [[0,1],[0,2]], source = 0, destination = 2
Output: false
Explanation: It is possible to reach and get stuck on both node 1 and node 2.
Example 2:



Input: n = 4, edges = [[0,1],[0,3],[1,2],[2,1]], source = 0, destination = 3
Output: false
Explanation: We have two possibilities: to end at node 3, or to loop over node 1 and node 2 indefinitely.
Example 3:



Input: n = 4, edges = [[0,1],[0,2],[1,3],[2,3]], source = 0, destination = 3
Output: true
Example 4:



Input: n = 3, edges = [[0,1],[1,1],[1,2]], source = 0, destination = 2
Output: false
Explanation: All paths from the source node end at the destination node, but there are an infinite number of paths, such as 0-1-2, 0-1-1-2, 0-1-1-1-2, 0-1-1-1-1-2, and so on.
Example 5:



Input: n = 2, edges = [[0,1],[1,1]], source = 0, destination = 1
Output: false
Explanation: There is infinite self-loop at destination node.


Note:

The given graph may have self loops and parallel edges.
The number of nodes n in the graph is between 1 and 10000
The number of edges in the graph is between 0 and 10000
0 <= edges.length <= 10000
edges[i].length == 2
0 <= source <= n - 1
0 <= destination <= n - 1
 */
public class AllpathsSourceToDestination {
    boolean reached = false;
    boolean hascycle = false;
    boolean stuck = false;

    Map<Integer, Set<Integer>> graph = new HashMap<>();

    Map<Integer, Integer> status = new HashMap<>();

    public boolean leadsToDestination(int n, int[][] edges, int s, int t) {
        build(edges);
        dfs(s, t);
        return good();
    }

    boolean good() {
        return reached && !bad();
    }

    boolean bad() {
        return hascycle || stuck;
    }

    void build(int[][] e) {
        for (int[] ei : e) {
            graph.computeIfAbsent(ei[0], k -> new HashSet<>()).add(ei[1]);
        }
    }

    void dfs(int s, int t) {
        if (s == t) {
            // need to check loop on t
            reached = true;
        }
        status.put(s, 1);
        Set<Integer> next = graph.getOrDefault(s, new HashSet<>());
        if (next.isEmpty() && s != t) {
            stuck = true;
            return;
        }
        for (int ni : next) {
            int st = status.getOrDefault(ni, 0);
            if (st == 1) {
                // note even if the loop is not on s-->t its counted
                hascycle = true;
                return;
            }
            if (st == 0) {
                dfs(ni, t);
            }
            // checkef st==2 nodes before dont need to do again
            if (bad()) {
                return;
            }
        }
        status.put(s, 2);
    }
}
