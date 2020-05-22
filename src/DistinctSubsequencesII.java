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
    // dp[i] means distinct subsequences ending at i
    // for a given i, find the same element at j (or there is none)
    // if there is none, then it's all j<i, sigma dp[j] + 1 (for current one)
    // otherwise anythign between last occurrence and i are added (inclusive)
    int Mod = 1000000007;

    public int distinctSubseqII(String s) {
        int n = s.length();
        long[] dp = new long[n];
        int r = 0;
        for (int i = 0; i < n; i++) {
            boolean eq = false;
            for (int j = i - 1; j >= 0; j--) {
                dp[i] += dp[j];
                dp[i] %= Mod;
                if (s.charAt(j) == s.charAt(i)) {
                    eq = true;
                    break;
                }
            }
            if (!eq) {
                dp[i] += 1;
                dp[i] %= Mod;
            }
            r += dp[i];
            r %= Mod;
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(new DistinctSubsequencesII().distinctSubseqII("caba"));
        System.out.println(new DistinctSubsequencesII().distinctSubseqII("abc"));

        System.out.println(new DistinctSubsequencesII().distinctSubseqII("aaa"));
    }
}

class DistinctSubsequencesIIOn {
    // dp[i] means distinct subsequences ending at i
    // for a given i, find the same element at j (or there is none)
    // if there is none, then it's all j<i, sigma dp[j] + 1 (for current one)
    // otherwise anythign between last occurrence and i are added (inclusive)
    int Mod = 1000000007;

    public int distinctSubseqII(String s) {
        int n = s.length();
        int[] last = new int[26];
        Arrays.fill(last, -1);
        int[] occ = new int[n];
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            occ[i] = last[c - 'a'];
            last[c - 'a'] = i;
        }
        long[] dp = new long[n];
        int r = 0;
        long[] sum = new long[n];
        for (int i = 0; i < n; i++) {
            int lastoccr = occ[i];
            if (lastoccr == -1) {
                dp[i] = (i == 0 ? 0 : sum[i - 1]) + 1;
                dp[i] %= Mod;
            } else {
                dp[i] = (i == 0 ? 0 : sum[i - 1]) - (lastoccr == 0 ? 0 : sum[lastoccr - 1]);
                dp[i] = (dp[i] + Mod) % Mod;
            }
            sum[i] = (i == 0 ? 0 : sum[i - 1]) + dp[i];
            sum[i] = sum[i] % Mod;
            r += dp[i];
            r %= Mod;
        }
        return r;
    }
}

class DistinctSubsequencesIIAnotherDp {
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