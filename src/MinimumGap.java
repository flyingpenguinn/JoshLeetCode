import base.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
LC#164
Given an unsorted array, find the maximum difference between the successive elements in its sorted form.

Return 0 if the array contains less than 2 elements.

Example 1:

Input: [3,6,9,1]
Output: 3
Explanation: The sorted form of the array is [1,3,6,9], either
             (3,6) or (6,9) has the maximum difference 3.
Example 2:

Input: [10]
Output: 0
Explanation: The array contains less than 2 elements, therefore return 0.
Note:

You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
Try to solve it in linear time/space.
 */
public class MinimumGap {

    // put n numbers to n+1 buckets. must have one empty bucket. then the max gap is between buckets
    public int maximumGap(int[] a) {
        int n = a.length;
        if (n < 2) {
            return 0;
        }
        int min = a[0];
        int max = a[0];
        for (int i = 1; i < n; i++) {
            min = Math.min(min, a[i]);
            max = Math.max(max, a[i]);
        }
        if (max == min) {
            return 0;
        }
        int groups = n + 1;
        int bsize = (int) Math.ceil((max - min + 1) * 1.0 / groups);
        int[] bmax = new int[groups];
        Arrays.fill(bmax, -1);
        int[] bmin = new int[groups];
        Arrays.fill(bmin, Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            int bi = (a[i] - min) / bsize;
            bmax[bi] = Math.max(a[i], bmax[bi]);
            bmin[bi] = Math.min(a[i], bmin[bi]);
        }
        int pm = bmax[0];
        int maxgap = 0;
        for (int k = 1; k < groups; k++) {
            if (bmax[k] == -1) {
                // empty
                continue;
            }
            maxgap = Math.max(maxgap, bmin[k] - pm);
            pm = bmax[k];
        }
        return maxgap;
    }

    public static void main(String[] args) {
        System.out.println(new MinimumGap().maximumGap(ArrayUtils.read1d("[1,10000000]")));
    }
}

class MaxGapRadixSort {
    // On(10*n) radix sort also linear
    //Use the radix sort algorithm to sort based on each digit from Least Significant Bit
    //(LSB) to Most Significant Bit (MSB), that's exactly what's showing
    public int maximumGap(int[] a) {
        int n = a.length;
        List<Integer> r = new ArrayList<>();
        int max = 0;

        for (int i = 0; i < n; i++) {
            r.add(a[i]);
            max = Math.max(max, a[i]);
        }
        List<Integer>[] buc = new ArrayList[10];
        // at most 10 digits
        int base = 1;
        for (int i = 0; i <= 9; i++) {
            if (base > max) {
                break;
            }
            for (int j = 0; j < 10; j++) {
                buc[j] = new ArrayList<>();
            }
            for (int j = 0; j < n; j++) {
                int c = dig(r.get(j), base);
                buc[c].add(r.get(j));
            }
            r.clear();
            for (int j = 0; j < 10; j++) {
                r.addAll(buc[j]);
            }
            base *= 10;
        }
        int maxg = 0;
        for (int i = 1; i < r.size(); i++) {
            maxg = Math.max(maxg, r.get(i) - r.get(i - 1));
        }
        return maxg;
    }

    int dig(int n, int base) {
        return (n / base) % 10;
    }
}
