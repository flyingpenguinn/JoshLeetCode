public class MaxAlternatingSubseqSum {
    private Long[][] dp;
    public long maxAlternatingSum(int[] a) {
        dp = new Long[a.length][2];
        return solve(a, 0, 0);
    }

    long solve(int[] a, int i, int pos){
        int n = a.length;
        if(i==n){
            return 0;
        }
        if(dp[i][pos] != null){
            return dp[i][pos];
        }
        long way1 = solve(a, i+1, pos);
        long way2 = (pos==0? a[i]: -a[i]) + solve(a, i+1, pos^1);
        long rt = Math.max(way1, way2);
        dp[i][pos] = rt;
        return rt;
    }
}
