import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MaxNumOfKDivisibleComponents {
    private Map<Integer, Set<Integer>> t = new HashMap<>();
    private int[] v;
    private int k;
    private int res = 0;
    public int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
        for(int[] e: edges){
            int v1 = e[0];
            int v2 = e[1];
            t.computeIfAbsent(v1, p-> new HashSet<>()).add(v2);
            t.computeIfAbsent(v2, p-> new HashSet<>()).add(v1);
        }
        v = values;
        this.k = k;
        dfs(0, -1);
        return res;
    }

    private long dfs(int i, int p){
        long cur = v[i];
        for(int ne: t.getOrDefault(i, new HashSet<>())){
            if(ne==p){
                continue;
            }
            long later = dfs(ne, i);
            cur += later;
        }
        if(cur%k==0){
            ++res;
        }
        return cur;
    }
}
