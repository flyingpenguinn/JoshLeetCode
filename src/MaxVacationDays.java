import base.ArrayUtils;

import java.util.Arrays;

public class MaxVacationDays {

    // similar to assembly line: facing choice each time. do we go or do we stay

    int[][] dp;
    public int maxVacationDays(int[][] f, int[][] d) {
        int cities = d.length;
        int weeks = d[0].length;
        dp = new int[weeks][cities];
        for(int i=0; i<weeks;i++){
            Arrays.fill(dp[i], -1);
        }
        return dom(0, 0, f, d);
    }

    // week i, at city j
    int dom(int i, int j, int[][] f, int[][] d){
        int cities = d.length;
        int weeks = d[0].length;
        if(i==weeks){
            return 0;
        }
        if(dp[i][j]!= -1){
            return dp[i][j];
        }
        int max = 0;
        for(int k=0; k<cities;k++){
            if(f[j][k]==1 || j==k){
                int cur = d[k][i]+ dom(i+1, k, f, d);
                max = Math.max(max, cur);
            }
        }
        dp[i][j] = max;
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new MaxVacationDays().maxVacationDays(ArrayUtils.read("[[0,1,1],[1,0,1],[1,1,0]]"), ArrayUtils.read("[[1,3,1],[6,0,3],[3,3,3]]")));
    }
}
