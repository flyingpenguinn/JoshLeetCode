/*
LC#6
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"

Write the code that will take a string and make this conversion given a number of rows:

string convert(string s, int numRows);
Example 1:

Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"
Example 2:

Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:

P     I    N
A   L S  I G
Y A   H R
P     I

https://leetcode.com/problems/zigzag-conversion/
 */

public class ZigZagConversion {
    StringBuilder[] strs;
    boolean down = true;

    public String convert(String s, int n) {
        if (n <= 1) {
            return s;
        }
        // only deal with >= 2 rows
        strs = new StringBuilder[n];
        doc(s, 0, 0);
        StringBuilder sb = new StringBuilder();
        for (StringBuilder sbtr : strs) {
            if (sbtr != null) {
                sb.append(sbtr);
            }
        }
        return sb.toString();
    }

    // dont really need column just rows
    void doc(String s, int i, int r) {
        if (i == s.length()) {
            return;
        }
        // without init stringbuilders are null
        if (strs[r] == null) {
            strs[r] = new StringBuilder();
        }
        strs[r].append(s.charAt(i));
        if (r == strs.length - 1 && down) {
            down = false;
            doc(s, i + 1, r - 1);
        } else if (r == 0 && !down) {
            down = true;
            doc(s, i + 1, r + 1);
        } else if (down) {
            doc(s, i + 1, r + 1);
        } else {
            doc(s, i + 1, r - 1);
        }
    }
}
