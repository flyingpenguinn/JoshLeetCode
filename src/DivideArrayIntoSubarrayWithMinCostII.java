import java.util.TreeMap;

public class DivideArrayIntoSubarrayWithMinCostII {
    public long minimumCost(int[] ia, int k, int dist) {

        int n = ia.length;
        long[] a = new long[n];
        for (int i = 0; i < n; ++i) {
            a[i] = ia[i];
        }
        for (int i = 2; i <= dist + 1 && i < n; ++i) {
            insert(a[i], k - 2);
        }
        long res = Integer.MAX_VALUE;
        for (int i = 1; i <= n - k + 1; ++i) {
            long cur = 0;
            cur = 0L + a[0] + a[i] + tsum;
            res = Math.min(res, cur);
            if (i + 1 < n) {
                remove(a[i + 1]);
            }
            if (i + 1 + dist < n) {
                insert(a[i + 1 + dist], k - 2);
            }

            while (!tm2.isEmpty() && tm.size() < k - 2) {
                Long sm = tm2.firstKey();
                insert(sm, k - 2);
                update(sm, tm2);
            }

        }
        return res;
    }

    private TreeMap<Long, Integer> tm = new TreeMap<>();
    private TreeMap<Long, Integer> tm2 = new TreeMap<>();
    private long tsum = 0;
    private int tsize = 0;

    private void insert(Long k, int limit) {
        tsum += k;
        ++tsize;
        int cv = tm.getOrDefault(k, 0) + 1;
        tm.put(k, cv);
        if (tsize > limit) {
            Long bk = tm.lastKey();
            tsum -= bk;
            --tsize;
            int bcv = tm.getOrDefault(bk, 0) - 1;
            if (bcv <= 0) {
                tm.remove(bk);
            } else {
                tm.put(bk, bcv);
            }
            int cv2 = tm2.getOrDefault(bk, 0) + 1;
            tm2.put(bk, cv2);
        }

    }

    private boolean remove(Long k) {
        if (!tm.containsKey(k)) {
            return false;
        }
        tsum -= k;
        --tsize;
        update(k, tm);
        return true;
    }

    private void update(Long k, TreeMap<Long, Integer> tm2) {
        int cv2 = tm2.getOrDefault(k, 0) - 1;
        if (cv2 <= 0) {
            tm2.remove(k);
        } else {
            tm2.put(k, cv2);
        }
    }
}
