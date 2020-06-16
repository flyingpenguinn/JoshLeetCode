import base.ArrayUtils;

import java.util.PriorityQueue;

/*
LC#407
Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map, compute the volume of water it is able to trap after raining.



Note:

Both m and n are less than 110. The height of each unit cell is greater than 0 and is less than 20,000.



Example:

Given the following 3x6 height map:
[
  [1,4,3,1,3,2],
  [3,2,1,3,2,4],
  [2,3,3,2,3,1]
]

Return 4.


The above image represents the elevation map [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]] before the rain.





After the rain, water is trapped between the blocks. The total volume of water trapped is 4.
 */
public class TrappingRainWaterII {

    /* we can't just take 4 directions of a spot because water can flow down for example

   [
    [12,13,1,12],
    [13,4,13,12],
    [13,8,10,12],
    [12,13,12,12],
    [13,13,13,13]]

   at pos 1,1 = 4 the water we can accmulate is not 13-4 but 12-4 because it can leak to the 12 at pos 2,3

   this is a natural extension of queue based solution in 1d


    */

    // first put all "walls" into pq. then check its four neighbors. for smaller neighbor, let that small one carry the height of the wall
    // for bigger ones, replace current wall anyway
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int trapRainWater(int[][] a) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[2], y[2]));
        int m = a.length;
        int n = a[0].length;
        boolean[][] v = new boolean[m][n];
        for (int j = 0; j < n; j++) {
            if (!v[0][j]) {
                v[0][j] = true;
                pq.offer(new int[]{0, j, a[0][j]});
            }
        }
        for (int i = 0; i < m; i++) {
            if (!v[i][0]) {
                v[i][0] = true;
                pq.offer(new int[]{i, 0, a[i][0]});
            }
        }
        for (int j = 0; j < n; j++) {
            if (!v[m - 1][j]) {
                v[m - 1][j] = true;
                pq.offer(new int[]{m - 1, j, a[m - 1][j]});
            }
        }
        for (int i = 0; i < m; i++) {
            if (!v[i][n - 1]) {
                v[i][n - 1] = true;
                pq.offer(new int[]{i, n - 1, a[i][n - 1]});
            }
        }
        int r = 0;
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int i = top[0];
            int j = top[1];
            for (int[] d : dirs) {
                int ni = i + d[0];
                int nj = j + d[1];
                if (ni >= 0 && ni < m && nj >= 0 && nj < n && !v[ni][nj]) {
                    v[ni][nj] = true;
                    if (top[2] > a[ni][nj]) {
                        r += top[2] - a[ni][nj];
                        pq.offer(new int[]{ni, nj, top[2]});
                        // key! as if let the wall move in if we meet a smaller cell
                    } else {
                        pq.offer(new int[]{ni, nj, a[ni][nj]});
                        // otherwise we met a "better" wall
                    }
                }
            }
        }
        return r;
    }


    public static void main(String[] args) {
        int[][] h = ArrayUtils.read("[[5,5,5,1],[5,1,1,5],[5,1,5,5],[5,2,5,8]]");
        System.out.println(new TrappingRainWaterII().trapRainWater(h));
    }
}

