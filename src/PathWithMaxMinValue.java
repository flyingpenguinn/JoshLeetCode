import base.ArrayUtils;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
LC#1102
Given a matrix of integers A with R rows and C columns, find the maximum score of a path starting at [0,0] and ending at [R-1,C-1].

The score of a path is the minimum value in that path.  For example, the value of the path 8 →  4 →  5 →  9 is 4.

A path moves some number of times from one visited cell to any neighbouring unvisited cell in one of the 4 cardinal directions (north, east, west, south).



Example 1:



Input: [[5,4,5],[1,2,6],[7,4,6]]
Output: 4
Explanation:
The path with the maximum score is highlighted in yellow.
Example 2:



Input: [[2,2,1,2,2,2],[1,2,2,2,1,2]]
Output: 2
Example 3:



Input: [[3,4,6,3,4],[0,2,1,1,7],[8,8,3,2,7],[3,2,4,9,8],[4,1,2,0,0],[4,6,5,4,3]]
Output: 3


Note:

1 <= R, C <= 100
0 <= A[i][j] <= 10^9
 */
public class PathWithMaxMinValue {
    // similar to swimming in rising water: it's min max path there and it's max min path here
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int maximumMinimumPath(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        // init to min for each cell
        boolean[][] done = new boolean[m][n];
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dist[i], -1);
        }
        // max path...
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(y[2], x[2]));

        pq.offer(new int[]{0, 0, a[0][0]});
        dist[0][0] = a[0][0];
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int i = top[0];
            int j = top[1];
            if (done[i][j]) {
                continue;
            }
            done[i][j] = true;
            // System.out.println(Arrays.toString(top));
            if (i == m - 1 && j == n - 1) {
                break;
            }

            for (int[] d : dirs) {
                int ni = i + d[0];
                int nj = j + d[1];
                if (ni >= 0 && ni < m && nj >= 0 && nj < n) {
                    int nd = Math.min(top[2], a[ni][nj]);
                    if (dist[ni][nj] < nd) {
                        dist[ni][nj] = nd;
                        pq.offer(new int[]{ni, nj, nd});
                    }
                }
            }
        }
        return dist[m - 1][n - 1];
    }

    public static void main(String[] args) {
        System.out.println(new PathMaxMinBinarySearch().maximumMinimumPath(ArrayUtils.read("[[3,4,6,3,4],[0,2,1,1,7],[8,8,3,2,7],[3,2,4,9,8],[4,1,2,0,0],[4,6,5,4,3]]")));
    }
}

// binary search the value. if a path has min = v, we remove all values < v it becomes blocked
class PathMaxMinBinarySearch {
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int maximumMinimumPath(int[][] a) {
        int rows = a.length;
        int cols = a[0].length;
        int l = 0;
        // the min value of the path won't be bigger than this
        int u = Math.min(a[0][0], a[rows - 1][cols - 1]);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                l = Math.min(l, a[i][j]);
                u = Math.max(u, a[i][j]);
            }
        }

        while (l <= u) {
            int mid = l + (u - l) / 2;
            boolean[][] v = new boolean[rows][cols];
            if (dfs(a, 0, 0, mid, v)) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return u;
    }

    private boolean dfs(int[][] a, int i, int j, int mid, boolean[][] v) {
        int rows = a.length;
        int cols = a[0].length;
        if (a[i][j] < mid) {
            return false;
        }
        if (i == rows - 1 && j == cols - 1) {
            return true;
        }
        v[i][j] = true;
        for (int[] d : dirs) {
            int ni = i + d[0];
            int nj = j + d[1];
            if (ni >= 0 && ni < rows && nj >= 0 && nj < cols && a[ni][nj] >= mid && !v[ni][nj]) {
                boolean reached = dfs(a, ni, nj, mid, v);
                if (reached) {
                    return true;
                }
            }
        }
        return false;
    }
}
