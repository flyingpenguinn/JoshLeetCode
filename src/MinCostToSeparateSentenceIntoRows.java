public class MinCostToSeparateSentenceIntoRows {
    private Integer[] dp;
    public int minimumCost(String s, int k) {
        String[] ss = s.split(" ");
        int n = ss.length;
        int[] a = new int[n];
        for(int i=0; i<n; ++i){
            a[i] = ss[i].length();
        }
        dp = new Integer[n];
        return solve(a, 0, k);
    }

    private int solve(int[] a, int i, final int k){
        int n = a.length;
        if(i==n){
            return 0;
        }
        if(dp[i] != null){
            return dp[i];
        }
        int clen = 0;
        int res = Integer.MAX_VALUE;
        for(int j=i; j<n; ++j){
            int space = (j==i)?0:1;
            clen += space+a[j];
            if(clen>k){
                break;
            }
            int ccost = (j+1)==n? 0: (k-clen)*(k-clen);
            int cur = ccost + solve(a, j+1, k);
            res = Math.min(res, cur);
        }
        dp[i] = res;
        return res;
    }
}
