import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestCycleInAGraph {
    private int res = -1;
    private int[] st;

    public int longestCycle(int[] g) {
        int n = g.length;
        st = new int[n];
        for (int i = 0; i < n; ++i) {
            if (st[i] == 0) {
                dfs(g, i, new ArrayList<>(), new HashMap<>());
            }
        }
        return res;
    }

    private void dfs(int[] g, int i, List<Integer> cur, Map<Integer, Integer> pos) {
        int n = g.length;
        cur.add(i);
        pos.put(i, cur.size() - 1);
        st[i] = 1;
        int ne = g[i];

        if (ne != -1 && st[ne] == 1) {
            //   System.out.println(cur+" "+pos);
            res = Math.max(res, cur.size() - pos.get(ne));

            cur.remove(cur.size() - 1);
            pos.remove(i);
            st[i] = 2;
            return;
        } else if (ne != -1 && st[ne] == 0) {
            dfs(g, ne, cur, pos);
        }
        cur.remove(cur.size() - 1);
        pos.remove(i);
        st[i] = 2;
    }
}
