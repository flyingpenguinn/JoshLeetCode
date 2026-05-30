import java.util.ArrayList;
import java.util.List;

public class TimeTakenToMarkAllNodes {
    /*
Rerooting template: max contribution from every possible root.

Core rule:
All DP values should be measured relative to the node that owns/receives them.
Avoid absolute DFS-time values and offset subtraction.

DFS1: compute downward values.
max1[u] = largest contribution from u into one child subtree
max2[u] = second largest contribution from u into one child subtree

A child v contributes to u as:
    edge/node cost from u to v + down[v]

DFS2: pass upper/outside value.
upper = best contribution reaching u from outside u's subtree,
        already measured relative to u.

When moving from u to child v:
    outside for v can come from:
      1) upper side of u
      2) sibling subtrees of v through u

To get sibling-side contribution, use u's best downward contribution
excluding v's own contribution:
    other = (v contributed max1[u]) ? max2[u] : max1[u]

Then convert from u-relative to v-relative by adding the cost from v to u:
    nextUpper = cost(u) + max(upper, other)

Final answer:
    ans[u] = max(down[u], upper)

 template:
   dfs1(u):
    compute top downward contributions from children

dfs2(u, upper):
    ans[u] = combine(down[u], upper)

    for child v:
        other = best downward contribution of u excluding v
        nextUpper = cost(parent side from v to u)
                    + combine(upper, other)
        dfs2(v, nextUpper)
*/

    private List<Integer>[] t;

    private int[] max1;
    private int[] max2;
    private int[] res;

    public int[] timeTaken(int[][] edges) {
        int en = edges.length;
        int n = en + 1;
        max1 = new int[n];
        max2 = new int[n];
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
        dfs1(0, -1);
        res = new int[n];

        dfs2(0, -1, 0);

        return res;
    }

    private void dfs2(int i, int p, int upper) {

        res[i] = Math.max(upper, max1[i]);
        for (int ne : t[i]) {
            if (ne == p) {
                continue;
            }

            int necost = (ne % 2 == 0 ? 2 : 1);
            int nepeak = max1[ne] + necost;
            int other = 0;
            if (nepeak == max1[i]) {
                other = max2[i];
            } else {
                other = max1[i];
            }
            int delta = 0;
            if (i % 2 == 0) {
                delta = 2;
            } else {
                delta = 1;
            }
            int maxother = other + delta;
            int maxupper = upper + delta;

            dfs2(ne, i, Math.max(maxupper, maxother));
        }
    }


    private int dfs1(int i, int p) {
        max1[i] = 0;
        max2[i] = 0;
        for (int ne : t[i]) {
            if (ne == p) {
                continue;
            }
            int neres = 0;
            neres = dfs1(ne, i);
            if (ne % 2 == 1) {
                neres += 1;
            } else {
                neres += 2;
            }
            if (neres > max1[i]) {
                max2[i] = max1[i];
                max1[i] = neres;
            } else if (neres > max2[i]) {
                max2[i] = neres;
            }
        }
        return max1[i];
    }
}


