/*
LC#910
Given an array A of integers, for each integer A[i] we may choose any x with -K <= x <= K, and add x to A[i].

After this process, we have some array B.

Return the smallest possible difference between the maximum value of B and the minimum value of B.



Example 1:

Input: A = [1], K = 0
Output: 0
Explanation: B = [1]
Example 2:

Input: A = [0,10], K = 2
Output: 6
Explanation: B = [2,8]
Example 3:

Input: A = [1,3,6], K = 3
Output: 0
Explanation: B = [3,3,3] or B = [4,4,4]


Note:

1 <= A.length <= 10000
0 <= A[i] <= 10000
0 <= K <= 10000
 */

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class SmallestRangeII {
    // actually similar to "minimum deviation array", convert to n lists' minimum range cover (min..max cover one number from each array)
    public int smallestRangeII(int[] a, int k) {
        int n = a.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[0], y[0]));
        // value, array, index
        int max = 0;
        for (int i = 0; i < n; i++) {
            pq.offer(new int[]{a[i] - k, i, 0});
            max = Math.max(max, a[i] - k);
        }
        int res = Integer.MAX_VALUE;
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int topv = top[0];
            int topi = top[1];
            int topj = top[2];
            int diff = max - topv;
            //    System.out.println(topv+" "+max);
            res = Math.min(res, diff);
            if (topj == 0) {
                max = Math.max(max, a[topi] + k);
                pq.offer(new int[]{a[topi] + k, topi, 1});
            } else {
                break;
            }
        }
        return res;
    }
}