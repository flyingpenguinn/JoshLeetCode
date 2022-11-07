import java.util.PriorityQueue;

public class TotalCostToHireKWorkers {
    public long totalCost(int[] costs, int k, int candidates) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> {
            if (x[0] != y[0]) {
                return Integer.compare(x[0], y[0]);
            } else {
                return Integer.compare(x[1], y[1]);
            }
        });
        int n = costs.length;
        // value, index, isleft
        int i = 0;
        for (; i < candidates; ++i) {
            pq.offer(new int[]{costs[i], i, 1});
        }

        int j = n - 1;
        for (; j >= i && j >= n - candidates; --j) {
            pq.offer(new int[]{costs[j], j, 0});
        }
        long res = 0;
        while (k > 0) {
            int[] top = pq.poll();
            res += top[0];
            //  System.out.println("getting "+top[0]);
            int index = top[1];
            if (top[2] == 1) {
                if (i <= j) {
                    int ni = i;
                    //  System.out.println("putting left "+ni+" "+costs[ni]);
                    pq.offer(new int[]{costs[ni], ni, 1});
                    ++i;
                }
            } else {
                if (j >= i) {
                    int nj = j;
                    //   System.out.println("putting right "+nj+" "+costs[nj]);
                    pq.offer(new int[]{costs[nj], nj, 0});
                    --j;
                }
            }
            --k;
        }
        return res;
    }
}
