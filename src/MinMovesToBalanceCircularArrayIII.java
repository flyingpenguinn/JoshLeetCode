import java.util.*;

public class MinMovesToBalanceCircularArrayIII {
    // TODO network flow!...
    public long minMoves(int[] a) {
        int n = a.length;
        long sum = 0;
        long need = 0;

        for (int x : a) {
            sum += x;
            if (x < 0) {
                need -= (long) x;
            }
        }

        if (sum < 0) {
            return -1;
        }

        if (need == 0) {
            return 0;
        }

        int s = n;
        int t = n + 1;
        MinCostFlow mcf = new MinCostFlow(n + 2);

        for (int i = 0; i < n; ++i) {
            if (a[i] > 0) {
                mcf.addEdge(s, i, a[i], 0);
            } else if (a[i] < 0) {
                mcf.addEdge(i, t, -(long) a[i], 0);
            }
        }

        for (int i = 0; i < n; ++i) {
            int j = (i + 1) % n;
            mcf.addEdge(i, j, need, 1);
            mcf.addEdge(j, i, need, 1);
        }

        return mcf.flow(s, t, need);
    }

    private static class MinCostFlow {
        private static final long inf = Long.MAX_VALUE / 4;

        private static class Edge {
            int to;
            int rev;
            long cap;
            long cost;

            Edge(int to, int rev, long cap, long cost) {
                this.to = to;
                this.rev = rev;
                this.cap = cap;
                this.cost = cost;
            }
        }

        private final List<Edge>[] g;

        @SuppressWarnings("unchecked")
        MinCostFlow(int n) {
            g = new ArrayList[n];

            for (int i = 0; i < n; ++i) {
                g[i] = new ArrayList<>();
            }
        }

        void addEdge(int from, int to, long cap, long cost) {
            Edge f = new Edge(to, g[to].size(), cap, cost);
            Edge r = new Edge(from, g[from].size(), 0, -cost);

            g[from].add(f);
            g[to].add(r);
        }

        long flow(int s, int t, long need) {
            int n = g.length;

            long[] pot = new long[n];
            long[] dist = new long[n];
            int[] pv = new int[n];
            int[] pe = new int[n];

            long sent = 0;
            long cost = 0;

            while (sent < need) {
                Arrays.fill(dist, inf);
                dist[s] = 0;

                PriorityQueue<long[]> pq =
                        new PriorityQueue<>(Comparator.comparingLong(x -> x[0]));

                pq.offer(new long[]{0, s});

                while (!pq.isEmpty()) {
                    long[] cur = pq.poll();
                    long cd = cur[0];
                    int v = (int) cur[1];

                    if (cd != dist[v]) {
                        continue;
                    }

                    for (int i = 0; i < g[v].size(); ++i) {
                        Edge e = g[v].get(i);

                        if (e.cap == 0) {
                            continue;
                        }

                        long nd = cd + e.cost + pot[v] - pot[e.to];

                        if (nd >= dist[e.to]) {
                            continue;
                        }

                        dist[e.to] = nd;
                        pv[e.to] = v;
                        pe[e.to] = i;
                        pq.offer(new long[]{nd, e.to});
                    }
                }

                if (dist[t] == inf) {
                    return -1;
                }

                for (int v = 0; v < n; ++v) {
                    if (dist[v] < inf) {
                        pot[v] += dist[v];
                    }
                }

                long add = need - sent;

                for (int v = t; v != s; v = pv[v]) {
                    Edge e = g[pv[v]].get(pe[v]);
                    add = Math.min(add, e.cap);
                }

                for (int v = t; v != s; v = pv[v]) {
                    Edge e = g[pv[v]].get(pe[v]);

                    cost += add * e.cost;
                    e.cap -= add;
                    g[v].get(e.rev).cap += add;
                }

                sent += add;
            }

            return cost;
        }
    }
}
