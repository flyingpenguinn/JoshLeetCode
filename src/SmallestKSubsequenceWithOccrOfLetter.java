import java.util.ArrayDeque;
import java.util.Deque;

public class SmallestKSubsequenceWithOccrOfLetter {
    // refer to leetcode 316
    public String smallestSubsequence(String s, int k, char letter, int rep) {
        Deque<Character> st = new ArrayDeque<>();
        int n = s.length();
        int[] count = new int[n + 1];
        for (int i = n - 1; i >= 0; --i) {
            count[i] = (s.charAt(i) == letter ? 1 : 0) + count[i + 1];
        }
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            // 2nd condition: not only compare char value, but also letters take precedence if it's about repetition counts
            // 3rd condition: if we pop up letter, make sure we will have enough of it
            while (!st.isEmpty() && (st.peek() > s.charAt(i) || (c == letter && st.size() + rep > k)) && count[i] >= (rep + (st.peek() == letter ? 1 : 0)) && (st.size() - 1 + n - i) >= k) {
                char popped = st.pop();
                if (popped == letter) {
                    ++rep;
                }
            }
            if (c == letter) {
                --rep;
            }
            st.push(c);
        }
        StringBuilder sb = new StringBuilder();
        while (!st.isEmpty()) {
            sb.append(st.pop());
        }
        return sb.reverse().toString().substring(0, k);
    }

    public static void main(String[] args) {
        System.out.println(new SmallestKSubsequenceWithOccrOfLetter().smallestSubsequence("hjjhhhmhhwhz", 6, 'h', 5));
        System.out.println(new SmallestKSubsequenceWithOccrOfLetter().smallestSubsequence("adffhjfmmmmorsfff", 6, 'f', 5));
    }
}
