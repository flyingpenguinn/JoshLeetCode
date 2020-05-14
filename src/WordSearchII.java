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
    Set<String> r = new HashSet<>();
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    class Tn {
        char c;
        boolean iw;
        Tn[] ch = new Tn[26];

        Tn(char c) {
            this.c = c;
        }

        void insert(String w, int i) {
            if (i == w.length()) {
                iw = true;
                return;
            }
            char wc = w.charAt(i);
            if (ch[wc - 'a'] == null) {
                ch[wc - 'a'] = new Tn(wc);
            }
            ch[wc - 'a'].insert(w, i + 1);
        }

        // up to this node it matches w
        void find(char[][] b, int i, int j, String w) {

            char c = b[i][j];


            if (ch[c - 'a'] == null) {
                return;
            }
            if (ch[c - 'a'].iw) {
                r.add(w + c);
            }
            b[i][j] = '#';
            for (int[] d : dirs) {
                int ni = i + d[0];
                int nj = j + d[1];
                if (ni >= 0 && ni < b.length && nj >= 0 && nj < b[0].length && b[ni][nj] != '#') {
                    ch[c - 'a'].find(b, ni, nj, w + c);
                }
            }
            b[i][j] = c;
        }
    }

    Tn trie = new Tn('-');

    public List<String> findWords(char[][] b, String[] words) {
        for (String w : words) {
            trie.insert(w, 0);
        }
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                trie.find(b, i, j, "");
            }

        }
        return new ArrayList<>(r);
    }
}
