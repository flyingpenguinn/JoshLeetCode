import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CountConnectedComponentsInLcmGraph {
    // use virtual node to bridge elements!
    private int[] pa;
    private int[] size;

    private int find(int code) {
        if (pa[code] == -1) {
            return code;
        }
        int rt = find(pa[code]);
        pa[code] = rt;
        return rt;
    }

    private boolean union(int c1, int c2) {
        int p1 = find(c1);
        int p2 = find(c2);
        if (p1 == p2) {
            return false;
        }
        if (size[p2] > size[p1]) {
            pa[p1] = p2;
            size[p2] += size[p1];
        } else {
            pa[p2] = p1;
            size[p1] += size[p2];
        }
        return true;
    }

    public int countComponents(int[] a, int t) {
        int n = a.length;
        pa = new int[t + 1];
        Arrays.fill(pa, -1);
        size = new int[t + 1];
        int res = 0;
        for (int ai : a) {
            if (ai > t) {
                ++res;
                continue;
            }
            for (int j = ai * 2; j <= t; j += ai) {
                union(ai, j);
            }
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            if (a[i] > t) {
                continue;
            }
            int aa = find(a[i]);
            set.add(aa);
        }
        return set.size() + res;
    }
}
