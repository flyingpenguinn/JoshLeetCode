import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
LC#1255
Given a list of words, list of  single letters (might be repeating) and score of every character.

Return the maximum score of any valid set of words formed by using the given letters (words[i] cannot be used two or more times).

It is not necessary to use all characters in letters and each letter can only be used once. Score of letters 'a', 'b', 'c', ... ,'z' is given by score[0], score[1], ... , score[25] respectively.



Example 1:

Input: words = ["dog","cat","dad","good"], letters = ["a","a","c","d","d","d","g","o","o"], score = [1,0,9,5,0,0,3,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0]
Output: 23
Explanation:
Score  a=1, c=9, d=5, g=3, o=2
Given letters, we can form the words "dad" (5+1+5) and "good" (3+2+2+5) with a score of 23.
Words "dad" and "dog" only get a score of 21.
Example 2:

Input: words = ["xxxz","ax","bx","cx"], letters = ["z","a","b","c","x","x","x"], score = [4,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,0,10]
Output: 27
Explanation:
Score  a=4, b=4, c=4, x=5, z=10
Given letters, we can form the words "ax" (4+5), "bx" (4+5) and "cx" (4+5) with a score of 27.
Word "xxxz" only get a score of 25.
Example 3:

Input: words = ["leetcode"], letters = ["l","e","t","c","o","d"], score = [0,0,1,1,1,0,0,0,0,0,0,1,0,0,1,0,0,0,0,1,0,0,0,0,0,0]
Output: 0
Explanation:
Letter "e" can only be used once.


Constraints:

1 <= words.length <= 14
1 <= words[i].length <= 15
1 <= letters.length <= 100
letters[i].length == 1
score.length == 26
0 <= score[i] <= 10
words[i], letters[i] contains only lower case English letters.
 */
public class MaxScoreWordsFormedByLetters {
    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        int n = words.length;
        int[] lmap = new int[26];
        for (int i = 0; i < letters.length; i++) {
            lmap[letters[i] - 'a']++;
        }
        int max = 0;
        int[] tlmap = new int[26];
        for (int i = 0; i < (1 << n); i++) {
            for (int j = 0; j < lmap.length; j++) {
                tlmap[j] = lmap[j];
            }
            int cur = calc(i, words, tlmap, score);
            max = Math.max(max, cur);
        }
        return max;
    }

    private int calc(int status, String[] words, int[] lmap, int[] score) {
        int j = words.length - 1;
        int r = 0;
        while (status > 0) {
            int bit = (status & 1);
            if (bit == 1) {
                String w = words[j];
                int cur = calcw(w, lmap, score);
                if (cur == -1) {
                    return -1;
                }
                r += cur;
            }
            status >>= 1;
            j--;
        }
        return r;
    }

    private int calcw(String w, int[] lmap, int[] score) {
        int r = 0;
        for (int i = 0; i < w.length(); i++) {
            int index = w.charAt(i) - 'a';
            if (lmap[index] <= 0) {
                return -1;
            }
            r += score[index];
            lmap[index]--;
        }
        return r;
    }

    public static void main(String[] args) {
        String[] strs = {"dog", "cat", "dad", "good"};
        char[] letters = {'a', 'a', 'c', 'd', 'd', 'd', 'g', 'o', 'o'};
        int[] score = {1, 0, 9, 5, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        System.out.println(new MaxScoreWordsFormedByLetters().maxScoreWords(strs, letters, score));
    }
}
