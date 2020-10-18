import java.util.*;

public class GraphConnectivityWithThreashold {
    // similar to #952: for each divisor setup a representative and all other numbers connect to this guy


    // i%j==0 and we now try to connect i with others
    private void process(Map<Integer, Integer> m, int i, int j, int[] pa, int threshold) {
        if (!m.containsKey(j)) {
            m.put(j, i);
        } else {
            int rep = m.get(j);
            if (j > threshold) {
                union(pa, i, rep);
            }
        }
    }

    public List<Boolean> areConnected(int n, int threshold, int[][] queries) {
        int[] pa = new int[n + 1];
        Arrays.fill(pa, -1);
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                if (i % j == 0) {
                    process(m, i, j, pa, threshold);
                    process(m, i, i / j, pa, threshold);
                }
            }
        }
        // System.out.println(m);
        // System.out.println(Arrays.toString(pa));
        List<Boolean> res = new ArrayList<>();
        for (int[] q : queries) {
            int pr1 = find(pa, q[0]);
            int pr2 = find(pa, q[1]);
            if (pr1 == pr2) {
                res.add(true);
            } else {
                res.add(false);
            }
        }
        return res;
    }

    private int find(int[] pa, int i) {
        if (pa[i] == -1) {
            return i;
        }
        int rt = find(pa, pa[i]);
        pa[i] = rt;
        return rt;
    }

    private void union(int[] pa, int i, int j) {
        int pri = find(pa, i);
        int prj = find(pa, j);
        if (pri != prj) {
            pa[pri] = prj;
        }
    }
}
