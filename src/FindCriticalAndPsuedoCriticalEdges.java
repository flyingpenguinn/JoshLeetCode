import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
LC#1489
Given a weighted undirected connected graph with n vertices numbered from 0 to n-1, and an array edges where edges[i] = [fromi, toi, weighti] represents a bidirectional and weighted edge between nodes fromi and toi. A minimum spanning tree (MST) is a subset of the edges of the graph that connects all vertices without cycles and with the minimum possible total edge weight.

Find all the critical and pseudo-critical edges in the minimum spanning tree (MST) of the given graph. An MST edge whose deletion from the graph would cause the MST weight to increase is called a critical edge. A pseudo-critical edge, on the other hand, is that which can appear in some MSTs but not all.

Note that you can return the indices of the edges in any order.



Example 1:



Input: n = 5, edges = [[0,1,1],[1,2,1],[2,3,2],[0,3,2],[0,4,3],[3,4,3],[1,4,6]]
Output: [[0,1],[2,3,4,5]]
Explanation: The figure above describes the graph.
The following figure shows all the possible MSTs:

Notice that the two edges 0 and 1 appear in all MSTs, therefore they are critical edges, so we return them in the first list of the output.
The edges 2, 3, 4, and 5 are only part of some MSTs, therefore they are considered pseudo-critical edges. We add them to the second list of the output.
Example 2:



Input: n = 4, edges = [[0,1,1],[1,2,1],[2,3,1],[0,3,1]]
Output: [[],[0,1,2,3]]
Explanation: We can observe that since all 4 edges have equal weight, choosing any 3 edges from the given 4 will yield an MST. Therefore all 4 edges are pseudo-critical.


Constraints:

2 <= n <= 100
1 <= edges.length <= min(200, n * (n - 1) / 2)
edges[i].length == 3
0 <= fromi < toi < n
1 <= weighti <= 1000
All pairs (fromi, toi) are distinct.
 */
public class FindCriticalAndPsuedoCriticalEdges {
    // crit edge is those if you + weight, mst increases
    // pseudo edge is those if you - weight, mst decreases
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        pa = new int[n];
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> crit = new ArrayList<>();
        List<Integer> pcrit = new ArrayList<>();
        int mst = mst(n, edges);
        for(int i=0; i<edges.length;i++){
            int old = edges[i][2];
            edges[i][2] = old+1;
            int cur = mst(n, edges);
            edges[i][2] = old;
            if(cur>mst){
                crit.add(i);
                continue;
            }
            edges[i][2] = old-1;
            cur = mst(n, edges);
            edges[i][2] = old;
            if(cur<mst){
                pcrit.add(i);
            }

        }
        res.add(crit);
        res.add(pcrit);
        return res;
    }

    private int[] pa;


    private int mst(int n, int[][] edges){
        int[][] es = new int[edges.length][3];
        for(int i=0; i<edges.length;i++){
            es[i][0] = edges[i][0];
            es[i][1] = edges[i][1];
            es[i][2] = edges[i][2];
        }
        Arrays.sort(es, (x,y)-> Integer.compare(x[2], y[2]));
        Arrays.fill(pa, -1);

        int res = 0;
        for(int[] e: es){
            int v1 = e[0];
            int v2 = e[1];
            int p1 = find(pa, v1);
            int p2 = find(pa, v2);
            if(p1 != p2){
                pa[p1] = p2;
                res += e[2];
            }
        }

        return res;
    }

    private int find(int[] pa, int v){
        if(pa[v] == -1){
            return v;
        }
        int rt = find(pa, pa[v]);
        pa[v] = rt;
        return rt;
    }
}
