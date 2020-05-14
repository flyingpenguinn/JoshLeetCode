/*
LC#1138

Given a string s, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them causing the left and the right side of the deleted substring to concatenate together.

We repeatedly make k duplicate removals on s until we no longer can.

Return the final string after all such duplicate removals have been made.

It is guaranteed that the answer is unique.



Example 1:

Input: s = "abcd", k = 2
Output: "abcd"
Explanation: There's nothing to delete.
Example 2:

Input: s = "deeedbbcccbdaa", k = 3
Output: "aa"
Explanation:
First delete "eee" and "ccc", get "ddbbbdaa"
Then delete "bbb", get "dddaa"
Finally delete "ddd", get "aa"
Example 3:

Input: s = "pbbcggttciiippooaais", k = 2
Output: "ps"


Constraints:

1 <= s.length <= 10^5
2 <= k <= 10^4
s only contains lower case English letters.
 */

public class AlphabetBoard {
    public String alphabetBoardPath(String s) {
        int r = 0;
        int c = 0;
        char[] moves = new char[2];
        int[] steps = new int[2];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            int d = s.charAt(i) - 'a';
            int cr = d / 5;
            int cc = d % 5;
            steps[0] = Math.abs(cr - r);
            steps[1] = Math.abs(cc - c);
            moves[0] = cr > r ? 'D' : 'U';
            moves[1] = cc > c ? 'R' : 'L';
            // for z we will have to go left first then down. otherwise we always go row first then col
            // another way: go up before go right, go left before go down
            int start = d == 25 ? 1 : 0;
            int delta = d == 25 ? -1 : 1;
            for (int j = start; j >= 0 && j < 2; j += delta) {
                while (steps[j] > 0) {
                    sb.append(moves[j]);
                    steps[j]--;
                }
            }
            sb.append("!");
            r = cr;
            c = cc;
        }
        return sb.toString();

    }
}
