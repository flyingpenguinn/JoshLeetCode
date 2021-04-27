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
    // using same code as II: max on the left is <= min on the right then we can sort this segment
    public int maxChunksToSorted(int[] a) {
        int n = a.length;
        int[] right = new int[n];
        // min on the right including self
        right[n - 1] = a[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.min(right[i + 1], a[i]);
        }
        int left = -1;
        int res = 0;
        for (int i = 0; i < n; i++) {
            left = Math.max(left, a[i]);
            if (i == n - 1 || left <= right[i + 1]) {
                res++;
            }
        }
        return res;
    }
}
