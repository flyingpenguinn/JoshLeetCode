import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/*
LC#828
Let's define a function countUniqueChars(s) that returns the number of unique characters on s, for example if s = "LEETCODE" then "L", "T","C","O","D" are the unique characters since they appear only once in s, therefore countUniqueChars(s) = 5.

On this problem given a string s we need to return the sum of countUniqueChars(t) where t is a substring of s. Notice that some substrings can be repeated so on this case you have to count the repeated ones too.

Since the answer can be very large, return the answer modulo 10 ^ 9 + 7.



Example 1:

Input: s = "ABC"
Output: 10
Explanation: All possible substrings are: "A","B","C","AB","BC" and "ABC".
Evey substring is composed with only unique letters.
Sum of lengths of all substring is 1 + 1 + 1 + 2 + 2 + 3 = 10
Example 2:

Input: s = "ABA"
Output: 8
Explanation: The same as example 1, except countUniqueChars("ABA") = 1.
Example 3:

Input: s = "LEETCODE"
Output: 92


Constraints:

0 <= s.length <= 10^4
s contain upper-case English letters only.
 */
public class CountUniqueCharacterSubstring {
    // think about contribution of each i on top of dp[i-1]
    private long Mod = 1000000007;

    public int uniqueLetterString(String s) {
        int n = s.length();
        Deque<Integer>[] pre = new ArrayDeque[26];
        for (int i = 0; i < 26; i++) {
            pre[i] = new ArrayDeque<>();
        }
        long last = 0;
        long res = 0;
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'A';
            Deque<Integer> dq = pre[c];
            int j = dq.isEmpty() ? -1 : dq.peekLast();
            int k = dq.size() < 2 ? -1 : dq.peekFirst();
            long cur = last + (i - j) - (j - k);
            // from j+1....i these substrings got 1 more unique char. from k+1...j these substrings lost 1 unique char
            res += cur;
            res %= Mod;
            last = cur;
            dq.offerLast(i);
            if (dq.size() > 2) {
                dq.pollFirst();
            }
        }
        return (int) res;
    }
}
