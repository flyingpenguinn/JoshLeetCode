import java.util.Collections;
import java.util.PriorityQueue;

public class TakeGitsFromRichestPile {
    public long pickGifts(int[] gifts, int k) {
        long sum = 0;
        PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int gi : gifts) {
            pq.offer(Long.valueOf(gi));
            sum += gi;
        }
        while (k > 0) {
            long top = pq.poll();
            sum -= top;
            long ret = (long) Math.sqrt(top);
            sum += ret;
            pq.offer(ret);
            --k;
        }
        return sum;
    }
}
