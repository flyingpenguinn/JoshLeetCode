import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class MaxAmountAfterTwoDays {
    private Map<String, Map<String, Double>> t1 = new HashMap<>();
    private Map<String, Map<String, Double>> t2 = new HashMap<>();
    private Map<String, Double> d1 = new HashMap<>();
    private Map<String, Double> d2 = new HashMap<>();

    private void dfs(Map<String, Map<String, Double>> t, String c, String p, double cur, Map<String, Double> d) {
        d.put(c, cur);
        for (String ne : t.getOrDefault(c, new HashMap<>()).keySet()) {
            if (ne.equals(p)) {
                continue;
            }
            double rate = t.get(c).get(ne);
            //   System.out.println(c+"->"+ne+" rate="+rate);
            dfs(t, ne, c, cur * rate, d);
        }
    }

    public double maxAmount(String ic, List<List<String>> p1, double[] r1, List<List<String>> p2, double[] r2) {
        int n = p1.size();
        for (int i = 0; i < n; ++i) {
            List<String> pi = p1.get(i);
            String c1 = pi.get(0);
            String c2 = pi.get(1);
            double rate = r1[i];
            t1.computeIfAbsent(c1, k -> new HashMap<>()).put(c2, rate);
            t1.computeIfAbsent(c2, k -> new HashMap<>()).put(c1, 1.0 / rate);
        }
        int m = p2.size();
        for (int i = 0; i < m; ++i) {
            List<String> pi = p2.get(i);
            String c1 = pi.get(0);
            String c2 = pi.get(1);
            double rate = r2[i];
            t2.computeIfAbsent(c1, k -> new HashMap<>()).put(c2, rate);
            t2.computeIfAbsent(c2, k -> new HashMap<>()).put(c1, 1.0 / rate);
        }
        dfs(t1, ic, "", 1.0, d1);
        dfs(t2, ic, "", 1.0, d2);
        double res = 1.0;
        for (String k1 : d1.keySet()) {
            if (d2.containsKey(k1)) {
                double cr1 = d1.get(k1);
                double cr2 = d2.get(k1);
                double cres = cr1 * 1.0 / cr2;
                res = Math.max(res, cres);
            }
        }
        return res;
    }
}
