import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinEdgeTogglesOnTree {
    private List<int[]>[] tree;
    private int[] d;
    private List<Integer> res = new ArrayList<>();

    public List<Integer> minimumFlips(int n, int[][] edges, String s, String t) {
        d = new int[n];

        for (int i = 0; i < n; ++i) {
            int sind = s.charAt(i) - '0';
            int tind = t.charAt(i) - '0';
            d[i] = sind ^ tind;
        }
        tree = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            tree[i] = new ArrayList<>();
        }
        int en = edges.length;
        for (int i = 0; i < en; ++i) {

            int v1 = edges[i][0];
            int v2 = edges[i][1];
            tree[v1].add(new int[]{v2, i});
            tree[v2].add(new int[]{v1, i});
        }
        int flip = dfs(0, -1);
        if (flip == 1) {
            return List.of(-1);
        }
        Collections.sort(res);
        return res;
    }

    private int dfs(int i, int p) {
        int xorall = 0;
        for (int[] nes : tree[i]) {
            int ne = nes[0];
            if (ne == p) {
                continue;
            }
            int needhelp = dfs(ne, i);
            if (needhelp == 1) {
                res.add(nes[1]);
                xorall ^= 1;
            }

        }
        if (xorall != d[i]) {
            return 1;
        } else {
            return 0;
        }
    }
}
