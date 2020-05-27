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
    // two conditions: open left always >=0, and in the end left == right
    List<String> r = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        dog(0, 0, n, "");
        return r;
    }

    void dog(int i, int ol, int n, String cur) {
        if (ol < 0) {
            // ol must be >=0 at any time
            return;
        }
        if (i == 2 * n) {
            if (ol == 0) {
                // in the end, balance is 0
                r.add(cur);
            }
            return;
        }
        dog(i + 1, ol + 1, n, cur + "(");
        dog(i + 1, ol - 1, n, cur + ")");
    }
}
