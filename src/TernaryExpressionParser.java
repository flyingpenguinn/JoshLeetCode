

import java.util.ArrayDeque;
import java.util.Deque;

/*
LC#439
Given a string representing arbitrarily nested ternary expressions, calculate the result of the expression. You can always assume that the given expression is valid and only consists of digits 0-9, ?, :, T and F (T and F represent True and False respectively).

Note:

The length of the given string is ≤ 10000.
Each number will contain only one digit.
The conditional expressions group right-to-left (as usual in most languages).
The condition will always be either T or F. That is, the condition will never be a digit.
The result of the expression will always evaluate to either a digit 0-9, T or F.
Example 1:

Input: "T?2:3"

Output: "2"

Explanation: If true, then result is 2; otherwise result is 3.
Example 2:

Input: "F?1:T?4:5"

Output: "4"

Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:

             "(F ? 1 : (T ? 4 : 5))"                   "(F ? 1 : (T ? 4 : 5))"
          -> "(F ? 1 : 4)"                 or       -> "(T ? 4 : 5)"
          -> "4"                                    -> "4"
Example 3:

Input: "T?T?F:5:3"

Output: "F"

Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:

             "(T ? (T ? F : 5) : 3)"                   "(T ? (T ? F : 5) : 3)"
          -> "(T ? F : 3)"                 or       -> "(T ? F : 5)"
          -> "F"                                    -> "F"
 */
public class TernaryExpressionParser {
    // nested structure! so use stack otherwise can't handlde t:(t:4:5):f
    public String parseTernary(String e) {
        Deque<Character> st = new ArrayDeque<>();
        int n = e.length();
        // right to left, bottom up
        for (int i = n - 1; i >= 0; i--) {
            char c = e.charAt(i);
            if (c == '?') {
                char sign = e.charAt(i - 1);
                i--;
                char tr = st.pop();
                char fa = st.pop();
                if (sign == 'T') {
                    st.push(tr);
                } else {
                    st.push(fa);
                }
            } else if (c != ':') {
                st.push(c);
            }
        }
        return String.valueOf(st.pop());
    }

    public static void main(String[] args) {
        System.out.println(new TernaryExpressionParser().parseTernary("T?2:3"));
        System.out.println(new TernaryExpressionParser().parseTernary("T?T?7:5:F?5:6"));
        System.out.println(new TernaryExpressionParser().parseTernary("T?T?F:5:3"));
        System.out.println(new TernaryExpressionParser().parseTernary("F?1:T?4:5"));

    }
}

class TernaryExpressionLevelCounting {
    // similar to the way to deal with (). note each : cancels out one level and each T? or F? adds one level
    public String parseTernary(String s) {
        return dop(s, 0, s.length() - 1);
    }

    private String dop(String s, int l, int u) {
        // l must be a T? or F>
        if (l == u) {
            return s.substring(l, u + 1);
        }
        int level = 0;
        // note we skip the first ?
        for (int i = l + 2; i <= u; i++) {
            if (s.charAt(i) == ':') {
                if (level == 0) {
                    if (s.charAt(l) == 'T') {
                        return dop(s, l + 2, i - 1);
                    } else {
                        return dop(s, i + 1, u);
                    }
                } else {
                    level--;
                }
            } else if (s.charAt(i) == '?') {
                level++;
            }
        }
        return "bad";
    }
}