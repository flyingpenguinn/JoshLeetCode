import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveOutermostParenthesis {
    public String removeOuterParentheses(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ')' && stack.peek() == '(') {
                stack.pop();
            } else {
                stack.push(c);
            }
            if (c == ')' && stack.size() >= 1) {
                sb.append(c);
            } else if (c == '(' && stack.size() > 1) {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}

class RemoveOutermostWithoutStack {
    public String removeOuterParentheses(String s) {

        StringBuilder sb = new StringBuilder();
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ')') {
                left--;
                if (left != 0) {
                    sb.append(c);
                }
            }
            if (c == '(') {
                left++;
                if (left != 1) {
                    sb.append(c);
                }


            }


        }
        return sb.toString();
    }
}
