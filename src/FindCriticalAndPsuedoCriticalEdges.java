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
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] es) {
        List<Integer> crit = new ArrayList<>();
        List<Integer> pcrit = new ArrayList<>();
        int[][] nes = copyof(es);
        int mst = mst(n,nes);
        for(int i=0; i<es.length;i++){
            nes = copyof(es);
            nes[i][2]++;
            int cur = mst(n,nes);
            if(cur>mst){
                crit.add(i);
            }else{
                nes = copyof(es);
                nes[i][2]--;
                cur = mst(n, nes);
                if(cur<mst){
                    pcrit.add(i);
                }
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        res.add(crit);
        res.add(pcrit);
        return res;
    }

    private int[][] copyof(int[][] input){
        int[][] es = new int[input.length][3];
        for(int i=0; i<input.length;i++){
            es[i] = Arrays.copyOf(input[i], 3);
        }
        return es;
    }

    private int mst(int n, int[][] es){
        int[] pa = new int[n];
        Arrays.sort(es, (x,y)-> Integer.compare(x[2], y[2]));
        Arrays.fill(pa, -1);
        int res = 0;
        for(int i=0; i<es.length;i++){
            int v1 = es[i][0];
            int v2 = es[i][1];
            int pv1 = find(pa, v1);
            int pv2 = find(pa, v2);
            if(pv1==pv2){
                continue;
            }
            res += es[i][2];
            pa[pv1] = pv2;
        }
        return res;
    }

    private int find(int[] pa, int i){
        if(pa[i] == -1){
            return i;
        }
        int rt = find(pa, pa[i]);
        pa[i] = rt;
        return rt;
    }
}
