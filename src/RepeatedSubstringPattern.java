/*
LC#459
Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together. You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.



Example 1:

Input: "abab"
Output: True
Explanation: It's the substring "ab" twice.
Example 2:

Input: "aba"
Output: False
Example 3:

Input: "abcabcabcabc"
Output: True
Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
 */
public class RepeatedSubstringPattern {
    // O(nsqrt(n) only check factor of n
    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        for (int len = n / 2; len >= 1; len--) {
            if (n % len == 0) {
                boolean bad = false;
                for (int i = len; i < n; i += len) {
                    // replace substring+equals with a method that checks if 2 strings are equal given starting points and len
                    if (!eq(s, i, 0, len)) {
                        bad = true;
                        break;
                    }
                }
                if (!bad) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean eq(String s, int i, int j, int len) {
        int k = 0;
        while (k < len) {
            if (s.charAt(i + k) != s.charAt(j + k)) {
                return false;
            }
            k++;
        }
        return true;
    }
}
