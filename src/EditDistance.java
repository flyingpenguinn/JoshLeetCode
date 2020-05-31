/*
LC#72
Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.

You have the following 3 operations permitted on a word:

Insert a character
Delete a character
Replace a character
Example 1:

Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation:
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
Example 2:

Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation:
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')
 */
public class EditDistance {
    // rolling array to reduce memory cost. save the previous value, i.e. dp[j-1] for dp[j] usage
    // dp[j] is rolled form j-1 to j each time on the same array
    public int minDistance(String w1, String w2) {
        int n1 = w1.length();
        int n2 = w2.length();
        int[] dp = new int[n2 + 1];

        for (int j = 0; j <= n2; j++) {
            // dp[0][j]=j;
            dp[j] = j;
        }
        for (int i = 1; i <= n1; i++) {
            // dp[i-1][0]=i-1;
            int oldpj = i - 1;
            dp[0] = i; // need to set this too
            for (int j = 1; j <= n2; j++) {
                int oldj = dp[j];
                if (w1.charAt(i - 1) != w2.charAt(j - 1)) {
                    dp[j] = Math.min(dp[j], Math.min(dp[j - 1], oldpj)) + 1;
                } else {
                    dp[j] = oldpj;
                }
                oldpj = oldj;
            }
        }
        return dp[n2];
    }

    public static void main(String[] args) {
        EditDistance ed = new EditDistance();
        System.out.println(ed.minDistance("horse", "ros"));
    }
}

class ClassicalDp {
    public int minDistance(String w1, String w2) {
        int n1 = w1.length();
        int n2 = w2.length();
        int[][] dp = new int[n1 + 1][n2 + 1];
        for (int i = 0; i <= n1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n2; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (w1.charAt(i - 1) == w2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; // i-1,j-1 better than i,j-1 or i-1,j so dont need checking other two
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                }
            }
        }
        return dp[n1][n2];
    }
}

class Memoization {
    int[][] dp = new int[0][0];

    public int minDistance(String w1, String w2) {
        dp = new int[w1.length()][w2.length()];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        return doMinDist(w1, w2, 0, 0);
    }

    // at i of w1, j of w2, edit distance
    private int doMinDist(String w1, String w2, int i, int j) {
        if (i == w1.length()) {
            return w2.length() - j; // delete all
        }
        if (j == w2.length()) {
            return w1.length() - i;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        int way1 = doMinDist(w1, w2, i + 1, j) + 1;
        int way2 = doMinDist(w1, w2, i, j + 1) + 1;
        int way3 = doMinDist(w1, w2, i + 1, j + 1) + (w1.charAt(i) == w2.charAt(j) ? 0 : 1);
        int rt = Math.min(way1, Math.min(way2, way3));
        dp[i][j] = rt;
        return rt;
    }
}
