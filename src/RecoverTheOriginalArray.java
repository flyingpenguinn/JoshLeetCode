import java.util.*;

public class RecoverTheOriginalArray {
    // any recover array problem: try to find what's known for sure and go from there
    // here we loop through j to see who can pair with aa[0], and verify that
    // because "any solution" can work, we can sort
    private Map<Integer, Integer> m = new HashMap<>();

    public int[] recoverArray(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        for (int i = 0; i < n; ++i) {
            update(m, a[i], 1);
        }
        int first = a[0];
        for (int j = 1; j < n; ++j) {
            if ((first + a[j]) % 2 == 0) {
                int mid = (first + a[j]) / 2;
                int k = mid - a[0];
                if (k == 0) {
                    continue;
                }
                Map<Integer, Integer> cm = new HashMap<>(m);
                List<Integer> res = new ArrayList<>();
                boolean good = true;
                for (int i = 0; i < n; ++i) {
                    if (!cm.containsKey(a[i])) {
                        continue;
                    }
                    int t = a[i] + 2 * k;
                    if (!cm.containsKey(t)) {
                        good = false;
                        break;
                    }
                    res.add(a[i] + k);
                    update(cm, a[i], -1);
                    update(cm, t, -1);
                }
                if (good) {
                    int[] rr = new int[res.size()];
                    for (int i = 0; i < res.size(); ++i) {
                        rr[i] = res.get(i);
                    }
                    return rr;
                }
            }
        }
        return null;
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
