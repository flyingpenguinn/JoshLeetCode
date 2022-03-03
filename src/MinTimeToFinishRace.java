import base.ArrayUtils;

import java.util.Arrays;

public class MinTimeToFinishRace {
    private int Max = (int) 1e9;
    private int limit = 20;

    public int minimumFinishTime(int[][] a, int changeTime, int numLaps) {
        int n = a.length;
        int[] dp = new int[numLaps + 1];
        int[][] nochange = new int[n][limit + 1];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(nochange[i], Max);
            nochange[i][1] = a[i][0];
            for (int j = 2; j <= limit; ++j) {
                long nc = 1L * nochange[i][j - 1] * a[i][1];
                if (nc > Max) {
                    break;
                }
                nochange[i][j] = (int) nc;
            }
            for(int j=2; j<=limit; ++j){
                if(nochange[i][j-1] < Max){
                    nochange[i][j] += nochange[i][j-1];
                }else{
                    break;
                }
            }
        }
        Arrays.fill(dp, Max);
        for (int i = 0; i < n; ++i) {
            dp[1] = Math.min(dp[1], a[i][0]);
        }
        dp[0] = -1;
        for (int j = 2; j <= numLaps; ++j) {
            if (j <= limit) {
                for (int i = 0; i < n; ++i) {
                    dp[j] = Math.min(dp[j], nochange[i][j]);
                }
            }
            for (int i = j - 1; i >= Math.max(j - limit, 1); --i) {
                dp[j] = Math.min(dp[j], dp[i] + changeTime + dp[j - i]);
            }

        }
        return dp[numLaps];
    }

    public static void main(String[] args) {
        System.out.println(new MinTimeToFinishRace().minimumFinishTime(ArrayUtils.read("[[36,5],[32,5],[88,8],[11,4],[52,2],[2,2],[90,5],[49,6],[68,9],[77,3],[42,7],[17,3],[73,7],[89,2],[92,9],[40,7],[71,8],[79,3],[55,6],[77,9],[14,3],[87,10],[4,2],[63,7],[79,8],[3,9],[44,2],[49,9],[91,3],[58,6],[62,3],[72,7],[97,6],[29,5],[88,9],[40,8],[36,4],[82,8],[53,8],[26,2],[26,6],[92,2],[46,2],[75,6],[85,2],[6,10],[12,4],[15,4]]"
                ), 24, 3));
    }
}
