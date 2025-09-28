import java.util.*;

public class MaximizeAlternatingSumUsingSwaps {
    private int find(int[] pa, int i) {
        if (pa[i] == i) {
            return i;
        }
        int rt = find(pa, pa[i]);
        pa[i] = rt;
        return rt;
    }

    private void union(int[] pa, int i, int j) {
        int ai = find(pa, i);
        int aj = find(pa, j);
        if (ai == aj) {
            return;
        }
        pa[ai] = aj;
    }

    public long maxAlternatingSum(int[] a, int[][] swaps) {
        int n = a.length;
        int[] pa = new int[n];
        for (int i = 0; i < n; ++i) {
            pa[i] = i;
        }
        for (int[] s : swaps) {
            int v1 = s[0];
            int v2 = s[1];
            union(pa, v1, v2);
        }
        Map<Integer, Set<Integer>> groups = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            int ai = find(pa, i);
            groups.computeIfAbsent(ai, k -> new HashSet<>()).add(i);
        }
        long res = 0;
        for (int k : groups.keySet()) {
            Set<Integer> indexes = groups.get(k);
            List<Long> allv = new ArrayList<>();
            for (int index : indexes) {
                allv.add((long) a[index]);
            }
            allv.sort(Collections.reverseOrder());
            int vi = 0;
            for (int index : indexes) {
                if (index % 2 == 0) {
                    res += allv.get(vi++);
                }
            }
            for (int index : indexes) {
                if (index % 2 == 1) {
                    res -= allv.get(vi++);
                }
            }
        }
        return res;
    }
}
