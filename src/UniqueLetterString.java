import java.util.Arrays;

/*
LC#828
A character is unique in string S if it occurs exactly once in it.

For example, in string S = "LETTER", the only unique characters are "L" and "R".

Let's define UNIQ(S) as the number of unique characters in string S.

For example, UNIQ("LETTER") =  2.

Given a string S with only uppercases, calculate the sum of UNIQ(substring) over all non-empty substrings of S.

If there are two or more equal substrings at different positions in S, we consider them different.

Since the answer can be very large, return the answer modulo 10 ^ 9 + 7.



Example 1:

Input: "ABC"
Output: 10
Explanation: All possible substrings are: "A","B","C","AB","BC" and "ABC".
Evey substring is composed with only unique letters.
Sum of lengths of all substring is 1 + 1 + 1 + 2 + 2 + 3 = 10
Example 2:

Input: "ABA"
Output: 8
Explanation: The same as example 1, except uni("ABA") = 1.
 */
public class UniqueLetterString {

    // think about contribution of a[i] as a function of a[i-1]. usually substring score problem can be converted to this
    int Mod = 1000000007;

    public int uniqueLetterString(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        long r = 0L;
        long prev = 0L;
        int[][] last = new int[26][2];
        for (int i = 0; i < 26; i++) {
            Arrays.fill(last[i], -1);
        }
        for (int i = 0; i < n; i++) {
            char c = cs[i];
            int cind = c - 'A';
            int j = last[cind][0];
            int k = last[cind][1];
            // ...k...j...i
            // (j+1...i)->i need +1
            // (k+1...j)->i-1 need -1
            // ( 0...k)->i-1 no change
            int p1 = (i - j);
            int p2 = (j - k);
            long cur = prev + p1 - p2;
            r += cur;
            r %= Mod;
            last[cind][1] = last[cind][0];
            last[cind][0] = i;
            prev = cur;
        }
        return (int) r;
    }

    public static void main(String[] args) {
        System.out.println(new UniqueLetterString().uniqueLetterString("ABCCDEGGEEFFDDE"));
    }

}
