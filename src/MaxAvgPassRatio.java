import java.util.PriorityQueue;

public class MaxAvgPassRatio {
    // whoever stands to get the most out of an increase
    private double diff(int[] x) {
        return x[2] * 1.0 / x[3] - x[0] * 1.0 / x[1];
    }

    public double maxAverageRatio(int[][] a, int es) {
        int n = a.length;
        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum += a[i][0] * 1.0 / a[i][1];
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> {
            double d1 = diff(x);
            double d2 = diff(y);
            return Double.compare(d2, d1);
        });
        for (int i = 0; i < n; i++) {
            int n1 = (a[i][0] + 1);
            int n2 = (a[i][1] + 1);
            int[] item = new int[]{a[i][0], a[i][1], n1, n2};
            pq.offer(item);
        }
        for (int i = 0; i < es; i++) {
            int[] top = pq.poll();

            sum -= top[0] * 1.0 / top[1];
            sum += top[2] * 1.0 / top[3];
            pq.offer(new int[]{top[2], top[3], top[2] + 1, top[3] + 1});
        }
        return sum / n;
    }
}
