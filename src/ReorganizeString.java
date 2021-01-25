import java.util.*;

/*
LC#767
Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.

If possible, output any possible result.  If not possible, return the empty string.

Example 1:

Input: S = "aab"
Output: "aba"
Example 2:

Input: S = "aaab"
Output: ""
Note:

S will consist of lowercase letters and have length in range [1, 500].
 */
public class ReorganizeString {
    // almost same as task scheduler, segment length is 2 here while in that problem it's k+1
    public String reorganizeString(String s) {
        int[][] count = new int[26][2];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int cind = s.charAt(i) - 'a';
            count[cind][0]++;
            count[cind][1] = cind;
        }
        StringBuilder res = new StringBuilder();
        while (true) {
            Arrays.sort(count, (x, y) -> Integer.compare(y[0], x[0]));
            //   System.out.println(Arrays.deepToString(count));
            if (count[0][0] == 0) {
                break;
            }
            if (count[1][0] == 0 && count[0][0] > 1) {
                return "";
            }
            int put = 0;
            for (int i = 0; i < 26 && count[i][0] != 0 && put < 2; i++) {
                res.append((char) ('a' + count[i][1]));
                count[i][0]--;
                put++;
            }
        }
        return res.toString();
    }
}