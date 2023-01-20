import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NonDecreasingSubsequences {
    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> findSubsequences(int[] a) {
        List<Integer> cur = new ArrayList<>();
        dfs(a, 0, cur);
        return res;
    }

    private void dfs(int[] a, int i, List<Integer> cur) {
        int n = a.length;
        if (cur.size() >= 2) {
            res.add(new ArrayList<>(cur));
        }
        Set<Integer> seen = new HashSet<>();
        for (int j = i; j < n; ++j) {
            if (seen.contains(a[j])) {
                // de-dupe. if we took 7 already in this round, don't take it again
                continue;
            }
            seen.add(a[j]);
            if (cur.isEmpty() || a[j] >= cur.get(cur.size() - 1)) {
                cur.add(a[j]);
                dfs(a, j + 1, cur);
                cur.remove(cur.size() - 1);
            }
        }
    }
}
