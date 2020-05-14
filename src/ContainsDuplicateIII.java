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
    public boolean containsNearbyAlmostDuplicate(int[] a, int k, long t) {
        TreeMap<Long, Integer> tm = new TreeMap<>();
        int n = a.length;
        for (int i = 0; i < n; i++) {
            // note can't use interval intersection here - 1,9,5: [1.9] intersects 2,6 but no number in the range
            Long floor = tm.floorKey(a[i] + t);
            if (floor != null && floor >= a[i] - t) {
                return true;
            }
            Long ceil = tm.ceilingKey(a[i] - t);
            if (ceil != null && ceil <= a[i] + t) {
                return true;
            }
            update(tm, a[i], 1);
            if (i - k >= 0) {
                update(tm, a[i - k], -1);
            }
        }
        return false;
    }

    void update(TreeMap<Long, Integer> tm, long k, int v) {
        int nv = tm.getOrDefault(k, 0) + v;
        if (nv == 0) {
            tm.remove(k);
        } else {
            tm.put(k, nv);
        }
    }
}
