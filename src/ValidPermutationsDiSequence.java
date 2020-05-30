import java.util.Arrays;

/*
LC#903
We are given S, a length n string of characters from the set {'D', 'I'}. (These letters stand for "decreasing" and "increasing".)

A valid permutation is a permutation P[0], P[1], ..., P[n] of integers {0, 1, ..., n}, such that for all i:

If S[i] == 'D', then P[i] > P[i+1], and;
If S[i] == 'I', then P[i] < P[i+1].
How many valid permutations are there?  Since the answer may be large, return your answer modulo 10^9 + 7.



Example 1:

Input: "DID"
Output: 5
Explanation:
The 5 valid permutations of (0, 1, 2, 3) are:
(1, 0, 3, 2)
(2, 0, 3, 1)
(2, 1, 3, 0)
(3, 0, 2, 1)
(3, 1, 2, 0)


Note:

1 <= S.length <= 200
S consists only of characters from the set {'D', 'I'}.
 */
public class ValidPermutationsDiSequence {
    // O(n3). note to use j to indicate the ranking of the previous selected in the remaining numbers. note if its ranking was j, we can still pick
    // jth at this step when we are facing "I"
    int MOD = 1000000007;
    long[][] dp;

    public int numPermsDISequence(String s) {
        int n = s.length();
        dp = new long[n][n + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        // number is from 0...n
        long r = 0;
        for (int i = 0; i <= n; i++) {
            // we iterate from the 2nd number
            r += dom(0, i, s);
            r %= MOD;
        }
        return (int) r;
    }

    // we are at position i, last number is the jth smallest in the remaining numbers
    private long dom(int i, int j, String s) {
        int n = s.length();
        if (i == n) {
            return 1;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        int rt = 0;
        if (s.charAt(i) == 'D') {
            for (int k = 0; k < j; k++) {
                rt += dom(i + 1, k, s);
                rt %= MOD;
            }
        } else {
            // prev number would have ranked j. so new j here is bigger that that number
            // when it's i-1 we only have 1 choice as 0...
            for (int k = j; k < n - i; k++) {
                rt += dom(i + 1, k, s);
                rt %= MOD;
            }
        }
        dp[i][j] = rt;
        return rt;
    }

    public static void main(String[] args) {
        System.out.println(new ValidPermutationFroDiOptimized().numPermsDISequence("DID"));
    }
}

// use prefix sum to turn n3 to n2. just need sum array. dpij is just a cur value
// can tune even further to 1d array
class ValidPermutationFroDiOptimized {
    int Mod = 1000000007;

    public int numPermsDISequence(String s) {
        int n = s.length();
        int[][] sum = new int[n + 1][n + 2];
        for (int j = 1; j <= n + 1; j++) {
            sum[n][j] = j;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 1; j <= n + 1; j++) {
                long cur = 0L;
                if (s.charAt(i) == 'D') {
                    cur = j == 1 ? 0 : sum[i + 1][j - 1];
                } else {
                    int high = n - i; // we have n-i to go
                    cur = (sum[i + 1][high] - (j == 1 ? 0 : sum[i + 1][j - 1]) + Mod) % Mod;
                }
                long cursum = j == 1 ? cur : (sum[i][j - 1] + cur);
                sum[i][j] = (int) (cursum % Mod);
            }
        }
        return sum[0][n + 1];
    }
}