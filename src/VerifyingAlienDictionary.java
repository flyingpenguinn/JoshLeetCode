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
    public boolean isAlienSorted(String[] words, String order) {
        for (int i = 1; i < words.length; i++) {
            if (!valid(words[i - 1], words[i], order)) {
                return false;
            }
        }
        return true;
    }

    private boolean valid(String s, String t, String order) {
        int i = 0;
        for (; i < s.length() && i < t.length(); i++) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            if (sc != tc) {
                int sindex = order.indexOf(sc);
                int tindex = order.indexOf(tc);
                if (sindex > tindex) {
                    return false;
                } else if (sindex < tindex) {
                    break;
                }
            }
        }
        if (i == t.length() && i != s.length()) {
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] args) {

    }
}
