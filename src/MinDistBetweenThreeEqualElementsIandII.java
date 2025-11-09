import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class MinDistBetweenThreeEqualElementsIandII {
    public int minimumDistance(int[] a) {
        int n = a.length;
        Map<Integer, Deque<Integer>> m = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            m.computeIfAbsent(a[i], p -> new ArrayDeque<>()).add(i);
        }
        int max = Integer.MAX_VALUE;
        int res = max;
        Map<Integer, Integer> prev = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            int v = a[i];
            Deque<Integer> apps = m.get(v);
            while (!apps.isEmpty() && apps.peekFirst() <= i) {
                apps.pollFirst();
            }
            Integer lower = prev.get(v);
            Integer higher = apps.peekFirst();
            if (lower != null && higher != null) {
                int cur = i - lower + higher - i + higher - lower;
                res = Math.min(res, cur);
            }
            prev.put(v, i);
        }
        return res >= max ? -1 : res;
    }
}
