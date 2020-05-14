import java.util.Arrays;

public class ValidParenthesisString {

    public boolean checkValidString(String s) {
        int low = 0;
        int high = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                low++;
                high++;
            } else if (c == ')') {
                high--;
                low--;
            } else {
                // == '*'
                high++;
                low--;
            }
            if (high < 0) {
                return false;
            }
            // needed because high is >=0 we still have hope
            low = Math.max(low, 0);
        }
        return low == 0;
    }

    public static void main(String[] args) {
        System.out.println(new ValidParenthesisString().checkValidString("()"));
    }
}


class ValidParenthesisMemoization {
    int[][] dp = null;

    public boolean checkValidString(String s) {
        dp = new int[s.length()][s.length()];
        return doCheck(0, 0, s);

    }

    private boolean doCheck(int i, int openLeft, String s) {
        if (i == s.length()) {
            return openLeft == 0;
        }
        if (openLeft < 0) {
            return false;
        }
        if (dp[i][openLeft] != 0) {
            return dp[i][openLeft] == 1;
        }
        char c = s.charAt(i);
        if (c == '(') {
            boolean rt = doCheck(i + 1, openLeft + 1, s);
            dp[i][openLeft] = rt ? 1 : 2;
            return rt;
        }
        if (c == ')') {
            boolean rt = doCheck(i + 1, openLeft - 1, s);
            dp[i][openLeft] = rt ? 1 : 2;
            return rt;
        } else {
            // * can be anything
            boolean rt = doCheck(i + 1, openLeft + 1, s) || doCheck(i + 1, openLeft - 1, s) || doCheck(i + 1, openLeft, s);
            dp[i][openLeft] = rt ? 1 : 2;
            return rt;
        }
    }
}

// slower than memoization because there are more useless "open left" checked...
class ValidParenthesisIterativeDp {

    public boolean checkValidString(String s) {
        int n = s.length();
        int[][] dp = new int[n + 1][n + 1];
        for (int j = 1; j <= n; j++) {
            dp[n][j] = 2;
        }
        dp[n][0] = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                char c = s.charAt(i);
                if (c == '(') {
                    dp[i][j] = dp[i + 1][j + 1];
                } else if (c == ')') {
                    dp[i][j] = j - 1 >= 0 ? dp[i + 1][j - 1] : 2;
                } else {
                    dp[i][j] = j - 1 >= 0 ? dp[i + 1][j - 1] : 2;
                    dp[i][j] = Math.min(dp[i + 1][j], Math.min(dp[i][j], dp[i + 1][j + 1]));
                }
            }
        }
        return dp[0][0] == 1;

    }
}

