import base.ArrayUtils;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
LC#839
Two strings X and Y are similar if we can swap two letters (in different positions) of X, so that it equals Y. Also two strings X and Y are similar if they are equal.

For example, "tars" and "rats" are similar (swapping at positions 0 and 2), and "rats" and "arts" are similar, but "star" is not similar to "tars", "rats", or "arts".

Together, these form two connected groups by similarity: {"tars", "rats", "arts"} and {"star"}.  Notice that "tars" and "arts" are in the same group even though they are not similar.  Formally, each group is such that a word is in the group if and only if it is similar to at least one other word in the group.

We are given a list A of strings.  Every string in A is an anagram of every other string in A.  How many groups are there?



Example 1:

Input: A = ["tars","rats","arts","star"]
Output: 2


Constraints:

1 <= A.length <= 2000
1 <= A[i].length <= 1000
A.length * A[i].length <= 20000
All words in A consist of lowercase letters only.
All words in A have the same length and are anagrams of each other.
The judging time limit has been increased for this question.
 */
public class SimilarStringGroups {
    // first create edges between directly similar strings
    // then dfs to get the connected components
    // similar to account merge. note we just need direct edges dont need to worry about transitive relationship: dfs will settle it
    public int numSimilarGroups(String[] a) {
        int n = a.length;
        Map<String, Set<String>> g = new HashMap<>();
        for (int i = 0; i < n; i++) {
            g.putIfAbsent(a[i], new HashSet<>());
            for (int j = i + 1; j < n; j++) {
                if (sameGroup(a[i], a[j])) {
                    g.computeIfAbsent(a[i], k -> new HashSet<>()).add(a[j]);
                    g.computeIfAbsent(a[j], k -> new HashSet<>()).add(a[i]);
                }
            }
        }

        Set<String> seen = new HashSet<>();
        int comp = 0;
        for (String k : g.keySet()) {
            if (!seen.contains(k)) {
                dfs(k, g, seen);
                comp++;
            }
        }
        return comp;
    }

    boolean sameGroup(String s, String t) {
        int diffs = 0;
        // slen == tlen
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != t.charAt(i)) {
                diffs++;
            }
            if (diffs > 2) {
                return false;
            }
        }
        return diffs == 2 || diffs == 0;
    }

    void dfs(String k, Map<String, Set<String>> g, Set<String> seen) {
        seen.add(k);
        for (String next : g.get(k)) {
            if (!seen.contains(next)) {
                dfs(next, g, seen);
            }
        }
    }


    public static void main(String[] args) throws IOException {
        String file = "E:\\dev\\project\\JoshLeet\\tests\\SimilarStringGroups.txt";
        BufferedReader reader = new BufferedReader(new FileReader(new File(file)));
        String s = reader.readLine();
        String[] strs = s.split(",");
        long start = System.currentTimeMillis();
        System.out.println(new SimilarStringGroups().numSimilarGroups(strs));
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}

class SimilarStringGroupsUnionFind {
    // if same group, union the groups
    public int numSimilarGroups(String[] ss) {
        // must all be anagrams. array non null
        int[] pa = new int[ss.length];
        for (int i = 0; i < ss.length; i++) {
            pa[i] = i;
        }
        int groups = ss.length;
        for (int i = 0; i < ss.length; i++) {
            for (int j = i + 1; j < ss.length; j++) {
                if (sameGroup(ss[i], ss[j])) {
                    if (union(pa, i, j)) {
                        groups--;
                    }
                }
            }
        }
        return groups;
    }

    private boolean union(int[] pa, int i, int j) {
        int ri = find(pa, i);
        int rj = find(pa, j);
        if (ri != rj) {
            pa[ri] = rj;
            return true;
        } else {
            return false;
        }
    }

    private int find(int[] pa, int cur) {
        int pcur = pa[cur];
        if (pcur == cur) {
            return cur;
        } else {
            int rcur = find(pa, pcur);
            pa[cur] = rcur;
            return rcur;
        }
    }

    private boolean sameGroup(String s, String t) {
        // anagrams, same length
        int diff = 0;
        for (int i = 0; i < s.length(); i++) {
            char cs = s.charAt(i);
            char ct = t.charAt(i);
            if (cs != ct) {
                diff++;
            }
            if (diff > 2) {
                break;
            }
        }
        return diff == 2 || diff == 0; // equal also counted as similar
    }
}
