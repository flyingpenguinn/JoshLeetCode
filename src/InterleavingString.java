/*
LC#97
Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

Example 1:

Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
Output: true
Example 2:

Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
Output: false
 */
public class InterleavingString {
    // whether s3 of length i+j can be formed by interleaving i chars from s1 and j chars from s2
    // note we dont need another k for s3 since s3 index = s1 index + s2 index

    public boolean isInterleave(String s1, String s2, String s3) {
        int s1n = s1.length();
        int s2n = s2.length();
        int s3n = s3.length();
        boolean[][] dp = new boolean[s2n+1][s3n+1];
        if(s3n != s1n + s2n){
            return false;
        }
        for(int j=0; j<=s2n; j++){
            dp[j][s3n%2] = true;
        }
        for(int k=s3n-1; k>=0; k--){
            for(int j = s2n; j>=0; j--){
                int  i = k-j;
                if(i<0 || i>s1n){
                    continue;
                }
                boolean rt = false;
                if(i<s1n && s1.charAt(i)== s3.charAt(k)){
                    rt = dp[j][(k+1)%2];
                }
                if(j<s2n && s2.charAt(j)==s3.charAt(k)){
                    rt = rt || dp[j+1][(k+1)%2];
                }
                dp[j][k%2] = rt;
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        System.out.println(new InterleavingString().isInterleave("aabcc", "dbbca", "aadbbbaccc"));
    }

}

class InterleavingStringMemoization {
    // no need k for pos in s3. k=i+j all the time
    private int[][] dp;
    public boolean isInterleave(String s1, String s2, String s3) {
        if(s3.length() != s1.length() + s2.length()){
            return false;
        }
        dp = new int[s2.length()+1][s3.length()];
        return solve(0, 0, s1, s2, s3);
    }

    private boolean solve(int j,int k, String s1, String s2, String s3){
        if(k==s3.length()){
            return true;
        }
        if(dp[j][k] != 0){
            return dp[j][k]==1;
        }
        int  i = k-j;
        boolean rt = false;
        if(i<s1.length() && s1.charAt(i) == s3.charAt(k)){
            rt = solve(j, k+1, s1, s2, s3);
        }
        if(j<s2.length() && s2.charAt(j)==s3.charAt(k)){
            rt = rt || solve(j+1, k+1, s1, s2,s3);
        }
        dp[j][k] = rt? 1: 2;
        return rt;
    }
}