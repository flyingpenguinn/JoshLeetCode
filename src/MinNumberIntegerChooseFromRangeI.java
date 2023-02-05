import java.util.HashSet;
import java.util.Set;

public class MinNumberIntegerChooseFromRangeI {
    public int maxCount(int[] banned, int n, int maxSum) {
        Set<Integer> bset = new HashSet<>();
        for (int b : banned) {
            bset.add(b);
        }
        int csum = 0;
        int res = 0;
        for (int i = 1; i <= n; ++i) {
            if (!bset.contains(i) && csum + i <= maxSum) {
                ++res;
                csum += i;
            }
        }
        return res;
    }
}
