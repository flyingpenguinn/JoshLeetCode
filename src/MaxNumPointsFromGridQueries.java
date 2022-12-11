import java.util.*;

public class MaxNumPointsFromGridQueries {
    // use priority queue to get small numbers first
    // remember maxpos seen so that we only query from that number onward
    private class Query {
        int v;
        int index;
        int res = 0;

        public Query(int v, int index) {
            this.v = v;
            this.index = index;
        }
    }

    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int[] maxPoints(int[][] a, int[] qs) {
        int m = a.length;
        int n = a[0].length;
        int qn = qs.length;
        List<Query> qlist = new ArrayList<>();
        for (int i = 0; i < qn; ++i) {
            qlist.add(new Query(qs[i], i));
        }
        Collections.sort(qlist, (x, y) -> Integer.compare(x.v, y.v));
        PriorityQueue<int[]> q = new PriorityQueue<>((x, y) -> Integer.compare(x[2], y[2]));
        q.offer(new int[]{0, 0, a[0][0]});
        Set<Integer> seen = new HashSet<>();
        seen.add(0);
        int maxpos = 0;
        while (!q.isEmpty()) {
            int[] top = q.poll();
            int r = top[0];
            int c = top[1];
            int v = top[2];

            int pos = binary(qlist, maxpos, v);
            maxpos = Math.max(maxpos, pos);
            if (pos < qlist.size()) {
                ++qlist.get(pos).res;
            } else {
                //    System.out.println("value too big " + v);
            }
            for (int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    if (seen.contains(nr * n + nc)) {
                        continue;
                    }
                    q.offer(new int[]{nr, nc, a[nr][nc]});
                    seen.add(nr * n + nc);
                }
            }
        }
        for (int i = 1; i < qn; ++i) {
            qlist.get(i).res += qlist.get(i - 1).res;
        }
        int[] res = new int[qn];
        for (int i = 0; i < qn; ++i) {
            int index = qlist.get(i).index;
            res[index] = qlist.get(i).res;
        }
        return res;
    }

    // first value > t
    private int binary(List<Query> qlist, int l, int t) {
        int u = qlist.size() - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (qlist.get(mid).v <= t) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return l;
    }
}
