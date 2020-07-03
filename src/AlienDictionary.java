import java.util.*;

/*
LC#269
There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

Example 1:

Input:
[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]

Output: "wertf"
Example 2:

Input:
[
  "z",
  "x"
]

Output: "zx"
Example 3:

Input:
[
  "z",
  "x",
  "z"
]

Output: ""

Explanation: The order is invalid, so return "".
Note:

You may assume all letters are in lowercase.
You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.
 */
public class AlienDictionary {
    // trap1: what are the chars in the dict? need to traverse. not all 26 chars are in the dict
    // trap2: abc, ab will yield an invalid seq
    public String alienOrder(String[] words) {
        // all lower case
        // if multiple, return anyone
        // if has circle, return ""
        // check null error out if needed
        Map<Integer, Set<Integer>> g = buildGraph(words);
        if (g == null) {
            return "";
        }
        int[] st = new int[26];
        StringBuilder sb = new StringBuilder();
        for (int k : g.keySet()) {
            if (st[k] == 0) {
                boolean valid = dfs(k, g, sb, st);
                if (!valid) {
                    return "";
                }
            }
        }
        return sb.reverse().toString();
    }

    private Map<Integer, Set<Integer>> buildGraph(String[] words) {
        // make sure we capture all words
        Map<Integer, Set<Integer>> g = new HashMap<>();
        for (String w : words) {
            for (int j = 0; j < w.length(); j++) {
                g.put((w.charAt(j) - 'a'), new HashSet<>());
            }
        }
        for (int i = 0; i + 1 < words.length; i++) {
            String w1 = words[i];
            String w2 = words[i + 1];
            int j = 0;
            int k = 0;
            while (j < w1.length() && k < w2.length()) {
                if (w1.charAt(j) != w2.charAt(k)) {
                    int ind1 = w1.charAt(j) - 'a';
                    int ind2 = w2.charAt(k) - 'a';
                    g.get(ind1).add(ind2);
                    break;
                } else {
                    j++;
                    k++;
                }
            }
            // abcd vs abc
            if (j < w1.length() && k == w2.length()) {
                return null;
            }
        }
        return g;
    }


    private boolean dfs(int i, Map<Integer, Set<Integer>> g, StringBuilder res, int[] st) {
        st[i] = 1;
        Set<Integer> nexts = g.get(i);
        for (int next : nexts) {
            if (st[next] == 1) {
                return false;
            } else if (st[next] == 0) {
                boolean valid = dfs(next, g, res, st);
                if (!valid) {
                    return false;
                }
            }
        }
        res.append((char) ('a' + i));
        st[i] = 2;
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new AlienDictionary().alienOrder(new String[]{"abc", "ab"}));
    }
}
