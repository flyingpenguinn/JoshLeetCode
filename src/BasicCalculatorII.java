import java.util.ArrayDeque;
import java.util.Deque;

/*
LC#227
Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

Example 1:

Input: "3+2*2"
Output: 7
Example 2:

Input: " 3/2 "
Output: 1
Example 3:

Input: " 3+5 / 2 "
Output: 5
Note:

You may assume that the given expression is always valid.
Do not use the eval built-in library function.
 */
public class BasicCalculatorII {
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
            } else {
                // use while because we could have 1+2*3*3+xxx we need to collapse the 2 *
                while (shouldcollapse(ops, c)) {
                    docollapse(nums, ops);
                }
                ops.push(c);
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
        nums.push(docalc(num2, num1, op));
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
