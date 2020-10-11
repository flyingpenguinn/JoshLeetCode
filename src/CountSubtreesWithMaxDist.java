import java.util.*;

public class CountSubtreesWithMaxDist {
    // basically enumerate subsets of graph, then calc max dist...
    public int[] countSubgraphsForEachDiameter(int n, int[][] es) {
        int[] res = new int[n - 1];
        for (int st = 0; st < (1 << n); st++) {
            if (Integer.bitCount(st) < 2) {
                continue;
            }
            List<Integer>[] cur = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                cur[i] = new ArrayList<>();
            }
            for (int[] e : es) {
                int start = e[0] - 1;
                int end = e[1] - 1;
                if (((st >> start & 1)) == 1 && ((st >> end) & 1) == 1) {
                    cur[start].add(end);
                    cur[end].add(start);
                }

            }
            int maxdist = maxdist(cur, st, n);
            if (maxdist >= 1) {
                res[maxdist - 1]++;
            }
        }
        return res;
    }

    private int maxdist(List<Integer>[] cur, int st, int n) {
        Deque<Integer> q = new ArrayDeque<>();
        int start = -1;
        for (int i = 0; i < n; i++) {
            if (((st >> i) & 1) == 1) {
                start = i;
                break;
            }
        }
        q.offer(start);
        int longest = -1;
        boolean[] v = new boolean[n];
        v[start] = true;
        while (!q.isEmpty()) {
            int top = q.poll();
            longest = top;
            for (int next : cur[top]) {
                if (!v[next]) {
                    v[next] = true;
                    q.offer(next);
                }
            }
        }
        // if the graph is not connected, return that it's bad
        for (int i = 0; i < n; i++) {
            if (!v[i] && ((st >> i) & 1) == 1) {
                return 0;
            }
        }
        Arrays.fill(v, false);
        Deque<int[]> q2 = new ArrayDeque<>();
        q2.offer(new int[]{longest, 0});
        v[longest] = true;
        int max = 0;
        while (!q2.isEmpty()) {
            int[] top = q2.poll();
            max = Math.max(max, top[1]);
            for (int next : cur[top[0]]) {
                if (!v[next]) {
                    v[next] = true;
                    q2.offer(new int[]{next, top[1] + 1});
                }
            }
        }
        return max;
    }
}
