import java.util.*;

/*
LC#212
Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.



Example:

Input:
board = [
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
words = ["oath","pea","eat","rain"]

Output: ["eat","oath"]


Note:

All inputs are consist of lowercase letters a-z.
The values of words are distinct.
 */
public class WordSearchII {
    // use trie to build the dictionary then dfs every i,j on the board to see if they can form any word....
    // backtrack with a trie, On^2*L^4 note we go L on 4 directions,n=edge len
    class Solution {
        private class Trie{
            private boolean isWord = false;
            private Trie[] ch = new Trie[26];
        }

        public List<String> findWords(char[][] a, String[] words) {
            Trie root = new Trie();
            for(String w: words){
                insert(root, w);
            }
            Set<String> r = new HashSet<>();
            for(int i=0; i<a.length;i++){
                for(int j = 0; j<a[0].length;j++){
                    Trie first = root.ch[a[i][j]-'a'];
                    if(first != null){
                        dfs(a, i, j, ""+a[i][j] ,first, r);
                    }
                }
            }
            return new ArrayList<>(r);
        }

        private void insert(Trie root, String w){
            Trie p= root;
            for(int i=0; i<w.length(); i++){
                char c = w.charAt(i);
                int cind = c-'a';
                Trie next = p.ch[cind];
                if(next == null){
                    next = p.ch[cind] = new Trie();
                }
                p = next;
            }
            p.isWord = true;
        }

        private int[][] dirs= {{-1,0}, {1,0}, {0,-1}, {0,1}};

        private void dfs(char[][] a, int i, int j, String cur, Trie node, Set<String> r){
            // a[i][j] == node == cur
            if(node.isWord){
                r.add(cur);
            }
            char orig = a[i][j];
            a[i][j] = '-';
            for(int[] d: dirs){
                int ni = i+d[0];
                int nj = j+d[1];
                if(ni>=0 && ni<a.length && nj>=0 && nj<a[0].length && a[ni][nj] != '-'){
                    char nc = a[ni][nj];
                    int ncind = nc-'a';
                    Trie nnext = node.ch[ncind];
                    if( nnext != null){
                        dfs(a, ni, nj, cur+a[ni][nj], nnext, r);
                    }
                }
            }
            a[i][j] = orig;
        }
    }
}
