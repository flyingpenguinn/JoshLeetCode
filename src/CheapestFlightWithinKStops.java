import base.ArrayUtils;

import java.util.*;

/*
LC#787
There are n cities connected by m flights. Each flight starts from city u and arrives at v with a price w.

Now given all the cities and flights, together with starting city src and the destination dst, your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.

Example 1:
Input:
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 1
Output: 200
Explanation:
The graph looks like this:


The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.
Example 2:
Input:
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 0
Output: 500
Explanation:
The graph looks like this:


The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.


Constraints:

The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
The size of flights will be in range [0, n * (n - 1) / 2].
The format of each flight will be (src, dst, price).
The price of each flight will be in the range [1, 10000].
k is in the range of [0, n - 1].
There will not be any duplicated flights or self cycles.
 */
public class CheapestFlightWithinKStops {

    // almost raw bellman ford. the only gotcha is
    // we need a k to store previous rounds paths so that we avoid this case: s->i->j, s..i has k stop, and we accidentally update j based on dist[i]
    // we can further improve by using %2 trick since we only need k-1
    private int Max = 10000000;

    public int findCheapestPrice(int n, int[][] es, int s, int t, int k) {
        int[] dist = new int[n];
        Arrays.fill(dist, Max);
        dist[s] = 0;
// k+1 relaxations because it's k stops i.e. path len = k+1
        for (int i = 0; i <= k; i++) {
            int[] ndist = Arrays.copyOf(dist, n);
            for (int[] e : es) {
                int from = e[0];
                int to = e[1];
                int newto = dist[from] + e[2];
                ndist[to] = Math.min(ndist[to], newto);
            }
            dist = ndist;
        }
        return dist[t] >= Max ? -1 : dist[t];
    }

    public static void main(String[] args) {
        CheapestFlightWithinKStops cfw = new CheapestFlightWithinKStops();
        System.out.println(cfw.findCheapestPrice(3, ArrayUtils.read("[[0,1,100],[1,2,100],[0,2,500]]"), 0, 2, 1));
    }
}

class Dijkastra {

    // dijkastra with states: dist array must be dist[i][time] in order to handle the additional state. similar to min cost to reach dest in time
    int Max = 10000000;

    public int findCheapestPrice(int n, int[][] es, int s, int t, int k) {

        // price, node, steps
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[0], y[0]));

        Map<Integer, Integer>[] g = new HashMap[n];
        for (int i = 0; i < n; i++) {
            g[i] = new HashMap<>();
        }
        for (int[] e : es) {
            g[e[0]].put(e[1], e[2]);
        }
        pq.offer(new int[]{0, s, 0});
        // note dist with extra state about stops
        int[][] dist = new int[n][k + 2];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Max);
        }
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int price = top[0];
            int x = top[1];
            int stops = top[2];
            if (x == t) {
                return price;
            }
            for (int nk : g[x].keySet()) {
                int nstop = stops+1;
                // must be price here so that we use the correct dist at "stop". note we are not doing dist array or done array here
                if (nstop > k + 1) {
                    continue;
                }
                int nprice = price + g[x].get(nk);
                if (dist[nk][nstop] > nprice) {
                    dist[nk][nstop] = nprice;
                    pq.offer(new int[]{nprice, nk, nstop});
                }
            }
        }
        return -1;
    }
}