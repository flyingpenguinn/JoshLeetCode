import base.ArrayUtils;

import java.util.*;

public class FindLastMarkedNodeInTree {
    // reroot again
    private List<Integer>[] t;
    private int[][] d1;
    private int[][] d2;

    // max dist from i to any node in its subtree. return dist and the node itself
    private int[] dfs(int i, int p) {
        d1[i][0] = 0;
        d1[i][1] = i;
        for (int ne : t[i]) {
            if (ne == p) {
                continue;
            }
            int[] rne = dfs(ne, i);
            final int nd = rne[0] + 1;
            if (nd > d1[i][0]) {
                d2[i][0] = d1[i][0];
                d2[i][1] = d1[i][1];
                d1[i][0] = nd;
                d1[i][1] = rne[1];
            } else if (nd > d2[i][0]) {
                d2[i][0] = nd;
                d2[i][1] = rne[1];
            }
        }
        return d1[i];
    }

    // pdist is the max dist and the node from p to any other node in the tree, and these nodes are not in the subtree of i
    private void dfs2(int i, int p, int[] pdist, int[] res) {
        if (pdist[0] > d1[i][0]) {
            res[i] = pdist[1];
        } else {
            res[i] = d1[i][1];
        }
        // for i the max dist is either from pdist or from its own subtree
        // pdist already factored in dist from p to i
        for (int ne : t[i]) {
            if (ne == p) {
                continue;
            }
            int pdistne1 = pdist[0] + 1;
            int neindex = pdist[1];
            int nedist = pdistne1;
            //for ne possibility one is from parent pdist to others not in i's subtree
            int pdistne2 = 0;

            // another is from ne go through i and go to another subtree of i. here we see which way we should go- either d1 or d2, depending on whether ne is in the subtreethat made d1
            if (d1[i][1] == d1[ne][1]) {
                // pick the dist that is not from the ne subtree
                pdistne2 = d2[i][0] + 1;
                if (pdistne2 > pdistne1) {
                    nedist = pdistne2;
                    neindex = d2[i][1];
                }
            } else {
                pdistne2 = d1[i][0] + 1;
                if (pdistne2 > pdistne1) {
                    nedist = pdistne2;
                    neindex = d1[i][1];
                }
            }
            int[] pdistne = new int[]{nedist, neindex};
            //  System.out.println(ne+" pdistne1="+pdistne1+" pdistne2="+pdistne2);
            dfs2(ne, i, pdistne, res);
        }
    }


    public int[] lastMarkedNodes(int[][] edges) {
        int n = edges.length + 1;
        t = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            t[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            t[v1].add(v2);
            t[v2].add(v1);
        }
        d1 = new int[n][2];  // max dist if we root on this node
        d2 = new int[n][2];  // 2nd max dist if we root on this

        dfs(0, -1);
        int[] res = new int[n];
        int[] initpdist = {0, 0};
        dfs2(0, -1, initpdist, res);
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new FindLastMarkedNodeInTree().lastMarkedNodes(ArrayUtils.read("[[0,1]]"))));
     //   System.out.println(Arrays.toString(new FindLastMarkedNodeInTree().lastMarkedNodes(ArrayUtils.read("[[0,1],[0,2]]"))));
       // System.out.println(Arrays.toString(new FindLastMarkedNodeInTree().lastMarkedNodes(ArrayUtils.read("[[0,1],[0,2],[2,3],[2,4]]"))));

    }
}
