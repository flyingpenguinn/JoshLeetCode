import java.util.Arrays;
import java.util.PriorityQueue;

public class MinIntervalToIncludeEachQuery {
    public int[] minInterval(int[][] intervals, int[] queries) {
        Arrays.sort(intervals, (x, y) -> x[0] != y[0] ? Integer.compare(x[0], y[0]) : Integer.compare(x[1], y[1]));
        int[][] nq = new int[queries.length][2];
        for (int i = 0; i < queries.length; i++) {
            nq[i][0] = queries[i];
            nq[i][1] = i;
        }
        Arrays.sort(nq, (x, y) -> Integer.compare(x[0], y[0]));
        int[] res = new int[queries.length];
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[0], y[0]));
        int j = 0;
        for (int i = 0; i < nq.length; i++) {
            while (j < intervals.length && intervals[j][0] <= nq[i][0]) {
                // put good ones into pq
                pq.offer(new int[]{intervals[j][1] - intervals[j][0] + 1, intervals[j][1]});
                j++;
            }
            while (!pq.isEmpty() && pq.peek()[1] < nq[i][0]) {
                // poll bad ones from the head. we may poll bad ones we just offered out of pq here
                pq.poll();
            }
            if (pq.isEmpty()) {
                res[nq[i][1]] = -1;
            } else {
                res[nq[i][1]] = pq.peek()[0];
            }
        }
        return res;
    }
}
