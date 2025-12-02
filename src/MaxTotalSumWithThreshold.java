import java.util.*;

public class MaxTotalSumWithThreshold {
    public long maxSum(int[] a, int[] t) {
        int n = a.length;
        PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
        List<long[]> all = new ArrayList<>();

        for (int i = 0; i < n; ++i) {
            all.add(new long[]{t[i], a[i]});
        }
        all.sort(Comparator.comparingLong(x -> x[0]));
        int j = 0;
        long res = 0;
        for (int step = 1; step <= n; ++step) {
            while (j < n && all.get(j)[0] <= step) {
                pq.offer(all.get(j)[1]);
                ++j;
            }
            if (pq.isEmpty()) {
                break;
            }
            res += pq.poll();
        }
        return res;
    }
}
