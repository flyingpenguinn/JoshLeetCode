import java.util.*;

/*
LC#899
A string S of lowercase letters is given.  Then, we may make any number of moves.

In each move, we choose one of the first K letters (starting from the left), remove it, and place it at the end of the string.

Return the lexicographically smallest string we could have after any number of moves.



Example 1:

Input: S = "cba", K = 1
Output: "acb"
Explanation:
In the first move, we move the 1st character ("c") to the end, obtaining the string "bac".
In the second move, we move the 1st character ("b") to the end, obtaining the final result "acb".
Example 2:

Input: S = "baaca", K = 3
Output: "aaabc"
Explanation:
In the first move, we move the 1st character ("b") to the end, obtaining the string "aacab".
In the second move, we move the 3rd character ("c") to the end, obtaining the final result "aaabc".


Note:

1 <= K <= S.length <= 1000
S consists of lowercase letters only.
 */
public class OrderlyQueue {
    public String orderlyQueue(String s, int k) {
        // when k>1 we can always sort it
        // we can first two chars as buffer to swap any two numbers
        if (k > 1) {
            char[] sc = s.toCharArray();
            Arrays.sort(sc);
            return new String(sc);
        }
        int n = s.length();
        String min = s;

        for (int i = 1; i < n; i++) {
            String cur = s.substring(i) + s.substring(0, i);
            if (cur.compareTo(min) < 0) {
                min = cur;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        System.out.println(new OrderlyQueue().orderlyQueue("xmvzi", 2));
    }
}
