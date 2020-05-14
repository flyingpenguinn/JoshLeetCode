/*
LC#768
This question is the same as "Max Chunks to Make Sorted" except the integers of the given array are not necessarily distinct, the input array could be up to length 2000, and the elements could be up to 10**8.

Given an array arr of integers (not necessarily distinct), we split the array into some number of "chunks" (partitions), and individually sort each chunk.  After concatenating them, the result equals the sorted array.

What is the most number of chunks we could have made?

Example 1:

Input: arr = [5,4,3,2,1]
Output: 1
Explanation:
Splitting into two or more chunks will not return the required result.
For example, splitting into [5, 4], [3, 2, 1] will result in [4, 5, 1, 2, 3], which isn't sorted.
Example 2:

Input: arr = [2,1,3,4,4]
Output: 4
Explanation:
We can split into two chunks, such as [2, 1], [3, 4, 4].
However, splitting into [2, 1], [3], [4], [4] is the highest number of chunks possible.
Note:

arr will have length in range [1, 2000].
arr[i] will be an integer in range [0, 10**8].
 */
public class MaxChunksToMakeSortedII {
    // if maxleft[i] <= minright[i+1] we got a new chunk
    public int maxChunksToSorted(int[] a) {
        int n = a.length;
        if (n == 0) {
            return 0;
        }
        int[] minRight = new int[n + 1];
        minRight[n] = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            minRight[i] = Math.min(minRight[i + 1], a[i]);
        }
        int maxleft = Integer.MIN_VALUE;
        int r = 0;
        for (int i = 0; i < n; i++) {
            maxleft = Math.max(maxleft, a[i]);
            if (maxleft <= minRight[i + 1]) {
                r++;
            }
        }
        return r;
    }
}

class MaxChunksToMakeSortedIISlidingWindow {

    // I and II combined
    // sliding window if curmax<min we found what we want otherwise extend it
    public int maxChunksToSorted(int[] a) {
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