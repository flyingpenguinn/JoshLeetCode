import java.util.TreeMap;

/*
Given an array of integers nums and an integer limit, return the size of the longest continuous subarray such that the absolute difference between any two elements is less than or equal to limit.

In case there is no subarray satisfying the given condition return 0.



Example 1:

Input: nums = [8,2,4,7], limit = 4
Output: 2
Explanation: All subarrays are:
[8] with maximum absolute diff |8-8| = 0 <= 4.
[8,2] with maximum absolute diff |8-2| = 6 > 4.
[8,2,4] with maximum absolute diff |8-2| = 6 > 4.
[8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
[2] with maximum absolute diff |2-2| = 0 <= 4.
[2,4] with maximum absolute diff |2-4| = 2 <= 4.
[2,4,7] with maximum absolute diff |2-7| = 5 > 4.
[4] with maximum absolute diff |4-4| = 0 <= 4.
[4,7] with maximum absolute diff |4-7| = 3 <= 4.
[7] with maximum absolute diff |7-7| = 0 <= 4.
Therefore, the size of the longest subarray is 2.
Example 2:

Input: nums = [10,1,2,4,7,2], limit = 5
Output: 4
Explanation: The subarray [2,4,7,2] is the longest since the maximum absolute diff is |2-7| = 5 <= 5.
Example 3:

Input: nums = [4,2,2,2,4,4,2,2], limit = 0
Output: 3
 */
public class LongestContinuousSubarrayWithAbsDiffLessThank {
    // find min/max whose diff is within limit. if it reaches a point where it's >, start shrinking until it's fit again
    // we can do this because once it's no longer good, all afterward is no good. this is similar in nature as at most k different chars
    public int longestSubarray(int[] a, int limit) {
        int n = a.length;
        int max = 1;
        int high = 0;
        int low = 0;
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        update(tm, a[0], 1);
        while (true) {
            int diff = tm.lastKey() - tm.firstKey(); // length at least 1 because limit>=0 so we dont need null check here
            if (diff <= limit) {
                max = Math.max(max, high - low + 1);
                high++;
                if (high == n) {
                    break;
                }
                update(tm, a[high], 1);
            } else {
                update(tm, a[low], -1);
                low++;
            }
        }
        return max;
    }

    private void update(TreeMap<Integer, Integer> tm, int key, int delta) {
        int nv = tm.getOrDefault(key, 0) + delta;
        if (nv <= 0) {
            tm.remove(key);
        } else {
            tm.put(key, nv);
        }
    }
}
