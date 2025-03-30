import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PropertiesGraph {
    private int[] pa;

    private void union(int i, int j) {
        int ai = find(i);
        int aj = find(j);
        if (ai == aj) {
            return;
        }
        pa[ai] = aj;
    }

    private int find(int i) {
        if (pa[i] == -1) {
            return i;
        }
        int rt = find(pa[i]);
        pa[i] = rt;
        return rt;
    }

    public int numberOfComponents(int[][] p, int k) {
        int n = p.length;
        pa = new int[n];
        Arrays.fill(pa, -1);
        for (int i = 0; i < n; ++i) {
            Set<Integer> si = new HashSet<>();
            for (int ki : p[i]) {
                si.add(ki);
            }
            for (int j = 0; j < n; ++j) {
                if (i == j) {
                    continue;
                }
                Set<Integer> inters = new HashSet<>();
                for (int kj : p[j]) {
                    if (si.contains(kj)) {
                        inters.add(kj);
                    }
                }
                if (inters.size() >= k) {
                    union(i, j);
                }
            }
        }
        int res = 0;
        for (int i = 0; i < n; ++i) {
            if (pa[i] == -1) {
                ++res;
            }
        }
        return res;
    }
}
