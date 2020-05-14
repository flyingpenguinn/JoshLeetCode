import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
LC#1065
Given a text string and words (a list of strings), return all index pairs [i, j] so that the substring text[i]...text[j] is in the list of words.



Example 1:

Input: text = "thestoryofleetcodeandme", words = ["story","fleet","leetcode"]
Output: [[3,7],[9,13],[10,17]]
Example 2:

Input: text = "ababa", words = ["aba","ab"]
Output: [[0,1],[0,2],[2,3],[2,4]]
Explanation:
Notice that matches can overlap, see "aba" is found in [0,2] and [2,4].


Note:

All strings contains only lowercase English letters.
It's guaranteed that all strings in words are different.
1 <= text.length <= 100
1 <= words.length <= 20
1 <= words[i].length <= 50
Return the pairs [i,j] in sorted order (i.e. sort them by their first coordinate in case of ties sort them by their second coordinate).
 */
public class IndexPairsOfString {
    public int[][] indexPairs(String s, String[] words) {
        Arrays.sort(words, (a, b) -> a.length() - b.length()); // short first

        List<int[]> r = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            for (String w : words) {
                if (i + w.length() - 1 >= s.length()) {
                    break;
                }
                if (s.startsWith(w, i)) {
                    r.add(new int[]{i, i + w.length() - 1});
                }
            }
        }

        int[][] rr = new int[r.size()][2];
        for (int i = 0; i < rr.length; i++) {
            rr[i] = r.get(i);
        }
        return rr;
    }
}
