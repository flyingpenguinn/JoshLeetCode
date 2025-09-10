import java.util.ArrayDeque;
import java.util.Deque;

public class NumberOfPeopleAwareOfSecret {
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        long[] dp = new long[n+1];
        dp[1] = 1;
        long active = 0;
        int mod = (int) (1e9+7);
        for(int i=2; i<=n; ++i){
            if(i-delay>=1){
                //  cout<<"day="<<i<<" got new from "<<(i-delay)<<" new active "<<dp[i-delay]<<endl;
                active += dp[i-delay];
                active %= mod;
            }
            if(i-forget>=1){
                //   cout<<"day="<<i<<" lost new from "<<(i-forget)<<" lost active "<<dp[i-forget]<<endl;
                active -= dp[i-forget];
                active %= mod;
                if(active<0){
                    active += mod;
                }
            }
            dp[i] = active;
            //  cout<<"after day "<<i<<" dpi="<<dp[i]<<endl;
        }
        long res = 0;
        for(int i=Math.max(1, n-forget+1); i<=n; ++i){
            res += dp[i];
            res %= mod;
        }
        return (int) res;
    }
}
