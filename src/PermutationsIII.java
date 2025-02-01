import java.util.ArrayList;
import java.util.List;

public class PermutationsIII {
    private List<List<Integer>> res = new ArrayList<>();

    public int[][] permute(int n) {
        List<Integer> cur = new ArrayList<>();
        dfs(0, n, 0, cur);
        int[][] rr = new int[res.size()][n];
        for (int i = 0; i < rr.length; ++i) {
            for (int j = 0; j < n; ++j) {
                rr[i][j] = res.get(i).get(j);
            }
        }
        return rr;
    }

    private void dfs(int i, int n, int st, List<Integer> cur) {
        if (i == n) {
            res.add(new ArrayList<>(cur));
            return;
        }
        for (int cand = 1; cand <= n; ++cand) {
            if (!cur.isEmpty() && cur.get(cur.size() - 1) % 2 == cand % 2) {
                continue;
            }
            if (((st >> cand) & 1) == 1) {
                continue;
            }
            int nst = st | (1 << cand);
            cur.add(cand);
            dfs(i + 1, n, nst, cur);
            cur.remove(cur.size() - 1);
        }
    }
}
