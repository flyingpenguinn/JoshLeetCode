/*
LC#209
Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.

Example:

Input: s = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: the subarray [4,3] has the minimal length under the problem constraint.
Follow up:
If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
 */

public class MinimumSizeSubarraySum {

    // all positive hence we can two pointer
    public int minSubArrayLen(int s, int[] a) {
        int n = a.length;
        int high = -1;
        int low = 0;
        int sum = 0;
        int min = n + 1;
        while (true) {
            if (sum < s) {
                high++;
                if (high == n) {
                    break;
                }
                sum += a[high];
            } else {
                min = Math.min(min, high - low + 1);
                sum -= a[low];
                low++;
            }
        }
        return min > n ? 0 : min;
    }
}

class MinsizeSubarraySumBinarySearch {
    public int minSubArrayLen(int s, int[] a) {
        int n = a.length;
        int[] sum = new int[n];
        int min = n + 1;
        for (int i = 0; i < n; i++) {
            sum[i] = (i == 0 ? 0 : sum[i - 1]) + a[i];
        }
        for (int i = 0; i < n; i++) {
            if (sum[i] < s) {
                continue;
            }
            int maxj = -1;// -1,not 0
            int t = sum[i] - s;
            // max j,sumj <=t
            int br = bslnb(sum, 0, i - 1, t);
            maxj = Math.max(br, maxj);
            // maxj+1...i
            min = Math.min(min, i - maxj);
        }
        return min > n ? 0 : min;

    }

    int bslnb(int[] a, int l, int u, int t) {
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a[mid] <= t) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        // u<=, l>
        return u;
    }
}
