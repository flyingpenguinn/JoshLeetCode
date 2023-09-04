import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaxSumOfAlmostUniqueSubarray {
    public long maxSum(List<Integer> a, int m, int k) {
        int n = a.size();
        long sum = 0;
        long res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            update(map, a.get(i), 1);
            sum += a.get(i);
            if (map.size() >= m) {
                res = Math.max(res, sum);
            }
            int head = i - k + 1;
            if (head >= 0) {
                update(map, a.get(head), -1);
                sum -= a.get(head);
            }
        }
        return res;
    }

    private void update(Map<Integer, Integer> m, int k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }
}
