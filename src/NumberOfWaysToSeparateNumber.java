public class NumberOfWaysToSeparateNumber {
    // we use common length to solve the problem of comparison between pre and i: we only compare pre+commlen vs i+commlen
    private long Mod = 1000000007;

    public int numberOfCombinations(String s) {
        int n = s.length();
        int[][] dp = new int[n + 1][n];
        int[][] sum = new int[n + 1][n];
        int[][] comm = new int[n+1][n+1];
        for(int i=n-1; i>=0; i--){
            for(int j=n-1; j>=0; j--){
                if(s.charAt(i)==s.charAt(j)){
                    comm[i][j] = 1+comm[i+1][j+1];
                }else{
                    comm[i][j] = 0;
                }
            }
        }
        for (int i = n; i >= 0; i--) {
            for (int pre = 0; pre <= Math.max(0, i - 1); pre++) {
                if (i == n) {
                    dp[i][pre] = 1;
                } else if (s.charAt(i) != '0') {
                    int prelen = i - pre;
                    long res = 0;
                    if (prelen > 0 && i + prelen - 1 < n && noSmall(s, pre, i, prelen, comm)) {
                        res += dp[i + prelen][i];
                        res %= Mod;
                    }
                    if (i + prelen + 1 <= n) {
                        long delta = sum[i + prelen + 1][i];
                        // from i+prelen+1 till n
                        res += delta;
                        res %= Mod;
                    }
                    dp[i][pre] = (int)res;
                }
                sum[i][pre] = dp[i][pre] + (i + 1 > n ? 0 : sum[i + 1][pre]);
                sum[i][pre] %= Mod;
            }
        }
        return (int) dp[0][0];
    }

    // i... vs j... length = len if j is bigger
    private boolean noSmall(String s, int i, int j,int len, int[][] comm) {
        int comlen = comm[i][j];
        //   System.out.println(i+" "+j+" "+comlen+" "+len);
        if(comlen >= len){
            return true;
        }
        int di = i+comlen;
        int dj = j+comlen;
        boolean rt = s.charAt(dj) >= s.charAt(di);
        return rt;
    }
}


class NumberOfWaysToSeparateNumbersNormalDp{
    // TLE if we dont do radix sort
    private long Mod = 1000000007;

    public int numberOfCombinations(String s) {
        int n = s.length();
        long[][] dp = new long[n + 1][n];
        long[][] sum = new long[n + 1][n];
        for (int i = n; i >= 0; i--) {
            for (int pre = 0; pre <= Math.max(0, i - 1); pre++) {
                if (i == n) {
                    dp[i][pre] = 1;
                } else if (s.charAt(i) != '0') {
                    int prelen = i - pre;
                    long res = 0;
                    if (prelen > 0 && i + prelen - 1 < n && noSmall(s, pre, i - 1, i, i + prelen - 1)) {
                        res += dp[i + prelen][i];
                        res %= Mod;
                    }
                    if (i + prelen + 1 <= n) {
                        long delta = sum[i + prelen + 1][i];     // from i+prelen+1 till n
                        res += delta;
                        res %= Mod;
                    }
                    dp[i][pre] = res;
                }
                sum[i][pre] = dp[i][pre] + (i + 1 > n ? 0 : sum[i + 1][pre]);
                sum[i][pre] %= Mod;
            }
        }
        return (int) dp[0][0];
    }

    private boolean noSmall(String s, int i, int j, int k, int l) {
        while (i <= j && k <= l) {
            if (s.charAt(k) > s.charAt(i)) {
                return true;
            } else if (s.charAt(k) < s.charAt(i)) {
                return false;
            }
            i++;
            k++;
        }
        return true;
    }
}
