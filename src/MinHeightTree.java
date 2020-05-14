import base.ArrayUtils;

import java.util.*;

/*
LC#310
For an undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given such a graph, write a function to find all the MHTs and return a list of their root labels.

Format
The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected edges (each edge is a pair of labels).

You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

Example 1 :

Input: n = 4, edges = [[1, 0], [1, 2], [1, 3]]

        0
        |
        1
       / \
      2   3

Output: [1]
Example 2 :

Input: n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

     0  1  2
      \ | /
        3
        |
        4
        |
        5

Output: [3, 4]
Note:

According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”
The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
 */
public class MinHeightTree {
    // get one leaf by bfs on one random node
    // get the other end by bfs on that leaf above
    // during bfs we keep the parent link in an array so that we can back out the root quickly. no need to keep a list

    int[] pa;

    public List<Integer> findMinHeightTrees(int n, int[][] es) {
        if (n == 0) {
            return new ArrayList<>();
        }
        pa = new int[n];
        Arrays.fill(pa, -1);
        List<Integer>[] g = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] e : es) {
            g[e[0]].add(e[1]);
            g[e[1]].add(e[0]);
        }
        int[] l1 = bfs(0, g);
        Arrays.fill(pa, -1);
        int[] l2 = bfs(l1[0], g);

        int pn = l2[1] + 1;
        int cur = l2[0];
        int cc = 0;
        List<Integer> r = new ArrayList<>();
        // uss pa in last step! only one path between any two nodes
        while (true) {
            cc++;
            if (pn % 2 == 1 && cc == (pn + 1) / 2) {
                r.add(cur);
                break;
            }
            if (pn % 2 == 0 && cc == pn / 2) {
                r.add(cur);
            }
            if (pn % 2 == 0 && cc == pn / 2 + 1) {
                r.add(cur);
                break;
            }
            cur = pa[cur];
        }
        return r;
    }

    int[] bfs(int i, List<Integer>[] g) {
        int[] last = null;
        // node,dist,parent
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{i, 0});
        while (!q.isEmpty()) {
            int[] top = q.poll();
            last = top;
            for (int next : g[top[0]]) {
                if (next != pa[top[0]]) {
                    pa[next] = top[0];
                    q.offer(new int[]{next, top[1] + 1});
                }
            }
        }
        return last;
    }
}
