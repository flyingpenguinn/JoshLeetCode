
/*
LC#1247
You are given two strings s1 and s2 of equal length consisting of letters "x" and "y" only. Your task is to make these two strings equal to each other. You can swap any two characters that belong to different strings, which means: swap s1[i] and s2[j].

Return the minimum number of swaps required to make s1 and s2 equal, or return -1 if it is impossible to do so.



Example 1:

Input: s1 = "xx", s2 = "yy"
Output: 1
Explanation:
Swap s1[0] and s2[1], s1 = "yx", s2 = "yx".
Example 2:

Input: s1 = "xy", s2 = "yx"
Output: 2
Explanation:
Swap s1[0] and s2[0], s1 = "yy", s2 = "xx".
Swap s1[0] and s2[1], s1 = "xy", s2 = "xy".
Note that you can't swap s1[0] and s1[1] to make s1 equal to "yx", cause we can only swap chars in different strings.
Example 3:

Input: s1 = "xx", s2 = "xy"
Output: -1
Example 4:

Input: s1 = "xxyyxyxyxx", s2 = "xyyxyxxxyx"
Output: 4


Constraints:

1 <= s1.length, s2.length <= 1000
s1, s2 only contain 'x' or 'y'.
 */
public class MinSwapsToMakeStringEqual {
    // skip same ones
    // all xxxyyyy and yyyyxxxx can be solved in /2 swaps
    // if there is one xy and yx left them pair them and +2 counts
    // otherwise if just one xy or yx left it's not doable
    public int minimumSwap(String s1, String s2) {
        int type1 = 0;
        int type2 = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == 'x' && s2.charAt(i) == 'y') {
                type1++;
            }
            if (s1.charAt(i) == 'y' && s2.charAt(i) == 'x') {
                type2++;
            }
        }
        int stub = 0;
        int r = 0;
        r += type1 / 2;
        r += type2 / 2;
        if (type1 % 2 == 1) {
            stub++;
        }
        if (type2 % 2 == 1) {
            stub++;
        }
        if (stub == 1) {
            return -1;
        }
        return r + stub;
    }
}
