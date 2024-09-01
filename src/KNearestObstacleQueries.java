import java.util.PriorityQueue;

public class KNearestObstacleQueries {
    public int[] resultsArray(int[][] qs, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int qn = qs.length;
        int[] res = new int[qn];

        for (int i = 0; i < qs.length; i++) {
            int d = Math.abs(qs[i][0]) + Math.abs(qs[i][1]);
            pq.offer(d);
            if (pq.size() > k) {
                pq.poll();
            }
            res[i] = pq.size() == k ? pq.peek() : -1;
        }

        return res;
    }
}
