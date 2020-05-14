import java.util.ArrayDeque;
import java.util.Deque;

public class ScoreOfParenthesis {


    public int scoreOfParentheses(String s) {

        int scores = 0;

        int n = s.length();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == ')') {
                Integer top = stack.pop();
                int sc = 0;
                if (top == 0) {
                    sc = 1;
                } else {
                    sc = top * 2;
                }
                if (!stack.isEmpty()) {
                    // record the score so far at the remaining left (, or at the outside score varaible
                    Integer nt = stack.pop();
                    nt += sc;
                    stack.push(nt);
                } else {
                    scores += sc;
                }
            } else {
                // begin with 0 and if 0 we know there is no inner ()
                stack.push(0);
            }
        }
        return scores;
    }
}

class ScoreOfParenthesisNoStack {

    // score is powers of 2s of the inner core ()
    public int scoreOfParentheses(String s) {
        int scores = 0;
        int n = s.length();
        int level = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                level++;
            } else if (s.charAt(i) == ')') {
                level--;
                if (s.charAt(i - 1) == '(') {
                    scores += Math.pow(2, level);
                }
            }
        }
        return scores;
    }
}
