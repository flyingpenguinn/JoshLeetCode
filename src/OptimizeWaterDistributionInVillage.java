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

    // make 0-i an edge too, thus becoming an MST problem
    public int minCostToSupplyWater(int n, int[] wells, int[][] es) {
        List<int[]> ses = new ArrayList<>();
        for(int[] e: es){
            ses.add(e);
        }
        for(int i=0; i< wells.length; i++){
            ses.add(new int[]{0, i+1, wells[i]});
        }
        Collections.sort(ses, (x,y) -> Integer.compare(x[2], y[2]));
        int[] pa = new int[n+1];
        int[] size = new int[n+1];
        Arrays.fill(pa, -1);
        Arrays.fill(size, 1);
        int res = 0;
        for(int i=0; i<ses.size();i++){
            int[] top = ses.get(i);
            if(union(pa, size, top[0], top[1])){
                res += top[2];
            }
        }
        return res;
    }

    private int find(int[] pa, int i){
        if(pa[i] == -1){
            return i;
        }
        int ri = find(pa, pa[i]);
        pa[i] = ri;
        return ri;
    }

    private boolean union(int[] pa, int[] size, int i, int j){
        int ri = find(pa, i);
        int rj = find(pa, j);
        if(ri==rj){
            return false;
        }
        if(size[ri] < size[rj]){
            pa[ri] = rj;
            size[rj] += size[ri];
        }else{
            pa[rj] = ri;
            size[ri] += size[rj];
        }
        return true;
    }
}
