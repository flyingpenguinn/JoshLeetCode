public class AvgWaitingTime {
    public double averageWaitingTime(int[][] a) {
        int n = a.length;
        int ct = 0;
        double sum = 0;
        for (int i = 0; i < n; i++) {
            int start = Math.max(ct, a[i][0]);
            int end = start + a[i][1];
            int wait = end - a[i][0];
            sum += wait;
            ct = end;
        }
        return sum / n;
    }
}
