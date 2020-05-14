import java.util.ArrayDeque;
import java.util.Deque;

/*
LC#394
Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

Examples:

s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 */
public class DecodeString {
    public String decodeString(String s) {
        return dod(s, 0, s.length() - 1);
    }

    String dod(String s, int l, int u) {
        if (l > u) {
            return "";
        }
        int i = l;
        int times = 0;
        int level = 0;
        int start = -1;
        StringBuilder sb = new StringBuilder();
        while (i <= u) {
            char c = s.charAt(i);
            if (c == '[') {
                if (level == 0) {
                    start = i;
                }
                level++;
            } else if (c == ']') {
                level--;
                if (level == 0) {
                    String later = dod(s, start + 1, i - 1);
                    for (int j = 0; j < times; j++) {
                        sb.append(later);
                    }
                    times = 0;
                }
            } else if (Character.isDigit(c)) {
                if (level == 0) {
                    times = times * 10 + s.charAt(i) - '0';
                }
            } else {
                if (level == 0) {
                    sb.append(c);
                }
            }
            i++;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new DecodeStringStack().decodeString("3[a]2[bc]"));
    }
}

class DecodeStringStack {
    // create a new stack when we see [, pop up when we see ]. save the number of repeats of current level on the stack with the string
    class StackItem {
        StringBuilder s;
        int t;

        public StackItem(StringBuilder s, int t) {
            this.s = s;
            this.t = t;
        }
    }

    public String decodeString(String s) {
        Deque<StackItem> st = new ArrayDeque<>();
        // outer level always repeat once only
        st.push(new StackItem(new StringBuilder(), 1));
        int times = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                times = times * 10 + (c - '0');
            } else if (c == '[') {
                StackItem si = new StackItem(new StringBuilder(), times);
                st.push(si);
                times = 0;
            } else if (c == ']') {
                StringBuilder sb = new StringBuilder();
                StackItem top = st.pop();
                for (int j = 0; j < top.t; j++) {
                    sb.append(top.s);
                }
                st.peek().s.append(sb);
            } else {
                st.peek().s.append(c);
            }
        }
        return st.pop().s.toString();
    }
}
