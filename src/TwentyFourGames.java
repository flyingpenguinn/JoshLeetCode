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
        if (a == null || a.length == 0) {
            return false;
        }
        // assuming a is valid positive number only
        List<Double> input = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            input.add(Double.valueOf(a[i]));
        }
        return dfs(input);
    }

    private double Eps = 0.000001;

    private boolean dfs(List<Double> input) {
        if (input.size() == 1) {
            if (Math.abs(input.get(0) - 24.0) < Eps) {
                return true;
            } else {
                return false;
            }
        }
        // get two different numbers together, j can start from i+1 because we loop both i-j and j-i in. but note this is NOT first two numbers!
        for (int i = 0; i < input.size(); i++) {
            for (int j = i + 1; j < input.size(); j++) {
                List<Double> combi = combinations(input.get(i), input.get(j));
                List<Double> ninput = new ArrayList<>();
                // can't do copy list and remove i+ remove j, because j's index may be impacted by i
                for (int k = 0; k < input.size(); k++) {
                    if (k != i && k != j) {
                        ninput.add(input.get(k));
                    }
                }
                for (double com : combi) {
                    List<Double> innerInput = new ArrayList<>(ninput);
                    innerInput.add(com);
                    boolean res = dfs(innerInput);
                    if (res) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private List<Double> combinations(double n1, double n2) {
        List<Double> r = new ArrayList<>();
        r.add(n1 + n2);
        r.add(n1 - n2);
        r.add(n2 - n1);
        r.add(n1 * n2);
        r.add(n1 / n2);
        r.add(n2 / n1);
        return r;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 4, 6};
        System.out.println(new TwentyFourGames().judgePoint24(nums));
    }
}
