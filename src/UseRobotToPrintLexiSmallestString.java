import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class UseRobotToPrintLexiSmallestString {
    // robot is basically a stack. best out of stack string
    public String robotWithString(String s) {
        int n = s.length();
        int[] right = new int[n];
        Arrays.fill(right, 30);
        right[n - 1] = s.charAt(n - 1) - 'a';
        for (int i = n - 2; i >= 0; --i) {
            right[i] = Math.min(right[i+1], s.charAt(i) - 'a');
        }
        Deque<Character> st = new ArrayDeque<>();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            while (!st.isEmpty() && (st.peek() - 'a') <= right[i]) {
                res.append(st.pop());
            }
            st.push(s.charAt(i));
        }
        while (!st.isEmpty()) {
            res.append(st.pop());
        }
        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(new UseRobotToPrintLexiSmallestString().robotWithString("bccabccabcca"));
    }
}
