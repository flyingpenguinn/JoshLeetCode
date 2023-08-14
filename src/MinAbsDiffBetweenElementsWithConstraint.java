import java.util.List;
import java.util.TreeSet;

public class MinAbsDiffBetweenElementsWithConstraint {
    public int minAbsoluteDifference(List<Integer> a, int k) {
        int n = a.size();
        TreeSet<Integer> ts = new TreeSet<>();
        int res = (int) 2e9;
        for (int i = k; i < n; ++i) {
            int v = a.get(i);
            int head = i - k;
            ts.add(a.get(head));
            Integer upper = ts.ceiling(v);
            if (upper != null) {
                res = Math.min(res, upper - v);
            }
            Integer lower = ts.floor(v);
            if (lower != null) {
                res = Math.min(res, v - lower);
            }
        }
        return res;
    }
}
