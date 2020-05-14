/*
LC#769
Given an array arr that is a permutation of [0, 1, ..., arr.length - 1], we split the array into some number of "chunks" (partitions), and individually sort each chunk.  After concatenating them, the result equals the sorted array.

What is the most number of chunks we could have made?

Example 1:

Input: arr = [4,3,2,1,0]
Output: 1
Explanation:
Splitting into two or more chunks will not return the required result.
For example, splitting into [4, 3], [2, 1, 0] will result in [3, 4, 0, 1, 2], which isn't sorted.
Example 2:

Input: arr = [1,0,2,3,4]
Output: 4
Explanation:
We can split into two chunks, such as [1, 0], [2, 3, 4].
However, splitting into [1, 0], [2], [3], [4] is the highest number of chunks possible.
Note:

arr will have length in range [1, 10].
arr[i] will be a permutation of [0, 1, ..., arr.length - 1].

 */
public class MaxChunksToMakeSorted {
    public int maxChunksToSorted(int[] a) {
        // same code as II
        int n = a.length;
        int[] min = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            min[i] = (i == n - 1) ? a[i] : Math.min(min[i + 1], a[i]);
        }
        int high = -1;
        int low = 0;
        int r = 1;
        int cl = -1;
        while (low < n && high < n) {
            if (low == high + 1) {
                high++;
                cl = a[high];
            } else {
                int lf = high == n - 1 ? -1 : min[high + 1];
                if (lf < cl) {
                    // intersecting...
                    high++;
                    if (high < n) {
                        cl = Math.max(cl, a[high]);
                    }
                } else {
                    r++;
                    low = high + 1;
                    cl = -1;
                }
            }
        }
        return r;
    }
}
