import java.util.ArrayDeque;
import java.util.Deque;

/*
LC#224
Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

Example 1:

Input: "1 + 1"
Output: 2
Example 2:

Input: " 2-1 + 2 "
Output: 3
Example 3:

Input: "(1+(4+5+2)-3)+(6+8)"
Output: 23
Note:
You may assume that the given expression is always valid.
Do not use the eval built-in library function.
 */

public class BasicCalculator {
    // copied code from basic 3 just the */ difference
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
        }
        nums.push(rt);
    }
}
