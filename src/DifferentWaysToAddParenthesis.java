import java.util.ArrayList;
import java.util.List;

/*
LC#241
Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are +, - and *.

Example 1:

Input: "2-1-1"
Output: [0, 2]
Explanation:
((2-1)-1) = 0
(2-(1-1)) = 2
Example 2:

Input: "2*3-4*5"
Output: [-34, -14, -10, -10, 10]
Explanation:
(2*(3-(4*5))) = -34
((2*3)-(4*5)) = -14
((2*(3-4))*5) = -10
(2*((3-4)*5)) = -10
(((2*3)-4)*5) = 10
 */
public class DifferentWaysToAddParenthesis {
    // divide the string by operation sign is better than grouping them in numbers
    // ops sign is a natural way to group
    // dont need to worry about "no bracket" case because it's the same result as "some" grouping
    List<Integer> r = new ArrayList<>();

    public List<Integer> diffWaysToCompute(String input) {
        return dor(input, 0, input.length() - 1);
    }

    List<Integer> dor(String input, int start, int end) {
        List<Integer> rl = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = start; i <= end; i++) {
            char c = input.charAt(i);
            sb.append(c);
            if (c == '+' || c == '-' || c == '*') {
                List<Integer> left = dor(input, start, i - 1);
                List<Integer> right = dor(input, i + 1, end);
                for (int l : left) {
                    for (int r : right) {
                        int cur = compute(l, c, r);
                        rl.add(cur);
                    }
                }
            }
        }
        if (rl.isEmpty()) {
            rl.add(Integer.valueOf(sb.toString()));
        }
        return rl;
    }

    int compute(int a, char sign, int b) {
        if (sign == '+') {
            return a + b;
        }
        if (sign == '-') {
            return a - b;
        } else {
            return a * b;
        }
    }
}
