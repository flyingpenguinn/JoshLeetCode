public class DistinctNumbersInSubarray {
    public int[] distinctNumbers(int[] a, int k) {
        int n = a.length;
        int[] res = new int[n - k + 1];
        int ri = 0;
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < n; i++) {
            update(m, a[i], 1);
            if (i - k + 1 >= 0) {
                res[ri++] = m.keySet().size();
                update(m, a[i - k + 1], -1);
            }
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
