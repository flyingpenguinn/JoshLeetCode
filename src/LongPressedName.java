/*
LC#925
Your friend is typing his name into a keyboard.  Sometimes, when typing a character c, the key might get long pressed, and the character will be typed 1 or more times.

You examine the typed characters of the keyboard.  Return True if it is possible that it was your friends name, with some characters (possibly none) being long pressed.



Example 1:

Input: name = "alex", typed = "aaleex"
Output: true
Explanation: 'a' and 'e' in 'alex' were long pressed.
Example 2:

Input: name = "saeed", typed = "ssaaedd"
Output: false
Explanation: 'e' must have been pressed twice, but it wasn't in the typed output.
Example 3:

Input: name = "leelee", typed = "lleeelee"
Output: true
Example 4:

Input: name = "laiden", typed = "laiden"
Output: true
Explanation: It's not necessary to long press any character.


Note:

name.length <= 1000
typed.length <= 1000
The characters of name and typed are lowercase letters.
 */
public class LongPressedName {
    // t block no smaller than n block
    public boolean isLongPressedName(String n, String t) {
        int nn = n.length();
        int tn = t.length();
        int i = 0;
        int j = 0;
        while (i < nn && j < tn) {
            if (n.charAt(i) != t.charAt(j)) {
                return false;
            }
            int ik = i;
            while (ik < nn && n.charAt(ik) == n.charAt(i)) {
                ik++;
            }
            int jk = j;
            while (jk < tn && t.charAt(jk) == t.charAt(j)) {
                jk++;
            }
            if (ik - i > jk - j) {
                return false;
            }
            i = ik;
            j = jk;
        }
        return i == nn && j == tn;

    }
}
