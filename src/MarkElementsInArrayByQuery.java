import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class MarkElementsInArrayByQuery {
    public long[] unmarkedSumArray(int[] a, int[][] qs) {
        int n = a.length;
        long sum = 0;
        Set<Integer> marked = new HashSet<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> {
            if (x[0] != y[0]) {
                return Integer.compare(x[0], y[0]);
            } else {
                return Integer.compare(x[1], y[1]);
            }
        });
        for (int i = 0; i < n; ++i) {
            sum += a[i];
            pq.offer(new int[]{a[i], i});
        }
        long marksum = 0;
        long[] res = new long[qs.length];
        for (int i = 0; i < qs.length; ++i) {
            int[] q = qs[i];
            int index = q[0];
            int nums = q[1];
            if (!marked.contains(index)) {
                marked.add(index);
                marksum += a[index];
            }
            while (!pq.isEmpty() && nums > 0) {
                int[] top = pq.poll();
                int tnum = top[0];
                int tindex = top[1];
                if (marked.contains(tindex)) {
                    continue;
                }
                marksum += tnum;
                marked.add(tindex);
                --nums;
            }
            res[i] = sum - marksum;
        }
        return res;
    }
}
