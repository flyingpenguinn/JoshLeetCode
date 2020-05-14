import java.util.Arrays;
import java.util.HashSet;

public class LongestWordInDict {
    public String longestWord(String[] words) {
        Arrays.sort(words);
        HashSet<String> hs = new HashSet<>();
        hs.add("");
        String res = "";
        for (String w : words) {
            if (hs.contains(w.substring(0, w.length() - 1))) {
                if( (w.length()>res.length()) || (res.length()==w.length() && res.compareTo(w)>0)){
                    res = w;
                }
            }
            hs.add(w);
        }
        return res;
    }


}

// trie is good at prefix, not so much for subsequence like companion "delete char" question
class LongestWordTrie {
    class TrieNode {
        char val;
        boolean isWord;
        TrieNode[] ch = new TrieNode[26];

        public TrieNode(char val) {
            this.val = val;
        }

        void insert(String s, int i) {
            if (i == s.length()) {
                this.isWord = true;
                return;
            }
            char c = s.charAt(i);
            if (ch[c - 'a'] == null) {
                ch[c - 'a'] = new TrieNode(c);
            }
            ch[c - 'a'].insert(s, i + 1);
        }

        boolean hasChar(char c) {
            return ch[c - 'a'] != null;

        }

        TrieNode getChar(char c) {
            return ch[c - 'a'];
        }


    }

    TrieNode tn = new TrieNode('-');

    public String longestWord(String[] words) {
        Arrays.sort(words, (a, b) -> a.length() != b.length() ? Integer.compare(b.length(), a.length()) : a.compareTo(b));
        for (String w : words) {
            tn.insert(w, 0);
        }
        for (String w : words) {
            if (canForm(w, 0, tn)) {
                return w;
            }
        }
        return "";
    }

    private boolean canForm(String w, int i, TrieNode p) {
        if (i == w.length()) {
            return true;
        }
        char wc = w.charAt(i);
        if (p.hasChar(wc)) {
            TrieNode tn = p.getChar(wc);
            if (!tn.isWord) {
                return false;
            }
            return canForm(w, i + 1, tn);
        }
        return false;
    }
}