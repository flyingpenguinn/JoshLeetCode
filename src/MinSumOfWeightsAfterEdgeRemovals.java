import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class MinSumOfWeightsAfterEdgeRemovals {
    private Map<Integer, Map<Integer, Long>> t = new HashMap<>();
    private Long[][] dp;
    private int k = 0;

    public long maximizeSumOfWeights(int[][] edges, int k) {
        int n = edges.length + 1;
        dp = new Long[n][2];
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            long w = e[2];
            this.k = k;
            t.computeIfAbsent(v1, p -> new HashMap<>()).put(v2, w);
            t.computeIfAbsent(v2, p -> new HashMap<>()).put(v1, w);
        }
        return solve(0, 0, n);
    }

    private long solve(int i, int hasp, int p) {
        // System.out.println(i+" "+hasp);
        if (dp[i][hasp] != null) {
            return dp[i][hasp];
        }
        int ck = k - hasp;
        PriorityQueue<Long> pq = new PriorityQueue<>();
        long res = 0;
        for (int ne : t.getOrDefault(i, new HashMap<>()).keySet()) {
            if (ne == p) {
                continue;
            }
            long w = t.get(i).get(ne);
            long way1 = w + solve(ne, 1, i);
            long way2 = solve(ne, 0, i);
            res += way2;
            long diff = way1 - way2;
            //   System.out.println("cur="+i+" offering diff "+diff);
            if (diff > 0) {
                pq.offer(diff);
            }
            if (pq.size() > ck) {
                pq.poll();
            }
            //     System.out.println("current = "+i+ " ne="+ne+" way1="+way1+" way2="+way2);
        }
        long delta = 0;
        while (!pq.isEmpty()) {
            //    System.out.println("cur="+i+" plus delta"+pq.peek());
            delta += pq.poll();
        }
        res += delta;
        //  System.out.println(i+" hasp="+hasp+" res="+res);
        dp[i][hasp] = res;
        return res;
    }
}
