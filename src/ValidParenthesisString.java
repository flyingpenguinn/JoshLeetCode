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
    // we dont really need open right because right+1 == left-1 we just care about their diff
    int[][] dp;

    public boolean checkValidString(String s) {
        int n = s.length();
        dp = new int[n][n];
        for (int i = 0; i < n; i++) {

            Arrays.fill(dp[i], -1);

        }
        int rt = dov(s, 0, 0);
        return rt == 1;
    }

    // dont really need right because our logic is centered on left/right balance
    int dov(String s, int i, int ol) {
        if (ol < 0) {
            return 2;
        }
        int n = s.length();
        if (i == n) {
            return ol == 0 ? 1 : 2;
        }
        if (dp[i][ol] != -1) {
            return dp[i][ol];
        }
        char c = s.charAt(i);
        int rt = 0;
        if (c == '(') {
            rt = dov(s, i + 1, ol + 1);
        } else if (c == ')') {
            rt = dov(s, i + 1, ol - 1);
        } else {
            int rt1 = dov(s, i + 1, ol + 1);
            int rt2 = dov(s, i + 1, ol - 1);
            int rt3 = dov(s, i + 1, ol);
            rt = Math.min(rt1, Math.min(rt2, rt3));
        }
        dp[i][ol] = rt;
        return rt;
    }
}

