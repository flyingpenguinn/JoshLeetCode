import java.util.Arrays;
import java.util.PriorityQueue;

public class CampusBikes {

    // if we use bucket sort it's even faster as the range of dist is way smaller than the number of pairs itself
    class Pair implements Comparable<Pair> {
        int bike;
        int worker;
        int dist;

        public Pair(int bike, int worker, int dist) {
            this.bike = bike;
            this.worker = worker;
            this.dist = dist;
        }


        @Override
        public int compareTo(Pair o) {
            if (this.dist != o.dist) {
                return Integer.compare(this.dist, o.dist);
            } else if (this.worker != o.worker) {
                return Integer.compare(this.worker, o.worker);
            } else {
                return Integer.compare(this.bike, o.bike);
            }
        }
    }

    public int[] assignBikes(int[][] workers, int[][] bikes) {
        int[] r = new int[workers.length];
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for (int b = 0; b < bikes.length; b++) {
            for (int w = 0; w < workers.length; w++) {
                int dist = Math.abs(bikes[b][0] - workers[w][0]) + Math.abs(bikes[b][1] - workers[w][1]);
                Pair p = new Pair(b, w, dist);
                pq.offer(p);
            }
        }
        int set = 0;
        boolean[] usedbikes = new boolean[bikes.length];
        Arrays.fill(r, -1);
        while (set < r.length) {
            Pair top = pq.poll();
            int w = top.worker;
            int b = top.bike;
            if (r[w] != -1 || usedbikes[b]) { //ignore workers already allocated
                continue;
            }
            r[w] = b;
            usedbikes[b] = true;
            set++;
        }
        return r;
    }
}
