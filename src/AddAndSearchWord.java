
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

    // assuming only lower cases in add, and only lower +. in search
    /** Initialize your data structure here. */
    private class Trie{
        private char c;
        private boolean isWord = false;
        private Trie[] children = new Trie[26];
        public Trie(char c){
            this.c = c;
        }
    }

    public WordDictionary() {

    }

    private Trie root = new Trie('-');

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        Trie cur = root;
        for(int i=0; i<word.length(); i++){
            char c = word.charAt(i);
            int cind = toCode(c);
            if(cur.children[cind] == null){
                cur.children[cind] = new Trie(c);
            }
            cur = cur.children[cind];
        }
        cur.isWord = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return dfs(word, 0, root);
    }

    // whether word from i.. till end is contained in this trie node
    private boolean dfs(String word, int i, Trie node){
        if(i==word.length()){
            return node.isWord;
        }
        char c = word.charAt(i);
        if(c=='.'){
            for(Trie next: node.children){
                if(next == null){
                    continue;
                }
                boolean found = dfs(word, i+1, next);
                if(found){
                    return true;
                }
            }
            return false;
        }else{
            Trie next = node.children[toCode(word.charAt(i))];
            if(next == null){
                return false;
            }
            return dfs(word, i+1, next);
        }
    }

    private int toCode(char c){
        return c-'a';
    }
}
