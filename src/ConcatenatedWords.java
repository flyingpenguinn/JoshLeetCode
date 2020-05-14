import java.io.*;
import java.util.*;

/*
LC#472
Given a list of words (without duplicates), please write a program that returns all concatenated words in the given list of words.
A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.

Example:
Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]

Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]

Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
 "dogcatsdog" can be concatenated by "dog", "cats" and "dog";
"ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
Note:
The number of elements of the given array will not exceed 10,000
The length sum of elements in the given array will not exceed 600,000.
All the input string will only include lower case letters.
The returned elements order does not matter.
 */
public class ConcatenatedWords {
    // actually worst case O(n*len^3) due to the .contains(String) operation
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Arrays.sort(words, (x, y) -> Integer.compare(x.length(), y.length()));
        Set<String> set = new HashSet<>();
        List<String> r = new ArrayList<>();
        for (String s : words) {
            int[] dp = new int[s.length()];
            if (wordbreak(s, 0, set, dp) && !s.isEmpty()) {
                r.add(s);
            }
            set.add(s);
        }
        return r;
    }

    private boolean wordbreak(String s, int i, Set<String> set, int[] dp) {
        if (i == s.length()) {
            return true;
        }
        if (dp[i] != 0) {
            return dp[i] == 1;
        }
        StringBuilder sb = new StringBuilder();
        for (int j = i; j < s.length(); j++) {
            sb.append(s.charAt(j));
            if (set.contains(sb.toString())) {
                if (wordbreak(s, j + 1, set, dp)) {
                    dp[i] = 1;
                    return true;
                }
            }
        }
        dp[i] = 2;
        return false;
    }

    public static void main(String[] args) throws IOException {
        String file = "E:\\dev\\project\\JoshLeet\\tests\\ConcatenatedWords";
        BufferedReader reader = new BufferedReader(new FileReader(new File(file)));
        String s = reader.readLine();
        System.out.println(new ConcatenateWordsTrie().findAllConcatenatedWordsInADict(s.split(",")));
    }
}

class ConcatenateWordsTrie {
    // might be slightly better than direct dp because in dp set.contains is O(len). so this is O(n*len^2)
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Arrays.sort(words, (a, b) -> Integer.compare(a.length(), b.length()));
        List<String> rt = check(words);
        return rt;
    }


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

    public List<String> check(String[] words) {
        List<String> r = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            if (words[i].length() == 0) {
                continue;
            }
            dp = new int[words[i].length()];
            Arrays.fill(dp, -1);
            int parts = doparts(i, 0, words);
            if (parts >= 2) {
                r.add(words[i]);
            }
            tn.insert(words[i], 0);
        }

        return r;
    }


    int[] dp = null;

    private int doparts(int index, int i, String[] words) {
        String w = words[index];
        if (i == w.length()) {
            return 0;
        }
        if (dp[i] >= 0) {
            return dp[i];
        }
        TrieNode p = tn;
        for (int j = i; j < w.length(); j++) {
            char wc = w.charAt(j);
            if (p.hasChar(wc)) {
                p = p.getChar(wc);
                if (p.isWord) {
                    int lt = doparts(index, j + 1, words);
                    if (lt != -1) {
                        dp[i] = Math.max(dp[i], 1 + lt);
                        if (dp[i] >= 2) {
                            break;
                        }
                    }
                }
            } else {
                break;
            }
        }
        return dp[i];
    }
}