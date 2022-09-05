import java.util.HashMap;
import java.util.Map;

public class NumberOfWaysReachPosAfterKsteps {
    private Map<Long, Map<Long, Long>> dp = new HashMap<>();

    private long mod = 1000000007;

    public int numberOfWays(int startPos, int endPos, int k) {
        return (int) solve(startPos, endPos, k);
    }

    private long solve(long s, long t, long k) {
        if (k == 0) {
            return s == t ? 1L : 0L;
        }
        if (Math.abs(t - s) > k) {
            return 0L;
        }
        Map<Long, Long> cdp = dp.getOrDefault(s, new HashMap<>());
        if (cdp.containsKey(k)) {
            return cdp.get(k);
        }
        long way1 = solve(s - 1, t, k - 1);
        long way2 = solve(s + 1, t, k - 1);
        long cur = way1 + way2;
        cur %= mod;
        cdp.put(k, cur);
        dp.put(s, cdp);
        return cur;
    }
}
