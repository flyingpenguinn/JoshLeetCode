/*
LC#1209
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

import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveAllAdjacentDuplicatesII {
    public String removeDuplicates(String s, int k) {
        Deque<int[]> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            if (!stack.isEmpty() && stack.peek()[0] == c) {
                int freq = stack.peek()[1];
                if (freq == k - 1) {
                    stack.pop();
                } else {
                    stack.peek()[1]++;
                }
            } else {
                stack.push(new int[]{c, 1});
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            while (stack.peek()[1] > 0) {
                sb.append((char) (stack.peek()[0] + 'a'));
                stack.peek()[1]--;
            }
            stack.pop();

        }
        return sb.reverse().toString();
    }
}
