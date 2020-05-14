/*
LC#844
Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.

Example 1:

Input: S = "ab#c", T = "ad#c"
Output: true
Explanation: Both S and T become "ac".
Example 2:

Input: S = "ab##", T = "c#d#"
Output: true
Explanation: Both S and T become "".
Example 3:

Input: S = "a##c", T = "#a#c"
Output: true
Explanation: Both S and T become "c".
Example 4:

Input: S = "a#c", T = "b"
Output: false
Explanation: S becomes "c" while T becomes "b".
Note:

1 <= S.length <= 200
1 <= T.length <= 200
S and T only contain lowercase letters and '#' characters.
Follow up:

Can you solve it in O(N) time and O(1) space?
 */
public class BackspaceStringComparison {
    // can also go backward and count the #s
    public boolean backspaceCompare(String s, String t) {
        char[] c1 = s.toCharArray();
        int p1 = 0;
        for (int i = 0; i < c1.length; i++) {
            if (c1[i] == '#') {
                p1 = p1 == 0 ? 0 : p1 - 1;
            } else {
                c1[p1++] = c1[i];
            }
        }
        char[] c2 = t.toCharArray();
        int p2 = 0;
        for (int i = 0; i < c2.length; i++) {
            if (c2[i] == '#') {
                p2 = p2 == 0 ? 0 : p2 - 1;
            } else {
                c2[p2++] = c2[i];
            }
        }
        while (p1 - 1 >= 0 && p2 - 1 >= 0) {
            if (c1[p1 - 1] != c2[p2 - 1]) {
                return false;
            }
            p1--;
            p2--;
        }
        return p1 == p2;
    }
}
