import base.ArrayUtils;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MinTimeToTransportAllIndividuals {
    static class Item {
        int st;
        int stage;
        double timetaken;
        int l2r;

        public Item(int st, int stage, double timetaken, int l2r) {
            this.st = st;
            this.stage = stage;
            this.timetaken = timetaken;
            this.l2r = l2r;
        }
    }

    public double minTime(int n, int k, int m, int[] time, double[] mul) {
        PriorityQueue<Item> pq = new PriorityQueue<Item>((x, y) -> Double.compare(x.timetaken, y.timetaken));
        pq.offer(new Item(((1 << n) - 1), 0, 0.0, 1));
        double[][][] dist = new double[(1 << n)][m][2];
        for (double[][] doubles : dist) {
            for (int j = 0; j < m; ++j) {
                Arrays.fill(doubles[j], Double.MAX_VALUE);
            }
        }
        while (!pq.isEmpty()) {
            Item top = pq.poll();
            int st = top.st;
            int stage = top.stage;
            double timetaken = top.timetaken;
            if (st == 0) {
                return timetaken;
            }
            int l2r = top.l2r;
            int newst = 0;
            int newstage = 0;
            int newl2r = 0;
            double newtimetaken = 0;
            int startingst = 0;
            if (l2r == 1) {
                startingst = st;
            }
            if (l2r == 0) {
                startingst = (1 << n) - 1 - st;
            }
            for (int gost = startingst; gost > 0; gost = (gost - 1) & startingst) {
                if (Integer.bitCount(gost) > k) {
                    continue;
                }
                if(l2r == 0 && Integer.bitCount(gost) > 1){
                    continue;
                }
                int maxtime = 0;
                for (int i = 0; i < n; ++i) {
                    if (((gost >> i) & 1) == 1) {
                        maxtime = Math.max(maxtime, time[i]);
                    }
                }
                double cmulti = mul[stage];
                final double spent = maxtime * cmulti;
                newtimetaken = spent + timetaken;
                newstage = (int) ((stage + Math.floor(spent)) % m);
                if (l2r == 1) {
                    newst = st - gost;
                } else {
                    newst = st + gost;
                }
                newl2r = l2r ^ 1;

                if (dist[newst][newstage][newl2r] > newtimetaken) {
                    dist[newst][newstage][newl2r] = newtimetaken;
                    pq.offer(new Item(newst, newstage, newtimetaken, newl2r));
                }
            }
        }
        return -1.0;
    }

    public static void main(String[] args) {
        MinTimeToTransportAllIndividuals sol = new MinTimeToTransportAllIndividuals();
          System.out.println(sol.minTime(1, 1, 2, ArrayUtils.read1d("[5]"), new double[]{1.0, 1.3}));
     //   System.out.println(sol.minTime(2,3,4, ArrayUtils.read1d("[40,1]"), new double[]{1.82,1.59,1.11,1.84}));
        //  System.out.println(sol.minTime(3, 2, 3, ArrayUtils.read1d("[2,5,8]"), new double[]{1.0, 1.5, 0.75}));
    }
}
