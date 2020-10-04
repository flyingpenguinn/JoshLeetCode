import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class FindServerHandlingMostRequests {
    // note this is n(logn + logk) complexity despite the pq poll operation. there are at most n items to poll for all the while loops
    // the argument is similar to #1487 making file names unique. when we add to a set/queue once a time, polling the best out of it is On amortized
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        // 0 is time, 1 is position
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[0], y[0]));
        int n = arrival.length;
        TreeSet<Integer> ts = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            ts.add(i);
        }
        int[] handle = new int[k];
        int maxhandle = 0;
        for (int i = 0; i < n; i++) {
            while (!pq.isEmpty() && pq.peek()[0] <= arrival[i]) {
                // this is done at most n times for the whole for loop outside since we put at most n items in it
                ts.add(pq.poll()[1]);
            }
            if (ts.isEmpty()) {
                continue;
            }
            Integer next = ts.ceiling(i % k);
            if (next == null) {
                next = ts.first();
            }
            ts.remove(next);
            handle[next]++;
            maxhandle = Math.max(maxhandle, handle[next]);
            pq.offer(new int[]{arrival[i] + load[i], next});
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (handle[i] == maxhandle) {
                res.add(i);
            }
        }
        return res;
    }
}
