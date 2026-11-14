import java.util.*;

public class MinPositiveSumSubarray {
    // 2026-11-14 daily redo: prefix sums + sliding window over prefix indices p in [i-r, i-l],
    // find the largest prefix < sum[i] (TreeMap floorKey). O(n log n); n <= 100 so O(n^2) also fine.
    // Off-by-one guard: compute the index first (rh / lh), then check index >= -1.
    private void update(Map<Integer, Integer> m, int k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }

    public int minimumSumSubarray(List<Integer> a, int l, int r) {
        int n = a.size();
        int[] sum = new int[n];
        sum[0] = a.get(0);
        for (int i = 1; i < n; ++i) {
            sum[i] = sum[i - 1] + a.get(i);
        }
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        int Max = (int) 1e9;
        int res = Max;
        for (int i = 0; i < n; ++i) {
            int rh = i - r - 1;
            if (rh >= -1) {
                update(tm, rh == -1 ? 0 : sum[rh], -1);
            }
            int lh = i - l;
            if (lh >= -1) {
                update(tm, lh == -1 ? 0 : sum[lh], 1);
            }
            if (!tm.isEmpty()) {
                int lookup = sum[i] - 1;
                Integer before = tm.floorKey(lookup);
                if (before != null) {
                    int cur = sum[i] - before;
                    res = Math.min(res, cur);
                }
            }
        }
        return res >= Max ? -1 : res;
    }

    public static void main(String[] args) {
        Random rd = new Random(5);
        int bad = 0;
        for (int t = 0; t < 3000; t++) {
            int n = 1 + rd.nextInt(8);
            List<Integer> a = new ArrayList<>();
            for (int i = 0; i < n; i++) a.add(rd.nextInt(11) - 5);
            int l = 1 + rd.nextInt(n), r = l + rd.nextInt(n - l + 1);
            int exp = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) { int s = 0; for (int j = i; j < n; j++) { s += a.get(j); int len = j - i + 1; if (len >= l && len <= r && s > 0) exp = Math.min(exp, s); } }
            if (exp == Integer.MAX_VALUE) exp = -1;
            if (exp != new MinPositiveSumSubarray().minimumSumSubarray(a, l, r)) bad++;
        }
        System.out.println("bad=" + bad);
    }
}
