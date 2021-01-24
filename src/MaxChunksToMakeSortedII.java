import java.util.ArrayList;
import java.util.List;

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

class MaxChunksToMakeSortedIIAnotherWay {
    // use binary search to get the left most >, j. i and j must be in the same chunk
    public int maxChunksToSorted(int[] a) {
        int n = a.length;
        int[] left = new int[n];// left most >
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int pos = binarySearchFirstBigger(list, a, i);
            if (pos < list.size()) {
                left[i] = list.get(pos);
            } else {
                left[i] = n;
                if (list.isEmpty() || a[i] > a[list.size() - 1]) {
                    list.add(i);
                }
            }
        }
        int cb = n; // current left boundary
        int res = 0;
        for (int i = n - 1; i >= 0; i--) {
            cb = Math.min(cb, left[i]);
            if (i == cb || cb == n) {
                res++;
                cb = n;
            }
        }
        return res;
    }

    private int binarySearchFirstBigger(List<Integer> list, int[] a, int i) {
        int l = 0;
        int u = list.size() - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a[list.get(mid)] <= a[i]) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return l;
    }
}