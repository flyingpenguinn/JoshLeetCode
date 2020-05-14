import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
LC#76
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

Example:

Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"
Note:

If there is no such window in S that covers all characters in T, return the empty string "".
If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 */
public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        int high = -1;
        int low = 0;
        int[] sm = new int[255];
        int[] tm = new int[255];
        int gap = 0;
        for (int i = 0; i < t.length(); i++) {
            int tc = t.charAt(i);
            tm[tc]++;
            if (tm[tc] == 1) {
                gap++;
            }
        }
        int min = Integer.MAX_VALUE;
        int mini = -1;
        int minj = -1;
        while (true) {
            if (gap > 0) {
                high++;
                if (high == s.length()) {
                    break;
                }
                int hc = s.charAt(high);
                int nhc = ++sm[hc];
                if (nhc == tm[hc]) {
                    gap--;
                }
            } else {
                int len = high - low + 1;
                if (len < min) {
                    min = len;
                    mini = low;
                    minj = high;
                }
                int lc = s.charAt(low);
                int nlc = --sm[lc];
                if (nlc + 1 == tm[lc]) {
                    gap++;
                }
                low++;
            }
        }
        return mini == -1 ? "" : s.substring(mini, minj + 1);
    }

    public static void main(String[] args) throws IOException {
        //    System.out.println(new MinimumWindowSubstring().minWindow("ADOBECODEBANC", "ABC"));
        //    System.out.println(new MinimumWindowSubstring().minWindow("a", "aa"));

        String file = "E:\\dev\\project\\JoshLeet\\tests\\MinwindowSubstring.txt";
        BufferedReader reader = new BufferedReader(new FileReader(new File(file)));
        String s = reader.readLine();
        String t = reader.readLine();
        reader.close();
        System.out.println(new MinimumWindowSubstring().minWindow(s, t));
    }
}
