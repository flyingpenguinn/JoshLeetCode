import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class MaxStarsOfGraph {

    private Map<Integer, PriorityQueue<Integer>> m = new HashMap<>();
    private Map<Integer, Integer> vm = new HashMap<>();

    public int maxStarSum(int[] vals, int[][] edges, int k) {
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            process(v1, v2, vals, k);
            process(v2, v1, vals, k);
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < vals.length; ++i) {
            res = Math.max(res, vm.getOrDefault(i, 0) + vals[i]);
        }
        return res;
    }

    private void process(int v1, int v2, int[] vals, int k) {
        if (vals[v2] < 0) {
            return;
        }
        PriorityQueue<Integer> pq1 = m.getOrDefault(v1, new PriorityQueue<>());
        int sum1 = vm.getOrDefault(v1, 0);
        pq1.offer(vals[v2]);
        sum1 += vals[v2];
        if (pq1.size() > k) {
            sum1 -= pq1.poll();
        }
        m.put(v1, pq1);
        vm.put(v1, sum1);
    }
}
