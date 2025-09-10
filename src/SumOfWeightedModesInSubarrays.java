import base.ArrayUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class SumOfWeightedModesInSubarrays {
    TreeMap<Integer, TreeSet<Integer>> tm = new TreeMap<>();
    Map<Integer, Integer> m = new HashMap<>();

    public long modeWeight(int[] a, int k) {
        int n = a.length;

        long res = 0;
        for (int i = 0; i < n; ++i) {
            int v = a[i];
            adj(v, 1);
            int head = i - k + 1;
            if (head >= 0) {
                long ele = tm.lastEntry().getValue().iterator().next();
                long freq = tm.lastKey();
                res += ele * freq;
                int hv = a[head];
                adj(hv, -1);
            }
        }
        return res;
    }

    protected void adj(int v, int d) {
        int of = m.getOrDefault(v, 0);
        int nf = of + d;
        m.put(v, nf);
        tm.computeIfAbsent(of, p -> new TreeSet<>()).remove(v);
        if (tm.getOrDefault(of, new TreeSet<>()).isEmpty()) {
            tm.remove(of);
        }
        tm.computeIfAbsent(nf, p -> new TreeSet<>()).add(v);
    }

    public static void main(String[] args) {
        System.out.println(new SumOfWeightedModesInSubarrays().modeWeight(ArrayUtils.read1d("1,2,1,2"), 2));
    }
}
