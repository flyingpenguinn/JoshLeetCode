import java.util.*;

public class MinCostToReachWithDiscount {
    private int Max = (int) 1e9;
    class Item{
        int i;
        int discount;
        int dist;

        public Item(int i, int discount, int dist) {
            this.i = i;
            this.discount = discount;
            this.dist = dist;
        }
    };
    public int minimumCost(int n, int[][] highways, int discounts) {
        Map<Integer,Integer>[] g = new HashMap[n];
        for(int i=0; i<n; ++i){
            g[i] = new HashMap<>();
        }
        for(int[] h: highways){
            g[h[0]].put(h[1], h[2]);
            g[h[1]].put(h[0], h[2]);
        }
        int[][] dist = new int[n][discounts+1];
        for(int i=0; i<n; ++i){
            Arrays.fill(dist[i], Max);
        }
        PriorityQueue<Item> pq = new PriorityQueue<>((x,y)-> Integer.compare(x.dist, y.dist));
        pq.offer(new Item(0, discounts, 0));

        while(!pq.isEmpty()){
            Item top = pq.poll();
            int i = top.i;
            int cdisc = top.discount;
            int cdist = top.dist;
            if(i==n-1){
                return cdist;
            }
            for(int ne: g[i].keySet()){
                int toll = g[i].get(ne);
                if(dist[ne][cdisc] > cdist + toll){
                    dist[ne][cdisc] = cdist+toll;
                    pq.offer(new Item(ne, cdisc, cdist + toll));
                }
                if(cdisc > 0 && dist[ne][cdisc-1] > cdist + toll/2){
                    dist[ne][cdisc-1] = cdist + toll/2;
                    pq.offer(new Item(ne, cdisc-1, cdist+toll/2));
                }
            }
        }
        return -1;
    }
}
