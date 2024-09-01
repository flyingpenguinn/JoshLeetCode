import java.util.*;

public class SelectCellsInGridWithMaxScore {
    // choose numbers, not columns !
    private HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();

    public int maxScore(List<List<Integer>> g) {
        int[][] a = new int[g.size()][];
        for (int i = 0; i < g.size(); i++) {
            a[i] = g.get(i).stream().mapToInt(Integer::intValue).toArray();
        }
        int m = a.length;
        int n = a[0].length;
        Set<Integer> all = new HashSet<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                all.add(a[i][j]);
                map.computeIfAbsent(a[i][j], p -> new ArrayList<>()).add(i);
            }
        }
        List<Integer> uniq = new ArrayList<>(all);
        Collections.sort(uniq, Collections.reverseOrder());
        dp = new int[all.size()][(1 << m)];
        for (int i = 0; i < dp.length; ++i) {
            Arrays.fill(dp[i], -1);
        }
        return solve(uniq, 0, 0);
    }

    private int[][] dp;

    private int solve(List<Integer> a, int i, int st) {
        int n = a.size();
        if (i == n) {
            return 0;
        }
        if (dp[i][st] != -1) {
            return dp[i][st];
        }
        int res = solve(a, i + 1, st);
        ArrayList<Integer> rows = map.getOrDefault(a.get(i), new ArrayList<>());
        for (int r : rows) {
            if (((st >> r) & 1) == 1) {
                continue;
            }
            int nst = st | (1 << r);
            int cur = a.get(i) + solve(a, i + 1, nst);
            res = Math.max(res, cur);
        }
        dp[i][st] = res;
        return res;
    }
}
