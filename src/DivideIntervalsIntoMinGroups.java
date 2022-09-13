import java.util.Arrays;
import java.util.PriorityQueue;

public class DivideIntervalsIntoMinGroups {
    public int minGroups(int[][] a) {
        int n = a.length;
        Arrays.sort(a, (x, y) -> x[0] != y[0] ? Integer.compare(x[0], y[0]) : Integer.compare(x[1], y[1]));
        // int, index
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[1], y[1]));
        int cur = 0;
        for (int i = 0; i < n; ++i) {
            if (pq.isEmpty()) {
                pq.offer(new int[]{a[i][0], a[i][1], cur++});
            } else {
                int[] top = pq.poll();
                //  System.out.println(Arrays.toString(top)+" "+Arrays.toString(a[i]));
                int ts = top[0];
                int te = top[1];
                int ti = top[2];
                if (te < a[i][0]) {
                    //    System.out.println("old group "+top[1]);
                    pq.offer(new int[]{a[i][0], a[i][1], ti});
                } else {
                    //     System.out.println("new group "+cur);
                    pq.offer(top);
                    pq.offer(new int[]{a[i][0], a[i][1], cur++});
                }
            }
        }
        return cur;
    }
}
