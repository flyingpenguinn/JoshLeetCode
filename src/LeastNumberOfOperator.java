import java.util.HashMap;
import java.util.Map;

public class LeastNumberOfOperator {
    Map<Integer, Integer> cache = new HashMap<>();

    public int leastOpsExpressTarget(int x, int target) {
        if (x == 1) {
            return target - 1;
        }
        if (x == target) {
            return 0;
        }
        if (x > target) {
            return Math.min(target * 2 - 1, (x - target) * 2);
        }
        Integer cached = cache.get(target);
        if (cached != null) {
            return cached;
        }
        int count = 0;
        //in case it overflows
        long cur = x;
        while (cur < target) {
            cur *= x;
            count++;
        }
        if (cur == target) {
            cache.put(target, count);
            return count;
        }
        int minus = Integer.MAX_VALUE;
        if (cur - target < target) {
            // must be better than the original problem...
            minus = count + leastOpsExpressTarget(x, (int) (cur - target)) + 1;
        }
        int plus = count - 1 + leastOpsExpressTarget(x, (int) (target - cur / x)) + 1;
        int ans = Math.min(minus, plus);
        cache.put(target, ans);
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new LeastNumberOfOperator().leastOpsExpressTarget(100,
                200000000));
    }
}
