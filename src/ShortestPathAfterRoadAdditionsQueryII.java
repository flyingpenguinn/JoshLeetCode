import base.ArrayUtils;

import java.util.Arrays;
import java.util.TreeSet;

public class ShortestPathAfterRoadAdditionsQueryII {
    public int[] shortestDistanceAfterQueries(int n, int[][] qs) {
        // adding edge i -> j is the same as removing i+1... j-1. dist = node number -1
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < n; ++i) {
            set.add(i);
        }
        int[] res = new int[qs.length];
        int ri = 0;
        for (int[] qi : qs) {
            int start = qi[0];
            int end = qi[1];
            int cur = start + 1;
            while (true) {
                Integer next = set.ceiling(cur);
                if (next == null || next >= end) {
                    break;
                }
                set.remove(next);

            }
            res[ri++] = set.size() - 1;
        }
        return res;
    }

    static void main() {
        System.out.println(Arrays.toString(new ShortestPathAfterRoadAdditionsQueryII().shortestDistanceAfterQueries(4, ArrayUtils.read("[[0,3]]"))));
        System.out.println(Arrays.toString(new ShortestPathAfterRoadAdditionsQueryII().shortestDistanceAfterQueries(5, ArrayUtils.read("[[2,4],[0,2],[0,4]]"))));
    }

}
