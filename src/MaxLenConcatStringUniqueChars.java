import java.util.*;

/*
LC#1239
Given an array of strings arr. String s is a concatenation of a sub-sequence of arr which have unique characters.

Return the maximum possible length of s.



Example 1:

Input: arr = ["un","iq","ue"]
Output: 4
Explanation: All possible concatenations are "","un","iq","ue","uniq" and "ique".
Maximum length is 4.
Example 2:

Input: arr = ["cha","r","act","ers"]
Output: 6
Explanation: Possible solutions are "chaers" and "acters".
Example 3:

Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
Output: 26


Constraints:

1 <= arr.length <= 16
1 <= arr[i].length <= 26
arr[i] contains only lower case English letters.
 */
public class MaxLenConcatStringUniqueChars {

    public int maxLength(List<String> a) {
        int n = a.size();
        int res = 0;
        List<Integer> code = new ArrayList<>();
        Set<Integer> exc = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            String ai = a.get(i);
            int cc = 0;
            for (int j = 0; j < ai.length(); ++j) {
                int dig = (1 << (ai.charAt(j) - 'a'));
                if ((cc & dig) != 0) {
                    exc.add(i);
                    break;
                } else {
                    cc |= dig;
                }
            }
            code.add(cc);
        }
        for (int st = 0; st < (1 << n); ++st) {
            int cur = 0;
            boolean bad = false;
            int len = 0;
            for (int i = 0; i < n; ++i) {
                if (((st >> i) & 1) == 1) {
                    if (exc.contains(i)) {
                        bad = true;
                        break;
                    }
                    len += a.get(i).length();
                    if ((cur & code.get(i)) != 0) {
                        bad = true;
                        break;
                    } else {
                        cur |= code.get(i);
                    }
                }
            }
            if (!bad) {
                res = Math.max(res, len);
            }
        }
        return res;
    }
}
