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
    // acyclic,n-1 edges, connected, two of three can deduce the third
    // using union find. can also use dfs to check connected components
    int[] p;
    int[] sz;

    public boolean validTree(int n, int[][] es) {
        if (es.length != n - 1) {
            return false;
        }
        p = new int[n];
        sz = new int[n];
        Arrays.fill(p, -1);
        Arrays.fill(sz, 1);

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
            if (sz[pi] < sz[pj]) {
                p[pi] = pj;
                sz[pj] += sz[pi];
            } else {
                p[pj] = pi;
                sz[pi] += sz[pj];
            }
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

//connected and no circle
class GraphValidTreeCircleDetection {
    boolean hascircle = false;
    Set<Integer> v = new HashSet<>();
    Map<Integer, Set<Integer>> map = new HashMap<>();


    public boolean validTree(int n, int[][] edges) {
        for (int[] e : edges) {
            Set<Integer> cur = map.getOrDefault(e[0], new HashSet<>());
            cur.add(e[1]);
            map.put(e[0], cur);
            cur = map.getOrDefault(e[1], new HashSet<>());
            cur.add(e[0]);
            map.put(e[1], cur);
        }

        dfs(0, -1);
        return v.size() == n && !hascircle;
    }

    void dfs(int s, int parent) {
        if (hascircle) {
            return;
        }
        //   System.out.println(s);
        v.add(s);
        for (int t : map.getOrDefault(s, new HashSet<>())) {
            if (t == parent) {
                // for non directional graph we must use next != parent to rule out back edge in circle detection
                continue;
            }
            if (v.contains(t)) {
                hascircle = true;
                return;
            }
            dfs(t, s);
        }
    }
}
