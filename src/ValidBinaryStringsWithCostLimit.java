import java.util.*;

public class ValidBinaryStringsWithCostLimit {
    private Set<String> res = new HashSet<>();

    private void dfs(int i, int n, int k, String cur, int ones) {
        if (i == n) {
            res.add(cur);
            return;
        }
        dfs(i + 1, n, k, cur + "0", ones);
        if (ones + i <= k && (cur.isEmpty() || cur.charAt(cur.length() - 1) != '1')) {
            dfs(i + 1, n, k, cur + "1", ones + i);
        }
    }

    public List<String> generateValidStrings(int n, int k) {
        dfs(0, n, k, "", 0);
        List<String> rres = new ArrayList<>(res);
        Collections.sort(rres);
        return rres;
    }
}
