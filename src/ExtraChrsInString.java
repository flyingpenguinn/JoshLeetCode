import java.util.HashSet;
import java.util.Set;

public class ExtraChrsInString {
    private Set<String> set = new HashSet<>();
    private Integer[] dp;
    public int minExtraChar(String s, String[] dictionary) {
        for(String ds: dictionary){
            set.add(ds);
        }
        int n = s.length();
        dp = new Integer[n];
        return solve(s, 0);
    }

    private int solve(String s, int i){
        int n = s.length();
        if(i==n){
            return 0;
        }
        if(dp[i] != null){
            return dp[i];
        }
        int res = 1 + solve(s, i+1);
        StringBuilder sb = new StringBuilder();
        for(int j=i; j<n; ++j){
            sb.append(s.charAt(j));
            String sbs = sb.toString();
            if(set.contains(sbs)){
                int cur = solve(s, j+1);
                res = Math.min(cur, res);
            }
        }
        dp[i] = res;
        return res;
    }
}
