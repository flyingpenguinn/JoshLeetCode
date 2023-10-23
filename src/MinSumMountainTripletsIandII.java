import java.util.TreeMap;

public class MinSumMountainTripletsIandII {
    private int MAX = (int) 1e9;

    public int minimumSum(int[] a) {
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        int n = a.length;
        for (int i = n - 1; i >= 2; --i) {
            update(tm, a[i], 1);
        }
        int minleft = a[0];
        int res = MAX;
        for (int i = 1; i < n - 1; ++i) {
            Integer minright = tm.firstKey();
            if (minright != null && minright < a[i] && minleft < a[i]) {
                int cur = minleft + a[i] + minright;
                res = Math.min(res, cur);
            }
            update(tm, a[i + 1], -1);
            minleft = Math.min(minleft, a[i]);
        }
        return res >= MAX ? -1 : res;
    }

    private void update(TreeMap<Integer, Integer> tm, int k, int d) {
        int nv = tm.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            tm.remove(k);
        } else {
            tm.put(k, nv);
        }
    }
}
