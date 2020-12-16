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
    private int Mod = 1000000007;
    private Long[][] dp;

    public int numPermsDISequence(String s) {
        int n = s.length();
        dp = new Long[n][n + 1];
        long res = 0;
        for (int j = 0; j <= n; j++) {
            res += don(0, j, s);
            res %= Mod;
        }
        return (int) res;
    }

    // at seq pos i, the last num picked is jth in the available numbers (the number j itself included)
    private long don(int i, int j, String s) {
        int n = s.length();
        if (i == n) {
            return 1L;
        }
        if (dp[i][j] != null) {
            return dp[i][j];
        }
        int rem = n - i - 1;// at ith seq pos we have n-i-1 numbers to deal with. note when we are at 0, we already took away 1 number
        long res = 0;
        if (s.charAt(i) == 'I') {
            // gotcha is if it's I, we start from the jth from the remaining because we are taking this one out
            for (int k = j; k <= rem; k++) {
                res += don(i + 1, k, s);
                res %= Mod;
            }
        } else {
            for (int k = 0; k < j; k++) {
                res += don(i + 1, k, s);
                res %= Mod;
            }
        }
        dp[i][j] = res;
        return res;
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