import java.util.PriorityQueue;

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
    // pick only one each time from the max. if the max forms aaa, pick the 2nd max, but only pick 1 each time
    public String longestDiverseString(int a, int b, int c) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(y[0], x[0]));
        pushToQ(a, pq, 1);
        pushToQ(b, pq, 2);
        pushToQ(c, pq, 3);
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            int[] t1 = pq.poll();
            char last = (char) (t1[1] + 'a' - 1);
            int sn = sb.length();
            if (sn >= 2 && sb.charAt(sn - 1) == last && sb.charAt(sn - 2) == last) {
                if (pq.isEmpty()) {
                    break;
                }

                int[] t2 = pq.poll();
                sb.append((char) (t2[1] + 'a' - 1));
                pushToQ(t2[0] - 1, pq, t2[1]);
                pq.offer(t1);
            } else {
                sb.append((char) (t1[1] + 'a' - 1));
                pushToQ(t1[0] - 1, pq, t1[1]);
            }
        }
        return sb.toString();
    }

    private static void pushToQ(int a, PriorityQueue<int[]> pq, int x) {
        if (a > 0) {
            pq.offer(new int[]{a, x});
        }
    }
}
