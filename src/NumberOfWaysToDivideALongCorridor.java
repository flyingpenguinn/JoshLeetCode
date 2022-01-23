import java.util.Arrays;

public class NumberOfWaysToDivideALongCorridor {
    private long Mod = 1000000007;
    private long[][] dp;

    public int numberOfWays(String corridor) {

        dp = new long[corridor.length()][3];
        for(int i=0; i<dp.length;++i){
            Arrays.fill(dp[i], -1);
        }
        return (int) solve(corridor, 0, 0);
    }

    private long solve(String s, int i, int seats) {
        int n = s.length();
        if (i == n) {
            return seats == 2 ? 1 : 0;
        }
        if (dp[i][seats] != -1) {
            return dp[i][seats];
        }
        long cur = 0;
        if (s.charAt(i) == 'P') {
            if (seats == 2) {
                cur += solve(s, i + 1, 0);
                cur += solve(s, i + 1, seats);
                cur %= Mod;
            }else{
                cur = solve(s, i + 1, seats);
            }
        } else {
            if (seats == 2) {
                cur = solve(s, i + 1, 1);
            } else {
                cur = solve(s, i + 1, seats+1);
            }
        }
        dp[i][seats] = cur;
        return cur;
    }
}
