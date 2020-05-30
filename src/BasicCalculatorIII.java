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
        Deque<Long> nst = new ArrayDeque<>();
        Deque<Character> ost = new ArrayDeque<>();
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < n) {
            char c = s.charAt(i);
            int nexti = i + 1;
            if (c == '(' && s.charAt(i + 1) == '-') {
                // negative number
                int end = s.indexOf(")", i + 1);
                long neg = Long.valueOf(s.substring(i + 1, end));
                nst.push(neg);
                nexti = end + 1;
            } else if (c == '(') {
                ost.push(c);
            } else if (c == ')') {
                sb = pushnumber(nst, sb);
                collapsetillchar('(', nst, ost);
            } else if (i > 0 && (c == '+' || c == '-' || c == '*' || c == '/')) {
                // note the handling of -2-5 or +3+6
                sb = pushnumber(nst, sb);
                collapsetilllower(nst, ost, c);
            } else {
                sb.append(c);
            }
            i = nexti;
        }
        pushnumber(nst, sb);
        collapsall(nst, ost);
        return (int) nst.pop().longValue();
    }

    protected StringBuilder pushnumber(Deque<Long> nst, StringBuilder sb) {
        String trim = sb.toString().trim();
        if (!trim.isEmpty()) {
            nst.push(Long.valueOf(trim));
        }
        return new StringBuilder();
    }

    private void collapsall(Deque<Long> nst, Deque<Character> ost) {
        while (!ost.isEmpty()) {
            popandcalc(nst, ost);
        }
    }

    private void collapsetillchar(char c, Deque<Long> nst, Deque<Character> ost) {
        // must be of same priority, or higher priorities are at the top of stack so done first anyway
        while (ost.peek() != c) {
            popandcalc(nst, ost);
        }
        ost.pop();
    }

    private void collapsetilllower(Deque<Long> nst, Deque<Character> ost, char op) {
        // this is key of this problem
        // if we see +, keep doing * /  or +- itself, till we see (
        // if we see */, keep doing */, we should stop there
        // but at any rate, stop at (
        while (!ost.isEmpty() && priority(ost.peek()) >= priority(op)) {
            popandcalc(nst, ost);
        }
        ost.push(op);
    }

    private void popandcalc(Deque<Long> nst, Deque<Character> ost) {
        char topop = ost.pop();
        long v1 = nst.pop();
        long v2 = nst.pop();
        nst.push(operation(v2, v1, topop));
    }

    private int priority(char c) {
        if (c == '*' || c == '/') {
            return 2;
        } else if (c == '+' || c == '-') {
            return 1;
        } else {
            /// ( is the lowest, we stop at it all the time
            return 0;
        }
    }

    private long operation(long v1, long v2, char op) {
        if (op == '+') {
            return v1 + v2;
        } else if (op == '-') {
            return v1 - v2;
        } else if (op == '*') {
            return v1 * v2;
        } else {
            return v1 / v2;
        }
    }
}
