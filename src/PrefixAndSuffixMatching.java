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
    public static void main(String[] args) {
        String[] ws = {"abc", "ab"};
        WordFilter wf = new WordFilter(ws);
        wf.f("ab", "");
    }
}

class WordFilter {
    //search suffix+"#"+prefix. construct suffixes + "#"+whold word. O(n*l^2)
    // better than enumerating both prefix and suffix and do a O(n*l^3) search.
    class Tn {
        int mw = -1;
        Tn[] ch = new Tn[27];

        void insert(String w, int i, int ww) {
            mw = Math.max(mw, ww); // must be here. it won't cause trouble when it's abc vs ab: if ab is suffix we would find ab# nicely. if ab is prefix we should find abc instead
            if (i == w.length()) {
                return;
            }
            int cind = tocode(w.charAt(i));
            if (ch[cind] == null) {
                ch[cind] = new Tn();
            }
            ch[cind].insert(w, i + 1, ww);
        }

        int search(String w, int i) {
            if (i == w.length()) {
                return mw;
            }
            int cind = tocode(w.charAt(i));
            if (ch[cind] == null) {
                return -1;
            }
            return ch[cind].search(w, i + 1);
        }
    }

    int tocode(char c) {
        return c == '#' ? 26 : c - 'a';
    }

    private Tn root = new Tn();

    public WordFilter(String[] ws) {
        // abc=> []#cba  a#cba ab#cba abc#cba
        for (int k = 0; k < ws.length; k++) {
            String w = ws[k];
            int wn = w.length();
            for (int i = 0; i <= wn; i++) {
                String cp = w.substring(0, i);
                for (int j = 0; j <= wn; j++) {
                    // abc => c#abc, bc#abc, abc#abc
                    String csuf = w.substring(j, wn);
                    String cur = cp + "#" + csuf;
                    root.insert(cur, 0, k);
                }
            }
        }
    }

    public int f(String pre, String suf) {
        return root.search(pre + "#" + suf, 0);
    }
}

class PrefixAndSuffixBruteForce {
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
