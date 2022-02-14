import base.ArrayUtils;

import java.util.HashMap;
import java.util.Map;

public class MaxAndSumOfArray {
    // three state dp
    private Map<Long, Map<Integer, Integer>> dp = new HashMap<>();

    public int maximumANDSum(int[] a, int k) {
        long mask = 0;
        for (int i = 0; i < k; ++i) {
            mask = mask * 10 + 2;
        }
        return solve(a, mask, 0);
    }

    private int solve(int[] a, long mask, int i) {
        // put ith into a good slot then go to the next
        int n = a.length;
        if (i == n) {
            return 0;
        }
        Map<Integer, Integer> cm = dp.getOrDefault(mask, new HashMap<>());
        if (cm.containsKey(i)) {
            return cm.get(i);
        }
        int slotmask = 1;
        int slot = 1;
        int max = 0;
        long oldmask = mask;
        while (mask > 0) {
            int cur = (int) (mask % 10);
            if (cur > 0) {
                // we can still place number at this place = slot
                int cr = (a[i] & slot) + solve(a, oldmask - slotmask, i + 1);
                max = Math.max(max, cr);
            }
            slotmask *=10;
            mask /= 10;
            ++slot;
        }
        cm.put(i, max);
        dp.put(oldmask, cm);
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new MaxAndSumOfArray().maximumANDSum(ArrayUtils.read1d("[14,7,9,8,2,4,11,1,9]"), 8));
    }
}
