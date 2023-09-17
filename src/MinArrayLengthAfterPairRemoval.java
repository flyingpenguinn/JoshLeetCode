import java.util.List;

public class MinArrayLengthAfterPairRemoval {
    // can also use pq to remove the most frequent elements each time
    public int minLengthAfterRemovals(List<Integer> a) {
        int i = 0;
        int n = a.size();
        int res = 0;
        while (i < n) {
            int j = i;
            while (j < n && a.get(j).equals(a.get(i))) {
                ++j;
            }
            int count = j - i;
            res = Math.max(res, count);
            i = j;
        }
        int other = n - res;
        int rem = res - other;
        if (n % 2 == 1) {
            return Math.max(rem, 1);
        } else {
            return Math.max(rem, 0);
        }
    }
}
