import base.ArrayUtils;

import java.util.*;

/*
LC#1168
There are n houses in a village. We want to supply water for all the houses by building wells and laying pipes.

For each house i, we can either build a well inside it directly with cost wells[i], or pipe in water from another well to it. The costs to lay pipes between houses are given by the array pipes, where each pipes[i] = [house1, house2, cost] represents the cost to connect house1 and house2 together using a pipe. Connections are bidirectional.

Find the minimum total cost to supply water to all houses.



Example 1:



Input: n = 3, wells = [1,2,2], pipes = [[1,2,1],[2,3,1]]
Output: 3
Explanation:
The image shows the costs of connecting houses using pipes.
The best strategy is to build a well in the first house with cost 1 and connect the other houses to it with cost 2 so the total cost is 3.


Constraints:

1 <= n <= 10000
wells.length == n
0 <= wells[i] <= 10^5
1 <= pipes.length <= 10000
1 <= pipes[i][0], pipes[i][1] <= n
0 <= pipes[i][2] <= 10^5
pipes[i][0] != pipes[i][1]
 */
public class OptimizeWaterDistributionInVillage {

    // mst, could be many of them. water must come from well, so assign node 0 to be a well
    public int minCostToSupplyWater(int n, int[] w, int[][] pp) {
        List<int[]> edges = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            edges.add(new int[]{0, i, w[i - 1]});
        }
        for (int i = 0; i < pp.length; i++) {
            edges.add(pp[i]);
        }
        // kruskal, sort edges first
        Collections.sort(edges, (a, b) -> Integer.compare(a[2], b[2]));
        int[] pa = new int[n + 1];
        Arrays.fill(pa, -1);
        int r = 0;
        for (int[] pi : edges) {
            int i = pi[0];
            int j = pi[1];
            int edge = pi[2];
            boolean rt = union(i, j, pa);
            if (rt) {
                r += edge;
            }
        }
        return r;
    }

    // i=1...n
    int find(int i, int[] pa) {
        if (pa[i] == -1) {
            return i;
        }
        int r = find(pa[i], pa);
        pa[i] = r;
        return r;
    }

    boolean union(int i, int j, int[] pa) {
        int pi = find(i, pa);
        int pj = find(j, pa);
        if (pi == pj) {
            return false;
        }
        pa[pi] = pj;// note must be pi == pj
        return true;
    }
}
