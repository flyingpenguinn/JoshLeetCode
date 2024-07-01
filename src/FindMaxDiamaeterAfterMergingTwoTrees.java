import base.ArrayUtils;

import java.util.*;

public class FindMaxDiamaeterAfterMergingTwoTrees {
    // get diameter of either tree then find the best merge
    private Set<Integer>[] t1;
    private Set<Integer>[] t2;

    public int minimumDiameterAfterMerge(int[][] edges1, int[][] edges2) {
        int en1 = edges1.length;
        int n1 = en1 + 1;
        int en2 = edges2.length;
        int n2 = en2 + 1;
        t1 = new HashSet[n1];
        t2 = new HashSet[n2];
        for (int i = 0; i < n1; ++i) {
            t1[i] = new HashSet<>();
        }
        for (int i = 0; i < n2; ++i) {
            t2[i] = new HashSet<>();
        }
        for (int[] e1 : edges1) {
            int u = e1[0];
            int v = e1[1];
            t1[u].add(v);
            t1[v].add(u);
        }
        for (int[] e2 : edges2) {
            int u = e2[0];
            int v = e2[1];
            t2[u].add(v);
            t2[v].add(u);
        }
        int diag1 = getDiameter(t1);
        int diag2 = getDiameter(t2);
        int l1 = (diag1 + 1) / 2;
        int l2 = (diag2 + 1) / 2;
        return Math.max(diag1, Math.max(diag2, l1 + l2 + 1));
    }

    private int getDiameter(Set<Integer>[] t1) {
        int[] res = bfs(t1, 0); // dist, node
        int v2 = res[1];
        res = bfs(t1, v2);
        int rt = res[0];
     //   System.out.println(v2 + " dist from 0 " + res[0]);
    //    System.out.println(res[1] + " dist from  " + v2 + " " + res[0]);
        return rt;
    }

    private int Max = (int) (1e9);

    private int[] bfs(Set<Integer>[] t, int v) {
        int n = t.length;
        Deque<Integer> q = new ArrayDeque<>();
        q.offerLast(v);
        int[] dist = new int[n];
        Arrays.fill(dist, Max);
        dist[v] = 0;
        int[] res = new int[]{0, 0};
        while (!q.isEmpty()) {
            int top = q.pollFirst();
            int cd = dist[top];
            System.out.println(top + " dist is " + cd);
            int nd = cd + 1;
            res[0] = cd;
            res[1] = top;
            for (int ne : t[top]) {
                if (dist[ne] > nd) {
               //     System.out.println("Pushing " + ne + " from " + top + " dist is " + nd);
                    dist[ne] = nd;
                    q.offerLast(ne);
                }
            }
        }
        return res;
    }

}
