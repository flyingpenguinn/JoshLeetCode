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
        if (n == 0) {
            return false;
        }
        for (int len = n / 2; len >= 1; len--) {
            if (n % len != 0) {
                continue;
            }
            int j = len;
            boolean bad = false;
            while (j + len - 1 < n) {
                for (int k = 0; k < len; k++) {
                    if (s.charAt(k) != s.charAt(j + k)) {
                        bad = true;
                        break;
                    }
                }
                if (bad) {
                    break;
                } else {
                    j += len;
                }
            }
            if (!bad) {
                return true;
            }
        }
        return false;
    }
}
