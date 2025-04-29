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


public class ShortestPalindrome {

    // find max palin from index 0, then add rest, reversed, at the head.
    // max prefix that is also palin can use kmp lps table algo
    public String shortestPalindrome(String s) {
        int n = s.length();
        if (n <= 1) return s;
        String rev = new StringBuilder(s).reverse().toString();
        // build “pattern” = s + "#" + rev(s)
        String t = s + "#" + rev;
        int m = t.length();
        int[] lps = new int[m];
        // compute prefix-function on t
        for (int i = 1; i < m; i++) {
            int j = lps[i - 1];
            while (j > 0 && t.charAt(i) != t.charAt(j)) {
                j = lps[j - 1];
            }
            if (t.charAt(i) == t.charAt(j)) j++;
            lps[i] = j;
        }
        // lps[m-1] = length of longest prefix of s that matches a suffix of rev(s)
        int palPref = lps[m - 1];
        // the part s[palPref..n) isn’t in that palindrome; reverse it and prepend
        String toAdd = rev.substring(0, n - palPref);
        return toAdd + s;
    }

    public static void main(String[] args) {
        System.out.println(new ShortestPalindrome().shortestPalindrome("abaa"));
    }

}

