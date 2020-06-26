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
    // note there could be duplicates, so we must use a set
    public List<String> removeInvalidParentheses(String s) {
        int n = s.length();
        int openleft = 0;
        int invalidright = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                openleft++;
            } else if (s.charAt(i) == ')') {
                if (openleft == 0) {
                    invalidright++;
                } else {
                    openleft--;
                }
            }
        }
        int invalidleft = openleft;
        Set<String> r = new HashSet<>();
        dfs(0, s, invalidleft, invalidright, 0, "", r);
        return new ArrayList<>(r);
    }

    private void dfs(int i, String s, int ln, int rn, int openleft, String cur, Set<String> r) {
        if (openleft < 0 || ln < 0 || rn < 0) {
            return;
        }
        int n = s.length();
        if (i == n) {
            if (openleft == 0) {

                r.add(cur);
            }
            return;
        }
        char c = s.charAt(i);
        if (c == '(') {
            dfs(i + 1, s, ln, rn, openleft + 1, cur + "(", r); // keep
            dfs(i + 1, s, ln - 1, rn, openleft, cur, r); // delete
        } else if (c == ')') {
            dfs(i + 1, s, ln, rn, openleft - 1, cur + ")", r); // keep
            dfs(i + 1, s, ln, rn - 1, openleft, cur, r);// delete
        } else {
            dfs(i + 1, s, ln, rn, openleft, cur + c, r);
        }
    }
}
