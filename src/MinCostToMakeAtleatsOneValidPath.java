import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.PriorityQueue;

/*
LC#1368
Given a m x n grid. Each cell of the grid has a sign pointing to the next cell you should visit if you are currently in this cell. The sign of grid[i][j] can be:
1 which means go to the cell to the right. (i.e go from grid[i][j] to grid[i][j + 1])
2 which means go to the cell to the left. (i.e go from grid[i][j] to grid[i][j - 1])
3 which means go to the lower cell. (i.e go from grid[i][j] to grid[i + 1][j])
4 which means go to the upper cell. (i.e go from grid[i][j] to grid[i - 1][j])
Notice that there could be some invalid signs on the cells of the grid which points outside the grid.

You will initially start at the upper left cell (0,0). A valid path in the grid is a path which starts from the upper left cell (0,0) and ends at the bottom-right cell (m - 1, n - 1) following the signs on the grid. The valid path doesn't have to be the shortest.

You can modify the sign on a cell with cost = 1. You can modify the sign on a cell one time only.

Return the minimum cost to make the grid have at least one valid path.



Example 1:


Input: grid = [[1,1,1,1],[2,2,2,2],[1,1,1,1],[2,2,2,2]]
Output: 3
Explanation: You will start at point (0, 0).
The path to (3, 3) is as follows. (0, 0) --> (0, 1) --> (0, 2) --> (0, 3) change the arrow to down with cost = 1 --> (1, 3) --> (1, 2) --> (1, 1) --> (1, 0) change the arrow to down with cost = 1 --> (2, 0) --> (2, 1) --> (2, 2) --> (2, 3) change the arrow to down with cost = 1 --> (3, 3)
The total cost = 3.
Example 2:


Input: grid = [[1,1,3],[3,2,2],[1,1,4]]
Output: 0
Explanation: You can follow the path from (0, 0) to (2, 2).
Example 3:


Input: grid = [[1,2],[4,3]]
Output: 1
Example 4:

Input: grid = [[2,2,2],[2,2,2]]
Output: 3
Example 5:

Input: grid = [[4]]
Output: 0


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 100
 */
public class MinCostToMakeAtleatsOneValidPath {
    // convert to shortest path, then dijkastra.
    // note it's not a dag so can't dp
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    int Max = 1000000000;

    public int minCost(int[][] g) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
        pq.offer(new int[]{0, 0, 0});
        int m = g.length;
        int n = g[0].length;
        boolean[][] done = new boolean[m][n];
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dist[i], Max);
        }
        dist[0][0] = 0;
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int i = top[0];
            int j = top[1];
            if (done[i][j]) {
                continue;
            }
            done[i][j] = true;
            if (i == m - 1 && j == n - 1) {
                // in dijkatra we can terminate as soon as seeing the result
                break;
            }
            for (int k = 0; k < 4; k++) {
                int cost = g[i][j] == k + 1 ? 0 : 1;
                int ni = i + dirs[k][0];
                int nj = j + dirs[k][1];
                int newcost = cost + top[2];
                if (ni >= 0 && ni < m && nj >= 0 && nj < n) {
                    if (dist[ni][nj] > newcost) {
                        dist[ni][nj] = newcost;
                        pq.offer(new int[]{ni, nj, newcost});
                    }
                }
            }
        }
        return dist[m - 1][n - 1];
    }
}

class MinCostToMakeAtleatsOneValidPath01Variation {
    //we can put 0 in the front and 1 in the back. but note the way we set done is still dijkastra style not bfs style
    // nodes can enter the queue more than once
    // faster than spfa because we terminate earlier but the overall complexity is 16om the same
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    int Max = 1000000000;

    public int minCost(int[][] g) {
        Deque<int[]> pq = new ArrayDeque<>();
        pq.offer(new int[]{0, 0, 0});
        int m = g.length;
        int n = g[0].length;
        boolean[][] done = new boolean[m][n];
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dist[i], Max);
        }
        dist[0][0] = 0;
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int i = top[0];
            int j = top[1];
            if (done[i][j]) {
                continue;
            }
            done[i][j] = true;
            if (i == m - 1 && j == n - 1) {
                // in dijkatra we can terminate as soon as seeing the result
                break;
            }
            for (int k = 0; k < 4; k++) {
                int cost = g[i][j] == k + 1 ? 0 : 1;
                int ni = i + dirs[k][0];
                int nj = j + dirs[k][1];
                int newcost = cost + top[2];
                if (ni >= 0 && ni < m && nj >= 0 && nj < n) {
                    if (dist[ni][nj] > newcost) {
                        dist[ni][nj] = newcost;
                        if (cost == 1) {
                            pq.offer(new int[]{ni, nj, newcost});
                        } else {
                            pq.offerFirst(new int[]{ni, nj, newcost});
                        }
                    }
                }
            }
        }
        return dist[m - 1][n - 1];
    }
}


class MinCostMakeValidPathSpfa {
    // spfa can do the job too
    // complexity == n*possible enqueue times * fan out of ever node = n*4*4 = 16n. n is the node count
    // in worst case it's ne = n^3 but hard to hit must be very dense graph
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    int Max = 1000000000;

    public int minCost(int[][] g) {
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0});
        int m = g.length;
        int n = g[0].length;
        int[][] dist = new int[m][n];
        boolean[][] inq = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dist[i], Max);
        }
        dist[0][0] = 0;
        inq[0][0] = true;

        while (!q.isEmpty()) {
            int[] top = q.poll();
            int i = top[0];
            int j = top[1];
            inq[i][j] = false;
            // dont return target in spfa it could be relaxed later
            for (int k = 0; k < 4; k++) {
                int cost = g[i][j] == k + 1 ? 0 : 1;
                int ni = i + dirs[k][0];
                int nj = j + dirs[k][1];
                int newcost = cost + dist[i][j];
                if (ni >= 0 && ni < m && nj >= 0 && nj < n && dist[ni][nj] > newcost) {
                    dist[ni][nj] = newcost;
                    if (!inq[ni][nj]) {
                        inq[ni][nj] = true;
                        q.offer(new int[]{ni, nj, newcost});
                    }
                }
            }
        }
        return dist[m - 1][n - 1];
    }
}