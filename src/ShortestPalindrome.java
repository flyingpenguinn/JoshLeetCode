/*
LC#214
Given a string s, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.

Example 1:

Input: "aacecaaa"
Output: "aaacecaaa"
Example 2:

Input: "abcd"
Output: "dcbabcd"
 */

// @TODO do it with tdp: concept is similar that we construct s#reverse(s) then calc the kmp table
public class ShortestPalindrome {

    // find max palin from index 0, then add rest, reversed, at the head
    public String shortestPalindrome(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        for (int i = n - 1; i >= 0; i--) {
            if (cs[0] == cs[i] && ispalin(cs, 0, i)) {
                return new StringBuilder((s.substring(i + 1))).reverse().toString() + s;
            }
        }
        return reverse(s) + s;
    }

    private String reverse(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    private boolean ispalin(char[] cs, int i, int j) {
        while (i <= j) {
            if (cs[i++] != cs[j--]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new ShortestPalindrome().shortestPalindrome("abaa"));
    }

}

