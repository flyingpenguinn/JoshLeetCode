public class ImplementTrie {
}

class Trie {

    /**
     * Initialize your data structure here.
     */
    Trie[] ch = new Trie[26];
    char c = '-';
    boolean isw;

    public Trie() {
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        Trie cur = this;// root
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int ci = c - 'a';
            if (cur.ch[ci] == null) {
                cur.ch[ci] = new Trie();
            }
            cur.ch[ci].c = c;
            cur = cur.ch[ci];
        }
        cur.isw = true;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        Trie fd = find(word);
        return fd == null ? false : fd.isw;
    }

    Trie find(String word) {
        Trie cur = this;// root
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int ci = c - 'a';
            if (cur.ch[ci] == null) {
                return null;
            }
            cur = cur.ch[ci];
        }
        return cur;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        Trie fd = find(prefix);
        return fd != null;
    }
}