import java.util.HashMap;
import java.util.Map;

public class FindNumberOfWaysReachKthStair {
    private Map<Integer, Map<Integer, Map<Integer, Integer>>> dp = new HashMap<>();

    public int waysToReachStair(int k) {
        return solve(1, 0, 0, k);
    }

    private int solve(int i, int jump, int j, int k) {
        if (i > k + 1) {
            return 0;
        }
        int res = 0;
        if (i == k) {
            res = 1;
        }
        if (dp.containsKey(i) && dp.get(i).containsKey(jump) && dp.get(i).get(jump).containsKey(j)) {
            return dp.get(i).get(jump).get(j);
        }
        int way1 = 0;
        if (i > 0 && j == 0) {
            way1 = solve(i - 1, jump, 1, k);
        }
        int way2 = solve(i + (1 << jump), jump + 1, 0, k);
        res += way1 + way2;
        dp.computeIfAbsent(i, m1 -> new HashMap<>()).computeIfAbsent(jump, m2 -> new HashMap<>()).put(j, res);

        return res;
    }
}
