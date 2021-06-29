import base.ArrayUtils;
import javafx.util.Pair;

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
    // make signature: list of points, of each island. use D4 to quickly generate all cases. we then sort all possible cases's signature and use the smallest to represent the whole group
    // java's pair is read only...

    class Point implements Comparable<Point> {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public int compareTo(Point o) {
            return x != o.x ? Integer.compare(x, o.x) : Integer.compare(y, o.y);
        }
    }

    private final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private final int[][] shifts = {{-1, 1}, {1, 1}, {-1, -1}, {1, -1}};
    private final Set<List<Point>> islands = new HashSet<>();

    public void dfs(int[][] g, int i, int j, List<Point> comp) {
        comp.add(new Point(i, j));
        g[i][j] = 2;
        for (var d : dirs) {
            int ni = i + d[0];
            int nj = j + d[1];
            if (ni >= 0 && ni < g.length && nj >= 0 && nj < g[0].length && g[ni][nj] == 1) {
                dfs(g, ni, nj, comp);
            }
        }
    }

    void populate(List<List<Point>> res, int lhs, int rhs, int base) {

        for (int i = 0; i < 4; i++) {
            var sh = shifts[i];
            int nx = lhs * sh[0];
            int ny = rhs * sh[1];
            if (res.size() == base + i) {
                res.add(new ArrayList<>());
            }
            res.get(base + i).add(new Point(nx, ny));
            // base is 0 or 4
        }

    }

    private List<Point> norm(List<Point> comp) {
        List<List<Point>> res = new ArrayList<>();
        for (var p : comp) {
            int x = p.x;
            int y = p.y;
            populate(res, x, y, 0);
            populate(res, y, x, 4);
        }
        // calc relative position to the top left corner
        for (var list : res) {
            Collections.sort(list);
            int keyadj = list.get(0).x;
            int vadj = list.get(0).y;
            list.set(0, new Point(0, 0));
            for (int j = 1; j < list.size(); j++) {
                list.get(j).x -= keyadj;
                list.get(j).y -= vadj;
            }
        }
        // then the first among the 8 possible changes is the one
        res.sort((x, y) -> {
            // x and y must be of the same length as they represent the same group of points
            for (int i = 0; i < x.size(); i++) {
                int v = x.get(i).compareTo(y.get(i));
                if (v != 0) {
                    return v;
                }
            }
            return 0;
        });
        return res.get(0);
    }

    int numDistinctIslands2(int[][] g) {
        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < g[0].length; j++) {
                if (g[i][j] == 1) {
                    List<Point> comp = new ArrayList<>();
                    dfs(g, i, j, comp);
                    List<Point> norms = norm(comp);
                    //     print(norms);
                    islands.add(norms);
                }
            }
        }
        return islands.size();
    }

    public static void main(String[] args) {
        System.out.println(new NumberOfDistinctIslandsII().numDistinctIslands2(ArrayUtils.read("[[1,1,0,0,0],[1,0,0,0,0],[0,0,0,0,1],[0,0,0,1,1]]")));


    }
}