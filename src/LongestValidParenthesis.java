import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/*
LC#32
Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

Example 1:

Input: "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()"
Example 2:

Input: ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()"
 */
public class LongestValidParenthesis {
    public int longestValidParentheses(String s) {
        int n = s.length();
        // len of longest valid starting from i
        int[] dp = new int[n];
        int max = 0;
        for (int i = 0; i + 1 < n; i++) {
            if (s.charAt(i) == '(' && s.charAt(i + 1) == ')') {
                dp[i] = 2;
                max = 2;
            }
        }
        for (int i = n - 2; i >= 0; i--) {
            if (s.charAt(i) == ')') {
                continue;
            }
            // farthest inner can ge to.if i snd j can form legal one inner wont cross j as that wouldbe illegal
            int len = dp[i + 1];
            // if this can form a max parenthesis there must be a ) at this position
            int j = i + len + 1;
            // instead of traversing we verify since if we traverse we want dp[i+1] to point to j-1 and j is a )
            if (j >= n || s.charAt(j) != ')') {
                continue;
            }
            // concat because j+1 is already computed
            int next = j == n - 1 ? 0 : dp[j + 1];
            int cm = (j - i + 1) + next;
            dp[i] = Math.max(dp[i], cm);
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new LongestValidParenthesisDp2().longestValidParentheses("()"));
    }
}

// can combine stack and dp
class LongestValidParenthesisDp2 {
    public int longestValidParentheses(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        int n = s.length();
        int[] dp = new int[n + 1];
        int[] map = new int[n];
        Arrays.fill(map, -1);
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (stack.isEmpty()) {
                stack.push(i); // could be a bad )
            } else if (c == '(') {
                stack.push(i);
            } else if (c == ')') {
                if (s.charAt(stack.peek()) == '(') {
                    map[stack.pop()] = i;
                }
            }
        }
        int max = 0;
        for (int i = n - 2; i >= 0; i--) {
            if (s.charAt(i) == '(' && map[i] != -1) {
                int j = map[i];
                dp[i] = j - i + 1 + dp[j + 1];
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }

    class LongestValidParenthesisStack {
        public int longestValidParentheses(String s) {
            Deque<Integer> stack = new ArrayDeque<>();
            int max = 0;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (stack.isEmpty()) {
                    stack.push(i); // could be a bad )
                } else if (c == '(') {
                    stack.push(i);
                } else if (c == ')') {
                    if (s.charAt(stack.peek()) == '(') {
                        stack.pop();
                        if (stack.isEmpty()) {
                            // all match
                            max = i + 1;
                        } else {
                            // from top to i let's a valid string. top is an unmatched one
                            max = Math.max(max, i - stack.peek());
                        }
                    } else {
                        // a bad one, still push to form a boundary
                        stack.push(i);
                    }
                }
            }
            return max;
        }
    }
}