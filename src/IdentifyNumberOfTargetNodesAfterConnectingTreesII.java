import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class IdentifyNumberOfTargetNodesAfterConnectingTreesII {
    private Map<Integer, ArrayList<Integer>> t1 = new HashMap<>();
    private Map<Integer, ArrayList<Integer>> t2 = new HashMap<>();
    private int t2offset = 10000;

    public int[] maxTargetNodes(int[][] edges1, int[][] edges2) {
        int n = edges1.length + 1;
        for (int[] e : edges1) {
            int v1 = e[0];
            int v2 = e[1];
            t1.computeIfAbsent(v1, p -> new ArrayList<>()).add(v2);
            t1.computeIfAbsent(v2, p -> new ArrayList<>()).add(v1);
        }
        for (int[] e : edges2) {
            int v1 = t2offset + e[0];
            int v2 = t2offset + e[1];
            t2.computeIfAbsent(v1, p -> new ArrayList<>()).add(v2);
            t2.computeIfAbsent(v2, p -> new ArrayList<>()).add(v1);
        }
        int r21 = dfs(t2, t2offset + 0, -1, 0, 1);
        int any = t2.get(t2offset + 0).iterator().next();
        int r22 = dfs(t2, any, -1, 0, 1);
        int maxr2 = Math.max(r21, r22);
        int r11 = dfs(t1, 0, -1, 0, 0);
        int[] res = new int[n];
        Arrays.fill(res, maxr2);
        dfs2(t1, 0, -1, 0, r11, n, res);
        return res;
    }

    private void dfs2(Map<Integer, ArrayList<Integer>> t1, int i, int p, int mod, int r1, int n, int[] res) {
        if (mod == 0) {
            res[i] += r1;
        } else {
            res[i] += n - r1;
        }
        for (int ne : t1.getOrDefault(i, new ArrayList<>())) {
            if (ne == p) {
                continue;
            }
            dfs2(t1, ne, i, mod ^ 1, r1, n, res);
        }
    }

    private int dfs(Map<Integer, ArrayList<Integer>> t2, int i, int p, int mod, int expected) {
        int res = 0;
        if (mod == expected) {
            ++res;
        }
        for (int ne : t2.getOrDefault(i, new ArrayList<>())) {
            if (ne == p) {
                continue;
            }
            int later = dfs(t2, ne, i, mod ^ 1, expected);
            res += later;
        }
        return res;
    }
}
