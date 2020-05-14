import java.util.ArrayList;
import java.util.List;

/*
LC#300
Given an unsorted array of integers, find the length of longest increasing subsequence.

Example:

Input: [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
Note:

There may be more than one LIS combination, it is only necessary for you to return the length.
Your algorithm should run in O(n2) complexity.
Follow up: Could you improve it to O(n log n) time complexity?
 */
public class LongestIncreasingSubsequence {
    // the nlogn way... binary search find the first bigger, or the value itself
    public int lengthOfLIS(int[] a) {
        List<Integer> r = new ArrayList<>();
        int n = a.length;
        for (int i = 0; i < n; i++) {
            // first >,or found
            int index = bs(r, a[i]);
            if (index == r.size()) {
                r.add(a[i]);
            } else {
                r.set(index, a[i]);
            }
        }
        return r.size();
    }

    int bs(List<Integer> a, int t) {
        int l = 0;
        int u = a.size() - 1;
        while (l <= u) {
            int m = l + (u - l) / 2;
            if (a.get(m) == t) {
                return m;
            } else if (a.get(m) < t) {
                l = m + 1;
            } else {
                u = m - 1;
            }
        }
        return l;
    }
}
