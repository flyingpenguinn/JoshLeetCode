import java.util.Arrays;

public class PathSumIv {
    
    private int res = 0;
    public int pathSum(int[] a) {
        int n = a.length;
        int[] t = new int[32];
        Arrays.fill(t, -1);
        for(int i=0; i<n; ++i){
            int dep = a[i]/100;
            int seq = (a[i]-100*dep)/10;
            int v = a[i]%10;
            int index = (1<<(dep-1)) + seq-1;          
            t[index] = v;
        }
        dfs(t, 1, 0);
        return res;
    }

    private void dfs(int[] t, int i, int csum){        
        if(t[i]==-1){
            return;
        }
      //  System.out.println("i="+i+" csum="+csum);
        csum += t[i];
        int li = 2*i;
        int ri = 2*i+1;
        if(t[li] == -1 && t[ri] ==-1){
     //       System.out.println("setting. i="+i+" csum="+csum);
            res += csum;
            return;
        }
        dfs(t, li, csum);
        dfs(t, ri, csum);
    }
}
