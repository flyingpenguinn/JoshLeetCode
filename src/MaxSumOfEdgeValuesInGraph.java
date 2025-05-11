import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class MaxSumOfEdgeValuesInGraph {
    private int[] pa, size, hasCycle;

    private int find(int x) {
        return pa[x] == x ? x : (pa[x] = find(pa[x]));
    }

    private boolean union(int x, int y) {
        int rx = find(x), ry = find(y);
        if (rx == ry) return false;
        if (size[rx] < size[ry]) {
            pa[rx] = ry;
            size[ry] += size[rx];
        } else {
            pa[ry] = rx;
            size[rx] += size[ry];
        }
        return true;
    }

    public long maxScore(int n, int[][] edges) {
        // 1) init UF
        pa       = new int[n];
        size     = new int[n];
        hasCycle = new int[n];
        for (int i = 0; i < n; i++) {
            pa[i]       = i;
            size[i]     = 1;
            hasCycle[i] = 0;
        }

        // 2) union edges, mark cycles
        for (int[] e : edges) {
            if (!union(e[0], e[1])) {
                hasCycle[find(e[0])] = 1;
            }
        }
        // propagate cycle flags to roots
        for (int i = 0; i < n; i++) {
            if (hasCycle[i] == 1) {
                hasCycle[find(i)] = 1;
            }
        }

        // 3) collect components: [size k, edgeCount y, da = k - y]
        List<int[]> comps = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (find(i) == i) {
                int k      = size[i];
                int y      = (hasCycle[i] == 1 ? k : k - 1);
                int da     = k - y;  // 0 for cycle, 1 for path
                comps.add(new int[]{k, y, da});
            }
        }

        // 4) sort: cycles first (da=0), then by size descending
        comps.sort((x, y) -> {
            if (x[2] != y[2]) return x[2] - y[2];
            return y[0] - x[0];
        });

        long ans = 0;
        int now = n;

        // 5) for each component, build deque of labels [now-k+1..now]
        for (int[] c : comps) {
            int k  = c[0];
            int da = c[2];
            Deque<Integer> dq = new ArrayDeque<>();
            for (int i = 0; i < k; i++) {
                if ((i & 1) == 1) dq.addLast(now);
                else              dq.addFirst(now);
                now--;
            }
            Integer[] arr = dq.toArray(new Integer[0]);
            for (int i = 0; i + 1 < arr.length; i++) {
                ans += 1L * arr[i] * arr[i + 1];
            }
            // wrap‐around for cycles
            if (da == 0 && arr.length > 1) {
                ans += 1L * arr[0] * arr[arr.length - 1];
            }
        }

        return ans;
    }
}
