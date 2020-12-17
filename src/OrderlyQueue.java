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
    // when k>1, it means we can swap two element's positions
    public String orderlyQueue(String s, int k) {
        char[] c = s.toCharArray();
        if(k>1){
            Arrays.sort(c);
            return new String(c);
        }else{
            String min = s;
            for(int i=0; i<s.length();i++){
                s = s.substring(1)+s.substring(0,1);
                if(s.compareTo(min)<0){
                    min = s;
                }
            }
            return min;
        }
    }

    public static void main(String[] args) {
        System.out.println(new OrderlyQueue().orderlyQueue("xmvzi", 2));
    }
}
