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
    Set<String> res = new HashSet<>();

    public List<String> removeInvalidParentheses(String s) {
        int n = s.length();
        // first get how many ls and rs to remove. given any sequence, we know the counts. this must be "minimal"
        int l = 0;
        int r = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                l++;
            } else if (s.charAt(i) == ')') {
                if (l == 0) {
                    r++;
                } else {
                    l--;
                }
            }
        }
        // we then dfs all solutions
        dfs(s, 0, 0, l, r, "");
        return new ArrayList<>(res);
    }

    void dfs(String s, int i, int lc, int rl, int rr, String cur) {
        if (rl < 0 || rr < 0 || lc < 0) {
            return;
        }
        int n = s.length();
        if (i == n) {
            if (rl == 0 && rr == 0) {
                res.add(cur);
            }
            return;
        }
        if (s.charAt(i) == '(') {
            dfs(s, i + 1, lc + 1, rl, rr, cur + "(");
            dfs(s, i + 1, lc, rl - 1, rr, cur);
        } else if (s.charAt(i) == ')') {
            dfs(s, i + 1, lc - 1, rl, rr, cur + ")");
            dfs(s, i + 1, lc, rl, rr - 1, cur);
        } else {
            dfs(s, i + 1, lc, rl, rr, cur + s.charAt(i));
        }
    }
}
