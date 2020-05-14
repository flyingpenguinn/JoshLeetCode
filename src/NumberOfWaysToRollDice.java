import java.util.Arrays;

public class NumberOfWaysToRollDice {
    int Mod = 1000000007;
    int[][] dp;
    public int numRollsToTarget(int d, int f, int target) {
        dp = new int[d+1][target+1];
        // f faces, d rolls
        for(int i=0; i<dp.length;i++) {
            Arrays.fill(dp[i], -1);
        }
        return dor(d, target, f);
    }

    private int dor(int d,  int target,int f) {
        if (target < 0) {
            return 0;
        }
        if (d == 0) {
            return target==0?1:0;
        }
        if(dp[d][target]!=-1){
            return dp[d][target];
        }
        int ways = 0;
        for (int j = 1; j <= f; j++) {
            ways += dor(d - 1, target-j, f);
            ways %= Mod;
        }
        dp[d][target] = ways;
        return ways;
    }

    public static void main(String[] args) {
        System.out.println(new NumberOfWaysToRollDice().numRollsToTarget(30,30,500));
    }
}
