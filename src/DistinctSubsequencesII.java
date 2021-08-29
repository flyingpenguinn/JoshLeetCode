import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/*
LC#940
Given a string S, count the number of distinct, non-empty subsequences of S .

Since the result may be large, return the answer modulo 10^9 + 7.



Example 1:

Input: "abc"
Output: 7
Explanation: The 7 distinct subsequences are "a", "b", "c", "ab", "ac", "bc", and "abc".
Example 2:

Input: "aba"
Output: 6
Explanation: The 6 distinct subsequences are "a", "b", "ab", "ba", "aa" and "aba".
Example 3:

Input: "aaa"
Output: 3
Explanation: The 3 distinct subsequences are "a", "aa" and "aaa".




Note:

S contains only lowercase letters.
1 <= S.length <= 2000
 */
public class DistinctSubsequencesII {
    // here dp[i] means all subsequences from 0...i, not ending at i
    // dp[i] = 2*dp[i-1] - dp[lastoccr-1]. we exclude 0...lastoccr-1 because these are double counted
    int Mod = 1000000007;

    public int distinctSubseqII(String s) {
        int n = s.length();
        int[] last = new int[26];
        Arrays.fill(last, -1);
        long[] dp = new long[n];

        for (int i = 0; i < n; i++) {
            dp[i] = 2 * (i == 0 ? 0 : dp[i - 1]);
            int cind = s.charAt(i) - 'a';
            int lastoccr = last[cind];
            if (lastoccr >= 0) {
                dp[i] -= lastoccr == 0 ? 0 : dp[lastoccr - 1];
            } else {
                dp[i]++;
            }
            dp[i] = (dp[i] + Mod) % Mod;
            last[cind] = i;
        }
        return (int) dp[n - 1];
    }
}

class DistinctSubsequencesIIOn {
    // dp[i] means distinct subsequences ending at i
    // for a given i, the choices ending at i is the choice from all previously seen endings +1
    // this can be used for number of unique good subsequences
    private int Mod = (int)(1e9+7);
    public int distinctSubseqII(String s) {
        long[] ends = new long[26];

        for(int i=0; i<s.length(); ++i){
            char c = s.charAt(i);
            long csum = 0;
            for(int j=0; j<ends.length; ++j){
                csum += ends[j];
            }
            csum +=1;
            csum %= Mod;
            ends[c-'a'] = csum;
        }
        long res = 0;
        for(long ei: ends){
            res += ei;
            res %= Mod;
        }
        return (int)res;
    }
}
