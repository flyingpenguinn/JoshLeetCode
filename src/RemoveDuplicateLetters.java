import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

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
        int n = s.length();
        int[] pos = new int[26];
        for (int i = 0; i < n; i++) {
            int cind = s.charAt(i) - 'a';
            pos[cind] = i;
        }
        Deque<Integer> st = new ArrayDeque<>();
        Set<Integer> seen = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int cind = s.charAt(i) - 'a';
            if (seen.contains(cind)) {
                continue;
            }
            while (!st.isEmpty() && st.peek() > cind && pos[st.peek()] > i) {
                seen.remove(st.pop());
            }
            st.push(cind);
            seen.add(cind);
        }
        StringBuilder res = new StringBuilder();
        while (!st.isEmpty()) {
            res.append((char) ('a' + st.pop()));
        }
        return res.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(new RemoveDuplicateLetters().removeDuplicateLetters("c"));
    }
}
