import java.util.HashMap;
import java.util.Map;

public class MaxFreqScoreOfSubarray {
    private long mod = (long) (1e9 + 7);

    private void update(Map<Integer, Integer> m, int k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }

    public int maxFrequencyScore(int[] a, int k) {
        int n = a.length;
        long score = 0;
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < k - 1; ++i) {
            update(m, a[i], 1);
        }
        for (int v : m.keySet()) {
            score += pow(v, m.get(v));
            score %= mod;
        }
        long res = 0;
        for (int i = k - 1; i < n; ++i) {
            int oldv = m.getOrDefault(a[i], 0);
            update(m, a[i], 1);
            int newv = m.getOrDefault(a[i], 0);
            if (oldv > 0) {
                score -= pow(a[i], oldv);
            }
            score += pow(a[i], newv);
            score %= mod;
            if (score < 0) {
                score += mod;
            }
            res = Math.max(res, score);
            int head = i - k + 1;
            int oldheadv = m.getOrDefault(a[head], 0);
            update(m, a[head], -1);
            int newheadv = m.getOrDefault(a[head], 0);
            score -= pow(a[head], oldheadv);
            if (newheadv > 0) {
                score += pow(a[head], newheadv);
            }
            score %= mod;
            if (score < 0) {
                score += mod;
            }
        }
        return (int) res;
    }

    private long pow(long v, long exp) {

        if (exp == 0) {
            return 1;
        }
        long half = pow(v, exp / 2);
        long res = half * half;
        res %= mod;
        if (exp % 2 == 1) {
            res *= v;
        }
        res %= mod;
        return res;
    }
}
