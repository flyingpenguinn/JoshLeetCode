import java.util.Comparator;
import java.util.PriorityQueue;

public class MinDamageDealtToBob {
    public long minDamage(int p, int[] d, int[] h) {
        int n = d.length;
        int[] timeToKill = new int[n];

        for (int i = 0; i < n; i++) {
            timeToKill[i] = (int) Math.ceil(h[i] * 1.0 / p);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return (b[0] * a[1]) - (a[0] * b[1]);
            }
        });

        for (int i = 0; i < n; i++) {
            pq.offer(new int[]{d[i], timeToKill[i]});
        }

        long totalDamage = 0;
        long curdamage = 0;
        for (int di : d) {
            curdamage += di;
        }

        while (!pq.isEmpty()) {
            int[] enemy = pq.poll();
            long timeToDefeat = enemy[1];
            totalDamage += curdamage * timeToDefeat;
            curdamage -= enemy[0];
        }

        return totalDamage;
    }
}
