import java.util.*;

public class RestoreArrayFromAdjacentPairs {
    // head/tail has degree 1 hence easy...
    public int[] restoreArray(int[][] ps) {
        int n = ps.length;
        int rn = n + 1;
        Set<Integer> seen = new HashSet<>();
        Map<Integer, List<Integer>> m = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int e1 = ps[i][0];
            int e2 = ps[i][1];
            addorremove(e1, seen);
            addorremove(e2, seen);
            m.computeIfAbsent(e1, k -> new ArrayList<>()).add(e2);
            m.computeIfAbsent(e2, k -> new ArrayList<>()).add(e1);
        }
        int start = seen.iterator().next();
        //    System.out.println(start);
        List<Integer> list = new ArrayList<>();
        seen.clear();
        dfs(start, m, list, seen);
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    private void dfs(int i, Map<Integer, List<Integer>> m, List<Integer> res, Set<Integer> seen) {
        seen.add(i);
        res.add(i);
        List<Integer> nexts = m.get(i);
        for (int ne : nexts) {
            if (!seen.contains(ne)) {
                //    System.out.println(i+"->"+ne);
                dfs(ne, m, res, seen);
            }
        }
    }

    private void addorremove(int e, Set<Integer> seen) {
        if (seen.contains(e)) {
            seen.remove(e);
        } else {
            seen.add(e);
        }
    }
}
