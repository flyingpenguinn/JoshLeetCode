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
    // note leetcode removed the requirement of supporting negative numbers
    public int calculate(String s) {
        Deque<Character> ops = new ArrayDeque<>();
        Deque<Integer> nums = new ArrayDeque<>();
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c == ' ') {
                i++;
            } else if (isnum(c)) {
                int j = i;
                int num = 0;
                while (j < s.length() && isnum(s.charAt(j))) {
                    num = num * 10 + (s.charAt(j) - '0');
                    j++;
                }
                nums.push(num);
                i = j;
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                // use while because we could have 1+2*3*3+xxx we need to collapse the 2 *
                while (shouldcollapse(ops, c)) {
                    docollapse(nums, ops);
                }
                ops.push(c);
                i++;
            } else if (c == '(') {
                ops.push(c);
                i++;
            } else {
                // )

                while (!ops.isEmpty() && ops.peek() != '(') {
                    docollapse(nums, ops);
                }
                ops.pop();
                i++;
            }
        }
        while (!ops.isEmpty()) {
            docollapse(nums, ops);
        }
        return nums.pop();
    }

    private boolean isnum(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean shouldcollapse(Deque<Character> ops, char c) {
        if (ops.isEmpty()) {
            return false;
        }
        if (ops.peek() == '(') {
            return false;
        }
        // 1+2*3 we can't collapse the + yet
        if ((c == '*' || c == '/') && (ops.peek() == '+' || ops.peek() == '-')) {
            return false;
        }
        return true;
    }

    private void docollapse(Deque<Integer> nums, Deque<Character> ops) {
        int num1 = nums.pop();
        int num2 = nums.pop();
        char op = ops.pop();
        int rt = docalc(num2, num1, op);
        nums.push(rt);
    }

    private int docalc(int n1, int n2, char op) {
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
}
