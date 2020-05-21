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
    // note the special processing of rows==1
    public String convert(String s, int rows) {
        if (rows == 1) {
            return s;
        }
        StringBuilder[] sbs = new StringBuilder[rows];
        for (int i = 0; i < rows; i++) {
            sbs[i] = new StringBuilder();
        }
        int i = 0;
        int r = 0;
        int n = s.length();
        while (i < n) {
            while (i < n && r < rows) {
                sbs[r++].append(s.charAt(i++));
            }
            r -= 2; // move back to rows-2. note we can't step on the last row
            while (i < n && r >= 0) {
                sbs[r--].append(s.charAt(i++));
            }
            r += 2; // move back to 1. note we can't step on the first row
        }
        StringBuilder res = new StringBuilder();
        for (int j = 0; j < rows; j++) {
            res.append(sbs[j]);
        }
        return res.toString();
    }
}
