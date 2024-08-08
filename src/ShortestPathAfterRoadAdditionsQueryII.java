import java.util.HashSet;
import java.util.Set;
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
        for (int[] q : qs) {
            int start = q[0];
            int end = q[1];
            Integer ss = set.ceiling(start + 1);
            Integer se = set.higher(end - 1);
            Integer next = ss;
            while (next != se) {
                set.remove(next);
                next = set.higher(next);
            }
            res[ri++] = set.size() - 1;
        }
        return res;
    }

}
