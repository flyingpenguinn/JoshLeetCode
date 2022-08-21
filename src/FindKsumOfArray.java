import base.ArrayUtils;

import java.util.Arrays;
import java.util.PriorityQueue;

public class FindKsumOfArray {
    // biggest is all pos together
    // next in line is either - smallest pos, or + biggest neg, basically - abs(smallest).
    // achieve subset sum by either retaining kth element, - k+1 th element, or do no contain kth element
    public long kSum(int[] a, int k) {
        int n = a.length;
        // max heap
        PriorityQueue<long[]> pq = new PriorityQueue<long[]>((x, y) -> Long.compare(y[0], x[0]));
        long cur = 0;
        long[] abs = new long[n];
        for (int i = 0; i < n; ++i) {
            if (a[i] > 0) {
                cur += a[i];
            }
            // if neg then + it if pos then - it, so we are - abs value anyway
            abs[i] = Math.abs(a[i]);
        }
        Arrays.sort(abs);
        pq.offer(new long[]{cur - abs[0], 0});
        while (--k > 0) {
            long[] top = pq.poll();
            long csum = top[0];
            cur = csum;
            int index = (int) top[1];
            if (index + 1 < n) {
                pq.offer(new long[]{csum + abs[index] - abs[index + 1], index + 1});
                pq.offer(new long[]{csum - abs[index + 1], index + 1});
            }
        }
        return cur;
    }

    public static void main(String[] args) {
        System.out.println(new FindKsumOfArray().kSum(ArrayUtils.read1d("2,3"), 2));
    }
}
