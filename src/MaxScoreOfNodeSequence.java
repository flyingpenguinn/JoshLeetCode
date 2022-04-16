import base.ArrayUtils;

import java.util.*;

public class MaxScoreOfNodeSequence {

    public int maximumScore(int[] scores, int[][] edges) {
        Map<Integer, PriorityQueue<int[]>> g = new HashMap<>();

        for (int[] e : edges) {
            int v0 = e[0];
            int v1 = e[1];
            process( g, v0, v1, scores);
            process( g, v1, v0, scores);
        }
        int res = -1;
        for (int[] e : edges) {
            int v0 = e[0];
            int v1 = e[1];
            PriorityQueue<int[]> tm0 = new PriorityQueue<>(g.get(v0));

            List<int[]> cnd0 = new ArrayList<>();
            while (!tm0.isEmpty()) {
                cnd0.add(tm0.poll());
            }
            PriorityQueue<int[]> tm1 = new PriorityQueue<>(g.get(v1));
            List<int[]> cnd1 = new ArrayList<>();
            while (!tm1.isEmpty()) {
                cnd1.add(tm1.poll());
            }

            for (int i = 0; i < cnd0.size(); ++i) {
                for (int j = 0; j < cnd1.size(); ++j) {
                    if (cnd0.get(i)[1] == cnd1.get(j)[1]) {
                        continue;
                    }
                    if (cnd0.get(i)[1] == v1) {
                        continue;
                    }
                    if (cnd1.get(j)[1] == v0) {
                        continue;
                    }
                    int cur = scores[v0] + scores[v1] + cnd0.get(i)[0] + cnd1.get(j)[0];
                    res = Math.max(res, cur);
                }
            }
        }
        return res;
    }

    protected void process(Map<Integer, PriorityQueue<int[]>> g, int v0, int v1, int[] scores) {
        PriorityQueue<int[]> pq = g.getOrDefault(v0, new PriorityQueue<int[]>((x, y) -> Integer.compare(x[0], y[0])));
        pq.offer(new int[]{scores[v1], v1});
        if (pq.size() > 3) {
            pq.poll();
        }
        g.put(v0, pq);
    }





    public static void main(String[] args) {
        System.out.println(new MaxScoreOfNodeSequence().maximumScore(ArrayUtils.read1d("[18,6,4,9,8,2]"), ArrayUtils.read("[[0,1],[0,2],[0,3],[0,4],[0,5],[1,2],[1,3],[1,4],[1,5],[2,3],[2,4],[2,5],[3,4],[3,5],[4,5]]")));
    }
}
