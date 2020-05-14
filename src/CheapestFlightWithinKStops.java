import base.ArrayUtils;

import java.util.*;

public class CheapestFlightWithinKStops {

    // almost raw bellman ford. the only gotcha is
    // we need a k to store previous rounds paths so that we avoid this case: s->i->j, s..i has k stop, and we accidentally update j based on dist[i]
    // we can further improve by using %2 trick since we only need k-1
    int Max = 10000000;
    public int findCheapestPrice(int n, int[][] es, int s, int t, int round) {
        int[][] dist = new int[n][round + 2];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Max);
        }
        dist[s][0] = 0;
        // round k dist has k-1 stops
        for (int k = 1; k <= round + 1; k++) {
            for (int[] e : es) {
                int i = e[0];
                int j = e[1];
                dist[j][k] = Math.min(dist[j][k - 1], Math.min(dist[j][k], dist[i][k - 1] + e[2]));
                // in case k-1 stops is better!
            }
        }
        return dist[t][round + 1] >= Max ? -1 : dist[t][round + 1];
    }

    public static void main(String[] args) {
        CheapestFlightWithinKStops cfw = new CheapestFlightWithinKStops();
        System.out.println(cfw.findCheapestPrice(3, ArrayUtils.read("[[0,1,100],[1,2,100],[0,2,500]]"), 0, 2, 1));
    }
}

class Dijkastra {

    // changed dijkastra not using dist/done arrays because a shoter node can use cause more stops. we need to put all the updated values into pq
    // i.e. we can select a "worse" path but it will lead us to dst we stop after traversing k+1 nodes
    int Max = 10000000;

    public int findCheapestPrice(int n, int[][] fs, int s, int t, int k) {

        // price, node, steps
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });
        int[][] g = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(g[i], Max);
        }
        for (int[] f : fs) {
            g[f[0]][f[1]] = f[2];
            if (f[0] == s) {
                pq.offer(new int[]{f[2], f[1], 0});
            }
        }
        // cant use done array here because a longer path may need fewer stops
        /*
 4
[[0,1,1],[0,2,5],[1,2,1],[2,3,1]]
0
3
1

here we need to keep evaluating 0->2 = 5 though it's not a good route for 2, it's a good one for 3

         */
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int price = top[0];
            int x = top[1];
            int stops = top[2];
            if (x == t) {
                return price;
            }
            if (stops == k) {
                continue;
            }
            for (int i = 0; i < n; i++) {
                // must be price here so that we use the correct dist at "stop". note we are not doing dist array or done array here
                if (g[x][i] < Max) {
                    pq.offer(new int[]{price + g[x][i], i, stops + 1});
                }
            }
        }
        return -1;
    }
}