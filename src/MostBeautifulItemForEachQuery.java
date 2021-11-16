import javafx.scene.layout.Priority;

import java.util.*;

public class MostBeautifulItemForEachQuery {

    public int[] maximumBeauty(int[][] items, int[] queries) {
       int[][] qs = new int[queries.length][2];
       int qi = 0;
        for(int i=0; i<queries.length; ++i){
            var q = queries[i];
            qs[qi++] = new int[]{q, i};
        }
        Arrays.sort(qs, (x, y)-> Integer.compare(x[0], y[0]));
        int[] res = new int[queries.length];
        Arrays.sort(items, (x,y)-> Integer.compare(x[0], y[0]));
        int j = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i=0; i<qs.length; ++i){
            while(j<items.length && items[j][0]<=qs[i][0]){
                pq.offer(items[j][1]);
                ++j;
            }
            res[qs[i][1]] = pq.isEmpty()? 0: pq.peek();
        }
        return res;
    }
}
