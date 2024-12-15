import java.util.*;

/*
LC#261
Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

Example 1:

Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
Output: true
Example 2:

Input: n = 5, and edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
Output: false
Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0,1] is the same as [1,0] and thus will not appear together in edges.
 */
public class GraphValidTree {
    /*
    Two out of 3 must be satisfied
    No cycles
    Connected
    n-1 edges
     */
    private  int[] p;
    public boolean validTree(int n, int[][] es) {
        if (es.length != n - 1) {
            return false;
        }
        p = new int[n];
        Arrays.fill(p, -1);
        for (int[] e : es) {
            union(e[0], e[1]);
        }
        int r = 0;
        for (int i = 0; i < n; i++) {
            if (p[i] == -1) {
                r++;
                if (r >= 2) {
                    return false;
                }
            }
        }
        return true;
    }

    void union(int i, int j) {
        int pi = find(i);
        int pj = find(j);
        if (pi != pj) {
            p[pi] = pj;
        }
    }

    int find(int i) {
        if (p[i] == -1) {
            return i;
        }
        int rt = find(p[i]);
        p[i] = rt;
        return rt;
    }
}


class GraphValidTreeCircleDetection {
    //connected and no circle
    private Map<Integer, Set<Integer>> map = new HashMap<>();

    public boolean validTree(int n, int[][] edges) {
        for (int[] e : edges) {
            map.computeIfAbsent(e[0], k -> new HashSet<>()).add(e[1]);
            map.computeIfAbsent(e[1], k -> new HashSet<>()).add(e[0]);
        }
        int[] st = new int[n];
        boolean cycle = dfs(0, st, -1);
        if (cycle) {
            return false;
        }
        for (int i = 0; i < n; i++) {
            if (st[i] == 0) {
                return false;
            }
        }
        return true;
    }

    // return has cycle or not.
    // note when we check cycle on undirected graph, we should avoid going back to the node that we started at
    private boolean dfs(int i, int[] st, int parent) {
        st[i] = 1;
        for (int next : map.getOrDefault(i, new HashSet<>())) {
            if (next == parent) {
                continue;
            } else if (st[next] == 1) {
                return true;
            } else if (st[next] == 0) {
                boolean cycle = dfs(next, st, i);
                if (cycle) {
                    return true;
                }
            }
        }
        st[i] = 2;
        return false;
    }
}
