import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/*
LC#150
Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Note:

Division between two integers should truncate toward zero.
The given RPN expression is always valid. That means the expression would always evaluate to a result and there won't be any divide by zero operation.
Example 1:

Input: ["2", "1", "+", "3", "*"]
Output: 9
Explanation: ((2 + 1) * 3) = 9
Example 2:

Input: ["4", "13", "5", "/", "+"]
Output: 6
Explanation: (4 + (13 / 5)) = 6
Example 3:

Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
Output: 22
Explanation:
  ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22
 */
public class EvaluateReversePolishNotation {
    Set<String> set = new HashSet<>();

    {
        set.add("+");
        set.add("-");
        set.add("*");
        set.add("/");
    }

    public int evalRPN(String[] tokens) {
        Deque<String> stack = new ArrayDeque<>();
        int n = tokens.length;
        for (int i = 0; i < n; i++) {
            String t = tokens[i];
            if (set.contains(t)) {
                String o2 = stack.pop();
                String o1 = stack.pop();
                stack.push(func(o1, o2, t));
            } else {
                stack.push(t);
            }
        }
        return Integer.valueOf(stack.pop());
    }

    String func(String a, String b, String op) {
        int ia = Integer.valueOf(a);
        int ib = Integer.valueOf(b);
        if ("+".equals(op)) {
            return String.valueOf(ia + ib);
        } else if ("-".equals(op)) {
            return String.valueOf(ia - ib);
        } else if ("*".equals(op)) {
            return String.valueOf(ia * ib);
        } else {
            return String.valueOf(ia / ib);
        }
    }

}
