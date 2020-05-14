/*
LC#1324
Given a string s. Return all the words vertically in the same order in which they appear in s.
Words are returned as a list of strings, complete with spaces when is necessary. (Trailing spaces are not allowed).
Each word would be put on only one column and that in one column there will be only one word.



Example 1:

Input: s = "HOW ARE YOU"
Output: ["HAY","ORO","WEU"]
Explanation: Each word is printed vertically.
 "HAY"
 "ORO"
 "WEU"
Example 2:

Input: s = "TO BE OR NOT TO BE"
Output: ["TBONTB","OEROOE","   T"]
Explanation: Trailing spaces is not allowed.
"TBONTB"
"OEROOE"
"   T"
Example 3:

Input: s = "CONTEST IS COMING"
Output: ["CIC","OSO","N M","T I","E N","S G","T"]


Constraints:

1 <= s.length <= 200
s contains only upper case English letters.
It's guaranteed that there is only one space between 2 words.
 */

import java.util.ArrayList;
import java.util.List;

public class PrintWordsVertically {
    // row count is the max word length, column count is word count
    // then need to cut trailing spaces in the solution
    public List<String> printVertically(String s) {
        String[] words = s.split(" ");
        int rows = 0;
        for (int i = 0; i < words.length; i++) {
            rows = Math.max(rows, words[i].length());
        }
        int cols = words.length;
        char[][] rc = new char[rows][cols];
        for (int j = 0; j < words.length; j++) {
            for (int i = 0; i < words[j].length(); i++) {
                rc[i][j] = words[j].charAt(i);
            }
            for (int i = words[j].length(); i < rows; i++) {
                rc[i][j] = ' ';
            }
        }
        List<String> r = new ArrayList<>();
        for (char[] c : rc) {
            int i = c.length - 1;
            while (c[i] == ' ') {
                i--;
            }
            // 0...i needed
            r.add(new String(c, 0, i + 1));
        }
        return r;
    }
}
