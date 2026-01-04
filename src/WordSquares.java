import java.util.*;

public class WordSquares {
    private List<List<String>> res = new ArrayList<>();

    public List<List<String>> wordSquares(String[] ws) {
        Arrays.sort(ws);
        int n = ws.length;
        List<String> cur = new ArrayList<>();
        Set<String> cset = new HashSet<>();
        dfs(ws, 0, cur, cset);
        return res;
    }

    private void dfs(String[] ws, int i, List<String> cur, Set<String> cset) {
        if (cur.size() == 4) {
            String top = cur.get(0);
            String left = cur.get(1);
            String right = cur.get(2);
            String bottom = cur.get(3);

            if (top.charAt(0) == left.charAt(0) && top.charAt(3) == right.charAt(0) && bottom.charAt(0) == left.charAt(3) && bottom.charAt(3) == right.charAt(3)) {
                res.add(new ArrayList<>(cur));
            }
            return;
        }
        int n = ws.length;
        for (int j = 0; j < n; ++j) {
            if (cset.contains(ws[j])) {
                continue;
            }
            cur.add(ws[j]);
            cset.add(ws[j]);
            dfs(ws, i, cur, cset);
            cur.remove(cur.size() - 1);
            cset.remove(ws[j]);
        }

    }
}
