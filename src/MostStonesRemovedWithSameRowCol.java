import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
LC#947
On a 2D plane, we place stones at some integer coordinate points.  Each coordinate point may have at most one stone.

Now, a move consists of removing a stone that shares a column or row with another stone on the grid.

What is the largest possible number of moves we can make?



Example 1:

Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
Output: 5
Example 2:

Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
Output: 3
Example 3:

Input: stones = [[0,0]]
Output: 0


Note:

1 <= stones.length <= 1000
0 <= stones[i][j] < 10000
 */
public class MostStonesRemovedWithSameRowCol {
    //all stones can be removed but the stone that you start your DFS.hence cur-1
    // In the view of DFS, a graph is explored in the structure of a tree.tree can be topologically sorted by removing leaves first
    public int removeStones(int[][] a) {
        Map<Integer, List<Integer>> rm = new HashMap<>();
        Map<Integer, List<Integer>> cm = new HashMap<>();
        int n = a.length;
        for (int i = 0; i < n; i++) {
            rm.computeIfAbsent(a[i][0], k -> new ArrayList<>()).add(i);
            cm.computeIfAbsent(a[i][1], k -> new ArrayList<>()).add(i);
        }
        boolean[] v = new boolean[n];
        int r = 0;
        for (int i = 0; i < n; i++) {
            if (!v[i]) {
                int cur = dfs(a, rm, cm, i, v);
                r += cur - 1; // -1: can't remove last stone
            }
        }
        return r;
    }

    private int dfs(int[][] a, Map<Integer, List<Integer>> rm, Map<Integer, List<Integer>> cm, int i, boolean[] v) {
        v[i] = true;
        int r = 1;
        for (int next : rm.get(a[i][0])) {
            if (!v[next]) {
                r += dfs(a, rm, cm, next, v);
            }
        }
        for (int next : cm.get(a[i][1])) {
            if (!v[next]) {
                r += dfs(a, rm, cm, next, v);
            }
        }
        return r;
    }
}
