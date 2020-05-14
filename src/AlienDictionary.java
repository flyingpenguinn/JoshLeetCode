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
    Set<Integer>[] g = new HashSet[26];
    int[] st = new int[26];
    List<Integer> r = new ArrayList<>();
    boolean cyc = false;

    public String alienOrder(String[] ws) {
        Arrays.fill(st, -1);
        int wn = ws.length;
        for (int i = 0; i < wn; i++) {
            int win = ws[i].length();
            for (int j = 0; j < win; j++) {
                char wij = ws[i].charAt(j);
                st[wij - 'a'] = 0;
                if (g[wij - 'a'] == null) {
                    g[wij - 'a'] = new HashSet<>();
                }
            }
            for (int j = i + 1; j < wn; j++) {
                int s = 0;
                int t = 0;
                int wjn = ws[j].length();
                while (s < win && t < wjn && ws[i].charAt(s) == ws[j].charAt(t)) {
                    s++;
                    t++;
                }
                if (s < win && t < wjn) {
                    char wis = ws[i].charAt(s);
                    char wjt = ws[j].charAt(t);
                    g[wis - 'a'].add(wjt - 'a');
                }
            }
        }
        for (int i = 0; i < 26; i++) {
            if (st[i] == 0) {
                dfs(i);
            }
        }
        if (cyc) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = r.size() - 1; i >= 0; i--) {
            sb.append((char) ('a' + r.get(i)));
        }
        return sb.toString();
    }

    void dfs(int i) {
        if (cyc) {
            return;
        }
        st[i] = 1;
        for (int j : g[i]) {
            if (st[j] == 1) {
                cyc = true;
                return;
            } else if (st[j] == 0) {
                dfs(j);
            }
        }

        st[i] = 2;
        r.add(i);
    }
}
