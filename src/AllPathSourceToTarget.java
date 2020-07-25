import java.util.*;

public class AllPathSourceToTarget {
    public List<List<Integer>> allPathsSourceTarget(int[][] g) {
        int n = g.length;
        List<List<Integer>> r = new ArrayList<>();
        dfs(0, n-1, n, new ArrayList<>(), r, g);
        return r;
    }

    private void dfs(int i, int t, int n, List<Integer> cur, List<List<Integer>> r, int[][] g){
        cur.add(i);
        if(i==t){
            r.add(new ArrayList<>(cur));
        }else{
            for(int j: g[i]){
                dfs(j, t, n, cur, r, g);
            }
        }
        cur.remove(cur.size()-1);
    }
}
