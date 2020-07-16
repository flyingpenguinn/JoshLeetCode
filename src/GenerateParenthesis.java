import java.util.ArrayList;
import java.util.List;

/*
LC#22
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
 */
public class GenerateParenthesis {
    // two conditions: open left always >=0 (which means right<=left at all times, and in the end left==n==right (cant have more lefts in the end)
    public List<String> generateParenthesis(int n) {
        List<String> r = new ArrayList<>();
        dfs(0, 0, n, "", r);
        return r;
    }

    private void dfs(int left, int right, int n, String cur, List<String> r) {
        if (left == n && right == n) {
            r.add(cur);
            return;
        }
        if (left < n) { // as long as left is not at the limit we can add for free
            dfs(left + 1, right, n, cur + "(", r);
        }
        if (right < left) { // as long as right count <= left count at all times...
            dfs(left, right + 1, n, cur + ")", r);
        }
    }
}
