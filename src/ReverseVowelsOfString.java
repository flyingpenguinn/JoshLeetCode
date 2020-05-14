/*
LC#345
Write a function that takes a string as input and reverse only the vowels of a string.

Example 1:

Input: "hello"
Output: "holle"
Example 2:

Input: "leetcode"
Output: "leotcede"
Note:
The vowels does not include the letter "y".
 */
public class ReverseVowelsOfString {
    String vowel = "aeiouAEIOU";  // note this trick!

    public String reverseVowels(String str) {
        char[] s = str.toCharArray();
        int i = 0;
        int j = s.length - 1;
        while (i < j) {
            while (i < j && !vow(s[i])) {
                i++;
            }
            while (i < j && !vow(s[j])) {
                j--;
            }
            // both vow
            if (i < j) {
                swap(s, i, j);
            }
            i++;
            j--;
        }
        return new String(s);
    }

    void swap(char[] s, int i, int j) {
        char tmp = s[i];
        s[i] = s[j];
        s[j] = tmp;
    }

    boolean vow(char c) {
        return vowel.indexOf(c) != -1;
    }
}
