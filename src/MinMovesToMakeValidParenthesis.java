import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;


/*
LC#1249
Given a string s of '(' , ')' and lowercase English characters.

Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.

Formally, a parentheses string is valid if and only if:

It is the empty string, contains only lowercase characters, or
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.


Example 1:

Input: s = "lee(t(c)o)de)"
Output: "lee(t(c)o)de"
Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
Example 2:

Input: s = "a)b(c)d"
Output: "ab(c)d"
Example 3:

Input: s = "))(("
Output: ""
Explanation: An empty string is also valid.
Example 4:

Input: s = "(a(b(c)d)"
Output: "a(b(c)d)"


Constraints:

1 <= s.length <= 10^5
s[i] is one of  '(' , ')' and lowercase English letters.
 */
public class MinMovesToMakeValidParenthesis {
    // 1. remove right brackets that appear too early
    // 2. remove left brackets that can't find a pair
    // 3. use a stack to keep open left indexes
    public String minRemoveToMakeValid(String s) {
        Deque<Integer> left = new ArrayDeque<>();
        Set<Integer> gone = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                left.push(i);
            } else if (c == ')') {
                if (left.isEmpty()) {
                    gone.add(i);
                } else {
                    left.pop();
                }
            }
        }
        while (!left.isEmpty()) {
            gone.add(left.pop());
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!gone.contains(i)) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
