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

    // using the n*len^2 solution from word break 1 with string result caching simple dfs will tle
    // in the worst case the solution space can be exponential: for len i, it generates 2^i-1 strings. for example aaaa with any len of a as dict string
    public List<String> wordBreak(String s, List<String> dict) {
        List<String> r = new ArrayList<>();
        if (s == null || s.isEmpty() || dict.isEmpty()) {
            return r;
        }
        int n = s.length();
        List<String>[] dp = new ArrayList[n];
        int mlen = 0;
        Set<String> set = new HashSet<>();
        for (String w : dict) {
            set.add(w);
            mlen = Math.max(mlen, w.length());
        }
        return dfs(0, s, set, mlen, dp);
    }

    private List<String> dfs(int i, String s, Set<String> dict, int mlen, List<String>[] dp) {
        int n = s.length();
        List<String> r = new ArrayList<>();
        if (i == n) {
            r.add("");
            return r;
        }
        if (dp[i] != null) {
            return dp[i];
        }
        StringBuilder sb = new StringBuilder();
        for (int j = i; j < n && j < i + mlen; j++) {
            sb.append(s.charAt(j));
            String str = sb.toString(); // i...j in string
            if (dict.contains(str)) {
                List<String> later = dfs(j + 1, s, dict, mlen, dp); // next one from j+1
                for (String ls : later) {
                    String cur = str + (ls.isEmpty() ? "" : " " + ls);
                    r.add(cur);
                }
            }
        }
        dp[i] = r;
        return r;
    }
}
