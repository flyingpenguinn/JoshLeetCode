/*
LC#424
Given a string s that consists of only uppercase English letters, you can perform at most k operations on that string.

In one operation, you can choose any character of the string and change it to any other uppercase English character.

Find the length of the longest sub-string containing all repeating letters you can get after performing the above operations.

Note:
Both the string's length and k will not exceed 104.

Example 1:

Input:
s = "ABAB", k = 2

Output:
4

Explanation:
Replace the two 'A's with two 'B's or vice versa.


Example 2:

Input:
s = "AABABBA", k = 1

Output:
4

Explanation:
Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.
 */

public class LongestRepeatingCharReplace {
    // at most k "other" chars apart from the max occr char
    public int characterReplacement(String s, int k) {
        int[] m = new int[26];
        int low = 0;
        int high = -1;
        int max = 0;

        while (true) {
            int len = high - low + 1;
            int maxoccr = 0;
            for (int i = 0; i < 26; i++) {
                maxoccr = Math.max(maxoccr, m[i]);
            }
            if (len - maxoccr <= k) {
                max = Math.max(len, max);
                if (high == s.length() - 1) {
                    break;
                }
                char c = s.charAt(++high);
                m[c - 'A']++;
            } else {
                char c = s.charAt(low++);
                m[c - 'A']--;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new LongestRepeatingCharReplace().characterReplacement("AABABBA", 1));
    }
}
