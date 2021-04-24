/*
LC#1192
There are n servers numbered from 0 to n-1 connected by undirected server-to-server connections forming a network where connections[i] = [a, b] represents a connection between servers a and b. Any server can reach any other server directly or indirectly through the network.

A critical connection is a connection that, if removed, will make some server unable to reach some other server.

Return all critical connections in the network in any order.



Example 1:



Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
Output: [[1,3]]
Explanation: [[3,1]] is also accepted.


Constraints:

1 <= n <= 10^5
n-1 <= connections.length <= 10^5
connections[i][0] != connections[i][1]
There are no repeated connections.
 */

import base.ArrayUtils;

import java.io.*;
import java.util.*;

/*
LC#1192
There are n servers numbered from 0 to n-1 connected by undirected server-to-server connections forming a network where connections[i] = [a, b] represents a connection between servers a and b. Any server can reach any other server directly or indirectly through the network.

A critical connection is a connection that, if removed, will make some server unable to reach some other server.

Return all critical connections in the network in any order.



Example 1:



Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
Output: [[1,3]]
Explanation: [[3,1]] is also accepted.


Constraints:

1 <= n <= 10^5
n-1 <= connections.length <= 10^5
connections[i][0] != connections[i][1]
There are no repeated connections.
 */
public class CriticalConnectionInNetwork {
    // textbook critical edge. low[i] = Math.min(index[i], low[u] where u is i's unvisited children, index[v] where v is a backedge)
    // https://cp-algorithms.com/graph/bridge-searching.html
    // for articulation vertex, need to edge the root too to see if it has more than one children- if so it's also articulation vertex.
    //https://cp-algorithms.com/graph/cutpoints.html
    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> es) {
        List<Integer>[] g = new ArrayList[n];
        int[] low = new int[n];
        int[] index = new int[n];
        // lowest num this point and its subtree can get to
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
            index[i] = -1;
        }
        for (List<Integer> e : es) {
            g[e.get(0)].add(e.get(1));
            g[e.get(1)].add(e.get(0));
        }
        dfs(0, g, low, -1, index);
        return res;
    }

    private int cur = 0;

    private void dfs(int i, List<Integer>[] g, int[] low, int pa, int[] index) {
        index[i] = low[i] = cur;
        List<Integer> next = g[i];
        for (int ne : next) {
            if (index[ne] == -1) {
                cur++;
                dfs(ne, g, low, i, index);
                if (low[ne] > index[i]) {
                    res.add(List.of(i, ne));
                }
                low[i] = Math.min(low[i], low[ne]);
            } else if (ne != pa) {
                // we are trying to see if we can take this edge out!
                low[i] = Math.min(low[i], index[ne]);
            }
        }
    }
}

class AriculationPoint {
    int clock = 0;
    int[] low; // low[u] is the minimum pre number u and its children in dfs tree can get to
    int[] pre;
    boolean[] seen;
    boolean[] isc;


    public boolean[] articulationPoints(int n, List<List<Integer>> connections) {
        low = new int[n];
        pre = new int[n];
        seen = new boolean[n];
        isc = new boolean[n];
        List<Integer>[] g = buildgraph(n, connections);
        dfs(0, g, -1);
        return isc;
    }

    private List<Integer>[] buildgraph(int n, List<List<Integer>> connections) {
        List<Integer>[] g = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        for (List<Integer> e : connections) {
            g[e.get(0)].add(e.get(1));
            g[e.get(1)].add(e.get(0));
        }
        return g;
    }

    private void dfs(int u, List<Integer>[] g, int parent) {
        pre[u] = clock++;
        low[u] = pre[u];
        seen[u] = true;
        int children = 0;
        for (int v : g[u]) {
            if (!seen[v]) {
                children++;
                // tree edge
                dfs(v, g, u);
                low[u] = Math.min(low[u], low[v]);
                if (low[v] >= pre[u]) {
                    // >= for articulation vertex ( critical node) , > for bridge
                    isc[u] = true;
                }
            } else if (v != parent) {
                // back edge, update low u to reflect this
                // note have to do != parent
                low[u] = Math.min(low[u], pre[v]);
            }
        }
        // special check for the root. if child = 1 we mark it not articulation point
        if (parent == -1 && children == 1) {
            isc[u] = false;
        }
    }
}
