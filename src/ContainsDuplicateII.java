import java.util.HashMap;
import java.util.Map;

/*
LC#219
Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] and the absolute difference between i and j is at most k.

Example 1:

Input: nums = [1,2,3,1], k = 3
Output: true
Example 2:

Input: nums = [1,0,1,1], k = 1
Output: true
Example 3:

Input: nums = [1,2,3,1,2,3], k = 2
Output: false
 */
public class ContainsDuplicateII {
    //old ones are useless
    public boolean containsNearbyDuplicate(int[] a, int k) {
        Map<Integer, Integer> lm = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            Integer last = lm.get(a[i]);
            if (last != null && i - last <= k) {
                return true;
            }
            lm.put(a[i], i);
        }
        return false;
    }
}
