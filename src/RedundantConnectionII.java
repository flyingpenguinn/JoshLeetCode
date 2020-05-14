import base.ArrayUtils;
import crap.Crap;

import java.util.*;

/*
LC#685
In this problem, a rooted tree is a directed graph such that, there is exactly one node (the root) for which all other nodes are descendants of this node, plus every node has exactly one parent, except for the root node which has no parents.

The given input is a directed graph that started as a rooted tree with N nodes (with distinct values 1, 2, ..., N), with one additional directed edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.

The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] that represents a directed edge connecting nodes u and v, where u is a parent of child v.

Return an edge that can be removed so that the resulting graph is a rooted tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array.

Example 1:
Input: [[1,2], [1,3], [2,3]]
Output: [2,3]
Explanation: The given directed graph will be like this:
  1
 / \
v   v
2-->3
Example 2:
Input: [[1,2], [2,3], [3,4], [4,1], [1,5]]
Output: [4,1]
Explanation: The given directed graph will be like this:
5 <- 1 -> 2
     ^    |
     |    v
     4 <- 3
Note:
The size of the input 2D-array will be between 3 and 1000.
Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.
 */
public class RedundantConnectionII {
    // key is to avoid useless traversal: we when know there are 2 nodes with indgree 0, or no node has indegree 0, we dont need to do dfs
    Set<Integer>[] g;
    int[] ind;
    int n;

    public int[] findRedundantDirectedConnection(int[][] edges) {
        n = edges.length;
        initgraph(edges);
        int start = -1;
        for (int j = 1; j <= n; j++) {
            if (ind[j] == 0) {
                start = j;
                break;
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            int[] e = edges[i];
            int v1 = e[0];
            int v2 = e[1];
            g[v1].remove(v2);
            ind[v2]--;
            int oldstart = start;
            boolean bad = false;
            if (start == -1) {
                if (ind[v2] != 0) {
                    bad = true;
                } else {
                    start = v2;
                }
            } else if (ind[v2] == 0) {
                // two stats? bad
                bad = true;
            }
            if (!bad) {
                int[] status = new int[n + 1];
                if (dfs(start, -1, status)) {
                    boolean isvalid = allvisited(status);
                    if (isvalid) {
                        return edges[i];
                    }
                }
            }
            ind[v2]++;
            g[v1].add(v2);
            start = oldstart;
        }
        return null;
    }

    protected boolean allvisited(int[] status) {
        boolean r = true;
        for (int j = 1; j <= n; j++) {
            if (status[j] != 2) {
                r = false;
                break;
            }
        }
        return r;
    }

    protected void initgraph(int[][] edges) {
        ind = new int[n + 1];
        g = new HashSet[n + 1];
        for (int i = 1; i <= n; i++) {
            g[i] = new HashSet<>();
        }
        for (int i = 0; i < n; i++) {
            int[] e = edges[i];
            int v1 = e[0];
            int v2 = e[1];
            g[v1].add(v2);
            ind[v2]++;
        }
    }

    // true == no cycle
    private boolean dfs(int i, int p, int[] status) {
        status[i] = 1;
        for (int next : g[i]) {
            if (next == p) {
                // undirected graph cycle check must avoid parent
                continue;
            }
            int st = status[next];
            if (st == 1) {
                return false;
            } else if (st == 0) {
                boolean later = dfs(next, i, status);
                if (!later) {
                    return false;
                }
            }
        }
        status[i] = 2;
        return true;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new RedundantConnectionIIUnionFind().findRedundantDirectedConnection(ArrayUtils.read("[[2,1],[3,1],[4,2],[1,4]]"))));
        System.out.println(Arrays.toString(new RedundantConnectionIIUnionFind().findRedundantDirectedConnection(ArrayUtils.read("[[1,2], [2,3],[3,4],   [4,1],[1,5]]"))));
        System.out.println(Arrays.toString(new RedundantConnectionIIUnionFind().findRedundantDirectedConnection(ArrayUtils.read("[[1,2], [1,3], [2,3]]"))));
        System.out.println(Arrays.toString(new RedundantConnectionIIUnionFind().findRedundantDirectedConnection(ArrayUtils.read("[[1,2], [2,3], [5,2],[3,4],[4,5]]"))));
        System.out.println(Arrays.toString(new RedundantConnectionIIUnionFind().findRedundantDirectedConnection(ArrayUtils.read("[[1,2], [2,3], [4,1],[3,4],[1,5]]"))));

    }
}

class RedundantConnectionIIUnionFind {
    // case 1: sb has 2 parent, no cycle.
    // case 2: has cycle, everyone has one parent (a perfect cycle)
    // case 3: both 2 parents and a cycle. the cycle is on the 2-parent node. we should remove the later edge that both forms the cycle, and casues 2 parents
    // note we can't simply remove edges that makes a node 2-parent: it could be an innocent edge.
    // if we remove an innocent edge but it still forms cycle, problem is with the other edge that we didnt remove, but pointed to the same end node
    int[] p;

    public int[] findRedundantDirectedConnection(int[][] es) {
        int n = es.length;
        p = new int[n + 1];
        Arrays.fill(p, -1);
        int[] ind = new int[n + 1];
        int cand1 = -1; // first edge that makes 2 parents
        int cand2 = -1;// first edge that forms a circle
        for (int i = 0; i < n; i++) {
            int[] e = es[i];
            int vs = e[0];
            int ve = e[1];
            ind[ve]++;
            if (ind[ve] == 2) {
                cand1 = i;
                continue; // here we may happen to skip an edge that can cause a circle- in this case we won't remove innocent edge
            }
            int ps = find(vs);
            int pe = find(ve);
            if (ps == pe) {
                cand2 = i;
                continue;
            }
            p[ps] = pe; // can use merge by rank if needed
        }
        if (cand2 == -1) {
            return es[cand1]; // case 1
        } else if (cand1 == -1) {
            return es[cand2];// case 2
        } else {
            // case 3: if both 2 parent and loop happens this means we didn't remove the correct edge before:
            // removing the correct edge would have eliminated 2 parents
            int pe = es[cand1][1];
            for (int i = n - 1; i >= 0; i--) {
                if (es[i][1] == pe && i != cand1) {
                    return es[i];
                }
            }
        }
        return null;
    }

    private int find(int vs) {
        if (p[vs] == -1) {
            return vs;
        }
        int rt = find(p[vs]);
        p[vs] = rt;
        return rt;
    }
}