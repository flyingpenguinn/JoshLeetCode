import java.util.Arrays;
import java.util.PriorityQueue;

public class SingleThreadedCpu {
    public int[] getOrder(int[][] a) {
        int n = a.length;
        // enqueue time, processing time and index
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> {
            if (x[1] != y[1]) {
                return Integer.compare(x[1], y[1]);
            } else {
                return Integer.compare(x[2], y[2]);
            }
        });
        int[][] ts = new int[n][3];
        for (int i = 0; i < n; i++) {
            ts[i][0] = a[i][0];
            ts[i][1] = a[i][1];
            ts[i][2] = i;
        }
        Arrays.sort(ts, (x, y) -> Integer.compare(x[0], y[0]));
        int[] res = new int[n];
        int ri = 0;
        int i = 0;
        int cpu = 0;
        while (true) {
            // we are at time = cpu, so all good tasks can come in
            while (i < n && ts[i][0] <= cpu) {
                pq.offer(ts[i]);
                i++;
            }
            if (!pq.isEmpty()) {
                int[] top = pq.poll();
                cpu += top[1];
                res[ri++] = top[2];
            } else {
                if (i == n) {
                    break;
                } else {
                    // move time to the start of the first task so that we can start working in the next round
                    cpu = ts[i][0];
                }
            }
        }
        return res;
    }
}
