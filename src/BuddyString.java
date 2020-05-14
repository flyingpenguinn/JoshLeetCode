/*
LC#859
Given two strings A and B of lowercase letters, return true if and only if we can swap two letters in A so that the result equals B.



Example 1:

Input: A = "ab", B = "ba"
Output: true
Example 2:

Input: A = "ab", B = "ab"
Output: false
Example 3:

Input: A = "aa", B = "aa"
Output: true
Example 4:

Input: A = "aaaaaaabc", B = "aaaaaaacb"
Output: true
Example 5:

Input: A = "", B = "aa"
Output: false


Note:

0 <= A.length <= 20000
0 <= B.length <= 20000
A and B consist only of lowercase letters.
 */
public class BuddyString {
    public boolean buddyStrings(String a, String b) {
        int an = a.length();
        int bn = b.length();
        if (an != bn) {
            return false;
        }
        if (a.equals(b)) {
            return hasdupe(a);
        }
        int d1 = -1;
        int d2 = -1;
        for (int i = 0; i < an; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                if (d1 > 0 && d2 > 0) {
                    return false;
                }
                if (d1 < 0) {
                    d1 = i;
                } else {
                    d2 = i;
                }
            }
        }
        if (d1 < 0 || d2 < 0) {
            return false;
        }
        if (a.charAt(d1) != b.charAt(d2) || a.charAt(d2) != b.charAt(d1)) {
            return false;
        }
        return true;
    }

    private boolean hasdupe(String a) {
        int[] c = new int[26];
        for (int i = 0; i < a.length(); i++) {
            if (c[a.charAt(i) - 'a']++ > 0) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new BuddyString().buddyStrings("bc", "ac"));
    }
}
