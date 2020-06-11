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
    Set<String> r = new HashSet<>();

    public List<String> removeInvalidParentheses(String s) {
        int openleft = 0;
        int badr = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                openleft++;
            } else if(c==')'){
                if (openleft == 0) {
                    badr++;
                } else {
                    openleft--;
                }
            }
        }
        int badl = openleft;
        dfs(0, badl, badr, 0, s, "");
        return new ArrayList<>(r);
    }

    private void dfs(int i, int reml, int remr, int openleft, String s, String cur) {
        if (openleft < 0 || reml < 0 || remr < 0) {
            return;
        }
        if (i == s.length()) {
            if (openleft == 0 && reml == 0 && remr == 0) {
                r.add(cur);
            }
            return;
        }
        char c = s.charAt(i);
        if (c == '(') {
            // keep this
            dfs(i + 1, reml, remr, openleft + 1, s, cur + "(");
            // remove this
            dfs(i + 1, reml - 1, remr, openleft, s, cur);
        } else if(c ==')'){
            // keep this
            dfs(i + 1, reml, remr, openleft - 1, s, cur + ")");
            // remove this
            dfs(i + 1, reml, remr - 1, openleft, s, cur);
        }else{
            dfs(i + 1, reml, remr, openleft, s, cur + c);
        }
    }
}
