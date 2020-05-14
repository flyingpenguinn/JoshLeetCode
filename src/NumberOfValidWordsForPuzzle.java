import java.util.*;

/*
LC#1178
With respect to a given puzzle string, a word is valid if both the following conditions are satisfied:
word contains the first letter of puzzle.
For each letter in word, that letter is in puzzle.
For example, if the puzzle is "abcdefg", then valid words are "faced", "cabbage", and "baggage"; while invalid words are "beefed" (doesn't include "a") and "based" (includes "s" which isn't in the puzzle).
Return an array answer, where answer[i] is the number of words in the given word list words that are valid with respect to the puzzle puzzles[i].


Example :

Input:
words = ["aaaa","asas","able","ability","actt","actor","access"],
puzzles = ["aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"]
Output: [1,1,3,2,4,0]
Explanation:
1 valid word for "aboveyz" : "aaaa"
1 valid word for "abrodyz" : "aaaa"
3 valid words for "abslute" : "aaaa", "asas", "able"
2 valid words for "absoryz" : "aaaa", "asas"
4 valid words for "actresz" : "aaaa", "asas", "actt", "access"
There're no valid words for "gaswxyz" cause none of the words in the list contains letter 'g'.


Constraints:

1 <= words.length <= 10^5
4 <= words[i].length <= 50
1 <= puzzles.length <= 10^4
puzzles[i].length == 7
words[i][j], puzzles[i][j] are English lowercase letters.
Each puzzles[i] doesn't contain repeated characters.
 */
public class NumberOfValidWordsForPuzzle {
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        Map<String, Integer> wordmap = new HashMap<>();
        for (String w : words) {
            String sigw = tosig(w, false);
            wordmap.put(sigw, wordmap.getOrDefault(sigw, 0) + 1);
        }
        List<Integer> r = new ArrayList<>();
        for (String p : puzzles) {
            String sigp = tosig(p, true);
            int cur = genall(sigp, p.charAt(0), wordmap);
            r.add(cur);
        }
        return r;
    }

    private String tosig(String w, boolean excludefirst) {
        int[] wca = new int[26];
        for (int i = 0; i < w.length(); i++) {
            if (excludefirst && w.charAt(i) == w.charAt(0)) {
                continue;
            }
            wca[w.charAt(i) - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (wca[i] > 0) {
                sb.append((char) (i + 'a'));
            }
        }
        return sb.toString();
    }

    private int genall(String p, char must, Map<String, Integer> wordmap) {
        int n = p.length();
        int r = 0;
        for (int i = 0; i < (1 << n); i++) {
            String cur = tostr(i, p, must);
            //    System.out.println(cur);
            if (wordmap.containsKey(cur)) {
                r += wordmap.get(cur);
            }
        }
        return r;
    }

    private String tostr(int status, String p, char must) {
        int ts = status;
        int n = 0;
        while (ts > 0) {
            ts &= (ts - 1);
            n++;
        }
        char[] r = new char[n + 1];
        r[n] = must;
        int rp = n - 1;
        int i = p.length() - 1;
        while (status > 0) {
            if ((status & 1) == 1) {
                r[rp--] = p.charAt(i);
            }
            status >>= 1;
            i--;
        }
        Arrays.sort(r);
        return new String(r);
    }

    public static void main(String[] args) {
        String[] words = {"apple", "pleas", "please"};
        String[] puzzles = {"aelwxyz", "aelpxyz", "aelpsxy", "saelpxy", "xaelpsy"};
        System.out.println(new NumberOfValidWordsForPuzzle().findNumOfValidWords(words, puzzles));
    }
}
