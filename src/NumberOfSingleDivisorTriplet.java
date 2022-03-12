import java.util.HashMap;
import java.util.Map;

public class NumberOfSingleDivisorTriplet {
    public long singleDivisorTriplet(int[] a) {
        int n = a.length;
        Map<Integer, Long> m = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            m.put(a[i], m.getOrDefault(a[i], 0L) + 1);
        }
        long res = 0L;
        for (int k1 : m.keySet()) {
            for (int k2 : m.keySet()) {
                if (k1 > k2) {
                    continue;
                }
                for (int k3 : m.keySet()) {
                    if (k1 > k3 || k2 > k3) {
                        continue;
                    }
                    int sum = k1 + k2 + k3;
                    int cnt = 0;
                    int[] cand = new int[]{k1, k2, k3};
                    for (int k : cand) {
                        if (sum % k == 0) {
                            ++cnt;
                        }
                    }
                    if (cnt != 1) {
                        continue;
                    }
                    long delta = 0;
                    if (k1 == k2) {
                        delta = m.get(k3) * m.get(k1) * (m.get(k1) - 1) / 2;
                    } else if (k2 == k3) {
                        delta = m.get(k1) * m.get(k2) * (m.get(k2) - 1) / 2;
                    } else if (k1 == k3) {
                        delta = m.get(k2) * m.get(k1) * (m.get(k3) - 1) / 2;
                    } else {
                        delta = m.get(k1) * m.get(k2) * m.get(k3);
                    }
                    delta *= 6L;
                    res += delta;
                }
            }
        }
        return res;
    }
}
