import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
LC#282
Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.

Example 1:

Input: num = "123", target = 6
Output: ["1+2+3", "1*2*3"]
Example 2:

Input: num = "232", target = 8
Output: ["2*3+2", "2+3*2"]
Example 3:

Input: num = "105", target = 5
Output: ["1*0+5","10-5"]
Example 4:

Input: num = "00", target = 0
Output: ["0+0", "0-0", "0*0"]
Example 5:

Input: num = "3456237490", target = 9191
Output: []
 */
public class ExpressionAddOperators {
    // trick 1: get a multi cache for better enumeration
    // trick2: handle numbers starting with 0
    // trick3: handle long
    List<String> r = new ArrayList<>();

    public List<String> addOperators(String s, int t) {
        dfs(s, 0, 0, 0, "", t);
        return r;
    }

    char[] addonly = {'+'};
    char[] all = {'+', '-', '*', '/'};

    // cur pos, last operator, current result, pending multi
    void dfs(String s, int i, long res, long pending, String cur, int t) {
        int n = s.length();
        if (i == n) {
            if (res + pending == t) {
                r.add(cur);
            }
            return;
        }
        long cv = 0;
        for (int j = i; j < n; j++) {
            cv = cv * 10 + (s.charAt(j) - '0');
            if (s.charAt(i) == '0' && j > i) {
                break;
            }
            // what if too long to fit into int?
            if (cv > Integer.MAX_VALUE) {
                break;
            }
            char[] ops = all;
            if (i == 0) {
                ops = addonly;
            }
            for (char op : ops) {
                if (op == '+') {
                    // what was pending is now ended. this number will serve as base for future *
                    dfs(s, j + 1, res + pending, cv, (cur.isEmpty() ? "" : cur + "+") + cv, t);
                }
                if (op == '-') {
                    // same, but -cv will serve as base
                    dfs(s, j + 1, res + pending, -cv, cur + "-" + cv, t);
                }
                if (op == '*') {
                    // just keep piling on the pending number to form a new pending multi cache
                    dfs(s, j + 1, res, pending * cv, cur + "*" + cv, t);
                }
            }
        }
    }

    public static void main(String[] args) {
        //   System.out.println(new ExpressionAddOperators().addOperators("123", 6));
        //  System.out.println(new ExpressionAddOperators().addOperators("92345", 1));
        System.out.println(new ExpressionAddOperators().addOperators("105", 5));
    }
}
