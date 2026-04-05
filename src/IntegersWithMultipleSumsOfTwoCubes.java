import java.util.*;

public class IntegersWithMultipleSumsOfTwoCubes {
    private int Max = (int) 1e9;
    private int Min = -Max;
    private long Mod = (long) (1e9 + 7);

    private void update(Map<Integer, Integer> m, int k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }

    private static Map<Integer, Integer> m = new HashMap<>();
    private static List<Integer> l = new ArrayList<>();

    static {
        for (int a = 1; a <= 1000; ++a) {
            for (int b = a; b <= 1000; ++b) {
                int v1 = (int) Math.pow(a, 3);
                int v2 = (int) Math.pow(b, 3);
                int v = v1 + v2;
                m.put(v, m.getOrDefault(v, 0) + 1);
            }
        }
        for (int k : m.keySet()) {
            if (m.get(k) >= 2) {
                l.add(k);
            }
        }
        Collections.sort(l);
    }

    public List<Integer> findGoodIntegers(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < l.size(); ++i) {
            int v = l.get(i);
            if (v > n) {
                break;
            }
            res.add(v);
        }
        return res;
    }
}
