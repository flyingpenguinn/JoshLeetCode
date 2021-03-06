import java.util.Arrays;

/*
LC#758
Given a set of keywords words and a string S, make all appearances of all keywords in S bold. Any letters between <b> and </b> tags become bold.

The returned string should use the least number of tags possible, and of course the tags should form a valid combination.

For example, given that words = ["ab", "bc"] and S = "aabcd", we should return "a<b>abc</b>d". Note that returning "a<b>a<b>b</b>c</b>d" would use more tags, so it is incorrect.

Constraints:

words has length in range [0, 50].
words[i] has length in range [1, 10].
S has length in range [0, 500].
All characters in words[i] and S are lowercase letters.
Note: This question is the same as 616: https://leetcode.com/problems/add-bold-tag-in-string/
 */
public class BoldWordsInString {
    public String addBoldTag(String s, String[] dict) {
        // non null, if so error out
        // size of s * size of dict
        int n = s.length();
        boolean[] bold = new boolean[n];
        int end = -1; // bold till end inclusive
        for (int i = 0; i < n; i++) {
            for (String w : dict) {
                if (s.startsWith(w, i)) {
                    end = Math.max(end, i + w.length() - 1); // use end mark to avoid setting repetitively
                }
            }
            bold[i] = i <= end;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (bold[i] && (i == 0 || !bold[i - 1])) {
                sb.append("<b>");
            }
            sb.append(s.charAt(i));
            if (bold[i] && (i == n - 1 || !bold[i + 1])) {
                sb.append("</b>");
            }
        }
        return sb.toString();
    }
}
