import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/*
LC#358
Given a non-empty string s and an integer k, rearrange the string such that the same characters are at least distance k from each other.

All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".

Example 1:

Input: s = "aabbcc", k = 3
Output: "abcabc"
Explanation: The same letters are at least distance 3 from each other.
Example 2:

Input: s = "aaabc", k = 3
Output: ""
Explanation: It is not possible to rearrange the string.
Example 3:

Input: s = "aaadbbcc", k = 2
Output: "abacabcd"
Explanation: The same letters are at least distance 2 from each other.
 */
public class RearrangeStringKDistanceApart {
    // almost the same as task scheduler and similar idea to distant barcode. we need to know the char count and arrange the mos frequent first
    // note pay attention to the last group it may not be of length k
    public String rearrangeString(String s, int k) {
        if (k <= 1) {
            return s;
        }
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            count[c - 'a']++;
        }
        List<int[]> clist = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if (count[i] != 0) {
                clist.add(new int[]{i, count[i]});
            }
        }
        // bigger first
        Collections.sort(clist, (a, b) -> a[1] != b[1] ? Integer.compare(b[1], a[1]) : a[0] - b[0]);
        StringBuilder sb = new StringBuilder();
        // use whether first >0 to judge if it's empty
        while (clist.get(0)[1] > 0) {
            int i = 0;
            for (; i < k && i < clist.size(); i++) {
                if (clist.get(i)[1] == 0) {
                    break;
                }
                clist.get(i)[1]--;
                sb.append((char) ('a' + clist.get(i)[0]));
            }
            if (clist.get(0)[1] > 0 && i < k) {
                return "";
            }
            Collections.sort(clist, (a, b) -> a[1] != b[1] ? Integer.compare(b[1], a[1]) : a[0] - b[0]);
        }
        return sb.toString();
    }
}
