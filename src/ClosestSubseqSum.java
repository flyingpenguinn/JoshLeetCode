import java.util.*;

public class ClosestSubseqSum {
    // turn subset sum problem to splitting to two halves then binary search one half sum in the other!
    // O(2*n*2^(n/2)+n^(n/2)*n/2) = O(n*2^(n/2)
    public int minAbsDifference(int[] a, int goal) {
        int n = a.length;
        int mid = n / 2;
        int l1 = (1 << mid);
        int l2 = (1 << (n - mid));
        TreeSet<Integer> lows = new TreeSet<>();
        int min = Math.abs(goal);
        for (int i = 0; i < l1; i++) {
            int sum = 0;
            for (int j = 0; j < mid; j++) {
                if (((i >> j) & 1) == 1) {
                    sum += a[j];
                }
            }
            if (sum == goal) {
                return 0;
            }
            lows.add(sum);
            min = Math.min(min, Math.abs(sum - goal));
        }
        for (int i = 0; i < l2; i++) {
            int sum = 0;
            int cur = Math.abs(goal);
            for (int j = mid; j < n; j++) {
                if (((i >> (j - mid)) & 1) == 1) {
                    sum += a[j];
                }
            }

            if (sum == goal) {
                return 0;
            }
            cur = Math.min(Math.abs(sum - goal), cur);
            Integer key1 = lows.ceiling(goal - sum);
            Integer key2 = lows.floor(goal - sum);

            if (key1 != null) {
                cur = Math.min(cur, Math.abs(sum + key1 - goal));
            }
            if (key2 != null) {
                cur = Math.min(cur, Math.abs(sum + key2 - goal));
            }

            min = Math.min(min, cur);
        }
        return min;
    }
}
