import java.util.PriorityQueue;

public class ProcessingTimeUsingServers {
    // two pqs, one for available jobs one for finishing jobs.
    // these types of question usually follows current time while we process tasks
    // similar to single threaded cpu. but there are 2 heaps here...
    public int[] assignTasks(int[] servers, int[] tasks) {
        PriorityQueue<int[]> avail = new PriorityQueue<>((x, y) -> x[0] == y[0] ? Integer.compare(x[1], y[1]) : Integer.compare(x[0], y[0]));
        for (int i = 0; i < servers.length; i++) {
            avail.offer(new int[]{servers[i], i});
        }
        int[] res = new int[tasks.length];
        PriorityQueue<int[]> busy = new PriorityQueue<>((x, y) -> Integer.compare(x[2], y[2]));
        int time = 0;
        int i = 0;
        while (i < tasks.length) {
            while (!busy.isEmpty() && busy.peek()[2] <= time) {
                avail.offer(busy.poll());
            }
            if (avail.isEmpty()) {
                // this is key. fi we dont have available servers, move time to the next finishing job-
                // we will have nothing to do but doing continue anyway if this happens in a brute-force solution
                time = busy.peek()[2];
                // note after we move time there will be new jobs available
            }else{
                while (!avail.isEmpty() && i <= time && i < tasks.length) {
                    int[] top = avail.poll();
                    res[i] = top[1];
                    busy.offer(new int[]{top[0], top[1], time + tasks[i]});
                    i++;
                }
                time++;
            }
        }
        return res;
    }
}
