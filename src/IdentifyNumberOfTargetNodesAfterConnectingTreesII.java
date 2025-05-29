import java.util.*;

public class IdentifyNumberOfTargetNodesAfterConnectingTreesII {
    List<Integer>[] t1;
    List<Integer>[] t2;
    int t2evens, t2odds;
    int t1evens, t1odds;
    int best2;
    int[] res;

    public int[] maxTargetNodes(int[][] edges1, int[][] edges2) {
        int n1 = edges1.length + 1;
        int n2 = edges2.length + 1;
        t1 = new ArrayList[n1];
        t2 = new ArrayList[n2];
        for (int i = 0; i < n1; i++) {
            t1[i] = new ArrayList<>();
        }
        for (int i = 0; i < n2; i++) {
            t2[i] = new ArrayList<>();
        }
        for (int[] e : edges2) {
            t2[e[0]].add(e[1]);
            t2[e[1]].add(e[0]);
        }
        for (int[] e : edges1) {
            t1[e[0]].add(e[1]);
            t1[e[1]].add(e[0]);
        }

        dfs2(0, -1, 0);
        best2 = Math.max(t2evens, t2odds);

        dfs1(0, -1, 0);
        res = new int[n1];
        dfsres(0, -1, 0);

        return res;
    }

    private void dfs2(int i, int p, int d) {
        if (d % 2 == 0) {
            t2evens++;
        } else {
            t2odds++;
        }
        for (int ne : t2[i]) {
            if (ne != p) {
                dfs2(ne, i, d + 1);
            }
        }
    }

    private void dfs1(int i, int p, int d) {
        if (d % 2 == 0) {
            t1evens++;
        } else {
            t1odds++;
        }
        for (int ne : t1[i]) {
            if (ne != p) {
                dfs1(ne, i, d + 1);
            }
        }
    }

    private void dfsres(int i, int p, int d) {
        if (d % 2 == 0) {
            res[i] = t1evens + best2;
        } else {
            res[i] = t1odds + best2;
        }
        for (int ne : t1[i]) {
            if (ne != p) {
                dfsres(ne, i, d + 1);
            }
        }
    }
}

