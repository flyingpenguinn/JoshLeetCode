import java.util.*;

public class AggTwoTimeSeries {
    public List<List<Integer>> aggregateTimeSeries(int[][] s1, int[][] s2) {
        TreeMap<Integer, Integer> m1 = new TreeMap<>();
        TreeMap<Integer, Integer> m2 = new TreeMap<>();
        int n1 = s1.length;
        Set<Integer> keys = new HashSet<>();
        for (int[] si1 : s1) {
            int t = si1[0];
            int v = si1[1];
            m1.put(t, v);
            keys.add(t);
        }

        for (int[] si2 : s2) {
            int t = si2[0];
            int v = si2[1];
            m2.put(t, v);
            keys.add(t);
        }

        List<List<Integer>> res = new ArrayList<>();
        for (int k : keys) {
            int v1 = 0;
            if (m1.containsKey(k)) {
                v1 = m1.get(k);
            } else {
                Integer v1k = m1.higherKey(k);
                if (v1k != null) {
                    v1 = m1.get(v1k);
                }
            }

            int v2 = 0;
            if (m2.containsKey(k)) {
                v2 = m2.get(k);
            } else {
                Integer v2k = m2.higherKey(k);
                if (v2k != null) {
                    v2 = m2.get(v2k);
                }
            }
            int cd = v1 + v2;
            res.add(List.of(k, cd));
        }
        Collections.sort(res, (x, y) -> Integer.compare(x.get(0), y.get(0)));
        return res;
    }
}
