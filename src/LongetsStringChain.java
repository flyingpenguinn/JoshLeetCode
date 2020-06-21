import java.util.*;

/*
LC#1048
Given a list of words, each word consists of English lowercase letters.

Let's say word1 is a predecessor of word2 if and only if we can add exactly one letter anywhere in word1 to make it equal to word2.  For example, "abc" is a predecessor of "abac".

A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >= 1, where word_1 is a predecessor of word_2, word_2 is a predecessor of word_3, and so on.

Return the longest possible length of a word chain with words chosen from the given list of words.



Example 1:

Input: ["a","b","ba","bca","bda","bdca"]
Output: 4
Explanation: one of the longest word chain is "a","ba","bda","bdca".


Note:

1 <= words.length <= 1000
1 <= words[i].length <= 16
words[i] only consists of English lowercase letters.
 */
public class LongetsStringChain {
    // n*l*l
    // use the fact that we can get a previous match in l^2 time rather than n*l time
    public int longestStrChain(String[] ws) {
        int n = ws.length;
        Arrays.sort(ws, (x, y) -> Integer.compare(x.length(), y.length()));
        Map<String, Integer> dp = new HashMap<>();
        int max = 0;
        for (int i = 0; i < n; i++) {
            int curmax = 1;
            for (int j = 0; j < ws[i].length(); j++) {
                StringBuilder sb = new StringBuilder(ws[i]);
                sb.deleteCharAt(j);
                String str = sb.toString();
                if (dp.containsKey(str)) {
                    int cached = dp.get(str);
                    curmax = Math.max(curmax, cached + 1);
                }
            }
            max = Math.max(max, curmax);
            dp.put(ws[i], curmax);
        }
        return max;
    }
}

class LongestStringChainAlternative {
    //  n^2*l
    // trap: equal words are not part of the chain
    // similar to LIS problem but we work backward here
    public int longestStrChain(String[] ws) {
        int n = ws.length;
        Arrays.sort(ws, (x, y) -> Integer.compare(x.length(), y.length()));
        int[] dp = new int[n];
        int max = 0;
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (ws[j].length() > ws[i].length() + 1) {
                    break;
                }
                if (chain(ws[i], ws[j])) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    boolean chain(String s, String t) {
        int i = 0;
        int j = 0;
        boolean diff = false;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) != t.charAt(j)) {
                if (diff) {
                    return false;
                } else {
                    j++;
                    diff = true;
                }
            } else {
                i++;
                j++;
            }
        }
        if (i == s.length() && j == t.length()) {
            return diff; // can't be all matching...
        } else if (i == s.length() && j == t.length() - 1) {
            return diff ? false : true;  // must all match before
        } else {
            return false;
        }
    }
}