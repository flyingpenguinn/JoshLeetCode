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
    // can use a better checking mechanism like note down the gap of tm and sm
    // note the diff from min window subsequence- there we need to include a subseq while where we include a multiset
    public String minWindow(String s, String t) {
        if (t == null || s == null || t.isEmpty() || s.isEmpty()) {
            return "";
        }
        int[] tm = new int[255];
        for (int i = 0; i < t.length(); i++) {
            tm[t.charAt(i)]++;
        }
        int low = 0;
        int high = -1;
        int[] sm = new int[255];
        int minlen = s.length() + 1;
        int mini = -1;
        while (true) {
            if (smaller(sm, tm)) {
                high++;
                if (high == s.length()) {
                    break;
                }
                sm[s.charAt(high)]++;
            } else {
                if (high - low + 1 < minlen) {
                    minlen = high - low + 1;
                    mini = low;
                }
                sm[s.charAt(low)]--;
                low++;
            }
        }
        if (mini == -1) {
            return "";
        } else {
            return s.substring(mini, mini + minlen);
        }
    }

    boolean smaller(int[] sm, int[] tm) {
        for (int i = 0; i < 255; i++) {
            if (sm[i] < tm[i]) {
                return true;
            }
        }
        return false;
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
