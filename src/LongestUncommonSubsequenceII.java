import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
LC#522
Given a list of strings, you need to find the longest uncommon subsequence among them. The longest uncommon subsequence is defined as the longest subsequence of one of these strings and this subsequence should not be any subsequence of the other strings.

A subsequence is a sequence that can be derived from one sequence by deleting some characters without changing the order of the remaining elements. Trivially, any string is a subsequence of itself and an empty string is a subsequence of any string.

The input will be a list of strings, and the output needs to be the length of the longest uncommon subsequence. If the longest uncommon subsequence doesn't exist, return -1.

Example 1:
Input: "aba", "cdc", "eae"
Output: 3
Note:

All the given strings' lengths will not exceed 10.
The length of the given list will be in the range of [2, 50].
 */
public class LongestUncommonSubsequenceII {
    // the result must be a full string, hence just check if it's sub of any given string
    public int findLUSlength(String[] strs) {
        int max = -1;
        Map<String, Integer> m = new HashMap<>();
        for (String s : strs) {
            m.put(s, m.getOrDefault(s, 0) + 1);
        }
        for (String s : m.keySet()) {
            if (m.get(s) > 1 || s.length() <= max) {
                // if there is one eq, can't have the candidate subsequence
                continue;
            }
            boolean bad = false;
            for (String other : m.keySet()) {
                if (other.equals(s)) {
                    continue;
                }
                if (issub(other, s)) {
                    bad = true;
                    break;
                }
            }
            if (!bad) {
                max = Math.max(max, s.length());
            }
        }

        return max;
    }

    boolean issub(String s, String sub) {
        int i = 0;
        int j = 0;
        while (i < s.length() && j < sub.length()) {
            if (s.charAt(i) == sub.charAt(j)) {
                j++;
            }
            i++;
        }
        return j == sub.length();
    }
}
