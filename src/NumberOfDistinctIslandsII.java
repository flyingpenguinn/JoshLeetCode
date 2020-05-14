import base.ArrayUtils;

import java.util.*;

/*
LC#711
Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

Count the number of distinct islands. An island is considered to be the same as another if they have the same shape, or have the same shape after rotation (90, 180, or 270 degrees only) or reflection (left/right direction or up/down direction).

Example 1:
11000
10000
00001
00011
Given the above grid map, return 1.

Notice that:
11
1
and
 1
11
are considered same island shapes. Because if we make a 180 degrees clockwise rotation on the first island, then two islands will have the same shapes.
Example 2:
11100
10001
01001
01110
Given the above grid map, return 2.

Here are the two distinct islands:
111
1
and
1
1

Notice that:
111
1
and
1
111
are considered same island shapes. Because if we flip the first array in the up/down direction, then they have the same shapes.
Note: The length of each dimension in the given grid does not exceed 50.
 */

public class NumberOfDistinctIslandsII {
    // make signature: list of points, of each island

    class Point implements Comparable<Point> {
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

        @Override
        public int compareTo(Point o) {
            return o.r == this.r ? Integer.compare(this.c, o.c) : Integer.compare(this.r, o.r);
        }
    }

    Set<List<Point>> seen = new HashSet<>();
    int r = 0;

    public int numDistinctIslands2(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 1) {
                    List<int[]> cur = new ArrayList<>();
                    dfs(a, i, j, cur);
                    hashcur(cur);
                }
            }
        }
        return r;
    }

    private void hashcur(List<int[]> cur) {
        int rs = Integer.MAX_VALUE;
        int re = Integer.MIN_VALUE;
        int cs = Integer.MAX_VALUE;
        int ce = Integer.MIN_VALUE;
        for (int[] c : cur) {
            rs = Math.min(rs, c[0]);
            re = Math.max(re, c[0]);
            cs = Math.min(cs, c[1]);
            ce = Math.max(ce, c[1]);
        }
        Set<List<Point>> curset = new HashSet<>();

        // 0d
        List<Point> s1 = new ArrayList<>();
        for (int i = 0; i < cur.size(); i++) {
            int[] p = cur.get(i);
            int pr = p[0] - rs;
            int pc = p[1] - cs;
            s1.add(new Point(pr, pc));
        }

        Collections.sort(s1);
        if (seen.contains(s1)) {
            return;
        }
        curset.add(s1);
        // 180d
        List<Point> s2 = new ArrayList<>();
        for (int i = 0; i < cur.size(); i++) {
            int[] p = cur.get(i);
            int pr = re - p[0];
            int pc = ce - p[1];
            s2.add(new Point(pr, pc));
        }
        Collections.sort(s2);
        if (seen.contains(s2)) {
            return;
        }
        curset.add(s2);
        // y reflection
        List<Point> s3 = new ArrayList<>();
        for (int i = 0; i < cur.size(); i++) {
            int[] p = cur.get(i);
            int pr = p[0] - rs;
            int pc = ce - p[1];
            s3.add(new Point(pr, pc));
        }
        Collections.sort(s3);
        if (seen.contains(s3)) {
            return;
        }
        curset.add(s3);
        // 90d
        List<Point> s4 = new ArrayList<>();
        for (int i = 0; i < cur.size(); i++) {
            int[] p = cur.get(i);
            int pr = p[1] - cs;
            int pc = re - p[0];
            s4.add(new Point(pr, pc));
        }
        Collections.sort(s4);
        if (seen.contains(s4)) {
            return;
        }
        curset.add(s4);

        // 270d
        List<Point> s5 = new ArrayList<>();
        for (int i = 0; i < cur.size(); i++) {
            int[] p = cur.get(i);
            int pr = ce - p[1];
            int pc = p[0] - rs;
            s5.add(new Point(pr, pc));
        }
        Collections.sort(s5);
        if (seen.contains(s5)) {
            return;
        }
        curset.add(s5);
        // x reflection
        List<Point> s6 = new ArrayList<>();
        for (int i = 0; i < cur.size(); i++) {
            int[] p = cur.get(i);
            int pr = re - p[0];
            int pc = p[1] - cs;
            s6.add(new Point(pr, pc));
        }
        Collections.sort(s6);
        if (seen.contains(s6)) {
            return;
        }
        curset.add(s6);
        seen.addAll(curset);
        r++;

    }

    int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    private void dfs(int[][] a, int i, int j, List<int[]> cur) {
        cur.add(new int[]{i, j});
        a[i][j] = -1;
        for (int[] d : dirs) {
            int ni = i + d[0];
            int nj = j + d[1];
            if (ni >= 0 && ni < a.length && nj >= 0 && nj < a[0].length && a[ni][nj] == 1) {
                dfs(a, ni, nj, cur);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new NumberOfDistinctIslandsII().numDistinctIslands2(ArrayUtils.read("[[0,1,0,0,0,0,1,0,0,0,1,0,1,1,0],[0,0,0,0,1,1,0,0,1,1,0,1,1,1,1],[0,1,1,1,0,1,0,0,1,1,0,0,1,0,0],[0,1,0,0,0,0,0,1,0,1,1,0,1,0,0],[0,0,0,1,0,0,0,0,0,1,1,1,0,0,1],[0,1,1,0,0,0,0,1,0,0,1,0,1,0,0],[0,0,0,0,1,0,1,1,1,1,1,0,0,1,1],[1,1,0,0,1,1,1,0,0,1,1,1,0,0,0],[1,1,1,0,0,0,1,1,0,0,1,0,0,0,1],[1,0,0,0,1,1,1,1,0,0,0,1,0,0,1]]")));
        System.out.println(new NumberOfDistinctIslandsII().numDistinctIslands2(ArrayUtils.read("[[1,1,0,0,0],[1,0,0,0,1],[1,0,0,0,1],[0,0,0,1,1]]")));

        System.out.println(new NumberOfDistinctIslandsII().numDistinctIslands2(ArrayUtils.read("[[1,1,1,0,0],[1,0,0,0,1],[0,1,0,0,1],[0,1,1,1,0]]")));

        System.out.println(new NumberOfDistinctIslandsII().numDistinctIslands2(ArrayUtils.read("[[1,1,0,0,0],[1,0,0,0,0],[0,0,0,0,1],[0,0,0,1,1]]")));


    }
}