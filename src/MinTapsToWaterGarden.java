/*
LC#1326
There is a one-dimensional garden on the x-axis. The garden starts at the point 0 and ends at the point n. (i.e The length of the garden is n).

There are n + 1 taps located at points [0, 1, ..., n] in the garden.

Given an integer n and an integer array ranges of length n + 1 where ranges[i] (0-indexed) means the i-th tap can water the area [i - ranges[i], i + ranges[i]] if it was open.

Return the minimum number of taps that should be open to water the whole garden, If the garden cannot be watered return -1.



Example 1:


Input: n = 5, ranges = [3,4,1,1,0,0]
Output: 1
Explanation: The tap at point 0 can cover the interval [-3,3]
The tap at point 1 can cover the interval [-3,5]
The tap at point 2 can cover the interval [1,3]
The tap at point 3 can cover the interval [2,4]
The tap at point 4 can cover the interval [4,4]
The tap at point 5 can cover the interval [5,5]
Opening Only the second tap will water the whole garden [0,5]
Example 2:

Input: n = 3, ranges = [0,0,0,0]
Output: -1
Explanation: Even if you activate all the four taps you cannot water the whole garden.
Example 3:

Input: n = 7, ranges = [1,2,1,0,2,1,0,1]
Output: 3
Example 4:

Input: n = 8, ranges = [4,0,0,0,0,0,0,0,4]
Output: 2
Example 5:

Input: n = 8, ranges = [4,0,0,0,4,0,0,0,4]
Output: 1


Constraints:

1 <= n <= 10^4
ranges.length == n + 1
0 <= ranges[i] <= 100
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MinTapsToWaterGarden {
    // first we find all intervals whose start point <= curstart. then try to extend them as far as possible
    // then next start is the end point of current round
    // note here next round must be able to concat with current round, but last tap seems to be fine... dont need to go n+1
    // similar to video stitching
    public int minTaps(int n, int[] a) {
        int tapCount = n + 1;
        int[][] taps = new int[tapCount][2];
        for (int i = 0; i < tapCount; i++) {
            taps[i][0] = i - a[i];
            taps[i][1] = i + a[i];
        }
        Arrays.sort(taps, (x, y) -> Integer.compare(x[0], y[0]));
        //  System.out.println(Arrays.deepToString(taps));
        int start = Math.max(taps[0][0], 0);
        int end = taps[0][1];
        if (start > 0) {
            return -1;
        }
        int res = 1;
        for (int i = 1; i < tapCount & end < n; i++) {
            if (taps[i][0] <= start) {
                end = Math.max(end, taps[i][1]);
            } else if (taps[i][0] > end) {
                break;
            } else {
                res++;
                start = end;
                end = taps[i][1];
            }
        }
        return end >= n ? res : -1;
    }
}
