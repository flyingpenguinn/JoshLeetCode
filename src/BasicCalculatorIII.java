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
    Deque<Long> nums = new ArrayDeque<>();
    Deque<Character> ops = new ArrayDeque<>();

    public int calculate(String s) {
        if (s.startsWith("-")) {
            return calculate("0" + s);
        }
        char[] cs = s.toCharArray();
        int n = cs.length;
        int i = 0;

        while (i < n) {
            char c = cs[i];
            if (c == ' ') {
                i++;
            } else if (Character.isDigit(c)) {
                int j = i;
                long cur = 0;
                while (j < n && Character.isDigit(cs[j])) {
                    cur = cur * 10 + (cs[j++] - '0');
                }
                nums.push(cur);
                i = j;
            } else if (c == '(') {
                if (i + 1 < n && cs[i + 1] == '-') {
                    // (-7)
                    int j = i + 2;
                    long cur = 0;
                    while (Character.isDigit(cs[j]) && cs[j] != ')') {
                        cur = cur * 10 + (cs[j++] - '0');
                    }
                    nums.push(-cur);
                    i = j + 1; // after )
                } else {
                    ops.push(c);
                    i++;
                }
            } else if (c == ')') {
                while (!ops.isEmpty() && ops.peek() != '(') {
                    calconce();
                }
                ops.pop(); // pop the (
                i++;
            } else {
                // + - * /
                while (shouldcalc(c)) {
                    // keep collapsing till we meet a stop for example 2-3/4+30 when we see the + we calc 3/4 but we can still keep calcing 2-0
                    // this is to ensure we always calc from left to right
                    calconce();
                }
                ops.push(c);
                i++;
            }
        }
        while (!ops.isEmpty()) {
            calconce();
        }
        return nums.isEmpty() ? 0 : nums.pop().intValue();
    }

    private boolean shouldcalc(char c) {
        // 1+2+3-> 1+2 first
        // 1-2-3-> 1-2 first
        // 1*2+3-> 1*2 first
        // 1-2*3=> dont know, need to wait for 2*3 to play out
        if (ops.isEmpty()) {
            return false;
        }
        char top = ops.peek();
        if (top == '(') { // otherwise it would be a true and we cant calc (
            return false;
        }
        // if current one can still develop then no rush to calc. otherwise calc it
        // this way we dont run into 2-3-4 != 2-(3-4) problem
        if ((top == '+' || top == '-') && (c == '*' || c == '/')) {
            return false;
        }
        return true;
    }

    private void calconce() {
        long n2 = nums.pop(); //note to flip
        long n1 = nums.pop();
        char op = ops.pop();
        long rt = 0;
        if (op == '+') {
            rt = n1 + n2;
        } else if (op == '-') {
            rt = n1 - n2;
        } else if (op == '*') {
            rt = n1 * n2;
        } else {
            rt = n1 / n2;
        }
        nums.push(rt);
    }
}
