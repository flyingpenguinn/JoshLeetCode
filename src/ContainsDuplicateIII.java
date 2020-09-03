import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/*
LC#220
Given an array of integers, find out whether there are two distinct indices i and j in the array such that the absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k.

Example 1:

Input: nums = [1,2,3,1], k = 3, t = 0
Output: true
Example 2:

Input: nums = [1,0,1,1], k = 1, t = 2
Output: true
Example 3:

Input: nums = [1,5,9,1,5,9], k = 2, t = 3
Output: false
 */
public class ContainsDuplicateIII {
    public boolean containsNearbyAlmostDuplicate(int[] a, int k, int t) {
        int n = a.length;
        if (n < 2 || k < 0 || t < 0) {
            return false;
        }
        TreeMap<Long, Integer> tm = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            Long v1 = tm.ceilingKey(0L + a[i] - t);
            if (v1 != null && v1 <= (0L + a[i] + t)) {
                return true;
            }
            update(tm, a[i], 1);
            int head = i - k; // not i-k+1! k diff
            if (head >= 0) {
                update(tm, a[head], -1);
            }
        }
        return false;
    }

    private void update(TreeMap<Long, Integer> m, long k, int delta) {
        int nv = m.getOrDefault(k, 0) + delta;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }
}
