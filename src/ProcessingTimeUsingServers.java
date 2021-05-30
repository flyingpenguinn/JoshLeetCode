import java.util.PriorityQueue;

public class ProcessingTimeUsingServers {
    // two pqs, one for available jobs one for finishing jobs.
    // these types of question usually follows current time while we process tasks
    public int[] assignTasks(int[] servers, int[] tasks) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> x[0] == y[0] ? Integer.compare(x[1], y[1]) : Integer.compare(x[0], y[0]));
        for (int i = 0; i < servers.length; i++) {
            pq.offer(new int[]{servers[i], i});
        }
        int[] res = new int[tasks.length];
        int j = 0;
        PriorityQueue<int[]> dq = new PriorityQueue<>((x, y) -> Integer.compare(x[2], y[2]));
        int i = 0;
        while (j < tasks.length) {
            while (!dq.isEmpty() && dq.peek()[2] <= i) {
                pq.offer(dq.poll());
            }
            if (pq.isEmpty()) {
                // this is key. fi we dont have available servers, move time to the next finishing job-
                // we will have nothing to do but doing continue anyway if this happens in a brute-force solution
                i = dq.peek()[2];
                // note after we move time there will be new jobs available
                while (!dq.isEmpty() && dq.peek()[2] <= i) {
                    pq.offer(dq.poll());
                }
            }
            while (!pq.isEmpty() && j <= i && j < tasks.length) {
                int[] top = pq.poll();
                res[j] = top[1];
                dq.offer(new int[]{top[0], top[1], i + tasks[j]});
                j++;
            }
            i++;
        }
        return res;
    }
}
