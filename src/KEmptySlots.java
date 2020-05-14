import base.ArrayUtils;

import java.io.*;
import java.util.*;

/*
LC#683
You have N bulbs in a row numbered from 1 to N. Initially, all the bulbs are turned off. We turn on exactly one bulb everyday until all bulbs are on after N days.

You are given an array bulbs of length N where bulbs[i] = x means that on the (i+1)th day, we will turn on the bulb at position x where i is 0-indexed and x is 1-indexed.

Given an integer K, find out the minimum day number such that there exists two turned on bulbs that have exactly K bulbs between them that are all turned off.

If there isn't such day, return -1.



Example 1:

Input:
bulbs: [1,3,2]
K: 1
Output: 2
Explanation:
On the first day: bulbs[0] = 1, first bulb is turned on: [1,0,0]
On the second day: bulbs[1] = 3, third bulb is turned on: [1,0,1]
On the third day: bulbs[2] = 2, second bulb is turned on: [1,1,1]
We return 2 because on the second day, there were two on bulbs with one off bulb between them.
Example 2:

Input:
bulbs: [1,2,3]
K: 1
Output: -1


Note:

1 <= N <= 20000
1 <= bulbs[i] <= N
bulbs is a permutation of numbers from 1 to N.
0 <= K <= 20000
 */
public class KEmptySlots {

    public int kEmptySlots(int[] b, int k) {
        TreeSet<Integer> on = new TreeSet<>();
        int n = b.length;
        for (int i = 0; i < n; i++) {
            int turned = b[i];
            Integer before = on.lower(turned);
            Integer after = on.higher(turned);
            if (before != null && turned - before == k + 1) {
                return i + 1;
            }
            if (after != null && after - turned == k + 1) {
                return i + 1;
            }
            on.add(turned);
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new KEmptySlotsSlidingWindow().kEmptySlots(ArrayUtils.read1d("[10,1,6,4,2,8,9,7,5,3]"), 1));
        System.out.println(new KEmptySlotsSlidingWindow().kEmptySlots(ArrayUtils.read1d("3,9,2,8,1,6,10,5,4,7"), 1));
        System.out.println(new KEmptySlotsSlidingWindow().kEmptySlots(ArrayUtils.read1d("6, 5, 8, 9, 7, 1, 10, 2, 3, 4"), 2));
        System.out.println(new KEmptySlotsSlidingWindow().kEmptySlots(ArrayUtils.read1d("1,3,2"), 1));
    }
}


class KEmptySlotsSlidingWindow {
    // key insight is days left and days right must be the smallest 2 in their window of size k,since they are the first 2 to be lit
    // below is an example to check if there is a window = size k where mins are at the two ends
    public int kEmptySlots(int[] bulbs, int k) {
        // on which day is this one turned on
        int n = bulbs.length;
        int[] bm = new int[n];
        for (int i = 0; i < n; i++) {
            bm[bulbs[i] - 1] = i;
        }
        //    System.out.println(Arrays.toString(bm));
        int left = 0;
        int right = k + 1;
        int min = n + 1;
        for (int i = 0; i < n; i++) {
            if (i == left) {
                continue;
            } else if (bm[i] > bm[left] && bm[i] > bm[right]) {
                // left and right still promising
                continue;
            }
            // otherwise if we are at the right boundary, use the max date among the two
            else if (i == right) {
                // can't return yet must keep looking for possible smaller ones
                int cur = Math.max(bm[right] + 1, bm[left] + 1);
                min = Math.min(min, cur);
            }
            // otherwise lefts in 0...i-1 are hopeless to be left, because these values >left and right, but this value is not.
            // so this value is smaller than any possible left
            // note this is called for both violation part and hit part
            left = i;
            right = i + k + 1;
            if (right >= n) {
                break;
            }
        }
        return min > n ? -1 : min;
    }
}