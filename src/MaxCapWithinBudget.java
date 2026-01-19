import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class MaxCapWithinBudget {
    class M {
        int cost;
        int cap;
    }

    public int maxCapacity(int[] costs, int[] capacity, int budget) {
        int n = costs.length;
        M[] ms = new M[n];
        int res = 0;
        for (int i = 0; i < n; ++i) {
            ms[i] = new M();
            ms[i].cost = costs[i];
            ms[i].cap = capacity[i];
            if(ms[i].cost<budget){
                res = Math.max(res, ms[i].cap);
            }
        }
        Arrays.sort(ms, (x, y) -> Integer.compare(x.cost, y.cost));
        PriorityQueue<M> pq = new PriorityQueue<>((x, y) -> Integer.compare(y.cost, x.cost));
        TreeMap<Integer, Integer> capm = new TreeMap<>();

        for (int i = 0; i < n; ++i) {
            int ccost = ms[i].cost;
            while (!pq.isEmpty() && pq.peek().cost + ccost >= budget) {
                M top = pq.poll();
                update(capm, top.cap, -1);
            }
            if (!capm.isEmpty()) {
                int bestcap = capm.lastKey();
                int cur = ms[i].cap + bestcap;
                res = Math.max(res, cur);
            }
            pq.offer(ms[i]);
            update(capm, ms[i].cap, 1);
        }
        return res;
    }

    private void update(TreeMap<Integer, Integer> m, int k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }
}
