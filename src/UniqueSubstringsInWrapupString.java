/*
LC#467
Consider the string s to be the infinite wraparound string of "abcdefghijklmnopqrstuvwxyz", so s will look like this: "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".

Now we have another string p. Your job is to find out how many unique non-empty substrings of p are present in s. In particular, your input is the string p and you need to output the number of different non-empty substrings of p in the string s.

Note: p consists of only lowercase English letters and the size of p might be over 10000.

Example 1:
Input: "a"
Output: 1

Explanation: Only the substring "a" of string "a" is in the string s.
Example 2:
Input: "cac"
Output: 2
Explanation: There are two substrings "a", "c" of string "cac" in the string s.
Example 3:
Input: "zab"
Output: 6
Explanation: There are six substrings "z", "a", "b", "za", "ab", "zab" of string "zab" in the string s.
 */
public class UniqueSubstringsInWrapupString {
    public int findSubstringInWraproundString(String p) {
        if (p.length() == 0) {
            return 0;
        }
        // length of the longest stream starting in c. we use end at c because at the start we know the length clearly since we go backward
        int[] count = new int[26];
        int len = 0;
        char prev = p.charAt(p.length() - 1);
        int r = 0;
        for (int i = p.length() - 1; i >= 0; i--) {
            char c = p.charAt(i);
            if (c == prev - 1 || (prev == 'a' && c == 'z')) {
                len++;
            } else {
                len = 1;
            }
            int curmax = count[c - 'a'];
            if (len > curmax) {
                //    System.out.println(c+" "+len+" vs "+curmax);
                r += len - curmax;
                count[c - 'a'] = len;
            }
            prev = c;
        }
        return r;
    }
}