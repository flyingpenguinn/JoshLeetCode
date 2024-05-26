import java.util.HashMap;
import java.util.Map;

public class FindNumberOfDistinctColors {
    public int[] queryResults(int n, int[][] qs) {
        Map<Integer, Integer> b2c = new HashMap<>();
        Map<Integer, Integer> c2c = new HashMap<>();
        int qn = qs.length;
        int[] res = new int[qn];
        for (int i = 0; i < qn; ++i) {
            int[] q = qs[i];
            int b = q[0];
            int c = q[1];
            if (b2c.containsKey(b)) {
                int oldc = b2c.get(b);
                update(c2c, oldc, -1);
            }
            b2c.put(b, c);
            update(c2c, c, 1);
            //  System.out.println(i+" "+c2c);
            int cs = c2c.size();
            res[i] = cs;
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
