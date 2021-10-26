import java.util.*;

public class ParallelCoursesIII {
    private Set<Integer>[] g;
    private int[] ind;
    public int minimumTime(int n, int[][] relations, int[] time) {
        g = new HashSet[n+1];
        for(int i=1; i<=n; ++i){
            g[i] = new HashSet<>();
        }
        ind = new int[n+1];
        for(var e: relations){
            g[e[0]].add(e[1]);
            ++ind[e[1]];
        }
        Deque<int[]> zeros = new ArrayDeque<>();
        for(int i=1; i<=n; ++i){
            if(ind[i]==0){
                zeros.offerLast( new int[]{i, time[i-1]});
            }
        }
        int rem = n;
        int res = 0;
        int[] start = new int[n+1];
        while(rem>0){
            Deque<int[]> newzeros = new ArrayDeque<>();
            while(!zeros.isEmpty()){
                int[] top = zeros.pollFirst();
                --rem;
                int z = top[0];
                int ct = top[1];
                res = Math.max(res, ct);
                for(int ne: g[z]){
                    --ind[ne];
                    start[ne] = Math.max(start[ne], ct);
                    if(ind[ne]==0){
                        newzeros.push(new int[]{ne, start[ne]+time[ne-1]});
                    }
                }
            }
            zeros = newzeros;
        }
        return res;
    }
}
