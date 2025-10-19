import java.util.HashSet;
import java.util.Set;

public class LongestBalancedSubarrayI {
    public int longestBalanced(int[] a) {
        int n = a.length;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            Set<Integer> evens = new HashSet<>();
            Set<Integer> odds = new HashSet<>();
            for (int j = i; j < n; ++j) {
                if (a[j] % 2 == 0) {
                    evens.add(a[j]);
                } else {
                    odds.add(a[j]);
                }
                if (evens.size() == odds.size()) {
                    int len = j - i + 1;
                    res = Math.max(res, len);
                }
            }

        }
        return res;
    }
}
