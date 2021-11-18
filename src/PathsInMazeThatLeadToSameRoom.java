import java.util.HashSet;
import java.util.Set;

public class PathsInMazeThatLeadToSameRoom {
    private int res = 0;

    private Set<Integer>[] g;
    private Set<Integer>[] rg;
    public int numberOfPaths(int n, int[][] corridors) {
        g = new HashSet[n+1];
        rg = new HashSet[n+1];
        for(int i=1; i<=n; ++i){
            g[i] = new HashSet<Integer>();
            rg[i] = new HashSet<Integer>();
        }
        for(int[] c: corridors){
            int min = Math.min(c[0], c[1]);
            int max = Math.max(c[0], c[1]);
            g[min].add(max);
            rg[max].add(min);
        }

        for(int i=1; i<=n; ++i){
            dfs(i, 1, i);
        }
        return res;
    }

    private void dfs(int i, int len, int start){

        if(len==3){
            if(rg[i].contains(start)){
                ++res;
            }
            return;
        }
        for(int ne: g[i]){
            dfs(ne, len+1, start);
        }
    }
}
