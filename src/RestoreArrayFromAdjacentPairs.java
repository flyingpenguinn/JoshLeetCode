import java.util.*;

public class RestoreArrayFromAdjacentPairs {

    public int[] restoreArray(int[][] a) {
        int n = a.length;
        Map<Integer, List<Integer>> m = new HashMap<>();
        for (int[] p : a) {
            int v1 = p[0];
            int v2 = p[1];
            m.computeIfAbsent(v1, k -> new ArrayList<>()).add(v2);
            m.computeIfAbsent(v2, k -> new ArrayList<>()).add(v1);
        }
        int cur = -1;
        for (Map.Entry<Integer, List<Integer>> entry : m.entrySet()) {
            int k = entry.getKey();
            List<Integer> v = entry.getValue();
            if (v.size() == 1) {
                cur = k;
                break;
            }
        }
        int[] res = new int[n + 1];
        int ri = 0;
        int pre = (int) 1e9;
        while (ri < n + 1) {
            res[ri++] = cur;
            for (int ne : m.get(cur)) {
                if (ne == pre) {
                    continue;
                } else {
                    pre = cur;
                    cur = ne;
                    break;
                }
            }
        }
        return res;
    }

}
