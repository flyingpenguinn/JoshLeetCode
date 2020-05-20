import base.ArrayUtils;

import java.util.HashMap;
import java.util.TreeSet;

/*
LC#745
Given many words, words[i] has weight i.

Design a class WordFilter that supports one function, WordFilter.f(String prefix, String suffix). It will return the word with given prefix and suffix with maximum weight. If no word exists, return -1.

Examples:

Input:
WordFilter(["apple"])
WordFilter.f("a", "e") // returns 0
WordFilter.f("b", "") // returns -1


Note:

words has length in range [1, 15000].
For each test case, up to words.length queries WordFilter.f may be made.
words[i] has length in range [1, 10].
prefix, suffix have lengths in range [0, 10].
words[i] and prefix, suffix queries consist of lowercase letters only.
 */
public class PrefixAndSuffixMatching {
    // make it suffix+"#"+prefix. we can quickly insert this into trie
    public static void main(String[] args) {
        String[] ws = {"abc", "ab"};
        WordFilter wf = new WordFilter(ws);
        wf.f("ab", "");
    }
}

class WordFilter {

    class Trie {
        char c;
        Trie[] ch = new Trie[27];
        int maxw = 0;

        public Trie(char c) {
            this.c = c;
        }

        void insert(String s, int i, int w) {
            int n = s.length();
            maxw = Math.max(maxw, w);
            if (i == n) {
                return;
            }
            char c = s.charAt(i);
            int cind = cind(c);
            Trie next = ch[cind];
            if (next == null) {
                next = ch[cind] = new Trie(c);
            }
            next.insert(s, i + 1, w);
        }

        int find(String s, int i) {
            int n = s.length();
            if (i == n) {
                return maxw;
            }

            char c = s.charAt(i);
            int cind = cind(c);
            if (ch[cind] == null) {
                return -1;
            }
            return ch[cind].find(s, i + 1);
        }

        int cind(char c) {
            return c == '#' ? 26 : c - 'a';
        }
    }

    Trie root = new Trie('-');

    public WordFilter(String[] words) {
        for (int i = 0; i < words.length; i++) {
            String w = words[i] + "#" + words[i];
            int n = w.length();
            for (int j = 0; j <= n; j++) {
                // till n to allow empty suffix
                // we can start with j elegantly, no need to substring
                root.insert(w, j, i);
            }
        }
    }

    public int f(String prefix, String suffix) {
        String tofind = suffix + "#" + prefix;
        return root.find(tofind, 0);
    }
}

class PrefixAndSuffixBruteForce {
    // same idea, but use hashmap...
    HashMap<String, Integer> map = new HashMap<>();

    public PrefixAndSuffixBruteForce(String[] words) {
        for (int w = 0; w < words.length; w++) {
            for (int i = 0; i <= 10 && i <= words[w].length(); i++) {
                for (int j = 0; j <= 10 && j <= words[w].length(); j++) {
                    map.put(words[w].substring(0, i) + "#" + words[w].substring(words[w].length() - j), w);
                }
            }
        }
    }

    public int f(String prefix, String suffix) {
        return (map.containsKey(prefix + "#" + suffix)) ? map.get(prefix + "#" + suffix) : -1;
    }
}
