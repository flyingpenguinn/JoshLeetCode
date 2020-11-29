import java.util.*;

/*
LC#1081
Return the lexicographically smallest subsequence of text that contains all the distinct characters of text exactly once.



Example 1:

Input: "cdadabcc"
Output: "adbc"
Example 2:

Input: "abcd"
Output: "abcd"
Example 3:

Input: "ecbacba"
Output: "eacb"
Example 4:

Input: "leetcode"
Output: "letcod"


Note:

1 <= text.length <= 1000
text consists of lowercase English letters.
 */
public class SmallestSubsequenceOfDistinctChars {
    // identical to #316
    // pop out if adding a smaller one won't hurt earlier big ones in the stack
    public String smallestSubsequence(String s) {
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
                // avoid duplicates
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
        System.out.println(new SmallestSubsequenceOfDistinctChars().smallestSubsequence("abcd"));
        System.out.println(new SmallestSubsequenceOfDistinctChars().smallestSubsequence("cdadabcc"));
        System.out.println(new SmallestSubsequenceOfDistinctChars().smallestSubsequence("ecbacba"));
        System.out.println(new SmallestSubsequenceOfDistinctChars().smallestSubsequence("leetcode"));
    }

}
