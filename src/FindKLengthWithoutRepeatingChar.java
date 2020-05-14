/*
LC#1100
Given a string S, return the number of substrings of length K with no repeated characters.



Example 1:

Input: S = "havefunonleetcode", K = 5
Output: 6
Explanation:
There are 6 substrings they are : 'havef','avefu','vefun','efuno','etcod','tcode'.
Example 2:

Input: S = "home", K = 5
Output: 0
Explanation:
Notice K can be larger than the length of S. In this case is not possible to find any substring.


Note:

1 <= S.length <= 10^4
All characters of S are lowercase English letters.
1 <= K <= 10^4
 */

public class FindKLengthWithoutRepeatingChar {
    // sliding window but length is fixed
    public int numKLenSubstrNoRepeats(String s, int k) {
        int[] map = new int[26];
        int chars = 0;
        int rep = 0;
        int r = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int gc = ++map[c - 'a'];
            if (gc == 2) {
                rep++;
            }
            if (gc == 1) {
                chars++;
            }
            if (chars == k && rep == 0) {
                r++;
            }
            int head = i - k + 1;
            if (head >= 0) {
                char hc = s.charAt(head);
                int sc = --map[hc - 'a'];
                if (sc == 0) {
                    chars--;
                }
                if (sc == 1) {
                    rep--;
                }
            }
        }
        return r;
    }
}
