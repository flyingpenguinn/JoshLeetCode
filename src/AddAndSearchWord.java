
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
    private class Trie{
        char c;
        Trie[] ch = new Trie[26];
        boolean isWord = false;

        public Trie(char c){
            this.c = c;
        }
    }

    private Trie root = new Trie('-');

    /** Initialize your data structure here. */
    public WordDictionary() {

    }

    /** Adds a word into the data structure. */
    public void addWord(String w) {
        // check null, error out if so
        Trie p = root;
        for(int i=0; i<w.length(); i++){
            char c = w.charAt(i);
            int cind = c-'a';
            Trie next = p.ch[cind];
            if(next == null){
                p.ch[cind] = next = new Trie(c);
            }
            p = next;
        }
        p.isWord = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String w) {
        // check null, error out if so
        return dfs(w, 0, root);
    }

    private boolean dfs(String w, int i, Trie node){
        if(i==w.length()){
            return node.isWord;
        }
        char c = w.charAt(i);
        if(c=='.'){
            for(Trie next: node.ch){
                if(next != null && dfs(w, i+1, next)){
                    return true;
                }
            }
            return false;
        }
        int cind = c-'a';
        Trie next = node.ch[cind];
        if(next==null){
            return false;
        }
        return dfs(w, i+1, next);
    }
}