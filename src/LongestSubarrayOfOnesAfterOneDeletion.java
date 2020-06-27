/*
LC#1493
Given a binary array nums, you should delete one element from it.

Return the size of the longest non-empty subarray containing only 1's in the resulting array.

Return 0 if there is no such subarray.



Example 1:

Input: nums = [1,1,0,1]
Output: 3
Explanation: After deleting the number in position 2, [1,1,1] contains 3 numbers with value of 1's.
Example 2:

Input: nums = [0,1,1,1,0,1,1,0,1]
Output: 5
Explanation: After deleting the number in position 4, [0,1,1,1,1,1,0,1] longest subarray with value of 1's is [1,1,1,1,1].
Example 3:

Input: nums = [1,1,1]
Output: 2
Explanation: You must delete one element.
Example 4:

Input: nums = [1,1,0,0,1,1,1,0,1]
Output: 4
Example 5:

Input: nums = [0,0,0]
Output: 0


Constraints:

1 <= nums.length <= 10^5
nums[i] is either 0 or 1.
 */
public class LongestSubarrayOfOnesAfterOneDeletion {
    public int longestSubarray(int[] a) {
        int n = a.length;
        int low = 0;
        int high = -1;
        int zeros = 0;
        int max = 0;
        while (true) {
            if (zeros <= 1) {
                max = Math.max(max, high - low + 1);
                high++;
                if (high == n) {
                    break;
                }
                zeros += (a[high] == 0) ? 1 : 0;
            } else {
                zeros -= (a[low] == 0) ? 1 : 0;
                low++;
            }
        }
        return max - 1;  //return -1. this includes all 1 case
    }
}
