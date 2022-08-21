import java.util.TreeMap;
import java.util.TreeSet;

public class MaxSegSumAfterRemovals {
    public long[] maximumSegmentSum(int[] a, int[] rqs) {
        int n = a.length;
        long[] sum = new long[n];
        sum[0] = a[0];
        for (int i = 1; i < n; ++i) {
            sum[i] = sum[i - 1] + a[i];
        }

        TreeSet<Integer> list = new TreeSet<>();
        list.add(-1);
        list.add(n);
        long[] res = new long[rqs.length];
        TreeMap<Long, Integer> tm = new TreeMap<>();
        update(tm, sum[n - 1], 1);
        for (int i = 0; i < rqs.length; ++i) {
            int q = rqs[i];
            // first that is >=
            int end = list.ceiling(q);
            int start = list.lower(end);
            long removed = seg(sum, start + 1, end - 1);
            long seg1 = seg(sum, start + 1, q - 1);
            long seg2 = seg(sum, q + 1, end - 1);
            update(tm, removed, -1);
            update(tm, seg1, 1);
            update(tm, seg2, 1);
            res[i] = tm.lastKey();
            list.add(q);
        }
        return res;
    }

    private long seg(long[] seg, int l, int u) {
        if(l>u){
            return 0L;
        }
        return seg[u] - (l==0?0:seg[l-1]);
    }

    private void update(TreeMap<Long, Integer> tm, long k, int d) {
        int nv = tm.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            tm.remove(k);
        } else {
            tm.put(k, nv);
        }
    }
}
