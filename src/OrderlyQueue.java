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
    // when k>1, it means we can swap two element's positions. because we can always do cba->cab->abc-> bca as we can see we can reverse the first two chars
    // with this we can reverse any two adjacent chars so it's as good as a free swapping
    public String orderlyQueue(String s, int k) {
        if (k > 1) {
            char[] sc = s.toCharArray();
            Arrays.sort(sc);
            return new String(sc);
        } else {
            String res = s;
            for (int i = 0; i < s.length(); i++) {
                s = s.substring(1) + s.charAt(0);
                if (res.compareTo(s) > 0) {
                    res = s;
                }
            }
            return res;
        }
    }

    public static void main(String[] args) {
        System.out.println(new OrderlyQueue().orderlyQueue("xmvzi", 2));
    }
}
