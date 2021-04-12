

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
    // because we can't use duplicated numbers, the complexity is 10! not 10^10
    // we then also want to make the leaf level check simple enough to be strictly O(1)
    // note the clever usage of "coe" to note the contributions of each char to avoid iterating the strings later
    private boolean solved = false;

    public boolean isSolvable(String[] words, String result) {
        int[] wcoe = getcoe(words);
        int[] rcoe = getcoe(new String[]{result});
        Set<Character> allcs = new HashSet<>();
        boolean[] starts = new boolean[26];
        for (String w : words) {
            starts[w.charAt(0) - 'A'] = w.length() > 1;
            for (int i = 0; i < w.length(); i++) {
                allcs.add(w.charAt(i));
            }
        }
        starts[result.charAt(0) - 'A'] = result.length() > 1;
        for (int i = 0; i < result.length(); i++) {
            allcs.add(result.charAt(i));
        }
        int[] chars = new int[allcs.size()];
        int ci = 0;
        for (char c : allcs) {
            chars[ci++] = c - 'A';
        }
        int[] assign = new int[26];
        boolean[] used = new boolean[10];
        dfs(chars, 0, wcoe, rcoe, 0, 0, assign, used, starts);
        return solved;
    }

    // only used for debugging
    private String converted(String w, int[] assign) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < w.length(); i++) {
            sb.append(assign[w.charAt(i) - 'A']);
        }
        return sb.toString();
    }

    //chars: array of chars in question, in index
    private void dfs(int[] chars, int i, int[] wcoe, int[] rcoe, int wr, int rr, int[] assign, boolean[] used, boolean[] starts) {
        if (solved) {
            return;
        }
        int n = chars.length;
        if (i == n) {
            if (wr == rr) {
                solved = true;
            }
            return;
        }
        int cind = chars[i];
        for (int v = 0; v <= 9; v++) {
            if (starts[cind] && v == 0) {
                continue;
            }
            if (used[v]) {
                continue;
            }
            used[v] = true;
            int nwr = wr + wcoe[cind] * v;
            int nrr = rr + rcoe[cind] * v;
            assign[cind] = v;
            dfs(chars, i + 1, wcoe, rcoe, nwr, nrr, assign, used, starts);
            if (solved) {
                break;
            }
            used[v] = false;
        }
    }


    // if A is at 10's digit its contribution is 10. if on 100's its contribution is 100
    private int[] getcoe(String[] words) {
        int[] coe = new int[26];
        for (String w : words) {
            int wn = w.length();
            int base = 1;
            for (int i = wn - 1; i >= 0; i--) {
                int cind = w.charAt(i) - 'A';
                coe[cind] += base;
                base *= 10;
            }
        }
        return coe;
    }
}