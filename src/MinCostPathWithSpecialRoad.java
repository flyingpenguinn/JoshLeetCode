import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class MinCostPathWithSpecialRoad {
    // for each top in the queue, check the dist to target and all special roads
    private long OFF = (long) 1e6;
    private int MAX = (int) 2e9;
    private Map<Long, Integer> dist = new HashMap<>();

    private long code(int x, int y) {
        return OFF * x + y;
    }

    private int getdist(int x, int y) {
        long codehere = code(x, y);
        return dist.getOrDefault(codehere, MAX);
    }

    private void setdist(int x, int y, int d) {
        long codehere = code(x, y);
        dist.put(codehere, d);
    }

    private int rawdist(int x, int y, int xo, int yo) {
        return Math.abs(x - xo) + Math.abs(y - yo);
    }

    public int minimumCost(int[] start, int[] target, int[][] specialRoads) {
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((x, y) -> Integer.compare(x[2], y[2]));
        pq.offer(new int[]{start[0], start[1], 0});
        int tx = target[0];
        int ty = target[1];
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int x = top[0];
            int y = top[1];
            int cd = top[2];
            //     System.out.println(Arrays.toString(top));
            if (x == target[0] && y == target[1]) {
                return cd;
            }
            int dt = rawdist(x, y, tx, ty);
            if (getdist(tx, ty) > cd + dt) {
                setdist(tx, ty, cd + dt);
                pq.offer(new int[]{tx, ty, cd + dt});
            }

            for (int[] sp : specialRoads) {
                int x1 = sp[0];
                int y1 = sp[1];
                int x2 = sp[2];
                int y2 = sp[3];
                if (x1 == x && y1 == y) {
                    int d = sp[4];
                    int curdistne = getdist(x2, y2);
                    if (curdistne > cd + d) {

                        setdist(x2, y2, cd + d);
                        pq.offer(new int[]{x2, y2, cd + d});
                    }
                } else {
                    int d = rawdist(x, y, x1, y1);
                    int curdistne = getdist(x1, y1);
                    if (curdistne > cd + d) {

                        setdist(x1, y1, cd + d);
                        pq.offer(new int[]{x1, y1, cd + d});
                    }
                }
            }
        }
        return -1;
    }
}
