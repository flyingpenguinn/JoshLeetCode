

import base.ArrayUtils;
import base.TreeNode;
import base.Trees;

import static base.ArrayUtils.*;

import java.util.*;

/*
LC
Given an equation, represented by words on left side and the result on right side.

You need to check if the equation is solvable under the following rules:

Each character is decoded as one digit (0 - 9).
Every pair of different characters they must map to different digits.
Each words[i] and result are decoded as one number without leading zeros.
Sum of numbers on left side (words) will equal to the number on right side (result).
Return True if the equation is solvable otherwise return False.



Example 1:

Input: words = ["SEND","MORE"], result = "MONEY"
Output: true
Explanation: Map 'S'-> 9, 'E'->5, 'N'->6, 'D'->7, 'M'->1, 'O'->0, 'R'->8, 'Y'->'2'
Such that: "SEND" + "MORE" = "MONEY" ,  9567 + 1085 = 10652
Example 2:

Input: words = ["SIX","SEVEN","SEVEN"], result = "TWENTY"
Output: true
Explanation: Map 'S'-> 6, 'I'->5, 'X'->0, 'E'->8, 'V'->7, 'N'->2, 'T'->1, 'W'->'3', 'Y'->4
Such that: "SIX" + "SEVEN" + "SEVEN" = "TWENTY" ,  650 + 68782 + 68782 = 138214
Example 3:

Input: words = ["THIS","IS","TOO"], result = "FUNNY"
Output: true
Example 4:

Input: words = ["LEET","CODE"], result = "POINT"
Output: false


Constraints:

2 <= words.length <= 5
1 <= words[i].length, results.length <= 7
words[i], result contains only upper case English letters.
Number of different characters used on the expression is at most 10.
 */
public class VerbalArithmeticPuzzle {
    // it's all about estimating the complexity and having the dfs body small enough
    boolean found = false;

    public boolean isSolvable(String[] words, String result) {
        Set<Character> nonzeros = new HashSet<>();
        Set<Character> allchars = new HashSet<>();
        Map<Character, Integer> basemap = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            processWord(words[i], nonzeros, allchars, basemap, 1);
        }
        processWord(result, nonzeros, allchars, basemap, -1);
        boolean[] used = new boolean[10];
        boolean[] nonzeroarray = new boolean[26];
        for (char c : nonzeros) {
            nonzeroarray[c - 'A'] = true;
        }
        char[] allchar = new char[allchars.size()];
        int rp = 0;
        for (char c : allchars) {
            allchar[rp++] = c;
        }
        int[] basearray = new int[26];
        for (char c : basemap.keySet()) {
            basearray[c - 'A'] = basemap.get(c);
        }
        dfs(used, nonzeroarray, basearray, 0, allchar, 0);
        //    System.out.println(count + " size==" + allchars.size());
        return found;
    }

    int count = 0;

    // although in worst case it's p10 = 362xxxx, optimizing this matters because even a const level upgrade means a lot
    // note the beautiful way of handling bases!
    private void dfs(boolean[] used, boolean[] nonzeroarray, int[] basearray, int sum, char[] all, int i) {
        //    System.out.println(mapping);
        count++;
        if (found) {
            return;
        }
        if (i == all.length) {
            if (sum == 0) {
                found = true;
            }
            return;
        }
        int start = nonzeroarray[all[i] - 'A'] ? 1 : 0;
        for (int v = start; v <= 9; v++) {
            if (used[v]) {
                continue;
            }
            used[v] = true;
            int base = basearray[all[i] - 'A'];
            dfs(used, nonzeroarray, basearray, sum - v * base, all, i + 1);
            if (found) {
                return;
            }
            used[v] = false;
        }
    }

    private void processWord(String str, Set<Character> nonzeros, Set<Character> all, Map<Character, Integer> basemap, int mult) {
        int base = 1;
        for (int j = str.length() - 1; j >= 1; j--) {
            char ci = str.charAt(j);
            basemap.put(ci, basemap.getOrDefault(ci, 0) + mult * base);
            all.add(ci);
            base *= 10;
        }
        char c0 = str.charAt(0);
        basemap.put(c0, basemap.getOrDefault(c0, 0) + mult * base);
        nonzeros.add(c0);
        all.add(c0);
    }

    public static void main(String[] args) throws Exception {
        VerbalArithmeticPuzzle sol = new VerbalArithmeticPuzzle();
        String[] strs = {"SEND", "MORE"};
        System.out.println(sol.isSolvable(strs, "MONEY"));
    }
}


class VerbalArithmeticPuzzleMoreNaive {
    // even without pruning zero starts early and not using the base trick, we can still pass OJ as lon gas we are not looping on 26 chars. iterating
    // on all from i-0 to n is critical
    boolean found = false;

    public boolean isSolvable(String[] words, String result) {
        Set<Character> nonzeros = new HashSet<>();
        Set<Character> allchars = new HashSet<>();
        for (int i = 0; i < words.length; i++) {
            processWord(words[i], allchars);
        }
        processWord(result, allchars);
        boolean[] used = new boolean[10];
        boolean[] nonzeroarray = new boolean[26];
        for (char c : nonzeros) {
            nonzeroarray[c - 'A'] = true;
        }
        char[] allchar = new char[allchars.size()];
        int[] mapping = new int[26];
        int rp = 0;
        for (char c : allchars) {
            allchar[rp++] = c;
            mapping[c - 'A'] = -1;
        }
        count = new int[allchar.length + 1];
        dfs(used, mapping, allchar, 0, words, result);

        System.out.println(Arrays.toString(count) + " size==" + allchars.size());
        return found;
    }

    int[] count;

    // although in worst case it's p10 = 362xxxx, optimizing this matters because even a const level upgrade means a lot
    // note the beautiful way of handling bases!
    private void dfs(boolean[] used, int[] mapping, char[] all, int i, String[] words, String result) {
        //    System.out.println(mapping);
        count[i]++;
        if (found) {
            return;
        }
        if (i == all.length) {
            if (good(mapping, words, result)) {
                found = true;
            }
            return;
        }
        for (int v = 0; v <= 9; v++) {
            if (used[v]) {
                continue;
            }
            used[v] = true;
            mapping[all[i] - 'A'] = v;
            dfs(used, mapping, all, i + 1, words, result);
            if (found) {
                return;
            }
            mapping[all[i] - 'A'] = -1;
            used[v] = false;

        }
    }

    private boolean good(int[] mapping, String[] words, String result) {
        int rn = ton(result, mapping);
        if (rn == -1) {
            return false;
        }
        int adder = 0;
        for (String w : words) {
            int wn = ton(w, mapping);
            if (wn == -1) {
                return false;
            }
            adder += wn;
        }
        return rn == adder;
    }

    private int ton(String w, int[] mapping) {
        int r = 0;
        for (int i = 0; i < w.length(); i++) {
            int v = mapping[w.charAt(i) - 'A'];
            if (i == 0 && v == 0) {
                return -1;
            }
            r = r * 10 + v;
        }
        return r;
    }

    private void processWord(String str, Set<Character> all) {
        for (int j = str.length() - 1; j >= 0; j--) {
            char ci = str.charAt(j);
            all.add(ci);
        }
    }
}