import java.util.*;

/*
LC#1032
Implement the StreamChecker class as follows:

StreamChecker(words): Constructor, init the data structure with the given words.
query(letter): returns true if and only if for some k >= 1, the last k characters queried (in order from oldest to newest, including this letter just queried) spell one of the words in the given list.


Example:

StreamChecker streamChecker = new StreamChecker(["cd","f","kl"]); // init the dictionary.
streamChecker.query('a');          // return false
streamChecker.query('b');          // return false
streamChecker.query('c');          // return false
streamChecker.query('d');          // return true, because 'cd' is in the wordlist
streamChecker.query('e');          // return false
streamChecker.query('f');          // return true, because 'f' is in the wordlist
streamChecker.query('g');          // return false
streamChecker.query('h');          // return false
streamChecker.query('i');          // return false
streamChecker.query('j');          // return false
streamChecker.query('k');          // return false
streamChecker.query('l');          // return true, because 'kl' is in the wordlist


Note:

1 <= words.length <= 2000
1 <= words[i].length <= 2000
Words will only consist of lowercase English letters.
Queries will only consist of lowercase English letters.
The number of queries is at most 40000.
 */
public class StreamCheckerRunner {
    // a trie to check the words backward...
    class StreamChecker {
        class Trie {
            char c;
            boolean isword;
            Trie[] ch = new Trie[26];

            public Trie(char c) {
                this.c = c;
            }

            void add(String s, int i) {
                if (i == s.length()) {
                    this.isword = true;
                    return;
                }
                char c = s.charAt(i);
                int cind = c - 'a';
                Trie next = ch[cind];
                if (next == null) {
                    next = ch[cind] = new Trie(c);
                }
                next.add(s, i + 1);
            }

        }

        Trie root = new Trie('-');

        List<Character> list = new ArrayList<>();

        public StreamChecker(String[] w) {
            for (String wi : w) {
                String rev = new StringBuilder(wi).reverse().toString();
                root.add(rev, 0);
            }
        }

        public boolean query(char letter) {
            list.add(letter);
            Trie p = root;
            for (int i = list.size() - 1; i >= 0; i--) {
                char c = list.get(i);
                int cind = c - 'a';
                if (p.ch[cind] == null) {
                    return false;
                }
                if (p.ch[cind].isword) {
                    return true;
                }
                p = p.ch[cind];
            }
            return false;
        }
    }
}