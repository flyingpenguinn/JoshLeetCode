import java.util.ArrayDeque;
import java.util.Deque;

public class MinCostToChangeFinalValueOfExpression {
    // just like basic calculator using two stacks! here we calculate the cost to change to 0 and 1 respectively
    // if it's & then we know the cost to change to 0 and 1 respectively. see below for details
    private Deque<int[]> nums = new ArrayDeque<>();
    private Deque<Character> ops = new ArrayDeque<>();

    public int minOperationsToFlip(String s) {
        // store the cost to change the values to 0 and 1 as an array of size 2
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '0') {
                nums.push(new int[]{0, 1});
            } else if (c == '1') {
                nums.push(new int[]{1, 0});
            } else if (c == '(') {
                ops.push(c);
            } else if (c == '&' || c == '|') {
                // almost always collapsable...
                while(!ops.isEmpty() && ops.peek() != '(') {
                    collapse();
                }
                ops.push(c);
            } else {
                while (ops.peek() != '(') {
                    collapse();
                }
                ops.pop();
            }
        }
        while (nums.size() > 1) {
            collapse();
        }
        int[] top = nums.pop();
        return Math.max(top[0], top[1]);
    }

    private void collapse() {
        int[] n1 = nums.pop();
        int[] n2 = nums.pop();
        char op = ops.pop();
        int c020 = n1[0];
        int c021 = n1[1];
        int c120 = n2[0];
        int c121 = n2[1];
        int[] cur = new int[]{0, 0};
        if (op == '&') {
            // to get 0, we turn either to 0
            cur[0] = Math.min(c020, c120);
            // to get 1, we turn both to 1, or turn op to | and either to 1
            cur[1] = Math.min((c021 + c121), 1 + Math.min(c021, c121));
        } else {
            // to get 1, we turn either to 1
            cur[1] = Math.min(c021, c121);
            // or both to 0, or turn op to & and either to 0
            cur[0] = Math.min((c020 + c120), 1 + Math.min(c020, c120));
        }
        nums.push(cur);
    }

    public static void main(String[] args) {
        System.out.println(new MinCostToChangeFinalValueOfExpression().minOperationsToFlip("1|1|0"));
    }
}
