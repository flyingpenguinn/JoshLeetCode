import java.util.Arrays;

public class DeleteColumnMakeSortedIII {
    int[][] dp;

    // if we have violation at col j s[j]<s[j-1] we will have to cut
    // otherwise we can cut(for later) or keep
    public int minDeletionSize(String[] a) {
        int c= a[0].length();

        dp=new int[c][c+1];
        for(int i=0;i<c;i++){
            Arrays.fill(dp[i],-1);
        }
        return domin(a,0,-1);
    }

    //min cost if start from i [0..last] sorted for all strings

    int domin(String[]a, int j,int last){
        int c= a[0].length();
        if(j==c){
            return 0;
        }
        if(dp[j][last+1]!=-1){
            return dp[j][last+1];
        }

        // cut
        int rt = 1+domin(a,j+1,last);
        boolean cankeep=true;

        for(int i=0;i<a.length;i++){
            if(last==-1){
                break;
            }
            if(a[i].charAt(j)<a[i].charAt(last)){
                cankeep=false;
                break;
            }
        }
        if(cankeep){
            int keep= domin(a,j+1,j);
            rt= Math.min(rt,keep);
        }
        dp[j][last+1]=rt;
        return rt;
    }
}
