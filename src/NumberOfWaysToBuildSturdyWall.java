import base.ArrayUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NumberOfWaysToBuildSturdyWall {

    private long mod = (long)(1e9+7);
    private Set<Integer> set = new HashSet<>();
    private Long[][] dp;

    public int buildWall(int h, int w, int[] a) {
        dfs(w, a, 0, 0);
        List<Integer> chs = new ArrayList<>(set);
        dp = new Long[h][(1<<w)];
        return (int) solve(0, 0, h, w,chs);
    }

    // at row i, last choice is j
    private long solve(int i, int j, int h, int w,List<Integer> chs) {
        if(i==h){
            return 1L;
        }
        if(dp[i][j] != null){
            return dp[i][j];
        }
        long res = 0;
        for(int k=0; k<chs.size(); ++k){
            boolean bad = false;
            if((j & chs.get(k)) != 0){
                continue;
            }
            if(!bad){
                res += solve(i+1, chs.get(k), h, w, chs);
                res %= mod;
            }
        }
        dp[i][j]  = res;
        return res;
    }

    private void dfs(int w, int[] a, int i, int j) {
        if(i==w){
            set.add(j);
            return;
        }
        for(int k=0; k<a.length; ++k){
            int ni = i+a[k];
            if(ni>w){
                continue;
            }
            int nj = j;
            if(ni<w) {
                nj = j | (1 << ni);
            }
            dfs(w, a, ni, nj);
        }
    }

    public static void main(String[] args) {
        System.out.println(new NumberOfWaysToBuildSturdyWall().buildWall(61,10, ArrayUtils.read1d("7,5,4,10,6,8,9,3,2,1")));
    }
}
