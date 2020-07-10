import java.util.ArrayDeque;
import java.util.Deque;

/*
LC#772
Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

The expression string contains only non-negative integers, +, -, *, / operators , open ( and closing parentheses ) and empty spaces . The integer division should truncate toward zero.

You may assume that the given expression is always valid. All intermediate results will be in the range of [-2147483648, 2147483647].

Some examples:

"1 + 1" = 2
" 6-4 / 2 " = 4
"2*(5+5*2)/3+(6/2+8)" = 21
"(2+6* 3+5- (3*14/7+2)*5)+3"=-12


Note: Do not use the eval built-in library function.
 */
public class BasicCalculatorIII {
    public int calculate(String s) {
        // assuming s valid
        if (s == null || s.isEmpty()) {
            return 0;
        }
        Deque<Long> num = new ArrayDeque<>();
        Deque<Character> op = new ArrayDeque<>();
        int i = 0;
        int n = s.length();
        StringBuilder pending = new StringBuilder();
        while (i < n) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                pending.append(c);
            } else if (c == '(') {
                if (i + 1 < n && s.charAt(i + 1) == '-') {
                    // (-2)*3
                    int end = s.indexOf(')', i + 1);
                    pending = pushNumber(s.substring(i + 1, end), num);
                    i = end;
                } else {
                    op.push(c);
                }
            } else if (c == ')') {
                pending = pushNumber(pending.toString(), num);
                collapseTillLeft(num, op);
            } else if (c == '-' && i == 0) {
                pending.append(c); // -2 +5 : -2 is part of the num
            } else if (c != ' ') {
                // operations, - without making numbers negative
                pending = pushNumber(pending.toString(), num);
                collapseAndPut(num, op, c);
            }
            i++;
        }
        // don't forget to push the final number
        pending = pushNumber(pending.toString(), num);
        while (!op.isEmpty()) {
            collapseAll(num, op);
        }
        return num.pop().intValue();
    }

    private StringBuilder pushNumber(String s, Deque<Long> st) {
        if (!s.isEmpty()) {
            st.push(Long.valueOf(s));
        }
        return new StringBuilder();
    }

    private void collapseAndPut(Deque<Long> num, Deque<Character> op, char c) {
        while (!op.isEmpty() && shouldCollapse(op.peek(), c)) {
            calc(num, op);
        }
        op.push(c);
    }

    private void collapseAll(Deque<Long> num, Deque<Character> op) {
        while (!op.isEmpty()) {
            calc(num, op);
        }
    }

    private void collapseTillLeft(Deque<Long> num, Deque<Character> op) {
        while (!op.isEmpty() && op.peek() != '(') {
            calc(num, op);
        }
        op.pop(); // pop the ( out
    }

    private void calc(Deque<Long> num, Deque<Character> op) {
        long v2 = num.pop();
        long v1 = num.pop();
        char func = op.pop();
        num.push(runCalc(func, v1, v2));
    }

    private long runCalc(char f, long v1, long v2) {
        if (f == '+') {
            return v1 + v2;
        } else if (f == '-') {
            return v1 - v2;
        } else if (f == '*') {
            return v1 * v2;
        } else {
            return v1 / v2;
        }
    }

    // should we trigger the calc of op1 immediately?
    private boolean shouldCollapse(char op1, char op2) {
        // nothing can trigger a calc on (
        if (op1 == '(') {
            return false;
        } else if ((op1 == '+' || op1 == '-') && (op2 == '*' || op2 == '/')) {
            // the only case that we need to store the operation to realize later
            return false;
        }
        return true;
    }
}
