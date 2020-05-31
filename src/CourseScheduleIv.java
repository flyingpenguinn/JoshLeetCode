import java.util.*;

/*
LC#1462
There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have direct prerequisites, for example, to take course 0 you have first to take course 1, which is expressed as a pair: [1,0]

Given the total number of courses n, a list of direct prerequisite pairs and a list of queries pairs.

You should answer for each queries[i] whether the course queries[i][0] is a prerequisite of the course queries[i][1] or not.

Return a list of boolean, the answers to the given queries.

Please note that if course a is a prerequisite of course b and course b is a prerequisite of course c, then, course a is a prerequisite of course c.



Example 1:


Input: n = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
Output: [false,true]
Explanation: course 0 is not a prerequisite of course 1 but the opposite is true.
Example 2:

Input: n = 2, prerequisites = [], queries = [[1,0],[0,1]]
Output: [false,false]
Explanation: There are no prerequisites and each course is independent.
Example 3:


Input: n = 3, prerequisites = [[1,2],[1,0],[2,0]], queries = [[1,0],[1,2]]
Output: [true,true]
Example 4:

Input: n = 3, prerequisites = [[1,0],[2,0]], queries = [[0,1],[2,0]]
Output: [false,true]
Example 5:

Input: n = 5, prerequisites = [[0,1],[1,2],[2,3],[3,4]], queries = [[0,4],[4,0],[1,3],[3,0]]
Output: [true,false,true,false]


Constraints:

2 <= n <= 100
0 <= prerequisite.length <= (n * (n - 1) / 2)
0 <= prerequisite[i][0], prerequisite[i][1] < n
prerequisite[i][0] != prerequisite[i][1]
The prerequisites graph has no cycles.
The prerequisites graph has no repeated edges.
1 <= queries.length <= 10^4
queries[i][0] != queries[i][1]
 */
public class CourseScheduleIv {

    // floyd algo to get transitive closure, O(n3) for the whole graph
    public List<Boolean> checkIfPrerequisite(int n, int[][] p, int[][] q) {
        int Max = 10000000;
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Max);
        }
        for (int[] pi : p) {
            dist[pi[0]][pi[1]] = 1;
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        List<Boolean> r = new ArrayList<>();
        for (int[] qi : q) {
            if (dist[qi[0]][qi[1]] < Max) {
                r.add(true);
            } else {
                r.add(false);
            }
        }
        return r;
    }
}

class CourseScheduleIvTopoSort {
    // bfs topo sort.
    // dfs topo sort can't guarantee visiting all nodes in their topo sorting sequence
    public List<Boolean> checkIfPrerequisite(int n, int[][] p, int[][] q) {

        int[] ind = new int[n];
        List<Integer>[] g = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] pi : p) {
            g[pi[0]].add(pi[1]);
            ind[pi[1]]++;
        }
        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            if (ind[i] == 0) {
                queue.offer(i);
            }
        }
        Set<Integer>[] ans = new HashSet[n];
        for (int i = 0; i < n; i++) {
            ans[i] = new HashSet<>();
        }
        while (!queue.isEmpty()) {
            int top = queue.poll();
            for (int next : g[top]) {
                ans[next].add(top);
                ans[next].addAll(ans[top]);
                // add the ancestors of the father too. this is at most n
                ind[next]--;
                if (ind[next] == 0) {
                    // only add to queue when in degree is 0. this happens only once for each node, so no need for visited array
                    queue.offer(next);
                }
            }
        }
        List<Boolean> r = new ArrayList<>();
        for (int[] qi : q) {
            if (ans[qi[1]].contains(qi[0])) {
                r.add(true);
            } else {
                r.add(false);
            }
        }
        return r;
    }
}
