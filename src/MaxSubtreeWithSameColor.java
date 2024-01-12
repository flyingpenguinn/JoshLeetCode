import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MaxSubtreeWithSameColor {
    private Map<Integer, Set<Integer>> tree = new HashMap<>();
    private int[] cl;
    private int res = 1;
    private int[] dfs(int i, int p){
        int cur = cl[i];
        int subtree = 1;
        boolean bad = false;
        for(int ne: tree.getOrDefault(i, new HashSet<>()) ){
            if(ne==p){
                continue;
            }
            int[] later = dfs(ne, i);
            int nodes = later[0];
            int color = later[1];
            if(color != cur){
                bad = true;
            }
            subtree += nodes;
        }
        if(!bad){
            res = Math.max(res, subtree);
            return new int[]{subtree, cur};
        }else{
            return new int[]{-1, -1};
        }

    }
    public int maximumSubtreeSize(int[][] edges, int[] colors) {
        cl = colors;
        for(int[] e: edges){
            int v1 = e[0];
            int v2 = e[1];
            tree.computeIfAbsent(v1, k-> new HashSet<>()).add(v2);
            tree.computeIfAbsent(v2, k-> new HashSet<>()).add(v1);
        }
        dfs(0, -1);
        return res;
    }
}
