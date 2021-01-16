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
        Arrays.fill(dist,Max);
        dist[s] = 0;
// k+1 relaxations because it's k stops i.e. path len = k+1
        for(int i=0; i<=k; i++){
            int[] ndist = Arrays.copyOf(dist,n);
            for(int[] e: es){
                int from = e[0];
                int to = e[1];
                int newto= dist[from]+e[2];
                ndist[to]= Math.min(ndist[to], newto);
            }
            dist = ndist;
        }
        return dist[t]>=Max? -1: dist[t];
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