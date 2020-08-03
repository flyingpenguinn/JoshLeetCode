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
    // ac in one shot in apple interview!
    // trap1: what are the chars in the dict? need to traverse. not all 26 chars are in the dict
    // trap2: abc, ab will yield an invalid seq
    public String alienOrder(String[] words) {
        // check null...do what we need

        Map<Character, Set<Character>> g = new HashMap<>();
        for (String w : words) {
            for (int i = 0; i < w.length(); i++) {
                char c = w.charAt(i);
                g.putIfAbsent(c, new HashSet<>());
            }
        }
        for (int i = 0; i + 1 < words.length; i++) {
            boolean valid = build(words[i], words[i + 1], g);
            if (!valid) {
                return "";
            }
        }
        Map<Character, Integer> status = new HashMap<>();
        // status nothing/0: not visited
        // status == 1 means expanding, loop if meet again
        // status==2 finished before, should walk away
        StringBuilder sb = new StringBuilder();
        for (char k : g.keySet()) {
            if (!status.containsKey(k)) {
                boolean valid = dfs(g, k, status, sb);
                if (!valid) {
                    return "";
                }
            }
        }
        //   a--> e
        //   | ----c
        return sb.reverse().toString();
    }

    private boolean dfs(Map<Character, Set<Character>> g, char key, Map<Character, Integer> status, StringBuilder sb) {
        status.put(key, 1); // expanding
        Set<Character> neg = g.getOrDefault(key, new HashSet<>());
        for (char next : neg) {
            if (status.getOrDefault(next, 0) == 1) {
                // found a circle
                return false;
            } else if (!status.containsKey(next)) {
                boolean valid = dfs(g, next, status, sb);
                if (!valid) {
                    return false;
                }
            }
        }
        sb.append(key);
        status.put(key, 2); // we are done
        return true;
    }

    // w1 before w2
    private boolean build(String w1, String w2, Map<Character, Set<Character>> g) {
        int i = 0;
        int j = 0;
        while (i < w1.length() && j < w2.length()) {
            // everything before i and j are the same
            char c1 = w1.charAt(i);
            char c2 = w2.charAt(j);
            if (c1 != c2) {
                g.get(c1).add(c2);
                // ae vs ac, e-->c
                // aez vs acd
                break;
            } else {
                i++;
                j++;
            }
        }
        if (i < w1.length() && j == w2.length()) {
            // abcd vs abc
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new AlienDictionary().alienOrder(new String[]{"abc", "ab"}));
    }
}
