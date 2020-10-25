import java.util.*;

/*
LC#1514
You are given an undirected weighted graph of n nodes (0-indexed), represented by an edge list where edges[i] = [a, b] is an undirected edge connecting the nodes a and b with a probability of success of traversing that edge succProb[i].

Given two nodes start and end, find the path with the maximum probability of success to go from start to end and return its success probability.

If there is no path from start to end, return 0. Your answer will be accepted if it differs from the correct answer by at most 1e-5.



Example 1:



Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0, end = 2
Output: 0.25000
Explanation: There are two paths from start to end, one having a probability of success = 0.2 and the other has 0.5 * 0.5 = 0.25.
Example 2:



Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.3], start = 0, end = 2
Output: 0.30000
Example 3:



Input: n = 3, edges = [[0,1]], succProb = [0.5], start = 0, end = 2
Output: 0.00000
Explanation: There is no path between 0 and 2.


Constraints:

2 <= n <= 10^4
0 <= start, end < n
start != end
0 <= a, b < n
a != b
0 <= succProb.length == edges.length <= 2*10^4
0 <= succProb[i] <= 1
There is at most one edge between every two nodes.
 */
public class PathWithMaxProbability {
    // bellman ford spfa... note turning longest path to shortest by doing 1/p and multplying them up. can also take logo of 1/p and run regular ones
    public double maxProbability(int n, int[][] edges, double[] prob, int start, int end) {
        // check null etc
        Map<Integer, Map<Integer, Double>> g = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            int es = edges[i][0];
            int ee = edges[i][1];
            g.computeIfAbsent(es, key -> new HashMap<>()).put(ee, 1.0 / prob[i]);
            g.computeIfAbsent(ee, key -> new HashMap<>()).put(es, 1.0 / prob[i]);
        }
        double[] dist = new double[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[start] = 1.0;
        Deque<Integer> q = new ArrayDeque<>();
        boolean[] inq = new boolean[n];
        q.offer(start);
        inq[start] = true;
        while (!q.isEmpty()) {
            int top = q.poll();
            inq[top] = false;
            Map<Integer, Double> cg = g.getOrDefault(top, new HashMap<>());
            for (int next : cg.keySet()) {
                double newdist = dist[top] * cg.get(next);
                if (dist[next] > newdist) {
                    dist[next] = newdist;
                    if (!inq[next]) {
                        q.offer(next);
                        inq[next] = true;
                    }
                }
            }
        }
        return dist[end] >= Long.MAX_VALUE ? 0 : 1 / dist[end];
    }
}
