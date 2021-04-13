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
    private class Trie {
        private char val;
        private Trie[] ch = new Trie[26];
        private String fullword = null;

        public Trie(char val) {
            this.val = val;
        }
    }

    private Trie root = new Trie('*');

    private void insert(String s) {
        Trie p = root;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int cind = c - 'a';
            if (p.ch[cind] == null) {
                p.ch[cind] = new Trie(c);
            }
            p = p.ch[cind];
        }
        p.fullword = s;
    }

    private List<String> res = new ArrayList<>();
    private int maxlen = 0;

    public List<String> findWords(char[][] a, String[] words) {
        if (a == null || a.length == 0 || a[0].length == 0 || words.length == 0) {
            return res;
        }
        for (String w : words) {
            insert(w);
            maxlen = Math.max(maxlen, w.length());
        }
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                dfs(a, i, j, root, 0, maxlen);
            }
        }
        return res;
    }

    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    // check ij with p.ch. if p.ch is a word, then ij ends the word add to the list
    private void dfs(char[][] a, int i, int j, Trie p, int l, int maxlen) {
        if (l > maxlen) {
            return;
        }
        int cind = a[i][j] - 'a';
        if (p.ch[cind] == null) {
            return;
        }
        if (p.ch[cind].fullword != null) {
            res.add(p.ch[cind].fullword);
            p.ch[cind].fullword = null;
            // only put each full words once
        }
        char old = a[i][j];
        a[i][j] = '*';
        for (int[] d : dirs) {
            int ni = i + d[0];
            int nj = j + d[1];
            if (ni >= 0 && ni < a.length && nj >= 0 && nj < a[0].length && a[ni][nj] != '*') {
                dfs(a, ni, nj, p.ch[cind], l + 1, maxlen);
            }
        }
        a[i][j] = old;
    }
}
