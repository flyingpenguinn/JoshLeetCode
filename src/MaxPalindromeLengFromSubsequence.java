public class MaxPalindromeLengFromSubsequence {
    // convert to longest palin subsequence with at least one match in w1 and w2
    public int longestPalindrome(String w1, String w2) {
        String merged = w1+w2;
        int n = merged.length();
        int n1 = w1.length();
        int[][] dp = new int[merged.length()][merged.length()];
        calc(merged, dp);
        int res = 0;
        for(int i=0; i<n1; i++){
            for(int j=n1; j<n; j++){
                if(merged.charAt(i) == merged.charAt(j)){
                    res = Math.max(res, dp[i][j]);
                }
            }
        }
        return res;
    }

    // longest palin subsequence code. note when len==2 case
    private void calc(String w, int[][] dp){
        for(int len=1; len<=w.length(); len++){
            for(int i=0; i+len-1 < w.length(); i++){
                int j = i+len-1;
                if(len==1){
                    dp[i][j] = 1;
                }else if(len==2){
                    dp[i][j] = w.charAt(i)== w.charAt(j)? 2: 1;
                }else{
                    if(w.charAt(i)==w.charAt(j)){
                        dp[i][j] = 2+dp[i+1][j-1];
                    }else{
                        dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                    }
                }
            }
        }
    }
}
