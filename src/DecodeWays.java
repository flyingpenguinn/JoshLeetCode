import java.util.Arrays;

/*
LC#91
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given a non-empty string containing only digits, determine the total number of ways to decode it.

Example 1:

Input: "12"
Output: 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).
Example 2:

Input: "226"
Output: 3
Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 */
public class DecodeWays {
    private int[] dp = null;

    // doDp(i) = doDp(i+1)  if single digit !=0
    //        = 0   if single digit = 0
    // +
    //   doDp(i+2) // if 2 digits make sense
    public int numDecodings(String s) {
        int[] dp = new int[s.length() + 1];
        dp[s.length()] = 1;
        for (int i = s.length() - 1; i >= 0; i--) {
            int singleDigit = s.charAt(i) - '0';
            if (singleDigit == 0) {
                dp[i] = 0;
                continue;
            }
            dp[i] = dp[i + 1];
            if (i + 2 <= s.length()) {
                int twoDigits = Integer.valueOf(s.substring(i, i + 2));
                if (twoDigits <= 26) {
                    dp[i] += dp[i + 2];
                }
            }
        }
        return dp[0];
    }


    public static void main(String[] args) {

    }
}

class DecodeWaysMemoization {
    private int[] dp;
    public int numDecodings(String s) {
        // check null, throw or error if so
        // assuming s is all numbers, no mknus sign etc
        dp = new int[s.length()+1];
        Arrays.fill(dp, -1);
        return solve(s, 0);
    }

    private int solve(String s, int i){
        if(i==s.length()){
            return 1;
        }
        if(dp[i]!= -1){
            return dp[i];
        }
        int ith = s.charAt(i)-'0';
        if(ith==0){
            // can't start with 0
            return 0;
        }
        int way1 = solve(s, i+1);
        int way2 = 0;
        if(i+1<s.length()){
            int iplus1 = s.charAt(i+1)-'0';
            int cur = ith*10+ iplus1;
            if(cur<=26){
                way2 = solve(s, i+2);
            }
        }
        int res = way1+way2;
        dp[i] = res;
        return res;
    }
}
