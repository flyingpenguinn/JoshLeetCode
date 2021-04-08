import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxNumAcceptedInvitations {
    public int maximumInvitations(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        List[] g = new ArrayList[m + n];
        for (int i = 0; i < m + n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 1) {
                    g[i].add(m + j);
                    g[m + j].add(i);
                }
            }
        }
        int[] matching = new int[m + n];
        boolean[] checked = new boolean[m + n];
        Arrays.fill(matching, -1);
        int res = 0;
        for (int i = 0; i < m + n; i++) {
            if (matching[i] == -1) {
                Arrays.fill(checked, false);
                if (dfs(g, i, checked, matching)) {
                    res++;
                }
            }
        }
        //   System.out.println(Arrays.toString(matching));
        return res;
    }

    private boolean dfs(List<Integer>[] g, int i, boolean[] checked, int[] matching) {

        for (int j = 0; j < g[i].size(); j++) {
            int v = g[i].get(j);
            if (!checked[v]) {
                checked[v] = true;
                if (matching[v] == -1 || dfs(g, matching[v], checked, matching)) {
                    //       System.out.println(i+" <-> "+v);
                    matching[v] = i;
                    matching[i] = v;
                    return true;
                }
            }
        }
        return false;
    }
}
