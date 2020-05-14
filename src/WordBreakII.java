import java.util.*;

/*
LC#140
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
[
  "cats and dog",
  "cat sand dog"
]
Example 2:

Input:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
Output:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
Explanation: Note that you are allowed to reuse a dictionary word.
Example 3:

Input:
s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]
Output:
[]
 */
public class WordBreakII {
    List<String>[] dp;

    public List<String> wordBreak(String s, List<String> ds) {
        dp = new ArrayList[s.length() + 1];
        return dow(s, 0, ds);
    }

    List<String> dow(String s, int i, List<String> ds) {
        if (dp[i] != null) {
            return dp[i];
        }
        List<String> r = new ArrayList<>();
        if (i == s.length()) {
            r.add("");
        } else {
            for (String d : ds) {
                if (s.startsWith(d, i)) {
                    int j = i + d.length();
                    List<String> c = dow(s, j, ds);
                    for (String cs : c) {
                        String ncs = cs.isEmpty() ? d : d + " " + cs;
                        r.add(ncs);
                    }
                }
            }
        }
        dp[i] = r;
        return r;
    }
}
