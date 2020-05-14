import java.util.*;

/*
LC#207
There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

Example 1:

Input: 2, [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take.
             To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: 2, [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take.
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.
Note:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.
 */
public class CourseSchedule {
    // check circle in directed graph
    List<Integer>[] g;
    int[] st;

    public boolean canFinish(int n, int[][] p) {
        g = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        st = new int[n];
        for (int[] pi : p) {
            g[pi[0]].add(pi[1]);

        }

        for (int i = 0; i < n; i++) {
            if (st[i] == 0) {
                if (!dfs(i)) {
                    return false;
                }
            }
        }
        return true;
    }

    boolean dfs(int i) {
        st[i] = 1;
        for (int j : g[i]) {
            if (st[j] == 1) {
                return false;
            }
            if (st[j] == 0) {
                if (!dfs(j)) {
                    return false;
                }
            }
        }
        st[i] = 2;
        return true;
    }

}
