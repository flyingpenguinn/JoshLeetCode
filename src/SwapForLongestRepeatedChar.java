import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/*
LC#1156
Given a string text, we are allowed to swap two of the characters in the string. Find the length of the longest substring with repeated characters.



Example 1:

Input: text = "ababa"
Output: 3
Explanation: We can swap the first 'b' with the last 'a', or the last 'b' with the first 'a'. Then, the longest repeated character substring is "aaa", which its length is 3.
Example 2:

Input: text = "aaabaaa"
Output: 6
Explanation: Swap 'b' with the last 'a' (or the first 'a'), and we get longest repeated character substring "aaaaaa", which its length is 6.
Example 3:

Input: text = "aaabbaaa"
Output: 4
Example 4:

Input: text = "aaaaa"
Output: 5
Explanation: No need to swap, longest repeated character substring is "aaaaa", length is 5.
Example 5:

Input: text = "abcdef"
Output: 1


Constraints:

1 <= text.length <= 20000
text consist of lowercase English characters only.
 */
public class SwapForLongestRepeatedChar {
    // find max window where max char happens all the time, or gap-1 time and we can find a good swap target outside current window
    public int maxRepOpt1(String text) {
        char[] a = text.toCharArray();
        int n = a.length;
        int[][] chars = new int[26][2];
        for (int i = 0; i < 26; i++) {
            chars[i][0] = n + 1;
            chars[i][1] = -1;
        }
        for (int i = 0; i < n; i++) {
            int cind = a[i] - 'a';
            chars[cind][0] = Math.min(chars[cind][0], i);
            chars[cind][1] = Math.max(chars[cind][1], i);
        }
        Map<Integer, Integer> m = new HashMap<>();
        int low = 0;
        int high = -1;
        int res = 0;
        while (true) {
            int gap = high - low + 1;
            int mc = maxcount(m);
            if (mc == gap || ((mc == gap - 1) && canswap(m, gap - 1, low, high, chars))) {
                res = Math.max(res, gap);
                high++;
                if (high == n) {
                    break;
                }
                int cind = a[high] - 'a';
                update(m, cind, 1);
            } else {
                update(m, a[low] - 'a', -1);
                low++;
            }
        }
        return res;
    }

    private int maxcount(Map<Integer, Integer> m) {
        int max = 0;
        for (int v : m.values()) {
            max = Math.max(max, v);
        }
        return max;
    }

    private void update(Map<Integer, Integer> m, int k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }

    private boolean canswap(Map<Integer, Integer> m, int v, int low, int high, int[][] chars) {
        for (int k : m.keySet()) {
            if (m.get(k) == v) {
                if (chars[k][0] < low || chars[k][1] > high) {
                    return true;
                }
            }
        }
        return false;
    }
}
