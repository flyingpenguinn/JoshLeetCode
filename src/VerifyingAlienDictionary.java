import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/*
LC#953
In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.

Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographicaly in this alien language.



Example 1:

Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
Output: true
Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
Example 2:

Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
Output: false
Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
Example 3:

Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
Output: false
Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).


Constraints:

1 <= words.length <= 100
1 <= words[i].length <= 20
order.length == 26
All characters in words[i] and order are English lowercase letters.
 */
public class VerifyingAlienDictionary {
    public boolean isAlienSorted(String[] w, String order) {
        for (int i = 0; i + 1 < w.length; i++) {
            int cmp = compare(w[i], w[i + 1], order);
            if (cmp > 0) {
                return false;
            }
        }
        return true;
    }

    int compare(String a, String b, String order) {
        int i = 0;
        int j = 0;
        while (i < a.length() && j < b.length()) {
            int cmp = comparechar(a.charAt(i), b.charAt(j), order);
            if (cmp != 0) {
                return cmp;
            }
            i++;
            j++;
        }
        if (j == b.length() && i < a.length()) {
            return 1;
        } else if (i == a.length() && j < b.length()) {
            return -1;
        } else {
            return 0;
        }
    }

    int comparechar(char c, char d, String order) {
        return order.indexOf(c) - order.indexOf(d);
    }

    public static void main(String[] args) {

    }
}
