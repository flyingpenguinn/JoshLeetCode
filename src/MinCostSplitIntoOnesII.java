import java.util.HashMap;
import java.util.Map;

public class MinCostSplitIntoOnesII {
    /*
    or just public long minCost(long n) {
        return n * (n - 1) / 2;
    }
     */
    private Map<Long, Long> dp = new HashMap<>();

    public long minCost(long n) {

        if (n == 1) {
            return 0;
        }
        if (dp.containsKey(n)) {
            return dp.get(n);
        }
        //   System.out.println(n);
        long a = n / 2;
        long b = n - a;
        long rt = a * b + minCost(a) + minCost(b);
        dp.put(n, rt);
        return rt;
    }
}
