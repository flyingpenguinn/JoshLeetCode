import java.util.*;

public class MaxHammingDistance {
    // find the shortest path from any complement to any node. Multi source BFS
    public int[] maxHammingDistances(int[] a, int m) {
        int n = a.length;
        Set<Integer> set = new HashSet<>();
        for (int ai : a) {
            set.add(ai);
        }
        int[] res = new int[n];
        if (set.size() == 1) {
            return res;
        }
        Deque<Integer> q = new ArrayDeque<>();
        Map<Integer, Integer> dist = new HashMap<>();
        for (int ai : set) {
            int comp = (~ai) & ((1 << m) - 1);
            q.offer(comp);
            dist.put(comp, 0);
        }
        while (!q.isEmpty()) {
            int top = q.poll();
            int cd = dist.get(top);
            for (int i = 0; i < m; ++i) {
                int ntop = top ^ (1 << i);
                if (set.contains(ntop) && !dist.containsKey(ntop)) {
                    dist.put(ntop, cd + 1);
                    q.offer(ntop);
                }
            }
        }
        for (int i = 0; i < n; ++i) {
            res[i] = m - dist.getOrDefault(a[i], 0);
        }
        return res;
    }
}
