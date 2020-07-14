import java.util.HashMap;
import java.util.Map;

/*
LC#494
You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.

Find out how many ways to assign symbols to make sum of integers equal to target S.

Example 1:
Input: nums is [1, 1, 1, 1, 1], S is 3.
Output: 5
Explanation:

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

There are 5 ways to assign symbols to make the sum of nums be target 3.
Note:
The length of the given array is positive and will not exceed 20.
The sum of elements in the given array will not exceed 1000.
Your output answer is guaranteed to be fitted in a 32-bit integer.
 */
public class TargetSum {
    // for each number either -a or +a and move on to the next. note we could convert this to a 1d dp
    // by factoring out i since we only need i+1
    private Map<Integer, Map<Integer, Integer>> dp = new HashMap<>();

    public int findTargetSumWays(int[] a, int t) {
        if (a == null || a.length == 0) {
            return 0;
        }
        return solve(a, 0, 0, t);
    }

    private int solve(int[] a, int i, int cur, int t) {
        if (i == a.length) {
            if (cur == t) {
                return 1;
            } else {
                return 0;
            }
        }
        Map<Integer, Integer> cm = dp.getOrDefault(i, new HashMap<>());
        Integer cached = cm.get(cur);
        if (cached != null) {
            return cached;
        }
        int rt = solve(a, i + 1, cur + a[i], t) + solve(a, i + 1, cur - a[i], t);
        cm.put(cur, rt);
        dp.put(i, cm);
        return rt;
    }
}
