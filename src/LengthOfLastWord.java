/*
LC#58
Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.

If the last word does not exist, return 0.

Note: A word is defined as a character sequence consists of non-space characters only.

Example:

Input: "Hello World"
Output: 5
 */
public class LengthOfLastWord {
    // deal with the boundaries especiall the start! i can be -1
    public int lengthOfLastWord(String s) {
        int n = s.length();
        int we = 0;
        // 1 char before the real start
        int ws = 0;
        for (int i = n - 1; i >= -1; i--) {
            boolean nonblank = i >= 0 && s.charAt(i) != ' ';
            boolean nextisblankorend = i + 1 == n || s.charAt(i + 1) == ' ';
            if (nonblank && nextisblankorend) {
                we = i;
            }
            boolean isblankorstart = i == -1 || s.charAt(i) == ' ';
            boolean nextnonblank = i + 1 < n && s.charAt(i + 1) != ' ';
            if (isblankorstart && nextnonblank) {
                ws = i;
                break;
            }
        }
        // ws already one step forward...
        return we - ws;
    }
}
