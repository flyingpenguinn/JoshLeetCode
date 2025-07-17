import java.util.Arrays;

public class MinimizeMaxComponentCost {
    private int[] pa;
    private int find(int i){
        if(pa[i] == -1){
            return i;
        }
        int rt = find(pa[i]);
        pa[i] = rt;
        return rt;
    }
    private void union(int i, int j){
        int ai = find(i);
        int aj = find(j);
        if(ai == aj){
            return;
        }
        pa[ai] = aj;
    }
    private int comps(int[][] es, int lim,int n){
        pa = new int[n];
        Arrays.fill(pa, -1);
        //  System.out.println("lim="+lim);
        for(int[] e: es){
            int u = e[0];
            int v = e[1];
            int w = e[2];
            if(w>lim){
                continue;
            }
            union(u, v);
        }
        int res = 0;
        for(int i=0; i<n; ++i){
            if(pa[i]==-1){
                ++res;
            }
        }
        return res;
    }
    public int minCost(int n, int[][] edges, int k) {
        int l = 0;
        int u = 0;
        for(int[] e: edges){
            u = Math.max(u, e[2]);
        }
        while(l<=u){
            int mid = l+(u-l)/2;
            if(comps(edges, mid, n) <= k){
                u = mid-1;
            }else{
                l = mid+1;
            }
        }
        return l;
    }
}
