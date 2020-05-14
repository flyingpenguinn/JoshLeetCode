import java.util.*;

/*
LC#679
You have 4 cards each containing a number from 1 to 9. You need to judge whether they could operated through *, /, +, -, (, ) to get the value of 24.

Example 1:
Input: [4, 1, 8, 7]
Output: True
Explanation: (8-4) * (7-1) = 24
Example 2:
Input: [1, 2, 1, 2]
Output: False
Note:
The division operator / represents real division, not integer division. For example, 4 / (1 - 2/3) = 12.
Every operation done is between two numbers. In particular, we cannot use - as a unary operator. For example, with [1, 1, 1, 1] as input, the expression -1 - 1 - 1 - 1 is not allowed.
You cannot concatenate numbers together. For example, if the input is [1, 2, 1, 2], we cannot write this as 12 + 12.
 */
public class TwentyFourGames {
    // fetch two numbers every time. put their result back to list. this is equivalent to adding () to every two numbers and get thei result
    // note in getting result we do a-b and b-a as well so that we can cover all possible sequences
    public boolean judgePoint24(int[] a) {
        List<Double> list = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            list.add(a[i] + 0.0);
        }
        return doj(list);
    }

    // fetch two every time. may feth the result of another two. i.e. add () around two numbers each time. can be a redundant ()
    private boolean doj(List<Double> list) {
        int n = list.size();
        if (n == 1) {
            boolean rt = Math.abs(list.get(0) - 24.0) < 0.000001;
            return rt;
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                List<Double> next = new ArrayList<>();
                for (int k = 0; k < n; k++) {
                    if (k != i && k != j) {
                        next.add(list.get(k));
                    }
                }
                List<Double> combi = add(list.get(i), list.get(j));
                for (double com : combi) {
                    next.add(com);
                    if (doj(next)) {
                        return true;
                    }
                    next.remove(next.size() - 1);
                }
            }
        }
        return false;
    }

    // all sorts of positioning!  a/b or b/a all good
    private List<Double> add(double v1, double v2) {
        List<Double> r = new ArrayList<>();
        r.add(v1 + v2);
        r.add(v1 - v2);
        r.add(v2 - v1);
        r.add(v1 * v2);
        r.add(v1 / v2);
        r.add(v2 / v1);
        return r;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 4, 6};
        System.out.println(new TwentyFourGames().judgePoint24(nums));
    }
}
