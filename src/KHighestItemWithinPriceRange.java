import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class KHighestItemWithinPriceRange {
    private class Dist implements Comparable<Dist> {
        int d;
        int p;
        int r;
        int c;

        @Override
        public int compareTo(Dist o) {
            if (d != o.d) {
                return Integer.compare(d, o.d);
            }
            if (p != o.p) {
                return Integer.compare(p, o.p);
            }
            if (r != o.r) {
                return Integer.compare(r, o.r);
            }
            if (c != o.c) {
                return Integer.compare(c, o.c);
            }
            return 0;
        }

        public Dist(int d, int p, int r, int c) {
            this.d = d;
            this.p = p;
            this.r = r;
            this.c = c;
        }
    }

    private class Cell {
        int i;
        int j;
        Dist d;


        public Cell(int i, int j, Dist d) {
            this.i = i;
            this.j = j;
            this.d = d;
        }
    }

    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public List<List<Integer>> highestRankedKItems(int[][] a, int[] pricing, int[] start, int k) {
        int m = a.length;
        int n = a[0].length;
        List<List<Integer>> res = new ArrayList<>();
        PriorityQueue<Cell> pq = new PriorityQueue<>((x, y) -> x.d.compareTo(y.d));
        Dist[][] dist = new Dist[m][n];
        dist[start[0]][start[1]] = new Dist(0, a[start[0]][start[1]], start[0], start[1]);
        pq.offer(new Cell(start[0], start[1], dist[start[0]][start[1]]));
        while (!pq.isEmpty() && k > 0) {
            Cell top = pq.poll();
            int i = top.i;
            int j = top.j;
            int cd = top.d.d;
            int price = top.d.p;
            if (price >= pricing[0] && price <= pricing[1]) {
                List<Integer> cur = new ArrayList<>();
                cur.add(i);
                cur.add(j);
                res.add(cur);
                --k;
            }
            for (int[] d : dirs) {
                int ni = i + d[0];
                int nj = j + d[1];
                if (ni >= 0 && ni < m && nj >= 0 && nj < n && a[ni][nj] != 0) {
                    Dist nd = new Dist(cd + 1, a[ni][nj], ni, nj);
                    if (dist[ni][nj] == null || nd.compareTo(dist[ni][nj]) < 0) {
                        dist[ni][nj] = nd;
                        pq.offer(new Cell(ni, nj, nd));
                    }
                }
            }
        }
        return res;
    }
}
