import java.util.*;

/*
LC#743
There are N network nodes, labelled 1 to N.

Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.

Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.



Example 1:



Input: times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2
Output: 2


Note:

N will be in the range [1, 100].
K will be in the range [1, N].
The length of times will be in the range [1, 6000].
All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 0 <= w <= 100.
 */
public class NetworkDelayTime {
    // spfa https://en.wikipedia.org/wiki/Shortest_Path_Faster_Algorithm
    // or just apply dijkastra
    int Max = 1000000000;

    public int networkDelayTime(int[][] es, int n, int k) {
        int[][] g = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(g[i], Max);
        }
        for (int[] e : es) {
            g[e[0]][e[1]] = e[2];
        }
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Max);
        dist[k] = 0;

        boolean[] inq = new boolean[n + 1];
        Deque<Integer> pq = new ArrayDeque<>();

        pq.offer(k);
        inq[k] = true;

        while (!pq.isEmpty()) {
            int cur = pq.poll();
            inq[cur] = false;
            for (int i = 1; i <= n; i++) {
                int nd = dist[cur] + g[cur][i];
                if (dist[i] > nd) {
                    dist[i] = nd;
                    if (!inq[i]) {
                        pq.offer(i);
                    }
                }
            }
        }
        int max = -1;
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, dist[i]);
        }
        return max >= Max ? -1 : max;
    }
}

class NetworkDelayTimeDijkastra {
    int Max = 1000000000;

    public int networkDelayTime(int[][] es, int n, int k) {
        int[][] g = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(g[i], Max);
        }
        for (int[] e : es) {
            g[e[0]][e[1]] = e[2];
        }
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Max);
        dist[k] = 0;

        boolean[] done = new boolean[n + 1];

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[]{k, 0});

        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int cur = top[0];
            if (done[cur]) {
                continue;
            }
            done[cur] = true;
            for (int i = 1; i <= n; i++) {
                int nd = dist[cur] + g[cur][i];
                if (dist[i] > nd) {
                    dist[i] = nd;
                    pq.offer(new int[]{i, nd});
                }
            }
        }
        int max = -1;
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, dist[i]);
        }
        return max >= Max ? -1 : max;
    }
}
