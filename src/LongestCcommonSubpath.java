import java.util.HashSet;
import java.util.Set;

public class LongestCcommonSubpath {
    // passing rolling hash version but it may have collision
    long Mod = 1000000000007L;
    long Base = 10000001L;

    public int longestCommonSubpath(int n, int[][] paths) {
        int l = 1;
        int u = 100000;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (good(paths, n, mid)) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return u;
    }

    private boolean good(int[][] paths, int n, int k) {
        Set<Long> rolling = null;
        for (int i = 0; i < paths.length; i++) {
            Set<Long> nrolling = calc(paths[i], k, rolling);
            rolling = nrolling;
        }
        return !rolling.isEmpty();
    }

    private Set<Long> calc(int[] a, int k, Set<Long> rolling) {
        Set<Long> sa = new HashSet<>();
        int na = a.length;
        long sum = 0;
        long baseminus = 1;
        for (int i = 0; i < na; i++) {
            sum = sum * Base + a[i];
            sum %= Mod;
            int head = i - k + 1;
            if (head >= 0) {
                if (rolling == null || rolling.contains(sum)) {
                    sa.add(sum);
                }
                sum = (sum - baseminus * a[head]) % Mod;
                if (sum < 0) {
                    sum += Mod;
                }
            } else {
                baseminus *= Base;
                baseminus %= Mod;
            }
        }
        return sa;
    }
}
