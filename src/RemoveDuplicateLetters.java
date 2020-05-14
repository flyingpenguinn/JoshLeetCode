import java.util.ArrayDeque;
import java.util.Deque;

/*
LC#316
Given a string which contains only lowercase letters, remove duplicate letters so that every letter appears once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.

Example 1:

Input: "bcabc"
Output: "abc"
Example 2:

Input: "cbacdcbc"
Output: "acdb"
 */
public class RemoveDuplicateLetters {
    // pop bigger out of stack if there are more later
    // if already in stack ignore. left ones always better
    public String removeDuplicateLetters(String s) {
        char[] cs = s.toCharArray();
        int n = s.length();
        int[] m = new int[26];
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            m[cs[i] - 'a']++;
        }
        Deque<Character> st = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (cnt[cs[i] - 'a'] == 0) {
                while (!st.isEmpty() && st.peek() > cs[i] && m[st.peek() - 'a'] > 0) {
                    cnt[st.pop() - 'a']--;
                }
                st.push(cs[i]);
                cnt[cs[i] - 'a']++;
            }
            m[cs[i] - 'a']--;

        }
        StringBuilder sb = new StringBuilder();
        while (!st.isEmpty()) {
            sb.append(st.pop());
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(new RemoveDuplicateLetters().removeDuplicateLetters("c"));
    }
}
