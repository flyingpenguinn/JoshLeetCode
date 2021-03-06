import java.util.HashMap;
import java.util.Map;

/*
LC#265
Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous subarray of size at least 2 that sums up to a multiple of k, that is, sums up to n*k where n is also an integer.



Example 1:

Input: [23, 2, 4, 6, 7],  k=6
Output: True
Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
Example 2:

Input: [23, 2, 6, 4, 7],  k=6
Output: True
Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.


Note:

The length of the array won't exceed 10,000.
You may assume the sum of all the numbers is in the range of a signed 32-bit integer.
 */
public class ContinuousSubarraySum {
    // k could be huge. use hashmap. we wont have more than n ks. k could be ==0. when mod think about ==0
    // note this is similar to LC#974, but there mod result can be of different sign, while here mod is of the same sign.
    // so we dont need to worry about complementing value like 3 and -2. but here k could be ==0
    public boolean checkSubarraySum(int[] a, int k) {
        // can handle negative numbers this way
        k = Math.abs(k);
        // mod and the first index when that happens
        Map<Integer, Integer> m = new HashMap<>();
        int preSum = 0;
        m.put(0, -1);
        for (int i = 0; i < a.length; i++) {
            preSum += a[i];
            int target = k == 0 ? preSum : preSum % k;
            if (target < 0) {
                // this will allow us handle negative numbers
                target += k;
            }
            Integer preIndex = m.get(target);
            if (preIndex != null && i - preIndex >= 2) { // i - pre >=2 because pre+1... i is the subarray
                return true;
            }
            m.putIfAbsent(target, i); // don't overrun old ones
        }
        return false;
    }
}
