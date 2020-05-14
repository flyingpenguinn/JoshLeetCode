/*
LC#186
Given an input string , reverse the string word by word.

Example:

Input:  ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
Output: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
Note:

A word is defined as a sequence of non-space characters.
The input string does not contain leading or trailing spaces.
The words are always separated by a single space.
Follow up: Could you do it in-place without allocating extra space?
 */

public class ReverseWordsInaStringII {
    // ABC => ArBrCr=> CBA
    public void reverseWords(char[] s) {
        int n = s.length;
        int start = 0;
        for (int i = 0; i <= n; i++) {
            if (i == n || s[i] == ' ') { // no leading or trailing
                reverse(s, start, i - 1);
            } else if (i > 0 && s[i - 1] == ' ') {
                start = i;
            }
        }
        reverse(s, 0, s.length - 1);
    }

    private void reverse(char[] s, int i, int j) {
        while (i < j) {
            swap(s, i++, j--);
        }
    }

    private void swap(char[] s, int i, int j) {
        char tmp = s[i];
        s[i] = s[j];
        s[j] = tmp;
    }
}
