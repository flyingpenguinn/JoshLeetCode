import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class NumOfWaysToArriveAtDestination {
    // find shortest distance, then while we find it, paths[j] += paths[i] if shortest dist j = shortest dist i + weight. otherwise path j = path i
    // this is like drawing a graph of equal dists and then use DP on this dag
    private long Max = 1_000_000_000_000L;
    private long Mod = 1000_000_007;
    public int countPaths(int n, int[][] roads) {
        Map<Integer,Long>[] g = new HashMap[n];
        for(int i=0; i<n; i++){
            g[i] = new HashMap<>();
        }
        for(int[] r: roads){
            int s = r[0];
            int e = r[1];
            long w = r[2];
            g[s].put(e, w);
            g[e].put(s, w);
        }
        long[] paths = new long[n];
        long[] dist = new long[n];
        Arrays.fill(dist, Max);
        dist[0] = 0;
        paths[0] = 1;
        PriorityQueue<long[]> pq = new PriorityQueue<>((x, y)-> Long.compare(x[0], y[0]));
        pq.offer(new long[]{0, 0}); // dist, node
        while(!pq.isEmpty()){
            long[] top = pq.poll();
            long cd = top[0];
            int i = (int)top[1];
            for(int ne: g[i].keySet()){
                long nd = cd+g[i].get(ne);
                if(dist[ne] > nd){
                    paths[ne] = paths[i];
                    dist[ne] = nd;
                    pq.offer(new long[]{nd, ne});
                }else if(dist[ne] == nd){
                    paths[ne] += paths[i];
                    paths[ne] %= Mod;
                }
            }
        }
        return (int)paths[n-1];
    }
}
