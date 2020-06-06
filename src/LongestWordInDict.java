import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
LC#720
Given a list of strings words representing an English Dictionary, find the longest word in words that can be built one character at a time by other words in words. If there is more than one possible answer, return the longest word with the smallest lexicographical order.

If there is no answer, return the empty string.
Example 1:
Input:
words = ["w","wo","wor","worl", "world"]
Output: "world"
Explanation:
The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".
Example 2:
Input:
words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
Output: "apple"
Explanation:
Both "apply" and "apple" can be built from other words in the dictionary. However, "apple" is lexicographically smaller than "apply".
Note:

All the strings in the input will only contain lowercase letters.
The length of words will be in the range [1, 1000].
The length of words[i] will be in the range [1, 30].
 */
public class LongestWordInDict {
    public String longestWord(String[] words) {
        Set<String> set = new HashSet<>();
        int n = words.length;
        for (int i = 0; i < n; i++) {
            set.add(words[i]);
        }
        Arrays.sort(words, (x, y) -> x.length() != y.length() ? Integer.compare(y.length(), x.length()) : x.compareTo(y));
        for (int i = 0; i < n; i++) {
            String cur = words[i];
            boolean bad = false;
            for (int j = 0; j < words[i].length() - 1; j++) {
                String stub = cur.substring(0, j + 1);
                if (!set.contains(stub)) {
                    bad = true;
                    break;
                }
            }
            if (!bad) {
                return cur;
            }
        }
        return "";
    }

}

// trie is good at prefix, not so much for subsequence like companion "delete char" question
class LongestWordTrie {
    class TrieNode {
        char val;
        boolean isWord;
        TrieNode[] ch = new TrieNode[26];

        public TrieNode(char val) {
            this.val = val;
        }

        void insert(String s, int i) {
            if (i == s.length()) {
                this.isWord = true;
                return;
            }
            char c = s.charAt(i);
            if (ch[c - 'a'] == null) {
                ch[c - 'a'] = new TrieNode(c);
            }
            ch[c - 'a'].insert(s, i + 1);
        }

        boolean hasChar(char c) {
            return ch[c - 'a'] != null;

        }

        TrieNode getChar(char c) {
            return ch[c - 'a'];
        }


    }

    TrieNode tn = new TrieNode('-');

    public String longestWord(String[] words) {
        Arrays.sort(words, (a, b) -> a.length() != b.length() ? Integer.compare(b.length(), a.length()) : a.compareTo(b));
        for (String w : words) {
            tn.insert(w, 0);
        }
        for (String w : words) {
            if (canForm(w, 0, tn)) {
                return w;
            }
        }
        return "";
    }

    private boolean canForm(String w, int i, TrieNode p) {
        if (i == w.length()) {
            return true;
        }
        char wc = w.charAt(i);
        if (p.hasChar(wc)) {
            TrieNode tn = p.getChar(wc);
            if (!tn.isWord) {
                return false;
            }
            return canForm(w, i + 1, tn);
        }
        return false;
    }
}