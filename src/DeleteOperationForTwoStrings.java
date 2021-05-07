import java.util.Arrays;

/*
LC#583
Given two words word1 and word2, find the minimum number of steps required to make word1 and word2 the same, where in each step you can delete one character in either string.

Example 1:
Input: "sea", "eat"
Output: 2
Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
Note:
The length of given words won't exceed 500.
Characters in given words can only be lower-case letters.
 */
public class DeleteOperationForTwoStrings {
    private Integer[][] dp;
    public int minDistance(String w1, String w2) {
        dp = new Integer[w1.length()][w2.length()];
        return solve(w1, w2, 0, 0);
    }

    private int solve(String w1, String w2, int i, int j){
        if(i==w1.length() && j==w2.length()){
            return 0;
        }
        else if(i==w1.length()){
            return w2.length()-j;
        }else if(j==w2.length()){
            return w1.length()-i;
        }
        if(dp[i][j]!= null){
            return dp[i][j];
        }
        int rt = 0;
        if(w1.charAt(i)==w2.charAt(j)){
            rt= solve(w1, w2, i+1, j+1);
        }else{
            rt = Math.min(solve(w1, w2, i+1, j), solve(w1, w2, i, j+1))+1;
        }
        dp[i][j] = rt;
        return rt;
    }
}
