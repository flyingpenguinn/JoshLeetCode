import java.util.*;

public class MinScoreOfPathBetweenCities {
    public int minScore(int n, int[][] roads) {
        Map<Integer, Set<Integer>> g = new HashMap<>();
        for (int[] r : roads) {
            int s = r[0];
            int e = r[1];
            g.computeIfAbsent(s, k -> new HashSet<>()).add(e);
            g.computeIfAbsent(e, k -> new HashSet<>()).add(s);
        }
        Deque<Integer> dq = new ArrayDeque<>();
        dq.offerLast(1);
        Set<Integer> seen = new HashSet<>();
        seen.add(1);
        while (!dq.isEmpty()) {
            int top = dq.pollFirst();
            Set<Integer> nexts = g.getOrDefault(top, new HashSet<>());
            for (int ne : nexts) {
                if (!seen.contains(ne)) {
                    seen.add(ne);
                    dq.offerLast(ne);
                }
            }
        }
        // System.out.println(seen);
        int res = (int) (1e9);
        for (int[] r : roads) {
            if (seen.contains(r[0])) {
                res = Math.min(res, r[2]);
            }
        }
        return res;
    }
}
