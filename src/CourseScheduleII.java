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
    // topo sort usually comes with circle detection!
    int[] r;
    int rp;
    int[] v;
    List<Integer>[] g;
    boolean cyc = false;

    public int[] findOrder(int n, int[][] p) {
        r = new int[n];
        rp = n - 1;
        g = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int i = 0; i < p.length; i++) {
            g[p[i][1]].add(p[i][0]);
        }
        v = new int[n];
        for (int i = 0; i < n; i++) {
            if (v[i] == 0) {
                dfs(i);
            }
        }
        return cyc ? new int[0] : r;

    }

    void dfs(int i) {
        v[i] = 1;
        for (int j : g[i]) {
            int vj = v[j];
            if (vj == 0) {
                dfs(j);
            } else if (vj == 1) {
                cyc = true;
                return;
            }
        }
        v[i] = 2;
        r[rp--] = i;
    }

    public static void main(String[] args) {
        int[][] pre = ArrayUtils.read("[[1,0],[2,0],[3,1],[3,2]]");
        int n = 4;
        System.out.println(Arrays.toString(new CourseScheduleII().findOrder(n, pre)));
    }
}
