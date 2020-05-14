
/*
LC#211
Design a data structure that supports the following two operations:

void addWord(word)
bool search(word)
search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

Example:

addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true
Note:
You may assume that all words are consist of lowercase letters a-z.
 */
public class AddAndSearchWord {
}

class WordDictionary {
    // Trie and dfs on search of wildcard
    class Tn {
        char c;
        Tn[] ch = new Tn[26];
        boolean isw;

        Tn(char c) {
            this.c = c;
        }
    }

    /**
     * Initialize your data structure here.
     */
    public WordDictionary() {

    }

    Tn root = new Tn('-');

    /**
     * Adds a word into the data structure.
     */
    public void addWord(String w) {
        Tn cur = root;
        // cur match till i-1
        for (int i = 0; i < w.length(); i++) {
            char c = w.charAt(i);
            int cind = c - 'a';
            if (cur.ch[cind] == null) {
                cur.ch[cind] = new Tn(c);
            }
            cur = cur.ch[cind];
        }
        cur.isw = true;
    }

    /**
     * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
     */
    public boolean search(String word) {
        return dfs(word, root, 0);
    }

    boolean dfs(String w, Tn cur, int i) {
        if (i == w.length()) {
            return cur.isw;
        }
        char c = w.charAt(i);
        if (c == '.') {
            for (int j = 0; j < 26; j++) {
                if (cur.ch[j] != null) {
                    if (dfs(w, cur.ch[j], i + 1)) {
                        return true;
                    }
                }
            }
        } else {
            int cind = c - 'a';
            if (cur.ch[cind] != null) {
                return dfs(w, cur.ch[cind], i + 1);
            }
        }
        return false;
    }
}
