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
    // classical two stack solution. note the definition of shouldCollapse
    private Deque<Long> nums = new ArrayDeque<>();
    private Deque<Character> ops = new ArrayDeque<>();

    public int calculate(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int n = s.length();
        int i = 0;
        while (i < n) {
            char c = s.charAt(i);
            if (c == ')') {
                collapse2Left();
                i++;
            } else if (c == '(') {
                if (i + 1 < n && s.charAt(i + 1) == '-') {  // (-1) neg number
                    int j = s.indexOf(')', i + 1);
                    Long num = Long.valueOf(s.substring(i + 1, j));
                    nums.push(num);
                    i = j + 1;
                } else {
                    ops.push(c); // push single ( non neg
                    i++;
                }
            } else if ((c == '-' && i == 0) || isNum(c)) {
                int j = i + 1;
                while (j < n && isNum(s.charAt(j))) {
                    j++;
                }
                Long num = Long.valueOf(s.substring(i, j));
                nums.push(num);
                i = j;
            } else if (c != ' ') {
                // operators like +-*/
                collapse(c);
                i++;
            } else {
                i++;
            }
        }
        collapseAll();
        return nums.pop().intValue();
    }

    private boolean isNum(char c) {
        return c >= '0' && c <= '9';
    }

    private void calc() {
        char curop = ops.pop();
        long num2 = nums.pop();
        long num1 = nums.pop();
        nums.push(doCalc(num1, num2, curop));
    }

    private long doCalc(long n1, long n2, char op) {
        if (op == '+') {
            return n1 + n2;
        } else if (op == '-') {
            return n1 - n2;
        } else if (op == '*') {
            return n1 * n2;
        } else {
            return n1 / n2;
        }
    }

    private void collapse(char c) {
        while (!ops.isEmpty() && shouldCollapse(ops.peek(), c)) {
            calc();
        }
        ops.push(c);
    }

    private void collapseAll() {
        while (!ops.isEmpty()) {
            calc();
        }
    }

    private void collapse2Left() {
        while (!ops.isEmpty() && ops.peek() != '(') {
            calc();
        }
        ops.pop(); // dont forget this!
    }

    private boolean shouldCollapse(char o1, char o2) {
        if (o1 == '(') { // ( means dont calc beyond this yet
            return false;
        }
        if ((o1 == '+' || o1 == '-') && (o2 == '*' || o2 == '/')) {
            return false;
        }
        return true;
    }
}
