import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CountNumberOfCompleteComponent {
    private HashSet<Integer>[] g;
    private int[] st;

    public int countCompleteComponents(int n, int[][] edges) {
        g = new HashSet[n];
        st = new int[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new HashSet<>();
        }
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            g[v1].add(v2);
            g[v2].add(v1);
        }
        int res = 0;
        for (int i = 0; i < n; ++i) {
            if (st[i] != 0) {
                continue;
            }
            List<Integer> nodes = new ArrayList<>();
            dfs(i, nodes);
            //    System.out.println(i+" "+nodes);

            boolean bad = false;
            for (int j = 0; j < nodes.size(); ++j) {
                if (bad) {
                    break;
                }
                int v1 = nodes.get(j);
                for (int k = j + 1; k < nodes.size(); ++k) {

                    int v2 = nodes.get(k);
                    //     System.out.println("c! "+v1+" "+v2);
                    if (!g[v1].contains(v2)) {

                        bad = true;
                        break;
                    }
                }
            }
            if (!bad) {
                ++res;
            }
        }
        return res;
    }

    private void dfs(int i, List<Integer> nodes) {
        st[i] = 1;
        nodes.add(i);
        for (int ne : g[i]) {
            if (st[ne] != 0) {
                continue;
            }
            dfs(ne, nodes);
        }
        st[i] = 2;
    }
}
