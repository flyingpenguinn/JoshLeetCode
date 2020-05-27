import java.util.ArrayList;
import java.util.List;

public class PossibleBipartition {
    // whether it is two colorable...
    int[] st;
    boolean good = true;
    List<Integer>[] g;

    public boolean possibleBipartition(int n, int[][] edges) {
        g = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            g[e[0]].add(e[1]);
            g[e[1]].add(e[0]);
        }
        st = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (st[i] == 0) {
                dfs(i, 1);
            }
        }
        return good;
    }

    void dfs(int i, int color) {
        st[i] = color;
        int nc = color == 1 ? 2 : 1;
        for (int next : g[i]) {
            if (!good) {
                return;
            }
            if (st[next] == nc) {
                // already colored the same one, do nothing
                continue;
            } else if (st[next] == 0) {
                // not colored yet
                dfs(next, nc);
            } else {
                // wrong color
                good = false;
                return;
            }
        }
    }
}
