import java.util.Arrays;

public class NumberOfLongestIncreasingSubseq {
    int[][]dp;
    public int findNumberOfLIS(int[] a) {
        int max=0;
        int maxc=0;
        dp= new int[a.length][2];
        Arrays.fill(dp,null);
        for(int i=0;i<a.length;i++){
            int[] r= dom(a,i);
            if(r[0]>max){
                max=r[0];
                maxc= r[1];
            } else if(r[0]==max){
                maxc += r[1];
            }
        }
        return maxc;

    }

    // len of longest and count
    int[] dom(int[] a, int i){
        if(dp[i]!=null){
            return dp[i];
        }
        int imax=1;
        int imaxc=1;
        for(int j=i+1;j<a.length;j++){
            if(a[j]>a[i]){
                int[] cur= dom(a,j);

                int curl= cur[0]+1;
                if(curl>imax){
                    imax=curl;
                    imaxc=cur[1];
                }else if(curl==imax){
                    imaxc+=cur[1];
                }
            }
        }

        //  System.out.println(i+" "+imax+" "+imaxc);
        dp[i]= new int[]{imax,imaxc};
        return dp[i];
    }
}
