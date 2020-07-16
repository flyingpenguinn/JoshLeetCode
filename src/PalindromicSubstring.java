/*
LC#647
Given a string, your task is to count how many palindromic substrings in this string.

The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

Example 1:

Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".


Example 2:

Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".


Note:

The input string length won't exceed 1000.
 */
public class PalindromicSubstring {
    public int countSubstrings(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int r = 0;
        for (int i = 0; i < s.length(); i++) {
            r += count(i, i, s);
            r += count(i, i + 1, s);
        }
        return r;
    }

    private int count(int start, int end, String s) {
        int r = 0;
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            r++;
            start--;
            end++;
        }
        return r;
    }
}
