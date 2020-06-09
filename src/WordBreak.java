import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
LC#139
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false
 */
public class WordBreak {
    // note the different complexities of the three solutions.

    // Osn*len of dict*len of word.= Osn* size of dict
    public boolean wordBreak(String s, List<String> dict) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[n] = true;
        for (int i = n - 1; i >= 0; i--) {
            for (String d : dict) {
                if (s.startsWith(d, i) && dp[i + d.length()]) {
                    dp[i] = true;
                }
            }
        }
        return dp[0];
    }
}

class WordBreakAnotherDp {
    // using memoization, O(sn*min(sn,len of word)^2).  if we use a trie we can reduce to Osn^2
    int[] dp;

    public boolean wordBreak(String s, List<String> list) {
        Set<String> dict = new HashSet<>();
        int maxlen = 0;
        for (String l : list) {
            dict.add(l);
            maxlen = Math.max(maxlen, l.length());
        }
        if (s.isEmpty()) {
            return list.contains(s);
        }
        dp = new int[s.length()];
        return dobreak(0, s, dict, maxlen) == 1;
    }

    int dobreak(int i, String s, Set<String> dict, int maxlen) {
        if (i == s.length()) {
            return 1;
        }
        if (dp[i] != 0) {
            return dp[i];
        }
        StringBuilder sb = new StringBuilder();
        for (int j = i; j < s.length(); j++) {
            // j is the end point of the segment
            sb.append(s.charAt(j));
            if (dict.contains(sb.toString())) {
                if (dobreak(j + 1, s, dict, maxlen) == 1) {
                    return 1;
                }
            }
            if (sb.length() == maxlen) {
                break;
            }
        }
        dp[i] = 2;
        return 2;
    }
}

class WordBreakDpWithTrie {
    // based on the dp solution above we can usually optimize out the substring part with a running trie
    // O(sn* min(sn,maxlen of word) + dict size to build the trie)
    class Trie {
        Trie[] ch = new Trie[26];
        boolean isword = false;

        void insert(String s, int i) {
            if (i == s.length()) {
                isword = true;
                return;
            }
            int cind = s.charAt(i) - 'a';
            Trie next = ch[cind];
            if (next == null) {
                next = ch[cind] = new Trie();
            }
            next.insert(s, i + 1);
        }
    }

    Trie root = new Trie();

    public boolean wordBreak(String s, List<String> dict) {
        for (String d : dict) {
            root.insert(d, 0);
        }
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[n] = true;
        for (int i = n - 1; i >= 0; i--) {
            Trie next = root;
            for (int j = i; j < s.length(); j++) {
                next = next.ch[s.charAt(j) - 'a'];
                if (next == null) {
                    // will break once we reach max word len
                    break;
                }
                if (next.isword && dp[j + 1]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[0];
    }
}
