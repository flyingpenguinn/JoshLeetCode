import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class ChooseKElementsWithMaxSum {
    public long[] findMaxSum(int[] a, int[] b, int k) {
        int n = a.length;
        List<long[]> list = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            list.add(new long[]{a[i], b[i], i});
        }
        Collections.sort(list, (x, y) -> Long.compare(x[0], y[0]));
        int j = 0;
        PriorityQueue<Long> pq = new PriorityQueue<>();
        long csum = 0;
        long[] res = new long[n];
        for (int i = 0; i < n; ++i) {
            int index = (int) list.get(i)[2];
            while (j < i && list.get(j)[0] < list.get(i)[0]) {
                csum += list.get(j)[1];
                pq.offer(list.get(j)[1]);
                ++j;
                if (pq.size() > k) {
                    csum -= pq.poll();
                }
            }
            res[index] = csum;

        }

        return res;
    }
}
