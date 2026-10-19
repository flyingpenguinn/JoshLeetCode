import java.util.*;

public class FindRootColoredTree {
    // every edge is actually already forced!
    public int findColoredRoot(int[][] edges, String colors) {
        int en = edges.length;
        int n = en + 1;
        int[] indeg = new int[n];
        int[] outdeg = new int[n];
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            char c1 = colors.charAt(v1);
            char c2 = colors.charAt(v2);
            if (good(c1, c2)) {
                ++indeg[v2];
                ++outdeg[v1];
            } else if (good(c2, c1)) {
                ++indeg[v1];
                ++outdeg[v2];
            } else {
                // any edge that connects two nodes identiacl indicates bad
                return -1;
            }
        }
        int res = -1;
        for (int i = 0; i < n; ++i) {
            if (outdeg[i] > 2) {
                return -1;
            }
            if (indeg[i] >= 2) {
                return -1;
            }
            if (indeg[i] == 0) {
                if (res != -1) {
                    return -1;
                }
                res = i;
            }
        }
        return res;
    }

    private boolean good(char c1, char c2) {
        return c1 == 'R' && c2 == 'B'
                || c1 == 'W' && c2 == 'R'
                || c1 == 'B' && c2 == 'W';

    }
}
