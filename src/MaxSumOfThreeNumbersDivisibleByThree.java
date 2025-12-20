import java.util.*;

public class MaxSumOfThreeNumbersDivisibleByThree {
    private int solve(List<Integer> l) {
        int res = 0;
        if (l.size() >= 3) {
            res = l.get(0) + l.get(1) + l.get(2);
        }
        return res;
    }

    public int maximumSum(int[] a) {
        Map<Integer, List<Integer>> m = new HashMap<>();
        int n = a.length;
        for (int i = 0; i < n; ++i) {
            int mod = a[i] % 3;
            int v = a[i];
            m.computeIfAbsent(mod, k -> new ArrayList<>()).add(v);
        }
        List<Integer> l1 = m.getOrDefault(1, new ArrayList<>());
        l1.sort(Collections.reverseOrder());
        List<Integer> l2 = m.getOrDefault(2, new ArrayList<>());
        l2.sort(Collections.reverseOrder());
        List<Integer> l3 = m.getOrDefault(0, new ArrayList<>());
        l3.sort(Collections.reverseOrder());
        int way1 = solve(l1);
        int way2 = solve(l2);
        int way3 = solve(l3);
        int way4 = 0;
        if (l1.size() > 0 && l2.size() > 0 && l3.size() > 0) {
            way4 = l1.get(0) + l2.get(0) + l3.get(0);
        }
        return Math.max(way1, Math.max(way2, Math.max(way3, way4)));
    }
}
