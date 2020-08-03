import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
LC#301
Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Example 1:

Input: "()())()"
Output: ["()()()", "(())()"]
Example 2:

Input: "(a)())()"
Output: ["(a)()()", "(a())()"]
Example 3:

Input: ")("
Output: [""]
 */
//TODO: try out bfs with the set removed
public class RemoveInvalidParenthesis {
    // only two ways for parenthesis to be invalid: l==0 when we have r or too many l in the end. use this to calc the counts we should have
    // then use dfs to pick or unpick the brackets

    // trap is there could be duplicates, so we must use a set
    public List<String> removeInvalidParentheses(String s) {
        // check null etc

        int badLeft = 0;
        int badRight = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                badLeft++;
            } else if (s.charAt(i) == ')') {
                if (badLeft == 0) {
                    badRight++;
                } else {
                    badLeft--;
                }
            }
        }
        Set<String> r = new HashSet<>();
        dfs(0, s, 0, badLeft, badRight, "", r);
        return new ArrayList<>(r);
    }

    // how many open l do we have
    // how many l and r can we remove
    private void dfs(int i, String s, int openl, int reml, int remr, String cur, Set<String> r) {
        int n = s.length();
        if (i == n) {
            if (openl == 0) { // over populated left
                r.add(cur);
            }
            return;
        }
        if (reml < 0 || remr < 0) { // over deleted
            return;
        }
        if (openl < 0) { // over populated right
            return;
        }
        char c = s.charAt(i);
        if (c == '(') {
            dfs(i + 1, s, openl + 1, reml, remr, cur + c, r); // keep this (
            dfs(i + 1, s, openl, reml - 1, remr, cur, r); // delete this (
        } else if (s.charAt(i) == ')') {
            dfs(i + 1, s, openl - 1, reml, remr, cur + c, r); // keep this )
            dfs(i + 1, s, openl, reml, remr - 1, cur, r); // delete this )
        } else {
            dfs(i + 1, s, openl, reml, remr, cur + c, r);
        }
    }
}
