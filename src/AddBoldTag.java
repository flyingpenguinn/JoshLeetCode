/*
LC#616
Given a string s and a list of strings dict, you need to add a closed pair of bold tag <b> and </b> to wrap the substrings in s that exist in dict. If two such substrings overlap, you need to wrap them together by only one pair of closed bold tag. Also, if two substrings wrapped by bold tags are consecutive, you need to combine them.
Example 1:

Input:
s = "abcxyz123"
dict = ["abc","123"]
Output:
"<b>abc</b>xyz<b>123</b>"


Example 2:

Input:
s = "aaabbcc"
dict = ["aaa","aab","bc"]
Output:
"<b>aaabbc</b>c"


Constraints:

The given dict won't contain duplicates, and its length won't exceed 100.
All the strings in input have length in range [1, 1000].
Note: This question is the same as 758: https://leetcode.com/problems/bold-words-in-string/
 */
public class AddBoldTag {
    public String addBoldTag(String s, String[] dict) {
        // non null, if so error out
        // size of s * size of dict
        int n = s.length();
        boolean[] bold = new boolean[n];
        for (int i = 0; i < n; i++) {
            for (String w : dict) {
                if (s.startsWith(w, i)) {
                    for (int j = i; j < i + w.length(); j++) {
                        bold[j] = true;
                    }
                }
            }
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
