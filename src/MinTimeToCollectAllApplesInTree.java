import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
Given an undirected tree consisting of n vertices numbered from 0 to n-1, which has some apples in their vertices. You spend 1 second to walk over one edge of the tree. Return the minimum time in seconds you have to spend in order to collect all apples in the tree starting at vertex 0 and coming back to this vertex.

The edges of the undirected tree are given in the array edges, where edges[i] = [fromi, toi] means that exists an edge connecting the vertices fromi and toi. Additionally, there is a boolean array hasApple, where hasApple[i] = true means that vertex i has an apple, otherwise, it does not have any apple.



Example 1:



Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,true,true,false]
Output: 8
Explanation: The figure above represents the given tree where red vertices have an apple. One optimal path to collect all apples is shown by the green arrows.
Example 2:



Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,false,true,false]
Output: 6
Explanation: The figure above represents the given tree where red vertices have an apple. One optimal path to collect all apples is shown by the green arrows.
Example 3:

Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,false,false,false,false,false]
Output: 0


Constraints:

1 <= n <= 10^5
edges.length == n-1
edges[i].length == 2
0 <= fromi, toi <= n-1
fromi < toi
hasApple.length == n
 */
public class MinTimeToCollectAllApplesInTree {

    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        Map<Integer, Integer> am = new HashMap<>();
        List<Integer>[] g = new ArrayList[n + 1];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            g[e[0]].add(e[1]);
            g[e[1]].add(e[0]);
        }
        for (int i = 0; i < hasApple.size(); i++) {
            if (hasApple.get(i)) {
                am.put(i, 1);
            }
        }
        return dfs(0, g, am, -1)[0];
    }

    // time and how many apples
    private int[] dfs(int i, List<Integer>[] g, Map<Integer, Integer> am, int p) {
        List<Integer> next = g[i];
        int appels = am.getOrDefault(i, 0);
        int time = 0;
        for (int nt : next) {
            if (nt == p) {
                continue;
            }
            int[] rt = dfs(nt, g, am, i);
            if (rt[1] > 0) {
                appels += rt[1];
                time += rt[0] + 2;
            }
        }
        if (appels == 0) {
            return new int[]{0, 0};
        } else {
            return new int[]{time, appels};
        }
    }
}
