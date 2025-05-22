import base.ArrayUtils;

import java.util.*;

public class ZeroArrayTransformationIII {
    // this is actually same as tap water question. smallest num of intervals cover a segment
    public int maxRemoval(int[] a, int[][] qs) {
        int n = a.length;
        List<Integer>[] starts = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            starts[i] = new ArrayList<>();
        }
        for (int[] q : qs) {
            int s = q[0];
            int e = q[1];
            starts[s].add(e);
        }
        PriorityQueue<Integer> ends = new PriorityQueue<>(Collections.reverseOrder());
        int csum = 0; // how much we can cover now
        int[] diff = new int[n + 1];
        int needed = 0;
        for (int i = 0; i < n; ++i) {
            if (!ends.isEmpty() && ends.peek() < i) {
                ends.clear();
            }
            for (int end : starts[i]) {
                ends.offer(end);
            }
            csum += diff[i];
            // System.out.println("csum="+csum+" diffi="+diff[i]);
            while (csum < a[i] && !ends.isEmpty() && ends.peek() >= i) {
                ++needed;
                int cand = ends.poll();
                diff[cand + 1] += -1;
                ++csum;
            }
            if (csum < a[i]) {
                return -1;
            }
        }
        return qs.length - needed;
    }

    public static void main(String[] args) {
        System.out.println(new ZeroArrayTransformationIII().maxRemoval(ArrayUtils.read1d("0,0,3"), ArrayUtils.read("[[0,2],[1,1],[0,0],[0,0]]")));
    }
}
