import java.util.*;

public class MinGroupsForValidAssignment {
    public int minGroupsForValidAssignment(int[] a) {
        int n = a.length;
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            m.put(a[i], m.getOrDefault(a[i], 0) + 1);
        }
        List<Integer> list = new ArrayList<>();
        for (int k : m.keySet()) {
            list.add(m.get(k));
        }
        Collections.sort(list);
        int ln = list.size();
        int b = list.get(0);
        for (int k = b; k >= 1; --k) {
            int cur = 0;
            boolean bad = false;
            for (int i = 0; i < ln; ++i) {
                int v = list.get(i);
                if (v == k) {
                    ++cur;
                } else if (v % (k + 1) == 0) {
                    cur += v / (k + 1);
                } else {
                    if (good1(v, k + 1)) {
                        cur += Math.ceil((1.0 * v) / (k + 1));
                    } else if (good2(v, k)) {
                        cur += Math.ceil((1.0 * v) / (k));
                    } else {
                        bad = true;
                    }
                }
            }
            if (!bad) {
                return cur;
            }
        }
        return -1;
    }

    private boolean good1(int v, int k) {
        int ceil = (int) Math.ceil(1.0 * v / (k));
        int ceilv = ceil * (k);
        int delta = ceilv - v;
        return delta <= ceil;
    }

    private boolean good2(int v, int k) {
        int floor = v / k;
        int floorv = floor * (k);
        int delta = v - floorv;
        return delta <= floor;
    }
}
