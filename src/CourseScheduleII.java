import base.ArrayUtils;

import java.util.*;
/*
LC#210
There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

Example 1:

Input: 2, [[1,0]]
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished
             course 0. So the correct course order is [0,1] .
Example 2:

Input: 4, [[1,0],[2,0],[3,1],[3,2]]
Output: [0,1,2,3] or [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both
             courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
             So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
Note:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.
 */

public class CourseScheduleII {
    public int[] findOrder(int n, int[][] edges) {
        // validate input....

        Map<Integer, Set<Integer>> g = buildGraph(edges);
        int[] st = new int[n];
        List<Integer> topo = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (st[i] == 0) {
                boolean cycle = dfs(g, i, st, topo);
                if (cycle) {
                    return new int[0];
                }
            }
        }

        int[] res = new int[n];
        for (int i = n-1; i >=0; i--) {
            res[i] = topo.get(n-1-i);
        }
        return res;
    }

    private Map<Integer, Set<Integer>> buildGraph(int[][] es) {
        Map<Integer, Set<Integer>> g = new HashMap<>();
        for (int[] e : es) {
            int start = e[0];
            int end = e[1];
            g.computeIfAbsent(end, k -> new HashSet<>()).add(start);
            // graph is given in reversed order so we will have to reverse here too
        }
        return g;
    }

    // return if we have circle or not here
    private boolean dfs(Map<Integer, Set<Integer>> g, int i, int[] st, List<Integer> topo) {
        st[i] = 1; // 1 means being visited
        for (int next : g.getOrDefault(i, new HashSet<>())) {
            if (st[next] == 1) {
                return true;
            } else if (st[next] == 0) {
                boolean nextCycle = dfs(g, next, st, topo);
                if (nextCycle) {
                    return true;
                }
            }
        }
        st[i] = 2;
        topo.add(i);
        return false;
    }

    public static void main(String[] args) {
        int[][] pre = ArrayUtils.read("[[1,0],[2,0],[3,1],[3,2]]");
        int n = 4;
        System.out.println(Arrays.toString(new CourseScheduleII().findOrder(n, pre)));
    }
}
