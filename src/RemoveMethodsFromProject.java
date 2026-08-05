import java.util.*;

public class RemoveMethodsFromProject {
    private List<Integer>[] g;

    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        g = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new ArrayList<>();
        }
        for (int[] e : invocations) {
            int v1 = e[0];
            int v2 = e[1];
            g[v1].add(v2);
        }
        Set<Integer> nodes = new HashSet<>();
        dfs(k, nodes);
        boolean bad = false;
        for (int[] e : invocations) {
            int v1 = e[0];
            int v2 = e[1];
            if (!nodes.contains(v1) && nodes.contains(v2)) {
                bad = true;
                break;
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (!bad && nodes.contains(i)) {
                continue;
            }
            res.add(i);
        }
        return res;
    }

    private void dfs(int k, Set<Integer> nodes) {
        nodes.add(k);
        for (int ne : g[k]) {
            if (nodes.contains(ne)) {
                continue;
            }
            dfs(ne, nodes);
        }
    }
}
