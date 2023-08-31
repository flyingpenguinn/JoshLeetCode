import java.util.HashSet;
import java.util.Set;

public class FindMinPossibleSum {
    public long minimumPossibleSum(int n, int target) {
        Set<Long> bad = new HashSet<>();
        long cur = 1;
        long res = 0;
        while (n > 0) {
            //   System.out.println(cur);
            if (bad.contains(cur)) {
                ++cur;
                continue;
            }
            res += cur;
            bad.add(target - cur);
            --n;
            ++cur;
        }
        return res;
    }
}
