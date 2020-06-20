import java.util.ArrayDeque;
import java.util.Deque;

/*
LC#934
In a given 2D binary array A, there are two islands.  (An island is a 4-directionally connected group of 1s not connected to any other 1s.)

Now, we may change 0s to 1s so as to connect the two islands together to form 1 island.

Return the smallest number of 0s that must be flipped.  (It is guaranteed that the answer is at least 1.)



Example 1:

Input: A = [[0,1],[1,0]]
Output: 1
Example 2:

Input: A = [[0,1,0],[0,0,0],[0,0,1]]
Output: 2
Example 3:

Input: A = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
Output: 1


Constraints:

2 <= A.length == A[0].length <= 100
A[i][j] == 0 or A[i][j] == 1
 */
public class ShortestBridge {
    // we extend to wider orbit one dist at a time till we hit another 1
    // a bit similar to "bus route": we grow one dist at a time from a "core"
    Deque<int[]> q = new ArrayDeque<>();
    int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public int shortestBridge(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        // double break here....
        boolean found = false;
        for (int i = 0; i < m; i++) {
            if (found) {
                break;
            }
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 1) {
                    dfs(a, i, j);
                    found = true;
                    break;
                }
            }
        }
        while (!q.isEmpty()) {
            int[] top = q.poll();
            int i = top[0];
            int j = top[1];
            int dist = top[2];
            int ndist = dist + 1;
            for (int[] d : dirs) {
                int ni = i + d[0];
                int nj = j + d[1];
                if (ni >= 0 && ni < m && nj >= 0 && nj < n) {
                    if (a[ni][nj] == 0) {
                        a[ni][nj] = 2;
                        q.offer(new int[]{ni, nj, ndist});
                    } else if (a[ni][nj] == 1) {
                        return dist;
                    }
                }
            }
        }
        return -1;
    }

    void dfs(int[][] a, int i, int j) {
        int m = a.length;
        int n = a[0].length;
        a[i][j] = 2;
        q.offer(new int[]{i, j, 0});
        for (int[] d : dirs) {
            int ni = i + d[0];
            int nj = j + d[1];
            if (ni >= 0 && ni < m && nj >= 0 && nj < n && a[ni][nj] == 1) {
                dfs(a, ni, nj);
            }
        }
    }
}
