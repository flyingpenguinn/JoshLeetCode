import java.util.*;

public class BuildMatrixWithConditions {

    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
        Map<Integer, List<Integer>> rg = new HashMap<>();
        Map<Integer, List<Integer>> cg = new HashMap<>();

        for (int[] r : rowConditions) {
            int start = r[0];
            int end = r[1];
            rg.computeIfAbsent(start, t -> new ArrayList<>()).add(end);
        }
        for (int[] c : colConditions) {
            int start = c[0];
            int end = c[1];
            cg.computeIfAbsent(start, t -> new ArrayList<>()).add(end);
        }
        List<Integer> tr = toposort(rg);
        if (bad) {
            return new int[0][0];
        }
        int[] rows = new int[k + 1];
        for (int i = 0; i < tr.size(); ++i) {
            int v = tr.get(i);
            rows[v] = i;
        }
        List<Integer> tc = toposort(cg);
        if (bad) {
            return new int[0][0];
        }
        int[] cols = new int[k + 1];
        for (int i = 0; i < tr.size(); ++i) {
            int v = tc.get(i);
            cols[v] = i;
        }
        int[][] res = new int[k][k];
        for (int i = 1; i <= k; ++i) {
            int vr = rows[i];
            int vc = cols[i];
            res[vr][vc] = i;
        }
        return res;
    }

    private List<Integer> toporesult = new ArrayList<>();
    private Map<Integer, Integer> seen = new HashMap<>();

    private boolean bad = false;

    private List<Integer> toposort(Map<Integer, List<Integer>> g) {
        toporesult.clear();
        seen.clear();
        for (int k : g.keySet()) {
            if (!seen.containsKey(k)) {
                dfs(k, g);
                if (bad) {
                    return new ArrayList<>();
                }
            }
        }
        Collections.reverse(toporesult);
        return toporesult;
    }

    private void dfs(int i, Map<Integer, List<Integer>> g) {
        if (bad) {
            return;
        }
        seen.put(i, 1);
        for (int ne : g.getOrDefault(i, new ArrayList<>())) {
            int nev = seen.getOrDefault(ne, 0);
            if (nev == 1) {
                bad = true;
                break;
            } else if (nev == 2) {
                continue;
            }
            dfs(ne, g);
        }
        seen.put(i, 2);
        toporesult.add(i);
    }
}
