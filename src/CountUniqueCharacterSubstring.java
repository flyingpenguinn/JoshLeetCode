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
    public int uniqueLetterString(String s) {
        Map<Character, Deque<Integer>> m = new HashMap<>();
        int n = s.length();
        long r = 0;
        long[] dp = new long[n];
        long Mod = 1000000007;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            Deque<Integer> pre = m.getOrDefault(c, new ArrayDeque<>());
            int j = pre.isEmpty() ? -1 : pre.peekLast();
            int k = pre.size() <= 1 ? -1 : pre.peekFirst();
            dp[i] = (i == 0 ? 0 : dp[i - 1]) + (i - j) - (j - k);
            dp[i] %= Mod;
            r += dp[i];
            r %= Mod;
            pre.offerLast(i);
            if (pre.size() > 2) {
                pre.pollFirst();
            }
            m.put(c, pre);
        }

        return (int) r;
    }
}
