public class DivisorGame {
    // could just return n%2==0
    int[] dp;
    public boolean divisorGame(int n) {
        dp= new int[n+1];
        return dod(n);
    }

    // whether sb can win facing n
    boolean dod(int n){
        if(n<=1){
            return false;
        }
        if(dp[n]!=0){
            return dp[n]==1;
        }
        boolean rt=false;
        // just need till i*i
        for(int i=1;i*i<=n;i++){

            if(n%i==0){
                if(!dod(n-i) || (i!=1 &&!dod(n-n/i))){
                    rt=true;
                    break;
                }

            }

        }
        dp[n]= rt?1:2;
        return rt;
    }
}
