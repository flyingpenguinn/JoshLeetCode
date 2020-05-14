/*
LC#125
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

Note: For the purpose of this problem, we define empty string as valid palindrome.

Example 1:

Input: "A man, a plan, a canal: Panama"
Output: true
Example 2:

Input: "race a car"
Output: false
 */
public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            char si = norm(s.charAt(i));
            char sj = norm(s.charAt(j));
            if (si == ' ') {
                i++;
            } else if (sj == ' ') {
                j--;
            } else if (si != sj) {
                return false;
            } else { // must be else
                i++;
                j--;
            }

        }
        return true;
    }

    char norm(char si) {
        if (si >= 'A' && si <= 'Z') {
            return (char) ('a' + (si - 'A'));
        } else if (si >= 'a' && si <= 'z') {
            return si;
        } else if (si >= '0' && si <= '9') {
            return si;
        } else {
            return ' ';
        }
    }


    public static void main(String[] args) {

    }
}
