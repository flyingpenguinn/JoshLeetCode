

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
    // note the clever usage of "addition" to note the contributions of each char to avoid iterating the strings later
    private void preprocess(String w, Set<Character> chars, boolean[] first, int[] additions, int sign) {
        int add = 1;
        for (int i = w.length() - 1; i >= 0; i--) {
            char c = w.charAt(i);
            chars.add(c);
            additions[c - 'A'] += add * sign;
            add *= 10;
        }
        first[w.charAt(0) - 'A'] = true;
    }

    public boolean isSolvable(String[] words, String result) {
        Set<Character> chars = new HashSet<>();
        boolean[] first = new boolean[26];
        int[] additions = new int[26];
        for (String w : words) {
            preprocess(w, chars, first, additions, 1);
        }
        preprocess(result, chars, first, additions, -1);
        char[] ca = new char[chars.size()]; //a,b,c in an array
        int cai = 0;
        for (char c : chars) {
            ca[cai++] = c;
        }
        boolean[] used = new boolean[10];
        return dfs(0, ca, first, used, additions, 0);
    }

    private boolean dfs(int i, char[] ca, boolean[] first, boolean[] used, int[] additions, int balance) {
        if (i == ca.length) {
            return balance == 0;
        }
        int cint = ca[i] - 'A';
        int start = first[cint] ? 1 : 0;
        for (int j = start; j <= 9; j++) {
            if (used[j]) {
                continue;
            }
            balance += additions[cint] * j;
            used[j] = true;
            boolean good = dfs(i + 1, ca, first, used, additions, balance);
            if (good) {
                return true;
            }
            used[j] = false;
            balance -= additions[cint] * j;
        }
        return false;
    }
}