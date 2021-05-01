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
    class WordFilter {

        private class Trie{
            private char val;
            private int index = -1;
            private Trie[] ch = new Trie[27];
            public Trie(char val){
                this.val = val;
            }
        }

        private Trie root = new Trie('*');

        private void insert(String word, int index){
            Trie p = root;
            for(int i=0; i<word.length(); i++){
                char c = word.charAt(i);
                int cind = c-'a';
                Trie next = p.ch[cind];
                if(next==null){
                    next = p.ch[cind] = new Trie(c);
                }
                next.index = index;
                p = next;
            }
        }

        public WordFilter(String[] words) {
            for(int i = 0; i<words.length; i++){
                for(int j=words[i].length()-1; j>=0; j--){
                    String toinsert = words[i].substring(j)+"{"+words[i];
                    insert(toinsert, i);
                }
            }
        }

        private int find(String word){
            Trie p = root;
            for(int i=0; i<word.length(); i++){
                int cind = word.charAt(i)-'a';
                if(p.ch[cind] == null){
                    return -1;
                }
                p = p.ch[cind];
            }
            return p.index;
        }

        public int f(String prefix, String suffix) {
            int found = find(suffix+"{"+prefix);
            return found;
        }
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
