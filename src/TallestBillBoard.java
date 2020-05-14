import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TallestBillBoard {

    public int tallestBillboard(int[] rods) {
        Map<Integer, Integer> dp = new HashMap<>(), cur; // key = diff, value = the best pair of sum (the min of them) that we can get
        dp.put(0, 0); // if pick nothing, diff = 0 and best sum = 0
        for (int x : rods) {
            cur = new HashMap<>(dp);
            for (int d : cur.keySet()) {
                Integer y = cur.get(d);
                // note y must be got when current rod x is not considered yet
                dp.put(d + x, Math.max(y, dp.getOrDefault(x + d, 0)));
                if (x > d) {
                    dp.put(x - d, Math.max(y + d, dp.getOrDefault(x - d, 0)));
                } else {
                    dp.put(d - x, Math.max(y + x, dp.getOrDefault(d - x, 0)));
                }
                // dp[d] is not to select x and it won't really change
            }
        }
        return dp.get(0);
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        // int[] rods = {1,2};
        int[] rods = {1, 2, 3, 4, 5, 6};
        // int[] rods = {3, 4, 3, 3, 2};
        System.out.println(new TallestBillBoard().tallestBillboard(rods));
        System.out.println(System.currentTimeMillis() - start);
    }
}


// passable but TLE very likely....
class TallestBillboardTwoStates {

    Map<Integer, Integer> dp = new HashMap<>();
    int best = -1;

    public int tallestBillboard(int[] rods) {
        if (rods.length == 0) {
            return 0;
        }
        int n = rods.length;
        int[] right = new int[n];
        right[n - 1] = rods[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            right[i] = rods[i] + right[i + 1];
        }
        int sum = Math.max(0, dfs(rods, 0, 0, 0, right));

        return sum;
    }

    private int dfs(int[] rods, int i, int sum, int delta, int[] right) {
        if (best == 2500) {
            return best;
        }
        if (i == rods.length) {
            if (delta == 0) {
                int result = sum / 2;
                best = Math.max(best, result);
                return result;
            } else {
                return -1;
            }
        }
        int later = right[i];
        if (delta > later) {
            return -1;
        }
        if (sum + later < best * 2) {
            return -1;
        }
        int hashSeed = 15;
        int code = 7 * hashSeed * ((hashSeed * 31 + sum) * 31 * hashSeed + delta) + i;
        Integer cached = dp.get(code);
        if (cached != null) {
            return cached;
        }

        int n1 = dfs(rods, i + 1, sum, delta, right);
        int bestwecan = (sum + later) / 2;
        int n2 = -1;
        int n3 = -1;
        int r = -1;
        if (n1 <= bestwecan) {
            n2 = dfs(rods, i + 1, sum + rods[i], delta + rods[i], right);
            n3 = dfs(rods, i + 1, sum + rods[i], Math.abs(delta - rods[i]), right);
            r = Math.max(n1, Math.max(n2, n3));
        }
        dp.put(code, r);
        return r;
    }

}
