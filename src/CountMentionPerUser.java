import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class CountMentionPerUser {


    public int[] countMentions(int n, List<List<String>> events) {
        int[] res = new int[n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[1], y[1]));
        Collections.sort(events, (x, y) -> {
            String typex = x.get(0);
            String typey = y.get(0);
            int tx = Integer.valueOf(x.get(1));
            int ty = Integer.valueOf(y.get(1));

            if (tx == ty) {
                if ("OFFLINE".equals(typex)) {
                    return -1;
                } else if ("OFFLINE".equals(typey)) {
                    return 1;
                } else {
                    return 0;
                }
            } else {
                return Long.compare(tx, ty);
            }
        });
        int[] status = new int[n];
        Arrays.fill(status, 1);
        for (List<String> e : events) {
            String type = e.get(0);
            int time = Integer.valueOf(e.get(1));
            while (!pq.isEmpty() && pq.peek()[1] <= time) {
                int[] top = pq.poll();
                int idint = top[0];
                status[idint] = 1;
            }
            if ("MESSAGE".equals(type)) {
                String mentioned = e.get(2);
                if ("ALL".equals(mentioned)) {
                    for (int i = 0; i < n; ++i) {
                        ++res[i];
                    }
                } else if ("HERE".equals(mentioned)) {
                    for (int i = 0; i < n; ++i) {
                        if (status[i] == 0) {
                            continue;
                        }
                        ++res[i];
                    }
                } else {
                    String[] ids = mentioned.split(" ");
                    for (String id : ids) {
                        int idint = Integer.valueOf(id.substring(2));
                        ++res[idint];
                    }
                }
            } else {
                int idint = Integer.valueOf(e.get(2));
                pq.offer(new int[]{idint, time + 60});
                status[idint] = 0;
            }
        }
        return res;
    }
}
