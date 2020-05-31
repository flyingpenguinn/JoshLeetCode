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
        dp = new int[w1.length() + 1][w2.length() + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        return doMinDist(w1, w2, w1.length(), w2.length());
    }

    // w1 from 0 to 0+l1-1
    // w2 from 0 to 0+l2-1
    private int doMinDist(String w1, String w2, int l1, int l2) {
        if (dp[l1][l2] != -1) {
            return dp[l1][l2];
        }
        if (l1 == 0 && l2 == 0) {
            return 0;
        } else if (l1 == 0) {
            return l2;
        } else if (l2 == 0) {
            return l1;
        } else {
            int d1 = doMinDist(w1, w2, l1 - 1, l2) + 1;
            int d2 = doMinDist(w1, w2, l1, l2 - 1) + 1;
            int d3 = doMinDist(w1, w2, l1 - 1, l2 - 1);
            if (w1.charAt(l1 - 1) != w2.charAt(l2 - 1)) {
                d3++;
            }
            int rt = Math.min(d1, Math.min(d2, d3));
            dp[l1][l2] = rt;
            return rt;

        }
    }
}
