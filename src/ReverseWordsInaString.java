/*
LC#151
Given an input string, reverse the string word by word.



Example 1:

Input: "the sky is blue"
Output: "blue is sky the"
Example 2:

Input: "  hello world!  "
Output: "world! hello"
Explanation: Your reversed string should not contain leading or trailing spaces.
Example 3:

Input: "a good   example"
Output: "example good a"
Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.


Note:

A word is defined as a sequence of non-space characters.
Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.
You need to reduce multiple spaces between two words to a single space in the reversed string.


Follow up:

For C programmers, try to solve it in-place in O(1) extra space.
 */
public class ReverseWordsInaString {
    public String reverseWords(String s) {
        char[] cs = s.toCharArray();
        // remove excessive spaces
        int k = removeExtraSpaces(cs);
        reverseIndividual(cs, k);
        // reverse all
        rev(cs, 0, k-1);
        return toString(cs, k);
    }

    private String toString(char[] cs, int k) {
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while (i < k) {
            sb.append(cs[i++]);
        }
        return sb.toString();
    }

    private int removeExtraSpaces(char[] cs) {
        int n = cs.length;
        int i = 0;
        int j = n - 1;
        while (i < n && cs[i] == ' ') {
            i++;
        }
        while (j >= 0 && cs[j] == ' ') {
            j--;
        }
        int k = 0;
        while (i <= j) {
            if (cs[i] == ' ' && (i > 0 && cs[i - 1] == ' ')) {
                i++;
                continue;
            }
            cs[k++] = cs[i];
            i++;
        }
        return k;
    }

    private int reverseIndividual(char[] cs, int n) {
        // reverse individual
        int i = 0;
        int j = 0;
        while (j <= n) {
            // j==n is same as cs==' '
            if (j == n || cs[j] == ' ') {
                rev(cs, i, j - 1);
            }
            //j==0 is same as cj-1==' '
            if (j > 0 && cs[j - 1] == ' ') {
                i = j;
            }
            j++;
        }
        return n;
    }

    void rev(char[] c, int i, int j) {
        //   System.out.println(i+"..."+j);
        while (i < j) {
            char tmp = c[i];
            c[i] = c[j];
            c[j] = tmp;
            i++;
            j--;
        }
    }

}
