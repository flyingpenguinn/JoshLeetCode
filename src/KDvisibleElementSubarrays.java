import java.util.HashSet;
import java.util.Set;

public class KDvisibleElementSubarrays {
    // can use rolling hash to make it n^2
    public int countDistinct(int[] a, int k, int p) {
        int n = a.length;
        Set<String> seen = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            StringBuilder sb = new StringBuilder();
            int cur = 0;
            for (int j = i; j < n; ++j) {
                if (sb.length() > 0) {
                    sb.append(",");
                }
                sb.append(a[j]);
                if (a[j] % p == 0) {
                    ++cur;
                }
                if (cur > k) {
                    break;
                }
                seen.add(sb.toString());
            }
        }
        return seen.size();
    }
}
