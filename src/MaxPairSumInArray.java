import java.util.HashMap;
import java.util.Map;

public class MaxPairSumInArray {
    public int maxSum(int[] a) {
        Map<Integer, Integer> m = new HashMap<>();
        int res = -1;
        for (int ai : a) {
            int md = maxd(ai);
            if (m.containsKey(md)) {
                int cmax = m.get(md);
                res = Math.max(res, cmax + ai);
                if (ai > cmax) {
                    m.put(md, ai);
                }
            } else {
                m.put(md, ai);
            }
        }
        return res;
    }

    private int maxd(int n) {
        int res = 0;
        while (n > 0) {
            int d = n % 10;
            res = Math.max(res, d);
            n /= 10;
        }
        return res;
    }
}
