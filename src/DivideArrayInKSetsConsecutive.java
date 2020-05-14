import java.util.TreeMap;

/*
LC#1296
Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into sets of k consecutive numbers
Return True if its possible otherwise return False.



Example 1:

Input: nums = [1,2,3,3,4,4,5,6], k = 4
Output: true
Explanation: Array can be divided into [1,2,3,4] and [3,4,5,6].
Example 2:

Input: nums = [3,2,1,2,3,4,3,4,5,9,10,11], k = 3
Output: true
Explanation: Array can be divided into [1,2,3] , [2,3,4] , [3,4,5] and [9,10,11].
Example 3:

Input: nums = [3,3,2,2,1,1], k = 3
Output: true
Example 4:

Input: nums = [1,2,3,4], k = 3
Output: false
Explanation: Each array should be divided in subarrays of size 3.


Constraints:

1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
1 <= k <= nums.length
 */
public class DivideArrayInKSetsConsecutive {
    public boolean isPossibleDivide(int[] a, int k) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int ai : a) {
            map.put(ai, map.getOrDefault(ai, 0) + 1);
        }
        int last = map.firstKey() - 1;
        int cur = 0;
        while (!map.isEmpty()) {
            if (cur < k) {
                int curi = last + 1;
                if (!map.containsKey(curi)) {
                    return false;
                }
                take(map, curi);
                last = curi;
                cur++;
            } else {
                last = map.firstKey() - 1;
                cur = 0;
            }
        }
        return cur == k;
    }

    private void take(TreeMap<Integer, Integer> map, Integer next) {
        Integer cur = map.get(next);
        if (cur == 1) {
            map.remove(next);
        } else {
            map.put(next, cur - 1);
        }
    }
}
