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
public class CountUniqueCharsOfAllSubstrings {

    // think about contribution of a[i] as a function of a[i-1]. usually substring score problem can be converted to this

    public int uniqueLetterString(String s) {
        long Mod = 1000000007;
        int n = s.length();
        int[][] map = new int[26][2];
        for (int i = 0; i < 26; i++) {
            Arrays.fill(map[i], -1);
        }
        long[] dp = new long[n];
        long r = 0;
        for (int i = 0; i < n; i++) {
            int[] dq = map[s.charAt(i) - 'A'];
            int j = dq[1];
            int k = dq[0];
            // this is key: note dp[i] means score for substring ENDING at i.
            // so if we add in i, then
            // k...j....i : i,j,k has equal chars
            // for substrings starting in j+1....i-1, ending at i-1, we add 1 because we now have a new unique char
            // for substrings starting in k+1....j, ending at i-1, we -1 because we  now took away a unique char
            // substrings ending in 0...k didnt change we have duplications anyway
            // we then add 1 to count in i itself
            long cur = (i == 0 ? 0 : dp[i - 1]) + (i - j) - (j - k);
            cur %= Mod;
            dp[i] = cur;
            r += cur;
            r %= Mod;
            dq[1] = i;
            dq[0] = j;
        }
        return (int) r;
    }

    public static void main(String[] args) {
        System.out.println(new CountUniqueCharsOfAllSubstrings().uniqueLetterString("ABCCDEGGEEFFDDE"));
    }

}
