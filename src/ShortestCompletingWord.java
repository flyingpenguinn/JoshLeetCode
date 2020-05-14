import java.util.Arrays;

/*
LC#748
Find the minimum length word from a given dictionary words, which has all the letters from the string licensePlate. Such a word is said to complete the given string licensePlate

Here, for letters we ignore case. For example, "P" on the licensePlate still matches "p" on the word.

It is guaranteed an answer exists. If there are multiple answers, return the one that occurs first in the array.

The license plate might have the same letter occurring multiple times. For example, given a licensePlate of "PP", the word "pair" does not complete the licensePlate, but the word "supper" does.

Example 1:
Input: licensePlate = "1s3 PSt", words = ["step", "steps", "stripe", "stepple"]
Output: "steps"
Explanation: The smallest length word that contains the letters "S", "P", "S", and "T".
Note that the answer is not "step", because the letter "s" must occur in the word twice.
Also note that we ignored case for the purposes of comparing whether a letter exists in the word.
Example 2:
Input: licensePlate = "1s3 456", words = ["looks", "pest", "stew", "show"]
Output: "pest"
Explanation: There are 3 smallest length words that contains the letters "s".
We return the one that occurred first.
Note:
licensePlate will be a string with length in range [1, 7].
licensePlate will contain digits, spaces, or letters (uppercase or lowercase).
words will have a length in the range [10, 1000].
Every words[i] will consist of lowercase letters, and have length in range [1, 15].
 */
public class ShortestCompletingWord {
    public String shortestCompletingWord(String s, String[] w) {
        int[] sc = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            update(c, sc);
        }
        int[] wc = new int[26];
        String r = null;
        for (int i = 0; i < w.length; i++) {
            Arrays.fill(wc, 0);
            for (int j = 0; j < w[i].length(); j++) {
                char c = w[i].charAt(j);
                update(c, wc);
            }
            if ((r == null || w[i].length() < r.length()) && cover(sc, wc)) {
                r = w[i];
            }
        }
        return r;
    }

    void update(char c, int[] sc) {
        if (c >= 'a' && c <= 'z') {
            sc[c - 'a']++;
        } else if (c >= 'A' && c <= 'Z') {
            sc[c - 'A']++;
        }
    }

    boolean cover(int[] a, int[] b) {
        for (int i = 0; i < 26; i++) {
            if (b[i] < a[i]) {
                return false;
            }
        }
        return true;
    }
}
