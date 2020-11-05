import base.ArrayUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/*
LC#1121
Given a non-decreasing array of positive integers nums and an integer K, find out if this array can be divided into one or more disjoint increasing subsequences of length at least K.



Example 1:

Input: nums = [1,2,2,3,3,4,4], K = 3
Output: true
Explanation:
The array can be divided into the two subsequences [1,2,3,4] and [2,3,4] with lengths at least 3 each.
Example 2:

Input: nums = [5,6,6,7,8], K = 3
Output: false
Explanation:
There is no way to divide the array using the conditions required.


Note:

1 <= nums.length <= 10^5
1 <= K <= nums.length
1 <= nums[i] <= 10^5
 */
public class DivideArrayIntoIncreasingSubsequences {
    // each number must belong to a k list. so as long as maxocc*k <=n we have a way to assign
    // assuming max occ is M, for each i assignit to i%M
    // k*M<=n => n/M>=k we must be able to find one
    public boolean canDivideIntoSubsequences(int[] a, int k) {
        int n = a.length;
        int cur = 1;
        int maxocc = 1;
        for (int i = 1; i < n; i++) {
            if (a[i] == a[i - 1]) {
                cur++;
            } else {
                maxocc = Math.max(maxocc, cur);
                cur = 1;
            }
        }
        maxocc = Math.max(maxocc, cur);
        if (k * maxocc > n) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new DivideArrayIntoIncreasingSubsequences().canDivideIntoSubsequences(ArrayUtils.read1d("5,6,6,7,8"), 3));
    }
}
