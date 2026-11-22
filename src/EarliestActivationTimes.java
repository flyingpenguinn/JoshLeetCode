import java.util.*;

public class EarliestActivationTimes {
    // Weekly 529 Q3 (1 WA). Dijkstra where a node's time = the req-th smallest arrival among its activated in-neighbors.
    // Keep a max-heap of size req per node; once it has req arrivals, its top is a candidate time.
    // Popped nodes come in increasing time and arrivals are >= the sender's time, so a node's time is final when popped.
    // WA version: decremented req per relaxation and then took any smaller arrival -> min arrival, not the req-th smallest.
    // Counterexample: n=3, edges [0,1,1],[0,2,5],[1,2,1], req[2]=2 -> expected 5, WA gave 2.
    public long[] earliestActivationTimes(int n, int[][] edges, int[] req) {
        Map<Integer, Integer>[] g = new HashMap[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new HashMap<>();
        }
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            int del = e[2];
            g[v1].put(v2, del);
        }
        PriorityQueue<long[]> pq = new PriorityQueue<>((x,y)-> Long.compare(x[1], y[1]));
        pq.offer(new long[]{0, 0});
        long[] dist = new long[n];
        long Max = (long) 1e18;
        Arrays.fill(dist, Max);
        dist[0] = 0;
        PriorityQueue<Long>[] cand = new PriorityQueue[n];
        for(int i=0; i<n; ++i){
            cand[i] = new PriorityQueue<>(Collections.reverseOrder());
        }
        while (!pq.isEmpty()) {
            long[] top = pq.poll();
            int node = (int) top[0];
            long cd = top[1];
            if (dist[node] != cd) {
                continue;
            }
            for (int ne : g[node].keySet()) {
                long ccost = g[node].get(ne);
                long nd = cd + ccost;
                cand[ne].offer(nd);
                if(cand[ne].size()>req[ne]){
                    cand[ne].poll();
                }
                if(cand[ne].size()>=req[ne]){
                    if(dist[ne]>cand[ne].peek()){
                        dist[ne] = cand[ne].peek();
                        pq.offer(new long[]{ne, dist[ne]});
                    }
                }
            }
        }
        for (int i = 0; i < n; ++i) {
            if (dist[i] == Max) {
                dist[i] = -1;
            }
        }
        return dist;
    }
}
