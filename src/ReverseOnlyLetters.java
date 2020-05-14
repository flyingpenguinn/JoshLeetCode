/*
LC#917
Given a string S, return the "reversed" string where all characters that are not a letter stay in the same place, and all letters reverse their positions.



Example 1:

Input: "ab-cd"
Output: "dc-ba"
Example 2:

Input: "a-bC-dEf-ghIj"
Output: "j-Ih-gfE-dCba"
Example 3:

Input: "Test1ng-Leet=code-Q!"
Output: "Qedo1ct-eeLg=ntse-T!"


Note:

S.length <= 100
33 <= S[i].ASCIIcode <= 122
S doesn't contain \ or "
 */
public class ReverseOnlyLetters {
    public String reverseOnlyLetters(String s) {
        char[] cs = s.toCharArray();
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            boolean li = Character.isLetter(cs[i]);
            boolean lj = Character.isLetter(cs[j]);

            if (li && lj) {
                swap(cs, i++, j--);
            } else if (li) {
                j--;
            } else if (lj) {
                i++;
            } else {
                // both non letter still add
                i++;
                j--;
            }
        }
        return new String(cs);
    }

    void swap(char[] cs, int i, int j) {
        char tmp = cs[i];
        cs[i] = cs[j];
        cs[j] = tmp;
    }
}
