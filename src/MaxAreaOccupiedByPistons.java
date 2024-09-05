import base.ArrayUtils;

import java.util.PriorityQueue;

public class MaxAreaOccupiedByPistons {
    public long maxArea(int h, int[] a, String dirs) {
        int n = a.length;
        char[] ds = dirs.toCharArray();
        int ups = 0;
        int downs = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[0], y[0]));
        int res = 0;
        for (int i = 0; i < n; ++i) {
            int cd = ds[i] == 'U' ? 1 : -1;
            if (cd > 0) {
                ++ups;
                pq.offer(new int[]{h - a[i] + 1, cd});
            } else {
                ++downs;
                pq.offer(new int[]{a[i] + 1, cd});
            }
            res += a[i];
        }
        // i = seconds
        int cur = res;
        for (int i = 1; i <= 2 * h; ++i) {
            while (!pq.isEmpty() && pq.peek()[0] <= i) {
                int cd = pq.peek()[1];
                pq.offer(new int[]{i + h, -cd});
                pq.poll();
                if (cd > 0) {
                    --ups;
                    ++downs;
                } else {
                    ++ups;
                    --downs;
                }
            }
            cur = cur + ups - downs;
            res = Math.max(res, cur);
        }
        return res;
    }

    public static void main(String[] args) {
        //System.out.println(new MaxAreaOccupiedByPistons().maxArea(5, ArrayUtils.read1d("[5,5]"), "UU"));
        //System.out.println(new MaxAreaOccupiedByPistons().maxArea(5, ArrayUtils.read1d("[2,5]"), "UD"));
        System.out.println(new MaxAreaOccupiedByPistons().maxArea(3, ArrayUtils.read1d("[0,1,1]"), "UDD"));
    }
}
