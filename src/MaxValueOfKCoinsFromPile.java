import base.ArrayUtils;

import java.util.List;

public class MaxValueOfKCoinsFromPile {
    private Integer[][] dp;
    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        dp = new Integer[piles.size()][k+1];
        return solve(0, k, piles);
    }

    private int solve(int i, int k, List<List<Integer>> piles) {
        int n = piles.size();
        if(i==n){
            return k==0?0: Integer.MIN_VALUE;
        }
        int ok = k;
        if(dp[i][k] != null){
            return dp[i][k];
        }
        List<Integer> cp = piles.get(i);
        int sum = 0;
        int max = solve(i+1, k, piles);
        for(int j=0; j<cp.size() && k>0; ++j){
            sum += cp.get(j);
            --k;
            int cur = sum + solve(i+1, k, piles);
            max = Math.max(max, cur);
        }
        dp[i][ok] = max;
        return max;
    }

    public static void main(String[] args) {
        List<List<Integer>> p = ArrayUtils.readAsListUneven("[[1,100,3],[7,8,9]]");
        System.out.println(new MaxValueOfKCoinsFromPile().maxValueOfCoins(p, 2));
    }

}
