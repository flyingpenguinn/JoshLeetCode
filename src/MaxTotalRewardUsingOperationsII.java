import java.util.*;

public class MaxTotalRewardUsingOperationsII {
    // must use the biggest
    // after picking the biggest, try others as close to last-1 as possible

    public int maxTotalReward(int[] a) {
        a = removeDups(a);
        final int n = a.length;
        return a[n - 1] + solve(a, a[n - 1] - 1);
    }

    private int solve(int[] a, int lim) {
        if (lim == 0) {
            return 0;
        }
        int ind = find(a, lim);
        if (ind == -1) {
            // == -1 means found limit
            return lim;
        }
        int res = 0;
        for (int i = 0; i < ind; i++) {
            res = Math.max(res, a[i] + solve(a, Math.min(lim - a[i], a[i] - 1)));
        }
        return res;
    }

    private int find(int[] a, int x) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (a[mid] == x) {
                return -1;
            } else if (a[mid] < x) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return lo;
    }

    private int[] removeDups(int[] a) {
        Set<Integer> set = new HashSet<>();
        for (int num : a) {
            set.add(num);
        }
        int[] res = new int[set.size()];
        int i = 0;
        for (int num : set) {
            res[i++] = num;
        }
        Arrays.sort(res);
        return res;
    }
}
