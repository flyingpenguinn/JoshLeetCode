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
    public int lengthOfLastWord(String s) {
        int n = s.length();
        int i = n - 1;
        while (i >= 0 && s.charAt(i) == ' ') {
            i--;
        }
        int len = 0;
        while (i >= 0 && s.charAt(i) != ' ') {
            len++;
            i--;
        }
        return len;
    }
}
