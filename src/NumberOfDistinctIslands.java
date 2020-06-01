import java.util.*;

public class NumberOfDistinctIslands {
    // encode each island according to their top left as "points"
    // we can use a set of list because if islands are the same the sequence from top left to all others must be the same, we visit in the same order
    // we can also use a direction string to tell the sequence from the starting point
    class Point {
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return r == point.r &&
                    c == point.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }

    public int numDistinctIslands(int[][] a) {
        Set<List<Point>> set = new HashSet<>();
        int m = a.length;
        int n = a[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 1) {
                    List<Point> r = dfs(a, i, j, i, j);
                    set.add(r);
                }
            }
        }
        return set.size();
    }

    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    List<Point> dfs(int[][] a, int i, int j, int si, int sj) {
        int m = a.length;
        int n = a[0].length;
        a[i][j] = 2;
        List<Point> r = new ArrayList<>();
        r.add(new Point(i - si, j - sj));
        for (int[] d : dirs) {
            int ni = i + d[0];
            int nj = j + d[1];
            if (ni >= 0 && ni < m && nj >= 0 && nj < n && a[ni][nj] == 1) {
                r.addAll(dfs(a, ni, nj, si, sj));
            }
        }
        return r;
    }


}
