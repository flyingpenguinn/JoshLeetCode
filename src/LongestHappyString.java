import java.util.ArrayDeque;
import java.util.Deque;

/*
LC#1405
A string is called happy if it does not have any of the strings 'aaa', 'bbb' or 'ccc' as a substring.

Given three integers a, b and c, return any string s, which satisfies following conditions:

s is happy and longest possible.
s contains at most a occurrences of the letter 'a', at most b occurrences of the letter 'b' and at most c occurrences of the letter 'c'.
s will only contain 'a', 'b' and 'c' letters.
If there is no such string s return the empty string "".



Example 1:

Input: a = 1, b = 1, c = 7
Output: "ccaccbcc"
Explanation: "ccbccacc" would also be a correct answer.
Example 2:

Input: a = 2, b = 2, c = 1
Output: "aabbc"
Example 3:

Input: a = 7, b = 1, c = 0
Output: "aabaa"
Explanation: It's the only correct answer in this case.


Constraints:

0 <= a, b, c <= 100
a + b + c > 0
 */
public class LongestHappyString {
    // always picked the most frequent one if we can.
    public String longestDiverseString(int a, int b, int c) {
        return dol(new int[]{a, b, c}, new ArrayDeque<>());
    }

    private String dol(int[] r, Deque<Character> dq) {
        char picked = '*';
        int ra = r[0];
        int rb = r[1];
        int rc = r[2];
        if (ra >= rb && rb >= rc) {
            picked = process('a', 'b', 'c', ra, rb, rc, dq);
        } else if (ra >= rb & ra >= rc) {
            picked = process('a', 'c', 'b', ra, rc, rb, dq);
        } else if (rb >= ra && rb >= rc) {
            picked = process('b', 'a', 'c', rb, ra, rc, dq);
        } else if (rb >= rc && rb >= ra) {
            picked = process('b', 'c', 'a', rb, rc, ra, dq);
        } else if (rc >= ra && rc >= rb) {
            picked = process('c', 'a', 'b', rc, ra, rb, dq);
        } else if (rc >= rb && rc >= ra) {
            picked = process('c', 'b', 'a', rc, rb, ra, dq);
        }
        if (picked == '*') {
            return "";
        } else {
            if (dq.size() == 2) {
                dq.pollFirst();
            }
            dq.offerLast(picked);
            r[picked - 'a']--;
            return picked + dol(r, dq);
        }
    }

    private char process(char a, char b, char c, int ra, int rb, int rc, Deque<Character> dq) {
        if (notbad(dq, a) && ra > 0) {
            return a;
        }
        if (notbad(dq, b) && rb > 0) {
            return b;
        }
        if (notbad(dq, c) && rc > 0) {
            return c;
        }
        return '*';
    }

    private boolean notbad(Deque<Character> dq, char a) {
        if (dq.size() < 2) {
            return true;
        }
        if (dq.peekFirst() != a || dq.peekLast() != a) {
            return true;
        }
        return false;
    }
}
