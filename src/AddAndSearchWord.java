
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

    class Trie {
        char c;
        Trie[] ch = new Trie[26];
        boolean isword = false;

        public Trie(char c) {
            this.c = c;
        }

        void insert(String s, int i) {
            if (i == s.length()) {
                isword = true;
                return;
            }
            char c = s.charAt(i);
            int cind = c - 'a';
            Trie next = ch[cind];
            if (next == null) {
                next = ch[cind] = new Trie(c);
            }
            next.insert(s, i + 1);
        }

        boolean search(String s, int i) {
            if (i == s.length()) {
                return isword;
            }
            char c = s.charAt(i);
            int cind = c - 'a';
            if (c != '.') {
                Trie next = ch[cind];
                return next != null && next.search(s, i + 1);
            } else {
                for (Trie next : ch) {
                    if (next != null && next.search(s, i + 1)) {
                        return true;
                    }
                }
                return false;
            }
        }
    }

    Trie root = new Trie('-');

    /**
     * Initialize your data structure here.
     */
    public WordDictionary() {

    }

    /**
     * Adds a word into the data structure.
     */
    public void addWord(String word) {
        root.insert(word, 0);
    }

    /**
     * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
     */
    public boolean search(String word) {
        return root.search(word, 0);
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */