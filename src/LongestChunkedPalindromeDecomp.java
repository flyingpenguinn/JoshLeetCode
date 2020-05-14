import java.util.Arrays;

/*
LC#1147
Return the largest possible k such that there exists a_1, a_2, ..., a_k such that:

Each a_i is a non-empty string;
Their concatenation a_1 + a_2 + ... + a_k is equal to text;
For all 1 <= i <= k,  a_i = a_{k+1 - i}.


Example 1:

Input: text = "ghiabcdefhelloadamhelloabcdefghi"
Output: 7
Explanation: We can split the string on "(ghi)(abcdef)(hello)(adam)(hello)(abcdef)(ghi)".
Example 2:

Input: text = "merchant"
Output: 1
Explanation: We can split the string on "(merchant)".
Example 3:

Input: text = "antaprezatepzapreanta"
Output: 11
Explanation: We can split the string on "(a)(nt)(a)(pre)(za)(tpe)(za)(pre)(a)(nt)(a)".
Example 4:

Input: text = "aaa"
Output: 3
Explanation: We can split the string on "(a)(a)(a)".


Constraints:

text consists only of lowercase English characters.
1 <= text.length <= 1000
 */
public class LongestChunkedPalindromeDecomp {
    // rolling hash. note the rolling hash part is similar to "longest happy prefix..."
    // can also greedily take matching two parts and recurse on it
    int[] dp;
    boolean[][] m;
    long mod = 1000000007;

    public int longestDecomposition(String s) {
        int n = s.length();
        m = new boolean[n][n];
        dp = new int[n];
        Arrays.fill(dp, -1);

        for (int i = 0; i < n / 2; i++) {
            long hash1 = 0l;
            long hash2 = 0l;
            long base = 1l;
            for (int len = 1; i + len - 1 < n / 2; len++) {
                int j = i + len - 1;
                hash1 = (hash1 * 26 + (s.charAt(j) - 'a')) % mod;
                hash2 = (hash2 + base * (s.charAt(n - 1 - j) - 'a')) % mod;
                base = (base * 26) % mod;
                if (hash1 == hash2) {
                    m[i][len] = true;
                }
            }
        }
        return dol(0, n, n, s);
    }

    private int dol(int i, int n, int allllen, String s) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (dp[i] != -1) {
            return dp[i];
        }
        int max = 1;
        for (int len = 1; i + len - 1 < allllen / 2; len++) {

            if (m[i][len]) {
                int cur = 2 + dol(i + len, n - 2 * len, allllen, s);
                max = Math.max(max, cur);
            }
        }
        dp[i] = max;
        return max;
    }
}
