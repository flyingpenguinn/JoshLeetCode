import java.util.Collections;
import java.util.PriorityQueue;

public class MinOperationsToHalveArraySum {
    public int halveArray(int[] a) {
        PriorityQueue<Double> pq = new PriorityQueue<>(Collections.reverseOrder());
        double sum = 0;
        for (double ai : a) {
            pq.offer(ai);
            sum += ai;
        }
        double oldsum = sum;
        int times = 0;
        while (sum > oldsum / 2.0) {
            double top = pq.poll();
            pq.offer(top / 2.0);
            sum -= top / 2.0;
            ++times;
        }
        return times;
    }
}
