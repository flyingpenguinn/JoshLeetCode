import java.util.ArrayList;
import java.util.List;

public class PossibleBipartition {
    // whether it is two colorable...
    List<Integer>[] g;
    int[] color;
    boolean failed = false;

    public boolean possibleBipartition(int n, int[][] dislikes) {
        g = new ArrayList[n + 1];
        color = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] d : dislikes) {
            g[d[0]].add(d[1]);
            g[d[1]].add(d[0]);
        }
        for (int i = 1; i <= n; i++) {
            if (color[i] == 0) {
                // use 1 and 2, not 0!
                dfs(i, 1);
                if (failed) {
                    return false;
                }
            }
        }
        return true;
    }

    void dfs(int i, int c) {
        List<Integer> nexts = g[i];
        color[i] = c;
        int nc = c == 1 ? 2 : 1;
        for (Integer next : nexts) {
            if (color[next] != 0 && color[next] != nc) {
                failed = true;
                break;
            } else if (color[next] == nc) {
                continue;
            } else {

                dfs(next, nc);
                if (failed) {
                    break;
                }
            }
        }

    }
}
