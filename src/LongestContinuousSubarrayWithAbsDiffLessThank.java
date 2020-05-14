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
    public int longestSubarray(int[] a, int limit) {
        int n = a.length;
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        int head = 0;
        int r = 0;
        for (int i = 0; i < n; i++) {
            update(tm, a[i], 1);
            int min = tm.firstKey();
            int max = tm.lastKey();
            if (Math.abs(max - min) <= limit) {
                r = Math.max(r, i - head + 1);
            } else {
                while (true) {
                    update(tm, a[head++], -1);
                    min = tm.firstKey();
                    max = tm.lastKey();
                    if (Math.abs(max - min) <= limit) {
                        break;
                    }
                }
            }
        }
        return r;
    }

    void update(TreeMap<Integer, Integer> tm, int t, int d) {
        int nv = tm.getOrDefault(t, 0) + d;
        if (nv <= 0) {
            tm.remove(t);
        } else {
            tm.put(t, nv);
        }
    }

}
