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
    public String alienOrder(String[] ws) {
        Map<Character, Set<Character>> m = new HashMap<>();
        for (int i = 0; i < ws.length; i++) {
            for (int j = 0; j < ws[i].length(); j++) {
                m.put(ws[i].charAt(j), new HashSet<>());
            }
        }
        for (int i = 1; i < ws.length; i++) {
            if (!link(ws[i - 1], ws[i], m)) {
                return "";
            }
        }
        int[] status = new int[26];
        StringBuilder sb = new StringBuilder();
        for (char k : m.keySet()) {
            if (status[k - 'a'] == 0) {
                boolean valid = dfs(k, m, status, sb);
                if (!valid) {
                    return "";
                }
            }
        }
        return sb.reverse().toString();

    }

    private boolean dfs(char k, Map<Character, Set<Character>> m, int[] st, StringBuilder sb) {
        st[k - 'a'] = 1;
        for (char next : m.getOrDefault(k, new HashSet<>())) {
            if (st[next - 'a'] == 0) {
                if (!dfs(next, m, st, sb)) {
                    return false;
                }
            } else if (st[next - 'a'] == 1) {
                return false;
            }
        }
        st[k - 'a'] = 2;
        sb.append(k);
        return true;
    }


    private boolean link(String s, String t, Map<Character, Set<Character>> m) {
        int i = 0;
        for (; i < s.length() && i < t.length(); i++) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            if (sc != tc) {
                m.get(sc).add(tc);
                break;
            }
        }
        if (i == t.length() && i != s.length()) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new AlienDictionary().alienOrder(new String[]{"abc", "ab"}));
    }
}
