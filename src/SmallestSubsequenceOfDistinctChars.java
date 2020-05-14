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
    public String smallestSubsequence(String text) {
        int[] map = new int[26];
        for (int i = 0; i < text.length(); i++) {
            map[text.charAt(i) - 'a']++;
        }
        boolean[] put = new boolean[26];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            int cint = c - 'a';
            if (put[cint]) {
                map[cint]--;
                continue;
            }
            while (!stack.isEmpty() && stack.peek() > cint && map[stack.peek()] > 0) {
                int popped = stack.pop();
                put[popped] = false;
            }
            stack.push(cint);
            put[cint] = true;
            map[cint]--;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append((char) (stack.pop() + 'a'));
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(new SmallestSubsequenceOfDistinctChars().smallestSubsequence("abcd"));
        System.out.println(new SmallestSubsequenceOfDistinctChars().smallestSubsequence("cdadabcc"));
        System.out.println(new SmallestSubsequenceOfDistinctChars().smallestSubsequence("ecbacba"));
        System.out.println(new SmallestSubsequenceOfDistinctChars().smallestSubsequence("leetcode"));
    }

}
