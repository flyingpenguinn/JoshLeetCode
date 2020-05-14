import base.ArrayUtils;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/*
LC#542
Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.

The distance between two adjacent cells is 1.



Example 1:

Input:
[[0,0,0],
 [0,1,0],
 [0,0,0]]

Output:
[[0,0,0],
 [0,1,0],
 [0,0,0]]
Example 2:

Input:
[[0,0,0],
 [0,1,0],
 [1,1,1]]

Output:
[[0,0,0],
 [0,1,0],
 [1,2,1]]

rem
Note:

The number of elements of the given matrix will not exceed 10,000.
There are at least one 0 in the given matrix.
The cells are adjacent in only four directions: up, down, left and right.
 */
public class ZeroOneMatrix {
    int Max = 10000000;
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int[][] updateMatrix(int[][] a) {
        Deque<int[]> q = new ArrayDeque<>();
        int m = a.length;
        int n = a[0].length;

        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dist[i], Max);
        }
        boolean[][] inq = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 0) {
                    q.offer(new int[]{i, j, 0});
                    inq[i][j] = true;
                    dist[i][j] = 0;
                }
            }
        }

        while (!q.isEmpty()) {
            int[] top = q.poll();
            int i = top[0];
            int j = top[1];
            dist[i][j] = top[2];
            int ndist = dist[i][j] + 1;
            for (int[] d : dirs) {
                int ni = i + d[0];
                int nj = j + d[1];
                if (ni >= 0 && ni < m && nj >= 0 && nj < n) {
                    if (dist[ni][nj] > ndist) {
                        dist[ni][nj] = ndist;
                        if (!inq[ni][nj]) {
                            q.offer(new int[]{ni, nj, ndist});
                        }
                    }
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        int[][] grids = ArrayUtils.read("[[1,1,1][1,0,1]]");
        System.out.println(Arrays.deepToString(new ZeroOneMatrixDp().updateMatrix(grids)));
    }
}

// this works because any path must be consisted of horizontal or vertical path. there is no zigzag path given the nature of this problem
// this is like 2d extension of "candy"
class ZeroOneMatrixDp {
    int[][] result = null;
    int[][] direction = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};


    public int[][] updateMatrix(int[][] matrix) {
        int rn = matrix.length;
        int cn = matrix[0].length;
        result = new int[rn][cn];

        for (int i = 0; i < rn; i++) {
            for (int j = 0; j < cn; j++) {
                if (matrix[i][j] == 0) {
                    result[i][j] = 0;
                } else {
                    result[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        // bottom to top, depend on next row or col
        for (int i = rn - 1; i >= 0; i--) {
            for (int j = cn - 1; j >= 0; j--) {
                if (matrix[i][j] == 1) {
                    if (i + 1 < rn) {
                        if (result[i + 1][j] != Integer.MAX_VALUE) {
                            result[i][j] = Math.min(result[i][j], result[i + 1][j] + 1);
                        }
                    }
                    if (j + 1 < cn) {
                        if (result[i][j + 1] != Integer.MAX_VALUE) {
                            result[i][j] = Math.min(result[i][j], result[i][j + 1] + 1);
                        }
                    }
                }
            }
        }

        // top to bottom, depend on previous row or col
        for (int i = 0; i < rn; i++) {
            for (int j = 0; j < cn; j++) {
                if (i - 1 >= 0) {
                    if (result[i - 1][j] != Integer.MAX_VALUE) {
                        result[i][j] = Math.min(result[i][j], result[i - 1][j] + 1);
                    }
                }
                if (j - 1 >= 0) {
                    if (result[i][j - 1] != Integer.MAX_VALUE) {
                        result[i][j] = Math.min(result[i][j], result[i][j - 1] + 1);
                    }
                }
            }
        }
        return result;
    }
}
