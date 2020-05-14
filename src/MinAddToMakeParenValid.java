import java.util.ArrayDeque;
import java.util.Deque;

/*
LC#921
Given a string S of '(' and ')' parentheses, we add the minimum number of parentheses ( '(' or ')', and in any positions ) so that the resulting parentheses string is valid.

Formally, a parentheses string is valid if and only if:

It is the empty string, or
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.
Given a parentheses string, return the minimum number of parentheses we must add to make the resulting string valid.



Example 1:

Input: "())"
Output: 1
Example 2:

Input: "((("
Output: 3
Example 3:

Input: "()"
Output: 0
Example 4:

Input: "()))(("
Output: 4


Note:

S.length <= 1000
S only consists of '(' and ')' characters.
 */
public class MinAddToMakeParenValid {
    public int minAddToMakeValid(String s) {
        Deque<Character> dq = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (dq.isEmpty()) {
                dq.push(c);
            } else if (dq.peek() == '(' && c == ')') {
                dq.pop();
            } else {
                dq.push(c);
            }
        }
        return dq.size();
    }

    public static void main(String[] args) {
        System.out.println(new MinAddToMakeParenValidDp().minAddToMakeValid(")()"));

        System.out.println(new MinAddToMakeParenValidDp().minAddToMakeValid("()"));
        System.out.println(new MinAddToMakeParenValidDp().minAddToMakeValid("())"));
        System.out.println(new MinAddToMakeParenValidDp().minAddToMakeValid("((("));
        System.out.println(new MinAddToMakeParenValidDp().minAddToMakeValid(")))"));
        System.out.println(new MinAddToMakeParenValidDp().minAddToMakeValid("()))(("));

    }
}

class MinAddToMakeParenValidO1space {
    public int minAddToMakeValid(String s) {
        int lc = 0;
        int r = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ')') {
                if (lc == 0) {
                    r++;
                } else {
                    lc--;
                }
            } else {
                lc++;
            }
        }
        return r + lc;
    }
}

// TLE, but a good solution
class MinAddToMakeParenValidDp {

    // split to 2 cases: (S) and then dp on S, or S=AB and dp on A and B
    public int minAddToMakeValid(String s) {
        int n = s.length();
        if (n == 0) {
            return 0;
        }
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    dp[i][j] = 1;
                } else if (i < j) {
                    dp[i][j] = j - i + 1;
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        // iterate lefts
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (match(s, i, j)) {
                    dp[i][j] = Math.min(dp[i][j], dp[i + 1][j - 1]);
                }
                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j]);
                }
            }
        }
        return dp[0][n - 1];
    }

    private boolean match(String s, int i, int j) {
        return s.charAt(i) == '(' && s.charAt(j) == ')';
    }
}
