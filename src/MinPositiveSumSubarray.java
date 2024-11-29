import java.util.List;
import java.util.TreeMap;

public class MinPositiveSumSubarray {
    public int minimumSumSubarray(List<Integer> a, int l, int r) {
        int n = a.size();
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        int[] sum = new int[n];
        update(tm, 0, 1);
        int Max = (int) 1e9;
        int res = Max;
        for (int i = 0; i < n; ++i) {
            // i-r+1... i-l+1-> i
            //   System.out.println(i+" "+tm);
            sum[i] = (i == 0 ? 0 : sum[i - 1]) + a.get(i);
            int head = i - r - 1;
            if (head >= -1) {
                int hv = (head == -1) ? 0 : sum[head];
                update(tm, hv, -1);
            }
            Integer best = tm.lowerKey(sum[i]);
            //  System.out.println(sum[i]+" "+best);
            if (i - l >= -1 && best != null) {
                int cur = sum[i] - best;
                res = Math.min(res, cur);
            }
            int tail = i - l + 1;
            if (tail >= 0) {
                update(tm, sum[tail], 1);
            }
        }
        return res >= Max ? -1 : res;
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
