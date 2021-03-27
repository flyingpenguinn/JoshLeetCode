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
        char[] sc = s.toCharArray();
        int res = 0;
        int n = sc.length;
        for (int i = 0; i < n; i++) {
            res += count(sc, i, i);
            if (i + 1 < n) {
                res += count(sc, i, i + 1);
            }
        }
        return res;
    }

    // starting from l and u how many palindrome substrings can we get
    private int count(char[] s, int l, int u) {
        int i = l;
        int j = u;
        int res = 0;
        while (i >= 0 && j < s.length && s[i--] == s[j++]) {
            res++;
        }
        return res;
    }
}
