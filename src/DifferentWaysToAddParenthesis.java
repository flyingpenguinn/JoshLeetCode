import java.util.ArrayList;
import java.util.List;

/*
LC#241
Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are +, - and *.

Example 1:

Input: "2-1-1"
Output: [0, 2]
Explanation:
((2-1)-1) = 0
(2-(1-1)) = 2
Example 2:

Input: "2*3-4*5"
Output: [-34, -14, -10, -10, 10]
Explanation:
(2*(3-(4*5))) = -34
((2*3)-(4*5)) = -14
((2*(3-4))*5) = -10
(2*((3-4)*5)) = -10
(((2*3)-4)*5) = 10
 */
public class DifferentWaysToAddParenthesis {
    // divide the string by operation sign is better than grouping them in numbers
    // ops sign is a natural way to group
    // dont need to worry about "no bracket" case because it's the same result as "some" grouping
    // if needed we can dp on l...u
    public List<Integer> diffWaysToCompute(String s) {
        return dfs(s, 0, s.length() - 1);
    }

    List<Integer> dfs(String s, int l, int u) {
        List<Integer> r = new ArrayList<>();
        boolean foundop = false;
        for (int i = l; i <= u; i++) {
            char c = s.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                foundop = true;
                List<Integer> left = dfs(s, l, i - 1);
                List<Integer> right = dfs(s, i + 1, u);
                for (int le : left) {
                    for (int rt : right) {
                        r.add(calc(le, rt, c));
                    }
                }
            }
        }
        if (!foundop) {
            r.add(Integer.valueOf(s.substring(l, u + 1)));
        }
        return r;
    }

    int calc(int le, int rt, char op) {
        if (op == '+') {
            return le + rt;
        } else if (op == '-') {
            return le - rt;
        } else if (op == '*') {
            return le * rt;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
