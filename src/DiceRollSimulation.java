import base.ArrayUtils;

import java.util.*;

public class DiceRollSimulation {
    int Mod = 1000000007;

    long[][][] dp;

    public int dieSimulator(int n, int[] rollMax) {
        dp = new long[n][7][20];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        return (int) doc(0, 0, 0, n, rollMax);
    }

    private long doc(int i, int last, int time, int n, int[] rollMax) {
        if (i == n) {
            return 1;
        }
        if (dp[i][last][time] != -1) {
            return dp[i][last][time];
        }

        long ways = 0;
        for (int j = 0; j < rollMax.length; j++) {
            if (j == last) {
                if (time + 1 <= rollMax[j]) {
                    ways += doc(i + 1, last, time + 1, n, rollMax);
                    ways %= Mod;
                }
            } else {
                ways += doc(i + 1, j, 1, n, rollMax);
                ways %= Mod;
            }
        }
        ways %= Mod;
        dp[i][last][time] = ways;
        return ways;
    }

    public static void main(String[] args) {
        System.out.println(new DiceRollSimulation().dieSimulator(10, ArrayUtils.read1d("2,5,7,2,8,7")));
    }
}
